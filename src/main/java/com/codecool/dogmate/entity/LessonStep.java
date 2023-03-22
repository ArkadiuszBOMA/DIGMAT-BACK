package com.codecool.dogmate.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lesson_steps")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @Column(name = "step_number")
    private Integer stepNumber;

    @Column(name = "description")
    @Type(type = "org.hibernate.type.TextType")
    private String description;

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

    public LessonStep(String name, String description, Integer stepNumber) {
        this.name = name;
        this.description = description;
        this.stepNumber = stepNumber;
    }

}
