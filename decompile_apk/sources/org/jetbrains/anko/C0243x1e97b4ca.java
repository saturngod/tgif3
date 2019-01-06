package org.jetbrains.anko;

import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
/* compiled from: AndroidAlertBuilder.kt */
/* renamed from: org.jetbrains.anko.AndroidAlertBuilderKt$sam$android_content_DialogInterface_OnKeyListener$0 */
final class C0243x1e97b4ca implements OnKeyListener {
    private final /* synthetic */ Function3 function;

    C0243x1e97b4ca(Function3 function3) {
        this.function = function3;
    }

    public final /* synthetic */ boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Object invoke = this.function.invoke(dialogInterface, Integer.valueOf(i), keyEvent);
        Intrinsics.checkExpressionValueIsNotNull(invoke, "invoke(...)");
        return ((Boolean) invoke).booleanValue();
    }
}
