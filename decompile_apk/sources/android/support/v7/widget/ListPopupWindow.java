package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.StyleRes;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.appcompat.C0192R;
import android.support.v7.view.menu.ShowableListMenu;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import java.lang.reflect.Method;

public class ListPopupWindow implements ShowableListMenu {
    private static final boolean DEBUG = false;
    static final int EXPAND_LIST_TIMEOUT = 250;
    public static final int INPUT_METHOD_FROM_FOCUSABLE = 0;
    public static final int INPUT_METHOD_NEEDED = 1;
    public static final int INPUT_METHOD_NOT_NEEDED = 2;
    public static final int MATCH_PARENT = -1;
    public static final int POSITION_PROMPT_ABOVE = 0;
    public static final int POSITION_PROMPT_BELOW = 1;
    private static final String TAG = "ListPopupWindow";
    public static final int WRAP_CONTENT = -2;
    private static Method sClipToWindowEnabledMethod;
    private static Method sGetMaxAvailableHeightMethod;
    private static Method sSetEpicenterBoundsMethod;
    private ListAdapter mAdapter;
    private Context mContext;
    private boolean mDropDownAlwaysVisible;
    private View mDropDownAnchorView;
    private int mDropDownGravity;
    private int mDropDownHeight;
    private int mDropDownHorizontalOffset;
    DropDownListView mDropDownList;
    private Drawable mDropDownListHighlight;
    private int mDropDownVerticalOffset;
    private boolean mDropDownVerticalOffsetSet;
    private int mDropDownWidth;
    private int mDropDownWindowLayoutType;
    private Rect mEpicenterBounds;
    private boolean mForceIgnoreOutsideTouch;
    final Handler mHandler;
    private final ListSelectorHider mHideSelector;
    private boolean mIsAnimatedFromAnchor;
    private OnItemClickListener mItemClickListener;
    private OnItemSelectedListener mItemSelectedListener;
    int mListItemExpandMaximum;
    private boolean mModal;
    private DataSetObserver mObserver;
    private boolean mOverlapAnchor;
    private boolean mOverlapAnchorSet;
    PopupWindow mPopup;
    private int mPromptPosition;
    private View mPromptView;
    final ResizePopupRunnable mResizePopupRunnable;
    private final PopupScrollListener mScrollListener;
    private Runnable mShowDropDownRunnable;
    private final Rect mTempRect;
    private final PopupTouchInterceptor mTouchInterceptor;

    /* renamed from: android.support.v7.widget.ListPopupWindow$2 */
    class C02152 implements Runnable {
        C02152() {
        }

        public void run() {
            View anchorView = ListPopupWindow.this.getAnchorView();
            if (anchorView != null && anchorView.getWindowToken() != null) {
                ListPopupWindow.this.show();
            }
        }
    }

    /* renamed from: android.support.v7.widget.ListPopupWindow$3 */
    class C02163 implements OnItemSelectedListener {
        public void onNothingSelected(AdapterView<?> adapterView) {
        }

        C02163() {
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            if (i != -1) {
                adapterView = ListPopupWindow.this.mDropDownList;
                if (adapterView != null) {
                    adapterView.setListSelectionHidden(null);
                }
            }
        }
    }

    private class ListSelectorHider implements Runnable {
        ListSelectorHider() {
        }

        public void run() {
            ListPopupWindow.this.clearListSelection();
        }
    }

    private class PopupDataSetObserver extends DataSetObserver {
        PopupDataSetObserver() {
        }

        public void onChanged() {
            if (ListPopupWindow.this.isShowing()) {
                ListPopupWindow.this.show();
            }
        }

        public void onInvalidated() {
            ListPopupWindow.this.dismiss();
        }
    }

