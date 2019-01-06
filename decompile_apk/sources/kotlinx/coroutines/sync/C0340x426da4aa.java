package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.LockFreeLinkedListNode.CondAddOp;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H\u0016¨\u0006\u0007¸\u0006\t"}, d2 = {"kotlinx/coroutines/internal/LockFreeLinkedListNode$makeCondAddOp$1", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$CondAddOp;", "prepare", "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/Node;", "kotlinx-coroutines-core", "kotlinx/coroutines/sync/MutexImpl$$special$$inlined$addLastIf$1", "kotlinx/coroutines/sync/MutexImpl$$special$$inlined$loop$lambda$1"}, k = 1, mv = {1, 1, 13})
/* compiled from: LockFreeLinkedList.kt */
/* renamed from: kotlinx.coroutines.sync.MutexImpl$lockSuspend$$inlined$suspendAtomicCancellableCoroutine$lambda$1 */
public final class C0340x426da4aa extends CondAddOp {
    final /* synthetic */ CancellableContinuation $cont$inlined;
    final /* synthetic */ LockFreeLinkedListNode $node;
    final /* synthetic */ Object $owner$inlined;
    final /* synthetic */ Object $state$inlined;
    final /* synthetic */ LockCont $waiter$inlined;
    final /* synthetic */ MutexImpl this$0;

    public C0340x426da4aa(LockFreeLinkedListNode lockFreeLinkedListNode, LockFreeLinkedListNode lockFreeLinkedListNode2, Object obj, CancellableContinuation cancellableContinuation, LockCont lockCont, MutexImpl mutexImpl, Object obj2) {
        this.$node = lockFreeLinkedListNode;
        this.$state$inlined = obj;
        this.$cont$inlined = cancellableContinuation;
        this.$waiter$inlined = lockCont;
        this.this$0 = mutexImpl;
        this.$owner$inlined = obj2;
        super(lockFreeLinkedListNode2);
    }

    @Nullable
    public Object prepare(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
        return (this.this$0._state == this.$state$inlined ? true : null) != null ? null : LockFreeLinkedListKt.getCONDITION_FALSE();
    }
}
