package kotlinx.coroutines;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.ContinuationInterceptor.DefaultImpls;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.CoroutineContext.Key;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u00060\tj\u0002`\nH&J\u001c\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u00060\tj\u0002`\nH\u0017J \u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000e0\r\"\u0004\b\u0000\u0010\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000e0\rJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u0007H\u0017J\u0011\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0000H\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016¨\u0006\u0016"}, d2 = {"Lkotlinx/coroutines/CoroutineDispatcher;", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlin/coroutines/ContinuationInterceptor;", "()V", "dispatch", "", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "dispatchYield", "interceptContinuation", "Lkotlin/coroutines/Continuation;", "T", "continuation", "isDispatchNeeded", "", "plus", "other", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: CoroutineDispatcher.kt */
public abstract class CoroutineDispatcher extends AbstractCoroutineContextElement implements ContinuationInterceptor {
    public abstract void dispatch(@NotNull CoroutineContext coroutineContext, @NotNull Runnable runnable);

    @ExperimentalCoroutinesApi
    public boolean isDispatchNeeded(@NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        return true;
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two CoroutineDispatcher objects is meaningless. CoroutineDispatcher is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The dispatcher to the right of `+` just replaces the dispatcher the left of `+`.")
    public final CoroutineDispatcher plus(@NotNull CoroutineDispatcher coroutineDispatcher) {
        Intrinsics.checkParameterIsNotNull(coroutineDispatcher, "other");
        return coroutineDispatcher;
    }

    @Nullable
    public <E extends Element> E get(@NotNull Key<E> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return DefaultImpls.get(this, key);
    }

    @NotNull
    public CoroutineContext minusKey(@NotNull Key<?> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return DefaultImpls.minusKey(this, key);
    }

    public void releaseInterceptedContinuation(@NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        DefaultImpls.releaseInterceptedContinuation(this, continuation);
    }

    public CoroutineDispatcher() {
        super(ContinuationInterceptor.Key);
    }

    @InternalCoroutinesApi
    public void dispatchYield(@NotNull CoroutineContext coroutineContext, @NotNull Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(runnable, "block");
        dispatch(coroutineContext, runnable);
    }

    @NotNull
    public final <T> Continuation<T> interceptContinuation(@NotNull Continuation<? super T> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        return new DispatchedContinuation(this, continuation);
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(DebugKt.getClassSimpleName(this));
        stringBuilder.append('@');
        stringBuilder.append(DebugKt.getHexAddress(this));
        return stringBuilder.toString();
    }
}
