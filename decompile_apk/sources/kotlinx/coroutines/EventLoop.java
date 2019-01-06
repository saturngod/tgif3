package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.CoroutineContext.Key;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lkotlinx/coroutines/EventLoop;", "Lkotlin/coroutines/ContinuationInterceptor;", "processNextEvent", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: EventLoop.kt */
public interface EventLoop extends ContinuationInterceptor {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* compiled from: EventLoop.kt */
    public static final class DefaultImpls {
        public static <R> R fold(EventLoop eventLoop, R r, @NotNull Function2<? super R, ? super Element, ? extends R> function2) {
            Intrinsics.checkParameterIsNotNull(function2, "operation");
            return kotlin.coroutines.ContinuationInterceptor.DefaultImpls.fold(eventLoop, r, function2);
        }

        @Nullable
        public static <E extends Element> E get(EventLoop eventLoop, @NotNull Key<E> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            return kotlin.coroutines.ContinuationInterceptor.DefaultImpls.get(eventLoop, key);
        }

        @NotNull
        public static CoroutineContext minusKey(EventLoop eventLoop, @NotNull Key<?> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            return kotlin.coroutines.ContinuationInterceptor.DefaultImpls.minusKey(eventLoop, key);
        }

        @NotNull
        public static CoroutineContext plus(EventLoop eventLoop, @NotNull CoroutineContext coroutineContext) {
            Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
            return kotlin.coroutines.ContinuationInterceptor.DefaultImpls.plus(eventLoop, coroutineContext);
        }

        public static void releaseInterceptedContinuation(EventLoop eventLoop, @NotNull Continuation<?> continuation) {
            Intrinsics.checkParameterIsNotNull(continuation, "continuation");
            kotlin.coroutines.ContinuationInterceptor.DefaultImpls.releaseInterceptedContinuation(eventLoop, continuation);
        }
    }

    long processNextEvent();
}
