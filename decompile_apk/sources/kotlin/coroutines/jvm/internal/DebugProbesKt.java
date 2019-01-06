package kotlin.coroutines.jvm.internal;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0001\u001a\u0014\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0001H\u0001\u001a\u0014\u0010\u0007\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0001H\u0001¨\u0006\b"}, d2 = {"probeCoroutineCreated", "Lkotlin/coroutines/Continuation;", "T", "completion", "probeCoroutineResumed", "", "frame", "probeCoroutineSuspended", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
/* compiled from: DebugProbes.kt */
public final class DebugProbesKt {
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final <T> Continuation<T> probeCoroutineCreated(@NotNull Continuation<? super T> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        return continuation;
    }

    @SinceKotlin(version = "1.3")
    public static final void probeCoroutineResumed(@NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "frame");
    }

    @SinceKotlin(version = "1.3")
    public static final void probeCoroutineSuspended(@NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "frame");
    }
}
