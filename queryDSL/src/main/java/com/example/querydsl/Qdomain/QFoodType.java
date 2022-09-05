package com.example.querydsl.Qdomain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.querydsl.domain.FoodStore;
import com.example.querydsl.domain.FoodType;
import com.example.querydsl.domain.QFoodStore;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFoodType is a Querydsl query type for FoodType
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFoodType extends EntityPathBase<FoodType> {

    private static final long serialVersionUID = 1647084954L;

    public static final QFoodType foodType = new QFoodType("foodType");

    public final NumberPath<Integer> foodOrder = createNumber("foodOrder", Integer.class);

    public final ListPath<FoodStore, QFoodStore> foodStoreList = this.<FoodStore, QFoodStore>createList("foodStoreList", FoodStore.class, QFoodStore.class, PathInits.DIRECT2);

    public final StringPath foodTypeName = createString("foodTypeName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QFoodType(String variable) {
        super(FoodType.class, forVariable(variable));
    }

    public QFoodType(Path<? extends FoodType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFoodType(PathMetadata metadata) {
        super(FoodType.class, metadata);
    }

}

