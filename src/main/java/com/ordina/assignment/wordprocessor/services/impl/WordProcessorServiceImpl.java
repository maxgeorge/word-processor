package com.ordina.assignment.wordprocessor.services.impl;

import com.ordina.assignment.wordprocessor.exception.WordProcessorException;
import com.ordina.assignment.wordprocessor.model.WordFrequency;
import com.ordina.assignment.wordprocessor.services.WordProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WordProcessorServiceImpl implements WordProcessorService {


    /**
     * This method will return the highest frequency in the text.
     */
    @Override
    public int calculateHighestFrequency(String inputText) {
        log.debug("Input received {}" + inputText);
        Map<String, Integer> wordCount = getWordMap(inputText);
        List<Integer> ss = new ArrayList(wordCount.values());
        Collections.sort(ss, Collections.reverseOrder());
        log.debug("Highest Frequency {}" + ss.get(0));
        return ss.get(0);
    }

    /**
     * This method will return the frequency of the given word.
     */
    @Override
    public int calculateFrequencyForWord(String inputText, String word) {
        log.debug("Inputs received, text {} word {}" + inputText, word);
        word = word.toLowerCase();
        Map<String, Integer> wordCount = getWordMap(inputText);
        if (wordCount.get(word) == null) {
            throw new WordProcessorException("The input word is not available in the Text. Please enter a valid word.");
        }
        log.debug("Frequency of word {} is {}" + word, wordCount.get(word));
        return wordCount.get(word);
    }

    /**
     * This method will return a list of most frequent n words in the input text. All the words will be in lower case
     * and if more words have same frequency then it will be ascending alphabetical order.
     */
    @Override
    public List<WordFrequency> calculateMostFrequentNWords(String inputText, int n) {
        log.debug("Inputs received, text {} N {}" + inputText, n);
        if (n <= 0) {
            throw new WordProcessorException("The number of N is Invalid it 0 or less than 0. Please send a positive number.");
        }
        Map<String, Integer> wordCount = getWordMap(inputText);

        ArrayList<WordFrequency> wordCountList = wordCount.entrySet()
                .stream()
                .map(m -> new WordFrequency(m.getKey(), m.getValue()))
                .collect(Collectors.toCollection(ArrayList::new));

        List<WordFrequency> sortedUsers = wordCountList.stream()
                .sorted(Comparator.comparing(WordFrequency::getFrequency).reversed()
                        .thenComparing(WordFrequency::getWord))
                .collect(Collectors.toList());

        List<WordFrequency> result = new ArrayList<>();
        if (sortedUsers.size() >= n) {
            for (int i = 0; i < n; i++) {
                result.add(sortedUsers.get(i));
            }
        } else {
            throw new WordProcessorException("The number of N words is more than the frequent words count.");
        }

        log.debug("MostFrequent {} Words are {} ", n, result.stream().map(WordFrequency::toString).collect(Collectors.joining("\n")));

        return result;
    }


    private Map<String, Integer> getWordMap(final String input) {

        String[] words = input.toLowerCase().split("[^a-zA-Z]");
        List<String> splittedWords = Arrays.asList(words);
        log.debug("Splitted Words are {} ", splittedWords.stream().map(String::toString).collect(Collectors.joining("\n")));

        Map<String, Integer> wordCount = new HashMap<String, Integer>();

        splittedWords.forEach(word -> {
            if (wordCount.containsKey(word)) {
                wordCount.put(word, wordCount.get(word) + 1);
            } else {
                wordCount.put(word, 1);
            }
        });
        return wordCount;
    }

}
