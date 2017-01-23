package com.github.kyleilantzis.yoar.redux;

public interface RunnableAction<S> {

    String getType();

    void run(Store<S> store);
}
