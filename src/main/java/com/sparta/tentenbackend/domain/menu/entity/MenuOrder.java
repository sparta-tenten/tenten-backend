package com.sparta.tentenbackend.domain.menu.entity;

import com.sparta.tentenbackend.domain.order.entity.Order;
import com.sparta.tentenbackend.global.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "p_menu_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuOrder extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToMany(mappedBy = "menuOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuOrderOption> menuOrderOptionList;

    private String createdBy;
    private String updatedBy;
    private String deletedBy;

    public MenuOrder(Menu menu, Order order, Long quantity) {
        this.menu = menu;
        this.order = order;
        this.quantity = quantity;
    }
}
