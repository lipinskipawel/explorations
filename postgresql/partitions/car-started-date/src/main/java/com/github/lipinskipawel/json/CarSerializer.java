package com.github.lipinskipawel.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.lipinskipawel.db.Car;

import java.io.IOException;

public final class CarSerializer extends JsonSerializer<Car> {

    @Override
    public void serialize(Car car, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("id", car.id().toString());
        gen.writeStringField("brand", car.brand());
        gen.writeStringField("model", car.model());
        gen.writeStringField("state", car.state().toString());
        gen.writeStringField("startedDate", car.startedDate().toString());
        gen.writeEndObject();
    }
}
