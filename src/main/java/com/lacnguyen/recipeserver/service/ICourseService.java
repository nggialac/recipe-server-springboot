package com.lacnguyen.recipeserver.service;

import com.lacnguyen.recipeserver.entity.CourseEntity;

import java.util.List;
import java.util.Optional;

public interface ICourseService {

    List<CourseEntity> findAll();

    Optional<CourseEntity> findByCourseId(Long id);

    CourseEntity save(CourseEntity objCourse);

    void deleteByCourseId(Long id);

    List<CourseEntity> findByCourseNameContains(String name);

    void updateCourse(CourseEntity objCourse);

    ////
    Optional<CourseEntity> getAllCourseByRecipeId(Long id);

    Optional<CourseEntity> findByIdAndRecipeId(Long id, Long recipeId);

    void deleteById(CourseEntity course);
}
