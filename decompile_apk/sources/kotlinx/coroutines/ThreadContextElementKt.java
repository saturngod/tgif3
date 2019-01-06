package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.ThreadLocalElement;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a+\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u0002H\u0002¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"asContextElement", "Lkotlinx/coroutines/ThreadContextElement;", "T", "Ljava/lang/ThreadLocal;", "value", "(Ljava/lang/ThreadLocal;Ljava/lang/Object;)Lkotlinx/coroutines/ThreadContextElement;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: ThreadContextElement.kt */
public final class ThreadContextElementKt {
    @NotNull
    public static /* synthetic */ ThreadContextElement asContextElement$default(ThreadLocal threadLocal, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = threadLocal.get();
        }
        return asContextElement(threadLocal, obj);
    }

    @NotNull
    public static final <T> ThreadContextElement<T> asContextElement(@NotNull ThreadLocal<T> threadLocal, T t) {
        Intrinsics.checkParameterIsNotNull(threadLocal, "receiver$0");
        return new ThreadLocalElement(t, threadLocal);
    }
}
