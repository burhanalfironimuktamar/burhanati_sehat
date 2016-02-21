package mahirsoft.diet.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import mahirsoft.diet.R;

/**
 * Created by alfironi on 2/20/2016.
 */
public class GuideAdapter extends PagerAdapter {

    private Context context;
    private ImageView img;
    private TextView txtDesc;
    private String[] description;

    public GuideAdapter(Context context, String[] description) {
        this.context = context;
        this.description = description;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.guide_layout, container, false);
        img = (ImageView) layout.findViewById(R.id.img);
        txtDesc = (TextView) layout.findViewById(R.id.desc);
        txtDesc.setText(description[position]);
        switch (position) {
            case 0:
                img.setImageResource(R.drawable.page_1);
                break;
            case 1:
                img.setImageResource(R.drawable.page_2);
                break;
            case 2:
                img.setImageResource(R.drawable.page_3);
                break;
            case 3:
                img.setImageResource(R.drawable.page_4);
                break;
            case 4:
                img.setImageResource(R.drawable.page_5);
                break;
            case 5:
                img.setImageResource(R.drawable.page_6);
                break;
            case 6:
                img.setImageResource(R.drawable.page_7);
                break;
        }
        container.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return description.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
