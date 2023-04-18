package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.traininglevel.NewTrainingLevelDto;
import com.codecool.dogmate.dto.traininglevel.TrainingLevelDto;
import com.codecool.dogmate.entity.TrainingLevel;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TrainingLevelMapperTest {

    private final TrainingLevelMapper trainingLevelMapper = new TrainingLevelMapper();
    private final EasyRandom easyRandom = new EasyRandom();

    @Test
    void mapTrainingLevelDtoToEntity() {
        //given:
        TrainingLevel trainingLevel = easyRandom.nextObject(TrainingLevel.class);
        //when:
        TrainingLevelDto actual = trainingLevelMapper.mapEntityToTrainingLevelDto(trainingLevel);
        //then:
        TrainingLevelDto expected = new TrainingLevelDto(
                trainingLevel.getId(),
                trainingLevel.getName(),
                trainingLevel.getDescription(),
                trainingLevel.getDate_create(),
                trainingLevel.getDate_modify(),
                trainingLevel.getDate_archive(),
                trainingLevel.getArchive()
        );
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void mapEntityToTrainingLevelDto() {
        //when:
        NewTrainingLevelDto newTrainingLevelDto = easyRandom.nextObject(NewTrainingLevelDto.class);
        //then:
        TrainingLevel actual = trainingLevelMapper.mapTrainingLevelDtoToEntity(newTrainingLevelDto);
        assertTrainingLevelEntityIsCorrect(newTrainingLevelDto, actual);
    }

    private void assertTrainingLevelEntityIsCorrect(NewTrainingLevelDto newTrainingLevelDto, TrainingLevel actual) {
        assertThat(newTrainingLevelDto.name()).isEqualTo(actual.getName());
        assertThat(newTrainingLevelDto.description()).isEqualTo(actual.getDescription());
    }

}