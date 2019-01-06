package org.jetbrains.anko.appcompat.v7;

import android.support.v7.widget.Toolbar;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.internals.AnkoInternals;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\"(\u0010\u0002\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018G@FX\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\"(\u0010\b\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018G@FX\u000e¢\u0006\f\u001a\u0004\b\t\u0010\u0005\"\u0004\b\n\u0010\u0007\"(\u0010\u000b\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018G@FX\u000e¢\u0006\f\u001a\u0004\b\f\u0010\u0005\"\u0004\b\r\u0010\u0007\"(\u0010\u000e\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018G@FX\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0005\"\u0004\b\u0010\u0010\u0007\"(\u0010\u0011\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018G@FX\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0005\"\u0004\b\u0013\u0010\u0007\"(\u0010\u0014\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018G@FX\u000e¢\u0006\f\u001a\u0004\b\u0015\u0010\u0005\"\u0004\b\u0016\u0010\u0007¨\u0006\u0017"}, d2 = {"v", "", "logoDescriptionResource", "Landroid/support/v7/widget/Toolbar;", "getLogoDescriptionResource", "(Landroid/support/v7/widget/Toolbar;)I", "setLogoDescriptionResource", "(Landroid/support/v7/widget/Toolbar;I)V", "logoResource", "getLogoResource", "setLogoResource", "navigationContentDescriptionResource", "getNavigationContentDescriptionResource", "setNavigationContentDescriptionResource", "navigationIconResource", "getNavigationIconResource", "setNavigationIconResource", "subtitleResource", "getSubtitleResource", "setSubtitleResource", "titleResource", "getTitleResource", "setTitleResource", "anko-appcompat-v7_release"}, k = 2, mv = {1, 1, 13})
@JvmName(name = "AppcompatV7PropertiesKt")
/* compiled from: Properties.kt */
public final class AppcompatV7PropertiesKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Property does not have a getter")
    public static final int getLogoResource(@NotNull Toolbar toolbar) {
        Intrinsics.checkParameterIsNotNull(toolbar, "receiver$0");
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    public static final void setLogoResource(@NotNull Toolbar toolbar, int i) {
        Intrinsics.checkParameterIsNotNull(toolbar, "receiver$0");
        toolbar.setLogo(i);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Property does not have a getter")
    public static final int getLogoDescriptionResource(@NotNull Toolbar toolbar) {
        Intrinsics.checkParameterIsNotNull(toolbar, "receiver$0");
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    public static final void setLogoDescriptionResource(@NotNull Toolbar toolbar, int i) {
        Intrinsics.checkParameterIsNotNull(toolbar, "receiver$0");
        toolbar.setLogoDescription(i);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Property does not have a getter")
    public static final int getNavigationContentDescriptionResource(@NotNull Toolbar toolbar) {
        Intrinsics.checkParameterIsNotNull(toolbar, "receiver$0");
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    public static final void setNavigationContentDescriptionResource(@NotNull Toolbar toolbar, int i) {
        Intrinsics.checkParameterIsNotNull(toolbar, "receiver$0");
        toolbar.setNavigationContentDescription(i);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Property does not have a getter")
    public static final int getNavigationIconResource(@NotNull Toolbar toolbar) {
        Intrinsics.checkParameterIsNotNull(toolbar, "receiver$0");
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    public static final void setNavigationIconResource(@NotNull Toolbar toolbar, int i) {
        Intrinsics.checkParameterIsNotNull(toolbar, "receiver$0");
        toolbar.setNavigationIcon(i);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Property does not have a getter")
    public static final int getSubtitleResource(@NotNull Toolbar toolbar) {
        Intrinsics.checkParameterIsNotNull(toolbar, "receiver$0");
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    public static final void setSubtitleResource(@NotNull Toolbar toolbar, int i) {
        Intrinsics.checkParameterIsNotNull(toolbar, "receiver$0");
        toolbar.setSubtitle(i);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Property does not have a getter")
    public static final int getTitleResource(@NotNull Toolbar toolbar) {
        Intrinsics.checkParameterIsNotNull(toolbar, "receiver$0");
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    public static final void setTitleResource(@NotNull Toolbar toolbar, int i) {
        Intrinsics.checkParameterIsNotNull(toolbar, "receiver$0");
        toolbar.setTitle(i);
    }
}
