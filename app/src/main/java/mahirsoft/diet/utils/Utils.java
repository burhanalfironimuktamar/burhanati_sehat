package mahirsoft.diet.utils;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import mahirsoft.diet.R;

/**
 * Created by ati on 2/19/2016.
 */
public class Utils {

    public static JSONArray getData(Context context) {
        InputStream inputStream = context.getResources().openRawResource(R.raw.data);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int ctr;
        try {
            ctr = inputStream.read();
            while (ctr != -1) {
                byteArrayOutputStream.write(ctr);
                ctr = inputStream.read();
            }
            inputStream.close();
            return new JSONArray(byteArrayOutputStream.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static long kaloriPerHari(String jk, int berat, int tinggi, float usia) {
        if (jk.equals("P")) {
            return Math.round(66.4730 + (13.7516 * berat) + (5.0033 * tinggi) - (6.7550 * usia));
        } else {
            return Math.round(655.0955 + (9.5634 * berat) + (1.8496 * tinggi) - (4.6756 * usia));
        }
    }

    public static boolean isBetweenDateDiet(String start, String end) {
        try {
            long now = Calendar.getInstance().getTimeInMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            long sDate = sdf.parse(start).getTime();
            long eDate = sdf.parse(end).getTime()+24*60*60*1000;
            if (sDate<=now && now<=eDate){
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
