package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.traininglevel.NewTrainingLevelDto;
import com.codecool.dogmate.dto.traininglevel.TrainingLevelDto;
import com.codecool.dogmate.entity.TrainingLevel;
import org.springframework.stereotype.Component;

@Component
public class TrainingLevelMapper {

    public TrainingLevel mapTrainingLevelDtoToEntity(NewTrainingLevelDto dto) {return new TrainingLevel(
            dto.name(),
            dto.description()
    );}

    public TrainingLevelDto mapEntityToTrainingLevelDto(TrainingLevel entity) {
        return new TrainingLevelDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription()
        );
    }
}
