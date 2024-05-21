package br.com.quizapi.model.dto;

import br.com.quizapi.model.entities.Category;

public record CategoryDTO(
        Long id,
        String category
) {
    public CategoryDTO(Category category) {
        this(category.getId(), category.getName());
    }
}
