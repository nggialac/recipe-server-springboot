package com.lacnguyen.recipeserver.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lacnguyen.recipeserver.entity.LoginEntity;
import com.lacnguyen.recipeserver.entity.UserEntity;
import com.lacnguyen.recipeserver.service.IUserService;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Api(value = "USER APIs")
@CrossOrigin
@RestController
@RequestMapping("/login")
public class UserApi {

    @Autowired
    IUserService iUserService;

//    @PostMapping
//    public ResponseEntity<UserEntity> userLogin(@RequestBody String json) throws JsonProcessingException {
//        //System.out.println(json);
//        ObjectMapper mapper = new ObjectMapper();
//        LoginEntity loginEntity = mapper.readValue(json, LoginEntity.class);
//        return new ResponseEntity<>(iUserService.userLogin(loginEntity.getUsername(), loginEntity.getPassword()), HttpStatus.OK);
//    }

    @GetMapping("/user/{id}")
    public UserEntity getInfoUser(@RequestParam("id") Long id) {
        return iUserService.getInfo(id);
    }

    @PostMapping
    public ResponseEntity<LoginEntity> userLogin(@RequestBody String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        LoginEntity loginEntity = mapper.readValue(json, LoginEntity.class);
        UserEntity userEntity = iUserService.userLogin(loginEntity.getUsername(), loginEntity.getPassword());
        if(userEntity != null){
            String token = getJWTToken(userEntity.getUserName());
            LoginEntity user = new LoginEntity();
            user.setUsername(userEntity.getUserName());
            user.setFullname(userEntity.getFullName());
            user.setToken(token);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("testJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes()).compact();
        return "Bearer " + token;
    }


}
