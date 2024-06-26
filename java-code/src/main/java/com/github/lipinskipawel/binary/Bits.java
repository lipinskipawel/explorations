package com.github.lipinskipawel.binary;

public final class Bits {

    public static int setBit(int flag, int position) {
        return flag | (1 << position);
    }

    public static int unsetBit(int flag, int position) {
        return flag & ~(1 << position);
    }

    public static short readBeShort(byte hi, byte lo) {
        return (short) (hi << 8 | (lo & 0xFF));
    }
}
