package org.jetbrains.anko;

import android.app.Fragment;
import android.content.Context;
import android.widget.Toast;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0005H\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0005H\b\u001a\u0019\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0019\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u0003\u001a\u00020\u0005H\b\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0005H\b\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0005H\b\u001a\u0019\u0010\b\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0019\u0010\b\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u0003\u001a\u00020\u0005H\b¨\u0006\t"}, d2 = {"longToast", "Landroid/widget/Toast;", "Landroid/app/Fragment;", "message", "", "", "Landroid/content/Context;", "Lorg/jetbrains/anko/AnkoContext;", "toast", "commons-base_release"}, k = 2, mv = {1, 1, 13})
/* compiled from: Toasts.kt */
public final class ToastsKt {
    @NotNull
    public static final Toast toast(@NotNull AnkoContext<?> ankoContext, int i) {
        Intrinsics.checkParameterIsNotNull(ankoContext, "receiver$0");
        ankoContext = Toast.makeText(ankoContext.getCtx(), i, 0);
        ankoContext.show();
        Intrinsics.checkExpressionValueIsNotNull(ankoContext, "Toast\n        .makeText(…         show()\n        }");
        return ankoContext;
    }

    @NotNull
    @Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
    public static final Toast toast(@NotNull Fragment fragment, int i) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "activity");
        fragment = Toast.makeText((Context) fragment, i, 0);
        fragment.show();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "Toast\n        .makeText(…         show()\n        }");
        return fragment;
    }

    @NotNull
    public static final Toast toast(@NotNull Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = Toast.makeText(context, i, 0);
        context.show();
        Intrinsics.checkExpressionValueIsNotNull(context, "Toast\n        .makeText(…         show()\n        }");
        return context;
    }

    @NotNull
    public static final Toast toast(@NotNull AnkoContext<?> ankoContext, @NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(ankoContext, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charSequence, "message");
        ankoContext = Toast.makeText(ankoContext.getCtx(), charSequence, 0);
        ankoContext.show();
        Intrinsics.checkExpressionValueIsNotNull(ankoContext, "Toast\n        .makeText(…         show()\n        }");
        return ankoContext;
    }

    @NotNull
    @Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
    public static final Toast toast(@NotNull Fragment fragment, @NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charSequence, "message");
        fragment = fragment.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "activity");
        fragment = Toast.makeText((Context) fragment, charSequence, 0);
        fragment.show();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "Toast\n        .makeText(…         show()\n        }");
        return fragment;
    }

    @NotNull
    public static final Toast toast(@NotNull Context context, @NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charSequence, "message");
        context = Toast.makeText(context, charSequence, 0);
        context.show();
        Intrinsics.checkExpressionValueIsNotNull(context, "Toast\n        .makeText(…         show()\n        }");
        return context;
    }

    @NotNull
    public static final Toast longToast(@NotNull AnkoContext<?> ankoContext, int i) {
        Intrinsics.checkParameterIsNotNull(ankoContext, "receiver$0");
        ankoContext = Toast.makeText(ankoContext.getCtx(), i, 1);
        ankoContext.show();
        Intrinsics.checkExpressionValueIsNotNull(ankoContext, "Toast\n        .makeText(…         show()\n        }");
        return ankoContext;
    }

    @NotNull
    @Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
    public static final Toast longToast(@NotNull Fragment fragment, int i) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "activity");
        fragment = Toast.makeText((Context) fragment, i, 1);
        fragment.show();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "Toast\n        .makeText(…         show()\n        }");
        return fragment;
    }

    @NotNull
    public static final Toast longToast(@NotNull Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = Toast.makeText(context, i, 1);
        context.show();
        Intrinsics.checkExpressionValueIsNotNull(context, "Toast\n        .makeText(…         show()\n        }");
        return context;
    }

    @NotNull
    public static final Toast longToast(@NotNull AnkoContext<?> ankoContext, @NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(ankoContext, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charSequence, "message");
        ankoContext = Toast.makeText(ankoContext.getCtx(), charSequence, 1);
        ankoContext.show();
        Intrinsics.checkExpressionValueIsNotNull(ankoContext, "Toast\n        .makeText(…         show()\n        }");
        return ankoContext;
    }

    @NotNull
    @Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
    public static final Toast longToast(@NotNull Fragment fragment, @NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charSequence, "message");
        fragment = fragment.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "activity");
        fragment = Toast.makeText((Context) fragment, charSequence, 1);
        fragment.show();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "Toast\n        .makeText(…         show()\n        }");
        return fragment;
    }

    @NotNull
    public static final Toast longToast(@NotNull Context context, @NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charSequence, "message");
        context = Toast.makeText(context, charSequence, 1);
        context.show();
        Intrinsics.checkExpressionValueIsNotNull(context, "Toast\n        .makeText(…         show()\n        }");
        return context;
    }
}
