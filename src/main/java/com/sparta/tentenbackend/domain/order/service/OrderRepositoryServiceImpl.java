package com.sparta.tentenbackend.domain.order.service;

import com.sparta.tentenbackend.domain.order.dto.OrderSearchRequest;
import com.sparta.tentenbackend.domain.order.dto.TemporaryOrderRequest;
import com.sparta.tentenbackend.domain.order.entity.Order;
import com.sparta.tentenbackend.domain.order.repository.OrderRepository;
import com.sparta.tentenbackend.domain.order.repository.OrderRepositoryQuery;
import com.sparta.tentenbackend.domain.store.entity.Store;
import com.sparta.tentenbackend.domain.store.service.StoreService;
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

    // TODO User 아이디 기준으로 찾기로 수정
    @Override
    public Page<Order> getOrderList(OrderSearchRequest orderSearchRequest) {
        return orderRepositoryQuery.getOrderList(orderSearchRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public Order getOrderById(UUID orderId) {
        return orderRepository.findById(orderId).orElseThrow(
            () -> new NotFoundException("존재하지 않는 주문입니다.")
        );
    }

    // TODO User 추가
    @Override
    @Transactional
    public Order createTemporaryOrder(TemporaryOrderRequest req) {
        Store store = storeService.getStoreById(req.getStoreId());
        Order order = new Order(req.getDeliveryType(), store);

        return orderRepository.save(order);
    }
}
