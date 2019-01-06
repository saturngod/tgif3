package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u00022\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B\u000f\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0014\u0010\u0017\u001a\u00020\u00182\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u0000H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J!\u0010\u001c\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u001d\u001a\u00028\u00002\b\u0010\u001e\u001a\u0004\u0018\u00010\u0015H\u0016¢\u0006\u0002\u0010\u001fJ\u0014\u0010 \u001a\u0004\u0018\u00010\u00152\b\u0010\u001e\u001a\u0004\u0018\u00010\u0015H\u0016R\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00008VX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00008VX\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\nR\u0011\u0010\r\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000f¨\u0006!"}, d2 = {"Lkotlinx/coroutines/channels/Closed;", "E", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/channels/Send;", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "closeCause", "", "(Ljava/lang/Throwable;)V", "offerResult", "getOfferResult", "()Lkotlinx/coroutines/channels/Closed;", "pollResult", "getPollResult", "receiveException", "getReceiveException", "()Ljava/lang/Throwable;", "sendException", "getSendException", "completeResumeReceive", "", "token", "", "completeResumeSend", "resumeSendClosed", "", "closed", "toString", "", "tryResumeReceive", "value", "idempotent", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "tryResumeSend", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: AbstractChannel.kt */
public final class Closed<E> extends LockFreeLinkedListNode implements Send, ReceiveOrClosed<E> {
    @Nullable
    @JvmField
    public final Throwable closeCause;

    @NotNull
    public Closed<E> getOfferResult() {
        return this;
    }

    @NotNull
    public Closed<E> getPollResult() {
        return this;
    }

    public Closed(@Nullable Throwable th) {
        this.closeCause = th;
    }

    @NotNull
    public final Throwable getSendException() {
        Throwable th = this.closeCause;
        return th != null ? th : new ClosedSendChannelException(ChannelsKt.DEFAULT_CLOSE_MESSAGE);
    }

    @NotNull
    public final Throwable getReceiveException() {
        Throwable th = this.closeCause;
        return th != null ? th : new ClosedReceiveChannelException(ChannelsKt.DEFAULT_CLOSE_MESSAGE);
    }

    @Nullable
    public Object tryResumeSend(@Nullable Object obj) {
        return AbstractChannelKt.CLOSE_RESUMED;
    }

    public void completeResumeSend(@NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "token");
        if ((obj == AbstractChannelKt.CLOSE_RESUMED ? true : null) == null) {
            throw ((Throwable) new IllegalStateException("Check failed.".toString()));
        }
    }

    @Nullable
    public Object tryResumeReceive(E e, @Nullable Object obj) {
        return AbstractChannelKt.CLOSE_RESUMED;
    }

    public void completeResumeReceive(@NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "token");
        if ((obj == AbstractChannelKt.CLOSE_RESUMED ? true : null) == null) {
            throw ((Throwable) new IllegalStateException("Check failed.".toString()));
        }
    }

    @NotNull
    public Void resumeSendClosed(@NotNull Closed<?> closed) {
        Intrinsics.checkParameterIsNotNull(closed, "closed");
        throw ((Throwable) new IllegalStateException("Should be never invoked".toString()));
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Closed[");
        stringBuilder.append(this.closeCause);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
