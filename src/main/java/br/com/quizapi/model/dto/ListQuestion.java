package br.com.quizapi.model.dto;

import br.com.quizapi.model.entities.IncorrectAnswers;
import br.com.quizapi.model.entities.Quiz;

import java.util.List;

public record ListQuestion(
        Long id,
        String question,
        String correctAnswer,
        List<String> incorrectAnswers
) {
    public ListQuestion(Quiz quiz) {
        this(quiz.getId(), quiz.getQuestion(), quiz.getCorrectAnswer(),
                List.of(String.valueOf(quiz.getIncorrectAnswers().stream()
                        .map(IncorrectAnswers::getIncorrectAnswer)
                        .toList())));
    }
}
