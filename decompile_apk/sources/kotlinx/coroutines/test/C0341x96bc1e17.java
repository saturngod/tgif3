package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.CoroutineContext.Key;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineExceptionHandler;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t¸\u0006\u0000"}, d2 = {"kotlinx/coroutines/CoroutineExceptionHandlerKt$CoroutineExceptionHandler$1", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "handleException", "", "context", "Lkotlin/coroutines/CoroutineContext;", "exception", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: CoroutineExceptionHandler.kt */
/* renamed from: kotlinx.coroutines.test.TestCoroutineContext$$special$$inlined$CoroutineExceptionHandler$1 */
public final class C0341x96bc1e17 extends AbstractCoroutineContextElement implements CoroutineExceptionHandler {
    final /* synthetic */ TestCoroutineContext this$0;

    public C0341x96bc1e17(Key key, TestCoroutineContext testCoroutineContext) {
        this.this$0 = testCoroutineContext;
        super(key);
    }

    public void handleException(@NotNull CoroutineContext coroutineContext, @NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(th, "exception");
        this.this$0.uncaughtExceptions.add(th);
    }
}
