package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.careannouncmenttype.CareAnnouncementTypeDto;
import com.codecool.dogmate.dto.careannouncmenttype.NewCareAnnouncementTypeDto;
import com.codecool.dogmate.entity.CareAnnouncementType;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CareAnnouncementTypeMapperTest {

    private final CareAnnouncementTypeMapper careAnnouncementTypeMapper = new CareAnnouncementTypeMapper();
    private final EasyRandom easyRandom = new EasyRandom();


    @Test
    void mapCareAnnouncementTypeDtoToEntity() {
        // given:
        CareAnnouncementType careAnnouncementType = easyRandom.nextObject(CareAnnouncementType.class);
        // when:
        CareAnnouncementTypeDto actual = careAnnouncementTypeMapper.mapEntityToCareAnnouncementTypeDto(careAnnouncementType);
        // then
        CareAnnouncementTypeDto expected = new CareAnnouncementTypeDto(
                actual.id(),
                actual.name()
        );
    }

    @Test
    void mapEntityToCareAnnouncementTypeDto() {
        //given
        NewCareAnnouncementTypeDto dto = easyRandom.nextObject(NewCareAnnouncementTypeDto.class);
        //when
        CareAnnouncementType actual = careAnnouncementTypeMapper.mapCareAnnouncementTypeDtoToEntity(dto);
        //then
        assertCareAnnouncementTypeIsCorrect(dto, actual);
    }
    private void assertCareAnnouncementTypeIsCorrect(NewCareAnnouncementTypeDto dto, CareAnnouncementType actual){
        assertThat(actual.getName()).isEqualTo(dto.name());
    };
}