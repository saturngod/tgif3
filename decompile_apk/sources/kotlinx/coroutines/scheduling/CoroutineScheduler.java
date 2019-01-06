package kotlinx.coroutines.scheduling;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.TimeSourceKt;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\b\u0000\u0018\u0000 E2\u00020\u00012\u00020\u0002:\u0003EFGB)\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0011\u0010\r\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u0007H\bJ\b\u0010#\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020\u0004H\u0002J!\u0010&\u001a\u00020'2\n\u0010(\u001a\u00060)j\u0002`*2\u0006\u0010+\u001a\u00020,H\u0000¢\u0006\u0002\b-J\u0011\u0010\u0014\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u0007H\bJ\t\u0010.\u001a\u00020$H\bJ\t\u0010/\u001a\u00020\u0004H\bJ&\u00100\u001a\u00020$2\n\u0010(\u001a\u00060)j\u0002`*2\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u00101\u001a\u00020\u0019J\u0014\u00102\u001a\u00020$2\n\u00103\u001a\u00060)j\u0002`*H\u0016J\t\u00104\u001a\u00020$H\bJ\t\u00105\u001a\u00020\u0004H\bJ\u0014\u00106\u001a\u00020\u00042\n\u00107\u001a\u00060 R\u00020\u0000H\u0002J\u000e\u00108\u001a\b\u0018\u00010 R\u00020\u0000H\u0002J\u0014\u00109\u001a\u00020$2\n\u00107\u001a\u00060 R\u00020\u0000H\u0002J$\u0010:\u001a\u00020$2\n\u00107\u001a\u00060 R\u00020\u00002\u0006\u0010;\u001a\u00020\u00042\u0006\u0010<\u001a\u00020\u0004H\u0002J\b\u0010=\u001a\u00020$H\u0002J\u0010\u0010>\u001a\u00020$2\u0006\u0010?\u001a\u00020'H\u0002J\u000e\u0010@\u001a\u00020$2\u0006\u0010A\u001a\u00020\u0007J\u0018\u0010B\u001a\u00020\u00042\u0006\u0010?\u001a\u00020'2\u0006\u00101\u001a\u00020\u0019H\u0002J\b\u0010C\u001a\u00020\tH\u0016J\b\u0010D\u001a\u00020\u0019H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\r\u001a\u00020\u00048Â\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\u0014\u001a\u00020\u00048Â\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000fR\u000e\u0010\u0016\u001a\u00020\u0017X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\u00020\u00198BX\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u001aR\u000e\u0010\u0005\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0018\u00010 R\u00020\u00000\u001fX\u0004¢\u0006\u0004\n\u0002\u0010!¨\u0006H"}, d2 = {"Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "Ljava/util/concurrent/Executor;", "Ljava/io/Closeable;", "corePoolSize", "", "maxPoolSize", "idleWorkerKeepAliveNs", "", "schedulerName", "", "(IIJLjava/lang/String;)V", "_isTerminated", "Lkotlinx/atomicfu/AtomicInt;", "blockingWorkers", "getBlockingWorkers", "()I", "controlState", "Lkotlinx/atomicfu/AtomicLong;", "cpuPermits", "Ljava/util/concurrent/Semaphore;", "createdWorkers", "getCreatedWorkers", "globalQueue", "Lkotlinx/coroutines/scheduling/GlobalQueue;", "isTerminated", "", "()Z", "parkedWorkersStack", "random", "Ljava/util/Random;", "workers", "", "Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;", "[Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;", "state", "close", "", "createNewWorker", "createTask", "Lkotlinx/coroutines/scheduling/Task;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "taskContext", "Lkotlinx/coroutines/scheduling/TaskContext;", "createTask$kotlinx_coroutines_core", "decrementBlockingWorkers", "decrementCreatedWorkers", "dispatch", "fair", "execute", "command", "incrementBlockingWorkers", "incrementCreatedWorkers", "parkedWorkersStackNextIndex", "worker", "parkedWorkersStackPop", "parkedWorkersStackPush", "parkedWorkersStackTopUpdate", "oldIndex", "newIndex", "requestCpuWorker", "runSafely", "task", "shutdown", "timeout", "submitToLocalQueue", "toString", "tryUnpark", "Companion", "Worker", "WorkerState", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: CoroutineScheduler.kt */
public final class CoroutineScheduler implements Executor, Closeable {
    private static final int ADDED = -1;
    private static final int ADDED_REQUIRES_HELP = 0;
    private static final int ALLOWED = 0;
    private static final long BLOCKING_MASK = 4398044413952L;
    private static final int BLOCKING_SHIFT = 21;
    private static final long CREATED_MASK = 2097151;
    public static final Companion Companion = new Companion();
    private static final int FORBIDDEN = -1;
    private static final int MAX_PARK_TIME_NS = ((int) TimeUnit.SECONDS.toNanos(1));
    private static final int MAX_SPINS = 1000;
    public static final int MAX_SUPPORTED_POOL_SIZE = 2097150;
    private static final int MAX_YIELDS = 1500;
    private static final int MIN_PARK_TIME_NS = ((int) RangesKt___RangesKt.coerceAtMost(RangesKt___RangesKt.coerceAtLeast(TasksKt.WORK_STEALING_TIME_RESOLUTION_NS / ((long) 4), 10), (long) MAX_PARK_TIME_NS));
    public static final int MIN_SUPPORTED_POOL_SIZE = 1;
    private static final int NOT_ADDED = 1;
    private static final Symbol NOT_IN_STACK = new Symbol("NOT_IN_STACK");
    private static final long PARKED_INDEX_MASK = 2097151;
    private static final long PARKED_VERSION_INC = 2097152;
    private static final long PARKED_VERSION_MASK = -2097152;
    private static final int TERMINATED = 1;
    private static final AtomicIntegerFieldUpdater _isTerminated$FU = AtomicIntegerFieldUpdater.newUpdater(CoroutineScheduler.class, "_isTerminated");
    static final AtomicLongFieldUpdater controlState$FU = AtomicLongFieldUpdater.newUpdater(CoroutineScheduler.class, "controlState");
    private static final AtomicLongFieldUpdater parkedWorkersStack$FU = AtomicLongFieldUpdater.newUpdater(CoroutineScheduler.class, "parkedWorkersStack");
    private volatile int _isTerminated;
    volatile long controlState;
    private final int corePoolSize;
    private final Semaphore cpuPermits;
    private final GlobalQueue globalQueue;
    private final long idleWorkerKeepAliveNs;
    private final int maxPoolSize;
    private volatile long parkedWorkersStack;
    private final Random random;
    private final String schedulerName;
    private final Worker[] workers;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\u00020\u00048\u0002X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\r\u0010\u0002R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\u00020\u00048\u0002X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0012\u0010\u0002R\u000e\u0010\u0013\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lkotlinx/coroutines/scheduling/CoroutineScheduler$Companion;", "", "()V", "ADDED", "", "ADDED_REQUIRES_HELP", "ALLOWED", "BLOCKING_MASK", "", "BLOCKING_SHIFT", "CREATED_MASK", "FORBIDDEN", "MAX_PARK_TIME_NS", "MAX_PARK_TIME_NS$annotations", "MAX_SPINS", "MAX_SUPPORTED_POOL_SIZE", "MAX_YIELDS", "MIN_PARK_TIME_NS", "MIN_PARK_TIME_NS$annotations", "MIN_SUPPORTED_POOL_SIZE", "NOT_ADDED", "NOT_IN_STACK", "Lkotlinx/coroutines/internal/Symbol;", "PARKED_INDEX_MASK", "PARKED_VERSION_INC", "PARKED_VERSION_MASK", "TERMINATED", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: CoroutineScheduler.kt */
    public static final class Companion {
        @JvmStatic
        private static /* synthetic */ void MAX_PARK_TIME_NS$annotations() {
        }

