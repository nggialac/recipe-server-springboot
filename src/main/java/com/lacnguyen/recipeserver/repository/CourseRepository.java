package com.lacnguyen.recipeserver.repository;

import com.lacnguyen.recipeserver.entity.CourseEntity;
import com.lacnguyen.recipeserver.entity.IngredientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    List<CourseEntity> findAll();

    Optional<CourseEntity> findByCourseId(Long id);

    CourseEntity save(CourseEntity objCourse);

    void deleteByCourseId(Long id);

    List<CourseEntity> findByCourseNameContains(String name);

    Optional<CourseEntity> findByRecipe_RecipeId(Long id);

    Optional<CourseEntity> findByCourseIdAndRecipe_RecipeId(Long id, Long recipeId);

    @Query("SELECT i FROM  CourseEntity i WHERE i.recipe.recipeId=:recipeId")
    List<CourseEntity> findAllCourse(@Param("recipeId") Long recipeId);

    @Query("SELECT i FROM  CourseEntity i WHERE i.recipe.recipeId=:recipeId")
    Page<CourseEntity> findAllCourse(@Param("recipeId") Long recipeId, Pageable pageable);

    Page<CourseEntity> findAllByOrderByCourseIdDesc(Pageable pageable);
}
