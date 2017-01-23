package com.github.kyleilantzis.yoar.redux;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;

public class Store<S> {

    public static final String RUNNABLE_ACTION_TYPE_DEFAULT = "default";

    private final Reducer<S> mReducer;
    private final List<StateChangeListener<S>> mListeners;
    private final ExecutorService mExecutorService;
    private final Object mExecutorServiceLock;
    private volatile S mState;

    private Store(Reducer<S> reducer, S state, ExecutorService executorService) {
        mReducer = reducer;
        mState = state;
        mListeners = new LinkedList<>();
        mExecutorService = executorService;
        mExecutorServiceLock = new Object();
    }

    public static <S> Store<S> createStore(Reducer<S> reducer, S initialState, ExecutorService executorService) {
        return new Store<>(reducer, initialState, executorService);
    }

    public S getState() {
        return mState;
    }

    public void dispatch(final Action<?> action) {
        if (mExecutorService.isOnDefaultThread()) {
            dispatchOnDefaultThread(action);
        } else {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    dispatchOnDefaultThread(action);
                }
            };
            execute(RUNNABLE_ACTION_TYPE_DEFAULT, runnable);
        }
    }

    public void dispatch(final RunnableAction<S> runnableAction) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                runnableAction.run(Store.this);
            }
        };
        String type = runnableAction.getType();
        execute(type, runnable);
    }

    public void subscribe(StateChangeListener<S> listener) {
        mListeners.add(listener);
    }

    public void unsubscribe(StateChangeListener<S> listener) {
        mListeners.remove(listener);
    }

    private void dispatchOnDefaultThread(Action<?> action) {

        mState = mReducer.reduce(action, mState);

        for (StateChangeListener<S> listener : mListeners) {
            listener.onChange(mState);
        }
    }

    private void execute(String type, Runnable runnable) {
        Executor executor;
        synchronized (mExecutorServiceLock) {
            executor = mExecutorService.getType(type);
        }
        executor.execute(runnable);
    }

    public interface StateChangeListener<S> {
        void onChange(S state);
    }

    public interface ExecutorService {

        boolean isOnDefaultThread();

        Executor getType(String type);
    }
}
