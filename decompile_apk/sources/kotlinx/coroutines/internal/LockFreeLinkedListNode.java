package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.TypeCastException;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.InternalCoroutinesApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0017\u0018\u00002\u00020\u0001:\u0004BCDEB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0016\u001a\u00020\u00172\n\u0010\u0018\u001a\u00060\u0000j\u0002`\u000fJ%\u0010\u0019\u001a\u00020\t2\n\u0010\u0018\u001a\u00060\u0000j\u0002`\u000f2\u000e\b\u0004\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\u001bH\bJ-\u0010\u001c\u001a\u00020\t2\n\u0010\u0018\u001a\u00060\u0000j\u0002`\u000f2\u0016\u0010\u001d\u001a\u0012\u0012\b\u0012\u00060\u0000j\u0002`\u000f\u0012\u0004\u0012\u00020\t0\u001eH\bJ=\u0010\u001f\u001a\u00020\t2\n\u0010\u0018\u001a\u00060\u0000j\u0002`\u000f2\u0016\u0010\u001d\u001a\u0012\u0012\b\u0012\u00060\u0000j\u0002`\u000f\u0012\u0004\u0012\u00020\t0\u001e2\u000e\b\u0004\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\u001bH\bJ \u0010 \u001a\u00020\t2\n\u0010\u0018\u001a\u00060\u0000j\u0002`\u000f2\n\u0010\u000b\u001a\u00060\u0000j\u0002`\u000fH\u0001J\u0012\u0010!\u001a\u00020\t2\n\u0010\u0018\u001a\u00060\u0000j\u0002`\u000fJ&\u0010\"\u001a\n\u0018\u00010\u0000j\u0004\u0018\u0001`\u000f2\n\u0010\u0005\u001a\u00060\u0000j\u0002`\u000f2\b\u0010#\u001a\u0004\u0018\u00010$H\u0002J'\u0010%\u001a\b\u0012\u0004\u0012\u0002H'0&\"\f\b\u0000\u0010'*\u00060\u0000j\u0002`\u000f2\u0006\u0010\u0018\u001a\u0002H'¢\u0006\u0002\u0010(J\n\u0010)\u001a\u0004\u0018\u00010*H\u0016J\u0010\u0010+\u001a\f\u0012\b\u0012\u00060\u0000j\u0002`\u000f0,J\f\u0010-\u001a\u00060\u0000j\u0002`\u000fH\u0002J\u0014\u0010.\u001a\u00020\u00172\n\u0010\u000b\u001a\u00060\u0000j\u0002`\u000fH\u0002J\u0014\u0010/\u001a\u00020\u00172\n\u0010\u000b\u001a\u00060\u0000j\u0002`\u000fH\u0002J\b\u00100\u001a\u00020\u0017H\u0001J\u0006\u00101\u001a\u00020\u0017J%\u00102\u001a\u0002032\n\u0010\u0018\u001a\u00060\u0000j\u0002`\u000f2\u000e\b\u0004\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\u001bH\bJ\f\u00104\u001a\u00060\u0000j\u0002`\u000fH\u0002J\b\u00105\u001a\u00020\tH\u0016J\u0018\u00106\u001a\u0004\u0018\u0001H'\"\u0006\b\u0000\u0010'\u0018\u0001H\b¢\u0006\u0002\u0010\rJ,\u00107\u001a\u0004\u0018\u0001H'\"\u0006\b\u0000\u0010'\u0018\u00012\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u0002H'\u0012\u0004\u0012\u00020\t0\u001eH\b¢\u0006\u0002\u00108J\u000e\u00109\u001a\n\u0018\u00010\u0000j\u0004\u0018\u0001`\u000fJ\b\u0010:\u001a\u00020\u0007H\u0002J\b\u0010;\u001a\u00020<H\u0016J(\u0010=\u001a\u00020>2\n\u0010\u0018\u001a\u00060\u0000j\u0002`\u000f2\n\u0010\u000b\u001a\u00060\u0000j\u0002`\u000f2\u0006\u0010?\u001a\u000203H\u0001J%\u0010@\u001a\u00020\u00172\n\u0010\u0012\u001a\u00060\u0000j\u0002`\u000f2\n\u0010\u000b\u001a\u00060\u0000j\u0002`\u000fH\u0000¢\u0006\u0002\bAR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\b\u0010\nR\u0011\u0010\u000b\u001a\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0015\u0010\u000e\u001a\u00060\u0000j\u0002`\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\rR\u0015\u0010\u0014\u001a\u00060\u0000j\u0002`\u000f8F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0011¨\u0006F"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "", "()V", "_next", "Lkotlinx/atomicfu/AtomicRef;", "_prev", "_removedRef", "Lkotlinx/coroutines/internal/Removed;", "isRemoved", "", "()Z", "next", "getNext", "()Ljava/lang/Object;", "nextNode", "Lkotlinx/coroutines/internal/Node;", "getNextNode", "()Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "prev", "getPrev", "prevNode", "getPrevNode", "addLast", "", "node", "addLastIf", "condition", "Lkotlin/Function0;", "addLastIfPrev", "predicate", "Lkotlin/Function1;", "addLastIfPrevAndIf", "addNext", "addOneIfEmpty", "correctPrev", "op", "Lkotlinx/coroutines/internal/OpDescriptor;", "describeAddLast", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "T", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "describeRemove", "Lkotlinx/coroutines/internal/AtomicDesc;", "describeRemoveFirst", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "findHead", "finishAdd", "finishRemove", "helpDelete", "helpRemove", "makeCondAddOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$CondAddOp;", "markPrev", "remove", "removeFirstIfIsInstanceOf", "removeFirstIfIsInstanceOfOrPeekIf", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "removeFirstOrNull", "removed", "toString", "", "tryCondAddNext", "", "condAdd", "validateNode", "validateNode$kotlinx_coroutines_core", "AbstractAtomicDesc", "AddLastDesc", "CondAddOp", "RemoveFirstDesc", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
@InternalCoroutinesApi
/* compiled from: LockFreeLinkedList.kt */
public class LockFreeLinkedListNode {
    static final AtomicReferenceFieldUpdater _next$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_next");
    static final AtomicReferenceFieldUpdater _prev$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_prev");
    private static final AtomicReferenceFieldUpdater _removedRef$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_removedRef");
    volatile Object _next = this;
    volatile Object _prev = this;
    private volatile Object _removedRef = null;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\u0010\u0010\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\u0011\u001a\u00020\u000fH\u0014J \u0010\u0012\u001a\u00020\u000b2\n\u0010\u0010\u001a\u00060\u0004j\u0002`\u00052\n\u0010\u0011\u001a\u00060\u0004j\u0002`\u0005H$J\"\u0010\u0013\u001a\u0004\u0018\u00010\u000f2\n\u0010\u0010\u001a\u00060\u0004j\u0002`\u00052\n\u0010\u0011\u001a\u00060\u0004j\u0002`\u0005H$J\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u000f2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\rJ\u001c\u0010\u0015\u001a\u00020\u00162\n\u0010\u0010\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\u0011\u001a\u00020\u000fH\u0014J\u0014\u0010\u0017\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\f\u001a\u00020\u0018H\u0014J \u0010\u0019\u001a\u00020\u000f2\n\u0010\u0010\u001a\u00060\u0004j\u0002`\u00052\n\u0010\u0011\u001a\u00060\u0004j\u0002`\u0005H$R\u001a\u0010\u0003\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u0005X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u0005X¤\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007¨\u0006\u001b"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AbstractAtomicDesc;", "Lkotlinx/coroutines/internal/AtomicDesc;", "()V", "affectedNode", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/Node;", "getAffectedNode", "()Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "originalNext", "getOriginalNext", "complete", "", "op", "Lkotlinx/coroutines/internal/AtomicOp;", "failure", "", "affected", "next", "finishOnSuccess", "onPrepare", "prepare", "retry", "", "takeAffectedNode", "Lkotlinx/coroutines/internal/OpDescriptor;", "updatedNext", "PrepareOp", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: LockFreeLinkedList.kt */
    public static abstract class AbstractAtomicDesc extends AtomicDesc {

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B+\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0010\u0010\u0005\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0014\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016R\u0010\u0010\u0007\u001a\u00020\b8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00060\u0003j\u0002`\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u00068\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AbstractAtomicDesc$PrepareOp;", "Lkotlinx/coroutines/internal/OpDescriptor;", "next", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/Node;", "op", "Lkotlinx/coroutines/internal/AtomicOp;", "desc", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AbstractAtomicDesc;", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;Lkotlinx/coroutines/internal/AtomicOp;Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AbstractAtomicDesc;)V", "perform", "", "affected", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
        /* compiled from: LockFreeLinkedList.kt */
        private static final class PrepareOp extends OpDescriptor {
            @NotNull
            @JvmField
            public final AbstractAtomicDesc desc;
            @NotNull
            @JvmField
            public final LockFreeLinkedListNode next;
            @NotNull
            @JvmField
            public final AtomicOp<LockFreeLinkedListNode> op;

            public PrepareOp(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull AtomicOp<? super LockFreeLinkedListNode> atomicOp, @NotNull AbstractAtomicDesc abstractAtomicDesc) {
                Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "next");
                Intrinsics.checkParameterIsNotNull(atomicOp, "op");
                Intrinsics.checkParameterIsNotNull(abstractAtomicDesc, "desc");
                this.next = lockFreeLinkedListNode;
                this.op = atomicOp;
                this.desc = abstractAtomicDesc;
            }

            @Nullable
            public Object perform(@Nullable Object obj) {
                if (obj != null) {
                    LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) obj;
                    Object onPrepare = this.desc.onPrepare(lockFreeLinkedListNode, this.next);
                    if (onPrepare != null) {
                        if (onPrepare == LockFreeLinkedListKt.REMOVE_PREPARED) {
                            if (LockFreeLinkedListNode._next$FU.compareAndSet(lockFreeLinkedListNode, this, this.next.removed())) {
                                lockFreeLinkedListNode.helpDelete();
                            }
                        } else {
                            this.op.tryDecide(onPrepare);
                            LockFreeLinkedListNode._next$FU.compareAndSet(lockFreeLinkedListNode, this, this.next);
                        }
                        return onPrepare;
                    }
                    LockFreeLinkedListNode._next$FU.compareAndSet(lockFreeLinkedListNode, this, this.op.isDecided() ? this.next : this.op);
                    return null;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
        }

        @Nullable
        protected Object failure(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
            Intrinsics.checkParameterIsNotNull(obj, "next");
            return null;
        }

        protected abstract void finishOnSuccess(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode2);

        @Nullable
        protected abstract LockFreeLinkedListNode getAffectedNode();

        @Nullable
        protected abstract LockFreeLinkedListNode getOriginalNext();

        @Nullable
        protected abstract Object onPrepare(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode2);

        protected boolean retry(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
            Intrinsics.checkParameterIsNotNull(obj, "next");
            return false;
        }

        @NotNull
        protected abstract Object updatedNext(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode2);

        @NotNull
        protected LockFreeLinkedListNode takeAffectedNode(@NotNull OpDescriptor opDescriptor) {
            Intrinsics.checkParameterIsNotNull(opDescriptor, "op");
            opDescriptor = getAffectedNode();
            if (opDescriptor == null) {
                Intrinsics.throwNpe();
            }
            return opDescriptor;
        }

        @Nullable
        public final Object prepare(@NotNull AtomicOp<?> atomicOp) {
            Intrinsics.checkParameterIsNotNull(atomicOp, "op");
            while (true) {
                LockFreeLinkedListNode takeAffectedNode = takeAffectedNode(atomicOp);
                AtomicOp<?> atomicOp2 = takeAffectedNode._next;
                if (atomicOp2 == atomicOp || atomicOp.isDecided()) {
                    return null;
                }
                if (atomicOp2 instanceof OpDescriptor) {
                    atomicOp2.perform(takeAffectedNode);
                } else {
                    Object failure = failure(takeAffectedNode, atomicOp2);
                    if (failure != null) {
                        return failure;
                    }
                    if (!retry(takeAffectedNode, atomicOp2)) {
                        if (atomicOp2 != null) {
                            PrepareOp prepareOp = new PrepareOp((LockFreeLinkedListNode) atomicOp2, atomicOp, this);
                            if (LockFreeLinkedListNode._next$FU.compareAndSet(takeAffectedNode, atomicOp2, prepareOp)) {
                                Object perform = prepareOp.perform(takeAffectedNode);
                                if (perform != LockFreeLinkedListKt.REMOVE_PREPARED) {
                                    return perform;
                                }
                            } else {
                                continue;
                            }
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
                        }
                    }
                }
            }
        }

        public final void complete(@NotNull AtomicOp<?> atomicOp, @Nullable Object obj) {
            Intrinsics.checkParameterIsNotNull(atomicOp, "op");
            obj = obj == null ? true : null;
            LockFreeLinkedListNode affectedNode = getAffectedNode();
            if (affectedNode != null) {
                LockFreeLinkedListNode originalNext = getOriginalNext();
                if (originalNext != null) {
                    if (!(LockFreeLinkedListNode._next$FU.compareAndSet(affectedNode, atomicOp, obj != null ? updatedNext(affectedNode, originalNext) : originalNext) == null || obj == null)) {
                        finishOnSuccess(affectedNode, originalNext);
                    }
                    return;
                }
                AbstractAtomicDesc abstractAtomicDesc = this;
                if ((obj ^ 1) == null) {
                    throw ((Throwable) new IllegalStateException("Check failed.".toString()));
                }
                return;
            }
            abstractAtomicDesc = this;
            if ((obj ^ 1) == null) {
                throw ((Throwable) new IllegalStateException("Check failed.".toString()));
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000*\f\b\u0000\u0010\u0001*\u00060\u0002j\u0002`\u00032\u00020\u0004B\u0019\u0012\n\u0010\u0005\u001a\u00060\u0002j\u0002`\u0003\u0012\u0006\u0010\u0006\u001a\u00028\u0000¢\u0006\u0002\u0010\u0007J \u0010\u0010\u001a\u00020\u00112\n\u0010\u0012\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u0013\u001a\u00060\u0002j\u0002`\u0003H\u0014J\"\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\u0010\u0012\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u0013\u001a\u00060\u0002j\u0002`\u0003H\u0014J\u001c\u0010\u0016\u001a\u00020\u00172\n\u0010\u0012\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010\u0013\u001a\u00020\u0015H\u0014J\u0014\u0010\u0018\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010\u0019\u001a\u00020\u001aH\u0004J \u0010\u001b\u001a\u00020\u00152\n\u0010\u0012\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u0013\u001a\u00060\u0002j\u0002`\u0003H\u0014R\u001c\u0010\b\u001a\u0010\u0012\f\u0012\n\u0018\u00010\u0002j\u0004\u0018\u0001`\u00030\tX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\n\u0018\u00010\u0002j\u0004\u0018\u0001`\u00038DX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\u0006\u001a\u00028\u00008\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\rR\u001c\u0010\u000e\u001a\n\u0018\u00010\u0002j\u0004\u0018\u0001`\u00038DX\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\fR\u0014\u0010\u0005\u001a\u00060\u0002j\u0002`\u00038\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "T", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/Node;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AbstractAtomicDesc;", "queue", "node", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)V", "_affectedNode", "Lkotlinx/atomicfu/AtomicRef;", "affectedNode", "getAffectedNode", "()Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "originalNext", "getOriginalNext", "finishOnSuccess", "", "affected", "next", "onPrepare", "", "retry", "", "takeAffectedNode", "op", "Lkotlinx/coroutines/internal/OpDescriptor;", "updatedNext", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: LockFreeLinkedList.kt */
    public static class AddLastDesc<T extends LockFreeLinkedListNode> extends AbstractAtomicDesc {
        private static final AtomicReferenceFieldUpdater _affectedNode$FU = AtomicReferenceFieldUpdater.newUpdater(AddLastDesc.class, Object.class, "_affectedNode");
        private volatile Object _affectedNode;
        @NotNull
        @JvmField
        public final T node;
        @NotNull
        @JvmField
        public final LockFreeLinkedListNode queue;

