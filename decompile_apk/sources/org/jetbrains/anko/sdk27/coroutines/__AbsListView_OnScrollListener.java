package org.jetbrains.anko.sdk27.coroutines;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J*\u0010\u0012\u001a\u00020\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\tH\u0016JQ\u0010\u0012\u001a\u00020\u000b2A\u0010\u0017\u001a=\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0006¢\u0006\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u001a\u0010\u0019\u001a\u00020\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\b2\u0006\u0010\u001a\u001a\u00020\tH\u0016JE\u0010\u0019\u001a\u00020\u000b25\u0010\u0017\u001a1\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0010¢\u0006\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010\u001bRP\u0010\u0005\u001a?\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u0006¢\u0006\u0002\b\rX\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u000eRD\u0010\u000f\u001a3\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u0010¢\u0006\u0002\b\rX\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/anko/sdk27/coroutines/__AbsListView_OnScrollListener;", "Landroid/widget/AbsListView$OnScrollListener;", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "_onScroll", "Lkotlin/Function6;", "Lkotlinx/coroutines/CoroutineScope;", "Landroid/widget/AbsListView;", "", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "Lkotlin/jvm/functions/Function6;", "_onScrollStateChanged", "Lkotlin/Function4;", "Lkotlin/jvm/functions/Function4;", "onScroll", "view", "firstVisibleItem", "visibleItemCount", "totalItemCount", "listener", "(Lkotlin/jvm/functions/Function6;)V", "onScrollStateChanged", "scrollState", "(Lkotlin/jvm/functions/Function4;)V", "anko-sdk27-coroutines_release"}, k = 1, mv = {1, 1, 13})
/* compiled from: ListenersWithCoroutines.kt */
public final class __AbsListView_OnScrollListener implements OnScrollListener {
    private Function6<? super CoroutineScope, ? super AbsListView, ? super Integer, ? super Integer, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> _onScroll;
    private Function4<? super CoroutineScope, ? super AbsListView, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> _onScrollStateChanged;
    private final CoroutineContext context;

    public __AbsListView_OnScrollListener(@NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        this.context = coroutineContext;
    }

    public void onScrollStateChanged(@Nullable AbsListView absListView, int i) {
        Function4 function4 = this._onScrollStateChanged;
        if (function4 != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.context, null, new __AbsListView_OnScrollListener$onScrollStateChanged$1(function4, absListView, i, null), 2, null);
        }
    }

    public final void onScrollStateChanged(@NotNull Function4<? super CoroutineScope, ? super AbsListView, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> function4) {
        Intrinsics.checkParameterIsNotNull(function4, "listener");
        this._onScrollStateChanged = function4;
    }

    public void onScroll(@Nullable AbsListView absListView, int i, int i2, int i3) {
        Function6 function6 = this._onScroll;
        if (function6 != null) {
            CoroutineScope coroutineScope = GlobalScope.INSTANCE;
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, this.context, null, new __AbsListView_OnScrollListener$onScroll$1(function6, absListView, i, i2, i3, null), 2, null);
        }
    }

    public final void onScroll(@NotNull Function6<? super CoroutineScope, ? super AbsListView, ? super Integer, ? super Integer, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> function6) {
        Intrinsics.checkParameterIsNotNull(function6, "listener");
        this._onScroll = function6;
    }
}
