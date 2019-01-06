package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Result.Failure;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u0001H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "E", "it", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$distinct$1", f = "Channels.common.kt", i = {}, l = {1438}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$distinct$1 extends SuspendLambda implements Function2<E, Continuation<? super E>, Object> {
    int label;
    private Object p$0;

    ChannelsKt__Channels_commonKt$distinct$1(Continuation continuation) {
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        Continuation channelsKt__Channels_commonKt$distinct$1 = new ChannelsKt__Channels_commonKt$distinct$1(continuation);
        channelsKt__Channels_commonKt$distinct$1.p$0 = obj;
        return channelsKt__Channels_commonKt$distinct$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$distinct$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else if (!(obj instanceof Failure)) {
            return this.p$0;
        } else {
            throw ((Failure) obj).exception;
        }
    }
}
