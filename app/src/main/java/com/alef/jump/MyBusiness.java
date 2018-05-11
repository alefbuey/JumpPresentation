package com.alef.jump;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MyBusiness extends AppCompatActivity {

    String TAG = "MyBusiness";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_business);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.ngt_MyBusiness);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Inicializar el primer fragmento
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.ll_containerFrag,JobContainer.newInstance(6));
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
                    ft.replace(R.id.ll_containerFrag,JobContainer.newInstance(6));
                    ft.commit();
                    return true;
                case R.id.navigation_posted:
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.ll_containerFrag, JobContainer.newInstance(7));
                    ft.commit();
                    return true;
                case R.id.navigation_history:
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.ll_containerFrag, JobContainer.newInstance(8));
                    ft.commit();
                    return true;
            }
            return false;
        }
    };
}
