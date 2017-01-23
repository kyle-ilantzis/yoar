package com.github.kyleilantzis.yoar.example.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import com.github.kyleilantzis.yoar.example.R;
import com.github.kyleilantzis.yoar.example.actions.Actions;
import com.github.kyleilantzis.yoar.example.app.AbstractActivity;
import com.github.kyleilantzis.yoar.example.states.AppState;
import com.github.kyleilantzis.yoar.example.states.LoginState;

public class LoginActivity extends AbstractActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        findViewById(R.id.button_login).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        dispatch(Actions.login("admin", "secret"));
    }

    @Override
    public void onChange(AppState state) {

        LoginState loginState = state.getLoginState();

        if (loginState.isLoginRequest()) {

            Toast.makeText(this, "Loging in ...", Toast.LENGTH_SHORT).show();

        } else if (loginState.isLoginSuccess()) {

        } else if (loginState.isLoginFailure()) {

            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Login failed");
            b.setMessage("Invalid username/password");
            b.show();

        }
    }
}
