package mahirsoft.diet.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import mahirsoft.diet.R;
import mahirsoft.diet.data.JadwalDiet;
import mahirsoft.diet.data.Serapan;
import mahirsoft.diet.utils.DataPref;

/**
 * Created by Ati on 2/16/2016.
 */
public class HomeFragment extends Fragment {
    private ImageView mAvatar;
    private TextView txtName;
    private TextView kalori;
    private TextView kaloriTerserap;

    private int terserap = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAvatar = (ImageView) view.findViewById(R.id.emoticon);
        txtName = (TextView) view.findViewById(R.id.name);
        kalori = (TextView) view.findViewById(R.id.kalori);
        kaloriTerserap = (TextView) view.findViewById(R.id.kalori_terserap);
    }

    @Override
    public void onResume() {
        super.onResume();
        txtName.setText("Selamat datang " + DataPref.getNama(getActivity()));

        Cursor cursor = getActivity().getContentResolver().query(Serapan.CONTENT_URI, new String[]{"sum(" + Serapan.COLUMN_KALORI + ")"}, null, null, null);
        if (cursor.moveToNext()) {
            terserap = cursor.getInt(cursor.getColumnIndexOrThrow("sum(" + Serapan.COLUMN_KALORI + ")"));
        }
        cursor.close();
        kaloriTerserap.setText("Jumlah kalori terserap hari ini adalah " + terserap);
        if (terserap <= DataPref.getKaloriPerHari(getActivity()) * 0.1) {
            mAvatar.setImageResource(R.drawable.sad);
        } else if (terserap <= DataPref.getKaloriPerHari(getActivity()) * 0.5) {
            mAvatar.setImageResource(R.drawable.normal);
        } else {
            mAvatar.setImageResource(R.drawable.pleased);
        }

        Cursor k = getActivity().getContentResolver().query(JadwalDiet.CONTENT_URI, new String[]{"sum(" + JadwalDiet.COLUMN_KALORI + ")"}, JadwalDiet.COLUMN_DATE + "='" + new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime()) + "'", null, null);
        if (k.moveToNext()) {
            int kal = k.getInt(k.getColumnIndexOrThrow("sum(" + JadwalDiet.COLUMN_KALORI + ")"));
            if (kal>0) {
                kalori.setText("Jumlah kalori per hari adalah " + kal);

            }else {
                kalori.setText("Jumlah kalori per hari adalah " + DataPref.getKaloriPerHari(getActivity()));
            }
        } else {
            kalori.setText("Jumlah kalori per hari adalah " + DataPref.getKaloriPerHari(getActivity()));
        }
        k.close();
    }
}