        public AddLastDesc(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull T t) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "queue");
            Intrinsics.checkParameterIsNotNull(t, "node");
            this.queue = lockFreeLinkedListNode;
            this.node = t;
            lockFreeLinkedListNode = (this.node._next == this.node && this.node._prev == this.node) ? true : null;
            if (lockFreeLinkedListNode != null) {
                this._affectedNode = null;
                return;
            }
            throw ((Throwable) new IllegalStateException("Check failed.".toString()));
        }

        @NotNull
        protected final LockFreeLinkedListNode takeAffectedNode(@NotNull OpDescriptor opDescriptor) {
            Intrinsics.checkParameterIsNotNull(opDescriptor, "op");
            while (true) {
                Object obj = this.queue._prev;
                if (obj != null) {
                    LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) obj;
                    Object obj2 = lockFreeLinkedListNode._next;
                    if (obj2 == this.queue || obj2 == opDescriptor) {
                        return lockFreeLinkedListNode;
                    }
                    if (obj2 instanceof OpDescriptor) {
                        ((OpDescriptor) obj2).perform(lockFreeLinkedListNode);
                    } else {
                        lockFreeLinkedListNode = this.queue.correctPrev(lockFreeLinkedListNode, opDescriptor);
                        if (lockFreeLinkedListNode != null) {
                            return lockFreeLinkedListNode;
                        }
                    }
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
                }
            }
        }

        @Nullable
        protected final LockFreeLinkedListNode getAffectedNode() {
            return (LockFreeLinkedListNode) this._affectedNode;
        }

        @Nullable
        protected final LockFreeLinkedListNode getOriginalNext() {
            return this.queue;
        }

        protected boolean retry(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
            Intrinsics.checkParameterIsNotNull(obj, "next");
            return obj != this.queue ? true : null;
        }

        @Nullable
        protected Object onPrepare(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode2) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode2, "next");
            _affectedNode$FU.compareAndSet(this, null, lockFreeLinkedListNode);
            return null;
        }

        @NotNull
        protected Object updatedNext(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode2) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode2, "next");
            LockFreeLinkedListNode._prev$FU.compareAndSet(this.node, this.node, lockFreeLinkedListNode);
            LockFreeLinkedListNode._next$FU.compareAndSet(this.node, this.node, this.queue);
            return this.node;
        }

        protected void finishOnSuccess(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode2) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode2, "next");
            this.node.finishAdd(this.queue);
        }
    }

    @PublishedApi
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\b!\u0018\u00002\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001B\u0011\u0012\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003¢\u0006\u0002\u0010\u0005J\u001e\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u00060\u0002j\u0002`\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016R\u0014\u0010\u0004\u001a\u00060\u0002j\u0002`\u00038\u0006X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\n\u0018\u00010\u0002j\u0004\u0018\u0001`\u00038\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeLinkedListNode$CondAddOp;", "Lkotlinx/coroutines/internal/AtomicOp;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/Node;", "newNode", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)V", "oldNext", "complete", "", "affected", "failure", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: LockFreeLinkedList.kt */
    public static abstract class CondAddOp extends AtomicOp<LockFreeLinkedListNode> {
        @NotNull
        @JvmField
        public final LockFreeLinkedListNode newNode;
        @Nullable
        @JvmField
        public LockFreeLinkedListNode oldNext;

        public CondAddOp(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "newNode");
            this.newNode = lockFreeLinkedListNode;
        }

        public void complete(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @Nullable Object obj) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
            obj = obj == null ? true : null;
            Object obj2 = obj != null ? this.newNode : this.oldNext;
            if (obj2 != null && LockFreeLinkedListNode._next$FU.compareAndSet(lockFreeLinkedListNode, this, obj2) != null && obj != null) {
                lockFreeLinkedListNode = this.newNode;
                obj = this.oldNext;
                if (obj == null) {
                    Intrinsics.throwNpe();
                }
                lockFreeLinkedListNode.finishAdd(obj);
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0011\u0012\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\u0010\u0016\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\u0017\u001a\u00020\u0015H\u0014J \u0010\u0018\u001a\u00020\u00192\n\u0010\u0016\u001a\u00060\u0004j\u0002`\u00052\n\u0010\u0017\u001a\u00060\u0004j\u0002`\u0005H\u0004J\"\u0010\u001a\u001a\u0004\u0018\u00010\u00152\n\u0010\u0016\u001a\u00060\u0004j\u0002`\u00052\n\u0010\u0017\u001a\u00060\u0004j\u0002`\u0005H\u0004J\u001c\u0010\u001b\u001a\u00020\u001c2\n\u0010\u0016\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\u0017\u001a\u00020\u0015H\u0004J\u0014\u0010\u001d\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\u001e\u001a\u00020\u001fH\u0004J \u0010 \u001a\u00020\u00152\n\u0010\u0016\u001a\u00060\u0004j\u0002`\u00052\n\u0010\u0017\u001a\u00060\u0004j\u0002`\u0005H\u0004J\u0015\u0010!\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010#R\u001c\u0010\u0007\u001a\u0010\u0012\f\u0012\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u00050\bX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u00050\bX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u00058DX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u00058DX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fR\u0014\u0010\u0003\u001a\u00060\u0004j\u0002`\u00058\u0006X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000f\u001a\u00028\u00008F¢\u0006\f\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006$"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "T", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AbstractAtomicDesc;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/Node;", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)V", "_affectedNode", "Lkotlinx/atomicfu/AtomicRef;", "_originalNext", "affectedNode", "getAffectedNode", "()Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "originalNext", "getOriginalNext", "result", "result$annotations", "()V", "getResult", "()Ljava/lang/Object;", "failure", "", "affected", "next", "finishOnSuccess", "", "onPrepare", "retry", "", "takeAffectedNode", "op", "Lkotlinx/coroutines/internal/OpDescriptor;", "updatedNext", "validatePrepared", "node", "(Ljava/lang/Object;)Z", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: LockFreeLinkedList.kt */
    public static class RemoveFirstDesc<T> extends AbstractAtomicDesc {
        private static final AtomicReferenceFieldUpdater _affectedNode$FU = AtomicReferenceFieldUpdater.newUpdater(RemoveFirstDesc.class, Object.class, "_affectedNode");
        private static final AtomicReferenceFieldUpdater _originalNext$FU = AtomicReferenceFieldUpdater.newUpdater(RemoveFirstDesc.class, Object.class, "_originalNext");
        private volatile Object _affectedNode = null;
        private volatile Object _originalNext = null;
        @NotNull
        @JvmField
        public final LockFreeLinkedListNode queue;

        public static /* synthetic */ void result$annotations() {
        }

        protected boolean validatePrepared(T t) {
            return true;
        }

        public RemoveFirstDesc(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "queue");
            this.queue = lockFreeLinkedListNode;
        }

        public final T getResult() {
            LockFreeLinkedListNode affectedNode = getAffectedNode();
            if (affectedNode == null) {
                Intrinsics.throwNpe();
            }
            return affectedNode;
        }

        @NotNull
        protected final LockFreeLinkedListNode takeAffectedNode(@NotNull OpDescriptor opDescriptor) {
            Intrinsics.checkParameterIsNotNull(opDescriptor, "op");
            opDescriptor = this.queue.getNext();
            if (opDescriptor != null) {
                return (LockFreeLinkedListNode) opDescriptor;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
        }

        @Nullable
        protected final LockFreeLinkedListNode getAffectedNode() {
            return (LockFreeLinkedListNode) this._affectedNode;
        }

        @Nullable
        protected final LockFreeLinkedListNode getOriginalNext() {
            return (LockFreeLinkedListNode) this._originalNext;
        }

        @Nullable
        protected Object failure(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
            Intrinsics.checkParameterIsNotNull(obj, "next");
            return lockFreeLinkedListNode == this.queue ? LockFreeLinkedListKt.getLIST_EMPTY() : null;
        }

        protected final boolean retry(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
            Intrinsics.checkParameterIsNotNull(obj, "next");
            if ((obj instanceof Removed) == null) {
                return null;
            }
            lockFreeLinkedListNode.helpDelete();
            return true;
        }

        @Nullable
        protected final Object onPrepare(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode2) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode2, "next");
            if ((!(lockFreeLinkedListNode instanceof LockFreeLinkedListHead) ? 1 : null) == null) {
                throw ((Throwable) new IllegalStateException("Check failed.".toString()));
            } else if (!validatePrepared(lockFreeLinkedListNode)) {
                return LockFreeLinkedListKt.REMOVE_PREPARED;
            } else {
                _affectedNode$FU.compareAndSet(this, null, lockFreeLinkedListNode);
                _originalNext$FU.compareAndSet(this, null, lockFreeLinkedListNode2);
                return null;
            }
        }

        @NotNull
        protected final Object updatedNext(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode2) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode2, "next");
            return lockFreeLinkedListNode2.removed();
        }

        protected final void finishOnSuccess(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode2) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode2, "next");
            lockFreeLinkedListNode.finishRemove(lockFreeLinkedListNode2);
        }
    }

    private final Removed removed() {
        Removed removed = (Removed) this._removedRef;
        if (removed != null) {
            return removed;
        }
        removed = new Removed(this);
        _removedRef$FU.lazySet(this, removed);
        return removed;
    }

    @NotNull
    @PublishedApi
    public final CondAddOp makeCondAddOp(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull Function0<Boolean> function0) {
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "node");
        Intrinsics.checkParameterIsNotNull(function0, "condition");
        return new LockFreeLinkedListNode$makeCondAddOp$1(function0, lockFreeLinkedListNode, lockFreeLinkedListNode);
    }

    public final boolean isRemoved() {
        return getNext() instanceof Removed;
    }

    @NotNull
    public final LockFreeLinkedListNode getNextNode() {
        return LockFreeLinkedListKt.unwrap(getNext());
    }

    @NotNull
    public final LockFreeLinkedListNode getPrevNode() {
        return LockFreeLinkedListKt.unwrap(getPrev());
    }

    public final boolean addOneIfEmpty(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "node");
        _prev$FU.lazySet(lockFreeLinkedListNode, this);
        _next$FU.lazySet(lockFreeLinkedListNode, this);
        while (getNext() == this) {
            if (_next$FU.compareAndSet(this, this, lockFreeLinkedListNode)) {
                lockFreeLinkedListNode.finishAdd(this);
                return true;
            }
        }
        return null;
    }

    public final void addLast(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "node");
        Object prev;
        do {
            prev = getPrev();
            if (prev == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
        } while (!((LockFreeLinkedListNode) prev).addNext(lockFreeLinkedListNode, this));
    }

    @NotNull
    public final <T extends LockFreeLinkedListNode> AddLastDesc<T> describeAddLast(@NotNull T t) {
        Intrinsics.checkParameterIsNotNull(t, "node");
        return new AddLastDesc(this, t);
    }

    public final boolean addLastIfPrev(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull Function1<? super LockFreeLinkedListNode, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "node");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        LockFreeLinkedListNode lockFreeLinkedListNode2;
        do {
            Object prev = getPrev();
            if (prev != null) {
                lockFreeLinkedListNode2 = (LockFreeLinkedListNode) prev;
                if (!((Boolean) function1.invoke(lockFreeLinkedListNode2)).booleanValue()) {
                    return null;
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
        } while (!lockFreeLinkedListNode2.addNext(lockFreeLinkedListNode, this));
        return true;
    }

    @PublishedApi
    public final boolean addNext(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode2) {
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "node");
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode2, "next");
        _prev$FU.lazySet(lockFreeLinkedListNode, this);
        _next$FU.lazySet(lockFreeLinkedListNode, lockFreeLinkedListNode2);
        if (!_next$FU.compareAndSet(this, lockFreeLinkedListNode2, lockFreeLinkedListNode)) {
            return null;
        }
        lockFreeLinkedListNode.finishAdd(lockFreeLinkedListNode2);
        return true;
    }

    @PublishedApi
    public final int tryCondAddNext(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode2, @NotNull CondAddOp condAddOp) {
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "node");
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode2, "next");
        Intrinsics.checkParameterIsNotNull(condAddOp, "condAdd");
        _prev$FU.lazySet(lockFreeLinkedListNode, this);
        _next$FU.lazySet(lockFreeLinkedListNode, lockFreeLinkedListNode2);
        condAddOp.oldNext = lockFreeLinkedListNode2;
        if (_next$FU.compareAndSet(this, lockFreeLinkedListNode2, condAddOp) == null) {
            return null;
        }
        return condAddOp.perform(this) == null ? true : 2;
    }

    public boolean remove() {
        LockFreeLinkedListNode lockFreeLinkedListNode;
        LockFreeLinkedListNode next;
        do {
            next = getNext();
            if ((next instanceof Removed) || next == this) {
                return false;
            }
            if (next != null) {
                lockFreeLinkedListNode = next;
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
        } while (!_next$FU.compareAndSet(this, next, lockFreeLinkedListNode.removed()));
        finishRemove(lockFreeLinkedListNode);
        return true;
    }

    public final void helpRemove() {
        Object next = getNext();
        if (!(next instanceof Removed)) {
            next = null;
        }
        Removed removed = (Removed) next;
        if (removed != null) {
            finishRemove(removed.ref);
            return;
        }
        throw new IllegalStateException("Must be invoked on a removed node".toString());
    }

    @Nullable
    public AtomicDesc describeRemove() {
        if (isRemoved()) {
            return null;
        }
        return new LockFreeLinkedListNode$describeRemove$1(this);
    }

    @Nullable
    public final LockFreeLinkedListNode removeFirstOrNull() {
        while (true) {
            Object next = getNext();
            if (next != null) {
                LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next;
                if (lockFreeLinkedListNode == this) {
                    return null;
                }
                if (lockFreeLinkedListNode.remove()) {
                    return lockFreeLinkedListNode;
                }
                lockFreeLinkedListNode.helpDelete();
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
        }
    }

    @NotNull
    public final RemoveFirstDesc<LockFreeLinkedListNode> describeRemoveFirst() {
        return new RemoveFirstDesc(this);
    }

    private final <T> T removeFirstIfIsInstanceOf() {
        while (true) {
            Object next = getNext();
            if (next != null) {
                LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next;
                if (lockFreeLinkedListNode == this) {
                    return null;
                }
                Intrinsics.reifiedOperationMarker(3, "T");
                if (!(lockFreeLinkedListNode instanceof Object)) {
                    return null;
                }
                if (lockFreeLinkedListNode.remove()) {
                    return lockFreeLinkedListNode;
                }
                lockFreeLinkedListNode.helpDelete();
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
        }
    }

    private final <T> T removeFirstIfIsInstanceOfOrPeekIf(Function1<? super T, Boolean> function1) {
        while (true) {
            Object next = getNext();
            if (next != null) {
                LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next;
                if (lockFreeLinkedListNode == this) {
                    return null;
                }
                Intrinsics.reifiedOperationMarker(3, "T");
                if (!(lockFreeLinkedListNode instanceof Object)) {
                    return null;
                }
                if (((Boolean) function1.invoke(lockFreeLinkedListNode)).booleanValue() || lockFreeLinkedListNode.remove()) {
                    return lockFreeLinkedListNode;
                }
                lockFreeLinkedListNode.helpDelete();
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
        }
    }

    private final void finishRemove(LockFreeLinkedListNode lockFreeLinkedListNode) {
        helpDelete();
        lockFreeLinkedListNode.correctPrev(LockFreeLinkedListKt.unwrap(this._prev), null);
    }

    private final LockFreeLinkedListNode findHead() {
        LockFreeLinkedListNode lockFreeLinkedListNode = this;
        LockFreeLinkedListNode lockFreeLinkedListNode2 = lockFreeLinkedListNode;
        while (!(lockFreeLinkedListNode2 instanceof LockFreeLinkedListHead)) {
            lockFreeLinkedListNode2 = lockFreeLinkedListNode2.getNextNode();
            if ((lockFreeLinkedListNode2 != lockFreeLinkedListNode ? 1 : null) == null) {
                throw new IllegalStateException("Cannot loop to this while looking for list head".toString());
            }
        }
        return lockFreeLinkedListNode2;
    }

    @PublishedApi
    public final void helpDelete() {
        LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) null;
        LockFreeLinkedListNode markPrev = markPrev();
        Object obj = this._next;
        if (obj != null) {
            LockFreeLinkedListNode lockFreeLinkedListNode2 = ((Removed) obj).ref;
            while (true) {
                LockFreeLinkedListNode next;
                Object obj2 = lockFreeLinkedListNode;
                while (true) {
                    Object next2 = lockFreeLinkedListNode2.getNext();
                    if (next2 instanceof Removed) {
                        lockFreeLinkedListNode2.markPrev();
                        lockFreeLinkedListNode2 = ((Removed) next2).ref;
                    } else {
                        next = markPrev.getNext();
                        if (next instanceof Removed) {
                            if (obj2 != null) {
                                break;
                            }
                            markPrev = LockFreeLinkedListKt.unwrap(markPrev._prev);
                        } else if (next != this) {
                            if (next != null) {
                                LockFreeLinkedListNode lockFreeLinkedListNode3 = next;
                                if (lockFreeLinkedListNode3 != lockFreeLinkedListNode2) {
                                    LockFreeLinkedListNode lockFreeLinkedListNode4 = lockFreeLinkedListNode3;
                                    obj2 = markPrev;
                                    markPrev = lockFreeLinkedListNode4;
                                } else {
                                    return;
                                }
                            }
                            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
                        } else if (_next$FU.compareAndSet(markPrev, this, lockFreeLinkedListNode2)) {
                            return;
                        }
                    }
                }
                markPrev.markPrev();
                _next$FU.compareAndSet(obj2, markPrev, ((Removed) next).ref);
                markPrev = obj2;
            }
        } else {
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Removed");
        }
    }

    private final LockFreeLinkedListNode correctPrev(LockFreeLinkedListNode lockFreeLinkedListNode, OpDescriptor opDescriptor) {
        LockFreeLinkedListNode lockFreeLinkedListNode2 = null;
        while (true) {
            OpDescriptor opDescriptor2;
            Object obj = lockFreeLinkedListNode2;
            while (true) {
                opDescriptor2 = lockFreeLinkedListNode._next;
                if (opDescriptor2 == opDescriptor) {
                    return lockFreeLinkedListNode;
                }
                if (opDescriptor2 instanceof OpDescriptor) {
                    opDescriptor2.perform(lockFreeLinkedListNode);
                } else if (!(opDescriptor2 instanceof Removed)) {
                    LockFreeLinkedListNode lockFreeLinkedListNode3 = this._prev;
                    if (lockFreeLinkedListNode3 instanceof Removed) {
                        return null;
                    }
                    if (opDescriptor2 != this) {
                        if (opDescriptor2 != null) {
                            obj = lockFreeLinkedListNode;
                            lockFreeLinkedListNode = (LockFreeLinkedListNode) opDescriptor2;
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
                        }
                    } else if (lockFreeLinkedListNode3 == lockFreeLinkedListNode) {
                        return null;
                    } else {
                        if (_prev$FU.compareAndSet(this, lockFreeLinkedListNode3, lockFreeLinkedListNode) && !(lockFreeLinkedListNode._prev instanceof Removed)) {
                            return null;
                        }
                    }
                } else if (obj != null) {
                    break;
                } else {
                    lockFreeLinkedListNode = LockFreeLinkedListKt.unwrap(lockFreeLinkedListNode._prev);
                }
            }
            lockFreeLinkedListNode.markPrev();
            _next$FU.compareAndSet(obj, lockFreeLinkedListNode, ((Removed) opDescriptor2).ref);
            lockFreeLinkedListNode = obj;
        }
    }

    public final void validateNode$kotlinx_coroutines_core(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode2) {
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "prev");
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode2, "next");
        Object obj = null;
        if ((lockFreeLinkedListNode == this._prev ? true : null) != null) {
            if (lockFreeLinkedListNode2 == this._next) {
                obj = 1;
            }
            if (obj == null) {
                throw ((Throwable) new IllegalStateException("Check failed.".toString()));
            }
            return;
        }
        throw ((Throwable) new IllegalStateException("Check failed.".toString()));
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append('@');
        stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        return stringBuilder.toString();
    }

    @NotNull
    public final Object getNext() {
        while (true) {
            Object obj = this._next;
            if (!(obj instanceof OpDescriptor)) {
                return obj;
            }
            ((OpDescriptor) obj).perform(this);
        }
    }

    @NotNull
    public final Object getPrev() {
        while (true) {
            Object obj = this._prev;
            if (obj instanceof Removed) {
                return obj;
            }
            if (obj != null) {
                LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) obj;
                if (lockFreeLinkedListNode.getNext() == this) {
                    return obj;
                }
                correctPrev(lockFreeLinkedListNode, null);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
        }
    }

    public final boolean addLastIf(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull Function0<Boolean> function0) {
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "node");
        Intrinsics.checkParameterIsNotNull(function0, "condition");
        CondAddOp lockFreeLinkedListNode$makeCondAddOp$1 = new LockFreeLinkedListNode$makeCondAddOp$1(function0, lockFreeLinkedListNode, lockFreeLinkedListNode);
        while (true) {
            function0 = getPrev();
            if (function0 != null) {
                switch (((LockFreeLinkedListNode) function0).tryCondAddNext(lockFreeLinkedListNode, this, lockFreeLinkedListNode$makeCondAddOp$1)) {
                    case 1:
                        return true;
                    case 2:
                        return null;
                    default:
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
        }
    }

    public final boolean addLastIfPrevAndIf(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull Function1<? super LockFreeLinkedListNode, Boolean> function1, @NotNull Function0<Boolean> function0) {
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "node");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        Intrinsics.checkParameterIsNotNull(function0, "condition");
        CondAddOp lockFreeLinkedListNode$makeCondAddOp$1 = new LockFreeLinkedListNode$makeCondAddOp$1(function0, lockFreeLinkedListNode, lockFreeLinkedListNode);
        while (true) {
            function0 = getPrev();
            if (function0 != null) {
                LockFreeLinkedListNode lockFreeLinkedListNode2 = (LockFreeLinkedListNode) function0;
                if (!((Boolean) function1.invoke(lockFreeLinkedListNode2)).booleanValue()) {
                    return false;
                }
                switch (lockFreeLinkedListNode2.tryCondAddNext(lockFreeLinkedListNode, this, lockFreeLinkedListNode$makeCondAddOp$1)) {
                    case 1:
                        return true;
                    case 2:
                        return false;
                    default:
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
        }
    }

    private final void finishAdd(LockFreeLinkedListNode lockFreeLinkedListNode) {
        Object obj;
        do {
            obj = lockFreeLinkedListNode._prev;
            if (!(obj instanceof Removed)) {
                if (getNext() != lockFreeLinkedListNode) {
                }
            }
            return;
        } while (!_prev$FU.compareAndSet(lockFreeLinkedListNode, obj, this));
        if (getNext() instanceof Removed) {
            if (obj != null) {
                lockFreeLinkedListNode.correctPrev((LockFreeLinkedListNode) obj, null);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
        }
    }

    private final LockFreeLinkedListNode markPrev() {
        LockFreeLinkedListNode lockFreeLinkedListNode;
        LockFreeLinkedListNode findHead;
        do {
            lockFreeLinkedListNode = this._prev;
            if (lockFreeLinkedListNode instanceof Removed) {
                return ((Removed) lockFreeLinkedListNode).ref;
            }
            if (lockFreeLinkedListNode == this) {
                findHead = findHead();
            } else if (lockFreeLinkedListNode != null) {
                findHead = lockFreeLinkedListNode;
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
        } while (!_prev$FU.compareAndSet(this, lockFreeLinkedListNode, findHead.removed()));
        return lockFreeLinkedListNode;
    }
}
