package org.jetbrains.anko.support.v4;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import org.jetbrains.anko.AnkoContext;
import org.jetbrains.anko.AnkoContextImpl;
import org.jetbrains.anko.ContextUtilsKt;
import org.jetbrains.anko.Orientation;
import org.jetbrains.anko.ScreenSize;
import org.jetbrains.anko.UiMode;
import org.jetbrains.anko.internals.AnkoInternals;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a/\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00022\u001d\u0010\u0003\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006\u001a¶\u0001\u0010\u0007\u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\t*\u00020\u00022\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u000e2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\b0\u001cH\b¢\u0006\u0002\u0010\u001d\u001a&\u0010\u001e\u001a\u0002H\b\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\u001f*\u00020\u00022\u0006\u0010 \u001a\u00020\u000eH\b¢\u0006\u0002\u0010!\u001a(\u0010\"\u001a\u0004\u0018\u0001H\b\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\u001f*\u00020\u00022\u0006\u0010 \u001a\u00020\u000eH\b¢\u0006\u0002\u0010!\u001aI\u0010#\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u0002*\u0002H\b2.\u0010$\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\t0&0%\"\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\t0&¢\u0006\u0002\u0010'¨\u0006("}, d2 = {"UI", "Lorg/jetbrains/anko/AnkoContext;", "Landroid/support/v4/app/Fragment;", "init", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "configuration", "T", "", "screenSize", "Lorg/jetbrains/anko/ScreenSize;", "density", "Lkotlin/ranges/ClosedRange;", "", "language", "", "orientation", "Lorg/jetbrains/anko/Orientation;", "long", "", "fromSdk", "sdk", "uiMode", "Lorg/jetbrains/anko/UiMode;", "nightMode", "rightToLeft", "smallestWidth", "Lkotlin/Function0;", "(Landroid/support/v4/app/Fragment;Lorg/jetbrains/anko/ScreenSize;Lkotlin/ranges/ClosedRange;Ljava/lang/String;Lorg/jetbrains/anko/Orientation;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Lorg/jetbrains/anko/UiMode;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "find", "Landroid/view/View;", "id", "(Landroid/support/v4/app/Fragment;I)Landroid/view/View;", "findOptional", "withArguments", "params", "", "Lkotlin/Pair;", "(Landroid/support/v4/app/Fragment;[Lkotlin/Pair;)Landroid/support/v4/app/Fragment;", "supportV4-base_release"}, k = 2, mv = {1, 1, 13})
/* compiled from: Support.kt */
public final class SupportKt {
    private static final <T extends View> T find(@NotNull Fragment fragment, int i) {
        fragment = fragment.getView();
        fragment = fragment != null ? fragment.findViewById(i) : null;
        Intrinsics.reifiedOperationMarker(1, "T");
        return (View) fragment;
    }

    private static final <T extends View> T findOptional(@NotNull Fragment fragment, int i) {
        fragment = fragment.getView();
        fragment = fragment != null ? fragment.findViewById(i) : null;
        Intrinsics.reifiedOperationMarker(2, "T");
        return (View) fragment;
    }

    @NotNull
    public static final AnkoContext<Fragment> UI(@NotNull Fragment fragment, @NotNull Function1<? super AnkoContext<? extends Fragment>, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        AnkoInternals ankoInternals = AnkoInternals.INSTANCE;
        FragmentActivity requireActivity = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        AnkoContextImpl ankoContextImpl = new AnkoContextImpl(requireActivity, fragment, false);
        function1.invoke(ankoContextImpl);
        return ankoContextImpl;
    }

    @Nullable
    public static /* synthetic */ Object configuration$default(Fragment fragment, ScreenSize screenSize, ClosedRange closedRange, String str, Orientation orientation, Boolean bool, Integer num, Integer num2, UiMode uiMode, Boolean bool2, Boolean bool3, Integer num3, Function0 function0, int i, Object obj) {
        int i2 = i;
        ScreenSize screenSize2 = (i2 & 1) != 0 ? (ScreenSize) null : screenSize;
        ClosedRange closedRange2 = (i2 & 2) != 0 ? (ClosedRange) null : closedRange;
        String str2 = (i2 & 4) != 0 ? (String) null : str;
        Orientation orientation2 = (i2 & 8) != 0 ? (Orientation) null : orientation;
        Boolean bool4 = (i2 & 16) != 0 ? (Boolean) null : bool;
        Integer num4 = (i2 & 32) != 0 ? (Integer) null : num;
        Integer num5 = (i2 & 64) != 0 ? (Integer) null : num2;
        UiMode uiMode2 = (i2 & 128) != 0 ? (UiMode) null : uiMode;
        Boolean bool5 = (i2 & 256) != 0 ? (Boolean) null : bool2;
        Boolean bool6 = (i2 & 512) != 0 ? (Boolean) null : bool3;
        Integer num6 = (i2 & 1024) != 0 ? (Integer) null : num3;
        Fragment fragment2 = fragment;
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function0, "init");
        FragmentActivity activity = fragment.getActivity();
        if (activity == null || !AnkoInternals.testConfiguration(activity, screenSize2, closedRange2, str2, orientation2, bool4, num4, num5, uiMode2, bool5, bool6, num6)) {
            return null;
        }
        return function0.invoke();
    }

    @Nullable
    public static final <T> T configuration(@NotNull Fragment fragment, @Nullable ScreenSize screenSize, @Nullable ClosedRange<Integer> closedRange, @Nullable String str, @Nullable Orientation orientation, @Nullable Boolean bool, @Nullable Integer num, @Nullable Integer num2, @Nullable UiMode uiMode, @Nullable Boolean bool2, @Nullable Boolean bool3, @Nullable Integer num3, @NotNull Function0<? extends T> function0) {
        Fragment fragment2 = fragment;
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function0, "init");
        FragmentActivity activity = fragment.getActivity();
        if (activity == null || !AnkoInternals.testConfiguration(activity, screenSize, closedRange, str, orientation, bool, num, num2, uiMode, bool2, bool3, num3)) {
            return null;
        }
        return function0.invoke();
    }

    @NotNull
    public static final <T extends Fragment> T withArguments(@NotNull T t, @NotNull Pair<String, ? extends Object>... pairArr) {
        Intrinsics.checkParameterIsNotNull(t, "receiver$0");
        Intrinsics.checkParameterIsNotNull(pairArr, "params");
        t.setArguments(ContextUtilsKt.bundleOf((Pair[]) Arrays.copyOf(pairArr, pairArr.length)));
        return t;
    }
}
