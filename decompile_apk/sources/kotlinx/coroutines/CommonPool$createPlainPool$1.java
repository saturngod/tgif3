package kotlinx.coroutines;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Ljava/lang/Thread;", "it", "Ljava/lang/Runnable;", "kotlin.jvm.PlatformType", "newThread"}, k = 3, mv = {1, 1, 13})
/* compiled from: CommonPool.kt */
final class CommonPool$createPlainPool$1 implements ThreadFactory {
    final /* synthetic */ AtomicInteger $threadId;

    CommonPool$createPlainPool$1(AtomicInteger atomicInteger) {
        this.$threadId = atomicInteger;
    }

    @NotNull
    public final Thread newThread(Runnable runnable) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CommonPool-worker-");
        stringBuilder.append(this.$threadId.incrementAndGet());
        Thread thread = new Thread(runnable, stringBuilder.toString());
        thread.setDaemon(true);
        return thread;
    }
}
