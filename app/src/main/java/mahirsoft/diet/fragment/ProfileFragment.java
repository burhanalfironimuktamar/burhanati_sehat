package mahirsoft.diet.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import mahirsoft.diet.R;
import mahirsoft.diet.utils.DataPref;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private EditText txtNama, txtUmur, txtBerat;
    private Spinner spinneDarah;
    private RadioButton rdL, rdP;
    private Button btnSimpan;
    private String nama, JK, darah;
    private int umur, berat;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
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
        rdL = (RadioButton) view.findViewById(R.id.radioMale);
        rdP = (RadioButton) view.findViewById(R.id.radioFemale);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.golongan_darah, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneDarah.setAdapter(adapter);

        btnSimpan.setOnClickListener(this);

        initView();
    }

    private void initView() {
        nama = DataPref.getNama(getActivity());
        umur = DataPref.getUmur(getActivity());
        JK = DataPref.getJK(getActivity());
        berat = DataPref.getBerat(getActivity());
        darah = DataPref.getDarah(getActivity());

        if (nama.length() > 0) {
            txtNama.setText(nama);
        }
        if (umur > 0) {
            txtUmur.setText(umur + "");
        }
        if (JK.equalsIgnoreCase("L")) {
            rdL.setSelected(true);
        } else {
            rdP.setSelected(true);
        }
        if (berat > 0) {
            txtBerat.setText(berat + "");
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
            String strUmur = txtUmur.getText().toString().trim();
            if (strUmur.length() > 0) {
                umur = Integer.parseInt(strUmur);
            } else {
                umur = 0;
            }
            if (rdL.isSelected()) {
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
            darah = spinneDarah.getSelectedItem().toString().trim();

            if (isValid()) {
                DataPref.setNama(nama);
                DataPref.setUmur(umur);
                DataPref.setJK(JK);
                DataPref.setBerat(berat);
                DataPref.setDarah(darah);
                Toast.makeText(getActivity(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
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
}
