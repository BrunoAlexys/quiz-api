package br.com.quizapi.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "tb_quiz")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String difficulty;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    private String question;
    private String correctAnswer;
    @OneToMany(mappedBy = "quiz",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IncorrectAnswers> incorrectAnswers;
}
