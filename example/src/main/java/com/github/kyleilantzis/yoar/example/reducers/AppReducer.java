package com.github.kyleilantzis.yoar.example.reducers;

import com.github.kyleilantzis.yoar.example.Constants;
import com.github.kyleilantzis.yoar.example.states.AppState;
import com.github.kyleilantzis.yoar.example.states.LoginState;
import com.github.kyleilantzis.yoar.redux.Action;
import com.github.kyleilantzis.yoar.redux.Reducer;

public class AppReducer implements Reducer<AppState> {

    private AppReducer() {
    }

    public static AppReducer newAppReducer() {
        return new AppReducer();
    }

    @Override
    public AppState reduce(Action action, AppState state) {

        LoginState loginState = reduce(action, state.getLoginState());

        return AppState.builder()
                .setLoginState(loginState)
                .build();
    }

    private LoginState reduce(Action action, LoginState state) {

        switch (action.type) {

            case Constants.ACTION_TYPE_LOGIN_REQUEST:
                return LoginState.builder()
                        .setLoginState(state)
                        .setLoginRequest(true)
                        .setLoginSuccess(false)
                        .setLoginFailure(false)
                        .build();

            case Constants.ACTION_TYPE_LOGIN_SUCCESS:
                return LoginState.builder()
                        .setLoginState(state)
                        .setLoginRequest(false)
                        .setLoginSuccess(true)
                        .setLoginFailure(false)
                        .build();

            case Constants.ACTION_TYPE_LOGIN_FAILURE:
                return LoginState.builder()
                        .setLoginState(state)
                        .setLoginRequest(false)
                        .setLoginSuccess(false)
                        .setLoginFailure(true)
                        .build();

            default:
                return state;
        }
    }
}
