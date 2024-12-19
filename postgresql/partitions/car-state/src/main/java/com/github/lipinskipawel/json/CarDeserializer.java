package com.github.lipinskipawel.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.lipinskipawel.db.Car;
import com.github.lipinskipawel.db.CarState;

import java.io.IOException;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static java.util.Optional.ofNullable;

public final class CarDeserializer extends JsonDeserializer<Car> {

    @Override
    public Car deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        final JsonNode tree = p.getCodec().readTree(p);

        final var id = ofNullable(tree.get("id")).map(JsonNode::asText).map(UUID::fromString).orElseThrow();
        final var brand = ofNullable(tree.get("brand")).map(JsonNode::asText).orElseThrow();
        final var model = ofNullable(tree.get("model")).map(JsonNode::asText).orElseThrow();
        final var state = ofNullable(tree.get("state")).map(JsonNode::asText).map(CarState::valueOf).orElseThrow();
        final var assembledDate = parseOptionalNode(ctx, tree, "assembledDate", Instant.class);

        return new Car(
            id,
            brand,
            model,
            state,
            assembledDate
        );
    }

    private <T> Optional<T> parseOptionalNode(
        DeserializationContext ctx,
        TreeNode tree,
        String nodeName,
        Class<T> zlass
    ) throws IOException {
        final var jsonNode = tree.get(nodeName);
        if (jsonNode == null) {
            return Optional.empty();
        }

        final var parser = jsonNode.traverse(ctx.getParser().getCodec());
        parser.nextToken();

        return Optional.of(ctx.readValue(parser, zlass));
    }
}
