package Logic;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.widget.Toast;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class SendPostRequest extends AsyncTask<String, Void, Integer> {

    String receiveUrl;
    JSONObject receiveJSON;

    String mensaje; //Mensaje de confirmacion, si desea ponerlo

    public SendPostRequest(String receiveUrl, JSONObject receiveJSON) {
        this.receiveUrl = receiveUrl;
        this.receiveJSON = receiveJSON;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    protected void onPreExecute(){}

    protected Integer doInBackground(String... arg0) {

        int response;
        try {

            URL url = new URL(this.receiveUrl); // here is your URL path
            String message = this.receiveJSON.toString();

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(10000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setFixedLengthStreamingMode(message.getBytes().length);
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            conn.connect();

            OutputStream os = new BufferedOutputStream(conn.getOutputStream());;
            os.write(message.getBytes());
            os.close();

            InputStream in = new BufferedInputStream(conn.getInputStream());
            //response = convertStreamToString(in);
            response = 0;
            conn.disconnect();


        } catch (IOException e) {
//            response = "Exception: " + e.getMessage();
            response = 1;
        }

        return response;
    }

    @Override
    protected abstract void onPostExecute(Integer respuesta);


    @NonNull
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
