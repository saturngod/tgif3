package kotlinx.coroutines.channels;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.AtomicDesc;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u001b\u001a\u00020\u001cH\u0014J\u0015\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u001fJ!\u0010 \u001a\u00020\b2\u0006\u0010\u001e\u001a\u00028\u00002\n\u0010!\u001a\u0006\u0012\u0002\b\u00030\"H\u0014¢\u0006\u0002\u0010#J\n\u0010$\u001a\u0004\u0018\u00010\bH\u0014J\u0016\u0010%\u001a\u0004\u0018\u00010\b2\n\u0010!\u001a\u0006\u0012\u0002\b\u00030\"H\u0014R\u0018\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8TX\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u00128DX\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00128DX\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u00128DX\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u0014\u0010\u0016\u001a\u00020\u00128DX\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0013R\u0012\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lkotlinx/coroutines/channels/ArrayChannel;", "E", "Lkotlinx/coroutines/channels/AbstractChannel;", "capacity", "", "(I)V", "buffer", "", "", "[Ljava/lang/Object;", "bufferDebugString", "", "getBufferDebugString", "()Ljava/lang/String;", "getCapacity", "()I", "head", "isBufferAlwaysEmpty", "", "()Z", "isBufferAlwaysFull", "isBufferEmpty", "isBufferFull", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/internal/ReentrantLock;", "size", "cleanupSendQueueOnCancel", "", "offerInternal", "element", "(Ljava/lang/Object;)Ljava/lang/Object;", "offerSelectInternal", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "pollInternal", "pollSelectInternal", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: ArrayChannel.kt */
public class ArrayChannel<E> extends AbstractChannel<E> {
    private final Object[] buffer;
    private final int capacity;
    private int head;
    private final ReentrantLock lock;
    private volatile int size;

    protected final boolean isBufferAlwaysEmpty() {
        return false;
    }

    protected final boolean isBufferAlwaysFull() {
        return false;
    }

    public final int getCapacity() {
        return this.capacity;
    }

    public ArrayChannel(int i) {
        this.capacity = i;
        Object obj = 1;
        if (this.capacity < 1) {
            obj = null;
        }
        if (obj != null) {
            this.lock = new ReentrantLock();
            this.buffer = new Object[this.capacity];
            return;
        }
        i = new StringBuilder();
        i.append("ArrayChannel capacity must be at least 1, but ");
        i.append(this.capacity);
        i.append(" was specified");
        throw new IllegalArgumentException(i.toString().toString());
    }

    protected final boolean isBufferEmpty() {
        return this.size == 0;
    }

