package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.selects.SelectClause2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0012\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00042\b\u0012\u0004\u0012\u0002H\u00010\u0005B#\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0012\u0010\u001c\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0013\u0010\u001f\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0001J.\u0010 \u001a\u00020\u00032#\u0010!\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u001e¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u00030\"H\u0001J\u0016\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00028\u0000H\u0001¢\u0006\u0002\u0010'J'\u0010(\u001a\u00020\u00032\b\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\nH\u0010¢\u0006\u0002\b.J\u000f\u0010/\u001a\b\u0012\u0004\u0012\u00028\u000000H\u0001J\u0019\u00101\u001a\u00020\u00032\u0006\u0010&\u001a\u00028\u0000HAø\u0001\u0000¢\u0006\u0002\u00102R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\n8TX\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u00128VX\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\n8VX\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0010R\u0014\u0010\u0016\u001a\u00020\n8\u0016X\u0005¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0010R\u0014\u0010\u0017\u001a\u00020\n8\u0016X\u0005¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0010R$\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00120\u0019X\u0005¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b\u0002\u0004\n\u0002\b\u0019¨\u00063"}, d2 = {"Lkotlinx/coroutines/channels/BroadcastCoroutine;", "E", "Lkotlinx/coroutines/AbstractCoroutine;", "", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlinx/coroutines/channels/BroadcastChannel;", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "_channel", "active", "", "(Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/channels/BroadcastChannel;Z)V", "get_channel", "()Lkotlinx/coroutines/channels/BroadcastChannel;", "cancelsParent", "getCancelsParent", "()Z", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "getChannel", "()Lkotlinx/coroutines/channels/SendChannel;", "isActive", "isClosedForSend", "isFull", "onSend", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "cancel", "cause", "", "close", "invokeOnClose", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "offer", "element", "(Ljava/lang/Object;)Z", "onCompletionInternal", "state", "", "mode", "", "suppressed", "onCompletionInternal$kotlinx_coroutines_core", "openSubscription", "Lkotlinx/coroutines/channels/ReceiveChannel;", "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Broadcast.kt */
class BroadcastCoroutine<E> extends AbstractCoroutine<Unit> implements ProducerScope<E>, BroadcastChannel<E> {
    @NotNull
    private final BroadcastChannel<E> _channel;

    public boolean close(@Nullable Throwable th) {
        return this._channel.close(th);
    }

    protected boolean getCancelsParent() {
        return true;
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

    public boolean isClosedForSend() {
        return this._channel.isClosedForSend();
    }

    public boolean isFull() {
        return this._channel.isFull();
    }

    public boolean offer(E e) {
        return this._channel.offer(e);
    }

    @NotNull
    public ReceiveChannel<E> openSubscription() {
        return this._channel.openSubscription();
    }

    @Nullable
    public Object send(E e, @NotNull Continuation<? super Unit> continuation) {
        return this._channel.send(e, continuation);
    }

    @NotNull
    protected final BroadcastChannel<E> get_channel() {
        return this._channel;
    }

    public BroadcastCoroutine(@NotNull CoroutineContext coroutineContext, @NotNull BroadcastChannel<E> broadcastChannel, boolean z) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "parentContext");
        Intrinsics.checkParameterIsNotNull(broadcastChannel, "_channel");
        super(coroutineContext, z);
        this._channel = broadcastChannel;
    }

    public boolean isActive() {
        return super.isActive();
    }

    @NotNull
    public SendChannel<E> getChannel() {
        return this;
    }

    public boolean cancel(@Nullable Throwable th) {
        boolean cancel = this._channel.cancel(th);
        if (cancel) {
            super.cancel(th);
        }
        return cancel;
    }

    public void onCompletionInternal$kotlinx_coroutines_core(@Nullable Object obj, int i, boolean z) {
        Throwable th = null;
        if ((obj instanceof CompletedExceptionally) == 0) {
            obj = null;
        }
        CompletedExceptionally completedExceptionally = (CompletedExceptionally) obj;
        if (completedExceptionally != null) {
            th = completedExceptionally.cause;
        }
        obj = this._channel.close(th);
        if (th != null && obj == null && z) {
            CoroutineExceptionHandlerKt.handleExceptionViaHandler(getContext(), th);
        }
    }
}
