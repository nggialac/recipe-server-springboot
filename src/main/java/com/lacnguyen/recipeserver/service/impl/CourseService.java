package com.lacnguyen.recipeserver.service.impl;

import com.lacnguyen.recipeserver.entity.CourseEntity;
import com.lacnguyen.recipeserver.entity.RecipeEntity;
import com.lacnguyen.recipeserver.repository.CourseRepository;
import com.lacnguyen.recipeserver.repository.RecipeRepository;
import com.lacnguyen.recipeserver.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {
    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<CourseEntity> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<CourseEntity> findByCourseId(Long id) {
        return courseRepository.findByCourseId(id);
    }

    @Override
    public CourseEntity save(CourseEntity objCourse) {
        return courseRepository.save(objCourse);
    }

    @Override
    public void deleteByCourseId(Long id) {
        courseRepository.deleteByCourseId(id);
    }

    @Override
    public List<CourseEntity> findByCourseNameContains(String name){
        return courseRepository.findByCourseNameContains(name);
    }

    @Override
    public void updateCourse(CourseEntity objCourse){
        courseRepository.save(objCourse);
    }
}
