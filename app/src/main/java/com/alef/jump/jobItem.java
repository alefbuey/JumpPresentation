package com.alef.jump;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Logic.Constants;
import Logic.GetRequest;
import Logic.SendGetRequest;
import android.support.v4.app.Fragment;

public class jobItem extends android.support.v4.app.Fragment {

    String TAG = "jobItem";

    ImageView imPhotoProf, imShare, imAddFav;
    TextView tvProfileName, tvJobCost,tvJobName, tvNumDays;
    ImageView imPhotoJob;
    LinearLayout llNumVac, llListaCateg;

    boolean isFav = false;

    int id;
    int actividadActual;

    public jobItem (){}





    public static jobItem newInstance(int id, int actividad) {

        jobItem f = new jobItem();
        Bundle b = new Bundle();
        b.putInt("id",id);
        b.putInt("actividad",actividad);
        f.setArguments(b);
        return f;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            actividadActual = getArguments().getInt("actividad");
            id = getArguments().getInt("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_item, container, false);

        imPhotoProf = view.findViewById(R.id.im_photoProfile);
        tvProfileName = view.findViewById(R.id.tv_profileName);
        tvJobCost = view.findViewById(R.id.tv_jobCost);
        tvJobName = view.findViewById(R.id.tv_jobName);
        tvNumDays = view.findViewById(R.id.tv_numDaysAgo);
        imPhotoJob = view.findViewById(R.id.im_photoJob);
        imAddFav = view.findViewById(R.id.im_addFav);
        imShare = view.findViewById(R.id.im_share);
        llNumVac = view.findViewById(R.id.ll_numVac);
        llListaCateg = view.findViewById(R.id.ll_listCateg);

        String idUser = String.valueOf(Globals.getInstance().getId());
        String idJob = String.valueOf(id);
        String url = Constants.getJobCheckFavorite() +"?idUser="+idUser+"&idJob="+idJob;

        @SuppressLint("StaticFieldLeak") SendGetRequest sendGetRequest1 = new SendGetRequest(url) {
            @Override
            protected void onPostExecute(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int estado = jsonObject.getInt("estado");
                    if(estado ==1){
                        imAddFav.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_filled));
                        isFav = !isFav;
                    }else{

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        sendGetRequest1.execute();


        tvJobName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), JobView.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        imAddFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idUser = String.valueOf(Globals.getInstance().getId());
                String idJob = String.valueOf(id);
                Log.e(TAG,idJob);
                if(!isFav){
                    String url = Constants.getJobUpdateFavorite()+"?idUser="+idUser+"&idJob="+idJob+"&action=1";
                    Log.e(TAG,url);
                    @SuppressLint("StaticFieldLeak") SendGetRequest sendGetRequest = new SendGetRequest(url) {
                        @Override
                        protected void onPostExecute(String response) {
                            try {
                                Log.e(TAG,response);
                                JSONObject jsonObject = new JSONObject(response);
                                Log.e(TAG,jsonObject.toString());
                                int estado = jsonObject.getInt("estado");
                                String mensaje = jsonObject.getString("mensaje");
                                if( estado == 1) {
                                    imAddFav.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_filled));
                                    isFav = !isFav;
                                }
                                Toast.makeText(getActivity(),mensaje, Toast.LENGTH_LONG);

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getActivity(),"The response was not received",Toast.LENGTH_LONG);
                            }
                        }
                    };

                    sendGetRequest.execute();

                } else {

                    String url = Constants.getJobUpdateFavorite()+"?idUser="+idUser+"&idJob="+idJob+"&action=2";
                    @SuppressLint("StaticFieldLeak") SendGetRequest sendGetRequest = new SendGetRequest(url) {
                        @Override
                        protected void onPostExecute(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int estado = jsonObject.getInt("estado");
                                String mensaje = jsonObject.getString("mensaje");
                                if( estado == 1) {
                                    imAddFav.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite));
                                    isFav = !isFav;
                                }
                                if( actividadActual == 2){
                                    getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.ll_containerJobs)).commit();
                                }
                                    Toast.makeText(getActivity(),mensaje, Toast.LENGTH_LONG);

                                } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getActivity(),"The response was not received",Toast.LENGTH_LONG);
                            }


                        }
                    };
                    sendGetRequest.execute();
                }

            }
        });

        imShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "No social networks available",
                        Toast.LENGTH_LONG).show();
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


                        tvProfileName.setText(dataUser.getString("name")+ " " + dataUser.getString("lastname"));
                        tvJobName.setText(dataJob.getString("title"));
                        tvJobCost.setText(dataJob.getString("jobcost"));
                        tvNumDays.setText(dataJob.getString("dateposted"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        };

        if(id > 0){
            sendGetRequest.execute();
            return view;
        }else{
            return view;
        }

    }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */



}
