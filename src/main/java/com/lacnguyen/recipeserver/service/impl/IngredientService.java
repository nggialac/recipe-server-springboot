package com.lacnguyen.recipeserver.service.impl;


import com.lacnguyen.recipeserver.entity.IngredientEntity;
import com.lacnguyen.recipeserver.repository.IngredientRepository;
import com.lacnguyen.recipeserver.service.IIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientService implements IIngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    @Override
    public Optional<IngredientEntity> getAllIngredientByRecipeId(Long id){
        return ingredientRepository.findByRecipe_RecipeId(id);
    }

    @Override
    public IngredientEntity save(IngredientEntity obj){
        return ingredientRepository.save(obj);
    }

    @Override
    public Optional<IngredientEntity> getIngredientById(Long id){
        return ingredientRepository.findById(id);
    }

    @Override
    public void deleteIngredient(IngredientEntity obj){
        ingredientRepository.delete(obj);
    }

    @Override
    public Optional<IngredientEntity> findByIdAndRecipeId(Long id, Long recipeId){
        return ingredientRepository.findByIngredientIdAndRecipe_RecipeId(id, recipeId);
    }
}
