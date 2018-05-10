package Logic;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;



public abstract class GetRequest {

    public void getJsonObject(Context context, String url) {
        // Petición GET
        VolleySingleton.
                getInstance(context).
                addToRequestQueue(
                        new JsonObjectRequest(
                                Request.Method.GET,
                                url,
                                null,
                                new Response.Listener<JSONObject>() {

                                    @Override
                                    public void onResponse(JSONObject response) {
                                        // Procesar la respuesta Json
                                        procesarRespuesta(response);
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Log.d(VolleyLog.TAG, "Error Volley: " + error.getMessage());
                                    }
                                }

                        )
                );
    }

    public void getJsonArray(Context context, String url) {
        // Petición GET
        VolleySingleton.
                getInstance(context).
                addToRequestQueue(
                        new JsonArrayRequest(
                                Request.Method.GET,
                                url,
                                null,
                                new Response.Listener<JSONArray>() {

                                    @Override
                                    public void onResponse(JSONArray response) {
                                        // Procesar la respuesta Json
                                        procesarRespuesta(response);
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Log.d(VolleyLog.TAG, "Error Volley: " + error.getMessage());
                                    }
                                }

                        )
                );
    }


    public abstract void procesarRespuesta(JSONObject jsonObject);
    public abstract void procesarRespuesta(JSONArray jsonArray);

    public interface getRequestInterface {
        void procesarRespuesta(JSONObject jsonObject);
    }
}