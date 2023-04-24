package com.codecool.dogmate.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "breeds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Breed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @EqualsAndHashCode.Include
    @Column(name = "name", unique = true)
    @Size(min = 3, max = 55)
    private String name;

    @Column(name = "archive")
    private Boolean archive = false;

    @Version
    private Integer version;

    @Column(name = "date_create")
    private LocalDateTime date_create = LocalDateTime.now();

    @Column(name = "date_modify")
    private LocalDateTime date_modify ;

    @Column(name = "date_archive")
    private LocalDateTime date_archive ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_types_id")
    @NotNull
    private AnimalType animalTypes;

    public Breed(String name) {
        this.name = name;
    }

    public Breed(Integer id, String name, AnimalType animalTypes) {
        this.id = id;
        this.name = name;
        this.animalTypes = animalTypes;
    }
    public Breed(Integer id) {
        this.id = id;
    }
}
