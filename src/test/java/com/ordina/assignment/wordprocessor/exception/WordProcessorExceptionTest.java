package com.ordina.assignment.wordprocessor.exception;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class WordProcessorExceptionTest {

    @InjectMocks
    private WordProcessorException wordProcessorException;

    @Test
    public void wordProcessorException_with_message() {
        final WordProcessorException wordProcessorException = new WordProcessorException("test");
        assertEquals("test", wordProcessorException.getMessage());
    }

    @Test
    public void wordProcessorException_with_message_errorCode() {
        final WordProcessorException wordProcessorException = new WordProcessorException("WORD_001", "test exception");
        assertEquals("WORD_001", wordProcessorException.getErrorCode());
        assertEquals("test exception", wordProcessorException.getMessage());
    }
}