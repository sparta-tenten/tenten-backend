package com.sparta.tentenbackend.domain.delivery_address.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.tentenbackend.domain.delivery_address.entity.DeliveryAddress;
import com.sparta.tentenbackend.domain.delivery_address.entity.QDeliveryAddress;
import com.sparta.tentenbackend.domain.user.entity.User;
import com.sparta.tentenbackend.global.util.PageUtils;
import com.sparta.tentenbackend.global.util.PageUtils.CommonSortBy;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeliveryAddressRepositoryQueryImpl implements DeliveryAddressRepositoryQuery {

    private final JPAQueryFactory queryFactory;

    QDeliveryAddress deliveryAddress = QDeliveryAddress.deliveryAddress;

    @Override
    public Page<DeliveryAddress> getDeliveryAddressList(User user, Pageable pageable,
        Direction sortDirection,
        CommonSortBy sortBy, String keyword) {

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(deliveryAddress.user.id.eq(user.getId()));
        if (keyword != null && !keyword.isBlank()) {
            builder.and(deliveryAddress.name.containsIgnoreCase(keyword));
        }

        OrderSpecifier<?> orderSpecifier = PageUtils.getCommonOrderSpecifier(deliveryAddress,
            sortDirection, sortBy);

        List<DeliveryAddress> resultList = queryFactory
            .selectFrom(deliveryAddress)
            .where(builder)
            .offset(pageable.getOffset())   // 페이지네이션 적용
            .orderBy(orderSpecifier)
            .limit(pageable.getPageSize())
            .fetch();

        // 전체 개수 조회 (페이징을 위해 필요)
        long totalCount = Optional.ofNullable(
            queryFactory
                .select(deliveryAddress.count())
                .from(deliveryAddress)
                .where(builder)
                .fetchOne()
        ).orElse(0L);

        return PageableExecutionUtils.getPage(resultList, pageable, () -> totalCount);
    }
}
