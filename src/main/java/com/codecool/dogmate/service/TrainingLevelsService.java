package com.codecool.dogmate.service;

import com.codecool.dogmate.dto.traininglevel.NewTrainingLevelDto;
import com.codecool.dogmate.dto.traininglevel.TrainingLevelDto;
import com.codecool.dogmate.entity.TrainingLevel;
import com.codecool.dogmate.mapper.TrainingLevelMapper;
import com.codecool.dogmate.repository.TrainingLevelRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TrainingLevelsService {

    private final TrainingLevelRepository trainingLevelRepository;
    private final TrainingLevelMapper trainingLevelMapper;

    public TrainingLevelsService(TrainingLevelRepository trainingLevelRepository, TrainingLevelMapper trainingLevelMapper) {
        this.trainingLevelRepository = trainingLevelRepository;
        this.trainingLevelMapper = trainingLevelMapper;
    }


    public List<TrainingLevelDto> getTrainingLevels() {
        return trainingLevelRepository.findAllBy().stream()
                .map(trainingLevelMapper::mapEntityToTrainingLevelDto)
                .toList();
    }

    public List<TrainingLevelDto> getTrainingLevels(Pageable pageable) {
        return trainingLevelRepository.findAllBy(pageable).stream()
                .map(trainingLevelMapper::mapEntityToTrainingLevelDto)
                .toList();
    }

    public TrainingLevelDto getTrainingLevelById(Integer id) {
        return trainingLevelRepository.findOneById(id)
                .map(trainingLevelMapper::mapEntityToTrainingLevelDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public TrainingLevelDto createTrainingLevel(NewTrainingLevelDto traininglevel) {
        TrainingLevel entity = trainingLevelMapper.mapTrainingLevelDtoToEntity(traininglevel);
        TrainingLevel savedEntity = trainingLevelRepository.save(entity);
        return trainingLevelMapper.mapEntityToTrainingLevelDto(savedEntity);
    }
}
