package com.github.kyleilantzis.yoar.example.actions;

import com.github.kyleilantzis.yoar.example.values.AppState;
import com.github.kyleilantzis.yoar.redux.Action;
import com.github.kyleilantzis.yoar.redux.RunnableAction;
import com.github.kyleilantzis.yoar.redux.Store;

public class Actions {

    public static final String RUNNABLE_ACTION_TYPE_NET_IO = "net-io";

    public static final String ACTION_TYPE_LOGIN_REQUEST = "login-request";
    public static final String ACTION_TYPE_LOGIN_SUCCESS = "login-request";
    public static final String ACTION_TYPE_LOGIN_FAILURE = "login-failure";

    public static RunnableAction<AppState> login(String username, String password) {

        return new RunnableAction<AppState>() {
            @Override
            public String getType() {
                return RUNNABLE_ACTION_TYPE_NET_IO;
            }

            @Override
            public void run(Store<AppState> store) {

                store.dispatch(action(ACTION_TYPE_LOGIN_REQUEST));

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                store.dispatch(action(ACTION_TYPE_LOGIN_FAILURE));
            }
        };
    }


    public static Action<Void> action(String type) {
        return new Action<>(type, null);
    }
}
