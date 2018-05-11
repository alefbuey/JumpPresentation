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
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import People.User;

public class Profile extends AppCompatActivity {

    String TAG = "Profile";

    TabLayout tlProfile;
    ViewPager vpProfile;
    int modo;

    //Variables de informacion
    ImageView ivProfile,ivSetting;
    TextView tvName, tvLastName, tvRank,tvPreferences,tvLocation;
    User user;
    ImageButton btnBack1, btnSettings;

    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);

        modo = getIntent().getIntExtra("modo",2);

        if(modo == 2){ //Perfil visto por otros
            btnSettings = findViewById(R.id.btn_Settings);
            btnSettings.setVisibility(View.GONE);
            //ivProfile.setClickable(false); //NOTA MENTA HACER QUE NO TOME FOTO EN ESTE CASO
        }

        vpProfile = (ViewPager) findViewById(R.id.vpProfile);
        setupViewPager(vpProfile);

        tlProfile = (TabLayout) findViewById(R.id.tlProfile);
        tlProfile.setupWithViewPager(vpProfile);

        //Variables de informacion
        tvRank = findViewById(R.id.tvRank);
        tvName = findViewById(R.id.tvName);
        tvLastName = findViewById(R.id.tvLastName);

        ivProfile = findViewById(R.id.ivProfile);

        //
        user = (User) getIntent().getSerializableExtra("user");

        tvRank.setText(user.getRank());
        tvName.setText(user.getName());
        tvLastName.setText(user.getLastname());


    }

    //Menu desplegable
    //implementar


    //Camara
    static final int REQUEST_IMAGE_CAPTURE = 1;

    public void onClickPhoto(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //takePictureIntent.putExtra("user",user);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void onClickSetting(View v) {
        User user = (User) getIntent().getSerializableExtra("user");
        Intent intent = new Intent(getApplicationContext(),ProfileSetting.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    public void onClickBack(View v) {
        finish();
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivProfile.setImageBitmap(imageBitmap);
        }
    }

    //ViewPager
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