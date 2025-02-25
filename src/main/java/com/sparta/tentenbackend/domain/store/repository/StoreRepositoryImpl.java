package com.sparta.tentenbackend.domain.store.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.tentenbackend.domain.category.entity.QCategory;
import com.sparta.tentenbackend.domain.region.entity.QTown;
import com.sparta.tentenbackend.domain.store.entity.QStore;
import com.sparta.tentenbackend.domain.store.entity.Store;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class StoreRepositoryImpl extends QuerydslRepositorySupport implements
    StoreRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public StoreRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Store.class);
        this.queryFactory = queryFactory;
    }


    @Override
    public Page<Store> findStoresByCategory(UUID categoryId, String storeName, String townCode,
        String sortBy, boolean isAsc, Pageable pageable) {
        QStore store = QStore.store;
        QCategory category = QCategory.category;
        QTown town = QTown.town;

        BooleanBuilder builder = new BooleanBuilder();
        // 카테고리별 가게 목록 조회
        if (categoryId != null) {
            builder.and(category.id.eq(categoryId));
        }
        // storeName을 포함하는 카테고리별 가게 목록 조회
        if (storeName != null && !storeName.isEmpty()) {
            builder.and(store.name.containsIgnoreCase(storeName));
        }
        if (townCode != null && !townCode.isEmpty()) {
            builder.and(store.town.code.eq(townCode));
        }

        // 정렬
        OrderSpecifier<?> orderSpecifier = getOrderSpecifier(sortBy, isAsc);

        // 쿼리 생성
        JPAQuery<Store> query = queryFactory.selectFrom(store)
            .join(store.category, category)
            .where(builder)
            .orderBy(orderSpecifier);

        // 페이징 적용 후 조회
        List<Store> results = getQuerydsl().applyPagination(pageable, query).fetch();

        // 전체 데이터 개수 조회 (NullPointerException 방지)
        long total = Optional.ofNullable(
            queryFactory.select(store.count())
                .from(store)
                .join(store.category, category)
                .where(builder)
                .fetchOne()
        ).orElse(0L);

        return new PageImpl<>(results, pageable, total);
    }

    private OrderSpecifier<?> getOrderSpecifier(String sortBy, boolean isAsc) {
        QStore store = QStore.store;
        // 기본 이름 사전순 정렬
        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "name";
        }
        if ("updatedAt".equalsIgnoreCase(sortBy)) {
            return isAsc ? store.updatedAt.asc() : store.updatedAt.desc();
        } else if ("createdAt".equalsIgnoreCase(sortBy)) {
            return isAsc ? store.createdAt.asc() : store.createdAt.desc();
        } else {
            return isAsc ? store.name.asc() : store.name.desc();
        }
    }
}
