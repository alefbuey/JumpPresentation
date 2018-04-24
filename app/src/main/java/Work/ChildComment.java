package Work;

import android.icu.text.SimpleDateFormat;

import People.User;
import Work.AbstractComment;

public class ChildComment extends AbstractComment {

    public ChildComment(int id, User user, String description, SimpleDateFormat datePosted) {
        super(id, user, description, datePosted);
    }
}
