package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.internal.LongCompanionObject;
import kotlinx.coroutines.internal.Symbol;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0000\u001a\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\tXT¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\tXT¢\u0006\u0002\n\u0000*\u001e\b\u0002\u0010\u0010\u001a\u0004\b\u0000\u0010\u0011\"\b\u0012\u0004\u0012\u0002H\u00110\u00122\b\u0012\u0004\u0012\u0002H\u00110\u0012¨\u0006\u0013"}, d2 = {"CLOSED_EMPTY", "Lkotlinx/coroutines/internal/Symbol;", "CLOSED_EMPTY$annotations", "()V", "DISPOSED_TASK", "MAX_MS", "", "MS_TO_NS", "SCHEDULE_COMPLETED", "", "SCHEDULE_DISPOSED", "SCHEDULE_OK", "delayNanosToMillis", "timeNanos", "delayToNanos", "timeMillis", "Queue", "T", "Lkotlinx/coroutines/internal/LockFreeMPSCQueueCore;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: EventLoop.kt */
public final class EventLoopKt {
    private static final Symbol CLOSED_EMPTY = new Symbol("CLOSED_EMPTY");
    private static final Symbol DISPOSED_TASK = new Symbol("REMOVED_TASK");
    private static final long MAX_MS = 9223372036854L;
    private static final long MS_TO_NS = 1000000;
    private static final int SCHEDULE_COMPLETED = 1;
    private static final int SCHEDULE_DISPOSED = 2;
    private static final int SCHEDULE_OK = 0;

    private static /* synthetic */ void CLOSED_EMPTY$annotations() {
    }

    public static final long delayToNanos(long j) {
        return j <= 0 ? 0 : j >= MAX_MS ? LongCompanionObject.MAX_VALUE : MS_TO_NS * j;
    }

    public static final long delayNanosToMillis(long j) {
        return j / MS_TO_NS;
    }
}
