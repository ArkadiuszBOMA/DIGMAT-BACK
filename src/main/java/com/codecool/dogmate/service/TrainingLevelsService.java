package com.codecool.dogmate.service;

import com.codecool.dogmate.advice.Exceptions.TrainingLevelNotFoundException;
import com.codecool.dogmate.dto.lessons.LessonDto;
import com.codecool.dogmate.dto.traininglevel.NewTrainingLevelDto;
import com.codecool.dogmate.dto.traininglevel.TrainingLevelDto;
import com.codecool.dogmate.dto.traininglevel.UpdateTrainingLevelDto;
import com.codecool.dogmate.entity.TrainingLevel;
import com.codecool.dogmate.mapper.LessonMapper;
import com.codecool.dogmate.mapper.TrainingLevelMapper;
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
public class TrainingLevelsService {

    private final TrainingLevelRepository trainingLevelRepository;

    private final LessonRepository lessonRepository;
    private final TrainingLevelMapper trainingLevelMapper;
    private final LessonMapper lessonMapper;

    public TrainingLevelsService(TrainingLevelRepository trainingLevelRepository, LessonRepository lessonRepository, TrainingLevelMapper trainingLevelMapper, LessonMapper lessonMapper) {
        this.trainingLevelRepository = trainingLevelRepository;
        this.lessonRepository = lessonRepository;
        this.trainingLevelMapper = trainingLevelMapper;
        this.lessonMapper = lessonMapper;
    }


    public List<TrainingLevelDto> getTrainingLevels() {
        return trainingLevelRepository.findAllBy().stream()
                .sorted(Comparator.comparing(TrainingLevel::getId))
                .map(trainingLevelMapper::mapEntityToTrainingLevelDto)
                .toList();
    }

    public List<TrainingLevelDto> getTrainingLevels(Pageable pageable) {
        return trainingLevelRepository.findAllBy(pageable).stream()
                .sorted(Comparator.comparing(TrainingLevel::getId))
                .map(trainingLevelMapper::mapEntityToTrainingLevelDto)
                .toList();
    }

    public TrainingLevelDto getTrainingLevelById(Integer id) {
        return trainingLevelRepository.findOneById(id)
                .map(trainingLevelMapper::mapEntityToTrainingLevelDto)
                .orElseThrow(() -> new TrainingLevelNotFoundException(id));
    }

    public TrainingLevelDto getTrainingLevelByName(String name) {
        return trainingLevelRepository.findOneByName(name)
                .map(trainingLevelMapper::mapEntityToTrainingLevelDto)
                .orElseThrow(() -> new TrainingLevelNotFoundException(name));
    }

    public TrainingLevelDto createTrainingLevel(NewTrainingLevelDto traininglevel) {
        TrainingLevel entity = trainingLevelMapper.mapTrainingLevelDtoToEntity(traininglevel);
        entity.setName(traininglevel.name().trim().toUpperCase().replaceAll("( )+", " "));
        TrainingLevel savedEntity = trainingLevelRepository.save(entity);
        return trainingLevelMapper.mapEntityToTrainingLevelDto(savedEntity);
    }

    public TrainingLevelDto updateTrainingLevel(UpdateTrainingLevelDto trainingLevel) {
        log.info("Zaktualizowałem dane dla id {}", trainingLevel.id());
        TrainingLevel updateTrainingLeve = trainingLevelRepository.findOneById(trainingLevel.id())
                .orElseThrow(() -> new TrainingLevelNotFoundException(trainingLevel.id()));
        updateTrainingLeve.setName(trainingLevel.name().trim().toUpperCase().replaceAll("( )+", " "));
        updateTrainingLeve.setDate_modify(LocalDateTime.now());
        trainingLevelRepository.save(updateTrainingLeve);
        return trainingLevelMapper.mapEntityToTrainingLevelDto(updateTrainingLeve);
    }

    public void archiveTrainingLevel(Integer id) {
        TrainingLevel archivedTrainingLevel = trainingLevelRepository.findOneById(id)
                .orElseThrow(() -> new TrainingLevelNotFoundException(id));
        if(!archivedTrainingLevel.getArchive()) {
            archivedTrainingLevel.setDate_archive(LocalDateTime.now());
            archivedTrainingLevel.setArchive(true);
            log.info("Zarchiwizowane dane dla id {}", id);
        } else {
            log.info("Dane już były archiwizowane;");
        }
        trainingLevelRepository.save(archivedTrainingLevel);
    }

    public void deleteTrainingLevelData(Integer id) {
        trainingLevelRepository.findOneById(id)
                .orElseThrow(() -> new TrainingLevelNotFoundException(id));
        log.info("Usunąłeś poziom trudności o id {}", id);
        trainingLevelRepository.deleteById(id);
    }

    public List<LessonDto> getLessonForThisTrainingLevelId(Integer id) {
        return lessonRepository.findAllByTrainingLevelId(id)
                .stream()
                .map(lessonMapper::mapEntityToLessonDto)
                .toList();
    }
}
