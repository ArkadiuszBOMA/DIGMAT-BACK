package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.animal.AnimalDto;
import com.codecool.dogmate.dto.animal.NewAnimalDto;
import com.codecool.dogmate.entity.Animal;
import com.codecool.dogmate.entity.AnimalType;
import com.codecool.dogmate.entity.AppUser;
import com.codecool.dogmate.entity.Breed;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnimalMapperTest {

    private final AnimalMapper animalMapper = new AnimalMapper();
    private final EasyRandom easyRandom = new EasyRandom();

    @Test
    void mapNewAnimalDtoToEntity() {
        // given:
        Animal animal = easyRandom.nextObject(Animal.class);
        // when:
        AnimalDto actual = animalMapper.mapEntityToAnimalDto(animal);
        // then
        AnimalDto expected = new AnimalDto(
                animal.getId(),
                animal.getName(),
                animal.getAnimalType().getName(),
                animal.getBreed().getName(),
                animal.getAppUser().getLast_name(),
                animal.getBirthYear(),
                animal.getPictureLocation(),
                animal.getDescription(),
                animal.getGender(),
                animal.getDate_create(),
                animal.getDate_modify(),
                animal.getDate_archive(),
                animal.getArchive()
        );
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void mapEntityToAnimalDto() {

        NewAnimalDto dto = easyRandom.nextObject(NewAnimalDto.class);
        AnimalType animalType = easyRandom.nextObject(AnimalType.class);
        Breed breed = easyRandom.nextObject(Breed.class);
        AppUser appUser = easyRandom.nextObject(AppUser.class);

        Animal actual = animalMapper.mapNewAnimalDtoToEntity(dto, animalType, breed, appUser);

        assertAnimalEntityIsCorrect(dto, actual);

    }
    private void assertAnimalEntityIsCorrect(NewAnimalDto dto, Animal actual) {
        assertThat(actual.getName()).isEqualTo(dto.name());
    }
}