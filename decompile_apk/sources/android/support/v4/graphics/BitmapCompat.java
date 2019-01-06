package android.support.v4.graphics;

import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;

public final class BitmapCompat {
    public static boolean hasMipMap(@NonNull Bitmap bitmap) {
        return VERSION.SDK_INT >= 18 ? bitmap.hasMipMap() : null;
    }

    public static void setHasMipMap(@NonNull Bitmap bitmap, boolean z) {
        if (VERSION.SDK_INT >= 18) {
            bitmap.setHasMipMap(z);
        }
    }

    public static int getAllocationByteCount(@NonNull Bitmap bitmap) {
        if (VERSION.SDK_INT >= 19) {
            return bitmap.getAllocationByteCount();
        }
        return bitmap.getByteCount();
    }

    private BitmapCompat() {
    }
}
