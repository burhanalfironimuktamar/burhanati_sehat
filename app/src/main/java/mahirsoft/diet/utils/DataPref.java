package mahirsoft.diet.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by alfironi on 2/19/2016.
 */
public class DataPref {
    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor mEditor;

    public static void setNama(String nama) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(Constant.PREF_NAMA, nama);
        mEditor.commit();
    }

    public static String getNama(Context context) {
        String nama = "";
        mSharedPreferences = context.getSharedPreferences(Constant.PREF_NAME,
                Context.MODE_PRIVATE);
        nama = mSharedPreferences.getString(Constant.PREF_NAMA, "");
        Log.d("", "hans getNama " + nama);
        return nama;
    }

    public static void setUmur(float umur) {
        mEditor = mSharedPreferences.edit();
        mEditor.putFloat(Constant.PREF_UMUR, umur);
        mEditor.commit();
    }

    public static float getUmur(Context context) {
        float umur = 0;
        mSharedPreferences = context.getSharedPreferences(Constant.PREF_NAME,
                Context.MODE_PRIVATE);
        umur = mSharedPreferences.getFloat(Constant.PREF_UMUR, 0);
        Log.d("", "hans getUmur " + umur);
        return umur;
    }

    public static void setJK(String JK) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(Constant.PREF_JK, JK);
        mEditor.commit();
    }

    public static String getJK(Context context) {
        String JK = "";
        mSharedPreferences = context.getSharedPreferences(Constant.PREF_NAME,
                Context.MODE_PRIVATE);
        JK = mSharedPreferences.getString(Constant.PREF_JK, "L");
        Log.d("hans", "hans getJK " + JK);
        return JK;
    }

    public static void setBerat(int berat) {
        mEditor = mSharedPreferences.edit();
        mEditor.putInt(Constant.PREF_BERAT, berat);
        mEditor.commit();
    }

    public static int getBerat(Context context) {
        int berat = 0;
        mSharedPreferences = context.getSharedPreferences(Constant.PREF_NAME,
                Context.MODE_PRIVATE);
        berat = mSharedPreferences.getInt(Constant.PREF_BERAT, 0);
        Log.d("", "hans getberat" + berat);
        return berat;
    }
    public static void setTinggi(int tinggi) {
        mEditor = mSharedPreferences.edit();
        mEditor.putInt(Constant.PREF_TINGGI, tinggi);
        mEditor.commit();
    }

    public static int getTinggi(Context context) {
        mSharedPreferences = context.getSharedPreferences(Constant.PREF_NAME,
                Context.MODE_PRIVATE);
        return mSharedPreferences.getInt(Constant.PREF_TINGGI, 0);
    }
    public static void setDarah(String darah) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(Constant.PREF_DARAH, darah);
        mEditor.commit();
    }

    public static String getDarah(Context context) {
        String darah = "";
        mSharedPreferences = context.getSharedPreferences(Constant.PREF_NAME,
                Context.MODE_PRIVATE);
        darah = mSharedPreferences.getString(Constant.PREF_DARAH, "A");
        Log.d("", "hans getDarah" + darah);
        return darah;
    }

    public static void setTglMulai(String tglMulai) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(Constant.PREF_TGL_MULAI, tglMulai);
        mEditor.commit();
    }

    public static String getTglMulai(Context context) {
        String tglMulai = "";
        mSharedPreferences = context.getSharedPreferences(Constant.PREF_NAME,
                Context.MODE_PRIVATE);
        tglMulai = mSharedPreferences.getString(Constant.PREF_TGL_MULAI, "");
        return tglMulai;
    }

    public static void setTglSelesai(String tglSelesai) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(Constant.PREF_TGL_SELESAI, tglSelesai);
        mEditor.commit();
    }

    public static String getTglSelesai(Context context) {
        String tglSelesai = "";
        mSharedPreferences = context.getSharedPreferences(Constant.PREF_NAME,
                Context.MODE_PRIVATE);
        tglSelesai = mSharedPreferences.getString(Constant.PREF_TGL_SELESAI, "");
        return tglSelesai;
    }

    public static void setKaloriPerHari(long kaloriPerHari) {
        mEditor = mSharedPreferences.edit();
        mEditor.putLong(Constant.PREF_KALORI_PERHARI, kaloriPerHari);
        mEditor.commit();
    }

    public static long getKaloriPerHari(Context context) {
        mSharedPreferences = context.getSharedPreferences(Constant.PREF_NAME,
                Context.MODE_PRIVATE);
        return mSharedPreferences.getLong(Constant.PREF_KALORI_PERHARI, 0);
    }

    public static void setTTL(String ttl) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(Constant.PREF_TTL, ttl);
        mEditor.commit();
    }

    public static String getTTL(Context context) {
        String ttl = "";
        mSharedPreferences = context.getSharedPreferences(Constant.PREF_NAME,
                Context.MODE_PRIVATE);
        ttl = mSharedPreferences.getString(Constant.PREF_TTL, "");
        return ttl;
    }

    public static void setDateSerapan(String ttl) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(Constant.PREF_SERAPAN, ttl);
        mEditor.commit();
    }

    public static String getSerapan(Context context) {
        String serapan = "";
        mSharedPreferences = context.getSharedPreferences(Constant.PREF_NAME,
                Context.MODE_PRIVATE);
        serapan = mSharedPreferences.getString(Constant.PREF_SERAPAN, "");
        return serapan;
    }

    public static void isFirst(boolean isFirst) {
        mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(Constant.PREF_ISFIRST, isFirst);
        mEditor.commit();
    }

    public static boolean getFirst(Context context) {
        boolean isFirst = true;
        mSharedPreferences = context.getSharedPreferences(Constant.PREF_NAME,
                Context.MODE_PRIVATE);
        isFirst = mSharedPreferences.getBoolean(Constant.PREF_ISFIRST, true);
        return isFirst;
    }
}
