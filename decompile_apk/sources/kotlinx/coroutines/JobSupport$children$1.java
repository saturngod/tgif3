package kotlinx.coroutines;

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

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Lkotlinx/coroutines/ChildJob;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx/coroutines/JobSupport$children$1", f = "JobSupport.kt", i = {0, 1, 1, 1, 1, 1}, l = {838, 842, 1323}, m = "invokeSuspend", n = {"state", "state", "list", "this_$iv", "cur$iv", "it"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
/* compiled from: JobSupport.kt */
final class JobSupport$children$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super ChildJob>, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    private SequenceScope p$;
    final /* synthetic */ JobSupport this$0;

    JobSupport$children$1(JobSupport jobSupport, Continuation continuation) {
        this.this$0 = jobSupport;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        Continuation jobSupport$children$1 = new JobSupport$children$1(this.this$0, continuation);
        jobSupport$children$1.p$ = (SequenceScope) obj;
        return jobSupport$children$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((JobSupport$children$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r10) {
        /*
        r9 = this;
        r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r1 = r9.label;
        r2 = 1;
        switch(r1) {
            case 0: goto L_0x0041;
            case 1: goto L_0x0034;
            case 2: goto L_0x0012;
            default: goto L_0x000a;
        };
    L_0x000a:
        r10 = new java.lang.IllegalStateException;
        r0 = "call to 'resume' before 'invoke' with coroutine";
        r10.<init>(r0);
        throw r10;
    L_0x0012:
        r1 = r9.L$5;
        r1 = (kotlinx.coroutines.ChildHandleNode) r1;
        r1 = r9.L$4;
        r1 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r1;
        r3 = r9.L$3;
        r3 = (kotlinx.coroutines.NodeList) r3;
        r4 = r9.L$2;
        r4 = (kotlinx.coroutines.NodeList) r4;
        r5 = r9.L$1;
        r6 = r9.L$0;
        r6 = (kotlin.sequences.SequenceScope) r6;
        r7 = r10 instanceof kotlin.Result.Failure;
        if (r7 != 0) goto L_0x002f;
    L_0x002c:
        r10 = r9;
        goto L_0x00a4;
    L_0x002f:
        r10 = (kotlin.Result.Failure) r10;
        r10 = r10.exception;
        throw r10;
    L_0x0034:
        r0 = r9.L$0;
        r0 = r10 instanceof kotlin.Result.Failure;
        if (r0 != 0) goto L_0x003c;
    L_0x003a:
        goto L_0x00b1;
    L_0x003c:
        r10 = (kotlin.Result.Failure) r10;
        r10 = r10.exception;
        throw r10;
    L_0x0041:
        r1 = r10 instanceof kotlin.Result.Failure;
        if (r1 != 0) goto L_0x00b4;
    L_0x0045:
        r10 = r9.p$;
        r1 = r9.this$0;
        r1 = r1.getState$kotlinx_coroutines_core();
        r3 = r1 instanceof kotlinx.coroutines.ChildHandleNode;
        if (r3 == 0) goto L_0x0061;
    L_0x0051:
        r3 = r1;
        r3 = (kotlinx.coroutines.ChildHandleNode) r3;
        r3 = r3.childJob;
        r9.L$0 = r1;
        r9.label = r2;
        r10 = r10.yield(r3, r9);
        if (r10 != r0) goto L_0x00b1;
    L_0x0060:
        return r0;
    L_0x0061:
        r3 = r1 instanceof kotlinx.coroutines.Incomplete;
        if (r3 == 0) goto L_0x00b1;
    L_0x0065:
        r3 = r1;
        r3 = (kotlinx.coroutines.Incomplete) r3;
        r3 = r3.getList();
        if (r3 == 0) goto L_0x00b1;
    L_0x006e:
        r4 = r3.getNext();
        if (r4 == 0) goto L_0x00a9;
    L_0x0074:
        r4 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r4;
        r6 = r10;
        r5 = r1;
        r1 = r4;
        r10 = r9;
        r4 = r3;
    L_0x007b:
        r7 = r3;
        r7 = (kotlinx.coroutines.internal.LockFreeLinkedListHead) r7;
        r7 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r7);
        r7 = r7 ^ r2;
        if (r7 == 0) goto L_0x00b1;
    L_0x0085:
        r7 = r1 instanceof kotlinx.coroutines.ChildHandleNode;
        if (r7 == 0) goto L_0x00a4;
    L_0x0089:
        r7 = r1;
        r7 = (kotlinx.coroutines.ChildHandleNode) r7;
        r8 = r7.childJob;
        r10.L$0 = r6;
        r10.L$1 = r5;
        r10.L$2 = r4;
        r10.L$3 = r3;
        r10.L$4 = r1;
        r10.L$5 = r7;
        r7 = 2;
        r10.label = r7;
        r7 = r6.yield(r8, r10);
        if (r7 != r0) goto L_0x00a4;
    L_0x00a3:
        return r0;
    L_0x00a4:
        r1 = r1.getNextNode();
        goto L_0x007b;
    L_0x00a9:
        r10 = new kotlin.TypeCastException;
        r0 = "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */";
        r10.<init>(r0);
        throw r10;
    L_0x00b1:
        r10 = kotlin.Unit.INSTANCE;
        return r10;
    L_0x00b4:
        r10 = (kotlin.Result.Failure) r10;
        r10 = r10.exception;
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport$children$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
