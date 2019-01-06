package org.jetbrains.anko;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewManager;
import android.widget.ListAdapter;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.internals.AnkoInternals;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0013\b\u0016\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J9\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182!\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00140\u001aJ1\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u001f2!\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00140\u001aJ!\u0010 \u001a\u00020\u00142\u0019\b\u0002\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\"J\u0010\u0010#\u001a\u00020\u00142\b\b\u0002\u0010#\u001a\u00020$J\b\u0010%\u001a\u00020\u0014H\u0002J\u000e\u0010&\u001a\u00020\u00142\u0006\u0010'\u001a\u00020(J\u001f\u0010&\u001a\u00020\u00142\u0017\u0010)\u001a\u0013\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\"J\u000e\u0010+\u001a\u00020\u00142\u0006\u0010'\u001a\u00020(J\u001f\u0010+\u001a\u00020\u00142\u0017\u0010)\u001a\u0013\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\"J\u0006\u0010,\u001a\u00020\u0014J\u000e\u0010-\u001a\u00020\u00142\u0006\u0010-\u001a\u00020.J\u000e\u0010-\u001a\u00020\u00142\u0006\u0010-\u001a\u00020\u001bJ<\u0010/\u001a\u00020\u00142\f\u0010/\u001a\b\u0012\u0004\u0012\u000201002!\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\u00102J1\u0010/\u001a\u00020\u00142\u0006\u00103\u001a\u00020\u001b2!\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00140\u001aJ7\u0010/\u001a\u00020\u00142\f\u0010/\u001a\b\u0012\u0004\u0012\u000201042!\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00140\u001aJ\u000e\u00105\u001a\u00020\u00142\u0006\u00105\u001a\u000201J\u000e\u00105\u001a\u00020\u00142\u0006\u00105\u001a\u00020\u001bJ)\u00106\u001a\u00020\u00142\u0006\u00107\u001a\u0002012\u0019\b\u0002\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\"J)\u00106\u001a\u00020\u00142\u0006\u00107\u001a\u00020\u001b2\u0019\b\u0002\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\"J)\u00108\u001a\u00020\u00142\u0006\u00109\u001a\u0002012\u0019\b\u0002\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\"J+\u00108\u001a\u00020\u00142\b\b\u0002\u00109\u001a\u00020\u001b2\u0019\b\u0002\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\"J!\u0010:\u001a\u00020\u00142\u0019\b\u0002\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\"J\u001f\u0010;\u001a\u00020\u00142\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\"J\u0014\u0010<\u001a\u00020\u00142\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00140=J>\u0010>\u001a\u00020\u001426\u0010\u0019\u001a2\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(@\u0012\u0013\u0012\u00110A¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(B\u0012\u0004\u0012\u00020$0?J'\u0010C\u001a\u00020\u00142\u0006\u0010D\u001a\u0002012\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\"J'\u0010C\u001a\u00020\u00142\u0006\u0010D\u001a\u00020\u001b2\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\"J\u0006\u0010E\u001a\u00020\u0000J\u000e\u0010F\u001a\u00020\u00142\u0006\u0010F\u001a\u000201J\u000e\u0010F\u001a\u00020\u00142\u0006\u0010F\u001a\u00020\u001bJ\u001f\u0010G\u001a\u00020\u00142\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\"R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR(\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\f\u001a\u0004\u0018\u00010\r@BX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006H"}, d2 = {"Lorg/jetbrains/anko/AlertDialogBuilder;", "", "ankoContext", "Lorg/jetbrains/anko/AnkoContext;", "(Lorg/jetbrains/anko/AnkoContext;)V", "ctx", "Landroid/content/Context;", "(Landroid/content/Context;)V", "builder", "Landroid/app/AlertDialog$Builder;", "getCtx", "()Landroid/content/Context;", "<set-?>", "Landroid/app/AlertDialog;", "dialog", "getDialog", "()Landroid/app/AlertDialog;", "setDialog", "(Landroid/app/AlertDialog;)V", "adapter", "", "cursor", "Landroid/database/Cursor;", "labelColumn", "", "callback", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "which", "Landroid/widget/ListAdapter;", "cancelButton", "Landroid/content/DialogInterface;", "Lkotlin/ExtensionFunctionType;", "cancellable", "", "checkBuilder", "customTitle", "view", "Landroid/view/View;", "dsl", "Landroid/view/ViewManager;", "customView", "dismiss", "icon", "Landroid/graphics/drawable/Drawable;", "items", "", "", "([Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)V", "itemsId", "", "message", "negativeButton", "negativeText", "neutralButton", "neutralText", "noButton", "okButton", "onCancel", "Lkotlin/Function0;", "onKey", "Lkotlin/Function2;", "keyCode", "Landroid/view/KeyEvent;", "e", "positiveButton", "positiveText", "show", "title", "yesButton", "commons-base_release"}, k = 1, mv = {1, 1, 13})
@Deprecated(message = "Use AlertBuilder class instead.")
/* compiled from: AlertDialogBuilder.kt */
public final class AlertDialogBuilder {
    private Builder builder;
    @NotNull
    private final Context ctx;
    @Nullable
    private AlertDialog dialog;

