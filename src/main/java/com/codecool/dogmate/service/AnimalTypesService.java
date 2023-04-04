package com.codecool.dogmate.service;

import com.codecool.dogmate.advice.Exceptions.AnimalTypeNotFoundException;
import com.codecool.dogmate.dto.animaltype.AnimalTypeDto;
import com.codecool.dogmate.dto.animaltype.NewAnimalTypeDto;
import com.codecool.dogmate.dto.animaltype.UpdateAnimalTypeDto;
import com.codecool.dogmate.entity.AnimalType;
import com.codecool.dogmate.mapper.AnimalTypeMapper;
import com.codecool.dogmate.repository.AnimalTypeRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Slf4j
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
    public AnimalTypeDto getAnimalTypeByName(String nameAT) {
        return animalTypeRepository.findOneByName(nameAT)
                .map(animalTypeMapper::mapEntityToAnimalTypeDto)
                .orElseThrow(() -> new AnimalTypeNotFoundException(nameAT));
    }

    public AnimalTypeDto createAnimalType(NewAnimalTypeDto animalType) {
        AnimalType entity = animalTypeMapper.mapNewAnimalTypeDtoToEntity(animalType);
        AnimalType savedEntity = animalTypeRepository.save(entity);
        return animalTypeMapper.mapEntityToAnimalTypeDto(savedEntity);
    }

    public void updateAnimalTypeData(UpdateAnimalTypeDto animalType) {
        log.info("Zaktualizowałem dane dla id {}", animalType.id());
        AnimalType updatedAnimalType = animalTypeRepository.findById(animalType.id())
                .orElseThrow(() -> new AnimalTypeNotFoundException(animalType.id()));
        updatedAnimalType.setName(animalType.name());
        updatedAnimalType.setDescription(animalType.description());
        updatedAnimalType.setDate_modify(LocalDateTime.now());
        animalTypeRepository.save(updatedAnimalType);
    }

    public void archiveAnimalTypeData(Integer id) {
        log.info("Zarchiwizowane dane dla id {}", id);
        AnimalType archivedAnimalType = animalTypeRepository.findById(id)
                .orElseThrow(() -> new AnimalTypeNotFoundException(id));
        archivedAnimalType.setDate_archive(LocalDateTime.now());
        archivedAnimalType.setArchive(true);
        animalTypeRepository.save(archivedAnimalType);
    }
}
