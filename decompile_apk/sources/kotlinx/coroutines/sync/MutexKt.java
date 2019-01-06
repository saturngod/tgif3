package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlin.Result.Failure;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r\u001a5\u0010\u000e\u001a\u0002H\u000f\"\u0004\b\u0000\u0010\u000f*\u00020\u000b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0013HHø\u0001\u0000¢\u0006\u0002\u0010\u0014\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"ENQUEUE_FAIL", "Lkotlinx/coroutines/internal/Symbol;", "EmptyLocked", "Lkotlinx/coroutines/sync/Empty;", "EmptyUnlocked", "LOCKED", "LOCK_FAIL", "SELECT_SUCCESS", "UNLOCKED", "UNLOCK_FAIL", "Mutex", "Lkotlinx/coroutines/sync/Mutex;", "locked", "", "withLock", "T", "owner", "", "action", "Lkotlin/Function0;", "(Lkotlinx/coroutines/sync/Mutex;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: Mutex.kt */
public final class MutexKt {
    private static final Symbol ENQUEUE_FAIL = new Symbol("ENQUEUE_FAIL");
    private static final Empty EmptyLocked = new Empty(LOCKED);
    private static final Empty EmptyUnlocked = new Empty(UNLOCKED);
    private static final Symbol LOCKED = new Symbol("LOCKED");
    private static final Symbol LOCK_FAIL = new Symbol("LOCK_FAIL");
    private static final Symbol SELECT_SUCCESS = new Symbol("SELECT_SUCCESS");
    private static final Symbol UNLOCKED = new Symbol("UNLOCKED");
    private static final Symbol UNLOCK_FAIL = new Symbol("UNLOCK_FAIL");

    @NotNull
    public static /* synthetic */ Mutex Mutex$default(boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return Mutex(z);
    }

    @NotNull
    public static final Mutex Mutex(boolean z) {
        return new MutexImpl(z);
    }

    @Nullable
    public static final <T> Object withLock(@NotNull Mutex mutex, @Nullable Object obj, @NotNull Function0<? extends T> function0, @NotNull Continuation<? super T> continuation) {
        Continuation continuation2;
        Continuation<? super T> coroutine_suspended;
        if (continuation instanceof MutexKt$withLock$1) {
            continuation2 = (MutexKt$withLock$1) continuation;
            if ((continuation2.label & Integer.MIN_VALUE) != 0) {
                continuation2.label -= -2147483648;
                continuation = continuation2.result;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (continuation2.label) {
                    case 0:
                        if (continuation instanceof Failure) {
                            continuation2.L$0 = mutex;
                            continuation2.L$1 = obj;
                            continuation2.L$2 = function0;
                            continuation2.label = 1;
                            if (mutex.lock(obj, continuation2) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                        throw ((Failure) continuation).exception;
                        break;
                    case 1:
                        function0 = (Function0) continuation2.L$2;
                        obj = continuation2.L$1;
                        mutex = continuation2.L$0;
                        if (!(continuation instanceof Failure)) {
                            break;
                        }
                        throw ((Failure) continuation).exception;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                function0 = function0.invoke();
                return function0;
            }
        }
        continuation2 = new MutexKt$withLock$1(continuation);
        continuation = continuation2.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (continuation2.label) {
            case 0:
                if (continuation instanceof Failure) {
                    throw ((Failure) continuation).exception;
                }
                continuation2.L$0 = mutex;
                continuation2.L$1 = obj;
                continuation2.L$2 = function0;
                continuation2.label = 1;
                if (mutex.lock(obj, continuation2) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                function0 = (Function0) continuation2.L$2;
                obj = continuation2.L$1;
                mutex = continuation2.L$0;
                if (!(continuation instanceof Failure)) {
                    break;
                }
                throw ((Failure) continuation).exception;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        try {
            function0 = function0.invoke();
            return function0;
        } finally {
            InlineMarker.finallyStart(1);
            mutex.unlock(obj);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    private static final Object withLock$$forInline(@NotNull Mutex mutex, @Nullable Object obj, @NotNull Function0 function0, @NotNull Continuation continuation) {
        InlineMarker.mark(0);
        mutex.lock(obj, continuation);
        InlineMarker.mark(2);
        InlineMarker.mark(1);
        try {
            function0 = function0.invoke();
            return function0;
        } finally {
            InlineMarker.finallyStart(1);
            mutex.unlock(obj);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    public static /* synthetic */ Object withLock$default(Mutex mutex, Object obj, Function0 function0, Continuation continuation, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = null;
        }
        InlineMarker.mark(0);
        mutex.lock(obj, continuation);
        InlineMarker.mark(2);
        InlineMarker.mark(1);
        try {
            function0 = function0.invoke();
            return function0;
        } finally {
            InlineMarker.finallyStart(1);
            mutex.unlock(obj);
            InlineMarker.finallyEnd(1);
        }
    }
}
