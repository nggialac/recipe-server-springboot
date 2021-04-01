package com.lacnguyen.recipeserver.api;

import com.lacnguyen.recipeserver.entity.FoodCategoryEntity;
import com.lacnguyen.recipeserver.repository.FoodCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api/foodcategory")
public class FoodCategoryApi {

    @Autowired
    FoodCategoryRepository foodCategoryRepository;

    @GetMapping
    public List<FoodCategoryEntity> fc() {
        return foodCategoryRepository.findAll();
    }
}
