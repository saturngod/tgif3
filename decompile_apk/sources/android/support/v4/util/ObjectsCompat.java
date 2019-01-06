package android.support.v4.util;

import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import java.util.Arrays;
import java.util.Objects;

public class ObjectsCompat {
    private ObjectsCompat() {
    }

    public static boolean equals(@Nullable Object obj, @Nullable Object obj2) {
        if (VERSION.SDK_INT >= 19) {
            return Objects.equals(obj, obj2);
        }
        if (obj != obj2) {
            if (obj == null || obj.equals(obj2) == null) {
                obj = null;
                return obj;
            }
        }
        obj = true;
        return obj;
    }

    public static int hashCode(@Nullable Object obj) {
        return obj != null ? obj.hashCode() : null;
    }

    public static int hash(@Nullable Object... objArr) {
        if (VERSION.SDK_INT >= 19) {
            return Objects.hash(objArr);
        }
        return Arrays.hashCode(objArr);
    }
}
