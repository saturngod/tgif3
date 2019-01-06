package kotlinx.coroutines.android;

import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0011\u0010\u000b\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0002\u0010\f\u001a\u001e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\u0010H\u0002\u001a\u0016\u0010\u0011\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\u0010H\u0002\u001a\u001d\u0010\u0012\u001a\u00020\u0003*\u00020\b2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0007¢\u0006\u0002\b\u0015\u001a\u0014\u0010\u0016\u001a\u00020\b*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u00038\u0000X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000\"\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"MAX_DELAY", "", "Main", "Lkotlinx/coroutines/android/HandlerDispatcher;", "MainDispatcher", "choreographer", "Landroid/view/Choreographer;", "mainHandler", "Landroid/os/Handler;", "getMainHandler", "()Landroid/os/Handler;", "awaitFrame", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "postFrameCallback", "", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "updateChoreographerAndPostFrameCallback", "asCoroutineDispatcher", "name", "", "from", "asHandler", "Landroid/os/Looper;", "async", "", "kotlinx-coroutines-android"}, k = 2, mv = {1, 1, 13})
/* compiled from: HandlerDispatcher.kt */
public final class HandlerDispatcherKt {
    private static final long MAX_DELAY = 4611686018427387903L;
    @NotNull
    @JvmField
    public static final HandlerDispatcher Main = new HandlerContext(mainHandler, "Main");
    private static final HandlerDispatcher MainDispatcher = Main;
    private static volatile Choreographer choreographer;
    @NotNull
    private static final Handler mainHandler;

    @NotNull
    @JvmOverloads
    @JvmName(name = "from")
    public static final HandlerDispatcher from(@NotNull Handler handler) {
        return from$default(handler, null, 1, null);
    }

    @NotNull
    @JvmOverloads
    @JvmName(name = "from")
    public static /* synthetic */ HandlerDispatcher from$default(Handler handler, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        return from(handler, str);
    }

    @NotNull
    @JvmOverloads
    @JvmName(name = "from")
    public static final HandlerDispatcher from(@NotNull Handler handler, @Nullable String str) {
        Intrinsics.checkParameterIsNotNull(handler, "receiver$0");
        return new HandlerContext(handler, str);
    }

    static {
        Looper mainLooper = Looper.getMainLooper();
        Intrinsics.checkExpressionValueIsNotNull(mainLooper, "Looper.getMainLooper()");
        mainHandler = asHandler(mainLooper, true);
    }

    @NotNull
    public static final Handler getMainHandler() {
        return mainHandler;
    }

    @org.jetbrains.annotations.NotNull
    @android.support.annotation.VisibleForTesting
    public static final android.os.Handler asHandler(@org.jetbrains.annotations.NotNull android.os.Looper r7, boolean r8) {
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
        r0 = "receiver$0";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0);
        if (r8 == 0) goto L_0x0073;
    L_0x0007:
        r8 = android.os.Build.VERSION.SDK_INT;
        r0 = 16;
        if (r8 >= r0) goto L_0x000e;
    L_0x000d:
        goto L_0x0073;
    L_0x000e:
        r8 = android.os.Build.VERSION.SDK_INT;
        r0 = 28;
        r1 = 0;
        r2 = 0;
        r3 = 1;
        if (r8 < r0) goto L_0x003a;
    L_0x0017:
        r8 = android.os.Handler.class;
        r0 = "createAsync";
        r4 = new java.lang.Class[r3];
        r5 = android.os.Looper.class;
        r4[r2] = r5;
        r8 = r8.getDeclaredMethod(r0, r4);
        r0 = new java.lang.Object[r3];
        r0[r2] = r7;
        r7 = r8.invoke(r1, r0);
        if (r7 == 0) goto L_0x0032;
    L_0x002f:
        r7 = (android.os.Handler) r7;
        return r7;
    L_0x0032:
        r7 = new kotlin.TypeCastException;
        r8 = "null cannot be cast to non-null type android.os.Handler";
        r7.<init>(r8);
        throw r7;
    L_0x003a:
        r8 = android.os.Handler.class;	 Catch:{ NoSuchMethodException -> 0x006d }
        r0 = 3;	 Catch:{ NoSuchMethodException -> 0x006d }
        r4 = new java.lang.Class[r0];	 Catch:{ NoSuchMethodException -> 0x006d }
        r5 = android.os.Looper.class;	 Catch:{ NoSuchMethodException -> 0x006d }
        r4[r2] = r5;	 Catch:{ NoSuchMethodException -> 0x006d }
        r5 = android.os.Handler.Callback.class;	 Catch:{ NoSuchMethodException -> 0x006d }
        r4[r3] = r5;	 Catch:{ NoSuchMethodException -> 0x006d }
        r5 = java.lang.Boolean.TYPE;	 Catch:{ NoSuchMethodException -> 0x006d }
        r6 = 2;	 Catch:{ NoSuchMethodException -> 0x006d }
        r4[r6] = r5;	 Catch:{ NoSuchMethodException -> 0x006d }
        r8 = r8.getDeclaredConstructor(r4);	 Catch:{ NoSuchMethodException -> 0x006d }
        r4 = "Handler::class.java.getD…:class.javaPrimitiveType)";	 Catch:{ NoSuchMethodException -> 0x006d }
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r4);	 Catch:{ NoSuchMethodException -> 0x006d }
        r0 = new java.lang.Object[r0];
        r0[r2] = r7;
        r0[r3] = r1;
        r7 = java.lang.Boolean.valueOf(r3);
        r0[r6] = r7;
        r7 = r8.newInstance(r0);
        r8 = "constructor.newInstance(this, null, true)";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r8);
        r7 = (android.os.Handler) r7;
        return r7;
    L_0x006d:
        r8 = new android.os.Handler;
        r8.<init>(r7);
        return r8;
    L_0x0073:
        r8 = new android.os.Handler;
        r8.<init>(r7);
        return r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.android.HandlerDispatcherKt.asHandler(android.os.Looper, boolean):android.os.Handler");
    }

    @Nullable
    public static final Object awaitFrame(@NotNull Continuation<? super Long> continuation) {
        Choreographer choreographer = choreographer;
        Object result;
        if (choreographer != null) {
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
            cancellableContinuationImpl.initCancellability();
            postFrameCallback(choreographer, cancellableContinuationImpl);
            result = cancellableContinuationImpl.getResult();
            if (result == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return result;
        }
        CancellableContinuationImpl cancellableContinuationImpl2 = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl2.initCancellability();
        getMainHandler().post(new HandlerDispatcherKt$awaitFrame$3$1(cancellableContinuationImpl2));
        result = cancellableContinuationImpl2.getResult();
        if (result == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    private static final void updateChoreographerAndPostFrameCallback(CancellableContinuation<? super Long> cancellableContinuation) {
        Choreographer choreographer = choreographer;
        if (choreographer == null) {
            choreographer = Choreographer.getInstance();
            if (choreographer == null) {
                Intrinsics.throwNpe();
            }
            choreographer = choreographer;
        }
        postFrameCallback(choreographer, cancellableContinuation);
    }

    private static final void postFrameCallback(Choreographer choreographer, CancellableContinuation<? super Long> cancellableContinuation) {
        choreographer.postFrameCallback(new HandlerDispatcherKt$postFrameCallback$1(cancellableContinuation));
    }
}
