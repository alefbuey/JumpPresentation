package com.alef.jump;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import People.User;


public class ProfileGeneral extends Fragment {

    TextView tvAbout,tvLocation, tvNationality, tvBirthDate,tvDirection, tvAvailableMoney, tvPreferences;

    public ProfileGeneral() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Traer el usuario
        User user = (User) getArguments().getSerializable("user");

        //Identificar atributos
        tvAbout = (TextView) getView().findViewById(R.id.tvAbout);
        tvLocation = (TextView) getView().findViewById(R.id.tvLocation);
        tvNationality = (TextView) getView().findViewById(R.id.tvNationality);
        tvBirthDate = (TextView) getView().findViewById(R.id.tvBirthDate);
        tvDirection = (TextView) getView().findViewById(R.id.tvDirection);
        tvAvailableMoney = (TextView) getView().findViewById(R.id.tvAvailableMoney);
        tvPreferences = (TextView) getView().findViewById(R.id.tvPreferences);

        //Asignar valores
        tvBirthDate.setText(user.getBirthDate());
        tvDirection.setText(user.getDirection());


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_general, container, false);
    }

}
