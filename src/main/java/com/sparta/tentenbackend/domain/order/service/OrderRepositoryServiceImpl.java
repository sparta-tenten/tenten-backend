package com.sparta.tentenbackend.domain.order.service;

import com.sparta.tentenbackend.domain.order.dto.OrderSearchRequest;
import com.sparta.tentenbackend.domain.order.dto.TemporaryOrderRequest;
import com.sparta.tentenbackend.domain.order.entity.Order;
import com.sparta.tentenbackend.domain.order.entity.OrderStatus;
import com.sparta.tentenbackend.domain.order.repository.OrderRepository;
import com.sparta.tentenbackend.domain.order.repository.OrderRepositoryQuery;
import com.sparta.tentenbackend.domain.store.entity.Store;
import com.sparta.tentenbackend.domain.store.service.StoreService;
import com.sparta.tentenbackend.domain.user.entity.User;
import com.sparta.tentenbackend.global.exception.BadRequestException;
import com.sparta.tentenbackend.global.exception.NotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderRepositoryServiceImpl implements OrderRepositoryService {

    private final OrderRepositoryQuery orderRepositoryQuery;
    private final OrderRepository orderRepository;
    private final StoreService storeService;

    // TODO 사장님 id 검증 로직 추가하기
    @Override
    @Transactional(readOnly = true)
    public Page<Order> getOrderListByStoreId(UUID storeId, OrderSearchRequest orderSearchRequest) {
        return orderRepositoryQuery.getOrderListByStoreId(storeId, orderSearchRequest);
    }

    @Override
    public Page<Order> getOrderList(OrderSearchRequest orderSearchRequest, User user) {
        return orderRepositoryQuery.getOrderList(orderSearchRequest, user);
    }

    @Override
    @Transactional(readOnly = true)
    public Order getOrderById(UUID orderId) {
        return orderRepository.findById(orderId).orElseThrow(
            () -> new NotFoundException("존재하지 않는 주문입니다.")
        );
    }

    @Override
    @Transactional
    public Order createTemporaryOrder(TemporaryOrderRequest req, User user) {
        Store store = storeService.getStoreById(req.getStoreId());
        Order order = new Order(req.getDeliveryType(), store);

        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public void deleteTemporaryOrder(UUID orderId, User user) {
        Order order = getOrderById(orderId);

        if (!order.getOrderStatus().equals(OrderStatus.WAITING_PAYMENT) ||
            !order.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("잘못된 요청입니다!");
        }

        orderRepository.deleteById(orderId);
    }
}
