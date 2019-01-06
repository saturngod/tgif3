package kotlinx.coroutines.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.CoroutineContext.Key;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.Delay.DefaultImpls;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.ObsoleteCoroutinesApi;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001:\u0001<B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u0019J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u0019J$\u0010\u001d\u001a\u00020\u001b2\b\b\u0002\u0010\u001e\u001a\u00020\u00032\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020!0 J$\u0010\"\u001a\u00020\u001b2\b\b\u0002\u0010\u001e\u001a\u00020\u00032\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020!0 J*\u0010#\u001a\u00020\u001b2\b\b\u0002\u0010\u001e\u001a\u00020\u00032\u0018\u0010\u001f\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0004\u0012\u00020!0 J$\u0010$\u001a\u00020\u001b2\b\b\u0002\u0010\u001e\u001a\u00020\u00032\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020!0 J\u0006\u0010%\u001a\u00020\u001bJ5\u0010&\u001a\u0002H'\"\u0004\b\u0000\u0010'2\u0006\u0010(\u001a\u0002H'2\u0018\u0010)\u001a\u0014\u0012\u0004\u0012\u0002H'\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u0002H'0*H\u0016¢\u0006\u0002\u0010,J(\u0010-\u001a\u0004\u0018\u0001H.\"\b\b\u0000\u0010.*\u00020+2\f\u0010/\u001a\b\u0012\u0004\u0012\u0002H.00H\u0002¢\u0006\u0002\u00101J\u0014\u00102\u001a\u00020\u00012\n\u0010/\u001a\u0006\u0012\u0002\b\u000300H\u0016J\u0010\u00103\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u0019J\u0014\u00104\u001a\u00020\u001b2\n\u00105\u001a\u000606j\u0002`7H\u0002J\u001c\u00108\u001a\u00020\u00122\n\u00105\u001a\u000606j\u0002`72\u0006\u0010\u0017\u001a\u00020\u0006H\u0002J\b\u00109\u001a\u00020\u0006H\u0002J\b\u0010:\u001a\u00020\u0003H\u0016J\u0006\u0010;\u001a\u00020\u001bJ\u0010\u0010;\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0006H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00060\bR\u00020\u0000X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\u0015X\u0004¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lkotlinx/coroutines/test/TestCoroutineContext;", "Lkotlin/coroutines/CoroutineContext;", "name", "", "(Ljava/lang/String;)V", "counter", "", "ctxDispatcher", "Lkotlinx/coroutines/test/TestCoroutineContext$Dispatcher;", "ctxHandler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "exceptions", "", "", "getExceptions", "()Ljava/util/List;", "queue", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "Lkotlinx/coroutines/test/TimedRunnable;", "time", "uncaughtExceptions", "", "advanceTimeBy", "delayTime", "unit", "Ljava/util/concurrent/TimeUnit;", "advanceTimeTo", "", "targetTime", "assertAllUnhandledExceptions", "message", "predicate", "Lkotlin/Function1;", "", "assertAnyUnhandledException", "assertExceptions", "assertUnhandledException", "cancelAllActions", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "Lkotlin/coroutines/CoroutineContext$Element;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", "E", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "minusKey", "now", "post", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "postDelayed", "processNextEvent", "toString", "triggerActions", "Dispatcher", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
@ObsoleteCoroutinesApi
/* compiled from: TestCoroutineContext.kt */
public final class TestCoroutineContext implements CoroutineContext {
    private long counter;
    private final Dispatcher ctxDispatcher;
    private final CoroutineExceptionHandler ctxHandler;
    private final String name;
    private final ThreadSafeHeap<TimedRunnable> queue;
    private long time;
    private final List<Throwable> uncaughtExceptions;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u00060\nj\u0002`\u000bH\u0016J\u001c\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\n\u0010\t\u001a\u00060\nj\u0002`\u000bH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\u001e\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016¨\u0006\u0016"}, d2 = {"Lkotlinx/coroutines/test/TestCoroutineContext$Dispatcher;", "Lkotlinx/coroutines/CoroutineDispatcher;", "Lkotlinx/coroutines/Delay;", "Lkotlinx/coroutines/EventLoop;", "(Lkotlinx/coroutines/test/TestCoroutineContext;)V", "dispatch", "", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "", "processNextEvent", "scheduleResumeAfterDelay", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: TestCoroutineContext.kt */
    private final class Dispatcher extends CoroutineDispatcher implements Delay, EventLoop {
        @Nullable
        public Object delay(long j, @NotNull Continuation<? super Unit> continuation) {
            return DefaultImpls.delay(this, j, continuation);
        }

        public void dispatch(@NotNull CoroutineContext coroutineContext, @NotNull Runnable runnable) {
            Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
            Intrinsics.checkParameterIsNotNull(runnable, "block");
            TestCoroutineContext.this.post(runnable);
        }

