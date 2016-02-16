package mahirsoft.diet.data;

import android.net.Uri;

public class AppData {
    public static final String CONTENT_AUTHORITY = "mahirsoft.die.contentprovider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
}
