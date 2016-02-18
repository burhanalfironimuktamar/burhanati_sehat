package mahirsoft.diet.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by alfironi on 2/19/2016.
 */
public class DataPref {
    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor mEditor;

    public static void setNama(String nama){
        mEditor = mSharedPreferences.edit();
        mEditor.putString(Constant.PREF_NAMA, nama);
        mEditor.commit();
    }

    public static String getNama(Context context){
        String nama = "";
        mSharedPreferences = context.getSharedPreferences(Constant.PREF_NAME,
                Context.MODE_PRIVATE);
        nama = mSharedPreferences.getString(Constant.PREF_NAMA, "");
        return nama;
    }

    public static void setUmur(int umur){
        mEditor = mSharedPreferences.edit();
        mEditor.putInt(Constant.PREF_UMUR, umur);
        mEditor.commit();
    }

    public static int getUmur(Context context){
        int umur = 0;
        mSharedPreferences = context.getSharedPreferences(Constant.PREF_NAME,
                Context.MODE_PRIVATE);
        umur = mSharedPreferences.getInt(Constant.PREF_UMUR, 0);
        return umur;
    }

    public static void setJK(String JK){
        mEditor = mSharedPreferences.edit();
        mEditor.putString(Constant.PREF_JK, JK);
        mEditor.commit();
    }

    public static String getJK(Context context){
        String JK = "";
        mSharedPreferences = context.getSharedPreferences(Constant.PREF_NAME,
                Context.MODE_PRIVATE);
        JK = mSharedPreferences.getString(Constant.PREF_JK, "L");
        return JK;
    }

    public static void setBerat(int berat){
        mEditor = mSharedPreferences.edit();
        mEditor.putInt(Constant.PREF_BERAT, 0);
        mEditor.commit();
    }

    public static int getBerat(Context context){
        int berat = 0;
        mSharedPreferences = context.getSharedPreferences(Constant.PREF_NAME,
                Context.MODE_PRIVATE);
        berat = mSharedPreferences.getInt(Constant.PREF_BERAT, 0);
        return berat;
    }

    public static void setDarah(String darah){
        mEditor = mSharedPreferences.edit();
        mEditor.putString(Constant.PREF_JK, darah);
        mEditor.commit();
    }

    public static String getDarah(Context context){
        String darah = "";
        mSharedPreferences = context.getSharedPreferences(Constant.PREF_NAME,
                Context.MODE_PRIVATE);
        darah = mSharedPreferences.getString(Constant.PREF_JK, "A");
        return darah;
    }

    public static void setTglMulai(String tglMulai){
        mEditor = mSharedPreferences.edit();
        mEditor.putString(Constant.PREF_TGL_MULAI, tglMulai);
        mEditor.commit();
    }

    public static String getTglMulai(Context context){
        String tglMulai = "";
        mSharedPreferences = context.getSharedPreferences(Constant.PREF_NAME,
                Context.MODE_PRIVATE);
        tglMulai = mSharedPreferences.getString(Constant.PREF_TGL_MULAI, "");
        return tglMulai;
    }

    public static void setTglSelesai(String tglSelesai){
        mEditor = mSharedPreferences.edit();
        mEditor.putString(Constant.PREF_TGL_SELESAI, tglSelesai);
        mEditor.commit();
    }

    public static String getTglSelesai(Context context){
        String tglSelesai = "";
        mSharedPreferences = context.getSharedPreferences(Constant.PREF_NAME,
                Context.MODE_PRIVATE);
        tglSelesai = mSharedPreferences.getString(Constant.PREF_TGL_SELESAI, "");
        return tglSelesai;
    }
}
