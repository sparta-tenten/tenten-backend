package com.sparta.tentenbackend.domain.region.entity;

import com.sparta.tentenbackend.domain.user.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;

@Getter
@Entity
@Table(name = "p_town")
public class Town {

    @Id
    private String code;

    @Column(nullable = false, length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "district_code")
    private District district;

    @OneToMany(mappedBy = "town", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<User> userList;

//    @OneToMany(mappedBy = "town", cascade = CascadeType.REMOVE, orphanRemoval = true)
//    private List<DeliveryAddress> deliveryAddressList;
}
