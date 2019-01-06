package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.ObsoleteCoroutinesApi;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a/\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a/\u0010\b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a4\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"fixedDelayTicker", "", "delayMillis", "", "initialDelayMillis", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "(JJLkotlinx/coroutines/channels/SendChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fixedPeriodTicker", "ticker", "Lkotlinx/coroutines/channels/ReceiveChannel;", "context", "Lkotlin/coroutines/CoroutineContext;", "mode", "Lkotlinx/coroutines/channels/TickerMode;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: TickerChannels.kt */
public final class TickerChannelsKt {
    @NotNull
    @ObsoleteCoroutinesApi
    public static /* synthetic */ ReceiveChannel ticker$default(long j, long j2, CoroutineContext coroutineContext, TickerMode tickerMode, int i, Object obj) {
        if ((i & 2) != null) {
            j2 = j;
        }
        if ((i & 4) != null) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i & 8) != 0) {
            tickerMode = TickerMode.FIXED_PERIOD;
        }
        return ticker(j, j2, coroutineContext, tickerMode);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final ReceiveChannel<Unit> ticker(long j, long j2, @NotNull CoroutineContext coroutineContext, @NotNull TickerMode tickerMode) {
        long j3 = j;
        long j4 = j2;
        CoroutineContext coroutineContext2 = coroutineContext;
        Intrinsics.checkParameterIsNotNull(coroutineContext2, "context");
        Intrinsics.checkParameterIsNotNull(tickerMode, "mode");
        Object obj = 1;
        if ((j3 >= 0 ? 1 : null) != null) {
            if (j4 < 0) {
                obj = null;
            }
            if (obj != null) {
                return ProduceKt.produce(GlobalScope.INSTANCE, Dispatchers.getUnconfined().plus(coroutineContext2), 0, new TickerChannelsKt$ticker$3(tickerMode, j, j2, null));
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Expected non-negative initial delay, but has ");
            stringBuilder.append(j2);
            stringBuilder.append(" ms");
            throw new IllegalArgumentException(stringBuilder.toString().toString());
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("Expected non-negative delay, but has ");
        stringBuilder.append(j);
        stringBuilder.append(" ms");
        throw new IllegalArgumentException(stringBuilder.toString().toString());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    static final /* synthetic */ java.lang.Object fixedPeriodTicker(long r27, long r29, @org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.SendChannel<? super kotlin.Unit> r31, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r32) {
        /*
        r0 = r29;
        r2 = r32;
        r3 = r2 instanceof kotlinx.coroutines.channels.TickerChannelsKt$fixedPeriodTicker$1;
        if (r3 == 0) goto L_0x0018;
    L_0x0008:
        r3 = r2;
        r3 = (kotlinx.coroutines.channels.TickerChannelsKt$fixedPeriodTicker$1) r3;
        r4 = r3.label;
        r5 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r4 = r4 & r5;
        if (r4 == 0) goto L_0x0018;
    L_0x0012:
        r2 = r3.label;
        r2 = r2 - r5;
        r3.label = r2;
        goto L_0x001d;
    L_0x0018:
        r3 = new kotlinx.coroutines.channels.TickerChannelsKt$fixedPeriodTicker$1;
        r3.<init>(r2);
    L_0x001d:
        r2 = r3.result;
        r4 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r5 = r3.label;
        switch(r5) {
            case 0: goto L_0x0099;
            case 1: goto L_0x007f;
            case 2: goto L_0x0068;
            case 3: goto L_0x004a;
            case 4: goto L_0x0030;
            default: goto L_0x0028;
        };
    L_0x0028:
        r0 = new java.lang.IllegalStateException;
        r1 = "call to 'resume' before 'invoke' with coroutine";
        r0.<init>(r1);
        throw r0;
    L_0x0030:
        r0 = r3.J$5;
        r0 = r3.J$4;
        r0 = r3.J$3;
        r5 = r3.J$2;
        r7 = r3.L$0;
        r7 = (kotlinx.coroutines.channels.SendChannel) r7;
        r8 = r3.J$1;
        r10 = r3.J$0;
        r12 = r2 instanceof kotlin.Result.Failure;
        if (r12 != 0) goto L_0x0045;
    L_0x0044:
        goto L_0x0060;
    L_0x0045:
        r2 = (kotlin.Result.Failure) r2;
        r0 = r2.exception;
        throw r0;
    L_0x004a:
        r0 = r3.J$6;
        r0 = r3.J$5;
        r0 = r3.J$4;
        r0 = r3.J$3;
        r5 = r3.J$2;
        r7 = r3.L$0;
        r7 = (kotlinx.coroutines.channels.SendChannel) r7;
        r8 = r3.J$1;
        r10 = r3.J$0;
        r12 = r2 instanceof kotlin.Result.Failure;
        if (r12 != 0) goto L_0x0063;
    L_0x0060:
        r2 = r7;
        goto L_0x0132;
    L_0x0063:
        r2 = (kotlin.Result.Failure) r2;
        r0 = r2.exception;
        throw r0;
    L_0x0068:
        r0 = r3.J$3;
        r5 = r3.J$2;
        r7 = r3.L$0;
        r7 = (kotlinx.coroutines.channels.SendChannel) r7;
        r8 = r3.J$1;
        r10 = r3.J$0;
        r12 = r2 instanceof kotlin.Result.Failure;
        if (r12 != 0) goto L_0x007a;
    L_0x0078:
        goto L_0x00e3;
    L_0x007a:
        r2 = (kotlin.Result.Failure) r2;
        r0 = r2.exception;
        throw r0;
    L_0x007f:
        r0 = r3.J$2;
        r5 = r3.L$0;
        r5 = (kotlinx.coroutines.channels.SendChannel) r5;
        r6 = r3.J$1;
        r8 = r3.J$0;
        r10 = r2 instanceof kotlin.Result.Failure;
        if (r10 != 0) goto L_0x0094;
    L_0x008d:
        r2 = r5;
        r25 = r0;
        r0 = r6;
        r5 = r25;
        goto L_0x00c0;
    L_0x0094:
        r2 = (kotlin.Result.Failure) r2;
        r0 = r2.exception;
        throw r0;
    L_0x0099:
        r5 = r2 instanceof kotlin.Result.Failure;
        if (r5 != 0) goto L_0x0165;
    L_0x009d:
        r2 = kotlinx.coroutines.TimeSourceKt.getTimeSource();
        r5 = r2.nanoTime();
        r7 = kotlinx.coroutines.EventLoopKt.delayToNanos(r29);
        r5 = r5 + r7;
        r8 = r27;
        r3.J$0 = r8;
        r3.J$1 = r0;
        r2 = r31;
        r3.L$0 = r2;
        r3.J$2 = r5;
        r7 = 1;
        r3.label = r7;
        r7 = kotlinx.coroutines.DelayKt.delay(r0, r3);
        if (r7 != r4) goto L_0x00c0;
    L_0x00bf:
        return r4;
    L_0x00c0:
        r10 = kotlinx.coroutines.EventLoopKt.delayToNanos(r8);
    L_0x00c4:
        r7 = 0;
        r5 = r5 + r10;
        r7 = kotlin.Unit.INSTANCE;
        r3.J$0 = r8;
        r3.J$1 = r0;
        r3.L$0 = r2;
        r3.J$2 = r5;
        r3.J$3 = r10;
        r12 = 2;
        r3.label = r12;
        r7 = r2.send(r7, r3);
        if (r7 != r4) goto L_0x00dc;
    L_0x00db:
        return r4;
    L_0x00dc:
        r7 = r2;
        r25 = r0;
        r0 = r10;
        r10 = r8;
        r8 = r25;
    L_0x00e3:
        r2 = kotlinx.coroutines.TimeSourceKt.getTimeSource();
        r12 = r2.nanoTime();
        r14 = r5 - r12;
        r18 = r7;
        r16 = r8;
        r7 = 0;
        r14 = kotlin.ranges.RangesKt___RangesKt.coerceAtLeast(r14, r7);
        r2 = (r14 > r7 ? 1 : (r14 == r7 ? 0 : -1));
        if (r2 != 0) goto L_0x0139;
    L_0x00fb:
        r2 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1));
        if (r2 == 0) goto L_0x0139;
    L_0x00ff:
        r5 = r12 - r5;
        r5 = r5 % r0;
        r5 = r0 - r5;
        r7 = r12 + r5;
        r19 = r14;
        r14 = kotlinx.coroutines.EventLoopKt.delayNanosToMillis(r5);
        r3.J$0 = r10;
        r21 = r10;
        r9 = r16;
        r3.J$1 = r9;
        r2 = r18;
        r3.L$0 = r2;
        r3.J$2 = r7;
        r3.J$3 = r0;
        r3.J$4 = r12;
        r11 = r19;
        r3.J$5 = r11;
        r3.J$6 = r5;
        r5 = 3;
        r3.label = r5;
        r5 = kotlinx.coroutines.DelayKt.delay(r14, r3);
        if (r5 != r4) goto L_0x012e;
    L_0x012d:
        return r4;
    L_0x012e:
        r5 = r7;
        r8 = r9;
        r10 = r21;
    L_0x0132:
        r25 = r8;
        r8 = r10;
        r10 = r0;
        r0 = r25;
        goto L_0x00c4;
    L_0x0139:
        r21 = r10;
        r7 = r14;
        r9 = r16;
        r2 = r18;
        r14 = kotlinx.coroutines.EventLoopKt.delayNanosToMillis(r7);
        r23 = r14;
        r14 = r21;
        r3.J$0 = r14;
        r3.J$1 = r9;
        r3.L$0 = r2;
        r3.J$2 = r5;
        r3.J$3 = r0;
        r3.J$4 = r12;
        r3.J$5 = r7;
        r7 = 4;
        r3.label = r7;
        r7 = r23;
        r7 = kotlinx.coroutines.DelayKt.delay(r7, r3);
        if (r7 != r4) goto L_0x0162;
    L_0x0161:
        return r4;
    L_0x0162:
        r8 = r9;
        r10 = r14;
        goto L_0x0132;
    L_0x0165:
        r2 = (kotlin.Result.Failure) r2;
        r0 = r2.exception;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.TickerChannelsKt.fixedPeriodTicker(long, long, kotlinx.coroutines.channels.SendChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @org.jetbrains.annotations.Nullable
    static final /* synthetic */ java.lang.Object fixedDelayTicker(long r5, long r7, @org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.SendChannel<? super kotlin.Unit> r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = r10 instanceof kotlinx.coroutines.channels.TickerChannelsKt$fixedDelayTicker$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r10;
        r0 = (kotlinx.coroutines.channels.TickerChannelsKt$fixedDelayTicker$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r10 = r0.label;
        r10 = r10 - r2;
        r0.label = r10;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.TickerChannelsKt$fixedDelayTicker$1;
        r0.<init>(r10);
    L_0x0019:
        r10 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        switch(r2) {
            case 0: goto L_0x0067;
            case 1: goto L_0x0054;
            case 2: goto L_0x0042;
            case 3: goto L_0x002c;
            default: goto L_0x0024;
        };
    L_0x0024:
        r5 = new java.lang.IllegalStateException;
        r6 = "call to 'resume' before 'invoke' with coroutine";
        r5.<init>(r6);
        throw r5;
    L_0x002c:
        r5 = r0.L$0;
        r5 = (kotlinx.coroutines.channels.SendChannel) r5;
        r6 = r0.J$1;
        r8 = r0.J$0;
        r2 = r10 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x003d;
    L_0x0038:
        r3 = r8;
        r9 = r5;
        r7 = r6;
        r5 = r3;
        goto L_0x007b;
    L_0x003d:
        r10 = (kotlin.Result.Failure) r10;
        r5 = r10.exception;
        throw r5;
    L_0x0042:
        r5 = r0.L$0;
        r5 = (kotlinx.coroutines.channels.SendChannel) r5;
        r6 = r0.J$1;
        r8 = r0.J$0;
        r2 = r10 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x004f;
    L_0x004e:
        goto L_0x0091;
    L_0x004f:
        r10 = (kotlin.Result.Failure) r10;
        r5 = r10.exception;
        throw r5;
    L_0x0054:
        r5 = r0.L$0;
        r9 = r5;
        r9 = (kotlinx.coroutines.channels.SendChannel) r9;
        r7 = r0.J$1;
        r5 = r0.J$0;
        r2 = r10 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x0062;
    L_0x0061:
        goto L_0x007b;
    L_0x0062:
        r10 = (kotlin.Result.Failure) r10;
        r5 = r10.exception;
        throw r5;
    L_0x0067:
        r2 = r10 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x00a1;
    L_0x006b:
        r0.J$0 = r5;
        r0.J$1 = r7;
        r0.L$0 = r9;
        r10 = 1;
        r0.label = r10;
        r10 = kotlinx.coroutines.DelayKt.delay(r7, r0);
        if (r10 != r1) goto L_0x007b;
    L_0x007a:
        return r1;
    L_0x007b:
        r10 = kotlin.Unit.INSTANCE;
        r0.J$0 = r5;
        r0.J$1 = r7;
        r0.L$0 = r9;
        r2 = 2;
        r0.label = r2;
        r10 = r9.send(r10, r0);
        if (r10 != r1) goto L_0x008d;
    L_0x008c:
        return r1;
    L_0x008d:
        r3 = r5;
        r5 = r9;
        r6 = r7;
        r8 = r3;
    L_0x0091:
        r0.J$0 = r8;
        r0.J$1 = r6;
        r0.L$0 = r5;
        r10 = 3;
        r0.label = r10;
        r10 = kotlinx.coroutines.DelayKt.delay(r8, r0);
        if (r10 != r1) goto L_0x0038;
    L_0x00a0:
        return r1;
    L_0x00a1:
        r10 = (kotlin.Result.Failure) r10;
        r5 = r10.exception;
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.TickerChannelsKt.fixedDelayTicker(long, long, kotlinx.coroutines.channels.SendChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
