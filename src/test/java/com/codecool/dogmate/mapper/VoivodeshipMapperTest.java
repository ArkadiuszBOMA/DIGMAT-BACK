package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.voivodeship.NewVoivodeshipDto;
import com.codecool.dogmate.dto.voivodeship.VoivodeshipDto;
import com.codecool.dogmate.entity.Voivodeship;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VoivodeshipMapperTest {

    private final VoivodeshipMapper voivodeshipMapper = new VoivodeshipMapper();
    private final EasyRandom easyRandom = new EasyRandom();


    @Test
    void mapNewVoivodeshipDtoToEntity() {
        Voivodeship voivodeship = easyRandom.nextObject(Voivodeship.class);

        VoivodeshipDto actual = voivodeshipMapper.mapEntityToVoivodeshipDto(voivodeship);

        VoivodeshipDto expected = new VoivodeshipDto(
                voivodeship.getId(),
                voivodeship.getTerytId(),
                voivodeship.getName(),
                voivodeship.getDate_create(),
                voivodeship.getDate_modify(),
                voivodeship.getDate_archive(),
                voivodeship.getArchive()
        );
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void mapEntityToVoivodeshipDto() {
        NewVoivodeshipDto dto = easyRandom.nextObject(NewVoivodeshipDto.class);
        Voivodeship actual = voivodeshipMapper.mapNewVoivodeshipDtoToEntity(dto);

        assertVoivodeshipIsCorrect(dto, actual);
    }

    private void assertVoivodeshipIsCorrect(NewVoivodeshipDto dto, Voivodeship actual) {
        assertThat(actual.getName()).isEqualTo(dto.name());
        assertThat(actual.getTerytId()).isEqualTo(dto.terytId());

    }
}