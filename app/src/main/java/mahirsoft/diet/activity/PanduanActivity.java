package mahirsoft.diet.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import mahirsoft.diet.R;
import mahirsoft.diet.adapter.GuideAdapter;

public class PanduanActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panduan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        data = getResources().getStringArray(R.array.guide);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new GuideAdapter(this, data));
    }

}
