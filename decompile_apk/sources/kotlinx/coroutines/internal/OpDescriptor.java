package kotlinx.coroutines.internal;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001H&¨\u0006\u0005"}, d2 = {"Lkotlinx/coroutines/internal/OpDescriptor;", "", "()V", "perform", "affected", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Atomic.kt */
public abstract class OpDescriptor {
    @Nullable
    public abstract Object perform(@Nullable Object obj);
}
