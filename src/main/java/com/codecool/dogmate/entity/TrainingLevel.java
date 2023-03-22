package com.codecool.dogmate.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "training_levels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TrainingLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @EqualsAndHashCode.Include
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "trainingLevel")
    private Set<Lesson> lessons = new LinkedHashSet<>();

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

    public TrainingLevel(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
