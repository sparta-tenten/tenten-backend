package com.sparta.tentenbackend.domain.store.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStore is a Querydsl query type for Store
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStore extends EntityPathBase<Store> {

    private static final long serialVersionUID = -305224211L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStore store = new QStore("store");

    public final com.sparta.tentenbackend.global.QBaseEntity _super = new com.sparta.tentenbackend.global.QBaseEntity(this);

    public final StringPath address = createString("address");

    public final com.sparta.tentenbackend.domain.category.entity.QCategory category;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final StringPath image = createString("image");

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final StringPath name = createString("name");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final NumberPath<Integer> storeGrade = createNumber("storeGrade", Integer.class);

    public final NumberPath<Integer> totalReviewCount = createNumber("totalReviewCount", Integer.class);

    public final com.sparta.tentenbackend.domain.region.entity.QTown town;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.sparta.tentenbackend.domain.user.entity.QUser user;

    public QStore(String variable) {
        this(Store.class, forVariable(variable), INITS);
    }

    public QStore(Path<? extends Store> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStore(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStore(PathMetadata metadata, PathInits inits) {
        this(Store.class, metadata, inits);
    }

    public QStore(Class<? extends Store> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new com.sparta.tentenbackend.domain.category.entity.QCategory(forProperty("category")) : null;
        this.town = inits.isInitialized("town") ? new com.sparta.tentenbackend.domain.region.entity.QTown(forProperty("town"), inits.get("town")) : null;
        this.user = inits.isInitialized("user") ? new com.sparta.tentenbackend.domain.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

