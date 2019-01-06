package org.jetbrains.anko.appcompat.v7.coroutines;

import kotlin.Metadata;
import kotlin.Result.Failure;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "org/jetbrains/anko/appcompat/v7/coroutines/__SearchView_OnSuggestionListener$onSuggestionClick$1", f = "ListenersWithCoroutines.kt", i = {}, l = {166, 168}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ListenersWithCoroutines.kt */
final class __SearchView_OnSuggestionListener$onSuggestionClick$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function3 $handler;
    final /* synthetic */ int $position;
    int label;
    private CoroutineScope p$;

    __SearchView_OnSuggestionListener$onSuggestionClick$1(Function3 function3, int i, Continuation continuation) {
        this.$handler = function3;
        this.$position = i;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        Continuation __searchview_onsuggestionlistener_onsuggestionclick_1 = new __SearchView_OnSuggestionListener$onSuggestionClick$1(this.$handler, this.$position, continuation);
        __searchview_onsuggestionlistener_onsuggestionclick_1.p$ = (CoroutineScope) obj;
        return __searchview_onsuggestionlistener_onsuggestionclick_1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((__SearchView_OnSuggestionListener$onSuggestionClick$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                if (obj instanceof Failure) {
                    throw ((Failure) obj).exception;
                }
                obj = this.p$;
                Function3 function3 = this.$handler;
                Integer boxInt = Boxing.boxInt(this.$position);
                this.label = 1;
                if (function3.invoke(obj, boxInt, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                if (!(obj instanceof Failure)) {
                    break;
                }
                throw ((Failure) obj).exception;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
