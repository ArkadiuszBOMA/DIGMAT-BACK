package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.breed.BreedDto;
import com.codecool.dogmate.entity.Breed;
import org.jeasy.random.EasyRandom;

import static org.assertj.core.api.Assertions.assertThat;

class AnimalMapperTest {

    private BreedMapper breedMapper = new BreedMapper();
    private EasyRandom easyRandom = new EasyRandom();


    void shouldMapNewBreedDtoToEntity() {
        // given:
        Breed breed = easyRandom.nextObject(Breed.class);

        // when:
        BreedDto actual = breedMapper.mapEntityToBreedDto(breed);

        // then

        BreedDto expected = new BreedDto(
                breed.getId(),
                breed.getName(),
                breed.getAnimalTypes().getName(),
                breed.getDate_create(),
                breed.getDate_modify(),
                breed.getDate_archive(),
                breed.getArchive()
        );

        assertThat(actual).isEqualTo(expected);
    }

}