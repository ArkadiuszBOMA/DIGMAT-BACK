package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.city.CityDto;
import com.codecool.dogmate.dto.province.NewProvinceDto;
import com.codecool.dogmate.dto.province.ProvinceDto;
import com.codecool.dogmate.dto.province.UpdateProvinceDto;
import com.codecool.dogmate.service.ProvincesService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1/provinces")
public class ProvincesController {
    private final ProvincesService provincesService;

    public ProvincesController(ProvincesService provincesService) {
        this.provincesService = provincesService;
    }

    @GetMapping()
    public List<ProvinceDto> getAllProvinces() {
        return provincesService.getProvinces();
    }

    @GetMapping(params = {"page", "size", "sort"})
    public List<ProvinceDto> getAllProvincesWithPageable(Pageable pageable) {
        return provincesService.getProvinces(pageable);
    }

    @GetMapping("/{id}")
    public ProvinceDto getProvinceByProvinceId(@PathVariable Integer id) {
        return provincesService.getProvinceById(id);
    }

    @GetMapping(value="/{id}", params={"cities"})
    public List<CityDto> getCitiesForThisProvinceById(@PathVariable Integer id) {
        return provincesService.getCitiesForThisProvinceById(id);
    }

    @PostMapping()
    public ProvinceDto newProvince(@RequestBody @Valid NewProvinceDto province) {
        return provincesService.createProvince(province);
    }
    @PutMapping(params={"update"})
    public ProvinceDto updateProvince(@RequestBody @Valid UpdateProvinceDto province) {
        return provincesService.updateProvince(province);
    }

    @PutMapping(value="/{id}", params={"archive"})
    public void archiveProvince(@PathVariable Integer id) {provincesService.archiveProvince(id);}

    @DeleteMapping("/{id}")
    public void deleteProvince(@PathVariable Integer id) {
        provincesService.deleteProvinceData(id);
    }
}
