package mahirsoft.diet.activity;

import android.database.Cursor;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import mahirsoft.diet.R;
import mahirsoft.diet.adapter.SerapanAdapter;
import mahirsoft.diet.data.JadwalDiet;

public class JadwalDietActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private TextView txtTitle;
    private TextView date;
    private TextView kalori;
    private RecyclerView list;
    private SerapanAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_jadwaldiet);
        getSupportLoaderManager().initLoader(0, null, this);
        initView();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtTitle = (TextView) findViewById(R.id.title);
        date = (TextView) findViewById(R.id.date);
        kalori = (TextView) findViewById(R.id.kalori);
        list = (RecyclerView) findViewById(R.id.list);

        listAdapter = new SerapanAdapter(this);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(listAdapter);

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

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, JadwalDiet.CONTENT_URI, JadwalDiet.COLUMNS, JadwalDiet.COLUMN_DATE+"='"+new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime())+"'", null, JadwalDiet.TABLE_NAME+"."+JadwalDiet._ID+" ASC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        int sumKalori = 0;
        while (data.moveToNext()){
            sumKalori += data.getInt(data.getColumnIndexOrThrow(JadwalDiet.COLUMN_KALORI));
        }
        kalori.setText("Jumlah kalori untuk hari ini adalah "+sumKalori);
        listAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
