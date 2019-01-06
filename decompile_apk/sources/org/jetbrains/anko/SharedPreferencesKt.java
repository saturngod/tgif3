package org.jetbrains.anko;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0002\b\u0006H\b\u001a&\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0002\b\u0006H\b¨\u0006\b"}, d2 = {"apply", "", "Landroid/content/SharedPreferences;", "modifier", "Lkotlin/Function1;", "Landroid/content/SharedPreferences$Editor;", "Lkotlin/ExtensionFunctionType;", "commit", "commons-base_release"}, k = 2, mv = {1, 1, 13})
/* compiled from: SharedPreferences.kt */
public final class SharedPreferencesKt {
    @Deprecated(message = "Use the Android KTX version", replaceWith = @ReplaceWith(expression = "edit(modifier)", imports = {"androidx.core.content.edit"}))
    public static final void apply(@NotNull SharedPreferences sharedPreferences, @NotNull Function1<? super Editor, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "modifier");
        sharedPreferences = sharedPreferences.edit();
        Intrinsics.checkExpressionValueIsNotNull(sharedPreferences, "editor");
        function1.invoke(sharedPreferences);
        sharedPreferences.apply();
    }

    @Deprecated(message = "Use the Android KTX version", replaceWith = @ReplaceWith(expression = "edit(true, modifier)", imports = {"androidx.core.content.edit"}))
    public static final void commit(@NotNull SharedPreferences sharedPreferences, @NotNull Function1<? super Editor, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "modifier");
        sharedPreferences = sharedPreferences.edit();
        Intrinsics.checkExpressionValueIsNotNull(sharedPreferences, "editor");
        function1.invoke(sharedPreferences);
        sharedPreferences.commit();
    }
}
