package com.sparta.tentenbackend.domain.menu.entity;

import com.sparta.tentenbackend.domain.store.entity.Store;
import com.sparta.tentenbackend.global.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "p_menu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "메뉴 이름은 필수입니다.")
    private String name;

    @NotNull(message = "가격을 입력해주세요.")
    @Positive(message = "가격은 0보다 커야 합니다.")
    private Long price;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private MenuStatus status; // ENUM (판매중, 하루품절, 숨김)

    private String image;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "menu")
    private List<MenuOption> menuOptionList;

    private String createdBy;
    private String updatedBy;
    private String deletedBy;


    // Soft Delete 메서드 수정
    public void markAsDeleted(String deletedBy) {
        this.setDeleted(true);
        this.setDeletedAt(LocalDateTime.now());
        this.deletedBy = deletedBy;
    }
}
