package mahirsoft.diet.data;

import android.content.ContentUris;
import android.net.Uri;

public class Serapan extends Food {
    public static final String TABLE_NAME = "serapan";

    public static final String PATH_SERAPAN = "serapan";
    public static final String PATH_SERAPAN_ITEM = "serapan/#";

    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
            .appendPath(PATH_SERAPAN).build();

    public static Uri buildUri(long id) {
        return ContentUris.withAppendedId(CONTENT_URI, id);
    }

    public static final String CONTENT_TYPE =
            "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_SERAPAN;

    public static final String CONTENT_ITEM_TYPE =
            "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_SERAPAN_ITEM;

    protected static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Food.COLUMN_NAME + " TEXT NOT NULL DEFAULT '', " +
            Food.COLUMN_KALORI + " TEXT NOT NULL DEFAULT '', " +
            Food.COLUMN_GOLONGANDARAH + " TEXT NOT NULL DEFAULT '', " +
            Food.COLUMN_CATEGORY + " TEXT NOT NULL DEFAULT '', " +
            "UNIQUE (" + Food.COLUMN_NAME + ") ON CONFLICT REPLACE); ";
}
