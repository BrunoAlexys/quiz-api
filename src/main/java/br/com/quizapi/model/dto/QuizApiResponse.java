package br.com.quizapi.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record QuizApiResponse(
        List<QuizQuestionDTO> results
) {
}
