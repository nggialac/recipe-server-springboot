package com.lacnguyen.recipeserver.api;

import com.lacnguyen.recipeserver.entity.RecipeEntity;
import com.lacnguyen.recipeserver.repository.RecipeRepository;
import com.lacnguyen.recipeserver.service.IRecipeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.lang.Long;
import java.util.List;

@Api(value = "Recipe APIs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    public List<RecipeEntity> findRecipeById(@PathVariable("id") Long id) {
        return recipeRepository.findByRecipeId(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public RecipeEntity newRecipe(@RequestBody RecipeEntity newRecipe) {
        return recipeRepository.save(newRecipe);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipeEntity(@PathVariable("id") Long id) {
        recipeRepository.deleteById(id);
    }

}