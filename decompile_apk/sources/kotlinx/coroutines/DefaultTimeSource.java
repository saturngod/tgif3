package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0004H\u0016J\b\u0010\u000b\u001a\u00020\u0007H\u0016J\b\u0010\f\u001a\u00020\u0007H\u0016J\b\u0010\r\u001a\u00020\u0007H\u0016J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0007H\u0016J\u0018\u0010\u0012\u001a\u00060\u0013j\u0002`\u00142\n\u0010\u0015\u001a\u00060\u0013j\u0002`\u0014H\u0016¨\u0006\u0016"}, d2 = {"Lkotlinx/coroutines/DefaultTimeSource;", "Lkotlinx/coroutines/TimeSource;", "()V", "currentTimeMillis", "", "nanoTime", "parkNanos", "", "blocker", "", "nanos", "registerTimeLoopThread", "trackTask", "unTrackTask", "unpark", "thread", "Ljava/lang/Thread;", "unregisterTimeLoopThread", "wrapTask", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "block", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: TimeSource.kt */
public final class DefaultTimeSource implements TimeSource {
    public static final DefaultTimeSource INSTANCE = new DefaultTimeSource();

    public void registerTimeLoopThread() {
    }

    public void trackTask() {
    }

    public void unTrackTask() {
    }

    public void unregisterTimeLoopThread() {
    }

    @NotNull
    public Runnable wrapTask(@NotNull Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(runnable, "block");
        return runnable;
    }

    private DefaultTimeSource() {
    }

    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public long nanoTime() {
        return System.nanoTime();
    }

    public void parkNanos(@NotNull Object obj, long j) {
        Intrinsics.checkParameterIsNotNull(obj, "blocker");
        LockSupport.parkNanos(obj, j);
    }

    public void unpark(@NotNull Thread thread) {
        Intrinsics.checkParameterIsNotNull(thread, "thread");
        LockSupport.unpark(thread);
    }
}
