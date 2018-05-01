package People;

//import java.awt.Image;
import android.util.Log;

import org.json.JSONObject;

import java.text.SimpleDateFormat;

public class User {

    private static final String TAG = User.class.getSimpleName();

    private int id;
    private String location;
    private String state;
    private String nationalID;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private SimpleDateFormat birthDate;
    private String address;
    private char gender;
    private String nationality;
    private float availableAmount;
    private String about;
    //private Image photo;
    private String phone;
    private String preferences;

    public static boolean checkPassword(String userJSON){
        int estado = 0;
        boolean existeJSON = false;
        try{
            JSONObject json = new JSONObject(userJSON);
            estado = json.getInt("estado");
        }catch (Exception e) {
            Log.d(TAG + "->REVISANDO JSON: ", e.getMessage().toString());
        }
        if(estado==1){
            existeJSON = true;
        }
        return existeJSON;
    }

}
