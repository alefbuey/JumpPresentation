package com.alef.jump;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ViewFlipper;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import Logic.Constants;
import Logic.SendPostRequest;

public class Register extends AppCompatActivity {

    EditText etName, etLastName,etEmail, etPassword;
    DatePicker dpBirthDate;
    CheckBox cbMale,cbFemale;
    Button btnNext, btnPrev, btnSave;
    String gender;
    ViewFlipper vfRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText) findViewById(R.id.etName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        dpBirthDate = (DatePicker) findViewById(R.id.dpBirthDate);
        cbFemale = (CheckBox) findViewById(R.id.cbFemale);
        cbMale = (CheckBox) findViewById(R.id.cbMale);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnPrev = (Button) findViewById(R.id.btnPrev);
        vfRegister = (ViewFlipper) findViewById(R.id.vfRegister);



    }

    public void onClickNext(View v){

        if (vfRegister.getDisplayedChild() == 0 || vfRegister.getDisplayedChild() == 1 || vfRegister.getDisplayedChild() == 2) {
            if (vfRegister.getDisplayedChild() == 2) {
                btnSave.setVisibility(View.VISIBLE);
                btnNext.setVisibility(View.GONE);
            }
            btnPrev.setVisibility(View.VISIBLE);
            vfRegister.setInAnimation(inFromRightAnimation());
            vfRegister.setOutAnimation(outToLeftAnimation());
            vfRegister.showNext();

        }
    }

    public void onClickPrev(View v) {

        if (vfRegister.getDisplayedChild() == 1 || vfRegister.getDisplayedChild() == 2 || vfRegister.getDisplayedChild() == 3) {
            if (vfRegister.getDisplayedChild() == 3) {
                btnNext.setVisibility(View.VISIBLE);
                btnSave.setVisibility(View.GONE);
            }
            if (vfRegister.getDisplayedChild() == 1) {
                btnPrev.setVisibility(View.GONE);
            }
            vfRegister.setInAnimation(inFromLeftAnimation());
            vfRegister.setOutAnimation(outToRightAnimation());
            vfRegister.showPrevious();
        }
    }

    public void onClickSave(View v){

        String dateString = chagedFormatDate();

        //Creacion del Json que se va a enviar
//        String values = getIntent().getStringExtra("values")+","+ etEmail.getText().toString()+","+etPassword.getText().toString();
//        String[] partValues = values.split(",");
        JSONObject data = new JSONObject();
        try{
            data.put("name",etName.getText().toString());
            data.put("lastname",etLastName.getText().toString());
            data.put("birthdate",dateString);
            data.put("gender",gender);
            data.put("email",etEmail.getText().toString());
            data.put("password",etPassword.getText().toString());
            Log.d("JSON TO STRING",data.toString());
            //Envio a traves de metodo POST
            SendPostRequest spr = new SendPostRequest(getApplicationContext(), Constants.getInsertUser(),data);
            spr.setMensaje("Successful User Creation");
            spr.execute();
        }catch (Exception e){
            Log.d("CREACION JSON",e.getMessage());
        }


        Intent i = new Intent(getApplicationContext(),Login.class);
        startActivity(i);
    }

    //Método para cambiar de formato

    public String chagedFormatDate(){
        //Fecha
        int year = dpBirthDate.getYear();
        int month = dpBirthDate.getMonth();
        int day = dpBirthDate.getDayOfMonth();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(calendar.getTime());
        return dateString;
    }

    //Metodo para el checkbox

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.cbFemale:
                if (checked)
                    gender = "F";
                cbFemale.setChecked(true);
                cbMale.setChecked(false);
                break;
            case R.id.cbMale:
                if (checked)
                    gender = "M";
                cbMale.setChecked(true);
                cbFemale.setChecked(false);
                break;
        }

        Log.d("SELECTION",gender);
    }
    ///////////////////////METODOS PARA EL FLIPPER////////////////

    private Animation inFromRightAnimation() {

        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,  +1.0f,
                Animation.RELATIVE_TO_PARENT,  0.0f,
                Animation.RELATIVE_TO_PARENT,  0.0f,
                Animation.RELATIVE_TO_PARENT,   0.0f );

        inFromRight.setDuration(200);
        inFromRight.setInterpolator(new AccelerateInterpolator());

        return inFromRight;

    }

    private Animation outToLeftAnimation() {
        Animation outtoLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoLeft.setDuration(200);
        outtoLeft.setInterpolator(new AccelerateInterpolator());
        return outtoLeft;
    }

    private Animation inFromLeftAnimation() {
        Animation inFromLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromLeft.setDuration(200);
        inFromLeft.setInterpolator(new AccelerateInterpolator());
        return inFromLeft;
    }

    private Animation outToRightAnimation() {
        Animation outtoRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoRight.setDuration(200);
        outtoRight.setInterpolator(new AccelerateInterpolator());
        return outtoRight;
    }
}
