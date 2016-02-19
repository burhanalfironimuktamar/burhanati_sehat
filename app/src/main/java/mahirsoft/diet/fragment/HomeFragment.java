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

/**
 * Created by Ati on 2/16/2016.
 */
public class HomeFragment extends Fragment {
    private ImageView mAvatar;
    private TextView txtName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAvatar = (ImageView) view.findViewById(R.id.emoticon);
        txtName = (TextView) view.findViewById(R.id.name);

        txtName.setText("Selamat datang, " + DataPref.getNama(getActivity()));
        String JK = DataPref.getJK(getActivity());
        if (JK.equalsIgnoreCase("L")) {
            mAvatar.setImageResource(R.drawable.male);
        } else {
            mAvatar.setImageResource(R.drawable.female);
        }
    }

}
