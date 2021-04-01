package com.lacnguyen.recipeserver.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rolee")
public class RoleEntity {

    @Id
    @Column(name = "roleid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column
    private String code;

    @Column
    private String name;

//    @ManyToMany(mappedBy = "roles")
//    private Collection<UserEntity> users = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "role")
    private Collection<UserEntity> users = new ArrayList<>();

}
