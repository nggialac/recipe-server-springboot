package com.lacnguyen.recipeserver.repository;

import com.lacnguyen.recipeserver.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {

    // JPQL
    @Query(value = "SELECT r.recipeid, fc.foodcategoryname \n" +
            "FROM recipe as r\n" +
            "INNER JOIN recipe_category as rc\n" +
            "    ON r.recipeid = rc.recipe_id \n" +
            "INNER JOIN food_category as fc\n" +
            "    ON rc.food_category_id = fc.foodcategoryid \n", nativeQuery = true)
    List<Object> findFoodCategoryByRecipeId();
}
