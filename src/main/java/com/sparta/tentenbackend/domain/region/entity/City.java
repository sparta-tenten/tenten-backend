package com.sparta.tentenbackend.domain.region.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;

@Getter
@Entity
@Table(name = "p_city")
public class City {

    @Id
    private String code;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "city", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<District> districtList;
}
