package com.codecool.dogmate.service;

import com.codecool.dogmate.advice.Exceptions.AnimalNotFoundException;
import com.codecool.dogmate.advice.Exceptions.AnimalTypeNotFoundException;
import com.codecool.dogmate.advice.Exceptions.AppUserNotFoundException;
import com.codecool.dogmate.advice.Exceptions.BreadNotFoundException;
import com.codecool.dogmate.dto.animal.AnimalDto;
import com.codecool.dogmate.dto.animal.NewAnimalDto;
import com.codecool.dogmate.dto.animal.UpdateAnimalDto;
import com.codecool.dogmate.entity.*;
import com.codecool.dogmate.mapper.AnimalMapper;
import com.codecool.dogmate.mapper.LessonAnimalMapper;
import com.codecool.dogmate.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
public class AnimalsService {

    private final AnimalRepository animalRepository;
    private final AnimalTypeRepository animalTypeRepository;
    private final BreedRepository breedRepository;
    private final AppUserRepository appUserRepository;
    private final LessonAnimalRepository lessonAnimalRepository;

    private final AnimalMapper animalMapper;
    private final LessonAnimalMapper lessonAnimalMapper;

    public AnimalsService(AnimalRepository animalRepository, AnimalTypeRepository animalTypeRepository, BreedRepository breedRepository, AppUserRepository appUserRepository, LessonsAnimal lessonsAnimal, LessonAnimalRepository lessonAnimalRepository, AnimalMapper animalMapper, LessonAnimalMapper lessonAnimalMapper) {
        this.animalRepository = animalRepository;
        this.animalTypeRepository = animalTypeRepository;
        this.breedRepository = breedRepository;
        this.appUserRepository = appUserRepository;
        this.lessonAnimalRepository = lessonAnimalRepository;
        this.animalMapper = animalMapper;
        this.lessonAnimalMapper = lessonAnimalMapper;
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

    public AnimalDto getAnimalByName(String name) {
        return animalRepository.findOneByName(name)
                .map(animalMapper::mapEntityToAnimalDto)
                .orElseThrow(() -> new AnimalNotFoundException(name));
    }

    public AnimalDto createAnimal(NewAnimalDto animal) {
        AnimalType animalType = animalTypeRepository.findOneById(animal.animalTypesId())
                .orElseThrow(() -> new AnimalTypeNotFoundException(animal.animalTypesId()));
        Breed breed = breedRepository.findOneById(animal.breedId())
                .orElseThrow(() -> new BreadNotFoundException(animal.breedId()));
        AppUser appUser = appUserRepository.findById(animal.userId())
                .orElseThrow(() -> new AppUserNotFoundException(animal.userId()));
        Animal entity = animalMapper.mapNewAnimalDtoToEntity(
                animal,animalType,breed, appUser
                );
        Animal savedEntity = animalRepository.save(entity);
        return animalMapper.mapEntityToAnimalDto(savedEntity);
    }

    public void updateAnimal(UpdateAnimalDto animal) {
        AnimalType animalType = animalTypeRepository.findOneById(animal.animalTypesId())
                .orElseThrow(() -> new AnimalTypeNotFoundException(animal.animalTypesId()));
        Breed breed = breedRepository.findOneById(animal.breedId())
                .orElseThrow(() -> new BreadNotFoundException(animal.breedId()));
        AppUser appUser = appUserRepository.findById(animal.userId())
                .orElseThrow(() -> new AppUserNotFoundException(animal.userId()));

        log.info("Zaktualizowałem dane dla id {}", animal.id());
        Animal updatedAnimal = animalRepository.findById(animal.id())
                .orElseThrow(() -> new AnimalNotFoundException(animal.id()));
        updatedAnimal.setName(animal.name().trim().toUpperCase().replaceAll("( )+", " "));
        updatedAnimal.setAnimalType(animalType);
        updatedAnimal.setBreed(breed);
        updatedAnimal.setAppUser(appUser);
        updatedAnimal.setBirthYear(animal.birthYear());
        updatedAnimal.setPictureLocation(animal.pictureLocation());
        updatedAnimal.setDescription(animal.description());
        updatedAnimal.setGender(animal.gender());
        updatedAnimal.setDate_modify(LocalDateTime.now());
        animalRepository.save(updatedAnimal);
    }

    public void archiveAnimal(Integer id) {
        Animal archivedAnimal = animalRepository.findOneById(id)
                .orElseThrow(() -> new AnimalNotFoundException(id));

        if(!archivedAnimal.getArchive()) {
            archivedAnimal.setDate_archive(LocalDateTime.now());
            archivedAnimal.setArchive(true);
            log.info("Zarchiwizowane dane dla id {}", id);
        } else {
            log.info("Dane już były archiwizowane;");
        }
        animalRepository.save(archivedAnimal);
    }

    public void deleteAnimalData(Integer id) {
        Animal deletedAnimal = animalRepository.findOneById(id)
                .orElseThrow(() -> new AnimalNotFoundException(id));
        log.info("Usunąłeś zwierzaka o id {}", id);
        animalRepository.deleteById(deletedAnimal.getId());
    }

}
