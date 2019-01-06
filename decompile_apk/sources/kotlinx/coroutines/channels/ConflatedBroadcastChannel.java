package kotlinx.coroutines.channels;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.internal.ArrayCopyKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u0000 @*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0004?@ABB\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0002\u0010\u0004B\u0005¢\u0006\u0002\u0010\u0005J=\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001d0\u001c2\u0014\u0010\u001e\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001d\u0018\u00010\u001c2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\u001dH\u0002¢\u0006\u0002\u0010 J\u0012\u0010!\u001a\u00020\f2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u0012\u0010$\u001a\u00020\f2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u0016\u0010%\u001a\u00020&2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\u001dH\u0002J\"\u0010'\u001a\u00020&2\u0018\u0010(\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010#\u0012\u0004\u0012\u00020&0)j\u0002`*H\u0016J\u0012\u0010+\u001a\u00020&2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0002J\u0015\u0010,\u001a\u00020\f2\u0006\u0010-\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010.J\u0017\u0010/\u001a\u0004\u0018\u0001002\u0006\u0010-\u001a\u00028\u0000H\u0002¢\u0006\u0002\u00101J\u000e\u00102\u001a\b\u0012\u0004\u0012\u00028\u000003H\u0016JV\u00104\u001a\u00020&\"\u0004\b\u0001\u001052\f\u00106\u001a\b\u0012\u0004\u0012\u0002H5072\u0006\u0010-\u001a\u00028\u00002(\u00108\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u0002H50:\u0012\u0006\u0012\u0004\u0018\u00010\b09H\u0002ø\u0001\u0000¢\u0006\u0002\u0010;J=\u0010<\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001d\u0018\u00010\u001c2\u0012\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001d0\u001c2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\u001dH\u0002¢\u0006\u0002\u0010 J\u0019\u0010=\u001a\u00020&2\u0006\u0010-\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010>R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\rR\u0014\u0010\u000e\u001a\u00020\f8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\rR\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0004¢\u0006\u0002\n\u0000R&\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00120\u00118VX\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0003\u001a\u00028\u00008F¢\u0006\f\u0012\u0004\b\u0015\u0010\u0005\u001a\u0004\b\u0016\u0010\u0017R\u0019\u0010\u0018\u001a\u0004\u0018\u00018\u00008F¢\u0006\f\u0012\u0004\b\u0019\u0010\u0005\u001a\u0004\b\u001a\u0010\u0017\u0002\u0004\n\u0002\b\u0019¨\u0006C"}, d2 = {"Lkotlinx/coroutines/channels/ConflatedBroadcastChannel;", "E", "Lkotlinx/coroutines/channels/BroadcastChannel;", "value", "(Ljava/lang/Object;)V", "()V", "_state", "Lkotlinx/atomicfu/AtomicRef;", "", "_updating", "Lkotlinx/atomicfu/AtomicInt;", "isClosedForSend", "", "()Z", "isFull", "onCloseHandler", "onSend", "Lkotlinx/coroutines/selects/SelectClause2;", "Lkotlinx/coroutines/channels/SendChannel;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "value$annotations", "getValue", "()Ljava/lang/Object;", "valueOrNull", "valueOrNull$annotations", "getValueOrNull", "addSubscriber", "", "Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Subscriber;", "list", "subscriber", "([Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Subscriber;Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Subscriber;)[Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Subscriber;", "cancel", "cause", "", "close", "closeSubscriber", "", "invokeOnClose", "handler", "Lkotlin/Function1;", "Lkotlinx/coroutines/channels/Handler;", "invokeOnCloseHandler", "offer", "element", "(Ljava/lang/Object;)Z", "offerInternal", "Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Closed;", "(Ljava/lang/Object;)Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Closed;", "openSubscription", "Lkotlinx/coroutines/channels/ReceiveChannel;", "registerSelectSend", "R", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "removeSubscriber", "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Closed", "Companion", "State", "Subscriber", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
@ExperimentalCoroutinesApi
/* compiled from: ConflatedBroadcastChannel.kt */
public final class ConflatedBroadcastChannel<E> implements BroadcastChannel<E> {
    private static final Closed CLOSED = new Closed(null);
    @Deprecated
    public static final Companion Companion = new Companion();
    private static final State<Object> INITIAL_STATE = new State(UNDEFINED, null);
    private static final Symbol UNDEFINED = new Symbol("UNDEFINED");
    private static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(ConflatedBroadcastChannel.class, Object.class, "_state");
    private static final AtomicIntegerFieldUpdater _updating$FU = AtomicIntegerFieldUpdater.newUpdater(ConflatedBroadcastChannel.class, "_updating");
    private static final AtomicReferenceFieldUpdater onCloseHandler$FU = AtomicReferenceFieldUpdater.newUpdater(ConflatedBroadcastChannel.class, Object.class, "onCloseHandler");
    private volatile Object _state;
    private volatile int _updating;
    private volatile Object onCloseHandler;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Closed;", "", "closeCause", "", "(Ljava/lang/Throwable;)V", "sendException", "getSendException", "()Ljava/lang/Throwable;", "valueException", "getValueException", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: ConflatedBroadcastChannel.kt */
    private static final class Closed {
        @Nullable
        @JvmField
        public final Throwable closeCause;

        public Closed(@Nullable Throwable th) {
            this.closeCause = th;
        }

        @NotNull
        public final Throwable getSendException() {
            Throwable th = this.closeCause;
            return th != null ? th : new ClosedSendChannelException(ChannelsKt.DEFAULT_CLOSE_MESSAGE);
        }

        @NotNull
        public final Throwable getValueException() {
            Throwable th = this.closeCause;
            return th != null ? th : new IllegalStateException(ChannelsKt.DEFAULT_CLOSE_MESSAGE);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Companion;", "", "()V", "CLOSED", "Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Closed;", "INITIAL_STATE", "Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$State;", "UNDEFINED", "Lkotlinx/coroutines/internal/Symbol;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: ConflatedBroadcastChannel.kt */
    private static final class Companion {
        private Companion() {
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002B%\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0014\u0010\u0004\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007R \u0010\u0004\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0006\u0018\u00010\u00058\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$State;", "E", "", "value", "subscribers", "", "Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Subscriber;", "(Ljava/lang/Object;[Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Subscriber;)V", "[Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Subscriber;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: ConflatedBroadcastChannel.kt */
    private static final class State<E> {
        @Nullable
        @JvmField
        public final Subscriber<E>[] subscribers;
        @Nullable
        @JvmField
        public final Object value;

        public State(@Nullable Object obj, @Nullable Subscriber<E>[] subscriberArr) {
            this.value = obj;
            this.subscribers = subscriberArr;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0015\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u000eR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Subscriber;", "E", "Lkotlinx/coroutines/channels/ConflatedChannel;", "Lkotlinx/coroutines/channels/ReceiveChannel;", "broadcastChannel", "Lkotlinx/coroutines/channels/ConflatedBroadcastChannel;", "(Lkotlinx/coroutines/channels/ConflatedBroadcastChannel;)V", "cancel", "", "cause", "", "offerInternal", "", "element", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: ConflatedBroadcastChannel.kt */
    private static final class Subscriber<E> extends ConflatedChannel<E> implements ReceiveChannel<E> {
        private final ConflatedBroadcastChannel<E> broadcastChannel;

        public Subscriber(@NotNull ConflatedBroadcastChannel<E> conflatedBroadcastChannel) {
            Intrinsics.checkParameterIsNotNull(conflatedBroadcastChannel, "broadcastChannel");
            this.broadcastChannel = conflatedBroadcastChannel;
        }

        public boolean cancel(@Nullable Throwable th) {
            th = close(th);
            if (th != null) {
                this.broadcastChannel.closeSubscriber(this);
            }
            return th;
        }

        @NotNull
        public Object offerInternal(E e) {
            return super.offerInternal(e);
        }
    }

    public static /* synthetic */ void value$annotations() {
    }

    public static /* synthetic */ void valueOrNull$annotations() {
    }

    public boolean isFull() {
        return false;
    }

    public ConflatedBroadcastChannel() {
        this._state = INITIAL_STATE;
        this._updating = 0;
        this.onCloseHandler = null;
    }

    public ConflatedBroadcastChannel(E e) {
        this();
        _state$FU.lazySet(this, new State(e, null));
    }

    @Nullable
    public final E getValueOrNull() {
        Object obj = this._state;
        if (obj instanceof Closed) {
            return null;
        }
        if (obj instanceof State) {
            State state = (State) obj;
            if (state.value == UNDEFINED) {
                return null;
            }
            return state.value;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Invalid state ");
        stringBuilder.append(obj);
        throw new IllegalStateException(stringBuilder.toString().toString());
    }

    public boolean isClosedForSend() {
        return this._state instanceof Closed;
    }

    @NotNull
    public ReceiveChannel<E> openSubscription() {
        Subscriber subscriber = new Subscriber(this);
        Object obj;
        Object obj2;
        State state;
        do {
            obj = this._state;
            if (obj instanceof Closed) {
                subscriber.close(((Closed) obj).closeCause);
                return subscriber;
            } else if (obj instanceof State) {
                state = (State) obj;
                if (state.value != UNDEFINED) {
                    subscriber.offerInternal(state.value);
                }
                obj2 = state.value;
                if (obj != null) {
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.channels.ConflatedBroadcastChannel.State<E>");
                }
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Invalid state ");
                stringBuilder.append(obj);
                throw new IllegalStateException(stringBuilder.toString().toString());
            }
        } while (!_state$FU.compareAndSet(this, obj, new State(obj2, addSubscriber(state.subscribers, subscriber))));
        return subscriber;
    }

    private final Subscriber<E>[] removeSubscriber(Subscriber<E>[] subscriberArr, Subscriber<E> subscriber) {
        int length = subscriberArr.length;
        subscriber = ArraysKt___ArraysKt.indexOf((Object[]) subscriberArr, (Object) subscriber);
        if ((subscriber >= null ? 1 : null) == null) {
            throw ((Throwable) new IllegalStateException("Check failed.".toString()));
        } else if (length == 1) {
            return null;
        } else {
            Subscriber<E>[] subscriberArr2 = new Subscriber[(length - 1)];
            ArrayCopyKt.arraycopy(subscriberArr, 0, subscriberArr2, 0, subscriber);
            ArrayCopyKt.arraycopy(subscriberArr, subscriber + 1, subscriberArr2, subscriber, (length - subscriber) - 1);
            return subscriberArr2;
        }
    }

    private final void invokeOnCloseHandler(Throwable th) {
        Object obj = this.onCloseHandler;
        if (obj != null && obj != AbstractChannelKt.HANDLER_INVOKED && onCloseHandler$FU.compareAndSet(this, obj, AbstractChannelKt.HANDLER_INVOKED)) {
            ((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(obj, 1)).invoke(th);
        }
    }

    public void invokeOnClose(@NotNull Function1<? super Throwable, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "handler");
        if (onCloseHandler$FU.compareAndSet(this, null, function1)) {
            Object obj = this._state;
            if ((obj instanceof Closed) && onCloseHandler$FU.compareAndSet(this, function1, AbstractChannelKt.HANDLER_INVOKED)) {
                function1.invoke(((Closed) obj).closeCause);
                return;
            }
            return;
        }
        function1 = this.onCloseHandler;
        if (function1 == AbstractChannelKt.HANDLER_INVOKED) {
            throw ((Throwable) new IllegalStateException("Another handler was already registered and successfully invoked"));
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Another handler was already registered: ");
        stringBuilder.append(function1);
        throw new IllegalStateException(stringBuilder.toString());
    }

    public boolean cancel(@Nullable Throwable th) {
        return close(th);
    }

    @Nullable
    public Object send(E e, @NotNull Continuation<? super Unit> continuation) {
        e = offerInternal(e);
        if (e == null) {
            return Unit.INSTANCE;
        }
        throw e.getSendException();
    }

    public boolean offer(E e) {
        e = offerInternal(e);
        if (e == null) {
            return true;
        }
        throw e.getSendException();
    }

    private final Closed offerInternal(E e) {
        if (!_updating$FU.compareAndSet(this, 0, 1)) {
            return null;
        }
        Object obj;
        do {
            obj = this._state;
            if (obj instanceof Closed) {
                Closed closed = (Closed) obj;
                this._updating = 0;
                return closed;
            }
            try {
                if (!(obj instanceof State)) {
                    e = new StringBuilder();
                    e.append("Invalid state ");
                    e.append(obj);
                    throw new IllegalStateException(e.toString().toString());
                } else if (obj != null) {
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.channels.ConflatedBroadcastChannel.State<E>");
                }
            } catch (Throwable th) {
                this._updating = 0;
            }
        } while (!_state$FU.compareAndSet(this, obj, new State(e, ((State) obj).subscribers)));
        Subscriber[] subscriberArr = ((State) obj).subscribers;
        if (subscriberArr != null) {
            for (Subscriber offerInternal : subscriberArr) {
                offerInternal.offerInternal(e);
            }
        }
        this._updating = 0;
        return null;
    }

    @NotNull
    public SelectClause2<E, SendChannel<E>> getOnSend() {
        return new ConflatedBroadcastChannel$onSend$1(this);
    }

    private final <R> void registerSelectSend(SelectInstance<? super R> selectInstance, E e, Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> function2) {
        if (selectInstance.trySelect(null)) {
            e = offerInternal(e);
            if (e != null) {
                selectInstance.resumeSelectCancellableWithException(e.getSendException());
            } else {
                UndispatchedKt.startCoroutineUnintercepted(function2, this, selectInstance.getCompletion());
            }
        }
    }

    public final E getValue() {
        Object obj = this._state;
        if (obj instanceof Closed) {
            throw ((Closed) obj).getValueException();
        } else if (obj instanceof State) {
            State state = (State) obj;
            if (state.value != UNDEFINED) {
                return state.value;
            }
            throw new IllegalStateException("No value");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Invalid state ");
            stringBuilder.append(obj);
            throw new IllegalStateException(stringBuilder.toString().toString());
        }
    }

    private final void closeSubscriber(Subscriber<E> subscriber) {
        Object obj;
        Object obj2;
        Subscriber[] subscriberArr;
        do {
            obj = this._state;
            if (!(obj instanceof Closed)) {
                if (obj instanceof State) {
                    State state = (State) obj;
                    obj2 = state.value;
                    if (obj != null) {
                        subscriberArr = state.subscribers;
                        if (subscriberArr == null) {
                            Intrinsics.throwNpe();
                        }
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.channels.ConflatedBroadcastChannel.State<E>");
                    }
                }
                subscriber = new StringBuilder();
                subscriber.append("Invalid state ");
                subscriber.append(obj);
                throw new IllegalStateException(subscriber.toString().toString());
            }
            return;
        } while (!_state$FU.compareAndSet(this, obj, new State(obj2, removeSubscriber(subscriberArr, subscriber))));
    }

    private final Subscriber<E>[] addSubscriber(Subscriber<E>[] subscriberArr, Subscriber<E> subscriber) {
        if (subscriberArr != null) {
            return (Subscriber[]) ArraysKt___ArraysJvmKt.plus((Object[]) subscriberArr, (Object) subscriber);
        }
        subscriberArr = new Subscriber[1];
        int length = subscriberArr.length;
        for (int i = 0; i < length; i++) {
            subscriberArr[i] = subscriber;
        }
        return subscriberArr;
    }

    public boolean close(@Nullable Throwable th) {
        Object obj;
        do {
            obj = this._state;
            int i = 0;
            if (obj instanceof Closed) {
                return false;
            }
            if (!(obj instanceof State)) {
                th = new StringBuilder();
                th.append("Invalid state ");
                th.append(obj);
                throw new IllegalStateException(th.toString().toString());
            }
        } while (!_state$FU.compareAndSet(this, obj, th == null ? CLOSED : new Closed(th)));
        if (obj != null) {
            Subscriber[] subscriberArr = ((State) obj).subscribers;
            if (subscriberArr != null) {
                int length = subscriberArr.length;
                while (i < length) {
                    subscriberArr[i].close(th);
                    i++;
                }
            }
            invokeOnCloseHandler(th);
            return true;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.channels.ConflatedBroadcastChannel.State<E>");
    }
}
