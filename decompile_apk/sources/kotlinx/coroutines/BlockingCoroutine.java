package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u000b\u0010\f\u001a\u00028\u0000¢\u0006\u0002\u0010\rJ'\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\nH\u0010¢\u0006\u0002\b\u0015R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lkotlinx/coroutines/BlockingCoroutine;", "T", "Lkotlinx/coroutines/AbstractCoroutine;", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "blockedThread", "Ljava/lang/Thread;", "eventLoop", "Lkotlinx/coroutines/EventLoop;", "privateEventLoop", "", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Thread;Lkotlinx/coroutines/EventLoop;Z)V", "joinBlocking", "()Ljava/lang/Object;", "onCompletionInternal", "", "state", "", "mode", "", "suppressed", "onCompletionInternal$kotlinx_coroutines_core", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Builders.kt */
final class BlockingCoroutine<T> extends AbstractCoroutine<T> {
    private final Thread blockedThread;
    private final EventLoop eventLoop;
    private final boolean privateEventLoop;

    public BlockingCoroutine(@NotNull CoroutineContext coroutineContext, @NotNull Thread thread, @Nullable EventLoop eventLoop, boolean z) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "parentContext");
        Intrinsics.checkParameterIsNotNull(thread, "blockedThread");
        super(coroutineContext, true);
        this.blockedThread = thread;
        this.eventLoop = eventLoop;
        this.privateEventLoop = z;
        if (this.privateEventLoop == null) {
            return;
        }
        if ((this.eventLoop instanceof BlockingEventLoop) == null) {
            throw ((Throwable) new IllegalArgumentException("Failed requirement.".toString()));
        }
    }

    public void onCompletionInternal$kotlinx_coroutines_core(@Nullable Object obj, int i, boolean z) {
        if ((Intrinsics.areEqual(Thread.currentThread(), this.blockedThread) ^ 1) != null) {
            LockSupport.unpark(this.blockedThread);
        }
    }

    public final T joinBlocking() {
        TimeSourceKt.getTimeSource().registerTimeLoopThread();
        while (!Thread.interrupted()) {
            EventLoop eventLoop = this.eventLoop;
            long processNextEvent = eventLoop != null ? eventLoop.processNextEvent() : LongCompanionObject.MAX_VALUE;
            if (isCompleted()) {
                if (this.privateEventLoop) {
                    eventLoop = this.eventLoop;
                    if (eventLoop != null) {
                        BlockingEventLoop blockingEventLoop = (BlockingEventLoop) eventLoop;
                        blockingEventLoop.setCompleted(true);
                        blockingEventLoop.shutdown();
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.BlockingEventLoop");
                    }
                }
                TimeSourceKt.getTimeSource().unregisterTimeLoopThread();
                T state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
                CompletedExceptionally completedExceptionally = (CompletedExceptionally) (!(state$kotlinx_coroutines_core instanceof CompletedExceptionally) ? null : state$kotlinx_coroutines_core);
                if (completedExceptionally == null) {
                    return state$kotlinx_coroutines_core;
                }
                throw completedExceptionally.cause;
            }
            TimeSourceKt.getTimeSource().parkNanos(this, processNextEvent);
        }
        Throwable interruptedException = new InterruptedException();
        cancel(interruptedException);
        throw interruptedException;
    }
}
