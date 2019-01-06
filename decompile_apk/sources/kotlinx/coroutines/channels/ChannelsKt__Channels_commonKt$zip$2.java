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

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0005H@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "E", "R", "V", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$zip$2", f = "Channels.common.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4}, l = {1881, 1881, 1884, 1885, 1886, 1887}, m = "invokeSuspend", n = {"otherIterator", "$receiver$iv", "$receiver$iv$iv", "cause$iv$iv", "$receiver$iv", "otherIterator", "$receiver$iv", "$receiver$iv$iv", "cause$iv$iv", "$receiver$iv", "otherIterator", "$receiver$iv", "$receiver$iv$iv", "cause$iv$iv", "$receiver$iv", "e$iv", "element1", "otherIterator", "$receiver$iv", "$receiver$iv$iv", "cause$iv$iv", "$receiver$iv", "e$iv", "element1", "otherIterator", "$receiver$iv", "$receiver$iv$iv", "cause$iv$iv", "$receiver$iv", "e$iv", "element1", "element2"}, s = {"L$1", "L$2", "L$4", "L$5", "L$6", "L$1", "L$2", "L$4", "L$5", "L$6", "L$1", "L$2", "L$4", "L$5", "L$6", "L$8", "L$9", "L$1", "L$2", "L$4", "L$5", "L$6", "L$8", "L$9", "L$1", "L$2", "L$4", "L$5", "L$6", "L$8", "L$9", "L$10"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$zip$2 extends SuspendLambda implements Function2<ProducerScope<? super V>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel $other;
    final /* synthetic */ ReceiveChannel $this_zip;
    final /* synthetic */ Function2 $transform;
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    private ProducerScope p$;

    ChannelsKt__Channels_commonKt$zip$2(ReceiveChannel receiveChannel, ReceiveChannel receiveChannel2, Function2 function2, Continuation continuation) {
        this.$this_zip = receiveChannel;
        this.$other = receiveChannel2;
        this.$transform = function2;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        Continuation channelsKt__Channels_commonKt$zip$2 = new ChannelsKt__Channels_commonKt$zip$2(this.$this_zip, this.$other, this.$transform, continuation);
        channelsKt__Channels_commonKt$zip$2.p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$zip$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$zip$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r18) {
        /*
        r17 = this;
        r1 = r17;
        r0 = r18;
        r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r3 = r1.label;
        switch(r3) {
            case 0: goto L_0x012c;
            case 1: goto L_0x00f9;
            case 2: goto L_0x00c8;
            case 3: goto L_0x0086;
            case 4: goto L_0x0048;
            case 5: goto L_0x0015;
            default: goto L_0x000d;
        };
    L_0x000d:
        r0 = new java.lang.IllegalStateException;
        r2 = "call to 'resume' before 'invoke' with coroutine";
        r0.<init>(r2);
        throw r0;
    L_0x0015:
        r3 = r1.L$10;
        r3 = r1.L$9;
        r3 = r1.L$8;
        r3 = r1.L$7;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r4 = r1.L$6;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r1.L$5;
        r5 = (java.lang.Throwable) r5;
        r6 = r1.L$4;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r1.L$3;
        r7 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2) r7;
        r8 = r1.L$2;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$1;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r10 = r1.L$0;
        r10 = (kotlinx.coroutines.channels.ProducerScope) r10;
        r11 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0128 }
        if (r11 != 0) goto L_0x0043;
    L_0x003f:
        r13 = r2;
        r2 = r1;
        goto L_0x0210;
    L_0x0043:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x0128 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x0128 }
        throw r0;	 Catch:{ Throwable -> 0x0128 }
    L_0x0048:
        r3 = r1.L$9;
        r4 = r1.L$8;
        r5 = r1.L$7;
        r5 = (kotlinx.coroutines.channels.ChannelIterator) r5;
        r6 = r1.L$6;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r1.L$5;
        r7 = (java.lang.Throwable) r7;
        r8 = r1.L$4;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$3;
        r9 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2) r9;
        r10 = r1.L$2;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r11 = r1.L$1;
        r11 = (kotlinx.coroutines.channels.ChannelIterator) r11;
        r12 = r1.L$0;
        r12 = (kotlinx.coroutines.channels.ProducerScope) r12;
        r13 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00c3, all -> 0x00be }
        if (r13 != 0) goto L_0x0081;
    L_0x0070:
        r13 = r2;
        r2 = r1;
        r15 = r12;
        r12 = r4;
        r4 = r6;
        r6 = r8;
        r8 = r10;
        r10 = r15;
        r16 = r11;
        r11 = r5;
        r5 = r7;
        r7 = r9;
        r9 = r16;
        goto L_0x01e9;
    L_0x0081:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x00c3, all -> 0x00be }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x00c3, all -> 0x00be }
        throw r0;	 Catch:{ Throwable -> 0x00c3, all -> 0x00be }
    L_0x0086:
        r3 = r1.L$9;
        r4 = r1.L$8;
        r5 = r1.L$7;
        r5 = (kotlinx.coroutines.channels.ChannelIterator) r5;
        r6 = r1.L$6;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r1.L$5;
        r7 = (java.lang.Throwable) r7;
        r8 = r1.L$4;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$3;
        r9 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2) r9;
        r10 = r1.L$2;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r11 = r1.L$1;
        r11 = (kotlinx.coroutines.channels.ChannelIterator) r11;
        r12 = r1.L$0;
        r12 = (kotlinx.coroutines.channels.ProducerScope) r12;
        r13 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00c3, all -> 0x00be }
        if (r13 != 0) goto L_0x00b9;
    L_0x00ae:
        r13 = r3;
        r3 = r5;
        r5 = r7;
        r7 = r2;
        r2 = r1;
        r15 = r8;
        r8 = r4;
        r4 = r6;
        r6 = r15;
        goto L_0x01b0;
    L_0x00b9:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x00c3, all -> 0x00be }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x00c3, all -> 0x00be }
        throw r0;	 Catch:{ Throwable -> 0x00c3, all -> 0x00be }
    L_0x00be:
        r0 = move-exception;
        r5 = r7;
        r6 = r8;
        goto L_0x021d;
    L_0x00c3:
        r0 = move-exception;
        r5 = r0;
        r6 = r8;
        goto L_0x021c;
    L_0x00c8:
        r3 = r1.L$7;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r4 = r1.L$6;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r1.L$5;
        r5 = (java.lang.Throwable) r5;
        r6 = r1.L$4;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r1.L$3;
        r7 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2) r7;
        r8 = r1.L$2;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$1;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r10 = r1.L$0;
        r10 = (kotlinx.coroutines.channels.ProducerScope) r10;
        r11 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0128 }
        if (r11 != 0) goto L_0x00f4;
    L_0x00ec:
        r11 = r9;
        r12 = r10;
        r9 = r7;
        r10 = r8;
        r7 = r2;
        r2 = r1;
        goto L_0x018f;
    L_0x00f4:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x0128 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x0128 }
        throw r0;	 Catch:{ Throwable -> 0x0128 }
    L_0x00f9:
        r3 = r1.L$7;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r4 = r1.L$6;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r1.L$5;
        r5 = (java.lang.Throwable) r5;
        r6 = r1.L$4;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r1.L$3;
        r7 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2) r7;
        r8 = r1.L$2;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$1;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r10 = r1.L$0;
        r10 = (kotlinx.coroutines.channels.ProducerScope) r10;
        r11 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0128 }
        if (r11 != 0) goto L_0x0120;
    L_0x011d:
        r11 = r2;
        r2 = r1;
        goto L_0x0167;
    L_0x0120:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x0128 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x0128 }
        throw r0;	 Catch:{ Throwable -> 0x0128 }
    L_0x0125:
        r0 = move-exception;
        goto L_0x021d;
    L_0x0128:
        r0 = move-exception;
        r5 = r0;
        goto L_0x021c;
    L_0x012c:
        r3 = r0 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x0221;
    L_0x0130:
        r0 = r1.p$;
        r3 = r1.$other;
        r3 = r3.iterator();
        r6 = r1.$this_zip;
        r4 = 0;
        r5 = r4;
        r5 = (java.lang.Throwable) r5;
        r4 = r6.iterator();	 Catch:{ Throwable -> 0x0128 }
        r10 = r0;
        r0 = r1;
        r7 = r0;
        r9 = r3;
        r3 = r4;
        r4 = r6;
        r8 = r4;
    L_0x0149:
        r0.L$0 = r10;	 Catch:{ Throwable -> 0x0128 }
        r0.L$1 = r9;	 Catch:{ Throwable -> 0x0128 }
        r0.L$2 = r8;	 Catch:{ Throwable -> 0x0128 }
        r0.L$3 = r7;	 Catch:{ Throwable -> 0x0128 }
        r0.L$4 = r6;	 Catch:{ Throwable -> 0x0128 }
        r0.L$5 = r5;	 Catch:{ Throwable -> 0x0128 }
        r0.L$6 = r4;	 Catch:{ Throwable -> 0x0128 }
        r0.L$7 = r3;	 Catch:{ Throwable -> 0x0128 }
        r11 = 1;
        r0.label = r11;	 Catch:{ Throwable -> 0x0128 }
        r11 = r3.hasNext(r7);	 Catch:{ Throwable -> 0x0128 }
        if (r11 != r2) goto L_0x0163;
    L_0x0162:
        return r2;
    L_0x0163:
        r15 = r2;
        r2 = r0;
        r0 = r11;
        r11 = r15;
    L_0x0167:
        r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x0128 }
        r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x0128 }
        if (r0 == 0) goto L_0x0214;
    L_0x016f:
        r2.L$0 = r10;	 Catch:{ Throwable -> 0x0128 }
        r2.L$1 = r9;	 Catch:{ Throwable -> 0x0128 }
        r2.L$2 = r8;	 Catch:{ Throwable -> 0x0128 }
        r2.L$3 = r7;	 Catch:{ Throwable -> 0x0128 }
        r2.L$4 = r6;	 Catch:{ Throwable -> 0x0128 }
        r2.L$5 = r5;	 Catch:{ Throwable -> 0x0128 }
        r2.L$6 = r4;	 Catch:{ Throwable -> 0x0128 }
        r2.L$7 = r3;	 Catch:{ Throwable -> 0x0128 }
        r0 = 2;
        r2.label = r0;	 Catch:{ Throwable -> 0x0128 }
        r0 = r3.next(r7);	 Catch:{ Throwable -> 0x0128 }
        if (r0 != r11) goto L_0x0189;
    L_0x0188:
        return r11;
    L_0x0189:
        r12 = r10;
        r10 = r8;
        r15 = r9;
        r9 = r7;
        r7 = r11;
        r11 = r15;
    L_0x018f:
        r2.L$0 = r12;	 Catch:{ Throwable -> 0x0128 }
        r2.L$1 = r11;	 Catch:{ Throwable -> 0x0128 }
        r2.L$2 = r10;	 Catch:{ Throwable -> 0x0128 }
        r2.L$3 = r9;	 Catch:{ Throwable -> 0x0128 }
        r2.L$4 = r6;	 Catch:{ Throwable -> 0x0128 }
        r2.L$5 = r5;	 Catch:{ Throwable -> 0x0128 }
        r2.L$6 = r4;	 Catch:{ Throwable -> 0x0128 }
        r2.L$7 = r3;	 Catch:{ Throwable -> 0x0128 }
        r2.L$8 = r0;	 Catch:{ Throwable -> 0x0128 }
        r2.L$9 = r0;	 Catch:{ Throwable -> 0x0128 }
        r8 = 3;
        r2.label = r8;	 Catch:{ Throwable -> 0x0128 }
        r8 = r11.hasNext(r2);	 Catch:{ Throwable -> 0x0128 }
        if (r8 != r7) goto L_0x01ad;
    L_0x01ac:
        return r7;
    L_0x01ad:
        r13 = r0;
        r0 = r8;
        r8 = r13;
    L_0x01b0:
        r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x0128 }
        r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x0128 }
        if (r0 != 0) goto L_0x01bf;
    L_0x01b8:
        r0 = r2;
        r2 = r7;
        r7 = r9;
        r8 = r10;
        r9 = r11;
        r10 = r12;
        goto L_0x0149;
    L_0x01bf:
        r2.L$0 = r12;	 Catch:{ Throwable -> 0x0128 }
        r2.L$1 = r11;	 Catch:{ Throwable -> 0x0128 }
        r2.L$2 = r10;	 Catch:{ Throwable -> 0x0128 }
        r2.L$3 = r9;	 Catch:{ Throwable -> 0x0128 }
        r2.L$4 = r6;	 Catch:{ Throwable -> 0x0128 }
        r2.L$5 = r5;	 Catch:{ Throwable -> 0x0128 }
        r2.L$6 = r4;	 Catch:{ Throwable -> 0x0128 }
        r2.L$7 = r3;	 Catch:{ Throwable -> 0x0128 }
        r2.L$8 = r8;	 Catch:{ Throwable -> 0x0128 }
        r2.L$9 = r13;	 Catch:{ Throwable -> 0x0128 }
        r0 = 4;
        r2.label = r0;	 Catch:{ Throwable -> 0x0128 }
        r0 = r11.next(r2);	 Catch:{ Throwable -> 0x0128 }
        if (r0 != r7) goto L_0x01dd;
    L_0x01dc:
        return r7;
    L_0x01dd:
        r15 = r11;
        r11 = r3;
        r3 = r13;
        r13 = r7;
        r7 = r9;
        r9 = r15;
        r16 = r12;
        r12 = r8;
        r8 = r10;
        r10 = r16;
    L_0x01e9:
        r14 = r2.$transform;	 Catch:{ Throwable -> 0x0128 }
        r14 = r14.invoke(r3, r0);	 Catch:{ Throwable -> 0x0128 }
        r2.L$0 = r10;	 Catch:{ Throwable -> 0x0128 }
        r2.L$1 = r9;	 Catch:{ Throwable -> 0x0128 }
        r2.L$2 = r8;	 Catch:{ Throwable -> 0x0128 }
        r2.L$3 = r7;	 Catch:{ Throwable -> 0x0128 }
        r2.L$4 = r6;	 Catch:{ Throwable -> 0x0128 }
        r2.L$5 = r5;	 Catch:{ Throwable -> 0x0128 }
        r2.L$6 = r4;	 Catch:{ Throwable -> 0x0128 }
        r2.L$7 = r11;	 Catch:{ Throwable -> 0x0128 }
        r2.L$8 = r12;	 Catch:{ Throwable -> 0x0128 }
        r2.L$9 = r3;	 Catch:{ Throwable -> 0x0128 }
        r2.L$10 = r0;	 Catch:{ Throwable -> 0x0128 }
        r0 = 5;
        r2.label = r0;	 Catch:{ Throwable -> 0x0128 }
        r0 = r10.send(r14, r2);	 Catch:{ Throwable -> 0x0128 }
        if (r0 != r13) goto L_0x020f;
    L_0x020e:
        return r13;
    L_0x020f:
        r3 = r11;
    L_0x0210:
        r0 = r2;
        r2 = r13;
        goto L_0x0149;
    L_0x0214:
        r0 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0128 }
        r6.cancel(r5);
        r0 = kotlin.Unit.INSTANCE;
        return r0;
    L_0x021c:
        throw r5;	 Catch:{ all -> 0x0125 }
    L_0x021d:
        r6.cancel(r5);
        throw r0;
    L_0x0221:
        r0 = (kotlin.Result.Failure) r0;
        r0 = r0.exception;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
