package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aG\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032'\u0010\u0004\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\tø\u0001\u0000¢\u0006\u0002\u0010\n\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"runBlocking", "T", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 5, mv = {1, 1, 13}, xs = "kotlinx/coroutines/BuildersKt")
/* compiled from: Builders.kt */
final /* synthetic */ class BuildersKt__BuildersKt {
    public static /* synthetic */ Object runBlocking$default(CoroutineContext coroutineContext, Function2 function2, int i, Object obj) throws InterruptedException {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return BuildersKt.runBlocking(coroutineContext, function2);
    }

    public static final <T> T runBlocking(@NotNull CoroutineContext coroutineContext, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2) throws InterruptedException {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "block");
        Thread currentThread = Thread.currentThread();
        BlockingEventLoop blockingEventLoop = (ContinuationInterceptor) coroutineContext.get(ContinuationInterceptor.Key);
        boolean z = blockingEventLoop == null;
        if (z) {
            Intrinsics.checkExpressionValueIsNotNull(currentThread, "currentThread");
            blockingEventLoop = new BlockingEventLoop(currentThread);
        } else if (!(blockingEventLoop instanceof EventLoop)) {
            blockingEventLoop = null;
        }
        EventLoop eventLoop = blockingEventLoop;
        CoroutineScope coroutineScope = GlobalScope.INSTANCE;
        if (z) {
            if (eventLoop != null) {
                coroutineContext = coroutineContext.plus(eventLoop);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.ContinuationInterceptor");
            }
        }
        coroutineContext = CoroutineContextKt.newCoroutineContext(coroutineScope, coroutineContext);
        Intrinsics.checkExpressionValueIsNotNull(currentThread, "currentThread");
        BlockingCoroutine blockingCoroutine = new BlockingCoroutine(coroutineContext, currentThread, eventLoop, z);
        blockingCoroutine.start(CoroutineStart.DEFAULT, blockingCoroutine, function2);
        return blockingCoroutine.joinBlocking();
    }
}
