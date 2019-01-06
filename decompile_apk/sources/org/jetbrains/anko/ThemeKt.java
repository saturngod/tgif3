package org.jetbrains.anko;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources.Theme;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.Dimension;
import android.util.TypedValue;
import android.view.View;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00052\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u00060\u0006R\u00020\u00072\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\b2\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\b\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\t2\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\b\u001a\u001a\u0010\n\u001a\u00020\u0004*\u00060\u0006R\u00020\u00072\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0007\u001a\u0017\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0016\u0010\u000b\u001a\u00020\u0004*\u00020\u00052\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0007\u001a\u0017\u0010\u000b\u001a\u00020\u0004*\u00020\b2\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\b\u001a\u001b\u0010\u000b\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\t2\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0017\u0010\f\u001a\u00020\u0004*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0016\u0010\f\u001a\u00020\u0004*\u00020\u00052\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0007\u001a\u0017\u0010\f\u001a\u00020\u0004*\u00020\b2\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\b\u001a\u001b\u0010\f\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\t2\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\b¨\u0006\r"}, d2 = {"attr", "Landroid/util/TypedValue;", "Landroid/app/Fragment;", "attribute", "", "Landroid/content/Context;", "Landroid/content/res/Resources$Theme;", "Landroid/content/res/Resources;", "Landroid/view/View;", "Lorg/jetbrains/anko/AnkoContext;", "color", "colorAttr", "dimenAttr", "commons-base_release"}, k = 2, mv = {1, 1, 13})
/* compiled from: Theme.kt */
public final class ThemeKt {
    @NotNull
    public static final TypedValue attr(@NotNull Theme theme, @AttrRes int i) {
        Intrinsics.checkParameterIsNotNull(theme, "receiver$0");
        TypedValue typedValue = new TypedValue();
        if (theme.resolveAttribute(i, typedValue, true) != null) {
            return typedValue;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Failed to resolve attribute: ");
        stringBuilder.append(i);
        throw ((Throwable) new IllegalArgumentException(stringBuilder.toString()));
    }

    @ColorInt
    public static final int color(@NotNull Theme theme, @AttrRes int i) {
        Intrinsics.checkParameterIsNotNull(theme, "receiver$0");
        theme = attr(theme, i);
        if (theme.type >= 28 && theme.type <= 31) {
            return theme.data;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Attribute value type is not color: ");
        stringBuilder.append(i);
        throw ((Throwable) new IllegalArgumentException(stringBuilder.toString()));
    }

    @NotNull
    public static final TypedValue attr(@NotNull Context context, @AttrRes int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Theme theme = context.getTheme();
        Intrinsics.checkExpressionValueIsNotNull(theme, "theme");
        return attr(theme, i);
    }

    @Dimension(unit = 1)
    public static final int dimenAttr(@NotNull Context context, @AttrRes int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        i = attr(context, i).data;
        context = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(context, "resources");
        return TypedValue.complexToDimensionPixelSize(i, context.getDisplayMetrics());
    }

    @ColorInt
    public static final int colorAttr(@NotNull Context context, @AttrRes int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getTheme();
        Intrinsics.checkExpressionValueIsNotNull(context, "theme");
        return color(context, i);
    }

    @Dimension(unit = 1)
    public static final int dimenAttr(@NotNull AnkoContext<?> ankoContext, @AttrRes int i) {
        Intrinsics.checkParameterIsNotNull(ankoContext, "receiver$0");
        return dimenAttr(ankoContext.getCtx(), i);
    }

    @ColorInt
    public static final int colorAttr(@NotNull AnkoContext<?> ankoContext, @AttrRes int i) {
        Intrinsics.checkParameterIsNotNull(ankoContext, "receiver$0");
        return colorAttr(ankoContext.getCtx(), i);
    }

    @NotNull
    public static final TypedValue attr(@NotNull AnkoContext<?> ankoContext, @AttrRes int i) {
        Intrinsics.checkParameterIsNotNull(ankoContext, "receiver$0");
        return attr(ankoContext.getCtx(), i);
    }

    @Dimension(unit = 1)
    public static final int dimenAttr(@NotNull View view, @AttrRes int i) {
        Intrinsics.checkParameterIsNotNull(view, "receiver$0");
        Context context = view.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        return dimenAttr(context, i);
    }

    @ColorInt
    public static final int colorAttr(@NotNull View view, @AttrRes int i) {
        Intrinsics.checkParameterIsNotNull(view, "receiver$0");
        Context context = view.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        return colorAttr(context, i);
    }

    @NotNull
    public static final TypedValue attr(@NotNull View view, @AttrRes int i) {
        Intrinsics.checkParameterIsNotNull(view, "receiver$0");
        Context context = view.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        return attr(context, i);
    }

    @Dimension(unit = 1)
    @Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
    public static final int dimenAttr(@NotNull Fragment fragment, @AttrRes int i) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "activity");
        return dimenAttr((Context) fragment, i);
    }

    @ColorInt
    @Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
    public static final int colorAttr(@NotNull Fragment fragment, @AttrRes int i) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "activity");
        return colorAttr((Context) fragment, i);
    }

    @NotNull
    @Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
    public static final TypedValue attr(@NotNull Fragment fragment, @AttrRes int i) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        fragment = fragment.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "activity");
        return attr((Context) fragment, i);
    }
}
