package kotlinx.coroutines;

import java.util.Iterator;
import java.util.ServiceLoader;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.MainDispatcherFactory;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u0010\u0005\u001a\u00020\u0004*\u00020\u0006H\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/MainDispatcherLoader;", "", "()V", "dispatcher", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "tryCreateDispatcher", "Lkotlinx/coroutines/internal/MainDispatcherFactory;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Dispatchers.kt */
final class MainDispatcherLoader {
    public static final MainDispatcherLoader INSTANCE;
    @NotNull
    @JvmField
    public static final MainCoroutineDispatcher dispatcher;

    static {
        Object next;
        MainCoroutineDispatcher tryCreateDispatcher;
        MainDispatcherLoader mainDispatcherLoader = new MainDispatcherLoader();
        INSTANCE = mainDispatcherLoader;
        Class cls = MainDispatcherFactory.class;
        ServiceLoader load = ServiceLoader.load(cls, cls.getClassLoader());
        Intrinsics.checkExpressionValueIsNotNull(load, "ServiceLoader.load(clz, clz.classLoader)");
        Iterator it = CollectionsKt___CollectionsKt.toList(load).iterator();
        if (it.hasNext()) {
            next = it.next();
            int loadPriority = ((MainDispatcherFactory) next).getLoadPriority();
            while (it.hasNext()) {
                Object next2 = it.next();
                int loadPriority2 = ((MainDispatcherFactory) next2).getLoadPriority();
                if (loadPriority < loadPriority2) {
                    next = next2;
                    loadPriority = loadPriority2;
                }
            }
        } else {
            next = null;
        }
        MainDispatcherFactory mainDispatcherFactory = (MainDispatcherFactory) next;
        if (mainDispatcherFactory != null) {
            tryCreateDispatcher = mainDispatcherLoader.tryCreateDispatcher(mainDispatcherFactory);
            if (tryCreateDispatcher != null) {
                dispatcher = tryCreateDispatcher;
            }
        }
        tryCreateDispatcher = new MissingMainCoroutineDispatcher(null);
        dispatcher = tryCreateDispatcher;
    }

    private MainDispatcherLoader() {
    }

    private final MainCoroutineDispatcher tryCreateDispatcher(@NotNull MainDispatcherFactory mainDispatcherFactory) {
        try {
            return mainDispatcherFactory.createDispatcher();
        } catch (MainDispatcherFactory mainDispatcherFactory2) {
            return new MissingMainCoroutineDispatcher(mainDispatcherFactory2);
        }
    }
}
