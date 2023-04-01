package com.codecool.dogmate.service;

import com.codecool.dogmate.advice.Exceptions.AnimalTypeNotFoundException;
import com.codecool.dogmate.dto.animaltype.AnimalTypeDto;
import com.codecool.dogmate.dto.animaltype.NewAnimalTypeDto;
import com.codecool.dogmate.entity.Animal;
import com.codecool.dogmate.entity.AnimalType;
import com.codecool.dogmate.mapper.AnimalTypeMapper;
import com.codecool.dogmate.repository.AnimalTypeRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
@Service
public class AnimalTypesService {

    private final AnimalTypeRepository animalTypeRepository;
    private final AnimalTypeMapper animalTypeMapper;

    public AnimalTypesService(AnimalTypeRepository animalTypeRepository, AnimalTypeMapper animalTypeMapper) {
        this.animalTypeRepository = animalTypeRepository;
        this.animalTypeMapper = animalTypeMapper;
    }


    public List<AnimalTypeDto> getAnimalType() {
        return animalTypeRepository.findAllBy().stream()
                .sorted(Comparator.comparing(AnimalType::getDate_create))
                .map(animalTypeMapper::mapEntityToAnimalTypeDto)
                .toList();
    }

    public List<AnimalTypeDto> getAnimalType(Pageable pageable) {
        return animalTypeRepository.findAllBy(pageable).stream()
                .sorted(Comparator.comparing(AnimalType::getDate_create))
                .map(animalTypeMapper::mapEntityToAnimalTypeDto)
                .toList();
    }

    public AnimalTypeDto getAnimalTypeById(Integer id) {
        return animalTypeRepository.findOneById(id)
                .map(animalTypeMapper::mapEntityToAnimalTypeDto)
                .orElseThrow(() -> new AnimalTypeNotFoundException(id));
    }

    public AnimalTypeDto createAnimalType(NewAnimalTypeDto animalType) {
        AnimalType entity = animalTypeMapper.mapNewAnimalTypeDtoToEntity(animalType);
        AnimalType savedEntity = animalTypeRepository.save(entity);
        return animalTypeMapper.mapEntityToAnimalTypeDto(savedEntity);
    }
}
