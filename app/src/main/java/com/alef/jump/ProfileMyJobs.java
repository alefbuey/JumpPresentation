package com.alef.jump;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ProfileMyJobs extends Fragment {

    private static String TAG = "ProfileMyJOBS";

    private float[] rankData = {4.5f,4.2f,4.0f,3.7f};
    private int[] numeroDeTrabajosData = {7,6,2,1};
    private String[] jobsData = {"Programming","Graphic Design","Art","Teaching"};

    PieChart pcMyJobs;

    public ProfileMyJobs() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment ProfileMyJobs.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static ProfileMyJobs newInstance(String param1, String param2) {
//        ProfileMyJobs fragment = new ProfileMyJobs();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Description description = new Description();
        description.setTextColor(ColorTemplate.VORDIPLOM_COLORS[2]);
        description.setText("");


        pcMyJobs = (PieChart) getView().findViewById(R.id.pcMyJobs);
        pcMyJobs.setDescription(description);
        pcMyJobs.setRotationEnabled(true);
        pcMyJobs.setHoleRadius(50f);
        pcMyJobs.setTransparentCircleAlpha(10);
        pcMyJobs.setDrawEntryLabels(false);
        pcMyJobs.animateXY(1500, 1500);

        // Inflate the layout for this fragment
        addDataSet();

        pcMyJobs.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                int pos1 = e.toString().indexOf("y: ");
                String numeroTrabajos = e.toString().substring(pos1+3);

                int pos2 = 0;
                while(numeroDeTrabajosData[pos2] != Float.parseFloat(numeroTrabajos)){
                    pos2++;
                }

                float rank = rankData[pos2];
                String job = jobsData[pos2];
                Toast.makeText(getContext(), "Job: " + job + "\n" + "Rank: " + rank, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_my_jobs, container, false);
    }

    private void addDataSet() {
        Log.d(TAG, "add set started");
        ArrayList<PieEntry> Entries = new ArrayList<>();

        for (int i = 0; i < jobsData.length; i++) {
            Entries.add(new PieEntry(numeroDeTrabajosData[i], jobsData[i]));
        }

        //creacion de data set
        PieDataSet pieDataSet = new PieDataSet(Entries,"<-Jobs");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //Añadir colores
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);
        pieDataSet.setColors(colors);
        PieData pieData = new PieData(pieDataSet);
        pcMyJobs.setData(pieData);
        pcMyJobs.invalidate();
    }

}
