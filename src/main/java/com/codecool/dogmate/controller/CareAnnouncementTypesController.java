package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.careannouncmenttype.CareAnnouncementTypeDto;
import com.codecool.dogmate.dto.careannouncmenttype.NewCareAnnouncementTypeDto;
import com.codecool.dogmate.dto.careannouncmenttype.UpdateCareAnnouncementTypeDto;
import com.codecool.dogmate.service.CareAnnouncementTypeService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1/care-announcement-types")
public class CareAnnouncementTypesController {

    private final CareAnnouncementTypeService careAnnouncementTypeService;

    public CareAnnouncementTypesController(CareAnnouncementTypeService careAnnouncementTypeService) {
        this.careAnnouncementTypeService = careAnnouncementTypeService;
    }


    @GetMapping()
    public List<CareAnnouncementTypeDto> getAllCareAnnouncementTypes() {return careAnnouncementTypeService.getCareAnnouncementTypes();}

    @GetMapping(params = {"page", "size", "sort"})
    public List<CareAnnouncementTypeDto> getAllCareAnnouncementTypeDtoWithPageable(Pageable pageable) {
        return careAnnouncementTypeService.getCareAnnouncementTypes(pageable);
    }

    @GetMapping("/{id}")
    public CareAnnouncementTypeDto getCareAnnouncementTypeByCareAnnouncementTypeId(@PathVariable Integer id) {
        return careAnnouncementTypeService.getCareAnnouncementTypeById(id);
    }

    @PostMapping()
    public CareAnnouncementTypeDto newCareAnnouncementType(@RequestBody @Valid NewCareAnnouncementTypeDto careAnnouncementTypeDto) {
        return careAnnouncementTypeService.createCareAnnouncementType(careAnnouncementTypeDto);
    }
    @PutMapping(params={"update"})
    public CareAnnouncementTypeDto updateCareAnnouncementType(@RequestBody @Valid UpdateCareAnnouncementTypeDto careAnnouncementTypeDto) {
        return careAnnouncementTypeService.updateCareAnnouncementType(careAnnouncementTypeDto);
    }
    @PutMapping(value="/{id}", params={"archive"})
    public void archiveCareAnnouncementType(@PathVariable Integer id) {
        careAnnouncementTypeService.archiveCareAnnouncementType(id);
    }
    @DeleteMapping("/{id}")
    public void deleteCareAnnouncementType(@PathVariable Integer id) {
        careAnnouncementTypeService.deleteCareAnnouncementTypeData(id);
    }
}

