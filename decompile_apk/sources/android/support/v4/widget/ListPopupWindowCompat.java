package android.support.v4.widget;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ListPopupWindow;

public final class ListPopupWindowCompat {
    private ListPopupWindowCompat() {
    }

    @Deprecated
    public static OnTouchListener createDragToOpenListener(Object obj, View view) {
        return createDragToOpenListener((ListPopupWindow) obj, view);
    }

    @Nullable
    public static OnTouchListener createDragToOpenListener(@NonNull ListPopupWindow listPopupWindow, @NonNull View view) {
        return VERSION.SDK_INT >= 19 ? listPopupWindow.createDragToOpenListener(view) : null;
    }
}
