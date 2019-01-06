package org.jetbrains.anko;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "throwable", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: Async.kt */
final class AsyncKt$crashLogger$1 extends Lambda implements Function1<Throwable, Unit> {
    public static final AsyncKt$crashLogger$1 INSTANCE = new AsyncKt$crashLogger$1();

    AsyncKt$crashLogger$1() {
        super(1);
    }

    public final void invoke(@NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "throwable");
        th.printStackTrace();
    }
}
