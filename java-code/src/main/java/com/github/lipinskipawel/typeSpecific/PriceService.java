package com.github.lipinskipawel.typeSpecific;

final class PriceService {
    private final PriceRepo priceRepo;

    PriceService(final PriceRepo priceRepo) {
        this.priceRepo = priceRepo;
    }

    int computeRegularPrice(final int days) {
        return days + 1;
    }

    int computeNewReleasePrice(final int days) {
        return (int) this.priceRepo.getFactor() * days;
    }

    int computeChildrenPrice(final int days) {
        return 5;
    }

    int computePrice(final Movie.Type type, final int days) {
        return type.priceAlgo.apply(this, days);
    }
}
