package com.sparta.tentenbackend.domain.menu.entity;
import com.sparta.tentenbackend.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigInteger;
import java.util.UUID;

@Entity
@Table(name = "p_menu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private BigInteger price;

    @Column(columnDefinition = "TEXT")
    private String description;

//    @Enumerated(EnumType.STRING)
//    private MenuStatus status; // ENUM (판매중, 하루품절, 숨김)

    private String image;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

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
