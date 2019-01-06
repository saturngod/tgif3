package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0003\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0005\u001a\u00020\u00068TX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u00068TX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0014\u0010\u000b\u001a\u00020\u00068PX\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\b¨\u0006\u0010"}, d2 = {"Lkotlinx/coroutines/SupervisorJobImpl;", "Lkotlinx/coroutines/JobSupport;", "parent", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/Job;)V", "cancelsParent", "", "getCancelsParent", "()Z", "handlesException", "getHandlesException", "onCancelComplete", "getOnCancelComplete$kotlinx_coroutines_core", "childCancelled", "cause", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Supervisor.kt */
final class SupervisorJobImpl extends JobSupport {
    public boolean childCancelled(@NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "cause");
        return false;
    }

    protected boolean getCancelsParent() {
        return true;
    }

    protected boolean getHandlesException() {
        return false;
    }

    public boolean getOnCancelComplete$kotlinx_coroutines_core() {
        return true;
    }

    public SupervisorJobImpl(@Nullable Job job) {
        super(true);
        initParentJobInternal$kotlinx_coroutines_core(job);
    }
}
