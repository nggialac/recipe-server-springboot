package com.lacnguyen.recipeserver.api;

import com.lacnguyen.recipeserver.entity.UserEntity;
import com.lacnguyen.recipeserver.repository.UserRepository;
import com.lacnguyen.recipeserver.service.IUserService;
import com.lacnguyen.recipeserver.service.impl.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "USER APIs")
@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserApi {

    @Autowired
    IUserService iUserService;

    @PostMapping
    public UserEntity userLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        return iUserService.login(username, password);
    }

    @GetMapping("/{id}")
    public UserEntity getInfoUser(@RequestParam("id") Long id) {
        return iUserService.getInfo(id);
    }
}
