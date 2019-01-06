package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

public class ImageViewCompat {
    @Nullable
    public static ColorStateList getImageTintList(@NonNull ImageView imageView) {
        if (VERSION.SDK_INT >= 21) {
            return imageView.getImageTintList();
        }
        return imageView instanceof TintableImageSourceView ? ((TintableImageSourceView) imageView).getSupportImageTintList() : null;
    }

    public static void setImageTintList(@NonNull ImageView imageView, @Nullable ColorStateList colorStateList) {
        if (VERSION.SDK_INT >= 21) {
            imageView.setImageTintList(colorStateList);
            if (VERSION.SDK_INT == 21) {
                colorStateList = imageView.getDrawable();
                Object obj = (imageView.getImageTintList() == null || imageView.getImageTintMode() == null) ? null : 1;
                if (colorStateList != null && obj != null) {
                    if (colorStateList.isStateful()) {
                        colorStateList.setState(imageView.getDrawableState());
                    }
                    imageView.setImageDrawable(colorStateList);
                }
            }
        } else if (imageView instanceof TintableImageSourceView) {
            ((TintableImageSourceView) imageView).setSupportImageTintList(colorStateList);
        }
    }

    @Nullable
    public static Mode getImageTintMode(@NonNull ImageView imageView) {
        if (VERSION.SDK_INT >= 21) {
            return imageView.getImageTintMode();
        }
        return imageView instanceof TintableImageSourceView ? ((TintableImageSourceView) imageView).getSupportImageTintMode() : null;
    }

    public static void setImageTintMode(@NonNull ImageView imageView, @Nullable Mode mode) {
        if (VERSION.SDK_INT >= 21) {
            imageView.setImageTintMode(mode);
            if (VERSION.SDK_INT == 21) {
                mode = imageView.getDrawable();
                Object obj = (imageView.getImageTintList() == null || imageView.getImageTintMode() == null) ? null : 1;
                if (mode != null && obj != null) {
                    if (mode.isStateful()) {
                        mode.setState(imageView.getDrawableState());
                    }
                    imageView.setImageDrawable(mode);
                }
            }
        } else if (imageView instanceof TintableImageSourceView) {
            ((TintableImageSourceView) imageView).setSupportImageTintMode(mode);
        }
    }

    private ImageViewCompat() {
    }
}
