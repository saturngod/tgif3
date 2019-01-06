package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0013\u0010\f\u001a\u00020\r2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlinx/coroutines/ChildHandleNode;", "Lkotlinx/coroutines/JobCancellingNode;", "Lkotlinx/coroutines/JobSupport;", "Lkotlinx/coroutines/ChildHandle;", "parent", "childJob", "Lkotlinx/coroutines/ChildJob;", "(Lkotlinx/coroutines/JobSupport;Lkotlinx/coroutines/ChildJob;)V", "childCancelled", "", "cause", "", "invoke", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: JobSupport.kt */
public final class ChildHandleNode extends JobCancellingNode<JobSupport> implements ChildHandle {
    @NotNull
    @JvmField
    public final ChildJob childJob;

    public ChildHandleNode(@NotNull JobSupport jobSupport, @NotNull ChildJob childJob) {
        Intrinsics.checkParameterIsNotNull(jobSupport, "parent");
        Intrinsics.checkParameterIsNotNull(childJob, "childJob");
        super(jobSupport);
        this.childJob = childJob;
    }

    public void invoke(@Nullable Throwable th) {
        this.childJob.parentCancelled((ParentJob) this.job);
    }

    public boolean childCancelled(@NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "cause");
        return ((JobSupport) this.job).childCancelled(th);
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ChildHandle[");
        stringBuilder.append(this.childJob);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
