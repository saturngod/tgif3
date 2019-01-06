package org.jetbrains.anko;

import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "R", "T", "call", "()Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
/* compiled from: Async.kt */
final class AsyncKt$doAsyncResult$2<V> implements Callable<R> {
    final /* synthetic */ AnkoAsyncContext $context;
    final /* synthetic */ Function1 $exceptionHandler;
    final /* synthetic */ Function1 $task;

    AsyncKt$doAsyncResult$2(Function1 function1, AnkoAsyncContext ankoAsyncContext, Function1 function12) {
        this.$task = function1;
        this.$context = ankoAsyncContext;
        this.$exceptionHandler = function12;
    }

    public final R call() {
        try {
            return this.$task.invoke(this.$context);
        } catch (Throwable th) {
            Function1 function1 = this.$exceptionHandler;
            if (function1 != null) {
                Unit unit = (Unit) function1.invoke(th);
            }
        }
    }
}
