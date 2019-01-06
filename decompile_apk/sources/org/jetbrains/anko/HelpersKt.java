package org.jetbrains.anko;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import org.jetbrains.anko.internals.AnkoInternals;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a#\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\nH\b\u001a\u001f\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\f0\nH\b\u001a\u001f\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\f0\nH\b\u001a\u001f\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\f0\nH\b\u001a¶\u0001\u0010\u0010\u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\u0011*\u00020\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\nH\b¢\u0006\u0002\u0010$\u001a¶\u0001\u0010\u0010\u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\u0011*\u00020%2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\nH\b¢\u0006\u0002\u0010&\u001a¶\u0001\u0010\u0010\u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\u0011*\u00020'2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\nH\b¢\u0006\u0002\u0010(\u001aº\u0001\u0010\u0010\u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\u0011*\u0006\u0012\u0002\b\u00030)2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\nH\b¢\u0006\u0002\u0010*\u001a\u0012\u0010+\u001a\u00020\u0001*\u00020\u00012\u0006\u0010,\u001a\u00020\u0001\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003\"\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0003¨\u0006-"}, d2 = {"gray", "", "getGray", "(I)I", "opaque", "getOpaque", "attempt", "Lorg/jetbrains/anko/AttemptResult;", "T", "f", "Lkotlin/Function0;", "doBeforeSdk", "", "version", "doFromSdk", "doIfSdk", "configuration", "", "Landroid/app/Activity;", "screenSize", "Lorg/jetbrains/anko/ScreenSize;", "density", "Lkotlin/ranges/ClosedRange;", "language", "", "orientation", "Lorg/jetbrains/anko/Orientation;", "long", "", "fromSdk", "sdk", "uiMode", "Lorg/jetbrains/anko/UiMode;", "nightMode", "rightToLeft", "smallestWidth", "(Landroid/app/Activity;Lorg/jetbrains/anko/ScreenSize;Lkotlin/ranges/ClosedRange;Ljava/lang/String;Lorg/jetbrains/anko/Orientation;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Lorg/jetbrains/anko/UiMode;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Landroid/app/Fragment;", "(Landroid/app/Fragment;Lorg/jetbrains/anko/ScreenSize;Lkotlin/ranges/ClosedRange;Ljava/lang/String;Lorg/jetbrains/anko/Orientation;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Lorg/jetbrains/anko/UiMode;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Landroid/content/Context;", "(Landroid/content/Context;Lorg/jetbrains/anko/ScreenSize;Lkotlin/ranges/ClosedRange;Ljava/lang/String;Lorg/jetbrains/anko/Orientation;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Lorg/jetbrains/anko/UiMode;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Lorg/jetbrains/anko/AnkoContext;", "(Lorg/jetbrains/anko/AnkoContext;Lorg/jetbrains/anko/ScreenSize;Lkotlin/ranges/ClosedRange;Ljava/lang/String;Lorg/jetbrains/anko/Orientation;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Lorg/jetbrains/anko/UiMode;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withAlpha", "alpha", "commons-base_release"}, k = 2, mv = {1, 1, 13})
/* compiled from: Helpers.kt */
public final class HelpersKt {
    public static final int getGray(int i) {
        return (i << 16) | ((i << 8) | i);
    }

    public static final int getOpaque(int i) {
        return i | ((int) 4278190080L);
    }

    public static final int withAlpha(int i, int i2) {
        Object obj;
        if (i2 >= 0) {
            if (255 >= i2) {
                obj = 1;
                if (obj != null) {
                    return (i & ViewCompat.MEASURED_SIZE_MASK) | (i2 << 24);
                }
                throw ((Throwable) new IllegalArgumentException("Failed requirement.".toString()));
            }
        }
        obj = null;
        if (obj != null) {
            return (i & ViewCompat.MEASURED_SIZE_MASK) | (i2 << 24);
        }
        throw ((Throwable) new IllegalArgumentException("Failed requirement.".toString()));
    }

