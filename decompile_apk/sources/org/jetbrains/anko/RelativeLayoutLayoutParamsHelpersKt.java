package org.jetbrains.anko;

import android.support.annotation.IdRes;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u001a\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\b\u001a\u0017\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\b\u001a\r\u0010\b\u001a\u00020\u0001*\u00020\u0002H\b\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\u0002H\b\u001a\r\u0010\n\u001a\u00020\u0001*\u00020\u0002H\b\u001a\r\u0010\u000b\u001a\u00020\u0001*\u00020\u0002H\b\u001a\r\u0010\f\u001a\u00020\u0001*\u00020\u0002H\b\u001a\r\u0010\r\u001a\u00020\u0001*\u00020\u0002H\b\u001a\u0017\u0010\u000e\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\b\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0017\u0010\u000f\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\b\u001a\u0012\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0017\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\b\u001a\u0012\u0010\u0011\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0017\u0010\u0011\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\b\u001a\r\u0010\u0012\u001a\u00020\u0001*\u00020\u0002H\b\u001a\r\u0010\u0013\u001a\u00020\u0001*\u00020\u0002H\b\u001a\r\u0010\u0014\u001a\u00020\u0001*\u00020\u0002H\b\u001a\u0015\u0010\u0015\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0017\u0010\u0015\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\b\u001a\u0015\u0010\u0016\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0017\u0010\u0016\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\b\u001a\u0015\u0010\u0017\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0017\u0010\u0017\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\b\u001a\u0015\u0010\u0018\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0017\u0010\u0018\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\b\u001a\u0015\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0017\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\b\u001a\u0015\u0010\u001a\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0017\u0010\u001a\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\b\u001a\u0015\u0010\u001b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0017\u0010\u001b\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\b\u001a\u0015\u0010\u001c\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0017\u0010\u001c\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\b\u001a\u0015\u0010\u001d\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0017\u0010\u001d\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\b\u001a\u0015\u0010\u001e\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0017\u0010\u001e\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\b\u001a\u0012\u0010\u001f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0017\u0010\u001f\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\b¨\u0006 "}, d2 = {"above", "", "Landroid/widget/RelativeLayout$LayoutParams;", "view", "Landroid/view/View;", "id", "", "alignEnd", "alignParentBottom", "alignParentEnd", "alignParentLeft", "alignParentRight", "alignParentStart", "alignParentTop", "alignStart", "baselineOf", "below", "bottomOf", "centerHorizontally", "centerInParent", "centerVertically", "endOf", "leftOf", "rightOf", "sameBottom", "sameEnd", "sameLeft", "sameRight", "sameStart", "sameTop", "startOf", "topOf", "commons-base_release"}, k = 2, mv = {1, 1, 13})
/* compiled from: RelativeLayoutLayoutParamsHelpers.kt */
public final class RelativeLayoutLayoutParamsHelpersKt {
    public static final void topOf(@NotNull LayoutParams layoutParams, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            layoutParams.addRule(2, id);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id is not set for ");
        stringBuilder.append(view);
        throw ((Throwable) new AnkoException(stringBuilder.toString()));
    }

    public static final void above(@NotNull LayoutParams layoutParams, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            layoutParams.addRule(2, id);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id is not set for ");
        stringBuilder.append(view);
        throw ((Throwable) new AnkoException(stringBuilder.toString()));
    }

    public static final void bottomOf(@NotNull LayoutParams layoutParams, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            layoutParams.addRule(3, id);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id is not set for ");
        stringBuilder.append(view);
        throw ((Throwable) new AnkoException(stringBuilder.toString()));
    }

    public static final void below(@NotNull LayoutParams layoutParams, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            layoutParams.addRule(3, id);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id is not set for ");
        stringBuilder.append(view);
        throw ((Throwable) new AnkoException(stringBuilder.toString()));
    }

    public static final void leftOf(@NotNull LayoutParams layoutParams, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            layoutParams.addRule(null, id);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id is not set for ");
        stringBuilder.append(view);
        throw ((Throwable) new AnkoException(stringBuilder.toString()));
    }

    @RequiresApi(17)
    public static final void startOf(@NotNull LayoutParams layoutParams, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            layoutParams.addRule(16, id);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id is not set for ");
        stringBuilder.append(view);
        throw ((Throwable) new AnkoException(stringBuilder.toString()));
    }

    public static final void rightOf(@NotNull LayoutParams layoutParams, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            layoutParams.addRule(1, id);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id is not set for ");
        stringBuilder.append(view);
        throw ((Throwable) new AnkoException(stringBuilder.toString()));
    }

    @RequiresApi(17)
    public static final void endOf(@NotNull LayoutParams layoutParams, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            layoutParams.addRule(17, id);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id is not set for ");
        stringBuilder.append(view);
        throw ((Throwable) new AnkoException(stringBuilder.toString()));
    }

    public static final void sameLeft(@NotNull LayoutParams layoutParams, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            layoutParams.addRule(5, id);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id is not set for ");
        stringBuilder.append(view);
        throw ((Throwable) new AnkoException(stringBuilder.toString()));
    }

