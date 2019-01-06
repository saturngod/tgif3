package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "E", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx/coroutines/channels/BroadcastKt$broadcast$1", f = "Broadcast.kt", i = {2}, l = {23, 23, 25, 24}, m = "invokeSuspend", n = {"e"}, s = {"L$1"})
/* compiled from: Broadcast.kt */
final class BroadcastKt$broadcast$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel $this_broadcast;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private ProducerScope p$;

    BroadcastKt$broadcast$1(ReceiveChannel receiveChannel, Continuation continuation) {
        this.$this_broadcast = receiveChannel;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        Continuation broadcastKt$broadcast$1 = new BroadcastKt$broadcast$1(this.$this_broadcast, continuation);
        broadcastKt$broadcast$1.p$ = (ProducerScope) obj;
        return broadcastKt$broadcast$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((BroadcastKt$broadcast$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r7) {
        /*
        r6 = this;
        r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r1 = r6.label;
        switch(r1) {
            case 0: goto L_0x004d;
            case 1: goto L_0x0039;
            case 2: goto L_0x0025;
            case 3: goto L_0x0011;
            default: goto L_0x0009;
        };
    L_0x0009:
        r7 = new java.lang.IllegalStateException;
        r0 = "call to 'resume' before 'invoke' with coroutine";
        r7.<init>(r0);
        throw r7;
    L_0x0011:
        r1 = r6.L$2;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r2 = r6.L$1;
        r2 = r6.L$0;
        r2 = (kotlinx.coroutines.channels.ProducerScope) r2;
        r3 = r7 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x0020;
    L_0x001f:
        goto L_0x005a;
    L_0x0020:
        r7 = (kotlin.Result.Failure) r7;
        r7 = r7.exception;
        throw r7;
    L_0x0025:
        r1 = r6.L$1;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r2 = r6.L$0;
        r2 = (kotlinx.coroutines.channels.ProducerScope) r2;
        r3 = r7 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x0034;
    L_0x0031:
        r3 = r0;
        r0 = r6;
        goto L_0x0083;
    L_0x0034:
        r7 = (kotlin.Result.Failure) r7;
        r7 = r7.exception;
        throw r7;
    L_0x0039:
        r1 = r6.L$1;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r2 = r6.L$0;
        r2 = (kotlinx.coroutines.channels.ProducerScope) r2;
        r3 = r7 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x0048;
    L_0x0045:
        r3 = r0;
        r0 = r6;
        goto L_0x006d;
    L_0x0048:
        r7 = (kotlin.Result.Failure) r7;
        r7 = r7.exception;
        throw r7;
    L_0x004d:
        r1 = r7 instanceof kotlin.Result.Failure;
        if (r1 != 0) goto L_0x0099;
    L_0x0051:
        r7 = r6.p$;
        r1 = r6.$this_broadcast;
        r1 = r1.iterator();
        r2 = r7;
    L_0x005a:
        r7 = r6;
    L_0x005b:
        r7.L$0 = r2;
        r7.L$1 = r1;
        r3 = 1;
        r7.label = r3;
        r3 = r1.hasNext(r7);
        if (r3 != r0) goto L_0x0069;
    L_0x0068:
        return r0;
    L_0x0069:
        r5 = r0;
        r0 = r7;
        r7 = r3;
        r3 = r5;
    L_0x006d:
        r7 = (java.lang.Boolean) r7;
        r7 = r7.booleanValue();
        if (r7 == 0) goto L_0x0096;
    L_0x0075:
        r0.L$0 = r2;
        r0.L$1 = r1;
        r7 = 2;
        r0.label = r7;
        r7 = r1.next(r0);
        if (r7 != r3) goto L_0x0083;
    L_0x0082:
        return r3;
    L_0x0083:
        r0.L$0 = r2;
        r0.L$1 = r7;
        r0.L$2 = r1;
        r4 = 3;
        r0.label = r4;
        r7 = r2.send(r7, r0);
        if (r7 != r3) goto L_0x0093;
    L_0x0092:
        return r3;
    L_0x0093:
        r7 = r0;
        r0 = r3;
        goto L_0x005b;
    L_0x0096:
        r7 = kotlin.Unit.INSTANCE;
        return r7;
    L_0x0099:
        r7 = (kotlin.Result.Failure) r7;
        r7 = r7.exception;
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BroadcastKt$broadcast$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
