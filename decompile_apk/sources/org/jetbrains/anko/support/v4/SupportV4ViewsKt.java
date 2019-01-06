package org.jetbrains.anko.support.v4;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v4.widget.Space;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.internals.AnkoInternals;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000|\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\b\u001a+\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0001¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\b\u001a\u00020\t*\u00020\nH\b\u001a+\u0010\b\u001a\u00020\t*\u00020\n2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000b¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\b\u001a\u00020\t*\u00020\fH\b\u001a+\u0010\b\u001a\u00020\t*\u00020\f2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000b¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\b\u001a\u00020\t*\u00020\u0002H\b\u001a+\u0010\b\u001a\u00020\t*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000b¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\r\u001a\u00020\u000e*\u00020\nH\b\u001a+\u0010\r\u001a\u00020\u000e*\u00020\n2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000f¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\r\u001a\u00020\u000e*\u00020\fH\b\u001a+\u0010\r\u001a\u00020\u000e*\u00020\f2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000f¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\r\u001a\u00020\u000e*\u00020\u0002H\b\u001a+\u0010\r\u001a\u00020\u000e*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000f¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u0010\u001a\u00020\u0011*\u00020\nH\b\u001a+\u0010\u0010\u001a\u00020\u0011*\u00020\n2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0012¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u0010\u001a\u00020\u0011*\u00020\fH\b\u001a+\u0010\u0010\u001a\u00020\u0011*\u00020\f2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0012¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u0010\u001a\u00020\u0011*\u00020\u0002H\b\u001a+\u0010\u0010\u001a\u00020\u0011*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0012¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u0013\u001a\u00020\u0014*\u00020\nH\b\u001a+\u0010\u0013\u001a\u00020\u0014*\u00020\n2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0014¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u0013\u001a\u00020\u0014*\u00020\fH\b\u001a+\u0010\u0013\u001a\u00020\u0014*\u00020\f2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0014¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u0013\u001a\u00020\u0014*\u00020\u0002H\b\u001a+\u0010\u0013\u001a\u00020\u0014*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0014¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u0015\u001a\u00020\u0016*\u00020\nH\b\u001a+\u0010\u0015\u001a\u00020\u0016*\u00020\n2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0016¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u0015\u001a\u00020\u0016*\u00020\fH\b\u001a+\u0010\u0015\u001a\u00020\u0016*\u00020\f2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0016¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u0015\u001a\u00020\u0016*\u00020\u0002H\b\u001a+\u0010\u0015\u001a\u00020\u0016*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0016¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u0017\u001a\u00020\u0018*\u00020\nH\b\u001a+\u0010\u0017\u001a\u00020\u0018*\u00020\n2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0019¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u0017\u001a\u00020\u0018*\u00020\fH\b\u001a+\u0010\u0017\u001a\u00020\u0018*\u00020\f2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0019¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u0017\u001a\u00020\u0018*\u00020\u0002H\b\u001a+\u0010\u0017\u001a\u00020\u0018*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0019¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u001a\u001a\u00020\u001b*\u00020\u0002H\b\u001a+\u0010\u001a\u001a\u00020\u001b*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u001b¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u001c\u001a\u00020\u001d*\u00020\nH\b\u001a+\u0010\u001c\u001a\u00020\u001d*\u00020\n2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u001d¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u001c\u001a\u00020\u001d*\u00020\fH\b\u001a+\u0010\u001c\u001a\u00020\u001d*\u00020\f2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u001d¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u001c\u001a\u00020\u001d*\u00020\u0002H\b\u001a+\u0010\u001c\u001a\u00020\u001d*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u001d¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010\u001e\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010\u001e\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0001¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010!\u001a\u00020\t*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010!\u001a\u00020\t*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000b¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010!\u001a\u00020\t*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010!\u001a\u00020\t*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000b¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010!\u001a\u00020\t*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010!\u001a\u00020\t*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000b¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010\"\u001a\u00020\u000e*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010\"\u001a\u00020\u000e*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000f¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010\"\u001a\u00020\u000e*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010\"\u001a\u00020\u000e*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000f¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010\"\u001a\u00020\u000e*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010\"\u001a\u00020\u000e*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000f¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010#\u001a\u00020\u0011*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010#\u001a\u00020\u0011*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0012¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010#\u001a\u00020\u0011*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010#\u001a\u00020\u0011*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0012¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010#\u001a\u00020\u0011*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010#\u001a\u00020\u0011*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0012¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010$\u001a\u00020\u0014*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010$\u001a\u00020\u0014*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0014¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010$\u001a\u00020\u0014*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010$\u001a\u00020\u0014*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0014¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010$\u001a\u00020\u0014*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010$\u001a\u00020\u0014*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0014¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010%\u001a\u00020\u0016*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010%\u001a\u00020\u0016*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0016¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010%\u001a\u00020\u0016*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010%\u001a\u00020\u0016*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0016¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010%\u001a\u00020\u0016*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010%\u001a\u00020\u0016*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0016¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010&\u001a\u00020\u0018*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010&\u001a\u00020\u0018*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0019¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010&\u001a\u00020\u0018*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010&\u001a\u00020\u0018*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0019¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010&\u001a\u00020\u0018*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010&\u001a\u00020\u0018*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0019¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010'\u001a\u00020\u001b*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010'\u001a\u00020\u001b*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u001b¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010(\u001a\u00020\u001d*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010(\u001a\u00020\u001d*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u001d¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010(\u001a\u00020\u001d*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010(\u001a\u00020\u001d*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u001d¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010(\u001a\u00020\u001d*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010(\u001a\u00020\u001d*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u001d¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010)\u001a\u00020**\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010)\u001a\u00020**\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070+¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010)\u001a\u00020**\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010)\u001a\u00020**\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070+¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010)\u001a\u00020**\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010)\u001a\u00020**\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070+¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010,\u001a\u00020**\u00020\nH\b\u001a+\u0010,\u001a\u00020**\u00020\n2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070+¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010,\u001a\u00020**\u00020\fH\b\u001a+\u0010,\u001a\u00020**\u00020\f2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070+¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010,\u001a\u00020**\u00020\u0002H\b\u001a+\u0010,\u001a\u00020**\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070+¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b¨\u0006-"}, d2 = {"contentLoadingProgressBar", "Landroid/support/v4/widget/ContentLoadingProgressBar;", "Landroid/view/ViewManager;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/anko/AnkoViewDslMarker;", "", "Lkotlin/ExtensionFunctionType;", "drawerLayout", "Landroid/support/v4/widget/DrawerLayout;", "Landroid/app/Activity;", "Lorg/jetbrains/anko/support/v4/_DrawerLayout;", "Landroid/content/Context;", "fragmentTabHost", "Landroid/support/v4/app/FragmentTabHost;", "Lorg/jetbrains/anko/support/v4/_FragmentTabHost;", "nestedScrollView", "Landroid/support/v4/widget/NestedScrollView;", "Lorg/jetbrains/anko/support/v4/_NestedScrollView;", "pagerTabStrip", "Landroid/support/v4/view/PagerTabStrip;", "pagerTitleStrip", "Landroid/support/v4/view/PagerTitleStrip;", "slidingPaneLayout", "Landroid/support/v4/widget/SlidingPaneLayout;", "Lorg/jetbrains/anko/support/v4/_SlidingPaneLayout;", "space", "Landroid/support/v4/widget/Space;", "swipeRefreshLayout", "Landroid/support/v4/widget/SwipeRefreshLayout;", "themedContentLoadingProgressBar", "theme", "", "themedDrawerLayout", "themedFragmentTabHost", "themedNestedScrollView", "themedPagerTabStrip", "themedPagerTitleStrip", "themedSlidingPaneLayout", "themedSpace", "themedSwipeRefreshLayout", "themedViewPager", "Landroid/support/v4/view/ViewPager;", "Lorg/jetbrains/anko/support/v4/_ViewPager;", "viewPager", "anko-support-v4_release"}, k = 2, mv = {1, 1, 13})
@JvmName(name = "SupportV4ViewsKt")
/* compiled from: Views.kt */
public final class SupportV4ViewsKt {
    @NotNull
    public static final PagerTabStrip pagerTabStrip(@NotNull ViewManager viewManager, @NotNull Function1<? super PagerTabStrip, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) view;
        function1.invoke(pagerTabStrip);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return pagerTabStrip;
    }

    @NotNull
    public static /* synthetic */ PagerTabStrip themedPagerTabStrip$default(ViewManager viewManager, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return pagerTabStrip;
    }

    @NotNull
    public static /* synthetic */ PagerTabStrip themedPagerTabStrip$default(ViewManager viewManager, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) view;
        function1.invoke(pagerTabStrip);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return pagerTabStrip;
    }

    @NotNull
    public static final PagerTabStrip themedPagerTabStrip(@NotNull ViewManager viewManager, int i, @NotNull Function1<? super PagerTabStrip, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) view;
        function1.invoke(pagerTabStrip);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return pagerTabStrip;
    }

    @NotNull
    public static final PagerTabStrip pagerTabStrip(@NotNull Context context, @NotNull Function1<? super PagerTabStrip, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) view;
        function1.invoke(pagerTabStrip);
        AnkoInternals.INSTANCE.addView(context, view);
        return pagerTabStrip;
    }

    @NotNull
    public static /* synthetic */ PagerTabStrip themedPagerTabStrip$default(Context context, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return pagerTabStrip;
    }

    @NotNull
    public static /* synthetic */ PagerTabStrip themedPagerTabStrip$default(Context context, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) view;
        function1.invoke(pagerTabStrip);
        AnkoInternals.INSTANCE.addView(context, view);
        return pagerTabStrip;
    }

    @NotNull
    public static final PagerTabStrip themedPagerTabStrip(@NotNull Context context, int i, @NotNull Function1<? super PagerTabStrip, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) view;
        function1.invoke(pagerTabStrip);
        AnkoInternals.INSTANCE.addView(context, view);
        return pagerTabStrip;
    }

    @NotNull
    public static final PagerTabStrip pagerTabStrip(@NotNull Activity activity, @NotNull Function1<? super PagerTabStrip, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) view;
        function1.invoke(pagerTabStrip);
        AnkoInternals.INSTANCE.addView(activity, view);
        return pagerTabStrip;
    }

    @NotNull
    public static /* synthetic */ PagerTabStrip themedPagerTabStrip$default(Activity activity, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return pagerTabStrip;
    }

    @NotNull
    public static /* synthetic */ PagerTabStrip themedPagerTabStrip$default(Activity activity, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) view;
        function1.invoke(pagerTabStrip);
        AnkoInternals.INSTANCE.addView(activity, view);
        return pagerTabStrip;
    }

    @NotNull
    public static final PagerTabStrip themedPagerTabStrip(@NotNull Activity activity, int i, @NotNull Function1<? super PagerTabStrip, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) view;
        function1.invoke(pagerTabStrip);
        AnkoInternals.INSTANCE.addView(activity, view);
        return pagerTabStrip;
    }

    @NotNull
    public static final PagerTitleStrip pagerTitleStrip(@NotNull ViewManager viewManager, @NotNull Function1<? super PagerTitleStrip, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) view;
        function1.invoke(pagerTitleStrip);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return pagerTitleStrip;
    }

    @NotNull
    public static /* synthetic */ PagerTitleStrip themedPagerTitleStrip$default(ViewManager viewManager, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return pagerTitleStrip;
    }

    @NotNull
    public static /* synthetic */ PagerTitleStrip themedPagerTitleStrip$default(ViewManager viewManager, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) view;
        function1.invoke(pagerTitleStrip);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return pagerTitleStrip;
    }

    @NotNull
    public static final PagerTitleStrip themedPagerTitleStrip(@NotNull ViewManager viewManager, int i, @NotNull Function1<? super PagerTitleStrip, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) view;
        function1.invoke(pagerTitleStrip);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return pagerTitleStrip;
    }

    @NotNull
    public static final PagerTitleStrip pagerTitleStrip(@NotNull Context context, @NotNull Function1<? super PagerTitleStrip, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) view;
        function1.invoke(pagerTitleStrip);
        AnkoInternals.INSTANCE.addView(context, view);
        return pagerTitleStrip;
    }

    @NotNull
    public static /* synthetic */ PagerTitleStrip themedPagerTitleStrip$default(Context context, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return pagerTitleStrip;
    }

    @NotNull
    public static /* synthetic */ PagerTitleStrip themedPagerTitleStrip$default(Context context, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) view;
        function1.invoke(pagerTitleStrip);
        AnkoInternals.INSTANCE.addView(context, view);
        return pagerTitleStrip;
    }

    @NotNull
    public static final PagerTitleStrip themedPagerTitleStrip(@NotNull Context context, int i, @NotNull Function1<? super PagerTitleStrip, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) view;
        function1.invoke(pagerTitleStrip);
        AnkoInternals.INSTANCE.addView(context, view);
        return pagerTitleStrip;
    }

    @NotNull
    public static final PagerTitleStrip pagerTitleStrip(@NotNull Activity activity, @NotNull Function1<? super PagerTitleStrip, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) view;
        function1.invoke(pagerTitleStrip);
        AnkoInternals.INSTANCE.addView(activity, view);
        return pagerTitleStrip;
    }

    @NotNull
    public static /* synthetic */ PagerTitleStrip themedPagerTitleStrip$default(Activity activity, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return pagerTitleStrip;
    }

    @NotNull
    public static /* synthetic */ PagerTitleStrip themedPagerTitleStrip$default(Activity activity, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) view;
        function1.invoke(pagerTitleStrip);
        AnkoInternals.INSTANCE.addView(activity, view);
        return pagerTitleStrip;
    }

    @NotNull
    public static final PagerTitleStrip themedPagerTitleStrip(@NotNull Activity activity, int i, @NotNull Function1<? super PagerTitleStrip, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) view;
        function1.invoke(pagerTitleStrip);
        AnkoInternals.INSTANCE.addView(activity, view);
        return pagerTitleStrip;
    }

    @NotNull
    public static final ContentLoadingProgressBar contentLoadingProgressBar(@NotNull ViewManager viewManager, @NotNull Function1<? super ContentLoadingProgressBar, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getCONTENT_LOADING_PROGRESS_BAR().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        ContentLoadingProgressBar contentLoadingProgressBar = (ContentLoadingProgressBar) view;
        function1.invoke(contentLoadingProgressBar);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return contentLoadingProgressBar;
    }

    @NotNull
    public static /* synthetic */ ContentLoadingProgressBar themedContentLoadingProgressBar$default(ViewManager viewManager, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getCONTENT_LOADING_PROGRESS_BAR().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        ContentLoadingProgressBar contentLoadingProgressBar = (ContentLoadingProgressBar) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return contentLoadingProgressBar;
    }

    @NotNull
    public static /* synthetic */ ContentLoadingProgressBar themedContentLoadingProgressBar$default(ViewManager viewManager, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getCONTENT_LOADING_PROGRESS_BAR().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        ContentLoadingProgressBar contentLoadingProgressBar = (ContentLoadingProgressBar) view;
        function1.invoke(contentLoadingProgressBar);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return contentLoadingProgressBar;
    }

    @NotNull
    public static final ContentLoadingProgressBar themedContentLoadingProgressBar(@NotNull ViewManager viewManager, int i, @NotNull Function1<? super ContentLoadingProgressBar, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getCONTENT_LOADING_PROGRESS_BAR().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        ContentLoadingProgressBar contentLoadingProgressBar = (ContentLoadingProgressBar) view;
        function1.invoke(contentLoadingProgressBar);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return contentLoadingProgressBar;
    }

    @NotNull
    public static final Space space(@NotNull ViewManager viewManager, @NotNull Function1<? super Space, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getSPACE().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        Space space = (Space) view;
        function1.invoke(space);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return space;
    }

    @NotNull
    public static /* synthetic */ Space themedSpace$default(ViewManager viewManager, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getSPACE().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        Space space = (Space) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return space;
    }

    @NotNull
    public static /* synthetic */ Space themedSpace$default(ViewManager viewManager, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getSPACE().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        Space space = (Space) view;
        function1.invoke(space);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return space;
    }

    @NotNull
    public static final Space themedSpace(@NotNull ViewManager viewManager, int i, @NotNull Function1<? super Space, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getSPACE().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        Space space = (Space) view;
        function1.invoke(space);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return space;
    }

    @NotNull
    public static final SwipeRefreshLayout swipeRefreshLayout(@NotNull ViewManager viewManager, @NotNull Function1<? super SwipeRefreshLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view;
        function1.invoke(swipeRefreshLayout);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return swipeRefreshLayout;
    }

    @NotNull
    public static /* synthetic */ SwipeRefreshLayout themedSwipeRefreshLayout$default(ViewManager viewManager, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return swipeRefreshLayout;
    }

    @NotNull
    public static /* synthetic */ SwipeRefreshLayout themedSwipeRefreshLayout$default(ViewManager viewManager, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view;
        function1.invoke(swipeRefreshLayout);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return swipeRefreshLayout;
    }

    @NotNull
    public static final SwipeRefreshLayout themedSwipeRefreshLayout(@NotNull ViewManager viewManager, int i, @NotNull Function1<? super SwipeRefreshLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view;
        function1.invoke(swipeRefreshLayout);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return swipeRefreshLayout;
    }

    @NotNull
    public static final SwipeRefreshLayout swipeRefreshLayout(@NotNull Context context, @NotNull Function1<? super SwipeRefreshLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view;
        function1.invoke(swipeRefreshLayout);
        AnkoInternals.INSTANCE.addView(context, view);
        return swipeRefreshLayout;
    }

    @NotNull
    public static /* synthetic */ SwipeRefreshLayout themedSwipeRefreshLayout$default(Context context, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return swipeRefreshLayout;
    }

    @NotNull
    public static /* synthetic */ SwipeRefreshLayout themedSwipeRefreshLayout$default(Context context, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view;
        function1.invoke(swipeRefreshLayout);
        AnkoInternals.INSTANCE.addView(context, view);
        return swipeRefreshLayout;
    }

    @NotNull
    public static final SwipeRefreshLayout themedSwipeRefreshLayout(@NotNull Context context, int i, @NotNull Function1<? super SwipeRefreshLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view;
        function1.invoke(swipeRefreshLayout);
        AnkoInternals.INSTANCE.addView(context, view);
        return swipeRefreshLayout;
    }

    @NotNull
    public static final SwipeRefreshLayout swipeRefreshLayout(@NotNull Activity activity, @NotNull Function1<? super SwipeRefreshLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view;
        function1.invoke(swipeRefreshLayout);
        AnkoInternals.INSTANCE.addView(activity, view);
        return swipeRefreshLayout;
    }

    @NotNull
    public static /* synthetic */ SwipeRefreshLayout themedSwipeRefreshLayout$default(Activity activity, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return swipeRefreshLayout;
    }

    @NotNull
    public static /* synthetic */ SwipeRefreshLayout themedSwipeRefreshLayout$default(Activity activity, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view;
        function1.invoke(swipeRefreshLayout);
        AnkoInternals.INSTANCE.addView(activity, view);
        return swipeRefreshLayout;
    }

    @NotNull
    public static final SwipeRefreshLayout themedSwipeRefreshLayout(@NotNull Activity activity, int i, @NotNull Function1<? super SwipeRefreshLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view;
        function1.invoke(swipeRefreshLayout);
        AnkoInternals.INSTANCE.addView(activity, view);
        return swipeRefreshLayout;
    }

    @NotNull
    public static final FragmentTabHost fragmentTabHost(@NotNull ViewManager viewManager, @NotNull Function1<? super _FragmentTabHost, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        function1.invoke((_FragmentTabHost) view);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (FragmentTabHost) view;
    }

    @NotNull
    public static /* synthetic */ FragmentTabHost themedFragmentTabHost$default(ViewManager viewManager, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        _FragmentTabHost _fragmenttabhost = (_FragmentTabHost) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (FragmentTabHost) view;
    }

    @NotNull
    public static /* synthetic */ FragmentTabHost themedFragmentTabHost$default(ViewManager viewManager, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        function1.invoke((_FragmentTabHost) view);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (FragmentTabHost) view;
    }

    @NotNull
    public static final FragmentTabHost themedFragmentTabHost(@NotNull ViewManager viewManager, int i, @NotNull Function1<? super _FragmentTabHost, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        function1.invoke((_FragmentTabHost) view);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (FragmentTabHost) view;
    }

    @NotNull
    public static final FragmentTabHost fragmentTabHost(@NotNull Context context, @NotNull Function1<? super _FragmentTabHost, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        function1.invoke((_FragmentTabHost) view);
        AnkoInternals.INSTANCE.addView(context, view);
        return (FragmentTabHost) view;
    }

    @NotNull
    public static /* synthetic */ FragmentTabHost themedFragmentTabHost$default(Context context, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        _FragmentTabHost _fragmenttabhost = (_FragmentTabHost) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return (FragmentTabHost) view;
    }

    @NotNull
    public static /* synthetic */ FragmentTabHost themedFragmentTabHost$default(Context context, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        function1.invoke((_FragmentTabHost) view);
        AnkoInternals.INSTANCE.addView(context, view);
        return (FragmentTabHost) view;
    }

    @NotNull
    public static final FragmentTabHost themedFragmentTabHost(@NotNull Context context, int i, @NotNull Function1<? super _FragmentTabHost, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        function1.invoke((_FragmentTabHost) view);
        AnkoInternals.INSTANCE.addView(context, view);
        return (FragmentTabHost) view;
    }

    @NotNull
    public static final FragmentTabHost fragmentTabHost(@NotNull Activity activity, @NotNull Function1<? super _FragmentTabHost, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        function1.invoke((_FragmentTabHost) view);
        AnkoInternals.INSTANCE.addView(activity, view);
        return (FragmentTabHost) view;
    }

    @NotNull
    public static /* synthetic */ FragmentTabHost themedFragmentTabHost$default(Activity activity, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        _FragmentTabHost _fragmenttabhost = (_FragmentTabHost) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return (FragmentTabHost) view;
    }

    @NotNull
    public static /* synthetic */ FragmentTabHost themedFragmentTabHost$default(Activity activity, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        function1.invoke((_FragmentTabHost) view);
        AnkoInternals.INSTANCE.addView(activity, view);
        return (FragmentTabHost) view;
    }

    @NotNull
    public static final FragmentTabHost themedFragmentTabHost(@NotNull Activity activity, int i, @NotNull Function1<? super _FragmentTabHost, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        function1.invoke((_FragmentTabHost) view);
        AnkoInternals.INSTANCE.addView(activity, view);
        return (FragmentTabHost) view;
    }

    @NotNull
    public static final ViewPager viewPager(@NotNull ViewManager viewManager, @NotNull Function1<? super _ViewPager, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        function1.invoke((_ViewPager) view);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (ViewPager) view;
    }

    @NotNull
    public static /* synthetic */ ViewPager themedViewPager$default(ViewManager viewManager, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        _ViewPager _viewpager = (_ViewPager) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (ViewPager) view;
    }

    @NotNull
    public static /* synthetic */ ViewPager themedViewPager$default(ViewManager viewManager, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        function1.invoke((_ViewPager) view);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (ViewPager) view;
    }

    @NotNull
    public static final ViewPager themedViewPager(@NotNull ViewManager viewManager, int i, @NotNull Function1<? super _ViewPager, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        function1.invoke((_ViewPager) view);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (ViewPager) view;
    }

    @NotNull
    public static final ViewPager viewPager(@NotNull Context context, @NotNull Function1<? super _ViewPager, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        function1.invoke((_ViewPager) view);
        AnkoInternals.INSTANCE.addView(context, view);
        return (ViewPager) view;
    }

    @NotNull
    public static /* synthetic */ ViewPager themedViewPager$default(Context context, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        _ViewPager _viewpager = (_ViewPager) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return (ViewPager) view;
    }

    @NotNull
    public static /* synthetic */ ViewPager themedViewPager$default(Context context, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        function1.invoke((_ViewPager) view);
        AnkoInternals.INSTANCE.addView(context, view);
        return (ViewPager) view;
    }

    @NotNull
    public static final ViewPager themedViewPager(@NotNull Context context, int i, @NotNull Function1<? super _ViewPager, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        function1.invoke((_ViewPager) view);
        AnkoInternals.INSTANCE.addView(context, view);
        return (ViewPager) view;
    }

    @NotNull
    public static final ViewPager viewPager(@NotNull Activity activity, @NotNull Function1<? super _ViewPager, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        function1.invoke((_ViewPager) view);
        AnkoInternals.INSTANCE.addView(activity, view);
        return (ViewPager) view;
    }

    @NotNull
    public static /* synthetic */ ViewPager themedViewPager$default(Activity activity, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        _ViewPager _viewpager = (_ViewPager) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return (ViewPager) view;
    }

    @NotNull
    public static /* synthetic */ ViewPager themedViewPager$default(Activity activity, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        function1.invoke((_ViewPager) view);
        AnkoInternals.INSTANCE.addView(activity, view);
        return (ViewPager) view;
    }

    @NotNull
    public static final ViewPager themedViewPager(@NotNull Activity activity, int i, @NotNull Function1<? super _ViewPager, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        function1.invoke((_ViewPager) view);
        AnkoInternals.INSTANCE.addView(activity, view);
        return (ViewPager) view;
    }

    @NotNull
    public static final DrawerLayout drawerLayout(@NotNull ViewManager viewManager, @NotNull Function1<? super _DrawerLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        function1.invoke((_DrawerLayout) view);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (DrawerLayout) view;
    }

    @NotNull
    public static /* synthetic */ DrawerLayout themedDrawerLayout$default(ViewManager viewManager, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        _DrawerLayout _drawerlayout = (_DrawerLayout) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (DrawerLayout) view;
    }

    @NotNull
    public static /* synthetic */ DrawerLayout themedDrawerLayout$default(ViewManager viewManager, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        function1.invoke((_DrawerLayout) view);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (DrawerLayout) view;
    }

    @NotNull
    public static final DrawerLayout themedDrawerLayout(@NotNull ViewManager viewManager, int i, @NotNull Function1<? super _DrawerLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        function1.invoke((_DrawerLayout) view);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (DrawerLayout) view;
    }

    @NotNull
    public static final DrawerLayout drawerLayout(@NotNull Context context, @NotNull Function1<? super _DrawerLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        function1.invoke((_DrawerLayout) view);
        AnkoInternals.INSTANCE.addView(context, view);
        return (DrawerLayout) view;
    }

    @NotNull
    public static /* synthetic */ DrawerLayout themedDrawerLayout$default(Context context, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        _DrawerLayout _drawerlayout = (_DrawerLayout) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return (DrawerLayout) view;
    }

    @NotNull
    public static /* synthetic */ DrawerLayout themedDrawerLayout$default(Context context, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        function1.invoke((_DrawerLayout) view);
        AnkoInternals.INSTANCE.addView(context, view);
        return (DrawerLayout) view;
    }

    @NotNull
    public static final DrawerLayout themedDrawerLayout(@NotNull Context context, int i, @NotNull Function1<? super _DrawerLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        function1.invoke((_DrawerLayout) view);
        AnkoInternals.INSTANCE.addView(context, view);
        return (DrawerLayout) view;
    }

    @NotNull
    public static final DrawerLayout drawerLayout(@NotNull Activity activity, @NotNull Function1<? super _DrawerLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        function1.invoke((_DrawerLayout) view);
        AnkoInternals.INSTANCE.addView(activity, view);
        return (DrawerLayout) view;
    }

    @NotNull
    public static /* synthetic */ DrawerLayout themedDrawerLayout$default(Activity activity, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        _DrawerLayout _drawerlayout = (_DrawerLayout) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return (DrawerLayout) view;
    }

    @NotNull
    public static /* synthetic */ DrawerLayout themedDrawerLayout$default(Activity activity, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        function1.invoke((_DrawerLayout) view);
        AnkoInternals.INSTANCE.addView(activity, view);
        return (DrawerLayout) view;
    }

    @NotNull
    public static final DrawerLayout themedDrawerLayout(@NotNull Activity activity, int i, @NotNull Function1<? super _DrawerLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        function1.invoke((_DrawerLayout) view);
        AnkoInternals.INSTANCE.addView(activity, view);
        return (DrawerLayout) view;
    }

    @NotNull
    public static final NestedScrollView nestedScrollView(@NotNull ViewManager viewManager, @NotNull Function1<? super _NestedScrollView, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        function1.invoke((_NestedScrollView) view);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (NestedScrollView) view;
    }

    @NotNull
    public static /* synthetic */ NestedScrollView themedNestedScrollView$default(ViewManager viewManager, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        _NestedScrollView _nestedscrollview = (_NestedScrollView) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (NestedScrollView) view;
    }

    @NotNull
    public static /* synthetic */ NestedScrollView themedNestedScrollView$default(ViewManager viewManager, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        function1.invoke((_NestedScrollView) view);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (NestedScrollView) view;
    }

    @NotNull
    public static final NestedScrollView themedNestedScrollView(@NotNull ViewManager viewManager, int i, @NotNull Function1<? super _NestedScrollView, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        function1.invoke((_NestedScrollView) view);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (NestedScrollView) view;
    }

    @NotNull
    public static final NestedScrollView nestedScrollView(@NotNull Context context, @NotNull Function1<? super _NestedScrollView, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        function1.invoke((_NestedScrollView) view);
        AnkoInternals.INSTANCE.addView(context, view);
        return (NestedScrollView) view;
    }

    @NotNull
    public static /* synthetic */ NestedScrollView themedNestedScrollView$default(Context context, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        _NestedScrollView _nestedscrollview = (_NestedScrollView) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return (NestedScrollView) view;
    }

    @NotNull
    public static /* synthetic */ NestedScrollView themedNestedScrollView$default(Context context, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        function1.invoke((_NestedScrollView) view);
        AnkoInternals.INSTANCE.addView(context, view);
        return (NestedScrollView) view;
    }

    @NotNull
    public static final NestedScrollView themedNestedScrollView(@NotNull Context context, int i, @NotNull Function1<? super _NestedScrollView, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        function1.invoke((_NestedScrollView) view);
        AnkoInternals.INSTANCE.addView(context, view);
        return (NestedScrollView) view;
    }

    @NotNull
    public static final NestedScrollView nestedScrollView(@NotNull Activity activity, @NotNull Function1<? super _NestedScrollView, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        function1.invoke((_NestedScrollView) view);
        AnkoInternals.INSTANCE.addView(activity, view);
        return (NestedScrollView) view;
    }

    @NotNull
    public static /* synthetic */ NestedScrollView themedNestedScrollView$default(Activity activity, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        _NestedScrollView _nestedscrollview = (_NestedScrollView) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return (NestedScrollView) view;
    }

    @NotNull
    public static /* synthetic */ NestedScrollView themedNestedScrollView$default(Activity activity, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        function1.invoke((_NestedScrollView) view);
        AnkoInternals.INSTANCE.addView(activity, view);
        return (NestedScrollView) view;
    }

    @NotNull
    public static final NestedScrollView themedNestedScrollView(@NotNull Activity activity, int i, @NotNull Function1<? super _NestedScrollView, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        function1.invoke((_NestedScrollView) view);
        AnkoInternals.INSTANCE.addView(activity, view);
        return (NestedScrollView) view;
    }

    @NotNull
    public static final SlidingPaneLayout slidingPaneLayout(@NotNull ViewManager viewManager, @NotNull Function1<? super _SlidingPaneLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        function1.invoke((_SlidingPaneLayout) view);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (SlidingPaneLayout) view;
    }

    @NotNull
    public static /* synthetic */ SlidingPaneLayout themedSlidingPaneLayout$default(ViewManager viewManager, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        _SlidingPaneLayout _slidingpanelayout = (_SlidingPaneLayout) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (SlidingPaneLayout) view;
    }

    @NotNull
    public static /* synthetic */ SlidingPaneLayout themedSlidingPaneLayout$default(ViewManager viewManager, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        function1.invoke((_SlidingPaneLayout) view);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (SlidingPaneLayout) view;
    }

    @NotNull
    public static final SlidingPaneLayout themedSlidingPaneLayout(@NotNull ViewManager viewManager, int i, @NotNull Function1<? super _SlidingPaneLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        function1.invoke((_SlidingPaneLayout) view);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (SlidingPaneLayout) view;
    }

    @NotNull
    public static final SlidingPaneLayout slidingPaneLayout(@NotNull Context context, @NotNull Function1<? super _SlidingPaneLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        function1.invoke((_SlidingPaneLayout) view);
        AnkoInternals.INSTANCE.addView(context, view);
        return (SlidingPaneLayout) view;
    }

    @NotNull
    public static /* synthetic */ SlidingPaneLayout themedSlidingPaneLayout$default(Context context, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        _SlidingPaneLayout _slidingpanelayout = (_SlidingPaneLayout) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return (SlidingPaneLayout) view;
    }

    @NotNull
    public static /* synthetic */ SlidingPaneLayout themedSlidingPaneLayout$default(Context context, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        function1.invoke((_SlidingPaneLayout) view);
        AnkoInternals.INSTANCE.addView(context, view);
        return (SlidingPaneLayout) view;
    }

    @NotNull
    public static final SlidingPaneLayout themedSlidingPaneLayout(@NotNull Context context, int i, @NotNull Function1<? super _SlidingPaneLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        function1.invoke((_SlidingPaneLayout) view);
        AnkoInternals.INSTANCE.addView(context, view);
        return (SlidingPaneLayout) view;
    }

    @NotNull
    public static final SlidingPaneLayout slidingPaneLayout(@NotNull Activity activity, @NotNull Function1<? super _SlidingPaneLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        function1.invoke((_SlidingPaneLayout) view);
        AnkoInternals.INSTANCE.addView(activity, view);
        return (SlidingPaneLayout) view;
    }

    @NotNull
    public static /* synthetic */ SlidingPaneLayout themedSlidingPaneLayout$default(Activity activity, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        _SlidingPaneLayout _slidingpanelayout = (_SlidingPaneLayout) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return (SlidingPaneLayout) view;
    }

    @NotNull
    public static /* synthetic */ SlidingPaneLayout themedSlidingPaneLayout$default(Activity activity, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        function1.invoke((_SlidingPaneLayout) view);
        AnkoInternals.INSTANCE.addView(activity, view);
        return (SlidingPaneLayout) view;
    }

    @NotNull
    public static final SlidingPaneLayout themedSlidingPaneLayout(@NotNull Activity activity, int i, @NotNull Function1<? super _SlidingPaneLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        function1.invoke((_SlidingPaneLayout) view);
        AnkoInternals.INSTANCE.addView(activity, view);
        return (SlidingPaneLayout) view;
    }

    @NotNull
    public static final PagerTabStrip pagerTabStrip(@NotNull ViewManager viewManager) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return pagerTabStrip;
    }

    @NotNull
    public static final PagerTabStrip themedPagerTabStrip(@NotNull ViewManager viewManager, int i) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return pagerTabStrip;
    }

    @NotNull
    public static final PagerTabStrip pagerTabStrip(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return pagerTabStrip;
    }

    @NotNull
    public static final PagerTabStrip themedPagerTabStrip(@NotNull Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return pagerTabStrip;
    }

    @NotNull
    public static final PagerTabStrip pagerTabStrip(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return pagerTabStrip;
    }

    @NotNull
    public static final PagerTabStrip themedPagerTabStrip(@NotNull Activity activity, int i) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return pagerTabStrip;
    }

    @NotNull
    public static final PagerTitleStrip pagerTitleStrip(@NotNull ViewManager viewManager) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return pagerTitleStrip;
    }

    @NotNull
    public static final PagerTitleStrip themedPagerTitleStrip(@NotNull ViewManager viewManager, int i) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return pagerTitleStrip;
    }

    @NotNull
    public static final PagerTitleStrip pagerTitleStrip(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return pagerTitleStrip;
    }

    @NotNull
    public static final PagerTitleStrip themedPagerTitleStrip(@NotNull Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return pagerTitleStrip;
    }

    @NotNull
    public static final PagerTitleStrip pagerTitleStrip(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return pagerTitleStrip;
    }

    @NotNull
    public static final PagerTitleStrip themedPagerTitleStrip(@NotNull Activity activity, int i) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return pagerTitleStrip;
    }

    @NotNull
    public static final ContentLoadingProgressBar contentLoadingProgressBar(@NotNull ViewManager viewManager) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getCONTENT_LOADING_PROGRESS_BAR().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        ContentLoadingProgressBar contentLoadingProgressBar = (ContentLoadingProgressBar) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return contentLoadingProgressBar;
    }

    @NotNull
    public static final ContentLoadingProgressBar themedContentLoadingProgressBar(@NotNull ViewManager viewManager, int i) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getCONTENT_LOADING_PROGRESS_BAR().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        ContentLoadingProgressBar contentLoadingProgressBar = (ContentLoadingProgressBar) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return contentLoadingProgressBar;
    }

    @NotNull
    public static final Space space(@NotNull ViewManager viewManager) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getSPACE().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        Space space = (Space) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return space;
    }

    @NotNull
    public static final Space themedSpace(@NotNull ViewManager viewManager, int i) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getSPACE().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        Space space = (Space) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return space;
    }

    @NotNull
    public static final SwipeRefreshLayout swipeRefreshLayout(@NotNull ViewManager viewManager) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return swipeRefreshLayout;
    }

    @NotNull
    public static final SwipeRefreshLayout themedSwipeRefreshLayout(@NotNull ViewManager viewManager, int i) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return swipeRefreshLayout;
    }

    @NotNull
    public static final SwipeRefreshLayout swipeRefreshLayout(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return swipeRefreshLayout;
    }

    @NotNull
    public static final SwipeRefreshLayout themedSwipeRefreshLayout(@NotNull Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return swipeRefreshLayout;
    }

    @NotNull
    public static final SwipeRefreshLayout swipeRefreshLayout(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return swipeRefreshLayout;
    }

    @NotNull
    public static final SwipeRefreshLayout themedSwipeRefreshLayout(@NotNull Activity activity, int i) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return swipeRefreshLayout;
    }

    @NotNull
    public static final FragmentTabHost fragmentTabHost(@NotNull ViewManager viewManager) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        _FragmentTabHost _fragmenttabhost = (_FragmentTabHost) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (FragmentTabHost) view;
    }

    @NotNull
    public static final FragmentTabHost themedFragmentTabHost(@NotNull ViewManager viewManager, int i) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        _FragmentTabHost _fragmenttabhost = (_FragmentTabHost) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (FragmentTabHost) view;
    }

    @NotNull
    public static final FragmentTabHost fragmentTabHost(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        _FragmentTabHost _fragmenttabhost = (_FragmentTabHost) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return (FragmentTabHost) view;
    }

    @NotNull
    public static final FragmentTabHost themedFragmentTabHost(@NotNull Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        _FragmentTabHost _fragmenttabhost = (_FragmentTabHost) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return (FragmentTabHost) view;
    }

    @NotNull
    public static final FragmentTabHost fragmentTabHost(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        _FragmentTabHost _fragmenttabhost = (_FragmentTabHost) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return (FragmentTabHost) view;
    }

    @NotNull
    public static final FragmentTabHost themedFragmentTabHost(@NotNull Activity activity, int i) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        _FragmentTabHost _fragmenttabhost = (_FragmentTabHost) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return (FragmentTabHost) view;
    }

    @NotNull
    public static final ViewPager viewPager(@NotNull ViewManager viewManager) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        _ViewPager _viewpager = (_ViewPager) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (ViewPager) view;
    }

    @NotNull
    public static final ViewPager themedViewPager(@NotNull ViewManager viewManager, int i) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        _ViewPager _viewpager = (_ViewPager) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (ViewPager) view;
    }

    @NotNull
    public static final ViewPager viewPager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        _ViewPager _viewpager = (_ViewPager) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return (ViewPager) view;
    }

    @NotNull
    public static final ViewPager themedViewPager(@NotNull Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        _ViewPager _viewpager = (_ViewPager) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return (ViewPager) view;
    }

    @NotNull
    public static final ViewPager viewPager(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        _ViewPager _viewpager = (_ViewPager) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return (ViewPager) view;
    }

    @NotNull
    public static final ViewPager themedViewPager(@NotNull Activity activity, int i) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        _ViewPager _viewpager = (_ViewPager) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return (ViewPager) view;
    }

    @NotNull
    public static final DrawerLayout drawerLayout(@NotNull ViewManager viewManager) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        _DrawerLayout _drawerlayout = (_DrawerLayout) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (DrawerLayout) view;
    }

    @NotNull
    public static final DrawerLayout themedDrawerLayout(@NotNull ViewManager viewManager, int i) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        _DrawerLayout _drawerlayout = (_DrawerLayout) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (DrawerLayout) view;
    }

    @NotNull
    public static final DrawerLayout drawerLayout(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        _DrawerLayout _drawerlayout = (_DrawerLayout) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return (DrawerLayout) view;
    }

    @NotNull
    public static final DrawerLayout themedDrawerLayout(@NotNull Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        _DrawerLayout _drawerlayout = (_DrawerLayout) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return (DrawerLayout) view;
    }

    @NotNull
    public static final DrawerLayout drawerLayout(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        _DrawerLayout _drawerlayout = (_DrawerLayout) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return (DrawerLayout) view;
    }

    @NotNull
    public static final DrawerLayout themedDrawerLayout(@NotNull Activity activity, int i) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        _DrawerLayout _drawerlayout = (_DrawerLayout) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return (DrawerLayout) view;
    }

    @NotNull
    public static final NestedScrollView nestedScrollView(@NotNull ViewManager viewManager) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        _NestedScrollView _nestedscrollview = (_NestedScrollView) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (NestedScrollView) view;
    }

    @NotNull
    public static final NestedScrollView themedNestedScrollView(@NotNull ViewManager viewManager, int i) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        _NestedScrollView _nestedscrollview = (_NestedScrollView) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (NestedScrollView) view;
    }

    @NotNull
    public static final NestedScrollView nestedScrollView(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        _NestedScrollView _nestedscrollview = (_NestedScrollView) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return (NestedScrollView) view;
    }

    @NotNull
    public static final NestedScrollView themedNestedScrollView(@NotNull Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        _NestedScrollView _nestedscrollview = (_NestedScrollView) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return (NestedScrollView) view;
    }

    @NotNull
    public static final NestedScrollView nestedScrollView(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        _NestedScrollView _nestedscrollview = (_NestedScrollView) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return (NestedScrollView) view;
    }

    @NotNull
    public static final NestedScrollView themedNestedScrollView(@NotNull Activity activity, int i) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        _NestedScrollView _nestedscrollview = (_NestedScrollView) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return (NestedScrollView) view;
    }

    @NotNull
    public static final SlidingPaneLayout slidingPaneLayout(@NotNull ViewManager viewManager) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        _SlidingPaneLayout _slidingpanelayout = (_SlidingPaneLayout) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (SlidingPaneLayout) view;
    }

    @NotNull
    public static final SlidingPaneLayout themedSlidingPaneLayout(@NotNull ViewManager viewManager, int i) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        _SlidingPaneLayout _slidingpanelayout = (_SlidingPaneLayout) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (SlidingPaneLayout) view;
    }

    @NotNull
    public static final SlidingPaneLayout slidingPaneLayout(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        _SlidingPaneLayout _slidingpanelayout = (_SlidingPaneLayout) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return (SlidingPaneLayout) view;
    }

    @NotNull
    public static final SlidingPaneLayout themedSlidingPaneLayout(@NotNull Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        _SlidingPaneLayout _slidingpanelayout = (_SlidingPaneLayout) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return (SlidingPaneLayout) view;
    }

    @NotNull
    public static final SlidingPaneLayout slidingPaneLayout(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        _SlidingPaneLayout _slidingpanelayout = (_SlidingPaneLayout) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return (SlidingPaneLayout) view;
    }

    @NotNull
    public static final SlidingPaneLayout themedSlidingPaneLayout(@NotNull Activity activity, int i) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        _SlidingPaneLayout _slidingpanelayout = (_SlidingPaneLayout) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return (SlidingPaneLayout) view;
    }
}
