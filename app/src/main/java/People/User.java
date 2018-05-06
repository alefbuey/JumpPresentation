package People;

import android.media.Image;
import android.util.Log;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class User implements Serializable {

    private static final String TAG = User.class.getSimpleName();

    private String id;
    private String idlocation;
    private String idsate;
    private String typeNationalIdentifier;
    private String nationalIdentifier;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String birthDate;
    private String direction;
    private String gender;
    private String nationality;
    private String availableAmount;
    private String nonce;

    //Extras
    private String about;
    private String photopath;
    private String cellphone;
    private String image;


    public User() {
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public User(String id, String name, String lastname, String email, String birthDate, String direction, String nationality, String availableAmount) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.birthDate = birthDate;
        this.direction = direction;
        this.nationality = nationality;
        this.availableAmount = availableAmount;
    }

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
