package br.com.quizapi.model.repository;

import br.com.quizapi.model.entities.IncorrectAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncorrectAnswerRepository extends JpaRepository<IncorrectAnswer, Long> {
}