package org.jetbrains.anko.support.v4;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnAdapterChangeListener;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
/* compiled from: Listeners.kt */
/* renamed from: org.jetbrains.anko.support.v4.SupportV4ListenersKt$sam$i$android_support_v4_view_ViewPager_OnAdapterChangeListener$0 */
public final class C0321x4210a5d implements OnAdapterChangeListener {
    private final /* synthetic */ Function3 function;

    public C0321x4210a5d(Function3 function3) {
        this.function = function3;
    }

    public final /* synthetic */ void onAdapterChanged(@NotNull @NonNull ViewPager viewPager, @Nullable @org.jetbrains.annotations.Nullable PagerAdapter pagerAdapter, @Nullable @org.jetbrains.annotations.Nullable PagerAdapter pagerAdapter2) {
        Intrinsics.checkParameterIsNotNull(viewPager, "p0");
        Intrinsics.checkExpressionValueIsNotNull(this.function.invoke(viewPager, pagerAdapter, pagerAdapter2), "invoke(...)");
    }
}
