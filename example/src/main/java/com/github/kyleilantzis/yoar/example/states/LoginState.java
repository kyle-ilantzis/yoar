package com.github.kyleilantzis.yoar.example.states;

public class LoginState {

    private final boolean mIsLoginRequest;
    private final boolean mIsLoginSuccess;
    private final boolean mIsLoginFailure;

    private LoginState(boolean mIsLoginRequest, boolean mIsLoginSuccess, boolean mIsLoginFailure) {
        this.mIsLoginRequest = mIsLoginRequest;
        this.mIsLoginSuccess = mIsLoginSuccess;
        this.mIsLoginFailure = mIsLoginFailure;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean isLoginRequest() {
        return mIsLoginRequest;
    }

    public boolean isLoginSuccess() {
        return mIsLoginSuccess;
    }

    public boolean isLoginFailure() {
        return mIsLoginFailure;
    }

    public static class Builder {

        private boolean mIsLoginRequest;
        private boolean mIsLoginSuccess;
        private boolean mIsLoginFailure;

        private Builder() {
        }

        public Builder setLoginState(LoginState loginState) {
            setLoginRequest(loginState.mIsLoginRequest);
            setLoginSuccess(loginState.mIsLoginSuccess);
            setLoginFailure(loginState.mIsLoginFailure);
            return this;
        }

        public Builder setLoginRequest(boolean loginRequest) {
            mIsLoginRequest = loginRequest;
            return this;
        }

        public Builder setLoginSuccess(boolean loginSuccess) {
            mIsLoginSuccess = loginSuccess;
            return this;
        }

        public Builder setLoginFailure(boolean loginFailure) {
            mIsLoginFailure = loginFailure;
            return this;
        }

        public LoginState build() {
            return new LoginState(mIsLoginRequest, mIsLoginSuccess, mIsLoginFailure);
        }
    }
}
