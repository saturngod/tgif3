package kotlinx.coroutines.scheduling;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013J\u0016\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\u0005H\u0002J\u0015\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0013H\u0000¢\u0006\u0002\b\u0018J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u001a\u001a\u0004\u0018\u00010\u0005J!\u0010\u001b\u001a\u0004\u0018\u00010\u00052\u0014\b\u0002\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00100\u001dH\bJ\r\u0010\u001e\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\u001fJ\u0010\u0010 \u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\u0002J\u0016\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0013J \u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020%2\u0006\u0010\"\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u0016\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078@X\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lkotlinx/coroutines/scheduling/WorkQueue;", "", "()V", "buffer", "Ljava/util/concurrent/atomic/AtomicReferenceArray;", "Lkotlinx/coroutines/scheduling/Task;", "bufferSize", "", "getBufferSize$kotlinx_coroutines_core", "()I", "consumerIndex", "Lkotlinx/atomicfu/AtomicInt;", "lastScheduledTask", "Lkotlinx/atomicfu/AtomicRef;", "producerIndex", "add", "", "task", "globalQueue", "Lkotlinx/coroutines/scheduling/GlobalQueue;", "addLast", "addToGlobalQueue", "", "offloadAllWork", "offloadAllWork$kotlinx_coroutines_core", "offloadWork", "poll", "pollExternal", "predicate", "Lkotlin/Function1;", "size", "size$kotlinx_coroutines_core", "tryAddLast", "trySteal", "victim", "tryStealLastScheduled", "time", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: WorkQueue.kt */
public final class WorkQueue {
    static final AtomicIntegerFieldUpdater consumerIndex$FU = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "consumerIndex");
    private static final AtomicReferenceFieldUpdater lastScheduledTask$FU = AtomicReferenceFieldUpdater.newUpdater(WorkQueue.class, Object.class, "lastScheduledTask");
    static final AtomicIntegerFieldUpdater producerIndex$FU = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "producerIndex");
    private final AtomicReferenceArray<Task> buffer = new AtomicReferenceArray(128);
    volatile int consumerIndex = 0;
    private volatile Object lastScheduledTask = null;
    volatile int producerIndex = 0;

    public final int getBufferSize$kotlinx_coroutines_core() {
        return this.producerIndex - this.consumerIndex;
    }

    @Nullable
    public final Task poll() {
        Task task = (Task) lastScheduledTask$FU.getAndSet(this, null);
        if (task != null) {
            return task;
        }
        while (true) {
            int i = this.consumerIndex;
            if (i - this.producerIndex == 0) {
                return null;
            }
            int i2 = i & WorkQueueKt.MASK;
            if (((Task) this.buffer.get(i2)) != null && consumerIndex$FU.compareAndSet(this, i, i + 1)) {
                return (Task) this.buffer.getAndSet(i2, null);
            }
        }
    }

    public final boolean add(@NotNull Task task, @NotNull GlobalQueue globalQueue) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        Intrinsics.checkParameterIsNotNull(globalQueue, "globalQueue");
        task = (Task) lastScheduledTask$FU.getAndSet(this, task);
        return task != null ? addLast(task, globalQueue) : true;
    }

    public final boolean addLast(@NotNull Task task, @NotNull GlobalQueue globalQueue) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        Intrinsics.checkParameterIsNotNull(globalQueue, "globalQueue");
        boolean z = true;
        while (!tryAddLast(task)) {
            offloadWork(globalQueue);
            z = false;
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean trySteal(@org.jetbrains.annotations.NotNull kotlinx.coroutines.scheduling.WorkQueue r18, @org.jetbrains.annotations.NotNull kotlinx.coroutines.scheduling.GlobalQueue r19) {
        /*
        r17 = this;
        r0 = r17;
        r1 = r18;
        r2 = r19;
        r3 = "victim";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r1, r3);
        r3 = "globalQueue";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r3);
        r3 = kotlinx.coroutines.scheduling.TasksKt.schedulerTimeSource;
        r3 = r3.nanoTime();
        r5 = r18.getBufferSize$kotlinx_coroutines_core();
        if (r5 != 0) goto L_0x0021;
    L_0x001c:
        r1 = r0.tryStealLastScheduled(r3, r1, r2);
        return r1;
    L_0x0021:
        r5 = r5 / 2;
        r6 = 1;
        r5 = kotlin.ranges.RangesKt___RangesKt.coerceAtLeast(r5, r6);
        r7 = 0;
        r8 = 0;
        r9 = 0;
    L_0x002b:
        if (r8 >= r5) goto L_0x007d;
    L_0x002d:
        r10 = r1.consumerIndex;
        r11 = r1.producerIndex;
        r11 = r10 - r11;
        r12 = 0;
        if (r11 != 0) goto L_0x0037;
    L_0x0036:
        goto L_0x0073;
    L_0x0037:
        r11 = r10 & 127;
        r13 = r18.buffer;
        r13 = r13.get(r11);
        r13 = (kotlinx.coroutines.scheduling.Task) r13;
        if (r13 == 0) goto L_0x002d;
    L_0x0045:
        r13 = r13.submissionTime;
        r13 = r3 - r13;
        r15 = kotlinx.coroutines.scheduling.TasksKt.WORK_STEALING_TIME_RESOLUTION_NS;
        r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1));
        if (r13 >= 0) goto L_0x005a;
    L_0x004f:
        r13 = r18.getBufferSize$kotlinx_coroutines_core();
        r14 = kotlinx.coroutines.scheduling.TasksKt.QUEUE_SIZE_OFFLOAD_THRESHOLD;
        if (r13 <= r14) goto L_0x0058;
    L_0x0057:
        goto L_0x005a;
    L_0x0058:
        r13 = 0;
        goto L_0x005b;
    L_0x005a:
        r13 = 1;
    L_0x005b:
        if (r13 != 0) goto L_0x005e;
    L_0x005d:
        goto L_0x0073;
    L_0x005e:
        r13 = consumerIndex$FU;
        r14 = r10 + 1;
        r10 = r13.compareAndSet(r1, r10, r14);
        if (r10 == 0) goto L_0x002d;
    L_0x0068:
        r10 = r18.buffer;
        r10 = r10.getAndSet(r11, r12);
        r12 = r10;
        r12 = (kotlinx.coroutines.scheduling.Task) r12;
    L_0x0073:
        if (r12 == 0) goto L_0x007c;
    L_0x0075:
        r0.add(r12, r2);
        r8 = r8 + 1;
        r9 = 1;
        goto L_0x002b;
    L_0x007c:
        return r9;
    L_0x007d:
        return r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.scheduling.WorkQueue.trySteal(kotlinx.coroutines.scheduling.WorkQueue, kotlinx.coroutines.scheduling.GlobalQueue):boolean");
    }

    private final boolean tryStealLastScheduled(long j, WorkQueue workQueue, GlobalQueue globalQueue) {
        Task task = (Task) workQueue.lastScheduledTask;
        if (task == null || j - task.submissionTime < TasksKt.WORK_STEALING_TIME_RESOLUTION_NS || lastScheduledTask$FU.compareAndSet(workQueue, task, null) == null) {
            return false;
        }
        add(task, globalQueue);
        return 1;
    }

    public final int size$kotlinx_coroutines_core() {
        return this.lastScheduledTask != null ? getBufferSize$kotlinx_coroutines_core() + 1 : getBufferSize$kotlinx_coroutines_core();
    }

    private final void offloadWork(GlobalQueue globalQueue) {
        int coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(getBufferSize$kotlinx_coroutines_core() / 2, 1);
        int i = 0;
        while (i < coerceAtLeast) {
            Task task;
            int i2;
            while (true) {
                int i3 = this.consumerIndex;
                task = null;
                if (i3 - this.producerIndex != 0) {
                    i2 = i3 & WorkQueueKt.MASK;
                    if (((Task) this.buffer.get(i2)) != null && consumerIndex$FU.compareAndSet(this, i3, i3 + 1)) {
                        break;
                    }
                } else {
                    break;
                }
                if (task != null) {
                    addToGlobalQueue(globalQueue, task);
                    i++;
                } else {
                    return;
                }
            }
            task = (Task) this.buffer.getAndSet(i2, null);
            if (task != null) {
                addToGlobalQueue(globalQueue, task);
                i++;
            } else {
                return;
            }
        }
    }

    private final void addToGlobalQueue(GlobalQueue globalQueue, Task task) {
        if (globalQueue.add(task) == null) {
            throw ((Throwable) new IllegalStateException("GlobalQueue could not be closed yet".toString()));
        }
    }

    public final void offloadAllWork$kotlinx_coroutines_core(@NotNull GlobalQueue globalQueue) {
        Intrinsics.checkParameterIsNotNull(globalQueue, "globalQueue");
        Task task = (Task) lastScheduledTask$FU.getAndSet(this, null);
        if (task != null) {
            addToGlobalQueue(globalQueue, task);
        }
        while (true) {
            int i = this.consumerIndex;
            if (i - this.producerIndex == 0) {
                task = null;
            } else {
                int i2 = i & WorkQueueKt.MASK;
                if (((Task) this.buffer.get(i2)) != null && consumerIndex$FU.compareAndSet(this, i, i + 1)) {
                    task = (Task) this.buffer.getAndSet(i2, null);
                }
            }
            if (task != null) {
                addToGlobalQueue(globalQueue, task);
            } else {
                return;
            }
        }
    }

    static /* synthetic */ Task pollExternal$default(WorkQueue workQueue, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = WorkQueue$pollExternal$1.INSTANCE;
        }
        while (true) {
            i = workQueue.consumerIndex;
            if (i - workQueue.producerIndex == null) {
                return null;
            }
            obj = i & WorkQueueKt.MASK;
            Task task = (Task) workQueue.buffer.get(obj);
            if (task != null) {
                if (!((Boolean) function1.invoke(task)).booleanValue()) {
                    return null;
                }
                if (consumerIndex$FU.compareAndSet(workQueue, i, i + 1) != 0) {
                    return (Task) workQueue.buffer.getAndSet(obj, null);
                }
            }
        }
    }

    private final Task pollExternal(Function1<? super Task, Boolean> function1) {
        while (true) {
            int i = this.consumerIndex;
            if (i - this.producerIndex == 0) {
                return null;
            }
            int i2 = i & WorkQueueKt.MASK;
            Task task = (Task) this.buffer.get(i2);
            if (task != null) {
                if (!((Boolean) function1.invoke(task)).booleanValue()) {
                    return null;
                }
                if (consumerIndex$FU.compareAndSet(this, i, i + 1)) {
                    return (Task) this.buffer.getAndSet(i2, null);
                }
            }
        }
    }

    private final boolean tryAddLast(Task task) {
        if (getBufferSize$kotlinx_coroutines_core() == WorkQueueKt.MASK) {
            return false;
        }
        int i = this.producerIndex & WorkQueueKt.MASK;
        if (this.buffer.get(i) != null) {
            return false;
        }
        this.buffer.lazySet(i, task);
        producerIndex$FU.incrementAndGet(this);
        return true;
    }
}
