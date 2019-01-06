package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.util.SparseArray;

@Deprecated
public abstract class WakefulBroadcastReceiver extends BroadcastReceiver {
    private static final String EXTRA_WAKE_LOCK_ID = "android.support.content.wakelockid";
    private static int mNextId = 1;
    private static final SparseArray<WakeLock> sActiveWakeLocks = new SparseArray();

    public static ComponentName startWakefulService(Context context, Intent intent) {
        synchronized (sActiveWakeLocks) {
            int i = mNextId;
            mNextId++;
            if (mNextId <= 0) {
                mNextId = 1;
            }
            intent.putExtra(EXTRA_WAKE_LOCK_ID, i);
            intent = context.startService(intent);
            if (intent == null) {
                return null;
            }
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("androidx.core:wake:");
            stringBuilder.append(intent.flattenToShortString());
            context = powerManager.newWakeLock(1, stringBuilder.toString());
            context.setReferenceCounted(false);
            context.acquire(60000);
            sActiveWakeLocks.put(i, context);
            return intent;
        }
    }

    public static boolean completeWakefulIntent(Intent intent) {
        intent = intent.getIntExtra(EXTRA_WAKE_LOCK_ID, 0);
        if (intent == null) {
            return false;
        }
        synchronized (sActiveWakeLocks) {
            WakeLock wakeLock = (WakeLock) sActiveWakeLocks.get(intent);
            if (wakeLock != null) {
                wakeLock.release();
                sActiveWakeLocks.remove(intent);
                return true;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("No active wake lock id #");
            stringBuilder.append(intent);
            Log.w("WakefulBroadcastReceiv.", stringBuilder.toString());
            return true;
        }
    }
}
