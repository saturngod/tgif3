package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.UndispatchedEventLoop.EventLoop;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/UndispatchedEventLoop$EventLoop;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: Dispatched.kt */
final class UndispatchedEventLoop$threadLocalEventLoop$1 extends Lambda implements Function0<EventLoop> {
    public static final UndispatchedEventLoop$threadLocalEventLoop$1 INSTANCE = new UndispatchedEventLoop$threadLocalEventLoop$1();

    UndispatchedEventLoop$threadLocalEventLoop$1() {
        super(0);
    }

    @NotNull
    public final EventLoop invoke() {
        return new EventLoop(false, null, 3, null);
    }
}
