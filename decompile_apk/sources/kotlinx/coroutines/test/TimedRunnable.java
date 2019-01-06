package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00060\u0002j\u0002`\u00032\u00020\u0004B%\u0012\n\u0010\u0005\u001a\u00060\u0002j\u0002`\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u0011\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0000H\u0002J\b\u0010\u0005\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R \u0010\n\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0012\u0010\u0005\u001a\u00060\u0002j\u0002`\u0003X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00078\u0000X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lkotlinx/coroutines/test/TimedRunnable;", "", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "run", "count", "", "time", "(Ljava/lang/Runnable;JJ)V", "heap", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "getHeap", "()Lkotlinx/coroutines/internal/ThreadSafeHeap;", "setHeap", "(Lkotlinx/coroutines/internal/ThreadSafeHeap;)V", "index", "", "getIndex", "()I", "setIndex", "(I)V", "compareTo", "other", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: TestCoroutineContext.kt */
final class TimedRunnable implements Comparable<TimedRunnable>, Runnable, ThreadSafeHeapNode {
    private final long count;
    @Nullable
    private ThreadSafeHeap<?> heap;
    private int index;
    private final Runnable run;
    @JvmField
    public final long time;

    public TimedRunnable(@NotNull Runnable runnable, long j, long j2) {
        Intrinsics.checkParameterIsNotNull(runnable, "run");
        this.run = runnable;
        this.count = j;
        this.time = j2;
    }

    public /* synthetic */ TimedRunnable(Runnable runnable, long j, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(runnable, (i & 2) != null ? 0 : j, (i & 4) != null ? 0 : j2);
    }

    @Nullable
    public ThreadSafeHeap<?> getHeap() {
        return this.heap;
    }

    public void setHeap(@Nullable ThreadSafeHeap<?> threadSafeHeap) {
        this.heap = threadSafeHeap;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public void run() {
        this.run.run();
    }

    public int compareTo(@NotNull TimedRunnable timedRunnable) {
        Intrinsics.checkParameterIsNotNull(timedRunnable, "other");
        if (this.time == timedRunnable.time) {
            return (this.count > timedRunnable.count ? 1 : (this.count == timedRunnable.count ? 0 : -1));
        }
        return (this.time > timedRunnable.time ? 1 : (this.time == timedRunnable.time ? 0 : -1));
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TimedRunnable(time=");
        stringBuilder.append(this.time);
        stringBuilder.append(", run=");
        stringBuilder.append(this.run);
        stringBuilder.append(')');
        return stringBuilder.toString();
    }
}
