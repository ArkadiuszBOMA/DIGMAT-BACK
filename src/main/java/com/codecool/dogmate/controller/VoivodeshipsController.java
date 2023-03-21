package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.voivodeship.NewVoivodeshipDto;
import com.codecool.dogmate.dto.voivodeship.VoivodeshipDto;
import com.codecool.dogmate.service.VoivodeshipsService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voivodeships")
public class VoivodeshipsController {

    private final VoivodeshipsService voivodeshipsService;

    public VoivodeshipsController(VoivodeshipsService voivodeshipsService) {
        this.voivodeshipsService = voivodeshipsService;
    }

    @GetMapping
    public List<VoivodeshipDto> getAllVoivodeship() {
        return voivodeshipsService.getVoivodeships();
    }


    @GetMapping(params = {"page", "size", "sort"})
    public List<VoivodeshipDto> getAllVoivodeshipWithPageable(Pageable pageable) {
        return voivodeshipsService.getVoivodeships(pageable);
    }

    @GetMapping("/{id}")
    public VoivodeshipDto getVoivodeshipById(@PathVariable Integer id) {
        return voivodeshipsService.getVoivodeshipByVoivodeshipId(id);
    }

    @PostMapping
    public VoivodeshipDto newVoivodeship(@RequestBody NewVoivodeshipDto voivodeship) {
        return voivodeshipsService.createVoivodeship(voivodeship);
    }

}
