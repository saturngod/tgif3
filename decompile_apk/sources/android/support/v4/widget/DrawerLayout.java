package android.support.v4.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;
import java.util.List;

public class DrawerLayout extends ViewGroup {
    private static final boolean ALLOW_EDGE_LOCK = false;
    static final boolean CAN_HIDE_DESCENDANTS = (VERSION.SDK_INT >= 19 ? CHILDREN_DISALLOW_INTERCEPT : false);
    private static final boolean CHILDREN_DISALLOW_INTERCEPT = true;
    private static final int DEFAULT_SCRIM_COLOR = -1728053248;
    private static final int DRAWER_ELEVATION = 10;
    static final int[] LAYOUT_ATTRS = new int[]{16842931};
    public static final int LOCK_MODE_LOCKED_CLOSED = 1;
    public static final int LOCK_MODE_LOCKED_OPEN = 2;
    public static final int LOCK_MODE_UNDEFINED = 3;
    public static final int LOCK_MODE_UNLOCKED = 0;
    private static final int MIN_DRAWER_MARGIN = 64;
    private static final int MIN_FLING_VELOCITY = 400;
    private static final int PEEK_DELAY = 160;
    private static final boolean SET_DRAWER_SHADOW_FROM_ELEVATION;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    private static final String TAG = "DrawerLayout";
    private static final int[] THEME_ATTRS = new int[]{16843828};
    private static final float TOUCH_SLOP_SENSITIVITY = 1.0f;
    private final ChildAccessibilityDelegate mChildAccessibilityDelegate;
    private Rect mChildHitRect;
    private Matrix mChildInvertedMatrix;
    private boolean mChildrenCanceledTouch;
    private boolean mDisallowInterceptRequested;
    private boolean mDrawStatusBarBackground;
    private float mDrawerElevation;
    private int mDrawerState;
    private boolean mFirstLayout;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private Object mLastInsets;
    private final ViewDragCallback mLeftCallback;
    private final ViewDragHelper mLeftDragger;
    @Nullable
    private DrawerListener mListener;
    private List<DrawerListener> mListeners;
    private int mLockModeEnd;
    private int mLockModeLeft;
    private int mLockModeRight;
    private int mLockModeStart;
    private int mMinDrawerMargin;
    private final ArrayList<View> mNonDrawerViews;
    private final ViewDragCallback mRightCallback;
    private final ViewDragHelper mRightDragger;
    private int mScrimColor;
    private float mScrimOpacity;
    private Paint mScrimPaint;
    private Drawable mShadowEnd;
    private Drawable mShadowLeft;
    private Drawable mShadowLeftResolved;
    private Drawable mShadowRight;
    private Drawable mShadowRightResolved;
    private Drawable mShadowStart;
    private Drawable mStatusBarBackground;
    private CharSequence mTitleLeft;
    private CharSequence mTitleRight;

