package com.sparta.tentenbackend.domain.order.service;

import com.sparta.tentenbackend.domain.menu.entity.Menu;
import com.sparta.tentenbackend.domain.menu.entity.MenuOption;
import com.sparta.tentenbackend.domain.menu.entity.MenuOrder;
import com.sparta.tentenbackend.domain.menu.entity.MenuOrderOption;
import com.sparta.tentenbackend.domain.menu.repository.MenuOptionRepository;
import com.sparta.tentenbackend.domain.menu.repository.MenuRepository;
import com.sparta.tentenbackend.domain.order.dto.OrderMenuRequest;
import com.sparta.tentenbackend.domain.order.dto.OrderRequest;
import com.sparta.tentenbackend.domain.order.entity.DeliveryType;
import com.sparta.tentenbackend.domain.order.entity.Order;
import com.sparta.tentenbackend.domain.order.entity.OrderStatus;
import com.sparta.tentenbackend.domain.order.repository.OrderRepository;
import com.sparta.tentenbackend.domain.payment.entity.Payment;
import com.sparta.tentenbackend.domain.payment.repository.PaymentRepository;
import com.sparta.tentenbackend.domain.payment.service.PaymentService;
import com.sparta.tentenbackend.domain.store.entity.Store;
import com.sparta.tentenbackend.domain.store.service.StoreService;
import com.sparta.tentenbackend.domain.user.entity.User;
import com.sparta.tentenbackend.global.exception.BadRequestException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderRepositoryService orderRepositoryService;
    private final MenuRepository menuRepository;
    private final MenuOptionRepository menuOptionRepository;
    private final PaymentService paymentService;
    private final StoreService storeService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public Order orderForCustomer(UUID orderId, OrderRequest req, User user) {
        Order order = orderRepositoryService.getOrderById(orderId);

        if (!paymentService.isPaymentCompleted(req.getOrderId()) ||
            !order.getOrderStatus().equals(OrderStatus.PAYMENT_COMPLETED)) {
            throw new BadRequestException("결제가 완료 되지 않은 주문입니다.");
        }

        if (!order.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("유저가 일치하지 않습니다!");
        }

        if (!order.getStore().getId().equals(req.getStoreId())) {
            throw new BadRequestException("가게가 일치하지 않습니다!");
        }

        setDeliveryInfo(req, order);
        order.setRequest(req.getRequest());
        order.setOrderStatus(OrderStatus.WAITING_ORDER_RECEIVE);
        order.setOrderedAt(LocalDateTime.now());
        setMenuList(order, req.getOrderMenuRequestList());

        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public void cancelOrder(UUID orderId, User user) {
        Order order = orderRepositoryService.getOrderById(orderId);

        if (!order.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("유저 정보가 일치하지 않습니다!");
        }

        if (order.getOrderStatus() != OrderStatus.WAITING_ORDER_RECEIVE ||
            LocalDateTime.now().isAfter(order.getOrderedAt().plusMinutes(5))) {
            throw new BadRequestException("주문 접수 대기 중이거나 주문 요청 후 5분 전에 취소할 수 있습니다!");
        }

        order.cancel(user);
    }

    @Override
    @Transactional
    public void cancelOrderForOwner(UUID orderId, User owner) {
        Order order = orderRepositoryService.getOrderById(orderId);

        if (order.getOrderStatus().equals(OrderStatus.DELIVERY_COMPLETED) ||
            order.getOrderStatus().equals(OrderStatus.PICKED_UP)) {
            throw new BadRequestException("주문 취소 가능한 상태가 아닙니다!");
        }

        order.cancel(owner);
    }

    @Override
    @Transactional
    public void updateOrderStatus(UUID orderId) {
        Order order = orderRepositoryService.getOrderById(orderId);
        OrderStatus status = order.getOrderStatus();

        switch (status) {
            case ORDER_RECEIVED:
                order.setOrderStatus(OrderStatus.COOKING);
                break;
            case COOKING:
                order.setOrderStatus(OrderStatus.COOKING_COMPLETED);
                break;
            case COOKING_COMPLETED:
                if (order.getDeliveryType().equals(DeliveryType.DELIVERY)) {
                    order.setOrderStatus(OrderStatus.DELIVERING);
                } else {
                    order.setOrderStatus(OrderStatus.PICKED_UP);
                }
                break;
            case DELIVERING:
                order.setOrderStatus(OrderStatus.DELIVERY_COMPLETED);
                break;
            default:
                throw new BadRequestException("주문 상태를 업데이트 할 수 없습니다.");

        }
    }


    @Override
    @Transactional
    public void acceptOrder(UUID orderId) {
        Order order = orderRepositoryService.getOrderById(orderId);

        if (!order.getOrderStatus().equals(OrderStatus.WAITING_ORDER_RECEIVE)) {
            throw new BadRequestException("주문 수락이 가능한 상태가 아닙니다!");
        }

        order.setOrderStatus(OrderStatus.ORDER_RECEIVED);
        order.accept();
    }

    @Override
    @Transactional
    public void rejectOrder(UUID orderId) {
        Order order = orderRepositoryService.getOrderById(orderId);

        if (!order.getOrderStatus().equals(OrderStatus.WAITING_ORDER_RECEIVE)) {
            throw new BadRequestException("주문 거절이 가능한 상태가 아닙니다!");
        }

        order.setOrderStatus(OrderStatus.REJECTED);
    }

    @Override
    @Transactional
    public Order deliveryOrderForOwner(OrderRequest req, User owner) {
        Store store = storeService.getStoreById(req.getStoreId());
        Order order = new Order(DeliveryType.DELIVERY, store);

        setDeliveryInfo(req, order);
        order.setRequest(req.getRequest());
        order.setOrderStatus(OrderStatus.ORDER_RECEIVED);
        order.setOrderedAt(LocalDateTime.now());
        order.setMenuOrderList(new ArrayList<>());
        setMenuList(order, req.getOrderMenuRequestList());

        Payment payment = Payment.createWaitingPayment(order.getTotalPrice(), order);

        orderRepository.save(order);
        paymentRepository.save(payment);

        return order;
    }

    @Override
    @Transactional
    public Order pickupOrderForOwner(OrderRequest req, User owner) {
        Store store = storeService.getStoreById(req.getStoreId());
        Order order = new Order(DeliveryType.DELIVERY, store);

        order.setOrderStatus(OrderStatus.ORDER_RECEIVED);
        order.setOrderedAt(LocalDateTime.now());
        order.setMenuOrderList(new ArrayList<>());
        setMenuList(order, req.getOrderMenuRequestList());

        return orderRepository.save(order);
    }

    private void setDeliveryInfo(OrderRequest req, Order order) {
        if (order.getDeliveryType().equals(DeliveryType.DELIVERY)) {
            if (req.getDeliveryAddress() == null || req.getPhoneNumber() == null) {
                throw new BadRequestException("배달 주문 시 주소와 전화번호가 필요합니다.");
            }
            order.setDeliveryAddress(req.getDeliveryAddress());
            order.setPhoneNumber(req.getPhoneNumber());
        }
    }

    private void setMenuList(Order order, List<OrderMenuRequest> orderMenuRequestList) {
        List<UUID> menuIdList = orderMenuRequestList.stream().map(OrderMenuRequest::getId)
            .toList();
        Map<UUID, Menu> menuMap = menuRepository.findAllByIdIn(menuIdList).stream()
            .collect(Collectors.toMap(Menu::getId, menu -> menu));

        List<UUID> optionIdList = orderMenuRequestList.stream()
            .flatMap(
                orderMenuRequest -> Optional.ofNullable(orderMenuRequest.getOptionIdList()).orElse(
                    Collections.emptyList()).stream()).toList();

        Map<UUID, List<MenuOption>> optionMap = menuOptionRepository.findAllByIdIn(optionIdList)
            .stream()
            .collect(Collectors.groupingBy(op -> op.getMenu().getId()));

        List<MenuOrder> menuOrderList = new ArrayList<>();
        long totalPrice = 0L;
        for (OrderMenuRequest menuReq : orderMenuRequestList) {
            Menu menu = menuMap.get(menuReq.getId());
            MenuOrder menuOrder = new MenuOrder(menu, order, menuReq.getQuantity());

            List<MenuOption> menuOptionList = optionMap.get(menuOrder.getMenu().getId());

            long menuPrice = menu.getPrice() * menuOrder.getQuantity();
            if (menuOptionList != null) {
                menuPrice += menuOptionList.stream().map(MenuOption::getPrice)
                    .reduce(0L, Long::sum);
                menuOrder.setMenuOrderOptionList(menuOptionList.stream()
                    .map(menuOption -> new MenuOrderOption(menuOrder, menuOption)).toList());
            }
            totalPrice += menuPrice;
            order.getMenuOrderList().add(menuOrder);
        }
        order.setTotalPrice(totalPrice);
    }
}
