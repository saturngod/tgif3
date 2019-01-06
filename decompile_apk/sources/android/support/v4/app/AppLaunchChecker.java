package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.content.IntentCompat;

public class AppLaunchChecker {
    private static final String KEY_STARTED_FROM_LAUNCHER = "startedFromLauncher";
    private static final String SHARED_PREFS_NAME = "android.support.AppLaunchChecker";

    public static boolean hasStartedFromLauncher(@NonNull Context context) {
        return context.getSharedPreferences(SHARED_PREFS_NAME, 0).getBoolean(KEY_STARTED_FROM_LAUNCHER, false);
    }

    public static void onActivityCreate(@NonNull Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(SHARED_PREFS_NAME, 0);
        if (!sharedPreferences.getBoolean(KEY_STARTED_FROM_LAUNCHER, false)) {
            activity = activity.getIntent();
            if (activity != null && "android.intent.action.MAIN".equals(activity.getAction()) && (activity.hasCategory("android.intent.category.LAUNCHER") || activity.hasCategory(IntentCompat.CATEGORY_LEANBACK_LAUNCHER) != null)) {
                sharedPreferences.edit().putBoolean(KEY_STARTED_FROM_LAUNCHER, true).apply();
            }
        }
    }
}
