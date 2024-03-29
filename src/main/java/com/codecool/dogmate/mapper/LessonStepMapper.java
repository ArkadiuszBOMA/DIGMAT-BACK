package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.lessonsteps.LessonStepDto;
import com.codecool.dogmate.dto.lessonsteps.NewLessonStepDto;
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
                entity.getLesson().getName(),
                entity.getDescription(),
                entity.getStepNumber(),
                entity.getDate_create(),
                entity.getDate_modify(),
                entity.getDate_archive(),
                entity.getArchive()
        );
    }

}
