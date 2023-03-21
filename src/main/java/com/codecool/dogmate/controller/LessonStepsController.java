package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.lessonsteps.LessonStepDto;
import com.codecool.dogmate.dto.lessonsteps.NewLessonStepDto;
import com.codecool.dogmate.service.LessonStepsService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessonstep")
public class LessonStepsController {
    private final LessonStepsService lessonStepService;
    public LessonStepsController(LessonStepsService lessonStepService) {
        this.lessonStepService = lessonStepService;
    }
    @GetMapping
    public List<LessonStepDto> getAllLessonSteps() {return lessonStepService.getLessonSteps();}
    @GetMapping(params = {"page", "size", "sort"})
    public List<LessonStepDto> getAllLessonStepsWithPageable(Pageable pageable) {
        return lessonStepService.getLessonSteps(pageable);
    }
    @GetMapping("/{id}")
    public LessonStepDto getLessonStepByLessonStepId(@PathVariable Integer id) {
        return lessonStepService.getLessonStepById(id);
    }
    @PostMapping
    public LessonStepDto newLessonStep(@RequestBody NewLessonStepDto lessonstep) {
        return lessonStepService.createLessonStep(lessonstep);
    }
}
