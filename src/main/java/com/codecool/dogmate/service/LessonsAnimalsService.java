package com.codecool.dogmate.service;

import com.codecool.dogmate.dto.lessonanimal.LessonAnimalDto;
import com.codecool.dogmate.dto.lessonanimal.NewLessonAnimalDto;
import com.codecool.dogmate.entity.*;
import com.codecool.dogmate.mapper.LessonAnimalMapper;
import com.codecool.dogmate.repository.AnimalRepository;
import com.codecool.dogmate.repository.LessonAnimalRepository;
import com.codecool.dogmate.repository.LessonRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LessonsAnimalsService {

    private final LessonAnimalRepository lessonAnimalRepository;
    private final LessonRepository lessonRepository;
    private final AnimalRepository animalRepository;
    private final LessonAnimalMapper lessonAnimalMapper;

    public LessonsAnimalsService(LessonAnimalRepository lessonAnimalRepository, LessonRepository lessonRepository, AnimalRepository animalRepository, LessonAnimalMapper lessonAnimalMapper) {
        this.lessonAnimalRepository = lessonAnimalRepository;
        this.lessonRepository = lessonRepository;
        this.animalRepository = animalRepository;
        this.lessonAnimalMapper = lessonAnimalMapper;
    }


    public List<LessonAnimalDto> getLessonsAnimal() {
        return lessonAnimalRepository.findAllBy().stream()
                .map(lessonAnimalMapper::mapEntityToLessonAnimalDto)
                .toList();
    }
    public List<LessonAnimalDto> getLessonsAnimal(Pageable pageable) {
        return lessonAnimalRepository.findAllBy(pageable).stream()
                .map(lessonAnimalMapper::mapEntityToLessonAnimalDto)
                .toList();
    }

    public LessonAnimalDto getLessonAnimalById(Integer id) {
        return lessonAnimalRepository.findOneById(id)
                .map(lessonAnimalMapper::mapEntityToLessonAnimalDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public LessonAnimalDto createLessonAnimal(NewLessonAnimalDto lessonanimal) {
        Lesson lesson = lessonRepository.findOneById(lessonanimal.lessonId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Animal animal = animalRepository.findOneById(lessonanimal.animalId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        LessonsAnimal entity = lessonAnimalMapper.mapNewLessonAnimalDtoToEntity(animal, lesson);
        LessonsAnimal savedEntity = lessonAnimalRepository.save(entity);
        return lessonAnimalMapper.mapEntityToLessonAnimalDto(savedEntity);
    }
}
