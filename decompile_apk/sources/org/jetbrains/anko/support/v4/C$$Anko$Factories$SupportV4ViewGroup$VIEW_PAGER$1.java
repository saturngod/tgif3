package org.jetbrains.anko.support.v4;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lorg/jetbrains/anko/support/v4/_ViewPager;", "ctx", "Landroid/content/Context;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: Views.kt */
/* renamed from: org.jetbrains.anko.support.v4.$$Anko$Factories$SupportV4ViewGroup$VIEW_PAGER$1 */
final class C$$Anko$Factories$SupportV4ViewGroup$VIEW_PAGER$1 extends Lambda implements Function1<Context, _ViewPager> {
    public static final C$$Anko$Factories$SupportV4ViewGroup$VIEW_PAGER$1 INSTANCE = new C$$Anko$Factories$SupportV4ViewGroup$VIEW_PAGER$1();

    C$$Anko$Factories$SupportV4ViewGroup$VIEW_PAGER$1() {
        super(1);
    }

    @NotNull
    public final _ViewPager invoke(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "ctx");
        return new _ViewPager(context);
    }
}
