package br.com.quizapi.model.dto;

import br.com.quizapi.model.entities.IncorrectAnswer;
import br.com.quizapi.model.entities.Quiz;

import java.util.List;

public record ListQuizDTO(
        Long id,
        String type,
        String difficulty,
        String category,
        String question,
        String correctAnswer,
        List<String> incorrectAnswers
) {
    public ListQuizDTO(Quiz quiz) {
        this(quiz.getId(), quiz.getType(), quiz.getDifficulty(), quiz.getCategory(), quiz.getQuestion()
                , quiz.getCorrectAnswer(), List.of(String.valueOf(quiz.getIncorrectAnswers().stream()
                        .map(IncorrectAnswer::getIncorrectAnswer)
                        .toList())));
    }
}
