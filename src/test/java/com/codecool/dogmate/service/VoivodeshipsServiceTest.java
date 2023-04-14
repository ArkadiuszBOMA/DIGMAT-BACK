package com.codecool.dogmate.service;

import com.codecool.dogmate.dto.voivodeship.VoivodeshipDto;
import com.codecool.dogmate.entity.Voivodeship;
import com.codecool.dogmate.mapper.VoivodeshipMapper;
import com.codecool.dogmate.repository.VoivodeshipRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

//pamiętaj dodaj poniższy parametr
@ExtendWith(MockitoExtension.class)
class VoivodeshipsServiceTest {
    @Mock
    private VoivodeshipRepository voivodeshipRepository;
    @Mock
    private VoivodeshipMapper voivodeshipMapper;

    @InjectMocks
    private VoivodeshipsService voivodeshipsService;

    @Test
    void getVoivodeships() {

        Voivodeship v1 = new Voivodeship("voj1", "1222");
        Voivodeship v2 = new Voivodeship("voj2", "1223");

        VoivodeshipDto v1DTO = new VoivodeshipDto(null, "1222", "voj1",
                null, null, null, null, null);
        VoivodeshipDto v2DTO = new VoivodeshipDto(null, "1223", "voj2",
                null, null, null, null, null);

        Mockito.when(voivodeshipRepository.findAllBy()).thenReturn(List.of(v1, v2));
        Mockito.when(voivodeshipMapper.mapEntityToVoivodeshipDto(v1)).thenReturn(v1DTO);
        Mockito.when(voivodeshipMapper.mapEntityToVoivodeshipDto(v2)).thenReturn(v2DTO);

        List<VoivodeshipDto> actual = voivodeshipsService.getVoivodeships();

        Assertions.assertThat(actual.size()).isEqualTo(2);
        Assertions.assertThat(actual).containsExactlyInAnyOrder(v1DTO, v2DTO);

    }
}