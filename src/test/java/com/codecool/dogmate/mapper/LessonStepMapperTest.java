package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.lessonsteps.LessonStepDto;
import com.codecool.dogmate.dto.lessonsteps.NewLessonStepDto;
import com.codecool.dogmate.entity.LessonStep;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LessonStepMapperTest {

    private final LessonStepMapper lessonStepMapper = new LessonStepMapper();
    private final EasyRandom easyRandom = new EasyRandom();

    @Test
    void mapLessonStepDtoToEntity() {
        //given
        LessonStep lessonStep = easyRandom.nextObject(LessonStep.class);
        //when
        LessonStepDto actual = lessonStepMapper.mapEntityToLessonStepDto(lessonStep);
        //then
        LessonStepDto expected = new LessonStepDto(
                lessonStep.getId(),
                lessonStep.getName(),
                lessonStep.getLesson().getId(),
                lessonStep.getDescription(),
                lessonStep.getStepNumber(),
                lessonStep.getDate_create(),
                lessonStep.getDate_modify(),
                lessonStep.getDate_archive(),
                lessonStep.getArchive()
        );

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void mapEntityToLessonStepDto() {
        //given
        NewLessonStepDto lessonStepDto = easyRandom.nextObject(NewLessonStepDto.class);
        //when
        LessonStep actual = lessonStepMapper.mapLessonStepDtoToEntity(lessonStepDto);
        //when
        assertLessonStepEntityIsCorrect(lessonStepDto, actual);

    }

    private void assertLessonStepEntityIsCorrect(NewLessonStepDto lessonStepDto, LessonStep actual) {
        assertThat(actual.getName()).isEqualTo(lessonStepDto.name());
        assertThat(actual.getDescription()).isEqualTo(lessonStepDto.description());
        assertThat(actual.getStepNumber()).isEqualTo(lessonStepDto.stepNumber());
    }
}