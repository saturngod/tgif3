package org.jetbrains.anko.sdk27.coroutines;

import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.view.MotionEvent;
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

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0012\u001a\u00020\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\tH\u0016JG\u0010\u0012\u001a\u00020\u000b27\u0010\u0015\u001a3\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0006¢\u0006\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010\u0016J\u001c\u0010\u0017\u001a\u00020\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\tH\u0016JG\u0010\u0017\u001a\u00020\u000b27\u0010\u0015\u001a3\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0006¢\u0006\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010\u0016J\u001c\u0010\u0018\u001a\u00020\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\tH\u0016JG\u0010\u0018\u001a\u00020\u000b27\u0010\u0015\u001a3\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0006¢\u0006\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010\u0016J\u001c\u0010\u0019\u001a\u00020\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\tH\u0016JG\u0010\u0019\u001a\u00020\u000b27\u0010\u0015\u001a3\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0006¢\u0006\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010\u0016RF\u0010\u0005\u001a5\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u0006¢\u0006\u0002\b\rX\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u000eRF\u0010\u000f\u001a5\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u0006¢\u0006\u0002\b\rX\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u000eRF\u0010\u0010\u001a5\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u0006¢\u0006\u0002\b\rX\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u000eRF\u0010\u0011\u001a5\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u0006¢\u0006\u0002\b\rX\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/anko/sdk27/coroutines/__GestureOverlayView_OnGestureListener;", "Landroid/gesture/GestureOverlayView$OnGestureListener;", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "_onGesture", "Lkotlin/Function4;", "Lkotlinx/coroutines/CoroutineScope;", "Landroid/gesture/GestureOverlayView;", "Landroid/view/MotionEvent;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "Lkotlin/jvm/functions/Function4;", "_onGestureCancelled", "_onGestureEnded", "_onGestureStarted", "onGesture", "overlay", "event", "listener", "(Lkotlin/jvm/functions/Function4;)V", "onGestureCancelled", "onGestureEnded", "onGestureStarted", "anko-sdk27-coroutines_release"}, k = 1, mv = {1, 1, 13})
/* compiled from: ListenersWithCoroutines.kt */
public final class __GestureOverlayView_OnGestureListener implements OnGestureListener {
    private Function4<? super CoroutineScope, ? super GestureOverlayView, ? super MotionEvent, ? super Continuation<? super Unit>, ? extends Object> _onGesture;
    private Function4<? super CoroutineScope, ? super GestureOverlayView, ? super MotionEvent, ? super Continuation<? super Unit>, ? extends Object> _onGestureCancelled;
    private Function4<? super CoroutineScope, ? super GestureOverlayView, ? super MotionEvent, ? super Continuation<? super Unit>, ? extends Object> _onGestureEnded;
    private Function4<? super CoroutineScope, ? super GestureOverlayView, ? super MotionEvent, ? super Continuation<? super Unit>, ? extends Object> _onGestureStarted;
    private final CoroutineContext context;

    public __GestureOverlayView_OnGestureListener(@NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        this.context = coroutineContext;
    }

    public void onGestureStarted(@Nullable GestureOverlayView gestureOverlayView, @Nullable MotionEvent motionEvent) {
        Function4 function4 = this._onGestureStarted;
        if (function4 != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.context, null, new __GestureOverlayView_OnGestureListener$onGestureStarted$1(function4, gestureOverlayView, motionEvent, null), 2, null);
        }
    }

    public final void onGestureStarted(@NotNull Function4<? super CoroutineScope, ? super GestureOverlayView, ? super MotionEvent, ? super Continuation<? super Unit>, ? extends Object> function4) {
        Intrinsics.checkParameterIsNotNull(function4, "listener");
        this._onGestureStarted = function4;
    }

    public void onGesture(@Nullable GestureOverlayView gestureOverlayView, @Nullable MotionEvent motionEvent) {
        Function4 function4 = this._onGesture;
        if (function4 != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.context, null, new __GestureOverlayView_OnGestureListener$onGesture$1(function4, gestureOverlayView, motionEvent, null), 2, null);
        }
    }

    public final void onGesture(@NotNull Function4<? super CoroutineScope, ? super GestureOverlayView, ? super MotionEvent, ? super Continuation<? super Unit>, ? extends Object> function4) {
        Intrinsics.checkParameterIsNotNull(function4, "listener");
        this._onGesture = function4;
    }

    public void onGestureEnded(@Nullable GestureOverlayView gestureOverlayView, @Nullable MotionEvent motionEvent) {
        Function4 function4 = this._onGestureEnded;
        if (function4 != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.context, null, new __GestureOverlayView_OnGestureListener$onGestureEnded$1(function4, gestureOverlayView, motionEvent, null), 2, null);
        }
    }

    public final void onGestureEnded(@NotNull Function4<? super CoroutineScope, ? super GestureOverlayView, ? super MotionEvent, ? super Continuation<? super Unit>, ? extends Object> function4) {
        Intrinsics.checkParameterIsNotNull(function4, "listener");
        this._onGestureEnded = function4;
    }

    public void onGestureCancelled(@Nullable GestureOverlayView gestureOverlayView, @Nullable MotionEvent motionEvent) {
        Function4 function4 = this._onGestureCancelled;
        if (function4 != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.context, null, new __GestureOverlayView_OnGestureListener$onGestureCancelled$1(function4, gestureOverlayView, motionEvent, null), 2, null);
        }
    }

    public final void onGestureCancelled(@NotNull Function4<? super CoroutineScope, ? super GestureOverlayView, ? super MotionEvent, ? super Continuation<? super Unit>, ? extends Object> function4) {
        Intrinsics.checkParameterIsNotNull(function4, "listener");
        this._onGestureCancelled = function4;
    }
}
