package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00018\u0006X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00018\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/CompletedIdempotentResult;", "", "idempotentResume", "result", "token", "Lkotlinx/coroutines/NotCompleted;", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlinx/coroutines/NotCompleted;)V", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: CancellableContinuation.kt */
final class CompletedIdempotentResult {
    @Nullable
    @JvmField
    public final Object idempotentResume;
    @Nullable
    @JvmField
    public final Object result;
    @NotNull
    @JvmField
    public final NotCompleted token;

    public CompletedIdempotentResult(@Nullable Object obj, @Nullable Object obj2, @NotNull NotCompleted notCompleted) {
        Intrinsics.checkParameterIsNotNull(notCompleted, "token");
        this.idempotentResume = obj;
        this.result = obj2;
        this.token = notCompleted;
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CompletedIdempotentResult[");
        stringBuilder.append(this.result);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
