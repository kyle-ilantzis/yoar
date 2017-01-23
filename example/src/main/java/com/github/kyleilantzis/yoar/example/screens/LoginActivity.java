package com.github.kyleilantzis.yoar.example.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.github.kyleilantzis.yoar.R;
import com.github.kyleilantzis.yoar.example.actions.Actions;
import com.github.kyleilantzis.yoar.example.app.AbstractActivity;

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
}
