package android.support.v4.os;

import android.os.Build.VERSION;
import android.os.Environment;
import android.util.Log;
import java.io.File;

public final class EnvironmentCompat {
    public static final String MEDIA_UNKNOWN = "unknown";
    private static final String TAG = "EnvironmentCompat";

    public static String getStorageState(File file) {
        if (VERSION.SDK_INT >= 19) {
            return Environment.getStorageState(file);
        }
        try {
            if (file.getCanonicalPath().startsWith(Environment.getExternalStorageDirectory().getCanonicalPath()) != null) {
                return Environment.getExternalStorageState();
            }
        } catch (File file2) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to resolve canonical path: ");
            stringBuilder.append(file2);
            Log.w(str, stringBuilder.toString());
        }
        return MEDIA_UNKNOWN;
    }

    private EnvironmentCompat() {
    }
}
