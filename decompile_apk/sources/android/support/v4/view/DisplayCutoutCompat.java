package android.support.v4.view;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.DisplayCutout;
import java.util.List;

public final class DisplayCutoutCompat {
    private final Object mDisplayCutout;

    public DisplayCutoutCompat(Rect rect, List<Rect> list) {
        this(VERSION.SDK_INT >= 28 ? new DisplayCutout(rect, list) : null);
    }

    private DisplayCutoutCompat(Object obj) {
        this.mDisplayCutout = obj;
    }

    public int getSafeInsetTop() {
        return VERSION.SDK_INT >= 28 ? ((DisplayCutout) this.mDisplayCutout).getSafeInsetTop() : 0;
    }

    public int getSafeInsetBottom() {
        return VERSION.SDK_INT >= 28 ? ((DisplayCutout) this.mDisplayCutout).getSafeInsetBottom() : 0;
    }

    public int getSafeInsetLeft() {
        return VERSION.SDK_INT >= 28 ? ((DisplayCutout) this.mDisplayCutout).getSafeInsetLeft() : 0;
    }

    public int getSafeInsetRight() {
        return VERSION.SDK_INT >= 28 ? ((DisplayCutout) this.mDisplayCutout).getSafeInsetRight() : 0;
    }

    public List<Rect> getBoundingRects() {
        return VERSION.SDK_INT >= 28 ? ((DisplayCutout) this.mDisplayCutout).getBoundingRects() : null;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj != null) {
            if (getClass() == obj.getClass()) {
                DisplayCutoutCompat displayCutoutCompat = (DisplayCutoutCompat) obj;
                if (this.mDisplayCutout != null) {
                    z = this.mDisplayCutout.equals(displayCutoutCompat.mDisplayCutout);
                } else if (displayCutoutCompat.mDisplayCutout != null) {
                    z = false;
                }
                return z;
            }
        }
        return false;
    }

    public int hashCode() {
        return this.mDisplayCutout == null ? 0 : this.mDisplayCutout.hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DisplayCutoutCompat{");
        stringBuilder.append(this.mDisplayCutout);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    static DisplayCutoutCompat wrap(Object obj) {
        return obj == null ? null : new DisplayCutoutCompat(obj);
    }
}
