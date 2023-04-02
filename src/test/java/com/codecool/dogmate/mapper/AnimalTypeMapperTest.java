package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.animaltype.AnimalTypeDto;
import com.codecool.dogmate.dto.animaltype.NewAnimalTypeDto;
import com.codecool.dogmate.dto.breed.BreedDto;
import com.codecool.dogmate.entity.AnimalType;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AnimalTypeMapperTest {

    private AnimalTypeMapper animalTypeMapper = new AnimalTypeMapper(new BreedMapper());
    private EasyRandom easyRandom = new EasyRandom();

    @Test
    void mapNewAnimalTypeDtoToEntity() {
        AnimalType animalType = easyRandom.nextObject(AnimalType.class);

        BreedDto breedDto1 = easyRandom.nextObject(BreedDto.class);
        BreedDto breedDto2 = easyRandom.nextObject(BreedDto.class);

        List<BreedDto> breedsDto = new ArrayList<>();

        breedsDto.add(breedDto1);
        breedsDto.add(breedDto2);

        // when:
        AnimalTypeDto actual = animalTypeMapper.mapEntityToAnimalTypeDto(animalType);

        // then

        AnimalTypeDto expected = new AnimalTypeDto(
                animalType.getId(),
                animalType.getName(),
                animalType.getDescription(),
                animalType.getDate_create(),
                animalType.getDate_modify(),
                animalType.getDate_archive(),
                animalType.getArchive(),
                breedsDto
        );

        assertThat(actual).isEqualTo(expected);

    }

    @Test
    void mapEntityToAnimalTypeDto() {

        NewAnimalTypeDto dto = easyRandom.nextObject(NewAnimalTypeDto.class);

        // when:
        AnimalType actual = animalTypeMapper.mapNewAnimalTypeDtoToEntity(dto);

        // then:
        assertEntityIsCorrect(dto, actual);
    }

    private void assertEntityIsCorrect(NewAnimalTypeDto dto, AnimalType actual) {
        assertThat(actual.getName()).isEqualTo(dto.name());
        assertThat(actual.getDescription()).isEqualTo(dto.description());

    }
}