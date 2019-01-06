package kotlinx.coroutines.android;

import kotlin.Metadata;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 13})
/* compiled from: HandlerDispatcher.kt */
final class HandlerDispatcherKt$awaitFrame$3$1 implements Runnable {
    final /* synthetic */ CancellableContinuation $cont;

    HandlerDispatcherKt$awaitFrame$3$1(CancellableContinuation cancellableContinuation) {
        this.$cont = cancellableContinuation;
    }

    public final void run() {
        HandlerDispatcherKt.updateChoreographerAndPostFrameCallback(this.$cont);
    }
}
