
package com.sparta.tentenbackend.domain.menu.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "p_menu_order_option")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuOrderOption {
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

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date updatedAt;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date deletedAt;
}

