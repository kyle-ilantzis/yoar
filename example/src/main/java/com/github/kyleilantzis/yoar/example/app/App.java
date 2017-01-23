package com.github.kyleilantzis.yoar.example.app;

import android.app.Application;

import com.github.kyleilantzis.yoar.android.AndroidExecutorService;
import com.github.kyleilantzis.yoar.example.reducers.AppReducer;
import com.github.kyleilantzis.yoar.example.states.AppState;
import com.github.kyleilantzis.yoar.example.states.LoginState;
import com.github.kyleilantzis.yoar.redux.Reducer;
import com.github.kyleilantzis.yoar.redux.Store;

public class App extends Application {

    private Store<AppState> mStore;

    @Override
    public void onCreate() {
        super.onCreate();

        Reducer<AppState> reducer = AppReducer.newAppReducer();

        AppState state = AppState.builder()
                .setLoginState(LoginState.builder().build())
                .build();

        Store.ExecutorService executorService = AndroidExecutorService.newAndroidExecutorService();

        mStore = Store.createStore(reducer, state, executorService);
    }

    public Store<AppState> getStore() {
        return mStore;
    }
}
