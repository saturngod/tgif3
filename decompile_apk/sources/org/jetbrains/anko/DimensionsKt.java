package org.jetbrains.anko;

import android.app.Fragment;
import android.content.Context;
import android.support.annotation.DimenRes;
import android.view.View;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\u001a\u0017\u0010\t\u001a\u00020\u0001*\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\u0001H\b\u001a\u0014\u0010\t\u001a\u00020\u0001*\u00020\f2\b\b\u0001\u0010\u000b\u001a\u00020\u0001\u001a\u0017\u0010\t\u001a\u00020\u0001*\u00020\r2\b\b\u0001\u0010\u000b\u001a\u00020\u0001H\b\u001a\u001b\u0010\t\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u000e2\b\b\u0001\u0010\u000b\u001a\u00020\u0001H\b\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\b\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0001H\b\u001a\u0012\u0010\u000f\u001a\u00020\u0001*\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u0012\u0010\u000f\u001a\u00020\u0001*\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0001\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\b\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0001H\b\u001a\u0019\u0010\u000f\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\b\u001a\u0019\u0010\u000f\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0010\u001a\u00020\u0001H\b\u001a\u0015\u0010\u0012\u001a\u00020\u0011*\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0001H\b\u001a\u0012\u0010\u0012\u001a\u00020\u0011*\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0001\u001a\u0015\u0010\u0012\u001a\u00020\u0011*\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0001H\b\u001a\u0019\u0010\u0012\u001a\u00020\u0011*\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0013\u001a\u00020\u0001H\b\u001a\u0015\u0010\u0014\u001a\u00020\u0011*\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0001H\b\u001a\u0012\u0010\u0014\u001a\u00020\u0011*\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0001\u001a\u0015\u0010\u0014\u001a\u00020\u0011*\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0001H\b\u001a\u0019\u0010\u0014\u001a\u00020\u0011*\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0013\u001a\u00020\u0001H\b\u001a\u0015\u0010\u0015\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\b\u001a\u0015\u0010\u0015\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0001H\b\u001a\u0012\u0010\u0015\u001a\u00020\u0001*\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u0012\u0010\u0015\u001a\u00020\u0001*\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0001\u001a\u0015\u0010\u0015\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\b\u001a\u0015\u0010\u0015\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0001H\b\u001a\u0019\u0010\u0015\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\b\u001a\u0019\u0010\u0015\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0010\u001a\u00020\u0001H\b\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"HDPI", "", "LDPI", "MAXDPI", "MDPI", "TVDPI", "XHDPI", "XXHDPI", "XXXHDPI", "dimen", "Landroid/app/Fragment;", "resource", "Landroid/content/Context;", "Landroid/view/View;", "Lorg/jetbrains/anko/AnkoContext;", "dip", "value", "", "px2dip", "px", "px2sp", "sp", "commons-base_release"}, k = 2, mv = {1, 1, 13})
/* compiled from: Dimensions.kt */
public final class DimensionsKt {
    public static final int HDPI = 240;
    public static final int LDPI = 120;
    public static final int MAXDPI = 65534;
    public static final int MDPI = 160;
    public static final int TVDPI = 213;
    public static final int XHDPI = 320;
    public static final int XXHDPI = 480;
    public static final int XXXHDPI = 640;

    public static final int dip(@NotNull Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        i = (float) i;
        context = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(context, "resources");
        return (int) (i * context.getDisplayMetrics().density);
    }

