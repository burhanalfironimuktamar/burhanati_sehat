package mahirsoft.diet.data;

import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.provider.BaseColumns;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Food extends AppData implements BaseColumns {
    public static final String TABLE_NAME = "food";

    public static final String PATH_FOOD = "food";
    public static final String PATH_FOOD_ITEM = "food/#";

    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
            .appendPath(PATH_FOOD).build();

    public static Uri buildUri(long id) {
        return ContentUris.withAppendedId(CONTENT_URI, id);
    }

    public static final String CONTENT_TYPE =
            "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_FOOD;

    public static final String CONTENT_ITEM_TYPE =
            "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_FOOD_ITEM;

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_KALORI = "calory";
    public static final String COLUMN_GOLONGANDARAH = "golongandarah";
    public static final String COLUMN_CATEGORY = "category";

    protected static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Food.COLUMN_NAME + " TEXT NOT NULL DEFAULT '', " +
            Food.COLUMN_KALORI + " TEXT NOT NULL DEFAULT '', " +
            Food.COLUMN_GOLONGANDARAH + " TEXT NOT NULL DEFAULT '', " +
            Food.COLUMN_CATEGORY + " TEXT NOT NULL DEFAULT '', " +
            "UNIQUE (" + Food.COLUMN_NAME + ") ON CONFLICT REPLACE); ";

    public static final String[] COLUMNS = {
            _ID, COLUMN_NAME, COLUMN_KALORI, COLUMN_GOLONGANDARAH, COLUMN_CATEGORY
    };

    public static List<ContentValues> fromJSONArray(JSONArray jsonArray) {
        List<ContentValues> listValues = new ArrayList<ContentValues>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject obj = jsonArray.getJSONObject(i);
                ContentValues values = Food.fromJSONObject(obj);
                listValues.add(values);
            } catch (JSONException e) {
            }
        }
        return listValues;
    }

    public static ContentValues fromJSONObject(JSONObject jsonObject) throws JSONException {
        ContentValues contentValues = new ContentValues();
        for (int i = 1; i < COLUMNS.length; i++) {
            contentValues.put(COLUMNS[i], jsonObject.getString(COLUMNS[i]));
        }
        return contentValues;
    }
}
