package org.jetbrains.anko;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.internals.AnkoInternals;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\b\u001a=\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\t\u0012\u00070\u0001¢\u0006\u0002\b\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\b\u001a\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\f2\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\b\u001a=\u0010\u0000\u001a\u00020\u0001*\u00020\f2\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\t\u0012\u00070\u0001¢\u0006\u0002\b\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\b\u001a\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\b\u001a=\u0010\u0000\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\t\u0012\u00070\u0001¢\u0006\u0002\b\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\b\u001a\u0017\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\b\u001a5\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\t\u0012\u00070\u000f¢\u0006\u0002\b\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\b\u001a\u0017\u0010\u000e\u001a\u00020\u000f*\u00020\f2\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\b\u001a5\u0010\u000e\u001a\u00020\u000f*\u00020\f2\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\t\u0012\u00070\u000f¢\u0006\u0002\b\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\b\u001a\u0017\u0010\u000e\u001a\u00020\u000f*\u00020\r2\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\b\u001a5\u0010\u000e\u001a\u00020\u000f*\u00020\r2\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\t\u0012\u00070\u000f¢\u0006\u0002\b\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\b\u001a$\u0010\u0010\u001a\u0002H\u0011\"\b\b\u0000\u0010\u0011*\u00020\u0012*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0006H\b¢\u0006\u0002\u0010\u0014\u001aB\u0010\u0010\u001a\u0002H\u0011\"\b\b\u0000\u0010\u0011*\u00020\u0012*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\t\u0012\u0007H\u0011¢\u0006\u0002\b\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\b¢\u0006\u0002\u0010\u0015\u001a$\u0010\u0010\u001a\u0002H\u0011\"\b\b\u0000\u0010\u0011*\u00020\u0012*\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0006H\b¢\u0006\u0002\u0010\u0016\u001aB\u0010\u0010\u001a\u0002H\u0011\"\b\b\u0000\u0010\u0011*\u00020\u0012*\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\t\u0012\u0007H\u0011¢\u0006\u0002\b\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\b¢\u0006\u0002\u0010\u0017\u001a$\u0010\u0010\u001a\u0002H\u0011\"\b\b\u0000\u0010\u0011*\u00020\u0012*\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u0006H\b¢\u0006\u0002\u0010\u0019\u001aB\u0010\u0010\u001a\u0002H\u0011\"\b\b\u0000\u0010\u0011*\u00020\u0012*\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\t\u0012\u0007H\u0011¢\u0006\u0002\b\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\b¢\u0006\u0002\u0010\u001a\u001a$\u0010\u0010\u001a\u0002H\u0011\"\b\b\u0000\u0010\u0011*\u00020\u0012*\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0006H\b¢\u0006\u0002\u0010\u001b\u001aB\u0010\u0010\u001a\u0002H\u0011\"\b\b\u0000\u0010\u0011*\u00020\u0012*\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\t\u0012\u0007H\u0011¢\u0006\u0002\b\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\b¢\u0006\u0002\u0010\u001c\u001a\u0017\u0010\u001d\u001a\u00020\u001e*\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\b\u001a5\u0010\u001d\u001a\u00020\u001e*\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\t\u0012\u00070\u001f¢\u0006\u0002\b\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\b\u001a\u0017\u0010\u001d\u001a\u00020\u001e*\u00020\f2\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\b\u001a5\u0010\u001d\u001a\u00020\u001e*\u00020\f2\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\t\u0012\u00070\u001f¢\u0006\u0002\b\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\b\u001a\u0017\u0010\u001d\u001a\u00020\u001e*\u00020\r2\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\b\u001a5\u0010\u001d\u001a\u00020\u001e*\u00020\r2\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\t\u0012\u00070\u001f¢\u0006\u0002\b\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\b¨\u0006 "}, d2 = {"editText", "Landroid/widget/EditText;", "Landroid/app/Activity;", "constraints", "Lorg/jetbrains/anko/InputConstraints;", "theme", "", "init", "Lkotlin/Function1;", "Lorg/jetbrains/anko/AnkoViewDslMarker;", "", "Lkotlin/ExtensionFunctionType;", "Landroid/content/Context;", "Landroid/view/ViewManager;", "horizontalProgressBar", "Landroid/widget/ProgressBar;", "include", "T", "Landroid/view/View;", "layoutId", "(Landroid/app/Activity;I)Landroid/view/View;", "(Landroid/app/Activity;ILkotlin/jvm/functions/Function1;)Landroid/view/View;", "(Landroid/content/Context;I)Landroid/view/View;", "(Landroid/content/Context;ILkotlin/jvm/functions/Function1;)Landroid/view/View;", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;I)Landroid/view/View;", "(Landroid/view/ViewGroup;ILkotlin/jvm/functions/Function1;)Landroid/view/View;", "(Landroid/view/ViewManager;I)Landroid/view/View;", "(Landroid/view/ViewManager;ILkotlin/jvm/functions/Function1;)Landroid/view/View;", "verticalLayout", "Landroid/widget/LinearLayout;", "Lorg/jetbrains/anko/_LinearLayout;", "platform-base_release"}, k = 2, mv = {1, 1, 13})
/* compiled from: CustomViews.kt */
public final class CustomViewsKt {
    @NotNull
    public static /* synthetic */ LinearLayout verticalLayout$default(ViewManager viewManager, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getVERTICAL_LAYOUT_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        _LinearLayout _linearlayout = (_LinearLayout) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (LinearLayout) view;
    }

