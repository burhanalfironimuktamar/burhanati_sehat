package mahirsoft.diet.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;

    public static final String DATABASE_NAME = "diet.db";

    public AppDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Food.SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(Serapan.SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(JadwalDiet.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Food.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Serapan.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + JadwalDiet.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
