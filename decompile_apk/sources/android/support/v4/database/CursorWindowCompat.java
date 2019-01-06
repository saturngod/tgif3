package android.support.v4.database;

import android.database.CursorWindow;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class CursorWindowCompat {
    private CursorWindowCompat() {
    }

    @NonNull
    public static CursorWindow create(@Nullable String str, long j) {
        if (VERSION.SDK_INT >= 28) {
            return new CursorWindow(str, j);
        }
        if (VERSION.SDK_INT >= 15) {
            return new CursorWindow(str);
        }
        return new CursorWindow(0);
    }
}
