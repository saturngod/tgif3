package android.support.v4.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.drawable.Drawable;
import android.icu.text.DecimalFormatSymbols;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.StyleRes;
import android.support.v4.text.PrecomputedTextCompat;
import android.support.v4.text.PrecomputedTextCompat.Params;
import android.support.v4.text.PrecomputedTextCompat.Params.Builder;
import android.support.v4.util.Preconditions;
import android.text.Editable;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.PasswordTransformationMethod;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class TextViewCompat {
    public static final int AUTO_SIZE_TEXT_TYPE_NONE = 0;
    public static final int AUTO_SIZE_TEXT_TYPE_UNIFORM = 1;
    private static final int LINES = 1;
    private static final String LOG_TAG = "TextViewCompat";
    private static Field sMaxModeField;
    private static boolean sMaxModeFieldFetched;
    private static Field sMaximumField;
    private static boolean sMaximumFieldFetched;
    private static Field sMinModeField;
    private static boolean sMinModeFieldFetched;
    private static Field sMinimumField;
    private static boolean sMinimumFieldFetched;

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AutoSizeTextType {
    }

    @RequiresApi(26)
    private static class OreoCallback implements Callback {
        private static final int MENU_ITEM_ORDER_PROCESS_TEXT_INTENT_ACTIONS_START = 100;
        private final Callback mCallback;
        private boolean mCanUseMenuBuilderReferences;
        private boolean mInitializedMenuBuilderReferences = null;
        private Class mMenuBuilderClass;
        private Method mMenuBuilderRemoveItemAtMethod;
        private final TextView mTextView;

        OreoCallback(Callback callback, TextView textView) {
            this.mCallback = callback;
            this.mTextView = textView;
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return this.mCallback.onCreateActionMode(actionMode, menu);
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            recomputeProcessTextMenuItems(menu);
            return this.mCallback.onPrepareActionMode(actionMode, menu);
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.mCallback.onActionItemClicked(actionMode, menuItem);
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            this.mCallback.onDestroyActionMode(actionMode);
        }

        private void recomputeProcessTextMenuItems(android.view.Menu r9) {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
            /*
            r8 = this;
            r0 = r8.mTextView;
            r0 = r0.getContext();
            r1 = r0.getPackageManager();
            r2 = r8.mInitializedMenuBuilderReferences;
            r3 = 0;
            r4 = 1;
            if (r2 != 0) goto L_0x0034;
        L_0x0010:
            r8.mInitializedMenuBuilderReferences = r4;
            r2 = "com.android.internal.view.menu.MenuBuilder";	 Catch:{ ClassNotFoundException -> 0x002d, ClassNotFoundException -> 0x002d }
            r2 = java.lang.Class.forName(r2);	 Catch:{ ClassNotFoundException -> 0x002d, ClassNotFoundException -> 0x002d }
            r8.mMenuBuilderClass = r2;	 Catch:{ ClassNotFoundException -> 0x002d, ClassNotFoundException -> 0x002d }
            r2 = r8.mMenuBuilderClass;	 Catch:{ ClassNotFoundException -> 0x002d, ClassNotFoundException -> 0x002d }
            r5 = "removeItemAt";	 Catch:{ ClassNotFoundException -> 0x002d, ClassNotFoundException -> 0x002d }
            r6 = new java.lang.Class[r4];	 Catch:{ ClassNotFoundException -> 0x002d, ClassNotFoundException -> 0x002d }
            r7 = java.lang.Integer.TYPE;	 Catch:{ ClassNotFoundException -> 0x002d, ClassNotFoundException -> 0x002d }
            r6[r3] = r7;	 Catch:{ ClassNotFoundException -> 0x002d, ClassNotFoundException -> 0x002d }
            r2 = r2.getDeclaredMethod(r5, r6);	 Catch:{ ClassNotFoundException -> 0x002d, ClassNotFoundException -> 0x002d }
            r8.mMenuBuilderRemoveItemAtMethod = r2;	 Catch:{ ClassNotFoundException -> 0x002d, ClassNotFoundException -> 0x002d }
            r8.mCanUseMenuBuilderReferences = r4;	 Catch:{ ClassNotFoundException -> 0x002d, ClassNotFoundException -> 0x002d }
            goto L_0x0034;
        L_0x002d:
            r2 = 0;
            r8.mMenuBuilderClass = r2;
            r8.mMenuBuilderRemoveItemAtMethod = r2;
            r8.mCanUseMenuBuilderReferences = r3;
        L_0x0034:
            r2 = r8.mCanUseMenuBuilderReferences;	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
            if (r2 == 0) goto L_0x0043;	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
        L_0x0038:
            r2 = r8.mMenuBuilderClass;	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
            r2 = r2.isInstance(r9);	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
            if (r2 == 0) goto L_0x0043;	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
        L_0x0040:
            r2 = r8.mMenuBuilderRemoveItemAtMethod;	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
            goto L_0x0053;	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
        L_0x0043:
            r2 = r9.getClass();	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
            r5 = "removeItemAt";	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
            r6 = new java.lang.Class[r4];	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
            r7 = java.lang.Integer.TYPE;	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
            r6[r3] = r7;	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
            r2 = r2.getDeclaredMethod(r5, r6);	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
        L_0x0053:
            r5 = r9.size();	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
            r5 = r5 - r4;	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
        L_0x0058:
            if (r5 < 0) goto L_0x0082;	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
        L_0x005a:
            r6 = r9.getItem(r5);	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
            r7 = r6.getIntent();	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
            if (r7 == 0) goto L_0x007f;	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
        L_0x0064:
            r7 = "android.intent.action.PROCESS_TEXT";	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
            r6 = r6.getIntent();	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
            r6 = r6.getAction();	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
            r6 = r7.equals(r6);	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
            if (r6 == 0) goto L_0x007f;	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
        L_0x0074:
            r6 = new java.lang.Object[r4];	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
            r7 = java.lang.Integer.valueOf(r5);	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
            r6[r3] = r7;	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
            r2.invoke(r9, r6);	 Catch:{ NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae, NoSuchMethodException -> 0x00ae }
        L_0x007f:
            r5 = r5 + -1;
            goto L_0x0058;
        L_0x0082:
            r0 = r8.getSupportedActivities(r0, r1);
            r2 = 0;
        L_0x0087:
            r5 = r0.size();
            if (r2 >= r5) goto L_0x00ad;
        L_0x008d:
            r5 = r0.get(r2);
            r5 = (android.content.pm.ResolveInfo) r5;
            r6 = r2 + 100;
            r7 = r5.loadLabel(r1);
            r6 = r9.add(r3, r3, r6, r7);
            r7 = r8.mTextView;
            r5 = r8.createProcessTextIntentForResolveInfo(r5, r7);
            r5 = r6.setIntent(r5);
            r5.setShowAsAction(r4);
            r2 = r2 + 1;
            goto L_0x0087;
        L_0x00ad:
            return;
        L_0x00ae:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.TextViewCompat.OreoCallback.recomputeProcessTextMenuItems(android.view.Menu):void");
        }

        private List<ResolveInfo> getSupportedActivities(Context context, PackageManager packageManager) {
            List<ResolveInfo> arrayList = new ArrayList();
            if (!(context instanceof Activity)) {
                return arrayList;
            }
            for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(createProcessTextIntent(), 0)) {
                if (isSupportedActivity(resolveInfo, context)) {
                    arrayList.add(resolveInfo);
                }
            }
            return arrayList;
        }

        private boolean isSupportedActivity(ResolveInfo resolveInfo, Context context) {
            boolean z = true;
            if (context.getPackageName().equals(resolveInfo.activityInfo.packageName)) {
                return true;
            }
            if (!resolveInfo.activityInfo.exported) {
                return false;
            }
            if (resolveInfo.activityInfo.permission != null) {
                if (context.checkSelfPermission(resolveInfo.activityInfo.permission) != null) {
                    z = false;
                }
            }
            return z;
        }

        private Intent createProcessTextIntentForResolveInfo(ResolveInfo resolveInfo, TextView textView) {
            return createProcessTextIntent().putExtra("android.intent.extra.PROCESS_TEXT_READONLY", isEditable(textView) ^ 1).setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        }

        private boolean isEditable(TextView textView) {
            return ((textView instanceof Editable) && textView.onCheckIsTextEditor() && textView.isEnabled() != null) ? true : null;
        }

        private Intent createProcessTextIntent() {
            return new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain");
        }
    }

    private TextViewCompat() {
    }

    private static java.lang.reflect.Field retrieveField(java.lang.String r4) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = 0;
        r1 = android.widget.TextView.class;	 Catch:{ NoSuchFieldException -> 0x000c }
        r1 = r1.getDeclaredField(r4);	 Catch:{ NoSuchFieldException -> 0x000c }
        r0 = 1;
        r1.setAccessible(r0);	 Catch:{ NoSuchFieldException -> 0x000d }
        goto L_0x0028;
    L_0x000c:
        r1 = r0;
    L_0x000d:
        r0 = "TextViewCompat";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Could not retrieve ";
        r2.append(r3);
        r2.append(r4);
        r4 = " field.";
        r2.append(r4);
        r4 = r2.toString();
        android.util.Log.e(r0, r4);
    L_0x0028:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.TextViewCompat.retrieveField(java.lang.String):java.lang.reflect.Field");
    }

    private static int retrieveIntFromField(java.lang.reflect.Field r2, android.widget.TextView r3) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r3 = r2.getInt(r3);	 Catch:{ IllegalAccessException -> 0x0005 }
        return r3;
    L_0x0005:
        r3 = "TextViewCompat";
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Could not retrieve value of ";
        r0.append(r1);
        r2 = r2.getName();
        r0.append(r2);
        r2 = " field.";
        r0.append(r2);
        r2 = r0.toString();
        android.util.Log.d(r3, r2);
        r2 = -1;
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.TextViewCompat.retrieveIntFromField(java.lang.reflect.Field, android.widget.TextView):int");
    }

    public static void setCompoundDrawablesRelative(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
        if (VERSION.SDK_INT >= 18) {
            textView.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        } else if (VERSION.SDK_INT >= 17) {
            Object obj = 1;
            if (textView.getLayoutDirection() != 1) {
                obj = null;
            }
            Drawable drawable5 = obj != null ? drawable3 : drawable;
            if (obj == null) {
                drawable = drawable3;
            }
            textView.setCompoundDrawables(drawable5, drawable2, drawable, drawable4);
        } else {
            textView.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        }
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
        if (VERSION.SDK_INT >= 18) {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        } else if (VERSION.SDK_INT >= 17) {
            Object obj = 1;
            if (textView.getLayoutDirection() != 1) {
                obj = null;
            }
            Drawable drawable5 = obj != null ? drawable3 : drawable;
            if (obj == null) {
                drawable = drawable3;
            }
            textView.setCompoundDrawablesWithIntrinsicBounds(drawable5, drawable2, drawable, drawable4);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        }
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @DrawableRes int i, @DrawableRes int i2, @DrawableRes int i3, @DrawableRes int i4) {
        if (VERSION.SDK_INT >= 18) {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(i, i2, i3, i4);
        } else if (VERSION.SDK_INT >= 17) {
            Object obj = 1;
            if (textView.getLayoutDirection() != 1) {
                obj = null;
            }
            int i5 = obj != null ? i3 : i;
            if (obj == null) {
                i = i3;
            }
            textView.setCompoundDrawablesWithIntrinsicBounds(i5, i2, i, i4);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(i, i2, i3, i4);
        }
    }

    public static int getMaxLines(@NonNull TextView textView) {
        if (VERSION.SDK_INT >= 16) {
            return textView.getMaxLines();
        }
        if (!sMaxModeFieldFetched) {
            sMaxModeField = retrieveField("mMaxMode");
            sMaxModeFieldFetched = true;
        }
        if (sMaxModeField != null && retrieveIntFromField(sMaxModeField, textView) == 1) {
            if (!sMaximumFieldFetched) {
                sMaximumField = retrieveField("mMaximum");
                sMaximumFieldFetched = true;
            }
            if (sMaximumField != null) {
                return retrieveIntFromField(sMaximumField, textView);
            }
        }
        return -1;
    }

    public static int getMinLines(@NonNull TextView textView) {
        if (VERSION.SDK_INT >= 16) {
            return textView.getMinLines();
        }
        if (!sMinModeFieldFetched) {
            sMinModeField = retrieveField("mMinMode");
            sMinModeFieldFetched = true;
        }
        if (sMinModeField != null && retrieveIntFromField(sMinModeField, textView) == 1) {
            if (!sMinimumFieldFetched) {
                sMinimumField = retrieveField("mMinimum");
                sMinimumFieldFetched = true;
            }
            if (sMinimumField != null) {
                return retrieveIntFromField(sMinimumField, textView);
            }
        }
        return -1;
    }

    public static void setTextAppearance(@NonNull TextView textView, @StyleRes int i) {
        if (VERSION.SDK_INT >= 23) {
            textView.setTextAppearance(i);
        } else {
            textView.setTextAppearance(textView.getContext(), i);
        }
    }

    @NonNull
    public static Drawable[] getCompoundDrawablesRelative(@NonNull TextView textView) {
        if (VERSION.SDK_INT >= 18) {
            return textView.getCompoundDrawablesRelative();
        }
        if (VERSION.SDK_INT < 17) {
            return textView.getCompoundDrawables();
        }
        Object obj = 1;
        if (textView.getLayoutDirection() != 1) {
            obj = null;
        }
        textView = textView.getCompoundDrawables();
        if (obj != null) {
            obj = textView[2];
            Object obj2 = textView[0];
            textView[0] = obj;
            textView[2] = obj2;
        }
        return textView;
    }

    public static void setAutoSizeTextTypeWithDefaults(@NonNull TextView textView, int i) {
        if (VERSION.SDK_INT >= 27) {
            textView.setAutoSizeTextTypeWithDefaults(i);
        } else if (textView instanceof AutoSizeableTextView) {
            ((AutoSizeableTextView) textView).setAutoSizeTextTypeWithDefaults(i);
        }
    }

    public static void setAutoSizeTextTypeUniformWithConfiguration(@NonNull TextView textView, int i, int i2, int i3, int i4) throws IllegalArgumentException {
        if (VERSION.SDK_INT >= 27) {
            textView.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
        } else if (textView instanceof AutoSizeableTextView) {
            ((AutoSizeableTextView) textView).setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
        }
    }

    public static void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull TextView textView, @NonNull int[] iArr, int i) throws IllegalArgumentException {
        if (VERSION.SDK_INT >= 27) {
            textView.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
        } else if (textView instanceof AutoSizeableTextView) {
            ((AutoSizeableTextView) textView).setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
        }
    }

    public static int getAutoSizeTextType(@NonNull TextView textView) {
        if (VERSION.SDK_INT >= 27) {
            return textView.getAutoSizeTextType();
        }
        return textView instanceof AutoSizeableTextView ? ((AutoSizeableTextView) textView).getAutoSizeTextType() : null;
    }

    public static int getAutoSizeStepGranularity(@NonNull TextView textView) {
        if (VERSION.SDK_INT >= 27) {
            return textView.getAutoSizeStepGranularity();
        }
        return textView instanceof AutoSizeableTextView ? ((AutoSizeableTextView) textView).getAutoSizeStepGranularity() : -1;
    }

    public static int getAutoSizeMinTextSize(@NonNull TextView textView) {
        if (VERSION.SDK_INT >= 27) {
            return textView.getAutoSizeMinTextSize();
        }
        return textView instanceof AutoSizeableTextView ? ((AutoSizeableTextView) textView).getAutoSizeMinTextSize() : -1;
    }

    public static int getAutoSizeMaxTextSize(@NonNull TextView textView) {
        if (VERSION.SDK_INT >= 27) {
            return textView.getAutoSizeMaxTextSize();
        }
        return textView instanceof AutoSizeableTextView ? ((AutoSizeableTextView) textView).getAutoSizeMaxTextSize() : -1;
    }

    @NonNull
    public static int[] getAutoSizeTextAvailableSizes(@NonNull TextView textView) {
        if (VERSION.SDK_INT >= 27) {
            return textView.getAutoSizeTextAvailableSizes();
        }
        if (textView instanceof AutoSizeableTextView) {
            return ((AutoSizeableTextView) textView).getAutoSizeTextAvailableSizes();
        }
        return new int[null];
    }

    public static void setCustomSelectionActionModeCallback(@NonNull TextView textView, @NonNull Callback callback) {
        textView.setCustomSelectionActionModeCallback(wrapCustomSelectionActionModeCallback(textView, callback));
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    public static Callback wrapCustomSelectionActionModeCallback(@NonNull TextView textView, @NonNull Callback callback) {
        if (VERSION.SDK_INT >= 26 && VERSION.SDK_INT <= 27) {
            if (!(callback instanceof OreoCallback)) {
                return new OreoCallback(callback, textView);
            }
        }
        return callback;
    }

    public static void setFirstBaselineToTopHeight(@NonNull TextView textView, @Px @IntRange(from = 0) int i) {
        Preconditions.checkArgumentNonnegative(i);
        if (VERSION.SDK_INT >= 28) {
            textView.setFirstBaselineToTopHeight(i);
            return;
        }
        int i2;
        FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        if (VERSION.SDK_INT >= 16) {
            if (!textView.getIncludeFontPadding()) {
                i2 = fontMetricsInt.ascent;
                if (i > Math.abs(i2)) {
                    textView.setPadding(textView.getPaddingLeft(), i - (-i2), textView.getPaddingRight(), textView.getPaddingBottom());
                }
            }
        }
        i2 = fontMetricsInt.top;
        if (i > Math.abs(i2)) {
            textView.setPadding(textView.getPaddingLeft(), i - (-i2), textView.getPaddingRight(), textView.getPaddingBottom());
        }
    }

    public static void setLastBaselineToBottomHeight(@NonNull TextView textView, @Px @IntRange(from = 0) int i) {
        int i2;
        Preconditions.checkArgumentNonnegative(i);
        FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        if (VERSION.SDK_INT >= 16) {
            if (!textView.getIncludeFontPadding()) {
                i2 = fontMetricsInt.descent;
                if (i > Math.abs(i2)) {
                    textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), i - i2);
                }
            }
        }
        i2 = fontMetricsInt.bottom;
        if (i > Math.abs(i2)) {
            textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), i - i2);
        }
    }

    public static int getFirstBaselineToTopHeight(@NonNull TextView textView) {
        return textView.getPaddingTop() - textView.getPaint().getFontMetricsInt().top;
    }

    public static int getLastBaselineToBottomHeight(@NonNull TextView textView) {
        return textView.getPaddingBottom() + textView.getPaint().getFontMetricsInt().bottom;
    }

    public static void setLineHeight(@NonNull TextView textView, @Px @IntRange(from = 0) int i) {
        Preconditions.checkArgumentNonnegative(i);
        int fontMetricsInt = textView.getPaint().getFontMetricsInt(null);
        if (i != fontMetricsInt) {
            textView.setLineSpacing((float) (i - fontMetricsInt), 1.0f);
        }
    }

    @NonNull
    public static Params getTextMetricsParams(@NonNull TextView textView) {
        if (VERSION.SDK_INT >= 28) {
            return new Params(textView.getTextMetricsParams());
        }
        Builder builder = new Builder(new TextPaint(textView.getPaint()));
        if (VERSION.SDK_INT >= 23) {
            builder.setBreakStrategy(textView.getBreakStrategy());
            builder.setHyphenationFrequency(textView.getHyphenationFrequency());
        }
        if (VERSION.SDK_INT >= 18) {
            builder.setTextDirection(getTextDirectionHeuristic(textView));
        }
        return builder.build();
    }

    public static void setTextMetricsParams(@NonNull TextView textView, @NonNull Params params) {
        if (VERSION.SDK_INT >= 18) {
            textView.setTextDirection(getTextDirection(params.getTextDirection()));
        }
        if (VERSION.SDK_INT < 23) {
            float textScaleX = params.getTextPaint().getTextScaleX();
            textView.getPaint().set(params.getTextPaint());
            if (textScaleX == textView.getTextScaleX()) {
                textView.setTextScaleX((textScaleX / 1073741824) + 1.0f);
            }
            textView.setTextScaleX(textScaleX);
            return;
        }
        textView.getPaint().set(params.getTextPaint());
        textView.setBreakStrategy(params.getBreakStrategy());
        textView.setHyphenationFrequency(params.getHyphenationFrequency());
    }

    public static void setPrecomputedText(@NonNull TextView textView, @NonNull PrecomputedTextCompat precomputedTextCompat) {
        if (VERSION.SDK_INT >= 28) {
            textView.setText(precomputedTextCompat.getPrecomputedText());
        } else if (getTextMetricsParams(textView).equals(precomputedTextCompat.getParams())) {
            textView.setText(precomputedTextCompat);
        } else {
            throw new IllegalArgumentException("Given text can not be applied to TextView.");
        }
    }

    @RequiresApi(18)
    private static TextDirectionHeuristic getTextDirectionHeuristic(@NonNull TextView textView) {
        if (textView.getTransformationMethod() instanceof PasswordTransformationMethod) {
            return TextDirectionHeuristics.LTR;
        }
        int i = 0;
        if (VERSION.SDK_INT < 28 || (textView.getInputType() & 15) != 3) {
            if (textView.getLayoutDirection() == 1) {
                i = 1;
            }
            switch (textView.getTextDirection()) {
                case 2:
                    return TextDirectionHeuristics.ANYRTL_LTR;
                case 3:
                    return TextDirectionHeuristics.LTR;
                case 4:
                    return TextDirectionHeuristics.RTL;
                case 5:
                    return TextDirectionHeuristics.LOCALE;
                case 6:
                    return TextDirectionHeuristics.FIRSTSTRONG_LTR;
                case 7:
                    return TextDirectionHeuristics.FIRSTSTRONG_RTL;
                default:
                    return i != 0 ? TextDirectionHeuristics.FIRSTSTRONG_RTL : TextDirectionHeuristics.FIRSTSTRONG_LTR;
            }
        }
        textView = Character.getDirectionality(DecimalFormatSymbols.getInstance(textView.getTextLocale()).getDigitStrings()[0].codePointAt(0));
        if (textView != 1) {
            if (textView != 2) {
                return TextDirectionHeuristics.LTR;
            }
        }
        return TextDirectionHeuristics.RTL;
    }

    @RequiresApi(18)
    private static int getTextDirection(@NonNull TextDirectionHeuristic textDirectionHeuristic) {
        if (textDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_RTL || textDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_LTR) {
            return 1;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.ANYRTL_LTR) {
            return 2;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.LTR) {
            return 3;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.RTL) {
            return 4;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.LOCALE) {
            return 5;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_LTR) {
            return 6;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_RTL) {
            return 7;
        }
        return 1;
    }
}
