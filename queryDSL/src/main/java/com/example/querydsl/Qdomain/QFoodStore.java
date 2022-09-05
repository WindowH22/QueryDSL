package com.example.querydsl.Qdomain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.querydsl.domain.FoodStore;
import com.example.querydsl.domain.QFoodType;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFoodStore is a Querydsl query type for FoodStore
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFoodStore extends EntityPathBase<FoodStore> {

    private static final long serialVersionUID = -481046911L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFoodStore foodStore = new QFoodStore("foodStore");

    public final com.example.querydsl.domain.QFoodType foodType;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath ownerName = createString("ownerName");

    public final NumberPath<Integer> rate = createNumber("rate", Integer.class);

    public final StringPath storeName = createString("storeName");

    public QFoodStore(String variable) {
        this(FoodStore.class, forVariable(variable), INITS);
    }

    public QFoodStore(Path<? extends FoodStore> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFoodStore(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFoodStore(PathMetadata metadata, PathInits inits) {
        this(FoodStore.class, metadata, inits);
    }

    public QFoodStore(Class<? extends FoodStore> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.foodType = inits.isInitialized("foodType") ? new QFoodType(forProperty("foodType")) : null;
    }

}