        public void scheduleResumeAfterDelay(long j, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
            Intrinsics.checkParameterIsNotNull(cancellableContinuation, "continuation");
            TestCoroutineContext.this.postDelayed(new C0241xe7664c50(this, cancellableContinuation), j);
        }

        @NotNull
        public DisposableHandle invokeOnTimeout(long j, @NotNull Runnable runnable) {
            Intrinsics.checkParameterIsNotNull(runnable, "block");
            return new TestCoroutineContext$Dispatcher$invokeOnTimeout$1(this, TestCoroutineContext.this.postDelayed(runnable, j));
        }

        public long processNextEvent() {
            return TestCoroutineContext.this.processNextEvent();
        }

        @NotNull
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Dispatcher(");
            stringBuilder.append(TestCoroutineContext.this);
            stringBuilder.append(')');
            return stringBuilder.toString();
        }
    }

    public TestCoroutineContext() {
        this(null, 1, null);
    }

    public TestCoroutineContext(@Nullable String str) {
        this.name = str;
        this.uncaughtExceptions = (List) new ArrayList();
        this.ctxDispatcher = new Dispatcher();
        this.ctxHandler = (CoroutineExceptionHandler) new C0341x96bc1e17(CoroutineExceptionHandler.Key, this);
        this.queue = new ThreadSafeHeap();
    }

