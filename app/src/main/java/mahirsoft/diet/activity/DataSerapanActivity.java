package mahirsoft.diet.activity;

import android.content.ContentValues;
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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import mahirsoft.diet.R;
import mahirsoft.diet.adapter.SerapanAdapter;
import mahirsoft.diet.data.Food;
import mahirsoft.diet.data.Serapan;

public class DataSerapanActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor>{

    private TextView txtTitle;
    private AutoCompleteTextView autoComplete;
    private Button btnInsert;
    private Button btnReset;
    private RecyclerView list;
    private SerapanAdapter listAdapter;
    private ArrayAdapter<String> autoCompleteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_serapan);
        getSupportLoaderManager().initLoader(0, null, this);
        initView();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtTitle = (TextView) findViewById(R.id.title_data_serapan);
        autoComplete = (AutoCompleteTextView) findViewById(R.id.autoComplete);
        btnInsert = (Button) findViewById(R.id.btn_insert);
        btnReset = (Button) findViewById(R.id.btn_reset);
        list = (RecyclerView) findViewById(R.id.list);

        Cursor foodCursor = getContentResolver().query(Food.CONTENT_URI, new String[]{Food.COLUMN_NAME}, null, null, null);
        String[] foods = new String[foodCursor.getCount()];
        int i = 0;
        while (foodCursor.moveToNext()){
            foods[i] = foodCursor.getString(foodCursor.getColumnIndexOrThrow(Food.COLUMN_NAME));
            i++;
        }
        foodCursor.close();
        autoCompleteAdapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, foods);
        autoComplete.setAdapter(autoCompleteAdapter);
        autoComplete.setThreshold(1);

        listAdapter = new SerapanAdapter(this);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(listAdapter);

        txtTitle.setPaintFlags(txtTitle.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        btnReset.setOnClickListener(this);
        btnInsert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnReset){
            getContentResolver().delete(Serapan.CONTENT_URI, null, null);
        }else if (v == btnInsert){
            insert();
        }
    }

    private void insert(){
        String food = autoComplete.getText().toString();
        Cursor cursor = getContentResolver().query(Food.CONTENT_URI, Food.COLUMNS, Food.COLUMN_NAME+"='"+food+"'", null, null);
        while (cursor.moveToNext()){
            ContentValues cv = new ContentValues();
            cv.put(Serapan.COLUMN_NAME, cursor.getString(cursor.getColumnIndexOrThrow(Food.COLUMN_NAME)));
            cv.put(Serapan.COLUMN_GOLONGANDARAH, cursor.getString(cursor.getColumnIndexOrThrow(Food.COLUMN_GOLONGANDARAH)));
            cv.put(Serapan.COLUMN_KALORI, cursor.getString(cursor.getColumnIndexOrThrow(Food.COLUMN_KALORI)));

            getContentResolver().insert(Serapan.CONTENT_URI, cv);
        }
        cursor.close();
        autoComplete.setText("");
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, Serapan.CONTENT_URI, Serapan.COLUMNS, null, null, Serapan.TABLE_NAME + "." + Serapan._ID + " ASC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        listAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
