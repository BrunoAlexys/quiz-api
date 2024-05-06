package br.com.quizapi.model.service.serviceImpl;

import br.com.quizapi.infra.exceptions.QuizException;
import br.com.quizapi.model.dto.QuizQuestionDTO;
import br.com.quizapi.model.dto.UpdateQuizDTO;
import br.com.quizapi.model.dto.UrlDTO;
import br.com.quizapi.model.entities.IncorrectAnswer;
import br.com.quizapi.model.entities.Quiz;
import br.com.quizapi.model.repository.QuizRepository;
import br.com.quizapi.model.service.QuizApiClient;
import br.com.quizapi.model.service.QuizService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class QuizServiceImpl implements QuizService {

    private static final String URL = "https://opentdb.com/api.php?";

    private final QuizRepository quizRepository;
    private final QuizApiClient quizApiClient;

    @Transactional
    @Override
    public void saveQuiz(UrlDTO url) {
        try {
            log.info("Iniciando busca de dados da API: {}", url);
            String apiUrl = buildApiUrl(url);
            log.debug("API URL: {}", apiUrl);

            List<QuizQuestionDTO> questions = fetchQuizQuestions(apiUrl);
            List<Quiz> quizList = mapQuestionsToQuizEntities(questions);

            saveQuizEntities(quizList);

            log.info("Quiz salvo com sucesso.");
        } catch (DataAccessException e) {
            log.error("Erro ao buscar ou salvar dados do quiz: {}", e.getMessage());
            throw new QuizException("Erro ao buscar ou salvar dados do quiz", e);
        }
    }

    @Override
    public List<Quiz> getQuiz() {
        try {
            return this.quizRepository.findAll();
        } catch (DataAccessException e) {
            log.error("Erro ao buscar dados do quiz: {}", e.getMessage());
            throw new QuizException("Erro ao buscar dados do quiz", e);
        }
    }

    @Override
    public Optional<Quiz> getQuizById(Long id) {
        try {
            return this.quizRepository.findById(id);
        } catch (DataAccessException e) {
            log.error("Erro ao buscar dados do quiz: {}", e.getMessage());
            throw new QuizException("Erro ao buscar dados do quiz", e);
        }
    }

    @Transactional
    @Override
    public void deleteQuiz(Long id) {
        try {
            this.quizRepository.deleteById(id);
        } catch (DataAccessException e) {
            log.error("Erro ao deletar dados do quiz: {}", e.getMessage());
            throw new QuizException("Erro ao deletar dados do quiz", e);
        }
    }

    @Transactional
    @Override
    public void updateQuiz(Long id, UpdateQuizDTO quiz) {
        try {
            Optional<Quiz> quizOptional = Optional.ofNullable(this.quizRepository.findById(id)
                    .orElseThrow(() -> new QuizException("Quiz não encontrado")));
            var quizEntity = quizOptional.get();
            if(quiz.category() != null) {
                quizEntity.setCategory(quiz.category());
            }
            if(quiz.type() != null) {
                quizEntity.setType(quiz.type());
            }
            if(quiz.difficulty() != null) {
                quizEntity.setDifficulty(quiz.difficulty());
            }
            if(quiz.question() != null) {
                quizEntity.setQuestion(quiz.question());
            }
            if(quiz.correctAnswer() != null) {
                quizEntity.setCorrectAnswer(quiz.correctAnswer());
            }

        } catch (DataAccessException e) {
            log.error("Erro ao atualizar dados do quiz: {}", e.getMessage());
            throw new QuizException("Erro ao atualizar dados do quiz", e);
        }
    }

    private String buildApiUrl(UrlDTO url) {
        return URL + "amount=" + url.amount() + "&category=" + url.category() + "&difficulty=" + url.difficulty() + "&type=" + url.type();
    }


    private List<QuizQuestionDTO> fetchQuizQuestions(String apiUrl) {
        log.info("Buscando dados da API: {}", apiUrl);
        return quizApiClient.fetchQuizQuestions(apiUrl);
    }

    private List<Quiz> mapQuestionsToQuizEntities(List<QuizQuestionDTO> questions) {
        return questions.stream().map(quizQuestionDTO -> {
            Quiz quiz = new Quiz();
            quiz.setCategory(quizQuestionDTO.category());
            quiz.setType(quizQuestionDTO.type());
            quiz.setDifficulty(quizQuestionDTO.difficulty());
            quiz.setQuestion(quizQuestionDTO.question());
            quiz.setCorrectAnswer(quizQuestionDTO.correctAnswer());
            quiz.setIncorrectAnswers(mapToIncorrectAnswers(quiz, quizQuestionDTO.incorrectAnswers()));
            return quiz;
        }).collect(Collectors.toList());
    }

    private List<IncorrectAnswer> mapToIncorrectAnswers(Quiz quiz, List<String> incorrectAnswers) {
        return incorrectAnswers.stream().map(incorrectAnswer -> {
            IncorrectAnswer incorrectAnswerEntity = new IncorrectAnswer();
            incorrectAnswerEntity.setQuiz(quiz);
            incorrectAnswerEntity.setIncorrectAnswer(incorrectAnswer);
            return incorrectAnswerEntity;
        }).collect(Collectors.toList());
    }

    private void saveQuizEntities(List<Quiz> quizList) {
        log.info("Salvando quiz no banco de dados: {}", quizList);
        quizRepository.saveAll(quizList);
    }
}
