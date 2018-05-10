package com.alef.jump;


import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import Logic.Constants;
import Logic.Functions;
import Logic.SendGetRequest;


public class JobView extends AppCompatActivity {

    private int id;

    TextView tvJobName, tvDesc, tvJobCost, tvStartDate, tvEndDate, tvNumVac;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_view);

        Bundle extras = getIntent().getExtras();

        id = extras.getInt("id");

        tvJobName = findViewById(R.id.tv_jobName);
        tvDesc = findViewById(R.id.tv_desc);
        tvJobCost = findViewById(R.id.tv_JobCostVal);
        tvStartDate = findViewById(R.id.tv_startDateVal);
        tvEndDate = findViewById(R.id.tv_endDateVal);
        tvNumVac = findViewById(R.id.tv_numVacVal);


        @SuppressLint("StaticFieldLeak") SendGetRequest sendGetRequest = new SendGetRequest(Constants.getJobRead()+"?id="+id) {
            @Override
            protected void onPostExecute(String response) {
                if(response!=null){
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response);
                        JSONObject dataJob = jsonObject.getJSONObject("dataJob");
                        JSONObject dataUser = jsonObject.getJSONObject("dataUser");
                        //  JSONObject dataUserStaff = jsonObject.getJSONObject("dataUserStaff");


                        tvJobName.setText(dataJob.getString("title"));
                        tvDesc.setText(dataJob.getString("description"));
                        tvJobCost.setText(dataJob.getString("jobcost"));
                        tvStartDate.setText(dataJob.getString("datestart"));
                        tvEndDate.setText(dataJob.getString("dateend"));
                        tvNumVac.setText(dataJob.getString("numbervacancies"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        };

        if(id > 0) {
            sendGetRequest.execute();
        }

    }




}
