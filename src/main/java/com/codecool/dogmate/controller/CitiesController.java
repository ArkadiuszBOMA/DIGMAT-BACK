package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.city.CityDto;
import com.codecool.dogmate.dto.city.NewCityDto;
import com.codecool.dogmate.dto.city.UpdateCityDto;
import com.codecool.dogmate.service.CitiesService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1/cities")
public class CitiesController {
    private final CitiesService citiesService;
    public CitiesController(CitiesService citiesService) {
        this.citiesService = citiesService;
    }
    @GetMapping()
    public List<CityDto> getAllCities() {return citiesService.getCities();}
    @GetMapping(params = {"page", "size", "sort"})
    public List<CityDto> getAllCitiesWithPageable(Pageable pageable) {
        return citiesService.getCities(pageable);
    }

    @GetMapping("/{id}")
    public CityDto getCityByCityId(@PathVariable Integer id) {
        return citiesService.getCityById(id);
    }

    @PostMapping("/add")
    public CityDto newCity(@RequestBody @Valid NewCityDto city) {
        return citiesService.createCity(city);
    }
    @PutMapping("/update/{id}")
    public void updateCity(@RequestBody @Valid UpdateCityDto city) {
        citiesService.updateCity(city);
    }

    @PutMapping("/archive/{id}")
    public void archiveCity(@PathVariable Integer id) {
        citiesService.archiveCity(id);
    }
}
