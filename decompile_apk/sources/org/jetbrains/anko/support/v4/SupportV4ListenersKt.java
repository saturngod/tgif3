package org.jetbrains.anko.support.v4;

import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a#\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0002\b\u0006\u001ab\u0010\u0007\u001a\u00020\u0001*\u00020\b2S\b\b\u0010\t\u001aM\u0012\u0015\u0012\u0013\u0018\u00010\b¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000f\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00010\nH\b\u001a#\u0010\u0011\u001a\u00020\u0001*\u00020\b2\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0002\b\u0006\u001a\u001d\u0010\u0013\u001a\u00020\u0001*\u00020\u00142\u000e\b\b\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\u0015H\b\u001a\u0001\u0010\u0016\u001a\u00020\u0001*\u00020\u00172y\b\b\u0010\t\u001as\u0012\u0015\u0012\u0013\u0018\u00010\u0017¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00010\u0018H\b\u001a4\u0010\u001f\u001a\u00020\u0001*\u00020 2%\b\b\u0010\t\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010!¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020\u00010\u0004H\b¨\u0006#"}, d2 = {"drawerListener", "", "Landroid/support/v4/widget/DrawerLayout;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/anko/support/v4/__DrawerLayout_DrawerListener;", "Lkotlin/ExtensionFunctionType;", "onAdapterChange", "Landroid/support/v4/view/ViewPager;", "l", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "viewPager", "Landroid/support/v4/view/PagerAdapter;", "oldAdapter", "newAdapter", "onPageChangeListener", "Lorg/jetbrains/anko/support/v4/__ViewPager_OnPageChangeListener;", "onRefresh", "Landroid/support/v4/widget/SwipeRefreshLayout;", "Lkotlin/Function0;", "onScrollChange", "Landroid/support/v4/widget/NestedScrollView;", "Lkotlin/Function5;", "v", "", "scrollX", "scrollY", "oldScrollX", "oldScrollY", "onTabChanged", "Landroid/support/v4/app/FragmentTabHost;", "", "tabId", "anko-support-v4_release"}, k = 2, mv = {1, 1, 13})
@JvmName(name = "SupportV4ListenersKt")
/* compiled from: Listeners.kt */
public final class SupportV4ListenersKt {
    public static final void drawerListener(@NotNull DrawerLayout drawerLayout, @NotNull Function1<? super __DrawerLayout_DrawerListener, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(drawerLayout, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        __DrawerLayout_DrawerListener __drawerlayout_drawerlistener = new __DrawerLayout_DrawerListener();
        function1.invoke(__drawerlayout_drawerlistener);
        drawerLayout.addDrawerListener(__drawerlayout_drawerlistener);
    }

    public static final void onAdapterChange(@NotNull ViewPager viewPager, @NotNull Function3<? super ViewPager, ? super PagerAdapter, ? super PagerAdapter, Unit> function3) {
        Intrinsics.checkParameterIsNotNull(viewPager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function3, "l");
        viewPager.addOnAdapterChangeListener(new C0321x4210a5d(function3));
    }

    public static final void onPageChangeListener(@NotNull ViewPager viewPager, @NotNull Function1<? super __ViewPager_OnPageChangeListener, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewPager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        __ViewPager_OnPageChangeListener __viewpager_onpagechangelistener = new __ViewPager_OnPageChangeListener();
        function1.invoke(__viewpager_onpagechangelistener);
        viewPager.addOnPageChangeListener(__viewpager_onpagechangelistener);
    }

    public static final void onTabChanged(@NotNull FragmentTabHost fragmentTabHost, @NotNull Function1<? super String, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(fragmentTabHost, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "l");
        fragmentTabHost.setOnTabChangedListener(new C0264xc5e8a822(function1));
    }

    public static final void onScrollChange(@NotNull NestedScrollView nestedScrollView, @NotNull Function5<? super NestedScrollView, ? super Integer, ? super Integer, ? super Integer, ? super Integer, Unit> function5) {
        Intrinsics.checkParameterIsNotNull(nestedScrollView, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function5, "l");
        nestedScrollView.setOnScrollChangeListener(new C0322xafeed10f(function5));
    }

    public static final void onRefresh(@NotNull SwipeRefreshLayout swipeRefreshLayout, @NotNull Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(swipeRefreshLayout, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function0, "l");
        swipeRefreshLayout.setOnRefreshListener(new C0323x8cd105cb(function0));
    }
}
