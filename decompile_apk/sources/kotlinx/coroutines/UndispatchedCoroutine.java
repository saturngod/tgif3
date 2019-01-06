package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.ScopeCoroutine;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8PX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/UndispatchedCoroutine;", "T", "Lkotlinx/coroutines/internal/ScopeCoroutine;", "context", "Lkotlin/coroutines/CoroutineContext;", "uCont", "Lkotlin/coroutines/Continuation;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)V", "defaultResumeMode", "", "getDefaultResumeMode$kotlinx_coroutines_core", "()I", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Builders.common.kt */
final class UndispatchedCoroutine<T> extends ScopeCoroutine<T> {
    public int getDefaultResumeMode$kotlinx_coroutines_core() {
        return 3;
    }

    public UndispatchedCoroutine(@NotNull CoroutineContext coroutineContext, @NotNull Continuation<? super T> continuation) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(continuation, "uCont");
        super(coroutineContext, continuation);
    }
}
