package com.alef.jump;

import android.annotation.SuppressLint;
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
import android.widget.Toast;
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

        gender = "";

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

        String name = etName.getText().toString();
        String lastname = etLastName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        Log.e("validacion", name.isEmpty() ? "Esta vacio" : "no esta vacio");

        if(!name.isEmpty() || !lastname.isEmpty() || !email.isEmpty() || !password.isEmpty() || !gender.isEmpty() ) {

            //Creacion del Json que se va a enviar
            JSONObject dataUser = new JSONObject();
            JSONObject data = new JSONObject();
            try {

                data.put("name", name);
                data.put("idstate",1);
                data.put("idlocation",1);
                data.put("typenationalidentifier",1);
                data.put("lastname", lastname);
                data.put("email", email);
                data.put("password", password);
                data.put("birthdate", dateString);
                data.put("gender", gender);
                data.put("nationalidentifier","No Info");
                data.put("direction","No Info");
                data.put("nationality","No Info");
                data.put("availablemoney","0.00");
                data.put("rank","0");


                //Envio a traves de metodo POST
                @SuppressLint("StaticFieldLeak") SendPostRequest spr = new SendPostRequest(Constants.getInsertUser(), dataUser) {
                    @Override
                    protected void onPostExecute(Integer respuesta) {
                        if (respuesta==0) {
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(),"Error in the process",Toast.LENGTH_LONG).show();
                        }
                    }
                };

                spr.setMensaje("Successful User Creation");
                spr.execute();
            } catch (Exception e) {
                Log.d("CREACION JSON", e.getMessage());
            }

        }else{
            Toast.makeText(getApplicationContext(),"Complete your register. Empty Fields",Toast.LENGTH_LONG).show();
        }
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
