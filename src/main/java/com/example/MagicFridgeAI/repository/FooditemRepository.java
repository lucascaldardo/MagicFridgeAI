package com.example.MagicFridgeAI.repository;

import com.example.MagicFridgeAI.model.FooditemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FooditemRepository extends JpaRepository<FooditemModel,Long> {
}
