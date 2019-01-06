package kotlinx.coroutines.scheduling;

import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.InternalCoroutinesApi;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0017\u0018\u00002\u00020\u0001B\u001b\b\u0016\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0003J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\nH\u0002J\u001c\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00172\n\u0010\u0018\u001a\u00060\u0019j\u0002`\u001aH\u0016J)\u0010\u001b\u001a\u00020\u00132\n\u0010\u0018\u001a\u00060\u0019j\u0002`\u001a2\u0006\u0010\u0016\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0000¢\u0006\u0002\b\u001fJ\u001c\u0010 \u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00172\n\u0010\u0018\u001a\u00060\u0019j\u0002`\u001aH\u0016J\u000e\u0010!\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003J\r\u0010\"\u001a\u00020\u0013H\u0000¢\u0006\u0002\b#J\u0015\u0010$\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u0007H\u0000¢\u0006\u0002\b&J\b\u0010'\u001a\u00020(H\u0016J\r\u0010)\u001a\u00020\u0013H\u0000¢\u0006\u0002\b*R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8VX\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lkotlinx/coroutines/scheduling/ExperimentalCoroutineDispatcher;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "corePoolSize", "", "maxPoolSize", "(II)V", "idleWorkerKeepAliveNs", "", "(IIJ)V", "coroutineScheduler", "Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "executor", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "blocking", "Lkotlinx/coroutines/CoroutineDispatcher;", "parallelism", "close", "", "createScheduler", "dispatch", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "dispatchWithContext", "Lkotlinx/coroutines/scheduling/TaskContext;", "fair", "", "dispatchWithContext$kotlinx_coroutines_core", "dispatchYield", "limited", "restore", "restore$kotlinx_coroutines_core", "shutdown", "timeout", "shutdown$kotlinx_coroutines_core", "toString", "", "usePrivateScheduler", "usePrivateScheduler$kotlinx_coroutines_core", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
@InternalCoroutinesApi
/* compiled from: Dispatcher.kt */
public class ExperimentalCoroutineDispatcher extends ExecutorCoroutineDispatcher {
    private final int corePoolSize;
    private CoroutineScheduler coroutineScheduler;
    private final long idleWorkerKeepAliveNs;
    private final int maxPoolSize;

    public ExperimentalCoroutineDispatcher(int i, int i2, long j) {
        this.corePoolSize = i;
        this.maxPoolSize = i2;
        this.idleWorkerKeepAliveNs = j;
        this.coroutineScheduler = createScheduler();
    }

    public /* synthetic */ ExperimentalCoroutineDispatcher(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i3 & 1) != null) {
            i = TasksKt.CORE_POOL_SIZE;
        }
        if ((i3 & 2) != 0) {
            i2 = TasksKt.MAX_POOL_SIZE;
        }
        this(i, i2);
    }

    public ExperimentalCoroutineDispatcher(int i, int i2) {
        this(i, i2, TasksKt.IDLE_WORKER_KEEP_ALIVE_NS);
    }

    @NotNull
    public Executor getExecutor() {
        return this.coroutineScheduler;
    }

    public void dispatch(@org.jetbrains.annotations.NotNull kotlin.coroutines.CoroutineContext r8, @org.jetbrains.annotations.NotNull java.lang.Runnable r9) {
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r7 = this;
        r0 = "context";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0);
        r0 = "block";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0);
        r1 = r7.coroutineScheduler;	 Catch:{ RejectedExecutionException -> 0x0015 }
        r3 = 0;	 Catch:{ RejectedExecutionException -> 0x0015 }
        r4 = 0;	 Catch:{ RejectedExecutionException -> 0x0015 }
        r5 = 6;	 Catch:{ RejectedExecutionException -> 0x0015 }
        r6 = 0;	 Catch:{ RejectedExecutionException -> 0x0015 }
        r2 = r9;	 Catch:{ RejectedExecutionException -> 0x0015 }
        kotlinx.coroutines.scheduling.CoroutineScheduler.dispatch$default(r1, r2, r3, r4, r5, r6);	 Catch:{ RejectedExecutionException -> 0x0015 }
        goto L_0x001a;
    L_0x0015:
        r0 = kotlinx.coroutines.DefaultExecutor.INSTANCE;
        r0.dispatch(r8, r9);
    L_0x001a:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.scheduling.ExperimentalCoroutineDispatcher.dispatch(kotlin.coroutines.CoroutineContext, java.lang.Runnable):void");
    }

    public void dispatchYield(@org.jetbrains.annotations.NotNull kotlin.coroutines.CoroutineContext r8, @org.jetbrains.annotations.NotNull java.lang.Runnable r9) {
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r7 = this;
        r0 = "context";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0);
        r0 = "block";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0);
        r1 = r7.coroutineScheduler;	 Catch:{ RejectedExecutionException -> 0x0015 }
        r3 = 0;	 Catch:{ RejectedExecutionException -> 0x0015 }
        r4 = 1;	 Catch:{ RejectedExecutionException -> 0x0015 }
        r5 = 2;	 Catch:{ RejectedExecutionException -> 0x0015 }
        r6 = 0;	 Catch:{ RejectedExecutionException -> 0x0015 }
        r2 = r9;	 Catch:{ RejectedExecutionException -> 0x0015 }
        kotlinx.coroutines.scheduling.CoroutineScheduler.dispatch$default(r1, r2, r3, r4, r5, r6);	 Catch:{ RejectedExecutionException -> 0x0015 }
        goto L_0x001a;
    L_0x0015:
        r0 = kotlinx.coroutines.DefaultExecutor.INSTANCE;
        r0.dispatchYield(r8, r9);
    L_0x001a:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.scheduling.ExperimentalCoroutineDispatcher.dispatchYield(kotlin.coroutines.CoroutineContext, java.lang.Runnable):void");
    }

    public void close() {
        this.coroutineScheduler.close();
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        stringBuilder.append("[scheduler = ");
        stringBuilder.append(this.coroutineScheduler);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    @NotNull
    public static /* synthetic */ CoroutineDispatcher blocking$default(ExperimentalCoroutineDispatcher experimentalCoroutineDispatcher, int i, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 1) != 0) {
                i = TasksKt.BLOCKING_DEFAULT_PARALLELISM;
            }
            return experimentalCoroutineDispatcher.blocking(i);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: blocking");
    }

    @NotNull
    public final CoroutineDispatcher blocking(int i) {
        if ((i > 0 ? 1 : null) != null) {
            return new LimitingDispatcher(this, i, TaskMode.PROBABLY_BLOCKING);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected positive parallelism level, but have ");
        stringBuilder.append(i);
        throw new IllegalArgumentException(stringBuilder.toString().toString());
    }

    @NotNull
    public final CoroutineDispatcher limited(int i) {
        Object obj = null;
        if ((i > 0 ? 1 : null) != null) {
            if (i <= this.corePoolSize) {
                obj = 1;
            }
            if (obj != null) {
                return new LimitingDispatcher(this, i, TaskMode.NON_BLOCKING);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Expected parallelism level lesser than core pool size (");
            stringBuilder.append(this.corePoolSize);
            stringBuilder.append("), but have ");
            stringBuilder.append(i);
            throw new IllegalArgumentException(stringBuilder.toString().toString());
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("Expected positive parallelism level, but have ");
        stringBuilder.append(i);
        throw new IllegalArgumentException(stringBuilder.toString().toString());
    }

    public final void dispatchWithContext$kotlinx_coroutines_core(@org.jetbrains.annotations.NotNull java.lang.Runnable r2, @org.jetbrains.annotations.NotNull kotlinx.coroutines.scheduling.TaskContext r3, boolean r4) {
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r1 = this;
        r0 = "block";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0);
        r0 = "context";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0);
        r0 = r1.coroutineScheduler;	 Catch:{ RejectedExecutionException -> 0x0010 }
        r0.dispatch(r2, r3, r4);	 Catch:{ RejectedExecutionException -> 0x0010 }
        goto L_0x001d;
    L_0x0010:
        r4 = kotlinx.coroutines.DefaultExecutor.INSTANCE;
        r0 = r1.coroutineScheduler;
        r2 = r0.createTask$kotlinx_coroutines_core(r2, r3);
        r2 = (java.lang.Runnable) r2;
        r4.execute$kotlinx_coroutines_core(r2);
    L_0x001d:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.scheduling.ExperimentalCoroutineDispatcher.dispatchWithContext$kotlinx_coroutines_core(java.lang.Runnable, kotlinx.coroutines.scheduling.TaskContext, boolean):void");
    }

    private final CoroutineScheduler createScheduler() {
        return new CoroutineScheduler(this.corePoolSize, this.maxPoolSize, this.idleWorkerKeepAliveNs, null, 8, null);
    }

    public final synchronized void usePrivateScheduler$kotlinx_coroutines_core() {
        this.coroutineScheduler.shutdown(10000);
        this.coroutineScheduler = createScheduler();
    }

    public final synchronized void shutdown$kotlinx_coroutines_core(long j) {
        this.coroutineScheduler.shutdown(j);
    }

    public final void restore$kotlinx_coroutines_core() {
        usePrivateScheduler$kotlinx_coroutines_core();
    }
}
