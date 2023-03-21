package com.codecool.dogmate.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonsAnimalId implements Serializable {

    private static final long serialVersionUID = 474489709784510173L;
    @Column(name = "animal_id")
    private Integer animalId;

    @Column(name = "lesson_id")
    private Integer lessonId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LessonsAnimalId entity = (LessonsAnimalId) o;
        return Objects.equals(this.lessonId, entity.lessonId) &&
                Objects.equals(this.animalId, entity.animalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lessonId, animalId);
    }

}
