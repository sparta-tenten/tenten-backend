package com.sparta.tentenbackend.domain.order.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.tentenbackend.domain.menu.entity.QMenu;
import com.sparta.tentenbackend.domain.menu.entity.QMenuOrder;
import com.sparta.tentenbackend.domain.order.dto.OrderSearchRequest;
import com.sparta.tentenbackend.domain.order.entity.DeliveryType;
import com.sparta.tentenbackend.domain.order.entity.Order;
import com.sparta.tentenbackend.domain.order.entity.OrderStatus;
import com.sparta.tentenbackend.domain.order.entity.QOrder;
import com.sparta.tentenbackend.domain.user.entity.User;
import com.sparta.tentenbackend.global.util.PageUtils;
import com.sparta.tentenbackend.global.util.PageUtils.CommonSortBy;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryQueryImpl implements OrderRepositoryQuery {

    private final JPAQueryFactory queryFactory;

    QOrder order = QOrder.order;
    QMenuOrder menuOrder = QMenuOrder.menuOrder;
    QMenu menu = QMenu.menu;

    @Override
    public Page<Order> getOrderList(OrderSearchRequest req, User user) {
        Pageable pageable = PageUtils.pageable(req.getPage(), req.getSize());

        BooleanBuilder builder = new BooleanBuilder();

        UUID categoryId = req.getCategoryId();
        DeliveryType deliveryType = req.getDeliveryType();
        OrderStatus orderStatus = req.getOrderStatus();
        String keyword = req.getKeyword();

        builder.and(order.user.id.eq(user.getId()));
        if (categoryId != null) {
            builder.and(order.store.category.id.eq(categoryId));
        }
        if (deliveryType != null) {
            builder.and(order.deliveryType.eq(deliveryType));
        }
        if (orderStatus != null) {
            builder.and(order.orderStatus.eq(orderStatus));
        }
        if (keyword != null && !keyword.isBlank()) {
            builder.and(order.store.name.containsIgnoreCase(keyword));
        }

        CommonSortBy sortBy = req.getSortBy();
        Sort.Direction sortDirection = req.getSortDirection();
        OrderSpecifier<?> orderSpecifier = PageUtils.getCommonOrderSpecifier(order, sortDirection,
            sortBy);

        // 쿼리 실행
        List<Order> resultList = queryFactory
            .selectFrom(order)
            .where(builder)
            .offset(pageable.getOffset())   // 페이지네이션 적용
            .orderBy(orderSpecifier)
            .limit(pageable.getPageSize())
            .fetch();

        // 전체 개수 조회 (페이징을 위해 필요)
        long totalCount = Optional.ofNullable(
            queryFactory
                .select(order.count())
                .from(order)
                .where(builder)
                .fetchOne()
        ).orElse(0L);

        return PageableExecutionUtils.getPage(resultList, pageable, () -> totalCount);
    }

    @Override
    public Page<Order> getOrderListByStoreId(UUID storeId, OrderSearchRequest req) {
        Pageable pageable = PageUtils.pageable(req.getPage(), req.getSize());

        BooleanBuilder builder = new BooleanBuilder();

        DeliveryType deliveryType = req.getDeliveryType();
        OrderStatus orderStatus = req.getOrderStatus();
        String keyword = req.getKeyword();

        if (storeId != null) {
            builder.and(order.store.id.eq(storeId));
        }
        if (deliveryType != null) {
            builder.and(order.deliveryType.eq(deliveryType));
        }
        if (orderStatus != null) {
            builder.and(order.orderStatus.eq(orderStatus));
        }
        if (keyword != null && !keyword.isBlank()) {
            builder.and(order.user.email.containsIgnoreCase(keyword))
                .or(order.user.userName.containsIgnoreCase(keyword))
                .or(menu.name.containsIgnoreCase(keyword));
        }

        CommonSortBy sortBy = req.getSortBy();
        Sort.Direction sortDirection = req.getSortDirection();
        OrderSpecifier<?> orderSpecifier = PageUtils.getCommonOrderSpecifier(order, sortDirection,
            sortBy);

        // 쿼리 실행
        List<Order> resultList = queryFactory
            .selectFrom(order)
            .leftJoin(order.menuOrderList, menuOrder)
            .leftJoin(menuOrder.menu, menu)
            .where(builder)
            .orderBy(orderSpecifier)
            .offset(pageable.getOffset())   // 페이지네이션 적용
            .limit(pageable.getPageSize())
            .fetch();

        // 전체 개수 조회 (페이징을 위해 필요)
        long totalCount = Optional.ofNullable(
            queryFactory
                .select(order.count())
                .from(order)
                .leftJoin(order.menuOrderList, menuOrder)
                .leftJoin(menuOrder.menu, menu)
                .where(builder)
                .fetchOne()
        ).orElse(0L);

        return PageableExecutionUtils.getPage(resultList, pageable, () -> totalCount);
    }
}
