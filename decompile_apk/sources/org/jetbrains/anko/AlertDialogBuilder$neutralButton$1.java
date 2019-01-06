package org.jetbrains.anko;

import android.content.DialogInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Landroid/content/DialogInterface;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: AlertDialogBuilder.kt */
final class AlertDialogBuilder$neutralButton$1 extends Lambda implements Function1<DialogInterface, Unit> {
    public static final AlertDialogBuilder$neutralButton$1 INSTANCE = new AlertDialogBuilder$neutralButton$1();

    AlertDialogBuilder$neutralButton$1() {
        super(1);
    }

    public final void invoke(@NotNull DialogInterface dialogInterface) {
        Intrinsics.checkParameterIsNotNull(dialogInterface, "receiver$0");
        dialogInterface.dismiss();
    }
}
