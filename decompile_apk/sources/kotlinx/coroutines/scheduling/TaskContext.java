package kotlinx.coroutines.scheduling;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\b\u0010\u0006\u001a\u00020\u0007H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\b"}, d2 = {"Lkotlinx/coroutines/scheduling/TaskContext;", "", "taskMode", "Lkotlinx/coroutines/scheduling/TaskMode;", "getTaskMode", "()Lkotlinx/coroutines/scheduling/TaskMode;", "afterTask", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Tasks.kt */
public interface TaskContext {
    void afterTask();

    @NotNull
    TaskMode getTaskMode();
}
