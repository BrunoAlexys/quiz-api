package br.com.quizapi.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record QuizQuestionDTO(
        String category,
        String type,
        String difficulty,
        String question,
        @JsonAlias("correct_answer")
        String correctAnswer,
        @JsonAlias("incorrect_answers")
        List<String> incorrectAnswers
) {
}
