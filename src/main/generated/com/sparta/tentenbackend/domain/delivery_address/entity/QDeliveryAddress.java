package com.sparta.tentenbackend.domain.delivery_address.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDeliveryAddress is a Querydsl query type for DeliveryAddress
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeliveryAddress extends EntityPathBase<DeliveryAddress> {

    private static final long serialVersionUID = -123973296L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDeliveryAddress deliveryAddress = new QDeliveryAddress("deliveryAddress");

    public final com.sparta.tentenbackend.global.QBaseEntity _super = new com.sparta.tentenbackend.global.QBaseEntity(this);

    public final StringPath address = createString("address");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath detailAddress = createString("detailAddress");

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final StringPath name = createString("name");

    public final com.sparta.tentenbackend.domain.region.entity.QTown town;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.sparta.tentenbackend.domain.user.entity.QUser user;

    public QDeliveryAddress(String variable) {
        this(DeliveryAddress.class, forVariable(variable), INITS);
    }

    public QDeliveryAddress(Path<? extends DeliveryAddress> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDeliveryAddress(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDeliveryAddress(PathMetadata metadata, PathInits inits) {
        this(DeliveryAddress.class, metadata, inits);
    }

    public QDeliveryAddress(Class<? extends DeliveryAddress> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.town = inits.isInitialized("town") ? new com.sparta.tentenbackend.domain.region.entity.QTown(forProperty("town"), inits.get("town")) : null;
        this.user = inits.isInitialized("user") ? new com.sparta.tentenbackend.domain.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

