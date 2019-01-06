package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlinx.coroutines.Delay.DefaultImpls;
import kotlinx.coroutines.internal.LockFreeMPSCQueueCore;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b \u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u0003345B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0015\u001a\u00020\u0016H\u0004J\u0010\u0010\u0017\u001a\n\u0018\u00010\u0018j\u0004\u0018\u0001`\u0019H\u0002J\u001c\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001c2\n\u0010\u001d\u001a\u00060\u0018j\u0002`\u0019H\u0016J\u0014\u0010\u001e\u001a\u00020\f2\n\u0010\u001f\u001a\u00060\u0018j\u0002`\u0019H\u0002J\u0019\u0010 \u001a\u00020\u00162\n\u0010\u001f\u001a\u00060\u0018j\u0002`\u0019H\u0000¢\u0006\u0002\b!J\b\u0010\"\u001a\u00020\fH$J\b\u0010#\u001a\u00020\u0012H\u0016J\u0015\u0010$\u001a\u00020\u00162\u0006\u0010%\u001a\u00020\bH\u0000¢\u0006\u0002\b&J\b\u0010'\u001a\u00020\u0016H\u0004J\b\u0010(\u001a\u00020\u0016H\u0004J\u0015\u0010)\u001a\u00020\u00162\u0006\u0010%\u001a\u00020\bH\u0000¢\u0006\u0002\b*J\u0010\u0010+\u001a\u00020,2\u0006\u0010%\u001a\u00020\bH\u0002J\u001e\u0010-\u001a\u00020\u00162\u0006\u0010.\u001a\u00020\u00122\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001600H\u0016J\u0010\u00101\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\bH\u0002J\b\u00102\u001a\u00020\u0016H$R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0006X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00020\fX¤\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\rR\u0014\u0010\u000e\u001a\u00020\f8BX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\rR\u0014\u0010\u000f\u001a\u00020\f8DX\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0014\u0010\u0010\u001a\u00020\f8BX\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\rR\u0014\u0010\u0011\u001a\u00020\u00128BX\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u00066"}, d2 = {"Lkotlinx/coroutines/EventLoopBase;", "Lkotlinx/coroutines/CoroutineDispatcher;", "Lkotlinx/coroutines/Delay;", "Lkotlinx/coroutines/EventLoop;", "()V", "_delayed", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "Lkotlinx/coroutines/EventLoopBase$DelayedTask;", "_queue", "", "isCompleted", "", "()Z", "isDelayedEmpty", "isEmpty", "isQueueEmpty", "nextTime", "", "getNextTime", "()J", "closeQueue", "", "dequeue", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "dispatch", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "enqueueImpl", "task", "execute", "execute$kotlinx_coroutines_core", "isCorrectThread", "processNextEvent", "removeDelayedImpl", "delayedTask", "removeDelayedImpl$kotlinx_coroutines_core", "rescheduleAllDelayed", "resetAll", "schedule", "schedule$kotlinx_coroutines_core", "scheduleImpl", "", "scheduleResumeAfterDelay", "timeMillis", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "shouldUnpark", "unpark", "DelayedResumeTask", "DelayedRunnableTask", "DelayedTask", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: EventLoop.kt */
public abstract class EventLoopBase extends CoroutineDispatcher implements Delay, EventLoop {
    private static final AtomicReferenceFieldUpdater _delayed$FU = AtomicReferenceFieldUpdater.newUpdater(EventLoopBase.class, Object.class, "_delayed");
    private static final AtomicReferenceFieldUpdater _queue$FU = AtomicReferenceFieldUpdater.newUpdater(EventLoopBase.class, Object.class, "_queue");
    private volatile Object _delayed = null;
    private volatile Object _queue = null;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b \u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00000\u00032\u00020\u00042\u00020\u0005B\r\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0011\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u0000H\u0002J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u001cJ\u001c\u0010\u001e\u001a\u00020\u00132\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00000\f2\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0007J\b\u0010%\u001a\u00020&H\u0016R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R0\u0010\r\u001a\b\u0012\u0002\b\u0003\u0018\u00010\f2\f\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\f8V@VX\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u00020\u00078\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lkotlinx/coroutines/EventLoopBase$DelayedTask;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "", "Lkotlinx/coroutines/DisposableHandle;", "Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "timeMillis", "", "(J)V", "_heap", "", "value", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "heap", "getHeap", "()Lkotlinx/coroutines/internal/ThreadSafeHeap;", "setHeap", "(Lkotlinx/coroutines/internal/ThreadSafeHeap;)V", "index", "", "getIndex", "()I", "setIndex", "(I)V", "nanoTime", "compareTo", "other", "dispose", "", "rescheduleOnShutdown", "schedule", "delayed", "eventLoop", "Lkotlinx/coroutines/EventLoopBase;", "timeToExecute", "", "now", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: EventLoop.kt */
    public static abstract class DelayedTask implements Runnable, Comparable<DelayedTask>, DisposableHandle, ThreadSafeHeapNode {
        private Object _heap;
        private int index = -1;
        @JvmField
        public final long nanoTime;

        public DelayedTask(long j) {
            this.nanoTime = TimeSourceKt.getTimeSource().nanoTime() + EventLoopKt.delayToNanos(j);
        }

        @Nullable
        public ThreadSafeHeap<?> getHeap() {
            Object obj = this._heap;
            if (!(obj instanceof ThreadSafeHeap)) {
                obj = null;
            }
            return (ThreadSafeHeap) obj;
        }

        public void setHeap(@Nullable ThreadSafeHeap<?> threadSafeHeap) {
            if ((this._heap != EventLoopKt.DISPOSED_TASK ? 1 : null) != null) {
                this._heap = threadSafeHeap;
                return;
            }
            throw ((Throwable) new IllegalArgumentException("Failed requirement.".toString()));
        }

        public int getIndex() {
            return this.index;
        }

        public void setIndex(int i) {
            this.index = i;
        }

        public int compareTo(@NotNull DelayedTask delayedTask) {
            Intrinsics.checkParameterIsNotNull(delayedTask, "other");
            delayedTask = ((this.nanoTime - delayedTask.nanoTime) > 0 ? 1 : ((this.nanoTime - delayedTask.nanoTime) == 0 ? 0 : -1));
            if (delayedTask > null) {
                return 1;
            }
            return delayedTask < null ? -1 : null;
        }

        public final boolean timeToExecute(long j) {
            return j - this.nanoTime >= 0 ? 1 : 0;
        }

        public final synchronized int schedule(@NotNull ThreadSafeHeap<DelayedTask> threadSafeHeap, @NotNull EventLoopBase eventLoopBase) {
            Intrinsics.checkParameterIsNotNull(threadSafeHeap, "delayed");
            Intrinsics.checkParameterIsNotNull(eventLoopBase, "eventLoop");
            if (this._heap == EventLoopKt.DISPOSED_TASK) {
                return 2;
            }
            ThreadSafeHeapNode threadSafeHeapNode = this;
            synchronized (threadSafeHeap) {
                if ((eventLoopBase.isCompleted() ^ 1) != null) {
                    threadSafeHeap.addImpl(threadSafeHeapNode);
                    eventLoopBase = true;
                } else {
                    eventLoopBase = null;
                }
            }
            return eventLoopBase ^ 1;
        }

        public final void rescheduleOnShutdown() {
            DefaultExecutor.INSTANCE.schedule$kotlinx_coroutines_core(this);
        }

        public final synchronized void dispose() {
            Object obj = this._heap;
            if (obj != EventLoopKt.DISPOSED_TASK) {
                if (!(obj instanceof ThreadSafeHeap)) {
                    obj = null;
                }
                ThreadSafeHeap threadSafeHeap = (ThreadSafeHeap) obj;
                if (threadSafeHeap != null) {
                    threadSafeHeap.remove(this);
                }
                this._heap = EventLoopKt.DISPOSED_TASK;
            }
        }

        @NotNull
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Delayed[nanos=");
            stringBuilder.append(this.nanoTime);
            stringBuilder.append(']');
            return stringBuilder.toString();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0004\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\u0006H\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/EventLoopBase$DelayedResumeTask;", "Lkotlinx/coroutines/EventLoopBase$DelayedTask;", "timeMillis", "", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "(Lkotlinx/coroutines/EventLoopBase;JLkotlinx/coroutines/CancellableContinuation;)V", "run", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: EventLoop.kt */
    private final class DelayedResumeTask extends DelayedTask {
        private final CancellableContinuation<Unit> cont;
        final /* synthetic */ EventLoopBase this$0;

        public DelayedResumeTask(EventLoopBase eventLoopBase, @NotNull long j, CancellableContinuation<? super Unit> cancellableContinuation) {
            Intrinsics.checkParameterIsNotNull(cancellableContinuation, "cont");
            this.this$0 = eventLoopBase;
            super(j);
            this.cont = cancellableContinuation;
            CancellableContinuationKt.disposeOnCancellation(this.cont, this);
        }

        public void run() {
            this.cont.resumeUndispatched(this.this$0, Unit.INSTANCE);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016R\u0012\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/EventLoopBase$DelayedRunnableTask;", "Lkotlinx/coroutines/EventLoopBase$DelayedTask;", "time", "", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "(JLjava/lang/Runnable;)V", "run", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: EventLoop.kt */
    public static final class DelayedRunnableTask extends DelayedTask {
        private final Runnable block;

        public DelayedRunnableTask(long j, @NotNull Runnable runnable) {
            Intrinsics.checkParameterIsNotNull(runnable, "block");
            super(j);
            this.block = runnable;
        }

        public void run() {
            this.block.run();
        }

        @NotNull
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(super.toString());
            stringBuilder.append(this.block.toString());
            return stringBuilder.toString();
        }
    }

    protected abstract boolean isCompleted();

    protected abstract boolean isCorrectThread();

    protected abstract void unpark();

    @Nullable
    public Object delay(long j, @NotNull Continuation<? super Unit> continuation) {
        return DefaultImpls.delay(this, j, continuation);
    }

    @NotNull
    public DisposableHandle invokeOnTimeout(long j, @NotNull Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(runnable, "block");
        return DefaultImpls.invokeOnTimeout(this, j, runnable);
    }

    protected final boolean isEmpty() {
        return isQueueEmpty() && isDelayedEmpty();
    }

    private final boolean isQueueEmpty() {
        Symbol symbol = this._queue;
        if (symbol == null) {
            return true;
        }
        if (symbol instanceof LockFreeMPSCQueueCore) {
            return ((LockFreeMPSCQueueCore) symbol).isEmpty();
        }
        if (symbol == EventLoopKt.CLOSED_EMPTY) {
            return true;
        }
        return false;
    }

    private final boolean isDelayedEmpty() {
        ThreadSafeHeap threadSafeHeap = (ThreadSafeHeap) this._delayed;
        if (threadSafeHeap != null) {
            if (!threadSafeHeap.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private final long getNextTime() {
        Symbol symbol = this._queue;
        if (symbol != null) {
            if (!(symbol instanceof LockFreeMPSCQueueCore)) {
                return symbol == EventLoopKt.CLOSED_EMPTY ? LongCompanionObject.MAX_VALUE : 0;
            } else {
                if (!((LockFreeMPSCQueueCore) symbol).isEmpty()) {
                    return 0;
                }
            }
        }
        ThreadSafeHeap threadSafeHeap = (ThreadSafeHeap) this._delayed;
        if (threadSafeHeap == null) {
            return LongCompanionObject.MAX_VALUE;
        }
        DelayedTask delayedTask = (DelayedTask) threadSafeHeap.peek();
        if (delayedTask != null) {
            return RangesKt___RangesKt.coerceAtLeast(delayedTask.nanoTime - TimeSourceKt.getTimeSource().nanoTime(), 0);
        }
        return LongCompanionObject.MAX_VALUE;
    }

    public void dispatch(@NotNull CoroutineContext coroutineContext, @NotNull Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(runnable, "block");
        execute$kotlinx_coroutines_core(runnable);
    }

    public void scheduleResumeAfterDelay(long j, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
        Intrinsics.checkParameterIsNotNull(cancellableContinuation, "continuation");
        schedule$kotlinx_coroutines_core(new DelayedResumeTask(this, j, cancellableContinuation));
    }

    public long processNextEvent() {
        if (!isCorrectThread()) {
            return LongCompanionObject.MAX_VALUE;
        }
        ThreadSafeHeap threadSafeHeap = (ThreadSafeHeap) this._delayed;
        if (threadSafeHeap != null && !threadSafeHeap.isEmpty()) {
            long nanoTime = TimeSourceKt.getTimeSource().nanoTime();
            while (true) {
                ThreadSafeHeapNode threadSafeHeapNode;
                synchronized (threadSafeHeap) {
                    ThreadSafeHeapNode firstImpl = threadSafeHeap.firstImpl();
                    threadSafeHeapNode = null;
                    if (firstImpl != null) {
                        DelayedTask delayedTask = (DelayedTask) firstImpl;
                        if (delayedTask.timeToExecute(nanoTime) ? enqueueImpl(delayedTask) : false) {
                            threadSafeHeapNode = threadSafeHeap.removeAtImpl(0);
                        }
                    }
                }
                if (((DelayedTask) threadSafeHeapNode) == null) {
                    break;
                }
            }
        }
        Runnable dequeue = dequeue();
        if (dequeue != null) {
            dequeue.run();
        }
        return getNextTime();
    }

    public final void execute$kotlinx_coroutines_core(@NotNull Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(runnable, "task");
        if (enqueueImpl(runnable)) {
            unpark();
        } else {
            DefaultExecutor.INSTANCE.execute$kotlinx_coroutines_core(runnable);
        }
    }

    protected final void closeQueue() {
        boolean isCompleted = isCompleted();
        if (_Assertions.ENABLED) {
            if (!isCompleted) {
                throw new AssertionError("Assertion failed");
            }
        }
        while (true) {
            Symbol symbol = this._queue;
            if (symbol == null) {
                if (_queue$FU.compareAndSet(this, null, EventLoopKt.CLOSED_EMPTY)) {
                    return;
                }
            } else if (symbol instanceof LockFreeMPSCQueueCore) {
                ((LockFreeMPSCQueueCore) symbol).close();
                return;
            } else if (symbol != EventLoopKt.CLOSED_EMPTY) {
                LockFreeMPSCQueueCore lockFreeMPSCQueueCore = new LockFreeMPSCQueueCore(8);
                if (symbol != null) {
                    lockFreeMPSCQueueCore.addLast((Runnable) symbol);
                    if (_queue$FU.compareAndSet(this, symbol, lockFreeMPSCQueueCore)) {
                        return;
                    }
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.Runnable /* = java.lang.Runnable */");
                }
            } else {
                return;
            }
        }
    }

    public final void schedule$kotlinx_coroutines_core(@NotNull DelayedTask delayedTask) {
        Intrinsics.checkParameterIsNotNull(delayedTask, "delayedTask");
        switch (scheduleImpl(delayedTask)) {
            case 0:
                if (shouldUnpark(delayedTask) != null) {
                    unpark();
                    return;
                }
                return;
            case 1:
                DefaultExecutor.INSTANCE.schedule$kotlinx_coroutines_core(delayedTask);
                return;
            case 2:
                return;
            default:
                throw ((Throwable) new IllegalStateException("unexpected result".toString()));
        }
    }

    private final boolean shouldUnpark(DelayedTask delayedTask) {
        ThreadSafeHeap threadSafeHeap = (ThreadSafeHeap) this._delayed;
        return (threadSafeHeap != null ? (DelayedTask) threadSafeHeap.peek() : null) == delayedTask ? true : null;
    }

    private final int scheduleImpl(DelayedTask delayedTask) {
        if (isCompleted()) {
            return 1;
        }
        ThreadSafeHeap threadSafeHeap = (ThreadSafeHeap) this._delayed;
        if (threadSafeHeap == null) {
            EventLoopBase eventLoopBase = this;
            _delayed$FU.compareAndSet(eventLoopBase, null, new ThreadSafeHeap());
            Object obj = eventLoopBase._delayed;
            if (obj == null) {
                Intrinsics.throwNpe();
            }
            threadSafeHeap = (ThreadSafeHeap) obj;
        }
        return delayedTask.schedule(threadSafeHeap, this);
    }

    public final void removeDelayedImpl$kotlinx_coroutines_core(@NotNull DelayedTask delayedTask) {
        Intrinsics.checkParameterIsNotNull(delayedTask, "delayedTask");
        ThreadSafeHeap threadSafeHeap = (ThreadSafeHeap) this._delayed;
        if (threadSafeHeap != null) {
            threadSafeHeap.remove(delayedTask);
        }
    }

    protected final void resetAll() {
        this._queue = null;
        this._delayed = null;
    }

    protected final void rescheduleAllDelayed() {
        while (true) {
            ThreadSafeHeap threadSafeHeap = (ThreadSafeHeap) this._delayed;
            if (threadSafeHeap != null) {
                DelayedTask delayedTask = (DelayedTask) threadSafeHeap.removeFirstOrNull();
                if (delayedTask != null) {
                    delayedTask.rescheduleOnShutdown();
                } else {
                    return;
                }
            }
            return;
        }
    }

    private final boolean enqueueImpl(Runnable runnable) {
        while (true) {
            Symbol symbol = this._queue;
            if (isCompleted()) {
                return false;
            }
            if (symbol == null) {
                if (_queue$FU.compareAndSet(this, null, runnable)) {
                    return true;
                }
            } else if (symbol instanceof LockFreeMPSCQueueCore) {
                if (symbol != null) {
                    LockFreeMPSCQueueCore lockFreeMPSCQueueCore = (LockFreeMPSCQueueCore) symbol;
                    switch (lockFreeMPSCQueueCore.addLast(runnable)) {
                        case 0:
                            return true;
                        case 1:
                            _queue$FU.compareAndSet(this, symbol, lockFreeMPSCQueueCore.next());
                            break;
                        case 2:
                            return false;
                        default:
                            break;
                    }
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.Queue<kotlinx.coroutines.Runnable /* = java.lang.Runnable */> /* = kotlinx.coroutines.internal.LockFreeMPSCQueueCore<kotlinx.coroutines.Runnable /* = java.lang.Runnable */> */");
            } else if (symbol == EventLoopKt.CLOSED_EMPTY) {
                return false;
            } else {
                LockFreeMPSCQueueCore lockFreeMPSCQueueCore2 = new LockFreeMPSCQueueCore(8);
                if (symbol != null) {
                    lockFreeMPSCQueueCore2.addLast((Runnable) symbol);
                    lockFreeMPSCQueueCore2.addLast(runnable);
                    if (_queue$FU.compareAndSet(this, symbol, lockFreeMPSCQueueCore2)) {
                        return true;
                    }
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.Runnable /* = java.lang.Runnable */");
                }
            }
        }
    }

    private final Runnable dequeue() {
        while (true) {
            Symbol symbol = this._queue;
            if (symbol == null) {
                return null;
            }
            if (symbol instanceof LockFreeMPSCQueueCore) {
                if (symbol != null) {
                    LockFreeMPSCQueueCore lockFreeMPSCQueueCore = (LockFreeMPSCQueueCore) symbol;
                    Symbol removeFirstOrNull = lockFreeMPSCQueueCore.removeFirstOrNull();
                    if (removeFirstOrNull != LockFreeMPSCQueueCore.REMOVE_FROZEN) {
                        return (Runnable) removeFirstOrNull;
                    }
                    _queue$FU.compareAndSet(this, symbol, lockFreeMPSCQueueCore.next());
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.Queue<kotlinx.coroutines.Runnable /* = java.lang.Runnable */> /* = kotlinx.coroutines.internal.LockFreeMPSCQueueCore<kotlinx.coroutines.Runnable /* = java.lang.Runnable */> */");
                }
            } else if (symbol == EventLoopKt.CLOSED_EMPTY) {
                return null;
            } else {
                if (_queue$FU.compareAndSet(this, symbol, null)) {
                    break;
                }
            }
        }
        if (symbol != null) {
            return (Runnable) symbol;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.Runnable /* = java.lang.Runnable */");
    }
}
