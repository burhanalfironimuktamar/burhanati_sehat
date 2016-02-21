package mahirsoft.diet.data;

import android.content.ContentUris;
import android.net.Uri;

public class JadwalDiet extends Food {
    public static final String TABLE_NAME = "jadwaldiet";

    public static final String PATH_JADWALDIET = "jadwaldiet";
    public static final String PATH_JADWALDIET_ITEM = "jadwaldiet/#";

    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
            .appendPath(PATH_JADWALDIET).build();

    public static Uri buildUri(long id) {
        return ContentUris.withAppendedId(CONTENT_URI, id);
    }

    public static final String CONTENT_TYPE =
            "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_JADWALDIET;

    public static final String CONTENT_ITEM_TYPE =
            "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_JADWALDIET_ITEM;

    public static final String COLUMN_DATE = "date";

    protected static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Food.COLUMN_NAME + " TEXT NOT NULL DEFAULT '', " +
            Food.COLUMN_KALORI + " TEXT NOT NULL DEFAULT '', " +
            Food.COLUMN_GOLONGANDARAH + " TEXT NOT NULL DEFAULT '', " +
            JadwalDiet.COLUMN_DATE + " TEXT NOT NULL DEFAULT '', " +
            "UNIQUE (" + Food.COLUMN_NAME + ") ON CONFLICT REPLACE); ";
}
