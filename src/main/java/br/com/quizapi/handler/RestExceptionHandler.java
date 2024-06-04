package br.com.quizapi.handler;

import br.com.quizapi.infra.exceptions.ExceptionDetails;
import br.com.quizapi.infra.exceptions.QuizNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(QuizNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDetails quizNotFoundException(QuizNotFoundException quizNotFoundException) {
        return new ExceptionDetails(
                HttpStatus.NOT_FOUND.name(),
                HttpStatus.NOT_FOUND.value(),
                quizNotFoundException.getMessage(),
                quizNotFoundException.getClass().getName(),
                LocalDate.now()
        );
    }
}
