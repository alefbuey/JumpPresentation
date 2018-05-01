package com.alef.jump;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Register1 extends AppCompatActivity {

    EditText etName, etLastName;
    Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);

        etName = (EditText) findViewById(R.id.etName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        btnNext = (Button) findViewById(R.id.btnNext);

    }

    public void onClickNext(View v){
        String values = etName.getText().toString()+","+etLastName.getText().toString();
        Intent intent = new Intent(getApplicationContext(),Register2.class);
        intent.putExtra("values",values);
        startActivity(intent);
    }
}
