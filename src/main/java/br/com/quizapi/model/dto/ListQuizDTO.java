package br.com.quizapi.model.dto;

import br.com.quizapi.model.entities.Category;
import br.com.quizapi.model.entities.IncorrectAnswers;
import br.com.quizapi.model.entities.Quiz;

import java.util.List;

public record ListQuizDTO(
        Long id,
        String difficulty,
        Category category,
        String question,
        String correctAnswer,
        List<String> incorrectAnswers
) {
    public ListQuizDTO(Quiz quiz) {
        this(quiz.getId(), quiz.getDifficulty(), quiz.getCategory(), quiz.getQuestion()
                , quiz.getCorrectAnswer(), List.of(String.valueOf(quiz.getIncorrectAnswers().stream()
                        .map(IncorrectAnswers::getIncorrectAnswer)
                        .toList())));
    }
}
