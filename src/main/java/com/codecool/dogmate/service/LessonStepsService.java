package com.codecool.dogmate.service;

import com.codecool.dogmate.advice.Exceptions.LessonNotFoundException;
import com.codecool.dogmate.advice.Exceptions.LessonStepNotFoundException;
import com.codecool.dogmate.dto.lessonsteps.LessonStepDto;
import com.codecool.dogmate.dto.lessonsteps.NewLessonStepDto;
import com.codecool.dogmate.dto.lessonsteps.UpdateLessonStepDto;
import com.codecool.dogmate.entity.Lesson;
import com.codecool.dogmate.entity.LessonStep;
import com.codecool.dogmate.mapper.LessonStepMapper;
import com.codecool.dogmate.repository.LessonRepository;
import com.codecool.dogmate.repository.LessonStepRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Slf4j
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
                .sorted(Comparator.comparing(LessonStep::getStepNumber))
                .map(lessonStepMapper::mapEntityToLessonStepDto)
                .toList();
    }
    public List<LessonStepDto> getLessonSteps(Pageable pageable) {
        return lessonStepRepository.findAllBy(pageable).stream()
                .sorted(Comparator.comparing(LessonStep::getStepNumber))
                .map(lessonStepMapper::mapEntityToLessonStepDto)
                .toList();
    }

    public LessonStepDto getLessonStepById(Integer id) {
        return lessonStepRepository.findOneById(id)
                .map(lessonStepMapper::mapEntityToLessonStepDto)
                .orElseThrow(() -> new LessonStepNotFoundException(id));
    }

    public LessonStepDto getLessonStepByName(String name) {
        return lessonStepRepository.findOneByName(name)
                .map(lessonStepMapper::mapEntityToLessonStepDto)
                .orElseThrow(() -> new LessonStepNotFoundException(name));
    }

    public LessonStepDto createLessonStep(NewLessonStepDto lessonstep) {
        LessonStep entity = lessonStepMapper.mapLessonStepDtoToEntity(lessonstep);
        Lesson lesson = lessonRepository.findOneById(lessonstep.lesson())
                .orElseThrow(() -> new LessonNotFoundException(lessonstep.lesson()));
        entity.setLesson(lesson);
        lesson.getLessonSteps().add(entity);
        LessonStep savedEntity = lessonStepRepository.save(entity);
        return lessonStepMapper.mapEntityToLessonStepDto(savedEntity);
    }

    public LessonStepDto updateLessonStep(UpdateLessonStepDto step) {
        log.info("Zaktualizowałem dane dla id {}", step.id());
        LessonStep updateStep = lessonStepRepository.findOneById(step.id())
                .orElseThrow(() -> new LessonStepNotFoundException(step.id()));
        Lesson lesson = lessonRepository.findOneById(step.lessonNumber())
                .orElseThrow(() -> new LessonNotFoundException(step.lessonNumber()));
        updateStep.setName(step.name().trim().toUpperCase().replaceAll("( )+", " "));
        updateStep.setDescription(step.description().trim().replaceAll("( )+", " "));
        updateStep.setStepNumber(step.stepNumber());
        updateStep.setDate_modify(LocalDateTime.now());
        updateStep.setLesson(lesson);
        lessonStepRepository.save(updateStep);
        return lessonStepMapper.mapEntityToLessonStepDto(updateStep);
    }

    public void archiveLessonStep(Integer id) {
        LessonStep archivedStep = lessonStepRepository.findOneById(id)
                .orElseThrow(() -> new LessonStepNotFoundException(id));
        if(!archivedStep.getArchive()) {
            archivedStep.setDate_archive(LocalDateTime.now());
            archivedStep.setArchive(true);
            log.info("Zarchiwizowane dane dla id {}", id);
        } else {
            log.info("Dane już były archiwizowane;");
        }
        lessonStepRepository.save(archivedStep);
    }

    public void deleteLessonStepData(Integer id) {
        lessonStepRepository.findOneById(id)
                .orElseThrow(() -> new LessonStepNotFoundException(id));
        log.info("Usunąłeś krok o id {}", id);
        lessonStepRepository.deleteById(id);
    }
}
