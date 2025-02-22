
package com.sparta.tentenbackend.domain.menu.entity;
import jakarta.persistence.*;
import java.util.Date;
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
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    // 데이터 숨김 처리를 위한 플래그
    @Builder.Default
    @Column(name = "is_deleted")
    private boolean deleted = false;

    /**
     * Soft Delete를 수행하는 메서드
     */
    public void markAsDeleted(String deletedBy) {
        this.deleted = true;
        this.deletedAt = new Date();
        this.deletedBy = deletedBy;
    }

    /**
     * 업데이트 타임스탬프 메서드 (선택적)
     */
    public void markAsUpdated(String updatedBy) {
        this.updatedAt = new Date();
        this.updatedBy = updatedBy;
    }

    /**
     * Menu를 설정하는 메서드 (MenuOptionService에서 사용)
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}