    @Nullable
    public static /* synthetic */ Object configuration$default(Context context, ScreenSize screenSize, ClosedRange closedRange, String str, Orientation orientation, Boolean bool, Integer num, Integer num2, UiMode uiMode, Boolean bool2, Boolean bool3, Integer num3, Function0 function0, int i, Object obj) {
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
        Context context2 = context;
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function0, "f");
        if (AnkoInternals.testConfiguration(context, screenSize2, closedRange2, str2, orientation2, bool4, num4, num5, uiMode2, bool5, bool6, num6)) {
            return function0.invoke();
        }
        return null;
    }

    @Nullable
    public static final <T> T configuration(@NotNull Context context, @Nullable ScreenSize screenSize, @Nullable ClosedRange<Integer> closedRange, @Nullable String str, @Nullable Orientation orientation, @Nullable Boolean bool, @Nullable Integer num, @Nullable Integer num2, @Nullable UiMode uiMode, @Nullable Boolean bool2, @Nullable Boolean bool3, @Nullable Integer num3, @NotNull Function0<? extends T> function0) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function0, "f");
        return AnkoInternals.testConfiguration(context, screenSize, closedRange, str, orientation, bool, num, num2, uiMode, bool2, bool3, num3) != null ? function0.invoke() : null;
    }

    @Nullable
    public static /* synthetic */ Object configuration$default(Activity activity, ScreenSize screenSize, ClosedRange closedRange, String str, Orientation orientation, Boolean bool, Integer num, Integer num2, UiMode uiMode, Boolean bool2, Boolean bool3, Integer num3, Function0 function0, int i, Object obj) {
        Activity activity2 = activity;
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
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function0, "f");
        if (AnkoInternals.testConfiguration(activity2, screenSize2, closedRange2, str2, orientation2, bool4, num4, num5, uiMode2, bool5, bool6, num6)) {
            return function0.invoke();
        }
        return null;
    }

    @Nullable
    public static final <T> T configuration(@NotNull Activity activity, @Nullable ScreenSize screenSize, @Nullable ClosedRange<Integer> closedRange, @Nullable String str, @Nullable Orientation orientation, @Nullable Boolean bool, @Nullable Integer num, @Nullable Integer num2, @Nullable UiMode uiMode, @Nullable Boolean bool2, @Nullable Boolean bool3, @Nullable Integer num3, @NotNull Function0<? extends T> function0) {
        Activity activity2 = activity;
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function0, "f");
        return AnkoInternals.testConfiguration((Context) activity2, screenSize, closedRange, str, orientation, bool, num, num2, uiMode, bool2, bool3, num3) ? function0.invoke() : null;
    }

    @Nullable
    public static /* synthetic */ Object configuration$default(AnkoContext ankoContext, ScreenSize screenSize, ClosedRange closedRange, String str, Orientation orientation, Boolean bool, Integer num, Integer num2, UiMode uiMode, Boolean bool2, Boolean bool3, Integer num3, Function0 function0, int i, Object obj) {
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
        AnkoContext ankoContext2 = ankoContext;
        Intrinsics.checkParameterIsNotNull(ankoContext, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function0, "f");
        if (AnkoInternals.testConfiguration(ankoContext.getCtx(), screenSize2, closedRange2, str2, orientation2, bool4, num4, num5, uiMode2, bool5, bool6, num6)) {
            return function0.invoke();
        }
        return null;
    }

    @Nullable
    public static final <T> T configuration(@NotNull AnkoContext<?> ankoContext, @Nullable ScreenSize screenSize, @Nullable ClosedRange<Integer> closedRange, @Nullable String str, @Nullable Orientation orientation, @Nullable Boolean bool, @Nullable Integer num, @Nullable Integer num2, @Nullable UiMode uiMode, @Nullable Boolean bool2, @Nullable Boolean bool3, @Nullable Integer num3, @NotNull Function0<? extends T> function0) {
        AnkoContext<?> ankoContext2 = ankoContext;
        Intrinsics.checkParameterIsNotNull(ankoContext, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function0, "f");
        return AnkoInternals.testConfiguration(ankoContext.getCtx(), screenSize, closedRange, str, orientation, bool, num, num2, uiMode, bool2, bool3, num3) ? function0.invoke() : null;
    }

    @Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
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
        Intrinsics.checkParameterIsNotNull(function0, "f");
        Activity activity = fragment.getActivity();
        if (activity == null || !AnkoInternals.testConfiguration(activity, screenSize2, closedRange2, str2, orientation2, bool4, num4, num5, uiMode2, bool5, bool6, num6)) {
            return null;
        }
        return function0.invoke();
    }

    @Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
    @Nullable
    public static final <T> T configuration(@NotNull Fragment fragment, @Nullable ScreenSize screenSize, @Nullable ClosedRange<Integer> closedRange, @Nullable String str, @Nullable Orientation orientation, @Nullable Boolean bool, @Nullable Integer num, @Nullable Integer num2, @Nullable UiMode uiMode, @Nullable Boolean bool2, @Nullable Boolean bool3, @Nullable Integer num3, @NotNull Function0<? extends T> function0) {
        Fragment fragment2 = fragment;
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function0, "f");
        Activity activity = fragment.getActivity();
        if (activity == null || !AnkoInternals.testConfiguration(activity, screenSize, closedRange, str, orientation, bool, num, num2, uiMode, bool2, bool3, num3)) {
            return null;
        }
        return function0.invoke();
    }

    public static final void doBeforeSdk(int i, @NotNull Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(function0, "f");
        if (VERSION.SDK_INT <= i) {
            function0.invoke();
        }
    }

    public static final void doFromSdk(int i, @NotNull Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(function0, "f");
        if (VERSION.SDK_INT >= i) {
            function0.invoke();
        }
    }

    public static final void doIfSdk(int i, @NotNull Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(function0, "f");
        if (VERSION.SDK_INT == i) {
            function0.invoke();
        }
    }

    @NotNull
    public static final <T> AttemptResult<T> attempt(@NotNull Function0<? extends T> function0) {
        Intrinsics.checkParameterIsNotNull(function0, "f");
        Throwable th = (Throwable) null;
        try {
            function0 = function0.invoke();
        } catch (Throwable th2) {
            th = th2;
            function0 = null;
        }
        return new AttemptResult(function0, th);
    }
}
