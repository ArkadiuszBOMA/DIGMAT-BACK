package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.lessonsteps.LessonStepDto;
import com.codecool.dogmate.dto.lessonsteps.NewLessonStepDto;
import com.codecool.dogmate.entity.Lesson;
import com.codecool.dogmate.entity.LessonStep;
import org.springframework.stereotype.Component;

@Component
public class LessonStepMapper {

    public LessonStep mapLessonStepDtoToEntity(NewLessonStepDto dto) {

        return new LessonStep(
                dto.name(),
                dto.description(),
                dto.stepNumber()
                );
    }

    public LessonStepDto mapEntityToLessonStepDto(LessonStep entity) {
        return new LessonStepDto(
                entity.getId(),
                entity.getName(),
                entity.getLesson().getId(),
                entity.getDescription(),
                entity.getStepNumber()
        );
    }

}
