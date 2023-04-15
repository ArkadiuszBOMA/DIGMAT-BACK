package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.animaltype.AnimalTypeDto;
import com.codecool.dogmate.dto.animaltype.NewAnimalTypeDto;
import com.codecool.dogmate.dto.animaltype.UpdateAnimalTypeDto;
import com.codecool.dogmate.dto.breed.BreedDto;
import com.codecool.dogmate.service.AnimalTypesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1/animal-types")
public class AnimalTypesController {
    private final AnimalTypesService animalTypesService;

    public AnimalTypesController(AnimalTypesService animalTypesService) {
        this.animalTypesService = animalTypesService;
    }
    @GetMapping()
    public List<AnimalTypeDto> getAllAnimalTypes() {return animalTypesService.getAnimalType();}
    @GetMapping(params = {"page", "size", "sort"})
    public List<AnimalTypeDto> getAllAnimalTypesWithPageable(Pageable pageable) {return animalTypesService.getAnimalType(pageable);}
    @GetMapping("/{id}")
    public AnimalTypeDto getAnimalTypeByAnimalTypeId(@PathVariable Integer id) {return animalTypesService.getAnimalTypeById(id);}
    @GetMapping(value="/{id}", params={"breeds"})
    public List<BreedDto> getBreedForThisAnimalTypeById(@PathVariable Integer id) {
        return animalTypesService.getBreedForThisAnimalTypeById(id);
    }
    @PostMapping()
    public AnimalTypeDto newAnimalType(@RequestBody @Valid NewAnimalTypeDto animalType) {return animalTypesService.createAnimalType(animalType);}
    @PutMapping(params={"update"})
    public void updateAnimalType(@RequestBody @Valid UpdateAnimalTypeDto animalType) {animalTypesService.updateAnimalType(animalType);}
    @PutMapping(value="/{id}", params={"archive"})
    public void archiveAnimalType(@PathVariable Integer id) {
        animalTypesService.archiveAnimalType(id);
    }
    @DeleteMapping("/{id}")
    public void deleteAnimalType(@PathVariable Integer id) {
        animalTypesService.deleteAnimalTypeData(id);
    }
}
