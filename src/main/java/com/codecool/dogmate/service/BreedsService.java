package com.codecool.dogmate.service;

import com.codecool.dogmate.advice.Exceptions.AnimalTypeNotFoundException;
import com.codecool.dogmate.advice.Exceptions.BreadNotFoundException;
import com.codecool.dogmate.advice.Exceptions.VoivodeshipNotFoundException;
import com.codecool.dogmate.dto.breed.BreedDto;
import com.codecool.dogmate.dto.breed.NewBreedDto;
import com.codecool.dogmate.dto.breed.UpdateBreedDto;
import com.codecool.dogmate.dto.voivodeship.UpdateVoivodeshipDto;
import com.codecool.dogmate.entity.AnimalType;
import com.codecool.dogmate.entity.AppUser;
import com.codecool.dogmate.entity.Breed;
import com.codecool.dogmate.entity.Voivodeship;
import com.codecool.dogmate.mapper.AnimalTypeMapper;
import com.codecool.dogmate.mapper.BreedMapper;
import com.codecool.dogmate.repository.AnimalTypeRepository;
import com.codecool.dogmate.repository.BreedRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
@Slf4j
@Service
public class BreedsService {
    private final BreedRepository breedRepository;
    private final BreedMapper breedMapper;
    private final AnimalTypeRepository animalTypeRepository;
    private final AnimalTypeMapper animalTypeMapper;

    public BreedsService(BreedRepository breedRepository, BreedMapper breedMapper, AnimalTypeRepository animalTypeRepository, AnimalTypeMapper animalTypeMapper) {
        this.breedRepository = breedRepository;
        this.breedMapper = breedMapper;
        this.animalTypeRepository = animalTypeRepository;
        this.animalTypeMapper = animalTypeMapper;
    }


    public List<BreedDto> getBreeds() {
        return breedRepository.findAllBy().stream()
                .sorted(Comparator.comparing(Breed::getName))
                .map(breedMapper::mapEntityToBreedDto)
                .toList();
    }
    public List<BreedDto> getBreeds(Pageable pageable) {
        return breedRepository.findAllBy(pageable).stream()
                .sorted(Comparator.comparing(Breed::getName))
                .map(breedMapper::mapEntityToBreedDto)
                .toList();
    }

    public BreedDto getBreedById(Integer id) {
        return breedRepository.findOneById(id)
                .map(breedMapper::mapEntityToBreedDto)
                .orElseThrow(() -> new BreadNotFoundException(id));
    }

    public BreedDto createBreed(NewBreedDto breed) {
        Breed entity = breedMapper.mapNewBreedDtoToEntity(breed);
        AnimalType animaltype = animalTypeRepository.findOneById(breed.animalType())
                .orElseThrow(() -> new AnimalTypeNotFoundException(breed.animalType()));
        entity.setAnimalTypes(animaltype);
        animaltype.getBreeds().add(entity);
        Breed savedEntity = breedRepository.save(entity);
        return breedMapper.mapEntityToBreedDto(savedEntity);
    }

    public void updateBreedData(UpdateBreedDto breed) {
        log.info("Zaktualizowałem dane dla id {}", breed.id());
        AnimalType animaltype = animalTypeRepository.findOneByName(breed.animalType())
                .orElseThrow(() -> new AnimalTypeNotFoundException(breed.animalType()));
        Breed updatedBread = breedRepository.findById(breed.id())
                .orElseThrow(() -> new VoivodeshipNotFoundException(breed.id()));
        updatedBread.setName(breed.name().trim().toUpperCase().replaceAll("( )+", " "));
        updatedBread.setAnimalTypes(animaltype);
        updatedBread.setDate_modify(LocalDateTime.now());
        breedRepository.save(updatedBread);
    }

    public void archiveBreedData(Integer id) {
        Breed archivedBreed = breedRepository.findById(id)
                .orElseThrow(() -> new VoivodeshipNotFoundException(id));
        if(!archivedBreed.getArchive()) {
            archivedBreed.setDate_archive(LocalDateTime.now());
            archivedBreed.setArchive(true);
            log.info("Zarchiwizowane dane dla id {}", id);
        } else {
            log.info("Dane już były archiwizowane;");
        }
        breedRepository.save(archivedBreed);
    }
}