package org.jetbrains.anko.support.v4;

import android.content.Context;
import android.support.v4.app.Fragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.DimensionsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\b\u001a\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\b\u001a\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0001H\b\u001a\u0015\u0010\u0007\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\b\u001a\u00020\u0001H\b\u001a\u0015\u0010\t\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\b\u001a\u00020\u0001H\b\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\b\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0001H\b¨\u0006\u000b"}, d2 = {"dimen", "", "Landroid/support/v4/app/Fragment;", "resource", "dip", "value", "", "px2dip", "px", "px2sp", "sp", "supportV4-base_release"}, k = 2, mv = {1, 1, 13})
/* compiled from: SupportDimensions.kt */
public final class SupportDimensionsKt {
    public static final int dip(@NotNull Fragment fragment, int i) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        return DimensionsKt.dip((Context) fragment, i);
    }

    public static final int dip(@NotNull Fragment fragment, float f) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        return DimensionsKt.dip((Context) fragment, f);
    }

    public static final int sp(@NotNull Fragment fragment, int i) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        return DimensionsKt.sp((Context) fragment, i);
    }

    public static final int sp(@NotNull Fragment fragment, float f) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        return DimensionsKt.sp((Context) fragment, f);
    }

    public static final float px2dip(@NotNull Fragment fragment, int i) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        return DimensionsKt.px2dip((Context) fragment, i);
    }

    public static final float px2sp(@NotNull Fragment fragment, int i) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        return DimensionsKt.px2sp((Context) fragment, i);
    }

    public static final int dimen(@NotNull Fragment fragment, int i) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        return DimensionsKt.dimen((Context) fragment, i);
    }
}
