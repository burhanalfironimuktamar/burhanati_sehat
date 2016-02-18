package mahirsoft.diet.fragment;


import android.app.DatePickerDialog;
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

import java.util.Calendar;

import mahirsoft.diet.R;
import mahirsoft.diet.utils.DataPref;

public class JadwalFragment extends Fragment implements View.OnTouchListener, View.OnClickListener {

    private Button btnSetJadwal;
    private EditText edit_mulai, edit_selesai;
    private Calendar calendar;
    private int year, month, day;
    private String tglMulai, tglSelesai;

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
                DataPref.setTglMulai(tglMulai);
                DataPref.setTglSelesai(tglSelesai);
                Toast.makeText(getActivity(), "Set jadwal berhasil", Toast.LENGTH_SHORT).show();
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

}
