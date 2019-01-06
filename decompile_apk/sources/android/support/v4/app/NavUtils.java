package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class NavUtils {
    public static final String PARENT_ACTIVITY = "android.support.PARENT_ACTIVITY";
    private static final String TAG = "NavUtils";

    public static boolean shouldUpRecreateTask(@NonNull Activity activity, @NonNull Intent intent) {
        if (VERSION.SDK_INT >= 16) {
            return activity.shouldUpRecreateTask(intent);
        }
        activity = activity.getIntent().getAction();
        activity = (activity == null || activity.equals("android.intent.action.MAIN") != null) ? null : true;
        return activity;
    }

    public static void navigateUpFromSameTask(@NonNull Activity activity) {
        Intent parentActivityIntent = getParentActivityIntent(activity);
        if (parentActivityIntent != null) {
            navigateUpTo(activity, parentActivityIntent);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Activity ");
        stringBuilder.append(activity.getClass().getSimpleName());
        stringBuilder.append(" does not have a parent activity name specified.");
        stringBuilder.append(" (Did you forget to add the android.support.PARENT_ACTIVITY <meta-data> ");
        stringBuilder.append(" element in your manifest?)");
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public static void navigateUpTo(@NonNull Activity activity, @NonNull Intent intent) {
        if (VERSION.SDK_INT >= 16) {
            activity.navigateUpTo(intent);
            return;
        }
        intent.addFlags(67108864);
        activity.startActivity(intent);
        activity.finish();
    }

    @android.support.annotation.Nullable
    public static android.content.Intent getParentActivityIntent(@android.support.annotation.NonNull android.app.Activity r4) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 16;
        if (r0 < r1) goto L_0x000d;
    L_0x0006:
        r0 = r4.getParentActivityIntent();
        if (r0 == 0) goto L_0x000d;
    L_0x000c:
        return r0;
    L_0x000d:
        r0 = getParentActivityName(r4);
        r1 = 0;
        if (r0 != 0) goto L_0x0015;
    L_0x0014:
        return r1;
    L_0x0015:
        r2 = new android.content.ComponentName;
        r2.<init>(r4, r0);
        r4 = getParentActivityName(r4, r2);	 Catch:{ NameNotFoundException -> 0x002f }
        if (r4 != 0) goto L_0x0025;	 Catch:{ NameNotFoundException -> 0x002f }
    L_0x0020:
        r4 = android.content.Intent.makeMainActivity(r2);	 Catch:{ NameNotFoundException -> 0x002f }
        goto L_0x002e;	 Catch:{ NameNotFoundException -> 0x002f }
    L_0x0025:
        r4 = new android.content.Intent;	 Catch:{ NameNotFoundException -> 0x002f }
        r4.<init>();	 Catch:{ NameNotFoundException -> 0x002f }
        r4 = r4.setComponent(r2);	 Catch:{ NameNotFoundException -> 0x002f }
    L_0x002e:
        return r4;
    L_0x002f:
        r4 = "NavUtils";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "getParentActivityIntent: bad parentActivityName '";
        r2.append(r3);
        r2.append(r0);
        r0 = "' in manifest";
        r2.append(r0);
        r0 = r2.toString();
        android.util.Log.e(r4, r0);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.NavUtils.getParentActivityIntent(android.app.Activity):android.content.Intent");
    }

    @Nullable
    public static Intent getParentActivityIntent(@NonNull Context context, @NonNull Class<?> cls) throws NameNotFoundException {
        cls = getParentActivityName(context, new ComponentName(context, cls));
        if (cls == null) {
            return null;
        }
        ComponentName componentName = new ComponentName(context, cls);
        if (getParentActivityName(context, componentName) == null) {
            context = Intent.makeMainActivity(componentName);
        } else {
            context = new Intent().setComponent(componentName);
        }
        return context;
    }

    @Nullable
    public static Intent getParentActivityIntent(@NonNull Context context, @NonNull ComponentName componentName) throws NameNotFoundException {
        String parentActivityName = getParentActivityName(context, componentName);
        if (parentActivityName == null) {
            return null;
        }
        ComponentName componentName2 = new ComponentName(componentName.getPackageName(), parentActivityName);
        if (getParentActivityName(context, componentName2) == null) {
            context = Intent.makeMainActivity(componentName2);
        } else {
            context = new Intent().setComponent(componentName2);
        }
        return context;
    }

    @Nullable
    public static String getParentActivityName(@NonNull Activity activity) {
        try {
            return getParentActivityName(activity, activity.getComponentName());
        } catch (Activity activity2) {
            throw new IllegalArgumentException(activity2);
        }
    }

    @Nullable
    public static String getParentActivityName(@NonNull Context context, @NonNull ComponentName componentName) throws NameNotFoundException {
        componentName = context.getPackageManager().getActivityInfo(componentName, 128);
        if (VERSION.SDK_INT >= 16) {
            String str = componentName.parentActivityName;
            if (str != null) {
                return str;
            }
        }
        if (componentName.metaData == null) {
            return null;
        }
        componentName = componentName.metaData.getString(PARENT_ACTIVITY);
        if (componentName == null) {
            return null;
        }
        if (componentName.charAt(0) == '.') {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(context.getPackageName());
            stringBuilder.append(componentName);
            componentName = stringBuilder.toString();
        }
        return componentName;
    }

    private NavUtils() {
    }
}
