package org.jetbrains.anko.sdk27.coroutines;

import android.view.View;
import android.view.ViewGroup.OnHierarchyChangeListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\u000f\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\bH\u0016JG\u0010\u000f\u001a\u00020\n27\u0010\u0012\u001a3\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0006¢\u0006\u0002\b\fø\u0001\u0000¢\u0006\u0002\u0010\u0013J\u001c\u0010\u0014\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\bH\u0016JG\u0010\u0014\u001a\u00020\n27\u0010\u0012\u001a3\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0006¢\u0006\u0002\b\fø\u0001\u0000¢\u0006\u0002\u0010\u0013RF\u0010\u0005\u001a5\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0018\u00010\u0006¢\u0006\u0002\b\fX\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\rRF\u0010\u000e\u001a5\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0018\u00010\u0006¢\u0006\u0002\b\fX\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/anko/sdk27/coroutines/__ViewGroup_OnHierarchyChangeListener;", "Landroid/view/ViewGroup$OnHierarchyChangeListener;", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "_onChildViewAdded", "Lkotlin/Function4;", "Lkotlinx/coroutines/CoroutineScope;", "Landroid/view/View;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "Lkotlin/jvm/functions/Function4;", "_onChildViewRemoved", "onChildViewAdded", "parent", "child", "listener", "(Lkotlin/jvm/functions/Function4;)V", "onChildViewRemoved", "anko-sdk27-coroutines_release"}, k = 1, mv = {1, 1, 13})
/* compiled from: ListenersWithCoroutines.kt */
public final class __ViewGroup_OnHierarchyChangeListener implements OnHierarchyChangeListener {
    private Function4<? super CoroutineScope, ? super View, ? super View, ? super Continuation<? super Unit>, ? extends Object> _onChildViewAdded;
    private Function4<? super CoroutineScope, ? super View, ? super View, ? super Continuation<? super Unit>, ? extends Object> _onChildViewRemoved;
    private final CoroutineContext context;

    public __ViewGroup_OnHierarchyChangeListener(@NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        this.context = coroutineContext;
    }

    public void onChildViewAdded(@Nullable View view, @Nullable View view2) {
        Function4 function4 = this._onChildViewAdded;
        if (function4 != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.context, null, new __ViewGroup_OnHierarchyChangeListener$onChildViewAdded$1(function4, view, view2, null), 2, null);
        }
    }

    public final void onChildViewAdded(@NotNull Function4<? super CoroutineScope, ? super View, ? super View, ? super Continuation<? super Unit>, ? extends Object> function4) {
        Intrinsics.checkParameterIsNotNull(function4, "listener");
        this._onChildViewAdded = function4;
    }

    public void onChildViewRemoved(@Nullable View view, @Nullable View view2) {
        Function4 function4 = this._onChildViewRemoved;
        if (function4 != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.context, null, new __ViewGroup_OnHierarchyChangeListener$onChildViewRemoved$1(function4, view, view2, null), 2, null);
        }
    }

    public final void onChildViewRemoved(@NotNull Function4<? super CoroutineScope, ? super View, ? super View, ? super Continuation<? super Unit>, ? extends Object> function4) {
        Intrinsics.checkParameterIsNotNull(function4, "listener");
        this._onChildViewRemoved = function4;
    }
}
