package com.lacnguyen.recipeserver.service;


import com.lacnguyen.recipeserver.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    UserEntity userLogin(String username, String password);
    UserEntity getInfo(Long id);
    UserEntity getInfo(String username);
    boolean isExist(Long id);
    Optional<UserEntity> getUserById(Long id);
    UserEntity save(UserEntity userEntity);
    UserEntity findUserByName(String name);
}
