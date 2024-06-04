package br.com.quizapi.model.service;

import br.com.quizapi.model.dto.CategoryDTO;
import br.com.quizapi.model.dto.ListQuestionDTO;
import br.com.quizapi.model.dto.ListQuizDTO;
import br.com.quizapi.model.dto.SearchDataDTO;

import java.util.List;

public interface QuizService {
    void saveQuiz(SearchDataDTO url);

    List<ListQuizDTO> getQuiz();

    List<ListQuestionDTO> getQuestions(int amount, String category, String difficulty);

    List<CategoryDTO> getCategory();
}
