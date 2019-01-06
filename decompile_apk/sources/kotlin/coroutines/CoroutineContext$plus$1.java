package kotlin.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lkotlin/coroutines/CoroutineContext;", "acc", "element", "Lkotlin/coroutines/CoroutineContext$Element;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: CoroutineContext.kt */
final class CoroutineContext$plus$1 extends Lambda implements Function2<CoroutineContext, Element, CoroutineContext> {
    public static final CoroutineContext$plus$1 INSTANCE = new CoroutineContext$plus$1();

    CoroutineContext$plus$1() {
        super(2);
    }

    @NotNull
    public final CoroutineContext invoke(@NotNull CoroutineContext coroutineContext, @NotNull Element element) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "acc");
        Intrinsics.checkParameterIsNotNull(element, "element");
        coroutineContext = coroutineContext.minusKey(element.getKey());
        if (coroutineContext == EmptyCoroutineContext.INSTANCE) {
            return element;
        }
        ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) coroutineContext.get(ContinuationInterceptor.Key);
        if (continuationInterceptor == null) {
            coroutineContext = new CombinedContext(coroutineContext, element);
        } else {
            coroutineContext = coroutineContext.minusKey(ContinuationInterceptor.Key);
            if (coroutineContext == EmptyCoroutineContext.INSTANCE) {
                coroutineContext = new CombinedContext(element, continuationInterceptor);
            } else {
                coroutineContext = new CombinedContext(new CombinedContext(coroutineContext, element), continuationInterceptor);
            }
        }
        return coroutineContext;
    }
}
