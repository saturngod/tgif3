package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.ObsoleteCoroutinesApi;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectClause2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0004B#\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010!\u001a\u00020\u0003H\u0016J\u0012\u0010!\u001a\u00020\t2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020\tH\u0016J\u0013\u0010%\u001a\u00020\t2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0001J.\u0010&\u001a\u00020\u00032#\u0010'\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010#¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020\u00030(H\u0001J\u000f\u0010+\u001a\b\u0012\u0004\u0012\u00028\u00000,H\u0003J\u0016\u0010-\u001a\u00020\t2\u0006\u0010.\u001a\u00028\u0000H\u0001¢\u0006\u0002\u0010/J\u0010\u00100\u001a\u0004\u0018\u00018\u0000H\u0001¢\u0006\u0002\u00101J\u0011\u00102\u001a\u00028\u0000HAø\u0001\u0000¢\u0006\u0002\u00103J\u0013\u00104\u001a\u0004\u0018\u00018\u0000HAø\u0001\u0000¢\u0006\u0002\u00103J\u0019\u00105\u001a\u00020\u00032\u0006\u0010.\u001a\u00028\u0000HAø\u0001\u0000¢\u0006\u0002\u00106R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\t8TX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\fR\u0014\u0010\u0012\u001a\u00020\t8\u0016X\u0005¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u000fR\u0014\u0010\u0013\u001a\u00020\t8\u0016X\u0005¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000fR\u0014\u0010\u0014\u001a\u00020\t8\u0016X\u0005¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u000fR\u0014\u0010\u0015\u001a\u00020\t8\u0016X\u0005¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000fR\u0018\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017X\u0005¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u00178\u0016X\u0005¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0019R$\u0010\u001c\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001e0\u001dX\u0005¢\u0006\u0006\u001a\u0004\b\u001f\u0010 \u0002\u0004\n\u0002\b\u0019¨\u00067"}, d2 = {"Lkotlinx/coroutines/channels/ChannelCoroutine;", "E", "Lkotlinx/coroutines/AbstractCoroutine;", "", "Lkotlinx/coroutines/channels/Channel;", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "_channel", "active", "", "(Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/channels/Channel;Z)V", "get_channel", "()Lkotlinx/coroutines/channels/Channel;", "cancelsParent", "getCancelsParent", "()Z", "channel", "getChannel", "isClosedForReceive", "isClosedForSend", "isEmpty", "isFull", "onReceive", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceive", "()Lkotlinx/coroutines/selects/SelectClause1;", "onReceiveOrNull", "getOnReceiveOrNull", "onSend", "Lkotlinx/coroutines/selects/SelectClause2;", "Lkotlinx/coroutines/channels/SendChannel;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "cancel", "cause", "", "cancel0", "close", "invokeOnClose", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "iterator", "Lkotlinx/coroutines/channels/ChannelIterator;", "offer", "element", "(Ljava/lang/Object;)Z", "poll", "()Ljava/lang/Object;", "receive", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveOrNull", "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: ChannelCoroutine.kt */
public class ChannelCoroutine<E> extends AbstractCoroutine<Unit> implements Channel<E> {
    @NotNull
    private final Channel<E> _channel;

    public boolean close(@Nullable Throwable th) {
        return this._channel.close(th);
    }

    protected boolean getCancelsParent() {
        return true;
    }

    @NotNull
    public SelectClause1<E> getOnReceive() {
        return this._channel.getOnReceive();
    }

    @NotNull
    public SelectClause1<E> getOnReceiveOrNull() {
        return this._channel.getOnReceiveOrNull();
    }

    @NotNull
    public SelectClause2<E, SendChannel<E>> getOnSend() {
        return this._channel.getOnSend();
    }

    @ExperimentalCoroutinesApi
    public void invokeOnClose(@NotNull Function1<? super Throwable, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "handler");
        this._channel.invokeOnClose(function1);
    }

    public boolean isClosedForReceive() {
        return this._channel.isClosedForReceive();
    }

    public boolean isClosedForSend() {
        return this._channel.isClosedForSend();
    }

    public boolean isEmpty() {
        return this._channel.isEmpty();
    }

    public boolean isFull() {
        return this._channel.isFull();
    }

    @NotNull
    public ChannelIterator<E> iterator() {
        return this._channel.iterator();
    }

    public boolean offer(E e) {
        return this._channel.offer(e);
    }

    @Nullable
    public E poll() {
        return this._channel.poll();
    }

    @Nullable
    public Object receive(@NotNull Continuation<? super E> continuation) {
        return this._channel.receive(continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public Object receiveOrNull(@NotNull Continuation<? super E> continuation) {
        return this._channel.receiveOrNull(continuation);
    }

    @Nullable
    public Object send(E e, @NotNull Continuation<? super Unit> continuation) {
        return this._channel.send(e, continuation);
    }

    @NotNull
    protected final Channel<E> get_channel() {
        return this._channel;
    }

    public ChannelCoroutine(@NotNull CoroutineContext coroutineContext, @NotNull Channel<E> channel, boolean z) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "parentContext");
        Intrinsics.checkParameterIsNotNull(channel, "_channel");
        super(coroutineContext, z);
        this._channel = channel;
    }

    @NotNull
    public final Channel<E> getChannel() {
        return this;
    }

    public void cancel() {
        cancel(null);
    }

    public /* synthetic */ boolean cancel0() {
        return cancel(null);
    }

    public boolean cancel(@Nullable Throwable th) {
        boolean cancel = this._channel.cancel(th);
        if (cancel) {
            super.cancel(th);
        }
        return cancel;
    }
}
