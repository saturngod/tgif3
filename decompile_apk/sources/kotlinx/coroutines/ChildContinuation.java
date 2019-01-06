package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016R\u0014\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00058\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/ChildContinuation;", "Lkotlinx/coroutines/JobCancellingNode;", "Lkotlinx/coroutines/Job;", "parent", "child", "Lkotlinx/coroutines/AbstractContinuation;", "(Lkotlinx/coroutines/Job;Lkotlinx/coroutines/AbstractContinuation;)V", "invoke", "", "cause", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: JobSupport.kt */
public final class ChildContinuation extends JobCancellingNode<Job> {
    @NotNull
    @JvmField
    public final AbstractContinuation<?> child;

    public ChildContinuation(@NotNull Job job, @NotNull AbstractContinuation<?> abstractContinuation) {
        Intrinsics.checkParameterIsNotNull(job, "parent");
        Intrinsics.checkParameterIsNotNull(abstractContinuation, "child");
        super(job);
        this.child = abstractContinuation;
    }

    public void invoke(@Nullable Throwable th) {
        this.child.cancelImpl(this.child.getContinuationCancellationCause(this.job));
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ChildContinuation[");
        stringBuilder.append(this.child);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
