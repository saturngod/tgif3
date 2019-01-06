package kotlinx.coroutines;

import java.util.concurrent.ThreadFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/PoolThread;", "target", "Ljava/lang/Runnable;", "kotlin.jvm.PlatformType", "newThread"}, k = 3, mv = {1, 1, 13})
/* compiled from: ThreadPoolDispatcher.kt */
final class ThreadPoolDispatcher$executor$1 implements ThreadFactory {
    final /* synthetic */ ThreadPoolDispatcher this$0;

    ThreadPoolDispatcher$executor$1(ThreadPoolDispatcher threadPoolDispatcher) {
        this.this$0 = threadPoolDispatcher;
    }

    @NotNull
    public final PoolThread newThread(Runnable runnable) {
        String access$getName$p;
        ThreadPoolDispatcher threadPoolDispatcher = this.this$0;
        Intrinsics.checkExpressionValueIsNotNull(runnable, "target");
        if (this.this$0.nThreads == 1) {
            access$getName$p = this.this$0.name;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.this$0.name);
            stringBuilder.append("-");
            stringBuilder.append(this.this$0.threadNo.incrementAndGet());
            access$getName$p = stringBuilder.toString();
        }
        return new PoolThread(threadPoolDispatcher, runnable, access$getName$p);
    }
}
