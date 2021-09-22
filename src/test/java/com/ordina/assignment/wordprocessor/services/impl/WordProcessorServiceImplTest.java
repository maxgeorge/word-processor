package com.ordina.assignment.wordprocessor.services.impl;


import com.ordina.assignment.wordprocessor.exception.WordProcessorException;
import com.ordina.assignment.wordprocessor.model.WordFrequency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class WordProcessorServiceImplTest {

    @InjectMocks
    private WordProcessorServiceImpl wordFrequencyAnalyzer;

    @Test
    public void calculateHighestFrequency_space_separator() throws Exception {
        Integer highestFrequency = wordFrequencyAnalyzer.calculateHighestFrequency("Hello JAVA this is java tutorial And is good Java");
        assertEquals(new Integer(3), highestFrequency);
    }

    @Test
    public void calculateHighestFrequency_different_separator() throws Exception {
        Integer highestFrequency = wordFrequencyAnalyzer.calculateHighestFrequency("Hello JAVA,this is java,tutorial And is good;Java:");
        assertEquals(new Integer(3), highestFrequency);
    }

    @Test
    public void calculateHighestFrequency_different_separator1() throws Exception {
        Integer highestFrequency = wordFrequencyAnalyzer.calculateHighestFrequency("Hello_JAVA,this_is java,tutorial?And.is,,good;Java:");
        assertEquals(new Integer(3), highestFrequency);
    }

    @Test
    public void calculateFrequencyForWord() throws Exception {
        Integer frequency = wordFrequencyAnalyzer.calculateFrequencyForWord("Hello JAVA this is java tutorial And JAva is good Java", "Java");
        assertEquals(new Integer(4), frequency);
    }

    @Test(expected = WordProcessorException.class)
    public void calculateFrequencyForWord_invalid_word() throws Exception {
        try {
            Integer frequency = wordFrequencyAnalyzer.calculateFrequencyForWord("Hello JAVA this is java tutorial And JAva is good Java", "test");
        } catch (WordProcessorException e) {
            assertEquals("The input word is not available in the Text. Please enter a valid word.", e.getMessage());
            throw new WordProcessorException(e.getMessage());
        }
    }

    @Test
    public void calculateMostFrequentNWords() throws Exception {
        List<WordFrequency> frequencyList = wordFrequencyAnalyzer.calculateMostFrequentNWords("Hello JAVA this is java tutorial And is good Java", 3);
        assertEquals(3, frequencyList.size());
        assertEquals("java", frequencyList.get(0).getWord());
        assertEquals(3, frequencyList.get(0).getFrequency().intValue());
        assertEquals("is", frequencyList.get(1).getWord());
        assertEquals(2, frequencyList.get(1).getFrequency().intValue());
        assertEquals("and", frequencyList.get(2).getWord());
        assertEquals(1, frequencyList.get(2).getFrequency().intValue());
    }

    @Test
    public void calculateMostFrequentNWords_sort_ordering() throws Exception {
        List<WordFrequency> frequencyList = wordFrequencyAnalyzer.calculateMostFrequentNWords("Hello JAVA this is java tutorial And is good Java", 7);
        assertEquals(7, frequencyList.size());
        assertEquals("java", frequencyList.get(0).getWord());
        assertEquals(3, frequencyList.get(0).getFrequency().intValue());
        assertEquals("is", frequencyList.get(1).getWord());
        assertEquals(2, frequencyList.get(1).getFrequency().intValue());
        assertEquals("and", frequencyList.get(2).getWord());
        assertEquals(1, frequencyList.get(2).getFrequency().intValue());
        assertEquals("good", frequencyList.get(3).getWord());
        assertEquals(1, frequencyList.get(3).getFrequency().intValue());
        assertEquals("hello", frequencyList.get(4).getWord());
        assertEquals(1, frequencyList.get(4).getFrequency().intValue());
        assertEquals("this", frequencyList.get(5).getWord());
        assertEquals(1, frequencyList.get(5).getFrequency().intValue());
        assertEquals("tutorial", frequencyList.get(6).getWord());
        assertEquals(1, frequencyList.get(6).getFrequency().intValue());
    }

    @Test(expected = WordProcessorException.class)
    public void calculateMostFrequentNWords_exception_scenario() throws Exception {
        try {
            List<WordFrequency> frequencyList = wordFrequencyAnalyzer.calculateMostFrequentNWords("Hello JAVA this is java tutorial And is good Java", 8);
        } catch (WordProcessorException e) {
            assertEquals("The number of N words is more than the frequent words count.", e.getMessage());
            throw new WordProcessorException(e.getMessage());
        }
    }

    @Test(expected = WordProcessorException.class)
    public void calculateMostFrequentNWords_invalid_N_number() throws Exception {
        try {
            List<WordFrequency> frequencyList = wordFrequencyAnalyzer.calculateMostFrequentNWords("Hello JAVA this is java tutorial And is good Java", 0);
        } catch (WordProcessorException e) {
            assertEquals("The number of N is Invalid it 0 or less than 0. Please send a positive number.", e.getMessage());
            throw new WordProcessorException(e.getMessage());
        }
    }
}
