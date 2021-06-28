package com.lacnguyen.recipeserver.repository;

import com.lacnguyen.recipeserver.entity.FoodCategoryEntity;
import com.lacnguyen.recipeserver.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {

    // JPQL
    @Query(value = "SELECT r.recipeid, fc.foodcategoryname \n" +
            "FROM recipe as r\n" +
            "INNER JOIN recipe_category as rc\n" +
            "    ON r.recipeid = rc.recipe_id \n" +
            "INNER JOIN food_category as fc\n" +
            "    ON rc.food_category_id = fc.foodcategoryid \n", nativeQuery = true)
    List<FoodCategoryEntity> findFoodCategoryByRecipeId(Long id);

    RecipeEntity findByRecipeId(Long id);

    List<RecipeEntity> findByRecipeNameContains(String name);

    RecipeEntity existsByRecipeId(Long id);

    //Category
    Optional<RecipeEntity> findByRecipeName(String name);
//    Optional<RecipeEntity> findByRecipeId(Long id);
//    @Query(value = "delete * from recipe_category where recipe_id= :recipe_id", nativeQuery = true)
//    void deleteRelation(@Param("recipe_id") Long recipe_id);
    //    @Query("from User where email= :email")
//   User  findByEmail(@Param("email") String email);
}
