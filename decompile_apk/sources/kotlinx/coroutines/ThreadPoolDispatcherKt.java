package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u0010\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¨\u0006\u0007"}, d2 = {"newFixedThreadPoolContext", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "nThreads", "", "name", "", "newSingleThreadContext", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: ThreadPoolDispatcher.kt */
public final class ThreadPoolDispatcherKt {
    @NotNull
    @ObsoleteCoroutinesApi
    public static final ExecutorCoroutineDispatcher newSingleThreadContext(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        return newFixedThreadPoolContext(1, str);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final ExecutorCoroutineDispatcher newFixedThreadPoolContext(int i, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        Object obj = 1;
        if (i < 1) {
            obj = null;
        }
        if (obj != null) {
            return new ThreadPoolDispatcher(i, str);
        }
        str = new StringBuilder();
        str.append("Expected at least one thread, but ");
        str.append(i);
        str.append(" specified");
        throw ((Throwable) new IllegalArgumentException(str.toString().toString()));
    }
}
