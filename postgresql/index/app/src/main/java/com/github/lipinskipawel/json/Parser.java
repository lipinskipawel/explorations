package com.github.lipinskipawel.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.lipinskipawel.db.Car;

public final class Parser {
    private static final ObjectMapper PARSER = new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .registerModule(new Jdk8Module())
        .registerModule(new SimpleModule()
            .addDeserializer(Car.class, new CarDeserializer())
            .addSerializer(Car.class, new CarSerializer()));

    public Car parse(String json) {
        try {
            return PARSER.readValue(json, Car.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(json);
        }
    }

    public String parse(Car car) {
        try {
            return PARSER.writeValueAsString(car);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
