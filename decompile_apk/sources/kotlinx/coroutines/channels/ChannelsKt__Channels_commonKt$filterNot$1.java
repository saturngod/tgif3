package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Result.Failure;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "E", "it", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$filterNot$1", f = "Channels.common.kt", i = {}, l = {711, 711}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$filterNot$1 extends SuspendLambda implements Function2<E, Continuation<? super Boolean>, Object> {
    final /* synthetic */ Function2 $predicate;
    int label;
    private Object p$0;

    ChannelsKt__Channels_commonKt$filterNot$1(Function2 function2, Continuation continuation) {
        this.$predicate = function2;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        Continuation channelsKt__Channels_commonKt$filterNot$1 = new ChannelsKt__Channels_commonKt$filterNot$1(this.$predicate, continuation);
        channelsKt__Channels_commonKt$filterNot$1.p$0 = obj;
        return channelsKt__Channels_commonKt$filterNot$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$filterNot$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                if (obj instanceof Failure) {
                    throw ((Failure) obj).exception;
                }
                obj = this.p$0;
                Function2 function2 = this.$predicate;
                this.label = 1;
                obj = function2.invoke(obj, this);
                if (obj == coroutine_suspended) {
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
        return Boxing.boxBoolean(((Boolean) obj).booleanValue() ^ 1);
    }
}
