package com.github.kyleilantzis.yoar.example.app;

import android.support.v4.app.FragmentActivity;

import com.github.kyleilantzis.yoar.example.values.AppState;
import com.github.kyleilantzis.yoar.redux.Action;
import com.github.kyleilantzis.yoar.redux.RunnableAction;
import com.github.kyleilantzis.yoar.redux.Store;

public class AbstractActivity extends FragmentActivity implements Store.StateChangeListener<AppState> {

    @Override
    protected void onResume() {
        super.onResume();

        Store<AppState> store = getStore();
        onChange(store.getState());
        store.subscribe(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        Store<AppState> store = getStore();
        store.unsubscribe(this);
    }

    public App getApp() {
        return (App) getApplication();
    }

    public Store<AppState> getStore() {
        App app = (App) getApp();
        return app.getStore();
    }

    public void dispatch(Action<?> action) {
        App app = (App) getApp();
        Store<AppState> store = app.getStore();
        store.dispatch(action);
    }

    public void dispatch(RunnableAction<AppState> runnableAction) {
        App app = (App) getApp();
        Store<AppState> store = app.getStore();
        store.dispatch(runnableAction);
    }

    @Override
    public void onChange(AppState state) {

    }
}
