package com.lacnguyen.recipeserver.repository;

import com.lacnguyen.recipeserver.entity.TipsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipsRepository extends JpaRepository<TipsEntity, Long> {

}
