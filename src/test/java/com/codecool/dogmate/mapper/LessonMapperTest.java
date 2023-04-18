package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.lessons.LessonDto;
import com.codecool.dogmate.dto.lessons.NewLessonDto;
import com.codecool.dogmate.entity.Lesson;
import com.codecool.dogmate.entity.TrainingLevel;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LessonMapperTest {

    private final LessonStepMapper lessonStepMapper = new LessonStepMapper();
    private final LessonMapper lessonMapper = new LessonMapper(lessonStepMapper);
    private final EasyRandom easyRandom = new EasyRandom();

    @Test
    void mapLessonDtoToEntity() {
        // given:
        Lesson lesson = easyRandom.nextObject(Lesson.class);
        // when:
        LessonDto actual = lessonMapper.mapEntityToLessonDto(lesson);
        // then:
        LessonDto expected = new LessonDto(
                lesson.getId(),
                lesson.getName(),
                lesson.getTrainingLevel().getName(),
                lesson.getDescription(),
                lesson.getImageLocation(),
                lesson.getDate_create(),
                lesson.getDate_modify(),
                lesson.getDate_archive(),
                lesson.getArchive()
        );

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void mapEntityToLessonDto() {
        //given
        NewLessonDto dto = easyRandom.nextObject(NewLessonDto.class);
        TrainingLevel trainingLevel = easyRandom.nextObject(TrainingLevel.class);
        // when:
        Lesson actual = lessonMapper.mapLessonDtoToEntity(dto, trainingLevel);
        // then:
        assertEntityIsCorrect(dto, actual);
    }

    private void assertEntityIsCorrect(NewLessonDto dto, Lesson actual) {
        assertThat(actual.getName()).isEqualTo(dto.name());
        assertThat(actual.getDescription()).isEqualTo(dto.description());
    }
}