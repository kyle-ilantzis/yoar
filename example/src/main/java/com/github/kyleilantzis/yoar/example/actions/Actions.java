package com.github.kyleilantzis.yoar.example.actions;

import com.github.kyleilantzis.yoar.example.Constants;
import com.github.kyleilantzis.yoar.example.states.AppState;
import com.github.kyleilantzis.yoar.redux.Action;
import com.github.kyleilantzis.yoar.redux.RunnableAction;
import com.github.kyleilantzis.yoar.redux.Store;

public class Actions {

    public static RunnableAction<AppState> login(String username, String password) {

        return new RunnableAction<AppState>() {
            @Override
            public String getType() {
                return Constants.RUNNABLE_ACTION_TYPE_NET_IO;
            }

            @Override
            public void run(Store<AppState> store) {

                store.dispatch(action(Constants.ACTION_TYPE_LOGIN_REQUEST));

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                store.dispatch(action(Constants.ACTION_TYPE_LOGIN_FAILURE));
            }
        };
    }


    public static Action<Void> action(String type) {
        return new Action<>(type, null);
    }
}
