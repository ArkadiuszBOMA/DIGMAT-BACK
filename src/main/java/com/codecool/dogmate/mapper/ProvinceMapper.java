package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.province.NewProvinceDto;
import com.codecool.dogmate.dto.province.ProvinceDto;
import com.codecool.dogmate.entity.Province;
import org.springframework.stereotype.Component;

@Component
public class ProvinceMapper {

    private final CityMapper cityMapper;
    public ProvinceMapper(CityMapper cityMapper) {
        this.cityMapper = cityMapper; }

    public Province mapNewProvinceDtoToEntity(NewProvinceDto dto) {
        return new Province(dto.name(), dto.terytId());
    }

    public ProvinceDto mapEntityToProvinceDto(Province entity) {
        return new ProvinceDto(
                entity.getId(),
                entity.getName(),
                entity.getTerytId(),
                entity.getDate_create(),
                entity.getDate_modify(),
                entity.getDate_archive(),
                entity.getArchive(),
                entity.getCities().stream()
                        .map(cityMapper::mapEntityToCityDto)
                        .toList()
        );
    }

}
