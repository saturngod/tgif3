package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.LockFreeLinkedListNode.CondAddOp;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H\u0016¨\u0006\u0007¸\u0006\u0000"}, d2 = {"kotlinx/coroutines/internal/LockFreeLinkedListNode$makeCondAddOp$1", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$CondAddOp;", "prepare", "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/Node;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: LockFreeLinkedList.kt */
public final class SelectBuilderImpl$disposeOnSelect$$inlined$addLastIf$1 extends CondAddOp {
    final /* synthetic */ LockFreeLinkedListNode $node;
    final /* synthetic */ SelectBuilderImpl this$0;

    public SelectBuilderImpl$disposeOnSelect$$inlined$addLastIf$1(LockFreeLinkedListNode lockFreeLinkedListNode, LockFreeLinkedListNode lockFreeLinkedListNode2, SelectBuilderImpl selectBuilderImpl) {
        this.$node = lockFreeLinkedListNode;
        this.this$0 = selectBuilderImpl;
        super(lockFreeLinkedListNode2);
    }

    @Nullable
    public Object prepare(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
        return (this.this$0.getState() == this.this$0 ? true : null) != null ? null : LockFreeLinkedListKt.getCONDITION_FALSE();
    }
}
