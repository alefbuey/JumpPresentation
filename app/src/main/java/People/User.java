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
    private String image;
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

    public String getImage() {
        return image;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getNonce() {
        return nonce;
    }

    public User(String id, String email, String name, String lastname) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
    }

    public User(String id, String email, String name, String lastname, String location, String state, String typeNationalIdentifier, String nationalIdentifier, String birthDate, String direction, String gender, String nationality, String availableAmount, String rank, String preferences, String about, String cellphone) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.location = location;
        this.state = state;
        this.typeNationalIdentifier = typeNationalIdentifier;
        this.nationalIdentifier = nationalIdentifier;
        this.birthDate = birthDate;
        this.direction = direction;
        this.gender = gender;
        this.nationality = nationality;
        this.availableAmount = availableAmount;
        this.rank = rank;
        this.preferences = preferences;
        this.about = about;
        this.cellphone = cellphone;
    }

    public static boolean checkPassword(String userJSON){
        int estado = 0;
        boolean existeJSON = false;
        try{
            JSONObject json = new JSONObject(userJSON);
            estado = json.getInt("estado");
        }catch (Exception e) {
            Log.e(TAG + "->REVISANDO JSON: ", e.getMessage().toString());
        }
        if(estado==1){
            existeJSON = true;
        }
        return existeJSON;
    }


}
