package com.sparta.tentenbackend.domain.region.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.UUID;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "p_region", uniqueConstraints = {
    @UniqueConstraint(name = "uniqueRegion", columnNames = {"city", "district","street"})
})
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false,unique = true)
    private String city;
    @Column(nullable = false,unique = true)
    private String district;
    @Column(nullable = false,unique = true)
    private String street;



    /*
     * @OneToOne(mappedBy = "region", fetch = FetchType.LAZY)
     * @JoinColumn(name = "user_id")
     * private User user;
     */


    /*
     * @OneToOne(mappedBy = "region", fetch = FetchType.LAZY)
     * @JoinColumn(name = "store_id")
     * private Store store;
     */

    /*
     * @OneToOne(mappedBy = "region", fetch = FetchType.LAZY)
     * @JoinColumn(name = "deliveryAddress_id")
     * private DeliveryAddress deliveryAddress;
     */



}
