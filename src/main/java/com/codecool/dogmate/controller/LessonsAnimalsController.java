package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.lessonanimal.LessonAnimalDto;
import com.codecool.dogmate.dto.lessonanimal.NewLessonAnimalDto;
import com.codecool.dogmate.service.LessonsAnimalsService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1/lessons-animals")
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
    @GetMapping("/{id}")
    public LessonAnimalDto getLessonAnimalByLessonId(@PathVariable Integer id) {
        return lessonsAnimalsService.getLessonAnimalById(id);
    }
    @PostMapping("/add")
    public LessonAnimalDto newLessonsAnimals(@RequestBody @Valid NewLessonAnimalDto lessonanimal) {
        return lessonsAnimalsService.createLessonAnimal(lessonanimal);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteLessonsAnimals(@PathVariable Integer id) {
        lessonsAnimalsService.deleteLessonAnimalData(id);
    }
}
