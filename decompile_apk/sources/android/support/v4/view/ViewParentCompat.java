package android.support.v4.view;

import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;

public final class ViewParentCompat {
    private static final String TAG = "ViewParentCompat";

    private ViewParentCompat() {
    }

    @Deprecated
    public static boolean requestSendAccessibilityEvent(ViewParent viewParent, View view, AccessibilityEvent accessibilityEvent) {
        return viewParent.requestSendAccessibilityEvent(view, accessibilityEvent);
    }

    public static boolean onStartNestedScroll(ViewParent viewParent, View view, View view2, int i) {
        return onStartNestedScroll(viewParent, view, view2, i, 0);
    }

    public static void onNestedScrollAccepted(ViewParent viewParent, View view, View view2, int i) {
        onNestedScrollAccepted(viewParent, view, view2, i, 0);
    }

    public static void onStopNestedScroll(ViewParent viewParent, View view) {
        onStopNestedScroll(viewParent, view, 0);
    }

    public static void onNestedScroll(ViewParent viewParent, View view, int i, int i2, int i3, int i4) {
        onNestedScroll(viewParent, view, i, i2, i3, i4, 0);
    }

    public static void onNestedPreScroll(ViewParent viewParent, View view, int i, int i2, int[] iArr) {
        onNestedPreScroll(viewParent, view, i, i2, iArr, 0);
    }

    public static boolean onStartNestedScroll(ViewParent viewParent, View view, View view2, int i, int i2) {
        if (viewParent instanceof NestedScrollingParent2) {
            return ((NestedScrollingParent2) viewParent).onStartNestedScroll(view, view2, i, i2);
        }
        if (i2 == 0) {
            if (VERSION.SDK_INT >= 21) {
                try {
                    return viewParent.onStartNestedScroll(view, view2, i);
                } catch (View view3) {
                    view2 = TAG;
                    i = new StringBuilder();
                    i.append("ViewParent ");
                    i.append(viewParent);
                    i.append(" does not implement interface ");
                    i.append("method onStartNestedScroll");
                    Log.e(view2, i.toString(), view3);
                }
            } else if ((viewParent instanceof NestedScrollingParent) != 0) {
                return ((NestedScrollingParent) viewParent).onStartNestedScroll(view3, view2, i);
            }
        }
        return null;
    }

    public static void onNestedScrollAccepted(ViewParent viewParent, View view, View view2, int i, int i2) {
        if (viewParent instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2) viewParent).onNestedScrollAccepted(view, view2, i, i2);
        } else if (i2 != 0) {
        } else {
            if (VERSION.SDK_INT >= 21) {
                try {
                    viewParent.onNestedScrollAccepted(view, view2, i);
                } catch (View view3) {
                    view2 = TAG;
                    i = new StringBuilder();
                    i.append("ViewParent ");
                    i.append(viewParent);
                    i.append(" does not implement interface ");
                    i.append("method onNestedScrollAccepted");
                    Log.e(view2, i.toString(), view3);
                }
            } else if ((viewParent instanceof NestedScrollingParent) != 0) {
                ((NestedScrollingParent) viewParent).onNestedScrollAccepted(view3, view2, i);
            }
        }
    }

    public static void onStopNestedScroll(ViewParent viewParent, View view, int i) {
        if (viewParent instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2) viewParent).onStopNestedScroll(view, i);
        } else if (i != 0) {
        } else {
            if (VERSION.SDK_INT >= 21) {
                try {
                    viewParent.onStopNestedScroll(view);
                } catch (View view2) {
                    i = TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("ViewParent ");
                    stringBuilder.append(viewParent);
                    stringBuilder.append(" does not implement interface ");
                    stringBuilder.append("method onStopNestedScroll");
                    Log.e(i, stringBuilder.toString(), view2);
                }
            } else if ((viewParent instanceof NestedScrollingParent) != 0) {
                ((NestedScrollingParent) viewParent).onStopNestedScroll(view2);
            }
        }
    }

    public static void onNestedScroll(ViewParent viewParent, View view, int i, int i2, int i3, int i4, int i5) {
        if (viewParent instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2) viewParent).onNestedScroll(view, i, i2, i3, i4, i5);
        } else if (i5 != 0) {
        } else {
            if (VERSION.SDK_INT >= 21) {
                try {
                    viewParent.onNestedScroll(view, i, i2, i3, i4);
                } catch (View view2) {
                    i = TAG;
                    i2 = new StringBuilder();
                    i2.append("ViewParent ");
                    i2.append(viewParent);
                    i2.append(" does not implement interface ");
                    i2.append("method onNestedScroll");
                    Log.e(i, i2.toString(), view2);
                }
            } else if ((viewParent instanceof NestedScrollingParent) != 0) {
                ((NestedScrollingParent) viewParent).onNestedScroll(view2, i, i2, i3, i4);
            }
        }
    }

    public static void onNestedPreScroll(ViewParent viewParent, View view, int i, int i2, int[] iArr, int i3) {
        if (viewParent instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2) viewParent).onNestedPreScroll(view, i, i2, iArr, i3);
        } else if (i3 != 0) {
        } else {
            if (VERSION.SDK_INT >= 21) {
                try {
                    viewParent.onNestedPreScroll(view, i, i2, iArr);
                } catch (View view2) {
                    i = TAG;
                    i2 = new StringBuilder();
                    i2.append("ViewParent ");
                    i2.append(viewParent);
                    i2.append(" does not implement interface ");
                    i2.append("method onNestedPreScroll");
                    Log.e(i, i2.toString(), view2);
                }
            } else if ((viewParent instanceof NestedScrollingParent) != 0) {
                ((NestedScrollingParent) viewParent).onNestedPreScroll(view2, i, i2, iArr);
            }
        }
    }

    public static boolean onNestedFling(ViewParent viewParent, View view, float f, float f2, boolean z) {
        if (VERSION.SDK_INT >= 21) {
            try {
                return viewParent.onNestedFling(view, f, f2, z);
            } catch (View view2) {
                f = TAG;
                f2 = new StringBuilder();
                f2.append("ViewParent ");
                f2.append(viewParent);
                f2.append(" does not implement interface ");
                f2.append("method onNestedFling");
                Log.e(f, f2.toString(), view2);
            }
        } else {
            if (viewParent instanceof NestedScrollingParent) {
                return ((NestedScrollingParent) viewParent).onNestedFling(view2, f, f2, z);
            }
            return null;
        }
    }

    public static boolean onNestedPreFling(ViewParent viewParent, View view, float f, float f2) {
        if (VERSION.SDK_INT >= 21) {
            try {
                return viewParent.onNestedPreFling(view, f, f2);
            } catch (View view2) {
                f = TAG;
                f2 = new StringBuilder();
                f2.append("ViewParent ");
                f2.append(viewParent);
                f2.append(" does not implement interface ");
                f2.append("method onNestedPreFling");
                Log.e(f, f2.toString(), view2);
            }
        } else {
            if (viewParent instanceof NestedScrollingParent) {
                return ((NestedScrollingParent) viewParent).onNestedPreFling(view2, f, f2);
            }
            return null;
        }
    }

    public static void notifySubtreeAccessibilityStateChanged(ViewParent viewParent, View view, View view2, int i) {
        if (VERSION.SDK_INT >= 19) {
            viewParent.notifySubtreeAccessibilityStateChanged(view, view2, i);
        }
    }
}
