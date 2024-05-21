package br.com.quizapi.model.service.serviceImpl;

import br.com.quizapi.infra.exceptions.QuizException;
import br.com.quizapi.model.dto.*;
import br.com.quizapi.model.entities.Category;
import br.com.quizapi.model.entities.IncorrectAnswers;
import br.com.quizapi.model.entities.Quiz;
import br.com.quizapi.model.repository.CategoryRepository;
import br.com.quizapi.model.repository.QuizRepository;
import br.com.quizapi.model.service.QuizService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class QuizServiceImpl implements QuizService {

    private static final String URL = "https://opentdb.com/api.php?";

    private final QuizRepository quizRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public void saveQuiz(SearchDataDTO url) {
        try {
            log.info("Iniciando busca de dados da API: {}", url);
            String apiUrl = buildApiUrl(url);
            log.debug("API URL: {}", apiUrl);

            RestTemplate template = new RestTemplate();
            QuizApiResponseDTO response = template.getForObject(apiUrl, QuizApiResponseDTO.class);
            List<Quiz> quizList = mapQuestionsToQuizEntities(response.results());
            this.quizRepository.saveAll(quizList);
        } catch (DataAccessException e) {
            log.error("Erro ao buscar ou salvar dados do quiz: {}", e.getMessage());
            throw new QuizException("Erro ao buscar ou salvar dados do quiz", e);
        }
    }

    @Override
    public List<ListQuizDTO> getQuiz() {
        try {
            List<Quiz> quizList = this.quizRepository.findAll();
            return quizList.stream()
                    .map(ListQuizDTO::new)
                    .toList();
        } catch (DataAccessException e) {
            log.error("Erro ao buscar dados do quiz: {}", e.getMessage());
            throw new QuizException("Erro ao buscar dados do quiz", e);
        }
    }

    @Override
    public List<ListQuestion> getQuestions(SearchDataDTO searchDataDTO) {
        try {
            int numberQuestions = searchDataDTO.amount();
            List<Quiz> quizList = this.quizRepository
                    .searchQuestions(searchDataDTO,numberQuestions);
            return quizList.stream()
                    .map(ListQuestion::new)
                    .toList();
        }catch (Exception e) {
            log.error("Erro ao buscar dados do quiz: {}", e.getMessage());
            throw new QuizException("Erro ao buscar dados do quiz", e);
        }
    }

    @Override
    public List<ListQuizDTO> searchQuestions(SearchDataDTO searchDataDTO) {
        try {
            int numberQuestions = searchDataDTO.amount();
            List<Quiz> quizList = this.quizRepository
                    .searchQuestions(searchDataDTO,numberQuestions);
            return quizList.stream()
                    .map(ListQuizDTO::new)
                    .toList();
        }catch (Exception e) {
            log.error("Erro ao buscar dados do quiz: {}", e.getMessage());
            throw new QuizException("Erro ao buscar dados do quiz", e);
        }
    }

    @Override
    public List<CategoryDTO> getCategory() {
        return categoryRepository.findAll().stream().map(CategoryDTO::new).toList();
    }

    private String buildApiUrl(SearchDataDTO url) {
        return URL + "amount=" + url.amount() + "&category=" + url.category() + "&difficulty=" + url.difficulty() + "&type=multiple";
    }

    private List<Quiz> mapQuestionsToQuizEntities(List<QuizQuestionDTO> questions) {
        log.info("Mapeando dados para entidades de quiz: {}", questions);
        return questions.stream().map(quizQuestionDTO -> {
            Quiz quiz = new Quiz();
            Optional<Category> category = categoryRepository.findByName(quizQuestionDTO.category());

            if (category.isEmpty()) {
                Category newCategory = new Category();
                newCategory.setName(quizQuestionDTO.category());
                category = Optional.of(categoryRepository.save(newCategory));
            }

            quiz.setCategory(category.get());
            quiz.setDifficulty(quizQuestionDTO.difficulty());
            quiz.setQuestion(quizQuestionDTO.question());
            quiz.setCorrectAnswer(quizQuestionDTO.correctAnswer());
            quiz.setIncorrectAnswers(mapToIncorrectAnswers(quiz, quizQuestionDTO.incorrectAnswers()));
            log.info("Quiz mapeado: {}", quiz);
            return quiz;
        }).collect(Collectors.toList());
    }

    private List<IncorrectAnswers> mapToIncorrectAnswers(Quiz quiz, List<String> incorrectAnswers) {
        log.info("Mapeando respostas incorretas para entidades de quiz: {}", incorrectAnswers);
        return incorrectAnswers.stream().map(incorrectAnswer -> {
            IncorrectAnswers incorrectAnswersEntity = new IncorrectAnswers();
            incorrectAnswersEntity.setQuiz(quiz);
            incorrectAnswersEntity.setIncorrectAnswer(incorrectAnswer);
            log.info("Resposta incorreta mapeada: {}", incorrectAnswersEntity);
            return incorrectAnswersEntity;
        }).collect(Collectors.toList());
    }
}
