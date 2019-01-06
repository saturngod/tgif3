package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0001\"\u0010\b\u0001\u0010\u0003*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0004*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u00052\u0006\u0010\u0006\u001a\u0002H\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00030\bH@ø\u0001\u0000"}, d2 = {"filterNotNullTo", "", "E", "C", "", "Lkotlinx/coroutines/channels/ReceiveChannel;", "destination", "continuation", "Lkotlin/coroutines/Continuation;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt", f = "Channels.common.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, l = {737, 737, 739}, m = "filterNotNullTo", n = {"$receiver", "destination", "$receiver$iv", "$receiver$iv$iv", "cause$iv$iv", "$receiver$iv", "$receiver", "destination", "$receiver$iv", "$receiver$iv$iv", "cause$iv$iv", "$receiver$iv"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$filterNotNullTo$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    /* synthetic */ Object result;

    ChannelsKt__Channels_commonKt$filterNotNullTo$1(Continuation continuation) {
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ChannelsKt.filterNotNullTo(null, null, (Continuation) this);
    }
}
