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
    String mensaje; //Mensaje de confirmacion, si desea ponerlo

    public SendPostRequest(Context context, String receiveUrl, JSONObject receiveJSON) {
        this.context = context;
        this.receiveUrl = receiveUrl;
        this.receiveJSON = receiveJSON;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    protected void onPreExecute(){}

    protected String doInBackground(String... arg0) {
        String respuesta;
        try {
            URL url = new URL(this.receiveUrl);             // La URL a la cual quieres enviar
            String message = this.receiveJSON.toString();   // El objeto en Json que quieres enviar

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setReadTimeout(15000 /* milliseconds */);
//            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setFixedLengthStreamingMode(message.getBytes().length);
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            conn.connect();

            OutputStream os = new BufferedOutputStream(conn.getOutputStream());;
            os.write(message.getBytes());
            os.flush();
            os.close();

            conn.disconnect();
            respuesta = this.mensaje;

        } catch (IOException e) {
            respuesta = "Exception: " + e.getMessage();
        }

        return respuesta;
    }

    @Override
    protected void onPostExecute(String respuesta) {
        if(respuesta!=null){
            Toast.makeText(this.context, respuesta, Toast.LENGTH_LONG).show();
        }
    }
}


