package com.github.lipinskipawel.rollout;

import java.util.UUID;

import static java.lang.Math.abs;

public final class Canary {

    public static boolean isUnder(UUID userId, int rolloutPercentage) {
        final var base10 = abs(userId.getLeastSignificantBits());
        final var percentage = base10 % 100;

        return percentage < rolloutPercentage;
    }
}
