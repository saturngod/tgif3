package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.selects.SelectClause0;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u0000 )2\u00020\u0001:\u0001)J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H'J\b\u0010\u0013\u001a\u00020\u0014H&J\u0014\u0010\u0013\u001a\u00020\u00072\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016H'J\r\u0010\u0017\u001a\u00020\u0007H\u0017¢\u0006\u0002\b\u0013J\f\u0010\u0018\u001a\u00060\u0019j\u0002`\u001aH'JE\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u00072'\u0010\u001f\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0016¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00140 j\u0002`#H'J1\u0010\u001b\u001a\u00020\u001c2'\u0010\u001f\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0016¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00140 j\u0002`#H&J\u0011\u0010$\u001a\u00020\u0014H¦@ø\u0001\u0000¢\u0006\u0002\u0010%J\u0011\u0010&\u001a\u00020\u00002\u0006\u0010'\u001a\u00020\u0000H\u0002J\b\u0010(\u001a\u00020\u0007H&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00000\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\bR\u0012\u0010\t\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\bR\u0012\u0010\n\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0012\u0010\u000b\u001a\u00020\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e\u0002\u0004\n\u0002\b\u0019¨\u0006*"}, d2 = {"Lkotlinx/coroutines/Job;", "Lkotlin/coroutines/CoroutineContext$Element;", "children", "Lkotlin/sequences/Sequence;", "getChildren", "()Lkotlin/sequences/Sequence;", "isActive", "", "()Z", "isCancelled", "isCompleted", "onJoin", "Lkotlinx/coroutines/selects/SelectClause0;", "getOnJoin", "()Lkotlinx/coroutines/selects/SelectClause0;", "attachChild", "Lkotlinx/coroutines/ChildHandle;", "child", "Lkotlinx/coroutines/ChildJob;", "cancel", "", "cause", "", "cancel0", "getCancellationException", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "invokeOnCompletion", "Lkotlinx/coroutines/DisposableHandle;", "onCancelling", "invokeImmediately", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "join", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "plus", "other", "start", "Key", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Job.kt */
public interface Job extends Element {
    public static final Key Key = Key.$$INSTANCE;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* compiled from: Job.kt */
    public static final class DefaultImpls {
        public static <R> R fold(Job job, R r, @NotNull Function2<? super R, ? super Element, ? extends R> function2) {
            Intrinsics.checkParameterIsNotNull(function2, "operation");
            return kotlin.coroutines.CoroutineContext.Element.DefaultImpls.fold(job, r, function2);
        }

        @Nullable
        public static <E extends Element> E get(Job job, @NotNull kotlin.coroutines.CoroutineContext.Key<E> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            return kotlin.coroutines.CoroutineContext.Element.DefaultImpls.get(job, key);
        }

        @NotNull
        public static CoroutineContext minusKey(Job job, @NotNull kotlin.coroutines.CoroutineContext.Key<?> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            return kotlin.coroutines.CoroutineContext.Element.DefaultImpls.minusKey(job, key);
        }

        @NotNull
        public static CoroutineContext plus(Job job, @NotNull CoroutineContext coroutineContext) {
            Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
            return kotlin.coroutines.CoroutineContext.Element.DefaultImpls.plus(job, coroutineContext);
        }

        @NotNull
        @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
        public static Job plus(Job job, @NotNull Job job2) {
            Intrinsics.checkParameterIsNotNull(job2, "other");
            return job2;
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Use CompletableDeferred.completeExceptionally(cause) or Job.cancel() instead", replaceWith = @ReplaceWith(expression = "cancel()", imports = {}))
        @ObsoleteCoroutinesApi
        public static /* synthetic */ boolean cancel$default(Job job, Throwable th, int i, Object obj) {
            if (obj == null) {
                if ((i & 1) != 0) {
                    th = null;
                }
                return job.cancel(th);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancel");
        }

        @NotNull
        @InternalCoroutinesApi
        public static /* synthetic */ DisposableHandle invokeOnCompletion$default(Job job, boolean z, boolean z2, Function1 function1, int i, Object obj) {
            if (obj == null) {
                if ((i & 1) != null) {
                    z = false;
                }
                if ((i & 2) != 0) {
                    z2 = true;
                }
                return job.invokeOnCompletion(z, z2, function1);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: invokeOnCompletion");
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lkotlinx/coroutines/Job$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlinx/coroutines/Job;", "()V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Job.kt */
    public static final class Key implements kotlin.coroutines.CoroutineContext.Key<Job> {
        static final /* synthetic */ Key $$INSTANCE = new Key();

        static {
            kotlinx.coroutines.CoroutineExceptionHandler.Key key = CoroutineExceptionHandler.Key;
        }

        private Key() {
        }
    }

    @NotNull
    @InternalCoroutinesApi
    ChildHandle attachChild(@NotNull ChildJob childJob);

    void cancel();

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Left here for binary compatibility")
    @JvmName(name = "cancel")
    /* renamed from: cancel */
    /* synthetic */ boolean m18cancel();

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use CompletableDeferred.completeExceptionally(cause) or Job.cancel() instead", replaceWith = @ReplaceWith(expression = "cancel()", imports = {}))
    @ObsoleteCoroutinesApi
    boolean cancel(@Nullable Throwable th);

    @NotNull
    @InternalCoroutinesApi
    CancellationException getCancellationException();

    @NotNull
    Sequence<Job> getChildren();

    @NotNull
    SelectClause0 getOnJoin();

    @NotNull
    DisposableHandle invokeOnCompletion(@NotNull Function1<? super Throwable, Unit> function1);

    @NotNull
    @InternalCoroutinesApi
    DisposableHandle invokeOnCompletion(boolean z, boolean z2, @NotNull Function1<? super Throwable, Unit> function1);

    boolean isActive();

    boolean isCancelled();

    boolean isCompleted();

    @Nullable
    Object join(@NotNull Continuation<? super Unit> continuation);

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
    Job plus(@NotNull Job job);

    boolean start();
}
