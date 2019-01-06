package kotlinx.coroutines;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u00008&X§\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0002\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/MainCoroutineDispatcher;", "Lkotlinx/coroutines/CoroutineDispatcher;", "()V", "immediate", "immediate$annotations", "getImmediate", "()Lkotlinx/coroutines/MainCoroutineDispatcher;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: MainCoroutineDispatcher.kt */
public abstract class MainCoroutineDispatcher extends CoroutineDispatcher {
    @ExperimentalCoroutinesApi
    public static /* synthetic */ void immediate$annotations() {
    }

    @NotNull
    public abstract MainCoroutineDispatcher getImmediate();
}
