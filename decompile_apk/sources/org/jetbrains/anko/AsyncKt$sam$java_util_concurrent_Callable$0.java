package org.jetbrains.anko;

import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
/* compiled from: Async.kt */
final class AsyncKt$sam$java_util_concurrent_Callable$0 implements Callable {
    private final /* synthetic */ Function0 function;

    AsyncKt$sam$java_util_concurrent_Callable$0(Function0 function0) {
        this.function = function0;
    }

    public final /* synthetic */ Object call() {
        return this.function.invoke();
    }
}
