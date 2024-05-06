package br.com.quizapi.model.service.serviceImpl;

import br.com.quizapi.infra.exceptions.QuizApiException;
import br.com.quizapi.model.dto.QuizApiResponse;
import br.com.quizapi.model.dto.QuizQuestionDTO;
import br.com.quizapi.model.service.ConsumeAPI;
import br.com.quizapi.model.service.ConvertData;
import br.com.quizapi.model.service.QuizApiClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class QuizApiClientImpl implements QuizApiClient {
    private final ConvertData convertData;
    private final ConsumeAPI consumeAPI;

    public List<QuizQuestionDTO> fetchQuizQuestions(String url) {
        try {
            String jsonData = consumeAPI.consumeAPI(url);
            QuizApiResponse apiResponse = convertData.convertData(jsonData, QuizApiResponse.class);
            return apiResponse.results();
        } catch (Exception e) {
            log.error("Erro ao processar a resposta da API: {}", e.getMessage());
            throw new QuizApiException("Erro ao processar a resposta da API", e);
        }
    }

}
