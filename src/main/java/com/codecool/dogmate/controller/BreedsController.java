package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.breed.BreedDto;
import com.codecool.dogmate.dto.breed.NewBreedDto;
import com.codecool.dogmate.dto.breed.UpdateBreedDto;
import com.codecool.dogmate.service.BreedsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1/breeds")
public class BreedsController {
    private final BreedsService breedsService;

    public BreedsController(BreedsService breedsService) {
        this.breedsService = breedsService;
    }

    @GetMapping()
    public List<BreedDto> getAllBreeds() {return breedsService.getBreeds();}
    @GetMapping("/{id}")
    public BreedDto getBreedByBreedId(@PathVariable Integer id) {
        return breedsService.getBreedById(id);
    }
    @PostMapping()
    public BreedDto newBreed(@RequestBody @Valid NewBreedDto breed) {
        return breedsService.createBreed(breed);
    }
    @PutMapping(params={"update"})
    public void updateBreed(@RequestBody @Valid UpdateBreedDto breedDto){breedsService.updateBreed(breedDto);}
    @PutMapping(value="/{id}", params={"archive"})
    public void archiveBreed(@PathVariable Integer id) {breedsService.archiveBreed(id);}
    @DeleteMapping("/{id}")
    public void deleteBreed(@PathVariable Integer id) {
        breedsService.deleteBreedData(id);
    }
}