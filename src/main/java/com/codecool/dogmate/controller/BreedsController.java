package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.breed.BreedDto;
import com.codecool.dogmate.dto.breed.NewBreedDto;
import com.codecool.dogmate.service.BreedsService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breeds")
public class BreedsController {
    private final BreedsService breedsService;

    public BreedsController(BreedsService breedsService) {
        this.breedsService = breedsService;
    }


    @GetMapping
    public List<BreedDto> getAllBreeds() {return breedsService.getBreeds();}
    @GetMapping(params = {"page", "size", "sort"})
    public List<BreedDto> getAllBreedsWithPageable(Pageable pageable) {
        return breedsService.getBreeds(pageable);
    }

    @GetMapping("/{id}")
    public BreedDto getBreedByBreedId(@PathVariable Integer id) {
        return breedsService.getBreedById(id);
    }

    @PostMapping
    public BreedDto newBreed(@RequestBody NewBreedDto breed) {
        return breedsService.createBreed(breed);
    }
}