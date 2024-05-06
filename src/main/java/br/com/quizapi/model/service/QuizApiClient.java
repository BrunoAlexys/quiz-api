package br.com.quizapi.model.service;

import br.com.quizapi.model.dto.QuizQuestionDTO;

import java.util.List;

public interface QuizApiClient {
    List<QuizQuestionDTO> fetchQuizQuestions(String url);
}
