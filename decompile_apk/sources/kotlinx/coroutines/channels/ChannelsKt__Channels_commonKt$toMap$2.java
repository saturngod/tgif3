package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0018\b\u0002\u0010\u0004*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\u0005*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\u00062\u0006\u0010\b\u001a\u0002H\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00040\nH@ø\u0001\u0000"}, d2 = {"toMap", "", "K", "V", "M", "", "Lkotlinx/coroutines/channels/ReceiveChannel;", "Lkotlin/Pair;", "destination", "continuation", "Lkotlin/coroutines/Continuation;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt", f = "Channels.common.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, l = {1053, 1053, 1055}, m = "toMap", n = {"$receiver", "destination", "$receiver$iv", "$receiver$iv$iv", "cause$iv$iv", "$receiver$iv", "$receiver", "destination", "$receiver$iv", "$receiver$iv$iv", "cause$iv$iv", "$receiver$iv"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$toMap$2 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    /* synthetic */ Object result;

    ChannelsKt__Channels_commonKt$toMap$2(Continuation continuation) {
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ChannelsKt.toMap(null, null, this);
    }
}
