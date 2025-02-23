package com.sparta.tentenbackend.domain.menu.entity;

import com.sparta.tentenbackend.domain.menu.entity.Menu;
import com.sparta.tentenbackend.global.BaseEntity;
import jakarta.persistence.*;
import java.math.BigInteger;
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

    private BigInteger price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;


    // 삭제/업데이트한 사용자 정보는 여기서 관리
    private String deletedBy;
    private String updatedBy;

    public void markAsDeleted(String deletedBy) {
        this.setDeleted(true);  // 삭제 플래그 켜기
        this.setDeletedAt(java.time.LocalDateTime.now());  // 현재 시간 저장
        this.deletedBy = deletedBy;  // 누가 삭제했는지 저장
    }



    /**
     * 업데이트 타임스탬프 메서드 (선택적)
     */
    public void markAsUpdated(String updatedBy) {
        this.setUpdatedAt(java.time.LocalDateTime.now());  // 현재 시간 저장
        this.updatedBy = updatedBy;  // 누가 업데이트했는지 저장
    }

    /**
     * Menu를 설정하는 메서드 (MenuOptionService에서 사용)
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}

