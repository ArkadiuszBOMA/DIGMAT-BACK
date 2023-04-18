package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.timeunit.NewTimeUnitDto;
import com.codecool.dogmate.dto.timeunit.TimeUnitDto;
import com.codecool.dogmate.entity.TimeUnit;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TimeUnitMapperTest {

    private final TimeUnitMapper timeUnitMapper = new TimeUnitMapper();
    private final EasyRandom easyRandom = new EasyRandom();


    @Test
    void mapTimeUnitDtoToEntity() {
        // given:
        TimeUnit timeUnit = easyRandom.nextObject(TimeUnit.class);
        // when:
        TimeUnitDto actual = timeUnitMapper.mapEntityToTimeUnitDto(timeUnit);
        // then
        TimeUnitDto expected = new TimeUnitDto(
                timeUnit.getId(),
                timeUnit.getName(),
                timeUnit.getDate_create(),
                timeUnit.getDate_modify(),
                timeUnit.getDate_archive(),
                timeUnit.getArchive()
        );
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void mapEntityToTimeUnitDto() {
        // when:
        NewTimeUnitDto newTimeUnitDto = easyRandom.nextObject(NewTimeUnitDto.class);
        // then
        TimeUnit actual = timeUnitMapper.mapTimeUnitDtoToEntity(newTimeUnitDto);
        assertTimeUnitEntityIsCorrect(newTimeUnitDto, actual);
    }

    private void assertTimeUnitEntityIsCorrect(NewTimeUnitDto newTimeUnitDto, TimeUnit actual) {
        assertThat(newTimeUnitDto.name()).isEqualTo(actual.getName());
    }
}