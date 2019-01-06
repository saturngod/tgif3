package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class SlidingPaneLayout extends ViewGroup {
    private static final int DEFAULT_FADE_COLOR = -858993460;
    private static final int DEFAULT_OVERHANG_SIZE = 32;
    private static final int MIN_FLING_VELOCITY = 400;
    private static final String TAG = "SlidingPaneLayout";
    private boolean mCanSlide;
    private int mCoveredFadeColor;
    private boolean mDisplayListReflectionLoaded;
    final ViewDragHelper mDragHelper;
    private boolean mFirstLayout;
    private Method mGetDisplayList;
    private float mInitialMotionX;
    private float mInitialMotionY;
    boolean mIsUnableToDrag;
    private final int mOverhangSize;
    private PanelSlideListener mPanelSlideListener;
    private int mParallaxBy;
    private float mParallaxOffset;
    final ArrayList<DisableLayerRunnable> mPostedRunnables;
    boolean mPreservedOpenState;
    private Field mRecreateDisplayList;
    private Drawable mShadowDrawableLeft;
    private Drawable mShadowDrawableRight;
    float mSlideOffset;
    int mSlideRange;
    View mSlideableView;
    private int mSliderFadeColor;
    private final Rect mTmpRect;

    private class DisableLayerRunnable implements Runnable {
        final View mChildView;

        DisableLayerRunnable(View view) {
            this.mChildView = view;
        }

        public void run() {
            if (this.mChildView.getParent() == SlidingPaneLayout.this) {
                this.mChildView.setLayerType(0, null);
                SlidingPaneLayout.this.invalidateChildRegion(this.mChildView);
            }
            SlidingPaneLayout.this.mPostedRunnables.remove(this);
        }
    }

    public static class LayoutParams extends MarginLayoutParams {
        private static final int[] ATTRS = new int[]{16843137};
        Paint dimPaint;
        boolean dimWhenOffset;
        boolean slideable;
        public float weight = 0.0f;

        public LayoutParams() {
            super(-1, -1);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(@NonNull android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(@NonNull MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(@NonNull LayoutParams layoutParams) {
            super(layoutParams);
            this.weight = layoutParams.weight;
        }

        public LayoutParams(@NonNull Context context, @Nullable AttributeSet attributeSet) {
            super(context, attributeSet);
            context = context.obtainStyledAttributes(attributeSet, ATTRS);
            this.weight = context.getFloat(null, 0.0f);
            context.recycle();
        }
    }

    public interface PanelSlideListener {
        void onPanelClosed(@NonNull View view);

        void onPanelOpened(@NonNull View view);

        void onPanelSlide(@NonNull View view, float f);
    }

    class AccessibilityDelegate extends AccessibilityDelegateCompat {
        private final Rect mTmpRect = new Rect();

        AccessibilityDelegate() {
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain(accessibilityNodeInfoCompat);
            super.onInitializeAccessibilityNodeInfo(view, obtain);
            copyNodeInfoNoChildren(accessibilityNodeInfoCompat, obtain);
            obtain.recycle();
            accessibilityNodeInfoCompat.setClassName(SlidingPaneLayout.class.getName());
            accessibilityNodeInfoCompat.setSource(view);
            view = ViewCompat.getParentForAccessibility(view);
            if (view instanceof View) {
                accessibilityNodeInfoCompat.setParent(view);
            }
            view = SlidingPaneLayout.this.getChildCount();
            for (int i = 0; i < view; i++) {
                View childAt = SlidingPaneLayout.this.getChildAt(i);
                if (!filter(childAt) && childAt.getVisibility() == 0) {
                    ViewCompat.setImportantForAccessibility(childAt, 1);
                    accessibilityNodeInfoCompat.addChild(childAt);
                }
            }
        }

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(SlidingPaneLayout.class.getName());
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return !filter(view) ? super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent) : null;
        }

        public boolean filter(View view) {
            return SlidingPaneLayout.this.isDimmed(view);
        }

        private void copyNodeInfoNoChildren(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2) {
            Rect rect = this.mTmpRect;
            accessibilityNodeInfoCompat2.getBoundsInParent(rect);
            accessibilityNodeInfoCompat.setBoundsInParent(rect);
            accessibilityNodeInfoCompat2.getBoundsInScreen(rect);
            accessibilityNodeInfoCompat.setBoundsInScreen(rect);
            accessibilityNodeInfoCompat.setVisibleToUser(accessibilityNodeInfoCompat2.isVisibleToUser());
            accessibilityNodeInfoCompat.setPackageName(accessibilityNodeInfoCompat2.getPackageName());
            accessibilityNodeInfoCompat.setClassName(accessibilityNodeInfoCompat2.getClassName());
            accessibilityNodeInfoCompat.setContentDescription(accessibilityNodeInfoCompat2.getContentDescription());
            accessibilityNodeInfoCompat.setEnabled(accessibilityNodeInfoCompat2.isEnabled());
            accessibilityNodeInfoCompat.setClickable(accessibilityNodeInfoCompat2.isClickable());
            accessibilityNodeInfoCompat.setFocusable(accessibilityNodeInfoCompat2.isFocusable());
            accessibilityNodeInfoCompat.setFocused(accessibilityNodeInfoCompat2.isFocused());
            accessibilityNodeInfoCompat.setAccessibilityFocused(accessibilityNodeInfoCompat2.isAccessibilityFocused());
            accessibilityNodeInfoCompat.setSelected(accessibilityNodeInfoCompat2.isSelected());
            accessibilityNodeInfoCompat.setLongClickable(accessibilityNodeInfoCompat2.isLongClickable());
            accessibilityNodeInfoCompat.addAction(accessibilityNodeInfoCompat2.getActions());
            accessibilityNodeInfoCompat.setMovementGranularities(accessibilityNodeInfoCompat2.getMovementGranularities());
        }
    }

    private class DragHelperCallback extends Callback {
        DragHelperCallback() {
        }

        public boolean tryCaptureView(View view, int i) {
            if (SlidingPaneLayout.this.mIsUnableToDrag != 0) {
                return null;
            }
            return ((LayoutParams) view.getLayoutParams()).slideable;
        }

        public void onViewDragStateChanged(int i) {
            if (SlidingPaneLayout.this.mDragHelper.getViewDragState() != 0) {
                return;
            }
            if (SlidingPaneLayout.this.mSlideOffset == 0) {
                SlidingPaneLayout.this.updateObscuredViewsVisibility(SlidingPaneLayout.this.mSlideableView);
                SlidingPaneLayout.this.dispatchOnPanelClosed(SlidingPaneLayout.this.mSlideableView);
                SlidingPaneLayout.this.mPreservedOpenState = false;
                return;
            }
            SlidingPaneLayout.this.dispatchOnPanelOpened(SlidingPaneLayout.this.mSlideableView);
            SlidingPaneLayout.this.mPreservedOpenState = true;
        }

        public void onViewCaptured(View view, int i) {
            SlidingPaneLayout.this.setAllChildrenVisible();
        }

        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            SlidingPaneLayout.this.onPanelDragged(i);
            SlidingPaneLayout.this.invalidate();
        }

        public void onViewReleased(View view, float f, float f2) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (SlidingPaneLayout.this.isLayoutRtlSupport()) {
                int paddingRight = SlidingPaneLayout.this.getPaddingRight() + layoutParams.rightMargin;
                if (f < 0.0f || (f == 0.0f && SlidingPaneLayout.this.mSlideOffset > 0.5f)) {
                    paddingRight += SlidingPaneLayout.this.mSlideRange;
                }
                f2 = (SlidingPaneLayout.this.getWidth() - paddingRight) - SlidingPaneLayout.this.mSlideableView.getWidth();
            } else {
                f2 = layoutParams.leftMargin + SlidingPaneLayout.this.getPaddingLeft();
                f = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
                if (f > null || (f == null && SlidingPaneLayout.this.mSlideOffset > 0.5f)) {
                    f2 += SlidingPaneLayout.this.mSlideRange;
                }
            }
            SlidingPaneLayout.this.mDragHelper.settleCapturedViewAt(f2, view.getTop());
            SlidingPaneLayout.this.invalidate();
        }

        public int getViewHorizontalDragRange(View view) {
            return SlidingPaneLayout.this.mSlideRange;
        }

        public int clampViewPositionHorizontal(View view, int i, int i2) {
            LayoutParams layoutParams = (LayoutParams) SlidingPaneLayout.this.mSlideableView.getLayoutParams();
            if (SlidingPaneLayout.this.isLayoutRtlSupport() != 0) {
                i2 = SlidingPaneLayout.this.getWidth() - ((SlidingPaneLayout.this.getPaddingRight() + layoutParams.rightMargin) + SlidingPaneLayout.this.mSlideableView.getWidth());
                return Math.max(Math.min(i, i2), i2 - SlidingPaneLayout.this.mSlideRange);
            }
            i2 = SlidingPaneLayout.this.getPaddingLeft() + layoutParams.leftMargin;
            return Math.min(Math.max(i, i2), SlidingPaneLayout.this.mSlideRange + i2);
        }

        public int clampViewPositionVertical(View view, int i, int i2) {
            return view.getTop();
        }

        public void onEdgeDragStarted(int i, int i2) {
            SlidingPaneLayout.this.mDragHelper.captureChildView(SlidingPaneLayout.this.mSlideableView, i2);
        }
    }

    static class SavedState extends AbsSavedState {
        public static final Creator<SavedState> CREATOR = new C01661();
        boolean isOpen;

        /* renamed from: android.support.v4.widget.SlidingPaneLayout$SavedState$1 */
        static class C01661 implements ClassLoaderCreator<SavedState> {
            C01661() {
            }

            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, null);
            }

            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.isOpen = parcel.readInt() != null ? true : null;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.isOpen);
        }
    }

    public static class SimplePanelSlideListener implements PanelSlideListener {
        public void onPanelClosed(View view) {
        }

        public void onPanelOpened(View view) {
        }

        public void onPanelSlide(View view, float f) {
        }
    }

    public SlidingPaneLayout(@NonNull Context context) {
        this(context, null);
    }

    public SlidingPaneLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlidingPaneLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mSliderFadeColor = DEFAULT_FADE_COLOR;
        this.mFirstLayout = true;
        this.mTmpRect = new Rect();
        this.mPostedRunnables = new ArrayList();
        context = context.getResources().getDisplayMetrics().density;
        this.mOverhangSize = (int) ((1107296256 * context) + 1056964608);
        setWillNotDraw(0);
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegate());
        ViewCompat.setImportantForAccessibility(this, 1);
        this.mDragHelper = ViewDragHelper.create(this, 0.5f, new DragHelperCallback());
        this.mDragHelper.setMinVelocity(context * 1137180672);
    }

    public void setParallaxDistance(@Px int i) {
        this.mParallaxBy = i;
        requestLayout();
    }

    @Px
    public int getParallaxDistance() {
        return this.mParallaxBy;
    }

    public void setSliderFadeColor(@ColorInt int i) {
        this.mSliderFadeColor = i;
    }

    @ColorInt
    public int getSliderFadeColor() {
        return this.mSliderFadeColor;
    }

    public void setCoveredFadeColor(@ColorInt int i) {
        this.mCoveredFadeColor = i;
    }

    @ColorInt
    public int getCoveredFadeColor() {
        return this.mCoveredFadeColor;
    }

    public void setPanelSlideListener(@Nullable PanelSlideListener panelSlideListener) {
        this.mPanelSlideListener = panelSlideListener;
    }

    void dispatchOnPanelSlide(View view) {
        if (this.mPanelSlideListener != null) {
            this.mPanelSlideListener.onPanelSlide(view, this.mSlideOffset);
        }
    }

    void dispatchOnPanelOpened(View view) {
        if (this.mPanelSlideListener != null) {
            this.mPanelSlideListener.onPanelOpened(view);
        }
        sendAccessibilityEvent(32);
    }

    void dispatchOnPanelClosed(View view) {
        if (this.mPanelSlideListener != null) {
            this.mPanelSlideListener.onPanelClosed(view);
        }
        sendAccessibilityEvent(32);
    }

    void updateObscuredViewsVisibility(View view) {
        int i;
        int i2;
        int i3;
        int i4;
        View view2 = view;
        boolean isLayoutRtlSupport = isLayoutRtlSupport();
        int width = isLayoutRtlSupport ? getWidth() - getPaddingRight() : getPaddingLeft();
        int paddingLeft = isLayoutRtlSupport ? getPaddingLeft() : getWidth() - getPaddingRight();
        int paddingTop = getPaddingTop();
        int height = getHeight() - getPaddingBottom();
        if (view2 == null || !viewIsOpaque(view)) {
            i = 0;
            i2 = 0;
            i3 = 0;
            i4 = 0;
        } else {
            i = view.getLeft();
            i2 = view.getRight();
            i3 = view.getTop();
            i4 = view.getBottom();
        }
        int childCount = getChildCount();
        int i5 = 0;
        while (i5 < childCount) {
            View childAt = getChildAt(i5);
            if (childAt != view2) {
                boolean z;
                if (childAt.getVisibility() == 8) {
                    z = isLayoutRtlSupport;
                } else {
                    int i6;
                    int max = Math.max(isLayoutRtlSupport ? paddingLeft : width, childAt.getLeft());
                    int max2 = Math.max(paddingTop, childAt.getTop());
                    if (isLayoutRtlSupport) {
                        z = isLayoutRtlSupport;
                        i6 = width;
                    } else {
                        z = isLayoutRtlSupport;
                        i6 = paddingLeft;
                    }
                    max = (max < i || max2 < i3 || Math.min(i6, childAt.getRight()) > i2 || Math.min(height, childAt.getBottom()) > i4) ? 0 : 4;
                    childAt.setVisibility(max);
                }
                i5++;
                isLayoutRtlSupport = z;
                view2 = view;
            } else {
                return;
            }
        }
        SlidingPaneLayout slidingPaneLayout = this;
    }

    void setAllChildrenVisible() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 4) {
                childAt.setVisibility(0);
            }
        }
    }

    private static boolean viewIsOpaque(View view) {
        boolean z = true;
        if (view.isOpaque()) {
            return true;
        }
        if (VERSION.SDK_INT >= 18) {
            return false;
        }
        view = view.getBackground();
        if (view == null) {
            return false;
        }
        if (view.getOpacity() != -1) {
            z = false;
        }
        return z;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mFirstLayout = true;
        int size = this.mPostedRunnables.size();
        for (int i = 0; i < size; i++) {
            ((DisableLayerRunnable) this.mPostedRunnables.get(i)).run();
        }
        this.mPostedRunnables.clear();
    }

    protected void onMeasure(int i, int i2) {
        int paddingTop;
        int i3;
        SlidingPaneLayout slidingPaneLayout = this;
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            if (!isInEditMode()) {
                throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
            } else if (mode != Integer.MIN_VALUE) {
                if (mode == 0) {
                    size = 300;
                }
            }
        } else if (mode2 == 0) {
            if (!isInEditMode()) {
                throw new IllegalStateException("Height must not be UNSPECIFIED");
            } else if (mode2 == 0) {
                mode2 = Integer.MIN_VALUE;
                size2 = 300;
            }
        }
        boolean z = false;
        if (mode2 == Integer.MIN_VALUE) {
            paddingTop = (size2 - getPaddingTop()) - getPaddingBottom();
            size2 = 0;
        } else if (mode2 != 1073741824) {
            size2 = 0;
            paddingTop = 0;
        } else {
            size2 = (size2 - getPaddingTop()) - getPaddingBottom();
            paddingTop = size2;
        }
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int childCount = getChildCount();
        if (childCount > 2) {
            Log.e(TAG, "onMeasure: More than two child views are not supported.");
        }
        slidingPaneLayout.mSlideableView = null;
        int i4 = size2;
        int i5 = paddingLeft;
        size2 = 0;
        boolean z2 = false;
        float f = 0.0f;
        while (true) {
            int i6 = 8;
            if (size2 >= childCount) {
                break;
            }
            View childAt = getChildAt(size2);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (childAt.getVisibility() == 8) {
                layoutParams.dimWhenOffset = z;
            } else {
                if (layoutParams.weight > 0.0f) {
                    f += layoutParams.weight;
                    if (layoutParams.width == 0) {
                    }
                }
                i3 = layoutParams.leftMargin + layoutParams.rightMargin;
                if (layoutParams.width == -2) {
                    mode = MeasureSpec.makeMeasureSpec(paddingLeft - i3, Integer.MIN_VALUE);
                } else if (layoutParams.width == -1) {
                    mode = MeasureSpec.makeMeasureSpec(paddingLeft - i3, 1073741824);
                } else {
                    mode = MeasureSpec.makeMeasureSpec(layoutParams.width, 1073741824);
                }
                if (layoutParams.height == -2) {
                    i6 = MeasureSpec.makeMeasureSpec(paddingTop, Integer.MIN_VALUE);
                } else if (layoutParams.height == -1) {
                    i6 = MeasureSpec.makeMeasureSpec(paddingTop, 1073741824);
                } else {
                    i6 = MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
                }
                childAt.measure(mode, i6);
                mode = childAt.getMeasuredWidth();
                i3 = childAt.getMeasuredHeight();
                if (mode2 == Integer.MIN_VALUE && i3 > i4) {
                    i4 = Math.min(i3, paddingTop);
                }
                i5 -= mode;
                z = i5 < 0;
                layoutParams.slideable = z;
                z |= z2;
                if (layoutParams.slideable) {
                    slidingPaneLayout.mSlideableView = childAt;
                }
                z2 = z;
            }
            size2++;
            z = false;
        }
        if (z2 || f > 0.0f) {
            mode = paddingLeft - slidingPaneLayout.mOverhangSize;
            mode2 = 0;
            while (mode2 < childCount) {
                int i7;
                View childAt2 = getChildAt(mode2);
                if (childAt2.getVisibility() != i6) {
                    LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
                    if (childAt2.getVisibility() != i6) {
                        Object obj = (layoutParams2.width != 0 || layoutParams2.weight <= 0.0f) ? null : 1;
                        if (obj != null) {
                            i3 = 0;
                        } else {
                            i3 = childAt2.getMeasuredWidth();
                        }
                        int i8;
                        if (!z2 || childAt2 == slidingPaneLayout.mSlideableView) {
                            if (layoutParams2.weight > 0.0f) {
                                int makeMeasureSpec;
                                if (layoutParams2.width != 0) {
                                    makeMeasureSpec = MeasureSpec.makeMeasureSpec(childAt2.getMeasuredHeight(), 1073741824);
                                } else if (layoutParams2.height == -2) {
                                    i6 = MeasureSpec.makeMeasureSpec(paddingTop, Integer.MIN_VALUE);
                                    if (z2) {
                                        i7 = mode;
                                        childAt2.measure(MeasureSpec.makeMeasureSpec(i3 + ((int) ((layoutParams2.weight * ((float) Math.max(0, i5))) / f)), 1073741824), i6);
                                        mode2++;
                                        mode = i7;
                                        i6 = 8;
                                    } else {
                                        i8 = paddingLeft - (layoutParams2.leftMargin + layoutParams2.rightMargin);
                                        i7 = mode;
                                        mode = MeasureSpec.makeMeasureSpec(i8, 1073741824);
                                        if (i3 != i8) {
                                            childAt2.measure(mode, i6);
                                        }
                                        mode2++;
                                        mode = i7;
                                        i6 = 8;
                                    }
                                } else if (layoutParams2.height == -1) {
                                    makeMeasureSpec = MeasureSpec.makeMeasureSpec(paddingTop, 1073741824);
                                } else {
                                    makeMeasureSpec = MeasureSpec.makeMeasureSpec(layoutParams2.height, 1073741824);
                                }
                                i6 = makeMeasureSpec;
                                if (z2) {
                                    i7 = mode;
                                    childAt2.measure(MeasureSpec.makeMeasureSpec(i3 + ((int) ((layoutParams2.weight * ((float) Math.max(0, i5))) / f)), 1073741824), i6);
                                    mode2++;
                                    mode = i7;
                                    i6 = 8;
                                } else {
                                    i8 = paddingLeft - (layoutParams2.leftMargin + layoutParams2.rightMargin);
                                    i7 = mode;
                                    mode = MeasureSpec.makeMeasureSpec(i8, 1073741824);
                                    if (i3 != i8) {
                                        childAt2.measure(mode, i6);
                                    }
                                    mode2++;
                                    mode = i7;
                                    i6 = 8;
                                }
                            }
                        } else if (layoutParams2.width < 0 && (i3 > mode || layoutParams2.weight > 0.0f)) {
                            int i9;
                            if (obj == null) {
                                i9 = 1073741824;
                                i8 = MeasureSpec.makeMeasureSpec(childAt2.getMeasuredHeight(), 1073741824);
                            } else if (layoutParams2.height == -2) {
                                i8 = MeasureSpec.makeMeasureSpec(paddingTop, Integer.MIN_VALUE);
                                i9 = 1073741824;
                            } else if (layoutParams2.height == -1) {
                                i9 = 1073741824;
                                i8 = MeasureSpec.makeMeasureSpec(paddingTop, 1073741824);
                            } else {
                                i9 = 1073741824;
                                i8 = MeasureSpec.makeMeasureSpec(layoutParams2.height, 1073741824);
                            }
                            childAt2.measure(MeasureSpec.makeMeasureSpec(mode, i9), i8);
                        }
                    }
                }
                i7 = mode;
                mode2++;
                mode = i7;
                i6 = 8;
            }
        }
        setMeasuredDimension(size, (i4 + getPaddingTop()) + getPaddingBottom());
        slidingPaneLayout.mCanSlide = z2;
        if (slidingPaneLayout.mDragHelper.getViewDragState() != 0 && !z2) {
            slidingPaneLayout.mDragHelper.abort();
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        SlidingPaneLayout slidingPaneLayout = this;
        boolean isLayoutRtlSupport = isLayoutRtlSupport();
        if (isLayoutRtlSupport) {
            slidingPaneLayout.mDragHelper.setEdgeTrackingEnabled(2);
        } else {
            slidingPaneLayout.mDragHelper.setEdgeTrackingEnabled(1);
        }
        int i5 = i3 - i;
        int paddingRight = isLayoutRtlSupport ? getPaddingRight() : getPaddingLeft();
        int paddingLeft = isLayoutRtlSupport ? getPaddingLeft() : getPaddingRight();
        int paddingTop = getPaddingTop();
        int childCount = getChildCount();
        if (slidingPaneLayout.mFirstLayout) {
            float f = (slidingPaneLayout.mCanSlide && slidingPaneLayout.mPreservedOpenState) ? 1.0f : 0.0f;
            slidingPaneLayout.mSlideOffset = f;
        }
        int i6 = paddingRight;
        int i7 = i6;
        for (paddingRight = 0; paddingRight < childCount; paddingRight++) {
            View childAt = getChildAt(paddingRight);
            if (childAt.getVisibility() != 8) {
                int min;
                int i8;
                int i9;
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                if (layoutParams.slideable) {
                    int i10 = i5 - paddingLeft;
                    min = (Math.min(i6, i10 - slidingPaneLayout.mOverhangSize) - i7) - (layoutParams.leftMargin + layoutParams.rightMargin);
                    slidingPaneLayout.mSlideRange = min;
                    i8 = isLayoutRtlSupport ? layoutParams.rightMargin : layoutParams.leftMargin;
                    layoutParams.dimWhenOffset = ((i7 + i8) + min) + (measuredWidth / 2) > i10;
                    i10 = (int) (((float) min) * slidingPaneLayout.mSlideOffset);
                    i8 = (i8 + i10) + i7;
                    slidingPaneLayout.mSlideOffset = ((float) i10) / ((float) slidingPaneLayout.mSlideRange);
                } else if (!slidingPaneLayout.mCanSlide || slidingPaneLayout.mParallaxBy == 0) {
                    i8 = i6;
                } else {
                    i9 = (int) ((1.0f - slidingPaneLayout.mSlideOffset) * ((float) slidingPaneLayout.mParallaxBy));
                    i8 = i6;
                    if (isLayoutRtlSupport) {
                        i9 = i8 - i9;
                        min = i9 + measuredWidth;
                    } else {
                        min = (i5 - i8) + i9;
                        i9 = min - measuredWidth;
                    }
                    childAt.layout(i9, paddingTop, min, childAt.getMeasuredHeight() + paddingTop);
                    i6 += childAt.getWidth();
                    i7 = i8;
                }
                i9 = 0;
                if (isLayoutRtlSupport) {
                    i9 = i8 - i9;
                    min = i9 + measuredWidth;
                } else {
                    min = (i5 - i8) + i9;
                    i9 = min - measuredWidth;
                }
                childAt.layout(i9, paddingTop, min, childAt.getMeasuredHeight() + paddingTop);
                i6 += childAt.getWidth();
                i7 = i8;
            }
        }
        if (slidingPaneLayout.mFirstLayout) {
            if (slidingPaneLayout.mCanSlide) {
                if (slidingPaneLayout.mParallaxBy != 0) {
                    parallaxOtherViews(slidingPaneLayout.mSlideOffset);
                }
                if (((LayoutParams) slidingPaneLayout.mSlideableView.getLayoutParams()).dimWhenOffset) {
                    dimChildView(slidingPaneLayout.mSlideableView, slidingPaneLayout.mSlideOffset, slidingPaneLayout.mSliderFadeColor);
                }
            } else {
                for (int i11 = 0; i11 < childCount; i11++) {
                    dimChildView(getChildAt(i11), 0.0f, slidingPaneLayout.mSliderFadeColor);
                }
            }
            updateObscuredViewsVisibility(slidingPaneLayout.mSlideableView);
        }
        slidingPaneLayout.mFirstLayout = false;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3) {
            this.mFirstLayout = true;
        }
    }

    public void requestChildFocus(View view, View view2) {
        super.requestChildFocus(view, view2);
        if (isInTouchMode() == null && this.mCanSlide == null) {
            this.mPreservedOpenState = view == this.mSlideableView ? true : null;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        boolean z = true;
        if (!this.mCanSlide && actionMasked == 0 && getChildCount() > 1) {
            View childAt = getChildAt(1);
            if (childAt != null) {
                this.mPreservedOpenState = this.mDragHelper.isViewUnder(childAt, (int) motionEvent.getX(), (int) motionEvent.getY()) ^ true;
            }
        }
        if (this.mCanSlide) {
            if (!this.mIsUnableToDrag || actionMasked == 0) {
                if (actionMasked != 3) {
                    if (actionMasked != 1) {
                        Object obj;
                        float x;
                        float y;
                        if (actionMasked == 0) {
                            this.mIsUnableToDrag = false;
                            x = motionEvent.getX();
                            y = motionEvent.getY();
                            this.mInitialMotionX = x;
                            this.mInitialMotionY = y;
                            if (this.mDragHelper.isViewUnder(this.mSlideableView, (int) x, (int) y) && isDimmed(this.mSlideableView)) {
                                obj = 1;
                                if (this.mDragHelper.shouldInterceptTouchEvent(motionEvent) == null) {
                                    if (obj != null) {
                                        z = false;
                                    }
                                }
                                return z;
                            }
                        } else if (actionMasked == 2) {
                            x = motionEvent.getX();
                            y = motionEvent.getY();
                            x = Math.abs(x - this.mInitialMotionX);
                            y = Math.abs(y - this.mInitialMotionY);
                            if (x > ((float) this.mDragHelper.getTouchSlop()) && y > x) {
                                this.mDragHelper.cancel();
                                this.mIsUnableToDrag = true;
                                return false;
                            }
                        }
                        obj = null;
                        if (this.mDragHelper.shouldInterceptTouchEvent(motionEvent) == null) {
                            if (obj != null) {
                                z = false;
                            }
                        }
                        return z;
                    }
                }
                this.mDragHelper.cancel();
                return false;
            }
        }
        this.mDragHelper.cancel();
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mCanSlide) {
            return super.onTouchEvent(motionEvent);
        }
        this.mDragHelper.processTouchEvent(motionEvent);
        float x;
        switch (motionEvent.getActionMasked()) {
            case 0:
                x = motionEvent.getX();
                motionEvent = motionEvent.getY();
                this.mInitialMotionX = x;
                this.mInitialMotionY = motionEvent;
                break;
            case 1:
                if (isDimmed(this.mSlideableView)) {
                    x = motionEvent.getX();
                    motionEvent = motionEvent.getY();
                    float f = x - this.mInitialMotionX;
                    float f2 = motionEvent - this.mInitialMotionY;
                    int touchSlop = this.mDragHelper.getTouchSlop();
                    if ((f * f) + (f2 * f2) < ((float) (touchSlop * touchSlop)) && this.mDragHelper.isViewUnder(this.mSlideableView, (int) x, (int) motionEvent) != null) {
                        closePane(this.mSlideableView, 0);
                        break;
                    }
                }
                break;
            default:
                break;
        }
        return true;
    }

    private boolean closePane(View view, int i) {
        if (this.mFirstLayout == null) {
            if (smoothSlideTo(null, i) == null) {
                return false;
            }
        }
        this.mPreservedOpenState = false;
        return true;
    }

    private boolean openPane(View view, int i) {
        if (this.mFirstLayout == null) {
            if (smoothSlideTo(1.0f, i) == null) {
                return null;
            }
        }
        this.mPreservedOpenState = true;
        return true;
    }

    @Deprecated
    public void smoothSlideOpen() {
        openPane();
    }

    public boolean openPane() {
        return openPane(this.mSlideableView, 0);
    }

    @Deprecated
    public void smoothSlideClosed() {
        closePane();
    }

    public boolean closePane() {
        return closePane(this.mSlideableView, 0);
    }

    public boolean isOpen() {
        if (this.mCanSlide) {
            if (this.mSlideOffset != 1.0f) {
                return false;
            }
        }
        return true;
    }

    @Deprecated
    public boolean canSlide() {
        return this.mCanSlide;
    }

    public boolean isSlideable() {
        return this.mCanSlide;
    }

    void onPanelDragged(int i) {
        if (this.mSlideableView == null) {
            this.mSlideOffset = 0;
            return;
        }
        boolean isLayoutRtlSupport = isLayoutRtlSupport();
        LayoutParams layoutParams = (LayoutParams) this.mSlideableView.getLayoutParams();
        int width = this.mSlideableView.getWidth();
        if (isLayoutRtlSupport) {
            i = (getWidth() - i) - width;
        }
        this.mSlideOffset = ((float) (i - ((isLayoutRtlSupport ? getPaddingRight() : getPaddingLeft()) + (isLayoutRtlSupport ? layoutParams.rightMargin : layoutParams.leftMargin)))) / ((float) this.mSlideRange);
        if (this.mParallaxBy != 0) {
            parallaxOtherViews(this.mSlideOffset);
        }
        if (layoutParams.dimWhenOffset != 0) {
            dimChildView(this.mSlideableView, this.mSlideOffset, this.mSliderFadeColor);
        }
        dispatchOnPanelSlide(this.mSlideableView);
    }

    private void dimChildView(View view, float f, int i) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (f > 0.0f && i != 0) {
            f = (((int) (((float) ((ViewCompat.MEASURED_STATE_MASK & i) >>> 24)) * f)) << 24) | (i & ViewCompat.MEASURED_SIZE_MASK);
            if (layoutParams.dimPaint == 0) {
                layoutParams.dimPaint = new Paint();
            }
            layoutParams.dimPaint.setColorFilter(new PorterDuffColorFilter(f, Mode.SRC_OVER));
            if (view.getLayerType() != 2.8E-45f) {
                view.setLayerType(2, layoutParams.dimPaint);
            }
            invalidateChildRegion(view);
        } else if (view.getLayerType() != null) {
            if (layoutParams.dimPaint != null) {
                layoutParams.dimPaint.setColorFilter(0);
            }
            f = new DisableLayerRunnable(view);
            this.mPostedRunnables.add(f);
            ViewCompat.postOnAnimation(this, f);
        }
    }

    protected boolean drawChild(Canvas canvas, View view, long j) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int save = canvas.save();
        if (!(!this.mCanSlide || layoutParams.slideable || this.mSlideableView == null)) {
            canvas.getClipBounds(this.mTmpRect);
            if (isLayoutRtlSupport()) {
                this.mTmpRect.left = Math.max(this.mTmpRect.left, this.mSlideableView.getRight());
            } else {
                this.mTmpRect.right = Math.min(this.mTmpRect.right, this.mSlideableView.getLeft());
            }
            canvas.clipRect(this.mTmpRect);
        }
        view = super.drawChild(canvas, view, j);
        canvas.restoreToCount(save);
        return view;
    }

    void invalidateChildRegion(View view) {
        if (VERSION.SDK_INT >= 17) {
            ViewCompat.setLayerPaint(view, ((LayoutParams) view.getLayoutParams()).dimPaint);
            return;
        }
        if (VERSION.SDK_INT >= 16) {
            if (!this.mDisplayListReflectionLoaded) {
                try {
                    this.mGetDisplayList = View.class.getDeclaredMethod("getDisplayList", (Class[]) null);
                } catch (Throwable e) {
                    Log.e(TAG, "Couldn't fetch getDisplayList method; dimming won't work right.", e);
                }
                try {
                    this.mRecreateDisplayList = View.class.getDeclaredField("mRecreateDisplayList");
                    this.mRecreateDisplayList.setAccessible(true);
                } catch (Throwable e2) {
                    Log.e(TAG, "Couldn't fetch mRecreateDisplayList field; dimming will be slow.", e2);
                }
                this.mDisplayListReflectionLoaded = true;
            }
            if (this.mGetDisplayList != null) {
                if (this.mRecreateDisplayList != null) {
                    try {
                        this.mRecreateDisplayList.setBoolean(view, true);
                        this.mGetDisplayList.invoke(view, (Object[]) null);
                    } catch (Throwable e22) {
                        Log.e(TAG, "Error refreshing display list state", e22);
                    }
                }
            }
            view.invalidate();
            return;
        }
        ViewCompat.postInvalidateOnAnimation(this, view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }

    boolean smoothSlideTo(float f, int i) {
        if (this.mCanSlide == 0) {
            return false;
        }
        LayoutParams layoutParams = (LayoutParams) this.mSlideableView.getLayoutParams();
        if (isLayoutRtlSupport() != 0) {
            f = (int) (((float) getWidth()) - ((((float) (getPaddingRight() + layoutParams.rightMargin)) + (f * ((float) this.mSlideRange))) + ((float) this.mSlideableView.getWidth())));
        } else {
            f = (int) (((float) (getPaddingLeft() + layoutParams.leftMargin)) + (f * ((float) this.mSlideRange)));
        }
        if (this.mDragHelper.smoothSlideViewTo(this.mSlideableView, f, this.mSlideableView.getTop()) == null) {
            return false;
        }
        setAllChildrenVisible();
        ViewCompat.postInvalidateOnAnimation(this);
        return true;
    }

    public void computeScroll() {
        if (this.mDragHelper.continueSettling(true)) {
            if (this.mCanSlide) {
                ViewCompat.postInvalidateOnAnimation(this);
            } else {
                this.mDragHelper.abort();
            }
        }
    }

    @Deprecated
    public void setShadowDrawable(Drawable drawable) {
        setShadowDrawableLeft(drawable);
    }

    public void setShadowDrawableLeft(@Nullable Drawable drawable) {
        this.mShadowDrawableLeft = drawable;
    }

    public void setShadowDrawableRight(@Nullable Drawable drawable) {
        this.mShadowDrawableRight = drawable;
    }

    @Deprecated
    public void setShadowResource(@DrawableRes int i) {
        setShadowDrawable(getResources().getDrawable(i));
    }

    public void setShadowResourceLeft(int i) {
        setShadowDrawableLeft(ContextCompat.getDrawable(getContext(), i));
    }

    public void setShadowResourceRight(int i) {
        setShadowDrawableRight(ContextCompat.getDrawable(getContext(), i));
    }

    public void draw(Canvas canvas) {
        Drawable drawable;
        super.draw(canvas);
        if (isLayoutRtlSupport()) {
            drawable = this.mShadowDrawableRight;
        } else {
            drawable = this.mShadowDrawableLeft;
        }
        View childAt = getChildCount() > 1 ? getChildAt(1) : null;
        if (childAt != null) {
            if (drawable != null) {
                int right;
                int top = childAt.getTop();
                int bottom = childAt.getBottom();
                int intrinsicWidth = drawable.getIntrinsicWidth();
                if (isLayoutRtlSupport()) {
                    right = childAt.getRight();
                    intrinsicWidth += right;
                } else {
                    right = childAt.getLeft();
                    int i = right - intrinsicWidth;
                    intrinsicWidth = right;
                    right = i;
                }
                drawable.setBounds(right, top, intrinsicWidth, bottom);
                drawable.draw(canvas);
            }
        }
    }

    private void parallaxOtherViews(float f) {
        Object obj;
        int childCount;
        int i;
        View childAt;
        int i2;
        boolean isLayoutRtlSupport = isLayoutRtlSupport();
        LayoutParams layoutParams = (LayoutParams) this.mSlideableView.getLayoutParams();
        if (layoutParams.dimWhenOffset) {
            if ((isLayoutRtlSupport ? layoutParams.rightMargin : layoutParams.leftMargin) <= 0) {
                obj = 1;
                childCount = getChildCount();
                for (i = 0; i < childCount; i++) {
                    childAt = getChildAt(i);
                    if (childAt == this.mSlideableView) {
                        i2 = (int) ((1.0f - this.mParallaxOffset) * ((float) this.mParallaxBy));
                        this.mParallaxOffset = f;
                        i2 -= (int) ((1.0f - f) * ((float) this.mParallaxBy));
                        if (isLayoutRtlSupport) {
                            i2 = -i2;
                        }
                        childAt.offsetLeftAndRight(i2);
                        if (obj == null) {
                            dimChildView(childAt, isLayoutRtlSupport ? this.mParallaxOffset - 1.0f : 1.0f - this.mParallaxOffset, this.mCoveredFadeColor);
                        }
                    }
                }
            }
        }
        obj = null;
        childCount = getChildCount();
        for (i = 0; i < childCount; i++) {
            childAt = getChildAt(i);
            if (childAt == this.mSlideableView) {
                i2 = (int) ((1.0f - this.mParallaxOffset) * ((float) this.mParallaxBy));
                this.mParallaxOffset = f;
                i2 -= (int) ((1.0f - f) * ((float) this.mParallaxBy));
                if (isLayoutRtlSupport) {
                    i2 = -i2;
                }
                childAt.offsetLeftAndRight(i2);
                if (obj == null) {
                    if (isLayoutRtlSupport) {
                    }
                    dimChildView(childAt, isLayoutRtlSupport ? this.mParallaxOffset - 1.0f : 1.0f - this.mParallaxOffset, this.mCoveredFadeColor);
                }
            }
        }
    }

    protected boolean canScroll(View view, boolean z, int i, int i2, int i3) {
        View view2 = view;
        boolean z2 = true;
        if (view2 instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view2;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i4 = i2 + scrollX;
                if (i4 >= childAt.getLeft() && i4 < childAt.getRight()) {
                    int i5 = i3 + scrollY;
                    if (i5 >= childAt.getTop() && i5 < childAt.getBottom()) {
                        if (canScroll(childAt, true, i, i4 - childAt.getLeft(), i5 - childAt.getTop())) {
                            return true;
                        }
                    }
                }
            }
        }
        if (z) {
            if (view.canScrollHorizontally(isLayoutRtlSupport() ? i : -i)) {
                return z2;
            }
        }
        z2 = false;
        return z2;
    }

    boolean isDimmed(View view) {
        boolean z = false;
        if (view == null) {
            return false;
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (this.mCanSlide && layoutParams.dimWhenOffset != null && this.mSlideOffset > 0.0f) {
            z = true;
        }
        return z;
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof MarginLayoutParams ? new LayoutParams((MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return (!(layoutParams instanceof LayoutParams) || super.checkLayoutParams(layoutParams) == null) ? null : true;
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.isOpen = isSlideable() ? isOpen() : this.mPreservedOpenState;
        return savedState;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            if (savedState.isOpen) {
                openPane();
            } else {
                closePane();
            }
            this.mPreservedOpenState = savedState.isOpen;
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    boolean isLayoutRtlSupport() {
        return ViewCompat.getLayoutDirection(this) == 1;
    }
}