    public static final int dip(@NotNull Context context, float f) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(context, "resources");
        return (int) (f * context.getDisplayMetrics().density);
    }

    public static final int sp(@NotNull Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        i = (float) i;
        context = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(context, "resources");
        return (int) (i * context.getDisplayMetrics().scaledDensity);
    }

    public static final int sp(@NotNull Context context, float f) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(context, "resources");
        return (int) (f * context.getDisplayMetrics().scaledDensity);
    }

    public static final float px2dip(@NotNull Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        i = (float) i;
        context = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(context, "resources");
        return i / context.getDisplayMetrics().density;
    }

    public static final float px2sp(@NotNull Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        i = (float) i;
        context = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(context, "resources");
        return i / context.getDisplayMetrics().scaledDensity;
    }

    public static final int dimen(@NotNull Context context, @DimenRes int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        return context.getResources().getDimensionPixelSize(i);
    }

    public static final int dip(@NotNull AnkoContext<?> ankoContext, int i) {
        Intrinsics.checkParameterIsNotNull(ankoContext, "receiver$0");
        return dip(ankoContext.getCtx(), i);
    }

    public static final int dip(@NotNull AnkoContext<?> ankoContext, float f) {
        Intrinsics.checkParameterIsNotNull(ankoContext, "receiver$0");
        return dip(ankoContext.getCtx(), f);
    }

    public static final int sp(@NotNull AnkoContext<?> ankoContext, int i) {
        Intrinsics.checkParameterIsNotNull(ankoContext, "receiver$0");
        return sp(ankoContext.getCtx(), i);
    }

    public static final int sp(@NotNull AnkoContext<?> ankoContext, float f) {
        Intrinsics.checkParameterIsNotNull(ankoContext, "receiver$0");
        return sp(ankoContext.getCtx(), f);
    }

    public static final float px2dip(@NotNull AnkoContext<?> ankoContext, int i) {
        Intrinsics.checkParameterIsNotNull(ankoContext, "receiver$0");
        return px2dip(ankoContext.getCtx(), i);
    }

    public static final float px2sp(@NotNull AnkoContext<?> ankoContext, int i) {
        Intrinsics.checkParameterIsNotNull(ankoContext, "receiver$0");
        return px2sp(ankoContext.getCtx(), i);
    }

    public static final int dimen(@NotNull AnkoContext<?> ankoContext, @DimenRes int i) {
        Intrinsics.checkParameterIsNotNull(ankoContext, "receiver$0");
        return dimen(ankoContext.getCtx(), i);
    }

    public static final int dip(@NotNull View view, int i) {
        Intrinsics.checkParameterIsNotNull(view, "receiver$0");
        Context context = view.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        return dip(context, i);
    }

    public static final int dip(@NotNull View view, float f) {
        Intrinsics.checkParameterIsNotNull(view, "receiver$0");
        Context context = view.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        return dip(context, f);
    }

    public static final int sp(@NotNull View view, int i) {
        Intrinsics.checkParameterIsNotNull(view, "receiver$0");
        Context context = view.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        return sp(context, i);
    }

    public static final int sp(@NotNull View view, float f) {
        Intrinsics.checkParameterIsNotNull(view, "receiver$0");
        Context context = view.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        return sp(context, f);
    }

    public static final float px2dip(@NotNull View view, int i) {
        Intrinsics.checkParameterIsNotNull(view, "receiver$0");
        Context context = view.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        return px2dip(context, i);
    }

    public static final float px2sp(@NotNull View view, int i) {
        Intrinsics.checkParameterIsNotNull(view, "receiver$0");
        Context context = view.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        return px2sp(context, i);
    }

    public static final int dimen(@NotNull View view, @DimenRes int i) {
        Intrinsics.checkParameterIsNotNull(view, "receiver$0");
        Context context = view.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        return dimen(context, i);
    }

    @Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
    public static final int dip(@NotNull Fragment fragment, int i) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "activity");
        return dip((Context) fragment, i);
    }

    @Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
    public static final int dip(@NotNull Fragment fragment, float f) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "activity");
        return dip((Context) fragment, f);
    }

    @Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
    public static final int sp(@NotNull Fragment fragment, int i) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "activity");
        return sp((Context) fragment, i);
    }

    @Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
    public static final int sp(@NotNull Fragment fragment, float f) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "activity");
        return sp((Context) fragment, f);
    }

    @Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
    public static final float px2dip(@NotNull Fragment fragment, int i) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "activity");
        return px2dip((Context) fragment, i);
    }

    @Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
    public static final float px2sp(@NotNull Fragment fragment, int i) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "activity");
        return px2sp((Context) fragment, i);
    }

    @Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
    public static final int dimen(@NotNull Fragment fragment, @DimenRes int i) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "activity");
        return dimen((Context) fragment, i);
    }
}
