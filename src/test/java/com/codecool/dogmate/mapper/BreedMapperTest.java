package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.breed.BreedDto;
import com.codecool.dogmate.dto.breed.NewBreedDto;
import com.codecool.dogmate.entity.Breed;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BreedMapperTest {

    private BreedMapper breedMapper = new BreedMapper();
    private EasyRandom easyRandom = new EasyRandom();

    @Test
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
                breed.getDate_archive(),
                breed.getDate_modify(),
                breed.getArchive()
        );

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldMapEntityToBreedDto() {
        // given:
        NewBreedDto dto = easyRandom.nextObject(NewBreedDto.class);

        // when:
        Breed actual = breedMapper.mapNewBreedDtoToEntity(dto);

        // then:

        assertBreedEntityIsCorrect(dto, actual);
    }

    private void assertBreedEntityIsCorrect(NewBreedDto dto, Breed actual) {
        assertThat(actual.getName()).isEqualTo(dto.name());
    }

}