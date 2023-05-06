package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.city.CityDto;
import com.codecool.dogmate.dto.city.NewCityDto;
import com.codecool.dogmate.entity.City;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class CityMapperTest {

    private final CityMapper cityMapper = new CityMapper();
    private final EasyRandom easyRandom = new EasyRandom();

    @Test
    void mapNewCityDtoToEntity (){

        City city = easyRandom.nextObject(City.class);

        CityDto actual = cityMapper.mapEntityToCityDto(city);

        CityDto expected = new CityDto(
                city.getId(),
                city.getName(),
                city.getDate_create(),
                city.getDate_modify(),
                city.getDate_archive(),
                city.getArchive(),
                city.getProvince().getName(),
                city.getProvince().getId(),
                city.getProvince().getVoivodeship().getName()
        );
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void mapEntityToCityDto (){

        NewCityDto dto = easyRandom.nextObject(NewCityDto.class);

        City actual = cityMapper.mapNewCityDtoToEntity(dto);

        assertCityEntityIsCorrect(dto, actual);

    }

    private void assertCityEntityIsCorrect(NewCityDto dto, City actual) {
        assertThat(actual.getName()).isEqualTo(dto.name());
    }

}