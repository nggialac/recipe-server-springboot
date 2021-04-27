package com.lacnguyen.recipeserver.repository;

import com.lacnguyen.recipeserver.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserNameAndAndPassword(String username, String password);
    UserEntity findByUserId(Long id);
}