    @RequiresApi(17)
    public static final void sameStart(@NotNull LayoutParams layoutParams, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            layoutParams.addRule(18, id);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id is not set for ");
        stringBuilder.append(view);
        throw ((Throwable) new AnkoException(stringBuilder.toString()));
    }

    public static final void sameTop(@NotNull LayoutParams layoutParams, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            layoutParams.addRule(6, id);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id is not set for ");
        stringBuilder.append(view);
        throw ((Throwable) new AnkoException(stringBuilder.toString()));
    }

    public static final void sameRight(@NotNull LayoutParams layoutParams, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            layoutParams.addRule(7, id);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id is not set for ");
        stringBuilder.append(view);
        throw ((Throwable) new AnkoException(stringBuilder.toString()));
    }

    @RequiresApi(17)
    public static final void sameEnd(@NotNull LayoutParams layoutParams, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            layoutParams.addRule(19, id);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id is not set for ");
        stringBuilder.append(view);
        throw ((Throwable) new AnkoException(stringBuilder.toString()));
    }

    public static final void sameBottom(@NotNull LayoutParams layoutParams, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            layoutParams.addRule(8, id);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id is not set for ");
        stringBuilder.append(view);
        throw ((Throwable) new AnkoException(stringBuilder.toString()));
    }

    public static final void topOf(@NotNull LayoutParams layoutParams, @IdRes int i) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        layoutParams.addRule(2, i);
    }

    public static final void above(@NotNull LayoutParams layoutParams, @IdRes int i) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        layoutParams.addRule(2, i);
    }

    public static final void below(@NotNull LayoutParams layoutParams, @IdRes int i) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        layoutParams.addRule(3, i);
    }

    public static final void bottomOf(@NotNull LayoutParams layoutParams, @IdRes int i) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        layoutParams.addRule(3, i);
    }

    public static final void leftOf(@NotNull LayoutParams layoutParams, @IdRes int i) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        layoutParams.addRule(0, i);
    }

    @RequiresApi(17)
    public static final void startOf(@NotNull LayoutParams layoutParams, @IdRes int i) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        layoutParams.addRule(16, i);
    }

    public static final void rightOf(@NotNull LayoutParams layoutParams, @IdRes int i) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        layoutParams.addRule(1, i);
    }

    @RequiresApi(17)
    public static final void endOf(@NotNull LayoutParams layoutParams, @IdRes int i) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        layoutParams.addRule(17, i);
    }

    public static final void sameLeft(@NotNull LayoutParams layoutParams, @IdRes int i) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        layoutParams.addRule(5, i);
    }

    @RequiresApi(17)
    public static final void sameStart(@NotNull LayoutParams layoutParams, @IdRes int i) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        layoutParams.addRule(18, i);
    }

    public static final void sameTop(@NotNull LayoutParams layoutParams, @IdRes int i) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        layoutParams.addRule(6, i);
    }

    public static final void sameRight(@NotNull LayoutParams layoutParams, @IdRes int i) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        layoutParams.addRule(7, i);
    }

    @RequiresApi(17)
    public static final void sameEnd(@NotNull LayoutParams layoutParams, @IdRes int i) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        layoutParams.addRule(19, i);
    }

    public static final void sameBottom(@NotNull LayoutParams layoutParams, @IdRes int i) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        layoutParams.addRule(8, i);
    }

    @RequiresApi(17)
    public static final void alignStart(@NotNull LayoutParams layoutParams, @IdRes int i) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        layoutParams.addRule(18, i);
    }

    @RequiresApi(17)
    public static final void alignEnd(@NotNull LayoutParams layoutParams, @IdRes int i) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        layoutParams.addRule(19, i);
    }

    public static final void alignParentTop(@NotNull LayoutParams layoutParams) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        layoutParams.addRule(10);
    }

    public static final void alignParentRight(@NotNull LayoutParams layoutParams) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        layoutParams.addRule(11);
    }

    public static final void alignParentBottom(@NotNull LayoutParams layoutParams) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        layoutParams.addRule(12);
    }

    public static final void alignParentLeft(@NotNull LayoutParams layoutParams) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        layoutParams.addRule(9);
    }

    public static final void centerHorizontally(@NotNull LayoutParams layoutParams) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        layoutParams.addRule(14);
    }

    public static final void centerVertically(@NotNull LayoutParams layoutParams) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        layoutParams.addRule(15);
    }

    public static final void centerInParent(@NotNull LayoutParams layoutParams) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        layoutParams.addRule(13);
    }

    @RequiresApi(17)
    public static final void alignParentStart(@NotNull LayoutParams layoutParams) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        layoutParams.addRule(20);
    }

    @RequiresApi(17)
    public static final void alignParentEnd(@NotNull LayoutParams layoutParams) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        layoutParams.addRule(21);
    }

    public static final void baselineOf(@NotNull LayoutParams layoutParams, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            layoutParams.addRule(4, id);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id is not set for ");
        stringBuilder.append(view);
        throw ((Throwable) new AnkoException(stringBuilder.toString()));
    }

    public static final void baselineOf(@NotNull LayoutParams layoutParams, @IdRes int i) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "receiver$0");
        layoutParams.addRule(4, i);
    }
}
