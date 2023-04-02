package com.codecool.dogmate.service;

import com.codecool.dogmate.advice.Exceptions.AnimalNotFoundException;
import com.codecool.dogmate.dto.animal.AnimalDto;
import com.codecool.dogmate.dto.animal.NewAnimalDto;
import com.codecool.dogmate.entity.*;
import com.codecool.dogmate.mapper.AnimalMapper;
import com.codecool.dogmate.repository.AnimalRepository;
import com.codecool.dogmate.repository.AnimalTypeRepository;
import com.codecool.dogmate.repository.BreedRepository;
import com.codecool.dogmate.repository.AppUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
public class AnimalsService {

    private final AnimalRepository animalRepository;
    private final AnimalTypeRepository animalTypeRepository;
    private final BreedRepository breedRepository;
    private final AppUserRepository appUserRepository;

    private final AnimalMapper animalMapper;

    public AnimalsService(AnimalRepository animalRepository, AnimalTypeRepository animalTypeRepository, BreedRepository breedRepository, AppUserRepository appUserRepository, AnimalMapper animalMapper) {
        this.animalRepository = animalRepository;
        this.animalTypeRepository = animalTypeRepository;
        this.breedRepository = breedRepository;
        this.appUserRepository = appUserRepository;
        this.animalMapper = animalMapper;
    }


    public List<AnimalDto> getAnimals(Pageable pageable) {
        log.info("Wyświetlona lista zwierzaków");
        return animalRepository.findAllBy().stream()
                .sorted(Comparator.comparing(Animal::getDate_create))
                .map(animalMapper::mapEntityToAnimalDto)
                .toList();
    }

    public List<AnimalDto> getAnimals() {
        return animalRepository.findAllBy().stream()
                .sorted(Comparator.comparing(Animal::getDate_create))
                .map(animalMapper::mapEntityToAnimalDto)
                .toList();
    }

    public AnimalDto getAnimalById(Integer id) {

        return animalRepository.findOneById(id)
                .map(animalMapper::mapEntityToAnimalDto)
                .orElseThrow(() -> new AnimalNotFoundException(id));
    }

    public AnimalDto createAnimal(NewAnimalDto animal) {
        AnimalType animalType = animalTypeRepository.findOneById(animal.animalTypesId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        Breed breed = breedRepository.findOneById(animal.breedId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        AppUser appUser = appUserRepository.findById(animal.userId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        Animal entity = animalMapper.mapNewAnimalDtoToEntity(
                animal,animalType,breed, appUser
                );
        Animal savedEntity = animalRepository.save(entity);
        return animalMapper.mapEntityToAnimalDto(savedEntity);
    }
}
