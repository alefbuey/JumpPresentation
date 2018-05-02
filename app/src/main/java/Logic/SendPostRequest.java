package Logic;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import org.json.JSONObject;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendPostRequest extends AsyncTask<String, Void, String> {

    Context context;
    String receiveUrl;
    JSONObject receiveJSON;

    public SendPostRequest(Context context, String receiveUrl, JSONObject receiveJSON) {
        this.context = context;
        this.receiveUrl = receiveUrl;
        this.receiveJSON = receiveJSON;
    }

    protected void onPreExecute(){}

    protected String doInBackground(String... arg0) {
        OutputStream os;
        HttpURLConnection conn;
        String mensaje;
        try {
            URL url = new URL(this.receiveUrl); // here is your URL path
            String message = this.receiveJSON.toString();

            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setFixedLengthStreamingMode(message.getBytes().length);
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            conn.connect();

            os = new BufferedOutputStream(conn.getOutputStream());;
            os.write(message.getBytes());
            os.flush();
            os.close();

            conn.disconnect();
            mensaje = "Creación Exitosa";

        } catch (IOException e) {
            mensaje = new String("Exception: " + e.getMessage());
        }

        return mensaje;
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(this.context, result,
                Toast.LENGTH_LONG).show();
    }
}