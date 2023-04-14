package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.traininglevel.NewTrainingLevelDto;
import com.codecool.dogmate.dto.traininglevel.TrainingLevelDto;
import com.codecool.dogmate.dto.traininglevel.UpdateTrainingLevelDto;
import com.codecool.dogmate.service.TrainingLevelsService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1/training-levels")
public class TrainingLevelsController {

    private final TrainingLevelsService trainingLevelsService;

    public TrainingLevelsController(TrainingLevelsService trainingLevelsService) {
        this.trainingLevelsService = trainingLevelsService;
    }

    @GetMapping()
    public List<TrainingLevelDto> getAllTrainingLevel() {return trainingLevelsService.getTrainingLevels();}

    @GetMapping(params = {"page", "size", "sort"})
    public List<TrainingLevelDto> getAllTrainingLevelsWithPageable(Pageable pageable) {
        return trainingLevelsService.getTrainingLevels(pageable);
    }

    @GetMapping("/{id}")
    public TrainingLevelDto getTrainingLevelByTrainingLevelId(@PathVariable Integer id) {
        return trainingLevelsService.getTrainingLevelById(id);
    }

    @PostMapping("/add")
    public TrainingLevelDto newTrainingLevel(@RequestBody @Valid NewTrainingLevelDto traininglevel) {
        return trainingLevelsService.createTrainingLevel(traininglevel);
    }
    @PutMapping("/update/{id}")
    public void updateTrainingLevel(@RequestBody @Valid UpdateTrainingLevelDto trainingLevel) {
        trainingLevelsService.updateTrainingLevel(trainingLevel);
    }

    @PutMapping("/archive/{id}")
    public void archiveTrainingLevel(@PathVariable Integer id) {
        trainingLevelsService.archiveTrainingLevel(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTrainingLevel(@PathVariable Integer id) {
        trainingLevelsService.deleteTrainingLevelData(id);
    }
}

