package com.lacnguyen.recipeserver.service;

import com.lacnguyen.recipeserver.entity.FoodCategoryEntity;
import org.springframework.http.ResponseEntity;

public interface IFoodCategoryService {
    //    RoleService(RoleRepository roleRepository, UserRepository userRepository);
    ResponseEntity<Object> addFoodCategory(FoodCategoryEntity foodCategoryEntity);

    ResponseEntity<Object> deleteFoodCategory(Long id);

    ResponseEntity<Object> updateFoodCategory(Long id, FoodCategoryEntity fc);

    FoodCategoryEntity createFc(FoodCategoryEntity fc);
}
