package com.alef.jump;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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

    String TAG = "Job Container";

    int actividadActual;

    public JobContainer() {
        // Required empty public constructor
    }


    public static JobContainer newInstance(int actividad) {
        JobContainer fragment = new JobContainer();
        Bundle args = new Bundle();
        args.putInt("actividad",actividad);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            actividadActual = getArguments().getInt("actividad");
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment_view = inflater.inflate(R.layout.fragment_job_container, container, false);


        //   Definir en que actividad se va a realizar actividad
        //1. Feed
        //2. Favoritos
        //3. Current My Jobs
        //4. Applying MyJobs
        //5. History MyJobs
        //6. Current My Bussines
        //7. Posted MyBussines
        //8. History MyBussines

        //Aqui van los nuevos urls
        String url= null;

        switch (this.actividadActual){
            case 1: url = Constants.getJobReadMultiple()+"?limit=10"; break;
            case 2: break;
            case 3: break;
            case 4: break;
            case 5: break;
            case 6: break;
        }

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
        }else{
            Log.e(TAG,"Not established URL");
        }

        return fragment_view;
    }


}
