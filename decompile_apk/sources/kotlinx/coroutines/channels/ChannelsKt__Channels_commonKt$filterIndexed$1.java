package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "E", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$filterIndexed$1", f = "Channels.common.kt", i = {0, 1, 2, 2, 3, 3}, l = {654, 654, 657, 654, 656}, m = "invokeSuspend", n = {"index", "index", "index", "e", "index", "e"}, s = {"I$0", "I$0", "I$0", "L$1", "I$0", "L$1"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$filterIndexed$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function3 $predicate;
    final /* synthetic */ ReceiveChannel $this_filterIndexed;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private ProducerScope p$;

    ChannelsKt__Channels_commonKt$filterIndexed$1(ReceiveChannel receiveChannel, Function3 function3, Continuation continuation) {
        this.$this_filterIndexed = receiveChannel;
        this.$predicate = function3;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        Continuation channelsKt__Channels_commonKt$filterIndexed$1 = new ChannelsKt__Channels_commonKt$filterIndexed$1(this.$this_filterIndexed, this.$predicate, continuation);
        channelsKt__Channels_commonKt$filterIndexed$1.p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$filterIndexed$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$filterIndexed$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r12) {
        /*
        r11 = this;
        r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r1 = r11.label;
        r2 = 1;
        switch(r1) {
            case 0: goto L_0x007c;
            case 1: goto L_0x0066;
            case 2: goto L_0x004d;
            case 3: goto L_0x0030;
            case 4: goto L_0x0012;
            default: goto L_0x000a;
        };
    L_0x000a:
        r12 = new java.lang.IllegalStateException;
        r0 = "call to 'resume' before 'invoke' with coroutine";
        r12.<init>(r0);
        throw r12;
    L_0x0012:
        r1 = r11.L$2;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r3 = r11.L$1;
        r3 = r11.I$0;
        r4 = r11.L$0;
        r4 = (kotlinx.coroutines.channels.ProducerScope) r4;
        r5 = r12 instanceof kotlin.Result.Failure;
        if (r5 != 0) goto L_0x002b;
    L_0x0022:
        r12 = r11;
        r9 = r4;
        r4 = r0;
        r0 = r9;
        r10 = r3;
        r3 = r1;
        r1 = r10;
        goto L_0x008c;
    L_0x002b:
        r12 = (kotlin.Result.Failure) r12;
        r12 = r12.exception;
        throw r12;
    L_0x0030:
        r1 = r11.L$2;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r3 = r11.L$1;
        r4 = r11.I$0;
        r5 = r11.L$0;
        r5 = (kotlinx.coroutines.channels.ProducerScope) r5;
        r6 = r12 instanceof kotlin.Result.Failure;
        if (r6 != 0) goto L_0x0048;
    L_0x0040:
        r6 = r3;
        r3 = r1;
        r1 = r4;
        r4 = r5;
        r5 = r0;
        r0 = r11;
        goto L_0x00d9;
    L_0x0048:
        r12 = (kotlin.Result.Failure) r12;
        r12 = r12.exception;
        throw r12;
    L_0x004d:
        r1 = r11.L$1;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r3 = r11.I$0;
        r4 = r11.L$0;
        r4 = (kotlinx.coroutines.channels.ProducerScope) r4;
        r5 = r12 instanceof kotlin.Result.Failure;
        if (r5 != 0) goto L_0x0061;
    L_0x005b:
        r5 = r0;
        r0 = r11;
    L_0x005d:
        r9 = r3;
        r3 = r12;
        r12 = r9;
        goto L_0x00bb;
    L_0x0061:
        r12 = (kotlin.Result.Failure) r12;
        r12 = r12.exception;
        throw r12;
    L_0x0066:
        r1 = r11.L$1;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r3 = r11.I$0;
        r4 = r11.L$0;
        r4 = (kotlinx.coroutines.channels.ProducerScope) r4;
        r5 = r12 instanceof kotlin.Result.Failure;
        if (r5 != 0) goto L_0x0077;
    L_0x0074:
        r5 = r0;
        r0 = r11;
        goto L_0x00a3;
    L_0x0077:
        r12 = (kotlin.Result.Failure) r12;
        r12 = r12.exception;
        throw r12;
    L_0x007c:
        r1 = r12 instanceof kotlin.Result.Failure;
        if (r1 != 0) goto L_0x00fa;
    L_0x0080:
        r12 = r11.p$;
        r1 = 0;
        r3 = r11.$this_filterIndexed;
        r3 = r3.iterator();
        r4 = r0;
        r0 = r12;
        r12 = r11;
    L_0x008c:
        r12.L$0 = r0;
        r12.I$0 = r1;
        r12.L$1 = r3;
        r12.label = r2;
        r5 = r3.hasNext(r12);
        if (r5 != r4) goto L_0x009b;
    L_0x009a:
        return r4;
    L_0x009b:
        r9 = r0;
        r0 = r12;
        r12 = r5;
        r5 = r4;
        r4 = r9;
        r10 = r3;
        r3 = r1;
        r1 = r10;
    L_0x00a3:
        r12 = (java.lang.Boolean) r12;
        r12 = r12.booleanValue();
        if (r12 == 0) goto L_0x00f7;
    L_0x00ab:
        r0.L$0 = r4;
        r0.I$0 = r3;
        r0.L$1 = r1;
        r12 = 2;
        r0.label = r12;
        r12 = r1.next(r0);
        if (r12 != r5) goto L_0x005d;
    L_0x00ba:
        return r5;
    L_0x00bb:
        r6 = r0.$predicate;
        r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r12);
        r12 = r12 + r2;
        r0.L$0 = r4;
        r0.I$0 = r12;
        r0.L$1 = r3;
        r0.L$2 = r1;
        r8 = 3;
        r0.label = r8;
        r6 = r6.invoke(r7, r3, r0);
        if (r6 != r5) goto L_0x00d4;
    L_0x00d3:
        return r5;
    L_0x00d4:
        r9 = r1;
        r1 = r12;
        r12 = r6;
        r6 = r3;
        r3 = r9;
    L_0x00d9:
        r12 = (java.lang.Boolean) r12;
        r12 = r12.booleanValue();
        if (r12 == 0) goto L_0x00f3;
    L_0x00e1:
        r0.L$0 = r4;
        r0.I$0 = r1;
        r0.L$1 = r6;
        r0.L$2 = r3;
        r12 = 4;
        r0.label = r12;
        r12 = r4.send(r6, r0);
        if (r12 != r5) goto L_0x00f3;
    L_0x00f2:
        return r5;
    L_0x00f3:
        r12 = r0;
        r0 = r4;
        r4 = r5;
        goto L_0x008c;
    L_0x00f7:
        r12 = kotlin.Unit.INSTANCE;
        return r12;
    L_0x00fa:
        r12 = (kotlin.Result.Failure) r12;
        r12 = r12.exception;
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexed$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
