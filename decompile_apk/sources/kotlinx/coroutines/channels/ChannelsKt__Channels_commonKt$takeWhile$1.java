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
@DebugMetadata(c = "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$takeWhile$1", f = "Channels.common.kt", i = {2, 3}, l = {864, 864, 866, 867, 865}, m = "invokeSuspend", n = {"e", "e"}, s = {"L$1", "L$1"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$takeWhile$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2 $predicate;
    final /* synthetic */ ReceiveChannel $this_takeWhile;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private ProducerScope p$;

    ChannelsKt__Channels_commonKt$takeWhile$1(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
        this.$this_takeWhile = receiveChannel;
        this.$predicate = function2;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        Continuation channelsKt__Channels_commonKt$takeWhile$1 = new ChannelsKt__Channels_commonKt$takeWhile$1(this.$this_takeWhile, this.$predicate, continuation);
        channelsKt__Channels_commonKt$takeWhile$1.p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$takeWhile$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$takeWhile$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r8) {
        /*
        r7 = this;
        r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r1 = r7.label;
        switch(r1) {
            case 0: goto L_0x006f;
            case 1: goto L_0x005b;
            case 2: goto L_0x0044;
            case 3: goto L_0x002a;
            case 4: goto L_0x0011;
            default: goto L_0x0009;
        };
    L_0x0009:
        r8 = new java.lang.IllegalStateException;
        r0 = "call to 'resume' before 'invoke' with coroutine";
        r8.<init>(r0);
        throw r8;
    L_0x0011:
        r1 = r7.L$2;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r2 = r7.L$1;
        r2 = r7.L$0;
        r2 = (kotlinx.coroutines.channels.ProducerScope) r2;
        r3 = r8 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x0025;
    L_0x001f:
        r8 = r7;
        r6 = r2;
        r2 = r0;
        r0 = r6;
        goto L_0x007e;
    L_0x0025:
        r8 = (kotlin.Result.Failure) r8;
        r8 = r8.exception;
        throw r8;
    L_0x002a:
        r1 = r7.L$2;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r2 = r7.L$1;
        r3 = r7.L$0;
        r3 = (kotlinx.coroutines.channels.ProducerScope) r3;
        r4 = r8 instanceof kotlin.Result.Failure;
        if (r4 != 0) goto L_0x003f;
    L_0x0038:
        r4 = r0;
        r0 = r2;
        r2 = r3;
        r3 = r1;
        r1 = r7;
        goto L_0x00c0;
    L_0x003f:
        r8 = (kotlin.Result.Failure) r8;
        r8 = r8.exception;
        throw r8;
    L_0x0044:
        r1 = r7.L$1;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r2 = r7.L$0;
        r2 = (kotlinx.coroutines.channels.ProducerScope) r2;
        r3 = r8 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x0056;
    L_0x0050:
        r3 = r0;
        r0 = r7;
    L_0x0052:
        r6 = r2;
        r2 = r8;
        r8 = r6;
        goto L_0x00a7;
    L_0x0056:
        r8 = (kotlin.Result.Failure) r8;
        r8 = r8.exception;
        throw r8;
    L_0x005b:
        r1 = r7.L$1;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r2 = r7.L$0;
        r2 = (kotlinx.coroutines.channels.ProducerScope) r2;
        r3 = r8 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x006a;
    L_0x0067:
        r3 = r0;
        r0 = r7;
        goto L_0x0091;
    L_0x006a:
        r8 = (kotlin.Result.Failure) r8;
        r8 = r8.exception;
        throw r8;
    L_0x006f:
        r1 = r8 instanceof kotlin.Result.Failure;
        if (r1 != 0) goto L_0x00e3;
    L_0x0073:
        r8 = r7.p$;
        r1 = r7.$this_takeWhile;
        r1 = r1.iterator();
        r2 = r0;
        r0 = r8;
        r8 = r7;
    L_0x007e:
        r8.L$0 = r0;
        r8.L$1 = r1;
        r3 = 1;
        r8.label = r3;
        r3 = r1.hasNext(r8);
        if (r3 != r2) goto L_0x008c;
    L_0x008b:
        return r2;
    L_0x008c:
        r6 = r0;
        r0 = r8;
        r8 = r3;
        r3 = r2;
        r2 = r6;
    L_0x0091:
        r8 = (java.lang.Boolean) r8;
        r8 = r8.booleanValue();
        if (r8 == 0) goto L_0x00e0;
    L_0x0099:
        r0.L$0 = r2;
        r0.L$1 = r1;
        r8 = 2;
        r0.label = r8;
        r8 = r1.next(r0);
        if (r8 != r3) goto L_0x0052;
    L_0x00a6:
        return r3;
    L_0x00a7:
        r4 = r0.$predicate;
        r0.L$0 = r8;
        r0.L$1 = r2;
        r0.L$2 = r1;
        r5 = 3;
        r0.label = r5;
        r4 = r4.invoke(r2, r0);
        if (r4 != r3) goto L_0x00b9;
    L_0x00b8:
        return r3;
    L_0x00b9:
        r6 = r2;
        r2 = r8;
        r8 = r4;
        r4 = r3;
        r3 = r1;
        r1 = r0;
        r0 = r6;
    L_0x00c0:
        r8 = (java.lang.Boolean) r8;
        r8 = r8.booleanValue();
        if (r8 != 0) goto L_0x00cb;
    L_0x00c8:
        r8 = kotlin.Unit.INSTANCE;
        return r8;
    L_0x00cb:
        r1.L$0 = r2;
        r1.L$1 = r0;
        r1.L$2 = r3;
        r8 = 4;
        r1.label = r8;
        r8 = r2.send(r0, r1);
        if (r8 != r4) goto L_0x00db;
    L_0x00da:
        return r4;
    L_0x00db:
        r8 = r1;
        r0 = r2;
        r1 = r3;
        r2 = r4;
        goto L_0x007e;
    L_0x00e0:
        r8 = kotlin.Unit.INSTANCE;
        return r8;
    L_0x00e3:
        r8 = (kotlin.Result.Failure) r8;
        r8 = r8.exception;
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$takeWhile$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
