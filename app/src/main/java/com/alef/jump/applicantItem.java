package com.alef.jump;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import Logic.Constants;
import Logic.SendGetRequest;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link applicantItem#newInstance} factory method to
 * create an instance of this fragment.
 */
public class applicantItem extends Fragment {

    int idUser, idJob;

    JSONObject dataApply;


    TextView tvName, tvRanking;
    ImageView ivFlag;
    ImageButton ibtnInfor;

    public applicantItem() {
        // Required empty public constructor
    }


    public static applicantItem newInstance(int idUser, int idJob) {
        applicantItem fragment = new applicantItem();
        Bundle args = new Bundle();
        args.putInt("idUser",idUser);
        args.putInt("idJob",idJob);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idUser = getArguments().getInt("idUser");
            idJob = getArguments().getInt("idJob");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_applicant_item, container, false);

        tvName = view.findViewById(R.id.tv_applicantName);
        ivFlag = view.findViewById(R.id.iv_flag);
        tvRanking = view.findViewById(R.id.tv_ranking);
        ibtnInfor = view.findViewById(R.id.ibtn_info);


        ibtnInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AcceptApplicant.class);
                startActivity(intent);
            }
        });

        String url = Constants.getApplicantInfo() +"?idemployee="+idUser+"&idjob="+idJob;

        @SuppressLint("StaticFieldLeak") SendGetRequest sendGetRequest1 = new SendGetRequest(url) {
            @Override
            protected void onPostExecute(String response) {
                try {
                    if(response!= null) {

                        Log.d("response",response);
                        JSONObject jsonObject = new JSONObject(response);
                        dataApply = jsonObject.getJSONObject("applicationData");

                        JSONObject user = jsonObject.getJSONObject("employeeData");
                        String userName = user.getString("name") + " " + user.getString("lastname");
                        tvName.setText(userName);
                        tvRanking.setText(user.getString("rank"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        sendGetRequest1.execute();




        return view;
    }

}
