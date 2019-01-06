package org.jetbrains.anko.support.v4;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.AlertBuilder;
import org.jetbrains.anko.AndroidDialogsKt;
import org.jetbrains.anko.AndroidSelectorsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aO\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052!\b\n\u0010\u0007\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0001\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0002\b\nH\b¢\u0006\u0002\u0010\u000b\u001aJ\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\f0\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\r2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\r2!\b\n\u0010\u0007\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0001\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0002\b\nH\b\u001a4\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u001f\b\b\u0010\u0007\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\b\u001aG\u0010\u000e\u001a\u00020\u000f*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u001b\b\n\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0002\b\nH\b¢\u0006\u0002\u0010\u0010\u001aB\u0010\u000e\u001a\u00020\u000f*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\r2\u001b\b\n\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0002\b\nH\b\u001a\u0015\u0010\u0011\u001a\u00020\u0012*\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014H\b\u001a\u0015\u0010\u0011\u001a\u00020\u0012*\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0005H\b\u001aG\u0010\u0016\u001a\u00020\u000f*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u001b\b\n\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0002\b\nH\b¢\u0006\u0002\u0010\u0010\u001aB\u0010\u0016\u001a\u00020\u000f*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\r2\u001b\b\n\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0002\b\nH\b\u001aC\u0010\u0017\u001a\u00020\t*\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00142\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00140\u00192\u001a\b\b\u0010\u001a\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\u001bH\b\u001a\u0015\u0010\u001c\u001a\u00020\u0012*\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014H\b\u001a\u0015\u0010\u001c\u001a\u00020\u0012*\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0005H\b¨\u0006\u001d"}, d2 = {"alert", "Lorg/jetbrains/anko/AlertBuilder;", "Landroid/content/DialogInterface;", "Landroid/support/v4/app/Fragment;", "message", "", "title", "init", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "(Landroid/support/v4/app/Fragment;ILjava/lang/Integer;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/anko/AlertBuilder;", "Landroid/app/AlertDialog;", "", "indeterminateProgressDialog", "Landroid/app/ProgressDialog;", "(Landroid/support/v4/app/Fragment;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;)Landroid/app/ProgressDialog;", "longToast", "Landroid/widget/Toast;", "text", "", "textResource", "progressDialog", "selector", "items", "", "onClick", "Lkotlin/Function2;", "toast", "supportV4-base_release"}, k = 2, mv = {1, 1, 13})
/* compiled from: SupportDialogs.kt */
public final class SupportDialogsKt {
    @NotNull
    public static final Toast toast(@NotNull Fragment fragment, int i) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        fragment = Toast.makeText((Context) fragment, i, 0);
        fragment.show();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "Toast\n        .makeText(…         show()\n        }");
        return fragment;
    }

    @NotNull
    public static final Toast toast(@NotNull Fragment fragment, @NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charSequence, "text");
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        fragment = Toast.makeText((Context) fragment, charSequence, 0);
        fragment.show();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "Toast\n        .makeText(…         show()\n        }");
        return fragment;
    }

    @NotNull
    public static final Toast longToast(@NotNull Fragment fragment, int i) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        fragment = Toast.makeText((Context) fragment, i, 1);
        fragment.show();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "Toast\n        .makeText(…         show()\n        }");
        return fragment;
    }

    @NotNull
    public static final Toast longToast(@NotNull Fragment fragment, @NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charSequence, "text");
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        fragment = Toast.makeText((Context) fragment, charSequence, 1);
        fragment.show();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "Toast\n        .makeText(…         show()\n        }");
        return fragment;
    }

    public static /* synthetic */ void selector$default(Fragment fragment, CharSequence charSequence, List list, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            charSequence = null;
        }
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        Intrinsics.checkParameterIsNotNull(list, "items");
        Intrinsics.checkParameterIsNotNull(function2, "onClick");
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        AndroidSelectorsKt.selector((Context) fragment, charSequence, list, function2);
    }

    public static final void selector(@NotNull Fragment fragment, @Nullable CharSequence charSequence, @NotNull List<? extends CharSequence> list, @NotNull Function2<? super DialogInterface, ? super Integer, Unit> function2) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        Intrinsics.checkParameterIsNotNull(list, "items");
        Intrinsics.checkParameterIsNotNull(function2, "onClick");
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        AndroidSelectorsKt.selector((Context) fragment, charSequence, (List) list, (Function2) function2);
    }

    @NotNull
    public static /* synthetic */ AlertBuilder alert$default(Fragment fragment, String str, String str2, Function1 function1, int i, Object obj) {
        if ((i & 2) != null) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            function1 = null;
        }
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "message");
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        return AndroidDialogsKt.alert((Context) fragment, (CharSequence) str, (CharSequence) str2, function1);
    }

    @NotNull
    public static final AlertBuilder<AlertDialog> alert(@NotNull Fragment fragment, @NotNull String str, @Nullable String str2, @Nullable Function1<? super AlertBuilder<? extends DialogInterface>, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "message");
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        return AndroidDialogsKt.alert((Context) fragment, (CharSequence) str, (CharSequence) str2, (Function1) function1);
    }

    @NotNull
    public static /* synthetic */ AlertBuilder alert$default(Fragment fragment, int i, Integer num, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != null) {
            num = null;
        }
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        return AndroidDialogsKt.alert((Context) fragment, i, num, function1);
    }

    @NotNull
    public static final AlertBuilder<DialogInterface> alert(@NotNull Fragment fragment, int i, @Nullable Integer num, @Nullable Function1<? super AlertBuilder<? extends DialogInterface>, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        return AndroidDialogsKt.alert((Context) fragment, i, num, (Function1) function1);
    }

    @NotNull
    public static final AlertBuilder<DialogInterface> alert(@NotNull Fragment fragment, @NotNull Function1<? super AlertBuilder<? extends DialogInterface>, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        return AndroidDialogsKt.alert((Context) fragment, (Function1) function1);
    }

    @NotNull
    @Deprecated(message = "Android progress dialogs are deprecated")
    public static /* synthetic */ ProgressDialog progressDialog$default(Fragment fragment, String str, String str2, Function1 function1, int i, Object obj) {
        if ((i & 1) != null) {
            str = null;
        }
        if ((i & 2) != null) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            function1 = null;
        }
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        return AndroidDialogsKt.progressDialog((Context) fragment, (CharSequence) str, (CharSequence) str2, function1);
    }

    @NotNull
    @Deprecated(message = "Android progress dialogs are deprecated")
    public static final ProgressDialog progressDialog(@NotNull Fragment fragment, @Nullable String str, @Nullable String str2, @Nullable Function1<? super ProgressDialog, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        return AndroidDialogsKt.progressDialog((Context) fragment, (CharSequence) str, (CharSequence) str2, (Function1) function1);
    }

    @NotNull
    @Deprecated(message = "Android progress dialogs are deprecated")
    public static /* synthetic */ ProgressDialog indeterminateProgressDialog$default(Fragment fragment, String str, String str2, Function1 function1, int i, Object obj) {
        if ((i & 1) != null) {
            str = null;
        }
        if ((i & 2) != null) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            function1 = null;
        }
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        return AndroidDialogsKt.indeterminateProgressDialog((Context) fragment, (CharSequence) str, (CharSequence) str2, function1);
    }

    @NotNull
    @Deprecated(message = "Android progress dialogs are deprecated")
    public static final ProgressDialog indeterminateProgressDialog(@NotNull Fragment fragment, @Nullable String str, @Nullable String str2, @Nullable Function1<? super ProgressDialog, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        return AndroidDialogsKt.indeterminateProgressDialog((Context) fragment, (CharSequence) str, (CharSequence) str2, (Function1) function1);
    }

    @NotNull
    @Deprecated(message = "Android progress dialogs are deprecated")
    public static /* synthetic */ ProgressDialog progressDialog$default(Fragment fragment, Integer num, Integer num2, Function1 function1, int i, Object obj) {
        String str = null;
        if ((i & 1) != null) {
            num = null;
        }
        if ((i & 2) != null) {
            num2 = null;
        }
        if ((i & 4) != 0) {
            function1 = null;
        }
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        i = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(i, "requireActivity()");
        Context context = (Context) i;
        if (num != null) {
            num = fragment.requireActivity().getString(num.intValue());
        } else {
            num = null;
        }
        CharSequence charSequence = (CharSequence) num;
        if (num2 != null) {
            str = fragment.requireActivity().getString(num2.intValue());
        }
        return AndroidDialogsKt.progressDialog(context, charSequence, (CharSequence) str, function1);
    }

    @NotNull
    @Deprecated(message = "Android progress dialogs are deprecated")
    public static final ProgressDialog progressDialog(@NotNull Fragment fragment, @Nullable Integer num, @Nullable Integer num2, @Nullable Function1<? super ProgressDialog, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        FragmentActivity requireActivity = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        Context context = requireActivity;
        String str = null;
        if (num != null) {
            num = fragment.requireActivity().getString(num.intValue());
        } else {
            num = null;
        }
        CharSequence charSequence = (CharSequence) num;
        if (num2 != null) {
            str = fragment.requireActivity().getString(num2.intValue());
        }
        return AndroidDialogsKt.progressDialog(context, charSequence, (CharSequence) str, (Function1) function1);
    }

    @NotNull
    @Deprecated(message = "Android progress dialogs are deprecated")
    public static /* synthetic */ ProgressDialog indeterminateProgressDialog$default(Fragment fragment, Integer num, Integer num2, Function1 function1, int i, Object obj) {
        String str = null;
        if ((i & 1) != null) {
            num = null;
        }
        if ((i & 2) != null) {
            num2 = null;
        }
        if ((i & 4) != 0) {
            function1 = null;
        }
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        i = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(i, "requireActivity()");
        Context context = (Context) i;
        if (num != null) {
            num = fragment.requireActivity().getString(num.intValue());
        } else {
            num = null;
        }
        CharSequence charSequence = (CharSequence) num;
        if (num2 != null) {
            str = fragment.requireActivity().getString(num2.intValue());
        }
        return AndroidDialogsKt.indeterminateProgressDialog(context, charSequence, (CharSequence) str, function1);
    }

    @NotNull
    @Deprecated(message = "Android progress dialogs are deprecated")
    public static final ProgressDialog indeterminateProgressDialog(@NotNull Fragment fragment, @Nullable Integer num, @Nullable Integer num2, @Nullable Function1<? super ProgressDialog, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        FragmentActivity requireActivity = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        Context context = requireActivity;
        String str = null;
        if (num != null) {
            num = fragment.requireActivity().getString(num.intValue());
        } else {
            num = null;
        }
        CharSequence charSequence = (CharSequence) num;
        if (num2 != null) {
            str = fragment.requireActivity().getString(num2.intValue());
        }
        return AndroidDialogsKt.indeterminateProgressDialog(context, charSequence, (CharSequence) str, (Function1) function1);
    }
}
