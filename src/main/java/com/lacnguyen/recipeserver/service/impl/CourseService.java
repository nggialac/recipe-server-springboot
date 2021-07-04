package com.lacnguyen.recipeserver.service.impl;

import com.lacnguyen.recipeserver.entity.CourseEntity;
import com.lacnguyen.recipeserver.entity.RecipeEntity;
import com.lacnguyen.recipeserver.repository.CourseRepository;
import com.lacnguyen.recipeserver.repository.RecipeRepository;
import com.lacnguyen.recipeserver.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<CourseEntity> findAll(int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return courseRepository.findAll(page);
    }

    @Override
    public List<CourseEntity> findAllCourse(Long id) {
        return courseRepository.findAllCourse(id);
    }

    @Override
    public Page<CourseEntity> findAllCourse(Long id, int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return courseRepository.findAllCourse(id, page);
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

    @Override
    public Optional<CourseEntity> getAllCourseByRecipeId(Long id){
        return courseRepository.findByRecipe_RecipeId(id);
    }

    @Override
    public Optional<CourseEntity> findByIdAndRecipeId(Long id, Long recipeId){
        return courseRepository.findByCourseIdAndRecipe_RecipeId(id, recipeId);
    }

    @Override
    public void deleteById(CourseEntity course){
        courseRepository.delete(course);
    }
}
