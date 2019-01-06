package kotlinx.coroutines;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Delay.DefaultImpls;
import kotlinx.coroutines.internal.ConcurrentKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b \u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u001c\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u00060\fj\u0002`\rH\u0016J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\r\u0010\u0013\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\u0014J\u001c\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\n\u0010\u000b\u001a\u00060\fj\u0002`\rH\u0016J*\u0010\u0019\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001a2\n\u0010\u000b\u001a\u00060\fj\u0002`\r2\u0006\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u001e\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00070 H\u0016J\b\u0010!\u001a\u00020\"H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lkotlinx/coroutines/ExecutorCoroutineDispatcherBase;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "Lkotlinx/coroutines/Delay;", "()V", "removesFutureOnCancellation", "", "close", "", "dispatch", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "equals", "other", "", "hashCode", "", "initFutureCancellation", "initFutureCancellation$kotlinx_coroutines_core", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "", "scheduleBlock", "Ljava/util/concurrent/ScheduledFuture;", "time", "unit", "Ljava/util/concurrent/TimeUnit;", "scheduleResumeAfterDelay", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Executors.kt */
public abstract class ExecutorCoroutineDispatcherBase extends ExecutorCoroutineDispatcher implements Delay {
    private boolean removesFutureOnCancellation;

    @Nullable
    public Object delay(long j, @NotNull Continuation<? super Unit> continuation) {
        return DefaultImpls.delay(this, j, continuation);
    }

    public final void initFutureCancellation$kotlinx_coroutines_core() {
        this.removesFutureOnCancellation = ConcurrentKt.removeFutureOnCancel(getExecutor());
    }

    public void dispatch(@org.jetbrains.annotations.NotNull kotlin.coroutines.CoroutineContext r2, @org.jetbrains.annotations.NotNull java.lang.Runnable r3) {
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
        r0 = "context";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0);
        r2 = "block";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r2);
        r2 = r1.getExecutor();	 Catch:{ RejectedExecutionException -> 0x001a }
        r0 = kotlinx.coroutines.TimeSourceKt.getTimeSource();	 Catch:{ RejectedExecutionException -> 0x001a }
        r0 = r0.wrapTask(r3);	 Catch:{ RejectedExecutionException -> 0x001a }
        r2.execute(r0);	 Catch:{ RejectedExecutionException -> 0x001a }
        goto L_0x0026;
    L_0x001a:
        r2 = kotlinx.coroutines.TimeSourceKt.getTimeSource();
        r2.unTrackTask();
        r2 = kotlinx.coroutines.DefaultExecutor.INSTANCE;
        r2.execute$kotlinx_coroutines_core(r3);
    L_0x0026:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.ExecutorCoroutineDispatcherBase.dispatch(kotlin.coroutines.CoroutineContext, java.lang.Runnable):void");
    }

    public void scheduleResumeAfterDelay(long j, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
        Intrinsics.checkParameterIsNotNull(cancellableContinuation, "continuation");
        ScheduledFuture scheduleBlock = this.removesFutureOnCancellation ? scheduleBlock(new ResumeUndispatchedRunnable(this, cancellableContinuation), j, TimeUnit.MILLISECONDS) : null;
        if (scheduleBlock != null) {
            JobKt.cancelFutureOnCancellation(cancellableContinuation, scheduleBlock);
        } else {
            DefaultExecutor.INSTANCE.scheduleResumeAfterDelay(j, cancellableContinuation);
        }
    }

    @NotNull
    public DisposableHandle invokeOnTimeout(long j, @NotNull Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(runnable, "block");
        ScheduledFuture scheduleBlock = this.removesFutureOnCancellation ? scheduleBlock(runnable, j, TimeUnit.MILLISECONDS) : null;
        return scheduleBlock != null ? (DisposableHandle) new DisposableFutureHandle(scheduleBlock) : DefaultExecutor.INSTANCE.invokeOnTimeout(j, runnable);
    }

    private final java.util.concurrent.ScheduledFuture<?> scheduleBlock(java.lang.Runnable r4, long r5, java.util.concurrent.TimeUnit r7) {
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
        r3 = this;
        r0 = 0;
        r1 = r3.getExecutor();	 Catch:{ RejectedExecutionException -> 0x0013 }
        r2 = r1 instanceof java.util.concurrent.ScheduledExecutorService;	 Catch:{ RejectedExecutionException -> 0x0013 }
        if (r2 != 0) goto L_0x000a;	 Catch:{ RejectedExecutionException -> 0x0013 }
    L_0x0009:
        r1 = r0;	 Catch:{ RejectedExecutionException -> 0x0013 }
    L_0x000a:
        r1 = (java.util.concurrent.ScheduledExecutorService) r1;	 Catch:{ RejectedExecutionException -> 0x0013 }
        if (r1 == 0) goto L_0x0013;	 Catch:{ RejectedExecutionException -> 0x0013 }
    L_0x000e:
        r4 = r1.schedule(r4, r5, r7);	 Catch:{ RejectedExecutionException -> 0x0013 }
        r0 = r4;
    L_0x0013:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.ExecutorCoroutineDispatcherBase.scheduleBlock(java.lang.Runnable, long, java.util.concurrent.TimeUnit):java.util.concurrent.ScheduledFuture<?>");
    }

    public void close() {
        Executor executor = getExecutor();
        if (!(executor instanceof ExecutorService)) {
            executor = null;
        }
        ExecutorService executorService = (ExecutorService) executor;
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    @NotNull
    public String toString() {
        return getExecutor().toString();
    }

    public boolean equals(@Nullable Object obj) {
        return ((obj instanceof ExecutorCoroutineDispatcherBase) && ((ExecutorCoroutineDispatcherBase) obj).getExecutor() == getExecutor()) ? true : null;
    }

    public int hashCode() {
        return System.identityHashCode(getExecutor());
    }
}