    /* renamed from: android.support.v4.widget.DrawerLayout$1 */
    class C01621 implements OnApplyWindowInsetsListener {
        C01621() {
        }

        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            ((DrawerLayout) view).setChildInsets(windowInsets, windowInsets.getSystemWindowInsetTop() > 0 ? DrawerLayout.CHILDREN_DISALLOW_INTERCEPT : false);
            return windowInsets.consumeSystemWindowInsets();
        }
    }

    public interface DrawerListener {
        void onDrawerClosed(@NonNull View view);

        void onDrawerOpened(@NonNull View view);

        void onDrawerSlide(@NonNull View view, float f);

        void onDrawerStateChanged(int i);
    }

    public static class LayoutParams extends MarginLayoutParams {
        private static final int FLAG_IS_CLOSING = 4;
        private static final int FLAG_IS_OPENED = 1;
        private static final int FLAG_IS_OPENING = 2;
        public int gravity;
        boolean isPeeking;
        float onScreen;
        int openState;

        public LayoutParams(@NonNull Context context, @Nullable AttributeSet attributeSet) {
            super(context, attributeSet);
            this.gravity = 0;
            context = context.obtainStyledAttributes(attributeSet, DrawerLayout.LAYOUT_ATTRS);
            this.gravity = context.getInt(0, 0);
            context.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.gravity = 0;
        }

        public LayoutParams(int i, int i2, int i3) {
            this(i, i2);
            this.gravity = i3;
        }

        public LayoutParams(@NonNull LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = 0;
            this.gravity = layoutParams.gravity;
        }

        public LayoutParams(@NonNull android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = null;
        }

        public LayoutParams(@NonNull MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.gravity = null;
        }
    }

    class AccessibilityDelegate extends AccessibilityDelegateCompat {
        private final Rect mTmpRect = new Rect();

        AccessibilityDelegate() {
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (DrawerLayout.CAN_HIDE_DESCENDANTS) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            } else {
                AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain(accessibilityNodeInfoCompat);
                super.onInitializeAccessibilityNodeInfo(view, obtain);
                accessibilityNodeInfoCompat.setSource(view);
                ViewParent parentForAccessibility = ViewCompat.getParentForAccessibility(view);
                if (parentForAccessibility instanceof View) {
                    accessibilityNodeInfoCompat.setParent((View) parentForAccessibility);
                }
                copyNodeInfoNoChildren(accessibilityNodeInfoCompat, obtain);
                obtain.recycle();
                addChildrenForAccessibility(accessibilityNodeInfoCompat, (ViewGroup) view);
            }
            accessibilityNodeInfoCompat.setClassName(DrawerLayout.class.getName());
            accessibilityNodeInfoCompat.setFocusable(false);
            accessibilityNodeInfoCompat.setFocused(false);
            accessibilityNodeInfoCompat.removeAction(AccessibilityActionCompat.ACTION_FOCUS);
            accessibilityNodeInfoCompat.removeAction(AccessibilityActionCompat.ACTION_CLEAR_FOCUS);
        }

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(DrawerLayout.class.getName());
        }

        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            if (accessibilityEvent.getEventType() != 32) {
                return super.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
            }
            view = accessibilityEvent.getText();
            accessibilityEvent = DrawerLayout.this.findVisibleDrawer();
            if (accessibilityEvent != null) {
                accessibilityEvent = DrawerLayout.this.getDrawerTitle(DrawerLayout.this.getDrawerViewAbsoluteGravity(accessibilityEvent));
                if (accessibilityEvent != null) {
                    view.add(accessibilityEvent);
                }
            }
            return DrawerLayout.CHILDREN_DISALLOW_INTERCEPT;
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (!DrawerLayout.CAN_HIDE_DESCENDANTS) {
                if (!DrawerLayout.includeChildForAccessibility(view)) {
                    return null;
                }
            }
            return super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
        }

        private void addChildrenForAccessibility(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, ViewGroup viewGroup) {
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (DrawerLayout.includeChildForAccessibility(childAt)) {
                    accessibilityNodeInfoCompat.addChild(childAt);
                }
            }
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
        }
    }

    static final class ChildAccessibilityDelegate extends AccessibilityDelegateCompat {
        ChildAccessibilityDelegate() {
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            if (DrawerLayout.includeChildForAccessibility(view) == null) {
                accessibilityNodeInfoCompat.setParent(null);
            }
        }
    }

    protected static class SavedState extends AbsSavedState {
        public static final Creator<SavedState> CREATOR = new C01631();
        int lockModeEnd;
        int lockModeLeft;
        int lockModeRight;
        int lockModeStart;
        int openDrawerGravity = null;

        /* renamed from: android.support.v4.widget.DrawerLayout$SavedState$1 */
        static class C01631 implements ClassLoaderCreator<SavedState> {
            C01631() {
            }

            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(@NonNull Parcel parcel, @Nullable ClassLoader classLoader) {
            super(parcel, classLoader);
            this.openDrawerGravity = parcel.readInt();
            this.lockModeLeft = parcel.readInt();
            this.lockModeRight = parcel.readInt();
            this.lockModeStart = parcel.readInt();
            this.lockModeEnd = parcel.readInt();
        }

        public SavedState(@NonNull Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.openDrawerGravity);
            parcel.writeInt(this.lockModeLeft);
            parcel.writeInt(this.lockModeRight);
            parcel.writeInt(this.lockModeStart);
            parcel.writeInt(this.lockModeEnd);
        }
    }

    public static abstract class SimpleDrawerListener implements DrawerListener {
        public void onDrawerClosed(View view) {
        }

        public void onDrawerOpened(View view) {
        }

        public void onDrawerSlide(View view, float f) {
        }

        public void onDrawerStateChanged(int i) {
        }
    }

    private class ViewDragCallback extends Callback {
        private final int mAbsGravity;
        private ViewDragHelper mDragger;
        private final Runnable mPeekRunnable = new C01641();

        /* renamed from: android.support.v4.widget.DrawerLayout$ViewDragCallback$1 */
        class C01641 implements Runnable {
            C01641() {
            }

            public void run() {
                ViewDragCallback.this.peekDrawer();
            }
        }

        public boolean onEdgeLock(int i) {
            return false;
        }

        ViewDragCallback(int i) {
            this.mAbsGravity = i;
        }

        public void setDragger(ViewDragHelper viewDragHelper) {
            this.mDragger = viewDragHelper;
        }

        public void removeCallbacks() {
            DrawerLayout.this.removeCallbacks(this.mPeekRunnable);
        }

        public boolean tryCaptureView(View view, int i) {
            return (DrawerLayout.this.isDrawerView(view) == 0 || DrawerLayout.this.checkDrawerViewAbsoluteGravity(view, this.mAbsGravity) == 0 || DrawerLayout.this.getDrawerLockMode(view) != null) ? null : DrawerLayout.CHILDREN_DISALLOW_INTERCEPT;
        }

        public void onViewDragStateChanged(int i) {
            DrawerLayout.this.updateDrawerState(this.mAbsGravity, i, this.mDragger.getCapturedView());
        }

        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            i2 = view.getWidth();
            if (DrawerLayout.this.checkDrawerViewAbsoluteGravity(view, 3) != 0) {
                i = ((float) (i + i2)) / ((float) i2);
            } else {
                i = ((float) (DrawerLayout.this.getWidth() - i)) / ((float) i2);
            }
            DrawerLayout.this.setDrawerViewOffset(view, i);
            view.setVisibility(i == 0 ? 4 : 0);
            DrawerLayout.this.invalidate();
        }

        public void onViewCaptured(View view, int i) {
            ((LayoutParams) view.getLayoutParams()).isPeeking = false;
            closeOtherDrawer();
        }

        private void closeOtherDrawer() {
            int i = 3;
            if (this.mAbsGravity == 3) {
                i = 5;
            }
            View findDrawerWithGravity = DrawerLayout.this.findDrawerWithGravity(i);
            if (findDrawerWithGravity != null) {
                DrawerLayout.this.closeDrawer(findDrawerWithGravity);
            }
        }

        public void onViewReleased(View view, float f, float f2) {
            f2 = DrawerLayout.this.getDrawerViewOffset(view);
            int width = view.getWidth();
            if (DrawerLayout.this.checkDrawerViewAbsoluteGravity(view, 3)) {
                f = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
                if (f <= null) {
                    if (f != null || f2 <= 0.5f) {
                        f = -width;
                    }
                }
                f = 0.0f;
            } else {
                int width2 = DrawerLayout.this.getWidth();
                if (f < 0.0f || (f == 0.0f && f2 > 0.5f)) {
                    width2 -= width;
                }
                f = width2;
            }
            this.mDragger.settleCapturedViewAt(f, view.getTop());
            DrawerLayout.this.invalidate();
        }

        public void onEdgeTouched(int i, int i2) {
            DrawerLayout.this.postDelayed(this.mPeekRunnable, 160);
        }

        void peekDrawer() {
            View findDrawerWithGravity;
            int edgeSize = this.mDragger.getEdgeSize();
            int i = 0;
            Object obj = this.mAbsGravity == 3 ? 1 : null;
            if (obj != null) {
                findDrawerWithGravity = DrawerLayout.this.findDrawerWithGravity(3);
                if (findDrawerWithGravity != null) {
                    i = -findDrawerWithGravity.getWidth();
                }
                i += edgeSize;
            } else {
                findDrawerWithGravity = DrawerLayout.this.findDrawerWithGravity(5);
                i = DrawerLayout.this.getWidth() - edgeSize;
            }
            if (findDrawerWithGravity == null) {
                return;
            }
            if (((obj != null && findDrawerWithGravity.getLeft() < i) || (obj == null && findDrawerWithGravity.getLeft() > i)) && DrawerLayout.this.getDrawerLockMode(findDrawerWithGravity) == 0) {
                LayoutParams layoutParams = (LayoutParams) findDrawerWithGravity.getLayoutParams();
                this.mDragger.smoothSlideViewTo(findDrawerWithGravity, i, findDrawerWithGravity.getTop());
                layoutParams.isPeeking = DrawerLayout.CHILDREN_DISALLOW_INTERCEPT;
                DrawerLayout.this.invalidate();
                closeOtherDrawer();
                DrawerLayout.this.cancelChildViewTouch();
            }
        }

        public void onEdgeDragStarted(int i, int i2) {
            View findDrawerWithGravity;
            if ((i & 1) == 1) {
                findDrawerWithGravity = DrawerLayout.this.findDrawerWithGravity(3);
            } else {
                findDrawerWithGravity = DrawerLayout.this.findDrawerWithGravity(5);
            }
            if (findDrawerWithGravity != 0 && DrawerLayout.this.getDrawerLockMode(findDrawerWithGravity) == 0) {
                this.mDragger.captureChildView(findDrawerWithGravity, i2);
            }
        }

        public int getViewHorizontalDragRange(View view) {
            return DrawerLayout.this.isDrawerView(view) ? view.getWidth() : null;
        }

        public int clampViewPositionHorizontal(View view, int i, int i2) {
            if (DrawerLayout.this.checkDrawerViewAbsoluteGravity(view, 3) != 0) {
                return Math.max(-view.getWidth(), Math.min(i, 0));
            }
            i2 = DrawerLayout.this.getWidth();
            return Math.max(i2 - view.getWidth(), Math.min(i, i2));
        }

        public int clampViewPositionVertical(View view, int i, int i2) {
            return view.getTop();
        }
    }

    static {
        boolean z = CHILDREN_DISALLOW_INTERCEPT;
        if (VERSION.SDK_INT < 21) {
            z = false;
        }
        SET_DRAWER_SHADOW_FROM_ELEVATION = z;
    }

    public DrawerLayout(@NonNull Context context) {
        this(context, null);
    }

    public DrawerLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DrawerLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mChildAccessibilityDelegate = new ChildAccessibilityDelegate();
        this.mScrimColor = DEFAULT_SCRIM_COLOR;
        this.mScrimPaint = new Paint();
        this.mFirstLayout = CHILDREN_DISALLOW_INTERCEPT;
        this.mLockModeLeft = 3;
        this.mLockModeRight = 3;
        this.mLockModeStart = 3;
        this.mLockModeEnd = 3;
        this.mShadowStart = null;
        this.mShadowEnd = null;
        this.mShadowLeft = null;
        this.mShadowRight = null;
        setDescendantFocusability(262144);
        float f = getResources().getDisplayMetrics().density;
        this.mMinDrawerMargin = (int) ((64.0f * f) + 0.5f);
        float f2 = 400.0f * f;
        this.mLeftCallback = new ViewDragCallback(3);
        this.mRightCallback = new ViewDragCallback(5);
        this.mLeftDragger = ViewDragHelper.create(this, TOUCH_SLOP_SENSITIVITY, this.mLeftCallback);
        this.mLeftDragger.setEdgeTrackingEnabled(1);
        this.mLeftDragger.setMinVelocity(f2);
        this.mLeftCallback.setDragger(this.mLeftDragger);
        this.mRightDragger = ViewDragHelper.create(this, TOUCH_SLOP_SENSITIVITY, this.mRightCallback);
        this.mRightDragger.setEdgeTrackingEnabled(2);
        this.mRightDragger.setMinVelocity(f2);
        this.mRightCallback.setDragger(this.mRightDragger);
        setFocusableInTouchMode(CHILDREN_DISALLOW_INTERCEPT);
        ViewCompat.setImportantForAccessibility(this, 1);
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegate());
        setMotionEventSplittingEnabled(false);
        if (ViewCompat.getFitsSystemWindows(this) != 0) {
            if (VERSION.SDK_INT >= 21) {
                setOnApplyWindowInsetsListener(new C01621());
                setSystemUiVisibility(1280);
                context = context.obtainStyledAttributes(THEME_ATTRS);
                try {
                    this.mStatusBarBackground = context.getDrawable(0);
                } finally {
                    context.recycle();
                }
            } else {
                this.mStatusBarBackground = null;
            }
        }
        this.mDrawerElevation = f * 10.0f;
        this.mNonDrawerViews = new ArrayList();
    }

    public void setDrawerElevation(float f) {
        this.mDrawerElevation = f;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (isDrawerView(childAt)) {
                ViewCompat.setElevation(childAt, this.mDrawerElevation);
            }
        }
    }

    public float getDrawerElevation() {
        return SET_DRAWER_SHADOW_FROM_ELEVATION ? this.mDrawerElevation : 0.0f;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setChildInsets(Object obj, boolean z) {
        this.mLastInsets = obj;
        this.mDrawStatusBarBackground = z;
        obj = (z || getBackground() != null) ? null : CHILDREN_DISALLOW_INTERCEPT;
        setWillNotDraw(obj);
        requestLayout();
    }

    public void setDrawerShadow(Drawable drawable, int i) {
        if (!SET_DRAWER_SHADOW_FROM_ELEVATION) {
            if ((i & GravityCompat.START) == GravityCompat.START) {
                this.mShadowStart = drawable;
            } else if ((i & GravityCompat.END) == GravityCompat.END) {
                this.mShadowEnd = drawable;
            } else if ((i & 3) == 3) {
                this.mShadowLeft = drawable;
            } else if ((i & 5) == 5) {
                this.mShadowRight = drawable;
            } else {
                return;
            }
            resolveShadowDrawables();
            invalidate();
        }
    }

    public void setDrawerShadow(@DrawableRes int i, int i2) {
        setDrawerShadow(ContextCompat.getDrawable(getContext(), i), i2);
    }

    public void setScrimColor(@ColorInt int i) {
        this.mScrimColor = i;
        invalidate();
    }

    @Deprecated
    public void setDrawerListener(DrawerListener drawerListener) {
        if (this.mListener != null) {
            removeDrawerListener(this.mListener);
        }
        if (drawerListener != null) {
            addDrawerListener(drawerListener);
        }
        this.mListener = drawerListener;
    }

    public void addDrawerListener(@NonNull DrawerListener drawerListener) {
        if (drawerListener != null) {
            if (this.mListeners == null) {
                this.mListeners = new ArrayList();
            }
            this.mListeners.add(drawerListener);
        }
    }

    public void removeDrawerListener(@NonNull DrawerListener drawerListener) {
        if (drawerListener != null && this.mListeners != null) {
            this.mListeners.remove(drawerListener);
        }
    }

    public void setDrawerLockMode(int i) {
        setDrawerLockMode(i, 3);
        setDrawerLockMode(i, 5);
    }

    public void setDrawerLockMode(int i, int i2) {
        int absoluteGravity = GravityCompat.getAbsoluteGravity(i2, ViewCompat.getLayoutDirection(this));
        if (i2 == 3) {
            this.mLockModeLeft = i;
        } else if (i2 == 5) {
            this.mLockModeRight = i;
        } else if (i2 == GravityCompat.START) {
            this.mLockModeStart = i;
        } else if (i2 == GravityCompat.END) {
            this.mLockModeEnd = i;
        }
        if (i != 0) {
            (absoluteGravity == 3 ? this.mLeftDragger : this.mRightDragger).cancel();
        }
        View findDrawerWithGravity;
        switch (i) {
            case 1:
                findDrawerWithGravity = findDrawerWithGravity(absoluteGravity);
                if (findDrawerWithGravity != 0) {
                    closeDrawer(findDrawerWithGravity);
                    return;
                }
                return;
            case 2:
                findDrawerWithGravity = findDrawerWithGravity(absoluteGravity);
                if (findDrawerWithGravity != 0) {
                    openDrawer(findDrawerWithGravity);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void setDrawerLockMode(int i, @NonNull View view) {
        if (isDrawerView(view)) {
            setDrawerLockMode(i, ((LayoutParams) view.getLayoutParams()).gravity);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("View ");
        stringBuilder.append(view);
        stringBuilder.append(" is not a ");
        stringBuilder.append("drawer with appropriate layout_gravity");
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public int getDrawerLockMode(int i) {
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        if (i != 3) {
            if (i != 5) {
                if (i != GravityCompat.START) {
                    if (i == GravityCompat.END) {
                        if (this.mLockModeEnd != 3) {
                            return this.mLockModeEnd;
                        }
                        if (layoutDirection == 0) {
                            i = this.mLockModeRight;
                        } else {
                            i = this.mLockModeLeft;
                        }
                        if (i != 3) {
                            return i;
                        }
                    }
                } else if (this.mLockModeStart != 3) {
                    return this.mLockModeStart;
                } else {
                    if (layoutDirection == 0) {
                        i = this.mLockModeLeft;
                    } else {
                        i = this.mLockModeRight;
                    }
                    if (i != 3) {
                        return i;
                    }
                }
            } else if (this.mLockModeRight != 3) {
                return this.mLockModeRight;
            } else {
                if (layoutDirection == 0) {
                    i = this.mLockModeEnd;
                } else {
                    i = this.mLockModeStart;
                }
                if (i != 3) {
                    return i;
                }
            }
        } else if (this.mLockModeLeft != 3) {
            return this.mLockModeLeft;
        } else {
            i = layoutDirection == 0 ? this.mLockModeStart : this.mLockModeEnd;
            if (i != 3) {
                return i;
            }
        }
        return 0;
    }

    public int getDrawerLockMode(@NonNull View view) {
        if (isDrawerView(view)) {
            return getDrawerLockMode(((LayoutParams) view.getLayoutParams()).gravity);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("View ");
        stringBuilder.append(view);
        stringBuilder.append(" is not a drawer");
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public void setDrawerTitle(int i, @Nullable CharSequence charSequence) {
        i = GravityCompat.getAbsoluteGravity(i, ViewCompat.getLayoutDirection(this));
        if (i == 3) {
            this.mTitleLeft = charSequence;
        } else if (i == 5) {
            this.mTitleRight = charSequence;
        }
    }

    @Nullable
    public CharSequence getDrawerTitle(int i) {
        i = GravityCompat.getAbsoluteGravity(i, ViewCompat.getLayoutDirection(this));
        if (i == 3) {
            return this.mTitleLeft;
        }
        return i == 5 ? this.mTitleRight : 0;
    }

    private boolean isInBoundsOfChild(float f, float f2, View view) {
        if (this.mChildHitRect == null) {
            this.mChildHitRect = new Rect();
        }
        view.getHitRect(this.mChildHitRect);
        return this.mChildHitRect.contains((int) f, (int) f2);
    }

    private boolean dispatchTransformedGenericPointerEvent(MotionEvent motionEvent, View view) {
        if (view.getMatrix().isIdentity()) {
            float scrollX = (float) (getScrollX() - view.getLeft());
            float scrollY = (float) (getScrollY() - view.getTop());
            motionEvent.offsetLocation(scrollX, scrollY);
            view = view.dispatchGenericMotionEvent(motionEvent);
            motionEvent.offsetLocation(-scrollX, -scrollY);
            return view;
        }
        motionEvent = getTransformedMotionEvent(motionEvent, view);
        view = view.dispatchGenericMotionEvent(motionEvent);
        motionEvent.recycle();
        return view;
    }

    private MotionEvent getTransformedMotionEvent(MotionEvent motionEvent, View view) {
        float scrollX = (float) (getScrollX() - view.getLeft());
        float scrollY = (float) (getScrollY() - view.getTop());
        motionEvent = MotionEvent.obtain(motionEvent);
        motionEvent.offsetLocation(scrollX, scrollY);
        view = view.getMatrix();
        if (!view.isIdentity()) {
            if (this.mChildInvertedMatrix == null) {
                this.mChildInvertedMatrix = new Matrix();
            }
            view.invert(this.mChildInvertedMatrix);
            motionEvent.transform(this.mChildInvertedMatrix);
        }
        return motionEvent;
    }

    void updateDrawerState(int i, int i2, View view) {
        LayoutParams layoutParams;
        i = this.mLeftDragger.getViewDragState();
        int viewDragState = this.mRightDragger.getViewDragState();
        int i3 = 2;
        if (i != 1) {
            if (viewDragState != 1) {
                if (i != 2) {
                    if (viewDragState != 2) {
                        i3 = 0;
                    }
                }
                if (view != null && i2 == 0) {
                    layoutParams = (LayoutParams) view.getLayoutParams();
                    if (layoutParams.onScreen == 0) {
                        dispatchOnDrawerClosed(view);
                    } else if (layoutParams.onScreen == 1065353216) {
                        dispatchOnDrawerOpened(view);
                    }
                }
                if (i3 != this.mDrawerState) {
                    this.mDrawerState = i3;
                    if (this.mListeners != 0) {
                        for (i = this.mListeners.size() - 1; i >= 0; i--) {
                            ((DrawerListener) this.mListeners.get(i)).onDrawerStateChanged(i3);
                        }
                    }
                }
            }
        }
        i3 = 1;
        layoutParams = (LayoutParams) view.getLayoutParams();
        if (layoutParams.onScreen == 0) {
            dispatchOnDrawerClosed(view);
        } else if (layoutParams.onScreen == 1065353216) {
            dispatchOnDrawerOpened(view);
        }
        if (i3 != this.mDrawerState) {
            this.mDrawerState = i3;
            if (this.mListeners != 0) {
                for (i = this.mListeners.size() - 1; i >= 0; i--) {
                    ((DrawerListener) this.mListeners.get(i)).onDrawerStateChanged(i3);
                }
            }
        }
    }

    void dispatchOnDrawerClosed(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if ((layoutParams.openState & 1) == 1) {
            layoutParams.openState = 0;
            if (this.mListeners != null) {
                for (int size = this.mListeners.size() - 1; size >= 0; size--) {
                    ((DrawerListener) this.mListeners.get(size)).onDrawerClosed(view);
                }
            }
            updateChildrenImportantForAccessibility(view, false);
            if (hasWindowFocus() != null) {
                view = getRootView();
                if (view != null) {
                    view.sendAccessibilityEvent(32);
                }
            }
        }
    }

    void dispatchOnDrawerOpened(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if ((layoutParams.openState & 1) == 0) {
            layoutParams.openState = 1;
            if (this.mListeners != null) {
                for (int size = this.mListeners.size() - 1; size >= 0; size--) {
                    ((DrawerListener) this.mListeners.get(size)).onDrawerOpened(view);
                }
            }
            updateChildrenImportantForAccessibility(view, CHILDREN_DISALLOW_INTERCEPT);
            if (hasWindowFocus() != null) {
                sendAccessibilityEvent(32);
            }
        }
    }

    private void updateChildrenImportantForAccessibility(View view, boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((z || isDrawerView(childAt)) && !(z && childAt == view)) {
                ViewCompat.setImportantForAccessibility(childAt, 4);
            } else {
                ViewCompat.setImportantForAccessibility(childAt, 1);
            }
        }
    }

    void dispatchOnDrawerSlide(View view, float f) {
        if (this.mListeners != null) {
            for (int size = this.mListeners.size() - 1; size >= 0; size--) {
                ((DrawerListener) this.mListeners.get(size)).onDrawerSlide(view, f);
            }
        }
    }

    void setDrawerViewOffset(View view, float f) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (f != layoutParams.onScreen) {
            layoutParams.onScreen = f;
            dispatchOnDrawerSlide(view, f);
        }
    }

    float getDrawerViewOffset(View view) {
        return ((LayoutParams) view.getLayoutParams()).onScreen;
    }

    int getDrawerViewAbsoluteGravity(View view) {
        return GravityCompat.getAbsoluteGravity(((LayoutParams) view.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(this));
    }

    boolean checkDrawerViewAbsoluteGravity(View view, int i) {
        return (getDrawerViewAbsoluteGravity(view) & i) == i ? CHILDREN_DISALLOW_INTERCEPT : null;
    }

    View findOpenDrawer() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((((LayoutParams) childAt.getLayoutParams()).openState & 1) == 1) {
                return childAt;
            }
        }
        return null;
    }

    void moveDrawerToOffset(View view, float f) {
        float width = (float) view.getWidth();
        int i = (int) (width * f);
        i -= (int) (getDrawerViewOffset(view) * width);
        if (!checkDrawerViewAbsoluteGravity(view, 3)) {
            i = -i;
        }
        view.offsetLeftAndRight(i);
        setDrawerViewOffset(view, f);
    }

    View findDrawerWithGravity(int i) {
        i = GravityCompat.getAbsoluteGravity(i, ViewCompat.getLayoutDirection(this)) & 7;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((getDrawerViewAbsoluteGravity(childAt) & 7) == i) {
                return childAt;
            }
        }
        return 0;
    }

    static String gravityToString(int i) {
        if ((i & 3) == 3) {
            return "LEFT";
        }
        return (i & 5) == 5 ? "RIGHT" : Integer.toHexString(i);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mFirstLayout = CHILDREN_DISALLOW_INTERCEPT;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = CHILDREN_DISALLOW_INTERCEPT;
    }

    @SuppressLint({"WrongConstant"})
    protected void onMeasure(int i, int i2) {
        DrawerLayout drawerLayout = this;
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (!(mode == 1073741824 && mode2 == 1073741824)) {
            if (isInEditMode()) {
                if (mode != Integer.MIN_VALUE) {
                    if (mode == 0) {
                        size = 300;
                    }
                }
                if (mode2 != Integer.MIN_VALUE) {
                    if (mode2 == 0) {
                        size2 = 300;
                    }
                }
            } else {
                throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
            }
        }
        setMeasuredDimension(size, size2);
        int i3 = 0;
        Object obj = (drawerLayout.mLastInsets == null || !ViewCompat.getFitsSystemWindows(this)) ? null : 1;
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        int childCount = getChildCount();
        int i4 = 0;
        Object obj2 = null;
        Object obj3 = null;
        while (i4 < childCount) {
            int absoluteGravity;
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (obj != null) {
                    absoluteGravity = GravityCompat.getAbsoluteGravity(layoutParams.gravity, layoutDirection);
                    WindowInsets windowInsets;
                    if (ViewCompat.getFitsSystemWindows(childAt)) {
                        if (VERSION.SDK_INT >= 21) {
                            windowInsets = (WindowInsets) drawerLayout.mLastInsets;
                            if (absoluteGravity == 3) {
                                windowInsets = windowInsets.replaceSystemWindowInsets(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), i3, windowInsets.getSystemWindowInsetBottom());
                            } else if (absoluteGravity == 5) {
                                windowInsets = windowInsets.replaceSystemWindowInsets(i3, windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
                            }
                            childAt.dispatchApplyWindowInsets(windowInsets);
                        }
                    } else if (VERSION.SDK_INT >= 21) {
                        windowInsets = (WindowInsets) drawerLayout.mLastInsets;
                        if (absoluteGravity == 3) {
                            windowInsets = windowInsets.replaceSystemWindowInsets(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), i3, windowInsets.getSystemWindowInsetBottom());
                        } else if (absoluteGravity == 5) {
                            windowInsets = windowInsets.replaceSystemWindowInsets(i3, windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
                        }
                        layoutParams.leftMargin = windowInsets.getSystemWindowInsetLeft();
                        layoutParams.topMargin = windowInsets.getSystemWindowInsetTop();
                        layoutParams.rightMargin = windowInsets.getSystemWindowInsetRight();
                        layoutParams.bottomMargin = windowInsets.getSystemWindowInsetBottom();
                    }
                }
                if (isContentView(childAt)) {
                    childAt.measure(MeasureSpec.makeMeasureSpec((size - layoutParams.leftMargin) - layoutParams.rightMargin, 1073741824), MeasureSpec.makeMeasureSpec((size2 - layoutParams.topMargin) - layoutParams.bottomMargin, 1073741824));
                } else if (isDrawerView(childAt)) {
                    if (SET_DRAWER_SHADOW_FROM_ELEVATION && ViewCompat.getElevation(childAt) != drawerLayout.mDrawerElevation) {
                        ViewCompat.setElevation(childAt, drawerLayout.mDrawerElevation);
                    }
                    mode2 = getDrawerViewAbsoluteGravity(childAt) & 7;
                    Object obj4 = mode2 == 3 ? 1 : null;
                    if (obj4 == null || r10 == null) {
                        if (obj4 == null) {
                            if (obj3 == null) {
                            }
                        }
                        if (obj4 != null) {
                            obj2 = 1;
                        } else {
                            obj3 = 1;
                        }
                        childAt.measure(getChildMeasureSpec(i, (drawerLayout.mMinDrawerMargin + layoutParams.leftMargin) + layoutParams.rightMargin, layoutParams.width), getChildMeasureSpec(i2, layoutParams.topMargin + layoutParams.bottomMargin, layoutParams.height));
                        i4++;
                        i3 = 0;
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Child drawer has absolute gravity ");
                    stringBuilder.append(gravityToString(mode2));
                    stringBuilder.append(" but this ");
                    stringBuilder.append(TAG);
                    stringBuilder.append(" already has a ");
                    stringBuilder.append("drawer view along that edge");
                    throw new IllegalStateException(stringBuilder.toString());
                } else {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Child ");
                    stringBuilder2.append(childAt);
                    stringBuilder2.append(" at index ");
                    stringBuilder2.append(i4);
                    stringBuilder2.append(" does not have a valid layout_gravity - must be Gravity.LEFT, ");
                    stringBuilder2.append("Gravity.RIGHT or Gravity.NO_GRAVITY");
                    throw new IllegalStateException(stringBuilder2.toString());
                }
            }
            absoluteGravity = i;
            int i5 = i2;
            i4++;
            i3 = 0;
        }
    }

    private void resolveShadowDrawables() {
        if (!SET_DRAWER_SHADOW_FROM_ELEVATION) {
            this.mShadowLeftResolved = resolveLeftShadow();
            this.mShadowRightResolved = resolveRightShadow();
        }
    }

    private Drawable resolveLeftShadow() {
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        if (layoutDirection == 0) {
            if (this.mShadowStart != null) {
                mirror(this.mShadowStart, layoutDirection);
                return this.mShadowStart;
            }
        } else if (this.mShadowEnd != null) {
            mirror(this.mShadowEnd, layoutDirection);
            return this.mShadowEnd;
        }
        return this.mShadowLeft;
    }

    private Drawable resolveRightShadow() {
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        if (layoutDirection == 0) {
            if (this.mShadowEnd != null) {
                mirror(this.mShadowEnd, layoutDirection);
                return this.mShadowEnd;
            }
        } else if (this.mShadowStart != null) {
            mirror(this.mShadowStart, layoutDirection);
            return this.mShadowStart;
        }
        return this.mShadowRight;
    }

    private boolean mirror(Drawable drawable, int i) {
        if (drawable != null) {
            if (DrawableCompat.isAutoMirrored(drawable)) {
                DrawableCompat.setLayoutDirection(drawable, i);
                return CHILDREN_DISALLOW_INTERCEPT;
            }
        }
        return null;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.mInLayout = CHILDREN_DISALLOW_INTERCEPT;
        int i5 = i3 - i;
        int childCount = getChildCount();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (isContentView(childAt)) {
                    childAt.layout(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.leftMargin + childAt.getMeasuredWidth(), layoutParams.topMargin + childAt.getMeasuredHeight());
                } else {
                    int i7;
                    float f;
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (checkDrawerViewAbsoluteGravity(childAt, 3)) {
                        float f2 = (float) measuredWidth;
                        i7 = (-measuredWidth) + ((int) (layoutParams.onScreen * f2));
                        f = ((float) (measuredWidth + i7)) / f2;
                    } else {
                        float f3 = (float) measuredWidth;
                        int i8 = i5 - ((int) (layoutParams.onScreen * f3));
                        f = ((float) (i5 - i8)) / f3;
                        i7 = i8;
                    }
                    Object obj = f != layoutParams.onScreen ? 1 : null;
                    int i9 = layoutParams.gravity & 112;
                    int i10;
                    if (i9 == 16) {
                        i10 = i4 - i2;
                        i9 = (i10 - measuredHeight) / 2;
                        if (i9 < layoutParams.topMargin) {
                            i9 = layoutParams.topMargin;
                        } else if (i9 + measuredHeight > i10 - layoutParams.bottomMargin) {
                            i9 = (i10 - layoutParams.bottomMargin) - measuredHeight;
                        }
                        childAt.layout(i7, i9, measuredWidth + i7, measuredHeight + i9);
                    } else if (i9 != 80) {
                        childAt.layout(i7, layoutParams.topMargin, measuredWidth + i7, layoutParams.topMargin + measuredHeight);
                    } else {
                        i10 = i4 - i2;
                        childAt.layout(i7, (i10 - layoutParams.bottomMargin) - childAt.getMeasuredHeight(), measuredWidth + i7, i10 - layoutParams.bottomMargin);
                    }
                    if (obj != null) {
                        setDrawerViewOffset(childAt, f);
                    }
                    int i11 = layoutParams.onScreen > 0.0f ? 0 : 4;
                    if (childAt.getVisibility() != i11) {
                        childAt.setVisibility(i11);
                    }
                }
            }
        }
        r0.mInLayout = false;
        r0.mFirstLayout = false;
    }

    public void requestLayout() {
        if (!this.mInLayout) {
            super.requestLayout();
        }
    }

    public void computeScroll() {
        int childCount = getChildCount();
        float f = 0.0f;
        for (int i = 0; i < childCount; i++) {
            f = Math.max(f, ((LayoutParams) getChildAt(i).getLayoutParams()).onScreen);
        }
        this.mScrimOpacity = f;
        boolean continueSettling = this.mLeftDragger.continueSettling(CHILDREN_DISALLOW_INTERCEPT);
        boolean continueSettling2 = this.mRightDragger.continueSettling(CHILDREN_DISALLOW_INTERCEPT);
        if (continueSettling || continueSettling2) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private static boolean hasOpaqueBackground(View view) {
        view = view.getBackground();
        boolean z = false;
        if (view == null) {
            return false;
        }
        if (view.getOpacity() == -1) {
            z = CHILDREN_DISALLOW_INTERCEPT;
        }
        return z;
    }

    public void setStatusBarBackground(@Nullable Drawable drawable) {
        this.mStatusBarBackground = drawable;
        invalidate();
    }

    @Nullable
    public Drawable getStatusBarBackgroundDrawable() {
        return this.mStatusBarBackground;
    }

    public void setStatusBarBackground(int i) {
        this.mStatusBarBackground = i != 0 ? ContextCompat.getDrawable(getContext(), i) : 0;
        invalidate();
    }

    public void setStatusBarBackgroundColor(@ColorInt int i) {
        this.mStatusBarBackground = new ColorDrawable(i);
        invalidate();
    }

    public void onRtlPropertiesChanged(int i) {
        resolveShadowDrawables();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mDrawStatusBarBackground && this.mStatusBarBackground != null) {
            int systemWindowInsetTop = (VERSION.SDK_INT < 21 || this.mLastInsets == null) ? 0 : ((WindowInsets) this.mLastInsets).getSystemWindowInsetTop();
            if (systemWindowInsetTop > 0) {
                this.mStatusBarBackground.setBounds(0, 0, getWidth(), systemWindowInsetTop);
                this.mStatusBarBackground.draw(canvas);
            }
        }
    }

    protected boolean drawChild(Canvas canvas, View view, long j) {
        int i;
        DrawerLayout drawerLayout = this;
        Canvas canvas2 = canvas;
        View view2 = view;
        int height = getHeight();
        boolean isContentView = isContentView(view2);
        int width = getWidth();
        int save = canvas.save();
        int i2 = 0;
        if (isContentView) {
            int childCount = getChildCount();
            i = width;
            int i3 = 0;
            for (width = 0; width < childCount; width++) {
                View childAt = getChildAt(width);
                if (childAt != view2 && childAt.getVisibility() == 0 && hasOpaqueBackground(childAt) && isDrawerView(childAt)) {
                    if (childAt.getHeight() >= height) {
                        int right;
                        if (checkDrawerViewAbsoluteGravity(childAt, 3)) {
                            right = childAt.getRight();
                            if (right > i3) {
                                i3 = right;
                            }
                        } else {
                            right = childAt.getLeft();
                            if (right < i) {
                                i = right;
                            }
                        }
                    }
                }
            }
            canvas.clipRect(i3, 0, i, getHeight());
            i2 = i3;
        } else {
            i = width;
        }
        boolean drawChild = super.drawChild(canvas, view, j);
        canvas.restoreToCount(save);
        if (drawerLayout.mScrimOpacity > 0.0f && isContentView) {
            drawerLayout.mScrimPaint.setColor((((int) (((float) ((drawerLayout.mScrimColor & ViewCompat.MEASURED_STATE_MASK) >>> 24)) * drawerLayout.mScrimOpacity)) << 24) | (drawerLayout.mScrimColor & ViewCompat.MEASURED_SIZE_MASK));
            canvas.drawRect((float) i2, 0.0f, (float) i, (float) getHeight(), drawerLayout.mScrimPaint);
        } else if (drawerLayout.mShadowLeftResolved != null && checkDrawerViewAbsoluteGravity(view2, 3)) {
            height = drawerLayout.mShadowLeftResolved.getIntrinsicWidth();
            r7 = view.getRight();
            r5 = Math.max(0.0f, Math.min(((float) r7) / ((float) drawerLayout.mLeftDragger.getEdgeSize()), TOUCH_SLOP_SENSITIVITY));
            drawerLayout.mShadowLeftResolved.setBounds(r7, view.getTop(), height + r7, view.getBottom());
            drawerLayout.mShadowLeftResolved.setAlpha((int) (r5 * 255.0f));
            drawerLayout.mShadowLeftResolved.draw(canvas);
        } else if (drawerLayout.mShadowRightResolved != null && checkDrawerViewAbsoluteGravity(view2, 5)) {
            height = drawerLayout.mShadowRightResolved.getIntrinsicWidth();
            r7 = view.getLeft();
            r5 = Math.max(0.0f, Math.min(((float) (getWidth() - r7)) / ((float) drawerLayout.mRightDragger.getEdgeSize()), TOUCH_SLOP_SENSITIVITY));
            drawerLayout.mShadowRightResolved.setBounds(r7 - height, view.getTop(), r7, view.getBottom());
            drawerLayout.mShadowRightResolved.setAlpha((int) (r5 * 255.0f));
            drawerLayout.mShadowRightResolved.draw(canvas);
        }
        return drawChild;
    }

    boolean isContentView(View view) {
        return ((LayoutParams) view.getLayoutParams()).gravity == null ? CHILDREN_DISALLOW_INTERCEPT : null;
    }

    boolean isDrawerView(View view) {
        view = GravityCompat.getAbsoluteGravity(((LayoutParams) view.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(view));
        return ((view & 3) == 0 && (view & 5) == null) ? null : CHILDREN_DISALLOW_INTERCEPT;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int shouldInterceptTouchEvent = this.mLeftDragger.shouldInterceptTouchEvent(motionEvent) | this.mRightDragger.shouldInterceptTouchEvent(motionEvent);
        switch (motionEvent.getActionMasked()) {
            case 0:
                float x = motionEvent.getX();
                motionEvent = motionEvent.getY();
                this.mInitialMotionX = x;
                this.mInitialMotionY = motionEvent;
                if (this.mScrimOpacity > 0.0f) {
                    motionEvent = this.mLeftDragger.findTopChildUnder((int) x, (int) motionEvent);
                    if (!(motionEvent == null || isContentView(motionEvent) == null)) {
                        motionEvent = CHILDREN_DISALLOW_INTERCEPT;
                        this.mDisallowInterceptRequested = false;
                        this.mChildrenCanceledTouch = false;
                        break;
                    }
                }
                motionEvent = null;
                this.mDisallowInterceptRequested = false;
                this.mChildrenCanceledTouch = false;
            case 1:
            case 3:
                closeDrawers(CHILDREN_DISALLOW_INTERCEPT);
                this.mDisallowInterceptRequested = false;
                this.mChildrenCanceledTouch = false;
                break;
            case 2:
                if (this.mLeftDragger.checkTouchSlop(3) != null) {
                    this.mLeftCallback.removeCallbacks();
                    this.mRightCallback.removeCallbacks();
                    break;
                }
                break;
            default:
                break;
        }
        motionEvent = null;
        if (shouldInterceptTouchEvent != 0 || r7 != null || hasPeekingDrawer() != null) {
            return CHILDREN_DISALLOW_INTERCEPT;
        }
        if (this.mChildrenCanceledTouch != null) {
            return CHILDREN_DISALLOW_INTERCEPT;
        }
        return false;
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        if (!((motionEvent.getSource() & 2) == 0 || motionEvent.getAction() == 10)) {
            if (this.mScrimOpacity > 0.0f) {
                int childCount = getChildCount();
                if (childCount != 0) {
                    float x = motionEvent.getX();
                    float y = motionEvent.getY();
                    for (childCount--; childCount >= 0; childCount--) {
                        View childAt = getChildAt(childCount);
                        if (isInBoundsOfChild(x, y, childAt)) {
                            if (!isContentView(childAt)) {
                                if (dispatchTransformedGenericPointerEvent(motionEvent, childAt)) {
                                    return CHILDREN_DISALLOW_INTERCEPT;
                                }
                            }
                        }
                    }
                }
                return null;
            }
        }
        return super.dispatchGenericMotionEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.mLeftDragger.processTouchEvent(motionEvent);
        this.mRightDragger.processTouchEvent(motionEvent);
        int action = motionEvent.getAction() & 255;
        if (action != 3) {
            float x;
            switch (action) {
                case 0:
                    x = motionEvent.getX();
                    motionEvent = motionEvent.getY();
                    this.mInitialMotionX = x;
                    this.mInitialMotionY = motionEvent;
                    this.mDisallowInterceptRequested = false;
                    this.mChildrenCanceledTouch = false;
                    break;
                case 1:
                    x = motionEvent.getX();
                    motionEvent = motionEvent.getY();
                    View findTopChildUnder = this.mLeftDragger.findTopChildUnder((int) x, (int) motionEvent);
                    if (findTopChildUnder != null && isContentView(findTopChildUnder)) {
                        x -= this.mInitialMotionX;
                        motionEvent -= this.mInitialMotionY;
                        int touchSlop = this.mLeftDragger.getTouchSlop();
                        if ((x * x) + (motionEvent * motionEvent) < ((float) (touchSlop * touchSlop))) {
                            View findOpenDrawer = findOpenDrawer();
                            if (findOpenDrawer != null) {
                                if (getDrawerLockMode(findOpenDrawer) != 2) {
                                    motionEvent = null;
                                    closeDrawers(motionEvent);
                                    this.mDisallowInterceptRequested = false;
                                    break;
                                }
                            }
                        }
                    }
                    motionEvent = CHILDREN_DISALLOW_INTERCEPT;
                    closeDrawers(motionEvent);
                    this.mDisallowInterceptRequested = false;
                default:
                    break;
            }
        }
        closeDrawers(CHILDREN_DISALLOW_INTERCEPT);
        this.mDisallowInterceptRequested = false;
        this.mChildrenCanceledTouch = false;
        return CHILDREN_DISALLOW_INTERCEPT;
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        this.mDisallowInterceptRequested = z;
        if (z) {
            closeDrawers(CHILDREN_DISALLOW_INTERCEPT);
        }
    }

    public void closeDrawers() {
        closeDrawers(false);
    }

    void closeDrawers(boolean z) {
        int childCount = getChildCount();
        int i = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (isDrawerView(childAt)) {
                if (!z || layoutParams.isPeeking) {
                    int width = childAt.getWidth();
                    if (checkDrawerViewAbsoluteGravity(childAt, 3)) {
                        i |= this.mLeftDragger.smoothSlideViewTo(childAt, -width, childAt.getTop());
                    } else {
                        i |= this.mRightDragger.smoothSlideViewTo(childAt, getWidth(), childAt.getTop());
                    }
                    layoutParams.isPeeking = false;
                }
            }
        }
        this.mLeftCallback.removeCallbacks();
        this.mRightCallback.removeCallbacks();
        if (i != 0) {
            invalidate();
        }
    }

    public void openDrawer(@NonNull View view) {
        openDrawer(view, (boolean) CHILDREN_DISALLOW_INTERCEPT);
    }

    public void openDrawer(@NonNull View view, boolean z) {
        if (isDrawerView(view)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (this.mFirstLayout) {
                layoutParams.onScreen = TOUCH_SLOP_SENSITIVITY;
                layoutParams.openState = 1;
                updateChildrenImportantForAccessibility(view, CHILDREN_DISALLOW_INTERCEPT);
            } else if (z) {
                layoutParams.openState |= 2;
                if (checkDrawerViewAbsoluteGravity(view, true)) {
                    this.mLeftDragger.smoothSlideViewTo(view, 0, view.getTop());
                } else {
                    this.mRightDragger.smoothSlideViewTo(view, getWidth() - view.getWidth(), view.getTop());
                }
            } else {
                moveDrawerToOffset(view, TOUCH_SLOP_SENSITIVITY);
                updateDrawerState(layoutParams.gravity, 0, view);
                view.setVisibility(0);
            }
            invalidate();
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("View ");
        stringBuilder.append(view);
        stringBuilder.append(" is not a sliding drawer");
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public void openDrawer(int i) {
        openDrawer(i, (boolean) CHILDREN_DISALLOW_INTERCEPT);
    }

    public void openDrawer(int i, boolean z) {
        View findDrawerWithGravity = findDrawerWithGravity(i);
        if (findDrawerWithGravity != null) {
            openDrawer(findDrawerWithGravity, z);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("No drawer view found with gravity ");
        stringBuilder.append(gravityToString(i));
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public void closeDrawer(@NonNull View view) {
        closeDrawer(view, (boolean) CHILDREN_DISALLOW_INTERCEPT);
    }

    public void closeDrawer(@NonNull View view, boolean z) {
        if (isDrawerView(view)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (this.mFirstLayout) {
                layoutParams.onScreen = 0.0f;
                layoutParams.openState = 0;
            } else if (z) {
                layoutParams.openState |= true;
                if (checkDrawerViewAbsoluteGravity(view, true)) {
                    this.mLeftDragger.smoothSlideViewTo(view, -view.getWidth(), view.getTop());
                } else {
                    this.mRightDragger.smoothSlideViewTo(view, getWidth(), view.getTop());
                }
            } else {
                moveDrawerToOffset(view, 0.0f);
                updateDrawerState(layoutParams.gravity, 0, view);
                view.setVisibility(4);
            }
            invalidate();
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("View ");
        stringBuilder.append(view);
        stringBuilder.append(" is not a sliding drawer");
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public void closeDrawer(int i) {
        closeDrawer(i, (boolean) CHILDREN_DISALLOW_INTERCEPT);
    }

    public void closeDrawer(int i, boolean z) {
        View findDrawerWithGravity = findDrawerWithGravity(i);
        if (findDrawerWithGravity != null) {
            closeDrawer(findDrawerWithGravity, z);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("No drawer view found with gravity ");
        stringBuilder.append(gravityToString(i));
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public boolean isDrawerOpen(@NonNull View view) {
        if (isDrawerView(view)) {
            return (((LayoutParams) view.getLayoutParams()).openState & 1) == 1 ? CHILDREN_DISALLOW_INTERCEPT : false;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("View ");
            stringBuilder.append(view);
            stringBuilder.append(" is not a drawer");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    public boolean isDrawerOpen(int i) {
        View findDrawerWithGravity = findDrawerWithGravity(i);
        return findDrawerWithGravity != 0 ? isDrawerOpen(findDrawerWithGravity) : false;
    }

    public boolean isDrawerVisible(@NonNull View view) {
        if (isDrawerView(view)) {
            return ((LayoutParams) view.getLayoutParams()).onScreen > 0.0f ? CHILDREN_DISALLOW_INTERCEPT : null;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("View ");
            stringBuilder.append(view);
            stringBuilder.append(" is not a drawer");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    public boolean isDrawerVisible(int i) {
        View findDrawerWithGravity = findDrawerWithGravity(i);
        return findDrawerWithGravity != 0 ? isDrawerVisible(findDrawerWithGravity) : false;
    }

    private boolean hasPeekingDrawer() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (((LayoutParams) getChildAt(i).getLayoutParams()).isPeeking) {
                return CHILDREN_DISALLOW_INTERCEPT;
            }
        }
        return false;
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        return layoutParams instanceof MarginLayoutParams ? new LayoutParams((MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return (!(layoutParams instanceof LayoutParams) || super.checkLayoutParams(layoutParams) == null) ? null : CHILDREN_DISALLOW_INTERCEPT;
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        if (getDescendantFocusability() != 393216) {
            int childCount = getChildCount();
            Object obj = null;
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                if (!isDrawerView(childAt)) {
                    this.mNonDrawerViews.add(childAt);
                } else if (isDrawerOpen(childAt)) {
                    childAt.addFocusables(arrayList, i, i2);
                    obj = 1;
                }
            }
            if (obj == null) {
                childCount = this.mNonDrawerViews.size();
                for (int i4 = 0; i4 < childCount; i4++) {
                    View view = (View) this.mNonDrawerViews.get(i4);
                    if (view.getVisibility() == 0) {
                        view.addFocusables(arrayList, i, i2);
                    }
                }
            }
            this.mNonDrawerViews.clear();
        }
    }

    private boolean hasVisibleDrawer() {
        return findVisibleDrawer() != null ? CHILDREN_DISALLOW_INTERCEPT : false;
    }

    View findVisibleDrawer() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (isDrawerView(childAt) && isDrawerVisible(childAt)) {
                return childAt;
            }
        }
        return null;
    }

    void cancelChildViewTouch() {
        if (!this.mChildrenCanceledTouch) {
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                getChildAt(i).dispatchTouchEvent(obtain);
            }
            obtain.recycle();
            this.mChildrenCanceledTouch = CHILDREN_DISALLOW_INTERCEPT;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || !hasVisibleDrawer()) {
            return super.onKeyDown(i, keyEvent);
        }
        keyEvent.startTracking();
        return CHILDREN_DISALLOW_INTERCEPT;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        View findVisibleDrawer = findVisibleDrawer();
        if (findVisibleDrawer != 0 && getDrawerLockMode(findVisibleDrawer) == null) {
            closeDrawers();
        }
        return findVisibleDrawer != null ? 1 : 0;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            if (savedState.openDrawerGravity != 0) {
                View findDrawerWithGravity = findDrawerWithGravity(savedState.openDrawerGravity);
                if (findDrawerWithGravity != null) {
                    openDrawer(findDrawerWithGravity);
                }
            }
            if (savedState.lockModeLeft != 3) {
                setDrawerLockMode(savedState.lockModeLeft, 3);
            }
            if (savedState.lockModeRight != 3) {
                setDrawerLockMode(savedState.lockModeRight, 5);
            }
            if (savedState.lockModeStart != 3) {
                setDrawerLockMode(savedState.lockModeStart, (int) GravityCompat.START);
            }
            if (savedState.lockModeEnd != 3) {
                setDrawerLockMode(savedState.lockModeEnd, (int) GravityCompat.END);
            }
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        int childCount = getChildCount();
        int i = 0;
        while (i < childCount) {
            LayoutParams layoutParams = (LayoutParams) getChildAt(i).getLayoutParams();
            Object obj = 1;
            Object obj2 = layoutParams.openState == 1 ? 1 : null;
            if (layoutParams.openState != 2) {
                obj = null;
            }
            if (obj2 == null) {
                if (obj == null) {
                    i++;
                }
            }
            savedState.openDrawerGravity = layoutParams.gravity;
            break;
        }
        savedState.lockModeLeft = this.mLockModeLeft;
        savedState.lockModeRight = this.mLockModeRight;
        savedState.lockModeStart = this.mLockModeStart;
        savedState.lockModeEnd = this.mLockModeEnd;
        return savedState;
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (findOpenDrawer() == 0) {
            if (isDrawerView(view) == 0) {
                ViewCompat.setImportantForAccessibility(view, 1);
                if (CAN_HIDE_DESCENDANTS == 0) {
                    ViewCompat.setAccessibilityDelegate(view, this.mChildAccessibilityDelegate);
                }
            }
        }
        ViewCompat.setImportantForAccessibility(view, 4);
        if (CAN_HIDE_DESCENDANTS == 0) {
            ViewCompat.setAccessibilityDelegate(view, this.mChildAccessibilityDelegate);
        }
    }

    static boolean includeChildForAccessibility(View view) {
        return (ViewCompat.getImportantForAccessibility(view) == 4 || ViewCompat.getImportantForAccessibility(view) == 2) ? null : CHILDREN_DISALLOW_INTERCEPT;
    }
}
