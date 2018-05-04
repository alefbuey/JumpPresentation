package Logic;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.alef.jump.Feed;

import People.User;

public class GetRequestUser extends SendGetRequest{

    String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public GetRequestUser(Context context, String receiveUrl) {
        super(context, receiveUrl);
    }

    @Override
    protected void onPostExecute(String response) {
        if(User.checkPassword(response)){
            Intent i = new Intent(this.context,Feed.class);
            i.putExtra("email",this.email);
            this.context.startActivity(i);
        }else{
            Toast.makeText(this.context,"Incorrect Email or Password",Toast.LENGTH_LONG).show();
        }

    }
}
