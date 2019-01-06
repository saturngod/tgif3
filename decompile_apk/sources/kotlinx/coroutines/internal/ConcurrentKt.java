package kotlinx.coroutines.internal;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000\u001a\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0000\u001a\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u0002H\u00040\fj\b\u0012\u0004\u0012\u0002H\u0004`\r\"\u0004\b\u0000\u0010\u0004H\u0000\u001a*\u0010\u000e\u001a\u0002H\u000f\"\u0004\b\u0000\u0010\u000f*\u00060\u0010j\u0002`\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0013H\b¢\u0006\u0002\u0010\u0014\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0004¢\u0006\u0002\n\u0000*\f\b\u0000\u0010\u0015\"\u00020\u00102\u00020\u0010¨\u0006\u0016"}, d2 = {"REMOVE_FUTURE_ON_CANCEL", "Ljava/lang/reflect/Method;", "identitySet", "", "E", "expectedSize", "", "removeFutureOnCancel", "", "executor", "Ljava/util/concurrent/Executor;", "subscriberList", "", "Lkotlinx/coroutines/internal/SubscribersList;", "withLock", "T", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/internal/ReentrantLock;", "action", "Lkotlin/Function0;", "(Ljava/util/concurrent/locks/ReentrantLock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "ReentrantLock", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: Concurrent.kt */
public final class ConcurrentKt {
    private static final Method REMOVE_FUTURE_ON_CANCEL;

    public static /* synthetic */ void ReentrantLock$annotations() {
    }

    @NotNull
    public static final <E> List<E> subscriberList() {
        return new CopyOnWriteArrayList();
    }

    public static final <T> T withLock(@NotNull ReentrantLock reentrantLock, @NotNull Function0<? extends T> function0) {
        Intrinsics.checkParameterIsNotNull(reentrantLock, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function0, "action");
        Lock lock = reentrantLock;
        lock.lock();
        try {
            function0 = function0.invoke();
            return function0;
        } finally {
            InlineMarker.finallyStart(1);
            lock.unlock();
            InlineMarker.finallyEnd(1);
        }
    }

    @NotNull
    public static final <E> Set<E> identitySet(int i) {
        i = Collections.newSetFromMap(new IdentityHashMap(i));
        Intrinsics.checkExpressionValueIsNotNull(i, "Collections.newSetFromMa…ityHashMap(expectedSize))");
        return i;
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = java.util.concurrent.ScheduledThreadPoolExecutor.class;	 Catch:{ Throwable -> 0x0011 }
        r1 = "setRemoveOnCancelPolicy";	 Catch:{ Throwable -> 0x0011 }
        r2 = 1;	 Catch:{ Throwable -> 0x0011 }
        r2 = new java.lang.Class[r2];	 Catch:{ Throwable -> 0x0011 }
        r3 = 0;	 Catch:{ Throwable -> 0x0011 }
        r4 = java.lang.Boolean.TYPE;	 Catch:{ Throwable -> 0x0011 }
        r2[r3] = r4;	 Catch:{ Throwable -> 0x0011 }
        r0 = r0.getMethod(r1, r2);	 Catch:{ Throwable -> 0x0011 }
        goto L_0x0012;
    L_0x0011:
        r0 = 0;
    L_0x0012:
        REMOVE_FUTURE_ON_CANCEL = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.ConcurrentKt.<clinit>():void");
    }

    public static final boolean removeFutureOnCancel(@org.jetbrains.annotations.NotNull java.util.concurrent.Executor r5) {
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
        r0 = "executor";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0);
        r0 = 1;
        r1 = r5 instanceof java.util.concurrent.ScheduledExecutorService;	 Catch:{ Throwable -> 0x0022 }
        if (r1 != 0) goto L_0x000b;	 Catch:{ Throwable -> 0x0022 }
    L_0x000a:
        r5 = 0;	 Catch:{ Throwable -> 0x0022 }
    L_0x000b:
        r5 = (java.util.concurrent.ScheduledExecutorService) r5;	 Catch:{ Throwable -> 0x0022 }
        r1 = 0;	 Catch:{ Throwable -> 0x0022 }
        if (r5 == 0) goto L_0x0021;	 Catch:{ Throwable -> 0x0022 }
    L_0x0010:
        r2 = REMOVE_FUTURE_ON_CANCEL;	 Catch:{ Throwable -> 0x0022 }
        if (r2 == 0) goto L_0x0020;	 Catch:{ Throwable -> 0x0022 }
    L_0x0014:
        r3 = new java.lang.Object[r0];	 Catch:{ Throwable -> 0x0022 }
        r4 = java.lang.Boolean.valueOf(r0);	 Catch:{ Throwable -> 0x0022 }
        r3[r1] = r4;	 Catch:{ Throwable -> 0x0022 }
        r2.invoke(r5, r3);	 Catch:{ Throwable -> 0x0022 }
        return r0;
    L_0x0020:
        return r1;
    L_0x0021:
        return r1;
    L_0x0022:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.ConcurrentKt.removeFutureOnCancel(java.util.concurrent.Executor):boolean");
    }
}
