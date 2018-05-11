package com.alef.jump;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import Logic.Constants;
import Logic.SendPostRequest;

public class ApplyJob extends Activity {

    int idJob;
    String jobName;
    Double salary;

    TextView tvJobName, tvSalary;

    EditText etApplyReason, etCounterOffer, etCounterOfferReason;

    ImageView imAccept, imCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_apply_job);


        Bundle extras = getIntent().getExtras();

        idJob = extras.getInt("idJob");
        jobName = extras.getString("jobName");
        salary = extras.getDouble("salary");


        /*Parametros vista flotante*/
        this.setFinishOnTouchOutside(false);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;
        this.getWindow().setAttributes(params);
        /* ******************************* */

        etApplyReason = findViewById(R.id.et_applyReason);
        etCounterOfferReason = findViewById(R.id.et_counterOfferReason);
        etCounterOffer = findViewById(R.id.et_counterOffer);

        tvJobName = findViewById(R.id.tv_jobName);
        tvSalary = findViewById(R.id.tv_Salary);

        imAccept = findViewById(R.id.im_Accept);
        imCancel = findViewById(R.id.im_Decline);

        tvJobName.setText(jobName);
        tvSalary.setText(String.valueOf("Salary: $"+salary));


        imAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    JSONObject dataApplication = new JSONObject();
                    dataApplication.put("idemployee", Globals.getInstance().getId());
                    dataApplication.put("idjob", idJob);
                    dataApplication.put("state", 1);
                    dataApplication.put("salary", salary);
                    if(!etCounterOffer.getText().toString().isEmpty()){
                        dataApplication.put("counteroffer", etCounterOffer.getText().toString());
                        dataApplication.put("counterofferreason", etCounterOfferReason.getText().toString());
                    }
                    if(!etApplyReason.getText().toString().isEmpty()){
                        dataApplication.put("postedreason", etApplyReason.getText().toString());
                    }

                    SendPostRequest sp = new SendPostRequest(getApplicationContext(), Constants.getJobApply(),dataApplication);
                    sp.execute();
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        });

        imCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
