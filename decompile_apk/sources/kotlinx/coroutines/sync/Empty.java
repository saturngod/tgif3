package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016R\u0010\u0010\u0002\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/sync/Empty;", "", "locked", "(Ljava/lang/Object;)V", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Mutex.kt */
final class Empty {
    @NotNull
    @JvmField
    public final Object locked;

    public Empty(@NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "locked");
        this.locked = obj;
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Empty[");
        stringBuilder.append(this.locked);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
