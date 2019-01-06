package org.jetbrains.anko.custom;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.anko.AnkoAsyncContext;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "T", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: Deprecated.kt */
final class DeprecatedKt$async$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ AnkoAsyncContext $context;
    final /* synthetic */ Function1 $task;

    DeprecatedKt$async$1(Function1 function1, AnkoAsyncContext ankoAsyncContext) {
        this.$task = function1;
        this.$context = ankoAsyncContext;
        super(0);
    }

    public final void invoke() {
        this.$task.invoke(this.$context);
    }
}
