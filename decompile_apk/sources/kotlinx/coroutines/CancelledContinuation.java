package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/CancelledContinuation;", "Lkotlinx/coroutines/CompletedExceptionally;", "continuation", "Lkotlin/coroutines/Continuation;", "cause", "", "(Lkotlin/coroutines/Continuation;Ljava/lang/Throwable;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: CompletedExceptionally.kt */
public final class CancelledContinuation extends CompletedExceptionally {
    public CancelledContinuation(@NotNull Continuation<?> continuation, @Nullable Throwable th) {
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        if (th == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Continuation ");
            stringBuilder.append(continuation);
            stringBuilder.append(" was cancelled normally");
            th = new CancellationException(stringBuilder.toString());
        }
        super(th);
    }
}
