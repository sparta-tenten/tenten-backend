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
import com.sparta.tentenbackend.domain.payment.service.PaymentService;
import com.sparta.tentenbackend.domain.user.entity.User;
import com.sparta.tentenbackend.global.exception.BadRequestException;
import com.sparta.tentenbackend.global.exception.UnauthorizedException;
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

    // TODO User(주문한 사람) 추가
    @Override
    @Transactional
    public Order orderForCustomer(OrderRequest req) {
        if (!paymentService.isPaymentCompleted(req.getOrderId())) {
            throw new BadRequestException("결제가 완료 되지 않은 주문입니다.");
        }

        Order order = orderRepositoryService.getOrderById(req.getOrderId());
        order.setRequest(req.getRequest());
        order.setOrderStatus(OrderStatus.WAITING_ORDER_RECEIVE);
        order.setDeliveryAddress(req.getDeliveryAddress());
        order.setPhoneNumber(req.getPhoneNumber());
        setMenuList(order, req.getOrderMenuRequestList());

        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public void cancelOrder(UUID orderId) {
        Order order = orderRepositoryService.getOrderById(orderId);
        // TODO 유저 검증 로직 추가
        // TODO 유저가 CUSTOMER라면 주문 접수 대기중일때만 취소 가능하도록 && 주문 생성 후 5분 이내에만 취소 가능하도록
        // TODO cancel()에 파라미터로 User 추가
//        order.cancel();
    }

    @Override
    @Transactional
    public void updateOrderStatus(UUID orderId, User owner) {
        Order order = orderRepositoryService.getOrderById(orderId);
        OrderStatus status = order.getOrderStatus();

        checkOwner(order, owner);

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
    public void setMenuList(Order order, List<OrderMenuRequest> orderMenuRequestList) {
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


    @Override
    @Transactional
    public void acceptOrder(UUID orderId, User owner) {
        Order order = orderRepositoryService.getOrderById(orderId);

        if (!order.getStore().getUser().getId().equals(owner.getId())) {
            throw new UnauthorizedException("가게 주인이 아닙니다!");
        }

        if (!order.getOrderStatus().equals(OrderStatus.WAITING_ORDER_RECEIVE)) {
            throw new BadRequestException("주문 수락이 가능한 상태가 아닙니다!");
        }

        order.setOrderStatus(OrderStatus.ORDER_RECEIVED);
    }

    @Override
    @Transactional
    public void rejectOrder(UUID orderId, User owner) {
        Order order = orderRepositoryService.getOrderById(orderId);

        if (!order.getStore().getUser().getId().equals(owner.getId())) {
            throw new UnauthorizedException("가게 주인이 아닙니다!");
        }

        if (!order.getOrderStatus().equals(OrderStatus.WAITING_ORDER_RECEIVE)) {
            throw new BadRequestException("주문 거절이 가능한 상태가 아닙니다!");
        }

        order.setOrderStatus(OrderStatus.REJECTED);
    }

    // TODO AOP로 빼기
    @Override
    public void checkOwner(Order order, User owner) {
        if (!order.getStore().getUser().getId().equals(owner.getId())) {
            throw new UnauthorizedException("가게 주인이 아닙니다!");
        }
    }
}
