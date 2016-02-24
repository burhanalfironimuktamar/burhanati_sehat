package mahirsoft.diet.fragment;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import mahirsoft.diet.R;
import mahirsoft.diet.data.Food;
import mahirsoft.diet.data.JadwalDiet;
import mahirsoft.diet.utils.DataPref;

public class JadwalFragment extends Fragment implements View.OnTouchListener, View.OnClickListener {

    private Button btnSetJadwal;
    private EditText edit_mulai, edit_selesai;
    private Calendar calendar;
    private int year, month, day;
    private String tglMulai, tglSelesai;
    private ProgressDialog progressDialog;

    public JadwalFragment() {
        // Required empty public constructor
    }

    public static JadwalFragment newInstance() {
        JadwalFragment fragment = new JadwalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jadwal, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edit_mulai = (EditText) view.findViewById(R.id.edit_tgl_mulai);
        edit_selesai = (EditText) view.findViewById(R.id.edit_tgl_selesai);
        btnSetJadwal = (Button) view.findViewById(R.id.btn_set_jadwal);

        edit_mulai.setOnTouchListener(this);
        edit_selesai.setOnTouchListener(this);
        btnSetJadwal.setOnClickListener(this);

        edit_mulai.setText(DataPref.getTglMulai(getActivity()));
        edit_selesai.setText(DataPref.getTglSelesai(getActivity()));
    }

