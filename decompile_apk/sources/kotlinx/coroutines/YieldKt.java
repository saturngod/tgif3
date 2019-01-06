package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0002\u0010\u0002\u001a\f\u0010\u0003\u001a\u00020\u0001*\u00020\u0004H\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0005"}, d2 = {"yield", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkCompletion", "Lkotlin/coroutines/CoroutineContext;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: Yield.kt */
public final class YieldKt {
    @Nullable
    public static final Object yield(@NotNull Continuation<? super Unit> continuation) {
        Object obj;
        CoroutineContext context = continuation.getContext();
        checkCompletion(context);
        Continuation intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
        if (!(intercepted instanceof DispatchedContinuation)) {
            intercepted = null;
        }
        DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) intercepted;
        if (dispatchedContinuation == null) {
            obj = Unit.INSTANCE;
        } else if (dispatchedContinuation.dispatcher.isDispatchNeeded(context)) {
            dispatchedContinuation.dispatchYield$kotlinx_coroutines_core(Unit.INSTANCE);
            obj = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        } else {
            obj = DispatchedKt.yieldUndispatched(dispatchedContinuation) ? IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() : Unit.INSTANCE;
        }
        if (obj == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return obj;
    }

    public static final void checkCompletion(@NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "receiver$0");
        Job job = (Job) coroutineContext.get(Job.Key);
        if (job == null) {
            return;
        }
        if (!job.isActive()) {
            throw job.getCancellationException();
        }
    }
}
