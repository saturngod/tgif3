package org.jetbrains.anko.appcompat.v7;

import android.content.Context;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.ActionMenuView.LayoutParams;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J0\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u00032\b\u0010\t\u001a\u0004\u0018\u00010\nH\b¢\u0006\u0002\u0010\u000bJI\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u00032\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\b¢\u0006\u0002\u0010\u0011J&\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u000eH\b¢\u0006\u0002\u0010\u0013J?\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u000e2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\b¢\u0006\u0002\u0010\u0014J&\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0015H\b¢\u0006\u0002\u0010\u0016J?\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u00152\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\b¢\u0006\u0002\u0010\u0017J0\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u0019H\b¢\u0006\u0002\u0010\u001bJI\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00192\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\b¢\u0006\u0002\u0010\u001c¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/anko/appcompat/v7/_ActionMenuView;", "Landroid/support/v7/widget/ActionMenuView;", "ctx", "Landroid/content/Context;", "(Landroid/content/Context;)V", "lparams", "T", "Landroid/view/View;", "c", "attrs", "Landroid/util/AttributeSet;", "(Landroid/view/View;Landroid/content/Context;Landroid/util/AttributeSet;)Landroid/view/View;", "init", "Lkotlin/Function1;", "Landroid/support/v7/widget/ActionMenuView$LayoutParams;", "", "Lkotlin/ExtensionFunctionType;", "(Landroid/view/View;Landroid/content/Context;Landroid/util/AttributeSet;Lkotlin/jvm/functions/Function1;)Landroid/view/View;", "other", "(Landroid/view/View;Landroid/support/v7/widget/ActionMenuView$LayoutParams;)Landroid/view/View;", "(Landroid/view/View;Landroid/support/v7/widget/ActionMenuView$LayoutParams;Lkotlin/jvm/functions/Function1;)Landroid/view/View;", "Landroid/view/ViewGroup$LayoutParams;", "(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)Landroid/view/View;", "(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;Lkotlin/jvm/functions/Function1;)Landroid/view/View;", "width", "", "height", "(Landroid/view/View;II)Landroid/view/View;", "(Landroid/view/View;IILkotlin/jvm/functions/Function1;)Landroid/view/View;", "anko-appcompat-v7_release"}, k = 1, mv = {1, 1, 13})
/* compiled from: Layouts.kt */
public class _ActionMenuView extends ActionMenuView {
    public _ActionMenuView(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "ctx");
        super(context);
    }

    @NotNull
    public final <T extends View> T lparams(@NotNull T t, @Nullable Context context, @Nullable AttributeSet attributeSet, @NotNull Function1<? super LayoutParams, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(t, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        if (context == null) {
            Intrinsics.throwNpe();
        }
        if (attributeSet == null) {
            Intrinsics.throwNpe();
        }
        LayoutParams layoutParams = new LayoutParams(context, attributeSet);
        function1.invoke(layoutParams);
        t.setLayoutParams(layoutParams);
        return t;
    }

    @NotNull
    public final <T extends View> T lparams(@NotNull T t, @Nullable Context context, @Nullable AttributeSet attributeSet) {
        Intrinsics.checkParameterIsNotNull(t, "receiver$0");
        if (context == null) {
            Intrinsics.throwNpe();
        }
        if (attributeSet == null) {
            Intrinsics.throwNpe();
        }
        t.setLayoutParams(new LayoutParams(context, attributeSet));
        return t;
    }

    @NotNull
    public final <T extends View> T lparams(@NotNull T t, @Nullable ViewGroup.LayoutParams layoutParams, @NotNull Function1<? super LayoutParams, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(t, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        if (layoutParams == null) {
            Intrinsics.throwNpe();
        }
        LayoutParams layoutParams2 = new LayoutParams(layoutParams);
        function1.invoke(layoutParams2);
        t.setLayoutParams(layoutParams2);
        return t;
    }

    @NotNull
    public final <T extends View> T lparams(@NotNull T t, @Nullable ViewGroup.LayoutParams layoutParams) {
        Intrinsics.checkParameterIsNotNull(t, "receiver$0");
        if (layoutParams == null) {
            Intrinsics.throwNpe();
        }
        t.setLayoutParams(new LayoutParams(layoutParams));
        return t;
    }

    @NotNull
    public final <T extends View> T lparams(@NotNull T t, @Nullable LayoutParams layoutParams, @NotNull Function1<? super LayoutParams, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(t, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        if (layoutParams == null) {
            Intrinsics.throwNpe();
        }
        LayoutParams layoutParams2 = new LayoutParams(layoutParams);
        function1.invoke(layoutParams2);
        t.setLayoutParams(layoutParams2);
        return t;
    }

    @NotNull
    public final <T extends View> T lparams(@NotNull T t, @Nullable LayoutParams layoutParams) {
        Intrinsics.checkParameterIsNotNull(t, "receiver$0");
        if (layoutParams == null) {
            Intrinsics.throwNpe();
        }
        t.setLayoutParams(new LayoutParams(layoutParams));
        return t;
    }

    @NotNull
    public static /* synthetic */ View lparams$default(_ActionMenuView _actionmenuview, View view, int i, int i2, Function1 function1, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 1) != null) {
                i = -2;
            }
            if ((i3 & 2) != null) {
                i2 = -2;
            }
            Intrinsics.checkParameterIsNotNull(view, "receiver$0");
            Intrinsics.checkParameterIsNotNull(function1, "init");
            _actionmenuview = new LayoutParams(i, i2);
            function1.invoke(_actionmenuview);
            view.setLayoutParams((ViewGroup.LayoutParams) _actionmenuview);
            return view;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: lparams");
    }

    @NotNull
    public final <T extends View> T lparams(@NotNull T t, int i, int i2, @NotNull Function1<? super LayoutParams, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(t, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        LayoutParams layoutParams = new LayoutParams(i, i2);
        function1.invoke(layoutParams);
        t.setLayoutParams(layoutParams);
        return t;
    }

    @NotNull
    public static /* synthetic */ View lparams$default(_ActionMenuView _actionmenuview, View view, int i, int i2, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 1) != null) {
                i = -2;
            }
            if ((i3 & 2) != null) {
                i2 = -2;
            }
            Intrinsics.checkParameterIsNotNull(view, "receiver$0");
            view.setLayoutParams((ViewGroup.LayoutParams) new LayoutParams(i, i2));
            return view;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: lparams");
    }

    @NotNull
    public final <T extends View> T lparams(@NotNull T t, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(t, "receiver$0");
        t.setLayoutParams(new LayoutParams(i, i2));
        return t;
    }
}
