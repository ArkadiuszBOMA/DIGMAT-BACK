package com.codecool.dogmate.service;

import com.codecool.dogmate.advice.Exceptions.LessonNotFoundException;
import com.codecool.dogmate.advice.Exceptions.TrainingLevelNotFoundException;
import com.codecool.dogmate.dto.lessons.LessonDto;
import com.codecool.dogmate.dto.lessons.NewLessonDto;
import com.codecool.dogmate.dto.lessons.UpdateLessonDto;
import com.codecool.dogmate.entity.Lesson;
import com.codecool.dogmate.entity.TrainingLevel;
import com.codecool.dogmate.mapper.LessonMapper;
import com.codecool.dogmate.repository.LessonRepository;
import com.codecool.dogmate.repository.TrainingLevelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
public class LessonsService {
    private final LessonRepository lessonRepository;
    private final TrainingLevelRepository trainingLevelRepository;
    private final LessonMapper lessonMapper;

    public LessonsService(LessonRepository lessonRepository, TrainingLevelRepository trainingLevelRepository, LessonMapper lessonMapper) {
        this.lessonRepository = lessonRepository;
        this.trainingLevelRepository = trainingLevelRepository;
        this.lessonMapper = lessonMapper;
    }

    public List<LessonDto> getLessons() {
        return lessonRepository.findAllBy().stream()
                .sorted(Comparator.comparing(Lesson::getName))
                .map(lessonMapper::mapEntityToLessonDto)
                .toList();
    }
    public List<LessonDto> getLessons(Pageable pageable) {
        return lessonRepository.findAllBy(pageable).stream()
                .sorted(Comparator.comparing(Lesson::getName))
                .map(lessonMapper::mapEntityToLessonDto)
                .toList();
    }

    public LessonDto getLessonById(Integer id) {
        return lessonRepository.findOneById(id)
                .map(lessonMapper::mapEntityToLessonDto)
                .orElseThrow(() -> new LessonNotFoundException(id));
    }

    public LessonDto getLessonByName(String name) {
        return lessonRepository.findOneByName(name)
                .map(lessonMapper::mapEntityToLessonDto)
                .orElseThrow(() -> new LessonNotFoundException(name));
    }

    public LessonDto createLesson(NewLessonDto lesson) {
        TrainingLevel trainingLevel = trainingLevelRepository.findOneById(lesson.trainingLevel())
                .orElseThrow(() -> new TrainingLevelNotFoundException(lesson.trainingLevel()));
        Lesson entity = lessonMapper.mapLessonDtoToEntity(lesson, trainingLevel);
        Lesson savedEntity = lessonRepository.save(entity);
        return lessonMapper.mapEntityToLessonDto(savedEntity);
    }

    public void updateLesson(UpdateLessonDto lesson) {
        log.info("Zaktualizowałem dane dla id {}", lesson.id());
        Lesson updateLesson = lessonRepository.findOneById(lesson.id())
                .orElseThrow(() -> new LessonNotFoundException(lesson.id()));
        updateLesson.setName(lesson.name().trim().toUpperCase().replaceAll("( )+", " "));
        updateLesson.setDate_modify(LocalDateTime.now());
        lessonRepository.save(updateLesson);
    }

    public void archiveLesson(Integer id) {
        Lesson archivedLesson = lessonRepository.findOneById(id)
                .orElseThrow(() -> new LessonNotFoundException(id));
        if(!archivedLesson.getArchive()) {
            archivedLesson.setDate_archive(LocalDateTime.now());
            archivedLesson.setArchive(true);
            log.info("Zarchiwizowane dane dla id {}", id);
        } else {
            log.info("Dane już były archiwizowane;");
        }
        lessonRepository.save(archivedLesson);
    }
}
