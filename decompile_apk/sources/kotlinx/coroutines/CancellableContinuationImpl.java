package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@PublishedApi
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0011\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00060\u0004j\u0002`\u0005B\u001b\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001d\u0010\u0013\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0012H\u0016¢\u0006\u0002\u0010\u0015J\b\u0010\u0016\u001a\u00020\u0010H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0014J!\u0010\u0019\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u001a\u001a\u00028\u00002\b\u0010\u001b\u001a\u0004\u0018\u00010\u0012H\u0016¢\u0006\u0002\u0010\u001cJ\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0019\u0010 \u001a\u00020\u0010*\u00020!2\u0006\u0010\u001a\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\"J\u0014\u0010#\u001a\u00020\u0010*\u00020!2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016R\u0014\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006$"}, d2 = {"Lkotlinx/coroutines/CancellableContinuationImpl;", "T", "Lkotlinx/coroutines/AbstractContinuation;", "Lkotlinx/coroutines/CancellableContinuation;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "delegate", "Lkotlin/coroutines/Continuation;", "resumeMode", "", "(Lkotlin/coroutines/Continuation;I)V", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "completeResume", "", "token", "", "getSuccessfulResult", "state", "(Ljava/lang/Object;)Ljava/lang/Object;", "initCancellability", "nameString", "", "tryResume", "value", "idempotent", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "tryResumeWithException", "exception", "", "resumeUndispatched", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lkotlinx/coroutines/CoroutineDispatcher;Ljava/lang/Object;)V", "resumeUndispatchedWithException", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: CancellableContinuation.kt */
public class CancellableContinuationImpl<T> extends AbstractContinuation<T> implements CancellableContinuation<T>, Runnable {
    @NotNull
    private final CoroutineContext context;

    public CancellableContinuationImpl(@NotNull Continuation<? super T> continuation, int i) {
        Intrinsics.checkParameterIsNotNull(continuation, "delegate");
        super(continuation, i);
        this.context = continuation.getContext();
    }

    @NotNull
    public CoroutineContext getContext() {
        return this.context;
    }

    public void initCancellability() {
        initParentJobInternal$kotlinx_coroutines_core((Job) getDelegate().getContext().get(Job.Key));
    }

    public void completeResume(@NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "token");
        completeStateUpdate((NotCompleted) obj, getState$kotlinx_coroutines_core(), getResumeMode());
    }

    public void resumeUndispatched(@NotNull CoroutineDispatcher coroutineDispatcher, T t) {
        Intrinsics.checkParameterIsNotNull(coroutineDispatcher, "receiver$0");
        Continuation delegate = getDelegate();
        CoroutineDispatcher coroutineDispatcher2 = null;
        if (!(delegate instanceof DispatchedContinuation)) {
            delegate = null;
        }
        DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) delegate;
        if (dispatchedContinuation != null) {
            coroutineDispatcher2 = dispatchedContinuation.dispatcher;
        }
        resumeImpl(t, coroutineDispatcher2 == coroutineDispatcher ? 3 : getResumeMode());
    }

    public void resumeUndispatchedWithException(@NotNull CoroutineDispatcher coroutineDispatcher, @NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(coroutineDispatcher, "receiver$0");
        Intrinsics.checkParameterIsNotNull(th, "exception");
        Continuation delegate = getDelegate();
        CoroutineDispatcher coroutineDispatcher2 = null;
        if (!(delegate instanceof DispatchedContinuation)) {
            delegate = null;
        }
        DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) delegate;
        CompletedExceptionally completedExceptionally = new CompletedExceptionally(th);
        if (dispatchedContinuation != null) {
            coroutineDispatcher2 = dispatchedContinuation.dispatcher;
        }
        resumeImpl(completedExceptionally, coroutineDispatcher2 == coroutineDispatcher ? 3 : getResumeMode());
    }

    public <T> T getSuccessfulResult(@Nullable Object obj) {
        return obj instanceof CompletedIdempotentResult ? ((CompletedIdempotentResult) obj).result : obj;
    }

    @NotNull
    protected String nameString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CancellableContinuation(");
        stringBuilder.append(DebugKt.toDebugString(getDelegate()));
        stringBuilder.append(')');
        return stringBuilder.toString();
    }

    @Nullable
    public Object tryResume(T t, @Nullable Object obj) {
        Object state$kotlinx_coroutines_core;
        Object obj2;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (state$kotlinx_coroutines_core instanceof NotCompleted) {
                if (obj == null) {
                    obj2 = t;
                } else {
                    obj2 = new CompletedIdempotentResult(obj, t, (NotCompleted) state$kotlinx_coroutines_core);
                }
            } else if (!(state$kotlinx_coroutines_core instanceof CompletedIdempotentResult)) {
                return null;
            } else {
                CompletedIdempotentResult completedIdempotentResult = (CompletedIdempotentResult) state$kotlinx_coroutines_core;
                if (completedIdempotentResult.idempotentResume != obj) {
                    return null;
                }
                if ((completedIdempotentResult.result == t ? true : null) != null) {
                    return completedIdempotentResult.token;
                }
                throw ((Throwable) new IllegalStateException("Non-idempotent resume".toString()));
            }
        } while (!tryUpdateStateToFinal((NotCompleted) state$kotlinx_coroutines_core, obj2));
        return state$kotlinx_coroutines_core;
    }

    @Nullable
    public Object tryResumeWithException(@NotNull Throwable th) {
        Object state$kotlinx_coroutines_core;
        Intrinsics.checkParameterIsNotNull(th, "exception");
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof NotCompleted)) {
                return null;
            }
        } while (!tryUpdateStateToFinal((NotCompleted) state$kotlinx_coroutines_core, new CompletedExceptionally(th)));
        return state$kotlinx_coroutines_core;
    }
}
