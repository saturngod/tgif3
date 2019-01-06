package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u000e\b\u0000\u0018\u0000 \"*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002:\u0002\"#B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0013\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00028\u0000¢\u0006\u0002\u0010\u0013J \u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0000j\b\u0012\u0004\u0012\u00028\u0000`\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J \u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0000j\b\u0012\u0004\u0012\u00028\u0000`\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0006\u0010\u0018\u001a\u00020\u000eJ1\u0010\u0019\u001a\u0016\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\b2\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u001bJ\b\u0010\u001c\u001a\u00020\u0016H\u0002J\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000J\b\u0010\u001e\u001a\u0004\u0018\u00010\u0002J,\u0010\u001f\u001a\u0016\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\b2\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0004H\u0002R(\u0010\u0006\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\b0\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeMPSCQueueCore;", "E", "", "capacity", "", "(I)V", "_next", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/internal/Core;", "_state", "Lkotlinx/atomicfu/AtomicLong;", "array", "Ljava/util/concurrent/atomic/AtomicReferenceArray;", "isEmpty", "", "()Z", "mask", "addLast", "element", "(Ljava/lang/Object;)I", "allocateNextCopy", "state", "", "allocateOrGetNextCopy", "close", "fillPlaceholder", "index", "(ILjava/lang/Object;)Lkotlinx/coroutines/internal/LockFreeMPSCQueueCore;", "markFrozen", "next", "removeFirstOrNull", "removeSlowPath", "oldHead", "newHead", "Companion", "Placeholder", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: LockFreeMPSCQueue.kt */
public final class LockFreeMPSCQueueCore<E> {
    public static final int ADD_CLOSED = 2;
    public static final int ADD_FROZEN = 1;
    public static final int ADD_SUCCESS = 0;
    private static final int CAPACITY_BITS = 30;
    private static final long CLOSED_MASK = 2305843009213693952L;
    private static final int CLOSED_SHIFT = 61;
    public static final Companion Companion = new Companion();
    private static final long FROZEN_MASK = 1152921504606846976L;
    private static final int FROZEN_SHIFT = 60;
    private static final long HEAD_MASK = 1073741823;
    private static final int HEAD_SHIFT = 0;
    public static final int INITIAL_CAPACITY = 8;
    private static final int MAX_CAPACITY_MASK = 1073741823;
    @NotNull
    @JvmField
    public static final Symbol REMOVE_FROZEN = new Symbol("REMOVE_FROZEN");
    private static final long TAIL_MASK = 1152921503533105152L;
    private static final int TAIL_SHIFT = 30;
    private static final AtomicReferenceFieldUpdater _next$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeMPSCQueueCore.class, Object.class, "_next");
    private static final AtomicLongFieldUpdater _state$FU = AtomicLongFieldUpdater.newUpdater(LockFreeMPSCQueueCore.class, "_state");
    private volatile Object _next = 0;
    private volatile long _state = 0;
    private final AtomicReferenceArray<Object> array = new AtomicReferenceArray(this.capacity);
    private final int capacity;
    private final int mask = (this.capacity - 1);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u0010\u0015\u001a\u00020\u0004*\u00020\tH\u0002J\u0014\u0010\u0016\u001a\u00020\t*\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0004H\u0002J\u0014\u0010\u0018\u001a\u00020\t*\u00020\t2\u0006\u0010\u0019\u001a\u00020\u0004H\u0002JP\u0010\u001a\u001a\u0002H\u001b\"\u0004\b\u0001\u0010\u001b*\u00020\t26\u0010\u001c\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(!\u0012\u0004\u0012\u0002H\u001b0\u001dH\b¢\u0006\u0002\u0010\"J\u0015\u0010#\u001a\u00020\t*\u00020\t2\u0006\u0010$\u001a\u00020\tH\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u00128\u0000X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeMPSCQueueCore$Companion;", "", "()V", "ADD_CLOSED", "", "ADD_FROZEN", "ADD_SUCCESS", "CAPACITY_BITS", "CLOSED_MASK", "", "CLOSED_SHIFT", "FROZEN_MASK", "FROZEN_SHIFT", "HEAD_MASK", "HEAD_SHIFT", "INITIAL_CAPACITY", "MAX_CAPACITY_MASK", "REMOVE_FROZEN", "Lkotlinx/coroutines/internal/Symbol;", "TAIL_MASK", "TAIL_SHIFT", "addFailReason", "updateHead", "newHead", "updateTail", "newTail", "withState", "T", "block", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "head", "tail", "(JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "wo", "other", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: LockFreeMPSCQueue.kt */
    public static final class Companion {
        private final int addFailReason(long j) {
            return (j & LockFreeMPSCQueueCore.CLOSED_MASK) != 0 ? 2 : 1;
        }

        private final long wo(long j, long j2) {
            return j & (~j2);
        }

        private Companion() {
        }

        private final long updateHead(long j, int i) {
            return wo(j, LockFreeMPSCQueueCore.HEAD_MASK) | (((long) i) << 0);
        }

        private final long updateTail(long j, int i) {
            return wo(j, LockFreeMPSCQueueCore.TAIL_MASK) | (((long) i) << 30);
        }

        private final <T> T withState(long j, Function2<? super Integer, ? super Integer, ? extends T> function2) {
            return function2.invoke(Integer.valueOf((int) ((LockFreeMPSCQueueCore.HEAD_MASK & j) >> null)), Integer.valueOf((int) ((j & LockFreeMPSCQueueCore.TAIL_MASK) >> 30)));
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeMPSCQueueCore$Placeholder;", "", "index", "", "(I)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: LockFreeMPSCQueue.kt */
    private static final class Placeholder {
        @JvmField
        public final int index;

        public Placeholder(int i) {
            this.index = i;
        }
    }

    public LockFreeMPSCQueueCore(int i) {
        this.capacity = i;
        int i2 = 1;
        if ((this.mask <= MAX_CAPACITY_MASK ? 1 : 0) != 0) {
            if ((this.capacity & this.mask) != 0) {
                i2 = 0;
            }
            if (i2 == 0) {
                throw ((Throwable) new IllegalStateException("Check failed.".toString()));
            }
            return;
        }
        throw ((Throwable) new IllegalStateException("Check failed.".toString()));
    }

    public final boolean isEmpty() {
        Companion companion = Companion;
        long j = this._state;
        return ((int) ((HEAD_MASK & j) >> 0)) == ((int) ((j & TAIL_MASK) >> 30));
    }

    private final LockFreeMPSCQueueCore<E> fillPlaceholder(int i, E e) {
        Object obj = this.array.get(this.mask & i);
        if (!(obj instanceof Placeholder) || ((Placeholder) obj).index != i) {
            return 0;
        }
        this.array.set(i & this.mask, e);
        return this;
    }

    @NotNull
    public final LockFreeMPSCQueueCore<E> next() {
        return allocateOrGetNextCopy(markFrozen());
    }

    private final LockFreeMPSCQueueCore<E> allocateNextCopy(long j) {
        LockFreeMPSCQueueCore<E> lockFreeMPSCQueueCore = new LockFreeMPSCQueueCore(this.capacity * 2);
        Companion companion = Companion;
        int i = (int) ((TAIL_MASK & j) >> 30);
        for (int i2 = (int) ((HEAD_MASK & j) >> null); (this.mask & i2) != (this.mask & i); i2++) {
            AtomicReferenceArray atomicReferenceArray = lockFreeMPSCQueueCore.array;
            int i3 = lockFreeMPSCQueueCore.mask & i2;
            Object obj = this.array.get(this.mask & i2);
            if (obj == null) {
                obj = new Placeholder(i2);
            }
            atomicReferenceArray.set(i3, obj);
        }
        lockFreeMPSCQueueCore._state = Companion.wo(j, FROZEN_MASK);
        return lockFreeMPSCQueueCore;
    }

    public final boolean close() {
        long j;
        do {
            j = this._state;
            if ((j & CLOSED_MASK) != 0) {
                return true;
            }
            if ((FROZEN_MASK & j) != 0) {
                return false;
            }
        } while (!_state$FU.compareAndSet(this, j, j | CLOSED_MASK));
        return true;
    }

    public final int addLast(@NotNull E e) {
        int i;
        Intrinsics.checkParameterIsNotNull(e, "element");
        long j;
        int i2;
        do {
            j = this._state;
            if ((3458764513820540928L & j) != 0) {
                return Companion.addFailReason(j);
            }
            Companion companion = Companion;
            i = (int) ((TAIL_MASK & j) >> 30);
            if (((i + 2) & this.mask) == (((int) ((HEAD_MASK & j) >> 0)) & this.mask)) {
                return 1;
            }
            i2 = (i + 1) & MAX_CAPACITY_MASK;
        } while (!_state$FU.compareAndSet(this, j, Companion.updateTail(j, i2)));
        this.array.set(this.mask & i, e);
        LockFreeMPSCQueueCore lockFreeMPSCQueueCore = this;
        while ((lockFreeMPSCQueueCore._state & FROZEN_MASK) != 0) {
            lockFreeMPSCQueueCore = lockFreeMPSCQueueCore.next().fillPlaceholder(i, e);
            if (lockFreeMPSCQueueCore == null) {
                break;
            }
        }
        return 0;
    }

    @Nullable
    public final Object removeFirstOrNull() {
        long j = this._state;
        if ((FROZEN_MASK & j) != 0) {
            return REMOVE_FROZEN;
        }
        Companion companion = Companion;
        int i = (int) ((HEAD_MASK & j) >> null);
        if ((((int) ((TAIL_MASK & j) >> 30)) & this.mask) == (this.mask & i)) {
            return null;
        }
        Object obj = this.array.get(this.mask & i);
        if (obj == null || (obj instanceof Placeholder)) {
            return null;
        }
        int i2 = (i + 1) & MAX_CAPACITY_MASK;
        if (_state$FU.compareAndSet(this, j, Companion.updateHead(j, i2))) {
            this.array.set(this.mask & i, null);
            return obj;
        }
        LockFreeMPSCQueueCore lockFreeMPSCQueueCore = this;
        while (true) {
            lockFreeMPSCQueueCore = lockFreeMPSCQueueCore.removeSlowPath(i, i2);
            if (lockFreeMPSCQueueCore == null) {
                return obj;
            }
        }
    }

    private final LockFreeMPSCQueueCore<E> removeSlowPath(int i, int i2) {
        int i3;
        long j;
        do {
            j = this._state;
            Companion companion = Companion;
            Object obj = null;
            i3 = (int) ((HEAD_MASK & j) >> 0);
            if (i3 == i) {
                obj = 1;
            }
            if (obj == null) {
                throw ((Throwable) new IllegalStateException("This queue can have only one consumer".toString()));
            } else if ((FROZEN_MASK & j) != 0) {
                return next();
            }
        } while (!_state$FU.compareAndSet(this, j, Companion.updateHead(j, i2)));
        this.array.set(this.mask & i3, null);
        return null;
    }

    private final long markFrozen() {
        long j;
        long j2;
        do {
            j2 = this._state;
            if ((j2 & FROZEN_MASK) != 0) {
                return j2;
            }
            j = j2 | FROZEN_MASK;
        } while (!_state$FU.compareAndSet(this, j2, j));
        return j;
    }

    private final LockFreeMPSCQueueCore<E> allocateOrGetNextCopy(long j) {
        while (true) {
            LockFreeMPSCQueueCore<E> lockFreeMPSCQueueCore = (LockFreeMPSCQueueCore) this._next;
            if (lockFreeMPSCQueueCore != null) {
                return lockFreeMPSCQueueCore;
            }
            _next$FU.compareAndSet(this, null, allocateNextCopy(j));
        }
    }
}
