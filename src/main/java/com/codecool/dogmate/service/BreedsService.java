package com.codecool.dogmate.service;

import com.codecool.dogmate.dto.breed.BreedDto;
import com.codecool.dogmate.dto.breed.NewBreedDto;
import com.codecool.dogmate.entity.AnimalType;
import com.codecool.dogmate.entity.Breed;
import com.codecool.dogmate.mapper.AnimalTypeMapper;
import com.codecool.dogmate.mapper.BreedMapper;
import com.codecool.dogmate.repository.AnimalTypeRepository;
import com.codecool.dogmate.repository.BreedRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
                .map(breedMapper::mapEntityToBreedDto)
                .toList();
    }
    public List<BreedDto> getBreeds(Pageable pageable) {
        return breedRepository.findAllBy(pageable).stream()
                .map(breedMapper::mapEntityToBreedDto)
                .toList();
    }

    public BreedDto getBreedById(Integer id) {
        return breedRepository.findOneById(id)
                .map(breedMapper::mapEntityToBreedDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public BreedDto createBreed(NewBreedDto breed) {
        Breed entity = breedMapper.mapNewBreedDtoToEntity(breed);
        AnimalType animaltype = animalTypeRepository.findOneById(breed.animalType())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        entity.setAnimalTypes(animaltype);
        animaltype.getBreeds().add(entity);
        Breed savedEntity = breedRepository.save(entity);
        return breedMapper.mapEntityToBreedDto(savedEntity);
    }
}