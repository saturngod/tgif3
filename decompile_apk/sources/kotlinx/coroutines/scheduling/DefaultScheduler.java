package kotlinx.coroutines.scheduling;

import kotlin.Metadata;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DispatchersKt;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.internal.SystemPropsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0007J\b\u0010\u000b\u001a\u00020\nH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/scheduling/DefaultScheduler;", "Lkotlinx/coroutines/scheduling/ExperimentalCoroutineDispatcher;", "()V", "IO", "Lkotlinx/coroutines/CoroutineDispatcher;", "getIO", "()Lkotlinx/coroutines/CoroutineDispatcher;", "close", "", "toDebugString", "", "toString", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Dispatcher.kt */
public final class DefaultScheduler extends ExperimentalCoroutineDispatcher {
    public static final DefaultScheduler INSTANCE;
    @NotNull
    private static final CoroutineDispatcher IO;

    @NotNull
    public String toString() {
        return TasksKt.DEFAULT_SCHEDULER_NAME;
    }

    static {
        DefaultScheduler defaultScheduler = new DefaultScheduler();
        INSTANCE = defaultScheduler;
        IO = defaultScheduler.blocking(SystemPropsKt.systemProp$default(DispatchersKt.IO_PARALLELISM_PROPERTY_NAME, RangesKt___RangesKt.coerceAtLeast(64, SystemPropsKt.getAVAILABLE_PROCESSORS()), 0, 0, 12, null));
    }

    private DefaultScheduler() {
        super(0, 0, 3, null);
    }

    @NotNull
    public final CoroutineDispatcher getIO() {
        return IO;
    }

    public void close() {
        throw new UnsupportedOperationException("DefaultDispatcher cannot be closed");
    }

    @NotNull
    @InternalCoroutinesApi
    public final String toDebugString() {
        return super.toString();
    }
}
