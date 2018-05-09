package com.alef.jump;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import Logic.Constants;
import Logic.SendGetRequest;
import People.User;


public class Login extends AppCompatActivity {

    String TAG = "Login Activity";
    EditText etEmail,etPassword;
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail =  findViewById(R.id.etEmail);
        etPassword =  findViewById(R.id.etPassword);
        btnLogin =  findViewById(R.id.btnLogin);
        btnRegister =  findViewById(R.id.btnRegister);
    }

    public void onClickLogin(View v) {
        String url = Constants.getSelectUser() + "?email=" + etEmail.getText().toString() + "&password=" + etPassword.getText().toString();

        @SuppressLint("StaticFieldLeak") SendGetRequest sendGetRequest = new SendGetRequest(url) {
            @Override
            protected void onPostExecute(String response) {
                if(response != null) {
                    if (User.checkPassword(response)) {
                        JSONObject jsonObject = null;

                        User user = null;

                        try {
                            jsonObject = new JSONObject(response);
                            JSONObject jsonUser = (JSONObject) jsonObject.getJSONObject("user");
                            Log.e(TAG, jsonUser.toString());

                            user = new User(
                                    jsonUser.getString("id"),
                                    jsonUser.getString("email"),
                                    jsonUser.getString("name"),
                                    jsonUser.getString("lastname")
                            );

                            Globals g = Globals.getInstance();
                            g.setId(jsonUser.getInt("id"));
                            g.setEmail(jsonUser.getString("email"));
                            g.setName(jsonUser.getString("name"));
                            g.setLastName(jsonUser.getString("lastname"));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.e(TAG, user.toString());
                        Intent i = new Intent(getApplicationContext(), Feed.class);
                        i.putExtra("user", user);
                        startActivity(i);
                    } else {
                        try {
                            JSONObject jsonError = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonError.getString("mensaje"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"The json is not received",Toast.LENGTH_LONG).show();
                }
            }
        };

        sendGetRequest.execute();

    }

    public void onClickRegister(View v){
        Intent i = new Intent(getApplicationContext(),Register.class);
        startActivity(i);
    }
}
