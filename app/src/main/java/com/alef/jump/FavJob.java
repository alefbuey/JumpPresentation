package com.alef.jump;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FavJob.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FavJob#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavJob extends Fragment {

    public FavJob() {
        // Required empty public constructor
    }

    public static FavJob newInstance(String param1, String param2) {
        FavJob fragment = new FavJob();

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
        return inflater.inflate(R.layout.fragment_fav_job, container, false);
    }


}
