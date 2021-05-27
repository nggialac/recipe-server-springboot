package com.lacnguyen.recipeserver.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginEntity {
        @JsonProperty("username")
        public String username;

        @JsonProperty("password")
        public String password;


}

