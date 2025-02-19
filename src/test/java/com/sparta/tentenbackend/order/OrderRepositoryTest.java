package com.sparta.tentenbackend.order;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sparta.tentenbackend.domain.delivery_address.order.entity.DeliveryType;
import com.sparta.tentenbackend.domain.delivery_address.order.entity.Order;
import com.sparta.tentenbackend.domain.delivery_address.order.entity.OrderStatus;
import com.sparta.tentenbackend.domain.delivery_address.order.entity.OrderType;
import com.sparta.tentenbackend.domain.delivery_address.order.repository.OrderRepository;
import com.sparta.tentenbackend.domain.payment.entity.Payment;
import com.sparta.tentenbackend.domain.payment.entity.PaymentStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @DisplayName("order insert test")
    @Transactional
    void orderInsert() {
        Order order = new Order(10000L, DeliveryType.DELIVERY, OrderType.ONLINE,
            OrderStatus.ORDER_RECEIVED);

        Payment payment = new Payment(PaymentStatus.COMPLETED, 10000L);
        order.setPayment(payment);

        Order savedOrder = orderRepository.save(order);
        assertEquals(order, savedOrder);
        assertEquals(payment, savedOrder.getPayment());
    }
}
