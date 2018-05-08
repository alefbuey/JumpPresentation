package com.alef.jump;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JobView extends AppCompatActivity {

    ImageView im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_view);

        im = findViewById(R.id.pruebaFoto);
        Bitmap bm = getImage("/var/www/html/pruebaImg/index.php?id=3");
        im.setImageBitmap(Bitmap.createScaledBitmap(bm, 120, 120, false));

    }



    public static Bitmap getImage(String url){
        Bitmap img = null ;
        try {
            URL feedImage = new URL(url);
            HttpURLConnection conn= (HttpURLConnection)feedImage.openConnection();
            InputStream is = conn.getInputStream();
            img = BitmapFactory.decodeStream(is);

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return img ;
    }
}
