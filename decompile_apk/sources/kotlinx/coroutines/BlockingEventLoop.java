package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lkotlinx/coroutines/BlockingEventLoop;", "Lkotlinx/coroutines/ThreadEventLoop;", "thread", "Ljava/lang/Thread;", "(Ljava/lang/Thread;)V", "isCompleted", "", "()Z", "setCompleted", "(Z)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: EventLoop.kt */
public final class BlockingEventLoop extends ThreadEventLoop {
    private volatile boolean isCompleted;

    public BlockingEventLoop(@NotNull Thread thread) {
        Intrinsics.checkParameterIsNotNull(thread, "thread");
        super(thread);
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public void setCompleted(boolean z) {
        this.isCompleted = z;
    }
}
