package com.sparta.tentenbackend.domain.region.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCity is a Querydsl query type for City
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCity extends EntityPathBase<City> {

    private static final long serialVersionUID = 1847690182L;

    public static final QCity city = new QCity("city");

    public final StringPath code = createString("code");

    public final ListPath<District, QDistrict> districtList = this.<District, QDistrict>createList("districtList", District.class, QDistrict.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public QCity(String variable) {
        super(City.class, forVariable(variable));
    }

    public QCity(Path<? extends City> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCity(PathMetadata metadata) {
        super(City.class, metadata);
    }

}

