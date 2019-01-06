package kotlinx.coroutines;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0011\u001a\u0004\u0018\u0001H\u0012\"\u0004\b\u0000\u0010\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0014H\b¢\u0006\u0002\u0010\u0015J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0019H\u0002J\u001c\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001d2\n\u0010\u0013\u001a\u00060\u001ej\u0002`\u001fH\u0016J\b\u0010 \u001a\u00020\u0006H\u0002J!\u0010!\u001a\u00020\u00102\n\u0010\"\u001a\u0006\u0012\u0002\b\u00030#2\u0006\u0010\u0005\u001a\u00020\u0019H\u0000¢\u0006\u0002\b$J\r\u0010%\u001a\u00020\u0017H\u0000¢\u0006\u0002\b&J\u0015\u0010'\u001a\u00020\u00172\u0006\u0010(\u001a\u00020)H\u0000¢\u0006\u0002\b*J\b\u0010+\u001a\u00020\u0004H\u0016J\r\u0010\u000f\u001a\u00020\u0017H\u0000¢\u0006\u0002\b,R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8BX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lkotlinx/coroutines/CommonPool;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "()V", "DEFAULT_PARALLELISM_PROPERTY_NAME", "", "executor", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "parallelism", "", "getParallelism", "()I", "pool", "requestedParallelism", "usePrivatePool", "", "Try", "T", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "close", "", "createPlainPool", "Ljava/util/concurrent/ExecutorService;", "createPool", "dispatch", "context", "Lkotlin/coroutines/CoroutineContext;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "getOrCreatePoolSync", "isGoodCommonPool", "fjpClass", "Ljava/lang/Class;", "isGoodCommonPool$kotlinx_coroutines_core", "restore", "restore$kotlinx_coroutines_core", "shutdown", "timeout", "", "shutdown$kotlinx_coroutines_core", "toString", "usePrivatePool$kotlinx_coroutines_core", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: CommonPool.kt */
public final class CommonPool extends ExecutorCoroutineDispatcher {
    @NotNull
    public static final String DEFAULT_PARALLELISM_PROPERTY_NAME = "kotlinx.coroutines.default.parallelism";
    public static final CommonPool INSTANCE = new CommonPool();
    private static volatile Executor pool;
    private static final int requestedParallelism;
    private static boolean usePrivatePool;

    @NotNull
    public String toString() {
        return "CommonPool";
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
        r0 = new kotlinx.coroutines.CommonPool;
        r0.<init>();
        INSTANCE = r0;
        r0 = INSTANCE;
        r0 = "kotlinx.coroutines.default.parallelism";	 Catch:{ Throwable -> 0x0010 }
        r0 = java.lang.System.getProperty(r0);	 Catch:{ Throwable -> 0x0010 }
        goto L_0x0011;
    L_0x0010:
        r0 = 0;
    L_0x0011:
        if (r0 == 0) goto L_0x0042;
    L_0x0013:
        r1 = kotlin.text.StringsKt__StringNumberConversionsKt.toIntOrNull(r0);
        if (r1 == 0) goto L_0x0025;
    L_0x0019:
        r2 = r1.intValue();
        r3 = 1;
        if (r2 < r3) goto L_0x0025;
    L_0x0020:
        r0 = r1.intValue();
        goto L_0x0043;
    L_0x0025:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Expected positive number in kotlinx.coroutines.default.parallelism, but has ";
        r1.append(r2);
        r1.append(r0);
        r0 = r1.toString();
        r1 = new java.lang.IllegalStateException;
        r0 = r0.toString();
        r1.<init>(r0);
        r1 = (java.lang.Throwable) r1;
        throw r1;
    L_0x0042:
        r0 = -1;
    L_0x0043:
        requestedParallelism = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.CommonPool.<clinit>():void");
    }

    private CommonPool() {
    }

    @NotNull
    public Executor getExecutor() {
        Executor executor = pool;
        return executor != null ? executor : getOrCreatePoolSync();
    }

    private final int getParallelism() {
        Integer valueOf = Integer.valueOf(requestedParallelism);
        if ((((Number) valueOf).intValue() > 0 ? 1 : null) == null) {
            valueOf = null;
        }
        if (valueOf != null) {
            return valueOf.intValue();
        }
        return RangesKt___RangesKt.coerceAtLeast(Runtime.getRuntime().availableProcessors() - 1, 1);
    }

    private final <T> T Try(kotlin.jvm.functions.Function0<? extends T> r1) {
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
        r0 = this;
        r1 = r1.invoke();	 Catch:{ Throwable -> 0x0005 }
        goto L_0x0006;
    L_0x0005:
        r1 = 0;
    L_0x0006:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.CommonPool.Try(kotlin.jvm.functions.Function0):T");
    }

