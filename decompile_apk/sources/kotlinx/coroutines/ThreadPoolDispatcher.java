package kotlinx.coroutines;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0005H\u0016R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlinx/coroutines/ThreadPoolDispatcher;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcherBase;", "nThreads", "", "name", "", "(ILjava/lang/String;)V", "executor", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "threadNo", "Ljava/util/concurrent/atomic/AtomicInteger;", "close", "", "toString", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: ThreadPoolDispatcher.kt */
public final class ThreadPoolDispatcher extends ExecutorCoroutineDispatcherBase {
    @NotNull
    private final Executor executor;
    private final int nThreads;
    private final String name;
    private final AtomicInteger threadNo = new AtomicInteger();

    public ThreadPoolDispatcher(int i, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        this.nThreads = i;
        this.name = str;
        i = Executors.newScheduledThreadPool(this.nThreads, (ThreadFactory) new ThreadPoolDispatcher$executor$1(this));
        Intrinsics.checkExpressionValueIsNotNull(i, "Executors.newScheduledTh….incrementAndGet())\n    }");
        this.executor = (Executor) i;
        initFutureCancellation$kotlinx_coroutines_core();
    }

    @NotNull
    public Executor getExecutor() {
        return this.executor;
    }

    public void close() {
        Executor executor = getExecutor();
        if (executor != null) {
            ((ExecutorService) executor).shutdown();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.util.concurrent.ExecutorService");
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ThreadPoolDispatcher[");
        stringBuilder.append(this.nThreads);
        stringBuilder.append(", ");
        stringBuilder.append(this.name);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
