package com.alef.jump;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import Logic.Constants;
import Logic.GetRequestUser;
import People.User;

public class Profile extends AppCompatActivity {

    TabLayout tlProfile;
    ViewPager vpProfile;

    //Variables de informacion
    ImageView ivProfile;
    TextView tvName, tvLastName, tvRank;
    User user;

    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);

        vpProfile = (ViewPager) findViewById(R.id.vpProfile);
        setupViewPager(vpProfile);

        tlProfile = (TabLayout) findViewById(R.id.tlProfile);
        tlProfile.setupWithViewPager(vpProfile);

        //Variables de informacion
        user = (User) getIntent().getSerializableExtra("user");
        tvName = (TextView) findViewById(R.id.tvName);
        tvLastName = (TextView) findViewById(R.id.tvLastName);
        tvRank = (TextView) findViewById(R.id.tvRank);
        //SetText
        tvName.setText(user.getName());
        tvLastName.setText(user.getLastname());
        tvRank.setText(user.getRank());
        ivProfile = (ImageView) findViewById(R.id.ivProfile);
    }

    //Menu desplegable
    //implementar


    //Camara
    static final int REQUEST_IMAGE_CAPTURE = 1;

    public void onClickPhoto(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivProfile.setImageBitmap(imageBitmap);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        ProfileGeneral profile = new ProfileGeneral();
        sendInformation(profile);
        adapter.addFragment(profile, "General");
        adapter.addFragment(new ProfileMyJobs(), "My jobs");
        adapter.addFragment(new ProfileMyBusiness(), "Business");
        viewPager.setAdapter(adapter);
    }

    public void sendInformation(ProfileGeneral profileGeneral){
        Bundle bundle = new Bundle();
        User user = (User) getIntent().getSerializableExtra("user");
        bundle.putSerializable("user",user);
        profileGeneral.setArguments(bundle);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
