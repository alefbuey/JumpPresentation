package Logic;

import android.os.AsyncTask;

public abstract class Prueba extends AsyncTask<String, Void, String> {



    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    @Override
    protected abstract void onPostExecute(String response);
}
