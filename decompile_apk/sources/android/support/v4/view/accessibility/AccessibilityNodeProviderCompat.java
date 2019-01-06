package android.support.v4.view.accessibility;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.ArrayList;
import java.util.List;

public class AccessibilityNodeProviderCompat {
    public static final int HOST_VIEW_ID = -1;
    private final Object mProvider;

    @RequiresApi(16)
    static class AccessibilityNodeProviderApi16 extends AccessibilityNodeProvider {
        final AccessibilityNodeProviderCompat mCompat;

        AccessibilityNodeProviderApi16(AccessibilityNodeProviderCompat accessibilityNodeProviderCompat) {
            this.mCompat = accessibilityNodeProviderCompat;
        }

        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
            i = this.mCompat.createAccessibilityNodeInfo(i);
            if (i == 0) {
                return 0;
            }
            return i.unwrap();
        }

        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
            str = this.mCompat.findAccessibilityNodeInfosByText(str, i);
            if (str == null) {
                return null;
            }
            i = new ArrayList();
            int size = str.size();
            for (int i2 = 0; i2 < size; i2++) {
                i.add(((AccessibilityNodeInfoCompat) str.get(i2)).unwrap());
            }
            return i;
        }

        public boolean performAction(int i, int i2, Bundle bundle) {
            return this.mCompat.performAction(i, i2, bundle);
        }
    }

    @RequiresApi(19)
    static class AccessibilityNodeProviderApi19 extends AccessibilityNodeProviderApi16 {
        AccessibilityNodeProviderApi19(AccessibilityNodeProviderCompat accessibilityNodeProviderCompat) {
            super(accessibilityNodeProviderCompat);
        }

        public AccessibilityNodeInfo findFocus(int i) {
            i = this.mCompat.findFocus(i);
            if (i == 0) {
                return 0;
            }
            return i.unwrap();
        }
    }

    @Nullable
    public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int i) {
        return null;
    }

    @Nullable
    public List<AccessibilityNodeInfoCompat> findAccessibilityNodeInfosByText(String str, int i) {
        return null;
    }

    @Nullable
    public AccessibilityNodeInfoCompat findFocus(int i) {
        return null;
    }

    public boolean performAction(int i, int i2, Bundle bundle) {
        return false;
    }

    public AccessibilityNodeProviderCompat() {
        if (VERSION.SDK_INT >= 19) {
            this.mProvider = new AccessibilityNodeProviderApi19(this);
        } else if (VERSION.SDK_INT >= 16) {
            this.mProvider = new AccessibilityNodeProviderApi16(this);
        } else {
            this.mProvider = null;
        }
    }

    public AccessibilityNodeProviderCompat(Object obj) {
        this.mProvider = obj;
    }

    public Object getProvider() {
        return this.mProvider;
    }
}
