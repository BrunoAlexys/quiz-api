package br.com.quizapi.service.serviceImpl;

import br.com.quizapi.exceptions.ConsumeApiException;
import br.com.quizapi.service.ConsumeAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Slf4j
public class ConsumeApiImpl implements ConsumeAPI {
    @Override
    public String consumeAPI(String url) {
        log.info("Iniciando chamada para a URL: {}", url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            log.info("Resposta recebida da API: {}", response.statusCode());
        } catch (IOException | InterruptedException e) {
            log.error("Erro ao consumir a API: {}", e.getMessage());
            throw new ConsumeApiException("Error ao consumir a API ",e);
        }

        return response.body();
    }
}
