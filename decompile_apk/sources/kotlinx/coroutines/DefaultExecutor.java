package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.EventLoopBase.DelayedRunnableTask;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\bÀ\u0002\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u000eH\u0002J\r\u0010\u0018\u001a\u00020\u0016H\u0000¢\u0006\u0002\b\u0019J\u001c\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\b2\n\u0010\u001d\u001a\u00060\u0002j\u0002`\u0003H\u0016J\b\u0010\u001e\u001a\u00020\u0012H\u0014J\b\u0010\u001f\u001a\u00020\u0012H\u0002J\b\u0010 \u001a\u00020\u0016H\u0016J\u000e\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\bJ\b\u0010#\u001a\u00020\u000eH\u0002J\b\u0010$\u001a\u00020\u0016H\u0014R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u000f\u0010\u0004R\u000e\u0010\u0010\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u00128TX\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00128BX\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0013¨\u0006%"}, d2 = {"Lkotlinx/coroutines/DefaultExecutor;", "Lkotlinx/coroutines/EventLoopBase;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "()V", "ACTIVE", "", "DEFAULT_KEEP_ALIVE", "", "FRESH", "KEEP_ALIVE_NANOS", "SHUTDOWN_ACK", "SHUTDOWN_REQ", "_thread", "Ljava/lang/Thread;", "_thread$annotations", "debugStatus", "isCompleted", "", "()Z", "isShutdownRequested", "acknowledgeShutdownIfNeeded", "", "createThreadSync", "ensureStarted", "ensureStarted$kotlinx_coroutines_core", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "block", "isCorrectThread", "notifyStartup", "run", "shutdown", "timeout", "thread", "unpark", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: DefaultExecutor.kt */
public final class DefaultExecutor extends EventLoopBase implements Runnable {
    private static final int ACTIVE = 1;
    private static final long DEFAULT_KEEP_ALIVE = 1000;
    private static final int FRESH = 0;
    public static final DefaultExecutor INSTANCE = new DefaultExecutor();
    private static final long KEEP_ALIVE_NANOS;
    private static final int SHUTDOWN_ACK = 3;
    private static final int SHUTDOWN_REQ = 2;
    private static volatile Thread _thread;
    private static volatile int debugStatus;

    private static /* synthetic */ void _thread$annotations() {
    }

    protected boolean isCompleted() {
        return false;
    }

    protected boolean isCorrectThread() {
        return true;
    }

    static {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = new kotlinx.coroutines.DefaultExecutor;
        r0.<init>();
        INSTANCE = r0;
        r0 = java.util.concurrent.TimeUnit.MILLISECONDS;
        r1 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r3 = "kotlinx.coroutines.DefaultExecutor.keepAlive";	 Catch:{ SecurityException -> 0x0012 }
        r3 = java.lang.Long.getLong(r3, r1);	 Catch:{ SecurityException -> 0x0012 }
        goto L_0x0016;
    L_0x0012:
        r3 = java.lang.Long.valueOf(r1);
    L_0x0016:
        r1 = "try {\n            java.l…AULT_KEEP_ALIVE\n        }";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r1);
        r1 = r3.longValue();
        r0 = r0.toNanos(r1);
        KEEP_ALIVE_NANOS = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.DefaultExecutor.<clinit>():void");
    }

    private DefaultExecutor() {
    }

    private final boolean isShutdownRequested() {
        int i = debugStatus;
        if (i != 2) {
            if (i != 3) {
                return false;
            }
        }
        return true;
    }

    @NotNull
    public DisposableHandle invokeOnTimeout(long j, @NotNull Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(runnable, "block");
        DelayedRunnableTask delayedRunnableTask = new DelayedRunnableTask(j, runnable);
        INSTANCE.schedule$kotlinx_coroutines_core(delayedRunnableTask);
        return delayedRunnableTask;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r12 = this;
        r0 = kotlinx.coroutines.TimeSourceKt.getTimeSource();
        r0.registerTimeLoopThread();
        r0 = 0;
        r1 = r12.notifyStartup();	 Catch:{ all -> 0x009f }
        if (r1 != 0) goto L_0x0026;
    L_0x000e:
        r0 = (java.lang.Thread) r0;
        _thread = r0;
        r12.acknowledgeShutdownIfNeeded();
        r0 = kotlinx.coroutines.TimeSourceKt.getTimeSource();
        r0.unregisterTimeLoopThread();
        r0 = r12.isEmpty();
        if (r0 != 0) goto L_0x0025;
    L_0x0022:
        r12.thread();
    L_0x0025:
        return;
    L_0x0026:
        r1 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        r3 = r1;
    L_0x002c:
        java.lang.Thread.interrupted();	 Catch:{ all -> 0x009f }
        r5 = r12.processNextEvent();	 Catch:{ all -> 0x009f }
        r7 = 0;
        r9 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1));
        if (r9 != 0) goto L_0x0075;
    L_0x0039:
        r9 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1));
        if (r9 != 0) goto L_0x006f;
    L_0x003d:
        r10 = kotlinx.coroutines.TimeSourceKt.getTimeSource();	 Catch:{ all -> 0x009f }
        r10 = r10.nanoTime();	 Catch:{ all -> 0x009f }
        if (r9 != 0) goto L_0x004b;
    L_0x0047:
        r3 = KEEP_ALIVE_NANOS;	 Catch:{ all -> 0x009f }
        r9 = 0;
        r3 = r3 + r10;
    L_0x004b:
        r9 = 0;
        r9 = r3 - r10;
        r11 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1));
        if (r11 > 0) goto L_0x006a;
    L_0x0052:
        r0 = (java.lang.Thread) r0;
        _thread = r0;
        r12.acknowledgeShutdownIfNeeded();
        r0 = kotlinx.coroutines.TimeSourceKt.getTimeSource();
        r0.unregisterTimeLoopThread();
        r0 = r12.isEmpty();
        if (r0 != 0) goto L_0x0069;
    L_0x0066:
        r12.thread();
    L_0x0069:
        return;
    L_0x006a:
        r5 = kotlin.ranges.RangesKt___RangesKt.coerceAtMost(r5, r9);	 Catch:{ all -> 0x009f }
        goto L_0x0075;
    L_0x006f:
        r9 = KEEP_ALIVE_NANOS;	 Catch:{ all -> 0x009f }
        r5 = kotlin.ranges.RangesKt___RangesKt.coerceAtMost(r5, r9);	 Catch:{ all -> 0x009f }
    L_0x0075:
        r7 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1));
        if (r7 <= 0) goto L_0x002c;
    L_0x0079:
        r7 = r12.isShutdownRequested();	 Catch:{ all -> 0x009f }
        if (r7 == 0) goto L_0x0097;
    L_0x007f:
        r0 = (java.lang.Thread) r0;
        _thread = r0;
        r12.acknowledgeShutdownIfNeeded();
        r0 = kotlinx.coroutines.TimeSourceKt.getTimeSource();
        r0.unregisterTimeLoopThread();
        r0 = r12.isEmpty();
        if (r0 != 0) goto L_0x0096;
    L_0x0093:
        r12.thread();
    L_0x0096:
        return;
    L_0x0097:
        r7 = kotlinx.coroutines.TimeSourceKt.getTimeSource();	 Catch:{ all -> 0x009f }
        r7.parkNanos(r12, r5);	 Catch:{ all -> 0x009f }
        goto L_0x002c;
    L_0x009f:
        r1 = move-exception;
        r0 = (java.lang.Thread) r0;
        _thread = r0;
        r12.acknowledgeShutdownIfNeeded();
        r0 = kotlinx.coroutines.TimeSourceKt.getTimeSource();
        r0.unregisterTimeLoopThread();
        r0 = r12.isEmpty();
        if (r0 != 0) goto L_0x00b7;
    L_0x00b4:
        r12.thread();
    L_0x00b7:
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.DefaultExecutor.run():void");
    }

    private final Thread thread() {
        Thread thread = _thread;
        return thread != null ? thread : createThreadSync();
    }

    private final synchronized Thread createThreadSync() {
        Thread thread;
        thread = _thread;
        if (thread == null) {
            thread = new Thread(this, "kotlinx.coroutines.DefaultExecutor");
            _thread = thread;
            thread.setDaemon(true);
            thread.start();
        }
        return thread;
    }

    protected void unpark() {
        TimeSourceKt.getTimeSource().unpark(thread());
    }

    public final synchronized void ensureStarted$kotlinx_coroutines_core() {
        Object obj = 1;
        Object obj2 = _thread == null ? 1 : null;
        if (_Assertions.ENABLED) {
            if (obj2 == null) {
                throw new AssertionError("Assertion failed");
            }
        }
        if (debugStatus != 0) {
            if (debugStatus != 3) {
                obj = null;
            }
        }
        if (_Assertions.ENABLED) {
            if (obj == null) {
                throw new AssertionError("Assertion failed");
            }
        }
        debugStatus = 0;
        createThreadSync();
        while (debugStatus == 0) {
            wait();
        }
    }

    private final synchronized boolean notifyStartup() {
        if (isShutdownRequested()) {
            return false;
        }
        debugStatus = 1;
        notifyAll();
        return true;
    }

    public final synchronized void shutdown(long j) {
        long currentTimeMillis = System.currentTimeMillis() + j;
        if (!isShutdownRequested()) {
            debugStatus = 2;
        }
        while (debugStatus != 3 && _thread != null) {
            Thread thread = _thread;
            if (thread != null) {
                TimeSourceKt.getTimeSource().unpark(thread);
            }
            if (currentTimeMillis - System.currentTimeMillis() <= 0) {
                break;
            }
            wait(j);
        }
        debugStatus = 0;
    }

    private final synchronized void acknowledgeShutdownIfNeeded() {
        if (isShutdownRequested()) {
            debugStatus = 3;
            resetAll();
            notifyAll();
        }
    }
}
