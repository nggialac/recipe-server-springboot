package com.lacnguyen.recipeserver.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recipe_step")
public class RecipeStepEntity {

    @Id
    @Column(name = "stepid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stepId;

    @Column(name = "stepdescription")
    private String stepDescription;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "recipe_id", unique = true)
    private RecipeEntity recipe;
}
