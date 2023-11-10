package com.github.lipinskipawel.typeSpecific;

import java.util.function.BiFunction;

class Movie {

    private final Type type;

    Movie(final Type type) {
        this.type = type;
    }

    enum Type {
        REGULAR(PriceService::computeRegularPrice),
        NEW_RELEASE(PriceService::computeNewReleasePrice),
        CHILDREN(PriceService::computeChildrenPrice);

        public final BiFunction<PriceService, Integer, Integer> priceAlgo;

        Type(BiFunction<PriceService, Integer, Integer> priceAlgo) {
            this.priceAlgo = priceAlgo;
        }
    }
}
