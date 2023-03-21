package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.province.NewProvinceDto;
import com.codecool.dogmate.dto.province.ProvinceDto;
import com.codecool.dogmate.service.ProvincesService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provinces")
public class ProvincesController {
    private final ProvincesService provincesService;

    public ProvincesController(ProvincesService provincesService) {
        this.provincesService = provincesService;
    }

    @GetMapping
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

    @PostMapping
    public ProvinceDto newProvince(@RequestBody NewProvinceDto province) {
        return provincesService.createProvince(province);
    }
}
