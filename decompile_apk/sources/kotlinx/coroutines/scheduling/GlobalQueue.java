package kotlinx.coroutines.scheduling;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.LockFreeMPMCQueue;
import kotlinx.coroutines.internal.LockFreeMPMCQueueNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0010\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0002J\n\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\b\u001a\u0004\u0018\u00010\u0002¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/scheduling/GlobalQueue;", "Lkotlinx/coroutines/internal/LockFreeMPMCQueue;", "Lkotlinx/coroutines/scheduling/Task;", "()V", "add", "", "task", "removeFirstBlockingModeOrNull", "removeFirstIfNotClosed", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Tasks.kt */
public class GlobalQueue extends LockFreeMPMCQueue<Task> {
    public final boolean add(@NotNull Task task) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        while (true) {
            LockFreeMPMCQueueNode tailValue = getTailValue();
            LockFreeMPMCQueueNode lockFreeMPMCQueueNode = (LockFreeMPMCQueueNode) tailValue.getNextValue();
            if (lockFreeMPMCQueueNode != null) {
                tailCas(tailValue, lockFreeMPMCQueueNode);
            } else {
                if ((tailValue != TasksKt.getCLOSED_TASK() ? 1 : null) == null) {
                    return false;
                }
                LockFreeMPMCQueueNode lockFreeMPMCQueueNode2 = task;
                if (tailValue.nextCas(null, lockFreeMPMCQueueNode2)) {
                    tailCas(tailValue, lockFreeMPMCQueueNode2);
                    return true;
                }
            }
        }
    }

    @Nullable
    public final Task removeFirstIfNotClosed() {
        LockFreeMPMCQueueNode lockFreeMPMCQueueNode;
        LockFreeMPMCQueueNode lockFreeMPMCQueueNode2;
        LockFreeMPMCQueueNode headValue;
        do {
            headValue = getHeadValue();
            lockFreeMPMCQueueNode = (LockFreeMPMCQueueNode) headValue.getNextValue();
            lockFreeMPMCQueueNode2 = null;
            if (lockFreeMPMCQueueNode == null) {
                break;
            }
            if ((((Task) lockFreeMPMCQueueNode) != TasksKt.getCLOSED_TASK() ? 1 : null) == null) {
                break;
            }
        } while (!headCas(headValue, lockFreeMPMCQueueNode));
        lockFreeMPMCQueueNode2 = lockFreeMPMCQueueNode;
        return (Task) lockFreeMPMCQueueNode2;
    }

    @Nullable
    public Task removeFirstBlockingModeOrNull() {
        LockFreeMPMCQueueNode lockFreeMPMCQueueNode;
        LockFreeMPMCQueueNode lockFreeMPMCQueueNode2;
        LockFreeMPMCQueueNode headValue;
        do {
            headValue = getHeadValue();
            lockFreeMPMCQueueNode = (LockFreeMPMCQueueNode) headValue.getNextValue();
            lockFreeMPMCQueueNode2 = null;
            if (lockFreeMPMCQueueNode == null) {
                break;
            }
            if ((((Task) lockFreeMPMCQueueNode).getMode() == TaskMode.PROBABLY_BLOCKING ? 1 : null) == null) {
                break;
            }
        } while (!headCas(headValue, lockFreeMPMCQueueNode));
        lockFreeMPMCQueueNode2 = lockFreeMPMCQueueNode;
        return (Task) lockFreeMPMCQueueNode2;
    }
}
