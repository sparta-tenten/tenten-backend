package com.sparta.tentenbackend.domain.menu.entity;

import com.sparta.tentenbackend.domain.order.entity.Order;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigInteger;
import java.util.UUID;

@Entity
@Table(name = "p_menu_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private BigInteger quantity;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private String createdBy;
    private String updatedBy;
    private String deletedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date updatedAt;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date deletedAt;
}
