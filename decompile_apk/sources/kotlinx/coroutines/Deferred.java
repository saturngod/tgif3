package kotlinx.coroutines;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.CoroutineContext.Key;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.selects.SelectClause1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0000\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002J\u0011\u0010\u0007\u001a\u00028\u0000H¦@ø\u0001\u0000¢\u0006\u0002\u0010\bJ\r\u0010\t\u001a\u00028\u0000H'¢\u0006\u0002\u0010\nJ\n\u0010\u000b\u001a\u0004\u0018\u00010\fH'R\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/Deferred;", "T", "Lkotlinx/coroutines/Job;", "onAwait", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnAwait", "()Lkotlinx/coroutines/selects/SelectClause1;", "await", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCompleted", "()Ljava/lang/Object;", "getCompletionExceptionOrNull", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Deferred.kt */
public interface Deferred<T> extends Job {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* compiled from: Deferred.kt */
    public static final class DefaultImpls {
        public static <T, R> R fold(Deferred<? extends T> deferred, R r, @NotNull Function2<? super R, ? super Element, ? extends R> function2) {
            Intrinsics.checkParameterIsNotNull(function2, "operation");
            return kotlinx.coroutines.Job.DefaultImpls.fold(deferred, r, function2);
        }

        @Nullable
        public static <T, E extends Element> E get(Deferred<? extends T> deferred, @NotNull Key<E> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            return kotlinx.coroutines.Job.DefaultImpls.get(deferred, key);
        }

        @NotNull
        public static <T> CoroutineContext minusKey(Deferred<? extends T> deferred, @NotNull Key<?> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            return kotlinx.coroutines.Job.DefaultImpls.minusKey(deferred, key);
        }

        @NotNull
        public static <T> CoroutineContext plus(Deferred<? extends T> deferred, @NotNull CoroutineContext coroutineContext) {
            Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
            return kotlinx.coroutines.Job.DefaultImpls.plus((Job) deferred, coroutineContext);
        }

        @NotNull
        @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
        public static <T> Job plus(Deferred<? extends T> deferred, @NotNull Job job) {
            Intrinsics.checkParameterIsNotNull(job, "other");
            return kotlinx.coroutines.Job.DefaultImpls.plus((Job) deferred, job);
        }
    }

    @Nullable
    Object await(@NotNull Continuation<? super T> continuation);

    @ExperimentalCoroutinesApi
    T getCompleted();

    @Nullable
    @ExperimentalCoroutinesApi
    Throwable getCompletionExceptionOrNull();

    @NotNull
    SelectClause1<T> getOnAwait();
}
