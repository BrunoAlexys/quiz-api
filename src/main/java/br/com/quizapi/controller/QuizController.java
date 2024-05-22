package br.com.quizapi.controller;

import br.com.quizapi.model.dto.CategoryDTO;
import br.com.quizapi.model.dto.ListQuestion;
import br.com.quizapi.model.dto.ListQuizDTO;
import br.com.quizapi.model.dto.SearchDataDTO;
import br.com.quizapi.model.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@AllArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping
    public ResponseEntity<Void> saveQuiz(@RequestBody SearchDataDTO url) {
        this.quizService.saveQuiz(url);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ListQuizDTO>> getQuiz() {
        List<ListQuizDTO> quizQuestionDTOList = this.quizService.getQuiz();
        return ResponseEntity.ok(quizQuestionDTOList);
    }

    @PostMapping("/search")
    public ResponseEntity<List<ListQuizDTO>> searchQuestions(@RequestBody SearchDataDTO searchDataDTO) {
        List<ListQuizDTO> quizQuestionDTOList = this.quizService.searchQuestions(searchDataDTO);
        return ResponseEntity.ok(quizQuestionDTOList);
    }
    @PostMapping("/perguntas")
    public ResponseEntity<List<ListQuestion>> getQuestions(@RequestBody SearchDataDTO searchDataDTO) {
        List<ListQuestion> quizQuestionDTOList = this.quizService.getQuestions(searchDataDTO);
        return ResponseEntity.ok(quizQuestionDTOList);
    }

    @GetMapping("/category")
    public ResponseEntity<List<CategoryDTO>> getByCategory() {
        List<CategoryDTO> categoryList = this.quizService.getCategory();
        return ResponseEntity.ok(categoryList);
    }
}
