package android.support.v4.view;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.view.ViewConfiguration;
import java.lang.reflect.Method;

public final class ViewConfigurationCompat {
    private static final String TAG = "ViewConfigCompat";
    private static Method sGetScaledScrollFactorMethod;

    static {
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
        r1 = 25;
        if (r0 != r1) goto L_0x001b;
    L_0x0006:
        r0 = android.view.ViewConfiguration.class;	 Catch:{ Exception -> 0x0014 }
        r1 = "getScaledScrollFactor";	 Catch:{ Exception -> 0x0014 }
        r2 = 0;	 Catch:{ Exception -> 0x0014 }
        r2 = new java.lang.Class[r2];	 Catch:{ Exception -> 0x0014 }
        r0 = r0.getDeclaredMethod(r1, r2);	 Catch:{ Exception -> 0x0014 }
        sGetScaledScrollFactorMethod = r0;	 Catch:{ Exception -> 0x0014 }
        goto L_0x001b;
    L_0x0014:
        r0 = "ViewConfigCompat";
        r1 = "Could not find method getScaledScrollFactor() on ViewConfiguration";
        android.util.Log.i(r0, r1);
    L_0x001b:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewConfigurationCompat.<clinit>():void");
    }

    @Deprecated
    public static int getScaledPagingTouchSlop(ViewConfiguration viewConfiguration) {
        return viewConfiguration.getScaledPagingTouchSlop();
    }

    @Deprecated
    public static boolean hasPermanentMenuKey(ViewConfiguration viewConfiguration) {
        return viewConfiguration.hasPermanentMenuKey();
    }

    public static float getScaledHorizontalScrollFactor(@NonNull ViewConfiguration viewConfiguration, @NonNull Context context) {
        if (VERSION.SDK_INT >= 26) {
            return viewConfiguration.getScaledHorizontalScrollFactor();
        }
        return getLegacyScrollFactor(viewConfiguration, context);
    }

    public static float getScaledVerticalScrollFactor(@NonNull ViewConfiguration viewConfiguration, @NonNull Context context) {
        if (VERSION.SDK_INT >= 26) {
            return viewConfiguration.getScaledVerticalScrollFactor();
        }
        return getLegacyScrollFactor(viewConfiguration, context);
    }

    private static float getLegacyScrollFactor(android.view.ViewConfiguration r3, android.content.Context r4) {
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
        r1 = 25;
        if (r0 < r1) goto L_0x0022;
    L_0x0006:
        r0 = sGetScaledScrollFactorMethod;
        if (r0 == 0) goto L_0x0022;
    L_0x000a:
        r0 = sGetScaledScrollFactorMethod;	 Catch:{ Exception -> 0x001b }
        r1 = 0;	 Catch:{ Exception -> 0x001b }
        r1 = new java.lang.Object[r1];	 Catch:{ Exception -> 0x001b }
        r3 = r0.invoke(r3, r1);	 Catch:{ Exception -> 0x001b }
        r3 = (java.lang.Integer) r3;	 Catch:{ Exception -> 0x001b }
        r3 = r3.intValue();	 Catch:{ Exception -> 0x001b }
        r3 = (float) r3;
        return r3;
    L_0x001b:
        r3 = "ViewConfigCompat";
        r0 = "Could not find method getScaledScrollFactor() on ViewConfiguration";
        android.util.Log.i(r3, r0);
    L_0x0022:
        r3 = new android.util.TypedValue;
        r3.<init>();
        r0 = r4.getTheme();
        r1 = 16842829; // 0x101004d float:2.3693774E-38 double:8.321463E-317;
        r2 = 1;
        r0 = r0.resolveAttribute(r1, r3, r2);
        if (r0 == 0) goto L_0x0042;
    L_0x0035:
        r4 = r4.getResources();
        r4 = r4.getDisplayMetrics();
        r3 = r3.getDimension(r4);
        return r3;
    L_0x0042:
        r3 = 0;
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewConfigurationCompat.getLegacyScrollFactor(android.view.ViewConfiguration, android.content.Context):float");
    }

    public static int getScaledHoverSlop(ViewConfiguration viewConfiguration) {
        if (VERSION.SDK_INT >= 28) {
            return viewConfiguration.getScaledHoverSlop();
        }
        return viewConfiguration.getScaledTouchSlop() / 2;
    }

    public static boolean shouldShowMenuShortcutsWhenKeyboardPresent(ViewConfiguration viewConfiguration, @NonNull Context context) {
        if (VERSION.SDK_INT >= 28) {
            return viewConfiguration.shouldShowMenuShortcutsWhenKeyboardPresent();
        }
        viewConfiguration = context.getResources();
        context = viewConfiguration.getIdentifier("config_showMenuShortcutsWhenKeyboardPresent", "bool", "android");
        viewConfiguration = (context == null || viewConfiguration.getBoolean(context) == null) ? null : true;
        return viewConfiguration;
    }

    private ViewConfigurationCompat() {
    }
}
