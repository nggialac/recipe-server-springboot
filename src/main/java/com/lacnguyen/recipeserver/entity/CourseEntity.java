package com.lacnguyen.recipeserver.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course")
public class CourseEntity {

    @Id
    @Column(name = "courseid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(name = "coursename")
    private String courseName;

    @CreatedBy
    @Column(name = "createby")
    private Date createBy;

    @CreatedDate
    @Column(name = "createdate")
    private Date createDate;

    @OneToMany(mappedBy = "course")
    private Collection<RecipeEntity> recipes = new ArrayList<>();
}
