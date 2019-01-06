package kotlinx.coroutines.channels;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlinx.coroutines.internal.ConcurrentKt;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u00016B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010 \u001a\u00020\u00172\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010#\u001a\u00020$H\u0002J\u0012\u0010%\u001a\u00020\u00172\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010&\u001a\u00020\u0015H\u0002J\u0015\u0010'\u001a\u00028\u00002\u0006\u0010(\u001a\u00020\u0015H\u0002¢\u0006\u0002\u0010)J\u0015\u0010*\u001a\u00020\t2\u0006\u0010+\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010,J!\u0010-\u001a\u00020\t2\u0006\u0010+\u001a\u00028\u00002\n\u0010.\u001a\u0006\u0012\u0002\b\u00030/H\u0014¢\u0006\u0002\u00100J\u000e\u00101\u001a\b\u0012\u0004\u0012\u00028\u000002H\u0016J-\u00103\u001a\u00020$2\u0010\b\u0002\u00104\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u001d2\u0010\b\u0002\u00105\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u001dH\u0010R\u0018\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0004¢\u0006\u0004\n\u0002\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8TX\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0012\u0010\u000f\u001a\u00060\u0010j\u0002`\u0011X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\u00020\u00178TX\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00178TX\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0018R\u000e\u0010\u001a\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R*\u0010\u001b\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001d0\u001cj\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001d`\u001eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0015X\u000e¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lkotlinx/coroutines/channels/ArrayBroadcastChannel;", "E", "Lkotlinx/coroutines/channels/AbstractSendChannel;", "Lkotlinx/coroutines/channels/BroadcastChannel;", "capacity", "", "(I)V", "buffer", "", "", "[Ljava/lang/Object;", "bufferDebugString", "", "getBufferDebugString", "()Ljava/lang/String;", "bufferLock", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/internal/ReentrantLock;", "getCapacity", "()I", "head", "", "isBufferAlwaysFull", "", "()Z", "isBufferFull", "size", "subscribers", "", "Lkotlinx/coroutines/channels/ArrayBroadcastChannel$Subscriber;", "Lkotlinx/coroutines/internal/SubscribersList;", "tail", "cancel", "cause", "", "checkSubOffers", "", "close", "computeMinHead", "elementAt", "index", "(J)Ljava/lang/Object;", "offerInternal", "element", "(Ljava/lang/Object;)Ljava/lang/Object;", "offerSelectInternal", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "openSubscription", "Lkotlinx/coroutines/channels/ReceiveChannel;", "updateHead", "addSub", "removeSub", "Subscriber", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: ArrayBroadcastChannel.kt */
public final class ArrayBroadcastChannel<E> extends AbstractSendChannel<E> implements BroadcastChannel<E> {
    private final Object[] buffer;
    private final ReentrantLock bufferLock;
    private final int capacity;
    private volatile long head;
    private volatile int size;
    private final List<Subscriber<E>> subscribers;
    private volatile long tail;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u0012\u001a\u00020\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0006\u0010\u0015\u001a\u00020\bJ\b\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\bH\u0002J\n\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002J\n\u0010\u001b\u001a\u0004\u0018\u00010\u001aH\u0014J\u0016\u0010\u001c\u001a\u0004\u0018\u00010\u001a2\n\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\u001eH\u0014R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\b8TX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\tR\u0014\u0010\n\u001a\u00020\b8TX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\tR\u0014\u0010\u000b\u001a\u00020\b8TX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0014\u0010\f\u001a\u00020\b8TX\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\tR\u0012\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u00060\u0010j\u0002`\u0011X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lkotlinx/coroutines/channels/ArrayBroadcastChannel$Subscriber;", "E", "Lkotlinx/coroutines/channels/AbstractChannel;", "Lkotlinx/coroutines/channels/ReceiveChannel;", "broadcastChannel", "Lkotlinx/coroutines/channels/ArrayBroadcastChannel;", "(Lkotlinx/coroutines/channels/ArrayBroadcastChannel;)V", "isBufferAlwaysEmpty", "", "()Z", "isBufferAlwaysFull", "isBufferEmpty", "isBufferFull", "subHead", "", "subLock", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/internal/ReentrantLock;", "cancel", "cause", "", "checkOffer", "clearBuffer", "", "needsToCheckOfferWithoutLock", "peekUnderLock", "", "pollInternal", "pollSelectInternal", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: ArrayBroadcastChannel.kt */
    private static final class Subscriber<E> extends AbstractChannel<E> implements ReceiveChannel<E> {
        private final ArrayBroadcastChannel<E> broadcastChannel;
        @JvmField
        public volatile long subHead;
        private final ReentrantLock subLock = new ReentrantLock();

        protected boolean isBufferAlwaysEmpty() {
            return false;
        }

        public Subscriber(@NotNull ArrayBroadcastChannel<E> arrayBroadcastChannel) {
            Intrinsics.checkParameterIsNotNull(arrayBroadcastChannel, "broadcastChannel");
            this.broadcastChannel = arrayBroadcastChannel;
        }

        protected boolean isBufferEmpty() {
            return this.subHead >= this.broadcastChannel.tail;
        }

        protected boolean isBufferAlwaysFull() {
            throw new IllegalStateException("Should not be used".toString());
        }

        protected boolean isBufferFull() {
            throw new IllegalStateException("Should not be used".toString());
        }

        public boolean cancel(@Nullable Throwable th) {
            th = close(th);
            if (th != null) {
                ArrayBroadcastChannel.updateHead$default(this.broadcastChannel, null, this, 1, null);
            }
            clearBuffer();
            return th;
        }

        private final void clearBuffer() {
            Lock lock = this.subLock;
            lock.lock();
            try {
                this.subHead = this.broadcastChannel.tail;
                Unit unit = Unit.INSTANCE;
            } finally {
                lock.unlock();
            }
        }

        public final boolean checkOffer() {
            Closed closed = (Closed) null;
            boolean z = false;
            while (needsToCheckOfferWithoutLock()) {
                if (!this.subLock.tryLock()) {
                    break;
                }
                Object peekUnderLock = peekUnderLock();
                if (peekUnderLock != AbstractChannelKt.POLL_FAILED) {
                    try {
                        if (peekUnderLock instanceof Closed) {
                            closed = (Closed) peekUnderLock;
                        } else {
                            ReceiveOrClosed takeFirstReceiveOrPeekClosed = takeFirstReceiveOrPeekClosed();
                            if (takeFirstReceiveOrPeekClosed != null) {
                                if (!(takeFirstReceiveOrPeekClosed instanceof Closed)) {
                                    peekUnderLock = takeFirstReceiveOrPeekClosed.tryResumeReceive(peekUnderLock, null);
                                    if (peekUnderLock != null) {
                                        this.subHead++;
                                        z = true;
                                        this.subLock.unlock();
                                        if (takeFirstReceiveOrPeekClosed == null) {
                                            Intrinsics.throwNpe();
                                        }
                                        takeFirstReceiveOrPeekClosed.completeResumeReceive(peekUnderLock);
                                    }
                                }
                            }
                        }
                        this.subLock.unlock();
                        break;
                    } catch (Throwable th) {
                        this.subLock.unlock();
                    }
                }
                this.subLock.unlock();
            }
            if (closed != null) {
                close(closed.closeCause);
            }
            return z;
        }

        @Nullable
        protected Object pollInternal() {
            Lock lock = this.subLock;
            lock.lock();
            try {
                Object obj;
                Closed closed;
                Subscriber peekUnderLock = peekUnderLock();
                if (!(peekUnderLock instanceof Closed)) {
                    if (peekUnderLock != AbstractChannelKt.POLL_FAILED) {
                        this.subHead++;
                        obj = 1;
                        lock.unlock();
                        closed = (Closed) (peekUnderLock instanceof Closed ? null : peekUnderLock);
                        if (closed != null) {
                            close(closed.closeCause);
                        }
                        if (checkOffer()) {
                            obj = 1;
                        }
                        if (obj != null) {
                            ArrayBroadcastChannel.updateHead$default(this.broadcastChannel, null, null, 3, null);
                        }
                        return peekUnderLock;
                    }
                }
                obj = null;
                lock.unlock();
                if (peekUnderLock instanceof Closed) {
                }
                closed = (Closed) (peekUnderLock instanceof Closed ? null : peekUnderLock);
                if (closed != null) {
                    close(closed.closeCause);
                }
                if (checkOffer()) {
                    obj = 1;
                }
                if (obj != null) {
                    ArrayBroadcastChannel.updateHead$default(this.broadcastChannel, null, null, 3, null);
                }
                return peekUnderLock;
            } catch (Throwable th) {
                lock.unlock();
            }
        }

        @Nullable
        protected Object pollSelectInternal(@NotNull SelectInstance<?> selectInstance) {
            Intrinsics.checkParameterIsNotNull(selectInstance, "select");
            Lock lock = this.subLock;
            lock.lock();
            try {
                SelectInstance<?> peekUnderLock = peekUnderLock();
                Object obj = 1;
                Object obj2 = null;
                if (!(peekUnderLock instanceof Closed)) {
                    if (peekUnderLock != AbstractChannelKt.POLL_FAILED) {
                        if (selectInstance.trySelect(null) == null) {
                            peekUnderLock = SelectKt.getALREADY_SELECTED();
                        } else {
                            this.subHead++;
                            obj2 = 1;
                        }
                    }
                }
                lock.unlock();
                Closed closed = (Closed) ((peekUnderLock instanceof Closed) == null ? null : peekUnderLock);
                if (closed != null) {
                    close(closed.closeCause);
                }
                if (checkOffer() == null) {
                    obj = obj2;
                }
                if (obj != null) {
                    ArrayBroadcastChannel.updateHead$default(this.broadcastChannel, null, null, 3, null);
                }
                return peekUnderLock;
            } catch (Throwable th) {
                lock.unlock();
            }
        }

        private final boolean needsToCheckOfferWithoutLock() {
            if (getClosedForReceive() != null) {
                return false;
            }
            if (isBufferEmpty() && this.broadcastChannel.getClosedForReceive() == null) {
                return false;
            }
            return true;
        }

        private final Object peekUnderLock() {
            long j = this.subHead;
            Object closedForReceive = this.broadcastChannel.getClosedForReceive();
            if (j >= this.broadcastChannel.tail) {
                if (closedForReceive == null) {
                    closedForReceive = getClosedForReceive();
                }
                if (closedForReceive == null) {
                    closedForReceive = AbstractChannelKt.POLL_FAILED;
                }
                return closedForReceive;
            }
            Object access$elementAt = this.broadcastChannel.elementAt(j);
            Closed closedForReceive2 = getClosedForReceive();
            return closedForReceive2 != null ? closedForReceive2 : access$elementAt;
        }
    }

    protected boolean isBufferAlwaysFull() {
        return false;
    }

    public final int getCapacity() {
        return this.capacity;
    }

    public ArrayBroadcastChannel(int i) {
        this.capacity = i;
        Object obj = 1;
        if (this.capacity < 1) {
            obj = null;
        }
        if (obj != null) {
            this.bufferLock = new ReentrantLock();
            this.buffer = new Object[this.capacity];
            this.subscribers = ConcurrentKt.subscriberList();
            return;
        }
        i = new StringBuilder();
        i.append("ArrayBroadcastChannel capacity must be at least 1, but ");
        i.append(this.capacity);
        i.append(" was specified");
        throw new IllegalArgumentException(i.toString().toString());
    }

    protected boolean isBufferFull() {
        return this.size >= this.capacity;
    }

    @NotNull
    public ReceiveChannel<E> openSubscription() {
        Subscriber subscriber = new Subscriber(this);
        updateHead$default(this, subscriber, null, 2, null);
        return subscriber;
    }

    public boolean close(@Nullable Throwable th) {
        if (super.close(th) == null) {
            return null;
        }
        checkSubOffers();
        return true;
    }

    public boolean cancel(@Nullable Throwable th) {
        boolean close = close(th);
        for (Subscriber cancel : this.subscribers) {
            cancel.cancel(th);
        }
        return close;
    }

    @NotNull
    protected Object offerInternal(E e) {
        Lock lock = this.bufferLock;
        lock.lock();
        try {
            Object closedForSend = getClosedForSend();
            if (closedForSend != null) {
                return closedForSend;
            }
            closedForSend = this.size;
            if (closedForSend >= this.capacity) {
                e = AbstractChannelKt.OFFER_FAILED;
                lock.unlock();
                return e;
            }
            long j = this.tail;
            this.buffer[(int) (j % ((long) this.capacity))] = e;
            closedForSend++;
            this.size = closedForSend;
            this.tail = j + 1;
            e = Unit.INSTANCE;
            lock.unlock();
            checkSubOffers();
            return AbstractChannelKt.OFFER_SUCCESS;
        } finally {
            lock.unlock();
        }
    }

    @NotNull
    protected Object offerSelectInternal(E e, @NotNull SelectInstance<?> selectInstance) {
        Intrinsics.checkParameterIsNotNull(selectInstance, "select");
        Lock lock = this.bufferLock;
        lock.lock();
        try {
            Object closedForSend = getClosedForSend();
            if (closedForSend != null) {
                return closedForSend;
            }
            closedForSend = this.size;
            if (closedForSend >= this.capacity) {
                e = AbstractChannelKt.OFFER_FAILED;
                lock.unlock();
                return e;
            } else if (selectInstance.trySelect(null) == null) {
                e = SelectKt.getALREADY_SELECTED();
                lock.unlock();
                return e;
            } else {
                long j = this.tail;
                this.buffer[(int) (j % ((long) this.capacity))] = e;
                closedForSend++;
                this.size = closedForSend;
                this.tail = j + 1;
                e = Unit.INSTANCE;
                lock.unlock();
                checkSubOffers();
                return AbstractChannelKt.OFFER_SUCCESS;
            }
        } finally {
            lock.unlock();
        }
    }

    private final void checkSubOffers() {
        Object obj = null;
        Object obj2 = null;
        for (Subscriber checkOffer : this.subscribers) {
            if (checkOffer.checkOffer()) {
                obj = 1;
            }
            obj2 = 1;
        }
        if (obj != null || r3 == null) {
            updateHead$default(this, null, null, 3, null);
        }
    }

    static /* synthetic */ void updateHead$default(ArrayBroadcastChannel arrayBroadcastChannel, Subscriber subscriber, Subscriber subscriber2, int i, Object obj) {
        if ((i & 1) != null) {
            subscriber = null;
        }
        if ((i & 2) != 0) {
            subscriber2 = null;
        }
        arrayBroadcastChannel.updateHead(subscriber, subscriber2);
    }

    private final void updateHead(Subscriber<E> subscriber, Subscriber<E> subscriber2) {
        while (true) {
            Send send = (Send) null;
            Lock lock = this.bufferLock;
            lock.lock();
            if (subscriber != null) {
                subscriber.subHead = this.tail;
                boolean isEmpty = this.subscribers.isEmpty();
                this.subscribers.add(subscriber);
                if (!isEmpty) {
                    break;
                }
            }
            if (subscriber2 != null) {
                try {
                    this.subscribers.remove(subscriber2);
                    if (this.head != subscriber2.subHead) {
                        lock.unlock();
                        return;
                    }
                } finally {
                    lock.unlock();
                }
            }
            subscriber = computeMinHead();
            long j = this.tail;
            long j2 = this.head;
            subscriber = RangesKt___RangesKt.coerceAtMost((long) subscriber, j);
            if (subscriber <= j2) {
                lock.unlock();
                return;
            }
            int i = this.size;
            while (j2 < subscriber) {
                this.buffer[(int) (j2 % ((long) this.capacity))] = null;
                Object obj = i >= this.capacity ? 1 : null;
                j2++;
                this.head = j2;
                i--;
                this.size = i;
                if (obj != null) {
                    Object tryResumeSend;
                    Send takeFirstSendOrPeekClosed;
                    do {
                        takeFirstSendOrPeekClosed = takeFirstSendOrPeekClosed();
                        if (takeFirstSendOrPeekClosed == null) {
                            continue;
                        } else if (!(takeFirstSendOrPeekClosed instanceof Closed)) {
                            if (takeFirstSendOrPeekClosed == null) {
                                Intrinsics.throwNpe();
                            }
                            tryResumeSend = takeFirstSendOrPeekClosed.tryResumeSend(null);
                        }
                    } while (tryResumeSend == null);
                    subscriber = this.buffer;
                    subscriber2 = (int) (j % ((long) this.capacity));
                    if (takeFirstSendOrPeekClosed != null) {
                        subscriber[subscriber2] = takeFirstSendOrPeekClosed.getPollResult();
                        this.size = i + 1;
                        this.tail = j + 1;
                        subscriber = Unit.INSTANCE;
                        lock.unlock();
                        if (takeFirstSendOrPeekClosed == null) {
                            Intrinsics.throwNpe();
                        }
                        takeFirstSendOrPeekClosed.completeResumeSend(tryResumeSend);
                        checkSubOffers();
                        subscriber = null;
                        subscriber2 = subscriber;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.channels.Send");
                    }
                }
            }
            lock.unlock();
            return;
        }
    }

    private final long computeMinHead() {
        long j = LongCompanionObject.MAX_VALUE;
        for (Subscriber subscriber : this.subscribers) {
            j = RangesKt___RangesKt.coerceAtMost(j, subscriber.subHead);
        }
        return j;
    }

    private final E elementAt(long j) {
        return this.buffer[(int) (j % ((long) this.capacity))];
    }

    @NotNull
    protected String getBufferDebugString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(buffer:capacity=");
        stringBuilder.append(this.buffer.length);
        stringBuilder.append(",size=");
        stringBuilder.append(this.size);
        stringBuilder.append(')');
        return stringBuilder.toString();
    }
}
