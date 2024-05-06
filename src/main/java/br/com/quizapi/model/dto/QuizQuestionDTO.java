package br.com.quizapi.model.dto;

import br.com.quizapi.model.entities.IncorrectAnswer;
import br.com.quizapi.model.entities.Quiz;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record QuizQuestionDTO(
        @JsonAlias("type")
        String type,
        @JsonAlias("difficulty")
        String difficulty,
        @JsonAlias("category")
        String category,
        @JsonAlias("question")
        String question,
        @JsonAlias("correct_answer")
        String correctAnswer,
        @JsonAlias("incorrect_answers")
        List<String> incorrectAnswers

) {
}
