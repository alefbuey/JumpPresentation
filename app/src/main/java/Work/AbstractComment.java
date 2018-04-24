package Work;

import android.icu.text.SimpleDateFormat;

import People.User;

public abstract class AbstractComment {
    private int id;
    private User user;
    private String description;
    private SimpleDateFormat datePosted;


    //Constructor
    public AbstractComment(int id, User user, String description, SimpleDateFormat datePosted) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.datePosted = datePosted;
    }

    //Getters and setters

}
