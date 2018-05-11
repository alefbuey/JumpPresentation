package com.alef.jump;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import Logic.Constants;
import Logic.SendGetRequest;

public class ListApplicants extends AppCompatActivity {

    int idJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_applicants);

        Bundle extras = getIntent().getExtras();
        idJob = extras.getInt("idJob");


        String url = Constants.getApplicants() + "?idjob="+idJob;

        if(url!=null) {

            @SuppressLint("StaticFieldLeak") SendGetRequest newReq = new SendGetRequest(url) {
                @Override
                protected void onPostExecute(String response) {
                    if (response != null) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            int index;
                            for (int i = 0; i < jsonArray.length(); i++) {
                                index = jsonArray.getJSONArray(i).getInt(0);
                                Log.d("NUMERO DE ID", String.valueOf(index));
                                Fragment childFragment = applicantItem.newInstance(index,idJob);
                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                transaction.add(R.id.ll_containerApplicants, childFragment).commit();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };

            newReq.execute();

        }else{
            Log.e("TAG","Not established URL");
        }


    }
}
