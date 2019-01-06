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

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "E", "K", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$distinctBy$1", f = "Channels.common.kt", i = {0, 1, 2, 2, 3, 3, 3}, l = {1455, 1455, 1458, 1459, 1461}, m = "invokeSuspend", n = {"keys", "keys", "keys", "e", "keys", "e", "k"}, s = {"L$1", "L$1", "L$1", "L$2", "L$1", "L$2", "L$4"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$distinctBy$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2 $selector;
    final /* synthetic */ ReceiveChannel $this_distinctBy;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    private ProducerScope p$;

    ChannelsKt__Channels_commonKt$distinctBy$1(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
        this.$this_distinctBy = receiveChannel;
        this.$selector = function2;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        Continuation channelsKt__Channels_commonKt$distinctBy$1 = new ChannelsKt__Channels_commonKt$distinctBy$1(this.$this_distinctBy, this.$selector, continuation);
        channelsKt__Channels_commonKt$distinctBy$1.p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$distinctBy$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$distinctBy$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r10) {
        /*
        r9 = this;
        r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r1 = r9.label;
        switch(r1) {
            case 0: goto L_0x0081;
            case 1: goto L_0x0069;
            case 2: goto L_0x004d;
            case 3: goto L_0x002f;
            case 4: goto L_0x0011;
            default: goto L_0x0009;
        };
    L_0x0009:
        r10 = new java.lang.IllegalStateException;
        r0 = "call to 'resume' before 'invoke' with coroutine";
        r10.<init>(r0);
        throw r10;
    L_0x0011:
        r1 = r9.L$4;
        r2 = r9.L$3;
        r2 = (kotlinx.coroutines.channels.ChannelIterator) r2;
        r3 = r9.L$2;
        r3 = r9.L$1;
        r3 = (java.util.HashSet) r3;
        r4 = r9.L$0;
        r4 = (kotlinx.coroutines.channels.ProducerScope) r4;
        r5 = r10 instanceof kotlin.Result.Failure;
        if (r5 != 0) goto L_0x002a;
    L_0x0025:
        r5 = r0;
        r10 = r2;
        r0 = r9;
        goto L_0x00f9;
    L_0x002a:
        r10 = (kotlin.Result.Failure) r10;
        r10 = r10.exception;
        throw r10;
    L_0x002f:
        r1 = r9.L$3;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r2 = r9.L$2;
        r3 = r9.L$1;
        r3 = (java.util.HashSet) r3;
        r4 = r9.L$0;
        r4 = (kotlinx.coroutines.channels.ProducerScope) r4;
        r5 = r10 instanceof kotlin.Result.Failure;
        if (r5 != 0) goto L_0x0048;
    L_0x0041:
        r5 = r0;
        r0 = r9;
        r7 = r1;
        r1 = r10;
        r10 = r7;
        goto L_0x00df;
    L_0x0048:
        r10 = (kotlin.Result.Failure) r10;
        r10 = r10.exception;
        throw r10;
    L_0x004d:
        r1 = r9.L$2;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r2 = r9.L$1;
        r2 = (java.util.HashSet) r2;
        r3 = r9.L$0;
        r3 = (kotlinx.coroutines.channels.ProducerScope) r3;
        r4 = r10 instanceof kotlin.Result.Failure;
        if (r4 != 0) goto L_0x0064;
    L_0x005d:
        r4 = r0;
        r0 = r9;
    L_0x005f:
        r7 = r2;
        r2 = r10;
        r10 = r7;
        goto L_0x00c5;
    L_0x0064:
        r10 = (kotlin.Result.Failure) r10;
        r10 = r10.exception;
        throw r10;
    L_0x0069:
        r1 = r9.L$2;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r2 = r9.L$1;
        r2 = (java.util.HashSet) r2;
        r3 = r9.L$0;
        r3 = (kotlinx.coroutines.channels.ProducerScope) r3;
        r4 = r10 instanceof kotlin.Result.Failure;
        if (r4 != 0) goto L_0x007c;
    L_0x0079:
        r4 = r0;
        r0 = r9;
        goto L_0x00ad;
    L_0x007c:
        r10 = (kotlin.Result.Failure) r10;
        r10 = r10.exception;
        throw r10;
    L_0x0081:
        r1 = r10 instanceof kotlin.Result.Failure;
        if (r1 != 0) goto L_0x0108;
    L_0x0085:
        r10 = r9.p$;
        r1 = new java.util.HashSet;
        r1.<init>();
        r2 = r9.$this_distinctBy;
        r2 = r2.iterator();
        r3 = r0;
        r0 = r10;
        r10 = r9;
    L_0x0095:
        r10.L$0 = r0;
        r10.L$1 = r1;
        r10.L$2 = r2;
        r4 = 1;
        r10.label = r4;
        r4 = r2.hasNext(r10);
        if (r4 != r3) goto L_0x00a5;
    L_0x00a4:
        return r3;
    L_0x00a5:
        r7 = r0;
        r0 = r10;
        r10 = r4;
        r4 = r3;
        r3 = r7;
        r8 = r2;
        r2 = r1;
        r1 = r8;
    L_0x00ad:
        r10 = (java.lang.Boolean) r10;
        r10 = r10.booleanValue();
        if (r10 == 0) goto L_0x0105;
    L_0x00b5:
        r0.L$0 = r3;
        r0.L$1 = r2;
        r0.L$2 = r1;
        r10 = 2;
        r0.label = r10;
        r10 = r1.next(r0);
        if (r10 != r4) goto L_0x005f;
    L_0x00c4:
        return r4;
    L_0x00c5:
        r5 = r0.$selector;
        r0.L$0 = r3;
        r0.L$1 = r10;
        r0.L$2 = r2;
        r0.L$3 = r1;
        r6 = 3;
        r0.label = r6;
        r5 = r5.invoke(r2, r0);
        if (r5 != r4) goto L_0x00d9;
    L_0x00d8:
        return r4;
    L_0x00d9:
        r7 = r3;
        r3 = r10;
        r10 = r1;
        r1 = r5;
        r5 = r4;
        r4 = r7;
    L_0x00df:
        r6 = r3.contains(r1);
        if (r6 != 0) goto L_0x00ff;
    L_0x00e5:
        r0.L$0 = r4;
        r0.L$1 = r3;
        r0.L$2 = r2;
        r0.L$3 = r10;
        r0.L$4 = r1;
        r6 = 4;
        r0.label = r6;
        r2 = r4.send(r2, r0);
        if (r2 != r5) goto L_0x00f9;
    L_0x00f8:
        return r5;
    L_0x00f9:
        r2 = r3;
        r2 = (java.util.Collection) r2;
        r2.add(r1);
    L_0x00ff:
        r2 = r10;
        r10 = r0;
        r1 = r3;
        r0 = r4;
        r3 = r5;
        goto L_0x0095;
    L_0x0105:
        r10 = kotlin.Unit.INSTANCE;
        return r10;
    L_0x0108:
        r10 = (kotlin.Result.Failure) r10;
        r10 = r10.exception;
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$distinctBy$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
