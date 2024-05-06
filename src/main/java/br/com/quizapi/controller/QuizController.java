package br.com.quizapi.controller;

import br.com.quizapi.infra.exceptions.QuizException;
import br.com.quizapi.model.dto.ListQuizDTO;
import br.com.quizapi.model.dto.UpdateQuizDTO;
import br.com.quizapi.model.dto.UrlDTO;
import br.com.quizapi.model.entities.Quiz;
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
    public ResponseEntity<Void> saveQuiz(@RequestBody UrlDTO url) {
        this.quizService.saveQuiz(url);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ListQuizDTO>> getQuiz() {
        List<Quiz> quiz = this.quizService.getQuiz();
        List<ListQuizDTO> quizQuestionDTOList = quiz.stream()
                .map(ListQuizDTO::new)
                .toList();
        return ResponseEntity.ok(quizQuestionDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListQuizDTO> getById(@PathVariable Long id) {
        var quiz = this.quizService.getQuizById(id)
                .orElseThrow(() -> new QuizException("Quiz não encontrado"));
        return ResponseEntity.ok(new ListQuizDTO(quiz));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateQuiz(@PathVariable Long id, @RequestBody UpdateQuizDTO updateQuizDTO) {
        this.quizService.updateQuiz(id, updateQuizDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        this.quizService.deleteQuiz(id);
        return ResponseEntity.ok().build();
    }
}
