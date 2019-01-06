package kotlinx.coroutines.sync;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Result.Companion;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuation.DefaultImpls;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.internal.AtomicDesc;
import kotlinx.coroutines.internal.AtomicOp;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.LockFreeLinkedListNode.AddLastDesc;
import kotlinx.coroutines.internal.LockFreeLinkedListNode.CondAddOp;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u00012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00010\u0002:\u0007\"#$%&'(B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0003H\u0016J\u001b\u0010\u0012\u001a\u00020\u00132\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003H@ø\u0001\u0000¢\u0006\u0002\u0010\u0014J\u001b\u0010\u0015\u001a\u00020\u00132\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003H@ø\u0001\u0000¢\u0006\u0002\u0010\u0014JR\u0010\u0016\u001a\u00020\u0013\"\u0004\b\u0000\u0010\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00170\u00192\b\u0010\u0011\u001a\u0004\u0018\u00010\u00032\"\u0010\u001a\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00170\u001c\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u001bH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ\b\u0010\u001e\u001a\u00020\u001fH\u0016J\u0012\u0010 \u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003H\u0016J\u0012\u0010!\u001a\u00020\u00132\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003H\u0016R\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\u00058VX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\u00058@X\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\nR\"\u0010\r\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00010\u00028VX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\u0002\u0004\n\u0002\b\u0019¨\u0006)"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl;", "Lkotlinx/coroutines/sync/Mutex;", "Lkotlinx/coroutines/selects/SelectClause2;", "", "locked", "", "(Z)V", "_state", "Lkotlinx/atomicfu/AtomicRef;", "isLocked", "()Z", "isLockedEmptyQueueState", "isLockedEmptyQueueState$kotlinx_coroutines_core", "onLock", "getOnLock", "()Lkotlinx/coroutines/selects/SelectClause2;", "holdsLock", "owner", "lock", "", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lockSuspend", "registerSelectClause2", "R", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "toString", "", "tryLock", "unlock", "LockCont", "LockSelect", "LockWaiter", "LockedQueue", "TryEnqueueLockDesc", "TryLockDesc", "UnlockOp", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Mutex.kt */
public final class MutexImpl implements Mutex, SelectClause2<Object, Mutex> {
    static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(MutexImpl.class, Object.class, "_state");
    volatile Object _state;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\"\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004H&J\u0006\u0010\t\u001a\u00020\u0007J\n\u0010\n\u001a\u0004\u0018\u00010\u0004H&R\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$LockWaiter;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/DisposableHandle;", "owner", "", "(Ljava/lang/Object;)V", "completeResumeLockWaiter", "", "token", "dispose", "tryResumeLockWaiter", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Mutex.kt */
    private static abstract class LockWaiter extends LockFreeLinkedListNode implements DisposableHandle {
        @Nullable
        @JvmField
        public final Object owner;

        public abstract void completeResumeLockWaiter(@NotNull Object obj);

        @Nullable
        public abstract Object tryResumeLockWaiter();

        public LockWaiter(@Nullable Object obj) {
            this.owner = obj;
        }

