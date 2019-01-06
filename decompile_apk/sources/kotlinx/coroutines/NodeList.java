package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bJ\b\u0010\r\u001a\u00020\u000bH\u0016R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00008VX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/NodeList;", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "Lkotlinx/coroutines/Incomplete;", "()V", "isActive", "", "()Z", "list", "getList", "()Lkotlinx/coroutines/NodeList;", "getString", "", "state", "toString", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: JobSupport.kt */
public final class NodeList extends LockFreeLinkedListHead implements Incomplete {
    @NotNull
    public NodeList getList() {
        return this;
    }

    public boolean isActive() {
        return true;
    }

    @NotNull
    public final String getString(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "state");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("List{");
        stringBuilder.append(str);
        stringBuilder.append("}[");
        str = getNext();
        if (str != null) {
            Object obj = 1;
            for (Object obj2 = (LockFreeLinkedListNode) str; (Intrinsics.areEqual(obj2, (Object) this) ^ 1) != 0; obj2 = obj2.getNextNode()) {
                if (obj2 instanceof JobNode) {
                    JobNode jobNode = (JobNode) obj2;
                    if (obj != null) {
                        obj = null;
                    } else {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(jobNode);
                }
            }
            stringBuilder.append("]");
            str = stringBuilder.toString();
            Intrinsics.checkExpressionValueIsNotNull(str, "StringBuilder().apply(builderAction).toString()");
            return str;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
    }

    @NotNull
    public String toString() {
        return getString("Active");
    }
}
