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

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "E", "R", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$map$1", f = "Channels.common.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3}, l = {1193, 1193, 1195, 1196, 1897}, m = "invokeSuspend", n = {"$receiver$iv", "$receiver$iv$iv", "cause$iv$iv", "$receiver$iv", "$receiver$iv", "$receiver$iv$iv", "cause$iv$iv", "$receiver$iv", "$receiver$iv", "$receiver$iv$iv", "cause$iv$iv", "$receiver$iv", "e$iv", "it", "$receiver$iv", "$receiver$iv$iv", "cause$iv$iv", "$receiver$iv", "e$iv", "it"}, s = {"L$1", "L$3", "L$4", "L$5", "L$1", "L$3", "L$4", "L$5", "L$1", "L$3", "L$4", "L$5", "L$7", "L$8", "L$1", "L$3", "L$4", "L$5", "L$7", "L$8"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$map$1 extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel $this_map;
    final /* synthetic */ Function2 $transform;
    Object L$0;
    Object L$1;
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

    ChannelsKt__Channels_commonKt$map$1(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
        this.$this_map = receiveChannel;
        this.$transform = function2;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        Continuation channelsKt__Channels_commonKt$map$1 = new ChannelsKt__Channels_commonKt$map$1(this.$this_map, this.$transform, continuation);
        channelsKt__Channels_commonKt$map$1.p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$map$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$map$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r15) {
        /*
        r14 = this;
        r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r1 = r14.label;
        switch(r1) {
            case 0: goto L_0x00e4;
            case 1: goto L_0x00b5;
            case 2: goto L_0x0086;
            case 3: goto L_0x0041;
            case 4: goto L_0x0011;
            default: goto L_0x0009;
        };
    L_0x0009:
        r15 = new java.lang.IllegalStateException;
        r0 = "call to 'resume' before 'invoke' with coroutine";
        r15.<init>(r0);
        throw r15;
    L_0x0011:
        r1 = r14.L$8;
        r1 = r14.L$7;
        r1 = r14.L$6;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r2 = r14.L$5;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r3 = r14.L$4;
        r3 = (java.lang.Throwable) r3;
        r4 = r14.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r14.L$2;
        r5 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$map$1) r5;
        r6 = r14.L$1;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r14.L$0;
        r7 = (kotlinx.coroutines.channels.ProducerScope) r7;
        r8 = r15 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00e0 }
        if (r8 != 0) goto L_0x003c;
    L_0x0035:
        r15 = r14;
        r12 = r6;
        r6 = r0;
        r0 = r4;
        r4 = r12;
        goto L_0x00fa;
    L_0x003c:
        r15 = (kotlin.Result.Failure) r15;	 Catch:{ Throwable -> 0x00e0 }
        r15 = r15.exception;	 Catch:{ Throwable -> 0x00e0 }
        throw r15;	 Catch:{ Throwable -> 0x00e0 }
    L_0x0041:
        r1 = r14.L$9;
        r1 = (kotlinx.coroutines.channels.ProducerScope) r1;
        r2 = r14.L$8;
        r3 = r14.L$7;
        r4 = r14.L$6;
        r4 = (kotlinx.coroutines.channels.ChannelIterator) r4;
        r5 = r14.L$5;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r14.L$4;
        r6 = (java.lang.Throwable) r6;
        r7 = r14.L$3;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r14.L$2;
        r8 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$map$1) r8;
        r9 = r14.L$1;
        r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9;
        r10 = r14.L$0;
        r10 = (kotlinx.coroutines.channels.ProducerScope) r10;
        r11 = r15 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0081, all -> 0x007c }
        if (r11 != 0) goto L_0x0077;
    L_0x0069:
        r11 = r0;
        r0 = r14;
        r12 = r10;
        r10 = r2;
        r2 = r5;
        r5 = r4;
        r4 = r7;
        r7 = r12;
        r13 = r9;
        r9 = r3;
        r3 = r6;
        r6 = r13;
        goto L_0x0166;
    L_0x0077:
        r15 = (kotlin.Result.Failure) r15;	 Catch:{ Throwable -> 0x0081, all -> 0x007c }
        r15 = r15.exception;	 Catch:{ Throwable -> 0x0081, all -> 0x007c }
        throw r15;	 Catch:{ Throwable -> 0x0081, all -> 0x007c }
    L_0x007c:
        r15 = move-exception;
        r3 = r6;
        r4 = r7;
        goto L_0x0199;
    L_0x0081:
        r15 = move-exception;
        r3 = r15;
        r4 = r7;
        goto L_0x0198;
    L_0x0086:
        r1 = r14.L$6;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r2 = r14.L$5;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r3 = r14.L$4;
        r3 = (java.lang.Throwable) r3;
        r4 = r14.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r14.L$2;
        r5 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$map$1) r5;
        r6 = r14.L$1;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r14.L$0;
        r7 = (kotlinx.coroutines.channels.ProducerScope) r7;
        r8 = r15 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00e0 }
        if (r8 != 0) goto L_0x00b0;
    L_0x00a6:
        r8 = r5;
        r5 = r1;
        r1 = r7;
        r7 = r0;
        r0 = r14;
        r12 = r2;
        r2 = r15;
        r15 = r12;
        goto L_0x0140;
    L_0x00b0:
        r15 = (kotlin.Result.Failure) r15;	 Catch:{ Throwable -> 0x00e0 }
        r15 = r15.exception;	 Catch:{ Throwable -> 0x00e0 }
        throw r15;	 Catch:{ Throwable -> 0x00e0 }
    L_0x00b5:
        r1 = r14.L$6;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r2 = r14.L$5;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r3 = r14.L$4;
        r3 = (java.lang.Throwable) r3;
        r4 = r14.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r14.L$2;
        r5 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$map$1) r5;
        r6 = r14.L$1;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r14.L$0;
        r7 = (kotlinx.coroutines.channels.ProducerScope) r7;
        r8 = r15 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00e0 }
        if (r8 != 0) goto L_0x00d8;
    L_0x00d5:
        r8 = r0;
        r0 = r14;
        goto L_0x0118;
    L_0x00d8:
        r15 = (kotlin.Result.Failure) r15;	 Catch:{ Throwable -> 0x00e0 }
        r15 = r15.exception;	 Catch:{ Throwable -> 0x00e0 }
        throw r15;	 Catch:{ Throwable -> 0x00e0 }
    L_0x00dd:
        r15 = move-exception;
        goto L_0x0199;
    L_0x00e0:
        r15 = move-exception;
        r3 = r15;
        goto L_0x0198;
    L_0x00e4:
        r1 = r15 instanceof kotlin.Result.Failure;
        if (r1 != 0) goto L_0x019d;
    L_0x00e8:
        r15 = r14.p$;
        r4 = r14.$this_map;
        r1 = 0;
        r3 = r1;
        r3 = (java.lang.Throwable) r3;
        r1 = r4.iterator();	 Catch:{ Throwable -> 0x00e0 }
        r5 = r14;
        r7 = r15;
        r6 = r0;
        r0 = r4;
        r2 = r0;
        r15 = r5;
    L_0x00fa:
        r15.L$0 = r7;	 Catch:{ Throwable -> 0x0195, all -> 0x0192 }
        r15.L$1 = r4;	 Catch:{ Throwable -> 0x0195, all -> 0x0192 }
        r15.L$2 = r5;	 Catch:{ Throwable -> 0x0195, all -> 0x0192 }
        r15.L$3 = r0;	 Catch:{ Throwable -> 0x0195, all -> 0x0192 }
        r15.L$4 = r3;	 Catch:{ Throwable -> 0x0195, all -> 0x0192 }
        r15.L$5 = r2;	 Catch:{ Throwable -> 0x0195, all -> 0x0192 }
        r15.L$6 = r1;	 Catch:{ Throwable -> 0x0195, all -> 0x0192 }
        r8 = 1;
        r15.label = r8;	 Catch:{ Throwable -> 0x0195, all -> 0x0192 }
        r8 = r1.hasNext(r5);	 Catch:{ Throwable -> 0x0195, all -> 0x0192 }
        if (r8 != r6) goto L_0x0112;
    L_0x0111:
        return r6;
    L_0x0112:
        r12 = r0;
        r0 = r15;
        r15 = r8;
        r8 = r6;
        r6 = r4;
        r4 = r12;
    L_0x0118:
        r15 = (java.lang.Boolean) r15;	 Catch:{ Throwable -> 0x00e0 }
        r15 = r15.booleanValue();	 Catch:{ Throwable -> 0x00e0 }
        if (r15 == 0) goto L_0x018a;
    L_0x0120:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x00e0 }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x00e0 }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x00e0 }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x00e0 }
        r0.L$4 = r3;	 Catch:{ Throwable -> 0x00e0 }
        r0.L$5 = r2;	 Catch:{ Throwable -> 0x00e0 }
        r0.L$6 = r1;	 Catch:{ Throwable -> 0x00e0 }
        r15 = 2;
        r0.label = r15;	 Catch:{ Throwable -> 0x00e0 }
        r15 = r1.next(r5);	 Catch:{ Throwable -> 0x00e0 }
        if (r15 != r8) goto L_0x0138;
    L_0x0137:
        return r8;
    L_0x0138:
        r12 = r2;
        r2 = r15;
        r15 = r12;
        r13 = r5;
        r5 = r1;
        r1 = r7;
        r7 = r8;
        r8 = r13;
    L_0x0140:
        r9 = r0.$transform;	 Catch:{ Throwable -> 0x00e0 }
        r0.L$0 = r1;	 Catch:{ Throwable -> 0x00e0 }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x00e0 }
        r0.L$2 = r8;	 Catch:{ Throwable -> 0x00e0 }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x00e0 }
        r0.L$4 = r3;	 Catch:{ Throwable -> 0x00e0 }
        r0.L$5 = r15;	 Catch:{ Throwable -> 0x00e0 }
        r0.L$6 = r5;	 Catch:{ Throwable -> 0x00e0 }
        r0.L$7 = r2;	 Catch:{ Throwable -> 0x00e0 }
        r0.L$8 = r2;	 Catch:{ Throwable -> 0x00e0 }
        r0.L$9 = r1;	 Catch:{ Throwable -> 0x00e0 }
        r10 = 3;
        r0.label = r10;	 Catch:{ Throwable -> 0x00e0 }
        r9 = r9.invoke(r2, r0);	 Catch:{ Throwable -> 0x00e0 }
        if (r9 != r7) goto L_0x0160;
    L_0x015f:
        return r7;
    L_0x0160:
        r10 = r2;
        r11 = r7;
        r2 = r15;
        r7 = r1;
        r15 = r9;
        r9 = r10;
    L_0x0166:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x00e0 }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x00e0 }
        r0.L$2 = r8;	 Catch:{ Throwable -> 0x00e0 }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x00e0 }
        r0.L$4 = r3;	 Catch:{ Throwable -> 0x00e0 }
        r0.L$5 = r2;	 Catch:{ Throwable -> 0x00e0 }
        r0.L$6 = r5;	 Catch:{ Throwable -> 0x00e0 }
        r0.L$7 = r9;	 Catch:{ Throwable -> 0x00e0 }
        r0.L$8 = r10;	 Catch:{ Throwable -> 0x00e0 }
        r9 = 4;
        r0.label = r9;	 Catch:{ Throwable -> 0x00e0 }
        r15 = r1.send(r15, r0);	 Catch:{ Throwable -> 0x00e0 }
        if (r15 != r11) goto L_0x0182;
    L_0x0181:
        return r11;
    L_0x0182:
        r15 = r0;
        r0 = r4;
        r1 = r5;
        r4 = r6;
        r5 = r8;
        r6 = r11;
        goto L_0x00fa;
    L_0x018a:
        r15 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x00e0 }
        r4.cancel(r3);
        r15 = kotlin.Unit.INSTANCE;
        return r15;
    L_0x0192:
        r15 = move-exception;
        r4 = r0;
        goto L_0x0199;
    L_0x0195:
        r15 = move-exception;
        r3 = r15;
        r4 = r0;
    L_0x0198:
        throw r3;	 Catch:{ all -> 0x00dd }
    L_0x0199:
        r4.cancel(r3);
        throw r15;
    L_0x019d:
        r15 = (kotlin.Result.Failure) r15;
        r15 = r15.exception;
        throw r15;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$map$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
