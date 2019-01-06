package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Delay.DefaultImpls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u001c\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\n\u0010\u000f\u001a\u00060\u0010j\u0002`\u0011H\u0016J\b\u0010\u0012\u001a\u00020\fH\u0002J\u001e\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\f0\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u00018VX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u001a"}, d2 = {"Lkotlinx/coroutines/MissingMainCoroutineDispatcher;", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "Lkotlinx/coroutines/Delay;", "cause", "", "(Ljava/lang/Throwable;)V", "getCause", "()Ljava/lang/Throwable;", "immediate", "getImmediate", "()Lkotlinx/coroutines/MainCoroutineDispatcher;", "dispatch", "", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "missing", "scheduleResumeAfterDelay", "timeMillis", "", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Dispatchers.kt */
final class MissingMainCoroutineDispatcher extends MainCoroutineDispatcher implements Delay {
    @Nullable
    private final Throwable cause;

    public MissingMainCoroutineDispatcher(@Nullable Throwable th) {
        this.cause = th;
    }

    @Nullable
    public Object delay(long j, @NotNull Continuation<? super Unit> continuation) {
        return DefaultImpls.delay(this, j, continuation);
    }

    @Nullable
    public final Throwable getCause() {
        return this.cause;
    }

    @NotNull
    public DisposableHandle invokeOnTimeout(long j, @NotNull Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(runnable, "block");
        return DefaultImpls.invokeOnTimeout(this, j, runnable);
    }

    @NotNull
    public MainCoroutineDispatcher getImmediate() {
        return this;
    }

    public void dispatch(@NotNull CoroutineContext coroutineContext, @NotNull Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(runnable, "block");
        missing();
    }

    public void scheduleResumeAfterDelay(long j, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
        Intrinsics.checkParameterIsNotNull(cancellableContinuation, "continuation");
        missing();
    }

    private final void missing() {
        if (this.cause == null) {
            throw new IllegalStateException("Module with the Main dispatcher is missing. Add dependency providing the Main dispatcher, e.g. 'kotlinx-coroutines-android'");
        }
        throw new IllegalStateException("Module with the Main dispatcher had failed to initialize", this.cause);
    }

    @NotNull
    public String toString() {
        String stringBuilder;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Main[missing");
        if (this.cause != null) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(", cause=");
            stringBuilder3.append(this.cause);
            stringBuilder = stringBuilder3.toString();
        } else {
            stringBuilder = "";
        }
        stringBuilder2.append(stringBuilder);
        stringBuilder2.append(']');
        return stringBuilder2.toString();
    }
}
