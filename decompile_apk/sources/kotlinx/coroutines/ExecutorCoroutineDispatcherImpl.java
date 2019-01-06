package kotlinx.coroutines;

import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/ExecutorCoroutineDispatcherImpl;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcherBase;", "executor", "Ljava/util/concurrent/Executor;", "(Ljava/util/concurrent/Executor;)V", "getExecutor", "()Ljava/util/concurrent/Executor;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Executors.kt */
final class ExecutorCoroutineDispatcherImpl extends ExecutorCoroutineDispatcherBase {
    @NotNull
    private final Executor executor;

    public ExecutorCoroutineDispatcherImpl(@NotNull Executor executor) {
        Intrinsics.checkParameterIsNotNull(executor, "executor");
        this.executor = executor;
        initFutureCancellation$kotlinx_coroutines_core();
    }

    @NotNull
    public Executor getExecutor() {
        return this.executor;
    }
}