    private class PopupScrollListener implements OnScrollListener {
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        PopupScrollListener() {
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i == 1 && ListPopupWindow.this.isInputMethodNotNeeded() == null && ListPopupWindow.this.mPopup.getContentView() != null) {
                ListPopupWindow.this.mHandler.removeCallbacks(ListPopupWindow.this.mResizePopupRunnable);
                ListPopupWindow.this.mResizePopupRunnable.run();
            }
        }
    }

    private class PopupTouchInterceptor implements OnTouchListener {
        PopupTouchInterceptor() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            view = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            motionEvent = (int) motionEvent.getY();
            if (view == null && ListPopupWindow.this.mPopup != null && ListPopupWindow.this.mPopup.isShowing() && x >= 0 && x < ListPopupWindow.this.mPopup.getWidth() && motionEvent >= null && motionEvent < ListPopupWindow.this.mPopup.getHeight()) {
                ListPopupWindow.this.mHandler.postDelayed(ListPopupWindow.this.mResizePopupRunnable, 250);
            } else if (view == 1) {
                ListPopupWindow.this.mHandler.removeCallbacks(ListPopupWindow.this.mResizePopupRunnable);
            }
            return null;
        }
    }

    private class ResizePopupRunnable implements Runnable {
        ResizePopupRunnable() {
        }

        public void run() {
            if (ListPopupWindow.this.mDropDownList != null && ViewCompat.isAttachedToWindow(ListPopupWindow.this.mDropDownList) && ListPopupWindow.this.mDropDownList.getCount() > ListPopupWindow.this.mDropDownList.getChildCount() && ListPopupWindow.this.mDropDownList.getChildCount() <= ListPopupWindow.this.mListItemExpandMaximum) {
                ListPopupWindow.this.mPopup.setInputMethodMode(2);
                ListPopupWindow.this.show();
            }
        }
    }

    private static boolean isConfirmKey(int i) {
        if (i != 66) {
            if (i != 23) {
                return false;
            }
        }
        return true;
    }

    static {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = 0;
        r1 = 1;
        r2 = android.widget.PopupWindow.class;	 Catch:{ NoSuchMethodException -> 0x0013 }
        r3 = "setClipToScreenEnabled";	 Catch:{ NoSuchMethodException -> 0x0013 }
        r4 = new java.lang.Class[r1];	 Catch:{ NoSuchMethodException -> 0x0013 }
        r5 = java.lang.Boolean.TYPE;	 Catch:{ NoSuchMethodException -> 0x0013 }
        r4[r0] = r5;	 Catch:{ NoSuchMethodException -> 0x0013 }
        r2 = r2.getDeclaredMethod(r3, r4);	 Catch:{ NoSuchMethodException -> 0x0013 }
        sClipToWindowEnabledMethod = r2;	 Catch:{ NoSuchMethodException -> 0x0013 }
        goto L_0x001a;
    L_0x0013:
        r2 = "ListPopupWindow";
        r3 = "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.";
        android.util.Log.i(r2, r3);
    L_0x001a:
        r2 = android.widget.PopupWindow.class;	 Catch:{ NoSuchMethodException -> 0x0035 }
        r3 = "getMaxAvailableHeight";	 Catch:{ NoSuchMethodException -> 0x0035 }
        r4 = 3;	 Catch:{ NoSuchMethodException -> 0x0035 }
        r4 = new java.lang.Class[r4];	 Catch:{ NoSuchMethodException -> 0x0035 }
        r5 = android.view.View.class;	 Catch:{ NoSuchMethodException -> 0x0035 }
        r4[r0] = r5;	 Catch:{ NoSuchMethodException -> 0x0035 }
        r5 = java.lang.Integer.TYPE;	 Catch:{ NoSuchMethodException -> 0x0035 }
        r4[r1] = r5;	 Catch:{ NoSuchMethodException -> 0x0035 }
        r5 = 2;	 Catch:{ NoSuchMethodException -> 0x0035 }
        r6 = java.lang.Boolean.TYPE;	 Catch:{ NoSuchMethodException -> 0x0035 }
        r4[r5] = r6;	 Catch:{ NoSuchMethodException -> 0x0035 }
        r2 = r2.getDeclaredMethod(r3, r4);	 Catch:{ NoSuchMethodException -> 0x0035 }
        sGetMaxAvailableHeightMethod = r2;	 Catch:{ NoSuchMethodException -> 0x0035 }
        goto L_0x003c;
    L_0x0035:
        r2 = "ListPopupWindow";
        r3 = "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.";
        android.util.Log.i(r2, r3);
    L_0x003c:
        r2 = android.widget.PopupWindow.class;	 Catch:{ NoSuchMethodException -> 0x004d }
        r3 = "setEpicenterBounds";	 Catch:{ NoSuchMethodException -> 0x004d }
        r1 = new java.lang.Class[r1];	 Catch:{ NoSuchMethodException -> 0x004d }
        r4 = android.graphics.Rect.class;	 Catch:{ NoSuchMethodException -> 0x004d }
        r1[r0] = r4;	 Catch:{ NoSuchMethodException -> 0x004d }
        r0 = r2.getDeclaredMethod(r3, r1);	 Catch:{ NoSuchMethodException -> 0x004d }
        sSetEpicenterBoundsMethod = r0;	 Catch:{ NoSuchMethodException -> 0x004d }
        goto L_0x0054;
    L_0x004d:
        r0 = "ListPopupWindow";
        r1 = "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.";
        android.util.Log.i(r0, r1);
    L_0x0054:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ListPopupWindow.<clinit>():void");
    }

    public ListPopupWindow(@NonNull Context context) {
        this(context, null, C0192R.attr.listPopupWindowStyle);
    }

    public ListPopupWindow(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, C0192R.attr.listPopupWindowStyle);
    }

    public ListPopupWindow(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        this(context, attributeSet, i, 0);
    }

    public ListPopupWindow(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        this.mDropDownHeight = -2;
        this.mDropDownWidth = -2;
        this.mDropDownWindowLayoutType = PointerIconCompat.TYPE_HAND;
        this.mIsAnimatedFromAnchor = true;
        this.mDropDownGravity = 0;
        this.mDropDownAlwaysVisible = false;
        this.mForceIgnoreOutsideTouch = false;
        this.mListItemExpandMaximum = Integer.MAX_VALUE;
        this.mPromptPosition = 0;
        this.mResizePopupRunnable = new ResizePopupRunnable();
        this.mTouchInterceptor = new PopupTouchInterceptor();
        this.mScrollListener = new PopupScrollListener();
        this.mHideSelector = new ListSelectorHider();
        this.mTempRect = new Rect();
        this.mContext = context;
        this.mHandler = new Handler(context.getMainLooper());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0192R.styleable.ListPopupWindow, i, i2);
        this.mDropDownHorizontalOffset = obtainStyledAttributes.getDimensionPixelOffset(C0192R.styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        this.mDropDownVerticalOffset = obtainStyledAttributes.getDimensionPixelOffset(C0192R.styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        if (this.mDropDownVerticalOffset != 0) {
            this.mDropDownVerticalOffsetSet = true;
        }
        obtainStyledAttributes.recycle();
        this.mPopup = new AppCompatPopupWindow(context, attributeSet, i, i2);
        this.mPopup.setInputMethodMode(1);
    }

    public void setAdapter(@Nullable ListAdapter listAdapter) {
        if (this.mObserver == null) {
            this.mObserver = new PopupDataSetObserver();
        } else if (this.mAdapter != null) {
            this.mAdapter.unregisterDataSetObserver(this.mObserver);
        }
        this.mAdapter = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.mObserver);
        }
        if (this.mDropDownList != null) {
            this.mDropDownList.setAdapter(this.mAdapter);
        }
    }

    public void setPromptPosition(int i) {
        this.mPromptPosition = i;
    }

    public int getPromptPosition() {
        return this.mPromptPosition;
    }

    public void setModal(boolean z) {
        this.mModal = z;
        this.mPopup.setFocusable(z);
    }

    public boolean isModal() {
        return this.mModal;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setForceIgnoreOutsideTouch(boolean z) {
        this.mForceIgnoreOutsideTouch = z;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setDropDownAlwaysVisible(boolean z) {
        this.mDropDownAlwaysVisible = z;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public boolean isDropDownAlwaysVisible() {
        return this.mDropDownAlwaysVisible;
    }

    public void setSoftInputMode(int i) {
        this.mPopup.setSoftInputMode(i);
    }

    public int getSoftInputMode() {
        return this.mPopup.getSoftInputMode();
    }

    public void setListSelector(Drawable drawable) {
        this.mDropDownListHighlight = drawable;
    }

    @Nullable
    public Drawable getBackground() {
        return this.mPopup.getBackground();
    }

    public void setBackgroundDrawable(@Nullable Drawable drawable) {
        this.mPopup.setBackgroundDrawable(drawable);
    }

    public void setAnimationStyle(@StyleRes int i) {
        this.mPopup.setAnimationStyle(i);
    }

    @StyleRes
    public int getAnimationStyle() {
        return this.mPopup.getAnimationStyle();
    }

    @Nullable
    public View getAnchorView() {
        return this.mDropDownAnchorView;
    }

    public void setAnchorView(@Nullable View view) {
        this.mDropDownAnchorView = view;
    }

    public int getHorizontalOffset() {
        return this.mDropDownHorizontalOffset;
    }

    public void setHorizontalOffset(int i) {
        this.mDropDownHorizontalOffset = i;
    }

    public int getVerticalOffset() {
        if (this.mDropDownVerticalOffsetSet) {
            return this.mDropDownVerticalOffset;
        }
        return 0;
    }

    public void setVerticalOffset(int i) {
        this.mDropDownVerticalOffset = i;
        this.mDropDownVerticalOffsetSet = true;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setEpicenterBounds(Rect rect) {
        this.mEpicenterBounds = rect;
    }

    public void setDropDownGravity(int i) {
        this.mDropDownGravity = i;
    }

    public int getWidth() {
        return this.mDropDownWidth;
    }

    public void setWidth(int i) {
        this.mDropDownWidth = i;
    }

    public void setContentWidth(int i) {
        Drawable background = this.mPopup.getBackground();
        if (background != null) {
            background.getPadding(this.mTempRect);
            this.mDropDownWidth = (this.mTempRect.left + this.mTempRect.right) + i;
            return;
        }
        setWidth(i);
    }

    public int getHeight() {
        return this.mDropDownHeight;
    }

    public void setHeight(int i) {
        if (i < 0 && -2 != i) {
            if (-1 != i) {
                throw new IllegalArgumentException("Invalid height. Must be a positive value, MATCH_PARENT, or WRAP_CONTENT.");
            }
        }
        this.mDropDownHeight = i;
    }

    public void setWindowLayoutType(int i) {
        this.mDropDownWindowLayoutType = i;
    }

    public void setOnItemClickListener(@Nullable OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }

    public void setOnItemSelectedListener(@Nullable OnItemSelectedListener onItemSelectedListener) {
        this.mItemSelectedListener = onItemSelectedListener;
    }

    public void setPromptView(@Nullable View view) {
        boolean isShowing = isShowing();
        if (isShowing) {
            removePromptView();
        }
        this.mPromptView = view;
        if (isShowing) {
            show();
        }
    }

    public void postShow() {
        this.mHandler.post(this.mShowDropDownRunnable);
    }

    public void show() {
        int buildDropDown = buildDropDown();
        boolean isInputMethodNotNeeded = isInputMethodNotNeeded();
        PopupWindowCompat.setWindowLayoutType(this.mPopup, this.mDropDownWindowLayoutType);
        boolean z = true;
        if (!this.mPopup.isShowing()) {
            int i;
            if (this.mDropDownWidth == -1) {
                i = -1;
            } else if (this.mDropDownWidth == -2) {
                i = getAnchorView().getWidth();
            } else {
                i = this.mDropDownWidth;
            }
            if (this.mDropDownHeight == -1) {
                buildDropDown = -1;
            } else if (this.mDropDownHeight != -2) {
                buildDropDown = this.mDropDownHeight;
            }
            this.mPopup.setWidth(i);
            this.mPopup.setHeight(buildDropDown);
            setPopupClipToScreenEnabled(true);
            PopupWindow popupWindow = this.mPopup;
            isInputMethodNotNeeded = (this.mForceIgnoreOutsideTouch || this.mDropDownAlwaysVisible) ? false : true;
            popupWindow.setOutsideTouchable(isInputMethodNotNeeded);
            this.mPopup.setTouchInterceptor(this.mTouchInterceptor);
            if (this.mOverlapAnchorSet) {
                PopupWindowCompat.setOverlapAnchor(this.mPopup, this.mOverlapAnchor);
            }
            if (sSetEpicenterBoundsMethod != null) {
                try {
                    sSetEpicenterBoundsMethod.invoke(this.mPopup, new Object[]{this.mEpicenterBounds});
                } catch (Throwable e) {
                    Log.e(TAG, "Could not invoke setEpicenterBounds on PopupWindow", e);
                }
            }
            PopupWindowCompat.showAsDropDown(this.mPopup, getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, this.mDropDownGravity);
            this.mDropDownList.setSelection(-1);
            if (!this.mModal || this.mDropDownList.isInTouchMode()) {
                clearListSelection();
            }
            if (!this.mModal) {
                this.mHandler.post(this.mHideSelector);
            }
        } else if (ViewCompat.isAttachedToWindow(getAnchorView())) {
            int i2;
            if (this.mDropDownWidth == -1) {
                i2 = -1;
            } else if (this.mDropDownWidth == -2) {
                i2 = getAnchorView().getWidth();
            } else {
                i2 = this.mDropDownWidth;
            }
            if (this.mDropDownHeight == -1) {
                if (!isInputMethodNotNeeded) {
                    buildDropDown = -1;
                }
                if (isInputMethodNotNeeded) {
                    this.mPopup.setWidth(this.mDropDownWidth == -1 ? -1 : 0);
                    this.mPopup.setHeight(0);
                } else {
                    this.mPopup.setWidth(this.mDropDownWidth == -1 ? -1 : 0);
                    this.mPopup.setHeight(-1);
                }
            } else if (this.mDropDownHeight != -2) {
                buildDropDown = this.mDropDownHeight;
            }
            PopupWindow popupWindow2 = this.mPopup;
            if (this.mForceIgnoreOutsideTouch || this.mDropDownAlwaysVisible) {
                z = false;
            }
            popupWindow2.setOutsideTouchable(z);
            this.mPopup.update(getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, i2 < 0 ? -1 : i2, buildDropDown < 0 ? -1 : buildDropDown);
        }
    }

    public void dismiss() {
        this.mPopup.dismiss();
        removePromptView();
        this.mPopup.setContentView(null);
        this.mDropDownList = null;
        this.mHandler.removeCallbacks(this.mResizePopupRunnable);
    }

    public void setOnDismissListener(@Nullable OnDismissListener onDismissListener) {
        this.mPopup.setOnDismissListener(onDismissListener);
    }

    private void removePromptView() {
        if (this.mPromptView != null) {
            ViewParent parent = this.mPromptView.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.mPromptView);
            }
        }
    }

    public void setInputMethodMode(int i) {
        this.mPopup.setInputMethodMode(i);
    }

    public int getInputMethodMode() {
        return this.mPopup.getInputMethodMode();
    }

    public void setSelection(int i) {
        DropDownListView dropDownListView = this.mDropDownList;
        if (isShowing() && dropDownListView != null) {
            dropDownListView.setListSelectionHidden(false);
            dropDownListView.setSelection(i);
            if (dropDownListView.getChoiceMode() != 0) {
                dropDownListView.setItemChecked(i, true);
            }
        }
    }

    public void clearListSelection() {
        DropDownListView dropDownListView = this.mDropDownList;
        if (dropDownListView != null) {
            dropDownListView.setListSelectionHidden(true);
            dropDownListView.requestLayout();
        }
    }

    public boolean isShowing() {
        return this.mPopup.isShowing();
    }

    public boolean isInputMethodNotNeeded() {
        return this.mPopup.getInputMethodMode() == 2;
    }

    public boolean performItemClick(int i) {
        if (!isShowing()) {
            return false;
        }
        if (this.mItemClickListener != null) {
            AdapterView adapterView = this.mDropDownList;
            int i2 = i;
            this.mItemClickListener.onItemClick(adapterView, adapterView.getChildAt(i - adapterView.getFirstVisiblePosition()), i2, adapterView.getAdapter().getItemId(i));
        }
        return true;
    }

    @Nullable
    public Object getSelectedItem() {
        if (isShowing()) {
            return this.mDropDownList.getSelectedItem();
        }
        return null;
    }

    public int getSelectedItemPosition() {
        if (isShowing()) {
            return this.mDropDownList.getSelectedItemPosition();
        }
        return -1;
    }

    public long getSelectedItemId() {
        if (isShowing()) {
            return this.mDropDownList.getSelectedItemId();
        }
        return Long.MIN_VALUE;
    }

    @Nullable
    public View getSelectedView() {
        if (isShowing()) {
            return this.mDropDownList.getSelectedView();
        }
        return null;
    }

    @Nullable
    public ListView getListView() {
        return this.mDropDownList;
    }

    @NonNull
    DropDownListView createDropDownListView(Context context, boolean z) {
        return new DropDownListView(context, z);
    }

    void setListItemExpandMax(int i) {
        this.mListItemExpandMaximum = i;
    }

    public boolean onKeyDown(int i, @NonNull KeyEvent keyEvent) {
        if (isShowing() && i != 62 && (this.mDropDownList.getSelectedItemPosition() >= 0 || !isConfirmKey(i))) {
            int count;
            int selectedItemPosition = this.mDropDownList.getSelectedItemPosition();
            int isAboveAnchor = this.mPopup.isAboveAnchor() ^ 1;
            ListAdapter listAdapter = this.mAdapter;
            int i2 = Integer.MAX_VALUE;
            if (listAdapter != null) {
                int i3;
                boolean areAllItemsEnabled = listAdapter.areAllItemsEnabled();
                if (areAllItemsEnabled) {
                    i3 = 0;
                } else {
                    i3 = this.mDropDownList.lookForSelectablePosition(0, true);
                }
                if (areAllItemsEnabled) {
                    count = listAdapter.getCount() - 1;
                } else {
                    count = this.mDropDownList.lookForSelectablePosition(listAdapter.getCount() - 1, false);
                }
                i2 = i3;
            } else {
                count = Integer.MIN_VALUE;
            }
            if ((isAboveAnchor == 0 || i != 19 || selectedItemPosition > r5) && !(isAboveAnchor == 0 && i == 20 && selectedItemPosition >= count)) {
                this.mDropDownList.setListSelectionHidden(false);
                if (this.mDropDownList.onKeyDown(i, keyEvent) != null) {
                    this.mPopup.setInputMethodMode(2);
                    this.mDropDownList.requestFocusFromTouch();
                    show();
                    if (!(i == 23 || i == 66)) {
                        switch (i) {
                            case 19:
                            case 20:
                                break;
                            default:
                                break;
                        }
                    }
                    return true;
                } else if (isAboveAnchor == 0 || i != 20) {
                    if (isAboveAnchor == 0 && i == 19 && selectedItemPosition == r5) {
                        return true;
                    }
                    return false;
                } else if (selectedItemPosition == count) {
                    return true;
                }
            }
            clearListSelection();
            this.mPopup.setInputMethodMode(1);
            show();
            return true;
        }
        return false;
    }

    public boolean onKeyUp(int i, @NonNull KeyEvent keyEvent) {
        if (!isShowing() || this.mDropDownList.getSelectedItemPosition() < 0) {
            return false;
        }
        keyEvent = this.mDropDownList.onKeyUp(i, keyEvent);
        if (!(keyEvent == null || isConfirmKey(i) == 0)) {
            dismiss();
        }
        return keyEvent;
    }

    public boolean onKeyPreIme(int i, @NonNull KeyEvent keyEvent) {
        if (i == 4 && isShowing() != 0) {
            i = this.mDropDownAnchorView;
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                i = i.getKeyDispatcherState();
                if (i != 0) {
                    i.startTracking(keyEvent, this);
                }
                return true;
            } else if (keyEvent.getAction() == 1) {
                i = i.getKeyDispatcherState();
                if (i != 0) {
                    i.handleUpEvent(keyEvent);
                }
                if (keyEvent.isTracking() != 0 && keyEvent.isCanceled() == 0) {
                    dismiss();
                    return true;
                }
            }
        }
        return false;
    }

    public OnTouchListener createDragToOpenListener(View view) {
        return new ForwardingListener(view) {
            public ListPopupWindow getPopup() {
                return ListPopupWindow.this;
            }
        };
    }

    private int buildDropDown() {
        int i;
        int i2;
        boolean z = true;
        if (this.mDropDownList == null) {
            Context context = this.mContext;
            this.mShowDropDownRunnable = new C02152();
            this.mDropDownList = createDropDownListView(context, this.mModal ^ true);
            if (this.mDropDownListHighlight != null) {
                this.mDropDownList.setSelector(this.mDropDownListHighlight);
            }
            this.mDropDownList.setAdapter(this.mAdapter);
            this.mDropDownList.setOnItemClickListener(this.mItemClickListener);
            this.mDropDownList.setFocusable(true);
            this.mDropDownList.setFocusableInTouchMode(true);
            this.mDropDownList.setOnItemSelectedListener(new C02163());
            this.mDropDownList.setOnScrollListener(this.mScrollListener);
            if (this.mItemSelectedListener != null) {
                this.mDropDownList.setOnItemSelectedListener(this.mItemSelectedListener);
            }
            View view = this.mDropDownList;
            View view2 = this.mPromptView;
            if (view2 != null) {
                View linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0, 1.0f);
                switch (this.mPromptPosition) {
                    case 0:
                        linearLayout.addView(view2);
                        linearLayout.addView(view, layoutParams);
                        break;
                    case 1:
                        linearLayout.addView(view, layoutParams);
                        linearLayout.addView(view2);
                        break;
                    default:
                        String str = TAG;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Invalid hint position ");
                        stringBuilder.append(this.mPromptPosition);
                        Log.e(str, stringBuilder.toString());
                        break;
                }
                if (this.mDropDownWidth >= 0) {
                    i = this.mDropDownWidth;
                    i2 = Integer.MIN_VALUE;
                } else {
                    i = 0;
                    i2 = 0;
                }
                view2.measure(MeasureSpec.makeMeasureSpec(i, i2), 0);
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) view2.getLayoutParams();
                i = (view2.getMeasuredHeight() + layoutParams2.topMargin) + layoutParams2.bottomMargin;
                view = linearLayout;
            } else {
                i = 0;
            }
            this.mPopup.setContentView(view);
        } else {
            ViewGroup viewGroup = (ViewGroup) this.mPopup.getContentView();
            View view3 = this.mPromptView;
            if (view3 != null) {
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) view3.getLayoutParams();
                i = (view3.getMeasuredHeight() + layoutParams3.topMargin) + layoutParams3.bottomMargin;
            } else {
                i = 0;
            }
        }
        Drawable background = this.mPopup.getBackground();
        if (background != null) {
            background.getPadding(this.mTempRect);
            i2 = this.mTempRect.top + this.mTempRect.bottom;
            if (!this.mDropDownVerticalOffsetSet) {
                this.mDropDownVerticalOffset = -this.mTempRect.top;
            }
        } else {
            this.mTempRect.setEmpty();
            i2 = 0;
        }
        if (this.mPopup.getInputMethodMode() != 2) {
            z = false;
        }
        int maxAvailableHeight = getMaxAvailableHeight(getAnchorView(), this.mDropDownVerticalOffset, z);
        if (!this.mDropDownAlwaysVisible) {
            if (this.mDropDownHeight != -1) {
                int makeMeasureSpec;
                switch (this.mDropDownWidth) {
                    case -2:
                        makeMeasureSpec = MeasureSpec.makeMeasureSpec(this.mContext.getResources().getDisplayMetrics().widthPixels - (this.mTempRect.left + this.mTempRect.right), Integer.MIN_VALUE);
                        break;
                    case -1:
                        makeMeasureSpec = MeasureSpec.makeMeasureSpec(this.mContext.getResources().getDisplayMetrics().widthPixels - (this.mTempRect.left + this.mTempRect.right), 1073741824);
                        break;
                    default:
                        makeMeasureSpec = MeasureSpec.makeMeasureSpec(this.mDropDownWidth, 1073741824);
                        break;
                }
                makeMeasureSpec = this.mDropDownList.measureHeightOfChildrenCompat(makeMeasureSpec, 0, -1, maxAvailableHeight - i, -1);
                if (makeMeasureSpec > 0) {
                    i += i2 + (this.mDropDownList.getPaddingTop() + this.mDropDownList.getPaddingBottom());
                }
                return makeMeasureSpec + i;
            }
        }
        return maxAvailableHeight + i2;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setOverlapAnchor(boolean z) {
        this.mOverlapAnchorSet = true;
        this.mOverlapAnchor = z;
    }

    private void setPopupClipToScreenEnabled(boolean r5) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r4 = this;
        r0 = sClipToWindowEnabledMethod;
        if (r0 == 0) goto L_0x001d;
    L_0x0004:
        r0 = sClipToWindowEnabledMethod;	 Catch:{ Exception -> 0x0016 }
        r1 = r4.mPopup;	 Catch:{ Exception -> 0x0016 }
        r2 = 1;	 Catch:{ Exception -> 0x0016 }
        r2 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x0016 }
        r3 = 0;	 Catch:{ Exception -> 0x0016 }
        r5 = java.lang.Boolean.valueOf(r5);	 Catch:{ Exception -> 0x0016 }
        r2[r3] = r5;	 Catch:{ Exception -> 0x0016 }
        r0.invoke(r1, r2);	 Catch:{ Exception -> 0x0016 }
        goto L_0x001d;
    L_0x0016:
        r5 = "ListPopupWindow";
        r0 = "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.";
        android.util.Log.i(r5, r0);
    L_0x001d:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ListPopupWindow.setPopupClipToScreenEnabled(boolean):void");
    }

    private int getMaxAvailableHeight(android.view.View r6, int r7, boolean r8) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r5 = this;
        r0 = sGetMaxAvailableHeightMethod;
        if (r0 == 0) goto L_0x002e;
    L_0x0004:
        r0 = sGetMaxAvailableHeightMethod;	 Catch:{ Exception -> 0x0027 }
        r1 = r5.mPopup;	 Catch:{ Exception -> 0x0027 }
        r2 = 3;	 Catch:{ Exception -> 0x0027 }
        r2 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x0027 }
        r3 = 0;	 Catch:{ Exception -> 0x0027 }
        r2[r3] = r6;	 Catch:{ Exception -> 0x0027 }
        r3 = 1;	 Catch:{ Exception -> 0x0027 }
        r4 = java.lang.Integer.valueOf(r7);	 Catch:{ Exception -> 0x0027 }
        r2[r3] = r4;	 Catch:{ Exception -> 0x0027 }
        r3 = 2;	 Catch:{ Exception -> 0x0027 }
        r8 = java.lang.Boolean.valueOf(r8);	 Catch:{ Exception -> 0x0027 }
        r2[r3] = r8;	 Catch:{ Exception -> 0x0027 }
        r8 = r0.invoke(r1, r2);	 Catch:{ Exception -> 0x0027 }
        r8 = (java.lang.Integer) r8;	 Catch:{ Exception -> 0x0027 }
        r8 = r8.intValue();	 Catch:{ Exception -> 0x0027 }
        return r8;
    L_0x0027:
        r8 = "ListPopupWindow";
        r0 = "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.";
        android.util.Log.i(r8, r0);
    L_0x002e:
        r8 = r5.mPopup;
        r6 = r8.getMaxAvailableHeight(r6, r7);
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ListPopupWindow.getMaxAvailableHeight(android.view.View, int, boolean):int");
    }
}
