package com.alef.jump;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Logic.ConnectionDB;
import People.Employer;
import Work.Job;

public class AddJob extends AppCompatActivity {

    EditText etTitle, etDescription, etMode, etGenAmnt, etCurrAmnt, etDateStrt, etDateEnd,
            etDateLimAppl, etPayMeth, etNumVac;

    Button btnAddJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);

        etTitle = findViewById(R.id.etXTitle);
        etDescription = findViewById(R.id.etXDesc);
        etMode = findViewById(R.id.etXMode);
        etGenAmnt = findViewById(R.id.etXGenAmnt);
        etCurrAmnt = findViewById(R.id.etXCurrAmnt);
        etDateStrt = findViewById(R.id.etXDateStrt);
        etDateEnd = findViewById(R.id.etXDateEnd);
        etDateLimAppl = findViewById(R.id.etXDateLimAppl);
        etPayMeth = findViewById(R.id.etXPayMeth);
        etNumVac = findViewById(R.id.etXNumVac);
        btnAddJob = findViewById(R.id.btnXAddJob);

        btnAddJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Job newJob = new Job(
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
                                    );*/

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

}
