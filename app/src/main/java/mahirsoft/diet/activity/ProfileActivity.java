package mahirsoft.diet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.json.JSONArray;

import mahirsoft.diet.R;
import mahirsoft.diet.fragment.ProfileFragment;
import mahirsoft.diet.utils.DataPref;
import mahirsoft.diet.utils.Utils;

public class ProfileActivity extends AppCompatActivity {
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = DataPref.getNama(this);
        if (name.length() > 0) {
            enterApp();
        } else {
            ProfileFragment fragment = ProfileFragment.newInstance(true);
            getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).commit();
        }
    }

    private void enterApp(){
        startActivity(new Intent(this, DietSehatActivity.class));
        finish();
    }


}
