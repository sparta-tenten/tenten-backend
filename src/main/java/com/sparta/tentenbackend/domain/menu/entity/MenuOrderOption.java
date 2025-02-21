package com.sparta.tentenbackend.domain.menu.entity;

import com.sparta.tentenbackend.global.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "p_menu_order_option")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuOrderOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "menu_order_id")
    private MenuOrder menuOrder;

    @ManyToOne
    @JoinColumn(name = "menu_option_id")
    private MenuOption menuOption;

    private String createdBy;
    private String updatedBy;
    private String deletedBy;

    public MenuOrderOption(MenuOrder menuOrder, MenuOption menuOption) {
        this.menuOrder = menuOrder;
        this.menuOption = menuOption;
    }
}
