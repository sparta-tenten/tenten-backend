package com.sparta.tentenbackend.domain.region.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTown is a Querydsl query type for Town
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTown extends EntityPathBase<Town> {

    private static final long serialVersionUID = 1848202477L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTown town = new QTown("town");

    public final StringPath code = createString("code");

    public final QDistrict district;

    public final StringPath name = createString("name");

    public final ListPath<com.sparta.tentenbackend.domain.user.entity.User, com.sparta.tentenbackend.domain.user.entity.QUser> userList = this.<com.sparta.tentenbackend.domain.user.entity.User, com.sparta.tentenbackend.domain.user.entity.QUser>createList("userList", com.sparta.tentenbackend.domain.user.entity.User.class, com.sparta.tentenbackend.domain.user.entity.QUser.class, PathInits.DIRECT2);

    public QTown(String variable) {
        this(Town.class, forVariable(variable), INITS);
    }

    public QTown(Path<? extends Town> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTown(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTown(PathMetadata metadata, PathInits inits) {
        this(Town.class, metadata, inits);
    }

    public QTown(Class<? extends Town> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.district = inits.isInitialized("district") ? new QDistrict(forProperty("district"), inits.get("district")) : null;
    }

}

