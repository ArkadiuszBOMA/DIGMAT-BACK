package com.codecool.dogmate.service;

import com.codecool.dogmate.advice.Exceptions.TimeUnitNotFoundException;
import com.codecool.dogmate.dto.timeunit.NewTimeUnitDto;
import com.codecool.dogmate.dto.timeunit.TimeUnitDto;
import com.codecool.dogmate.dto.timeunit.UpdateTimeUnitDto;
import com.codecool.dogmate.entity.TimeUnit;
import com.codecool.dogmate.mapper.TimeUnitMapper;
import com.codecool.dogmate.repository.TimeUnitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Slf4j
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
                .sorted(Comparator.comparing(TimeUnit::getName))
                .map(timeUnitMapper::mapEntityToTimeUnitDto)
                .toList();
    }

    public List<TimeUnitDto> getTimeUnits(Pageable pageable) {
        return timeUnitRepository.findAllBy(pageable).stream()
                .sorted(Comparator.comparing(TimeUnit::getName))
                .map(timeUnitMapper::mapEntityToTimeUnitDto)
                .toList();
    }

    public TimeUnitDto getTimeUnitById(Integer id) {
        return timeUnitRepository.findOneById(id)
                .map(timeUnitMapper::mapEntityToTimeUnitDto)
                .orElseThrow(() -> new TimeUnitNotFoundException(id));
    }

    public TimeUnitDto getTimeUnitByName(String name) {
        return timeUnitRepository.findOneByName(name)
                .map(timeUnitMapper::mapEntityToTimeUnitDto)
                .orElseThrow(() -> new TimeUnitNotFoundException(name));
    }

    public TimeUnitDto createTimeUnit(NewTimeUnitDto timeunit) {
        TimeUnit entity = timeUnitMapper.mapTimeUnitDtoToEntity(timeunit);
        TimeUnit savedEntity = timeUnitRepository.save(entity);
        return timeUnitMapper.mapEntityToTimeUnitDto(savedEntity);
    }

    public void updateTimeUnit(UpdateTimeUnitDto timeUnit) {
        log.info("Zaktualizowałem dane dla id {}", timeUnit.id());
        TimeUnit updateTimeUnit = timeUnitRepository.findOneById(timeUnit.id())
                .orElseThrow(() -> new TimeUnitNotFoundException(timeUnit.id()));
        updateTimeUnit.setName(timeUnit.name().trim().toUpperCase().replaceAll("( )+", " "));
        updateTimeUnit.setDate_modify(LocalDateTime.now());
        timeUnitRepository.save(updateTimeUnit);
    }

    public void archiveTimeUnit(Integer id) {
        TimeUnit archivedTimeUnit = timeUnitRepository.findOneById(id)
                .orElseThrow(() -> new TimeUnitNotFoundException(id));
        if(!archivedTimeUnit.getArchive()) {
            archivedTimeUnit.setDate_archive(LocalDateTime.now());
            archivedTimeUnit.setArchive(true);
            log.info("Zarchiwizowane dane dla id {}", id);
        } else {
            log.info("Dane już były archiwizowane;");
        }
        timeUnitRepository.save(archivedTimeUnit);
    }
}
