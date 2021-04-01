package com.lacnguyen.recipeserver.service;

import com.lacnguyen.recipeserver.entity.CourseEntity;

import java.util.Optional;

public interface ICourseService {
    Optional<CourseEntity> findById(Long id);
}
