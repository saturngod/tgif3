package org.jetbrains.anko;

import android.view.View;
import android.view.ViewGroup;
import java.util.NoSuchElementException;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0002\u001a\u0012\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0002H\u0007\u001a!\u0010\u0004\u001a\u00020\u0002*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u0007H\b\u001a#\u0010\t\u001a\u0004\u0018\u00010\u0002*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u0007H\b\u001a!\u0010\n\u001a\u00020\u000b*\u00020\u00052\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\u0007H\b\u001a'\u0010\r\u001a\u00020\u000b*\u00020\u00052\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\u000eH\b¨\u0006\u0010"}, d2 = {"childrenRecursiveSequence", "Lkotlin/sequences/Sequence;", "Landroid/view/View;", "childrenSequence", "firstChild", "Landroid/view/ViewGroup;", "predicate", "Lkotlin/Function1;", "", "firstChildOrNull", "forEachChild", "", "action", "forEachChildWithIndex", "Lkotlin/Function2;", "", "commons-base_release"}, k = 2, mv = {1, 1, 13})
/* compiled from: viewChildrenSequences.kt */
public final class ViewChildrenSequencesKt {
    @Deprecated(message = "Use the Android KTX version", replaceWith = @ReplaceWith(expression = "forEach(action)", imports = {"androidx.core.view.forEach"}))
    public static final void forEachChild(@NotNull ViewGroup viewGroup, @NotNull Function1<? super View, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        int childCount = viewGroup.getChildCount() - 1;
        if (childCount >= 0) {
            int i = 0;
            while (true) {
                View childAt = viewGroup.getChildAt(i);
                Intrinsics.checkExpressionValueIsNotNull(childAt, "getChildAt(i)");
                function1.invoke(childAt);
                if (i != childCount) {
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    @Deprecated(message = "Use the Android KTX version", replaceWith = @ReplaceWith(expression = "forEachIndexed(action)", imports = {"androidx.core.view.forEachIndexed"}))
    public static final void forEachChildWithIndex(@NotNull ViewGroup viewGroup, @NotNull Function2<? super Integer, ? super View, Unit> function2) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function2, "action");
        int childCount = viewGroup.getChildCount() - 1;
        if (childCount >= 0) {
            int i = 0;
            while (true) {
                Integer valueOf = Integer.valueOf(i);
                View childAt = viewGroup.getChildAt(i);
                Intrinsics.checkExpressionValueIsNotNull(childAt, "getChildAt(i)");
                function2.invoke(valueOf, childAt);
                if (i != childCount) {
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    @Nullable
    public static final View firstChildOrNull(@NotNull ViewGroup viewGroup, @NotNull Function1<? super View, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        int childCount = viewGroup.getChildCount() - 1;
        if (childCount >= 0) {
            int i = 0;
            while (true) {
                View childAt = viewGroup.getChildAt(i);
                Intrinsics.checkExpressionValueIsNotNull(childAt, "child");
                if (!((Boolean) function1.invoke(childAt)).booleanValue()) {
                    if (i == childCount) {
                        break;
                    }
                    i++;
                } else {
                    return childAt;
                }
            }
        }
        return null;
    }

    @NotNull
    @Deprecated(message = "Use the Android KTX version", replaceWith = @ReplaceWith(expression = "children", imports = {"androidx.core.view.children"}))
    public static final Sequence<View> childrenSequence(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "receiver$0");
        return new ViewChildrenSequence(view);
    }

    @NotNull
    public static final Sequence<View> childrenRecursiveSequence(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "receiver$0");
        return new ViewChildrenRecursiveSequence(view);
    }

    @NotNull
    public static final View firstChild(@NotNull ViewGroup viewGroup, @NotNull Function1<? super View, Boolean> function1) {
        View childAt;
        Intrinsics.checkParameterIsNotNull(viewGroup, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        int childCount = viewGroup.getChildCount() - 1;
        if (childCount >= 0) {
            int i = 0;
            while (true) {
                childAt = viewGroup.getChildAt(i);
                Intrinsics.checkExpressionValueIsNotNull(childAt, "child");
                if (!((Boolean) function1.invoke(childAt)).booleanValue()) {
                    if (i == childCount) {
                        break;
                    }
                    i++;
                } else {
                    break;
                }
            }
        }
        childAt = null;
        if (childAt != null) {
            return childAt;
        }
        throw ((Throwable) new NoSuchElementException("No element matching predicate was found."));
    }
}
