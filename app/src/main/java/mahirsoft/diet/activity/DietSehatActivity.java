package mahirsoft.diet.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import mahirsoft.diet.R;
import mahirsoft.diet.fragment.AboutFragment;
import mahirsoft.diet.fragment.HomeFragment;
import mahirsoft.diet.fragment.JadwalFragment;
import mahirsoft.diet.fragment.ProfileFragment;

public class DietSehatActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Fragment currentFragment;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_sehat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        onNavigationItemSelected(navigationView.getMenu().getItem(0));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
                onNavigationItemSelected(navigationView.getMenu().getItem(1));
                break;
            case R.id.set_jadwal:
                onNavigationItemSelected(navigationView.getMenu().getItem(2));
                break;
            case R.id.about:
                onNavigationItemSelected(navigationView.getMenu().getItem(3));
                break;
            case R.id.close:
                exitApp();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            selectItem(0);
        } else if (id == R.id.nav_profile) {
            selectItem(1);
        } else if (id == R.id.nav_jadwal) {
            selectItem(2);
        } else if (id == R.id.nav_about) {
            selectItem(3);
        } else if (id == R.id.nav_exit) {
            exitApp();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void selectItem(int position) {
        switch (position) {
            case 0:
                currentFragment = new HomeFragment();
                break;
            case 1:
                currentFragment = ProfileFragment.newInstance(false);
                break;
            case 2:
                currentFragment = JadwalFragment.newInstance();
                break;
            case 3:
                currentFragment = AboutFragment.newInstance();
                break;
            case 4:
                exitApp();
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

    private void exitApp() {
        finish();
    }
}
