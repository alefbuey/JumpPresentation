package com.alef.jump;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import Logic.Constants;
import Logic.GetRequestUser;
import People.User;

public class Feed extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, jobItem.OnFragmentInteractionListener{


    LinearLayout container;
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };



    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        mTextMessage = findViewById(R.id.message);
        BottomNavigationView bottomBar = findViewById(R.id.navigation);
        bottomBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView lateralBar = findViewById(R.id.nav_view);
        lateralBar.setNavigationItemSelectedListener(this);


        //Generar jobItems

        container = findViewById(R.id.ll_container);

        LinearLayout pseudoCont = new LinearLayout(this);
        pseudoCont.setOrientation(LinearLayout.VERTICAL);
        pseudoCont.setId(12345);

        getFragmentManager().beginTransaction().add(pseudoCont.getId(), jobItem.newInstance(1), "someTag1").commit();
        getFragmentManager().beginTransaction().add(pseudoCont.getId(), jobItem.newInstance(2), "someTag2").commit();
        getFragmentManager().beginTransaction().add(pseudoCont.getId(), jobItem.newInstance(3), "someTag2").commit();

        container.addView(pseudoCont);




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.feed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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
            String url = Constants.getSelectUserProfile() + "?email="+user.getEmail();
            GetRequestUser getRequestUser = new GetRequestUser(url,getApplicationContext());
            getRequestUser.setOption(2);
            getRequestUser.execute();

        } else if (id == R.id.nav_myJobs) {

        } else if (id == R.id.nav_myBusiness) {

        } else if (id == R.id.nav_addJob) {
            Intent intent = new Intent(getApplicationContext(), AddJob.class);
            startActivity(intent);

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
