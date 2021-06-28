package com.lacnguyen.recipeserver.service.impl;

import com.lacnguyen.recipeserver.entity.RecipeEntity;
import com.lacnguyen.recipeserver.repository.RecipeRepository;
import com.lacnguyen.recipeserver.service.IRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService implements IRecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    FoodCategoryService foodCategoryService;

    @Override
    public RecipeEntity findARecipe(Long id){
        return recipeRepository.findByRecipeId(id);
    }

    @Override
    public boolean isExistRecipe(Long id) {
        return recipeRepository.existsById(id);
    }

    @Override
    public Optional findByRecipeId(Long id) {
        return recipeRepository.findById(id);
    }

    @Override
    public Collection<RecipeEntity> findListRecipe() {
        return recipeRepository.findAll();
    }

    @Override
    public Collection<RecipeEntity> findByRecipeNameContains() {
        return recipeRepository.findAll();
    }

    @Override
    public void removeARecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    //FOOD-CATEGORY
    /** Create a new User */
    public ResponseEntity<Object> createRecipe_FC(RecipeEntity recipe) {
        RecipeEntity recipeEntity = new RecipeEntity();
        if (recipeRepository.findByRecipeName(recipe.getRecipeName()).isPresent()) {
            return ResponseEntity.badRequest().body("The Name is already Present, Failed to Create new Recipe");
        } else {
            recipeEntity.setRecipeName(recipe.getRecipeName());
            recipeEntity.setRecipeDescription(recipe.getRecipeDescription());
            recipeEntity.setPrepTime(recipe.getPrepTime());
            recipeEntity.setCookTime(recipe.getCookTime());
            recipeEntity.setRecipeImage(recipe.getRecipeImage());
            recipeEntity.setFoodCategories(recipe.getFoodCategories());

            RecipeEntity savedRecipe = recipeRepository.save(recipeEntity);
            if (recipeRepository.findById(savedRecipe.getRecipeId()).isPresent())
                return ResponseEntity.ok("Recipe Created Successfully");
            else return ResponseEntity.unprocessableEntity().body("Failed Creating Recipe as Specified");
        }
    }

    /** Update an Existing User */
    @Transactional
    public ResponseEntity<Object> updateRecipe_FC(RecipeEntity recipe, Long id) {
        if(recipeRepository.findById(id).isPresent()) {
            RecipeEntity newRecipe = recipeRepository.findById(id).get();
            newRecipe.setRecipeName(recipe.getRecipeName());
            newRecipe.setRecipeDescription(recipe.getRecipeDescription());
            newRecipe.setPrepTime(recipe.getPrepTime());
            newRecipe.setCookTime(recipe.getCookTime());
            newRecipe.setRecipeImage(recipe.getRecipeImage());
            newRecipe.setFoodCategories(recipe.getFoodCategories());
            RecipeEntity savedRecipe = recipeRepository.save(newRecipe);
            if(recipeRepository.findById(savedRecipe.getRecipeId()).isPresent())
                return  ResponseEntity.accepted().body("Recipe updated successfully");
            else return ResponseEntity.unprocessableEntity().body("Failed updating the recipe specified");
        } else return ResponseEntity.unprocessableEntity().body("Cannot find the recipe specified");
    }
    /** Delete an User*/
    public ResponseEntity<Object> deleteRecipe_FC(Long id) {
        if (recipeRepository.findById(id).isPresent()) {
            recipeRepository.deleteById(id);
            if (recipeRepository.findById(id).isPresent())
                return ResponseEntity.unprocessableEntity().body("Failed to Delete the specified recipe");
            else return ResponseEntity.ok().body("Successfully deleted the specified recipe");
        } else return ResponseEntity.badRequest().body("Cannot find the recipe specified");
    }


}
