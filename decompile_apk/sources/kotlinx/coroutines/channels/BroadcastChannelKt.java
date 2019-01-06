package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"BroadcastChannel", "Lkotlinx/coroutines/channels/BroadcastChannel;", "E", "capacity", "", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: BroadcastChannel.kt */
public final class BroadcastChannelKt {
    @NotNull
    @ExperimentalCoroutinesApi
    public static final <E> BroadcastChannel<E> BroadcastChannel(int i) {
        if (i != Integer.MAX_VALUE) {
            switch (i) {
                case -1:
                    return (BroadcastChannel) new ConflatedBroadcastChannel();
                case 0:
                    throw ((Throwable) new IllegalArgumentException("Unsupported 0 capacity for BroadcastChannel"));
                default:
                    return new ArrayBroadcastChannel(i);
            }
        }
        throw ((Throwable) new IllegalArgumentException("Unsupported UNLIMITED capacity for BroadcastChannel"));
    }
}
