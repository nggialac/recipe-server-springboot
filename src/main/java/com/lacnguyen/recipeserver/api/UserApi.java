package com.lacnguyen.recipeserver.api;

import com.lacnguyen.recipeserver.entity.UserEntity;
import com.lacnguyen.recipeserver.repository.UserRepository;
import com.lacnguyen.recipeserver.service.IUserService;
import com.lacnguyen.recipeserver.service.impl.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "USER APIs")
@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserApi {

    @Autowired
    UserRepository userRepository;

    @Autowired
    IUserService iUserService;

    @GetMapping
    public List<UserEntity> findAllUser() {
        return userRepository.findAll();
    }

}
