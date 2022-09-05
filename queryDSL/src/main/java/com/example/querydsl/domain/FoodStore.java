package com.example.querydsl.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString(of = {"id", "storeName", "rate", "ownerName"})
public class FoodStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String storeName;

    @Column
    private int rate;

    @Column
    private String ownerName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_type_id")
    private FoodType foodType;

    public FoodStore( String storeName, int rate, String ownerName, FoodType foodType) {
        this.storeName = storeName;
        this.rate = rate;
        this.ownerName = ownerName;
        changeFoodType(foodType);
    }

    private void changeFoodType(FoodType foodType) {
        this.foodType = foodType;
        foodType.getFoodStoreList().add(this);

    }
}
