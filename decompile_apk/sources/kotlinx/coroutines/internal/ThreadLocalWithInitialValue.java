package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\r\u0010\u0006\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u0007R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lkotlinx/coroutines/internal/ThreadLocalWithInitialValue;", "T", "Ljava/lang/ThreadLocal;", "supplier", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)V", "initialValue", "()Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: ThreadLocal.kt */
public final class ThreadLocalWithInitialValue<T> extends ThreadLocal<T> {
    private final Function0<T> supplier;

    public ThreadLocalWithInitialValue(@NotNull Function0<? extends T> function0) {
        Intrinsics.checkParameterIsNotNull(function0, "supplier");
        this.supplier = function0;
    }

    protected T initialValue() {
        return this.supplier.invoke();
    }
}
