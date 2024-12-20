package com.github.lipinskipawel.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.lipinskipawel.db.Car;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
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
        final var submodel = ofNullable(tree.get("submodel")).map(JsonNode::asText).orElseThrow();
        final var wheels = ofNullable(tree.get("wheels")).map(JsonNode::asInt).orElseThrow();
        final var currentOwner = ofNullable(tree.get("current_owner")).map(JsonNode::asText).map(UUID::fromString);
        final var features = parseFeaturesArray(ctx, tree);
        final var details = ofNullable(tree.get("details")).map(JsonNode::asText).orElseThrow();
        final var createdDate = ofNullable(tree.get("created_date")).map(JsonNode::asText).map(Instant::parse).orElseThrow();
        final var assembledDate = parseOptionalNode(ctx, tree, "assembled_date", Instant.class);

        return new Car(
            id,
            brand,
            model,
            submodel,
            wheels,
            currentOwner,
            features,
            details,
            createdDate,
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

    private String[] parseFeaturesArray(DeserializationContext dctx, TreeNode tree) throws IOException {
        final var node = tree.get("features");
        if (node == null || !node.isArray()) {
            return new String[0];
        }

        final var result = new ArrayList<String>();
        final var elements = ((JsonNode) node).elements();

        while (elements.hasNext()) {
            final var parser = elements.next().traverse(dctx.getParser().getCodec());
            parser.nextToken();
            result.add(dctx.readValue(parser, String.class));
        }

        return result.toArray(String[]::new);
    }
}
