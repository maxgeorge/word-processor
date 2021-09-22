package com.ordina.assignment.wordprocessor.controller;

import com.ordina.assignment.wordprocessor.exception.WordProcessorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(assignableTypes = WordProcessorController.class)
@Slf4j
public class WordProcessorExceptionControllerAdvice {

    private static final String ERROR_CODE_GENERIC = "WORD_PRCS_001";

    private static final String ERROR_MSG = "Technical Error. Please contact Service Desk.";


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<String> handleGenericException(Exception ex) {
        log.error("Handling generic exception. message : {} exception  : {}" + ex.getMessage(), ex);
        return new ResponseEntity<String>(ERROR_MSG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(WordProcessorException.class)
    @ResponseBody
    public ResponseEntity<String> handleWordProcessorException(WordProcessorException ex) {
        log.error("Handling WordProcessor Exception. message : {} exception  : {}" + ex.getMessage(), ex);
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
