package com.github.kyleilantzis.yoar.example.values;

public class LoginState {

    private final boolean mIsLoginRequest;
    private final boolean mIsLoginSuccess;
    private final boolean mIsLoginFailure;

    public LoginState() {
        mIsLoginRequest = false;
        mIsLoginSuccess = false;
        mIsLoginFailure = false;
    }

    public LoginState(boolean mIsLoginRequest, boolean mIsLoginSuccess, boolean mIsLoginFailure) {
        this.mIsLoginRequest = mIsLoginRequest;
        this.mIsLoginSuccess = mIsLoginSuccess;
        this.mIsLoginFailure = mIsLoginFailure;
    }
}
