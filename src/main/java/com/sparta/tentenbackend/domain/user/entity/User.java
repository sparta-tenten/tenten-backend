package com.sparta.tentenbackend.domain.user.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "p_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String userName;            // 요구사항 : 최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)
    @Column(nullable = false)
    @JsonIgnore
    private String password;            // 요구사항 :  최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자
    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private UserRoleEnum role;              // CUSTOMER, OWNER, MANAGER, MASTER
    @Column(nullable = false)
    private String address;
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    /*
     * @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
     * @JoinColumn(name = "region_id")
     * private Region region;
     */

    /*
     * @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
     * @JoinColumn(name = "store_id")
     * private Store store;
     */

    /*
     * @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
     *  @JoinColumn(name = "payment_id")
     *  private Payment payment;
     */



    /*
     * @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
     * private List<Order> order = new ArrayList<>();
     */

    /*
     * @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
     * private List<DeliveryAddress> deliveryAddress = new ArrayList<>();
     */


}



