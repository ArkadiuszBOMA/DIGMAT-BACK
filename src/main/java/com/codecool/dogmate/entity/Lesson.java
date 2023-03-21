package com.codecool.dogmate.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @EqualsAndHashCode.Include
    @Column(name = "name", unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_level_id")
    private TrainingLevel trainingLevel;

    @Column(name = "description")
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @Column(name = "image_location")
    private String imageLocation;

    @OneToMany(mappedBy = "lesson")
    private Set<LessonStep> lessonSteps = new LinkedHashSet<>();

    public Lesson(String name, TrainingLevel trainingLevel, String description, String imageLocation) {
        this.name = name;
        this.trainingLevel = trainingLevel;
        this.description = description;
        this.imageLocation = imageLocation;
    }
}
