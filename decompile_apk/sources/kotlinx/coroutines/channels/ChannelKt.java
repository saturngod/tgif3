package kotlinx.coroutines.channels;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Channel", "Lkotlinx/coroutines/channels/Channel;", "E", "capacity", "", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: Channel.kt */
public final class ChannelKt {
    @NotNull
    public static /* synthetic */ Channel Channel$default(int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return Channel(i);
    }

    @NotNull
    public static final <E> Channel<E> Channel(int i) {
        if (i == Integer.MAX_VALUE) {
            return (Channel) new LinkedListChannel();
        }
        switch (i) {
            case -1:
                return (Channel) new ConflatedChannel();
            case 0:
                return (Channel) new RendezvousChannel();
            default:
                return new ArrayChannel(i);
        }
    }
}
