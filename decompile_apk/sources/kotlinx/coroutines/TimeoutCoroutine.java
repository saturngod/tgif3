package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0012\u0018\u0000*\u0004\b\u0000\u0010\u0001*\n\b\u0001\u0010\u0002 \u0000*\u0002H\u00012\b\u0012\u0004\u0012\u0002H\u00020\u00032\u00060\u0004j\u0002`\u00052\b\u0012\u0004\u0012\u0002H\u00020\u0006B\u001b\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\nJ\r\u0010\u000f\u001a\u00020\u0010H\u0010¢\u0006\u0002\b\u0011J'\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0010¢\u0006\u0002\b\u0019J\b\u0010\u001a\u001a\u00020\u0013H\u0016R\u0014\u0010\u000b\u001a\u00020\f8PX\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0010\u0010\u0007\u001a\u00020\b8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00068\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lkotlinx/coroutines/TimeoutCoroutine;", "U", "T", "Lkotlinx/coroutines/AbstractCoroutine;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "Lkotlin/coroutines/Continuation;", "time", "", "uCont", "(JLkotlin/coroutines/Continuation;)V", "defaultResumeMode", "", "getDefaultResumeMode$kotlinx_coroutines_core", "()I", "nameString", "", "nameString$kotlinx_coroutines_core", "onCompletionInternal", "", "state", "", "mode", "suppressed", "", "onCompletionInternal$kotlinx_coroutines_core", "run", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Timeout.kt */
class TimeoutCoroutine<U, T extends U> extends AbstractCoroutine<T> implements Runnable, Continuation<T> {
    @JvmField
    public final long time;
    @NotNull
    @JvmField
    public final Continuation<U> uCont;

    public int getDefaultResumeMode$kotlinx_coroutines_core() {
        return 2;
    }

    public TimeoutCoroutine(long j, @NotNull Continuation<? super U> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "uCont");
        super(continuation.getContext(), true);
        this.time = j;
        this.uCont = continuation;
    }

    public void run() {
        cancel(TimeoutKt.TimeoutCancellationException(this.time, this));
    }

    public void onCompletionInternal$kotlinx_coroutines_core(@Nullable Object obj, int i, boolean z) {
        if (obj instanceof CompletedExceptionally) {
            ResumeModeKt.resumeUninterceptedWithExceptionMode(this.uCont, ((CompletedExceptionally) obj).cause, i);
        } else {
            ResumeModeKt.resumeUninterceptedMode(this.uCont, obj, i);
        }
    }

    @NotNull
    public String nameString$kotlinx_coroutines_core() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.nameString$kotlinx_coroutines_core());
        stringBuilder.append("(timeMillis=");
        stringBuilder.append(this.time);
        stringBuilder.append(')');
        return stringBuilder.toString();
    }
}
