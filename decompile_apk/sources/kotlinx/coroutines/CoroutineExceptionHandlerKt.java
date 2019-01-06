package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a%\u0010\u0000\u001a\u00020\u00012\u001a\b\u0004\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003H\b\u001a$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007\u001a\u0018\u0010\f\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0005H\u0000\u001a\u0018\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0000¨\u0006\u0010"}, d2 = {"CoroutineExceptionHandler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "handler", "Lkotlin/Function2;", "Lkotlin/coroutines/CoroutineContext;", "", "", "handleCoroutineException", "context", "exception", "caller", "Lkotlinx/coroutines/Job;", "handleExceptionViaHandler", "handlerException", "originalException", "thrownException", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: CoroutineExceptionHandler.kt */
public final class CoroutineExceptionHandlerKt {
    @InternalCoroutinesApi
    public static /* synthetic */ void handleCoroutineException$default(CoroutineContext coroutineContext, Throwable th, Job job, int i, Object obj) {
        if ((i & 4) != 0) {
            job = null;
        }
        handleCoroutineException(coroutineContext, th, job);
    }

    @InternalCoroutinesApi
    public static final void handleCoroutineException(@NotNull CoroutineContext coroutineContext, @NotNull Throwable th, @Nullable Job job) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(th, "exception");
        if (!(th instanceof CancellationException)) {
            Job job2 = (Job) coroutineContext.get(Job.Key);
            if (job2 == null || job2 == job || job2.cancel(th) == null) {
                handleExceptionViaHandler(coroutineContext, th);
            }
        }
    }

    public static final void handleExceptionViaHandler(@NotNull CoroutineContext coroutineContext, @NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(th, "exception");
        try {
            CoroutineExceptionHandler coroutineExceptionHandler = (CoroutineExceptionHandler) coroutineContext.get(CoroutineExceptionHandler.Key);
            if (coroutineExceptionHandler != null) {
                coroutineExceptionHandler.handleException(coroutineContext, th);
            } else {
                CoroutineExceptionHandlerImplKt.handleCoroutineExceptionImpl(coroutineContext, th);
            }
        } catch (Throwable th2) {
            CoroutineExceptionHandlerImplKt.handleCoroutineExceptionImpl(coroutineContext, handlerException(th, th2));
        }
    }

    @NotNull
    public static final Throwable handlerException(@NotNull Throwable th, @NotNull Throwable th2) {
        Intrinsics.checkParameterIsNotNull(th, "originalException");
        Intrinsics.checkParameterIsNotNull(th2, "thrownException");
        if (th == th2) {
            return th;
        }
        Throwable runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
        ExceptionsKt__ExceptionsKt.addSuppressed(runtimeException, th);
        return runtimeException;
    }

    @NotNull
    public static final CoroutineExceptionHandler CoroutineExceptionHandler(@NotNull Function2<? super CoroutineContext, ? super Throwable, Unit> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "handler");
        return new CoroutineExceptionHandlerKt$CoroutineExceptionHandler$1(function2, CoroutineExceptionHandler.Key);
    }
}
