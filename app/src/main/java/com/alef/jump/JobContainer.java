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
    //String idUser;

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
        //6. In Course My Bussines
        //7. Posted MyBussines
        //8. Finished MyBussines

        //Aqui van los nuevos urls

        //estados de MyJobs
        //1. Applying
        //2. Working o Current
        //3. Done or History
        String url= null;
//        Log.e(TAG,getArguments().getString("idUser"));
        String idUser = String.valueOf(Globals.getInstance().getId());
        Log.e(TAG,idUser);
        switch (this.actividadActual){
            case 1: url = Constants.getJobReadMultiple()+"?limit=10"; break;
            case 2: url = Constants.getJobMyFovoriteJobs()+"?idUser="+idUser+"&limit=10";break;
            case 3: url = Constants.getJobMyJobs()+"?idUser="+ idUser +"&state=2&limit=10";break;
            case 4: url = Constants.getJobMyJobs()+"?idUser="+ idUser +"&state=1&limit=10";break;
            case 5: url = Constants.getJobMyJobs()+"?idUser="+ idUser +"&state=3&limit=10";break;
            case 6: url = Constants.getJobMyBusiness()+"?idUser="+idUser + "&state1=3&limit=10";break;
            case 7: url = Constants.getJobMyBusiness()+"?idUser="+idUser + "&state1=1&state2=2&limit=10";break;
            case 8: url = Constants.getJobMyBusiness()+"?idUser="+idUser + "&state1=4&state2=5&limit=10";break;


        }

        if(url!=null) {

            @SuppressLint("StaticFieldLeak") SendGetRequest newReq = new SendGetRequest(url) {
                @Override
                protected void onPostExecute(String response) {
                    if (response != null) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            Log.e(TAG,jsonArray.toString());
                            int index = 0;
                            for (int i = 0; i < jsonArray.length(); i++) {
                                index = jsonArray.getJSONArray(i).getInt(0);
                                Fragment childFragment = jobItem.newInstance(index,actividadActual);
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
