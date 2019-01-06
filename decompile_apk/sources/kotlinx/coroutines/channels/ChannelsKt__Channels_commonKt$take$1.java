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
@DebugMetadata(c = "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$take$1", f = "Channels.common.kt", i = {0, 1, 2, 2}, l = {840, 840, 845, 846}, m = "invokeSuspend", n = {"remaining", "remaining", "remaining", "e"}, s = {"I$0", "I$0", "I$0", "L$1"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$take$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $n;
    final /* synthetic */ ReceiveChannel $this_take;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private ProducerScope p$;

    ChannelsKt__Channels_commonKt$take$1(ReceiveChannel receiveChannel, int i, Continuation continuation) {
        this.$this_take = receiveChannel;
        this.$n = i;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        Continuation channelsKt__Channels_commonKt$take$1 = new ChannelsKt__Channels_commonKt$take$1(this.$this_take, this.$n, continuation);
        channelsKt__Channels_commonKt$take$1.p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$take$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$take$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r9) {
        /*
        r8 = this;
        r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r1 = r8.label;
        r2 = 1;
        switch(r1) {
            case 0: goto L_0x0057;
            case 1: goto L_0x0041;
            case 2: goto L_0x002a;
            case 3: goto L_0x0012;
            default: goto L_0x000a;
        };
    L_0x000a:
        r9 = new java.lang.IllegalStateException;
        r0 = "call to 'resume' before 'invoke' with coroutine";
        r9.<init>(r0);
        throw r9;
    L_0x0012:
        r1 = r8.L$2;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r3 = r8.L$1;
        r3 = r8.I$0;
        r4 = r8.L$0;
        r4 = (kotlinx.coroutines.channels.ProducerScope) r4;
        r5 = r9 instanceof kotlin.Result.Failure;
        if (r5 != 0) goto L_0x0025;
    L_0x0022:
        r9 = r8;
        goto L_0x00b9;
    L_0x0025:
        r9 = (kotlin.Result.Failure) r9;
        r9 = r9.exception;
        throw r9;
    L_0x002a:
        r1 = r8.L$1;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r3 = r8.I$0;
        r4 = r8.L$0;
        r4 = (kotlinx.coroutines.channels.ProducerScope) r4;
        r5 = r9 instanceof kotlin.Result.Failure;
        if (r5 != 0) goto L_0x003c;
    L_0x0038:
        r5 = r0;
        r0 = r8;
        goto L_0x00a5;
    L_0x003c:
        r9 = (kotlin.Result.Failure) r9;
        r9 = r9.exception;
        throw r9;
    L_0x0041:
        r1 = r8.L$1;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r3 = r8.I$0;
        r4 = r8.L$0;
        r4 = (kotlinx.coroutines.channels.ProducerScope) r4;
        r5 = r9 instanceof kotlin.Result.Failure;
        if (r5 != 0) goto L_0x0052;
    L_0x004f:
        r5 = r0;
        r0 = r8;
        goto L_0x008d;
    L_0x0052:
        r9 = (kotlin.Result.Failure) r9;
        r9 = r9.exception;
        throw r9;
    L_0x0057:
        r1 = r9 instanceof kotlin.Result.Failure;
        if (r1 != 0) goto L_0x00e7;
    L_0x005b:
        r9 = r8.p$;
        r1 = r8.$n;
        if (r1 != 0) goto L_0x0064;
    L_0x0061:
        r9 = kotlin.Unit.INSTANCE;
        return r9;
    L_0x0064:
        r1 = r8.$n;
        if (r1 < 0) goto L_0x006a;
    L_0x0068:
        r1 = 1;
        goto L_0x006b;
    L_0x006a:
        r1 = 0;
    L_0x006b:
        if (r1 == 0) goto L_0x00c3;
    L_0x006d:
        r1 = r8.$n;
        r3 = r8.$this_take;
        r3 = r3.iterator();
        r4 = r9;
        r9 = r8;
        r7 = r3;
        r3 = r1;
        r1 = r7;
    L_0x007a:
        r9.L$0 = r4;
        r9.I$0 = r3;
        r9.L$1 = r1;
        r9.label = r2;
        r5 = r1.hasNext(r9);
        if (r5 != r0) goto L_0x0089;
    L_0x0088:
        return r0;
    L_0x0089:
        r7 = r0;
        r0 = r9;
        r9 = r5;
        r5 = r7;
    L_0x008d:
        r9 = (java.lang.Boolean) r9;
        r9 = r9.booleanValue();
        if (r9 == 0) goto L_0x00c0;
    L_0x0095:
        r0.L$0 = r4;
        r0.I$0 = r3;
        r0.L$1 = r1;
        r9 = 2;
        r0.label = r9;
        r9 = r1.next(r0);
        if (r9 != r5) goto L_0x00a5;
    L_0x00a4:
        return r5;
    L_0x00a5:
        r0.L$0 = r4;
        r0.I$0 = r3;
        r0.L$1 = r9;
        r0.L$2 = r1;
        r6 = 3;
        r0.label = r6;
        r9 = r4.send(r9, r0);
        if (r9 != r5) goto L_0x00b7;
    L_0x00b6:
        return r5;
    L_0x00b7:
        r9 = r0;
        r0 = r5;
    L_0x00b9:
        r3 = r3 + -1;
        if (r3 != 0) goto L_0x007a;
    L_0x00bd:
        r9 = kotlin.Unit.INSTANCE;
        return r9;
    L_0x00c0:
        r9 = kotlin.Unit.INSTANCE;
        return r9;
    L_0x00c3:
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r0 = "Requested element count ";
        r9.append(r0);
        r0 = r8.$n;
        r9.append(r0);
        r0 = " is less than zero.";
        r9.append(r0);
        r9 = r9.toString();
        r0 = new java.lang.IllegalArgumentException;
        r9 = r9.toString();
        r0.<init>(r9);
        r0 = (java.lang.Throwable) r0;
        throw r0;
    L_0x00e7:
        r9 = (kotlin.Result.Failure) r9;
        r9 = r9.exception;
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$take$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
