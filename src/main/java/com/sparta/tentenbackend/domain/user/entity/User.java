
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
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String userName;
    @Column(nullable = false)
    @JsonIgnore
    private String password;
    @Column(nullable = false, unique = true)
    @Email(message = "올바른 이메일 형식을 입력하세요.")
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
        String detailAddress, String phoneNumber, Town town) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.address = address;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
        this.town = town;
    }

    public void userUpdate(String password, String email, String address, String detailAddress,
        String phoneNumber) {
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.detailAddress = detailAddress;

    }

    public void deleteUser() {
        this.setDeleted(true);
        this.setDeletedAt(LocalDateTime.now());
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
