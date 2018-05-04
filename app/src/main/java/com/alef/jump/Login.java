package com.alef.jump;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Logic.Constants;
import Logic.GetRequestUser;
import Logic.SendGetRequest;

public class Login extends AppCompatActivity {

    EditText etEmail,etPassword;
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);
    }

    public void onClickLogin(View v) {

        GetRequestUser gru = new GetRequestUser(getApplicationContext(),Constants.getSelectUser() + "?email=" + etEmail.getText().toString() + "&password=" + etPassword.getText().toString());
        gru.setEmail(etEmail.getText().toString());
        gru.execute();
    }

    public void onClickRegister(View v){
        Intent i = new Intent(getApplicationContext(),Register.class);
        startActivity(i);
    }
}
