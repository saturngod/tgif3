package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.ThreadContextElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<no name provided>", "", "countOrElement", "element", "Lkotlin/coroutines/CoroutineContext$Element;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: ThreadContext.kt */
final class ThreadContextKt$countAll$1 extends Lambda implements Function2<Object, Element, Object> {
    public static final ThreadContextKt$countAll$1 INSTANCE = new ThreadContextKt$countAll$1();

    ThreadContextKt$countAll$1() {
        super(2);
    }

    @Nullable
    public final Object invoke(@Nullable Object obj, @NotNull Element element) {
        Intrinsics.checkParameterIsNotNull(element, "element");
        if (!(element instanceof ThreadContextElement)) {
            return obj;
        }
        if (!(obj instanceof Integer)) {
            obj = null;
        }
        Integer num = (Integer) obj;
        obj = num != null ? num.intValue() : true;
        if (obj != null) {
            element = Integer.valueOf(obj + 1);
        }
        return element;
    }
}
