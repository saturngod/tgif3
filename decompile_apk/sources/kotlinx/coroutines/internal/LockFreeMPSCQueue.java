package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0013\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00028\u0000¢\u0006\u0002\u0010\rJ\u0006\u0010\u000e\u001a\u00020\u000fJ\r\u0010\u0010\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u0011R$\u0010\u0004\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00028\u00000\u0006j\b\u0012\u0004\u0012\u00028\u0000`\u00070\u0005X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\b\u0010\n¨\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeMPSCQueue;", "E", "", "()V", "_cur", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/internal/LockFreeMPSCQueueCore;", "Lkotlinx/coroutines/internal/Core;", "isEmpty", "", "()Z", "addLast", "element", "(Ljava/lang/Object;)Z", "close", "", "removeFirstOrNull", "()Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: LockFreeMPSCQueue.kt */
public final class LockFreeMPSCQueue<E> {
    private static final AtomicReferenceFieldUpdater _cur$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeMPSCQueue.class, Object.class, "_cur");
    private volatile Object _cur = new LockFreeMPSCQueueCore(8);

    public final boolean isEmpty() {
        return ((LockFreeMPSCQueueCore) this._cur).isEmpty();
    }

    public final void close() {
        while (true) {
            LockFreeMPSCQueueCore lockFreeMPSCQueueCore = (LockFreeMPSCQueueCore) this._cur;
            if (!lockFreeMPSCQueueCore.close()) {
                _cur$FU.compareAndSet(this, lockFreeMPSCQueueCore, lockFreeMPSCQueueCore.next());
            } else {
                return;
            }
        }
    }

    public final boolean addLast(@NotNull E e) {
        Intrinsics.checkParameterIsNotNull(e, "element");
        while (true) {
            LockFreeMPSCQueueCore lockFreeMPSCQueueCore = (LockFreeMPSCQueueCore) this._cur;
            switch (lockFreeMPSCQueueCore.addLast(e)) {
                case 0:
                    return true;
                case 1:
                    _cur$FU.compareAndSet(this, lockFreeMPSCQueueCore, lockFreeMPSCQueueCore.next());
                    break;
                case 2:
                    return null;
                default:
                    break;
            }
        }
    }

    @Nullable
    public final E removeFirstOrNull() {
        while (true) {
            LockFreeMPSCQueueCore lockFreeMPSCQueueCore = (LockFreeMPSCQueueCore) this._cur;
            E removeFirstOrNull = lockFreeMPSCQueueCore.removeFirstOrNull();
            if (removeFirstOrNull != LockFreeMPSCQueueCore.REMOVE_FROZEN) {
                return removeFirstOrNull;
            }
            _cur$FU.compareAndSet(this, lockFreeMPSCQueueCore, lockFreeMPSCQueueCore.next());
        }
    }
}
