package com.lacnguyen.recipeserver.api;

import com.lacnguyen.recipeserver.entity.RecipeEntity;
import com.lacnguyen.recipeserver.repository.RecipeRepository;
import com.lacnguyen.recipeserver.service.IRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.lang.Long;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/recipe")
public class RecipeApi {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IRecipeService iRecipeService;

    @GetMapping
    public Collection<RecipeEntity> findListRecipe() {
        return iRecipeService.findListRecipe();
    }

    @GetMapping("/{id}")
    public Optional<RecipeEntity> findRecipeById(@PathVariable("id") Long id) {
        return iRecipeService.findById(id);
    }

    @PostMapping
    public RecipeEntity createRecipeEntity(@RequestBody RecipeEntity objRecipe) {
        return recipeRepository.save(objRecipe);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipeEntity(@PathVariable("id") Long id) {
        recipeRepository.deleteById(id);
    }

}
