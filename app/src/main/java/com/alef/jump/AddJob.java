package com.alef.jump;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Logic.Constants;
import Logic.GetRequest;
import Logic.MaskWatcher;
import Logic.SendPostRequest;

public class AddJob extends Activity {


    EditText etTitle, etDescription, etGenAmnt, etCurrAmnt, etDateStrt, etDateEnd,
            etDateLimAppl, etNumVac;

    TextView tvMode;

    Button btnPrev, btnNext, btnDone;

    ViewFlipper vfAddJob;

    Spinner spinnerModes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);

        spinnerModes = findViewById(R.id.sp_Mode);

        etTitle = findViewById(R.id.et_Title);
        etDescription = findViewById(R.id.et_Desc);
        tvMode = findViewById(R.id.tv_Mode);
        etGenAmnt = findViewById(R.id.et_GenAmnt);
        etCurrAmnt = findViewById(R.id.et_CurrAmnt);

        etDateStrt = findViewById(R.id.et_DateStrt);
        etDateStrt.addTextChangedListener(new MaskWatcher("####-##-##"));

        etDateEnd = findViewById(R.id.et_DateEnd);
        etDateEnd.addTextChangedListener(new MaskWatcher("####-##-##"));

        etDateLimAppl = findViewById(R.id.et_DateLimAppl);
        etDateLimAppl.addTextChangedListener(new MaskWatcher("####-##-##"));

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

                JSONObject jobData= new JSONObject();

                try {
                    jobData.put("idemployer","1");
                    jobData.put("mode", tvMode.getText().toString());
                    jobData.put("state","1");
                   // jobData.put("idlocation","1");
                    jobData.put("title", etTitle.getText().toString());
                    jobData.put("description", etDescription.getText().toString());
                    jobData.put("jobcost",etGenAmnt.getText().toString());
                    jobData.put("jobcostcovered",etCurrAmnt.getText().toString());
                    jobData.put("dateposted", "2018-05-01");
                    jobData.put("datestart",etDateStrt.getText().toString());
                    jobData.put("dateend",etDateEnd.getText().toString());
                    jobData.put("datepostend", etDateLimAppl.getText().toString());
                    jobData.put("numbervacancies",etNumVac.getText().toString());

                    SendPostRequest saveJob = new SendPostRequest(getApplicationContext(), Constants.getJobCreate(),jobData);

                    saveJob.execute();


                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Error",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }




            }
        });



        /*Extraccion de los modos de trabajo*/

        final List<String> modes = new ArrayList<>();

        GetRequest getJobModes = new GetRequest() {
            @Override
            public void procesarRespuesta(JSONObject jsonObject) {

            }

            @Override
            public void procesarRespuesta(JSONArray jsonArray) {
                for (int i = 0; i<jsonArray.length();i++){
                    try {
                        modes.add(jsonArray.getJSONObject(i).getString("mode"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };


        getJobModes.getJsonArray(getApplicationContext(),Constants.getJobModeRead());


        ArrayAdapter<String> modesAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,modes);
        modesAdapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        spinnerModes.setAdapter(modesAdapter);
        spinnerModes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tvMode.setText(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                tvMode.setText("Choose an option");
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
