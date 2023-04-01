package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.lessons.LessonDto;
import com.codecool.dogmate.dto.lessons.NewLessonDto;
import com.codecool.dogmate.service.LessonsService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1/lessons")
public class LessonsController {
    private final LessonsService lessonsService;
    public LessonsController(LessonsService lessonsService) {
        this.lessonsService = lessonsService;
    }
    @GetMapping()
    public List<LessonDto> getAllLessons() {return lessonsService.getLessons();}
    @GetMapping(params = {"page", "size", "sort"})
    public List<LessonDto> getAllLessonsWithPageable(Pageable pageable) {
        return lessonsService.getLessons(pageable);
    }
    @GetMapping("/{id}")
    public LessonDto getLessonByLessonId(@PathVariable Integer id) {
        return lessonsService.getLessonById(id);
    }
    @PostMapping
    public LessonDto newLessons(@RequestBody @Valid NewLessonDto lesson) {
        return lessonsService.createLesson(lesson);
    }
}
