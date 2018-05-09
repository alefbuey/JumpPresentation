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
        View fragmentView = inflater.inflate(R.layout.fragment_profile_general, container, false);
        //Traer el usuario
        User user = (User) getArguments().getSerializable("user");

        //Identificar atributos
        tvAbout = (TextView) fragmentView.findViewById(R.id.tvAbout);
        tvLocation = (TextView) fragmentView.findViewById(R.id.tvLocation);
        tvNationality = (TextView) fragmentView.findViewById(R.id.tvNationality);
        tvBirthDate = (TextView) fragmentView.findViewById(R.id.tvBirthDate);
        tvDirection = (TextView) fragmentView.findViewById(R.id.tvDirection);
        tvAvailableMoney = (TextView) fragmentView.findViewById(R.id.tvAvailableMoney);
        tvPreferences = (TextView) fragmentView.findViewById(R.id.tvPreferences);

        //Asignar valores
        tvAbout.setText(user.getAbout());
        tvLocation.setText(user.getLocation());
        tvNationality.setText(user.getNationality());
        tvBirthDate.setText(user.getBirthDate());
        tvDirection.setText(user.getDirection());
        tvAvailableMoney.setText(user.getAvailableAmount());
        tvPreferences.setText(user.getPreferences());


        // Inflate the layout for this fragment
        return fragmentView;
    }

}
