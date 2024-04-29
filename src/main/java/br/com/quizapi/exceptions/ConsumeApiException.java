package br.com.quizapi.exceptions;

public class ConsumeApiException extends RuntimeException {
    public ConsumeApiException(String message) {
        super(message);
    }

    public ConsumeApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
