package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J'\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\rH\u0010¢\u0006\u0002\b\u0016R\u0014\u0010\b\u001a\u00020\t8PX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00068\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lkotlinx/coroutines/SupervisorCoroutine;", "R", "Lkotlinx/coroutines/AbstractCoroutine;", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "uCont", "Lkotlin/coroutines/Continuation;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)V", "defaultResumeMode", "", "getDefaultResumeMode$kotlinx_coroutines_core", "()I", "childCancelled", "", "cause", "", "onCompletionInternal", "", "state", "", "mode", "suppressed", "onCompletionInternal$kotlinx_coroutines_core", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Supervisor.kt */
final class SupervisorCoroutine<R> extends AbstractCoroutine<R> {
    @NotNull
    @JvmField
    public final Continuation<R> uCont;

    public boolean childCancelled(@NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "cause");
        return false;
    }

    public int getDefaultResumeMode$kotlinx_coroutines_core() {
        return 2;
    }

    public SupervisorCoroutine(@NotNull CoroutineContext coroutineContext, @NotNull Continuation<? super R> continuation) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "parentContext");
        Intrinsics.checkParameterIsNotNull(continuation, "uCont");
        super(coroutineContext, true);
        this.uCont = continuation;
    }

    public void onCompletionInternal$kotlinx_coroutines_core(@Nullable Object obj, int i, boolean z) {
        if (obj instanceof CompletedExceptionally) {
            ResumeModeKt.resumeUninterceptedWithExceptionMode(this.uCont, ((CompletedExceptionally) obj).cause, i);
        } else {
            ResumeModeKt.resumeUninterceptedMode(this.uCont, obj, i);
        }
    }
}
