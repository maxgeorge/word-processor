package com.ordina.assignment.wordprocessor.services;

import com.ordina.assignment.wordprocessor.model.WordFrequency;

import java.util.List;

public interface WordProcessorService {

    int calculateHighestFrequency(String text);

    int calculateFrequencyForWord(String text, String word);

    List<WordFrequency> calculateMostFrequentNWords(String text, int n);
}
