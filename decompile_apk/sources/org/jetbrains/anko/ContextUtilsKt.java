package org.jetbrains.anko;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import java.io.Serializable;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000z\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a=\u00106\u001a\u0002072.\u00108\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020;\u0012\u0006\u0012\u0004\u0018\u00010<0:09\"\u0010\u0012\u0004\u0012\u00020;\u0012\u0006\u0012\u0004\u0018\u00010<0:H\u0007¢\u0006\u0002\u0010=\u001a(\u0010>\u001a\u0002H?\"\n\b\u0000\u0010?\u0018\u0001*\u00020\u0015*\u00020\u00012\b\b\u0001\u0010@\u001a\u00020AH\b¢\u0006\u0002\u0010B\u001a(\u0010>\u001a\u0002H?\"\n\b\u0000\u0010?\u0018\u0001*\u00020\u0015*\u00020C2\b\b\u0001\u0010@\u001a\u00020AH\b¢\u0006\u0002\u0010D\u001a(\u0010>\u001a\u0002H?\"\n\b\u0000\u0010?\u0018\u0001*\u00020\u0015*\u00020\u00062\b\b\u0001\u0010@\u001a\u00020AH\b¢\u0006\u0002\u0010E\u001a(\u0010>\u001a\u0002H?\"\n\b\u0000\u0010?\u0018\u0001*\u00020\u0015*\u00020\u00152\b\b\u0001\u0010@\u001a\u00020AH\b¢\u0006\u0002\u0010F\u001a*\u0010G\u001a\u0004\u0018\u0001H?\"\n\b\u0000\u0010?\u0018\u0001*\u00020\u0015*\u00020\u00012\b\b\u0001\u0010@\u001a\u00020AH\b¢\u0006\u0002\u0010B\u001a*\u0010G\u001a\u0004\u0018\u0001H?\"\n\b\u0000\u0010?\u0018\u0001*\u00020\u0015*\u00020C2\b\b\u0001\u0010@\u001a\u00020AH\b¢\u0006\u0002\u0010D\u001a*\u0010G\u001a\u0004\u0018\u0001H?\"\n\b\u0000\u0010?\u0018\u0001*\u00020\u0015*\u00020\u00062\b\b\u0001\u0010@\u001a\u00020AH\b¢\u0006\u0002\u0010E\u001a*\u0010G\u001a\u0004\u0018\u0001H?\"\n\b\u0000\u0010?\u0018\u0001*\u00020\u0015*\u00020\u00152\b\b\u0001\u0010@\u001a\u00020AH\b¢\u0006\u0002\u0010F\u001aL\u0010H\u001a\u0002H?\"\b\b\u0000\u0010?*\u00020\u0006*\u0002H?2.\u00108\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020;\u0012\u0006\u0012\u0004\u0018\u00010<0:09\"\u0010\u0012\u0004\u0012\u00020;\u0012\u0006\u0012\u0004\u0018\u00010<0:H\b¢\u0006\u0002\u0010I\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00018Æ\u0002X\u0004¢\u0006\f\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00068Æ\u0002X\u0004¢\u0006\f\u0012\u0004\b\u0002\u0010\u0007\u001a\u0004\b\u0004\u0010\b\"\u001a\u0010\t\u001a\u00020\n*\u0006\u0012\u0002\b\u00030\u000b8Æ\u0002¢\u0006\u0006\u001a\u0004\b\f\u0010\r\"\u0016\u0010\u000e\u001a\u00020\u000f*\u00020\u00108Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012\"\u001a\u0010\u000e\u001a\u00020\u000f*\u0006\u0012\u0002\b\u00030\u000b8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0013\"\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u0015*\u00020\u00018Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017\"\u001f\u0010\u0018\u001a\u00020\u0010*\u00020\u00068Æ\u0002X\u0004¢\u0006\f\u0012\u0004\b\u0019\u0010\u0007\u001a\u0004\b\u001a\u0010\u001b\"\u001f\u0010\u0018\u001a\u00020\u0010*\u00020\u00108Æ\u0002X\u0004¢\u0006\f\u0012\u0004\b\u0019\u0010\u001c\u001a\u0004\b\u001a\u0010\u001d\"\u001f\u0010\u001e\u001a\u00020\u001f*\u00020\u00068Æ\u0002X\u0004¢\u0006\f\u0012\u0004\b \u0010\u0007\u001a\u0004\b!\u0010\"\"\u0016\u0010\u001e\u001a\u00020\u001f*\u00020\u00108Æ\u0002¢\u0006\u0006\u001a\u0004\b!\u0010#\"\u001a\u0010\u001e\u001a\u00020\u001f*\u0006\u0012\u0002\b\u00030\u000b8Æ\u0002¢\u0006\u0006\u001a\u0004\b!\u0010$\"\u0016\u0010%\u001a\u00020&*\u00020\u00108Æ\u0002¢\u0006\u0006\u001a\u0004\b'\u0010(\"\u001a\u0010%\u001a\u00020&*\u0006\u0012\u0002\b\u00030\u000b8Æ\u0002¢\u0006\u0006\u001a\u0004\b'\u0010)\"\u0016\u0010*\u001a\u00020+*\u00020\u000f8Æ\u0002¢\u0006\u0006\u001a\u0004\b,\u0010-\"\u0016\u0010.\u001a\u00020+*\u00020\u000f8Æ\u0002¢\u0006\u0006\u001a\u0004\b/\u0010-\"\u0016\u00100\u001a\u00020+*\u00020\u000f8Æ\u0002¢\u0006\u0006\u001a\u0004\b1\u0010-\"\u001a\u00102\u001a\u000203*\u0006\u0012\u0002\b\u00030\u000b8Æ\u0002¢\u0006\u0006\u001a\u0004\b4\u00105¨\u0006J"}, d2 = {"act", "Landroid/app/Activity;", "act$annotations", "(Landroid/app/Activity;)V", "getAct", "(Landroid/app/Activity;)Landroid/app/Activity;", "Landroid/app/Fragment;", "(Landroid/app/Fragment;)V", "(Landroid/app/Fragment;)Landroid/app/Activity;", "assets", "Landroid/content/res/AssetManager;", "Lorg/jetbrains/anko/AnkoContext;", "getAssets", "(Lorg/jetbrains/anko/AnkoContext;)Landroid/content/res/AssetManager;", "configuration", "Landroid/content/res/Configuration;", "Landroid/content/Context;", "getConfiguration", "(Landroid/content/Context;)Landroid/content/res/Configuration;", "(Lorg/jetbrains/anko/AnkoContext;)Landroid/content/res/Configuration;", "contentView", "Landroid/view/View;", "getContentView", "(Landroid/app/Activity;)Landroid/view/View;", "ctx", "ctx$annotations", "getCtx", "(Landroid/app/Fragment;)Landroid/content/Context;", "(Landroid/content/Context;)V", "(Landroid/content/Context;)Landroid/content/Context;", "defaultSharedPreferences", "Landroid/content/SharedPreferences;", "defaultSharedPreferences$annotations", "getDefaultSharedPreferences", "(Landroid/app/Fragment;)Landroid/content/SharedPreferences;", "(Landroid/content/Context;)Landroid/content/SharedPreferences;", "(Lorg/jetbrains/anko/AnkoContext;)Landroid/content/SharedPreferences;", "displayMetrics", "Landroid/util/DisplayMetrics;", "getDisplayMetrics", "(Landroid/content/Context;)Landroid/util/DisplayMetrics;", "(Lorg/jetbrains/anko/AnkoContext;)Landroid/util/DisplayMetrics;", "landscape", "", "getLandscape", "(Landroid/content/res/Configuration;)Z", "long", "getLong", "portrait", "getPortrait", "resources", "Landroid/content/res/Resources;", "getResources", "(Lorg/jetbrains/anko/AnkoContext;)Landroid/content/res/Resources;", "bundleOf", "Landroid/os/Bundle;", "params", "", "Lkotlin/Pair;", "", "", "([Lkotlin/Pair;)Landroid/os/Bundle;", "find", "T", "id", "", "(Landroid/app/Activity;I)Landroid/view/View;", "Landroid/app/Dialog;", "(Landroid/app/Dialog;I)Landroid/view/View;", "(Landroid/app/Fragment;I)Landroid/view/View;", "(Landroid/view/View;I)Landroid/view/View;", "findOptional", "withArguments", "(Landroid/app/Fragment;[Lkotlin/Pair;)Landroid/app/Fragment;", "commons-base_release"}, k = 2, mv = {1, 1, 13})
/* compiled from: ContextUtils.kt */
public final class ContextUtilsKt {
    @Deprecated(message = "Inline", replaceWith = @ReplaceWith(expression = "this", imports = {}))
    public static /* synthetic */ void act$annotations(Activity activity) {
    }

