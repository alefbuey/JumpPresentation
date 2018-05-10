package com.alef.jump;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MyJobs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_jobs);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.ngt_MyJobs);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.ll_containerFrag, JobContainer.newInstance(3));
        ft.commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction ft;
            switch (item.getItemId()) {
                case R.id.navigation_current:
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.ll_containerFrag, JobContainer.newInstance(3));
                    ft.commit();
                    return true;
                case R.id.navigation_applying:
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.ll_containerFrag, JobContainer.newInstance(4));
                    ft.commit();
                    return true;
                case R.id.navigation_history:
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.ll_containerFrag, JobContainer.newInstance(5));
                    ft.commit();
                    return true;
            }
            return false;
        }
    };


}
