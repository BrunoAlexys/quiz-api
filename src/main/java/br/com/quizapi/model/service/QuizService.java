package br.com.quizapi.model.service;

import br.com.quizapi.model.dto.UpdateQuizDTO;
import br.com.quizapi.model.dto.UrlDTO;
import br.com.quizapi.model.entities.Quiz;

import java.util.List;
import java.util.Optional;

public interface QuizService {
    void saveQuiz(UrlDTO url);
    List<Quiz> getQuiz();
    Optional<Quiz> getQuizById(Long id);
    void deleteQuiz(Long id);
    void updateQuiz(Long id, UpdateQuizDTO quiz);

}
