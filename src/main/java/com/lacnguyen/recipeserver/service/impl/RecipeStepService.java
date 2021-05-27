package com.lacnguyen.recipeserver.service.impl;

import com.lacnguyen.recipeserver.entity.RecipeStepEntity;
import com.lacnguyen.recipeserver.repository.RecipeStepRepository;
import com.lacnguyen.recipeserver.service.IRecipeStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeStepService implements IRecipeStepService {

    @Autowired
    RecipeStepRepository recipeStepRepository;


    @Override
    public List<RecipeStepEntity> findAllRecipeStep() {
        return recipeStepRepository.findAll();
    }

    @Override
    public Optional<RecipeStepEntity> findByRecipeStepId(Long id) {
        return recipeStepRepository.findById(id);
    }

    @Override
    public RecipeStepEntity save(RecipeStepEntity objCourse) {
        return recipeStepRepository.save(objCourse);
    }

    @Override
    public void deleteByRecipeStepId(Long id) {
        recipeStepRepository.deleteById(id);
    }

    @Override
    public void updateRecipeStep(RecipeStepEntity objCourse) {

    }


}
