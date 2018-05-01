package com.alef.jump;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ViewFlipper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import People.Employer;
import Work.Job;

public class AddJob extends AppCompatActivity {

    EditText etTitle, etDescription, etMode, etGenAmnt, etCurrAmnt, etDateStrt, etDateEnd,
            etDateLimAppl, etNumVac;

    Button btnPrev, btnNext, btnDone;

    ViewFlipper vfAddJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);

        etTitle = findViewById(R.id.et_Title);
        etDescription = findViewById(R.id.et_Desc);
        etMode = findViewById(R.id.et_Mode);
        etGenAmnt = findViewById(R.id.et_GenAmnt);
        etCurrAmnt = findViewById(R.id.et_CurrAmnt);
        etDateStrt = findViewById(R.id.et_DateStrt);
        etDateEnd = findViewById(R.id.et_DateEnd);
        etDateLimAppl = findViewById(R.id.et_DateLimAppl);
        etNumVac = findViewById(R.id.et_NumVac);

        btnPrev = findViewById(R.id.btn_Previous);
        btnNext = findViewById(R.id.btn_Next);
        btnDone = findViewById(R.id.btn_Done);

        vfAddJob = findViewById(R.id.vf_AddJob);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vfAddJob.getDisplayedChild() == 0 || vfAddJob.getDisplayedChild() == 1) {
                    if (vfAddJob.getDisplayedChild() == 1) {
                        btnDone.setVisibility(View.VISIBLE);
                        btnNext.setVisibility(View.GONE);
                    }
                    btnPrev.setVisibility(View.VISIBLE);
                    vfAddJob.setInAnimation(inFromRightAnimation());
                    vfAddJob.setOutAnimation(outToLeftAnimation());
                    vfAddJob.showNext();

                }
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vfAddJob.getDisplayedChild()==1 || vfAddJob.getDisplayedChild()==2) {
                    if (vfAddJob.getDisplayedChild()==2){
                        btnNext.setVisibility(View.VISIBLE);
                        btnDone.setVisibility(View.GONE);
                    }
                    if (vfAddJob.getDisplayedChild()==1){
                        btnPrev.setVisibility(View.GONE);
                    }
                    vfAddJob.setInAnimation(inFromLeftAnimation());
                    vfAddJob.setOutAnimation(outToRightAnimation());
                    vfAddJob.showPrevious();
                }

            }
        });




        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Job newJob = new Job(
                                    new Employer(1),
                                    etMode.getText().toString(),
                                    "Posted",
                                    "Quito",
                                    etTitle.getText().toString(),
                                    etDescription.getText().toString(),
                                    9999.99f,
                                    null,
                                    new Date(),
                                    getDate(etDateStrt.getText().toString()),
                                    getDate(etDateEnd.getText().toString()),
                                    getDate(etDateLimAppl.getText().toString()),
                                    (byte) 69,
                                    null,
                                    null
                                    );


/*
                Job newJob = new Job(
                        new Employer(1),
                        "Single",
                        "Posted",
                        "Quito",
                        "Clean House",
                        "Nooooo",
                        9999.99f,
                        null,
                        new Date(),
                        getDate("2018-05-15"),
                        getDate("2018-05-16"),
                        getDate("2018-05-10"),
                        (byte) 69,
                        null,
                        null
                );
*/


                newJob.createJob();

            }
        });

    }



    public Date getDate(String rawDate){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Date d1;
        try {
            d1 = date.parse(rawDate);
        } catch (ParseException e) {
            d1 = null;
            e.printStackTrace();
        }
        return d1;
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
