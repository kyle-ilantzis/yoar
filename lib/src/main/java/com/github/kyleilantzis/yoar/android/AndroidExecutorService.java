package com.github.kyleilantzis.yoar.android;

import android.os.Handler;
import android.os.Looper;

import com.github.kyleilantzis.yoar.redux.Store;

import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AndroidExecutorService implements Store.ExecutorService {

    private HashMap<String, Executor> mExecutors;

    private AndroidExecutorService() {

        mExecutors = new HashMap<>();

        mExecutors.put(Store.RUNNABLE_ACTION_TYPE_DEFAULT, new Executor() {

            Handler mHandler = new Handler(Looper.getMainLooper());

            @Override
            public void execute(Runnable command) {
                mHandler.post(command);
            }
        });
    }

    public static Store.ExecutorService newExecutorService() {
        return new AndroidExecutorService();
    }

    @Override
    public boolean isOnDefaultThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    @Override
    public Executor getType(String type) {
        Executor executor = mExecutors.get(type);
        if (executor == null) {
            executor = Executors.newSingleThreadExecutor();
            mExecutors.put(type, executor);
        }
        return executor;
    }
}
