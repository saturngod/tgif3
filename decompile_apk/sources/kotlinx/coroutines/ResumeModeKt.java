package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Result;
import kotlin.Result.Companion;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.ThreadContextKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0002\b\u0002\u001a-\u0010\u0010\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u00132\u0006\u0010\u0014\u001a\u0002H\u00122\u0006\u0010\u0015\u001a\u00020\u0001H\u0000¢\u0006\u0002\u0010\u0016\u001a-\u0010\u0017\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u00132\u0006\u0010\u0014\u001a\u0002H\u00122\u0006\u0010\u0015\u001a\u00020\u0001H\u0000¢\u0006\u0002\u0010\u0016\u001a(\u0010\u0018\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u0001H\u0000\u001a(\u0010\u001b\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u0001H\u0000\"\u0016\u0010\u0000\u001a\u00020\u00018\u0000XT¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0004\u001a\u00020\u00018\u0000XT¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003\"\u0016\u0010\u0006\u001a\u00020\u00018\u0000XT¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0003\"\u0016\u0010\b\u001a\u00020\u00018\u0000XT¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\u0003\"\u0016\u0010\n\u001a\u00020\u00018\u0000XT¢\u0006\b\n\u0000\u0012\u0004\b\u000b\u0010\u0003\"\u0018\u0010\f\u001a\u00020\r*\u00020\u00018@X\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u000e\"\u0018\u0010\u000f\u001a\u00020\r*\u00020\u00018@X\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u001c"}, d2 = {"MODE_ATOMIC_DEFAULT", "", "MODE_ATOMIC_DEFAULT$annotations", "()V", "MODE_CANCELLABLE", "MODE_CANCELLABLE$annotations", "MODE_DIRECT", "MODE_DIRECT$annotations", "MODE_IGNORE", "MODE_IGNORE$annotations", "MODE_UNDISPATCHED", "MODE_UNDISPATCHED$annotations", "isCancellableMode", "", "(I)Z", "isDispatchedMode", "resumeMode", "", "T", "Lkotlin/coroutines/Continuation;", "value", "mode", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;I)V", "resumeUninterceptedMode", "resumeUninterceptedWithExceptionMode", "exception", "", "resumeWithExceptionMode", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: ResumeMode.kt */
public final class ResumeModeKt {
    public static final int MODE_ATOMIC_DEFAULT = 0;
    public static final int MODE_CANCELLABLE = 1;
    public static final int MODE_DIRECT = 2;
    public static final int MODE_IGNORE = 4;
    public static final int MODE_UNDISPATCHED = 3;

    @PublishedApi
    public static /* synthetic */ void MODE_ATOMIC_DEFAULT$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void MODE_CANCELLABLE$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void MODE_DIRECT$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void MODE_IGNORE$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void MODE_UNDISPATCHED$annotations() {
    }

    public static final boolean isCancellableMode(int i) {
        return i == 1;
    }

    public static final boolean isDispatchedMode(int i) {
        return i != 0 ? i == 1 : true;
    }

    public static final <T> void resumeMode(@NotNull Continuation<? super T> continuation, T t, int i) {
        Intrinsics.checkParameterIsNotNull(continuation, "receiver$0");
        switch (i) {
            case 0:
                i = Result.Companion;
                continuation.resumeWith(Result.constructor-impl(t));
                return;
            case 1:
                DispatchedKt.resumeCancellable(continuation, t);
                return;
            case 2:
                DispatchedKt.resumeDirect(continuation, t);
                return;
            case 3:
                DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) continuation;
                i = dispatchedContinuation.getContext();
                Object updateThreadContext = ThreadContextKt.updateThreadContext(i, dispatchedContinuation.countOrElement);
                try {
                    continuation = dispatchedContinuation.continuation;
                    Companion companion = Result.Companion;
                    continuation.resumeWith(Result.constructor-impl(t));
                    continuation = Unit.INSTANCE;
                    return;
                } finally {
                    ThreadContextKt.restoreThreadContext(i, updateThreadContext);
                }
            case 4:
                return;
            default:
                continuation = new StringBuilder();
                continuation.append("Invalid mode ");
                continuation.append(i);
                throw ((Throwable) new IllegalStateException(continuation.toString().toString()));
        }
    }

    public static final <T> void resumeWithExceptionMode(@NotNull Continuation<? super T> continuation, @NotNull Throwable th, int i) {
        Intrinsics.checkParameterIsNotNull(continuation, "receiver$0");
        Intrinsics.checkParameterIsNotNull(th, "exception");
        switch (i) {
            case 0:
                i = Result.Companion;
                continuation.resumeWith(Result.constructor-impl(ResultKt.createFailure(th)));
                return;
            case 1:
                DispatchedKt.resumeCancellableWithException(continuation, th);
                return;
            case 2:
                DispatchedKt.resumeDirectWithException(continuation, th);
                return;
            case 3:
                DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) continuation;
                i = dispatchedContinuation.getContext();
                Object updateThreadContext = ThreadContextKt.updateThreadContext(i, dispatchedContinuation.countOrElement);
                try {
                    continuation = dispatchedContinuation.continuation;
                    Companion companion = Result.Companion;
                    continuation.resumeWith(Result.constructor-impl(ResultKt.createFailure(th)));
                    continuation = Unit.INSTANCE;
                    return;
                } finally {
                    ThreadContextKt.restoreThreadContext(i, updateThreadContext);
                }
            case 4:
                return;
            default:
                continuation = new StringBuilder();
                continuation.append("Invalid mode ");
                continuation.append(i);
                throw new IllegalStateException(continuation.toString().toString());
        }
    }

    public static final <T> void resumeUninterceptedMode(@NotNull Continuation<? super T> continuation, T t, int i) {
        Intrinsics.checkParameterIsNotNull(continuation, "receiver$0");
        switch (i) {
            case 0:
                continuation = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
                i = Result.Companion;
                continuation.resumeWith(Result.constructor-impl(t));
                return;
            case 1:
                DispatchedKt.resumeCancellable(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), t);
                return;
            case 2:
                i = Result.Companion;
                continuation.resumeWith(Result.constructor-impl(t));
                return;
            case 3:
                i = continuation.getContext();
                Object updateThreadContext = ThreadContextKt.updateThreadContext(i, null);
                try {
                    Companion companion = Result.Companion;
                    continuation.resumeWith(Result.constructor-impl(t));
                    continuation = Unit.INSTANCE;
                    return;
                } finally {
                    ThreadContextKt.restoreThreadContext(i, updateThreadContext);
                }
            case 4:
                return;
            default:
                continuation = new StringBuilder();
                continuation.append("Invalid mode ");
                continuation.append(i);
                throw ((Throwable) new IllegalStateException(continuation.toString().toString()));
        }
    }

    public static final <T> void resumeUninterceptedWithExceptionMode(@NotNull Continuation<? super T> continuation, @NotNull Throwable th, int i) {
        Intrinsics.checkParameterIsNotNull(continuation, "receiver$0");
        Intrinsics.checkParameterIsNotNull(th, "exception");
        switch (i) {
            case 0:
                continuation = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
                i = Result.Companion;
                continuation.resumeWith(Result.constructor-impl(ResultKt.createFailure(th)));
                return;
            case 1:
                DispatchedKt.resumeCancellableWithException(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), th);
                return;
            case 2:
                i = Result.Companion;
                continuation.resumeWith(Result.constructor-impl(ResultKt.createFailure(th)));
                return;
            case 3:
                i = continuation.getContext();
                Object updateThreadContext = ThreadContextKt.updateThreadContext(i, null);
                try {
                    Companion companion = Result.Companion;
                    continuation.resumeWith(Result.constructor-impl(ResultKt.createFailure(th)));
                    continuation = Unit.INSTANCE;
                    return;
                } finally {
                    ThreadContextKt.restoreThreadContext(i, updateThreadContext);
                }
            case 4:
                return;
            default:
                continuation = new StringBuilder();
                continuation.append("Invalid mode ");
                continuation.append(i);
                throw new IllegalStateException(continuation.toString().toString());
        }
    }
}
