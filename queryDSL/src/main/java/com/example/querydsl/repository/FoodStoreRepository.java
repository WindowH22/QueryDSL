package com.example.querydsl.repository;

import com.example.querydsl.domain.FoodStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodStoreRepository extends JpaRepository<FoodStore,Long> {
}
