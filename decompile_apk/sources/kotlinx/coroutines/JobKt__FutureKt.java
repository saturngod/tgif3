package kotlinx.coroutines;

import java.util.concurrent.Future;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004\u001a\u0018\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0007¨\u0006\b"}, d2 = {"cancelFutureOnCancellation", "", "Lkotlinx/coroutines/CancellableContinuation;", "future", "Ljava/util/concurrent/Future;", "cancelFutureOnCompletion", "Lkotlinx/coroutines/DisposableHandle;", "Lkotlinx/coroutines/Job;", "kotlinx-coroutines-core"}, k = 5, mv = {1, 1, 13}, xs = "kotlinx/coroutines/JobKt")
/* compiled from: Future.kt */
final /* synthetic */ class JobKt__FutureKt {
    @NotNull
    @InternalCoroutinesApi
    public static final DisposableHandle cancelFutureOnCompletion(@NotNull Job job, @NotNull Future<?> future) {
        Intrinsics.checkParameterIsNotNull(job, "receiver$0");
        Intrinsics.checkParameterIsNotNull(future, "future");
        return job.invokeOnCompletion(new CancelFutureOnCompletion(job, future));
    }

    public static final void cancelFutureOnCancellation(@NotNull CancellableContinuation<?> cancellableContinuation, @NotNull Future<?> future) {
        Intrinsics.checkParameterIsNotNull(cancellableContinuation, "receiver$0");
        Intrinsics.checkParameterIsNotNull(future, "future");
        cancellableContinuation.invokeOnCancellation(new CancelFutureOnCancel(future));
    }
}
