package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.appuser.AppUserDto;
import com.codecool.dogmate.dto.city.CityDto;
import com.codecool.dogmate.dto.city.NewCityDto;
import com.codecool.dogmate.dto.city.UpdateCityDto;
import com.codecool.dogmate.mapper.ProvinceMapper;
import com.codecool.dogmate.service.CitiesService;
import com.codecool.dogmate.service.ProvincesService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1/cities")
public class CitiesController {
    private final CitiesService citiesService;
    private final ProvincesService provincesService;
    public CitiesController(CitiesService citiesService, ProvincesService provincesService, ProvinceMapper provinceMapper) {
        this.citiesService = citiesService;
        this.provincesService = provincesService;
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
    @GetMapping(value="/{id}", params={"users"})
    public List<AppUserDto> getAppUserForThisCityId(@PathVariable Integer id) {
        return citiesService.getAppUserForThisCityId(id);
    }
    @PostMapping()
    public CityDto newCity(@RequestBody @Valid NewCityDto city) {
        return citiesService.createCity(city);
    }
    @PutMapping(params={"update"})
    public CityDto updateCity(@RequestBody @Valid UpdateCityDto city) {
        return citiesService.updateCity(city);
    }
    @PutMapping(value="/{id}", params={"archive"})
    public void archiveCity(@PathVariable Integer id) {
        citiesService.archiveCity(id);
    }
    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable Integer id) {
        citiesService.deleteCityData(id);
    }
}