    @Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
    public static /* synthetic */ void act$annotations(Fragment fragment) {
    }

    @Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
    public static /* synthetic */ void ctx$annotations(Fragment fragment) {
    }

    @Deprecated(message = "Inline", replaceWith = @ReplaceWith(expression = "this", imports = {}))
    public static /* synthetic */ void ctx$annotations(Context context) {
    }

    @Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
    public static /* synthetic */ void defaultSharedPreferences$annotations(Fragment fragment) {
    }

    @NotNull
    public static final Activity getAct(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        return activity;
    }

    @NotNull
    public static final Context getCtx(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        return context;
    }

    @NotNull
    public static final Resources getResources(@NotNull AnkoContext<?> ankoContext) {
        Intrinsics.checkParameterIsNotNull(ankoContext, "receiver$0");
        ankoContext = ankoContext.getCtx().getResources();
        Intrinsics.checkExpressionValueIsNotNull(ankoContext, "ctx.resources");
        return ankoContext;
    }

    @NotNull
    public static final AssetManager getAssets(@NotNull AnkoContext<?> ankoContext) {
        Intrinsics.checkParameterIsNotNull(ankoContext, "receiver$0");
        ankoContext = ankoContext.getCtx().getAssets();
        Intrinsics.checkExpressionValueIsNotNull(ankoContext, "ctx.assets");
        return ankoContext;
    }

