package Work;

import android.icu.text.SimpleDateFormat;

import java.util.ArrayList;

import People.User;
import Work.AbstractComment;

public class ParentComment extends AbstractComment {
    private ArrayList<ChildComment> childComments;

    //Constructor
    public ParentComment(int id, User user, String description, SimpleDateFormat datePosted, ArrayList<ChildComment> childComments) {
        super(id, user, description, datePosted);
        this.childComments = childComments;
    }

    //Getters and setters
    public ArrayList<ChildComment> getChildComments() {
        return childComments;
    }

    public void setChildComments(ArrayList<ChildComment> childComments) {
        this.childComments = childComments;
    }


    //Methods
    public void addComment(){

    }

    public void removeComment(){
    }

}
