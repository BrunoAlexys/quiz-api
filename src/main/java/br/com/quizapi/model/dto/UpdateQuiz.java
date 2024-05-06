package br.com.quizapi.model.dto;

import java.util.List;

public record UpdateQuiz(
        String type,
        String difficulty,
        String category,
        String question,
        String correctAnswer,
        List<String> incorrectAnswers
) {
}
