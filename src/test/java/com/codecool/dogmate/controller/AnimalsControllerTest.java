package com.codecool.dogmate.controller;

//to z naszego programu

import com.codecool.dogmate.dto.animal.AnimalDto;
import com.codecool.dogmate.service.AnimalsService;
import org.instancio.Instancio;
import org.instancio.Select;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//to ma być @WebMvcTest
@WebMvcTest(controllers = AnimalsController.class)
class AnimalsControllerTest {

    //i nie zapominaj również o tym @Autowired
    @Autowired
    private MockMvc mockMvc;

    //i o tym też @MockBean
    @MockBean
    private AnimalsService animalsService;

    void shouldReturnEmptyJson() throws Exception {
        // given:
        Mockito.when(animalsService.getAnimals()).thenReturn(List.of());

        // when:
        var response = mockMvc.perform(get("/api/v1/animals"));

        // then:
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    void shouldReturnAnimalsJson() throws Exception {
        // given:
        List<AnimalDto> dtos = Instancio.ofList(AnimalDto.class).size(1)
                .generate(Select.field(AnimalDto::lessonAnimal), gen -> gen.collection().size(1))
                .create();

        Mockito.when(animalsService.getAnimals()).thenReturn(dtos);

        // when:
        var response = mockMvc.perform(get("/api/v1/animals"));

        // then:
        response
//                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(dtos.get(0).id().toString()))
                .andExpect(jsonPath("$[0].name").value(dtos.get(0).name()))
                .andExpect(jsonPath("$[0].animalTypesId").value(dtos.get(0).animalTypesId()))
                .andExpect(jsonPath("$[0].breedId").value(dtos.get(0).breedId()));
    }
}