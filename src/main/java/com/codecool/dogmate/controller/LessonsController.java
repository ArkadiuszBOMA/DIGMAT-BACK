package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.lessons.LessonDto;
import com.codecool.dogmate.dto.lessons.NewLessonDto;
import com.codecool.dogmate.dto.lessons.UpdateLessonDto;
import com.codecool.dogmate.dto.lessonsteps.LessonStepDto;
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
    @GetMapping(value="{id}", params = {"steps"})
    public List<LessonStepDto> getStepsForThisLessonId(@PathVariable Integer id) {
        return lessonsService.getStepsForThisLessonId(id);
    }
    @GetMapping("/{id}")
    public LessonDto getLessonByLessonId(@PathVariable Integer id) {
        return lessonsService.getLessonById(id);
    }
    @PostMapping()
    public LessonDto newLessons(@RequestBody @Valid NewLessonDto lesson) {
        return lessonsService.createLesson(lesson);
    }
    @PutMapping(params={"update"})
    public LessonDto updateLesson(@RequestBody @Valid UpdateLessonDto lesson) {
        return lessonsService.updateLesson(lesson);
    }
    @PutMapping(value="/{id}", params={"archive"})
    public void archiveLesson(@PathVariable Integer id) {
        lessonsService.archiveLesson(id);
    }
    @DeleteMapping("/{id}")
    public void deleteLesson(@PathVariable Integer id) {
        lessonsService.deleteLessonData(id);
    }
}
