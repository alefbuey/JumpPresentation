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

        for (int i=1; i<4;i++){
            Fragment childFragment = new jobItem(i);
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.add(R.id.ll_containerJobs,childFragment).commit();
        }
    }

}
