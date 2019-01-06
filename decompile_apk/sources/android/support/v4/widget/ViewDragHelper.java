package android.support.v4.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import java.util.Arrays;

public class ViewDragHelper {
    private static final int BASE_SETTLE_DURATION = 256;
    public static final int DIRECTION_ALL = 3;
    public static final int DIRECTION_HORIZONTAL = 1;
    public static final int DIRECTION_VERTICAL = 2;
    public static final int EDGE_ALL = 15;
    public static final int EDGE_BOTTOM = 8;
    public static final int EDGE_LEFT = 1;
    public static final int EDGE_RIGHT = 2;
    private static final int EDGE_SIZE = 20;
    public static final int EDGE_TOP = 4;
    public static final int INVALID_POINTER = -1;
    private static final int MAX_SETTLE_DURATION = 600;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    private static final String TAG = "ViewDragHelper";
    private static final Interpolator sInterpolator = new C01751();
    private int mActivePointerId = -1;
    private final Callback mCallback;
    private View mCapturedView;
    private int mDragState;
    private int[] mEdgeDragsInProgress;
    private int[] mEdgeDragsLocked;
    private int mEdgeSize;
    private int[] mInitialEdgesTouched;
    private float[] mInitialMotionX;
    private float[] mInitialMotionY;
    private float[] mLastMotionX;
    private float[] mLastMotionY;
    private float mMaxVelocity;
    private float mMinVelocity;
    private final ViewGroup mParentView;
    private int mPointersDown;
    private boolean mReleaseInProgress;
    private OverScroller mScroller;
    private final Runnable mSetIdleRunnable = new C01762();
    private int mTouchSlop;
    private int mTrackingEdges;
    private VelocityTracker mVelocityTracker;

    /* renamed from: android.support.v4.widget.ViewDragHelper$1 */
    static class C01751 implements Interpolator {
        public float getInterpolation(float f) {
            f -= 1.0f;
            return ((((f * f) * f) * f) * f) + 1.0f;
        }

        C01751() {
        }
    }

    /* renamed from: android.support.v4.widget.ViewDragHelper$2 */
    class C01762 implements Runnable {
        C01762() {
        }

        public void run() {
            ViewDragHelper.this.setDragState(0);
        }
    }

    public static abstract class Callback {
        public int clampViewPositionHorizontal(@NonNull View view, int i, int i2) {
            return 0;
        }

        public int clampViewPositionVertical(@NonNull View view, int i, int i2) {
            return 0;
        }

        public int getOrderedChildIndex(int i) {
            return i;
        }

        public int getViewHorizontalDragRange(@NonNull View view) {
            return 0;
        }

        public int getViewVerticalDragRange(@NonNull View view) {
            return 0;
        }

        public void onEdgeDragStarted(int i, int i2) {
        }

        public boolean onEdgeLock(int i) {
            return false;
        }

        public void onEdgeTouched(int i, int i2) {
        }

        public void onViewCaptured(@NonNull View view, int i) {
        }

        public void onViewDragStateChanged(int i) {
        }

        public void onViewPositionChanged(@NonNull View view, int i, int i2, @Px int i3, @Px int i4) {
        }

        public void onViewReleased(@NonNull View view, float f, float f2) {
        }

        public abstract boolean tryCaptureView(@NonNull View view, int i);
    }

    public static ViewDragHelper create(@NonNull ViewGroup viewGroup, @NonNull Callback callback) {
        return new ViewDragHelper(viewGroup.getContext(), viewGroup, callback);
    }

    public static ViewDragHelper create(@NonNull ViewGroup viewGroup, float f, @NonNull Callback callback) {
        viewGroup = create(viewGroup, callback);
        viewGroup.mTouchSlop = (int) (((float) viewGroup.mTouchSlop) * (1.0f / f));
        return viewGroup;
    }

