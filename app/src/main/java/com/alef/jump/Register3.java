package com.alef.jump;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;

public class Register3 extends AppCompatActivity /*implements CompoundButton.OnCheckedChangeListener */{

    Button btnNext;
    CheckBox cbMale, cbFemale;
    String selection;
    private static int numberOfCheckboxesChecked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);

        btnNext = (Button) findViewById(R.id.btnNext);
        cbFemale = (CheckBox) findViewById(R.id.cbFemale);
        cbMale = (CheckBox) findViewById(R.id.cbMale);
    }


//    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        if (isChecked) {
//            if (numberOfCheckboxesChecked >= 1) {
//                buttonView.setChecked(false);
//            } else {
//                numberOfCheckboxesChecked++;
//            }
//        } else {
//            numberOfCheckboxesChecked--;
//        }
//    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.cbFemale:
                if (checked)
                    selection = "F";
                    cbFemale.setChecked(true);
                    cbMale.setChecked(false);
                break;
            case R.id.cbMale:
                if (checked)
                    selection = "M";
                    cbMale.setChecked(true);
                    cbFemale.setChecked(false);
                break;
        }

        Log.d("SELECTION",selection);
    }

    public void onClickNext(View v){

        String values = getIntent().getStringExtra("values")+","+selection;
        Intent i = new Intent(getApplicationContext(),Register4.class);
        i.putExtra("values",values);
        startActivity(i);
        Log.d("VALORES",values);
    }
}
