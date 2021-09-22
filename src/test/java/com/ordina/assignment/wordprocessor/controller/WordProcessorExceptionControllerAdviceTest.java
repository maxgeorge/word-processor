package com.ordina.assignment.wordprocessor.controller;

import com.ordina.assignment.wordprocessor.exception.WordProcessorException;
import com.ordina.assignment.wordprocessor.services.impl.WordProcessorServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class WordProcessorExceptionControllerAdviceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WordProcessorServiceImpl wordFrequencyAnalyzer;

    @Test
    public void handleGenericException() throws Exception {
        when(wordFrequencyAnalyzer.calculateHighestFrequency(any(String.class))).thenThrow(new RuntimeException("Generic exception"));
        final MvcResult mvcResult = this.mockMvc.perform(get("/getHighestFrequency/test")).andExpect(status().isInternalServerError()).andDo(print()).andReturn();
        assertEquals("Technical Error. Please contact Service Desk.", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void handleWordProcessorException() throws Exception {
        when(wordFrequencyAnalyzer.calculateHighestFrequency(any(String.class))).thenThrow(new WordProcessorException("WordProcessor Exception"));
        final MvcResult mvcResult = this.mockMvc.perform(get("/getHighestFrequency/test")).andExpect(status().isInternalServerError()).andDo(print()).andReturn();
        assertEquals("WordProcessor Exception", mvcResult.getResponse().getContentAsString());
    }


}
