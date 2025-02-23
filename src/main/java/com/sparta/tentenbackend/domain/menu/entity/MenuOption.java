package com.sparta.tentenbackend.domain.menu.entity;

import com.sparta.tentenbackend.domain.store.entity.Menu;
import com.sparta.tentenbackend.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "p_menu_option")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String category;

    private Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Builder.Default
    @Column(name = "is_deleted")
    private boolean deleted = false;

    /**
     * Soft Delete를 수행하는 메서드
     */
    public void markAsDeleted(String deletedBy) {
        this.deleted = true;
        setDeletedAtToNow();
        setDeletedBy(deletedBy);
    }

    /**
     * 업데이트 타임스탬프 메서드 (선택적)
     */
    public void markAsUpdated(String updatedBy) {
        setUpdatedAtToNow();
        setUpdatedBy(updatedBy);
    }

    /**
     * Menu를 설정하는 메서드 (MenuOptionService에서 사용)
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}

