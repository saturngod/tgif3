package android.support.v7.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.appcompat.C0192R;
import android.view.ViewConfiguration;
import org.jetbrains.anko.DimensionsKt;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ActionBarPolicy {
    private Context mContext;

    public static ActionBarPolicy get(Context context) {
        return new ActionBarPolicy(context);
    }

    private ActionBarPolicy(Context context) {
        this.mContext = context;
    }

    public int getMaxActionButtons() {
        Configuration configuration = this.mContext.getResources().getConfiguration();
        int i = configuration.screenWidthDp;
        int i2 = configuration.screenHeightDp;
        if (configuration.smallestScreenWidthDp <= 600 && i <= 600 && (i <= 960 || i2 <= 720)) {
            if (i <= 720 || i2 <= 960) {
                if (i < 500 && (i <= DimensionsKt.XXXHDPI || i2 <= DimensionsKt.XXHDPI)) {
                    if (i <= DimensionsKt.XXHDPI || i2 <= DimensionsKt.XXXHDPI) {
                        return i >= 360 ? 3 : 2;
                    }
                }
                return 4;
            }
        }
        return 5;
    }

    public boolean showsOverflowMenuButton() {
        if (VERSION.SDK_INT >= 19) {
            return true;
        }
        return ViewConfiguration.get(this.mContext).hasPermanentMenuKey() ^ true;
    }

    public int getEmbeddedMenuWidthLimit() {
        return this.mContext.getResources().getDisplayMetrics().widthPixels / 2;
    }

    public boolean hasEmbeddedTabs() {
        return this.mContext.getResources().getBoolean(C0192R.bool.abc_action_bar_embed_tabs);
    }

    public int getTabContainerHeight() {
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(null, C0192R.styleable.ActionBar, C0192R.attr.actionBarStyle, 0);
        int layoutDimension = obtainStyledAttributes.getLayoutDimension(C0192R.styleable.ActionBar_height, 0);
        Resources resources = this.mContext.getResources();
        if (!hasEmbeddedTabs()) {
            layoutDimension = Math.min(layoutDimension, resources.getDimensionPixelSize(C0192R.dimen.abc_action_bar_stacked_max_height));
        }
        obtainStyledAttributes.recycle();
        return layoutDimension;
    }

    public boolean enableHomeButtonByDefault() {
        return this.mContext.getApplicationInfo().targetSdkVersion < 14;
    }

    public int getStackedTabMaxWidth() {
        return this.mContext.getResources().getDimensionPixelSize(C0192R.dimen.abc_action_bar_stacked_tab_max_width);
    }
}