    @NotNull
    public static /* synthetic */ LinearLayout verticalLayout$default(ViewManager viewManager, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getVERTICAL_LAYOUT_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        function1.invoke(view);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (LinearLayout) view;
    }

    @NotNull
    public static final LinearLayout verticalLayout(@NotNull ViewManager viewManager, int i, @NotNull Function1<? super _LinearLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getVERTICAL_LAYOUT_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        function1.invoke(view);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (LinearLayout) view;
    }

    @NotNull
    public static /* synthetic */ LinearLayout verticalLayout$default(Context context, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getVERTICAL_LAYOUT_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        _LinearLayout _linearlayout = (_LinearLayout) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return (LinearLayout) view;
    }

    @NotNull
    public static /* synthetic */ LinearLayout verticalLayout$default(Context context, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getVERTICAL_LAYOUT_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        function1.invoke(view);
        AnkoInternals.INSTANCE.addView(context, view);
        return (LinearLayout) view;
    }

    @NotNull
    public static final LinearLayout verticalLayout(@NotNull Context context, int i, @NotNull Function1<? super _LinearLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getVERTICAL_LAYOUT_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        function1.invoke(view);
        AnkoInternals.INSTANCE.addView(context, view);
        return (LinearLayout) view;
    }

    @NotNull
    public static /* synthetic */ LinearLayout verticalLayout$default(Activity activity, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getVERTICAL_LAYOUT_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        _LinearLayout _linearlayout = (_LinearLayout) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return (LinearLayout) view;
    }

    @NotNull
    public static /* synthetic */ LinearLayout verticalLayout$default(Activity activity, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getVERTICAL_LAYOUT_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        function1.invoke(view);
        AnkoInternals.INSTANCE.addView(activity, view);
        return (LinearLayout) view;
    }

    @NotNull
    public static final LinearLayout verticalLayout(@NotNull Activity activity, int i, @NotNull Function1<? super _LinearLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getVERTICAL_LAYOUT_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        function1.invoke(view);
        AnkoInternals.INSTANCE.addView(activity, view);
        return (LinearLayout) view;
    }

    @NotNull
    public static /* synthetic */ EditText editText$default(ViewManager viewManager, InputConstraints inputConstraints, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(inputConstraints, "constraints");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getEDIT_TEXT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        EditText editText = (EditText) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        editText.setInputType(inputConstraints.getValue());
        return editText;
    }

