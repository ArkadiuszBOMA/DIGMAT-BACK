package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.animal.AnimalDto;
import com.codecool.dogmate.dto.animal.NewAnimalDto;
import com.codecool.dogmate.dto.animal.UpdateAnimalDto;
import com.codecool.dogmate.service.AnimalsService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1/animals")
public class AnimalsController {

    private final AnimalsService animalsService;

    public AnimalsController(AnimalsService animalsService) {
        this.animalsService = animalsService;
    }

    @GetMapping()
    public List<AnimalDto> getAllAnimals() {
        return animalsService.getAnimals();
    }

    @GetMapping(params = {"page", "size", "sort"})
    public List<AnimalDto> getAllAnimalsWithPageable(Pageable pageable) {
        return animalsService.getAnimals(pageable);
    }

    @GetMapping("/{id}")
    public AnimalDto getAnimalByAnimalId(@PathVariable Integer id) {
        return animalsService.getAnimalById(id);
    }

    @PostMapping("/add")
    public AnimalDto newAnimal(@RequestBody @Valid NewAnimalDto animal) {
        return animalsService.createAnimal(animal);
    }

    @PutMapping("/update/{id}")
    public void updateAnimal(@RequestBody @Valid UpdateAnimalDto animal) {
        animalsService.updateAnimal(animal);
    }

    @PutMapping("/archive/{id}")
    public void archiveAnimal(@PathVariable Integer id) {
        animalsService.archiveAnimal(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAnimal(@PathVariable Integer id) {
        animalsService.deleteAnimalData(id);
    }
}

