package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001e\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\u0010\r\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\u000e\u001a\u00020\fH\u0014J \u0010\u000f\u001a\u00020\u00102\n\u0010\r\u001a\u00060\u0004j\u0002`\u00052\n\u0010\u000e\u001a\u00060\u0004j\u0002`\u0005H\u0014J\"\u0010\u0011\u001a\u0004\u0018\u00010\f2\n\u0010\r\u001a\u00060\u0004j\u0002`\u00052\n\u0010\u000e\u001a\u00060\u0004j\u0002`\u0005H\u0014J \u0010\u0012\u001a\u00020\u00132\n\u0010\r\u001a\u00060\u0004j\u0002`\u00052\n\u0010\u000e\u001a\u00060\u0004j\u0002`\u0005H\u0014R\u001c\u0010\u0002\u001a\u0010\u0012\f\u0012\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u00050\u0003X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u00058TX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u00058TX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\b¨\u0006\u0014"}, d2 = {"kotlinx/coroutines/internal/LockFreeLinkedListNode$describeRemove$1", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AbstractAtomicDesc;", "_originalNext", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/Node;", "affectedNode", "getAffectedNode", "()Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "originalNext", "getOriginalNext", "failure", "", "affected", "next", "finishOnSuccess", "", "onPrepare", "updatedNext", "Lkotlinx/coroutines/internal/Removed;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: LockFreeLinkedList.kt */
public final class LockFreeLinkedListNode$describeRemove$1 extends AbstractAtomicDesc {
    private static final AtomicReferenceFieldUpdater _originalNext$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode$describeRemove$1.class, Object.class, "_originalNext");
    private volatile Object _originalNext = null;
    final /* synthetic */ LockFreeLinkedListNode this$0;

    LockFreeLinkedListNode$describeRemove$1(LockFreeLinkedListNode lockFreeLinkedListNode) {
        this.this$0 = lockFreeLinkedListNode;
    }

    @Nullable
    protected LockFreeLinkedListNode getAffectedNode() {
        return this.this$0;
    }

    @Nullable
    protected LockFreeLinkedListNode getOriginalNext() {
        return (LockFreeLinkedListNode) this._originalNext;
    }

    @Nullable
    protected Object failure(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
        Intrinsics.checkParameterIsNotNull(obj, "next");
        return (obj instanceof Removed) != null ? LockFreeLinkedListKt.getALREADY_REMOVED() : null;
    }

    @Nullable
    protected Object onPrepare(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode2) {
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode2, "next");
        _originalNext$FU.compareAndSet(this, null, lockFreeLinkedListNode2);
        return null;
    }

    @NotNull
    protected Removed updatedNext(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode2) {
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode2, "next");
        return lockFreeLinkedListNode2.removed();
    }

    protected void finishOnSuccess(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode2) {
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode2, "next");
        this.this$0.finishRemove(lockFreeLinkedListNode2);
    }
}
