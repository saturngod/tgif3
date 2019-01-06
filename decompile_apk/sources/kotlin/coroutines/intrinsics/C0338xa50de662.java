package kotlin.coroutines.intrinsics;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.RestrictedContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\"\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0007H\u0014ø\u0001\u0000¢\u0006\u0002\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\t¸\u0006\u0000"}, d2 = {"kotlin/coroutines/intrinsics/IntrinsicsKt__IntrinsicsJvmKt$createCoroutineFromSuspendFunction$1", "Lkotlin/coroutines/jvm/internal/RestrictedContinuationImpl;", "label", "", "invokeSuspend", "", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: IntrinsicsJvm.kt */
/* renamed from: kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$3 */
public final class C0338xa50de662 extends RestrictedContinuationImpl {
    final /* synthetic */ Continuation $completion;
    final /* synthetic */ Object $receiver$inlined;
    final /* synthetic */ Function2 $this_createCoroutineUnintercepted$inlined;
    private int label;

    public C0338xa50de662(Continuation continuation, Continuation continuation2, Function2 function2, Object obj) {
        this.$completion = continuation;
        this.$this_createCoroutineUnintercepted$inlined = function2;
        this.$receiver$inlined = obj;
        super(continuation2);
    }

    @Nullable
    protected Object invokeSuspend(@NotNull Object obj) {
        switch (this.label) {
            case 0:
                this.label = 1;
                ResultKt.throwOnFailure(obj);
                Continuation continuation = this;
                Function2 function2 = this.$this_createCoroutineUnintercepted$inlined;
                if (function2 != null) {
                    return ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2)).invoke(this.$receiver$inlined, continuation);
                }
                throw new TypeCastException("null cannot be cast to non-null type (R, kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
            case 1:
                this.label = 2;
                ResultKt.throwOnFailure(obj);
                return obj;
            default:
                throw ((Throwable) new IllegalStateException("This coroutine had already completed".toString()));
        }
    }
}
