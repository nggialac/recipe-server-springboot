package com.lacnguyen.recipeserver.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "measurement")
public class MeasurementEntity {

    @Id
    @Column(name = "measurementid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long measurementId;

    @Column(name = "measurementname")
    private String measurementName;

    @JsonManagedReference
    @OneToMany(mappedBy = "measurement")
    private Collection<IngredientEntity> ingredients = new ArrayList<>();
}
