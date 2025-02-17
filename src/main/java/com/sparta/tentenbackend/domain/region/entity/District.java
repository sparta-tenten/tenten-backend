package com.sparta.tentenbackend.domain.region.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;

@Getter
@Entity
@Table(name = "p_district")
public class District {

    @Id
    private String code;

    @Column(nullable = false, length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_code")
    private City city;

    @OneToMany(mappedBy = "district", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Town> townList;
}
