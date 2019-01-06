package org.jetbrains.anko;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.AnkoContext.DefaultImpls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001c\u0010\u0013\u001a\u00020\u00142\b\u0010\u0010\u001a\u0004\u0018\u00010\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0014H\u0014J\u0018\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\fH\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\u00028\u0000X\u0004¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\f8VX\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/anko/AnkoContextImpl;", "T", "Lorg/jetbrains/anko/AnkoContext;", "ctx", "Landroid/content/Context;", "owner", "setContentView", "", "(Landroid/content/Context;Ljava/lang/Object;Z)V", "getCtx", "()Landroid/content/Context;", "myView", "Landroid/view/View;", "getOwner", "()Ljava/lang/Object;", "Ljava/lang/Object;", "view", "getView", "()Landroid/view/View;", "addView", "", "params", "Landroid/view/ViewGroup$LayoutParams;", "alreadyHasView", "doAddView", "context", "commons-base_release"}, k = 1, mv = {1, 1, 13})
/* compiled from: AnkoContext.kt */
public class AnkoContextImpl<T> implements AnkoContext<T> {
    @NotNull
    private final Context ctx;
    private View myView;
    private final T owner;
    private final boolean setContentView;

    public AnkoContextImpl(@NotNull Context context, T t, boolean z) {
        Intrinsics.checkParameterIsNotNull(context, "ctx");
        this.ctx = context;
        this.owner = t;
        this.setContentView = z;
    }

    public void removeView(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        DefaultImpls.removeView(this, view);
    }

    public void updateViewLayout(@NotNull View view, @NotNull LayoutParams layoutParams) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(layoutParams, "params");
        DefaultImpls.updateViewLayout(this, view, layoutParams);
    }

    @NotNull
    public Context getCtx() {
        return this.ctx;
    }

    public T getOwner() {
        return this.owner;
    }

    @NotNull
    public View getView() {
        View view = this.myView;
        if (view != null) {
            return view;
        }
        throw new IllegalStateException("View was not set previously");
    }

    public void addView(@Nullable View view, @Nullable LayoutParams layoutParams) {
        if (view != null) {
            if (this.myView != null) {
                alreadyHasView();
            }
            this.myView = view;
            if (this.setContentView != null) {
                doAddView(getCtx(), view);
            }
        }
    }

    private final void doAddView(Context context, View view) {
        if (context instanceof Activity) {
            ((Activity) context).setContentView(view);
        } else if (context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context.baseContext");
            doAddView(context, view);
        } else {
            throw ((Throwable) new IllegalStateException("Context is not an Activity, can't set content view"));
        }
    }

    protected void alreadyHasView() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("View is already set: ");
        stringBuilder.append(this.myView);
        throw new IllegalStateException(stringBuilder.toString());
    }
}
