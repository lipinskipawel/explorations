package com.github.lipinskipawel.memoryModel.volatileObjects;

final class FlagObject {

    private final boolean flag;

    FlagObject(final boolean flag) {
        this.flag = flag;
    }

    boolean isNotDone() {
        return !flag;
    }
}
