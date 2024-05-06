package br.com.quizapi.model.service;

public interface ConvertData {
    <T> T convertData(String data, Class<T> clazz);
}
