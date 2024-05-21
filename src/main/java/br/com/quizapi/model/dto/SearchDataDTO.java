package br.com.quizapi.model.dto;

public record SearchDataDTO(
        int amount,
        String category,
        String difficulty
) {
}
