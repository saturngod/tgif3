package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Result.Companion;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.internal.ThreadContextKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b`\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00060\u0002j\u0002`\u0003J\u0014\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u001d\u0010\u0010\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016¢\u0006\u0002\u0010\u0011J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\n\u0010\u0014\u001a\u0004\u0018\u00010\u000fH&R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lkotlinx/coroutines/DispatchedTask;", "T", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "delegate", "Lkotlin/coroutines/Continuation;", "getDelegate", "()Lkotlin/coroutines/Continuation;", "resumeMode", "", "getResumeMode", "()I", "getExceptionalResult", "", "state", "", "getSuccessfulResult", "(Ljava/lang/Object;)Ljava/lang/Object;", "run", "", "takeState", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Dispatched.kt */
public interface DispatchedTask<T> extends Runnable {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* compiled from: Dispatched.kt */
    public static final class DefaultImpls {
        public static <T> int getResumeMode(DispatchedTask<? super T> dispatchedTask) {
            return 1;
        }

        public static <T_I1, T> T getSuccessfulResult(DispatchedTask<? super T_I1> dispatchedTask, @Nullable Object obj) {
            return obj;
        }

        @Nullable
        public static <T> Throwable getExceptionalResult(DispatchedTask<? super T> dispatchedTask, @Nullable Object obj) {
            if ((obj instanceof CompletedExceptionally) == null) {
                obj = null;
            }
            CompletedExceptionally completedExceptionally = (CompletedExceptionally) obj;
            return completedExceptionally != null ? completedExceptionally.cause : null;
        }

        public static <T> void run(DispatchedTask<? super T> dispatchedTask) {
            CoroutineContext context;
            Object updateThreadContext;
            try {
                Continuation delegate = dispatchedTask.getDelegate();
                if (delegate != null) {
                    Throwable cancellationException;
                    Companion companion;
                    Unit unit;
                    DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) delegate;
                    Continuation continuation = dispatchedContinuation.continuation;
                    context = continuation.getContext();
                    Job job = ResumeModeKt.isCancellableMode(dispatchedTask.getResumeMode()) ? (Job) context.get(Job.Key) : null;
                    Object takeState = dispatchedTask.takeState();
                    updateThreadContext = ThreadContextKt.updateThreadContext(context, dispatchedContinuation.countOrElement);
                    if (job != null) {
                        if (!job.isActive()) {
                            cancellationException = job.getCancellationException();
                            companion = Result.Companion;
                            continuation.resumeWith(Result.constructor-impl(ResultKt.createFailure(cancellationException)));
                            unit = Unit.INSTANCE;
                            ThreadContextKt.restoreThreadContext(context, updateThreadContext);
                            return;
                        }
                    }
                    cancellationException = dispatchedTask.getExceptionalResult(takeState);
                    if (cancellationException != null) {
                        companion = Result.Companion;
                        continuation.resumeWith(Result.constructor-impl(ResultKt.createFailure(cancellationException)));
                    } else {
                        Object successfulResult = dispatchedTask.getSuccessfulResult(takeState);
                        companion = Result.Companion;
                        continuation.resumeWith(Result.constructor-impl(successfulResult));
                    }
                    unit = Unit.INSTANCE;
                    ThreadContextKt.restoreThreadContext(context, updateThreadContext);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.DispatchedContinuation<T>");
            } catch (Throwable th) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unexpected exception running ");
                stringBuilder.append(dispatchedTask);
                Throwable dispatchException = new DispatchException(stringBuilder.toString(), th);
            }
        }
    }

    @NotNull
    Continuation<T> getDelegate();

    @Nullable
    Throwable getExceptionalResult(@Nullable Object obj);

    int getResumeMode();

    <T> T getSuccessfulResult(@Nullable Object obj);

    void run();

    @Nullable
    Object takeState();
}
