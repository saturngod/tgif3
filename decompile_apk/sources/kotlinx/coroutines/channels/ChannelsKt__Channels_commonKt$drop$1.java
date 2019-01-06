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
@DebugMetadata(c = "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$drop$1", f = "Channels.common.kt", i = {0, 1, 2, 3, 4, 4}, l = {584, 584, 589, 584, 594, 593}, m = "invokeSuspend", n = {"remaining", "remaining", "remaining", "remaining", "remaining", "e"}, s = {"I$0", "I$0", "I$0", "I$0", "I$0", "L$1"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$drop$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $n;
    final /* synthetic */ ReceiveChannel $this_drop;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private ProducerScope p$;

    ChannelsKt__Channels_commonKt$drop$1(ReceiveChannel receiveChannel, int i, Continuation continuation) {
        this.$this_drop = receiveChannel;
        this.$n = i;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        Continuation channelsKt__Channels_commonKt$drop$1 = new ChannelsKt__Channels_commonKt$drop$1(this.$this_drop, this.$n, continuation);
        channelsKt__Channels_commonKt$drop$1.p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$drop$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$drop$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
            case 0: goto L_0x0088;
            case 1: goto L_0x0072;
            case 2: goto L_0x0059;
            case 3: goto L_0x0042;
            case 4: goto L_0x002b;
            case 5: goto L_0x0012;
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
        r2 = r8.L$1;
        r2 = r8.I$0;
        r3 = r8.L$0;
        r3 = (kotlinx.coroutines.channels.ProducerScope) r3;
        r4 = r9 instanceof kotlin.Result.Failure;
        if (r4 != 0) goto L_0x0026;
    L_0x0022:
        r9 = r8;
        r4 = r0;
        goto L_0x00ef;
    L_0x0026:
        r9 = (kotlin.Result.Failure) r9;
        r9 = r9.exception;
        throw r9;
    L_0x002b:
        r1 = r8.L$1;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r2 = r8.I$0;
        r3 = r8.L$0;
        r3 = (kotlinx.coroutines.channels.ProducerScope) r3;
        r4 = r9 instanceof kotlin.Result.Failure;
        if (r4 != 0) goto L_0x003d;
    L_0x0039:
        r4 = r0;
        r0 = r8;
        goto L_0x011a;
    L_0x003d:
        r9 = (kotlin.Result.Failure) r9;
        r9 = r9.exception;
        throw r9;
    L_0x0042:
        r1 = r8.L$1;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r2 = r8.I$0;
        r3 = r8.L$0;
        r3 = (kotlinx.coroutines.channels.ProducerScope) r3;
        r4 = r9 instanceof kotlin.Result.Failure;
        if (r4 != 0) goto L_0x0054;
    L_0x0050:
        r4 = r0;
        r0 = r8;
        goto L_0x0102;
    L_0x0054:
        r9 = (kotlin.Result.Failure) r9;
        r9 = r9.exception;
        throw r9;
    L_0x0059:
        r1 = r8.L$1;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r3 = r8.I$0;
        r4 = r8.L$0;
        r4 = (kotlinx.coroutines.channels.ProducerScope) r4;
        r5 = r9 instanceof kotlin.Result.Failure;
        if (r5 != 0) goto L_0x006d;
    L_0x0067:
        r9 = r8;
        r6 = r4;
        r4 = r0;
        r0 = r6;
        goto L_0x00d6;
    L_0x006d:
        r9 = (kotlin.Result.Failure) r9;
        r9 = r9.exception;
        throw r9;
    L_0x0072:
        r1 = r8.L$1;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r3 = r8.I$0;
        r4 = r8.L$0;
        r4 = (kotlinx.coroutines.channels.ProducerScope) r4;
        r5 = r9 instanceof kotlin.Result.Failure;
        if (r5 != 0) goto L_0x0083;
    L_0x0080:
        r5 = r0;
        r0 = r8;
        goto L_0x00bb;
    L_0x0083:
        r9 = (kotlin.Result.Failure) r9;
        r9 = r9.exception;
        throw r9;
    L_0x0088:
        r1 = r9 instanceof kotlin.Result.Failure;
        if (r1 != 0) goto L_0x0155;
    L_0x008c:
        r9 = r8.p$;
        r1 = r8.$n;
        if (r1 < 0) goto L_0x0094;
    L_0x0092:
        r1 = 1;
        goto L_0x0095;
    L_0x0094:
        r1 = 0;
    L_0x0095:
        if (r1 == 0) goto L_0x0131;
    L_0x0097:
        r1 = r8.$n;
        if (r1 <= 0) goto L_0x00e3;
    L_0x009b:
        r3 = r8.$this_drop;
        r3 = r3.iterator();
        r4 = r0;
        r0 = r9;
        r9 = r8;
    L_0x00a4:
        r9.L$0 = r0;
        r9.I$0 = r1;
        r9.L$1 = r3;
        r9.label = r2;
        r5 = r3.hasNext(r9);
        if (r5 != r4) goto L_0x00b3;
    L_0x00b2:
        return r4;
    L_0x00b3:
        r6 = r0;
        r0 = r9;
        r9 = r5;
        r5 = r4;
        r4 = r6;
        r7 = r3;
        r3 = r1;
        r1 = r7;
    L_0x00bb:
        r9 = (java.lang.Boolean) r9;
        r9 = r9.booleanValue();
        if (r9 == 0) goto L_0x00df;
    L_0x00c3:
        r0.L$0 = r4;
        r0.I$0 = r3;
        r0.L$1 = r1;
        r9 = 2;
        r0.label = r9;
        r9 = r1.next(r0);
        if (r9 != r5) goto L_0x00d3;
    L_0x00d2:
        return r5;
    L_0x00d3:
        r9 = r0;
        r0 = r4;
        r4 = r5;
    L_0x00d6:
        r3 = r3 + -1;
        if (r3 != 0) goto L_0x00db;
    L_0x00da:
        goto L_0x00e7;
    L_0x00db:
        r6 = r3;
        r3 = r1;
        r1 = r6;
        goto L_0x00a4;
    L_0x00df:
        r9 = r0;
        r0 = r4;
        r4 = r5;
        goto L_0x00e7;
    L_0x00e3:
        r4 = r0;
        r3 = r1;
        r0 = r9;
        r9 = r8;
    L_0x00e7:
        r1 = r9.$this_drop;
        r1 = r1.iterator();
        r2 = r3;
        r3 = r0;
    L_0x00ef:
        r9.L$0 = r3;
        r9.I$0 = r2;
        r9.L$1 = r1;
        r0 = 3;
        r9.label = r0;
        r0 = r1.hasNext(r9);
        if (r0 != r4) goto L_0x00ff;
    L_0x00fe:
        return r4;
    L_0x00ff:
        r6 = r0;
        r0 = r9;
        r9 = r6;
    L_0x0102:
        r9 = (java.lang.Boolean) r9;
        r9 = r9.booleanValue();
        if (r9 == 0) goto L_0x012e;
    L_0x010a:
        r0.L$0 = r3;
        r0.I$0 = r2;
        r0.L$1 = r1;
        r9 = 4;
        r0.label = r9;
        r9 = r1.next(r0);
        if (r9 != r4) goto L_0x011a;
    L_0x0119:
        return r4;
    L_0x011a:
        r0.L$0 = r3;
        r0.I$0 = r2;
        r0.L$1 = r9;
        r0.L$2 = r1;
        r5 = 5;
        r0.label = r5;
        r9 = r3.send(r9, r0);
        if (r9 != r4) goto L_0x012c;
    L_0x012b:
        return r4;
    L_0x012c:
        r9 = r0;
        goto L_0x00ef;
    L_0x012e:
        r9 = kotlin.Unit.INSTANCE;
        return r9;
    L_0x0131:
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
    L_0x0155:
        r9 = (kotlin.Result.Failure) r9;
        r9 = r9.exception;
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$drop$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
