package com.lacnguyen.recipeserver.service;

import com.lacnguyen.recipeserver.entity.CourseEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ICourseService {

    List<CourseEntity> findAll();

    List<CourseEntity> findAllCourse(Long id);
    Page<CourseEntity> findAllCourse(Long id, int pageNumber, int pageSize);

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