    protected final boolean isBufferFull() {
        return this.size == this.capacity;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.NotNull
    protected java.lang.Object offerInternal(E r6) {
        /*
        r5 = this;
        r0 = 0;
        r1 = r0;
        r1 = (kotlinx.coroutines.channels.ReceiveOrClosed) r1;
        r1 = r5.lock;
        r1 = (java.util.concurrent.locks.Lock) r1;
        r1.lock();
        r2 = r5.size;	 Catch:{ all -> 0x0070 }
        r3 = r5.getClosedForSend();	 Catch:{ all -> 0x0070 }
        if (r3 == 0) goto L_0x0017;
    L_0x0013:
        r1.unlock();
        return r3;
    L_0x0017:
        r3 = r5.capacity;	 Catch:{ all -> 0x0070 }
        if (r2 >= r3) goto L_0x006a;
    L_0x001b:
        r3 = r2 + 1;
        r5.size = r3;	 Catch:{ all -> 0x0070 }
        if (r2 != 0) goto L_0x005a;
    L_0x0021:
        r3 = r5.takeFirstReceiveOrPeekClosed();	 Catch:{ all -> 0x0070 }
        if (r3 == 0) goto L_0x005a;
    L_0x0027:
        r4 = r3 instanceof kotlinx.coroutines.channels.Closed;	 Catch:{ all -> 0x0070 }
        if (r4 == 0) goto L_0x0036;
    L_0x002b:
        r5.size = r2;	 Catch:{ all -> 0x0070 }
        if (r3 != 0) goto L_0x0032;
    L_0x002f:
        kotlin.jvm.internal.Intrinsics.throwNpe();	 Catch:{ all -> 0x0070 }
    L_0x0032:
        r1.unlock();
        return r3;
    L_0x0036:
        if (r3 != 0) goto L_0x003b;
    L_0x0038:
        kotlin.jvm.internal.Intrinsics.throwNpe();	 Catch:{ all -> 0x0070 }
    L_0x003b:
        r4 = r3.tryResumeReceive(r6, r0);	 Catch:{ all -> 0x0070 }
        if (r4 == 0) goto L_0x0021;
    L_0x0041:
        r5.size = r2;	 Catch:{ all -> 0x0070 }
        r6 = kotlin.Unit.INSTANCE;	 Catch:{ all -> 0x0070 }
        r1.unlock();
        if (r3 != 0) goto L_0x004d;
    L_0x004a:
        kotlin.jvm.internal.Intrinsics.throwNpe();
    L_0x004d:
        r3.completeResumeReceive(r4);
        if (r3 != 0) goto L_0x0055;
    L_0x0052:
        kotlin.jvm.internal.Intrinsics.throwNpe();
    L_0x0055:
        r6 = r3.getOfferResult();
        return r6;
    L_0x005a:
        r0 = r5.buffer;	 Catch:{ all -> 0x0070 }
        r3 = r5.head;	 Catch:{ all -> 0x0070 }
        r3 = r3 + r2;
        r2 = r5.capacity;	 Catch:{ all -> 0x0070 }
        r3 = r3 % r2;
        r0[r3] = r6;	 Catch:{ all -> 0x0070 }
        r6 = kotlinx.coroutines.channels.AbstractChannelKt.OFFER_SUCCESS;	 Catch:{ all -> 0x0070 }
        r1.unlock();
        return r6;
    L_0x006a:
        r6 = kotlinx.coroutines.channels.AbstractChannelKt.OFFER_FAILED;	 Catch:{ all -> 0x0070 }
        r1.unlock();
        return r6;
    L_0x0070:
        r6 = move-exception;
        r1.unlock();
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ArrayChannel.offerInternal(java.lang.Object):java.lang.Object");
    }

    @NotNull
    protected Object offerSelectInternal(E e, @NotNull SelectInstance<?> selectInstance) {
        Intrinsics.checkParameterIsNotNull(selectInstance, "select");
        ReceiveOrClosed receiveOrClosed = (ReceiveOrClosed) null;
        Lock lock = this.lock;
        lock.lock();
        try {
            int i = this.size;
            Object closedForSend = getClosedForSend();
            if (closedForSend != null) {
                return closedForSend;
            }
            closedForSend = this.capacity;
            if (i < closedForSend) {
                closedForSend = i + 1;
                this.size = closedForSend;
                if (i == 0) {
                    closedForSend = describeTryOffer(e);
                    E performAtomicTrySelect = selectInstance.performAtomicTrySelect((AtomicDesc) closedForSend);
                    if (performAtomicTrySelect == null) {
                        this.size = i;
                        ReceiveOrClosed receiveOrClosed2 = (ReceiveOrClosed) closedForSend.getResult();
                        selectInstance = closedForSend.resumeToken;
                        if ((selectInstance != null ? 1 : null) != null) {
                            Unit unit = Unit.INSTANCE;
                            lock.unlock();
                            if (receiveOrClosed2 == null) {
                                Intrinsics.throwNpe();
                            }
                            if (selectInstance == null) {
                                Intrinsics.throwNpe();
                            }
                            receiveOrClosed2.completeResumeReceive(selectInstance);
                            if (receiveOrClosed2 == null) {
                                Intrinsics.throwNpe();
                            }
                            return receiveOrClosed2.getOfferResult();
                        }
                        throw ((Throwable) new IllegalStateException("Check failed.".toString()));
                    }
                    closedForSend = AbstractChannelKt.OFFER_FAILED;
                    if (performAtomicTrySelect != closedForSend) {
                        if (performAtomicTrySelect != SelectKt.getALREADY_SELECTED()) {
                            if ((performAtomicTrySelect instanceof Closed) == null) {
                                e = new StringBuilder();
                                e.append("performAtomicTrySelect(describeTryOffer) returned ");
                                e.append(performAtomicTrySelect);
                                throw ((Throwable) new IllegalStateException(e.toString().toString()));
                            }
                        }
                        this.size = i;
                        lock.unlock();
                        return performAtomicTrySelect;
                    }
                }
                if (selectInstance.trySelect(null) == null) {
                    this.size = i;
                    e = SelectKt.getALREADY_SELECTED();
                    lock.unlock();
                    return e;
                }
                this.buffer[(this.head + i) % this.capacity] = e;
                e = AbstractChannelKt.OFFER_SUCCESS;
                lock.unlock();
                return e;
            }
            e = AbstractChannelKt.OFFER_FAILED;
            lock.unlock();
            return e;
        } finally {
            lock.unlock();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    protected java.lang.Object pollInternal() {
        /*
        r8 = this;
        r0 = 0;
        r1 = r0;
        r1 = (kotlinx.coroutines.channels.Send) r1;
        r2 = r8.lock;
        r2 = (java.util.concurrent.locks.Lock) r2;
        r2.lock();
        r3 = r8.size;	 Catch:{ all -> 0x0081 }
        if (r3 != 0) goto L_0x001c;
    L_0x000f:
        r0 = r8.getClosedForSend();	 Catch:{ all -> 0x0081 }
        if (r0 == 0) goto L_0x0016;
    L_0x0015:
        goto L_0x0018;
    L_0x0016:
        r0 = kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED;	 Catch:{ all -> 0x0081 }
    L_0x0018:
        r2.unlock();
        return r0;
    L_0x001c:
        r4 = r8.buffer;	 Catch:{ all -> 0x0081 }
        r5 = r8.head;	 Catch:{ all -> 0x0081 }
        r4 = r4[r5];	 Catch:{ all -> 0x0081 }
        r5 = r8.buffer;	 Catch:{ all -> 0x0081 }
        r6 = r8.head;	 Catch:{ all -> 0x0081 }
        r5[r6] = r0;	 Catch:{ all -> 0x0081 }
        r5 = r3 + -1;
        r8.size = r5;	 Catch:{ all -> 0x0081 }
        r5 = kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED;	 Catch:{ all -> 0x0081 }
        r6 = r8.capacity;	 Catch:{ all -> 0x0081 }
        if (r3 != r6) goto L_0x0052;
    L_0x0032:
        r6 = r1;
        r1 = r0;
    L_0x0034:
        r7 = r8.takeFirstSendOrPeekClosed();	 Catch:{ all -> 0x0081 }
        if (r7 == 0) goto L_0x0054;
    L_0x003a:
        if (r7 != 0) goto L_0x003f;
    L_0x003c:
        kotlin.jvm.internal.Intrinsics.throwNpe();	 Catch:{ all -> 0x0081 }
    L_0x003f:
        r1 = r7.tryResumeSend(r0);	 Catch:{ all -> 0x0081 }
        if (r1 == 0) goto L_0x0050;
    L_0x0045:
        if (r7 != 0) goto L_0x004a;
    L_0x0047:
        kotlin.jvm.internal.Intrinsics.throwNpe();	 Catch:{ all -> 0x0081 }
    L_0x004a:
        r5 = r7.getPollResult();	 Catch:{ all -> 0x0081 }
        r6 = r7;
        goto L_0x0054;
    L_0x0050:
        r6 = r7;
        goto L_0x0034;
    L_0x0052:
        r6 = r1;
        r1 = r0;
    L_0x0054:
        r0 = kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED;	 Catch:{ all -> 0x0081 }
        if (r5 == r0) goto L_0x0068;
    L_0x0058:
        r0 = r5 instanceof kotlinx.coroutines.channels.Closed;	 Catch:{ all -> 0x0081 }
        if (r0 != 0) goto L_0x0068;
    L_0x005c:
        r8.size = r3;	 Catch:{ all -> 0x0081 }
        r0 = r8.buffer;	 Catch:{ all -> 0x0081 }
        r7 = r8.head;	 Catch:{ all -> 0x0081 }
        r7 = r7 + r3;
        r3 = r8.capacity;	 Catch:{ all -> 0x0081 }
        r7 = r7 % r3;
        r0[r7] = r5;	 Catch:{ all -> 0x0081 }
    L_0x0068:
        r0 = r8.head;	 Catch:{ all -> 0x0081 }
        r0 = r0 + 1;
        r3 = r8.capacity;	 Catch:{ all -> 0x0081 }
        r0 = r0 % r3;
        r8.head = r0;	 Catch:{ all -> 0x0081 }
        r0 = kotlin.Unit.INSTANCE;	 Catch:{ all -> 0x0081 }
        r2.unlock();
        if (r1 == 0) goto L_0x0080;
    L_0x0078:
        if (r6 != 0) goto L_0x007d;
    L_0x007a:
        kotlin.jvm.internal.Intrinsics.throwNpe();
    L_0x007d:
        r6.completeResumeSend(r1);
    L_0x0080:
        return r4;
    L_0x0081:
        r0 = move-exception;
        r2.unlock();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ArrayChannel.pollInternal():java.lang.Object");
    }

    @Nullable
    protected Object pollSelectInternal(@NotNull SelectInstance<?> selectInstance) {
        Intrinsics.checkParameterIsNotNull(selectInstance, "select");
        Send send = (Send) null;
        Lock lock = this.lock;
        lock.lock();
        try {
            int i = this.size;
            if (i == 0) {
                selectInstance = getClosedForSend();
                if (selectInstance == null) {
                    selectInstance = AbstractChannelKt.POLL_FAILED;
                }
                lock.unlock();
                return selectInstance;
            }
            Send send2;
            Object obj;
            Object obj2 = this.buffer[this.head];
            this.buffer[this.head] = null;
            this.size = i - 1;
            Object obj3 = AbstractChannelKt.POLL_FAILED;
            if (i == this.capacity) {
                TryPollDesc describeTryPoll = describeTryPoll();
                Object performAtomicTrySelect = selectInstance.performAtomicTrySelect(describeTryPoll);
                if (performAtomicTrySelect == null) {
                    send = (Send) describeTryPoll.getResult();
                    obj3 = describeTryPoll.resumeToken;
                    if ((obj3 != null ? 1 : null) != null) {
                        if (send == null) {
                            Intrinsics.throwNpe();
                        }
                        Object pollResult = send.getPollResult();
                        send2 = send;
                        obj = obj3;
                        obj3 = pollResult;
                    } else {
                        throw new IllegalStateException("Check failed.".toString());
                    }
                } else if (performAtomicTrySelect != AbstractChannelKt.POLL_FAILED) {
                    if (performAtomicTrySelect == SelectKt.getALREADY_SELECTED()) {
                        this.size = i;
                        this.buffer[this.head] = obj2;
                        lock.unlock();
                        return performAtomicTrySelect;
                    } else if (performAtomicTrySelect instanceof Closed) {
                        send2 = (Send) performAtomicTrySelect;
                        obj = ((Closed) performAtomicTrySelect).tryResumeSend(null);
                        obj3 = performAtomicTrySelect;
                    } else {
                        selectInstance = new StringBuilder();
                        selectInstance.append("performAtomicTrySelect(describeTryOffer) returned ");
                        selectInstance.append(performAtomicTrySelect);
                        throw new IllegalStateException(selectInstance.toString().toString());
                    }
                }
                if (obj3 == AbstractChannelKt.POLL_FAILED && !(obj3 instanceof Closed)) {
                    this.size = i;
                    this.buffer[(this.head + i) % this.capacity] = obj3;
                } else if (selectInstance.trySelect(null) == null) {
                    this.size = i;
                    this.buffer[this.head] = obj2;
                    selectInstance = SelectKt.getALREADY_SELECTED();
                    lock.unlock();
                    return selectInstance;
                }
                this.head = (this.head + 1) % this.capacity;
                selectInstance = Unit.INSTANCE;
                lock.unlock();
                if (obj != null) {
                    if (send2 == null) {
                        Intrinsics.throwNpe();
                    }
                    send2.completeResumeSend(obj);
                }
                return obj2;
            }
            send2 = send;
            obj = null;
            if (obj3 == AbstractChannelKt.POLL_FAILED) {
            }
            if (selectInstance.trySelect(null) == null) {
                this.size = i;
                this.buffer[this.head] = obj2;
                selectInstance = SelectKt.getALREADY_SELECTED();
                lock.unlock();
                return selectInstance;
            }
            this.head = (this.head + 1) % this.capacity;
            selectInstance = Unit.INSTANCE;
            lock.unlock();
            if (obj != null) {
                if (send2 == null) {
                    Intrinsics.throwNpe();
                }
                send2.completeResumeSend(obj);
            }
            return obj2;
        } catch (Throwable th) {
            lock.unlock();
        }
    }

    protected void cleanupSendQueueOnCancel() {
        Lock lock = this.lock;
        lock.lock();
        try {
            int i = this.size;
            for (int i2 = 0; i2 < i; i2++) {
                this.buffer[this.head] = Integer.valueOf(0);
                this.head = (this.head + 1) % this.capacity;
            }
            this.size = 0;
            Unit unit = Unit.INSTANCE;
            super.cleanupSendQueueOnCancel();
        } finally {
            lock.unlock();
        }
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
