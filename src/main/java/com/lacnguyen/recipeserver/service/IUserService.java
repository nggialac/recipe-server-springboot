package com.lacnguyen.recipeserver.service;


import com.lacnguyen.recipeserver.entity.UserEntity;

import java.util.List;

public interface IUserService {
    UserEntity userLogin(String username, String password);
    UserEntity getInfo(Long id);
}
