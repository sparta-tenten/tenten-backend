package com.sparta.tentenbackend.domain.ai.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAi is a Querydsl query type for Ai
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAi extends EntityPathBase<Ai> {

    private static final long serialVersionUID = -361883017L;

    public static final QAi ai = new QAi("ai");

    public final com.sparta.tentenbackend.global.QBaseEntity _super = new com.sparta.tentenbackend.global.QBaseEntity(this);

    public final StringPath answer = createString("answer");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final StringPath question = createString("question");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QAi(String variable) {
        super(Ai.class, forVariable(variable));
    }

    public QAi(Path<? extends Ai> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAi(PathMetadata metadata) {
        super(Ai.class, metadata);
    }

}