        @JvmStatic
        private static /* synthetic */ void MIN_PARK_TIME_NS$annotations() {
        }

        private Companion() {
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\r\b\u0004\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0005J\u0010\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0002J\u0010\u00100\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0002J\b\u00101\u001a\u00020\fH\u0002J\b\u00102\u001a\u00020-H\u0002J\b\u00103\u001a\u00020-H\u0002J\u0010\u00104\u001a\u00020-2\u0006\u00105\u001a\u00020\u0010H\u0002J\u000f\u00106\u001a\u0004\u0018\u00010/H\u0000¢\u0006\u0002\b7J\n\u00108\u001a\u0004\u0018\u00010/H\u0002J\u0010\u00109\u001a\u00020-2\u0006\u0010:\u001a\u00020;H\u0002J\u0006\u0010<\u001a\u00020-J\u0015\u0010=\u001a\u00020\u00032\u0006\u0010>\u001a\u00020\u0003H\u0000¢\u0006\u0002\b?J\b\u0010@\u001a\u00020-H\u0016J\u0006\u0010A\u001a\u00020\fJ\u0006\u0010B\u001a\u00020\fJ\u0015\u0010C\u001a\u00020\f2\u0006\u0010D\u001a\u00020$H\u0000¢\u0006\u0002\bEJ\n\u0010F\u001a\u0004\u0018\u00010/H\u0002J\b\u0010G\u001a\u00020-H\u0002R$\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\rR\u0011\u0010\u000e\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\rR\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u001e\u001a\u00020\u001f8F¢\u0006\u0006\u001a\u0004\b \u0010!R\u000e\u0010\"\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010#\u001a\u00020$X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u000e\u0010)\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0004¢\u0006\u0002\n\u0000¨\u0006H"}, d2 = {"Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;", "Ljava/lang/Thread;", "index", "", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler;I)V", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler;)V", "indexInArray", "getIndexInArray", "()I", "setIndexInArray", "(I)V", "isBlocking", "", "()Z", "isParking", "lastExhaustionTime", "", "lastStealIndex", "localQueue", "Lkotlinx/coroutines/scheduling/WorkQueue;", "getLocalQueue", "()Lkotlinx/coroutines/scheduling/WorkQueue;", "nextParkedWorker", "", "getNextParkedWorker", "()Ljava/lang/Object;", "setNextParkedWorker", "(Ljava/lang/Object;)V", "parkTimeNs", "rngState", "scheduler", "Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "getScheduler", "()Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "spins", "state", "Lkotlinx/coroutines/scheduling/CoroutineScheduler$WorkerState;", "getState", "()Lkotlinx/coroutines/scheduling/CoroutineScheduler$WorkerState;", "setState", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler$WorkerState;)V", "terminationDeadline", "terminationState", "Lkotlinx/atomicfu/AtomicInt;", "afterTask", "", "task", "Lkotlinx/coroutines/scheduling/Task;", "beforeTask", "blockingQuiescence", "blockingWorkerIdle", "cpuWorkerIdle", "doPark", "nanos", "findTask", "findTask$kotlinx_coroutines_core", "findTaskWithCpuPermit", "idleReset", "mode", "Lkotlinx/coroutines/scheduling/TaskMode;", "idleResetBeforeUnpark", "nextInt", "upperBound", "nextInt$kotlinx_coroutines_core", "run", "tryAcquireCpuPermit", "tryForbidTermination", "tryReleaseCpu", "newState", "tryReleaseCpu$kotlinx_coroutines_core", "trySteal", "tryTerminateWorker", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: CoroutineScheduler.kt */
    public final class Worker extends Thread {
        private static final AtomicIntegerFieldUpdater terminationState$FU = AtomicIntegerFieldUpdater.newUpdater(Worker.class, "terminationState");
        private volatile int indexInArray;
        private long lastExhaustionTime;
        private int lastStealIndex;
        @NotNull
        private final WorkQueue localQueue;
        @Nullable
        private volatile Object nextParkedWorker;
        private int parkTimeNs;
        private int rngState;
        private volatile int spins;
        @NotNull
        private volatile WorkerState state;
        private long terminationDeadline;
        private volatile int terminationState;

