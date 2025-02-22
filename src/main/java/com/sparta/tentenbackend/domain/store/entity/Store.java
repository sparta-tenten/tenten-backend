package com.sparta.tentenbackend.domain.store.entity;

import com.sparta.tentenbackend.domain.category.entity.Category;
import com.sparta.tentenbackend.domain.region.entity.Town;
import com.sparta.tentenbackend.domain.user.entity.User;
import com.sparta.tentenbackend.global.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "p_store")
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;  // 가게 ID (UUID)

    @Column(nullable = false)
    private String name;  // 가게명

    @Column(nullable = false)
    private String address;  // 가게 주소

    @Column(nullable = false)
    private String phoneNumber;  // 전화번호

    private String image;  // 가게 이미지 URL

    // ------------------------------------------------------------------- //
    private int storeGrade; // 가게 총 평점 합계
    private int totalReviewCount;   // 가게 총 리뷰 개수
    // ------------------------------------------------------------------- //

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 가게 소유자 (p_user 테이블과 연결)

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category; // 가게 카테고리 (p_category와 연결)

    @ManyToOne
    @JoinColumn(name = "town_code", nullable = false)
    private Town town; // 가게 지역 (p_town과 연결)

    public Store(String name, String address, String phoneNumber, String image, User user,
        Category category, Town town) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.image = image;
        this.user = user;
        this.category = category;
    }

    // ------------------------------------------------------------------- // 리뷰 생성, 수정, 삭제
    // 리뷰 생성시 총 평점 합계, 총 리뷰 개수 업데이트
    public void updateReviewStats(int grade) {
        this.storeGrade += grade;
        this.totalReviewCount += 1;
    }

    // 리뷰 수정시 총 평점 합계 업데이트
    public void modifyReviewStats(int oldGrade, int newGrade) {
        this.storeGrade = this.storeGrade - oldGrade + newGrade;
    }

    // 리뷰 삭제시 총 평점 합계, 총 리뷰 개수 업데이트
    public void removeReviewStats(int grade) {
        this.storeGrade -= grade;
        this.totalReviewCount -= 1;
    }
    // ------------------------------------------------------------------- //
}
