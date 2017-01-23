package com.github.kyleilantzis.yoar.example.app;

import android.app.Application;

import com.github.kyleilantzis.yoar.example.values.AppState;
import com.github.kyleilantzis.yoar.redux.Store;

public class App extends Application {

    private Store<AppState> mStore;

    @Override
    public void onCreate() {
        super.onCreate();
        //mStore = Store.createStore( new AppReducer(), new AppState(), AndroidExecutorService.newAndroidExecutorService() );
    }

    public Store<AppState> getStore() {
        return mStore;
    }
}
