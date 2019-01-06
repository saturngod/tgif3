package org.jetbrains.anko.appcompat.v7;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.widget.AutoCompleteTextView;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroid/widget/AutoCompleteTextView;", "ctx", "Landroid/content/Context;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: Views.kt */
/* renamed from: org.jetbrains.anko.appcompat.v7.$$Anko$Factories$AppcompatV7View$TINTED_AUTO_COMPLETE_TEXT_VIEW$1 */
final class C0342xa18a9233 extends Lambda implements Function1<Context, AutoCompleteTextView> {
    public static final C0342xa18a9233 INSTANCE = new C0342xa18a9233();

    C0342xa18a9233() {
        super(1);
    }

    @NotNull
    public final AutoCompleteTextView invoke(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "ctx");
        return VERSION.SDK_INT < 21 ? new AppCompatAutoCompleteTextView(context) : new AutoCompleteTextView(context);
    }
}
