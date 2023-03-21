package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.city.CityDto;
import com.codecool.dogmate.dto.city.NewCityDto;
import com.codecool.dogmate.entity.City;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {

    public City mapNewCityDtoToEntity(NewCityDto dto) {

        return new City(dto.name());
    }

    public CityDto mapEntityToCityDto(City entity) {
        return new CityDto(
                entity.getId(),
                entity.getName(),
                entity.getDate_create(),
                entity.getDate_modify(),
                entity.getDate_archive(),
                entity.getArchive(),
                entity.getProvince().getId()
        );
    }

}
