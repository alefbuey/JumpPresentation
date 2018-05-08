package People;

import android.media.Image;
import android.util.Log;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class User implements Serializable {

    private static final String TAG = User.class.getSimpleName();

    private String id;
    private String email;
    private String password;
    private String name;
    private String lastname;
    private String location;
    private String state;
    private String typeNationalIdentifier;
    private String nationalIdentifier;
    private String birthDate;
    private String direction;
    private String gender;
    private String nationality;
    private String availableAmount;
    private String rank;
    private String preferences;
    private String nonce;
    //Extras
    private String about;
    private String photopath;
    private String cellphone;


    public User() {
    }


    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getLocation() {
        return location;
    }

    public String getState() {
        return state;
    }

    public String getTypeNationalIdentifier() {
        return typeNationalIdentifier;
    }

    public String getNationalIdentifier() {
        return nationalIdentifier;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getDirection() {
        return direction;
    }

    public String getGender() {
        return gender;
    }

    public String getNationality() {
        return nationality;
    }

    public String getAvailableAmount() {
        return availableAmount;
    }

    public String getRank() {
        return rank;
    }

    public String getPreferences() {
        return preferences;
    }

    public String getAbout() {
        return about;
    }

    public String getPhotopath() {
        return photopath;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getNonce() {
        return nonce;
    }

    public User(String id, String name, String lastname, String email, String birthDate, String direction, String nationality, String availableAmount, String rank) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.birthDate = birthDate;
        this.direction = direction;
        this.nationality = nationality;
        this.availableAmount = availableAmount;
        this.rank = rank;
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
