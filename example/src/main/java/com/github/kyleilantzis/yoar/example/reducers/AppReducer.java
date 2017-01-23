package com.github.kyleilantzis.yoar.example.reducers;

import com.github.kyleilantzis.yoar.example.values.AppState;
import com.github.kyleilantzis.yoar.example.values.LoginState;
import com.github.kyleilantzis.yoar.redux.Action;
import com.github.kyleilantzis.yoar.redux.Reducer;

public class AppReducer implements Reducer<AppState> {

    @Override
    public AppState reduce(Action action, AppState state) {

        LoginState loginState = reduce(action, state.getLoginState());

        // return new AppState( loginState );
        return state;
    }

    private LoginState reduce(Action action, LoginState loginState) {

        return loginState;
    }
}
