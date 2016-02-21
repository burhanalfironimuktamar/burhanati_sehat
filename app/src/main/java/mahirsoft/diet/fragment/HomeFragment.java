package mahirsoft.diet.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

import mahirsoft.diet.R;
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
        validDate();
        kalori.setText("Jumlah kalori per hari adalah " + DataPref.getKaloriPerHari(getActivity()));
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
    }

    private void validDate() {
        String date = DataPref.getSerapan(getActivity());
        final Calendar c = Calendar.getInstance();
        String currentDate = c.get(Calendar.YEAR) + "" + (c.get(Calendar.MONTH) - 1) + "" + c.get(Calendar.DAY_OF_MONTH);
        if (date.length() > 0) {
            if (!date.equalsIgnoreCase(currentDate)) {
                getActivity().getContentResolver().delete(Serapan.CONTENT_URI, null, null);
            }
        }
        DataPref.setDateSerapan(currentDate);
    }
}
