package com.github.kyleilantzis.yoar.redux;

public class Action<V> {

    public final String type;

    public final V value;

    public Action(String type, V value) {
        this.type = type;
        this.value = value;
    }

    public static Action<Void> create(String type) {
        return new Action<>(type, null);
    }

    public static <V> Action<V> create(String type, V value) {
        return new Action<>(type, value);
    }
}
