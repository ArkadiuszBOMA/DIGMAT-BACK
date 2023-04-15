package com.codecool.dogmate.controller;

import com.codecool.dogmate.advice.Exceptions.VoivodeshipNotFoundException;
import com.codecool.dogmate.dto.voivodeship.VoivodeshipDto;
import com.codecool.dogmate.service.VoivodeshipsService;
import org.hamcrest.Matchers;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = VoivodeshipsController.class)
class VoivodeshipsControllerTest {

    //i nie zapominaj również o tym @Autowired
    @Autowired
    private MockMvc mockMvc;

    //i o tym też @MockBean
    @MockBean
    private VoivodeshipsService voivodeshipsService;

    @Test
    void shouldReturnEmptyJson() throws Exception {
        // given:
        Mockito.when(voivodeshipsService.getVoivodeships()).thenReturn(List.of());

        // when:
        var response = mockMvc.perform(get("/api/v1/voivodeships"));

        // then:
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
    @Test
    void shouldReturnVoivodeshipsJson() throws Exception {
        // given:
        List<VoivodeshipDto> dtos = Instancio.ofList(VoivodeshipDto.class).size(1)
                .create();

        Mockito.when(voivodeshipsService.getVoivodeships()).thenReturn(dtos);

        // when:
        var response = mockMvc.perform(get("/api/v1/voivodeships"));

        // then:
        response
//                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(dtos.get(0).id().toString()))
                .andExpect(jsonPath("$[0].name").value(dtos.get(0).name()))
                .andExpect(jsonPath("$[0].terytId").value(dtos.get(0).terytId()));
    }

    @Test
    void shouldReturn404WhenVoivodeshipNotFound() throws Exception {
        // given:
        Integer id = new Random().nextInt();
        Mockito.when(voivodeshipsService.getVoivodeshipByVoivodeshipId(id)).thenThrow(new VoivodeshipNotFoundException(id));

        // when:
        ResultActions response = mockMvc.perform(get("/api/v1/voivodeships/" + id));

        //then:
        response.andExpect(status().isNotFound())
//                poniżej opcja z kontrolą całej treści wiadomości uwaga zmiana treści wymaga zmiany testu;
//                .andExpect(jsonPath("$.errorMessage").value("Developer with id " + id + " not found"));
                .andExpect(jsonPath("$.errorMessage", Matchers.containsString(id.toString())));
    }
}