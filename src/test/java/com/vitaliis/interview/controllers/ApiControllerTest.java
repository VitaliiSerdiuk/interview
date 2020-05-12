package com.vitaliis.interview.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
class ApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldFindSimpleFile() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile(
                "file",
                getClass().getResourceAsStream("/simple.txt"));
        this.mvc.perform(multipart("/file").file(multipartFile))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("ok"));
    }

    @Test
    public void shouldReportInvalidHeader() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile(
                "file",
                getClass().getResourceAsStream("/invalidHeader.txt"));
        this.mvc.perform(multipart("/file").file(multipartFile))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("Invalid header"));
    }

}