    @NotNull
    public static /* synthetic */ EditText editText$default(ViewManager viewManager, InputConstraints inputConstraints, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(inputConstraints, "constraints");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getEDIT_TEXT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        function1.invoke(view);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        EditText editText = (EditText) view;
        editText.setInputType(inputConstraints.getValue());
        return editText;
    }

    @NotNull
    public static final EditText editText(@NotNull ViewManager viewManager, @NotNull InputConstraints inputConstraints, int i, @NotNull Function1<? super EditText, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(inputConstraints, "constraints");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getEDIT_TEXT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        function1.invoke(view);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        EditText editText = (EditText) view;
        editText.setInputType(inputConstraints.getValue());
        return editText;
    }

    @NotNull
    public static /* synthetic */ EditText editText$default(Context context, InputConstraints inputConstraints, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(inputConstraints, "constraints");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getEDIT_TEXT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        EditText editText = (EditText) view;
        AnkoInternals.INSTANCE.addView(context, view);
        editText.setInputType(inputConstraints.getValue());
        return editText;
    }

    @NotNull
    public static /* synthetic */ EditText editText$default(Context context, InputConstraints inputConstraints, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(inputConstraints, "constraints");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getEDIT_TEXT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        function1.invoke(view);
        AnkoInternals.INSTANCE.addView(context, view);
        EditText editText = (EditText) view;
        editText.setInputType(inputConstraints.getValue());
        return editText;
    }

    @NotNull
    public static final EditText editText(@NotNull Context context, @NotNull InputConstraints inputConstraints, int i, @NotNull Function1<? super EditText, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(inputConstraints, "constraints");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getEDIT_TEXT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        function1.invoke(view);
        AnkoInternals.INSTANCE.addView(context, view);
        EditText editText = (EditText) view;
        editText.setInputType(inputConstraints.getValue());
        return editText;
    }

    @NotNull
    public static /* synthetic */ EditText editText$default(Activity activity, InputConstraints inputConstraints, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(inputConstraints, "constraints");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getEDIT_TEXT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        EditText editText = (EditText) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        editText.setInputType(inputConstraints.getValue());
        return editText;
    }

    @NotNull
    public static /* synthetic */ EditText editText$default(Activity activity, InputConstraints inputConstraints, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(inputConstraints, "constraints");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getEDIT_TEXT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        function1.invoke(view);
        AnkoInternals.INSTANCE.addView(activity, view);
        EditText editText = (EditText) view;
        editText.setInputType(inputConstraints.getValue());
        return editText;
    }

    @NotNull
    public static final EditText editText(@NotNull Activity activity, @NotNull InputConstraints inputConstraints, int i, @NotNull Function1<? super EditText, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(inputConstraints, "constraints");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getEDIT_TEXT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        function1.invoke(view);
        AnkoInternals.INSTANCE.addView(activity, view);
        EditText editText = (EditText) view;
        editText.setInputType(inputConstraints.getValue());
        return editText;
    }

    @NotNull
    public static /* synthetic */ ProgressBar horizontalProgressBar$default(ViewManager viewManager, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getHORIZONTAL_PROGRESS_BAR_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        ProgressBar progressBar = (ProgressBar) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return progressBar;
    }

    @NotNull
    public static /* synthetic */ ProgressBar horizontalProgressBar$default(ViewManager viewManager, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getHORIZONTAL_PROGRESS_BAR_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        function1.invoke(view);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (ProgressBar) view;
    }

    @NotNull
    public static final ProgressBar horizontalProgressBar(@NotNull ViewManager viewManager, int i, @NotNull Function1<? super ProgressBar, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getHORIZONTAL_PROGRESS_BAR_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        function1.invoke(view);
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (ProgressBar) view;
    }

    @NotNull
    public static /* synthetic */ ProgressBar horizontalProgressBar$default(Context context, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getHORIZONTAL_PROGRESS_BAR_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        ProgressBar progressBar = (ProgressBar) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return progressBar;
    }

