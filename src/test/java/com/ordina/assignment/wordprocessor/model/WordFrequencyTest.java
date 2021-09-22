package com.ordina.assignment.wordprocessor.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class WordFrequencyTest {

    @Test
    public void test_Getter_Setter() throws Exception {
        final WordFrequency wordFrequency = new WordFrequency("java", 2);
        assertEquals("java", wordFrequency.getWord());
        assertEquals(2, wordFrequency.getFrequency().intValue());
    }

    @Test
    public void test_Equals() throws Exception {
        final WordFrequency wordFrequency1 = new WordFrequency("java", 2);
        final WordFrequency wordFrequency2 = new WordFrequency("java", 2);
        assertEquals(true, wordFrequency1.equals(wordFrequency2));
    }

    @Test
    public void test_Equals_Different_Object() throws Exception {
        final WordFrequency wordFrequency1 = new WordFrequency("java", 2);
        final WordFrequency wordFrequency2 = new WordFrequency("java", 3);
        assertEquals(false, wordFrequency1.equals(wordFrequency2));
    }

    @Test
    public void test_HashCode() throws Exception {
        final WordFrequency wordFrequency = new WordFrequency("java", 2);
        assertNotNull(wordFrequency.hashCode());
    }

    @Test
    public void test_ToString() throws Exception {
        final WordFrequency wordFrequency = new WordFrequency("java", 2);
        assertTrue(wordFrequency.toString().contains("WordFrequency(word=java"));

    }


}
