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

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "E", "R", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$mapIndexed$1", f = "Channels.common.kt", i = {0, 1, 2, 2, 3, 3}, l = {1214, 1214, 1217, 1216, 1219}, m = "invokeSuspend", n = {"index", "index", "index", "e", "index", "e"}, s = {"I$0", "I$0", "I$0", "L$1", "I$0", "L$1"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$mapIndexed$1 extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel $this_mapIndexed;
    final /* synthetic */ Function3 $transform;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    private ProducerScope p$;

    ChannelsKt__Channels_commonKt$mapIndexed$1(ReceiveChannel receiveChannel, Function3 function3, Continuation continuation) {
        this.$this_mapIndexed = receiveChannel;
        this.$transform = function3;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        Continuation channelsKt__Channels_commonKt$mapIndexed$1 = new ChannelsKt__Channels_commonKt$mapIndexed$1(this.$this_mapIndexed, this.$transform, continuation);
        channelsKt__Channels_commonKt$mapIndexed$1.p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$mapIndexed$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$mapIndexed$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
            case 0: goto L_0x007f;
            case 1: goto L_0x0069;
            case 2: goto L_0x004f;
            case 3: goto L_0x002d;
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
        if (r5 != 0) goto L_0x0028;
    L_0x0022:
        r12 = r11;
        r9 = r3;
        r3 = r1;
        r1 = r9;
        goto L_0x008e;
    L_0x0028:
        r12 = (kotlin.Result.Failure) r12;
        r12 = r12.exception;
        throw r12;
    L_0x002d:
        r1 = r11.L$3;
        r1 = (kotlinx.coroutines.channels.ProducerScope) r1;
        r3 = r11.L$2;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r4 = r11.L$1;
        r5 = r11.I$0;
        r6 = r11.L$0;
        r6 = (kotlinx.coroutines.channels.ProducerScope) r6;
        r7 = r12 instanceof kotlin.Result.Failure;
        if (r7 != 0) goto L_0x004a;
    L_0x0041:
        r7 = r0;
        r0 = r11;
        r9 = r6;
        r6 = r3;
        r3 = r5;
        r5 = r4;
        r4 = r9;
        goto L_0x00dd;
    L_0x004a:
        r12 = (kotlin.Result.Failure) r12;
        r12 = r12.exception;
        throw r12;
    L_0x004f:
        r1 = r11.L$1;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r3 = r11.I$0;
        r4 = r11.L$0;
        r4 = (kotlinx.coroutines.channels.ProducerScope) r4;
        r5 = r12 instanceof kotlin.Result.Failure;
        if (r5 != 0) goto L_0x0064;
    L_0x005d:
        r5 = r0;
        r0 = r11;
    L_0x005f:
        r9 = r4;
        r4 = r12;
        r12 = r1;
        r1 = r9;
        goto L_0x00bc;
    L_0x0064:
        r12 = (kotlin.Result.Failure) r12;
        r12 = r12.exception;
        throw r12;
    L_0x0069:
        r1 = r11.L$1;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r3 = r11.I$0;
        r4 = r11.L$0;
        r4 = (kotlinx.coroutines.channels.ProducerScope) r4;
        r5 = r12 instanceof kotlin.Result.Failure;
        if (r5 != 0) goto L_0x007a;
    L_0x0077:
        r5 = r0;
        r0 = r11;
        goto L_0x00a4;
    L_0x007a:
        r12 = (kotlin.Result.Failure) r12;
        r12 = r12.exception;
        throw r12;
    L_0x007f:
        r1 = r12 instanceof kotlin.Result.Failure;
        if (r1 != 0) goto L_0x00f7;
    L_0x0083:
        r12 = r11.p$;
        r1 = 0;
        r3 = r11.$this_mapIndexed;
        r3 = r3.iterator();
        r4 = r12;
        r12 = r11;
    L_0x008e:
        r12.L$0 = r4;
        r12.I$0 = r1;
        r12.L$1 = r3;
        r12.label = r2;
        r5 = r3.hasNext(r12);
        if (r5 != r0) goto L_0x009d;
    L_0x009c:
        return r0;
    L_0x009d:
        r9 = r0;
        r0 = r12;
        r12 = r5;
        r5 = r9;
        r10 = r3;
        r3 = r1;
        r1 = r10;
    L_0x00a4:
        r12 = (java.lang.Boolean) r12;
        r12 = r12.booleanValue();
        if (r12 == 0) goto L_0x00f4;
    L_0x00ac:
        r0.L$0 = r4;
        r0.I$0 = r3;
        r0.L$1 = r1;
        r12 = 2;
        r0.label = r12;
        r12 = r1.next(r0);
        if (r12 != r5) goto L_0x005f;
    L_0x00bb:
        return r5;
    L_0x00bc:
        r6 = r0.$transform;
        r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r3);
        r3 = r3 + r2;
        r0.L$0 = r1;
        r0.I$0 = r3;
        r0.L$1 = r4;
        r0.L$2 = r12;
        r0.L$3 = r1;
        r8 = 3;
        r0.label = r8;
        r6 = r6.invoke(r7, r4, r0);
        if (r6 != r5) goto L_0x00d7;
    L_0x00d6:
        return r5;
    L_0x00d7:
        r7 = r5;
        r5 = r4;
        r4 = r1;
        r9 = r6;
        r6 = r12;
        r12 = r9;
    L_0x00dd:
        r0.L$0 = r4;
        r0.I$0 = r3;
        r0.L$1 = r5;
        r0.L$2 = r6;
        r5 = 4;
        r0.label = r5;
        r12 = r1.send(r12, r0);
        if (r12 != r7) goto L_0x00ef;
    L_0x00ee:
        return r7;
    L_0x00ef:
        r12 = r0;
        r1 = r3;
        r3 = r6;
        r0 = r7;
        goto L_0x008e;
    L_0x00f4:
        r12 = kotlin.Unit.INSTANCE;
        return r12;
    L_0x00f7:
        r12 = (kotlin.Result.Failure) r12;
        r12 = r12.exception;
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexed$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
