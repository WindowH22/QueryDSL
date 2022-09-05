package com.example.querydsl.repository;

import com.example.querydsl.domain.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodTypeRepository extends JpaRepository<FoodType, Long> {
}
