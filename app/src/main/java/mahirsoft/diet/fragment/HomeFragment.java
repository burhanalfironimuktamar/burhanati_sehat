package mahirsoft.diet.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import mahirsoft.diet.R;
import mahirsoft.diet.utils.DataPref;
import mahirsoft.diet.utils.Utils;

/**
 * Created by Ati on 2/16/2016.
 */
public class HomeFragment extends Fragment {
    private ImageView mAvatar;
    private TextView txtName;
    private TextView kalori;
    private TextView kaloriTerserap;

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

        kalori.setText("Jumlah kalori per hari adalah "+DataPref.getKaloriPerHari(getActivity()));
        txtName.setText("Selamat datang " + DataPref.getNama(getActivity()));

        if (!DataPref.getTglMulai(getActivity()).isEmpty() && !DataPref.getTglSelesai(getActivity()).isEmpty()
                && Utils.isBetweenDateDiet(DataPref.getTglMulai(getActivity()), DataPref.getTglSelesai(getActivity()))){
            int terserap = 500;
            kaloriTerserap.setText("Jumlah kalori terserap per hari ini adalah ");

            if (terserap <= DataPref.getKaloriPerHari(getActivity())*0.1) {
                mAvatar.setImageResource(R.drawable.sad);
            } else if (terserap <= DataPref.getKaloriPerHari(getActivity())*0.5) {
                mAvatar.setImageResource(R.drawable.normal);
            }else {
                mAvatar.setImageResource(R.drawable.pleased);
            }
            kaloriTerserap.setVisibility(View.VISIBLE);
            mAvatar.setVisibility(View.VISIBLE);
        }else {
            kaloriTerserap.setVisibility(View.GONE);
            mAvatar.setVisibility(View.GONE);
        }
    }

}
