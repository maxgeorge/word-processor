package com.ordina.assignment.wordprocessor.controller;

import com.ordina.assignment.wordprocessor.services.impl.WordProcessorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class WordProcessorController {
    @Autowired
    private WordProcessorServiceImpl wordFrequencyAnalyzer;

    @RequestMapping(value = "/getHighestFrequency/{text}", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getHighestFrequency(@PathVariable("text") String text) {
        Integer count = wordFrequencyAnalyzer.calculateHighestFrequency(text);
        return ResponseEntity.ok(count.toString());
    }

    @RequestMapping(value = "/getFrequencyForWord/{text}/{word}", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getFrequencyForWord(@PathVariable("text") String text, @PathVariable("word") String word) {
        Integer count = wordFrequencyAnalyzer.calculateFrequencyForWord(text, word);
        return ResponseEntity.ok(count.toString());
    }

    @RequestMapping(value = "/getMostFrequentNWord/{text}/{number}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List> getMostFrequentNWord(@PathVariable("text") String text, @PathVariable("number") int number) {
        List results = wordFrequencyAnalyzer.calculateMostFrequentNWords(text, number);
        return ResponseEntity.ok(results);
    }

}