    public /* synthetic */ TestCoroutineContext(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            str = null;
        }
        this(str);
    }

    @NotNull
    public CoroutineContext plus(@NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        return CoroutineContext.DefaultImpls.plus(this, coroutineContext);
    }

    @NotNull
    public final List<Throwable> getExceptions() {
        return this.uncaughtExceptions;
    }

    public <R> R fold(R r, @NotNull Function2<? super R, ? super Element, ? extends R> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "operation");
        return function2.invoke(function2.invoke(r, this.ctxDispatcher), this.ctxHandler);
    }

    @Nullable
    public <E extends Element> E get(@NotNull Key<E> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (key == ContinuationInterceptor.Key) {
            key = this.ctxDispatcher;
            if (key != null) {
                return (Element) key;
            }
            throw new TypeCastException("null cannot be cast to non-null type E");
        } else if (key != CoroutineExceptionHandler.Key) {
            return null;
        } else {
            key = this.ctxHandler;
            if (key != null) {
                return (Element) key;
            }
            throw new TypeCastException("null cannot be cast to non-null type E");
        }
    }

    @NotNull
    public CoroutineContext minusKey(@NotNull Key<?> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (key == ContinuationInterceptor.Key) {
            return (CoroutineContext) this.ctxHandler;
        }
        if (key == CoroutineExceptionHandler.Key) {
            return (CoroutineContext) this.ctxDispatcher;
        }
        return this;
    }

    public static /* synthetic */ long now$default(TestCoroutineContext testCoroutineContext, TimeUnit timeUnit, int i, Object obj) {
        if ((i & 1) != 0) {
            timeUnit = TimeUnit.MILLISECONDS;
        }
        return testCoroutineContext.now(timeUnit);
    }

    public final long now(@NotNull TimeUnit timeUnit) {
        Intrinsics.checkParameterIsNotNull(timeUnit, "unit");
        return timeUnit.convert(this.time, TimeUnit.NANOSECONDS);
    }

    public static /* synthetic */ long advanceTimeBy$default(TestCoroutineContext testCoroutineContext, long j, TimeUnit timeUnit, int i, Object obj) {
        if ((i & 2) != 0) {
            timeUnit = TimeUnit.MILLISECONDS;
        }
        return testCoroutineContext.advanceTimeBy(j, timeUnit);
    }

    public final long advanceTimeBy(long j, @NotNull TimeUnit timeUnit) {
        Intrinsics.checkParameterIsNotNull(timeUnit, "unit");
        long j2 = this.time;
        advanceTimeTo(timeUnit.toNanos(j) + j2, TimeUnit.NANOSECONDS);
        return timeUnit.convert(this.time - j2, TimeUnit.NANOSECONDS);
    }

    public static /* synthetic */ void advanceTimeTo$default(TestCoroutineContext testCoroutineContext, long j, TimeUnit timeUnit, int i, Object obj) {
        if ((i & 2) != 0) {
            timeUnit = TimeUnit.MILLISECONDS;
        }
        testCoroutineContext.advanceTimeTo(j, timeUnit);
    }

    public final void advanceTimeTo(long j, @NotNull TimeUnit timeUnit) {
        Intrinsics.checkParameterIsNotNull(timeUnit, "unit");
        j = timeUnit.toNanos(j);
        triggerActions(j);
        if (j > this.time) {
            this.time = j;
        }
    }

    public final void triggerActions() {
        triggerActions(this.time);
    }

    public final void cancelAllActions() {
        if (!this.queue.isEmpty()) {
            this.queue.clear();
        }
    }

    public static /* synthetic */ void assertUnhandledException$default(TestCoroutineContext testCoroutineContext, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        testCoroutineContext.assertUnhandledException(str, function1);
    }

    public final void assertUnhandledException(@NotNull String str, @NotNull Function1<? super Throwable, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        if (this.uncaughtExceptions.size() != 1 || ((Boolean) function1.invoke(this.uncaughtExceptions.get(0))).booleanValue() == null) {
            throw ((Throwable) new AssertionError(str));
        }
        this.uncaughtExceptions.clear();
    }

    public static /* synthetic */ void assertAllUnhandledExceptions$default(TestCoroutineContext testCoroutineContext, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        testCoroutineContext.assertAllUnhandledExceptions(str, function1);
    }

    public final void assertAllUnhandledExceptions(@NotNull String str, @NotNull Function1<? super Throwable, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        Iterable<Object> iterable = this.uncaughtExceptions;
        Object obj = 1;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            for (Object invoke : iterable) {
                if (!((Boolean) function1.invoke(invoke)).booleanValue()) {
                    obj = null;
                    break;
                }
            }
        }
        if (obj != null) {
            this.uncaughtExceptions.clear();
            return;
        }
        throw ((Throwable) new AssertionError(str));
    }

    public static /* synthetic */ void assertAnyUnhandledException$default(TestCoroutineContext testCoroutineContext, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        testCoroutineContext.assertAnyUnhandledException(str, function1);
    }

    public final void assertAnyUnhandledException(@NotNull String str, @NotNull Function1<? super Throwable, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        Iterable<Object> iterable = this.uncaughtExceptions;
        Object obj = null;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            for (Object invoke : iterable) {
                if (((Boolean) function1.invoke(invoke)).booleanValue()) {
                    obj = 1;
                    break;
                }
            }
        }
        if (obj != null) {
            this.uncaughtExceptions.clear();
            return;
        }
        throw ((Throwable) new AssertionError(str));
    }

    public static /* synthetic */ void assertExceptions$default(TestCoroutineContext testCoroutineContext, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        testCoroutineContext.assertExceptions(str, function1);
    }

    public final void assertExceptions(@NotNull String str, @NotNull Function1<? super List<? extends Throwable>, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        if (((Boolean) function1.invoke(this.uncaughtExceptions)).booleanValue() != null) {
            this.uncaughtExceptions.clear();
            return;
        }
        throw ((Throwable) new AssertionError(str));
    }

    private final void post(Runnable runnable) {
        ThreadSafeHeap threadSafeHeap = this.queue;
        long j = this.counter;
        this.counter = 1 + j;
        threadSafeHeap.addLast(new TimedRunnable(runnable, j, 0, 4, null));
    }

    private final TimedRunnable postDelayed(Runnable runnable, long j) {
        long j2 = this.counter;
        this.counter = 1 + j2;
        TimedRunnable timedRunnable = new TimedRunnable(runnable, j2, this.time + TimeUnit.MILLISECONDS.toNanos(j));
        this.queue.addLast(timedRunnable);
        return timedRunnable;
    }

    private final long processNextEvent() {
        TimedRunnable timedRunnable = (TimedRunnable) this.queue.peek();
        if (timedRunnable != null) {
            triggerActions(timedRunnable.time);
        }
        return this.queue.isEmpty() ? LongCompanionObject.MAX_VALUE : 0;
    }

    private final void triggerActions(long j) {
        while (true) {
            ThreadSafeHeapNode threadSafeHeapNode;
            ThreadSafeHeap threadSafeHeap = this.queue;
            synchronized (threadSafeHeap) {
                ThreadSafeHeapNode firstImpl = threadSafeHeap.firstImpl();
                threadSafeHeapNode = null;
                if (firstImpl != null) {
                    if ((((TimedRunnable) firstImpl).time <= j ? 1 : null) != null) {
                        threadSafeHeapNode = threadSafeHeap.removeAtImpl(0);
                    }
                }
            }
            TimedRunnable timedRunnable = (TimedRunnable) threadSafeHeapNode;
            if (timedRunnable != null) {
                if (timedRunnable.time != 0) {
                    this.time = timedRunnable.time;
                }
                timedRunnable.run();
            } else {
                return;
            }
        }
    }

    @NotNull
    public String toString() {
        String str = this.name;
        if (str != null) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TestCoroutineContext@");
        stringBuilder.append(DebugKt.getHexAddress(this));
        return stringBuilder.toString();
    }
}
