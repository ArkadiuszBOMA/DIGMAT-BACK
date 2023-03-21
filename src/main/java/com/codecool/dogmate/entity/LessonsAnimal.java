package com.codecool.dogmate.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "lessons_animals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonsAnimal {

    @EmbeddedId
    private LessonsAnimalId id;

    @MapsId("animalId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @MapsId("lessonId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @Column(name = "progress")
    private Integer progress;

    @Column(name = "finished")
    private Boolean finished;

}
