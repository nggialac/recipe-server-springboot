package com.lacnguyen.recipeserver.repository;

import com.lacnguyen.recipeserver.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
}
