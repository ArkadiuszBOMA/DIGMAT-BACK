package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.lessons.LessonDto;
import com.codecool.dogmate.dto.lessons.NewLessonDto;
import com.codecool.dogmate.entity.Lesson;
import com.codecool.dogmate.entity.TrainingLevel;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {
    private final LessonStepMapper lessonStepMapper;

    public LessonMapper(LessonStepMapper lessonStepMapper) {
        this.lessonStepMapper = lessonStepMapper;
    }


    public Lesson mapLessonDtoToEntity(NewLessonDto dto, TrainingLevel trainingLevel) {

        return new Lesson(
                dto.name(),
                trainingLevel,
                dto.description(),
                dto.imageLocation()
                );
    }

    public LessonDto mapEntityToLessonDto(Lesson entity) {
        return new LessonDto(
                entity.getId(),
                entity.getName(),
                entity.getTrainingLevel().getName(),
                entity.getDescription(),
                entity.getImageLocation(),
                entity.getDate_create(),
                entity.getDate_modify(),
                entity.getDate_archive(),
                entity.getArchive()

        );
    }

}
