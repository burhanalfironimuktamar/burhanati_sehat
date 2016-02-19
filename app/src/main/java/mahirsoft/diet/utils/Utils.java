package mahirsoft.diet.utils;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
}
