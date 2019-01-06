package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlinx/coroutines/DisposeOnCancel;", "Lkotlinx/coroutines/CancelHandler;", "handle", "Lkotlinx/coroutines/DisposableHandle;", "(Lkotlinx/coroutines/DisposableHandle;)V", "invoke", "", "cause", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: CancellableContinuation.kt */
final class DisposeOnCancel extends CancelHandler {
    private final DisposableHandle handle;

    public DisposeOnCancel(@NotNull DisposableHandle disposableHandle) {
        Intrinsics.checkParameterIsNotNull(disposableHandle, "handle");
        this.handle = disposableHandle;
    }

    public void invoke(@Nullable Throwable th) {
        this.handle.dispose();
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DisposeOnCancel[");
        stringBuilder.append(this.handle);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
