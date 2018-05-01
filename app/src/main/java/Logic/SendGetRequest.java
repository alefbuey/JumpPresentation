package Logic;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.alef.jump.Feed;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import People.User;

public class SendGetRequest extends AsyncTask<String, Void, String> {

    private static final String TAG = SendGetRequest.class.getSimpleName();


    Context context;
    String receiveUrl;

    public SendGetRequest(Context context, String receiveUrl) {
        this.context = context;
        this.receiveUrl = receiveUrl;
    }
    @Override
    protected String doInBackground(String... strings) {
        String response = null;
        int code = 0;
        try {
            URL url = new URL(this.receiveUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            code = conn.getResponseCode();
            if(code==HttpURLConnection.HTTP_OK){
                // read the response
                InputStream in = new BufferedInputStream(conn.getInputStream());
                response = convertStreamToString(in);
            }

        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }

    @Override
    protected void onPostExecute(String response) {
        if(User.checkPassword(response)){
            Intent i = new Intent(this.context,Feed.class);
            //i.putExtra("email",etEmail.getText().toString());
            this.context.startActivity(i);
        }else{
            Toast.makeText(this.context,"Incorrect Email or Password",Toast.LENGTH_LONG).show();
        }

    }

    private String convertStreamToString(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}