package br.com.quizapi.model.service.serviceImpl;

import br.com.quizapi.model.service.ConvertData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConvertDataImpl implements ConvertData {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T convertData(String data, Class<T> clazz) {
        try {
            return objectMapper.readValue(data, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
