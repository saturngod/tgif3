package org.jetbrains.anko;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.KeyEvent;
import android.view.View;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u0003J\r\u00102\u001a\u00028\u0000H&¢\u0006\u0002\u00103Ji\u00104\u001a\u000205\"\u0004\b\u0001\u001062\f\u00104\u001a\b\u0012\u0004\u0012\u0002H6072K\u00108\u001aG\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(<\u0012\u0013\u0012\u0011H6¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(=\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(>\u0012\u0004\u0012\u00020509H&JN\u00104\u001a\u0002052\f\u00104\u001a\b\u0012\u0004\u0012\u00020$0726\u00108\u001a2\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(<\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(>\u0012\u0004\u0012\u0002050?H&J5\u0010@\u001a\u0002052\b\b\u0001\u0010A\u001a\u00020\u00182!\u0010B\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(<\u0012\u0004\u0012\u0002050CH&J3\u0010@\u001a\u0002052\u0006\u0010D\u001a\u00020E2!\u0010B\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(<\u0012\u0004\u0012\u0002050CH&J5\u0010F\u001a\u0002052\b\b\u0001\u0010A\u001a\u00020\u00182!\u0010B\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(<\u0012\u0004\u0012\u0002050CH&J3\u0010F\u001a\u0002052\u0006\u0010D\u001a\u00020E2!\u0010B\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(<\u0012\u0004\u0012\u0002050CH&J+\u0010G\u001a\u0002052!\u0010H\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(<\u0012\u0004\u0012\u0002050CH&JU\u0010I\u001a\u0002052K\u0010H\u001aG\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(<\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(J\u0012\u0013\u0012\u00110K¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(L\u0012\u0004\u0012\u00020\u001f09H&J5\u0010M\u001a\u0002052\b\b\u0001\u0010A\u001a\u00020\u00182!\u0010B\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(<\u0012\u0004\u0012\u0002050CH&J3\u0010M\u001a\u0002052\u0006\u0010D\u001a\u00020E2!\u0010B\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(<\u0012\u0004\u0012\u0002050CH&J\r\u0010N\u001a\u00028\u0000H&¢\u0006\u0002\u00103R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\t8gX¦\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\t8gX¦\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR\u001a\u0010\u0011\u001a\u00020\u00128gX¦\u000e¢\u0006\f\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R&\u0010\u0019\u001a\u00020\u00182\b\b\u0001\u0010\u0017\u001a\u00020\u00188g@fX¦\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001f8gX¦\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020$8gX¦\u000e¢\u0006\f\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010)\u001a\u00020\u00188gX¦\u000e¢\u0006\f\u001a\u0004\b*\u0010\u001b\"\u0004\b+\u0010\u001dR\u001a\u0010,\u001a\u00020$8gX¦\u000e¢\u0006\f\u001a\u0004\b-\u0010&\"\u0004\b.\u0010(R\u001a\u0010/\u001a\u00020\u00188gX¦\u000e¢\u0006\f\u001a\u0004\b0\u0010\u001b\"\u0004\b1\u0010\u001d¨\u0006O"}, d2 = {"Lorg/jetbrains/anko/AlertBuilder;", "D", "Landroid/content/DialogInterface;", "", "ctx", "Landroid/content/Context;", "getCtx", "()Landroid/content/Context;", "customTitle", "Landroid/view/View;", "getCustomTitle", "()Landroid/view/View;", "setCustomTitle", "(Landroid/view/View;)V", "customView", "getCustomView", "setCustomView", "icon", "Landroid/graphics/drawable/Drawable;", "getIcon", "()Landroid/graphics/drawable/Drawable;", "setIcon", "(Landroid/graphics/drawable/Drawable;)V", "<set-?>", "", "iconResource", "getIconResource", "()I", "setIconResource", "(I)V", "isCancelable", "", "()Z", "setCancelable", "(Z)V", "message", "", "getMessage", "()Ljava/lang/CharSequence;", "setMessage", "(Ljava/lang/CharSequence;)V", "messageResource", "getMessageResource", "setMessageResource", "title", "getTitle", "setTitle", "titleResource", "getTitleResource", "setTitleResource", "build", "()Landroid/content/DialogInterface;", "items", "", "T", "", "onItemSelected", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "dialog", "item", "index", "Lkotlin/Function2;", "negativeButton", "buttonTextResource", "onClicked", "Lkotlin/Function1;", "buttonText", "", "neutralPressed", "onCancelled", "handler", "onKeyPressed", "keyCode", "Landroid/view/KeyEvent;", "e", "positiveButton", "show", "commons-base_release"}, k = 1, mv = {1, 1, 13})
@SuppressLint({"SupportAnnotationUsage"})
/* compiled from: AlertBuilder.kt */
public interface AlertBuilder<D extends DialogInterface> {
    @NotNull
    D build();

    @NotNull
    Context getCtx();

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "Property does not have a getter")
    View getCustomTitle();

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "Property does not have a getter")
    View getCustomView();

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "Property does not have a getter")
    Drawable getIcon();

    @Deprecated(level = DeprecationLevel.ERROR, message = "Property does not have a getter")
    int getIconResource();

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "Property does not have a getter")
    CharSequence getMessage();

    @Deprecated(level = DeprecationLevel.ERROR, message = "Property does not have a getter")
    int getMessageResource();

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "Property does not have a getter")
    CharSequence getTitle();

    @Deprecated(level = DeprecationLevel.ERROR, message = "Property does not have a getter")
    int getTitleResource();

    @Deprecated(level = DeprecationLevel.ERROR, message = "Property does not have a getter")
    boolean isCancelable();

    void items(@NotNull List<? extends CharSequence> list, @NotNull Function2<? super DialogInterface, ? super Integer, Unit> function2);

    <T> void items(@NotNull List<? extends T> list, @NotNull Function3<? super DialogInterface, ? super T, ? super Integer, Unit> function3);

    void negativeButton(@StringRes int i, @NotNull Function1<? super DialogInterface, Unit> function1);

    void negativeButton(@NotNull String str, @NotNull Function1<? super DialogInterface, Unit> function1);

    void neutralPressed(@StringRes int i, @NotNull Function1<? super DialogInterface, Unit> function1);

    void neutralPressed(@NotNull String str, @NotNull Function1<? super DialogInterface, Unit> function1);

    void onCancelled(@NotNull Function1<? super DialogInterface, Unit> function1);

    void onKeyPressed(@NotNull Function3<? super DialogInterface, ? super Integer, ? super KeyEvent, Boolean> function3);

    void positiveButton(@StringRes int i, @NotNull Function1<? super DialogInterface, Unit> function1);

    void positiveButton(@NotNull String str, @NotNull Function1<? super DialogInterface, Unit> function1);

    void setCancelable(boolean z);

    void setCustomTitle(@NotNull View view);

    void setCustomView(@NotNull View view);

    void setIcon(@NotNull Drawable drawable);

    void setIconResource(@DrawableRes int i);

    void setMessage(@NotNull CharSequence charSequence);

    void setMessageResource(int i);

    void setTitle(@NotNull CharSequence charSequence);

    void setTitleResource(int i);

    @NotNull
    D show();
}
