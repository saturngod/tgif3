package android.support.v4.app;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class AppOpsManagerCompat {
    public static final int MODE_ALLOWED = 0;
    public static final int MODE_DEFAULT = 3;
    public static final int MODE_ERRORED = 2;
    public static final int MODE_IGNORED = 1;

    private AppOpsManagerCompat() {
    }

    @Nullable
    public static String permissionToOp(@NonNull String str) {
        return VERSION.SDK_INT >= 23 ? AppOpsManager.permissionToOp(str) : null;
    }

    public static int noteOp(@NonNull Context context, @NonNull String str, int i, @NonNull String str2) {
        return VERSION.SDK_INT >= 19 ? ((AppOpsManager) context.getSystemService("appops")).noteOp(str, i, str2) : 1;
    }

    public static int noteOpNoThrow(@NonNull Context context, @NonNull String str, int i, @NonNull String str2) {
        return VERSION.SDK_INT >= 19 ? ((AppOpsManager) context.getSystemService("appops")).noteOpNoThrow(str, i, str2) : 1;
    }

    public static int noteProxyOp(@NonNull Context context, @NonNull String str, @NonNull String str2) {
        return VERSION.SDK_INT >= 23 ? ((AppOpsManager) context.getSystemService(AppOpsManager.class)).noteProxyOp(str, str2) : 1;
    }

    public static int noteProxyOpNoThrow(@NonNull Context context, @NonNull String str, @NonNull String str2) {
        return VERSION.SDK_INT >= 23 ? ((AppOpsManager) context.getSystemService(AppOpsManager.class)).noteProxyOpNoThrow(str, str2) : 1;
    }
}
