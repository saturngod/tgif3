package kotlinx.coroutines;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Result.Failure;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\u001a=\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u001e\u0010\u0003\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004\"\b\u0012\u0004\u0012\u0002H\u00020\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a%\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\u0004\"\u00020\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\fH@ø\u0001\u0000¢\u0006\u0002\u0010\r\u001a\u001b\u0010\u0007\u001a\u00020\b*\b\u0012\u0004\u0012\u00020\n0\fH@ø\u0001\u0000¢\u0006\u0002\u0010\r\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"awaitAll", "", "T", "deferreds", "", "Lkotlinx/coroutines/Deferred;", "([Lkotlinx/coroutines/Deferred;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "joinAll", "", "jobs", "Lkotlinx/coroutines/Job;", "([Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "(Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: Await.kt */
public final class AwaitKt {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    public static final <T> java.lang.Object awaitAll(@org.jetbrains.annotations.NotNull kotlinx.coroutines.Deferred<? extends T>[] r3, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<? extends T>> r4) {
        /*
        r0 = r4 instanceof kotlinx.coroutines.AwaitKt$awaitAll$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r4;
        r0 = (kotlinx.coroutines.AwaitKt$awaitAll$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r4 = r0.label;
        r4 = r4 - r2;
        r0.label = r4;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.AwaitKt$awaitAll$1;
        r0.<init>(r4);
    L_0x0019:
        r4 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        switch(r2) {
            case 0: goto L_0x003a;
            case 1: goto L_0x002c;
            default: goto L_0x0024;
        };
    L_0x0024:
        r3 = new java.lang.IllegalStateException;
        r4 = "call to 'resume' before 'invoke' with coroutine";
        r3.<init>(r4);
        throw r3;
    L_0x002c:
        r3 = r0.L$0;
        r3 = (kotlinx.coroutines.Deferred[]) r3;
        r3 = r4 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x0035;
    L_0x0034:
        goto L_0x005c;
    L_0x0035:
        r4 = (kotlin.Result.Failure) r4;
        r3 = r4.exception;
        throw r3;
    L_0x003a:
        r2 = r4 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x0060;
    L_0x003e:
        r4 = r3.length;
        r2 = 1;
        if (r4 != 0) goto L_0x0044;
    L_0x0042:
        r4 = 1;
        goto L_0x0045;
    L_0x0044:
        r4 = 0;
    L_0x0045:
        if (r4 == 0) goto L_0x004c;
    L_0x0047:
        r3 = kotlin.collections.CollectionsKt__CollectionsKt.emptyList();
        goto L_0x005f;
    L_0x004c:
        r4 = new kotlinx.coroutines.AwaitAll;
        r4.<init>(r3);
        r0.L$0 = r3;
        r0.label = r2;
        r4 = r4.await(r0);
        if (r4 != r1) goto L_0x005c;
    L_0x005b:
        return r1;
    L_0x005c:
        r3 = r4;
        r3 = (java.util.List) r3;
    L_0x005f:
        return r3;
    L_0x0060:
        r4 = (kotlin.Result.Failure) r4;
        r3 = r4.exception;
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.AwaitKt.awaitAll(kotlinx.coroutines.Deferred[], kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    public static final <T> java.lang.Object awaitAll(@org.jetbrains.annotations.NotNull java.util.Collection<? extends kotlinx.coroutines.Deferred<? extends T>> r3, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<? extends T>> r4) {
        /*
        r0 = r4 instanceof kotlinx.coroutines.AwaitKt$awaitAll$2;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r4;
        r0 = (kotlinx.coroutines.AwaitKt$awaitAll$2) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r4 = r0.label;
        r4 = r4 - r2;
        r0.label = r4;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.AwaitKt$awaitAll$2;
        r0.<init>(r4);
    L_0x0019:
        r4 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        switch(r2) {
            case 0: goto L_0x003a;
            case 1: goto L_0x002c;
            default: goto L_0x0024;
        };
    L_0x0024:
        r3 = new java.lang.IllegalStateException;
        r4 = "call to 'resume' before 'invoke' with coroutine";
        r3.<init>(r4);
        throw r3;
    L_0x002c:
        r3 = r0.L$0;
        r3 = (java.util.Collection) r3;
        r3 = r4 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x0035;
    L_0x0034:
        goto L_0x0067;
    L_0x0035:
        r4 = (kotlin.Result.Failure) r4;
        r3 = r4.exception;
        throw r3;
    L_0x003a:
        r2 = r4 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x007b;
    L_0x003e:
        r4 = r3.isEmpty();
        if (r4 == 0) goto L_0x0049;
    L_0x0044:
        r3 = kotlin.collections.CollectionsKt__CollectionsKt.emptyList();
        goto L_0x006a;
    L_0x0049:
        if (r3 == 0) goto L_0x0073;
    L_0x004b:
        r4 = 0;
        r4 = new kotlinx.coroutines.Deferred[r4];
        r4 = r3.toArray(r4);
        if (r4 == 0) goto L_0x006b;
    L_0x0054:
        r4 = (kotlinx.coroutines.Deferred[]) r4;
        r2 = new kotlinx.coroutines.AwaitAll;
        r2.<init>(r4);
        r0.L$0 = r3;
        r3 = 1;
        r0.label = r3;
        r4 = r2.await(r0);
        if (r4 != r1) goto L_0x0067;
    L_0x0066:
        return r1;
    L_0x0067:
        r3 = r4;
        r3 = (java.util.List) r3;
    L_0x006a:
        return r3;
    L_0x006b:
        r3 = new kotlin.TypeCastException;
        r4 = "null cannot be cast to non-null type kotlin.Array<T>";
        r3.<init>(r4);
        throw r3;
    L_0x0073:
        r3 = new kotlin.TypeCastException;
        r4 = "null cannot be cast to non-null type java.util.Collection<T>";
        r3.<init>(r4);
        throw r3;
    L_0x007b:
        r4 = (kotlin.Result.Failure) r4;
        r3 = r4.exception;
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.AwaitKt.awaitAll(java.util.Collection, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @org.jetbrains.annotations.Nullable
    public static final java.lang.Object joinAll(@org.jetbrains.annotations.NotNull kotlinx.coroutines.Job[] r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
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
        r0 = r9 instanceof kotlinx.coroutines.AwaitKt$joinAll$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r9;
        r0 = (kotlinx.coroutines.AwaitKt$joinAll$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r9 = r0.label;
        r9 = r9 - r2;
        r0.label = r9;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.AwaitKt$joinAll$1;
        r0.<init>(r9);
    L_0x0019:
        r9 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 1;
        switch(r2) {
            case 0: goto L_0x0053;
            case 1: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r8 = new java.lang.IllegalStateException;
        r9 = "call to 'resume' before 'invoke' with coroutine";
        r8.<init>(r9);
        throw r8;
    L_0x002d:
        r8 = r0.L$4;
        r8 = (kotlinx.coroutines.Job) r8;
        r8 = r0.L$3;
        r8 = (kotlinx.coroutines.Job) r8;
        r8 = r0.I$1;
        r2 = r0.I$0;
        r4 = r0.L$2;
        r4 = (kotlinx.coroutines.Job[]) r4;
        r5 = r0.L$1;
        r5 = (kotlinx.coroutines.Job[]) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.Job[]) r6;
        r7 = r9 instanceof kotlin.Result.Failure;
        if (r7 != 0) goto L_0x004e;
    L_0x0049:
        r9 = r4;
        r4 = r5;
        r5 = r1;
        r1 = r6;
        goto L_0x007a;
    L_0x004e:
        r9 = (kotlin.Result.Failure) r9;
        r8 = r9.exception;
        throw r8;
    L_0x0053:
        r2 = r9 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x007f;
    L_0x0057:
        r9 = r8.length;
        r2 = 0;
        r4 = r8;
        r2 = r9;
        r5 = r1;
        r9 = r4;
        r1 = r9;
        r8 = 0;
    L_0x005f:
        if (r8 >= r2) goto L_0x007c;
    L_0x0061:
        r6 = r9[r8];
        r0.L$0 = r1;
        r0.L$1 = r4;
        r0.L$2 = r9;
        r0.I$0 = r2;
        r0.I$1 = r8;
        r0.L$3 = r6;
        r0.L$4 = r6;
        r0.label = r3;
        r6 = r6.join(r0);
        if (r6 != r5) goto L_0x007a;
    L_0x0079:
        return r5;
    L_0x007a:
        r8 = r8 + r3;
        goto L_0x005f;
    L_0x007c:
        r8 = kotlin.Unit.INSTANCE;
        return r8;
    L_0x007f:
        r9 = (kotlin.Result.Failure) r9;
        r8 = r9.exception;
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.AwaitKt.joinAll(kotlinx.coroutines.Job[], kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public static final Object joinAll(@NotNull Collection<? extends Job> collection, @NotNull Continuation<? super Unit> continuation) {
        Continuation continuation2;
        Object coroutine_suspended;
        Object obj;
        Collection collection2;
        if (continuation instanceof AwaitKt$joinAll$3) {
            continuation2 = (AwaitKt$joinAll$3) continuation;
            if ((continuation2.label & Integer.MIN_VALUE) != 0) {
                continuation2.label -= -2147483648;
                continuation = continuation2.result;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (continuation2.label) {
                    case 0:
                        if (!(continuation instanceof Failure)) {
                            Iterable iterable = collection;
                            Iterator it = iterable.iterator();
                            Iterable iterable2 = iterable;
                            continuation = collection;
                            collection = it;
                            obj = iterable2;
                            break;
                        }
                        throw ((Failure) continuation).exception;
                    case 1:
                        Job job = (Job) continuation2.L$4;
                        collection = continuation2.L$3;
                        collection = (Iterator) continuation2.L$2;
                        obj = (Iterable) continuation2.L$1;
                        collection2 = (Collection) continuation2.L$0;
                        if (!(continuation instanceof Failure)) {
                            continuation = collection2;
                            break;
                        }
                        throw ((Failure) continuation).exception;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                while (collection.hasNext()) {
                    Job job2;
                    Object next = collection.next();
                    job2 = (Job) next;
                    continuation2.L$0 = continuation;
                    continuation2.L$1 = obj;
                    continuation2.L$2 = collection;
                    continuation2.L$3 = next;
                    continuation2.L$4 = job2;
                    continuation2.label = 1;
                    if (job2.join(continuation2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            }
        }
        continuation2 = new AwaitKt$joinAll$3(continuation);
        continuation = continuation2.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (continuation2.label) {
            case 0:
                if (!(continuation instanceof Failure)) {
                    Iterable iterable3 = collection;
                    Iterator it2 = iterable3.iterator();
                    Iterable iterable22 = iterable3;
                    continuation = collection;
                    collection = it2;
                    obj = iterable22;
                    break;
                }
                throw ((Failure) continuation).exception;
            case 1:
                Job job3 = (Job) continuation2.L$4;
                collection = continuation2.L$3;
                collection = (Iterator) continuation2.L$2;
                obj = (Iterable) continuation2.L$1;
                collection2 = (Collection) continuation2.L$0;
                if (!(continuation instanceof Failure)) {
                    continuation = collection2;
                    break;
                }
                throw ((Failure) continuation).exception;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        while (collection.hasNext()) {
            Object next2 = collection.next();
            job2 = (Job) next2;
            continuation2.L$0 = continuation;
            continuation2.L$1 = obj;
            continuation2.L$2 = collection;
            continuation2.L$3 = next2;
            continuation2.L$4 = job2;
            continuation2.label = 1;
            if (job2.join(continuation2) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
