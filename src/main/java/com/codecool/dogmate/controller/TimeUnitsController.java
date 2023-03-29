package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.timeunit.NewTimeUnitDto;
import com.codecool.dogmate.dto.timeunit.TimeUnitDto;
import com.codecool.dogmate.service.TimeUnitService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1/time-units/")
public class TimeUnitsController {

    private final TimeUnitService timeUnitService;

    public TimeUnitsController(TimeUnitService timeUnitService) {
        this.timeUnitService = timeUnitService;
    }


    @GetMapping()
    public List<TimeUnitDto> getAllTimeUnits() {return timeUnitService.getTimeUnits();}

    @GetMapping(params = {"page", "size", "sort"})
    public List<TimeUnitDto> getAllTimeUnitDtoWithPageable(Pageable pageable) {
        return timeUnitService.getTimeUnits(pageable);
    }

    @GetMapping("{id}")
    public TimeUnitDto getTimeUnitByTrainingLevelId(@PathVariable Integer id) {
        return timeUnitService.getTimeUnitById(id);
    }

    @PostMapping
    public TimeUnitDto newTimeUnit(@RequestBody NewTimeUnitDto timeunit) {
        return timeUnitService.createTimeUnit(timeunit);
    }
}

