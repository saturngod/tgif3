package kotlinx.coroutines;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.CoroutineContext.Key;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/ChildJob;", "Lkotlinx/coroutines/Job;", "parentCancelled", "", "parentJob", "Lkotlinx/coroutines/ParentJob;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
@Deprecated(level = DeprecationLevel.ERROR, message = "This is internal API and may be removed in the future releases")
@InternalCoroutinesApi
/* compiled from: Job.kt */
public interface ChildJob extends Job {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* compiled from: Job.kt */
    public static final class DefaultImpls {
        public static <R> R fold(ChildJob childJob, R r, @NotNull Function2<? super R, ? super Element, ? extends R> function2) {
            Intrinsics.checkParameterIsNotNull(function2, "operation");
            return kotlinx.coroutines.Job.DefaultImpls.fold(childJob, r, function2);
        }

        @Nullable
        public static <E extends Element> E get(ChildJob childJob, @NotNull Key<E> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            return kotlinx.coroutines.Job.DefaultImpls.get(childJob, key);
        }

        @NotNull
        public static CoroutineContext minusKey(ChildJob childJob, @NotNull Key<?> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            return kotlinx.coroutines.Job.DefaultImpls.minusKey(childJob, key);
        }

        @NotNull
        public static CoroutineContext plus(ChildJob childJob, @NotNull CoroutineContext coroutineContext) {
            Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
            return kotlinx.coroutines.Job.DefaultImpls.plus((Job) childJob, coroutineContext);
        }

        @NotNull
        @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
        public static Job plus(ChildJob childJob, @NotNull Job job) {
            Intrinsics.checkParameterIsNotNull(job, "other");
            return kotlinx.coroutines.Job.DefaultImpls.plus((Job) childJob, job);
        }
    }

    @InternalCoroutinesApi
    void parentCancelled(@NotNull ParentJob parentJob);
}
