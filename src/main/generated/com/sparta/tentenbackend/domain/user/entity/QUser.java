package com.sparta.tentenbackend.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1298380035L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final com.sparta.tentenbackend.global.QBaseEntity _super = new com.sparta.tentenbackend.global.QBaseEntity(this);

    public final StringPath address = createString("address");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final ListPath<com.sparta.tentenbackend.domain.delivery_address.entity.DeliveryAddress, com.sparta.tentenbackend.domain.delivery_address.entity.QDeliveryAddress> deliveryAddressList = this.<com.sparta.tentenbackend.domain.delivery_address.entity.DeliveryAddress, com.sparta.tentenbackend.domain.delivery_address.entity.QDeliveryAddress>createList("deliveryAddressList", com.sparta.tentenbackend.domain.delivery_address.entity.DeliveryAddress.class, com.sparta.tentenbackend.domain.delivery_address.entity.QDeliveryAddress.class, PathInits.DIRECT2);

    public final StringPath detailAddress = createString("detailAddress");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final EnumPath<UserRoleEnum> role = createEnum("role", UserRoleEnum.class);

    public final com.sparta.tentenbackend.domain.region.entity.QTown town;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath userName = createString("userName");

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.town = inits.isInitialized("town") ? new com.sparta.tentenbackend.domain.region.entity.QTown(forProperty("town"), inits.get("town")) : null;
    }

}

