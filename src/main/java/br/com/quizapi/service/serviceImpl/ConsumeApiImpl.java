package br.com.quizapi.service.serviceImpl;

import br.com.quizapi.exceptions.ConsumeApiException;
import br.com.quizapi.service.ConsumeAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Slf4j
public class ConsumeApiImpl implements ConsumeAPI {
    @Override
    public String consumeAPI(String url) {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("URL is null or empty");
        }
        log.info("Iniciando chamada para a URL: {}", url);
        try {
            URI uri = new URI(url);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            log.info("Resposta recebida da API: {}", response.statusCode());
            return response.body();
        } catch (IOException | InterruptedException | URISyntaxException e) {
            log.error("Erro ao consumir a API: {}", e.getMessage());
            throw new ConsumeApiException("Erro ao consumir a API", e);
        }
    }
}
