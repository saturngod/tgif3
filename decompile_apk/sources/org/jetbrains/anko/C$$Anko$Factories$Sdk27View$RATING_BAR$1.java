package org.jetbrains.anko;

import android.content.Context;
import android.widget.RatingBar;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroid/widget/RatingBar;", "ctx", "Landroid/content/Context;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: Views.kt */
/* renamed from: org.jetbrains.anko.$$Anko$Factories$Sdk27View$RATING_BAR$1 */
final class C$$Anko$Factories$Sdk27View$RATING_BAR$1 extends Lambda implements Function1<Context, RatingBar> {
    public static final C$$Anko$Factories$Sdk27View$RATING_BAR$1 INSTANCE = new C$$Anko$Factories$Sdk27View$RATING_BAR$1();

    C$$Anko$Factories$Sdk27View$RATING_BAR$1() {
        super(1);
    }

    @NotNull
    public final RatingBar invoke(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "ctx");
        return new RatingBar(context);
    }
}
