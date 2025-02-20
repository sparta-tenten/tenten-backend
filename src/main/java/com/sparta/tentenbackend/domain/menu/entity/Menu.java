
package com.sparta.tentenbackend.domain.menu.entity;
import com.sparta.tentenbackend.domain.store.entity.Store;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;
import java.math.BigInteger;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @NotBlank(message = "메뉴 이름은 필수입니다.")
    private String name;


    @NotNull(message = "가격을 입력해주세요.")
    @Positive(message = "가격은 0보다 커야 합니다.")
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

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

}

