package kotlin.coroutines.experimental.migration;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007\u001a\f\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0007\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\tH\u0007\u001a\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0007\u001a\f\u0010\u000b\u001a\u00020\u0006*\u00020\u0005H\u0007\u001a\f\u0010\f\u001a\u00020\t*\u00020\bH\u0007\u001a^\u0010\r\u001a\"\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000e\"\u0004\b\u0000\u0010\u000f\"\u0004\b\u0001\u0010\u0010\"\u0004\b\u0002\u0010\u0011*\"\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000eH\u0000\u001aL\u0010\r\u001a\u001c\u0012\u0004\u0012\u0002H\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0013\"\u0004\b\u0000\u0010\u000f\"\u0004\b\u0001\u0010\u0011*\u001c\u0012\u0004\u0012\u0002H\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0013H\u0000\u001a:\u0010\r\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0014\"\u0004\b\u0000\u0010\u0011*\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0014H\u0000¨\u0006\u0015"}, d2 = {"toContinuation", "Lkotlin/coroutines/Continuation;", "T", "Lkotlin/coroutines/experimental/Continuation;", "toContinuationInterceptor", "Lkotlin/coroutines/ContinuationInterceptor;", "Lkotlin/coroutines/experimental/ContinuationInterceptor;", "toCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "Lkotlin/coroutines/experimental/CoroutineContext;", "toExperimentalContinuation", "toExperimentalContinuationInterceptor", "toExperimentalCoroutineContext", "toExperimentalSuspendFunction", "Lkotlin/Function3;", "T1", "T2", "R", "", "Lkotlin/Function2;", "Lkotlin/Function1;", "kotlin-stdlib_coroutines"}, k = 2, mv = {1, 1, 13})
/* compiled from: CoroutinesMigration.kt */
public final class CoroutinesMigrationKt {
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final <T> Continuation<T> toExperimentalContinuation(@NotNull kotlin.coroutines.Continuation<? super T> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "receiver$0");
        ContinuationMigration continuationMigration = (ContinuationMigration) (!(continuation instanceof ContinuationMigration) ? null : continuation);
        if (continuationMigration != null) {
            Continuation<T> continuation2 = continuationMigration.getContinuation();
            if (continuation2 != null) {
                return continuation2;
            }
        }
        return new ExperimentalContinuationMigration(continuation);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final <T> kotlin.coroutines.Continuation<T> toContinuation(@NotNull Continuation<? super T> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "receiver$0");
        ExperimentalContinuationMigration experimentalContinuationMigration = (ExperimentalContinuationMigration) (!(continuation instanceof ExperimentalContinuationMigration) ? null : continuation);
        if (experimentalContinuationMigration != null) {
            kotlin.coroutines.Continuation<T> continuation2 = experimentalContinuationMigration.getContinuation();
            if (continuation2 != null) {
                return continuation2;
            }
        }
        return new ContinuationMigration(continuation);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final CoroutineContext toExperimentalCoroutineContext(@NotNull kotlin.coroutines.CoroutineContext coroutineContext) {
        CoroutineContext context;
        Intrinsics.checkParameterIsNotNull(coroutineContext, "receiver$0");
        ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) coroutineContext.get(ContinuationInterceptor.Key);
        ContextMigration contextMigration = (ContextMigration) coroutineContext.get(ContextMigration.Key);
        coroutineContext = coroutineContext.minusKey(ContinuationInterceptor.Key).minusKey(ContextMigration.Key);
        if (contextMigration != null) {
            context = contextMigration.getContext();
            if (context != null) {
                if (coroutineContext == EmptyCoroutineContext.INSTANCE) {
                    context = context.plus(new ExperimentalContextMigration(coroutineContext));
                }
                return continuationInterceptor != null ? context : context.plus(toExperimentalContinuationInterceptor(continuationInterceptor));
            }
        }
        context = kotlin.coroutines.experimental.EmptyCoroutineContext.INSTANCE;
        if (coroutineContext == EmptyCoroutineContext.INSTANCE) {
            context = context.plus(new ExperimentalContextMigration(coroutineContext));
        }
        if (continuationInterceptor != null) {
        }
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final kotlin.coroutines.CoroutineContext toCoroutineContext(@NotNull CoroutineContext coroutineContext) {
        kotlin.coroutines.CoroutineContext context;
        Intrinsics.checkParameterIsNotNull(coroutineContext, "receiver$0");
        kotlin.coroutines.experimental.ContinuationInterceptor continuationInterceptor = (kotlin.coroutines.experimental.ContinuationInterceptor) coroutineContext.get(kotlin.coroutines.experimental.ContinuationInterceptor.Key);
        ExperimentalContextMigration experimentalContextMigration = (ExperimentalContextMigration) coroutineContext.get(ExperimentalContextMigration.Key);
        coroutineContext = coroutineContext.minusKey(kotlin.coroutines.experimental.ContinuationInterceptor.Key).minusKey(ExperimentalContextMigration.Key);
        if (experimentalContextMigration != null) {
            context = experimentalContextMigration.getContext();
            if (context != null) {
                if (coroutineContext == kotlin.coroutines.experimental.EmptyCoroutineContext.INSTANCE) {
                    context = context.plus(new ContextMigration(coroutineContext));
                }
                return continuationInterceptor != null ? context : context.plus(toContinuationInterceptor(continuationInterceptor));
            }
        }
        context = EmptyCoroutineContext.INSTANCE;
        if (coroutineContext == kotlin.coroutines.experimental.EmptyCoroutineContext.INSTANCE) {
            context = context.plus(new ContextMigration(coroutineContext));
        }
        if (continuationInterceptor != null) {
        }
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final kotlin.coroutines.experimental.ContinuationInterceptor toExperimentalContinuationInterceptor(@NotNull ContinuationInterceptor continuationInterceptor) {
        Intrinsics.checkParameterIsNotNull(continuationInterceptor, "receiver$0");
        ContinuationInterceptorMigration continuationInterceptorMigration = (ContinuationInterceptorMigration) (!(continuationInterceptor instanceof ContinuationInterceptorMigration) ? null : continuationInterceptor);
        if (continuationInterceptorMigration != null) {
            kotlin.coroutines.experimental.ContinuationInterceptor interceptor = continuationInterceptorMigration.getInterceptor();
            if (interceptor != null) {
                return interceptor;
            }
        }
        return new ExperimentalContinuationInterceptorMigration(continuationInterceptor);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final ContinuationInterceptor toContinuationInterceptor(@NotNull kotlin.coroutines.experimental.ContinuationInterceptor continuationInterceptor) {
        Intrinsics.checkParameterIsNotNull(continuationInterceptor, "receiver$0");
        ExperimentalContinuationInterceptorMigration experimentalContinuationInterceptorMigration = (ExperimentalContinuationInterceptorMigration) (!(continuationInterceptor instanceof ExperimentalContinuationInterceptorMigration) ? null : continuationInterceptor);
        if (experimentalContinuationInterceptorMigration != null) {
            ContinuationInterceptor interceptor = experimentalContinuationInterceptorMigration.getInterceptor();
            if (interceptor != null) {
                return interceptor;
            }
        }
        return new ContinuationInterceptorMigration(continuationInterceptor);
    }

    @NotNull
    public static final <R> Function1<Continuation<? super R>, Object> toExperimentalSuspendFunction(@NotNull Function1<? super kotlin.coroutines.Continuation<? super R>, ? extends Object> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "receiver$0");
        return new ExperimentalSuspendFunction0Migration(function1);
    }

    @NotNull
    public static final <T1, R> Function2<T1, Continuation<? super R>, Object> toExperimentalSuspendFunction(@NotNull Function2<? super T1, ? super kotlin.coroutines.Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "receiver$0");
        return new ExperimentalSuspendFunction1Migration(function2);
    }

    @NotNull
    public static final <T1, T2, R> Function3<T1, T2, Continuation<? super R>, Object> toExperimentalSuspendFunction(@NotNull Function3<? super T1, ? super T2, ? super kotlin.coroutines.Continuation<? super R>, ? extends Object> function3) {
        Intrinsics.checkParameterIsNotNull(function3, "receiver$0");
        return new ExperimentalSuspendFunction2Migration(function3);
    }
}
