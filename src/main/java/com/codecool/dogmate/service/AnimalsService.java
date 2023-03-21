package com.codecool.dogmate.service;

import com.codecool.dogmate.dto.animal.AnimalDto;
import com.codecool.dogmate.dto.animal.NewAnimalDto;
import com.codecool.dogmate.entity.Animal;
import com.codecool.dogmate.entity.AnimalType;
import com.codecool.dogmate.entity.AppUser;
import com.codecool.dogmate.entity.Breed;
import com.codecool.dogmate.mapper.AnimalMapper;
import com.codecool.dogmate.repository.AnimalRepository;
import com.codecool.dogmate.repository.AnimalTypeRepository;
import com.codecool.dogmate.repository.BreedRepository;
import com.codecool.dogmate.repository.AppUserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
        return animalRepository.findAllBy().stream()
                .map(animalMapper::mapEntityToAnimalDto)
                .toList();
    }

    public List<AnimalDto> getAnimals() {
        return animalRepository.findAllBy().stream()
                .map(animalMapper::mapEntityToAnimalDto)
                .toList();
    }

    public AnimalDto getAnimalById(Integer id) {
        return animalRepository.findOneById(id)
                .map(animalMapper::mapEntityToAnimalDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public AnimalDto createAnimal(NewAnimalDto animal) {
        AnimalType animalType = animalTypeRepository.findOneById(animal.animalTypesId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Breed breed = breedRepository.findOneById(animal.breedId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        AppUser appUser = appUserRepository.findById(animal.userId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Animal entity = animalMapper.mapNewAniamlDtoToEntity(
                animal,animalType,breed, appUser
                );
        Animal savedEntity = animalRepository.save(entity);
        return animalMapper.mapEntityToAnimalDto(savedEntity);
    }
}
