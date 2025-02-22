package com.sparta.tentenbackend.domain.user.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.tentenbackend.domain.delivery_address.entity.DeliveryAddress;
import com.sparta.tentenbackend.domain.region.entity.Town;
import com.sparta.tentenbackend.global.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.awt.print.Book;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "p_user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String userName;            // TODO: 최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)
    @Column(nullable = false)
    @JsonIgnore
    private String password;            // TODO:  최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자
    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private UserRoleEnum role;              // CUSTOMER, OWNER, MANAGER, MASTER
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String detailAddress;
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "town_code")
    private Town town;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<DeliveryAddress> deliveryAddressList;

    public User(String userName, String password, String email, UserRoleEnum role, String address,
        String detailAddress, String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.address = address;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
    }


    /*
     * @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
     * @JoinColumn(name = "store_id")
     * private Store store;
     */

    /*
     * @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE,orphanRemoval = true)
     * private List<Order> orders;
     */

    /*
     * @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
     * private List<DeliveryAddress> deliveryAddress = new ArrayList<>();
     */


    /*
     * @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
     * private List<Review> reviews;
     */


    /*
     * @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
     * private List<OwnerReview> ownerReview = new ArrayList<>();
     */

}



