package com.ordina.assignment.wordprocessor.controller;

import com.ordina.assignment.wordprocessor.model.WordFrequency;
import com.ordina.assignment.wordprocessor.services.impl.WordProcessorServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WordProcessorControllerTest {

    @InjectMocks
    private WordProcessorController wordProcessorController;
    @Mock
    private WordProcessorServiceImpl wordFrequencyAnalyzer;

    private static final String INPUT_TEXT = "Test";

    @Test
    public void getHighestFrequency() throws Exception {
        when(wordFrequencyAnalyzer.calculateHighestFrequency(any(String.class))).thenReturn(3);
        ResponseEntity<String> result = wordProcessorController.getHighestFrequency(INPUT_TEXT);
        verify(wordFrequencyAnalyzer, times(1)).calculateHighestFrequency("Test");
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("3", result.getBody());
    }

    @Test
    public void getFrequencyForWord() throws Exception {
        when(wordFrequencyAnalyzer.calculateFrequencyForWord(any(String.class), any(String.class))).thenReturn(2);
        ResponseEntity<String> result = wordProcessorController.getFrequencyForWord(INPUT_TEXT, "test");
        verify(wordFrequencyAnalyzer, times(1)).calculateFrequencyForWord("Test", "test");
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("2", result.getBody());
    }

    @Test
    public void getMostFrequentNWord() throws Exception {
        List<WordFrequency> wordFrequencies = new ArrayList();
        final WordFrequency wordFrequency1 = new WordFrequency("test", 3);
        final WordFrequency wordFrequency2 = new WordFrequency("tested", 2);
        wordFrequencies.add(wordFrequency1);
        wordFrequencies.add(wordFrequency2);
        when(wordFrequencyAnalyzer.calculateMostFrequentNWords(any(String.class), any(Integer.class))).thenReturn(wordFrequencies);
        ResponseEntity<List> result = wordProcessorController.getMostFrequentNWord("Test", 2);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(2, result.getBody().size());
        WordFrequency wordFrequency = (WordFrequency) result.getBody().get(0);
        assertEquals("test", wordFrequency.getWord());
        assertEquals(new Integer(3), wordFrequency.getFrequency());
        wordFrequency = (WordFrequency) result.getBody().get(1);
        assertEquals("tested", wordFrequency.getWord());
        assertEquals(new Integer(2), wordFrequency.getFrequency());
    }

}
