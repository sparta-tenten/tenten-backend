package com.sparta.tentenbackend.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "p_store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;  // 가게 ID (UUID)

    private String name;  // 가게명
    private String address;  // 가게 주소
    private String phoneNumber;  // 전화번호
    private String image;  // 가게 이미지 URL

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 가게 소유자 (p_user 테이블과 연결)

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category; // 가게 카테고리 (p_category와 연결)

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region; // 가게 지역 (p_region과 연결)

    private LocalDateTime createdAt; // 생성 시간
    private String createdBy; // 생성자

    private LocalDateTime updatedAt; // 수정 시간
    private String updatedBy; // 수정자

    private LocalDateTime deletedAt; // 삭제 시간
    private String deletedBy; // 삭제자
}