        private Worker() {
            setDaemon(true);
            this.localQueue = new WorkQueue();
            this.state = WorkerState.RETIRING;
            this.terminationState = 0;
            this.nextParkedWorker = CoroutineScheduler.NOT_IN_STACK;
            this.parkTimeNs = CoroutineScheduler.MIN_PARK_TIME_NS;
            this.rngState = CoroutineScheduler.this.random.nextInt();
        }

        public final int getIndexInArray() {
            return this.indexInArray;
        }

        public final void setIndexInArray(int i) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(CoroutineScheduler.this.schedulerName);
            stringBuilder.append("-worker-");
            stringBuilder.append(i == 0 ? "TERMINATED" : String.valueOf(i));
            setName(stringBuilder.toString());
            this.indexInArray = i;
        }

        public Worker(CoroutineScheduler coroutineScheduler, int i) {
            this();
            setIndexInArray(i);
        }

        @NotNull
        public final CoroutineScheduler getScheduler() {
            return CoroutineScheduler.this;
        }

        @NotNull
        public final WorkQueue getLocalQueue() {
            return this.localQueue;
        }

        @NotNull
        public final WorkerState getState() {
            return this.state;
        }

        public final void setState(@NotNull WorkerState workerState) {
            Intrinsics.checkParameterIsNotNull(workerState, "<set-?>");
            this.state = workerState;
        }

        public final boolean isParking() {
            return this.state == WorkerState.PARKING;
        }

        public final boolean isBlocking() {
            return this.state == WorkerState.BLOCKING;
        }

        @Nullable
        public final Object getNextParkedWorker() {
            return this.nextParkedWorker;
        }

        public final void setNextParkedWorker(@Nullable Object obj) {
            this.nextParkedWorker = obj;
        }

        public final boolean tryForbidTermination() {
            int i = this.terminationState;
            switch (i) {
                case -1:
                case 1:
                    return false;
                case 0:
                    return terminationState$FU.compareAndSet(this, 0, -1);
                default:
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Invalid terminationState = ");
                    stringBuilder.append(i);
                    throw new IllegalStateException(stringBuilder.toString().toString());
            }
        }

        public final boolean tryAcquireCpuPermit() {
            if (this.state == WorkerState.CPU_ACQUIRED) {
                return true;
            }
            if (!CoroutineScheduler.this.cpuPermits.tryAcquire()) {
                return false;
            }
            this.state = WorkerState.CPU_ACQUIRED;
            return true;
        }

        public final boolean tryReleaseCpu$kotlinx_coroutines_core(@NotNull WorkerState workerState) {
            Intrinsics.checkParameterIsNotNull(workerState, "newState");
            WorkerState workerState2 = this.state;
            boolean z = workerState2 == WorkerState.CPU_ACQUIRED;
            if (z) {
                CoroutineScheduler.this.cpuPermits.release();
            }
            if (workerState2 != workerState) {
                this.state = workerState;
            }
            return z;
        }

        public void run() {
            Object obj = null;
            while (!CoroutineScheduler.this.isTerminated() && this.state != WorkerState.TERMINATED) {
                Task findTask$kotlinx_coroutines_core = findTask$kotlinx_coroutines_core();
                if (findTask$kotlinx_coroutines_core == null) {
                    if (this.state == WorkerState.CPU_ACQUIRED) {
                        cpuWorkerIdle();
                    } else {
                        blockingWorkerIdle();
                    }
                    obj = 1;
                } else {
                    if (obj != null) {
                        idleReset(findTask$kotlinx_coroutines_core.getMode());
                        obj = null;
                    }
                    beforeTask(findTask$kotlinx_coroutines_core);
                    CoroutineScheduler.this.runSafely(findTask$kotlinx_coroutines_core);
                    afterTask(findTask$kotlinx_coroutines_core);
                }
            }
            tryReleaseCpu$kotlinx_coroutines_core(WorkerState.TERMINATED);
        }

