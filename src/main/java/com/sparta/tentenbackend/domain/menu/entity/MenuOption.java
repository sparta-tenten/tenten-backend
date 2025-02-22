
package com.sparta.tentenbackend.domain.menu.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigInteger;
import java.util.UUID;

@Entity
@Table(name = "p_menu_option")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuOption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String category;
    private BigInteger price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    private String createdBy;
    private String updatedBy;
    private String deletedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date updatedAt;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date deletedAt;

    // 데이터 숨김 처리를 위한 플래그
    @Builder.Default
    private boolean isDeleted = false;

    // 필요한 setter 메서드들 추가 (Lombok @Setter가 없다면)
    public void setDeleted(boolean deleted) {
        this.isDeleted = deleted;
    }

}

