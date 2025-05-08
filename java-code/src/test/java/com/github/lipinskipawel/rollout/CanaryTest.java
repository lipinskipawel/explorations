package com.github.lipinskipawel.rollout;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import static com.github.lipinskipawel.rollout.Canary.isUnder;
import static java.util.UUID.fromString;

class CanaryTest implements WithAssertions {

    @Test
    void rollout_to_10_percentage() {
        final var under = fromString("6f72d2de-a33c-48d9-8be2-c9e5bb29e759");
        final var upper = fromString("b579c334-9d1e-461d-a336-bf552954dff2");

        final var rollout = isUnder(under, 10);
        final var notRollout = isUnder(upper, 10);

        assertThat(rollout).isTrue();
        assertThat(notRollout).isFalse();
    }
}
