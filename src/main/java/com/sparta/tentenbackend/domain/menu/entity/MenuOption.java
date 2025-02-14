package com.sparta.tentenbackend.domain.menu.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigInteger;
import java.util.UUID;

@Entity
@Table(name = "p_menu_option")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuOption {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String category;

    private BigInteger price;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

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
