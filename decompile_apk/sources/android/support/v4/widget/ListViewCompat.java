package android.support.v4.widget;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ListView;

public final class ListViewCompat {
    public static void scrollListBy(@NonNull ListView listView, int i) {
        if (VERSION.SDK_INT >= 19) {
            listView.scrollListBy(i);
        } else {
            int firstVisiblePosition = listView.getFirstVisiblePosition();
            if (firstVisiblePosition != -1) {
                View childAt = listView.getChildAt(0);
                if (childAt != null) {
                    listView.setSelectionFromTop(firstVisiblePosition, childAt.getTop() - i);
                }
            }
        }
    }

    public static boolean canScrollList(@NonNull ListView listView, int i) {
        if (VERSION.SDK_INT >= 19) {
            return listView.canScrollList(i);
        }
        int childCount = listView.getChildCount();
        boolean z = false;
        if (childCount == 0) {
            return false;
        }
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        if (i > 0) {
            i = listView.getChildAt(childCount - 1).getBottom();
            if (firstVisiblePosition + childCount < listView.getCount() || i > listView.getHeight() - listView.getListPaddingBottom()) {
                z = true;
            }
            return z;
        }
        i = listView.getChildAt(0).getTop();
        if (firstVisiblePosition > 0 || i < listView.getListPaddingTop()) {
            z = true;
        }
        return z;
    }

    private ListViewCompat() {
    }
}
