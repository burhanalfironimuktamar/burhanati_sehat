package mahirsoft.diet.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import org.json.JSONArray;

import java.util.List;

import mahirsoft.diet.R;
import mahirsoft.diet.data.Food;
import mahirsoft.diet.utils.DataPref;
import mahirsoft.diet.utils.Utils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (DataPref.getFirst(this)){
            new SaveData().execute();
        } else {
            startActivity(new Intent(SplashActivity.this, ProfileActivity.class));
            finish();
        }
    }

    private class SaveData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            DataPref.isFirst(false);
            startActivity(new Intent(SplashActivity.this, ProfileActivity.class));
            finish();
        }

        @Override
        protected Void doInBackground(Void... params) {
            JSONArray jsonArray = Utils.getData(SplashActivity.this);
            List<ContentValues> cv = Food.fromJSONArray(jsonArray);

            getContentResolver().delete(Food.CONTENT_URI, null, null);
            getContentResolver().bulkInsert(Food.CONTENT_URI, cv.toArray(new ContentValues[cv.size()]));
            return null;
        }
    }

}