    @NotNull
    public static /* synthetic */ ProgressBar horizontalProgressBar$default(Context context, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getHORIZONTAL_PROGRESS_BAR_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        function1.invoke(view);
        AnkoInternals.INSTANCE.addView(context, view);
        return (ProgressBar) view;
    }

    @NotNull
    public static final ProgressBar horizontalProgressBar(@NotNull Context context, int i, @NotNull Function1<? super ProgressBar, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getHORIZONTAL_PROGRESS_BAR_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        function1.invoke(view);
        AnkoInternals.INSTANCE.addView(context, view);
        return (ProgressBar) view;
    }

    @NotNull
    public static /* synthetic */ ProgressBar horizontalProgressBar$default(Activity activity, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getHORIZONTAL_PROGRESS_BAR_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        ProgressBar progressBar = (ProgressBar) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return progressBar;
    }

    @NotNull
    public static /* synthetic */ ProgressBar horizontalProgressBar$default(Activity activity, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getHORIZONTAL_PROGRESS_BAR_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        function1.invoke(view);
        AnkoInternals.INSTANCE.addView(activity, view);
        return (ProgressBar) view;
    }

    @NotNull
    public static final ProgressBar horizontalProgressBar(@NotNull Activity activity, int i, @NotNull Function1<? super ProgressBar, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getHORIZONTAL_PROGRESS_BAR_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        function1.invoke(view);
        AnkoInternals.INSTANCE.addView(activity, view);
        return (ProgressBar) view;
    }

    @NotNull
    public static final <T extends View> T include(@NotNull ViewGroup viewGroup, int i, @NotNull Function1<? super T, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        ViewManager viewManager = viewGroup;
        Object systemService = AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0).getSystemService("layout_inflater");
        if (systemService != null) {
            View inflate = ((LayoutInflater) systemService).inflate(i, viewGroup, false);
            if (inflate != null) {
                function1.invoke(inflate);
                AnkoInternals.INSTANCE.addView(viewManager, inflate);
                return inflate;
            }
            throw new TypeCastException("null cannot be cast to non-null type T");
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    @NotNull
    public static final LinearLayout verticalLayout(@NotNull ViewManager viewManager, int i) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getVERTICAL_LAYOUT_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        _LinearLayout _linearlayout = (_LinearLayout) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return (LinearLayout) view;
    }

    @NotNull
    public static final LinearLayout verticalLayout(@NotNull Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getVERTICAL_LAYOUT_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        _LinearLayout _linearlayout = (_LinearLayout) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return (LinearLayout) view;
    }

