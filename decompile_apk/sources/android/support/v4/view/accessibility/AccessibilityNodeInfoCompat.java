package android.support.v4.view.accessibility;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction;
import android.view.accessibility.AccessibilityNodeInfo.CollectionInfo;
import android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo;
import android.view.accessibility.AccessibilityNodeInfo.RangeInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccessibilityNodeInfoCompat {
    public static final int ACTION_ACCESSIBILITY_FOCUS = 64;
    public static final String ACTION_ARGUMENT_COLUMN_INT = "android.view.accessibility.action.ARGUMENT_COLUMN_INT";
    public static final String ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN = "ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN";
    public static final String ACTION_ARGUMENT_HTML_ELEMENT_STRING = "ACTION_ARGUMENT_HTML_ELEMENT_STRING";
    public static final String ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT = "ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT";
    public static final String ACTION_ARGUMENT_MOVE_WINDOW_X = "ACTION_ARGUMENT_MOVE_WINDOW_X";
    public static final String ACTION_ARGUMENT_MOVE_WINDOW_Y = "ACTION_ARGUMENT_MOVE_WINDOW_Y";
    public static final String ACTION_ARGUMENT_PROGRESS_VALUE = "android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE";
    public static final String ACTION_ARGUMENT_ROW_INT = "android.view.accessibility.action.ARGUMENT_ROW_INT";
    public static final String ACTION_ARGUMENT_SELECTION_END_INT = "ACTION_ARGUMENT_SELECTION_END_INT";
    public static final String ACTION_ARGUMENT_SELECTION_START_INT = "ACTION_ARGUMENT_SELECTION_START_INT";
    public static final String ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE = "ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE";
    public static final int ACTION_CLEAR_ACCESSIBILITY_FOCUS = 128;
    public static final int ACTION_CLEAR_FOCUS = 2;
    public static final int ACTION_CLEAR_SELECTION = 8;
    public static final int ACTION_CLICK = 16;
    public static final int ACTION_COLLAPSE = 524288;
    public static final int ACTION_COPY = 16384;
    public static final int ACTION_CUT = 65536;
    public static final int ACTION_DISMISS = 1048576;
    public static final int ACTION_EXPAND = 262144;
    public static final int ACTION_FOCUS = 1;
    public static final int ACTION_LONG_CLICK = 32;
    public static final int ACTION_NEXT_AT_MOVEMENT_GRANULARITY = 256;
    public static final int ACTION_NEXT_HTML_ELEMENT = 1024;
    public static final int ACTION_PASTE = 32768;
    public static final int ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = 512;
    public static final int ACTION_PREVIOUS_HTML_ELEMENT = 2048;
    public static final int ACTION_SCROLL_BACKWARD = 8192;
    public static final int ACTION_SCROLL_FORWARD = 4096;
    public static final int ACTION_SELECT = 4;
    public static final int ACTION_SET_SELECTION = 131072;
    public static final int ACTION_SET_TEXT = 2097152;
    private static final int BOOLEAN_PROPERTY_IS_HEADING = 2;
    private static final int BOOLEAN_PROPERTY_IS_SHOWING_HINT = 4;
    private static final String BOOLEAN_PROPERTY_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY";
    private static final int BOOLEAN_PROPERTY_SCREEN_READER_FOCUSABLE = 1;
    public static final int FOCUS_ACCESSIBILITY = 2;
    public static final int FOCUS_INPUT = 1;
    private static final String HINT_TEXT_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.HINT_TEXT_KEY";
    public static final int MOVEMENT_GRANULARITY_CHARACTER = 1;
    public static final int MOVEMENT_GRANULARITY_LINE = 4;
    public static final int MOVEMENT_GRANULARITY_PAGE = 16;
    public static final int MOVEMENT_GRANULARITY_PARAGRAPH = 8;
    public static final int MOVEMENT_GRANULARITY_WORD = 2;
    private static final String PANE_TITLE_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY";
    private static final String ROLE_DESCRIPTION_KEY = "AccessibilityNodeInfo.roleDescription";
    private static final String TOOLTIP_TEXT_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.TOOLTIP_TEXT_KEY";
    private final AccessibilityNodeInfo mInfo;
    @RestrictTo({Scope.LIBRARY_GROUP})
    public int mParentVirtualDescendantId = -1;

    public static class AccessibilityActionCompat {
        public static final AccessibilityActionCompat ACTION_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(64, null);
        public static final AccessibilityActionCompat ACTION_CLEAR_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(128, null);
        public static final AccessibilityActionCompat ACTION_CLEAR_FOCUS = new AccessibilityActionCompat(2, null);
        public static final AccessibilityActionCompat ACTION_CLEAR_SELECTION = new AccessibilityActionCompat(8, null);
        public static final AccessibilityActionCompat ACTION_CLICK = new AccessibilityActionCompat(16, null);
        public static final AccessibilityActionCompat ACTION_COLLAPSE = new AccessibilityActionCompat(524288, null);
        public static final AccessibilityActionCompat ACTION_CONTEXT_CLICK = new AccessibilityActionCompat(VERSION.SDK_INT >= 23 ? AccessibilityAction.ACTION_CONTEXT_CLICK : null);
        public static final AccessibilityActionCompat ACTION_COPY = new AccessibilityActionCompat(16384, null);
        public static final AccessibilityActionCompat ACTION_CUT = new AccessibilityActionCompat(65536, null);
        public static final AccessibilityActionCompat ACTION_DISMISS = new AccessibilityActionCompat(1048576, null);
        public static final AccessibilityActionCompat ACTION_EXPAND = new AccessibilityActionCompat(262144, null);
        public static final AccessibilityActionCompat ACTION_FOCUS = new AccessibilityActionCompat(1, null);
        public static final AccessibilityActionCompat ACTION_HIDE_TOOLTIP;
        public static final AccessibilityActionCompat ACTION_LONG_CLICK = new AccessibilityActionCompat(32, null);
        public static final AccessibilityActionCompat ACTION_MOVE_WINDOW = new AccessibilityActionCompat(VERSION.SDK_INT >= 26 ? AccessibilityAction.ACTION_MOVE_WINDOW : null);
        public static final AccessibilityActionCompat ACTION_NEXT_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(256, null);
        public static final AccessibilityActionCompat ACTION_NEXT_HTML_ELEMENT = new AccessibilityActionCompat(1024, null);
        public static final AccessibilityActionCompat ACTION_PASTE = new AccessibilityActionCompat(32768, null);
        public static final AccessibilityActionCompat ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(512, null);
        public static final AccessibilityActionCompat ACTION_PREVIOUS_HTML_ELEMENT = new AccessibilityActionCompat(2048, null);
        public static final AccessibilityActionCompat ACTION_SCROLL_BACKWARD = new AccessibilityActionCompat(8192, null);
        public static final AccessibilityActionCompat ACTION_SCROLL_DOWN = new AccessibilityActionCompat(VERSION.SDK_INT >= 23 ? AccessibilityAction.ACTION_SCROLL_DOWN : null);
        public static final AccessibilityActionCompat ACTION_SCROLL_FORWARD = new AccessibilityActionCompat(4096, null);
        public static final AccessibilityActionCompat ACTION_SCROLL_LEFT = new AccessibilityActionCompat(VERSION.SDK_INT >= 23 ? AccessibilityAction.ACTION_SCROLL_LEFT : null);
        public static final AccessibilityActionCompat ACTION_SCROLL_RIGHT = new AccessibilityActionCompat(VERSION.SDK_INT >= 23 ? AccessibilityAction.ACTION_SCROLL_RIGHT : null);
        public static final AccessibilityActionCompat ACTION_SCROLL_TO_POSITION = new AccessibilityActionCompat(VERSION.SDK_INT >= 23 ? AccessibilityAction.ACTION_SCROLL_TO_POSITION : null);
        public static final AccessibilityActionCompat ACTION_SCROLL_UP = new AccessibilityActionCompat(VERSION.SDK_INT >= 23 ? AccessibilityAction.ACTION_SCROLL_UP : null);
        public static final AccessibilityActionCompat ACTION_SELECT = new AccessibilityActionCompat(4, null);
        public static final AccessibilityActionCompat ACTION_SET_PROGRESS = new AccessibilityActionCompat(VERSION.SDK_INT >= 24 ? AccessibilityAction.ACTION_SET_PROGRESS : null);
        public static final AccessibilityActionCompat ACTION_SET_SELECTION = new AccessibilityActionCompat(131072, null);
        public static final AccessibilityActionCompat ACTION_SET_TEXT = new AccessibilityActionCompat(2097152, null);
        public static final AccessibilityActionCompat ACTION_SHOW_ON_SCREEN = new AccessibilityActionCompat(VERSION.SDK_INT >= 23 ? AccessibilityAction.ACTION_SHOW_ON_SCREEN : null);
        public static final AccessibilityActionCompat ACTION_SHOW_TOOLTIP = new AccessibilityActionCompat(VERSION.SDK_INT >= 28 ? AccessibilityAction.ACTION_SHOW_TOOLTIP : null);
        final Object mAction;

        static {
            Object obj = null;
            if (VERSION.SDK_INT >= 28) {
                obj = AccessibilityAction.ACTION_HIDE_TOOLTIP;
            }
            ACTION_HIDE_TOOLTIP = new AccessibilityActionCompat(obj);
        }

        public AccessibilityActionCompat(int i, CharSequence charSequence) {
            this(VERSION.SDK_INT >= 21 ? new AccessibilityAction(i, charSequence) : null);
        }

        AccessibilityActionCompat(Object obj) {
            this.mAction = obj;
        }

        public int getId() {
            return VERSION.SDK_INT >= 21 ? ((AccessibilityAction) this.mAction).getId() : 0;
        }

        public CharSequence getLabel() {
            return VERSION.SDK_INT >= 21 ? ((AccessibilityAction) this.mAction).getLabel() : null;
        }
    }

    public static class CollectionInfoCompat {
        public static final int SELECTION_MODE_MULTIPLE = 2;
        public static final int SELECTION_MODE_NONE = 0;
        public static final int SELECTION_MODE_SINGLE = 1;
        final Object mInfo;

        public static CollectionInfoCompat obtain(int i, int i2, boolean z, int i3) {
            if (VERSION.SDK_INT >= 21) {
                return new CollectionInfoCompat(CollectionInfo.obtain(i, i2, z, i3));
            }
            if (VERSION.SDK_INT >= 19) {
                return new CollectionInfoCompat(CollectionInfo.obtain(i, i2, z));
            }
            return new CollectionInfoCompat(0);
        }

        public static CollectionInfoCompat obtain(int i, int i2, boolean z) {
            if (VERSION.SDK_INT >= 19) {
                return new CollectionInfoCompat(CollectionInfo.obtain(i, i2, z));
            }
            return new CollectionInfoCompat(0);
        }

        CollectionInfoCompat(Object obj) {
            this.mInfo = obj;
        }

        public int getColumnCount() {
            return VERSION.SDK_INT >= 19 ? ((CollectionInfo) this.mInfo).getColumnCount() : 0;
        }

        public int getRowCount() {
            return VERSION.SDK_INT >= 19 ? ((CollectionInfo) this.mInfo).getRowCount() : 0;
        }

        public boolean isHierarchical() {
            return VERSION.SDK_INT >= 19 ? ((CollectionInfo) this.mInfo).isHierarchical() : false;
        }

        public int getSelectionMode() {
            return VERSION.SDK_INT >= 21 ? ((CollectionInfo) this.mInfo).getSelectionMode() : 0;
        }
    }

    public static class CollectionItemInfoCompat {
        final Object mInfo;

        public static CollectionItemInfoCompat obtain(int i, int i2, int i3, int i4, boolean z, boolean z2) {
            if (VERSION.SDK_INT >= 21) {
                return new CollectionItemInfoCompat(CollectionItemInfo.obtain(i, i2, i3, i4, z, z2));
            }
            if (VERSION.SDK_INT >= true) {
                return new CollectionItemInfoCompat(CollectionItemInfo.obtain(i, i2, i3, i4, z));
            }
            return new CollectionItemInfoCompat(0);
        }

        public static CollectionItemInfoCompat obtain(int i, int i2, int i3, int i4, boolean z) {
            if (VERSION.SDK_INT >= 19) {
                return new CollectionItemInfoCompat(CollectionItemInfo.obtain(i, i2, i3, i4, z));
            }
            return new CollectionItemInfoCompat(0);
        }

        CollectionItemInfoCompat(Object obj) {
            this.mInfo = obj;
        }

        public int getColumnIndex() {
            return VERSION.SDK_INT >= 19 ? ((CollectionItemInfo) this.mInfo).getColumnIndex() : 0;
        }

        public int getColumnSpan() {
            return VERSION.SDK_INT >= 19 ? ((CollectionItemInfo) this.mInfo).getColumnSpan() : 0;
        }

        public int getRowIndex() {
            return VERSION.SDK_INT >= 19 ? ((CollectionItemInfo) this.mInfo).getRowIndex() : 0;
        }

        public int getRowSpan() {
            return VERSION.SDK_INT >= 19 ? ((CollectionItemInfo) this.mInfo).getRowSpan() : 0;
        }

        public boolean isHeading() {
            return VERSION.SDK_INT >= 19 ? ((CollectionItemInfo) this.mInfo).isHeading() : false;
        }

        public boolean isSelected() {
            return VERSION.SDK_INT >= 21 ? ((CollectionItemInfo) this.mInfo).isSelected() : false;
        }
    }

    public static class RangeInfoCompat {
        public static final int RANGE_TYPE_FLOAT = 1;
        public static final int RANGE_TYPE_INT = 0;
        public static final int RANGE_TYPE_PERCENT = 2;
        final Object mInfo;

        public static RangeInfoCompat obtain(int i, float f, float f2, float f3) {
            if (VERSION.SDK_INT >= 19) {
                return new RangeInfoCompat(RangeInfo.obtain(i, f, f2, f3));
            }
            return new RangeInfoCompat(0.0f);
        }

        RangeInfoCompat(Object obj) {
            this.mInfo = obj;
        }

        public float getCurrent() {
            return VERSION.SDK_INT >= 19 ? ((RangeInfo) this.mInfo).getCurrent() : 0.0f;
        }

        public float getMax() {
            return VERSION.SDK_INT >= 19 ? ((RangeInfo) this.mInfo).getMax() : 0.0f;
        }

        public float getMin() {
            return VERSION.SDK_INT >= 19 ? ((RangeInfo) this.mInfo).getMin() : 0.0f;
        }

        public int getType() {
            return VERSION.SDK_INT >= 19 ? ((RangeInfo) this.mInfo).getType() : 0;
        }
    }

    private static String getActionSymbolicName(int i) {
        switch (i) {
            case 1:
                return "ACTION_FOCUS";
            case 2:
                return "ACTION_CLEAR_FOCUS";
            case 4:
                return "ACTION_SELECT";
            case 8:
                return "ACTION_CLEAR_SELECTION";
            case 16:
                return "ACTION_CLICK";
            case 32:
                return "ACTION_LONG_CLICK";
            case 64:
                return "ACTION_ACCESSIBILITY_FOCUS";
            case 128:
                return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
            case 256:
                return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
            case 512:
                return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
            case 1024:
                return "ACTION_NEXT_HTML_ELEMENT";
            case 2048:
                return "ACTION_PREVIOUS_HTML_ELEMENT";
            case 4096:
                return "ACTION_SCROLL_FORWARD";
            case 8192:
                return "ACTION_SCROLL_BACKWARD";
            case 16384:
                return "ACTION_COPY";
            case 32768:
                return "ACTION_PASTE";
            case 65536:
                return "ACTION_CUT";
            case 131072:
                return "ACTION_SET_SELECTION";
            default:
                return "ACTION_UNKNOWN";
        }
    }

    static AccessibilityNodeInfoCompat wrapNonNullInstance(Object obj) {
        return obj != null ? new AccessibilityNodeInfoCompat(obj) : null;
    }

    @Deprecated
    public AccessibilityNodeInfoCompat(Object obj) {
        this.mInfo = (AccessibilityNodeInfo) obj;
    }

    private AccessibilityNodeInfoCompat(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.mInfo = accessibilityNodeInfo;
    }

    public static AccessibilityNodeInfoCompat wrap(@NonNull AccessibilityNodeInfo accessibilityNodeInfo) {
        return new AccessibilityNodeInfoCompat(accessibilityNodeInfo);
    }

    public AccessibilityNodeInfo unwrap() {
        return this.mInfo;
    }

    @Deprecated
    public Object getInfo() {
        return this.mInfo;
    }

    public static AccessibilityNodeInfoCompat obtain(View view) {
        return wrap(AccessibilityNodeInfo.obtain(view));
    }

    public static AccessibilityNodeInfoCompat obtain(View view, int i) {
        return VERSION.SDK_INT >= 16 ? wrapNonNullInstance(AccessibilityNodeInfo.obtain(view, i)) : null;
    }

    public static AccessibilityNodeInfoCompat obtain() {
        return wrap(AccessibilityNodeInfo.obtain());
    }

    public static AccessibilityNodeInfoCompat obtain(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return wrap(AccessibilityNodeInfo.obtain(accessibilityNodeInfoCompat.mInfo));
    }

    public void setSource(View view) {
        this.mInfo.setSource(view);
    }

    public void setSource(View view, int i) {
        if (VERSION.SDK_INT >= 16) {
            this.mInfo.setSource(view, i);
        }
    }

    public AccessibilityNodeInfoCompat findFocus(int i) {
        return VERSION.SDK_INT >= 16 ? wrapNonNullInstance(this.mInfo.findFocus(i)) : 0;
    }

    public AccessibilityNodeInfoCompat focusSearch(int i) {
        return VERSION.SDK_INT >= 16 ? wrapNonNullInstance(this.mInfo.focusSearch(i)) : 0;
    }

    public int getWindowId() {
        return this.mInfo.getWindowId();
    }

    public int getChildCount() {
        return this.mInfo.getChildCount();
    }

    public AccessibilityNodeInfoCompat getChild(int i) {
        return wrapNonNullInstance(this.mInfo.getChild(i));
    }

    public void addChild(View view) {
        this.mInfo.addChild(view);
    }

    public void addChild(View view, int i) {
        if (VERSION.SDK_INT >= 16) {
            this.mInfo.addChild(view, i);
        }
    }

    public boolean removeChild(View view) {
        return VERSION.SDK_INT >= 21 ? this.mInfo.removeChild(view) : null;
    }

    public boolean removeChild(View view, int i) {
        return VERSION.SDK_INT >= 21 ? this.mInfo.removeChild(view, i) : null;
    }

    public int getActions() {
        return this.mInfo.getActions();
    }

    public void addAction(int i) {
        this.mInfo.addAction(i);
    }

    public void addAction(AccessibilityActionCompat accessibilityActionCompat) {
        if (VERSION.SDK_INT >= 21) {
            this.mInfo.addAction((AccessibilityAction) accessibilityActionCompat.mAction);
        }
    }

    public boolean removeAction(AccessibilityActionCompat accessibilityActionCompat) {
        return VERSION.SDK_INT >= 21 ? this.mInfo.removeAction((AccessibilityAction) accessibilityActionCompat.mAction) : null;
    }

    public boolean performAction(int i) {
        return this.mInfo.performAction(i);
    }

    public boolean performAction(int i, Bundle bundle) {
        return VERSION.SDK_INT >= 16 ? this.mInfo.performAction(i, bundle) : false;
    }

    public void setMovementGranularities(int i) {
        if (VERSION.SDK_INT >= 16) {
            this.mInfo.setMovementGranularities(i);
        }
    }

    public int getMovementGranularities() {
        return VERSION.SDK_INT >= 16 ? this.mInfo.getMovementGranularities() : 0;
    }

    public List<AccessibilityNodeInfoCompat> findAccessibilityNodeInfosByText(String str) {
        List<AccessibilityNodeInfoCompat> arrayList = new ArrayList();
        str = this.mInfo.findAccessibilityNodeInfosByText(str);
        int size = str.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(wrap((AccessibilityNodeInfo) str.get(i)));
        }
        return arrayList;
    }

    public AccessibilityNodeInfoCompat getParent() {
        return wrapNonNullInstance(this.mInfo.getParent());
    }

    public void setParent(View view) {
        this.mInfo.setParent(view);
    }

    public void setParent(View view, int i) {
        this.mParentVirtualDescendantId = i;
        if (VERSION.SDK_INT >= 16) {
            this.mInfo.setParent(view, i);
        }
    }

    public void getBoundsInParent(Rect rect) {
        this.mInfo.getBoundsInParent(rect);
    }

    public void setBoundsInParent(Rect rect) {
        this.mInfo.setBoundsInParent(rect);
    }

    public void getBoundsInScreen(Rect rect) {
        this.mInfo.getBoundsInScreen(rect);
    }

    public void setBoundsInScreen(Rect rect) {
        this.mInfo.setBoundsInScreen(rect);
    }

    public boolean isCheckable() {
        return this.mInfo.isCheckable();
    }

    public void setCheckable(boolean z) {
        this.mInfo.setCheckable(z);
    }

    public boolean isChecked() {
        return this.mInfo.isChecked();
    }

    public void setChecked(boolean z) {
        this.mInfo.setChecked(z);
    }

    public boolean isFocusable() {
        return this.mInfo.isFocusable();
    }

    public void setFocusable(boolean z) {
        this.mInfo.setFocusable(z);
    }

    public boolean isFocused() {
        return this.mInfo.isFocused();
    }

    public void setFocused(boolean z) {
        this.mInfo.setFocused(z);
    }

    public boolean isVisibleToUser() {
        return VERSION.SDK_INT >= 16 ? this.mInfo.isVisibleToUser() : false;
    }

    public void setVisibleToUser(boolean z) {
        if (VERSION.SDK_INT >= 16) {
            this.mInfo.setVisibleToUser(z);
        }
    }

    public boolean isAccessibilityFocused() {
        return VERSION.SDK_INT >= 16 ? this.mInfo.isAccessibilityFocused() : false;
    }

    public void setAccessibilityFocused(boolean z) {
        if (VERSION.SDK_INT >= 16) {
            this.mInfo.setAccessibilityFocused(z);
        }
    }

    public boolean isSelected() {
        return this.mInfo.isSelected();
    }

    public void setSelected(boolean z) {
        this.mInfo.setSelected(z);
    }

    public boolean isClickable() {
        return this.mInfo.isClickable();
    }

    public void setClickable(boolean z) {
        this.mInfo.setClickable(z);
    }

    public boolean isLongClickable() {
        return this.mInfo.isLongClickable();
    }

    public void setLongClickable(boolean z) {
        this.mInfo.setLongClickable(z);
    }

    public boolean isEnabled() {
        return this.mInfo.isEnabled();
    }

    public void setEnabled(boolean z) {
        this.mInfo.setEnabled(z);
    }

    public boolean isPassword() {
        return this.mInfo.isPassword();
    }

    public void setPassword(boolean z) {
        this.mInfo.setPassword(z);
    }

    public boolean isScrollable() {
        return this.mInfo.isScrollable();
    }

    public void setScrollable(boolean z) {
        this.mInfo.setScrollable(z);
    }

    public boolean isImportantForAccessibility() {
        return VERSION.SDK_INT >= 24 ? this.mInfo.isImportantForAccessibility() : true;
    }

    public void setImportantForAccessibility(boolean z) {
        if (VERSION.SDK_INT >= 24) {
            this.mInfo.setImportantForAccessibility(z);
        }
    }

    public CharSequence getPackageName() {
        return this.mInfo.getPackageName();
    }

    public void setPackageName(CharSequence charSequence) {
        this.mInfo.setPackageName(charSequence);
    }

    public CharSequence getClassName() {
        return this.mInfo.getClassName();
    }

    public void setClassName(CharSequence charSequence) {
        this.mInfo.setClassName(charSequence);
    }

    public CharSequence getText() {
        return this.mInfo.getText();
    }

    public void setText(CharSequence charSequence) {
        this.mInfo.setText(charSequence);
    }

    public CharSequence getContentDescription() {
        return this.mInfo.getContentDescription();
    }

    public void setContentDescription(CharSequence charSequence) {
        this.mInfo.setContentDescription(charSequence);
    }

    public void recycle() {
        this.mInfo.recycle();
    }

    public void setViewIdResourceName(String str) {
        if (VERSION.SDK_INT >= 18) {
            this.mInfo.setViewIdResourceName(str);
        }
    }

    public String getViewIdResourceName() {
        return VERSION.SDK_INT >= 18 ? this.mInfo.getViewIdResourceName() : null;
    }

    public int getLiveRegion() {
        return VERSION.SDK_INT >= 19 ? this.mInfo.getLiveRegion() : 0;
    }

    public void setLiveRegion(int i) {
        if (VERSION.SDK_INT >= 19) {
            this.mInfo.setLiveRegion(i);
        }
    }

    public int getDrawingOrder() {
        return VERSION.SDK_INT >= 24 ? this.mInfo.getDrawingOrder() : 0;
    }

    public void setDrawingOrder(int i) {
        if (VERSION.SDK_INT >= 24) {
            this.mInfo.setDrawingOrder(i);
        }
    }

    public CollectionInfoCompat getCollectionInfo() {
        if (VERSION.SDK_INT >= 19) {
            CollectionInfo collectionInfo = this.mInfo.getCollectionInfo();
            if (collectionInfo != null) {
                return new CollectionInfoCompat(collectionInfo);
            }
        }
        return null;
    }

    public void setCollectionInfo(Object obj) {
        if (VERSION.SDK_INT >= 19) {
            this.mInfo.setCollectionInfo(obj == null ? null : (CollectionInfo) ((CollectionInfoCompat) obj).mInfo);
        }
    }

    public void setCollectionItemInfo(Object obj) {
        if (VERSION.SDK_INT >= 19) {
            this.mInfo.setCollectionItemInfo(obj == null ? null : (CollectionItemInfo) ((CollectionItemInfoCompat) obj).mInfo);
        }
    }

    public CollectionItemInfoCompat getCollectionItemInfo() {
        if (VERSION.SDK_INT >= 19) {
            CollectionItemInfo collectionItemInfo = this.mInfo.getCollectionItemInfo();
            if (collectionItemInfo != null) {
                return new CollectionItemInfoCompat(collectionItemInfo);
            }
        }
        return null;
    }

    public RangeInfoCompat getRangeInfo() {
        if (VERSION.SDK_INT >= 19) {
            RangeInfo rangeInfo = this.mInfo.getRangeInfo();
            if (rangeInfo != null) {
                return new RangeInfoCompat(rangeInfo);
            }
        }
        return null;
    }

    public void setRangeInfo(RangeInfoCompat rangeInfoCompat) {
        if (VERSION.SDK_INT >= 19) {
            this.mInfo.setRangeInfo((RangeInfo) rangeInfoCompat.mInfo);
        }
    }

    public List<AccessibilityActionCompat> getActionList() {
        List actionList = VERSION.SDK_INT >= 21 ? this.mInfo.getActionList() : null;
        if (actionList == null) {
            return Collections.emptyList();
        }
        List<AccessibilityActionCompat> arrayList = new ArrayList();
        int size = actionList.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(new AccessibilityActionCompat(actionList.get(i)));
        }
        return arrayList;
    }

    public void setContentInvalid(boolean z) {
        if (VERSION.SDK_INT >= 19) {
            this.mInfo.setContentInvalid(z);
        }
    }

    public boolean isContentInvalid() {
        return VERSION.SDK_INT >= 19 ? this.mInfo.isContentInvalid() : false;
    }

    public boolean isContextClickable() {
        return VERSION.SDK_INT >= 23 ? this.mInfo.isContextClickable() : false;
    }

    public void setContextClickable(boolean z) {
        if (VERSION.SDK_INT >= 23) {
            this.mInfo.setContextClickable(z);
        }
    }

    @Nullable
    public CharSequence getHintText() {
        if (VERSION.SDK_INT >= 26) {
            return this.mInfo.getHintText();
        }
        return VERSION.SDK_INT >= 19 ? this.mInfo.getExtras().getCharSequence(HINT_TEXT_KEY) : null;
    }

    public void setHintText(@Nullable CharSequence charSequence) {
        if (VERSION.SDK_INT >= 26) {
            this.mInfo.setHintText(charSequence);
        } else if (VERSION.SDK_INT >= 19) {
            this.mInfo.getExtras().putCharSequence(HINT_TEXT_KEY, charSequence);
        }
    }

    public void setError(CharSequence charSequence) {
        if (VERSION.SDK_INT >= 21) {
            this.mInfo.setError(charSequence);
        }
    }

    public CharSequence getError() {
        return VERSION.SDK_INT >= 21 ? this.mInfo.getError() : null;
    }

    public void setLabelFor(View view) {
        if (VERSION.SDK_INT >= 17) {
            this.mInfo.setLabelFor(view);
        }
    }

    public void setLabelFor(View view, int i) {
        if (VERSION.SDK_INT >= 17) {
            this.mInfo.setLabelFor(view, i);
        }
    }

    public AccessibilityNodeInfoCompat getLabelFor() {
        return VERSION.SDK_INT >= 17 ? wrapNonNullInstance(this.mInfo.getLabelFor()) : null;
    }

    public void setLabeledBy(View view) {
        if (VERSION.SDK_INT >= 17) {
            this.mInfo.setLabeledBy(view);
        }
    }

    public void setLabeledBy(View view, int i) {
        if (VERSION.SDK_INT >= 17) {
            this.mInfo.setLabeledBy(view, i);
        }
    }

    public AccessibilityNodeInfoCompat getLabeledBy() {
        return VERSION.SDK_INT >= 17 ? wrapNonNullInstance(this.mInfo.getLabeledBy()) : null;
    }

    public boolean canOpenPopup() {
        return VERSION.SDK_INT >= 19 ? this.mInfo.canOpenPopup() : false;
    }

    public void setCanOpenPopup(boolean z) {
        if (VERSION.SDK_INT >= 19) {
            this.mInfo.setCanOpenPopup(z);
        }
    }

    public List<AccessibilityNodeInfoCompat> findAccessibilityNodeInfosByViewId(String str) {
        if (VERSION.SDK_INT < 18) {
            return Collections.emptyList();
        }
        String<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = this.mInfo.findAccessibilityNodeInfosByViewId(str);
        List<AccessibilityNodeInfoCompat> arrayList = new ArrayList();
        for (AccessibilityNodeInfo wrap : findAccessibilityNodeInfosByViewId) {
            arrayList.add(wrap(wrap));
        }
        return arrayList;
    }

    public Bundle getExtras() {
        if (VERSION.SDK_INT >= 19) {
            return this.mInfo.getExtras();
        }
        return new Bundle();
    }

    public int getInputType() {
        return VERSION.SDK_INT >= 19 ? this.mInfo.getInputType() : 0;
    }

    public void setInputType(int i) {
        if (VERSION.SDK_INT >= 19) {
            this.mInfo.setInputType(i);
        }
    }

    public void setMaxTextLength(int i) {
        if (VERSION.SDK_INT >= 21) {
            this.mInfo.setMaxTextLength(i);
        }
    }

    public int getMaxTextLength() {
        return VERSION.SDK_INT >= 21 ? this.mInfo.getMaxTextLength() : -1;
    }

    public void setTextSelection(int i, int i2) {
        if (VERSION.SDK_INT >= 18) {
            this.mInfo.setTextSelection(i, i2);
        }
    }

    public int getTextSelectionStart() {
        return VERSION.SDK_INT >= 18 ? this.mInfo.getTextSelectionStart() : -1;
    }

    public int getTextSelectionEnd() {
        return VERSION.SDK_INT >= 18 ? this.mInfo.getTextSelectionEnd() : -1;
    }

    public AccessibilityNodeInfoCompat getTraversalBefore() {
        return VERSION.SDK_INT >= 22 ? wrapNonNullInstance(this.mInfo.getTraversalBefore()) : null;
    }

    public void setTraversalBefore(View view) {
        if (VERSION.SDK_INT >= 22) {
            this.mInfo.setTraversalBefore(view);
        }
    }

    public void setTraversalBefore(View view, int i) {
        if (VERSION.SDK_INT >= 22) {
            this.mInfo.setTraversalBefore(view, i);
        }
    }

    public AccessibilityNodeInfoCompat getTraversalAfter() {
        return VERSION.SDK_INT >= 22 ? wrapNonNullInstance(this.mInfo.getTraversalAfter()) : null;
    }

    public void setTraversalAfter(View view) {
        if (VERSION.SDK_INT >= 22) {
            this.mInfo.setTraversalAfter(view);
        }
    }

    public void setTraversalAfter(View view, int i) {
        if (VERSION.SDK_INT >= 22) {
            this.mInfo.setTraversalAfter(view, i);
        }
    }

    public AccessibilityWindowInfoCompat getWindow() {
        return VERSION.SDK_INT >= 21 ? AccessibilityWindowInfoCompat.wrapNonNullInstance(this.mInfo.getWindow()) : null;
    }

    public boolean isDismissable() {
        return VERSION.SDK_INT >= 19 ? this.mInfo.isDismissable() : false;
    }

    public void setDismissable(boolean z) {
        if (VERSION.SDK_INT >= 19) {
            this.mInfo.setDismissable(z);
        }
    }

    public boolean isEditable() {
        return VERSION.SDK_INT >= 18 ? this.mInfo.isEditable() : false;
    }

    public void setEditable(boolean z) {
        if (VERSION.SDK_INT >= 18) {
            this.mInfo.setEditable(z);
        }
    }

    public boolean isMultiLine() {
        return VERSION.SDK_INT >= 19 ? this.mInfo.isMultiLine() : false;
    }

    public void setMultiLine(boolean z) {
        if (VERSION.SDK_INT >= 19) {
            this.mInfo.setMultiLine(z);
        }
    }

    @Nullable
    public CharSequence getTooltipText() {
        if (VERSION.SDK_INT >= 28) {
            return this.mInfo.getTooltipText();
        }
        return VERSION.SDK_INT >= 19 ? this.mInfo.getExtras().getCharSequence(TOOLTIP_TEXT_KEY) : null;
    }

    public void setTooltipText(@Nullable CharSequence charSequence) {
        if (VERSION.SDK_INT >= 28) {
            this.mInfo.setTooltipText(charSequence);
        } else if (VERSION.SDK_INT >= 19) {
            this.mInfo.getExtras().putCharSequence(TOOLTIP_TEXT_KEY, charSequence);
        }
    }

    public void setPaneTitle(@Nullable CharSequence charSequence) {
        if (VERSION.SDK_INT >= 28) {
            this.mInfo.setPaneTitle(charSequence);
        } else if (VERSION.SDK_INT >= 19) {
            this.mInfo.getExtras().putCharSequence(PANE_TITLE_KEY, charSequence);
        }
    }

    @Nullable
    public CharSequence getPaneTitle() {
        if (VERSION.SDK_INT >= 28) {
            return this.mInfo.getPaneTitle();
        }
        return VERSION.SDK_INT >= 19 ? this.mInfo.getExtras().getCharSequence(PANE_TITLE_KEY) : null;
    }

    public boolean isScreenReaderFocusable() {
        if (VERSION.SDK_INT >= 28) {
            return this.mInfo.isScreenReaderFocusable();
        }
        return getBooleanProperty(1);
    }

    public void setScreenReaderFocusable(boolean z) {
        if (VERSION.SDK_INT >= 28) {
            this.mInfo.setScreenReaderFocusable(z);
        } else {
            setBooleanProperty(1, z);
        }
    }

    public boolean isShowingHintText() {
        if (VERSION.SDK_INT >= 26) {
            return this.mInfo.isShowingHintText();
        }
        return getBooleanProperty(4);
    }

    public void setShowingHintText(boolean z) {
        if (VERSION.SDK_INT >= 26) {
            this.mInfo.setShowingHintText(z);
        } else {
            setBooleanProperty(4, z);
        }
    }

    public boolean isHeading() {
        if (VERSION.SDK_INT >= 28) {
            return this.mInfo.isHeading();
        }
        boolean z = true;
        if (getBooleanProperty(2)) {
            return true;
        }
        CollectionItemInfoCompat collectionItemInfo = getCollectionItemInfo();
        if (collectionItemInfo == null || !collectionItemInfo.isHeading()) {
            z = false;
        }
        return z;
    }

    public void setHeading(boolean z) {
        if (VERSION.SDK_INT >= 28) {
            this.mInfo.setHeading(z);
        } else {
            setBooleanProperty(2, z);
        }
    }

    public boolean refresh() {
        return VERSION.SDK_INT >= 18 ? this.mInfo.refresh() : false;
    }

    @Nullable
    public CharSequence getRoleDescription() {
        return VERSION.SDK_INT >= 19 ? this.mInfo.getExtras().getCharSequence(ROLE_DESCRIPTION_KEY) : null;
    }

    public void setRoleDescription(@Nullable CharSequence charSequence) {
        if (VERSION.SDK_INT >= 19) {
            this.mInfo.getExtras().putCharSequence(ROLE_DESCRIPTION_KEY, charSequence);
        }
    }

    public int hashCode() {
        return this.mInfo == null ? 0 : this.mInfo.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
        if (this.mInfo == null) {
            if (accessibilityNodeInfoCompat.mInfo != null) {
                return false;
            }
        } else if (this.mInfo.equals(accessibilityNodeInfoCompat.mInfo) == null) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        Rect rect = new Rect();
        getBoundsInParent(rect);
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("; boundsInParent: ");
        stringBuilder2.append(rect);
        stringBuilder.append(stringBuilder2.toString());
        getBoundsInScreen(rect);
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("; boundsInScreen: ");
        stringBuilder2.append(rect);
        stringBuilder.append(stringBuilder2.toString());
        stringBuilder.append("; packageName: ");
        stringBuilder.append(getPackageName());
        stringBuilder.append("; className: ");
        stringBuilder.append(getClassName());
        stringBuilder.append("; text: ");
        stringBuilder.append(getText());
        stringBuilder.append("; contentDescription: ");
        stringBuilder.append(getContentDescription());
        stringBuilder.append("; viewId: ");
        stringBuilder.append(getViewIdResourceName());
        stringBuilder.append("; checkable: ");
        stringBuilder.append(isCheckable());
        stringBuilder.append("; checked: ");
        stringBuilder.append(isChecked());
        stringBuilder.append("; focusable: ");
        stringBuilder.append(isFocusable());
        stringBuilder.append("; focused: ");
        stringBuilder.append(isFocused());
        stringBuilder.append("; selected: ");
        stringBuilder.append(isSelected());
        stringBuilder.append("; clickable: ");
        stringBuilder.append(isClickable());
        stringBuilder.append("; longClickable: ");
        stringBuilder.append(isLongClickable());
        stringBuilder.append("; enabled: ");
        stringBuilder.append(isEnabled());
        stringBuilder.append("; password: ");
        stringBuilder.append(isPassword());
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("; scrollable: ");
        stringBuilder3.append(isScrollable());
        stringBuilder.append(stringBuilder3.toString());
        stringBuilder.append("; [");
        int actions = getActions();
        while (actions != 0) {
            int numberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(actions);
            actions &= ~numberOfTrailingZeros;
            stringBuilder.append(getActionSymbolicName(numberOfTrailingZeros));
            if (actions != 0) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private void setBooleanProperty(int i, boolean z) {
        Bundle extras = getExtras();
        if (extras != null) {
            int i2 = extras.getInt(BOOLEAN_PROPERTY_KEY, 0) & (~i);
            if (!z) {
                i = 0;
            }
            extras.putInt(BOOLEAN_PROPERTY_KEY, i | i2);
        }
    }

    private boolean getBooleanProperty(int i) {
        Bundle extras = getExtras();
        boolean z = false;
        if (extras == null) {
            return false;
        }
        if ((extras.getInt(BOOLEAN_PROPERTY_KEY, 0) & i) == i) {
            z = true;
        }
        return z;
    }
}
