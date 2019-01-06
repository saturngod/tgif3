package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.BitmapCompat;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import java.io.InputStream;

public final class RoundedBitmapDrawableFactory {
    private static final String TAG = "RoundedBitmapDrawableFa";

    private static class DefaultRoundedBitmapDrawable extends RoundedBitmapDrawable {
        DefaultRoundedBitmapDrawable(Resources resources, Bitmap bitmap) {
            super(resources, bitmap);
        }

        public void setMipMap(boolean z) {
            if (this.mBitmap != null) {
                BitmapCompat.setHasMipMap(this.mBitmap, z);
                invalidateSelf();
            }
        }

        public boolean hasMipMap() {
            return this.mBitmap != null && BitmapCompat.hasMipMap(this.mBitmap);
        }

        void gravityCompatApply(int i, int i2, int i3, Rect rect, Rect rect2) {
            GravityCompat.apply(i, i2, i3, rect, rect2, 0);
        }
    }

    @NonNull
    public static RoundedBitmapDrawable create(@NonNull Resources resources, @Nullable Bitmap bitmap) {
        if (VERSION.SDK_INT >= 21) {
            return new RoundedBitmapDrawable21(resources, bitmap);
        }
        return new DefaultRoundedBitmapDrawable(resources, bitmap);
    }

    @NonNull
    public static RoundedBitmapDrawable create(@NonNull Resources resources, @NonNull String str) {
        resources = create(resources, BitmapFactory.decodeFile(str));
        if (resources.getBitmap() == null) {
            String str2 = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("RoundedBitmapDrawable cannot decode ");
            stringBuilder.append(str);
            Log.w(str2, stringBuilder.toString());
        }
        return resources;
    }

    @NonNull
    public static RoundedBitmapDrawable create(@NonNull Resources resources, @NonNull InputStream inputStream) {
        resources = create(resources, BitmapFactory.decodeStream(inputStream));
        if (resources.getBitmap() == null) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("RoundedBitmapDrawable cannot decode ");
            stringBuilder.append(inputStream);
            Log.w(str, stringBuilder.toString());
        }
        return resources;
    }

    private RoundedBitmapDrawableFactory() {
    }
}
