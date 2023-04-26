package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.province.ProvinceDto;
import com.codecool.dogmate.dto.voivodeship.NewVoivodeshipDto;
import com.codecool.dogmate.dto.voivodeship.UpdateVoivodeshipDto;
import com.codecool.dogmate.dto.voivodeship.VoivodeshipDto;
import com.codecool.dogmate.service.VoivodeshipsService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1/voivodeships")
public class VoivodeshipsController {

    private final VoivodeshipsService voivodeshipsService;

    public VoivodeshipsController(VoivodeshipsService voivodeshipsService) {
        this.voivodeshipsService = voivodeshipsService;
    }

    @GetMapping()
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

    @GetMapping(value="/{id}", params={"province"})
    public List<ProvinceDto> getProvincesForThisVoivodeshipById(@PathVariable Integer id) {
        return voivodeshipsService.getProvincesForThisVoivodeshipById(id);
    }

    @PostMapping()
    public VoivodeshipDto newVoivodeship(@RequestBody @Valid NewVoivodeshipDto voivodeship) {
        return voivodeshipsService.createVoivodeship(voivodeship);
    }

    @PutMapping(params={"update"})
    public VoivodeshipDto updateVoivodeship(@RequestBody @Valid UpdateVoivodeshipDto voivodeship) {
        return voivodeshipsService.updateVoivodeshipData(voivodeship);
    }

    @PutMapping(value="/{id}", params={"archive"})
    public void archiveVoivodeship(@PathVariable Integer id) {
        voivodeshipsService.archiveVoivodeshipData(id);
    }

    @DeleteMapping("/{id}")
    public void deleteVoivodeship(@PathVariable Integer id) {
        voivodeshipsService.deleteVoivodeshipData(id);
    }
}
