package org.jetbrains.anko;

import android.content.Context;
import android.os.Vibrator;
import android.view.LayoutInflater;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0016\u0010\u0005\u001a\u00020\u0006*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"layoutInflater", "Landroid/view/LayoutInflater;", "Landroid/content/Context;", "getLayoutInflater", "(Landroid/content/Context;)Landroid/view/LayoutInflater;", "vibrator", "Landroid/os/Vibrator;", "getVibrator", "(Landroid/content/Context;)Landroid/os/Vibrator;", "platform-base_release"}, k = 2, mv = {1, 1, 13})
/* compiled from: CustomServices.kt */
public final class CustomServicesKt {
    @NotNull
    public static final Vibrator getVibrator(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("vibrator");
        if (context != null) {
            return (Vibrator) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.os.Vibrator");
    }

    @NotNull
    public static final LayoutInflater getLayoutInflater(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("layout_inflater");
        if (context != null) {
            return (LayoutInflater) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }
}
