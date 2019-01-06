package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.scheduling.DefaultScheduler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\r\u001a\u00020\u000eH\u0000\u001a\b\u0010\u000f\u001a\u00020\u0010H\u0000\u001a4\u0010\u0011\u001a\u0002H\u0012\"\u0004\b\u0000\u0010\u00122\u0006\u0010\u0013\u001a\u00020\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0017H\b¢\u0006\u0002\u0010\u0018\u001a\u0014\u0010\u0019\u001a\u00020\n*\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\nH\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u001a\u0010\t\u001a\u0004\u0018\u00010\u0001*\u00020\n8@X\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u001b"}, d2 = {"COROUTINES_SCHEDULER_PROPERTY_NAME", "", "COROUTINE_ID", "Ljava/util/concurrent/atomic/AtomicLong;", "DEBUG_THREAD_NAME_SEPARATOR", "useCoroutinesScheduler", "", "getUseCoroutinesScheduler", "()Z", "coroutineName", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineName", "(Lkotlin/coroutines/CoroutineContext;)Ljava/lang/String;", "createDefaultDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "resetCoroutineId", "", "withCoroutineContext", "T", "context", "countOrElement", "", "block", "Lkotlin/Function0;", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "newCoroutineContext", "Lkotlinx/coroutines/CoroutineScope;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: CoroutineContext.kt */
public final class CoroutineContextKt {
    @NotNull
    public static final String COROUTINES_SCHEDULER_PROPERTY_NAME = "kotlinx.coroutines.scheduler";
    private static final AtomicLong COROUTINE_ID = new AtomicLong();
    private static final String DEBUG_THREAD_NAME_SEPARATOR = " @";
    private static final boolean useCoroutinesScheduler;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = new java.util.concurrent.atomic.AtomicLong;
        r0.<init>();
        COROUTINE_ID = r0;
        r0 = "kotlinx.coroutines.scheduler";
        r0 = kotlinx.coroutines.internal.SystemPropsKt.systemProp(r0);
        if (r0 != 0) goto L_0x0010;
    L_0x000f:
        goto L_0x003a;
    L_0x0010:
        r1 = r0.hashCode();
        if (r1 == 0) goto L_0x0032;
    L_0x0016:
        r2 = 3551; // 0xddf float:4.976E-42 double:1.7544E-320;
        if (r1 == r2) goto L_0x0029;
    L_0x001a:
        r2 = 109935; // 0x1ad6f float:1.54052E-40 double:5.4315E-319;
        if (r1 != r2) goto L_0x003e;
    L_0x001f:
        r1 = "off";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x003e;
    L_0x0027:
        r0 = 0;
        goto L_0x003b;
    L_0x0029:
        r1 = "on";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x003e;
    L_0x0031:
        goto L_0x003a;
    L_0x0032:
        r1 = "";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x003e;
    L_0x003a:
        r0 = 1;
    L_0x003b:
        useCoroutinesScheduler = r0;
        return;
    L_0x003e:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "System property 'kotlinx.coroutines.scheduler' has unrecognized value '";
        r1.append(r2);
        r1.append(r0);
        r0 = 39;
        r1.append(r0);
        r0 = r1.toString();
        r1 = new java.lang.IllegalStateException;
        r0 = r0.toString();
        r1.<init>(r0);
        r1 = (java.lang.Throwable) r1;
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.CoroutineContextKt.<clinit>():void");
    }

    public static final void resetCoroutineId() {
        COROUTINE_ID.set(0);
    }

    public static final boolean getUseCoroutinesScheduler() {
        return useCoroutinesScheduler;
    }

    @NotNull
    public static final CoroutineDispatcher createDefaultDispatcher() {
        return useCoroutinesScheduler ? DefaultScheduler.INSTANCE : CommonPool.INSTANCE;
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final CoroutineContext newCoroutineContext(@NotNull CoroutineScope coroutineScope, @NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(coroutineScope, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        coroutineScope = coroutineScope.getCoroutineContext().plus(coroutineContext);
        coroutineContext = DebugKt.getDEBUG() != null ? coroutineScope.plus(new CoroutineId(COROUTINE_ID.incrementAndGet())) : coroutineScope;
        return (coroutineScope == Dispatchers.getDefault() || coroutineScope.get(ContinuationInterceptor.Key) != null) ? coroutineContext : coroutineContext.plus(Dispatchers.getDefault());
    }

    public static final <T> T withCoroutineContext(@NotNull CoroutineContext coroutineContext, @Nullable Object obj, @NotNull Function0<? extends T> function0) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function0, "block");
        obj = ThreadContextKt.updateThreadContext(coroutineContext, obj);
        try {
            function0 = function0.invoke();
            return function0;
        } finally {
            InlineMarker.finallyStart(1);
            ThreadContextKt.restoreThreadContext(coroutineContext, obj);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    public static final String getCoroutineName(@NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "receiver$0");
        if (!DebugKt.getDEBUG()) {
            return null;
        }
        CoroutineId coroutineId = (CoroutineId) coroutineContext.get(CoroutineId.Key);
        if (coroutineId == null) {
            return null;
        }
        StringBuilder stringBuilder;
        CoroutineName coroutineName = (CoroutineName) coroutineContext.get(CoroutineName.Key);
        if (coroutineName != null) {
            coroutineContext = coroutineName.getName();
            if (coroutineContext != null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(coroutineContext);
                stringBuilder.append('#');
                stringBuilder.append(coroutineId.getId());
                return stringBuilder.toString();
            }
        }
        coroutineContext = "coroutine";
        stringBuilder = new StringBuilder();
        stringBuilder.append(coroutineContext);
        stringBuilder.append('#');
        stringBuilder.append(coroutineId.getId());
        return stringBuilder.toString();
    }
}
