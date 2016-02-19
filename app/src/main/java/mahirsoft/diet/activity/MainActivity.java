package mahirsoft.diet.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import mahirsoft.diet.R;
import mahirsoft.diet.fragment.AboutFragment;
import mahirsoft.diet.fragment.HomeFragment;
import mahirsoft.diet.fragment.JadwalFragment;
import mahirsoft.diet.fragment.ProfileFragment;
import mahirsoft.diet.utils.DataPref;

public class MainActivity extends AppCompatActivity {

    private Fragment currentFragment;
    private String nameProfile = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameProfile = DataPref.getNama(this);
        if (nameProfile.length() > 0) {
            selectItem(0);
        } else {
            selectItem(1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.profile:
                selectItem(1);
                break;
            case R.id.set_jadwal:
                selectItem(2);
                break;
            case R.id.about:
                selectItem(3);
                break;
            case R.id.close:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void selectItem(int position) {
        switch (position) {
            case 0:
                currentFragment = new HomeFragment();
                break;
            case 1:
                currentFragment = new ProfileFragment();
                break;
            case 2:
                currentFragment = new JadwalFragment();
                break;
            case 3:
                currentFragment = new AboutFragment();
                break;
            case 4:
                currentFragment = new HomeFragment();
                Toast.makeText(this, "Data Serapan", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                currentFragment = new HomeFragment();
                Toast.makeText(this, "Kalori", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                currentFragment = new HomeFragment();
                Toast.makeText(this, "Jadwal", Toast.LENGTH_SHORT).show();
                break;
            case 7:
                currentFragment = new HomeFragment();
                Toast.makeText(this, "Panduan", Toast.LENGTH_SHORT).show();
                break;
        }
        getSupportFragmentManager().beginTransaction().add(R.id.container, currentFragment).commit();
    }

    public void onClickDataSerapan(View view) {
        selectItem(4);
    }

    public void onClickKalori(View view) {
        selectItem(5);
    }

    public void onCLickJadwal(View view) {
        selectItem(6);
    }

    public void onClickPanduan(View view) {
        selectItem(7);
    }
}
