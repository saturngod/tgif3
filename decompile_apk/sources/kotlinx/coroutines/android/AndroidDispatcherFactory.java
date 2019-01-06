package kotlinx.coroutines.android;

import android.support.annotation.Keep;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.internal.MainDispatcherFactory;
import org.jetbrains.annotations.NotNull;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lkotlinx/coroutines/android/AndroidDispatcherFactory;", "Lkotlinx/coroutines/internal/MainDispatcherFactory;", "()V", "loadPriority", "", "getLoadPriority", "()I", "createDispatcher", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "Companion", "kotlinx-coroutines-android"}, k = 1, mv = {1, 1, 13})
/* compiled from: HandlerDispatcher.kt */
public final class AndroidDispatcherFactory implements MainDispatcherFactory {
    public static final Companion Companion = new Companion();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lkotlinx/coroutines/android/AndroidDispatcherFactory$Companion;", "", "()V", "getDispatcher", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "kotlinx-coroutines-android"}, k = 1, mv = {1, 1, 13})
    /* compiled from: HandlerDispatcher.kt */
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final MainCoroutineDispatcher getDispatcher() {
            return HandlerDispatcherKt.Main;
        }
    }

    @JvmStatic
    @NotNull
    public static final MainCoroutineDispatcher getDispatcher() {
        return Companion.getDispatcher();
    }

    public int getLoadPriority() {
        return Integer.MAX_VALUE;
    }

    @NotNull
    public MainCoroutineDispatcher createDispatcher() {
        return HandlerDispatcherKt.Main;
    }
}