    private ViewDragHelper(@NonNull Context context, @NonNull ViewGroup viewGroup, @NonNull Callback callback) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        } else if (callback != null) {
            this.mParentView = viewGroup;
            this.mCallback = callback;
            viewGroup = ViewConfiguration.get(context);
            this.mEdgeSize = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
            this.mTouchSlop = viewGroup.getScaledTouchSlop();
            this.mMaxVelocity = (float) viewGroup.getScaledMaximumFlingVelocity();
            this.mMinVelocity = (float) viewGroup.getScaledMinimumFlingVelocity();
            this.mScroller = new OverScroller(context, sInterpolator);
        } else {
            throw new IllegalArgumentException("Callback may not be null");
        }
    }

    public void setMinVelocity(float f) {
        this.mMinVelocity = f;
    }

    public float getMinVelocity() {
        return this.mMinVelocity;
    }

    public int getViewDragState() {
        return this.mDragState;
    }

    public void setEdgeTrackingEnabled(int i) {
        this.mTrackingEdges = i;
    }

    @Px
    public int getEdgeSize() {
        return this.mEdgeSize;
    }

    public void captureChildView(@NonNull View view, int i) {
        if (view.getParent() == this.mParentView) {
            this.mCapturedView = view;
            this.mActivePointerId = i;
            this.mCallback.onViewCaptured(view, i);
            setDragState(1);
            return;
        }
        i = new StringBuilder();
        i.append("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (");
        i.append(this.mParentView);
        i.append(")");
        throw new IllegalArgumentException(i.toString());
    }

    @Nullable
    public View getCapturedView() {
        return this.mCapturedView;
    }

    public int getActivePointerId() {
        return this.mActivePointerId;
    }

    @Px
    public int getTouchSlop() {
        return this.mTouchSlop;
    }

    public void cancel() {
        this.mActivePointerId = -1;
        clearMotionHistory();
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public void abort() {
        cancel();
        if (this.mDragState == 2) {
            int currX = this.mScroller.getCurrX();
            int currY = this.mScroller.getCurrY();
            this.mScroller.abortAnimation();
            int currX2 = this.mScroller.getCurrX();
            int currY2 = this.mScroller.getCurrY();
            this.mCallback.onViewPositionChanged(this.mCapturedView, currX2, currY2, currX2 - currX, currY2 - currY);
        }
        setDragState(0);
    }

    public boolean smoothSlideViewTo(@NonNull View view, int i, int i2) {
        this.mCapturedView = view;
        this.mActivePointerId = -1;
        view = forceSettleCapturedViewAt(i, i2, 0, 0);
        if (view == null && this.mDragState == 0 && this.mCapturedView != 0) {
            this.mCapturedView = 0;
        }
        return view;
    }

    public boolean settleCapturedViewAt(int i, int i2) {
        if (this.mReleaseInProgress) {
            return forceSettleCapturedViewAt(i, i2, (int) this.mVelocityTracker.getXVelocity(this.mActivePointerId), (int) this.mVelocityTracker.getYVelocity(this.mActivePointerId));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    private boolean forceSettleCapturedViewAt(int i, int i2, int i3, int i4) {
        int left = this.mCapturedView.getLeft();
        int top = this.mCapturedView.getTop();
        i -= left;
        i2 -= top;
        if (i == 0 && i2 == 0) {
            this.mScroller.abortAnimation();
            setDragState(0);
            return false;
        }
        this.mScroller.startScroll(left, top, i, i2, computeSettleDuration(this.mCapturedView, i, i2, i3, i4));
        setDragState(2);
        return true;
    }

    private int computeSettleDuration(View view, int i, int i2, int i3, int i4) {
        float f;
        float f2;
        float f3;
        i3 = clampMag(i3, (int) this.mMinVelocity, (int) this.mMaxVelocity);
        i4 = clampMag(i4, (int) this.mMinVelocity, (int) this.mMaxVelocity);
        int abs = Math.abs(i);
        int abs2 = Math.abs(i2);
        int abs3 = Math.abs(i3);
        int abs4 = Math.abs(i4);
        int i5 = abs3 + abs4;
        int i6 = abs + abs2;
        if (i3 != 0) {
            f = (float) abs3;
            f2 = (float) i5;
        } else {
            f = (float) abs;
            f2 = (float) i6;
        }
        f /= f2;
        if (i4 != 0) {
            f3 = (float) abs4;
            f2 = (float) i5;
        } else {
            f3 = (float) abs2;
            f2 = (float) i6;
        }
        f3 /= f2;
        return (int) ((((float) computeAxisDuration(i, i3, this.mCallback.getViewHorizontalDragRange(view))) * f) + (((float) computeAxisDuration(i2, i4, this.mCallback.getViewVerticalDragRange(view))) * f3));
    }

    private int computeAxisDuration(int i, int i2, int i3) {
        if (i == 0) {
            return 0;
        }
        int width = this.mParentView.getWidth();
        float f = (float) (width / 2);
        f += distanceInfluenceForSnapDuration(Math.min(1.0f, ((float) Math.abs(i)) / ((float) width))) * f;
        i2 = Math.abs(i2);
        if (i2 > 0) {
            i = Math.round(Math.abs(f / ((float) i2)) * 1148846080) * 4;
        } else {
            i = (int) (((((float) Math.abs(i)) / ((float) i3)) + 1065353216) * 1132462080);
        }
        return Math.min(i, MAX_SETTLE_DURATION);
    }

    private int clampMag(int i, int i2, int i3) {
        int abs = Math.abs(i);
        if (abs < i2) {
            return 0;
        }
        if (abs <= i3) {
            return i;
        }
        if (i <= 0) {
            i3 = -i3;
        }
        return i3;
    }

    private float clampMag(float f, float f2, float f3) {
        float abs = Math.abs(f);
        if (abs < f2) {
            return 0.0f;
        }
        if (abs <= f3) {
            return f;
        }
        if (f <= 0.0f) {
            f3 = -f3;
        }
        return f3;
    }

    private float distanceInfluenceForSnapDuration(float f) {
        return (float) Math.sin((double) ((f - 0.5f) * 0.47123894f));
    }

    public void flingCapturedView(int i, int i2, int i3, int i4) {
        if (this.mReleaseInProgress) {
            this.mScroller.fling(this.mCapturedView.getLeft(), this.mCapturedView.getTop(), (int) this.mVelocityTracker.getXVelocity(this.mActivePointerId), (int) this.mVelocityTracker.getYVelocity(this.mActivePointerId), i, i3, i2, i4);
            setDragState(2);
            return;
        }
        throw new IllegalStateException("Cannot flingCapturedView outside of a call to Callback#onViewReleased");
    }

    public boolean continueSettling(boolean z) {
        if (this.mDragState == 2) {
            boolean computeScrollOffset = this.mScroller.computeScrollOffset();
            int currX = this.mScroller.getCurrX();
            int currY = this.mScroller.getCurrY();
            int left = currX - this.mCapturedView.getLeft();
            int top = currY - this.mCapturedView.getTop();
            if (left != 0) {
                ViewCompat.offsetLeftAndRight(this.mCapturedView, left);
            }
            if (top != 0) {
                ViewCompat.offsetTopAndBottom(this.mCapturedView, top);
            }
            if (!(left == 0 && top == 0)) {
                this.mCallback.onViewPositionChanged(this.mCapturedView, currX, currY, left, top);
            }
            if (computeScrollOffset && currX == this.mScroller.getFinalX() && currY == this.mScroller.getFinalY()) {
                this.mScroller.abortAnimation();
                computeScrollOffset = false;
            }
            if (!computeScrollOffset) {
                if (z) {
                    this.mParentView.post(this.mSetIdleRunnable);
                } else {
                    setDragState(0);
                }
            }
        }
        if (this.mDragState) {
            return true;
        }
        return false;
    }

    private void dispatchViewReleased(float f, float f2) {
        this.mReleaseInProgress = true;
        this.mCallback.onViewReleased(this.mCapturedView, f, f2);
        this.mReleaseInProgress = false;
        if (this.mDragState == Float.MIN_VALUE) {
            setDragState(0);
        }
    }

    private void clearMotionHistory() {
        if (this.mInitialMotionX != null) {
            Arrays.fill(this.mInitialMotionX, 0.0f);
            Arrays.fill(this.mInitialMotionY, 0.0f);
            Arrays.fill(this.mLastMotionX, 0.0f);
            Arrays.fill(this.mLastMotionY, 0.0f);
            Arrays.fill(this.mInitialEdgesTouched, 0);
            Arrays.fill(this.mEdgeDragsInProgress, 0);
            Arrays.fill(this.mEdgeDragsLocked, 0);
            this.mPointersDown = 0;
        }
    }

    private void clearMotionHistory(int i) {
        if (this.mInitialMotionX != null) {
            if (isPointerDown(i)) {
                this.mInitialMotionX[i] = 0.0f;
                this.mInitialMotionY[i] = 0.0f;
                this.mLastMotionX[i] = 0.0f;
                this.mLastMotionY[i] = 0.0f;
                this.mInitialEdgesTouched[i] = 0;
                this.mEdgeDragsInProgress[i] = 0;
                this.mEdgeDragsLocked[i] = 0;
                this.mPointersDown = (~(1 << i)) & this.mPointersDown;
            }
        }
    }

    private void ensureMotionHistorySizeForId(int i) {
        if (this.mInitialMotionX == null || this.mInitialMotionX.length <= i) {
            i++;
            Object obj = new float[i];
            Object obj2 = new float[i];
            Object obj3 = new float[i];
            Object obj4 = new float[i];
            Object obj5 = new int[i];
            Object obj6 = new int[i];
            i = new int[i];
            if (this.mInitialMotionX != null) {
                System.arraycopy(this.mInitialMotionX, 0, obj, 0, this.mInitialMotionX.length);
                System.arraycopy(this.mInitialMotionY, 0, obj2, 0, this.mInitialMotionY.length);
                System.arraycopy(this.mLastMotionX, 0, obj3, 0, this.mLastMotionX.length);
                System.arraycopy(this.mLastMotionY, 0, obj4, 0, this.mLastMotionY.length);
                System.arraycopy(this.mInitialEdgesTouched, 0, obj5, 0, this.mInitialEdgesTouched.length);
                System.arraycopy(this.mEdgeDragsInProgress, 0, obj6, 0, this.mEdgeDragsInProgress.length);
                System.arraycopy(this.mEdgeDragsLocked, 0, i, 0, this.mEdgeDragsLocked.length);
            }
            this.mInitialMotionX = obj;
            this.mInitialMotionY = obj2;
            this.mLastMotionX = obj3;
            this.mLastMotionY = obj4;
            this.mInitialEdgesTouched = obj5;
            this.mEdgeDragsInProgress = obj6;
            this.mEdgeDragsLocked = i;
        }
    }

    private void saveInitialMotion(float f, float f2, int i) {
        ensureMotionHistorySizeForId(i);
        float[] fArr = this.mInitialMotionX;
        this.mLastMotionX[i] = f;
        fArr[i] = f;
        fArr = this.mInitialMotionY;
        this.mLastMotionY[i] = f2;
        fArr[i] = f2;
        this.mInitialEdgesTouched[i] = getEdgesTouched((int) f, (int) f2);
        this.mPointersDown |= Float.MIN_VALUE << i;
    }

    private void saveLastMotion(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            int pointerId = motionEvent.getPointerId(i);
            if (isValidPointerForActionMove(pointerId)) {
                float x = motionEvent.getX(i);
                float y = motionEvent.getY(i);
                this.mLastMotionX[pointerId] = x;
                this.mLastMotionY[pointerId] = y;
            }
        }
    }

    public boolean isPointerDown(int i) {
        return ((1 << i) & this.mPointersDown) != 0;
    }

    void setDragState(int i) {
        this.mParentView.removeCallbacks(this.mSetIdleRunnable);
        if (this.mDragState != i) {
            this.mDragState = i;
            this.mCallback.onViewDragStateChanged(i);
            if (this.mDragState == 0) {
                this.mCapturedView = 0;
            }
        }
    }

    boolean tryCaptureViewForDrag(View view, int i) {
        if (view == this.mCapturedView && this.mActivePointerId == i) {
            return true;
        }
        if (view == null || !this.mCallback.tryCaptureView(view, i)) {
            return null;
        }
        this.mActivePointerId = i;
        captureChildView(view, i);
        return true;
    }

    protected boolean canScroll(@NonNull View view, boolean z, int i, int i2, int i3, int i4) {
        View view2 = view;
        boolean z2 = true;
        if (view2 instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view2;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i5 = i3 + scrollX;
                if (i5 >= childAt.getLeft() && i5 < childAt.getRight()) {
                    int i6 = i4 + scrollY;
                    if (i6 >= childAt.getTop() && i6 < childAt.getBottom()) {
                        if (canScroll(childAt, true, i, i2, i5 - childAt.getLeft(), i6 - childAt.getTop())) {
                            return true;
                        }
                    }
                }
            }
        }
        if (z) {
            if (!view.canScrollHorizontally(-i)) {
                if (view.canScrollVertically(-i2)) {
                }
            }
            return z2;
        }
        z2 = false;
        return z2;
    }

    public boolean shouldInterceptTouchEvent(@NonNull MotionEvent motionEvent) {
        boolean z;
        ViewDragHelper viewDragHelper = this;
        MotionEvent motionEvent2 = motionEvent;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            cancel();
        }
        if (viewDragHelper.mVelocityTracker == null) {
            viewDragHelper.mVelocityTracker = VelocityTracker.obtain();
        }
        viewDragHelper.mVelocityTracker.addMovement(motionEvent2);
        int pointerId;
        float x;
        switch (actionMasked) {
            case 0:
                float x2 = motionEvent.getX();
                float y = motionEvent.getY();
                z = false;
                pointerId = motionEvent2.getPointerId(0);
                saveInitialMotion(x2, y, pointerId);
                View findTopChildUnder = findTopChildUnder((int) x2, (int) y);
                if (findTopChildUnder == viewDragHelper.mCapturedView && viewDragHelper.mDragState == 2) {
                    tryCaptureViewForDrag(findTopChildUnder, pointerId);
                }
                actionMasked = viewDragHelper.mInitialEdgesTouched[pointerId];
                if ((viewDragHelper.mTrackingEdges & actionMasked) != 0) {
                    viewDragHelper.mCallback.onEdgeTouched(actionMasked & viewDragHelper.mTrackingEdges, pointerId);
                    break;
                }
                break;
            case 1:
            case 3:
                cancel();
                break;
            case 2:
                if (viewDragHelper.mInitialMotionX != null) {
                    if (viewDragHelper.mInitialMotionY == null) {
                        break;
                    }
                    actionMasked = motionEvent.getPointerCount();
                    for (actionIndex = 0; actionIndex < actionMasked; actionIndex++) {
                        int pointerId2 = motionEvent2.getPointerId(actionIndex);
                        if (isValidPointerForActionMove(pointerId2)) {
                            x = motionEvent2.getX(actionIndex);
                            float y2 = motionEvent2.getY(actionIndex);
                            float f = x - viewDragHelper.mInitialMotionX[pointerId2];
                            float f2 = y2 - viewDragHelper.mInitialMotionY[pointerId2];
                            View findTopChildUnder2 = findTopChildUnder((int) x, (int) y2);
                            Object obj = (findTopChildUnder2 == null || !checkTouchSlop(findTopChildUnder2, f, f2)) ? null : 1;
                            if (obj != null) {
                                int left = findTopChildUnder2.getLeft();
                                int i = (int) f;
                                i = viewDragHelper.mCallback.clampViewPositionHorizontal(findTopChildUnder2, left + i, i);
                                int top = findTopChildUnder2.getTop();
                                int i2 = (int) f2;
                                int clampViewPositionVertical = viewDragHelper.mCallback.clampViewPositionVertical(findTopChildUnder2, top + i2, i2);
                                i2 = viewDragHelper.mCallback.getViewHorizontalDragRange(findTopChildUnder2);
                                int viewVerticalDragRange = viewDragHelper.mCallback.getViewVerticalDragRange(findTopChildUnder2);
                                if (i2 == 0 || (i2 > 0 && i == left)) {
                                    if (viewVerticalDragRange != 0) {
                                        if (viewVerticalDragRange > 0 && clampViewPositionVertical == top) {
                                        }
                                    }
                                    saveLastMotion(motionEvent);
                                    break;
                                }
                            }
                            reportNewEdgeDrags(f, f2, pointerId2);
                            if (viewDragHelper.mDragState != 1) {
                                if (obj != null && tryCaptureViewForDrag(findTopChildUnder2, pointerId2)) {
                                }
                            }
                            saveLastMotion(motionEvent);
                        }
                    }
                    saveLastMotion(motionEvent);
                }
                break;
            case 5:
                actionMasked = motionEvent2.getPointerId(actionIndex);
                x = motionEvent2.getX(actionIndex);
                float y3 = motionEvent2.getY(actionIndex);
                saveInitialMotion(x, y3, actionMasked);
                if (viewDragHelper.mDragState != 0) {
                    if (viewDragHelper.mDragState == 2) {
                        View findTopChildUnder3 = findTopChildUnder((int) x, (int) y3);
                        if (findTopChildUnder3 == viewDragHelper.mCapturedView) {
                            tryCaptureViewForDrag(findTopChildUnder3, actionMasked);
                            break;
                        }
                    }
                }
                pointerId = viewDragHelper.mInitialEdgesTouched[actionMasked];
                if ((viewDragHelper.mTrackingEdges & pointerId) != 0) {
                    viewDragHelper.mCallback.onEdgeTouched(pointerId & viewDragHelper.mTrackingEdges, actionMasked);
                    break;
                }
                break;
            case 6:
                clearMotionHistory(motionEvent2.getPointerId(actionIndex));
                break;
        }
        z = false;
        return viewDragHelper.mDragState == 1 ? true : z;
    }

    public void processTouchEvent(@NonNull MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            cancel();
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int i = 0;
        float y;
        switch (actionMasked) {
            case 0:
                float x = motionEvent.getX();
                y = motionEvent.getY();
                motionEvent = motionEvent.getPointerId(0);
                View findTopChildUnder = findTopChildUnder((int) x, (int) y);
                saveInitialMotion(x, y, motionEvent);
                tryCaptureViewForDrag(findTopChildUnder, motionEvent);
                actionMasked = this.mInitialEdgesTouched[motionEvent];
                if ((this.mTrackingEdges & actionMasked) != 0) {
                    this.mCallback.onEdgeTouched(actionMasked & this.mTrackingEdges, motionEvent);
                    return;
                }
                return;
            case 1:
                if (this.mDragState == 1) {
                    releaseViewForPointerUp();
                }
                cancel();
                return;
            case 2:
                if (this.mDragState != 1) {
                    actionMasked = motionEvent.getPointerCount();
                    while (i < actionMasked) {
                        actionIndex = motionEvent.getPointerId(i);
                        if (isValidPointerForActionMove(actionIndex)) {
                            float x2 = motionEvent.getX(i);
                            float y2 = motionEvent.getY(i);
                            float f = x2 - this.mInitialMotionX[actionIndex];
                            float f2 = y2 - this.mInitialMotionY[actionIndex];
                            reportNewEdgeDrags(f, f2, actionIndex);
                            if (this.mDragState != 1) {
                                View findTopChildUnder2 = findTopChildUnder((int) x2, (int) y2);
                                if (checkTouchSlop(findTopChildUnder2, f, f2) && tryCaptureViewForDrag(findTopChildUnder2, actionIndex)) {
                                }
                            }
                            saveLastMotion(motionEvent);
                            return;
                        }
                        i++;
                    }
                    saveLastMotion(motionEvent);
                    return;
                } else if (isValidPointerForActionMove(this.mActivePointerId)) {
                    actionMasked = motionEvent.findPointerIndex(this.mActivePointerId);
                    y = motionEvent.getX(actionMasked);
                    actionIndex = (int) (y - this.mLastMotionX[this.mActivePointerId]);
                    actionMasked = (int) (motionEvent.getY(actionMasked) - this.mLastMotionY[this.mActivePointerId]);
                    dragTo(this.mCapturedView.getLeft() + actionIndex, this.mCapturedView.getTop() + actionMasked, actionIndex, actionMasked);
                    saveLastMotion(motionEvent);
                    return;
                } else {
                    return;
                }
            case 3:
                if (this.mDragState == 1) {
                    dispatchViewReleased(0.0f, 0.0f);
                }
                cancel();
                return;
            case 5:
                actionMasked = motionEvent.getPointerId(actionIndex);
                float x3 = motionEvent.getX(actionIndex);
                motionEvent = motionEvent.getY(actionIndex);
                saveInitialMotion(x3, motionEvent, actionMasked);
                if (this.mDragState == 0) {
                    tryCaptureViewForDrag(findTopChildUnder((int) x3, (int) motionEvent), actionMasked);
                    motionEvent = this.mInitialEdgesTouched[actionMasked];
                    if ((this.mTrackingEdges & motionEvent) != 0) {
                        this.mCallback.onEdgeTouched(motionEvent & this.mTrackingEdges, actionMasked);
                        return;
                    }
                    return;
                } else if (isCapturedViewUnder((int) x3, (int) motionEvent) != null) {
                    tryCaptureViewForDrag(this.mCapturedView, actionMasked);
                    return;
                } else {
                    return;
                }
            case 6:
                actionMasked = motionEvent.getPointerId(actionIndex);
                if (this.mDragState == 1 && actionMasked == this.mActivePointerId) {
                    actionIndex = motionEvent.getPointerCount();
                    while (i < actionIndex) {
                        int pointerId = motionEvent.getPointerId(i);
                        if (pointerId != this.mActivePointerId) {
                            if (findTopChildUnder((int) motionEvent.getX(i), (int) motionEvent.getY(i)) == this.mCapturedView && tryCaptureViewForDrag(this.mCapturedView, pointerId)) {
                                motionEvent = this.mActivePointerId;
                                if (motionEvent == -1) {
                                    releaseViewForPointerUp();
                                }
                            }
                        }
                        i++;
                    }
                    motionEvent = -1;
                    if (motionEvent == -1) {
                        releaseViewForPointerUp();
                    }
                }
                clearMotionHistory(actionMasked);
                return;
            default:
                return;
        }
    }

    private void reportNewEdgeDrags(float f, float f2, int i) {
        int i2 = 1;
        if (!checkNewEdgeDrag(f, f2, i, 1)) {
            i2 = 0;
        }
        if (checkNewEdgeDrag(f2, f, i, 4)) {
            i2 |= 4;
        }
        if (checkNewEdgeDrag(f, f2, i, 2)) {
            i2 |= 2;
        }
        if (checkNewEdgeDrag(f2, f, i, 8) != null) {
            i2 |= 8;
        }
        if (i2 != 0) {
            f = this.mEdgeDragsInProgress;
            f[i] = f[i] | i2;
            this.mCallback.onEdgeDragStarted(i2, i);
        }
    }

    private boolean checkNewEdgeDrag(float f, float f2, int i, int i2) {
        f = Math.abs(f);
        f2 = Math.abs(f2);
        boolean z = false;
        if (!((this.mInitialEdgesTouched[i] & i2) != i2 || (this.mTrackingEdges & i2) == 0 || (this.mEdgeDragsLocked[i] & i2) == i2 || (this.mEdgeDragsInProgress[i] & i2) == i2)) {
            if (f > ((float) this.mTouchSlop) || f2 > ((float) this.mTouchSlop)) {
                if (f >= f2 * 0.5f || this.mCallback.onEdgeLock(i2) == null) {
                    if ((this.mEdgeDragsInProgress[i] & i2) == null && f > ((float) this.mTouchSlop)) {
                        z = true;
                    }
                    return z;
                }
                f = this.mEdgeDragsLocked;
                f[i] = f[i] | i2;
                return false;
            }
        }
        return false;
    }

    private boolean checkTouchSlop(View view, float f, float f2) {
        boolean z = false;
        if (view == null) {
            return false;
        }
        Object obj = this.mCallback.getViewHorizontalDragRange(view) > 0 ? 1 : null;
        view = this.mCallback.getViewVerticalDragRange(view) > null ? true : null;
        if (obj != null && view != null) {
            if ((f * f) + (f2 * f2) > ((float) (this.mTouchSlop * this.mTouchSlop))) {
                z = true;
            }
            return z;
        } else if (obj != null) {
            if (Math.abs(f) > ((float) this.mTouchSlop)) {
                z = true;
            }
            return z;
        } else if (view == null) {
            return false;
        } else {
            if (Math.abs(f2) > ((float) this.mTouchSlop)) {
                z = true;
            }
            return z;
        }
    }

    public boolean checkTouchSlop(int i) {
        int length = this.mInitialMotionX.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (checkTouchSlop(i, i2)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkTouchSlop(int i, int i2) {
        boolean z = false;
        if (!isPointerDown(i2)) {
            return false;
        }
        Object obj = (i & 1) == 1 ? 1 : null;
        i = (i & 2) == 2 ? 1 : 0;
        float f = this.mLastMotionX[i2] - this.mInitialMotionX[i2];
        float f2 = this.mLastMotionY[i2] - this.mInitialMotionY[i2];
        if (obj != null && i != 0) {
            if ((f * f) + (f2 * f2) > ((float) (this.mTouchSlop * this.mTouchSlop))) {
                z = true;
            }
            return z;
        } else if (obj != null) {
            if (Math.abs(f) > ((float) this.mTouchSlop)) {
                z = true;
            }
            return z;
        } else if (i == 0) {
            return false;
        } else {
            if (Math.abs(f2) > ((float) this.mTouchSlop)) {
                z = true;
            }
            return z;
        }
    }

    public boolean isEdgeTouched(int i) {
        int length = this.mInitialEdgesTouched.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (isEdgeTouched(i, i2)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEdgeTouched(int i, int i2) {
        return isPointerDown(i2) && (i & this.mInitialEdgesTouched[i2]) != 0;
    }

    private void releaseViewForPointerUp() {
        this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaxVelocity);
        dispatchViewReleased(clampMag(this.mVelocityTracker.getXVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity), clampMag(this.mVelocityTracker.getYVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity));
    }

    private void dragTo(int i, int i2, int i3, int i4) {
        int left = this.mCapturedView.getLeft();
        int top = this.mCapturedView.getTop();
        if (i3 != 0) {
            i = this.mCallback.clampViewPositionHorizontal(this.mCapturedView, i, i3);
            ViewCompat.offsetLeftAndRight(this.mCapturedView, i - left);
        }
        int i5 = i;
        if (i4 != 0) {
            i2 = this.mCallback.clampViewPositionVertical(this.mCapturedView, i2, i4);
            ViewCompat.offsetTopAndBottom(this.mCapturedView, i2 - top);
        }
        int i6 = i2;
        if (i3 != 0 || i4 != 0) {
            this.mCallback.onViewPositionChanged(this.mCapturedView, i5, i6, i5 - left, i6 - top);
        }
    }

    public boolean isCapturedViewUnder(int i, int i2) {
        return isViewUnder(this.mCapturedView, i, i2);
    }

    public boolean isViewUnder(@Nullable View view, int i, int i2) {
        boolean z = false;
        if (view == null) {
            return false;
        }
        if (i >= view.getLeft() && i < view.getRight() && i2 >= view.getTop() && i2 < view.getBottom()) {
            z = true;
        }
        return z;
    }

    @Nullable
    public View findTopChildUnder(int i, int i2) {
        for (int childCount = this.mParentView.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.mParentView.getChildAt(this.mCallback.getOrderedChildIndex(childCount));
            if (i >= childAt.getLeft() && i < childAt.getRight() && i2 >= childAt.getTop() && i2 < childAt.getBottom()) {
                return childAt;
            }
        }
        return 0;
    }

    private int getEdgesTouched(int i, int i2) {
        int i3 = i < this.mParentView.getLeft() + this.mEdgeSize ? 1 : 0;
        if (i2 < this.mParentView.getTop() + this.mEdgeSize) {
            i3 |= 4;
        }
        if (i > this.mParentView.getRight() - this.mEdgeSize) {
            i3 |= 2;
        }
        return i2 > this.mParentView.getBottom() - this.mEdgeSize ? i3 | 8 : i3;
    }

    private boolean isValidPointerForActionMove(int i) {
        if (isPointerDown(i)) {
            return true;
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Ignoring pointerId=");
        stringBuilder.append(i);
        stringBuilder.append(" because ACTION_DOWN was not received ");
        stringBuilder.append("for this pointer before ACTION_MOVE. It likely happened because ");
        stringBuilder.append(" ViewDragHelper did not receive all the events in the event stream.");
        Log.e(str, stringBuilder.toString());
        return false;
    }
}
