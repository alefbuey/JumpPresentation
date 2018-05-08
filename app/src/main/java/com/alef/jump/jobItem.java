package com.alef.jump;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
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

public class jobItem extends Fragment{

    ImageView imPhotoProf, imShare, imAddFav;
    TextView tvProfileName, tvJobCost,tvJobName, tvNumDays;
    ImageView imPhotoJob;
    LinearLayout llNumVac, llListaCateg;

    boolean isFav = false;

    int id = 0;
    private static final String EXTRA_ID = "IDMETA";


    public jobItem (){}



    @SuppressLint("ValidFragment")
    public jobItem (int id){
        this.id = id;


    }


    public static jobItem newInstance(int id) {

        jobItem f = new jobItem(id);

        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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


        tvJobName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), JobView.class);
                startActivity(intent);
            }
        });

        imAddFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFav)imAddFav.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_filled));
                else imAddFav.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite));
                isFav = !isFav;
            }
        });

        imShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "No social networks available",
                        Toast.LENGTH_LONG).show();
            }
        });


        GetRequest testReqJob = new GetRequest() {
            @Override
            public void procesarRespuesta(JSONObject jsonObject) {
                try {
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

            @Override
            public void procesarRespuesta(JSONArray jsonArray) {

            }
        };

        if(id > 0){
            testReqJob.getJsonObject(getActivity(), Constants.getJobRead()+"?id="+id);
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



}
