package com.alef.jump;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import Logic.ConnectionDB;

public class testconnection extends AppCompatActivity {

    Button btns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testconnection);


        btns= findViewById(R.id.buttonqq);
        btns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectionDB c = new ConnectionDB();
                c.connectDB();
            }
        });
    }
}
