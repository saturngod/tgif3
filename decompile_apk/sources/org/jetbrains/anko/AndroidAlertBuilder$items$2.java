package org.jetbrains.anko;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "dialog", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "which", "", "onClick"}, k = 3, mv = {1, 1, 13})
/* compiled from: AndroidAlertBuilder.kt */
final class AndroidAlertBuilder$items$2 implements OnClickListener {
    final /* synthetic */ Function2 $onItemSelected;

    AndroidAlertBuilder$items$2(Function2 function2) {
        this.$onItemSelected = function2;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        Function2 function2 = this.$onItemSelected;
        Intrinsics.checkExpressionValueIsNotNull(dialogInterface, "dialog");
        function2.invoke(dialogInterface, Integer.valueOf(i));
    }
}
