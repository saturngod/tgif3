package android.support.v4.app;

import android.app.AlarmManager;
import android.app.AlarmManager.AlarmClockInfo;
import android.app.PendingIntent;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;

public final class AlarmManagerCompat {
    public static void setAlarmClock(@NonNull AlarmManager alarmManager, long j, @NonNull PendingIntent pendingIntent, @NonNull PendingIntent pendingIntent2) {
        if (VERSION.SDK_INT >= 21) {
            alarmManager.setAlarmClock(new AlarmClockInfo(j, pendingIntent), pendingIntent2);
        } else {
            setExact(alarmManager, null, j, pendingIntent2);
        }
    }

    public static void setAndAllowWhileIdle(@NonNull AlarmManager alarmManager, int i, long j, @NonNull PendingIntent pendingIntent) {
        if (VERSION.SDK_INT >= 23) {
            alarmManager.setAndAllowWhileIdle(i, j, pendingIntent);
        } else {
            alarmManager.set(i, j, pendingIntent);
        }
    }

    public static void setExact(@NonNull AlarmManager alarmManager, int i, long j, @NonNull PendingIntent pendingIntent) {
        if (VERSION.SDK_INT >= 19) {
            alarmManager.setExact(i, j, pendingIntent);
        } else {
            alarmManager.set(i, j, pendingIntent);
        }
    }

    public static void setExactAndAllowWhileIdle(@NonNull AlarmManager alarmManager, int i, long j, @NonNull PendingIntent pendingIntent) {
        if (VERSION.SDK_INT >= 23) {
            alarmManager.setExactAndAllowWhileIdle(i, j, pendingIntent);
        } else {
            setExact(alarmManager, i, j, pendingIntent);
        }
    }

    private AlarmManagerCompat() {
    }
}