    @NotNull
    public static final LinearLayout verticalLayout(@NotNull Activity activity, int i) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getVERTICAL_LAYOUT_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        _LinearLayout _linearlayout = (_LinearLayout) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return (LinearLayout) view;
    }

    @NotNull
    public static final EditText editText(@NotNull ViewManager viewManager, @NotNull InputConstraints inputConstraints, int i) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(inputConstraints, "constraints");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getEDIT_TEXT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        EditText editText = (EditText) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        editText.setInputType(inputConstraints.getValue());
        return editText;
    }

    @NotNull
    public static final EditText editText(@NotNull Context context, @NotNull InputConstraints inputConstraints, int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(inputConstraints, "constraints");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getEDIT_TEXT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        EditText editText = (EditText) view;
        AnkoInternals.INSTANCE.addView(context, view);
        editText.setInputType(inputConstraints.getValue());
        return editText;
    }

    @NotNull
    public static final EditText editText(@NotNull Activity activity, @NotNull InputConstraints inputConstraints, int i) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(inputConstraints, "constraints");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getEDIT_TEXT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        EditText editText = (EditText) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        editText.setInputType(inputConstraints.getValue());
        return editText;
    }

    @NotNull
    public static final ProgressBar horizontalProgressBar(@NotNull ViewManager viewManager, int i) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getHORIZONTAL_PROGRESS_BAR_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        ProgressBar progressBar = (ProgressBar) view;
        AnkoInternals.INSTANCE.addView(viewManager, view);
        return progressBar;
    }

    @NotNull
    public static final ProgressBar horizontalProgressBar(@NotNull Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getHORIZONTAL_PROGRESS_BAR_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        ProgressBar progressBar = (ProgressBar) view;
        AnkoInternals.INSTANCE.addView(context, view);
        return progressBar;
    }

    @NotNull
    public static final ProgressBar horizontalProgressBar(@NotNull Activity activity, int i) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View view = (View) C$$Anko$Factories$CustomViews.INSTANCE.getHORIZONTAL_PROGRESS_BAR_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        ProgressBar progressBar = (ProgressBar) view;
        AnkoInternals.INSTANCE.addView(activity, view);
        return progressBar;
    }

    @NotNull
    public static final <T extends View> T include(@NotNull ViewManager viewManager, int i) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Object systemService = AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0).getSystemService("layout_inflater");
        if (systemService != null) {
            View inflate = ((LayoutInflater) systemService).inflate(i, null);
            if (inflate != 0) {
                AnkoInternals.INSTANCE.addView(viewManager, inflate);
                return inflate;
            }
            throw new TypeCastException("null cannot be cast to non-null type T");
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    @NotNull
    public static final <T extends View> T include(@NotNull ViewManager viewManager, int i, @NotNull Function1<? super T, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        Object systemService = AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0).getSystemService("layout_inflater");
        if (systemService != null) {
            View inflate = ((LayoutInflater) systemService).inflate(i, null);
            if (inflate != 0) {
                function1.invoke(inflate);
                AnkoInternals.INSTANCE.addView(viewManager, inflate);
                return inflate;
            }
            throw new TypeCastException("null cannot be cast to non-null type T");
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    @NotNull
    public static final <T extends View> T include(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "receiver$0");
        ViewManager viewManager = viewGroup;
        Object systemService = AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0).getSystemService("layout_inflater");
        if (systemService != null) {
            View inflate = ((LayoutInflater) systemService).inflate(i, viewGroup, false);
            if (inflate != null) {
                AnkoInternals.INSTANCE.addView(viewManager, inflate);
                return inflate;
            }
            throw new TypeCastException("null cannot be cast to non-null type T");
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    @NotNull
    public static final <T extends View> T include(@NotNull Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Object systemService = AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0).getSystemService("layout_inflater");
        if (systemService != null) {
            View inflate = ((LayoutInflater) systemService).inflate(i, null);
            if (inflate != 0) {
                AnkoInternals.INSTANCE.addView(context, inflate);
                return inflate;
            }
            throw new TypeCastException("null cannot be cast to non-null type T");
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    @NotNull
    public static final <T extends View> T include(@NotNull Context context, int i, @NotNull Function1<? super T, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        Object systemService = AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0).getSystemService("layout_inflater");
        if (systemService != null) {
            View inflate = ((LayoutInflater) systemService).inflate(i, null);
            if (inflate != 0) {
                function1.invoke(inflate);
                AnkoInternals.INSTANCE.addView(context, inflate);
                return inflate;
            }
            throw new TypeCastException("null cannot be cast to non-null type T");
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    @NotNull
    public static final <T extends View> T include(@NotNull Activity activity, int i) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Object systemService = AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0).getSystemService("layout_inflater");
        if (systemService != null) {
            View inflate = ((LayoutInflater) systemService).inflate(i, null);
            if (inflate != 0) {
                AnkoInternals.INSTANCE.addView(activity, inflate);
                return inflate;
            }
            throw new TypeCastException("null cannot be cast to non-null type T");
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    @NotNull
    public static final <T extends View> T include(@NotNull Activity activity, int i, @NotNull Function1<? super T, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        Object systemService = AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0).getSystemService("layout_inflater");
        if (systemService != null) {
            View inflate = ((LayoutInflater) systemService).inflate(i, null);
            if (inflate != 0) {
                function1.invoke(inflate);
                AnkoInternals.INSTANCE.addView(activity, inflate);
                return inflate;
            }
            throw new TypeCastException("null cannot be cast to non-null type T");
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }
}
