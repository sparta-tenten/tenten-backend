package com.sparta.tentenbackend.domain.region.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDistrict is a Querydsl query type for District
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDistrict extends EntityPathBase<District> {

    private static final long serialVersionUID = 480868457L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDistrict district = new QDistrict("district");

    public final QCity city;

    public final StringPath code = createString("code");

    public final StringPath name = createString("name");

    public final ListPath<Town, QTown> townList = this.<Town, QTown>createList("townList", Town.class, QTown.class, PathInits.DIRECT2);

    public QDistrict(String variable) {
        this(District.class, forVariable(variable), INITS);
    }

    public QDistrict(Path<? extends District> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDistrict(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDistrict(PathMetadata metadata, PathInits inits) {
        this(District.class, metadata, inits);
    }

    public QDistrict(Class<? extends District> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.city = inits.isInitialized("city") ? new QCity(forProperty("city")) : null;
    }

}

