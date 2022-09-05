package com.example.querydsl;

import com.example.querydsl.domain.FoodStore;
import com.example.querydsl.domain.FoodType;
import com.example.querydsl.domain.QFoodStore;
import com.example.querydsl.domain.QFoodType;
import com.example.querydsl.repository.FoodStoreRepository;
import com.example.querydsl.repository.FoodTypeRepository;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static com.example.querydsl.domain.QFoodStore.*;
import static com.example.querydsl.domain.QFoodType.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
//@Transactional
public class QueryDSLTest {

    @Autowired
    FoodTypeRepository foodTypeRepository;

    @Autowired
    FoodStoreRepository foodStoreRepository;
    @Autowired
    JPAQueryFactory query;


    @Test
    public void setData(){
        FoodType korean = new FoodType("한식",1);
        FoodType western = new FoodType("양식",2);
        FoodType chinese= new FoodType("중식",3);

        foodTypeRepository.saveAll(List.of(korean,western,chinese));

        FoodStore foodStore1 = new FoodStore("삼겹살",9,"sangmessi",korean);;
        FoodStore foodStore2 = new FoodStore("닭갈비",2,"sangmessi",korean);
        FoodStore foodStore3 = new FoodStore("부대찌개",3,"lake",korean);
        FoodStore foodStore4 = new FoodStore("순대국밥",4,"lake",korean);
        FoodStore foodStore5 = new FoodStore("소고기",5,"lake",korean);
        FoodStore foodStore6 = new FoodStore("스파게티",6,"sangmessi",western);
        FoodStore foodStore7 = new FoodStore("피자",7,"sangmessi",western);
        FoodStore foodStore8 = new FoodStore("중국집",9,"hong",chinese);
        FoodStore foodStore9 = new FoodStore("중국집2",10,"hong",chinese);
        FoodStore foodStore10 = new FoodStore("중국집3",8,"hong",chinese);

        foodStoreRepository.saveAll(List.of(foodStore1,foodStore2,foodStore3,foodStore4,foodStore5,foodStore6,foodStore7,foodStore8,foodStore9,foodStore10));
    }

    @Test
    public void 기본쿼리(){
        List<FoodStore> results = query
                .selectFrom(foodStore)
                .fetch();

        assertThat(results.size()).isEqualTo(10);

    }

    @Test
    public void 기본쿼리_조건절(){
        List<FoodStore> results = query
                .selectFrom(foodStore)
                .where(foodStore.rate.goe(5))
                .fetch();
        assertThat(results.size()).isEqualTo(7);

    }

    @Test
    public void 기본쿼리_조건절2(){
        List<FoodStore> results = query
                .selectFrom(foodStore)
                .where(
                        foodStore.rate.goe(5), //기본적으로 ','는 and 를 의미한다.
                        foodStore.storeName.startsWith("삼"))
                .fetch();
        assertThat(results.size()).isEqualTo(1);

    }

    @Test
    public void 기본쿼리_정렬(){
        List<FoodStore> results = query
                .selectFrom(foodStore)
                .orderBy(foodStore.rate.desc())
                .fetch();
        assertThat(results.size()).isEqualTo(10);
        assertThat(results.get(0).getRate()).isEqualTo(10);
    }

    @Test
    public void 기본쿼리_페이징(){
        QueryResults<FoodStore> fetchResults = query.selectFrom(foodStore)
                .offset(0)
                .limit(3)
                .fetchResults();

        List<FoodStore> results = fetchResults.getResults();
        long limit = fetchResults.getLimit();
        long offset = fetchResults.getOffset();
        long total = fetchResults.getTotal();

        System.out.println("total = " + total );
        System.out.println("offset = " + offset);
        System.out.println("limit = " + limit);

        assertThat(results.size()).isEqualTo(3);
    }

    @Test
    public void 기본조인() {
        List<FoodStore> fetch = query
                .selectFrom(foodStore)
                .join(foodStore.foodType, foodType)
                .fetch();

         fetch.forEach(System.out::println);
    }

    @Test
    public void 연관관계_없는_조인(){
        List<FoodStore> result = query
                .select(foodStore)
                .from(foodStore, foodType)
                .where(foodStore.rate.eq(foodType.foodOrder))
                .fetch();

        result.forEach(System.out::println);
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void join_on(){
        List<FoodStore> result = query
                .select(foodStore)
                .from(foodStore)
                .join(foodType).on(foodType.foodOrder.eq(foodStore.rate))
                .fetch();

        assertThat(result.size()).isEqualTo(2);
    }


}