    private final java.util.concurrent.ExecutorService createPool() {
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
        r6 = this;
        r0 = java.lang.System.getSecurityManager();
        if (r0 == 0) goto L_0x000b;
    L_0x0006:
        r0 = r6.createPlainPool();
        return r0;
    L_0x000b:
        r0 = 0;
        r1 = "java.util.concurrent.ForkJoinPool";	 Catch:{ Throwable -> 0x0013 }
        r1 = java.lang.Class.forName(r1);	 Catch:{ Throwable -> 0x0013 }
        goto L_0x0014;
    L_0x0013:
        r1 = r0;
    L_0x0014:
        if (r1 == 0) goto L_0x0076;
    L_0x0016:
        r2 = usePrivatePool;
        r3 = 0;
        if (r2 != 0) goto L_0x0049;
    L_0x001b:
        r2 = requestedParallelism;
        if (r2 >= 0) goto L_0x0049;
    L_0x001f:
        r2 = "commonPool";	 Catch:{ Throwable -> 0x0039 }
        r4 = new java.lang.Class[r3];	 Catch:{ Throwable -> 0x0039 }
        r2 = r1.getMethod(r2, r4);	 Catch:{ Throwable -> 0x0039 }
        if (r2 == 0) goto L_0x0030;	 Catch:{ Throwable -> 0x0039 }
    L_0x0029:
        r4 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0039 }
        r2 = r2.invoke(r0, r4);	 Catch:{ Throwable -> 0x0039 }
        goto L_0x0031;	 Catch:{ Throwable -> 0x0039 }
    L_0x0030:
        r2 = r0;	 Catch:{ Throwable -> 0x0039 }
    L_0x0031:
        r4 = r2 instanceof java.util.concurrent.ExecutorService;	 Catch:{ Throwable -> 0x0039 }
        if (r4 != 0) goto L_0x0036;	 Catch:{ Throwable -> 0x0039 }
    L_0x0035:
        r2 = r0;	 Catch:{ Throwable -> 0x0039 }
    L_0x0036:
        r2 = (java.util.concurrent.ExecutorService) r2;	 Catch:{ Throwable -> 0x0039 }
        goto L_0x003a;
    L_0x0039:
        r2 = r0;
    L_0x003a:
        if (r2 == 0) goto L_0x0049;
    L_0x003c:
        r4 = INSTANCE;
        r4 = r4.isGoodCommonPool$kotlinx_coroutines_core(r1, r2);
        if (r4 == 0) goto L_0x0045;
    L_0x0044:
        goto L_0x0046;
    L_0x0045:
        r2 = r0;
    L_0x0046:
        if (r2 == 0) goto L_0x0049;
    L_0x0048:
        return r2;
    L_0x0049:
        r2 = 1;
        r4 = new java.lang.Class[r2];	 Catch:{ Throwable -> 0x006e }
        r5 = java.lang.Integer.TYPE;	 Catch:{ Throwable -> 0x006e }
        r4[r3] = r5;	 Catch:{ Throwable -> 0x006e }
        r1 = r1.getConstructor(r4);	 Catch:{ Throwable -> 0x006e }
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x006e }
        r4 = INSTANCE;	 Catch:{ Throwable -> 0x006e }
        r4 = r4.getParallelism();	 Catch:{ Throwable -> 0x006e }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Throwable -> 0x006e }
        r2[r3] = r4;	 Catch:{ Throwable -> 0x006e }
        r1 = r1.newInstance(r2);	 Catch:{ Throwable -> 0x006e }
        r2 = r1 instanceof java.util.concurrent.ExecutorService;	 Catch:{ Throwable -> 0x006e }
        if (r2 != 0) goto L_0x006b;	 Catch:{ Throwable -> 0x006e }
    L_0x006a:
        r1 = r0;	 Catch:{ Throwable -> 0x006e }
    L_0x006b:
        r1 = (java.util.concurrent.ExecutorService) r1;	 Catch:{ Throwable -> 0x006e }
        r0 = r1;
    L_0x006e:
        if (r0 == 0) goto L_0x0071;
    L_0x0070:
        return r0;
    L_0x0071:
        r0 = r6.createPlainPool();
        return r0;
    L_0x0076:
        r0 = r6.createPlainPool();
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.CommonPool.createPool():java.util.concurrent.ExecutorService");
    }

    public final boolean isGoodCommonPool$kotlinx_coroutines_core(@org.jetbrains.annotations.NotNull java.lang.Class<?> r5, @org.jetbrains.annotations.NotNull java.util.concurrent.ExecutorService r6) {
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
        r4 = this;
        r0 = "fjpClass";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0);
        r0 = "executor";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0);
        r0 = kotlinx.coroutines.CommonPool$isGoodCommonPool$1.INSTANCE;
        r0 = (java.lang.Runnable) r0;
        r6.submit(r0);
        r0 = 0;
        r1 = 0;
        r2 = "getPoolSize";	 Catch:{ Throwable -> 0x0029 }
        r3 = new java.lang.Class[r1];	 Catch:{ Throwable -> 0x0029 }
        r5 = r5.getMethod(r2, r3);	 Catch:{ Throwable -> 0x0029 }
        r2 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0029 }
        r5 = r5.invoke(r6, r2);	 Catch:{ Throwable -> 0x0029 }
        r6 = r5 instanceof java.lang.Integer;	 Catch:{ Throwable -> 0x0029 }
        if (r6 != 0) goto L_0x0026;	 Catch:{ Throwable -> 0x0029 }
    L_0x0025:
        r5 = r0;	 Catch:{ Throwable -> 0x0029 }
    L_0x0026:
        r5 = (java.lang.Integer) r5;	 Catch:{ Throwable -> 0x0029 }
        goto L_0x002a;
    L_0x0029:
        r5 = r0;
    L_0x002a:
        if (r5 == 0) goto L_0x0036;
    L_0x002c:
        r5 = r5.intValue();
        r6 = 1;
        if (r5 < r6) goto L_0x0034;
    L_0x0033:
        goto L_0x0035;
    L_0x0034:
        r6 = 0;
    L_0x0035:
        return r6;
    L_0x0036:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.CommonPool.isGoodCommonPool$kotlinx_coroutines_core(java.lang.Class, java.util.concurrent.ExecutorService):boolean");
    }

    private final ExecutorService createPlainPool() {
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(getParallelism(), new CommonPool$createPlainPool$1(new AtomicInteger()));
        Intrinsics.checkExpressionValueIsNotNull(newFixedThreadPool, "Executors.newFixedThread…Daemon = true }\n        }");
        return newFixedThreadPool;
    }

    private final synchronized Executor getOrCreatePoolSync() {
        Executor executor;
        executor = pool;
        if (executor == null) {
            ExecutorService createPool = createPool();
            pool = createPool;
            executor = createPool;
        }
        return executor;
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
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
        r2 = pool;	 Catch:{ RejectedExecutionException -> 0x001f }
        if (r2 == 0) goto L_0x000f;	 Catch:{ RejectedExecutionException -> 0x001f }
    L_0x000e:
        goto L_0x0013;	 Catch:{ RejectedExecutionException -> 0x001f }
    L_0x000f:
        r2 = r1.getOrCreatePoolSync();	 Catch:{ RejectedExecutionException -> 0x001f }
    L_0x0013:
        r0 = kotlinx.coroutines.TimeSourceKt.getTimeSource();	 Catch:{ RejectedExecutionException -> 0x001f }
        r0 = r0.wrapTask(r3);	 Catch:{ RejectedExecutionException -> 0x001f }
        r2.execute(r0);	 Catch:{ RejectedExecutionException -> 0x001f }
        goto L_0x002b;
    L_0x001f:
        r2 = kotlinx.coroutines.TimeSourceKt.getTimeSource();
        r2.unTrackTask();
        r2 = kotlinx.coroutines.DefaultExecutor.INSTANCE;
        r2.execute$kotlinx_coroutines_core(r3);
    L_0x002b:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.CommonPool.dispatch(kotlin.coroutines.CoroutineContext, java.lang.Runnable):void");
    }

    public final synchronized void usePrivatePool$kotlinx_coroutines_core() {
        shutdown$kotlinx_coroutines_core(0);
        usePrivatePool = true;
        pool = (Executor) null;
    }

    public final synchronized void shutdown$kotlinx_coroutines_core(long j) {
        Executor executor = pool;
        if (!(executor instanceof ExecutorService)) {
            executor = null;
        }
        ExecutorService executorService = (ExecutorService) executor;
        if (executorService != null) {
            executorService.shutdown();
            if (j > 0) {
                executorService.awaitTermination(j, TimeUnit.MILLISECONDS);
            }
            j = executorService.shutdownNow();
            Intrinsics.checkExpressionValueIsNotNull(j, "shutdownNow()");
            for (Runnable runnable : (Iterable) j) {
                DefaultExecutor defaultExecutor = DefaultExecutor.INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(runnable, "it");
                defaultExecutor.execute$kotlinx_coroutines_core(runnable);
            }
        }
        pool = (Executor) CommonPool$shutdown$2.INSTANCE;
    }

    public final synchronized void restore$kotlinx_coroutines_core() {
        shutdown$kotlinx_coroutines_core(0);
        usePrivatePool = false;
        pool = (Executor) null;
    }

    public void close() {
        throw new IllegalStateException("Close cannot be invoked on CommonPool".toString());
    }
}
