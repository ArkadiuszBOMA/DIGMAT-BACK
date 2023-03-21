package com.codecool.dogmate.entity;

import com.codecool.dogmate.mapper.Gender;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "animals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_types_id")
    private AnimalType animalType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "breed_id")
    private Breed breed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appuser_id")
    private AppUser appUser;

    @Column(name = "birth_year")
    private Integer birthYear;

    @Column(name = "picture_location")
    private String pictureLocation;

    @Column(name = "description")
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

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

    public Animal(String name, AnimalType animalType, Breed breed, AppUser appUser, Integer birthYear, String pictureLocation, String description, Gender gender) {
        this.name = name;
        this.animalType = animalType;
        this.breed = breed;
        this.appUser = appUser;
        this.birthYear = birthYear;
        this.pictureLocation = pictureLocation;
        this.description = description;
        this.gender = gender;
    }
}
