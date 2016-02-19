package mahirsoft.diet.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.Calendar;

import mahirsoft.diet.R;

public class JadwalDietActivity extends AppCompatActivity {

    private TextView txtTitle;
    private TextView date;
    private TextView kalori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_jadwaldiet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
    }

    private void initView() {
        txtTitle = (TextView) findViewById(R.id.title);
        date = (TextView) findViewById(R.id.date);
        kalori = (TextView) findViewById(R.id.kalori);

        Calendar now = Calendar.getInstance();
        String month = "";
        switch (now.get(Calendar.MONTH)){
            case 0:
                month = " Januari ";
                break;
            case 1:
                month = " February ";
                break;
            case 2:
                month = " Maret ";
                break;
            case 3:
                month = " April ";
                break;
            case 4:
                month = " Mei ";
                break;
            case 5:
                month = " Juni ";
                break;
            case 6:
                month = " Juli ";
                break;
            case 7:
                month = " Agustus ";
                break;
            case 8:
                month = " September ";
                break;
            case 9:
                month = " Oktober ";
                break;
            case 10:
                month = " November ";
                break;
            case 11:
                month = " Desember ";
                break;
        }
        date.setText(now.get(Calendar.DAY_OF_MONTH)+month+now.get(Calendar.YEAR));
        txtTitle.setPaintFlags(txtTitle.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

}
