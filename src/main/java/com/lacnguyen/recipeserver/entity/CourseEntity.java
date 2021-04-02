package com.lacnguyen.recipeserver.entity;


import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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
    private String createBy;

    @CreatedDate
    @Column(name = "createdate")
    private Date createDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private RecipeEntity recipe;
}
