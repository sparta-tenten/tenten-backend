package com.sparta.tentenbackend.domain.review.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOwnerReview is a Querydsl query type for OwnerReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOwnerReview extends EntityPathBase<OwnerReview> {

    private static final long serialVersionUID = 205617740L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOwnerReview ownerReview = new QOwnerReview("ownerReview");

    public final com.sparta.tentenbackend.global.QBaseEntity _super = new com.sparta.tentenbackend.global.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final QReview review;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QOwnerReview(String variable) {
        this(OwnerReview.class, forVariable(variable), INITS);
    }

    public QOwnerReview(Path<? extends OwnerReview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOwnerReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOwnerReview(PathMetadata metadata, PathInits inits) {
        this(OwnerReview.class, metadata, inits);
    }

    public QOwnerReview(Class<? extends OwnerReview> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.review = inits.isInitialized("review") ? new QReview(forProperty("review"), inits.get("review")) : null;
    }

}

