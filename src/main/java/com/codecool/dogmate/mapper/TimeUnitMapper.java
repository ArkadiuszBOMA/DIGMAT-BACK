package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.timeunit.NewTimeUnitDto;
import com.codecool.dogmate.dto.timeunit.TimeUnitDto;
import com.codecool.dogmate.entity.TimeUnit;
import org.springframework.stereotype.Component;

@Component
public class TimeUnitMapper {

    public TimeUnit mapTimeUnitDtoToEntity(NewTimeUnitDto dto) {return new TimeUnit(
            dto.name()
    );}

    public TimeUnitDto mapEntityToTimeUnitDto(TimeUnit entity) {
        return new TimeUnitDto(
                entity.getId(),
                entity.getName()
        );
    }
}
