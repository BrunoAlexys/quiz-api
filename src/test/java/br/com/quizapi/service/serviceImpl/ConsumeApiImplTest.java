package br.com.quizapi.service.serviceImpl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertThrows(RuntimeException.class, () -> {
            api.consumeAPI("invalid-url");
        });
    }
}