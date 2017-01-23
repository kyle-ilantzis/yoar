package com.github.kyleilantzis.yoar.redux;

public class Action<V> {

    public final String type;

    public final V value;

    public Action(String type, V value) {
        this.type = type;
        this.value = value;
    }
}
