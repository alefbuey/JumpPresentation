package com.alef.jump;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Logic.Constants;
import Logic.SendGetRequest;


public class JobContainer extends Fragment{



    public JobContainer() {
        // Required empty public constructor
    }


    public static JobContainer newInstance(String param1, String param2) {
        JobContainer fragment = new JobContainer();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_job_container, container, false);
    }


    @SuppressLint("ResourceType")
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


        @SuppressLint("StaticFieldLeak") SendGetRequest newReq = new SendGetRequest(Constants.getJobReadMultiple()+"?limit=10") {
            @Override
            protected void onPostExecute(String response) {
                if(response!=null) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        int index = 0;
                        for (int i = 0; i < jsonArray.length(); i++) {
                            index = jsonArray.getJSONArray(i).getInt(0);
                            Fragment childFragment = new jobItem(index);
                            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                            transaction.add(R.id.ll_containerJobs, childFragment).commit();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };


        newReq.execute();

    }

}
