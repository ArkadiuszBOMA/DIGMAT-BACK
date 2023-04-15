package com.codecool.dogmate.controller;

import com.codecool.dogmate.service.AnimalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = AnimalsController.class)
class AnimalsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    //i o tym też @MockBean
    @MockBean
    private AnimalsService animalsService;


}