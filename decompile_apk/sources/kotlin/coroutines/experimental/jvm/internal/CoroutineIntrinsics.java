package kotlin.coroutines.experimental.jvm.internal;

import kotlin.Metadata;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.ContinuationInterceptor;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a*\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0000\u001a \u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001¨\u0006\u0007"}, d2 = {"interceptContinuationIfNeeded", "Lkotlin/coroutines/experimental/Continuation;", "T", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "continuation", "normalizeContinuation", "kotlin-stdlib_coroutines"}, k = 2, mv = {1, 1, 13})
@JvmName(name = "CoroutineIntrinsics")
/* compiled from: CoroutineIntrinsics.kt */
public final class CoroutineIntrinsics {
    @NotNull
    public static final <T> Continuation<T> normalizeContinuation(@NotNull Continuation<? super T> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        CoroutineImpl coroutineImpl = (CoroutineImpl) (!(continuation instanceof CoroutineImpl) ? null : continuation);
        if (coroutineImpl == null) {
            return continuation;
        }
        Continuation<? super T> facade = coroutineImpl.getFacade();
        return facade != null ? facade : continuation;
    }

    @NotNull
    public static final <T> Continuation<T> interceptContinuationIfNeeded(@NotNull CoroutineContext coroutineContext, @NotNull Continuation<? super T> continuation) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) coroutineContext.get(ContinuationInterceptor.Key);
        if (continuationInterceptor != null) {
            coroutineContext = continuationInterceptor.interceptContinuation(continuation);
            if (coroutineContext != null) {
                return coroutineContext;
            }
        }
        return continuation;
    }
}
