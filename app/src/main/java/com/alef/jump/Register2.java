package com.alef.jump;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Register2 extends AppCompatActivity {
    Button btnNext;
    DatePicker dpBirthDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        btnNext = (Button) findViewById(R.id.btnNext);
        dpBirthDate = (DatePicker) findViewById(R.id.dpBirthDate);
    }

    public void onClickNext(View v){
        int year = dpBirthDate.getYear();
        int month = dpBirthDate.getMonth();
        int day = dpBirthDate.getDayOfMonth();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(calendar.getTime());
        String values = getIntent().getStringExtra("values")+","+dateString;

        Intent intent = new Intent(getApplicationContext(),Register3.class);
        intent.putExtra("values",values);
        startActivity(intent);
    }
}
