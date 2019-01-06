package org.jetbrains.anko.sdk27.coroutines;

import android.gesture.GestureOverlayView;
import android.view.MotionEvent;
import kotlin.Metadata;
import kotlin.Result.Failure;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "org/jetbrains/anko/sdk27/coroutines/__GestureOverlayView_OnGestureListener$onGestureStarted$1", f = "ListenersWithCoroutines.kt", i = {}, l = {142, 144}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ListenersWithCoroutines.kt */
final class __GestureOverlayView_OnGestureListener$onGestureStarted$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MotionEvent $event;
    final /* synthetic */ Function4 $handler;
    final /* synthetic */ GestureOverlayView $overlay;
    int label;
    private CoroutineScope p$;

    __GestureOverlayView_OnGestureListener$onGestureStarted$1(Function4 function4, GestureOverlayView gestureOverlayView, MotionEvent motionEvent, Continuation continuation) {
        this.$handler = function4;
        this.$overlay = gestureOverlayView;
        this.$event = motionEvent;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        Continuation __gestureoverlayview_ongesturelistener_ongesturestarted_1 = new __GestureOverlayView_OnGestureListener$onGestureStarted$1(this.$handler, this.$overlay, this.$event, continuation);
        __gestureoverlayview_ongesturelistener_ongesturestarted_1.p$ = (CoroutineScope) obj;
        return __gestureoverlayview_ongesturelistener_ongesturestarted_1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((__GestureOverlayView_OnGestureListener$onGestureStarted$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                if (obj instanceof Failure) {
                    throw ((Failure) obj).exception;
                }
                obj = this.p$;
                Function4 function4 = this.$handler;
                GestureOverlayView gestureOverlayView = this.$overlay;
                MotionEvent motionEvent = this.$event;
                this.label = 1;
                if (function4.invoke(obj, gestureOverlayView, motionEvent, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                if (!(obj instanceof Failure)) {
                    break;
                }
                throw ((Failure) obj).exception;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
