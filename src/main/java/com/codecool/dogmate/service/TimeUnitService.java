package com.codecool.dogmate.service;

import com.codecool.dogmate.dto.timeunit.NewTimeUnitDto;
import com.codecool.dogmate.dto.timeunit.TimeUnitDto;
import com.codecool.dogmate.entity.TimeUnit;
import com.codecool.dogmate.mapper.TimeUnitMapper;
import com.codecool.dogmate.repository.TimeUnitRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TimeUnitService {

    private final TimeUnitRepository timeUnitRepository;
    private final TimeUnitMapper timeUnitMapper;

    public TimeUnitService(TimeUnitRepository timeUnitRepository, TimeUnitMapper timeUnitMapper) {
        this.timeUnitRepository = timeUnitRepository;
        this.timeUnitMapper = timeUnitMapper;
    }


    public List<TimeUnitDto> getTimeUnits() {
        return timeUnitRepository.findAllBy().stream()
                .map(timeUnitMapper::mapEntityToTimeUnitDto)
                .toList();
    }

    public List<TimeUnitDto> getTimeUnits(Pageable pageable) {
        return timeUnitRepository.findAllBy(pageable).stream()
                .map(timeUnitMapper::mapEntityToTimeUnitDto)
                .toList();
    }

    public TimeUnitDto getTimeUnitById(Integer id) {
        return timeUnitRepository.findOneById(id)
                .map(timeUnitMapper::mapEntityToTimeUnitDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public TimeUnitDto createTimeUnit(NewTimeUnitDto timeunit) {
        TimeUnit entity = timeUnitMapper.mapTimeUnitDtoToEntity(timeunit);
        TimeUnit savedEntity = timeUnitRepository.save(entity);
        return timeUnitMapper.mapEntityToTimeUnitDto(savedEntity);
    }
}
