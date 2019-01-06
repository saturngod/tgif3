package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.ScopeCoroutine;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ'\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0010¢\u0006\u0002\b\u0016J\b\u0010\u0017\u001a\u00020\u0015H\u0002J\b\u0010\u0018\u001a\u00020\u0015H\u0002R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000b8PX\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lkotlinx/coroutines/DispatchedCoroutine;", "T", "Lkotlinx/coroutines/internal/ScopeCoroutine;", "context", "Lkotlin/coroutines/CoroutineContext;", "uCont", "Lkotlin/coroutines/Continuation;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)V", "_decision", "Lkotlinx/atomicfu/AtomicInt;", "defaultResumeMode", "", "getDefaultResumeMode$kotlinx_coroutines_core", "()I", "getResult", "", "onCompletionInternal", "", "state", "mode", "suppressed", "", "onCompletionInternal$kotlinx_coroutines_core", "tryResume", "trySuspend", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Builders.common.kt */
final class DispatchedCoroutine<T> extends ScopeCoroutine<T> {
    private static final AtomicIntegerFieldUpdater _decision$FU = AtomicIntegerFieldUpdater.newUpdater(DispatchedCoroutine.class, "_decision");
    private volatile int _decision = null;

    public int getDefaultResumeMode$kotlinx_coroutines_core() {
        return 0;
    }

    public DispatchedCoroutine(@NotNull CoroutineContext coroutineContext, @NotNull Continuation<? super T> continuation) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(continuation, "uCont");
        super(coroutineContext, continuation);
    }

    public void onCompletionInternal$kotlinx_coroutines_core(@Nullable Object obj, int i, boolean z) {
        if (!tryResume()) {
            super.onCompletionInternal$kotlinx_coroutines_core(obj, i, z);
        }
    }

    @Nullable
    public final Object getResult() {
        if (trySuspend()) {
            return IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (!(state$kotlinx_coroutines_core instanceof CompletedExceptionally)) {
            return state$kotlinx_coroutines_core;
        }
        throw ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
    }

    private final boolean trySuspend() {
        do {
            int i = this._decision;
            if (i != 0) {
                if (i == 2) {
                    return false;
                }
                throw new IllegalStateException("Already suspended".toString());
            }
        } while (!_decision$FU.compareAndSet(this, 0, 1));
        return true;
    }

    private final boolean tryResume() {
        do {
            switch (this._decision) {
                case 0:
                    break;
                case 1:
                    return false;
                default:
                    throw new IllegalStateException("Already resumed".toString());
            }
        } while (!_decision$FU.compareAndSet(this, 0, 2));
        return true;
    }
}
