package kotlinx.coroutines.android;

import android.support.annotation.Keep;
import java.lang.Thread.UncaughtExceptionHandler;
import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineExceptionHandler;
import org.jetbrains.annotations.NotNull;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lkotlinx/coroutines/android/AndroidExceptionPreHandler;", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "()V", "handleException", "", "context", "Lkotlin/coroutines/CoroutineContext;", "exception", "", "kotlinx-coroutines-android"}, k = 1, mv = {1, 1, 13})
/* compiled from: AndroidExceptionPreHandler.kt */
public final class AndroidExceptionPreHandler extends AbstractCoroutineContextElement implements CoroutineExceptionHandler {
    public AndroidExceptionPreHandler() {
        super(CoroutineExceptionHandler.Key);
    }

    public void handleException(@NotNull CoroutineContext coroutineContext, @NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(th, "exception");
        coroutineContext = AndroidExceptionPreHandlerKt.getter;
        coroutineContext = coroutineContext != null ? coroutineContext.invoke(null, new Object[0]) : null;
        if (!(coroutineContext instanceof UncaughtExceptionHandler)) {
            coroutineContext = null;
        }
        UncaughtExceptionHandler uncaughtExceptionHandler = (UncaughtExceptionHandler) coroutineContext;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), th);
        }
    }
}
