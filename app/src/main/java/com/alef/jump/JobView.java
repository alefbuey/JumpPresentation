package com.alef.jump;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import Logic.Constants;
import Logic.Functions;
import Logic.SendGetRequest;


public class JobView extends AppCompatActivity {

    private int id;
    private Double jobCost;

    TextView tvJobName, tvDesc, tvJobCost, tvStartDate, tvEndDate, tvNumVac;

    Button btnApply, btnList;


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

        btnApply = findViewById(R.id.btn_apply);
        btnList = findViewById(R.id.btn_listApplicants);

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JobView.this, ApplyJob.class);
                intent.putExtra("idJob", id);
                String jobName = tvJobName.getText().toString();
                Double salary = jobCost/Double.parseDouble(tvNumVac.getText().toString());
                intent.putExtra("jobName", jobName);
                intent.putExtra("salary",new Functions().round(salary));
                startActivity(intent);
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JobView.this, ListApplicants.class);
                intent.putExtra("idJob", id);
                startActivity(intent);

            }
        });

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

                        int idEmployer = dataUser.getInt("id");

                        if(idEmployer==Globals.getInstance().getId()){
                            btnApply.setVisibility(View.GONE);
                            btnList.setVisibility(View.VISIBLE);
                        }else{
                            btnList.setVisibility(View.GONE);
                            btnApply.setVisibility(View.VISIBLE);
                        }

                        String price = String.valueOf(dataJob.getInt("jobcost"));
                        jobCost = Double.parseDouble(price);
                        tvJobName.setText(dataJob.getString("title"));
                        tvDesc.setText(dataJob.getString("description"));
                        tvJobCost.setText("$ "+ price);
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
