package com.github.lipinskipawel.sorting;

import java.time.Instant;
import java.util.Objects;

final class Holder {

    private Instant time;

    public Holder(Instant time) {
        this.time = time;
    }

    public Instant time() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Holder holder = (Holder) o;
        return Objects.equals(time, holder.time);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(time);
    }

    @Override
    public String toString() {
        return "Holder{" +
            "time=" + time +
            '}';
    }
}
