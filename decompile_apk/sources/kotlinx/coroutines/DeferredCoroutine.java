package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result.Failure;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0012\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0011\u0010\u0010\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\r\u0010\u0012\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0013JH\u0010\u0014\u001a\u00020\u0015\"\u0004\b\u0001\u0010\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00160\u00182\"\u0010\u0019\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00160\u001b\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001aH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u001dR\u0014\u0010\n\u001a\u00020\b8TX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"Lkotlinx/coroutines/DeferredCoroutine;", "T", "Lkotlinx/coroutines/AbstractCoroutine;", "Lkotlinx/coroutines/Deferred;", "Lkotlinx/coroutines/selects/SelectClause1;", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "active", "", "(Lkotlin/coroutines/CoroutineContext;Z)V", "cancelsParent", "getCancelsParent", "()Z", "onAwait", "getOnAwait", "()Lkotlinx/coroutines/selects/SelectClause1;", "await", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCompleted", "()Ljava/lang/Object;", "registerSelectClause1", "", "R", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Builders.common.kt */
class DeferredCoroutine<T> extends AbstractCoroutine<T> implements Deferred<T>, SelectClause1<T> {
    @Nullable
    public Object await(@NotNull Continuation<? super T> continuation) {
        return await$suspendImpl(this, continuation);
    }

    protected boolean getCancelsParent() {
        return true;
    }

    public DeferredCoroutine(@NotNull CoroutineContext coroutineContext, boolean z) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "parentContext");
        super(coroutineContext, z);
    }

    public T getCompleted() {
        return getCompletedInternal$kotlinx_coroutines_core();
    }

    static /* synthetic */ Object await$suspendImpl(DeferredCoroutine deferredCoroutine, Continuation continuation) {
        Continuation continuation2;
        Continuation coroutine_suspended;
        if (continuation instanceof DeferredCoroutine$await$1) {
            continuation2 = (DeferredCoroutine$await$1) continuation;
            if ((continuation2.label & Integer.MIN_VALUE) != 0) {
                continuation2.label -= -2147483648;
                continuation = continuation2.result;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (continuation2.label) {
                    case 0:
                        if (continuation instanceof Failure) {
                            continuation2.L$0 = deferredCoroutine;
                            continuation2.label = 1;
                            continuation = deferredCoroutine.awaitInternal$kotlinx_coroutines_core(continuation2);
                            if (continuation != coroutine_suspended) {
                            }
                        }
                        throw ((Failure) continuation).exception;
                        break;
                    case 1:
                        deferredCoroutine = continuation2.L$0;
                        if ((continuation instanceof Failure) == null) {
                            break;
                        }
                        throw ((Failure) continuation).exception;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        continuation2 = new DeferredCoroutine$await$1(deferredCoroutine, continuation);
        continuation = continuation2.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (continuation2.label) {
            case 0:
                if (continuation instanceof Failure) {
                    throw ((Failure) continuation).exception;
                }
                continuation2.L$0 = deferredCoroutine;
                continuation2.label = 1;
                continuation = deferredCoroutine.awaitInternal$kotlinx_coroutines_core(continuation2);
                return continuation != coroutine_suspended ? continuation : coroutine_suspended;
            case 1:
                deferredCoroutine = continuation2.L$0;
                if ((continuation instanceof Failure) == null) {
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
}
