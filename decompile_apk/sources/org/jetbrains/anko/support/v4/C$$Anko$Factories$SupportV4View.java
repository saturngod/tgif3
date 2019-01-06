package org.jetbrains.anko.support.v4;

import android.content.Context;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.Space;
import android.support.v4.widget.SwipeRefreshLayout;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u001d\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00100\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\bR\u001d\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00130\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\b¨\u0006\u0015"}, d2 = {"org/jetbrains/anko/support/v4/$$Anko$Factories$SupportV4View", "", "()V", "CONTENT_LOADING_PROGRESS_BAR", "Lkotlin/Function1;", "Landroid/content/Context;", "Landroid/support/v4/widget/ContentLoadingProgressBar;", "getCONTENT_LOADING_PROGRESS_BAR", "()Lkotlin/jvm/functions/Function1;", "PAGER_TAB_STRIP", "Landroid/support/v4/view/PagerTabStrip;", "getPAGER_TAB_STRIP", "PAGER_TITLE_STRIP", "Landroid/support/v4/view/PagerTitleStrip;", "getPAGER_TITLE_STRIP", "SPACE", "Landroid/support/v4/widget/Space;", "getSPACE", "SWIPE_REFRESH_LAYOUT", "Landroid/support/v4/widget/SwipeRefreshLayout;", "getSWIPE_REFRESH_LAYOUT", "anko-support-v4_release"}, k = 1, mv = {1, 1, 13})
@PublishedApi
/* compiled from: Views.kt */
/* renamed from: org.jetbrains.anko.support.v4.$$Anko$Factories$SupportV4View */
public final class C$$Anko$Factories$SupportV4View {
    @NotNull
    private static final Function1<Context, ContentLoadingProgressBar> CONTENT_LOADING_PROGRESS_BAR = C$$Anko$Factories$SupportV4View$CONTENT_LOADING_PROGRESS_BAR$1.INSTANCE;
    public static final C$$Anko$Factories$SupportV4View INSTANCE = new C$$Anko$Factories$SupportV4View();
    @NotNull
    private static final Function1<Context, PagerTabStrip> PAGER_TAB_STRIP = C$$Anko$Factories$SupportV4View$PAGER_TAB_STRIP$1.INSTANCE;
    @NotNull
    private static final Function1<Context, PagerTitleStrip> PAGER_TITLE_STRIP = C$$Anko$Factories$SupportV4View$PAGER_TITLE_STRIP$1.INSTANCE;
    @NotNull
    private static final Function1<Context, Space> SPACE = C$$Anko$Factories$SupportV4View$SPACE$1.INSTANCE;
    @NotNull
    private static final Function1<Context, SwipeRefreshLayout> SWIPE_REFRESH_LAYOUT = C$$Anko$Factories$SupportV4View$SWIPE_REFRESH_LAYOUT$1.INSTANCE;

    private C$$Anko$Factories$SupportV4View() {
    }

    @NotNull
    public final Function1<Context, PagerTabStrip> getPAGER_TAB_STRIP() {
        return PAGER_TAB_STRIP;
    }

    @NotNull
    public final Function1<Context, PagerTitleStrip> getPAGER_TITLE_STRIP() {
        return PAGER_TITLE_STRIP;
    }

    @NotNull
    public final Function1<Context, ContentLoadingProgressBar> getCONTENT_LOADING_PROGRESS_BAR() {
        return CONTENT_LOADING_PROGRESS_BAR;
    }

    @NotNull
    public final Function1<Context, Space> getSPACE() {
        return SPACE;
    }

    @NotNull
    public final Function1<Context, SwipeRefreshLayout> getSWIPE_REFRESH_LAYOUT() {
        return SWIPE_REFRESH_LAYOUT;
    }
}
