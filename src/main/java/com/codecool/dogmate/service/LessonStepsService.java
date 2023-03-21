package com.codecool.dogmate.service;

import com.codecool.dogmate.dto.lessonsteps.LessonStepDto;
import com.codecool.dogmate.dto.lessonsteps.NewLessonStepDto;
import com.codecool.dogmate.entity.Lesson;
import com.codecool.dogmate.entity.LessonStep;
import com.codecool.dogmate.mapper.LessonStepMapper;
import com.codecool.dogmate.repository.LessonRepository;
import com.codecool.dogmate.repository.LessonStepRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LessonStepsService {
    private final LessonStepRepository lessonStepRepository;
    private final LessonRepository lessonRepository;
    private final LessonStepMapper lessonStepMapper;

    public LessonStepsService(LessonStepRepository lessonStepRepository, LessonRepository lessonRepository, LessonStepMapper lessonStepMapper) {
        this.lessonStepRepository = lessonStepRepository;
        this.lessonRepository = lessonRepository;
        this.lessonStepMapper = lessonStepMapper;
    }


    public List<LessonStepDto> getLessonSteps() {
        return lessonStepRepository.findAllBy().stream()
                .map(lessonStepMapper::mapEntityToLessonStepDto)
                .toList();
    }
    public List<LessonStepDto> getLessonSteps(Pageable pageable) {
        return lessonStepRepository.findAllBy(pageable).stream()
                .map(lessonStepMapper::mapEntityToLessonStepDto)
                .toList();
    }

    public LessonStepDto getLessonStepById(Integer id) {
        return lessonStepRepository.findOneById(id)
                .map(lessonStepMapper::mapEntityToLessonStepDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public LessonStepDto createLessonStep(NewLessonStepDto lessonstep) {
        LessonStep entity = lessonStepMapper.mapLessonStepDtoToEntity(lessonstep);
        Lesson lesson = lessonRepository.findOneById(lessonstep.lesson())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        entity.setLesson(lesson);
        lesson.getLessonSteps().add(entity);
        LessonStep savedEntity = lessonStepRepository.save(entity);
        return lessonStepMapper.mapEntityToLessonStepDto(savedEntity);
    }
}