        public final void dispose() {
            remove();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001:\u0001\rB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u0016J\u0016\u0010\f\u001a\u0004\u0018\u00010\u00052\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0016R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$TryLockDesc;", "Lkotlinx/coroutines/internal/AtomicDesc;", "mutex", "Lkotlinx/coroutines/sync/MutexImpl;", "owner", "", "(Lkotlinx/coroutines/sync/MutexImpl;Ljava/lang/Object;)V", "complete", "", "op", "Lkotlinx/coroutines/internal/AtomicOp;", "failure", "prepare", "PrepareOp", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Mutex.kt */
    private static final class TryLockDesc extends AtomicDesc {
        @NotNull
        @JvmField
        public final MutexImpl mutex;
        @Nullable
        @JvmField
        public final Object owner;

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016R\u0012\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$TryLockDesc$PrepareOp;", "Lkotlinx/coroutines/internal/OpDescriptor;", "op", "Lkotlinx/coroutines/internal/AtomicOp;", "(Lkotlinx/coroutines/sync/MutexImpl$TryLockDesc;Lkotlinx/coroutines/internal/AtomicOp;)V", "perform", "", "affected", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
        /* compiled from: Mutex.kt */
        private final class PrepareOp extends OpDescriptor {
            private final AtomicOp<?> op;
            final /* synthetic */ TryLockDesc this$0;

            public PrepareOp(@NotNull TryLockDesc tryLockDesc, AtomicOp<?> atomicOp) {
                Intrinsics.checkParameterIsNotNull(atomicOp, "op");
                this.this$0 = tryLockDesc;
                this.op = atomicOp;
            }

            @Nullable
            public Object perform(@Nullable Object obj) {
                Object access$getEmptyUnlocked$p = this.op.isDecided() ? MutexKt.EmptyUnlocked : this.op;
                if (obj != null) {
                    MutexImpl._state$FU.compareAndSet((MutexImpl) obj, this, access$getEmptyUnlocked$p);
                    return null;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.sync.MutexImpl");
            }
        }

        public TryLockDesc(@NotNull MutexImpl mutexImpl, @Nullable Object obj) {
            Intrinsics.checkParameterIsNotNull(mutexImpl, "mutex");
            this.mutex = mutexImpl;
            this.owner = obj;
        }

        @Nullable
        public Object prepare(@NotNull AtomicOp<?> atomicOp) {
            Intrinsics.checkParameterIsNotNull(atomicOp, "op");
            PrepareOp prepareOp = new PrepareOp(this, atomicOp);
            if (MutexImpl._state$FU.compareAndSet(this.mutex, MutexKt.EmptyUnlocked, prepareOp) == null) {
                return MutexKt.LOCK_FAIL;
            }
            return prepareOp.perform(this.mutex);
        }

        public void complete(@NotNull AtomicOp<?> atomicOp, @Nullable Object obj) {
            Intrinsics.checkParameterIsNotNull(atomicOp, "op");
            obj = obj != null ? MutexKt.EmptyUnlocked : this.owner == null ? MutexKt.EmptyLocked : new Empty(this.owner);
            MutexImpl._state$FU.compareAndSet(this.mutex, atomicOp, obj);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$UnlockOp;", "Lkotlinx/coroutines/internal/OpDescriptor;", "queue", "Lkotlinx/coroutines/sync/MutexImpl$LockedQueue;", "(Lkotlinx/coroutines/sync/MutexImpl$LockedQueue;)V", "perform", "", "affected", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Mutex.kt */
    private static final class UnlockOp extends OpDescriptor {
        @NotNull
        @JvmField
        public final LockedQueue queue;

        public UnlockOp(@NotNull LockedQueue lockedQueue) {
            Intrinsics.checkParameterIsNotNull(lockedQueue, "queue");
            this.queue = lockedQueue;
        }

        @Nullable
        public Object perform(@Nullable Object obj) {
            Object access$getEmptyUnlocked$p = this.queue.isEmpty() ? MutexKt.EmptyUnlocked : this.queue;
            if (obj != null) {
                MutexImpl mutexImpl = (MutexImpl) obj;
                MutexImpl._state$FU.compareAndSet(mutexImpl, this, access$getEmptyUnlocked$p);
                return mutexImpl._state == this.queue ? MutexKt.UNLOCK_FAIL : null;
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.sync.MutexImpl");
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0003H\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\n\u0010\f\u001a\u0004\u0018\u00010\u0003H\u0016R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$LockCont;", "Lkotlinx/coroutines/sync/MutexImpl$LockWaiter;", "owner", "", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "(Ljava/lang/Object;Lkotlinx/coroutines/CancellableContinuation;)V", "completeResumeLockWaiter", "token", "toString", "", "tryResumeLockWaiter", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Mutex.kt */
    private static final class LockCont extends LockWaiter {
        @NotNull
        @JvmField
        public final CancellableContinuation<Unit> cont;

        public LockCont(@Nullable Object obj, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
            Intrinsics.checkParameterIsNotNull(cancellableContinuation, "cont");
            super(obj);
            this.cont = cancellableContinuation;
        }

        @Nullable
        public Object tryResumeLockWaiter() {
            return DefaultImpls.tryResume$default(this.cont, Unit.INSTANCE, null, 2, null);
        }

        public void completeResumeLockWaiter(@NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(obj, "token");
            this.cont.completeResume(obj);
        }

        @NotNull
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("LockCont[");
            stringBuilder.append(this.owner);
            stringBuilder.append(", ");
            stringBuilder.append(this.cont);
            stringBuilder.append(']');
            return stringBuilder.toString();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002BL\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b\u0012\"\u0010\t\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00040\nø\u0001\u0000¢\u0006\u0002\u0010\fJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\n\u0010\u0013\u001a\u0004\u0018\u00010\u0004H\u0016R1\u0010\t\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00040\n8\u0006X\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\rR\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b8\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$LockSelect;", "R", "Lkotlinx/coroutines/sync/MutexImpl$LockWaiter;", "owner", "", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Ljava/lang/Object;Lkotlinx/coroutines/sync/Mutex;Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "completeResumeLockWaiter", "", "token", "toString", "", "tryResumeLockWaiter", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Mutex.kt */
    private static final class LockSelect<R> extends LockWaiter {
        @NotNull
        @JvmField
        public final Function2<Mutex, Continuation<? super R>, Object> block;
        @NotNull
        @JvmField
        public final Mutex mutex;
        @NotNull
        @JvmField
        public final SelectInstance<R> select;

        public LockSelect(@Nullable Object obj, @NotNull Mutex mutex, @NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super Mutex, ? super Continuation<? super R>, ? extends Object> function2) {
            Intrinsics.checkParameterIsNotNull(mutex, "mutex");
            Intrinsics.checkParameterIsNotNull(selectInstance, "select");
            Intrinsics.checkParameterIsNotNull(function2, "block");
            super(obj);
            this.mutex = mutex;
            this.select = selectInstance;
            this.block = function2;
        }

        @Nullable
        public Object tryResumeLockWaiter() {
            return this.select.trySelect(null) ? MutexKt.SELECT_SUCCESS : null;
        }

        public void completeResumeLockWaiter(@NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(obj, "token");
            if ((obj == MutexKt.SELECT_SUCCESS ? true : null) != null) {
                ContinuationKt.startCoroutine(this.block, this.mutex, this.select.getCompletion());
                return;
            }
            throw ((Throwable) new IllegalStateException("Check failed.".toString()));
        }

        @NotNull
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("LockSelect[");
            stringBuilder.append(this.owner);
            stringBuilder.append(", ");
            stringBuilder.append(this.mutex);
            stringBuilder.append(", ");
            stringBuilder.append(this.select);
            stringBuilder.append(']');
            return stringBuilder.toString();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016R\u0012\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$LockedQueue;", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "owner", "", "(Ljava/lang/Object;)V", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Mutex.kt */
    private static final class LockedQueue extends LockFreeLinkedListHead {
        @NotNull
        @JvmField
        public Object owner;

        public LockedQueue(@NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(obj, "owner");
            this.owner = obj;
        }

        @NotNull
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("LockedQueue[");
            stringBuilder.append(this.owner);
            stringBuilder.append(']');
            return stringBuilder.toString();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0003`\u0004BT\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\f\u0012\"\u0010\r\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0010\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000eø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u001a\u0010\u0012\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0014R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$TryEnqueueLockDesc;", "R", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/sync/MutexImpl$LockSelect;", "Lkotlinx/coroutines/internal/AddLastDesc;", "mutex", "Lkotlinx/coroutines/sync/MutexImpl;", "owner", "", "queue", "Lkotlinx/coroutines/sync/MutexImpl$LockedQueue;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/sync/Mutex;", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/sync/MutexImpl;Ljava/lang/Object;Lkotlinx/coroutines/sync/MutexImpl$LockedQueue;Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "onPrepare", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "next", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Mutex.kt */
    private static final class TryEnqueueLockDesc<R> extends AddLastDesc<LockSelect<R>> {
        @NotNull
        @JvmField
        public final MutexImpl mutex;

        public TryEnqueueLockDesc(@NotNull MutexImpl mutexImpl, @Nullable Object obj, @NotNull LockedQueue lockedQueue, @NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super Mutex, ? super Continuation<? super R>, ? extends Object> function2) {
            Intrinsics.checkParameterIsNotNull(mutexImpl, "mutex");
            Intrinsics.checkParameterIsNotNull(lockedQueue, "queue");
            Intrinsics.checkParameterIsNotNull(selectInstance, "select");
            Intrinsics.checkParameterIsNotNull(function2, "block");
            super(lockedQueue, new LockSelect(obj, mutexImpl, selectInstance, function2));
            this.mutex = mutexImpl;
        }

        @Nullable
        protected Object onPrepare(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode2) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode2, "next");
            if (this.mutex._state != this.queue) {
                return MutexKt.ENQUEUE_FAIL;
            }
            return super.onPrepare(lockFreeLinkedListNode, lockFreeLinkedListNode2);
        }
    }

    public MutexImpl(boolean z) {
        this._state = z ? MutexKt.EmptyLocked : MutexKt.EmptyUnlocked;
    }

    public final boolean isLockedEmptyQueueState$kotlinx_coroutines_core() {
        Object obj = this._state;
        return (obj instanceof LockedQueue) && ((LockedQueue) obj).isEmpty();
    }

    @Nullable
    public Object lock(@Nullable Object obj, @NotNull Continuation<? super Unit> continuation) {
        if (tryLock(obj)) {
            return Unit.INSTANCE;
        }
        return lockSuspend(obj, continuation);
    }

    @NotNull
    public SelectClause2<Object, Mutex> getOnLock() {
        return this;
    }

    public <R> void registerSelectClause2(@NotNull SelectInstance<? super R> selectInstance, @Nullable Object obj, @NotNull Function2<? super Mutex, ? super Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(selectInstance, "select");
        Intrinsics.checkParameterIsNotNull(function2, "block");
        while (!selectInstance.isSelected()) {
            Object obj2 = this._state;
            if (obj2 instanceof Empty) {
                Empty empty = (Empty) obj2;
                if (empty.locked != MutexKt.UNLOCKED) {
                    _state$FU.compareAndSet(this, obj2, new LockedQueue(empty.locked));
                } else {
                    Symbol performAtomicTrySelect = selectInstance.performAtomicTrySelect(new TryLockDesc(this, obj));
                    if (performAtomicTrySelect == null) {
                        UndispatchedKt.startCoroutineUnintercepted(function2, this, selectInstance.getCompletion());
                        return;
                    } else if (performAtomicTrySelect != SelectKt.getALREADY_SELECTED()) {
                        if (performAtomicTrySelect != MutexKt.LOCK_FAIL) {
                            selectInstance = new StringBuilder();
                            selectInstance.append("performAtomicTrySelect(TryLockDesc) returned ");
                            selectInstance.append(performAtomicTrySelect);
                            throw ((Throwable) new IllegalStateException(selectInstance.toString().toString()));
                        }
                    } else {
                        return;
                    }
                }
            } else if (obj2 instanceof LockedQueue) {
                LockedQueue lockedQueue = (LockedQueue) obj2;
                if ((lockedQueue.owner != obj ? 1 : null) != null) {
                    TryEnqueueLockDesc tryEnqueueLockDesc = new TryEnqueueLockDesc(this, obj, lockedQueue, selectInstance, function2);
                    Symbol performAtomicIfNotSelected = selectInstance.performAtomicIfNotSelected(tryEnqueueLockDesc);
                    if (performAtomicIfNotSelected == null) {
                        selectInstance.disposeOnSelect((DisposableHandle) tryEnqueueLockDesc.node);
                        return;
                    } else if (performAtomicIfNotSelected != SelectKt.getALREADY_SELECTED()) {
                        if (performAtomicIfNotSelected != MutexKt.ENQUEUE_FAIL) {
                            selectInstance = new StringBuilder();
                            selectInstance.append("performAtomicIfNotSelected(TryEnqueueLockDesc) returned ");
                            selectInstance.append(performAtomicIfNotSelected);
                            throw ((Throwable) new IllegalStateException(selectInstance.toString().toString()));
                        }
                    } else {
                        return;
                    }
                }
                selectInstance = new StringBuilder();
                selectInstance.append("Already locked by ");
                selectInstance.append(obj);
                throw ((Throwable) new IllegalStateException(selectInstance.toString().toString()));
            } else if (obj2 instanceof OpDescriptor) {
                ((OpDescriptor) obj2).perform(this);
            } else {
                selectInstance = new StringBuilder();
                selectInstance.append("Illegal state ");
                selectInstance.append(obj2);
                throw ((Throwable) new IllegalStateException(selectInstance.toString().toString()));
            }
        }
    }

    public boolean holdsLock(@NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "owner");
        Object obj2 = this._state;
        if (obj2 instanceof Empty) {
            if (((Empty) obj2).locked != obj) {
                return false;
            }
        } else if (!(obj2 instanceof LockedQueue) || ((LockedQueue) obj2).owner != obj) {
            return false;
        }
        return true;
    }

    public boolean isLocked() {
        boolean z;
        while (true) {
            Object obj = this._state;
            z = true;
            if (obj instanceof Empty) {
                break;
            } else if (obj instanceof LockedQueue) {
                return true;
            } else {
                if (obj instanceof OpDescriptor) {
                    ((OpDescriptor) obj).perform(this);
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Illegal state ");
                    stringBuilder.append(obj);
                    throw new IllegalStateException(stringBuilder.toString().toString());
                }
            }
        }
        if (((Empty) obj).locked == MutexKt.UNLOCKED) {
            z = false;
        }
        return z;
    }

    public boolean tryLock(@Nullable Object obj) {
        while (true) {
            Object obj2 = this._state;
            boolean z = true;
            if (obj2 instanceof Empty) {
                if (((Empty) obj2).locked != MutexKt.UNLOCKED) {
                    return false;
                }
                if (_state$FU.compareAndSet(this, obj2, obj == null ? MutexKt.EmptyLocked : new Empty(obj))) {
                    return true;
                }
            } else if (obj2 instanceof LockedQueue) {
                break;
            } else if (obj2 instanceof OpDescriptor) {
                ((OpDescriptor) obj2).perform(this);
            } else {
                obj = new StringBuilder();
                obj.append("Illegal state ");
                obj.append(obj2);
                throw new IllegalStateException(obj.toString().toString());
            }
        }
        if (((LockedQueue) obj2).owner == obj) {
            z = false;
        }
        if (z) {
            return false;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Already locked by ");
        stringBuilder.append(obj);
        throw new IllegalStateException(stringBuilder.toString().toString());
    }

    @Nullable
    final /* synthetic */ Object lockSuspend(@Nullable Object obj, @NotNull Continuation<? super Unit> continuation) {
        MutexImpl mutexImpl = this;
        Object obj2 = obj;
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 0);
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        LockCont lockCont = new LockCont(obj2, cancellableContinuation);
        while (true) {
            Object obj3 = mutexImpl._state;
            if (obj3 instanceof Empty) {
                Empty empty = (Empty) obj3;
                if (empty.locked != MutexKt.UNLOCKED) {
                    _state$FU.compareAndSet(mutexImpl, obj3, new LockedQueue(empty.locked));
                } else {
                    if (_state$FU.compareAndSet(mutexImpl, obj3, obj2 == null ? MutexKt.EmptyLocked : new Empty(obj2))) {
                        Continuation continuation2 = cancellableContinuation;
                        Unit unit = Unit.INSTANCE;
                        Companion companion = Result.Companion;
                        continuation2.resumeWith(Result.constructor-impl(unit));
                    }
                }
            } else if (obj3 instanceof LockedQueue) {
                LockedQueue lockedQueue = (LockedQueue) obj3;
                Object obj4 = 1;
                if ((lockedQueue.owner != obj2 ? 1 : null) != null) {
                    LockFreeLinkedListNode lockFreeLinkedListNode = lockCont;
                    LockFreeLinkedListNode lockFreeLinkedListNode2 = lockFreeLinkedListNode;
                    CondAddOp c0340x426da4aa = new C0340x426da4aa(lockFreeLinkedListNode, lockFreeLinkedListNode, obj3, cancellableContinuation, lockCont, this, obj);
                    while (true) {
                        Object prev = lockedQueue.getPrev();
                        if (prev != null) {
                            switch (((LockFreeLinkedListNode) prev).tryCondAddNext(lockFreeLinkedListNode2, lockedQueue, c0340x426da4aa)) {
                                case 1:
                                    break;
                                case 2:
                                    obj4 = null;
                                    break;
                                default:
                            }
                            if (obj4 != null) {
                                cancellableContinuation.initCancellability();
                                CancellableContinuationKt.removeOnCancellation(cancellableContinuation, lockFreeLinkedListNode2);
                            }
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
                        }
                    }
                }
                r0 = new StringBuilder();
                r0.append("Already locked by ");
                r0.append(obj2);
                throw new IllegalStateException(r0.toString().toString());
            } else if (obj3 instanceof OpDescriptor) {
                ((OpDescriptor) obj3).perform(mutexImpl);
            } else {
                r0 = new StringBuilder();
                r0.append("Illegal state ");
                r0.append(obj3);
                throw new IllegalStateException(r0.toString().toString());
            }
            Object result = cancellableContinuationImpl.getResult();
            if (result == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return result;
        }
    }

    public void unlock(@Nullable Object obj) {
        Object obj2;
        LockedQueue lockedQueue;
        LockWaiter lockWaiter;
        while (true) {
            obj2 = this._state;
            Object obj3 = null;
            StringBuilder stringBuilder;
            if (obj2 instanceof Empty) {
                if (obj == null) {
                    if (((Empty) obj2).locked != MutexKt.UNLOCKED) {
                        obj3 = 1;
                    }
                    if (obj3 == null) {
                        throw ((Throwable) new IllegalStateException("Mutex is not locked".toString()));
                    }
                } else {
                    Empty empty = (Empty) obj2;
                    if (empty.locked == obj) {
                        obj3 = 1;
                    }
                    if (obj3 == null) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Mutex is locked by ");
                        stringBuilder.append(empty.locked);
                        stringBuilder.append(" but expected ");
                        stringBuilder.append(obj);
                        throw new IllegalStateException(stringBuilder.toString().toString());
                    }
                }
                if (_state$FU.compareAndSet(this, obj2, MutexKt.EmptyUnlocked)) {
                    return;
                }
            } else if (obj2 instanceof OpDescriptor) {
                ((OpDescriptor) obj2).perform(this);
            } else if (obj2 instanceof LockedQueue) {
                if (obj != null) {
                    lockedQueue = (LockedQueue) obj2;
                    if (lockedQueue.owner == obj) {
                        obj3 = 1;
                    }
                    if (obj3 == null) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Mutex is locked by ");
                        stringBuilder.append(lockedQueue.owner);
                        stringBuilder.append(" but expected ");
                        stringBuilder.append(obj);
                        throw new IllegalStateException(stringBuilder.toString().toString());
                    }
                }
                lockedQueue = (LockedQueue) obj2;
                LockFreeLinkedListNode removeFirstOrNull = lockedQueue.removeFirstOrNull();
                if (removeFirstOrNull == null) {
                    UnlockOp unlockOp = new UnlockOp(lockedQueue);
                    if (_state$FU.compareAndSet(this, obj2, unlockOp) && unlockOp.perform(this) == null) {
                        return;
                    }
                }
                lockWaiter = (LockWaiter) removeFirstOrNull;
                obj2 = lockWaiter.tryResumeLockWaiter();
                if (obj2 != null) {
                    break;
                }
            } else {
                obj = new StringBuilder();
                obj.append("Illegal state ");
                obj.append(obj2);
                throw new IllegalStateException(obj.toString().toString());
            }
        }
        obj = lockWaiter.owner;
        if (obj == null) {
            obj = MutexKt.LOCKED;
        }
        lockedQueue.owner = obj;
        lockWaiter.completeResumeLockWaiter(obj2);
    }

    @NotNull
    public String toString() {
        while (true) {
            Object obj = this._state;
            if (!(obj instanceof Empty)) {
                if (!(obj instanceof OpDescriptor)) {
                    break;
                }
                ((OpDescriptor) obj).perform(this);
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Mutex[");
                stringBuilder.append(((Empty) obj).locked);
                stringBuilder.append(']');
                return stringBuilder.toString();
            }
        }
        if (obj instanceof LockedQueue) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Mutex[");
            stringBuilder.append(((LockedQueue) obj).owner);
            stringBuilder.append(']');
            return stringBuilder.toString();
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("Illegal state ");
        stringBuilder.append(obj);
        throw new IllegalStateException(stringBuilder.toString().toString());
    }
}
