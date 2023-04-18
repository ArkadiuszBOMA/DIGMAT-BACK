package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.lessonanimal.LessonAnimalDto;
import com.codecool.dogmate.entity.*;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class LessonAnimalMapperTest {

    private final LessonAnimalMapper lessonAnimalMapper = new LessonAnimalMapper();
    private final EasyRandom easyRandom = new EasyRandom();


    @Test
    void mapNewLessonAnimalDtoToEntity() {
        //given
        LessonsAnimal lessonsAnimal = easyRandom.nextObject(LessonsAnimal.class);
        //when
        LessonAnimalDto actual = lessonAnimalMapper.mapEntityToLessonAnimalDto(lessonsAnimal);
        //then
        LessonAnimalDto expected = new LessonAnimalDto(
                lessonsAnimal.getId(),
                lessonsAnimal.getFinished(),
                lessonsAnimal.getAnimal().getId(),
                lessonsAnimal.getLesson().getId(),
                lessonsAnimal.getDate_archive(),
                lessonsAnimal.getDate_modify(),
                lessonsAnimal.getDate_create(),
                lessonsAnimal.getArchive()
        );
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    void mapEntityToLessonAnimalDto() {
        //given
        Animal animalN = easyRandom.nextObject(Animal.class);
        Lesson lessonN = easyRandom.nextObject(Lesson.class);
        LessonAnimalDto actual = new LessonAnimalDto(
                1,
                false,
                animalN.getId(),
                lessonN.getId(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                false
        );
        //when
        LessonsAnimal expected = lessonAnimalMapper.mapNewLessonAnimalDtoToEntity(animalN, lessonN);
        //then
        assertLessonAnimalEntityIsCorrect(actual, expected);
    }

    private void assertLessonAnimalEntityIsCorrect(LessonAnimalDto dto, LessonsAnimal actual) {
        assertThat(actual.getAnimal().getId()).isEqualTo(dto.animalId());
    }
}