package com.github.lipinskipawel;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

final class AdvisoryLockTest
    extends IntegrationSpec
    implements WithAssertions {

    @Test
    void acquire_session_lock_with_success() {
        final var locked = advisoryLock.lockOnSession("some");
        final var unlocked = advisoryLock.unlockOnSession("some");

        assertThat(locked).isEqualTo(true);
        assertThat(unlocked).isEqualTo(true);
    }

    @Test
    void can_not_lock_already_locked_key() {
        final var locked = advisoryLock.lockOnSession("some");

        final var notLocked = advisoryLock.lockOnSession("some");

        final var unlocked = advisoryLock.unlockOnSession("some");
        assertThat(locked).isEqualTo(true);
        assertThat(notLocked).isEqualTo(false);
        assertThat(unlocked).isEqualTo(true);
    }
}