        private final void beforeTask(Task task) {
            if (task.getMode() != TaskMode.NON_BLOCKING) {
                CoroutineScheduler.controlState$FU.addAndGet(CoroutineScheduler.this, 2097152);
                if (tryReleaseCpu$kotlinx_coroutines_core(WorkerState.BLOCKING) != null) {
                    CoroutineScheduler.this.requestCpuWorker();
                }
            } else if (CoroutineScheduler.this.cpuPermits.availablePermits() != 0) {
                long nanoTime = TasksKt.schedulerTimeSource.nanoTime();
                if (nanoTime - task.submissionTime >= TasksKt.WORK_STEALING_TIME_RESOLUTION_NS && nanoTime - this.lastExhaustionTime >= TasksKt.WORK_STEALING_TIME_RESOLUTION_NS * ((long) 5)) {
                    this.lastExhaustionTime = nanoTime;
                    CoroutineScheduler.this.requestCpuWorker();
                }
            }
        }

        private final void afterTask(Task task) {
            if (task.getMode() != TaskMode.NON_BLOCKING) {
                CoroutineScheduler.controlState$FU.addAndGet(CoroutineScheduler.this, CoroutineScheduler.PARKED_VERSION_MASK);
                task = this.state;
                if (task != WorkerState.TERMINATED) {
                    Object obj = task == WorkerState.BLOCKING ? 1 : null;
                    if (_Assertions.ENABLED) {
                        if (obj == null) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Expected BLOCKING state, but has ");
                            stringBuilder.append(task);
                            throw new AssertionError(stringBuilder.toString());
                        }
                    }
                    this.state = WorkerState.RETIRING;
                }
            }
        }

        public final int nextInt$kotlinx_coroutines_core(int i) {
            this.rngState ^= this.rngState << 13;
            this.rngState ^= this.rngState >> 17;
            this.rngState ^= this.rngState << 5;
            int i2 = i - 1;
            if ((i2 & i) == 0) {
                return this.rngState & i2;
            }
            return (this.rngState & Integer.MAX_VALUE) % i;
        }

        private final void cpuWorkerIdle() {
            int i = this.spins;
            if (i <= CoroutineScheduler.MAX_YIELDS) {
                this.spins = i + 1;
                if (i >= 1000) {
                    Thread.yield();
                    return;
                }
                return;
            }
            if (this.parkTimeNs < CoroutineScheduler.MAX_PARK_TIME_NS) {
                this.parkTimeNs = RangesKt___RangesKt.coerceAtMost((this.parkTimeNs * 3) >>> 1, CoroutineScheduler.MAX_PARK_TIME_NS);
            }
            tryReleaseCpu$kotlinx_coroutines_core(WorkerState.PARKING);
            doPark((long) this.parkTimeNs);
        }

        private final void blockingWorkerIdle() {
            tryReleaseCpu$kotlinx_coroutines_core(WorkerState.PARKING);
            if (blockingQuiescence()) {
                this.terminationState = 0;
                if (this.terminationDeadline == 0) {
                    this.terminationDeadline = System.nanoTime() + CoroutineScheduler.this.idleWorkerKeepAliveNs;
                }
                doPark(CoroutineScheduler.this.idleWorkerKeepAliveNs);
                if (System.nanoTime() - this.terminationDeadline >= 0) {
                    this.terminationDeadline = 0;
                    tryTerminateWorker();
                }
            }
        }

        private final void doPark(long j) {
            CoroutineScheduler.this.parkedWorkersStackPush(this);
            LockSupport.parkNanos(j);
        }

        private final void tryTerminateWorker() {
            synchronized (CoroutineScheduler.this.workers) {
                if (CoroutineScheduler.this.isTerminated()) {
                } else if (CoroutineScheduler.this.getCreatedWorkers() <= CoroutineScheduler.this.corePoolSize) {
                } else if (!blockingQuiescence()) {
                } else if (terminationState$FU.compareAndSet(this, 0, 1)) {
                    int i = this.indexInArray;
                    setIndexInArray(0);
                    CoroutineScheduler.this.parkedWorkersStackTopUpdate(this, i, 0);
                    int andDecrement = (int) (CoroutineScheduler.controlState$FU.getAndDecrement(CoroutineScheduler.this) & 2097151);
                    if (andDecrement != i) {
                        Worker worker = CoroutineScheduler.this.workers[andDecrement];
                        if (worker == null) {
                            Intrinsics.throwNpe();
                        }
                        CoroutineScheduler.this.workers[i] = worker;
                        worker.setIndexInArray(i);
                        CoroutineScheduler.this.parkedWorkersStackTopUpdate(worker, andDecrement, i);
                    }
                    CoroutineScheduler.this.workers[andDecrement] = (Worker) null;
                    Unit unit = Unit.INSTANCE;
                    this.state = WorkerState.TERMINATED;
                }
            }
        }

        private final boolean blockingQuiescence() {
            Task removeFirstBlockingModeOrNull = CoroutineScheduler.this.globalQueue.removeFirstBlockingModeOrNull();
            if (removeFirstBlockingModeOrNull == null) {
                return true;
            }
            this.localQueue.add(removeFirstBlockingModeOrNull, CoroutineScheduler.this.globalQueue);
            return false;
        }

        private final void idleReset(TaskMode taskMode) {
            this.terminationDeadline = 0;
            this.lastStealIndex = 0;
            if (this.state == WorkerState.PARKING) {
                taskMode = taskMode == TaskMode.PROBABLY_BLOCKING ? true : null;
                if (_Assertions.ENABLED) {
                    if (taskMode == null) {
                        throw ((Throwable) new AssertionError("Assertion failed"));
                    }
                }
                this.state = WorkerState.BLOCKING;
                this.parkTimeNs = CoroutineScheduler.MIN_PARK_TIME_NS;
            }
            this.spins = 0;
        }

        public final void idleResetBeforeUnpark() {
            this.parkTimeNs = CoroutineScheduler.MIN_PARK_TIME_NS;
            this.spins = 0;
        }

        @Nullable
        public final Task findTask$kotlinx_coroutines_core() {
            if (tryAcquireCpuPermit()) {
                return findTaskWithCpuPermit();
            }
            Task poll = this.localQueue.poll();
            if (poll == null) {
                poll = CoroutineScheduler.this.globalQueue.removeFirstBlockingModeOrNull();
            }
            return poll;
        }

        private final Task findTaskWithCpuPermit() {
            Task removeFirstIfNotClosed;
            Object obj = nextInt$kotlinx_coroutines_core(CoroutineScheduler.this.corePoolSize * 2) == 0 ? 1 : null;
            if (obj != null) {
                removeFirstIfNotClosed = CoroutineScheduler.this.globalQueue.removeFirstIfNotClosed();
                if (removeFirstIfNotClosed != null) {
                    return removeFirstIfNotClosed;
                }
            }
            removeFirstIfNotClosed = this.localQueue.poll();
            if (removeFirstIfNotClosed != null) {
                return removeFirstIfNotClosed;
            }
            if (obj == null) {
                Task removeFirstIfNotClosed2 = CoroutineScheduler.this.globalQueue.removeFirstIfNotClosed();
                if (removeFirstIfNotClosed2 != null) {
                    return removeFirstIfNotClosed2;
                }
            }
            return trySteal();
        }

        private final Task trySteal() {
            int access$getCreatedWorkers$p = CoroutineScheduler.this.getCreatedWorkers();
            if (access$getCreatedWorkers$p < 2) {
                return null;
            }
            int i = this.lastStealIndex;
            if (i == 0) {
                i = nextInt$kotlinx_coroutines_core(access$getCreatedWorkers$p);
            }
            i++;
            if (i > access$getCreatedWorkers$p) {
                i = 1;
            }
            this.lastStealIndex = i;
            Worker worker = CoroutineScheduler.this.workers[i];
            if (worker == null || worker == this || !this.localQueue.trySteal(worker.localQueue, CoroutineScheduler.this.globalQueue)) {
                return null;
            }
            return this.localQueue.poll();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lkotlinx/coroutines/scheduling/CoroutineScheduler$WorkerState;", "", "(Ljava/lang/String;I)V", "CPU_ACQUIRED", "BLOCKING", "PARKING", "RETIRING", "TERMINATED", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: CoroutineScheduler.kt */
    public enum WorkerState {
    }

    private final int blockingWorkers(long j) {
        return (int) ((j & BLOCKING_MASK) >> 21);
    }

    private final int createdWorkers(long j) {
        return (int) (j & 2097151);
    }

    public CoroutineScheduler(int i, int i2, long j, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "schedulerName");
        this.corePoolSize = i;
        this.maxPoolSize = i2;
        this.idleWorkerKeepAliveNs = j;
        this.schedulerName = str;
        if ((this.corePoolSize >= 1 ? 1 : 0) != 0) {
            if ((this.maxPoolSize >= this.corePoolSize ? 1 : 0) != 0) {
                if ((this.maxPoolSize <= MAX_SUPPORTED_POOL_SIZE ? 1 : 0) != 0) {
                    if ((this.idleWorkerKeepAliveNs > 0 ? 1 : 0) != 0) {
                        this.globalQueue = new GlobalQueue();
                        this.cpuPermits = new Semaphore(this.corePoolSize, false);
                        this.parkedWorkersStack = 0;
                        this.workers = new Worker[(this.maxPoolSize + 1)];
                        this.controlState = 0;
                        this.random = new Random();
                        this._isTerminated = 0;
                        return;
                    }
                    i = new StringBuilder();
                    i.append("Idle worker keep alive time ");
                    i.append(this.idleWorkerKeepAliveNs);
                    i.append(" must be positive");
                    throw ((Throwable) new IllegalArgumentException(i.toString().toString()));
                }
                i = new StringBuilder();
                i.append("Max pool size ");
                i.append(this.maxPoolSize);
                i.append(" should not exceed maximal supported number of threads 2097150");
                throw ((Throwable) new IllegalArgumentException(i.toString().toString()));
            }
            i = new StringBuilder();
            i.append("Max pool size ");
            i.append(this.maxPoolSize);
            i.append(" should be greater than or equals to core pool size ");
            i.append(this.corePoolSize);
            throw ((Throwable) new IllegalArgumentException(i.toString().toString()));
        }
        i = new StringBuilder();
        i.append("Core pool size ");
        i.append(this.corePoolSize);
        i.append(" should be at least 1");
        throw ((Throwable) new IllegalArgumentException(i.toString().toString()));
    }

    public /* synthetic */ CoroutineScheduler(int i, int i2, long j, String str, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i3 & 4) != null) {
            j = TasksKt.IDLE_WORKER_KEEP_ALIVE_NS;
        }
        long j2 = j;
        if ((i3 & 8) != null) {
            str = TasksKt.DEFAULT_SCHEDULER_NAME;
        }
        this(i, i2, j2, str);
    }

    private final void parkedWorkersStackPush(Worker worker) {
        if (worker.getNextParkedWorker() == NOT_IN_STACK) {
            long j;
            int indexInArray;
            long j2;
            do {
                j = this.parkedWorkersStack;
                int i = (int) (2097151 & j);
                j2 = (2097152 + j) & PARKED_VERSION_MASK;
                indexInArray = worker.getIndexInArray();
                Object obj = indexInArray != 0 ? 1 : null;
                if (_Assertions.ENABLED) {
                    if (obj == null) {
                        throw ((Throwable) new AssertionError("Assertion failed"));
                    }
                }
                worker.setNextParkedWorker(this.workers[i]);
            } while (!parkedWorkersStack$FU.compareAndSet(this, j, ((long) indexInArray) | j2));
        }
    }

    private final int parkedWorkersStackNextIndex(Worker worker) {
        worker = worker.getNextParkedWorker();
        while (worker != NOT_IN_STACK) {
            if (worker == null) {
                return null;
            }
            worker = worker;
            int indexInArray = worker.getIndexInArray();
            if (indexInArray != 0) {
                return indexInArray;
            }
            worker = worker.getNextParkedWorker();
        }
        return -1;
    }

    private final int getCreatedWorkers() {
        return (int) (this.controlState & 2097151);
    }

    private final int getBlockingWorkers() {
        return (int) ((this.controlState & BLOCKING_MASK) >> 21);
    }

    private final int incrementCreatedWorkers() {
        return (int) (controlState$FU.incrementAndGet(this) & 2097151);
    }

    private final int decrementCreatedWorkers() {
        return (int) (controlState$FU.getAndDecrement(this) & 2097151);
    }

    private final void incrementBlockingWorkers() {
        controlState$FU.addAndGet(this, 2097152);
    }

    private final void decrementBlockingWorkers() {
        controlState$FU.addAndGet(this, PARKED_VERSION_MASK);
    }

    private final boolean isTerminated() {
        return this._isTerminated != 0;
    }

    public void execute(@NotNull Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(runnable, "command");
        dispatch$default(this, runnable, null, false, 6, null);
    }

    public void close() {
        shutdown(10000);
    }

    public final void shutdown(long j) {
        int i = 0;
        if (_isTerminated$FU.compareAndSet(this, 0, 1)) {
            int i2;
            Thread currentThread = Thread.currentThread();
            if (!(currentThread instanceof Worker)) {
                currentThread = null;
            }
            Worker worker = (Worker) currentThread;
            synchronized (this.workers) {
                i2 = (int) (this.controlState & 2097151);
            }
            if (1 <= i2) {
                int i3 = 1;
                while (true) {
                    Worker worker2 = this.workers[i3];
                    if (worker2 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (worker2 != worker) {
                        while (worker2.isAlive()) {
                            LockSupport.unpark(worker2);
                            worker2.join(j);
                        }
                        WorkerState state = worker2.getState();
                        if ((state == WorkerState.TERMINATED ? 1 : null) != null) {
                            worker2.getLocalQueue().offloadAllWork$kotlinx_coroutines_core(this.globalQueue);
                        } else {
                            j = new StringBuilder();
                            j.append("Expected TERMINATED state, but found ");
                            j.append(state);
                            throw new IllegalStateException(j.toString().toString());
                        }
                    }
                    if (i3 == i2) {
                        break;
                    }
                    i3++;
                }
            }
            if (this.globalQueue.add(TasksKt.getCLOSED_TASK()) != null) {
                while (true) {
                    if (worker != null) {
                        j = worker.findTask$kotlinx_coroutines_core();
                        if (j != null) {
                            if (j != null) {
                                break;
                            }
                            runSafely(j);
                        }
                    }
                    j = this.globalQueue.removeFirstIfNotClosed();
                    if (j != null) {
                        break;
                    }
                    runSafely(j);
                }
                if (worker != null) {
                    worker.tryReleaseCpu$kotlinx_coroutines_core(WorkerState.TERMINATED);
                }
                if (this.cpuPermits.availablePermits() == this.corePoolSize) {
                    i = 1;
                }
                if (_Assertions.ENABLED != null) {
                    if (i == 0) {
                        throw ((Throwable) new AssertionError("Assertion failed"));
                    }
                }
                this.parkedWorkersStack = 0;
                this.controlState = 0;
                return;
            }
            throw ((Throwable) new IllegalStateException("GlobalQueue could not be closed yet".toString()));
        }
    }

    public static /* synthetic */ void dispatch$default(CoroutineScheduler coroutineScheduler, Runnable runnable, TaskContext taskContext, boolean z, int i, Object obj) {
        if ((i & 2) != null) {
            taskContext = NonBlockingContext.INSTANCE;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        coroutineScheduler.dispatch(runnable, taskContext, z);
    }

    public final void dispatch(@NotNull Runnable runnable, @NotNull TaskContext taskContext, boolean z) {
        Intrinsics.checkParameterIsNotNull(runnable, "block");
        Intrinsics.checkParameterIsNotNull(taskContext, "taskContext");
        TimeSourceKt.getTimeSource().trackTask();
        runnable = createTask$kotlinx_coroutines_core(runnable, taskContext);
        taskContext = submitToLocalQueue(runnable, z);
        if (taskContext != true) {
            if (taskContext != true) {
                requestCpuWorker();
            } else if (this.globalQueue.add(runnable) != null) {
                requestCpuWorker();
            } else {
                taskContext = new StringBuilder();
                taskContext.append(this.schedulerName);
                taskContext.append(" was terminated");
                throw ((Throwable) new RejectedExecutionException(taskContext.toString()));
            }
        }
    }

    @NotNull
    public final Task createTask$kotlinx_coroutines_core(@NotNull Runnable runnable, @NotNull TaskContext taskContext) {
        Intrinsics.checkParameterIsNotNull(runnable, "block");
        Intrinsics.checkParameterIsNotNull(taskContext, "taskContext");
        return new Task(runnable, TasksKt.schedulerTimeSource.nanoTime(), taskContext);
    }

    private final void requestCpuWorker() {
        if (this.cpuPermits.availablePermits() == 0) {
            tryUnpark();
        } else if (!tryUnpark()) {
            long j = this.controlState;
            if (((int) (2097151 & j)) - ((int) ((j & BLOCKING_MASK) >> 21)) < this.corePoolSize) {
                int createNewWorker = createNewWorker();
                if (createNewWorker == 1 && this.corePoolSize > 1) {
                    createNewWorker();
                }
                if (createNewWorker > 0) {
                    return;
                }
            }
            tryUnpark();
        }
    }

    private final boolean tryUnpark() {
        while (true) {
            Worker parkedWorkersStackPop = parkedWorkersStackPop();
            if (parkedWorkersStackPop == null) {
                return false;
            }
            parkedWorkersStackPop.idleResetBeforeUnpark();
            boolean isParking = parkedWorkersStackPop.isParking();
            LockSupport.unpark(parkedWorkersStackPop);
            if (isParking) {
                if (parkedWorkersStackPop.tryForbidTermination()) {
                    return true;
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int createNewWorker() {
        /*
        r9 = this;
        r0 = r9.workers;
        monitor-enter(r0);
        r1 = r9.isTerminated();	 Catch:{ all -> 0x0068 }
        if (r1 == 0) goto L_0x000c;
    L_0x0009:
        r1 = -1;
        monitor-exit(r0);
        return r1;
    L_0x000c:
        r1 = r9.controlState;	 Catch:{ all -> 0x0068 }
        r3 = 2097151; // 0x1fffff float:2.938734E-39 double:1.0361303E-317;
        r5 = r1 & r3;
        r5 = (int) r5;	 Catch:{ all -> 0x0068 }
        r6 = 4398044413952; // 0x3ffffe00000 float:NaN double:2.1729226538177E-311;
        r1 = r1 & r6;
        r6 = 21;
        r1 = r1 >> r6;
        r1 = (int) r1;	 Catch:{ all -> 0x0068 }
        r1 = r5 - r1;
        r2 = r9.corePoolSize;	 Catch:{ all -> 0x0068 }
        r6 = 0;
        if (r1 < r2) goto L_0x0027;
    L_0x0025:
        monitor-exit(r0);
        return r6;
    L_0x0027:
        r2 = r9.maxPoolSize;	 Catch:{ all -> 0x0068 }
        if (r5 >= r2) goto L_0x0066;
    L_0x002b:
        r2 = r9.cpuPermits;	 Catch:{ all -> 0x0068 }
        r2 = r2.availablePermits();	 Catch:{ all -> 0x0068 }
        if (r2 != 0) goto L_0x0034;
    L_0x0033:
        goto L_0x0066;
    L_0x0034:
        r2 = controlState$FU;	 Catch:{ all -> 0x0068 }
        r7 = r2.incrementAndGet(r9);	 Catch:{ all -> 0x0068 }
        r2 = r7 & r3;
        r2 = (int) r2;	 Catch:{ all -> 0x0068 }
        r3 = 1;
        if (r2 <= 0) goto L_0x0047;
    L_0x0040:
        r4 = r9.workers;	 Catch:{ all -> 0x0068 }
        r4 = r4[r2];	 Catch:{ all -> 0x0068 }
        if (r4 != 0) goto L_0x0047;
    L_0x0046:
        r6 = 1;
    L_0x0047:
        if (r6 == 0) goto L_0x0058;
    L_0x0049:
        r4 = new kotlinx.coroutines.scheduling.CoroutineScheduler$Worker;	 Catch:{ all -> 0x0068 }
        r4.<init>(r9, r2);	 Catch:{ all -> 0x0068 }
        r4.start();	 Catch:{ all -> 0x0068 }
        r5 = r9.workers;	 Catch:{ all -> 0x0068 }
        r5[r2] = r4;	 Catch:{ all -> 0x0068 }
        r1 = r1 + r3;
        monitor-exit(r0);
        return r1;
    L_0x0058:
        r1 = "Failed requirement.";
        r2 = new java.lang.IllegalArgumentException;	 Catch:{ all -> 0x0068 }
        r1 = r1.toString();	 Catch:{ all -> 0x0068 }
        r2.<init>(r1);	 Catch:{ all -> 0x0068 }
        r2 = (java.lang.Throwable) r2;	 Catch:{ all -> 0x0068 }
        throw r2;	 Catch:{ all -> 0x0068 }
    L_0x0066:
        monitor-exit(r0);
        return r6;
    L_0x0068:
        r1 = move-exception;
        monitor-exit(r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.scheduling.CoroutineScheduler.createNewWorker():int");
    }

    private final int submitToLocalQueue(Task task, boolean z) {
        Thread currentThread = Thread.currentThread();
        if (!(currentThread instanceof Worker)) {
            currentThread = null;
        }
        Worker worker = (Worker) currentThread;
        if (worker == null || worker.getScheduler() != this || worker.getState() == WorkerState.TERMINATED) {
            return 1;
        }
        int i = -1;
        if (task.getMode() == TaskMode.NON_BLOCKING) {
            if (worker.isBlocking()) {
                i = 0;
            } else if (!worker.tryAcquireCpuPermit()) {
                return 1;
            }
        }
        if (z) {
            task = worker.getLocalQueue().addLast(task, this.globalQueue);
        } else {
            task = worker.getLocalQueue().add(task, this.globalQueue);
        }
        if (task == null || worker.getLocalQueue().getBufferSize$kotlinx_coroutines_core() > TasksKt.QUEUE_SIZE_OFFLOAD_THRESHOLD) {
            return 0;
        }
        return i;
    }

    @NotNull
    public String toString() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (Worker worker : this.workers) {
            if (worker != null) {
                int size$kotlinx_coroutines_core = worker.getLocalQueue().size$kotlinx_coroutines_core();
                Collection collection;
                StringBuilder stringBuilder;
                switch (worker.getState()) {
                    case PARKING:
                        i3++;
                        break;
                    case BLOCKING:
                        i2++;
                        collection = arrayList;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(String.valueOf(size$kotlinx_coroutines_core));
                        stringBuilder.append("b");
                        collection.add(stringBuilder.toString());
                        break;
                    case CPU_ACQUIRED:
                        i++;
                        collection = arrayList;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(String.valueOf(size$kotlinx_coroutines_core));
                        stringBuilder.append("c");
                        collection.add(stringBuilder.toString());
                        break;
                    case RETIRING:
                        i4++;
                        if (size$kotlinx_coroutines_core <= 0) {
                            break;
                        }
                        collection = arrayList;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(String.valueOf(size$kotlinx_coroutines_core));
                        stringBuilder.append("r");
                        collection.add(stringBuilder.toString());
                        break;
                    case TERMINATED:
                        i5++;
                        break;
                    default:
                        break;
                }
            }
        }
        long j = this.controlState;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(this.schedulerName);
        stringBuilder2.append('@');
        stringBuilder2.append(DebugKt.getHexAddress(this));
        stringBuilder2.append('[');
        stringBuilder2.append("Pool Size {");
        stringBuilder2.append("core = ");
        stringBuilder2.append(this.corePoolSize);
        stringBuilder2.append(", ");
        stringBuilder2.append("max = ");
        stringBuilder2.append(this.maxPoolSize);
        stringBuilder2.append("}, ");
        stringBuilder2.append("Worker States {");
        stringBuilder2.append("CPU = ");
        stringBuilder2.append(i);
        stringBuilder2.append(", ");
        stringBuilder2.append("blocking = ");
        stringBuilder2.append(i2);
        stringBuilder2.append(", ");
        stringBuilder2.append("parked = ");
        stringBuilder2.append(i3);
        stringBuilder2.append(", ");
        stringBuilder2.append("retired = ");
        stringBuilder2.append(i4);
        stringBuilder2.append(", ");
        stringBuilder2.append("terminated = ");
        stringBuilder2.append(i5);
        stringBuilder2.append("}, ");
        stringBuilder2.append("running workers queues = ");
        stringBuilder2.append(arrayList);
        stringBuilder2.append(", ");
        stringBuilder2.append("global queue size = ");
        stringBuilder2.append(this.globalQueue.getSize());
        stringBuilder2.append(", ");
        stringBuilder2.append("Control State Workers {");
        stringBuilder2.append("created = ");
        stringBuilder2.append((int) (2097151 & j));
        stringBuilder2.append(", ");
        stringBuilder2.append("blocking = ");
        stringBuilder2.append((int) ((j & BLOCKING_MASK) >> 21));
        stringBuilder2.append('}');
        stringBuilder2.append("]");
        return stringBuilder2.toString();
    }

    private final void runSafely(Task task) {
        try {
            task.run();
        } catch (Throwable th) {
            TimeSourceKt.getTimeSource().unTrackTask();
        }
        TimeSourceKt.getTimeSource().unTrackTask();
    }

    private final void parkedWorkersStackTopUpdate(Worker worker, int i, int i2) {
        while (true) {
            long j = this.parkedWorkersStack;
            int i3 = (int) (2097151 & j);
            long j2 = (2097152 + j) & PARKED_VERSION_MASK;
            if (i3 == i) {
                i3 = i2 == 0 ? parkedWorkersStackNextIndex(worker) : i2;
            }
            if (i3 >= 0) {
                if (parkedWorkersStack$FU.compareAndSet(this, j, j2 | ((long) i3))) {
                    return;
                }
            }
        }
    }

    private final Worker parkedWorkersStackPop() {
        while (true) {
            long j = this.parkedWorkersStack;
            Worker worker = this.workers[(int) (2097151 & j)];
            if (worker == null) {
                return null;
            }
            long j2 = (2097152 + j) & PARKED_VERSION_MASK;
            int parkedWorkersStackNextIndex = parkedWorkersStackNextIndex(worker);
            if (parkedWorkersStackNextIndex >= 0) {
                if (parkedWorkersStack$FU.compareAndSet(this, j, ((long) parkedWorkersStackNextIndex) | j2)) {
                    worker.setNextParkedWorker(NOT_IN_STACK);
                    return worker;
                }
            }
        }
    }
}
