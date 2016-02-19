package mahirsoft.diet.data;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;

public class AppContentProvider extends ContentProvider {
    private static final String LOG_TAG = "AppContentProvider";
    public static final int FOOD = 100;
    public static final int FOOD_ITEM = 1001;

    public static final UriMatcher sUriMatcher = buildUriMatcher();

    private AppDBHelper mOpenHelper;

    @Override
    public boolean onCreate() {
        mOpenHelper = new AppDBHelper(getContext());
        return true;
    }

    @Override
    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations) {
        ContentProviderResult[] result = new ContentProviderResult[operations.size()];
        int i = 0;

        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            for (ContentProviderOperation operation : operations) {
                result[i++] = operation.apply(this, result, i);
            }
            db.setTransactionSuccessful();
        } catch (OperationApplicationException e) {
            Log.d(getClass().getName(), "batch failed: " + e.getLocalizedMessage());
        } finally {
            db.endTransaction();
        }

        return result;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri = null;

        long _id = 0;
        switch (match) {
            case FOOD:
                _id = db.insert(Food.TABLE_NAME, null, values);
                if (_id > 0) {
                    returnUri = Food.buildUri(_id);
                }
               break;
        }

        if (_id == 0) {
            Log.i(LOG_TAG, "Failed to insert row to : " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        switch (sUriMatcher.match(uri)) {
            case FOOD_ITEM:
               queryBuilder.appendWhere(Food._ID + "=" + ContentUris.parseId(uri));
            case FOOD:
                queryBuilder.setTables(Food.TABLE_NAME);
                break;
        }

        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        String sql = queryBuilder.buildQuery(projection, selection, selectionArgs, null, null, sortOrder, null);
        Log.i(getClass().getName(), "sql:" + sql + ", args:" + selectionArgs);
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder, null);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
           case FOOD:
                return Food.CONTENT_TYPE;
            case FOOD_ITEM:
               return Food.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count = 0;
        switch (sUriMatcher.match(uri)) {
            case FOOD:
                count = db.delete(Food.TABLE_NAME, selection, selectionArgs);
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count = 0;
        switch (sUriMatcher.match(uri)) {
            case FOOD:
                count = db.update(Food.TABLE_NAME, values, selection, selectionArgs);
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    private static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AppData.CONTENT_AUTHORITY, Food.PATH_FOOD, FOOD);
        uriMatcher.addURI(AppData.CONTENT_AUTHORITY, Food.PATH_FOOD_ITEM, FOOD_ITEM);
        return uriMatcher;
    }

}
