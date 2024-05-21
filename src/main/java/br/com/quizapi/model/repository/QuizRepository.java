package br.com.quizapi.model.repository;

import br.com.quizapi.model.dto.SearchDataDTO;
import br.com.quizapi.model.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Query(value = "SELECT * FROM tb_quiz " +
            "WHERE category_id = CAST(:#{#searchDataDTO.category()} AS bigint) AND " +
            "difficulty = :#{#searchDataDTO.difficulty()} " +
            "ORDER BY RANDOM() LIMIT :limit", nativeQuery = true)
    List<Quiz> searchQuestions(@Param("searchDataDTO") SearchDataDTO searchDataDTO, @Param("limit") int limit);

    @Query(value = "SELECT DISTINCT category FROM tb_quiz", nativeQuery = true)
    List<String> findByCategory();
}