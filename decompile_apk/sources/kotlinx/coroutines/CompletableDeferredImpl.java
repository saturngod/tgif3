package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result.Failure;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B\u000f\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0011\u0010\u0011\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0012J\u0015\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\r\u0010\u0019\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001aJH\u0010\u001b\u001a\u00020\u001c\"\u0004\b\u0001\u0010\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u001d0\u001f2\"\u0010 \u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001d0\"\u0012\u0006\u0012\u0004\u0018\u00010#0!H\u0016ø\u0001\u0000¢\u0006\u0002\u0010$R\u0014\u0010\b\u001a\u00020\t8TX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\t8PX\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000b\u0002\u0004\n\u0002\b\u0019¨\u0006%"}, d2 = {"Lkotlinx/coroutines/CompletableDeferredImpl;", "T", "Lkotlinx/coroutines/JobSupport;", "Lkotlinx/coroutines/CompletableDeferred;", "Lkotlinx/coroutines/selects/SelectClause1;", "parent", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/Job;)V", "cancelsParent", "", "getCancelsParent", "()Z", "onAwait", "getOnAwait", "()Lkotlinx/coroutines/selects/SelectClause1;", "onCancelComplete", "getOnCancelComplete$kotlinx_coroutines_core", "await", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "complete", "value", "(Ljava/lang/Object;)Z", "completeExceptionally", "exception", "", "getCompleted", "()Ljava/lang/Object;", "registerSelectClause1", "", "R", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: CompletableDeferred.kt */
final class CompletableDeferredImpl<T> extends JobSupport implements CompletableDeferred<T>, SelectClause1<T> {
    protected boolean getCancelsParent() {
        return true;
    }

    public boolean getOnCancelComplete$kotlinx_coroutines_core() {
        return true;
    }

    public CompletableDeferredImpl(@Nullable Job job) {
        super(true);
        initParentJobInternal$kotlinx_coroutines_core(job);
    }

    public T getCompleted() {
        return getCompletedInternal$kotlinx_coroutines_core();
    }

    @Nullable
    public Object await(@NotNull Continuation<? super T> continuation) {
        Continuation continuation2;
        Continuation<? super T> coroutine_suspended;
        if (continuation instanceof CompletableDeferredImpl$await$1) {
            continuation2 = (CompletableDeferredImpl$await$1) continuation;
            if ((continuation2.label & Integer.MIN_VALUE) != 0) {
                continuation2.label -= -2147483648;
                continuation = continuation2.result;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (continuation2.label) {
                    case 0:
                        if (continuation instanceof Failure) {
                            continuation2.L$0 = this;
                            continuation2.label = 1;
                            continuation = awaitInternal$kotlinx_coroutines_core(continuation2);
                            if (continuation != coroutine_suspended) {
                            }
                        }
                        throw ((Failure) continuation).exception;
                        break;
                    case 1:
                        CompletableDeferredImpl completableDeferredImpl = (CompletableDeferredImpl) continuation2.L$0;
                        if (!(continuation instanceof Failure)) {
                            break;
                        }
                        throw ((Failure) continuation).exception;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        continuation2 = new CompletableDeferredImpl$await$1(this, continuation);
        continuation = continuation2.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (continuation2.label) {
            case 0:
                if (continuation instanceof Failure) {
                    throw ((Failure) continuation).exception;
                }
                continuation2.L$0 = this;
                continuation2.label = 1;
                continuation = awaitInternal$kotlinx_coroutines_core(continuation2);
                return continuation != coroutine_suspended ? continuation : coroutine_suspended;
            case 1:
                CompletableDeferredImpl completableDeferredImpl2 = (CompletableDeferredImpl) continuation2.L$0;
                if (!(continuation instanceof Failure)) {
                    break;
                }
                throw ((Failure) continuation).exception;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @NotNull
    public SelectClause1<T> getOnAwait() {
        return this;
    }

    public <R> void registerSelectClause1(@NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(selectInstance, "select");
        Intrinsics.checkParameterIsNotNull(function2, "block");
        registerSelectClause1Internal$kotlinx_coroutines_core(selectInstance, function2);
    }

    public boolean complete(T t) {
        return makeCompleting$kotlinx_coroutines_core(t);
    }

    public boolean completeExceptionally(@NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "exception");
        return makeCompleting$kotlinx_coroutines_core(new CompletedExceptionally(th));
    }
}
