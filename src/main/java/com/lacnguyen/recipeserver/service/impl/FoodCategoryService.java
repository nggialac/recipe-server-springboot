package com.lacnguyen.recipeserver.service.impl;

import com.lacnguyen.recipeserver.entity.FoodCategoryEntity;
import com.lacnguyen.recipeserver.entity.RecipeEntity;
import com.lacnguyen.recipeserver.repository.FoodCategoryRepository;
import com.lacnguyen.recipeserver.repository.RecipeRepository;
import com.lacnguyen.recipeserver.service.IFoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodCategoryService implements IFoodCategoryService {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    FoodCategoryRepository foodCategoryRepository;

    public FoodCategoryService(FoodCategoryRepository foodCategoryRepository, RecipeRepository recipeRepository) {
        this.foodCategoryRepository = foodCategoryRepository;
        this.recipeRepository = recipeRepository;
    }

    public FoodCategoryEntity createFc(FoodCategoryEntity fc) {
        return foodCategoryRepository.save(fc);
    }

    //New FoodCategory
    @Transactional
    public ResponseEntity<Object> addFoodCategory(FoodCategoryEntity foodCategoryEntity) {

        FoodCategoryEntity newFoodCategory = new FoodCategoryEntity();
        newFoodCategory.setFoodCategoryName(foodCategoryEntity.getFoodCategoryName());
//        newFoodCategory.setDescription(foodCategoryEntity.getDescription());
        //Add condition new single FC

        List<FoodCategoryEntity> foodCategoryList = new ArrayList<>();
        foodCategoryList.add(newFoodCategory);
        for (int i = 0; i < foodCategoryEntity.getRecipe().size(); i++) {
            if (!recipeRepository.findByRecipeName(foodCategoryEntity.getRecipe().get(i).getRecipeName()).isPresent()) {
                RecipeEntity newRecipe = foodCategoryEntity.getRecipe().get(i);
                newRecipe.setFoodCategories(foodCategoryList);
                RecipeEntity savedUser = recipeRepository.save(newRecipe);
                if (!recipeRepository.findById(savedUser.getRecipeId()).isPresent())
                    return ResponseEntity.unprocessableEntity().body("FoodCategory Creation Failed");
            } else return ResponseEntity.unprocessableEntity().body("Recipe with name Id is already Present");
        }
        return ResponseEntity.ok("Successfully created FoodCategory");
    }

    /**
     * Delete a specified role given the id
     */
    public ResponseEntity<Object> deleteFoodCategory(Long id) {
        if (foodCategoryRepository.findById(id).isPresent()) {
            foodCategoryRepository.deleteById(id);
            if (foodCategoryRepository.findById(id).isPresent()) {
                return ResponseEntity.unprocessableEntity().body("Failed to delete the specified record");
            } else return ResponseEntity.ok().body("Successfully deleted specified record");
        } else
            return ResponseEntity.unprocessableEntity().body("No Records Found");
    }

    public ResponseEntity<Object> updateFoodCategory(Long id, FoodCategoryEntity fc) {
        if (foodCategoryRepository.findById(id).isPresent()) {
            FoodCategoryEntity newFC = foodCategoryRepository.findById(id).get();
            newFC.setFoodCategoryName(fc.getFoodCategoryName());
//            newFC.setDescription(role.getDescription());
            FoodCategoryEntity savedFC = foodCategoryRepository.save(newFC);
            if (foodCategoryRepository.findById(savedFC.getFoodCategoryId()).isPresent())
                return ResponseEntity.accepted().body("Food category saved successfully");
            else return ResponseEntity.badRequest().body("Failed to update Food category");

        } else return ResponseEntity.unprocessableEntity().body("Specified FC not found");
    }
}
