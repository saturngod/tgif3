package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u00060\bj\u0002`\tH\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/Unconfined;", "Lkotlinx/coroutines/CoroutineDispatcher;", "()V", "dispatch", "", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "isDispatchNeeded", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Unconfined.kt */
public final class Unconfined extends CoroutineDispatcher {
    public static final Unconfined INSTANCE = new Unconfined();

    public boolean isDispatchNeeded(@NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        return false;
    }

    @NotNull
    public String toString() {
        return "Unconfined";
    }

    private Unconfined() {
    }

    public void dispatch(@NotNull CoroutineContext coroutineContext, @NotNull Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(runnable, "block");
        throw ((Throwable) new UnsupportedOperationException());
    }
}
