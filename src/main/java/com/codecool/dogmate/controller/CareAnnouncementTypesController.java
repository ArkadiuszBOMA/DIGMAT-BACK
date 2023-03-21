package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.careannouncmenttype.CareAnnouncementTypeDto;
import com.codecool.dogmate.dto.careannouncmenttype.NewCareAnnouncementTypeDto;
import com.codecool.dogmate.service.CareAnnouncementTypeService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/careannouncementtype")
public class CareAnnouncementTypesController {

    private final CareAnnouncementTypeService careAnnouncementTypeService;

    public CareAnnouncementTypesController(CareAnnouncementTypeService careAnnouncementTypeService) {
        this.careAnnouncementTypeService = careAnnouncementTypeService;
    }


    @GetMapping
    public List<CareAnnouncementTypeDto> getAllCareAnnouncementTypes() {return careAnnouncementTypeService.getCareAnnouncementTypes();}

    @GetMapping(params = {"page", "size", "sort"})
    public List<CareAnnouncementTypeDto> getAllCareAnnouncementTypeDtoWithPageable(Pageable pageable) {
        return careAnnouncementTypeService.getCareAnnouncementTypes(pageable);
    }

    @GetMapping("/{id}")
    public CareAnnouncementTypeDto getCareAnnouncementTypeByCareAnnouncementTypeId(@PathVariable Integer id) {
        return careAnnouncementTypeService.getCareAnnouncementTypeById(id);
    }

    @PostMapping
    public CareAnnouncementTypeDto newCareAnnouncementType(@RequestBody NewCareAnnouncementTypeDto careannouncementtype) {
        return careAnnouncementTypeService.createCareAnnouncementType(careannouncementtype);
    }
}

