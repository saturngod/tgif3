package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Result.Companion;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0013\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/ResumeOnCompletion;", "Lkotlinx/coroutines/JobNode;", "Lkotlinx/coroutines/Job;", "job", "continuation", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)V", "invoke", "cause", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: JobSupport.kt */
final class ResumeOnCompletion extends JobNode<Job> {
    private final Continuation<Unit> continuation;

    public ResumeOnCompletion(@NotNull Job job, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(job, "job");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        super(job);
        this.continuation = continuation;
    }

    public void invoke(@Nullable Throwable th) {
        th = this.continuation;
        Unit unit = Unit.INSTANCE;
        Companion companion = Result.Companion;
        th.resumeWith(Result.constructor-impl(unit));
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ResumeOnCompletion[");
        stringBuilder.append(this.continuation);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
