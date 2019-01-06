package org.jetbrains.anko.coroutines.experimental;

import kotlin.Metadata;
import kotlin.Result.Failure;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "org/jetbrains/anko/coroutines/experimental/BgKt$bg$1", f = "bg.kt", i = {}, l = {29}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: bg.kt */
public final class BgKt$bg$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
    final /* synthetic */ Function0 $block;
    int label;
    private CoroutineScope p$;

    public BgKt$bg$1(Function0 function0, Continuation continuation) {
        this.$block = function0;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        Continuation bgKt$bg$1 = new BgKt$bg$1(this.$block, continuation);
        bgKt$bg$1.p$ = (CoroutineScope) obj;
        return bgKt$bg$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((BgKt$bg$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else if (obj instanceof Failure) {
            throw ((Failure) obj).exception;
        } else {
            obj = this.p$;
            return this.$block.invoke();
        }
    }
}
