package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\n\u0010\n\u001a\u00060\u000bj\u0002`\fH\u0016J\u001e\u0010\r\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00052\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u000fH&\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Lkotlinx/coroutines/Delay;", "", "delay", "", "time", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "scheduleResumeAfterDelay", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
@InternalCoroutinesApi
/* compiled from: Delay.kt */
public interface Delay {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* compiled from: Delay.kt */
    public static final class DefaultImpls {
        @Nullable
        public static Object delay(Delay delay, long j, @NotNull Continuation<? super Unit> continuation) {
            if (j <= 0) {
                return Unit.INSTANCE;
            }
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
            cancellableContinuationImpl.initCancellability();
            delay.scheduleResumeAfterDelay(j, cancellableContinuationImpl);
            delay = cancellableContinuationImpl.getResult();
            if (delay == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return delay;
        }

        @NotNull
        public static DisposableHandle invokeOnTimeout(Delay delay, long j, @NotNull Runnable runnable) {
            Intrinsics.checkParameterIsNotNull(runnable, "block");
            return DefaultExecutorKt.getDefaultDelay().invokeOnTimeout(j, runnable);
        }
    }

    @Nullable
    Object delay(long j, @NotNull Continuation<? super Unit> continuation);

    @NotNull
    DisposableHandle invokeOnTimeout(long j, @NotNull Runnable runnable);

    void scheduleResumeAfterDelay(long j, @NotNull CancellableContinuation<? super Unit> cancellableContinuation);
}
