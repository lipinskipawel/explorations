package com.github.lipinskipawel.binary;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.lipinskipawel.binary.Bits.readBeShort;
import static com.github.lipinskipawel.binary.Bits.setBit;
import static com.github.lipinskipawel.binary.Bits.unsetBit;

@DisplayName("bitwise operation")
class BitsTest implements WithAssertions {

    @Test
    @DisplayName("set bit correctly")
    void set_bit() {
        byte flag = 0b0000_0001;

        var result = setBit(flag, 4);

        assertThat(result).isEqualTo(17);
    }

    @Test
    @DisplayName("unset bit correctly")
    void unset_bit() {
        byte flag = 0b0001_0001;

        var result = unsetBit(flag, 4);

        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("read short from 2 bytes big endian")
    void read_short_from_2_bytes() {
        byte x0 = (byte) 5;
        byte x1 = (byte) 179;

        int result = readBeShort(x0, x1);

        assertThat(result).isEqualTo((short) 1459);
    }
}