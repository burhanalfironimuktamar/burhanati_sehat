package mahirsoft.diet.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import mahirsoft.diet.R;

public class DataSerapanActivity extends AppCompatActivity {

    private TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_serapan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
    }

    private void initView() {
        txtTitle = (TextView) findViewById(R.id.title_data_serapan);
        txtTitle.setPaintFlags(txtTitle.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

}
