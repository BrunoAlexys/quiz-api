package br.com.quizapi.controller;

import br.com.quizapi.model.dto.CategoryDTO;
import br.com.quizapi.model.dto.ListQuestionDTO;
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

    @GetMapping("/perguntas")
    public ResponseEntity<List<ListQuestionDTO>> getQuestions(
            @RequestParam int amount,
            @RequestParam String category,
            @RequestParam String difficulty
    ) {
        List<ListQuestionDTO> quizQuestionDTOList = this.quizService.getQuestions(amount, category, difficulty);
        return ResponseEntity.ok(quizQuestionDTOList);
    }

    @GetMapping("/category")
    public ResponseEntity<List<CategoryDTO>> getByCategory() {
        List<CategoryDTO> categoryList = this.quizService.getCategory();
        return ResponseEntity.ok(categoryList);
    }
}
