package br.com.quizapi.model.repository;

import br.com.quizapi.model.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Query(value = "SELECT * FROM tb_quiz " +
            "WHERE category_id = CAST(:category AS bigint) AND " +
            "difficulty = :difficulty " +
            "ORDER BY RANDOM() LIMIT :amount", nativeQuery = true)
    List<Quiz> searchQuestions(int amount, String category, String difficulty);

    @Query(value = "SELECT DISTINCT category FROM tb_quiz", nativeQuery = true)
    List<String> findByCategory();
}