package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.lessonanimal.LessonAnimalDto;
import com.codecool.dogmate.dto.lessonanimal.NewLessonAnimalDto;
import com.codecool.dogmate.entity.Animal;
import com.codecool.dogmate.entity.Lesson;
import com.codecool.dogmate.entity.LessonsAnimal;
import org.springframework.stereotype.Component;

@Component
public class LessonAnimalMapper {


    public LessonsAnimal mapNewLessonAnimalDtoToEntity(Animal animal, Lesson lesson) {
        return new LessonsAnimal(
                animal,
                lesson
        );
    }

    public LessonAnimalDto mapEntityToLessonAnimalDto(LessonsAnimal entity) {
        return new LessonAnimalDto(
                entity.getId(),
                entity.getFinished(),
                entity.getAnimal().getId(),
                entity.getLesson().getId()
        );
    }

}
