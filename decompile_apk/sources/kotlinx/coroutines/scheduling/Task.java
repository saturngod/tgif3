package kotlinx.coroutines.scheduling;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.internal.LockFreeMPMCQueueNode;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00000\u0003B!\u0012\n\u0010\u0004\u001a\u00060\u0001j\u0002`\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u0014\u0010\u0004\u001a\u00060\u0001j\u0002`\u00028\u0006X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\b8\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/scheduling/Task;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "Lkotlinx/coroutines/internal/LockFreeMPMCQueueNode;", "block", "submissionTime", "", "taskContext", "Lkotlinx/coroutines/scheduling/TaskContext;", "(Ljava/lang/Runnable;JLkotlinx/coroutines/scheduling/TaskContext;)V", "mode", "Lkotlinx/coroutines/scheduling/TaskMode;", "getMode", "()Lkotlinx/coroutines/scheduling/TaskMode;", "run", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Tasks.kt */
public final class Task extends LockFreeMPMCQueueNode<Task> implements Runnable {
    @NotNull
    @JvmField
    public final Runnable block;
    @JvmField
    public final long submissionTime;
    @NotNull
    @JvmField
    public final TaskContext taskContext;

    public Task(@NotNull Runnable runnable, long j, @NotNull TaskContext taskContext) {
        Intrinsics.checkParameterIsNotNull(runnable, "block");
        Intrinsics.checkParameterIsNotNull(taskContext, "taskContext");
        this.block = runnable;
        this.submissionTime = j;
        this.taskContext = taskContext;
    }

    @NotNull
    public final TaskMode getMode() {
        return this.taskContext.getTaskMode();
    }

    public void run() {
        try {
            this.block.run();
        } finally {
            this.taskContext.afterTask();
        }
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Task[");
        stringBuilder.append(DebugKt.getClassSimpleName(this.block));
        stringBuilder.append('@');
        stringBuilder.append(DebugKt.getHexAddress(this.block));
        stringBuilder.append(", ");
        stringBuilder.append(this.submissionTime);
        stringBuilder.append(", ");
        stringBuilder.append(this.taskContext);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
