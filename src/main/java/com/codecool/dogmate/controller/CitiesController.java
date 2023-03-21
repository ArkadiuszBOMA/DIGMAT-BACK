package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.city.CityDto;
import com.codecool.dogmate.dto.city.NewCityDto;
import com.codecool.dogmate.service.CitiesService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {
    private final CitiesService citiesService;
    public CitiesController(CitiesService citiesService) {
        this.citiesService = citiesService;
    }
    @GetMapping
    public List<CityDto> getAllCities() {return citiesService.getCities();}
    @GetMapping(params = {"page", "size", "sort"})
    public List<CityDto> getAllCitiesWithPageable(Pageable pageable) {
        return citiesService.getCities(pageable);
    }

    @GetMapping("/{id}")
    public CityDto getCityByCityId(@PathVariable Integer id) {
        return citiesService.getCityById(id);
    }

    @PostMapping
    public CityDto newCity(@RequestBody NewCityDto city) {
        return citiesService.createCity(city);
    }
}
