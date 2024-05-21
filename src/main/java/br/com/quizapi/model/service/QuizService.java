package br.com.quizapi.model.service;

import br.com.quizapi.model.dto.CategoryDTO;
import br.com.quizapi.model.dto.ListQuestion;
import br.com.quizapi.model.dto.ListQuizDTO;
import br.com.quizapi.model.dto.SearchDataDTO;

import java.util.List;

public interface QuizService {
    void saveQuiz(SearchDataDTO url);

    List<ListQuizDTO> getQuiz();
    List<ListQuestion> getQuestions(SearchDataDTO searchDataDTO);

    List<ListQuizDTO> searchQuestions(SearchDataDTO searchDataDTO);

    List<CategoryDTO> getCategory();
}
