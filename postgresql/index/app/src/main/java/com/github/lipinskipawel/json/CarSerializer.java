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
        gen.writeStringField("submodel", car.submodel());
        gen.writeNumberField("wheels", car.wheels());
        car.currentOwner().ifPresent(it -> {
            try {
                gen.writeStringField("current_owner", it.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        if (car.features().length > 0) {
            gen.writeFieldName("features");
            gen.writeStartArray();
            for (String feature : car.features()) {
                gen.writeString(feature);
            }
            gen.writeEndArray();
        }
        gen.writeStringField("details", car.details());
        gen.writeStringField("created_date", car.createdDate().toString());
        car.assembledData().ifPresent(it -> {
            try {
                gen.writeStringField("assembled_date", it.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        gen.writeEndObject();
    }
}
