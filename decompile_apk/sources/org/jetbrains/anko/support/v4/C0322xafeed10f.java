package org.jetbrains.anko.support.v4;

import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.NestedScrollView.OnScrollChangeListener;
import kotlin.Metadata;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
/* compiled from: Listeners.kt */
/* renamed from: org.jetbrains.anko.support.v4.SupportV4ListenersKt$sam$i$android_support_v4_widget_NestedScrollView_OnScrollChangeListener$0 */
public final class C0322xafeed10f implements OnScrollChangeListener {
    private final /* synthetic */ Function5 function;

    public C0322xafeed10f(Function5 function5) {
        this.function = function5;
    }

    public final /* synthetic */ void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
        Intrinsics.checkExpressionValueIsNotNull(this.function.invoke(nestedScrollView, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)), "invoke(...)");
    }
}
