package android.support.v4.os;

import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class HandlerCompat {
    public static boolean postDelayed(@NonNull Handler handler, @NonNull Runnable runnable, @Nullable Object obj, long j) {
        if (VERSION.SDK_INT >= 28) {
            return handler.postDelayed(runnable, obj, j);
        }
        runnable = Message.obtain(handler, runnable);
        runnable.obj = obj;
        return handler.sendMessageDelayed(runnable, j);
    }

    private HandlerCompat() {
    }
}
