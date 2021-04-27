package com.lacnguyen.recipeserver.service;

import com.lacnguyen.recipeserver.entity.CourseEntity;

import java.util.List;
import java.util.Optional;

public interface ICourseService {

    List<CourseEntity> findAll();

    List<CourseEntity> findByCourseId(Long id);

    CourseEntity save(CourseEntity objCourse);

    CourseEntity deleteByCourseId(Long id);
}
