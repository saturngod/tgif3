package kotlin.collections;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequenceScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "T", "Lkotlin/sequences/SequenceScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlin/collections/SlidingWindowKt$windowedIterator$1", f = "SlidingWindow.kt", i = {0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4}, l = {26, 36, 44, 49, 55, 59}, m = "invokeSuspend", n = {"gap", "buffer", "skip", "e", "gap", "buffer", "skip", "gap", "buffer", "e", "gap", "buffer", "gap", "buffer"}, s = {"I$0", "L$1", "I$1", "L$2", "I$0", "L$0", "I$1", "I$0", "L$1", "L$2", "I$0", "L$1", "I$0", "L$0"})
/* compiled from: SlidingWindow.kt */
final class SlidingWindowKt$windowedIterator$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super List<? extends T>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Iterator $iterator;
    final /* synthetic */ boolean $partialWindows;
    final /* synthetic */ boolean $reuseBuffer;
    final /* synthetic */ int $size;
    final /* synthetic */ int $step;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    private SequenceScope p$;

    SlidingWindowKt$windowedIterator$1(int i, int i2, Iterator it, boolean z, boolean z2, Continuation continuation) {
        this.$step = i;
        this.$size = i2;
        this.$iterator = it;
        this.$reuseBuffer = z;
        this.$partialWindows = z2;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        Continuation slidingWindowKt$windowedIterator$1 = new SlidingWindowKt$windowedIterator$1(this.$step, this.$size, this.$iterator, this.$reuseBuffer, this.$partialWindows, continuation);
        slidingWindowKt$windowedIterator$1.p$ = (SequenceScope) obj;
        return slidingWindowKt$windowedIterator$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SlidingWindowKt$windowedIterator$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @org.jetbrains.annotations.Nullable
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r12) {
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
        r11 = this;
        r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r1 = r11.label;
        r2 = 1;
        switch(r1) {
            case 0: goto L_0x0087;
            case 1: goto L_0x0068;
            case 2: goto L_0x0055;
            case 3: goto L_0x0039;
            case 4: goto L_0x0023;
            case 5: goto L_0x0012;
            default: goto L_0x000a;
        };
    L_0x000a:
        r12 = new java.lang.IllegalStateException;
        r0 = "call to 'resume' before 'invoke' with coroutine";
        r12.<init>(r0);
        throw r12;
    L_0x0012:
        r0 = r11.L$0;
        r0 = (kotlin.collections.RingBuffer) r0;
        r0 = r11.I$0;
        r0 = r12 instanceof kotlin.Result.Failure;
        if (r0 != 0) goto L_0x001e;
    L_0x001c:
        goto L_0x01a5;
    L_0x001e:
        r12 = (kotlin.Result.Failure) r12;
        r12 = r12.exception;
        throw r12;
    L_0x0023:
        r1 = r11.L$1;
        r1 = (kotlin.collections.RingBuffer) r1;
        r3 = r11.I$0;
        r4 = r11.L$0;
        r4 = (kotlin.sequences.SequenceScope) r4;
        r5 = r12 instanceof kotlin.Result.Failure;
        if (r5 != 0) goto L_0x0034;
    L_0x0031:
        r12 = r11;
        goto L_0x0187;
    L_0x0034:
        r12 = (kotlin.Result.Failure) r12;
        r12 = r12.exception;
        throw r12;
    L_0x0039:
        r1 = r11.L$3;
        r1 = (java.util.Iterator) r1;
        r3 = r11.L$2;
        r3 = r11.L$1;
        r3 = (kotlin.collections.RingBuffer) r3;
        r4 = r11.I$0;
        r5 = r11.L$0;
        r5 = (kotlin.sequences.SequenceScope) r5;
        r6 = r12 instanceof kotlin.Result.Failure;
        if (r6 != 0) goto L_0x0050;
    L_0x004d:
        r12 = r11;
        goto L_0x0150;
    L_0x0050:
        r12 = (kotlin.Result.Failure) r12;
        r12 = r12.exception;
        throw r12;
    L_0x0055:
        r0 = r11.I$1;
        r0 = r11.L$0;
        r0 = (java.util.ArrayList) r0;
        r0 = r11.I$0;
        r0 = r12 instanceof kotlin.Result.Failure;
        if (r0 != 0) goto L_0x0063;
    L_0x0061:
        goto L_0x01a5;
    L_0x0063:
        r12 = (kotlin.Result.Failure) r12;
        r12 = r12.exception;
        throw r12;
    L_0x0068:
        r1 = r11.L$3;
        r1 = (java.util.Iterator) r1;
        r3 = r11.L$2;
        r3 = r11.I$1;
        r3 = r11.L$1;
        r3 = (java.util.ArrayList) r3;
        r4 = r11.I$0;
        r5 = r11.L$0;
        r5 = (kotlin.sequences.SequenceScope) r5;
        r6 = r12 instanceof kotlin.Result.Failure;
        if (r6 != 0) goto L_0x0082;
    L_0x007e:
        r12 = r11;
        r6 = r0;
        r0 = r4;
        goto L_0x00d2;
    L_0x0082:
        r12 = (kotlin.Result.Failure) r12;
        r12 = r12.exception;
        throw r12;
    L_0x0087:
        r1 = r12 instanceof kotlin.Result.Failure;
        if (r1 != 0) goto L_0x01a8;
    L_0x008b:
        r12 = r11.p$;
        r1 = r11.$step;
        r3 = r11.$size;
        r1 = r1 - r3;
        if (r1 < 0) goto L_0x0109;
    L_0x0094:
        r3 = new java.util.ArrayList;
        r4 = r11.$size;
        r3.<init>(r4);
        r4 = 0;
        r5 = r11.$iterator;
        r6 = r0;
        r0 = r1;
        r1 = r5;
        r5 = r12;
        r12 = r11;
    L_0x00a3:
        r7 = r1.hasNext();
        if (r7 == 0) goto L_0x00e3;
    L_0x00a9:
        r7 = r1.next();
        if (r4 <= 0) goto L_0x00b2;
    L_0x00af:
        r4 = r4 + -1;
        goto L_0x00a3;
    L_0x00b2:
        r3.add(r7);
        r8 = r3.size();
        r9 = r12.$size;
        if (r8 != r9) goto L_0x00a3;
    L_0x00bd:
        r12.L$0 = r5;
        r12.I$0 = r0;
        r12.L$1 = r3;
        r12.I$1 = r4;
        r12.L$2 = r7;
        r12.L$3 = r1;
        r12.label = r2;
        r4 = r5.yield(r3, r12);
        if (r4 != r6) goto L_0x00d2;
    L_0x00d1:
        return r6;
    L_0x00d2:
        r4 = r12.$reuseBuffer;
        if (r4 == 0) goto L_0x00da;
    L_0x00d6:
        r3.clear();
        goto L_0x00e1;
    L_0x00da:
        r3 = new java.util.ArrayList;
        r4 = r12.$size;
        r3.<init>(r4);
    L_0x00e1:
        r4 = r0;
        goto L_0x00a3;
    L_0x00e3:
        r1 = r3;
        r1 = (java.util.Collection) r1;
        r1 = r1.isEmpty();
        r1 = r1 ^ r2;
        if (r1 == 0) goto L_0x01a5;
    L_0x00ed:
        r1 = r12.$partialWindows;
        if (r1 != 0) goto L_0x00f9;
    L_0x00f1:
        r1 = r3.size();
        r2 = r12.$size;
        if (r1 != r2) goto L_0x01a5;
    L_0x00f9:
        r12.I$0 = r0;
        r12.L$0 = r3;
        r12.I$1 = r4;
        r0 = 2;
        r12.label = r0;
        r12 = r5.yield(r3, r12);
        if (r12 != r6) goto L_0x01a5;
    L_0x0108:
        return r6;
    L_0x0109:
        r3 = new kotlin.collections.RingBuffer;
        r4 = r11.$size;
        r3.<init>(r4);
        r4 = r11.$iterator;
        r5 = r12;
        r12 = r11;
        r10 = r4;
        r4 = r1;
        r1 = r10;
    L_0x0117:
        r6 = r1.hasNext();
        if (r6 == 0) goto L_0x0156;
    L_0x011d:
        r6 = r1.next();
        r3.add(r6);
        r7 = r3.isFull();
        if (r7 == 0) goto L_0x0117;
    L_0x012a:
        r7 = r12.$reuseBuffer;
        if (r7 == 0) goto L_0x0132;
    L_0x012e:
        r7 = r3;
        r7 = (java.util.List) r7;
        goto L_0x013c;
    L_0x0132:
        r7 = new java.util.ArrayList;
        r8 = r3;
        r8 = (java.util.Collection) r8;
        r7.<init>(r8);
        r7 = (java.util.List) r7;
    L_0x013c:
        r12.L$0 = r5;
        r12.I$0 = r4;
        r12.L$1 = r3;
        r12.L$2 = r6;
        r12.L$3 = r1;
        r6 = 3;
        r12.label = r6;
        r6 = r5.yield(r7, r12);
        if (r6 != r0) goto L_0x0150;
    L_0x014f:
        return r0;
    L_0x0150:
        r6 = r12.$step;
        r3.removeFirst(r6);
        goto L_0x0117;
    L_0x0156:
        r1 = r12.$partialWindows;
        if (r1 == 0) goto L_0x01a5;
    L_0x015a:
        r1 = r3;
        r3 = r4;
        r4 = r5;
    L_0x015d:
        r5 = r1.size();
        r6 = r12.$step;
        if (r5 <= r6) goto L_0x018d;
    L_0x0165:
        r5 = r12.$reuseBuffer;
        if (r5 == 0) goto L_0x016d;
    L_0x0169:
        r5 = r1;
        r5 = (java.util.List) r5;
        goto L_0x0177;
    L_0x016d:
        r5 = new java.util.ArrayList;
        r6 = r1;
        r6 = (java.util.Collection) r6;
        r5.<init>(r6);
        r5 = (java.util.List) r5;
    L_0x0177:
        r12.L$0 = r4;
        r12.I$0 = r3;
        r12.L$1 = r1;
        r6 = 4;
        r12.label = r6;
        r5 = r4.yield(r5, r12);
        if (r5 != r0) goto L_0x0187;
    L_0x0186:
        return r0;
    L_0x0187:
        r5 = r12.$step;
        r1.removeFirst(r5);
        goto L_0x015d;
    L_0x018d:
        r5 = r1;
        r5 = (java.util.Collection) r5;
        r5 = r5.isEmpty();
        r2 = r2 ^ r5;
        if (r2 == 0) goto L_0x01a5;
    L_0x0197:
        r12.I$0 = r3;
        r12.L$0 = r1;
        r2 = 5;
        r12.label = r2;
        r12 = r4.yield(r1, r12);
        if (r12 != r0) goto L_0x01a5;
    L_0x01a4:
        return r0;
    L_0x01a5:
        r12 = kotlin.Unit.INSTANCE;
        return r12;
    L_0x01a8:
        r12 = (kotlin.Result.Failure) r12;
        r12 = r12.exception;
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.SlidingWindowKt$windowedIterator$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
