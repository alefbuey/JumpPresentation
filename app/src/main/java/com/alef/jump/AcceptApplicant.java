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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import Logic.Constants;
import Logic.SendPostRequest;

public class AcceptApplicant extends Activity {

    JSONObject data;

    boolean dataCharged = false;

    int idEmployee, idJob;

    String userName, userLastname;

    TextView tvEmployeeName, tvApplyReason, tvCounterOffer;

    ImageView imAccept, imDecline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_accept_applicant);

        /*Parametros vista flotante*/
        this.setFinishOnTouchOutside(false);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;
        this.getWindow().setAttributes(params);
        /* ******************************* */

        Bundle extras = getIntent().getExtras();

        tvEmployeeName = findViewById(R.id.tv_employeeName);
        tvApplyReason = findViewById(R.id.tv_applyReason);
        tvCounterOffer = findViewById(R.id.tv_counterOffer);

        try {
            data = new JSONObject(extras.getString("dataApply"));
            idEmployee = extras.getInt("idEmployee");
            idJob = extras.getInt("idJob");
            userName = extras.getString("name");
            userLastname = extras.getString("lastname");
            tvEmployeeName.setText(userName+" "+userLastname);
            tvApplyReason.setText(data.getString("postedreason"));
            tvCounterOffer.setText("Counteroffer: $ " + data.getString("counteroffer"));
            dataCharged = true;
            } catch (JSONException e) {
            e.printStackTrace();
        }

        imAccept = findViewById(R.id.im_Accept);
        imDecline = findViewById(R.id.im_Decline);


        imDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dataCharged){

                JSONObject acceptance = new JSONObject();
                    try {
                        acceptance.put("state", 2);
                        acceptance.put("idemployee", idEmployee);
                        acceptance.put("idjob", idJob);

                        Log.d("JSON Acceptance", String.valueOf(acceptance));

                        SendPostRequest sp = new SendPostRequest(getApplicationContext(), Constants.getUpdateEmployeeState(),acceptance);
                        sp.execute();
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), "Error",
                                Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }


                }
            }
        });

    }
}
