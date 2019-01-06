package org.jetbrains.anko.support.v4;

import android.widget.TabHost.OnTabChangeListener;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
/* compiled from: Listeners.kt */
/* renamed from: org.jetbrains.anko.support.v4.SupportV4ListenersKt$sam$i$android_widget_TabHost_OnTabChangeListener$0 */
public final class C0264xc5e8a822 implements OnTabChangeListener {
    private final /* synthetic */ Function1 function;

    public C0264xc5e8a822(Function1 function1) {
        this.function = function1;
    }

    public final /* synthetic */ void onTabChanged(String str) {
        Intrinsics.checkExpressionValueIsNotNull(this.function.invoke(str), "invoke(...)");
    }
}