    private DatePickerDialog.OnDateSetListener selesaiListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            edit_selesai.setText(arg3 + "-" + (arg2 + 1) + "-" + arg1);
        }
    };

    private DatePickerDialog.OnDateSetListener mulaiListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            edit_mulai.setText(arg3 + "-" + (arg2 + 1) + "-" + arg1);
        }
    };

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (v == edit_mulai) {
                new DatePickerDialog(getActivity(), mulaiListener, year, month, day).show();
            } else if (v == edit_selesai) {
                new DatePickerDialog(getActivity(), selesaiListener, year, month, day).show();
            }
        }

        return false;
    }

    @Override
    public void onClick(View v) {
        if (v == btnSetJadwal) {
            tglMulai = edit_mulai.getText().toString().trim();
            tglSelesai = edit_selesai.getText().toString().trim();
            if (isValid()) {
                new SaveData().execute();
            }
        }
    }

    private boolean isValid() {
        boolean status = true;
        if (!(tglMulai.length() > 0)) {
            status = false;
            Toast.makeText(getActivity(), "Tanggal mulai tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (!(tglSelesai.length() > 0)) {
            status = false;
            Toast.makeText(getActivity(), "Tanggal selesai tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        return status;
    }

    private class SaveData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Saving...");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            Toast.makeText(getActivity(), "Set jadwal berhasil", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            DataPref.setTglMulai(tglMulai);
            DataPref.setTglSelesai(tglSelesai);
            getActivity().getContentResolver().delete(JadwalDiet.CONTENT_URI, null, null);

            try {
                long selesai = new SimpleDateFormat("dd-MM-yyyy").parse(tglSelesai).getTime();
                long mulai = new SimpleDateFormat("dd-MM-yyyy").parse(tglMulai).getTime();
                int diff = (int) ((selesai - mulai) / (24 * 60 * 60 * 1000));
                int kalori = (int) DataPref.getKaloriPerHari(getActivity());
                int umur = (int) Math.floor(DataPref.getUmur(getActivity()));
                String golonganDarah = DataPref.getDarah(getActivity());
                for (int day = 0; day < diff + 1; day++) {
                    int sumKalori = 0;
                    String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date(mulai + (day * 24 * 60 * 60 * 1000)));
                    Cursor cursor = null;
                    for (int i = 0; i<12; i++){
                        switch (i){
                            case 0:
                                cursor = getActivity().getContentResolver().query(Food.CONTENT_URI, new String[]{"*"},
                                    Food.COLUMN_DARIUMUR+"<='"+umur+"' AND "+Food.COLUMN_SAMPAIUMUR+">='"+umur+"' AND "
                                            +Food.COLUMN_GOLONGANDARAH + "='" + golonganDarah + "' AND " + Food.COLUMN_CATEGORY+ "='P'", null, "RANDOM() LIMIT 1");
                                break;
                            case 1:
                                cursor = getActivity().getContentResolver().query(Food.CONTENT_URI, new String[]{"*"},
                                        Food.COLUMN_DARIUMUR+"<='"+umur+"' AND "+Food.COLUMN_SAMPAIUMUR+">='"+umur+"' AND "+
                                                Food.COLUMN_GOLONGANDARAH + "='" + golonganDarah + "' AND " + Food.COLUMN_CATEGORY+ "='K'", null, "RANDOM() LIMIT 1");
                                break;
                            case 2:
                                cursor = getActivity().getContentResolver().query(Food.CONTENT_URI, new String[]{"*"},
                                        Food.COLUMN_DARIUMUR+"<='"+umur+"' AND "+Food.COLUMN_SAMPAIUMUR+">='"+umur+"' AND "+
                                                Food.COLUMN_GOLONGANDARAH + "='" + golonganDarah + "' AND " + Food.COLUMN_CATEGORY+ "='V'", null, "RANDOM() LIMIT 1");
                                break;
                            case 3:
                                cursor = getActivity().getContentResolver().query(Food.CONTENT_URI, new String[]{"*"},
                                        Food.COLUMN_DARIUMUR+"<='"+umur+"' AND "+Food.COLUMN_SAMPAIUMUR+">='"+umur+"' AND "+
                                                Food.COLUMN_GOLONGANDARAH + "='" + golonganDarah + "' AND " + Food.COLUMN_CATEGORY+ "='L'", null, "RANDOM() LIMIT 1");
                                break;
                            case 4:
                                cursor = getActivity().getContentResolver().query(Food.CONTENT_URI, new String[]{"*"},
                                        Food.COLUMN_DARIUMUR+"<='"+umur+"' AND "+Food.COLUMN_SAMPAIUMUR+">='"+umur+"' AND "
                                                +Food.COLUMN_GOLONGANDARAH + "='" + golonganDarah + "' AND " + Food.COLUMN_CATEGORY+ "='P'", null, "RANDOM() LIMIT 1");
                                break;
                            case 5:
                                cursor = getActivity().getContentResolver().query(Food.CONTENT_URI, new String[]{"*"},
                                        Food.COLUMN_DARIUMUR+"<='"+umur+"' AND "+Food.COLUMN_SAMPAIUMUR+">='"+umur+"' AND "+
                                                Food.COLUMN_GOLONGANDARAH + "='" + golonganDarah + "' AND " + Food.COLUMN_CATEGORY+ "='K'", null, "RANDOM() LIMIT 1");
                                break;
                            case 6:
                                cursor = getActivity().getContentResolver().query(Food.CONTENT_URI, new String[]{"*"},
                                        Food.COLUMN_DARIUMUR+"<='"+umur+"' AND "+Food.COLUMN_SAMPAIUMUR+">='"+umur+"' AND "+
                                                Food.COLUMN_GOLONGANDARAH + "='" + golonganDarah + "' AND " + Food.COLUMN_CATEGORY+ "='V'", null, "RANDOM() LIMIT 1");
                                break;
                            case 7:
                                cursor = getActivity().getContentResolver().query(Food.CONTENT_URI, new String[]{"*"},
                                        Food.COLUMN_DARIUMUR+"<='"+umur+"' AND "+Food.COLUMN_SAMPAIUMUR+">='"+umur+"' AND "+
                                                Food.COLUMN_GOLONGANDARAH + "='" + golonganDarah + "' AND " + Food.COLUMN_CATEGORY+ "='L'", null, "RANDOM() LIMIT 1");
                                break;
                            case 8:
                                cursor = getActivity().getContentResolver().query(Food.CONTENT_URI, new String[]{"*"},
                                        Food.COLUMN_DARIUMUR+"<='"+umur+"' AND "+Food.COLUMN_SAMPAIUMUR+">='"+umur+"' AND "
                                                +Food.COLUMN_GOLONGANDARAH + "='" + golonganDarah + "' AND " + Food.COLUMN_CATEGORY+ "='P'", null, "RANDOM() LIMIT 1");
                                break;
                            case 9:
                                cursor = getActivity().getContentResolver().query(Food.CONTENT_URI, new String[]{"*"},
                                        Food.COLUMN_DARIUMUR+"<='"+umur+"' AND "+Food.COLUMN_SAMPAIUMUR+">='"+umur+"' AND "+
                                                Food.COLUMN_GOLONGANDARAH + "='" + golonganDarah + "' AND " + Food.COLUMN_CATEGORY+ "='K'", null, "RANDOM() LIMIT 1");
                                break;
                            case 10:
                                cursor = getActivity().getContentResolver().query(Food.CONTENT_URI, new String[]{"*"},
                                        Food.COLUMN_DARIUMUR+"<='"+umur+"' AND "+Food.COLUMN_SAMPAIUMUR+">='"+umur+"' AND "+
                                                Food.COLUMN_GOLONGANDARAH + "='" + golonganDarah + "' AND " + Food.COLUMN_CATEGORY+ "='V'", null, "RANDOM() LIMIT 1");
                                break;
                            case 11:
                                cursor = getActivity().getContentResolver().query(Food.CONTENT_URI, new String[]{"*"},
                                        Food.COLUMN_DARIUMUR+"<='"+umur+"' AND "+Food.COLUMN_SAMPAIUMUR+">='"+umur+"' AND "+
                                                Food.COLUMN_GOLONGANDARAH + "='" + golonganDarah + "' AND " + Food.COLUMN_CATEGORY+ "='L'", null, "RANDOM() LIMIT 1");
                                break;
                        }
                        if (cursor!=null && cursor.moveToNext()) {
                            int colory = cursor.getInt(cursor.getColumnIndexOrThrow(JadwalDiet.COLUMN_KALORI));
                            sumKalori += colory;
                            if (sumKalori <= kalori) {
                                ContentValues cv = new ContentValues();
                                cv.put(JadwalDiet.COLUMN_NAME, cursor.getString(cursor.getColumnIndexOrThrow(JadwalDiet.COLUMN_NAME)));
                                cv.put(JadwalDiet.COLUMN_KALORI, colory);
                                cv.put(JadwalDiet.COLUMN_GOLONGANDARAH, cursor.getString(cursor.getColumnIndexOrThrow(JadwalDiet.COLUMN_GOLONGANDARAH)));
                                cv.put(JadwalDiet.COLUMN_DATE, date);
                                getActivity().getContentResolver().insert(JadwalDiet.CONTENT_URI, cv);
                            }
                        }
                        cursor.close();
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
