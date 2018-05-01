package com.alef.jump;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import Logic.Constants;
import Logic.SendPostRequest;

public class Register4 extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register4);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnSave = (Button) findViewById(R.id.btnSave);
    }

    public void onClickSave(View v){

        //Creacion del Json que se va a enviar
        String values = getIntent().getStringExtra("values")+","+ etEmail.getText().toString()+","+etPassword.getText().toString();
        String[] partValues = values.split(",");
        JSONObject data = new JSONObject();
        try{
            data.put("name",partValues[0]);
            data.put("lastname",partValues[1]);
            data.put("birthdate",partValues[2]);
            data.put("gender",partValues[3]);
            data.put("email",partValues[4]);
            data.put("password",partValues[5]);
            Log.d("JSON TO STING",data.toString());

        }catch (Exception e){
            Log.d("CREACION JSON",e.getMessage());
        }

        SendPostRequest spr = new SendPostRequest(getApplicationContext(), Constants.getInsertUser(),data);
        spr.execute();
        Intent i = new Intent(getApplicationContext(),Login.class);
        startActivity(i);
    }
}
