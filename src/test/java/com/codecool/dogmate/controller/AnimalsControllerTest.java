package com.codecool.dogmate.controller;

import com.codecool.dogmate.auth.SpringSecurityConfig;
import com.codecool.dogmate.service.AnimalsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AnimalsController.class)
@Import(SpringSecurityConfig.class)
class AnimalsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    //i o tym też @MockBean
    @MockBean
    private AnimalsService animalsService;

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnEmptyJson() throws Exception {
        // given:
        Mockito.when(animalsService.getAnimals()).thenReturn(List.of());

        // when:
        var response = mockMvc.perform(get("/api/v1/animals"));

        // then:
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }


}