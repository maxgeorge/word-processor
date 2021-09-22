package com.ordina.assignment.wordprocessor.exception;

public class WordProcessorException extends RuntimeException {
    private String errorCode;

    public WordProcessorException(final String errorCode, final String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public WordProcessorException(final String message) {
        super(message);
    }

    public String getErrorCode() {
        return this.errorCode;
    }
}
