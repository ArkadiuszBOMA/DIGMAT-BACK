package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.traininglevel.NewTrainingLevelDto;
import com.codecool.dogmate.dto.traininglevel.TrainingLevelDto;
import com.codecool.dogmate.service.TrainingLevelsService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/traininglevel")
public class TrainingLevelsController {

    private final TrainingLevelsService trainingLevelsService;

    public TrainingLevelsController(TrainingLevelsService trainingLevelsService) {
        this.trainingLevelsService = trainingLevelsService;
    }

    @GetMapping
    public List<TrainingLevelDto> getAllTrainingLevel() {return trainingLevelsService.getTrainingLevels();}

    @GetMapping(params = {"page", "size", "sort"})
    public List<TrainingLevelDto> getAllTrainingLevelsWithPageable(Pageable pageable) {
        return trainingLevelsService.getTrainingLevels(pageable);
    }

    @GetMapping("/{id}")
    public TrainingLevelDto getTrainingLevelByTrainingLevelId(@PathVariable Integer id) {
        return trainingLevelsService.getTrainingLevelById(id);
    }

    @PostMapping
    public TrainingLevelDto newTrainingLevel(@RequestBody NewTrainingLevelDto traininglevel) {
        return trainingLevelsService.createTrainingLevel(traininglevel);
    }
}

