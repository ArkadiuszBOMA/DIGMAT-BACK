package com.codecool.dogmate.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "lessons_animals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonsAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id")
    @NotNull
    private Animal animal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    @NotNull
    @Size(min = 5, max = 50)
    private Lesson lesson;

    @Column(name = "finished")
    private Boolean finished = false;

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

    public LessonsAnimal(Animal animal, Lesson lesson) {
        this.animal = animal;
        this.lesson = lesson;
    }
}
