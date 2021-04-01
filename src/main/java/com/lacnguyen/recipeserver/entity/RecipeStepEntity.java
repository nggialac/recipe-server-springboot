package com.lacnguyen.recipeserver.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @Column(name = "stepnumber")
    private int stepNumber;

    @Column(name = "stepdescription")
    private String stepDescription;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private RecipeEntity recipe;
}
