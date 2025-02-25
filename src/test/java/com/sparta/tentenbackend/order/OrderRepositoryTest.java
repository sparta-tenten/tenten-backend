package com.sparta.tentenbackend.order;


import com.sparta.tentenbackend.domain.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@ActiveProfiles("test")
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

//    @Test
//    @DisplayName("order insert test")
//    @Transactional
//    void orderInsert() {
//        Order order = new Order(10000L, DeliveryType.DELIVERY, OrderType.ONLINE,
//            OrderStatus.ORDER_RECEIVED);
//
//        Payment payment = new Payment(PaymentStatus.COMPLETED, 10000L);
//        order.setPayment(payment);
//
//        Order savedOrder = orderRepository.save(order);
//        assertEquals(order, savedOrder);
//        assertEquals(payment, savedOrder.getPayment());
//    }
}
