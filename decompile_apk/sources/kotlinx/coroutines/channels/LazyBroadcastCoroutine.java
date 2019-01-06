package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.intrinsics.CancellableKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002BM\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012-\u0010\u0007\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\b¢\u0006\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\b\u0010\u0010\u001a\u00020\u000bH\u0014J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012H\u0016R:\u0010\u0007\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\b¢\u0006\u0002\b\rX\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u000f\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/channels/LazyBroadcastCoroutine;", "E", "Lkotlinx/coroutines/channels/BroadcastCoroutine;", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "channel", "Lkotlinx/coroutines/channels/BroadcastChannel;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/channels/BroadcastChannel;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "onStart", "openSubscription", "Lkotlinx/coroutines/channels/ReceiveChannel;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Broadcast.kt */
final class LazyBroadcastCoroutine<E> extends BroadcastCoroutine<E> {
    private final Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> block;

    public LazyBroadcastCoroutine(@NotNull CoroutineContext coroutineContext, @NotNull BroadcastChannel<E> broadcastChannel, @NotNull Function2<? super ProducerScope<? super E>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "parentContext");
        Intrinsics.checkParameterIsNotNull(broadcastChannel, "channel");
        Intrinsics.checkParameterIsNotNull(function2, "block");
        super(coroutineContext, broadcastChannel, false);
        this.block = function2;
    }

    @NotNull
    public ReceiveChannel<E> openSubscription() {
        ReceiveChannel<E> openSubscription = get_channel().openSubscription();
        start();
        return openSubscription;
    }

    protected void onStart() {
        CancellableKt.startCoroutineCancellable(this.block, this, this);
    }
}
