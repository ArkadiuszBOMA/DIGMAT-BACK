package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.lessonanimal.LessonAnimalDto;
import com.codecool.dogmate.dto.lessonanimal.NewLessonAnimalDto;
import com.codecool.dogmate.dto.lessons.LessonDto;
import com.codecool.dogmate.dto.lessons.NewLessonDto;
import com.codecool.dogmate.service.LessonsAnimalsService;
import com.codecool.dogmate.service.LessonsService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lessons-animals/")
public class LessonsAnimalsController {
    private final LessonsAnimalsService lessonsAnimalsService;
    public LessonsAnimalsController(LessonsAnimalsService lessonsAnimalsService) {
        this.lessonsAnimalsService = lessonsAnimalsService;
    }
    @GetMapping()
    public List<LessonAnimalDto> getAllLessonsAnimals() {return lessonsAnimalsService.getLessonsAnimal();}
    @GetMapping(params = {"page", "size", "sort"})
    public List<LessonAnimalDto> getAllLessonsAnimalsWithPageable(Pageable pageable) {
        {return lessonsAnimalsService.getLessonsAnimal();}
    }
    @GetMapping("{id}")
    public LessonAnimalDto getLessonAnimalByLessonId(@PathVariable Integer id) {
        return lessonsAnimalsService.getLessonAnimalById(id);
    }
    @PostMapping
    public LessonAnimalDto newLessonsAnimals(@RequestBody NewLessonAnimalDto lessonanimal) {
        return lessonsAnimalsService.createLessonAnimal(lessonanimal);
    }
}
