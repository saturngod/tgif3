package kotlinx.coroutines;

import java.util.concurrent.Future;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"kotlinx/coroutines/JobKt__FutureKt", "kotlinx/coroutines/JobKt__JobKt"}, k = 4, mv = {1, 1, 13})
public final class JobKt {
    @NotNull
    @InternalCoroutinesApi
    public static final DisposableHandle DisposableHandle(@NotNull Function0<Unit> function0) {
        return JobKt__JobKt.DisposableHandle(function0);
    }

    @NotNull
    public static final Job Job(@Nullable Job job) {
        return JobKt__JobKt.Job(job);
    }

    public static final void cancel(@NotNull CoroutineContext coroutineContext) {
        JobKt__JobKt.cancel(coroutineContext);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use cancel() without cause", replaceWith = @ReplaceWith(expression = "cancel()", imports = {}))
    @ObsoleteCoroutinesApi
    public static final boolean cancel(@NotNull CoroutineContext coroutineContext, @Nullable Throwable th) {
        return JobKt__JobKt.cancel(coroutineContext, th);
    }

    @Nullable
    public static final Object cancelAndJoin(@NotNull Job job, @NotNull Continuation<? super Unit> continuation) {
        return JobKt__JobKt.cancelAndJoin(job, continuation);
    }

    public static final void cancelChildren(@NotNull CoroutineContext coroutineContext) {
        JobKt__JobKt.cancelChildren(coroutineContext);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use cancelChildren() without cause", replaceWith = @ReplaceWith(expression = "cancelChildren()", imports = {}))
    @ObsoleteCoroutinesApi
    public static final void cancelChildren(@NotNull CoroutineContext coroutineContext, @Nullable Throwable th) {
        JobKt__JobKt.cancelChildren(coroutineContext, th);
    }

    public static final void cancelChildren(@NotNull Job job) {
        JobKt__JobKt.cancelChildren(job);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use cancelChildren() without cause", replaceWith = @ReplaceWith(expression = "cancelChildren()", imports = {}))
    @ObsoleteCoroutinesApi
    public static final void cancelChildren(@NotNull Job job, @Nullable Throwable th) {
        JobKt__JobKt.cancelChildren(job, th);
    }

    public static final void cancelFutureOnCancellation(@NotNull CancellableContinuation<?> cancellableContinuation, @NotNull Future<?> future) {
        JobKt__FutureKt.cancelFutureOnCancellation(cancellableContinuation, future);
    }

    @NotNull
    @InternalCoroutinesApi
    public static final DisposableHandle cancelFutureOnCompletion(@NotNull Job job, @NotNull Future<?> future) {
        return JobKt__FutureKt.cancelFutureOnCompletion(job, future);
    }

    @NotNull
    public static final DisposableHandle disposeOnCompletion(@NotNull Job job, @NotNull DisposableHandle disposableHandle) {
        return JobKt__JobKt.disposeOnCompletion(job, disposableHandle);
    }

    public static final boolean isActive(@NotNull CoroutineContext coroutineContext) {
        return JobKt__JobKt.isActive(coroutineContext);
    }
}
