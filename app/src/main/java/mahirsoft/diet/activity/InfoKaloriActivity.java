package mahirsoft.diet.activity;

import android.database.Cursor;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import mahirsoft.diet.R;
import mahirsoft.diet.data.Food;

public class InfoKaloriActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView txtTitle;
    private Button btnLihat;
    private AutoCompleteTextView autoComplete;
    private ArrayAdapter<String> autoCompleteAdapter;
    private TextView nama;
    private TextView kalori;
    private TextView manfaaat;
    private TextView uraiain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_kalori);
        initView();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnLihat = (Button) findViewById(R.id.btn_lihat);
        txtTitle = (TextView) findViewById(R.id.title_info_kalori);
        autoComplete = (AutoCompleteTextView) findViewById(R.id.autoComplete);
        nama = (TextView) findViewById(R.id.txtNama);
        kalori = (TextView) findViewById(R.id.txtJmlKalori);
        manfaaat = (TextView) findViewById(R.id.txtManfaat);
        uraiain = (TextView) findViewById(R.id.txtUraian);

        Cursor foodCursor = getContentResolver().query(Food.CONTENT_URI, new String[]{"*"}, null, null, null);
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
        txtTitle.setPaintFlags(txtTitle.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        btnLihat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnLihat){
            String food = autoComplete.getText().toString();
            Cursor cursor = getContentResolver().query(Food.CONTENT_URI, Food.COLUMNS, Food.COLUMN_NAME+"='"+food+"'", null, null);
            while (cursor.moveToNext()){
                nama.setText(cursor.getString(cursor.getColumnIndexOrThrow(Food.COLUMN_NAME)));
                kalori.setText(cursor.getString(cursor.getColumnIndexOrThrow(Food.COLUMN_KALORI)));
                uraiain.setText(cursor.getString(cursor.getColumnIndexOrThrow(Food.COLUMN_URAIAN)));
                manfaaat.setText(cursor.getString(cursor.getColumnIndexOrThrow(Food.COLUMN_MANFAAT)));
            }
            cursor.close();

            autoComplete.setText("");
        }
    }
}
