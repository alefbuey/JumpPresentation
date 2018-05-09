package com.alef.jump;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import People.User;

public class Feed extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomBar = findViewById(R.id.navigation);
        bottomBar.setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener);
        
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView lateralBar = findViewById(R.id.nav_view);
        lateralBar.setNavigationItemSelectedListener(this);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.ll_containerFrag, new JobContainer());
        ft.commit();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.feed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent intent = new Intent(getApplicationContext(),Profile.class);
            startActivity(intent);
        }else if (id == R.id.nav_myJobs) {
        }else if (id == R.id.nav_myBusiness) {
        }else if (id == R.id.nav_addJob) {
            Intent intent = new Intent(getApplicationContext(), AddJob.class);
            startActivity(intent);
        }else if (id == R.id.nav_settings) {
        }else if (id == R.id.nav_logout) {
        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    private BottomNavigationView.OnNavigationItemSelectedListener OnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction ft;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.ll_containerFrag, new JobContainer());
                    ft.commit();
                    return true;
                case R.id.navigation_favorites:
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.ll_containerFrag, new FavJob());
                    ft.commit();
                    return true;
                case R.id.navigation_notifications:
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.ll_containerFrag, new Notifications());
                    ft.commit();
                    return true;
                case R.id.navigation_messages:
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.ll_containerFrag, new Messages());
                    ft.commit();
                    return true;

            }
            return false;
        }
    };

}
