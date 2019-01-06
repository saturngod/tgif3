package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.CompoundButton;
import java.lang.reflect.Field;

public final class CompoundButtonCompat {
    private static final String TAG = "CompoundButtonCompat";
    private static Field sButtonDrawableField;
    private static boolean sButtonDrawableFieldFetched;

    private CompoundButtonCompat() {
    }

    public static void setButtonTintList(@NonNull CompoundButton compoundButton, @Nullable ColorStateList colorStateList) {
        if (VERSION.SDK_INT >= 21) {
            compoundButton.setButtonTintList(colorStateList);
        } else if (compoundButton instanceof TintableCompoundButton) {
            ((TintableCompoundButton) compoundButton).setSupportButtonTintList(colorStateList);
        }
    }

    @Nullable
    public static ColorStateList getButtonTintList(@NonNull CompoundButton compoundButton) {
        if (VERSION.SDK_INT >= 21) {
            return compoundButton.getButtonTintList();
        }
        return compoundButton instanceof TintableCompoundButton ? ((TintableCompoundButton) compoundButton).getSupportButtonTintList() : null;
    }

    public static void setButtonTintMode(@NonNull CompoundButton compoundButton, @Nullable Mode mode) {
        if (VERSION.SDK_INT >= 21) {
            compoundButton.setButtonTintMode(mode);
        } else if (compoundButton instanceof TintableCompoundButton) {
            ((TintableCompoundButton) compoundButton).setSupportButtonTintMode(mode);
        }
    }

    @Nullable
    public static Mode getButtonTintMode(@NonNull CompoundButton compoundButton) {
        if (VERSION.SDK_INT >= 21) {
            return compoundButton.getButtonTintMode();
        }
        return compoundButton instanceof TintableCompoundButton ? ((TintableCompoundButton) compoundButton).getSupportButtonTintMode() : null;
    }

    @Nullable
    public static Drawable getButtonDrawable(@NonNull CompoundButton compoundButton) {
        if (VERSION.SDK_INT >= 23) {
            return compoundButton.getButtonDrawable();
        }
        if (!sButtonDrawableFieldFetched) {
            try {
                sButtonDrawableField = CompoundButton.class.getDeclaredField("mButtonDrawable");
                sButtonDrawableField.setAccessible(true);
            } catch (Throwable e) {
                Log.i(TAG, "Failed to retrieve mButtonDrawable field", e);
            }
            sButtonDrawableFieldFetched = true;
        }
        if (sButtonDrawableField != null) {
            try {
                return (Drawable) sButtonDrawableField.get(compoundButton);
            } catch (CompoundButton compoundButton2) {
                Log.i(TAG, "Failed to get button drawable via reflection", compoundButton2);
                sButtonDrawableField = null;
            }
        }
        return null;
    }
}
