package Logic;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.alef.jump.Feed;
import com.alef.jump.Profile;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.Serializable;

import People.User;

public class GetRequestUser extends SendGetRequest {

    private String TAG = "GetRequestUser";
    String email;
    int option; //option 1 = comprobar clave y enviar email
                //option 2 = comprobar email y enviar a objeto
    Context context;

    public void setOption(int option) {
        this.option = option;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GetRequestUser(String receiveUrl) {
        super(receiveUrl);
    }

    public GetRequestUser(String receiveUrl,Context context) {
        super(receiveUrl);
        this.context = context;
    }

    @Override
    protected void onPostExecute(String response) {

        switch (this.option){
            case 1: sendEmail(response); break;
            case 2: sendProfile(response); break;
        }


    }

    private void sendProfile(String response){
        if(User.checkPassword(response)){
//            JSONParser jsonParser = new JSONParser();
            Log.e(TAG,response);
            JSONObject jsonObject = null;

            User user = null;

            try {
//                jsonObject = (JSONObject) jsonParser.parse(response);
                jsonObject = new JSONObject(response);
                JSONObject jsonUser = (JSONObject) jsonObject.getJSONObject("user");
                JSONObject jsonUserStaff = (JSONObject) jsonObject.getJSONObject("userStaff");
                JSONObject jsonUserState = (JSONObject) jsonObject.getJSONObject("userState");
                JSONObject jsonUserNIType = (JSONObject) jsonObject.getJSONObject("userNIType");
                JSONObject jsonUserLocation = (JSONObject) jsonObject.getJSONObject("userLocation");
                JSONObject jsonUserPreferences = (JSONObject) jsonObject.getJSONObject("userPreferences");
                Log.e(TAG,jsonUser.toString());

                user = new User(
                        jsonUser.getString("id"),
                        jsonUser.getString("email"),
                        jsonUser.getString("name"),
                        jsonUser.getString("lastname"),
                        jsonUserLocation.getString("country")+" - "+jsonUserLocation.getString("city"),
                        jsonUserState.getString("state"),
                        jsonUserNIType.getString("description"),
                        jsonUser.getString("nationalidentifier"),
                        jsonUser.getString("birthdate"),
                        jsonUser.getString("direction"),
                        jsonUser.getString("gender"),
                        jsonUser.getString("nationality"),
                        jsonUser.getString("availablemoney"),
                        jsonUser.getString("rank"),
                        jsonUserPreferences.getString("preferences"),
                        jsonUserStaff.getString("about"),
                        jsonUserStaff.getString("cellphone")
                        );
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.e(TAG,user.toString());
            Intent i = new Intent(this.context,Profile.class);
            i.putExtra("user",user);
            this.context.startActivity(i);
        }else{
            Toast.makeText(this.context,"Incorrect Email or Password",Toast.LENGTH_LONG).show();
        }


    }

    private void sendEmail(String response) {
        if(User.checkPassword(response)){
            Intent i = new Intent(this.context,Feed.class);
            i.putExtra("email",this.email);
            this.context.startActivity(i);
        }else{
            Toast.makeText(this.context,"Incorrect Email or Password",Toast.LENGTH_LONG).show();
        }
    }



}
