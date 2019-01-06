package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Result.Failure;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "T", "R", "Lkotlin/sequences/SequenceScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlin/sequences/SequencesKt___SequencesKt$zipWithNext$2", f = "_Sequences.kt", i = {0, 0, 0}, l = {1676, 1683}, m = "invokeSuspend", n = {"iterator", "current", "next"}, s = {"L$1", "L$2", "L$3"})
/* compiled from: _Sequences.kt */
final class SequencesKt___SequencesKt$zipWithNext$2 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Sequence $this_zipWithNext;
    final /* synthetic */ Function2 $transform;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    private SequenceScope p$;

    SequencesKt___SequencesKt$zipWithNext$2(Sequence sequence, Function2 function2, Continuation continuation) {
        this.$this_zipWithNext = sequence;
        this.$transform = function2;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        Continuation sequencesKt___SequencesKt$zipWithNext$2 = new SequencesKt___SequencesKt$zipWithNext$2(this.$this_zipWithNext, this.$transform, continuation);
        sequencesKt___SequencesKt$zipWithNext$2.p$ = (SequenceScope) obj;
        return sequencesKt___SequencesKt$zipWithNext$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SequencesKt___SequencesKt$zipWithNext$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        SequenceScope sequenceScope;
        Object obj2;
        Iterator it;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        Object next;
        switch (this.label) {
            case 0:
                if (!(obj instanceof Failure)) {
                    SequenceScope sequenceScope2 = this.p$;
                    Iterator it2 = this.$this_zipWithNext.iterator();
                    if (it2.hasNext()) {
                        next = it2.next();
                        sequenceScope = sequenceScope2;
                        obj = this;
                        Iterator it3 = it2;
                        obj2 = coroutine_suspended;
                        coroutine_suspended = next;
                        it = it3;
                        break;
                    }
                    return Unit.INSTANCE;
                }
                throw ((Failure) obj).exception;
            case 1:
                obj2 = this.L$3;
                next = this.L$2;
                it = (Iterator) this.L$1;
                sequenceScope = (SequenceScope) this.L$0;
                if (!(obj instanceof Failure)) {
                    obj = this;
                    Object obj3 = obj2;
                    obj2 = coroutine_suspended;
                    coroutine_suspended = obj3;
                    break;
                }
                throw ((Failure) obj).exception;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        while (it.hasNext()) {
            Object next2 = it.next();
            Object invoke = obj.$transform.invoke(coroutine_suspended, next2);
            obj.L$0 = sequenceScope;
            obj.L$1 = it;
            obj.L$2 = coroutine_suspended;
            obj.L$3 = next2;
            obj.label = 1;
            if (sequenceScope.yield(invoke, obj) == obj2) {
                return obj2;
            }
            coroutine_suspended = next2;
        }
        return Unit.INSTANCE;
    }
}
