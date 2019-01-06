package android.support.v4.content.pm;

import android.annotation.SuppressLint;
import android.content.pm.PermissionInfo;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class PermissionInfoCompat {

    @RestrictTo({Scope.LIBRARY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Protection {
    }

    @SuppressLint({"UniqueConstants"})
    @RestrictTo({Scope.LIBRARY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ProtectionFlags {
    }

    private PermissionInfoCompat() {
    }

    @SuppressLint({"WrongConstant"})
    public static int getProtection(@NonNull PermissionInfo permissionInfo) {
        if (VERSION.SDK_INT >= 28) {
            return permissionInfo.getProtection();
        }
        return permissionInfo.protectionLevel & 15;
    }

    @SuppressLint({"WrongConstant"})
    public static int getProtectionFlags(@NonNull PermissionInfo permissionInfo) {
        if (VERSION.SDK_INT >= 28) {
            return permissionInfo.getProtectionFlags();
        }
        return permissionInfo.protectionLevel & -16;
    }
}
