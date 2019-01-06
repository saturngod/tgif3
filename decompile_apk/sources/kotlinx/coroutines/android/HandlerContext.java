package kotlinx.coroutines.android;

import android.os.Handler;
import android.os.Looper;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.DisposableHandle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u001b\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001c\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u00060\u0014j\u0002`\u0015H\u0016J\u0013\u0010\u0016\u001a\u00020\t2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u001c\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\n\u0010\u0013\u001a\u00060\u0014j\u0002`\u0015H\u0016J\u0010\u0010\u001f\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001e\u0010 \u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00100\"H\u0016J\b\u0010#\u001a\u00020\u0006H\u0016R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0000X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\u0000X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lkotlinx/coroutines/android/HandlerContext;", "Lkotlinx/coroutines/android/HandlerDispatcher;", "Lkotlinx/coroutines/Delay;", "handler", "Landroid/os/Handler;", "name", "", "(Landroid/os/Handler;Ljava/lang/String;)V", "invokeImmediately", "", "(Landroid/os/Handler;Ljava/lang/String;Z)V", "_immediate", "immediate", "getImmediate", "()Lkotlinx/coroutines/android/HandlerContext;", "dispatch", "", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "equals", "other", "", "hashCode", "", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "", "isDispatchNeeded", "scheduleResumeAfterDelay", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "toString", "kotlinx-coroutines-android"}, k = 1, mv = {1, 1, 13})
/* compiled from: HandlerDispatcher.kt */
public final class HandlerContext extends HandlerDispatcher implements Delay {
    private volatile HandlerContext _immediate;
    private final Handler handler;
    @NotNull
    private final HandlerContext immediate;
    private final boolean invokeImmediately;
    private final String name;

    private HandlerContext(Handler handler, String str, boolean z) {
        HandlerContext handlerContext = null;
        super();
        this.handler = handler;
        this.name = str;
        this.invokeImmediately = z;
        if (this.invokeImmediately != null) {
            handlerContext = this;
        }
        this._immediate = handlerContext;
        handler = this._immediate;
        if (handler == null) {
            handler = new HandlerContext(this.handler, this.name, true);
            this._immediate = handler;
        }
        this.immediate = handler;
    }

    public /* synthetic */ HandlerContext(Handler handler, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 2) != 0) {
            str = null;
        }
        this(handler, str);
    }

    public HandlerContext(@NotNull Handler handler, @Nullable String str) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        this(handler, str, false);
    }

    @NotNull
    public HandlerContext getImmediate() {
        return this.immediate;
    }

    public boolean isDispatchNeeded(@NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        if (this.invokeImmediately != null) {
            return (Intrinsics.areEqual(Looper.myLooper(), this.handler.getLooper()) ^ 1) != null;
        } else {
            return true;
        }
    }

    public void dispatch(@NotNull CoroutineContext coroutineContext, @NotNull Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(runnable, "block");
        this.handler.post(runnable);
    }

    @NotNull
    public DisposableHandle invokeOnTimeout(long j, @NotNull Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(runnable, "block");
        this.handler.postDelayed(runnable, RangesKt___RangesKt.coerceAtMost(j, 4611686018427387903L));
        return (DisposableHandle) new HandlerContext$invokeOnTimeout$1(this, runnable);
    }

    @NotNull
    public String toString() {
        if (this.name == null) {
            String handler = this.handler.toString();
            Intrinsics.checkExpressionValueIsNotNull(handler, "handler.toString()");
            return handler;
        } else if (!this.invokeImmediately) {
            return this.name;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.name);
            stringBuilder.append(" [immediate]");
            return stringBuilder.toString();
        }
    }

    public boolean equals(@Nullable Object obj) {
        return ((obj instanceof HandlerContext) && ((HandlerContext) obj).handler == this.handler) ? true : null;
    }

    public int hashCode() {
        return System.identityHashCode(this.handler);
    }

    public void scheduleResumeAfterDelay(long j, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
        Intrinsics.checkParameterIsNotNull(cancellableContinuation, "continuation");
        Runnable handlerContext$scheduleResumeAfterDelay$$inlined$Runnable$1 = new HandlerContext$scheduleResumeAfterDelay$$inlined$Runnable$1(this, cancellableContinuation);
        this.handler.postDelayed(handlerContext$scheduleResumeAfterDelay$$inlined$Runnable$1, RangesKt___RangesKt.coerceAtMost(j, 4611686018427387903L));
        cancellableContinuation.invokeOnCancellation((Function1) new HandlerContext$scheduleResumeAfterDelay$1(this, handlerContext$scheduleResumeAfterDelay$$inlined$Runnable$1));
    }
}
