package com.codecool.dogmate.dto.province;

import com.codecool.dogmate.dto.city.CityDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;

public record ProvinceDto(
        Integer id,
        String terytId,
        String name,
        LocalDateTime date_create,
        LocalDateTime date_modify,
        LocalDateTime date_archive,
        Boolean archive,
        List<CityDto> cities

){
}
