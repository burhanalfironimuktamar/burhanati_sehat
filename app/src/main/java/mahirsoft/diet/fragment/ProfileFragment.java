package mahirsoft.diet.fragment;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

import mahirsoft.diet.R;
import mahirsoft.diet.activity.DietSehatActivity;
import mahirsoft.diet.utils.DataPref;
import mahirsoft.diet.utils.Utils;

public class ProfileFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {

    private EditText txtNama, txtUmur, txtBerat, txtTinggi;
    private Spinner spinneDarah;
    private RadioButton rdL, rdP;
    private RadioGroup rdGroup;
    private Button btnSimpan;
    private String nama, JK, darah, ttl;
    private int berat, tinggi;
    private float umur;
    private boolean isFirst = false;

    private int year;
    private int month;
    private int day;

    private int currYear;
    private int currMonth;
    private int currDay;


    public ProfileFragment() {
    }

    public static ProfileFragment newInstance(boolean isFirst) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putBoolean("isFirst", isFirst);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isFirst = getArguments().getBoolean("isFirst");
        }
        final Calendar c = Calendar.getInstance();

        currYear = c.get(Calendar.YEAR);
        currMonth = c.get(Calendar.MONTH);
        currDay = c.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinneDarah = (Spinner) view.findViewById(R.id.spinner);
        btnSimpan = (Button) view.findViewById(R.id.btn_simpan);
        txtNama = (EditText) view.findViewById(R.id.edit_nama);
        txtUmur = (EditText) view.findViewById(R.id.edit_umur);
        txtBerat = (EditText) view.findViewById(R.id.edit_berat);
        txtTinggi = (EditText) view.findViewById(R.id.edit_tinggi);
        rdL = (RadioButton) view.findViewById(R.id.radioMale);
        rdP = (RadioButton) view.findViewById(R.id.radioFemale);
        rdGroup = (RadioGroup) view.findViewById(R.id.radioSex);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.golongan_darah, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneDarah.setAdapter(adapter);

        btnSimpan.setOnClickListener(this);
        txtUmur.setOnTouchListener(this);

        initView();
    }

    private void initView() {
        nama = DataPref.getNama(getActivity());
        umur = DataPref.getUmur(getActivity());
        ttl = DataPref.getTTL(getActivity());
        JK = DataPref.getJK(getActivity());
        berat = DataPref.getBerat(getActivity());
        darah = DataPref.getDarah(getActivity());
        tinggi = DataPref.getTinggi(getActivity());

        if (nama.length() > 0) {
            txtNama.setText(nama);
        }
        if (ttl.length() > 0) {
            txtUmur.setText(ttl);
        }

        if (JK.equalsIgnoreCase("L")) {
            rdL.setChecked(true);
        } else {
            rdP.setChecked(true);
        }

        if (berat > 0) {
            txtBerat.setText(berat + "");
        }

        if (tinggi > 0) {
            txtTinggi.setText(tinggi + "");
        }

        if (darah.equalsIgnoreCase("A")) {
            spinneDarah.setSelection(0);
        } else if (darah.equalsIgnoreCase("B")) {
            spinneDarah.setSelection(1);
        } else if (darah.equalsIgnoreCase("AB")) {
            spinneDarah.setSelection(2);
        } else {
            spinneDarah.setSelection(3);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnSimpan) {
            nama = txtNama.getText().toString().trim();

            String ttl = txtUmur.getText().toString().trim();
            if (ttl.length() > 0) {
                String[] date = ttl.split("-");
                umur = currYear - Integer.parseInt(date[2]);
                int diffMonth = (currMonth+1) - Integer.parseInt(date[1]);
                int diffDay = currDay - Integer.parseInt(date[0]);
                if (diffMonth < 0) {
                    umur--;
                    diffMonth += 12;
                }
                if (diffDay < 0) {
                    diffMonth--;
                }
                umur += Float.valueOf("0." + diffMonth);

            } else {
                umur = 0;
            }

            if (rdL.isChecked()) {
                JK = "L";
            } else {
                JK = "P";
            }

            String strBerat = txtBerat.getText().toString().trim();
            if (strBerat.length() > 0) {
                berat = Integer.parseInt(strBerat);
            } else {
                berat = 0;
            }

            String strTinggi = txtTinggi.getText().toString().trim();
            if (strBerat.length() > 0) {
                tinggi = Integer.parseInt(strTinggi);
            } else {
                tinggi = 0;
            }

            darah = spinneDarah.getSelectedItem().toString().trim();

            if (isValid()) {
                DataPref.setNama(nama);
                DataPref.setTTL(ttl);
                DataPref.setUmur(umur);
                DataPref.setJK(JK);
                DataPref.setBerat(berat);
                DataPref.setDarah(darah);
                DataPref.setTinggi(tinggi);
                DataPref.setKaloriPerHari(Utils.kaloriPerHari(JK, berat, tinggi, umur));
                if (isFirst) {
                    startActivity(new Intent(getActivity(), DietSehatActivity.class));
                    getActivity().finish();
                } else {
                    Toast.makeText(getActivity(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    private boolean isValid() {
        boolean status = true;
        if (nama.length() < 1) {
            status = false;
            Toast.makeText(getActivity(), "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
            txtNama.requestFocus();
        } else if (umur < 1) {
            status = false;
            Toast.makeText(getActivity(), "Umur tidak boleh kosong", Toast.LENGTH_SHORT).show();
            txtUmur.requestFocus();
        } else if (berat < 1) {
            status = false;
            Toast.makeText(getActivity(), "Berat tidak boleh kosong", Toast.LENGTH_SHORT).show();
            txtBerat.requestFocus();
        }
        return status;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        boolean status = false;
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (v == txtUmur) {
                if (ttl.length() > 0) {
                    String[] date = ttl.split("-");
                    day = Integer.parseInt(date[0]);
                    month = Integer.parseInt(date[1]) - 1;
                    year = Integer.parseInt(date[2]);
                } else {
                    year = currYear;
                    month = currMonth;
                    day = currDay;
                }
                new DatePickerDialog(getActivity(), datePickerListener,
                        year, month, day).show();
                status = true;
            }
        }

        return status;
    }


    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            txtUmur.setText(new StringBuilder().append(day).append("-").append(month + 1)
                    .append("-").append(year));

        }
    };
}
