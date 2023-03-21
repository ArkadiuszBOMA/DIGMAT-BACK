package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.breed.BreedDto;
import com.codecool.dogmate.dto.breed.NewBreedDto;
import com.codecool.dogmate.entity.Breed;
import org.springframework.stereotype.Component;

@Component
public class BreedMapper {

    public Breed mapNewBreedDtoToEntity(NewBreedDto dto) {
        return new Breed(dto.name());
    }

    public BreedDto mapEntityToBreedDto(Breed entity) {
        return new BreedDto(
                entity.getId(),
                entity.getName(),
                entity.getAnimalTypes().getId(),
                entity.getDate_create(),
                entity.getDate_archive(),
                entity.getDate_modify(),
                entity.getArchive()
        );
    }
}
