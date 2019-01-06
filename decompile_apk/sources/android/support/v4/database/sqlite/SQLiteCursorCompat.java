package android.support.v4.database.sqlite;

import android.database.sqlite.SQLiteCursor;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;

public final class SQLiteCursorCompat {
    private SQLiteCursorCompat() {
    }

    public static void setFillWindowForwardOnly(@NonNull SQLiteCursor sQLiteCursor, boolean z) {
        if (VERSION.SDK_INT >= 28) {
            sQLiteCursor.setFillWindowForwardOnly(z);
        }
    }
}
