package com.github.kyleilantzis.yoar.redux;

public interface Reducer<S> {

    S reduce(Action action, S state);
}
