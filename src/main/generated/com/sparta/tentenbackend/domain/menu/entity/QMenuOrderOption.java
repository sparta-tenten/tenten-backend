package com.sparta.tentenbackend.domain.menu.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMenuOrderOption is a Querydsl query type for MenuOrderOption
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMenuOrderOption extends EntityPathBase<MenuOrderOption> {

    private static final long serialVersionUID = -1949206530L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMenuOrderOption menuOrderOption = new QMenuOrderOption("menuOrderOption");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> deletedAt = createDateTime("deletedAt", java.util.Date.class);

    public final StringPath deletedBy = createString("deletedBy");

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final QMenuOption menuOption;

    public final QMenuOrder menuOrder;

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final StringPath updatedBy = createString("updatedBy");

    public QMenuOrderOption(String variable) {
        this(MenuOrderOption.class, forVariable(variable), INITS);
    }

    public QMenuOrderOption(Path<? extends MenuOrderOption> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMenuOrderOption(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMenuOrderOption(PathMetadata metadata, PathInits inits) {
        this(MenuOrderOption.class, metadata, inits);
    }

    public QMenuOrderOption(Class<? extends MenuOrderOption> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.menuOption = inits.isInitialized("menuOption") ? new QMenuOption(forProperty("menuOption"), inits.get("menuOption")) : null;
        this.menuOrder = inits.isInitialized("menuOrder") ? new QMenuOrder(forProperty("menuOrder"), inits.get("menuOrder")) : null;
    }

}

