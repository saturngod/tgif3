package org.jetbrains.anko.sdk27.coroutines;

import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturingListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u000f\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\bH\u0016J?\u0010\u000f\u001a\u00020\n2/\u0010\u0011\u001a+\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0006¢\u0006\u0002\b\fø\u0001\u0000¢\u0006\u0002\u0010\u0012J\u0012\u0010\u0013\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\bH\u0016J?\u0010\u0013\u001a\u00020\n2/\u0010\u0011\u001a+\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0006¢\u0006\u0002\b\fø\u0001\u0000¢\u0006\u0002\u0010\u0012R>\u0010\u0005\u001a-\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0018\u00010\u0006¢\u0006\u0002\b\fX\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\rR>\u0010\u000e\u001a-\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0018\u00010\u0006¢\u0006\u0002\b\fX\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/anko/sdk27/coroutines/__GestureOverlayView_OnGesturingListener;", "Landroid/gesture/GestureOverlayView$OnGesturingListener;", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "_onGesturingEnded", "Lkotlin/Function3;", "Lkotlinx/coroutines/CoroutineScope;", "Landroid/gesture/GestureOverlayView;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "Lkotlin/jvm/functions/Function3;", "_onGesturingStarted", "onGesturingEnded", "overlay", "listener", "(Lkotlin/jvm/functions/Function3;)V", "onGesturingStarted", "anko-sdk27-coroutines_release"}, k = 1, mv = {1, 1, 13})
/* compiled from: ListenersWithCoroutines.kt */
public final class __GestureOverlayView_OnGesturingListener implements OnGesturingListener {
    private Function3<? super CoroutineScope, ? super GestureOverlayView, ? super Continuation<? super Unit>, ? extends Object> _onGesturingEnded;
    private Function3<? super CoroutineScope, ? super GestureOverlayView, ? super Continuation<? super Unit>, ? extends Object> _onGesturingStarted;
    private final CoroutineContext context;

    public __GestureOverlayView_OnGesturingListener(@NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        this.context = coroutineContext;
    }

    public void onGesturingStarted(@Nullable GestureOverlayView gestureOverlayView) {
        Function3 function3 = this._onGesturingStarted;
        if (function3 != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.context, null, new __GestureOverlayView_OnGesturingListener$onGesturingStarted$1(function3, gestureOverlayView, null), 2, null);
        }
    }

    public final void onGesturingStarted(@NotNull Function3<? super CoroutineScope, ? super GestureOverlayView, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkParameterIsNotNull(function3, "listener");
        this._onGesturingStarted = function3;
    }

    public void onGesturingEnded(@Nullable GestureOverlayView gestureOverlayView) {
        Function3 function3 = this._onGesturingEnded;
        if (function3 != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.context, null, new __GestureOverlayView_OnGesturingListener$onGesturingEnded$1(function3, gestureOverlayView, null), 2, null);
        }
    }

    public final void onGesturingEnded(@NotNull Function3<? super CoroutineScope, ? super GestureOverlayView, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkParameterIsNotNull(function3, "listener");
        this._onGesturingEnded = function3;
    }
}
