package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "E", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/collections/IndexedValue;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$withIndex$1", f = "Channels.common.kt", i = {0, 1, 2, 2}, l = {1418, 1418, 1421, 1420}, m = "invokeSuspend", n = {"index", "index", "index", "e"}, s = {"I$0", "I$0", "I$0", "L$1"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$withIndex$1 extends SuspendLambda implements Function2<ProducerScope<? super IndexedValue<? extends E>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel $this_withIndex;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private ProducerScope p$;

    ChannelsKt__Channels_commonKt$withIndex$1(ReceiveChannel receiveChannel, Continuation continuation) {
        this.$this_withIndex = receiveChannel;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        Continuation channelsKt__Channels_commonKt$withIndex$1 = new ChannelsKt__Channels_commonKt$withIndex$1(this.$this_withIndex, continuation);
        channelsKt__Channels_commonKt$withIndex$1.p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$withIndex$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$withIndex$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @org.jetbrains.annotations.Nullable
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r9) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r8 = this;
        r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r1 = r8.label;
        switch(r1) {
            case 0: goto L_0x0057;
            case 1: goto L_0x0041;
            case 2: goto L_0x002b;
            case 3: goto L_0x0011;
            default: goto L_0x0009;
        };
    L_0x0009:
        r9 = new java.lang.IllegalStateException;
        r0 = "call to 'resume' before 'invoke' with coroutine";
        r9.<init>(r0);
        throw r9;
    L_0x0011:
        r1 = r8.L$2;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r2 = r8.L$1;
        r2 = r8.I$0;
        r3 = r8.L$0;
        r3 = (kotlinx.coroutines.channels.ProducerScope) r3;
        r4 = r9 instanceof kotlin.Result.Failure;
        if (r4 != 0) goto L_0x0026;
    L_0x0021:
        r9 = r8;
        r7 = r2;
        r2 = r0;
        r0 = r7;
        goto L_0x0069;
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
        if (r4 != 0) goto L_0x003c;
    L_0x0039:
        r4 = r0;
        r0 = r8;
        goto L_0x0096;
    L_0x003c:
        r9 = (kotlin.Result.Failure) r9;
        r9 = r9.exception;
        throw r9;
    L_0x0041:
        r1 = r8.L$1;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r2 = r8.I$0;
        r3 = r8.L$0;
        r3 = (kotlinx.coroutines.channels.ProducerScope) r3;
        r4 = r9 instanceof kotlin.Result.Failure;
        if (r4 != 0) goto L_0x0052;
    L_0x004f:
        r4 = r0;
        r0 = r8;
        goto L_0x007e;
    L_0x0052:
        r9 = (kotlin.Result.Failure) r9;
        r9 = r9.exception;
        throw r9;
    L_0x0057:
        r1 = r9 instanceof kotlin.Result.Failure;
        if (r1 != 0) goto L_0x00b6;
    L_0x005b:
        r9 = r8.p$;
        r1 = 0;
        r2 = r8.$this_withIndex;
        r2 = r2.iterator();
        r3 = r9;
        r1 = r2;
        r9 = r8;
        r2 = r0;
        r0 = 0;
    L_0x0069:
        r9.L$0 = r3;
        r9.I$0 = r0;
        r9.L$1 = r1;
        r4 = 1;
        r9.label = r4;
        r4 = r1.hasNext(r9);
        if (r4 != r2) goto L_0x0079;
    L_0x0078:
        return r2;
    L_0x0079:
        r7 = r0;
        r0 = r9;
        r9 = r4;
        r4 = r2;
        r2 = r7;
    L_0x007e:
        r9 = (java.lang.Boolean) r9;
        r9 = r9.booleanValue();
        if (r9 == 0) goto L_0x00b3;
    L_0x0086:
        r0.L$0 = r3;
        r0.I$0 = r2;
        r0.L$1 = r1;
        r9 = 2;
        r0.label = r9;
        r9 = r1.next(r0);
        if (r9 != r4) goto L_0x0096;
    L_0x0095:
        return r4;
    L_0x0096:
        r5 = new kotlin.collections.IndexedValue;
        r6 = r2 + 1;
        r5.<init>(r2, r9);
        r0.L$0 = r3;
        r0.I$0 = r6;
        r0.L$1 = r9;
        r0.L$2 = r1;
        r9 = 3;
        r0.label = r9;
        r9 = r3.send(r5, r0);
        if (r9 != r4) goto L_0x00af;
    L_0x00ae:
        return r4;
    L_0x00af:
        r9 = r0;
        r2 = r4;
        r0 = r6;
        goto L_0x0069;
    L_0x00b3:
        r9 = kotlin.Unit.INSTANCE;
        return r9;
    L_0x00b6:
        r9 = (kotlin.Result.Failure) r9;
        r9 = r9.exception;
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$withIndex$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
