package com.sparta.tentenbackend.domain.order.entity;

import com.sparta.tentenbackend.domain.menu.entity.MenuOrder;
import com.sparta.tentenbackend.domain.order.dto.OrderRequest;
import com.sparta.tentenbackend.domain.payment.entity.Payment;
import com.sparta.tentenbackend.domain.store.entity.Store;
import com.sparta.tentenbackend.domain.user.entity.User;
import com.sparta.tentenbackend.domain.user.entity.UserRoleEnum;
import com.sparta.tentenbackend.global.BaseEntity;
import com.sparta.tentenbackend.global.exception.BadRequestException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@Entity
@Table(name = "p_order")
@NoArgsConstructor
@DynamicUpdate
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Long totalPrice = 0L;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToOne(mappedBy = "order", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Payment payment;

    private String deliveryAddress;
    private String phoneNumber;
    private String request;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuOrder> menuOrderList;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime cancelledAt;
    private String cancelledBy;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime acceptedAt;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime orderedAt;

    public Order(Long totalPrice, DeliveryType deliveryType, OrderType orderType,
        OrderStatus orderStatus) {
        this.totalPrice = totalPrice;
        this.deliveryType = deliveryType;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
    }

    public Order(OrderRequest req, UserRoleEnum userRole, Store store) {
        this.store = store;
        this.deliveryType = req.getDeliveryType();
        this.request = req.getRequest();
        this.orderType = req.getOrderType();
        if (userRole.equals(UserRoleEnum.CUSTOMER)) {
            this.orderStatus = OrderStatus.WAITING_ORDER_RECEIVE;
        } else {
            this.orderStatus = OrderStatus.ORDER_RECEIVED;
        }
        if (req.getDeliveryType().equals(DeliveryType.DELIVERY)) {
            if (req.getDeliveryAddress() == null || req.getPhoneNumber() == null) {
                throw new BadRequestException("배달 주문 시 주소와 전화번호가 필요합니다.");
            }
            this.deliveryAddress = req.getDeliveryAddress();
            this.phoneNumber = req.getPhoneNumber();
        }
    }

    public Order(DeliveryType deliveryType, Store store) {
        this.deliveryType = deliveryType;
        this.orderType = OrderType.ONLINE;
        this.store = store;
        this.orderStatus = OrderStatus.WAITING_PAYMENT;
    }

    public Order(DeliveryType deliveryType, Store store, User user) {
        this.deliveryType = deliveryType;
        this.orderType = OrderType.ONLINE;
        this.store = store;
        this.orderStatus = OrderStatus.WAITING_PAYMENT;
        this.user = user;
    }

    public void cancel(User user) {
        this.orderStatus = OrderStatus.CANCELLED;
        this.cancelledAt = LocalDateTime.now();
        this.cancelledBy = user.getEmail();
    }

    public void accept() {
        this.orderStatus = OrderStatus.ORDER_RECEIVED;
        this.acceptedAt = LocalDateTime.now();
    }
}
