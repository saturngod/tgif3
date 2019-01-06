package kotlinx.coroutines.scheduling;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lkotlinx/coroutines/scheduling/Task;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: WorkQueue.kt */
public final class WorkQueue$pollExternal$1 extends Lambda implements Function1<Task, Boolean> {
    public static final WorkQueue$pollExternal$1 INSTANCE = new WorkQueue$pollExternal$1();

    public WorkQueue$pollExternal$1() {
        super(1);
    }

    public final boolean invoke(@NotNull Task task) {
        Intrinsics.checkParameterIsNotNull(task, "it");
        return true;
    }
}
