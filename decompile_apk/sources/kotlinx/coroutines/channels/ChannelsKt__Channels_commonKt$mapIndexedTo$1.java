package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0010\b\u0002\u0010\u0004*\n\u0012\u0006\b\u0000\u0012\u0002H\u00030\u0005*\b\u0012\u0004\u0012\u0002H\u00020\u00062\u0006\u0010\u0007\u001a\u0002H\u00042'\u0010\b\u001a#\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\t2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00040\u000fHHø\u0001\u0000"}, d2 = {"mapIndexedTo", "", "E", "R", "C", "", "Lkotlinx/coroutines/channels/ReceiveChannel;", "destination", "transform", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "index", "continuation", "Lkotlin/coroutines/Continuation;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt", f = "Channels.common.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1}, l = {1291, 1291, 1294}, m = "mapIndexedTo", n = {"$receiver", "destination", "transform", "index", "$receiver$iv", "$receiver$iv$iv", "cause$iv$iv", "$receiver$iv", "$receiver", "destination", "transform", "index", "$receiver$iv", "$receiver$iv$iv", "cause$iv$iv", "$receiver$iv"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7"})
/* compiled from: Channels.common.kt */
public final class ChannelsKt__Channels_commonKt$mapIndexedTo$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    int label;
    /* synthetic */ Object result;

    public ChannelsKt__Channels_commonKt$mapIndexedTo$1(Continuation continuation) {
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ChannelsKt.mapIndexedTo(null, null, null, (Continuation) this);
    }
}