    @NotNull
    public static final SharedPreferences getDefaultSharedPreferences(@NotNull AnkoContext<?> ankoContext) {
        Intrinsics.checkParameterIsNotNull(ankoContext, "receiver$0");
        ankoContext = PreferenceManager.getDefaultSharedPreferences(ankoContext.getCtx());
        Intrinsics.checkExpressionValueIsNotNull(ankoContext, "PreferenceManager.getDefaultSharedPreferences(ctx)");
        return ankoContext;
    }

    @NotNull
    public static final SharedPreferences getDefaultSharedPreferences(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = PreferenceManager.getDefaultSharedPreferences(context);
        Intrinsics.checkExpressionValueIsNotNull(context, "PreferenceManager.getDef…ltSharedPreferences(this)");
        return context;
    }

    @NotNull
    public static final SharedPreferences getDefaultSharedPreferences(@NotNull Fragment fragment) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = PreferenceManager.getDefaultSharedPreferences(fragment.getActivity());
        Intrinsics.checkExpressionValueIsNotNull(fragment, "PreferenceManager.getDef…aredPreferences(activity)");
        return fragment;
    }

    @NotNull
    public static final Activity getAct(@NotNull Fragment fragment) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "activity");
        return fragment;
    }

    @NotNull
    public static final Context getCtx(@NotNull Fragment fragment) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "activity");
        return (Context) fragment;
    }

    private static final <T extends View> T find(@NotNull View view, @IdRes int i) {
        view = view.findViewById(i);
        Intrinsics.checkExpressionValueIsNotNull(view, "findViewById(id)");
        return view;
    }

    private static final <T extends View> T find(@NotNull Activity activity, @IdRes int i) {
        activity = activity.findViewById(i);
        Intrinsics.checkExpressionValueIsNotNull(activity, "findViewById(id)");
        return activity;
    }

    @Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
    private static final <T extends View> T find(@NotNull Fragment fragment, @IdRes int i) {
        fragment = fragment.getView();
        fragment = fragment != null ? fragment.findViewById(i) : null;
        Intrinsics.reifiedOperationMarker(1, "T");
        return (View) fragment;
    }

    private static final <T extends View> T find(@NotNull Dialog dialog, @IdRes int i) {
        dialog = dialog.findViewById(i);
        Intrinsics.checkExpressionValueIsNotNull(dialog, "findViewById(id)");
        return dialog;
    }

    private static final <T extends View> T findOptional(@NotNull View view, @IdRes int i) {
        view = view.findViewById(i);
        Intrinsics.reifiedOperationMarker(2, "T");
        return view;
    }

    private static final <T extends View> T findOptional(@NotNull Activity activity, @IdRes int i) {
        activity = activity.findViewById(i);
        Intrinsics.reifiedOperationMarker(2, "T");
        return (View) activity;
    }

    @Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
    private static final <T extends View> T findOptional(@NotNull Fragment fragment, @IdRes int i) {
        fragment = fragment.getView();
        fragment = fragment != null ? fragment.findViewById(i) : null;
        Intrinsics.reifiedOperationMarker(2, "T");
        return (View) fragment;
    }

    private static final <T extends View> T findOptional(@NotNull Dialog dialog, @IdRes int i) {
        dialog = dialog.findViewById(i);
        Intrinsics.reifiedOperationMarker(2, "T");
        return (View) dialog;
    }

    @NotNull
    @Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
    public static final <T extends Fragment> T withArguments(@NotNull T t, @NotNull Pair<String, ? extends Object>... pairArr) {
        Intrinsics.checkParameterIsNotNull(t, "receiver$0");
        Intrinsics.checkParameterIsNotNull(pairArr, "params");
        t.setArguments(bundleOf((Pair[]) Arrays.copyOf(pairArr, pairArr.length)));
        return t;
    }

    @NotNull
    @Deprecated(message = "Use the Android KTX version", replaceWith = @ReplaceWith(expression = "bundleOf(params)", imports = {"androidx.core.os.bundleOf"}))
    public static final Bundle bundleOf(@NotNull Pair<String, ? extends Object>... pairArr) {
        Intrinsics.checkParameterIsNotNull(pairArr, "params");
        Bundle bundle = new Bundle();
        for (Pair pair : pairArr) {
            String str = (String) pair.component1();
            Object component2 = pair.component2();
            if (component2 == null) {
                bundle.putSerializable(str, null);
            } else if (component2 instanceof Boolean) {
                bundle.putBoolean(str, ((Boolean) component2).booleanValue());
            } else if (component2 instanceof Byte) {
                bundle.putByte(str, ((Number) component2).byteValue());
            } else if (component2 instanceof Character) {
                bundle.putChar(str, ((Character) component2).charValue());
            } else if (component2 instanceof Short) {
                bundle.putShort(str, ((Number) component2).shortValue());
            } else if (component2 instanceof Integer) {
                bundle.putInt(str, ((Number) component2).intValue());
            } else if (component2 instanceof Long) {
                bundle.putLong(str, ((Number) component2).longValue());
            } else if (component2 instanceof Float) {
                bundle.putFloat(str, ((Number) component2).floatValue());
            } else if (component2 instanceof Double) {
                bundle.putDouble(str, ((Number) component2).doubleValue());
            } else if (component2 instanceof String) {
                bundle.putString(str, (String) component2);
            } else if (component2 instanceof CharSequence) {
                bundle.putCharSequence(str, (CharSequence) component2);
            } else if (component2 instanceof Parcelable) {
                bundle.putParcelable(str, (Parcelable) component2);
            } else if (component2 instanceof Serializable) {
                bundle.putSerializable(str, (Serializable) component2);
            } else if (component2 instanceof boolean[]) {
                bundle.putBooleanArray(str, (boolean[]) component2);
            } else if (component2 instanceof byte[]) {
                bundle.putByteArray(str, (byte[]) component2);
            } else if (component2 instanceof char[]) {
                bundle.putCharArray(str, (char[]) component2);
            } else if (component2 instanceof double[]) {
                bundle.putDoubleArray(str, (double[]) component2);
            } else if (component2 instanceof float[]) {
                bundle.putFloatArray(str, (float[]) component2);
            } else if (component2 instanceof int[]) {
                bundle.putIntArray(str, (int[]) component2);
            } else if (component2 instanceof long[]) {
                bundle.putLongArray(str, (long[]) component2);
            } else if (component2 instanceof Object[]) {
                Object[] objArr = (Object[]) component2;
                if (objArr instanceof Parcelable[]) {
                    if (component2 != null) {
                        bundle.putParcelableArray(str, (Parcelable[]) component2);
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<out android.os.Parcelable>");
                    }
                } else if (objArr instanceof CharSequence[]) {
                    if (component2 != null) {
                        bundle.putCharSequenceArray(str, (CharSequence[]) component2);
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<out kotlin.CharSequence>");
                    }
                } else if (!(objArr instanceof String[])) {
                    r0 = new StringBuilder();
                    r0.append("Unsupported bundle component (");
                    r0.append(objArr.getClass());
                    r0.append(')');
                    throw ((Throwable) new AnkoException(r0.toString()));
                } else if (component2 != null) {
                    bundle.putStringArray(str, (String[]) component2);
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<out kotlin.String>");
                }
            } else if (component2 instanceof short[]) {
                bundle.putShortArray(str, (short[]) component2);
            } else if (component2 instanceof Bundle) {
                bundle.putBundle(str, (Bundle) component2);
            } else {
                r0 = new StringBuilder();
                r0.append("Unsupported bundle component (");
                r0.append(component2.getClass());
                r0.append(')');
                throw ((Throwable) new AnkoException(r0.toString()));
            }
        }
        return bundle;
    }

    @NotNull
    public static final DisplayMetrics getDisplayMetrics(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(context, "resources");
        context = context.getDisplayMetrics();
        Intrinsics.checkExpressionValueIsNotNull(context, "resources.displayMetrics");
        return context;
    }

    @NotNull
    public static final Configuration getConfiguration(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(context, "resources");
        context = context.getConfiguration();
        Intrinsics.checkExpressionValueIsNotNull(context, "resources.configuration");
        return context;
    }

    @NotNull
    public static final DisplayMetrics getDisplayMetrics(@NotNull AnkoContext<?> ankoContext) {
        Intrinsics.checkParameterIsNotNull(ankoContext, "receiver$0");
        ankoContext = ankoContext.getCtx().getResources();
        Intrinsics.checkExpressionValueIsNotNull(ankoContext, "ctx.resources");
        ankoContext = ankoContext.getDisplayMetrics();
        Intrinsics.checkExpressionValueIsNotNull(ankoContext, "ctx.resources.displayMetrics");
        return ankoContext;
    }

    @NotNull
    public static final Configuration getConfiguration(@NotNull AnkoContext<?> ankoContext) {
        Intrinsics.checkParameterIsNotNull(ankoContext, "receiver$0");
        ankoContext = ankoContext.getCtx().getResources();
        Intrinsics.checkExpressionValueIsNotNull(ankoContext, "ctx.resources");
        ankoContext = ankoContext.getConfiguration();
        Intrinsics.checkExpressionValueIsNotNull(ankoContext, "ctx.resources.configuration");
        return ankoContext;
    }

    public static final boolean getPortrait(@NotNull Configuration configuration) {
        Intrinsics.checkParameterIsNotNull(configuration, "receiver$0");
        return configuration.orientation == 1;
    }

    public static final boolean getLandscape(@NotNull Configuration configuration) {
        Intrinsics.checkParameterIsNotNull(configuration, "receiver$0");
        return configuration.orientation == 2 ? true : null;
    }

    public static final boolean getLong(@NotNull Configuration configuration) {
        Intrinsics.checkParameterIsNotNull(configuration, "receiver$0");
        return (configuration.screenLayout & 32) != null ? true : null;
    }

    @Nullable
    public static final View getContentView(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        activity = activity.findViewById(16908290);
        if (!(activity instanceof ViewGroup)) {
            activity = null;
        }
        ViewGroup viewGroup = (ViewGroup) activity;
        return viewGroup != null ? viewGroup.getChildAt(0) : null;
    }
}
