package com.github.kyleilantzis.yoar.example.states;

public class AppState {

    private final LoginState mLoginState;

    private AppState(LoginState loginState) {
        this.mLoginState = loginState;
    }

    public static Builder builder() {
        return new Builder();
    }

    public LoginState getLoginState() {
        return mLoginState;
    }

    public static class Builder {

        private LoginState mLoginState;

        private Builder() {
        }

        public Builder setAppState(AppState appState) {
            mLoginState = appState.mLoginState;
            return this;
        }

        public Builder setLoginState(LoginState loginState) {
            mLoginState = loginState;
            return this;
        }

        public AppState build() {
            return new AppState(mLoginState);
        }
    }
}
