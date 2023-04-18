package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.province.ProvinceDto;
import com.codecool.dogmate.entity.Province;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProvinceMapperTest {

    private final ProvinceMapper provinceMapper = new ProvinceMapper();
    private final EasyRandom easyRandom = new EasyRandom();



    @Test
    void mapNewProvinceDtoToEntity() {
        Province province = easyRandom.nextObject(Province.class);

        ProvinceDto actual = provinceMapper.mapEntityToProvinceDto(province);

        ProvinceDto expected = new ProvinceDto(
                province.getId(),
                province.getName(),
                province.getTerytId(),
                province.getDate_create(),
                province.getDate_modify(),
                province.getDate_archive(),
                province.getArchive(),
                province.getVoivodeship().getName()
        );
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void mapEntityToProvinceDto() {
    }
}