    public AlertDialogBuilder(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "ctx");
        this.ctx = context;
        this.builder = new Builder(this.ctx);
    }

    @NotNull
    public final Context getCtx() {
        return this.ctx;
    }

    private final void setDialog(AlertDialog alertDialog) {
        this.dialog = alertDialog;
    }

    @Nullable
    public final AlertDialog getDialog() {
        return this.dialog;
    }

    public AlertDialogBuilder(@NotNull AnkoContext<?> ankoContext) {
        Intrinsics.checkParameterIsNotNull(ankoContext, "ankoContext");
        this(ankoContext.getCtx());
    }

    public final void dismiss() {
        AlertDialog alertDialog = this.dialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    private final void checkBuilder() {
        if (this.builder == null) {
            throw new IllegalStateException("show() was already called for this AlertDialogBuilder");
        }
    }

    @NotNull
    public final AlertDialogBuilder show() {
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        this.dialog = builder.create();
        this.builder = (Builder) null;
        AlertDialog alertDialog = this.dialog;
        if (alertDialog == null) {
            Intrinsics.throwNpe();
        }
        alertDialog.show();
        return this;
    }

    public final void title(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "title");
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setTitle(charSequence);
    }

    public final void title(int i) {
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setTitle(i);
    }

    public final void message(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "message");
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setMessage(charSequence);
    }

    public final void message(int i) {
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setMessage(i);
    }

    public final void icon(int i) {
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setIcon(i);
    }

    public final void icon(@NotNull Drawable drawable) {
        Intrinsics.checkParameterIsNotNull(drawable, "icon");
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setIcon(drawable);
    }

    public final void customTitle(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setCustomTitle(view);
    }

    public final void customTitle(@NotNull Function1<? super ViewManager, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "dsl");
        checkBuilder();
        Context context = this.ctx;
        AnkoInternals ankoInternals = AnkoInternals.INSTANCE;
        AnkoContextImpl ankoContextImpl = new AnkoContextImpl(context, context, false);
        function1.invoke(ankoContextImpl);
        function1 = ankoContextImpl.getView();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setCustomTitle(function1);
    }

    public final void customView(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setView(view);
    }

    public final void customView(@NotNull Function1<? super ViewManager, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "dsl");
        checkBuilder();
        Context context = this.ctx;
        AnkoInternals ankoInternals = AnkoInternals.INSTANCE;
        AnkoContextImpl ankoContextImpl = new AnkoContextImpl(context, context, false);
        function1.invoke(ankoContextImpl);
        function1 = ankoContextImpl.getView();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setView(function1);
    }

    public static /* synthetic */ void cancellable$default(AlertDialogBuilder alertDialogBuilder, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        alertDialogBuilder.cancellable(z);
    }

    public final void cancellable(boolean z) {
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setCancelable(z);
    }

    public final void onCancel(@NotNull Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(function0, "callback");
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setOnCancelListener(new AlertDialogBuilder$onCancel$1(function0));
    }

    public final void onKey(@NotNull Function2<? super Integer, ? super KeyEvent, Boolean> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "callback");
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setOnKeyListener(new AlertDialogBuilder$onKey$1(function2));
    }

    public static /* synthetic */ void neutralButton$default(AlertDialogBuilder alertDialogBuilder, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != null) {
            i = 17039370;
        }
        if ((i2 & 2) != 0) {
            function1 = AlertDialogBuilder$neutralButton$1.INSTANCE;
        }
        alertDialogBuilder.neutralButton(i, function1);
    }

    public final void neutralButton(int i, @NotNull Function1<? super DialogInterface, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "callback");
        i = this.ctx.getString(i);
        Intrinsics.checkExpressionValueIsNotNull(i, "ctx.getString(neutralText)");
        neutralButton((CharSequence) i, (Function1) function1);
    }

    public static /* synthetic */ void neutralButton$default(AlertDialogBuilder alertDialogBuilder, CharSequence charSequence, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = AlertDialogBuilder$neutralButton$2.INSTANCE;
        }
        alertDialogBuilder.neutralButton(charSequence, function1);
    }

    public final void neutralButton(@NotNull CharSequence charSequence, @NotNull Function1<? super DialogInterface, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "neutralText");
        Intrinsics.checkParameterIsNotNull(function1, "callback");
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setNeutralButton(charSequence, new AlertDialogBuilder$neutralButton$3(function1));
    }

    public final void positiveButton(int i, @NotNull Function1<? super DialogInterface, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "callback");
        i = this.ctx.getString(i);
        Intrinsics.checkExpressionValueIsNotNull(i, "ctx.getString(positiveText)");
        positiveButton((CharSequence) i, (Function1) function1);
    }

    public final void okButton(@NotNull Function1<? super DialogInterface, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "callback");
        String string = this.ctx.getString(17039370);
        Intrinsics.checkExpressionValueIsNotNull(string, "ctx.getString(R.string.ok)");
        positiveButton((CharSequence) string, (Function1) function1);
    }

    public final void yesButton(@NotNull Function1<? super DialogInterface, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "callback");
        String string = this.ctx.getString(17039379);
        Intrinsics.checkExpressionValueIsNotNull(string, "ctx.getString(R.string.yes)");
        positiveButton((CharSequence) string, (Function1) function1);
    }

    public final void positiveButton(@NotNull CharSequence charSequence, @NotNull Function1<? super DialogInterface, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "positiveText");
        Intrinsics.checkParameterIsNotNull(function1, "callback");
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setPositiveButton(charSequence, new AlertDialogBuilder$positiveButton$1(function1));
    }

    public static /* synthetic */ void negativeButton$default(AlertDialogBuilder alertDialogBuilder, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            function1 = AlertDialogBuilder$negativeButton$1.INSTANCE;
        }
        alertDialogBuilder.negativeButton(i, function1);
    }

    public final void negativeButton(int i, @NotNull Function1<? super DialogInterface, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "callback");
        i = this.ctx.getString(i);
        Intrinsics.checkExpressionValueIsNotNull(i, "ctx.getString(negativeText)");
        negativeButton((CharSequence) i, (Function1) function1);
    }

    public static /* synthetic */ void cancelButton$default(AlertDialogBuilder alertDialogBuilder, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = AlertDialogBuilder$cancelButton$1.INSTANCE;
        }
        alertDialogBuilder.cancelButton(function1);
    }

    public final void cancelButton(@NotNull Function1<? super DialogInterface, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "callback");
        String string = this.ctx.getString(17039360);
        Intrinsics.checkExpressionValueIsNotNull(string, "ctx.getString(R.string.cancel)");
        negativeButton((CharSequence) string, (Function1) function1);
    }

    public static /* synthetic */ void noButton$default(AlertDialogBuilder alertDialogBuilder, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = AlertDialogBuilder$noButton$1.INSTANCE;
        }
        alertDialogBuilder.noButton(function1);
    }

    public final void noButton(@NotNull Function1<? super DialogInterface, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "callback");
        String string = this.ctx.getString(17039369);
        Intrinsics.checkExpressionValueIsNotNull(string, "ctx.getString(R.string.no)");
        negativeButton((CharSequence) string, (Function1) function1);
    }

    public static /* synthetic */ void negativeButton$default(AlertDialogBuilder alertDialogBuilder, CharSequence charSequence, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = AlertDialogBuilder$negativeButton$2.INSTANCE;
        }
        alertDialogBuilder.negativeButton(charSequence, function1);
    }

    public final void negativeButton(@NotNull CharSequence charSequence, @NotNull Function1<? super DialogInterface, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "negativeText");
        Intrinsics.checkParameterIsNotNull(function1, "callback");
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setNegativeButton(charSequence, new AlertDialogBuilder$negativeButton$3(function1));
    }

    public final void items(int i, @NotNull Function1<? super Integer, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "callback");
        Resources resources = this.ctx.getResources();
        if (resources == null) {
            Intrinsics.throwNpe();
        }
        CharSequence[] textArray = resources.getTextArray(i);
        Intrinsics.checkExpressionValueIsNotNull(textArray, "ctx.resources!!.getTextArray(itemsId)");
        items(textArray, (Function1) function1);
    }

    public final void items(@NotNull List<? extends CharSequence> list, @NotNull Function1<? super Integer, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(list, "items");
        Intrinsics.checkParameterIsNotNull(function1, "callback");
        list = list.toArray(new CharSequence[0]);
        if (list != null) {
            items((CharSequence[]) list, (Function1) function1);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public final void items(@NotNull CharSequence[] charSequenceArr, @NotNull Function1<? super Integer, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(charSequenceArr, "items");
        Intrinsics.checkParameterIsNotNull(function1, "callback");
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setItems(charSequenceArr, new AlertDialogBuilder$items$1(function1));
    }

    public final void adapter(@NotNull ListAdapter listAdapter, @NotNull Function1<? super Integer, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(listAdapter, "adapter");
        Intrinsics.checkParameterIsNotNull(function1, "callback");
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setAdapter(listAdapter, new AlertDialogBuilder$adapter$1(function1));
    }

    public final void adapter(@NotNull Cursor cursor, @NotNull String str, @NotNull Function1<? super Integer, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(cursor, "cursor");
        Intrinsics.checkParameterIsNotNull(str, "labelColumn");
        Intrinsics.checkParameterIsNotNull(function1, "callback");
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setCursor(cursor, new AlertDialogBuilder$adapter$2(function1), str);
    }
}
