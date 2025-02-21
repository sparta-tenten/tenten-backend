package com.sparta.tentenbackend.domain.menu.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMenuOption is a Querydsl query type for MenuOption
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMenuOption extends EntityPathBase<MenuOption> {

    private static final long serialVersionUID = -533928710L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMenuOption menuOption = new QMenuOption("menuOption");

    public final StringPath category = createString("category");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> deletedAt = createDateTime("deletedAt", java.util.Date.class);

    public final StringPath deletedBy = createString("deletedBy");

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final QMenu menu;

    public final NumberPath<java.math.BigInteger> price = createNumber("price", java.math.BigInteger.class);

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final StringPath updatedBy = createString("updatedBy");

    public QMenuOption(String variable) {
        this(MenuOption.class, forVariable(variable), INITS);
    }

    public QMenuOption(Path<? extends MenuOption> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMenuOption(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMenuOption(PathMetadata metadata, PathInits inits) {
        this(MenuOption.class, metadata, inits);
    }

    public QMenuOption(Class<? extends MenuOption> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.menu = inits.isInitialized("menu") ? new QMenu(forProperty("menu"), inits.get("menu")) : null;
    }

}

