package android.support.v4.widget;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class PopupWindowCompat {
    private static final String TAG = "PopupWindowCompatApi21";
    private static Method sGetWindowLayoutTypeMethod;
    private static boolean sGetWindowLayoutTypeMethodAttempted;
    private static Field sOverlapAnchorField;
    private static boolean sOverlapAnchorFieldAttempted;
    private static Method sSetWindowLayoutTypeMethod;
    private static boolean sSetWindowLayoutTypeMethodAttempted;

    private PopupWindowCompat() {
    }

    public static void showAsDropDown(@NonNull PopupWindow popupWindow, @NonNull View view, int i, int i2, int i3) {
        if (VERSION.SDK_INT >= 19) {
            popupWindow.showAsDropDown(view, i, i2, i3);
            return;
        }
        if ((GravityCompat.getAbsoluteGravity(i3, ViewCompat.getLayoutDirection(view)) & 7) == 5) {
            i -= popupWindow.getWidth() - view.getWidth();
        }
        popupWindow.showAsDropDown(view, i, i2);
    }

    public static void setOverlapAnchor(@NonNull PopupWindow popupWindow, boolean z) {
        if (VERSION.SDK_INT >= 23) {
            popupWindow.setOverlapAnchor(z);
        } else if (VERSION.SDK_INT >= 21) {
            if (!sOverlapAnchorFieldAttempted) {
                try {
                    sOverlapAnchorField = PopupWindow.class.getDeclaredField("mOverlapAnchor");
                    sOverlapAnchorField.setAccessible(true);
                } catch (Throwable e) {
                    Log.i(TAG, "Could not fetch mOverlapAnchor field from PopupWindow", e);
                }
                sOverlapAnchorFieldAttempted = true;
            }
            if (sOverlapAnchorField != null) {
                try {
                    sOverlapAnchorField.set(popupWindow, Boolean.valueOf(z));
                } catch (PopupWindow popupWindow2) {
                    Log.i(TAG, "Could not set overlap anchor field in PopupWindow", popupWindow2);
                }
            }
        }
    }

    public static boolean getOverlapAnchor(@NonNull PopupWindow popupWindow) {
        if (VERSION.SDK_INT >= 23) {
            return popupWindow.getOverlapAnchor();
        }
        if (VERSION.SDK_INT >= 21) {
            if (!sOverlapAnchorFieldAttempted) {
                try {
                    sOverlapAnchorField = PopupWindow.class.getDeclaredField("mOverlapAnchor");
                    sOverlapAnchorField.setAccessible(true);
                } catch (Throwable e) {
                    Log.i(TAG, "Could not fetch mOverlapAnchor field from PopupWindow", e);
                }
                sOverlapAnchorFieldAttempted = true;
            }
            if (sOverlapAnchorField != null) {
                try {
                    return ((Boolean) sOverlapAnchorField.get(popupWindow)).booleanValue();
                } catch (PopupWindow popupWindow2) {
                    Log.i(TAG, "Could not get overlap anchor field in PopupWindow", popupWindow2);
                }
            }
        }
        return null;
    }

    public static void setWindowLayoutType(@android.support.annotation.NonNull android.widget.PopupWindow r6, int r7) {
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
        r1 = 23;
        if (r0 < r1) goto L_0x000a;
    L_0x0006:
        r6.setWindowLayoutType(r7);
        return;
    L_0x000a:
        r0 = sSetWindowLayoutTypeMethodAttempted;
        r1 = 0;
        r2 = 1;
        if (r0 != 0) goto L_0x0027;
    L_0x0010:
        r0 = android.widget.PopupWindow.class;	 Catch:{ Exception -> 0x0025 }
        r3 = "setWindowLayoutType";	 Catch:{ Exception -> 0x0025 }
        r4 = new java.lang.Class[r2];	 Catch:{ Exception -> 0x0025 }
        r5 = java.lang.Integer.TYPE;	 Catch:{ Exception -> 0x0025 }
        r4[r1] = r5;	 Catch:{ Exception -> 0x0025 }
        r0 = r0.getDeclaredMethod(r3, r4);	 Catch:{ Exception -> 0x0025 }
        sSetWindowLayoutTypeMethod = r0;	 Catch:{ Exception -> 0x0025 }
        r0 = sSetWindowLayoutTypeMethod;	 Catch:{ Exception -> 0x0025 }
        r0.setAccessible(r2);	 Catch:{ Exception -> 0x0025 }
    L_0x0025:
        sSetWindowLayoutTypeMethodAttempted = r2;
    L_0x0027:
        r0 = sSetWindowLayoutTypeMethod;
        if (r0 == 0) goto L_0x0038;
    L_0x002b:
        r0 = sSetWindowLayoutTypeMethod;	 Catch:{ Exception -> 0x0038 }
        r2 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x0038 }
        r7 = java.lang.Integer.valueOf(r7);	 Catch:{ Exception -> 0x0038 }
        r2[r1] = r7;	 Catch:{ Exception -> 0x0038 }
        r0.invoke(r6, r2);	 Catch:{ Exception -> 0x0038 }
    L_0x0038:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.PopupWindowCompat.setWindowLayoutType(android.widget.PopupWindow, int):void");
    }

    public static int getWindowLayoutType(@android.support.annotation.NonNull android.widget.PopupWindow r5) {
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
        r1 = 23;
        if (r0 < r1) goto L_0x000b;
    L_0x0006:
        r5 = r5.getWindowLayoutType();
        return r5;
    L_0x000b:
        r0 = sGetWindowLayoutTypeMethodAttempted;
        r1 = 0;
        if (r0 != 0) goto L_0x0024;
    L_0x0010:
        r0 = 1;
        r2 = android.widget.PopupWindow.class;	 Catch:{ Exception -> 0x0022 }
        r3 = "getWindowLayoutType";	 Catch:{ Exception -> 0x0022 }
        r4 = new java.lang.Class[r1];	 Catch:{ Exception -> 0x0022 }
        r2 = r2.getDeclaredMethod(r3, r4);	 Catch:{ Exception -> 0x0022 }
        sGetWindowLayoutTypeMethod = r2;	 Catch:{ Exception -> 0x0022 }
        r2 = sGetWindowLayoutTypeMethod;	 Catch:{ Exception -> 0x0022 }
        r2.setAccessible(r0);	 Catch:{ Exception -> 0x0022 }
    L_0x0022:
        sGetWindowLayoutTypeMethodAttempted = r0;
    L_0x0024:
        r0 = sGetWindowLayoutTypeMethod;
        if (r0 == 0) goto L_0x0037;
    L_0x0028:
        r0 = sGetWindowLayoutTypeMethod;	 Catch:{ Exception -> 0x0037 }
        r2 = new java.lang.Object[r1];	 Catch:{ Exception -> 0x0037 }
        r5 = r0.invoke(r5, r2);	 Catch:{ Exception -> 0x0037 }
        r5 = (java.lang.Integer) r5;	 Catch:{ Exception -> 0x0037 }
        r5 = r5.intValue();	 Catch:{ Exception -> 0x0037 }
        return r5;
    L_0x0037:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.PopupWindowCompat.getWindowLayoutType(android.widget.PopupWindow):int");
    }
}
