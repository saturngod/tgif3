package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a=\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u001a\b\u0004\u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0006\u0012\u0004\u0012\u00020\u00070\u0005HHø\u0001\u0000¢\u0006\u0002\u0010\b\u001a3\u0010\t\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u001a\b\u0004\u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0006\u0012\u0004\u0012\u00020\u00070\u0005HHø\u0001\u0000¢\u0006\u0002\u0010\n\u001a\u0018\u0010\u000b\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\f\u001a\u00020\rH\u0007\u001a\u0018\u0010\u000e\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"suspendAtomicCancellableCoroutine", "T", "holdCancellability", "", "block", "Lkotlin/Function1;", "Lkotlinx/coroutines/CancellableContinuation;", "", "(ZLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "suspendCancellableCoroutine", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "disposeOnCancellation", "handle", "Lkotlinx/coroutines/DisposableHandle;", "removeOnCancellation", "node", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: CancellableContinuation.kt */
public final class CancellableContinuationKt {
    private static final <T> Object suspendCancellableCoroutine(Function1<? super CancellableContinuation<? super T>, Unit> function1, Continuation<? super T> continuation) {
        InlineMarker.mark(0);
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        function1.invoke(cancellableContinuationImpl);
        function1 = cancellableContinuationImpl.getResult();
        if (function1 == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        InlineMarker.mark(1);
        return function1;
    }

    @InternalCoroutinesApi
    private static final <T> Object suspendAtomicCancellableCoroutine(boolean z, Function1<? super CancellableContinuation<? super T>, Unit> function1, Continuation<? super T> continuation) {
        InlineMarker.mark(0);
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 0);
        if (!z) {
            cancellableContinuationImpl.initCancellability();
        }
        function1.invoke(cancellableContinuationImpl);
        z = cancellableContinuationImpl.getResult();
        if (z == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        InlineMarker.mark(1);
        return z;
    }

    public static final void removeOnCancellation(@NotNull CancellableContinuation<?> cancellableContinuation, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
        Intrinsics.checkParameterIsNotNull(cancellableContinuation, "receiver$0");
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "node");
        cancellableContinuation.invokeOnCancellation(new RemoveOnCancel(lockFreeLinkedListNode));
    }

    @InternalCoroutinesApi
    public static final void disposeOnCancellation(@NotNull CancellableContinuation<?> cancellableContinuation, @NotNull DisposableHandle disposableHandle) {
        Intrinsics.checkParameterIsNotNull(cancellableContinuation, "receiver$0");
        Intrinsics.checkParameterIsNotNull(disposableHandle, "handle");
        cancellableContinuation.invokeOnCancellation(new DisposeOnCancel(disposableHandle));
    }

    @InternalCoroutinesApi
    static /* synthetic */ Object suspendAtomicCancellableCoroutine$default(boolean z, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        InlineMarker.mark(0);
        i = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 0);
        if (!z) {
            i.initCancellability();
        }
        function1.invoke(i);
        z = i.getResult();
        if (z == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        InlineMarker.mark(1);
        return z;
    }
}
