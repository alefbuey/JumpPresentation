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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import Logic.Constants;
import Logic.SendGetRequest;
import People.User;

public class Feed extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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
        ft.replace(R.id.ll_containerFrag, JobContainer.newInstance(1));
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

            User user = (User) getIntent().getSerializableExtra("user");
            String url = Constants.getSelectUserProfile() + "?email=" + user.getEmail();

            @SuppressLint("StaticFieldLeak") SendGetRequest sendGetRequest = new SendGetRequest(url) {
                @Override
                protected void onPostExecute(String response) {
                    if (response != null) {
                        JSONObject jsonObject = null;

                        User userToPass = null;

                        try {
                            jsonObject = new JSONObject(response);
                            JSONObject jsonUser = (JSONObject) jsonObject.getJSONObject("user");
                            JSONObject jsonUserStaff = (JSONObject) jsonObject.getJSONObject("userStaff");
                            JSONObject jsonUserState = (JSONObject) jsonObject.getJSONObject("userState");
                            JSONObject jsonUserNIType = (JSONObject) jsonObject.getJSONObject("userNIType");
                            JSONObject jsonUserLocation = (JSONObject) jsonObject.getJSONObject("userLocation");
                            JSONObject jsonUserPreferences = (JSONObject) jsonObject.getJSONObject("userPreferences");
                            Log.e("TAG", jsonUser.toString());

                            userToPass = new User(
                                    jsonUser.getString("id"),
                                    jsonUser.getString("email"),
                                    jsonUser.getString("name"),
                                    jsonUser.getString("lastname"),
                                    jsonUserLocation.getString("country") + " - " + jsonUserLocation.getString("city"),
                                    jsonUserState.getString("state"),
                                    jsonUserNIType.getString("description"),
                                    jsonUser.getString("nationalidentifier"),
                                    jsonUser.getString("birthdate"),
                                    jsonUser.getString("direction"),
                                    jsonUser.getString("gender"),
                                    jsonUser.getString("nationality"),
                                    jsonUser.getString("availablemoney"),
                                    jsonUser.getString("rank"),
                                    jsonUserPreferences.getString("preferences"),
                                    jsonUserStaff.getString("about"),
                                    jsonUserStaff.getString("cellphone")
                            );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.e("TAG", userToPass.toString());
                        Intent i = new Intent(getApplicationContext(), Profile.class);
                        i.putExtra("user", userToPass);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed Connection", Toast.LENGTH_LONG).show();
                    }
                }
            };


            sendGetRequest.execute();


        } else if (id == R.id.nav_myJobs) {
            Intent intent = new Intent(getApplicationContext(),MyJobs.class);
            startActivity(intent);
        } else if (id == R.id.nav_myBusiness) {

        } else if (id == R.id.nav_addJob) {
            Intent intent = new Intent(getApplicationContext(), AddJob.class);
            startActivity(intent);
        } else if (id == R.id.nav_settings) {
        } else if (id == R.id.nav_logout) {
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
                    ft.replace(R.id.ll_containerFrag, JobContainer.newInstance(1));
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
