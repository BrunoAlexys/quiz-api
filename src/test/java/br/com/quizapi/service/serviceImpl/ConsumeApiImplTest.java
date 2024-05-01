package br.com.quizapi.service.serviceImpl;

import br.com.quizapi.model.service.serviceImpl.ConsumeApiImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ConsumeApiImplTest {

    @Test
    void testConsumeAPI_WithNullURL() {
        ConsumeApiImpl api = new ConsumeApiImpl();
        assertThrows(NullPointerException.class, () -> {
            api.consumeAPI(null);
        });
    }

    @Test
    void testConsumeAPI_WithInvalidURL() {
        ConsumeApiImpl api = new ConsumeApiImpl();
        assertThrows(IllegalArgumentException.class, () -> {
            api.consumeAPI("invalid-url");
        });
    }
}