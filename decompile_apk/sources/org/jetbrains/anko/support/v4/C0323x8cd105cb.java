package org.jetbrains.anko.support.v4;

import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
/* compiled from: Listeners.kt */
/* renamed from: org.jetbrains.anko.support.v4.SupportV4ListenersKt$sam$i$android_support_v4_widget_SwipeRefreshLayout_OnRefreshListener$0 */
public final class C0323x8cd105cb implements OnRefreshListener {
    private final /* synthetic */ Function0 function;

    public C0323x8cd105cb(Function0 function0) {
        this.function = function0;
    }

    public final /* synthetic */ void onRefresh() {
        Intrinsics.checkExpressionValueIsNotNull(this.function.invoke(), "invoke(...)");
    }
}
