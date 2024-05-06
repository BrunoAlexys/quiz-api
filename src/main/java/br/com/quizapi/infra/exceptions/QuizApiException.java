package br.com.quizapi.infra.exceptions;

public class QuizApiException extends RuntimeException {
    public QuizApiException(String message) {
        super(message);
    }

    public QuizApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
