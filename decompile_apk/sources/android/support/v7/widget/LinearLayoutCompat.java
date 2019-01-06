package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.InputDeviceCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.C0192R;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LinearLayoutCompat extends ViewGroup {
    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DividerMode {
    }

    public static class LayoutParams extends MarginLayoutParams {
        public int gravity;
        public float weight;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.gravity = -1;
            context = context.obtainStyledAttributes(attributeSet, C0192R.styleable.LinearLayoutCompat_Layout);
            this.weight = context.getFloat(C0192R.styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.gravity = context.getInt(C0192R.styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            context.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.gravity = -1;
            this.weight = 0;
        }

        public LayoutParams(int i, int i2, float f) {
            super(i, i2);
            this.gravity = -1;
            this.weight = f;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = -1;
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.gravity = -1;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = -1;
            this.weight = layoutParams.weight;
            this.gravity = layoutParams.gravity;
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface OrientationMode {
    }

    int getChildrenSkipCount(View view, int i) {
        return 0;
    }

    int getLocationOffset(View view) {
        return 0;
    }

    int getNextLocationOffset(View view) {
        return 0;
    }

    int measureNullChild(int i) {
        return 0;
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = 8388659;
        context = TintTypedArray.obtainStyledAttributes(context, attributeSet, C0192R.styleable.LinearLayoutCompat, i, 0);
        attributeSet = context.getInt(C0192R.styleable.LinearLayoutCompat_android_orientation, -1);
        if (attributeSet >= null) {
            setOrientation(attributeSet);
        }
        attributeSet = context.getInt(C0192R.styleable.LinearLayoutCompat_android_gravity, -1);
        if (attributeSet >= null) {
            setGravity(attributeSet);
        }
        attributeSet = context.getBoolean(C0192R.styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (attributeSet == null) {
            setBaselineAligned(attributeSet);
        }
        this.mWeightSum = context.getFloat(C0192R.styleable.LinearLayoutCompat_android_weightSum, -1082130432);
        this.mBaselineAlignedChildIndex = context.getInt(C0192R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = context.getBoolean(C0192R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(context.getDrawable(C0192R.styleable.LinearLayoutCompat_divider));
        this.mShowDividers = context.getInt(C0192R.styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = context.getDimensionPixelSize(C0192R.styleable.LinearLayoutCompat_dividerPadding, 0);
        context.recycle();
    }

    public void setShowDividers(int i) {
        if (i != this.mShowDividers) {
            requestLayout();
        }
        this.mShowDividers = i;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable != this.mDivider) {
            this.mDivider = drawable;
            boolean z = false;
            if (drawable != null) {
                this.mDividerWidth = drawable.getIntrinsicWidth();
                this.mDividerHeight = drawable.getIntrinsicHeight();
            } else {
                this.mDividerWidth = 0;
                this.mDividerHeight = 0;
            }
            if (drawable == null) {
                z = true;
            }
            setWillNotDraw(z);
            requestLayout();
        }
    }

    public void setDividerPadding(int i) {
        this.mDividerPadding = i;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    protected void onDraw(Canvas canvas) {
        if (this.mDivider != null) {
            if (this.mOrientation == 1) {
                drawDividersVertical(canvas);
            } else {
                drawDividersHorizontal(canvas);
            }
        }
    }

    void drawDividersVertical(Canvas canvas) {
        int virtualChildCount = getVirtualChildCount();
        int i = 0;
        while (i < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(i);
            if (!(virtualChildAt == null || virtualChildAt.getVisibility() == 8 || !hasDividerBeforeChildAt(i))) {
                drawHorizontalDivider(canvas, (virtualChildAt.getTop() - ((LayoutParams) virtualChildAt.getLayoutParams()).topMargin) - this.mDividerHeight);
            }
            i++;
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 == null) {
                virtualChildCount = (getHeight() - getPaddingBottom()) - this.mDividerHeight;
            } else {
                virtualChildCount = virtualChildAt2.getBottom() + ((LayoutParams) virtualChildAt2.getLayoutParams()).bottomMargin;
            }
            drawHorizontalDivider(canvas, virtualChildCount);
        }
    }

    void drawDividersHorizontal(Canvas canvas) {
        int virtualChildCount = getVirtualChildCount();
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        int i = 0;
        while (i < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(i);
            if (!(virtualChildAt == null || virtualChildAt.getVisibility() == 8 || !hasDividerBeforeChildAt(i))) {
                int right;
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (isLayoutRtl) {
                    right = virtualChildAt.getRight() + layoutParams.rightMargin;
                } else {
                    right = (virtualChildAt.getLeft() - layoutParams.leftMargin) - this.mDividerWidth;
                }
                drawVerticalDivider(canvas, right);
            }
            i++;
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 != null) {
                LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                if (isLayoutRtl) {
                    virtualChildCount = (virtualChildAt2.getLeft() - layoutParams2.leftMargin) - this.mDividerWidth;
                } else {
                    virtualChildCount = virtualChildAt2.getRight() + layoutParams2.rightMargin;
                }
            } else if (isLayoutRtl) {
                virtualChildCount = getPaddingLeft();
            } else {
                virtualChildCount = (getWidth() - getPaddingRight()) - this.mDividerWidth;
            }
            drawVerticalDivider(canvas, virtualChildCount);
        }
    }

    void drawHorizontalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, i, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + i);
        this.mDivider.draw(canvas);
    }

    void drawVerticalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(i, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + i, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    public boolean isBaselineAligned() {
        return this.mBaselineAligned;
    }

    public void setBaselineAligned(boolean z) {
        this.mBaselineAligned = z;
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.mUseLargestChild;
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.mUseLargestChild = z;
    }

    public int getBaseline() {
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        if (getChildCount() > this.mBaselineAlignedChildIndex) {
            View childAt = getChildAt(this.mBaselineAlignedChildIndex);
            int baseline = childAt.getBaseline();
            if (baseline != -1) {
                int i = this.mBaselineChildTop;
                if (this.mOrientation == 1) {
                    int i2 = this.mGravity & 112;
                    if (i2 != 48) {
                        if (i2 == 16) {
                            i += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
                        } else if (i2 == 80) {
                            i = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
                        }
                    }
                }
                return (i + ((LayoutParams) childAt.getLayoutParams()).topMargin) + baseline;
            } else if (this.mBaselineAlignedChildIndex == 0) {
                return -1;
            } else {
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
        }
        throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i < 0 || i >= getChildCount()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("base aligned child index out of range (0, ");
            stringBuilder.append(getChildCount());
            stringBuilder.append(")");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        this.mBaselineAlignedChildIndex = i;
    }

    View getVirtualChildAt(int i) {
        return getChildAt(i);
    }

    int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    public void setWeightSum(float f) {
        this.mWeightSum = Math.max(0.0f, f);
    }

    protected void onMeasure(int i, int i2) {
        if (this.mOrientation == 1) {
            measureVertical(i, i2);
        } else {
            measureHorizontal(i, i2);
        }
    }

    @RestrictTo({Scope.LIBRARY})
    protected boolean hasDividerBeforeChildAt(int i) {
        boolean z = false;
        if (i == 0) {
            if ((this.mShowDividers & 1) != 0) {
                z = true;
            }
            return z;
        } else if (i == getChildCount()) {
            if ((this.mShowDividers & 4) != 0) {
                z = true;
            }
            return z;
        } else if ((this.mShowDividers & 2) == 0) {
            return false;
        } else {
            for (i--; i >= 0; i--) {
                if (getChildAt(i).getVisibility() != 8) {
                    z = true;
                    break;
                }
            }
            return z;
        }
    }

    void measureVertical(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        View view;
        int i8;
        int i9 = i;
        int i10 = i2;
        this.mTotalLength = 0;
        int virtualChildCount = getVirtualChildCount();
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int i11 = this.mBaselineAlignedChildIndex;
        boolean z = this.mUseLargestChild;
        float f = 0.0f;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        Object obj = null;
        Object obj2 = 1;
        Object obj3 = null;
        while (true) {
            i3 = 8;
            i4 = i15;
            if (i17 >= virtualChildCount) {
                break;
            }
            Object obj4;
            View virtualChildAt = getVirtualChildAt(i17);
            if (virtualChildAt == null) {
                r7.mTotalLength += measureNullChild(i17);
                i5 = virtualChildCount;
                i6 = mode2;
                i15 = i4;
            } else {
                int i18 = i12;
                if (virtualChildAt.getVisibility() == 8) {
                    i17 += getChildrenSkipCount(virtualChildAt, i17);
                    i5 = virtualChildCount;
                    i6 = mode2;
                    i15 = i4;
                    i12 = i18;
                } else {
                    int i19;
                    int i20;
                    if (hasDividerBeforeChildAt(i17)) {
                        r7.mTotalLength += r7.mDividerHeight;
                    }
                    LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                    float f2 = f + layoutParams.weight;
                    int i21;
                    if (mode2 == 1073741824 && layoutParams.height == 0 && layoutParams.weight > 0.0f) {
                        i7 = r7.mTotalLength;
                        i21 = i13;
                        r7.mTotalLength = Math.max(i7, (layoutParams.topMargin + i7) + layoutParams.bottomMargin);
                        i7 = i14;
                        view = virtualChildAt;
                        i19 = i16;
                        i5 = virtualChildCount;
                        i6 = mode2;
                        mode2 = i4;
                        i9 = i18;
                        i20 = i21;
                        obj = 1;
                        virtualChildCount = i17;
                    } else {
                        i21 = i13;
                        if (layoutParams.height != 0 || layoutParams.weight <= 0.0f) {
                            i13 = Integer.MIN_VALUE;
                        } else {
                            layoutParams.height = -2;
                            i13 = 0;
                        }
                        i9 = i18;
                        int i22 = i13;
                        i20 = i21;
                        i10 = i14;
                        View view2 = virtualChildAt;
                        i5 = virtualChildCount;
                        i6 = mode2;
                        mode2 = i4;
                        i19 = i16;
                        virtualChildCount = i17;
                        measureChildBeforeLayout(virtualChildAt, i17, i, 0, i2, f2 == 0.0f ? r7.mTotalLength : 0);
                        i7 = i22;
                        if (i7 != Integer.MIN_VALUE) {
                            layoutParams.height = i7;
                        }
                        i7 = view2.getMeasuredHeight();
                        i12 = r7.mTotalLength;
                        view = view2;
                        r7.mTotalLength = Math.max(i12, (((i12 + i7) + layoutParams.topMargin) + layoutParams.bottomMargin) + getNextLocationOffset(view));
                        i7 = z ? Math.max(i7, i10) : i10;
                    }
                    if (i11 >= 0 && i11 == virtualChildCount + 1) {
                        r7.mBaselineChildTop = r7.mTotalLength;
                    }
                    if (virtualChildCount < i11) {
                        if (layoutParams.weight > 0.0f) {
                            throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                        }
                    }
                    if (mode == 1073741824 || layoutParams.width != -1) {
                        obj4 = null;
                    } else {
                        obj4 = 1;
                        obj3 = 1;
                    }
                    i13 = layoutParams.leftMargin + layoutParams.rightMargin;
                    i15 = view.getMeasuredWidth() + i13;
                    i16 = Math.max(i20, i15);
                    i17 = View.combineMeasuredStates(i9, view.getMeasuredState());
                    Object obj5 = (obj2 == null || layoutParams.width != -1) ? null : 1;
                    if (layoutParams.weight > 0.0f) {
                        if (obj4 == null) {
                            i13 = i15;
                        }
                        mode2 = Math.max(mode2, i13);
                        i12 = i19;
                    } else {
                        if (obj4 == null) {
                            i13 = i15;
                        }
                        i12 = Math.max(i19, i13);
                    }
                    i14 = i7;
                    obj2 = obj5;
                    i15 = mode2;
                    f = f2;
                    i8 = i16;
                    i16 = i12;
                    i12 = i17;
                    i17 = getChildrenSkipCount(view, virtualChildCount) + virtualChildCount;
                    i13 = i8;
                }
            }
            i17++;
            mode2 = i6;
            virtualChildCount = i5;
            i9 = i;
            i10 = i2;
        }
        i9 = i12;
        i10 = i14;
        i12 = i16;
        i5 = virtualChildCount;
        i6 = mode2;
        mode2 = i4;
        i16 = i13;
        if (r7.mTotalLength > 0) {
            i13 = i5;
            if (hasDividerBeforeChildAt(i13)) {
                r7.mTotalLength += r7.mDividerHeight;
            }
        } else {
            i13 = i5;
        }
        if (z) {
            i14 = i6;
            if (i14 == Integer.MIN_VALUE || i14 == 0) {
                r7.mTotalLength = 0;
                i15 = 0;
                while (i15 < i13) {
                    View virtualChildAt2 = getVirtualChildAt(i15);
                    if (virtualChildAt2 == null) {
                        r7.mTotalLength += measureNullChild(i15);
                    } else if (virtualChildAt2.getVisibility() == i3) {
                        i15 += getChildrenSkipCount(virtualChildAt2, i15);
                    } else {
                        LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                        i11 = r7.mTotalLength;
                        r7.mTotalLength = Math.max(i11, (((i11 + i10) + layoutParams2.topMargin) + layoutParams2.bottomMargin) + getNextLocationOffset(virtualChildAt2));
                    }
                    i15++;
                    i3 = 8;
                }
            }
        } else {
            i14 = i6;
        }
        r7.mTotalLength += getPaddingTop() + getPaddingBottom();
        i3 = i10;
        i17 = i2;
        i15 = View.resolveSizeAndState(Math.max(r7.mTotalLength, getSuggestedMinimumHeight()), i17, 0);
        i10 = (ViewCompat.MEASURED_SIZE_MASK & i15) - r7.mTotalLength;
        if (obj == null) {
            if (i10 == 0 || f <= 0.0f) {
                i7 = Math.max(i12, mode2);
                if (z && r3 != 1073741824) {
                    for (i12 = 0; i12 < i13; i12++) {
                        view = getVirtualChildAt(i12);
                        if (view != null) {
                            if (view.getVisibility() != 8) {
                                if (((LayoutParams) view.getLayoutParams()).weight > 0.0f) {
                                    view.measure(MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), 1073741824), MeasureSpec.makeMeasureSpec(i3, 1073741824));
                                }
                            }
                        }
                    }
                }
                i12 = i9;
                virtualChildCount = i;
                if (obj2 == null || mode == 1073741824) {
                    i7 = i16;
                }
                setMeasuredDimension(View.resolveSizeAndState(Math.max(i7 + (getPaddingLeft() + getPaddingRight()), getSuggestedMinimumWidth()), virtualChildCount, i12), i15);
                if (obj3 != null) {
                    forceUniformWidth(i13, i17);
                }
            }
        }
        if (r7.mWeightSum > 0.0f) {
            f = r7.mWeightSum;
        }
        r7.mTotalLength = 0;
        float f3 = f;
        i7 = 0;
        i8 = i9;
        i9 = i12;
        i12 = i8;
        while (i7 < i13) {
            int i23;
            float f4;
            View virtualChildAt3 = getVirtualChildAt(i7);
            if (virtualChildAt3.getVisibility() == 8) {
                i23 = i14;
                f4 = f3;
                virtualChildCount = i;
            } else {
                int i24;
                LayoutParams layoutParams3 = (LayoutParams) virtualChildAt3.getLayoutParams();
                f4 = layoutParams3.weight;
                if (f4 > 0.0f) {
                    i24 = (int) ((((float) i10) * f4) / f3);
                    int i25 = i10 - i24;
                    float f5 = f3 - f4;
                    i10 = getChildMeasureSpec(i, ((getPaddingLeft() + getPaddingRight()) + layoutParams3.leftMargin) + layoutParams3.rightMargin, layoutParams3.width);
                    if (layoutParams3.height == 0) {
                        i3 = 1073741824;
                        if (i14 == 1073741824) {
                            if (i24 <= 0) {
                                i24 = 0;
                            }
                            virtualChildAt3.measure(i10, MeasureSpec.makeMeasureSpec(i24, 1073741824));
                            i12 = View.combineMeasuredStates(i12, virtualChildAt3.getMeasuredState() & InputDeviceCompat.SOURCE_ANY);
                            i10 = i25;
                            f4 = f5;
                        }
                    } else {
                        i3 = 1073741824;
                    }
                    i24 = virtualChildAt3.getMeasuredHeight() + i24;
                    if (i24 < 0) {
                        i24 = 0;
                    }
                    virtualChildAt3.measure(i10, MeasureSpec.makeMeasureSpec(i24, i3));
                    i12 = View.combineMeasuredStates(i12, virtualChildAt3.getMeasuredState() & InputDeviceCompat.SOURCE_ANY);
                    i10 = i25;
                    f4 = f5;
                } else {
                    f4 = f3;
                    virtualChildCount = i;
                }
                int i26 = i12;
                i24 = layoutParams3.leftMargin + layoutParams3.rightMargin;
                i12 = virtualChildAt3.getMeasuredWidth() + i24;
                i16 = Math.max(i16, i12);
                int i27 = i12;
                if (mode != 1073741824) {
                    i23 = i14;
                    i14 = -1;
                    if (layoutParams3.width == -1) {
                        obj4 = 1;
                        if (obj4 != null) {
                            i24 = i27;
                        }
                        i12 = Math.max(i9, i24);
                        obj5 = (obj2 == null && layoutParams3.width == i14) ? 1 : null;
                        i24 = r7.mTotalLength;
                        r7.mTotalLength = Math.max(i24, (((i24 + virtualChildAt3.getMeasuredHeight()) + layoutParams3.topMargin) + layoutParams3.bottomMargin) + getNextLocationOffset(virtualChildAt3));
                        obj2 = obj5;
                        i9 = i12;
                        i12 = i26;
                    }
                } else {
                    i23 = i14;
                    i14 = -1;
                }
                obj4 = null;
                if (obj4 != null) {
                    i24 = i27;
                }
                i12 = Math.max(i9, i24);
                if (obj2 == null) {
                }
                i24 = r7.mTotalLength;
                r7.mTotalLength = Math.max(i24, (((i24 + virtualChildAt3.getMeasuredHeight()) + layoutParams3.topMargin) + layoutParams3.bottomMargin) + getNextLocationOffset(virtualChildAt3));
                obj2 = obj5;
                i9 = i12;
                i12 = i26;
            }
            i7++;
            f3 = f4;
            i14 = i23;
        }
        virtualChildCount = i;
        r7.mTotalLength += getPaddingTop() + getPaddingBottom();
        i7 = i9;
        if (obj2 == null) {
        }
        i7 = i16;
        setMeasuredDimension(View.resolveSizeAndState(Math.max(i7 + (getPaddingLeft() + getPaddingRight()), getSuggestedMinimumWidth()), virtualChildCount, i12), i15);
        if (obj3 != null) {
            forceUniformWidth(i13, i17);
        }
    }

    private void forceUniformWidth(int i, int i2) {
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.width == -1) {
                    int i4 = layoutParams.height;
                    layoutParams.height = virtualChildAt.getMeasuredHeight();
                    measureChildWithMargins(virtualChildAt, makeMeasureSpec, 0, i2, 0);
                    layoutParams.height = i4;
                }
            }
        }
    }

    void measureHorizontal(int i, int i2) {
        int[] iArr;
        int i3;
        boolean z;
        boolean z2;
        View view;
        int measuredHeight;
        int combineMeasuredStates;
        int baseline;
        int i4;
        int i5;
        int i6;
        View virtualChildAt;
        LayoutParams layoutParams;
        int i7;
        int i8;
        float f;
        View virtualChildAt2;
        float f2;
        float f3;
        int i9;
        float f4;
        Object obj;
        Object obj2;
        int i10 = i;
        int i11 = i2;
        this.mTotalLength = 0;
        int virtualChildCount = getVirtualChildCount();
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        if (this.mMaxAscent == null || r7.mMaxDescent == null) {
            r7.mMaxAscent = new int[4];
            r7.mMaxDescent = new int[4];
        }
        int[] iArr2 = r7.mMaxAscent;
        int[] iArr3 = r7.mMaxDescent;
        iArr2[3] = -1;
        iArr2[2] = -1;
        iArr2[1] = -1;
        iArr2[0] = -1;
        iArr3[3] = -1;
        iArr3[2] = -1;
        iArr3[1] = -1;
        iArr3[0] = -1;
        boolean z3 = r7.mBaselineAligned;
        boolean z4 = r7.mUseLargestChild;
        int i12 = 1073741824;
        Object obj3 = mode == 1073741824 ? 1 : null;
        float f5 = 0.0f;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        Object obj4 = null;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        Object obj5 = 1;
        Object obj6 = null;
        while (true) {
            iArr = iArr3;
            if (i13 >= virtualChildCount) {
                break;
            }
            View virtualChildAt3 = getVirtualChildAt(i13);
            if (virtualChildAt3 == null) {
                r7.mTotalLength += measureNullChild(i13);
            } else if (virtualChildAt3.getVisibility() == 8) {
                i13 += getChildrenSkipCount(virtualChildAt3, i13);
            } else {
                int i19;
                Object obj7;
                Object obj8;
                if (hasDividerBeforeChildAt(i13)) {
                    r7.mTotalLength += r7.mDividerWidth;
                }
                LayoutParams layoutParams2 = (LayoutParams) virtualChildAt3.getLayoutParams();
                float f6 = f5 + layoutParams2.weight;
                if (mode == i12 && layoutParams2.width == 0 && layoutParams2.weight > 0.0f) {
                    if (obj3 != null) {
                        r7.mTotalLength += layoutParams2.leftMargin + layoutParams2.rightMargin;
                    } else {
                        i3 = r7.mTotalLength;
                        r7.mTotalLength = Math.max(i3, (layoutParams2.leftMargin + i3) + layoutParams2.rightMargin);
                    }
                    if (z3) {
                        i12 = MeasureSpec.makeMeasureSpec(0, 0);
                        virtualChildAt3.measure(i12, i12);
                        i19 = i13;
                        z = z4;
                        z2 = z3;
                        view = virtualChildAt3;
                    } else {
                        i19 = i13;
                        z = z4;
                        z2 = z3;
                        view = virtualChildAt3;
                        i13 = 1073741824;
                        obj4 = 1;
                        if (mode2 == i13 && layoutParams2.height == -1) {
                            obj7 = 1;
                            obj6 = 1;
                        } else {
                            obj7 = null;
                        }
                        i12 = layoutParams2.topMargin + layoutParams2.bottomMargin;
                        measuredHeight = view.getMeasuredHeight() + i12;
                        combineMeasuredStates = View.combineMeasuredStates(i18, view.getMeasuredState());
                        if (z2) {
                            baseline = view.getBaseline();
                            if (baseline != -1) {
                                i11 = ((((layoutParams2.gravity >= 0 ? r7.mGravity : layoutParams2.gravity) & 112) >> 4) & -2) >> 1;
                                iArr2[i11] = Math.max(iArr2[i11], baseline);
                                iArr[i11] = Math.max(iArr[i11], measuredHeight - baseline);
                            }
                        }
                        i13 = Math.max(i15, measuredHeight);
                        obj8 = (obj5 == null && layoutParams2.height == -1) ? 1 : null;
                        if (layoutParams2.weight <= 0.0f) {
                            if (obj7 == null) {
                                i12 = measuredHeight;
                            }
                            i17 = Math.max(i17, i12);
                        } else {
                            i4 = i17;
                            if (obj7 != null) {
                                measuredHeight = i12;
                            }
                            i16 = Math.max(i16, measuredHeight);
                            i17 = i4;
                        }
                        i4 = i19;
                        i15 = i13;
                        i18 = combineMeasuredStates;
                        obj5 = obj8;
                        i13 = getChildrenSkipCount(view, i4) + i4;
                        f5 = f6;
                        i13++;
                        iArr3 = iArr;
                        z4 = z;
                        z3 = z2;
                        i12 = 1073741824;
                        i11 = i2;
                    }
                } else {
                    if (layoutParams2.width != 0 || layoutParams2.weight <= 0.0f) {
                        i12 = Integer.MIN_VALUE;
                    } else {
                        layoutParams2.width = -2;
                        i12 = 0;
                    }
                    i19 = i13;
                    int i20 = i12;
                    z = z4;
                    z2 = z3;
                    View view2 = virtualChildAt3;
                    measureChildBeforeLayout(virtualChildAt3, i19, i, f6 == 0.0f ? r7.mTotalLength : 0, i2, 0);
                    i3 = i20;
                    if (i3 != Integer.MIN_VALUE) {
                        layoutParams2.width = i3;
                    }
                    i3 = view2.getMeasuredWidth();
                    if (obj3 != null) {
                        view = view2;
                        r7.mTotalLength += ((layoutParams2.leftMargin + i3) + layoutParams2.rightMargin) + getNextLocationOffset(view);
                    } else {
                        view = view2;
                        i13 = r7.mTotalLength;
                        r7.mTotalLength = Math.max(i13, (((i13 + i3) + layoutParams2.leftMargin) + layoutParams2.rightMargin) + getNextLocationOffset(view));
                    }
                    if (z) {
                        i14 = Math.max(i3, i14);
                    }
                }
                i13 = 1073741824;
                if (mode2 == i13) {
                }
                obj7 = null;
                i12 = layoutParams2.topMargin + layoutParams2.bottomMargin;
                measuredHeight = view.getMeasuredHeight() + i12;
                combineMeasuredStates = View.combineMeasuredStates(i18, view.getMeasuredState());
                if (z2) {
                    baseline = view.getBaseline();
                    if (baseline != -1) {
                        if (layoutParams2.gravity >= 0) {
                        }
                        i11 = ((((layoutParams2.gravity >= 0 ? r7.mGravity : layoutParams2.gravity) & 112) >> 4) & -2) >> 1;
                        iArr2[i11] = Math.max(iArr2[i11], baseline);
                        iArr[i11] = Math.max(iArr[i11], measuredHeight - baseline);
                    }
                }
                i13 = Math.max(i15, measuredHeight);
                if (obj5 == null) {
                }
                if (layoutParams2.weight <= 0.0f) {
                    i4 = i17;
                    if (obj7 != null) {
                        measuredHeight = i12;
                    }
                    i16 = Math.max(i16, measuredHeight);
                    i17 = i4;
                } else {
                    if (obj7 == null) {
                        i12 = measuredHeight;
                    }
                    i17 = Math.max(i17, i12);
                }
                i4 = i19;
                i15 = i13;
                i18 = combineMeasuredStates;
                obj5 = obj8;
                i13 = getChildrenSkipCount(view, i4) + i4;
                f5 = f6;
                i13++;
                iArr3 = iArr;
                z4 = z;
                z3 = z2;
                i12 = 1073741824;
                i11 = i2;
            }
            z = z4;
            z2 = z3;
            i13++;
            iArr3 = iArr;
            z4 = z;
            z3 = z2;
            i12 = 1073741824;
            i11 = i2;
        }
        z = z4;
        z2 = z3;
        i13 = i15;
        i12 = i16;
        i4 = i17;
        baseline = i18;
        if (r7.mTotalLength > 0 && hasDividerBeforeChildAt(virtualChildCount)) {
            r7.mTotalLength += r7.mDividerWidth;
        }
        if (iArr2[1] == -1 && iArr2[0] == -1 && iArr2[2] == -1) {
            if (iArr2[3] == -1) {
                i5 = baseline;
                if (z && (mode == Integer.MIN_VALUE || mode == 0)) {
                    r7.mTotalLength = 0;
                    i6 = 0;
                    while (i6 < virtualChildCount) {
                        virtualChildAt = getVirtualChildAt(i6);
                        if (virtualChildAt == null) {
                            r7.mTotalLength += measureNullChild(i6);
                        } else if (virtualChildAt.getVisibility() != 8) {
                            i6 += getChildrenSkipCount(virtualChildAt, i6);
                        } else {
                            layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                            if (obj3 == null) {
                                r7.mTotalLength += ((layoutParams.leftMargin + i14) + layoutParams.rightMargin) + getNextLocationOffset(virtualChildAt);
                            } else {
                                baseline = r7.mTotalLength;
                                i7 = i13;
                                r7.mTotalLength = Math.max(baseline, (((baseline + i14) + layoutParams.leftMargin) + layoutParams.rightMargin) + getNextLocationOffset(virtualChildAt));
                                i6++;
                                i13 = i7;
                            }
                        }
                        i7 = i13;
                        i6++;
                        i13 = i7;
                    }
                }
                i7 = i13;
                r7.mTotalLength += getPaddingLeft() + getPaddingRight();
                i13 = View.resolveSizeAndState(Math.max(r7.mTotalLength, getSuggestedMinimumWidth()), i10, 0);
                i6 = (ViewCompat.MEASURED_SIZE_MASK & i13) - r7.mTotalLength;
                if (obj4 == null) {
                    if (i6 != 0 || f5 <= 0.0f) {
                        i3 = Math.max(i12, i4);
                        if (z && mode != 1073741824) {
                            for (i12 = 0; i12 < virtualChildCount; i12++) {
                                view = getVirtualChildAt(i12);
                                if (view == null) {
                                    if (view.getVisibility() == 8) {
                                        if (((LayoutParams) view.getLayoutParams()).weight > 0.0f) {
                                            view.measure(MeasureSpec.makeMeasureSpec(i14, 1073741824), MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 1073741824));
                                        }
                                    }
                                }
                            }
                        }
                        i8 = virtualChildCount;
                        i6 = i2;
                        if (obj5 == null && mode2 != 1073741824) {
                            i7 = i3;
                        }
                        setMeasuredDimension(i13 | (i5 & ViewCompat.MEASURED_STATE_MASK), View.resolveSizeAndState(Math.max(i7 + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight()), i6, i5 << 16));
                        if (obj6 != null) {
                            forceUniformHeight(i8, i);
                        }
                    }
                }
                if (r7.mWeightSum > 0.0f) {
                    f5 = r7.mWeightSum;
                }
                iArr2[3] = -1;
                iArr2[2] = -1;
                iArr2[1] = -1;
                iArr2[0] = -1;
                iArr[3] = -1;
                iArr[2] = -1;
                iArr[1] = -1;
                iArr[0] = -1;
                r7.mTotalLength = 0;
                i4 = i12;
                i11 = i5;
                baseline = -1;
                f = f5;
                i3 = 0;
                while (i3 < virtualChildCount) {
                    virtualChildAt2 = getVirtualChildAt(i3);
                    if (virtualChildAt2 != null) {
                        if (virtualChildAt2.getVisibility() == 8) {
                            layoutParams = (LayoutParams) virtualChildAt2.getLayoutParams();
                            f2 = layoutParams.weight;
                            if (f2 <= 0.0f) {
                                i10 = (int) ((((float) i6) * f2) / f);
                                f3 = f - f2;
                                i9 = i6 - i10;
                                i8 = virtualChildCount;
                                i12 = getChildMeasureSpec(i2, ((getPaddingTop() + getPaddingBottom()) + layoutParams.topMargin) + layoutParams.bottomMargin, layoutParams.height);
                                if (layoutParams.width != 0) {
                                    measuredHeight = 1073741824;
                                    if (mode != 1073741824) {
                                        if (i10 > 0) {
                                            i10 = 0;
                                        }
                                        virtualChildAt2.measure(MeasureSpec.makeMeasureSpec(i10, 1073741824), i12);
                                        i11 = View.combineMeasuredStates(i11, virtualChildAt2.getMeasuredState() & ViewCompat.MEASURED_STATE_MASK);
                                        f = f3;
                                        measuredHeight = i9;
                                    }
                                } else {
                                    measuredHeight = 1073741824;
                                }
                                i10 = virtualChildAt2.getMeasuredWidth() + i10;
                                if (i10 < 0) {
                                    i10 = 0;
                                }
                                virtualChildAt2.measure(MeasureSpec.makeMeasureSpec(i10, measuredHeight), i12);
                                i11 = View.combineMeasuredStates(i11, virtualChildAt2.getMeasuredState() & ViewCompat.MEASURED_STATE_MASK);
                                f = f3;
                                measuredHeight = i9;
                            } else {
                                measuredHeight = i6;
                                i8 = virtualChildCount;
                                i6 = i2;
                            }
                            if (obj3 == null) {
                                r7.mTotalLength += ((virtualChildAt2.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin) + getNextLocationOffset(virtualChildAt2);
                                f4 = f;
                            } else {
                                i10 = r7.mTotalLength;
                                f4 = f;
                                r7.mTotalLength = Math.max(i10, (((virtualChildAt2.getMeasuredWidth() + i10) + layoutParams.leftMargin) + layoutParams.rightMargin) + getNextLocationOffset(virtualChildAt2));
                            }
                            obj = (mode2 == 1073741824 && layoutParams.height == -1) ? 1 : null;
                            i10 = layoutParams.topMargin + layoutParams.bottomMargin;
                            virtualChildCount = virtualChildAt2.getMeasuredHeight() + i10;
                            baseline = Math.max(baseline, virtualChildCount);
                            if (obj != null) {
                                i10 = virtualChildCount;
                            }
                            i12 = Math.max(i4, i10);
                            if (obj5 == null) {
                                i4 = -1;
                                if (layoutParams.height == -1) {
                                    obj2 = 1;
                                    if (z2) {
                                        i14 = virtualChildAt2.getBaseline();
                                        if (i14 != i4) {
                                            combineMeasuredStates = ((((layoutParams.gravity >= 0 ? r7.mGravity : layoutParams.gravity) & 112) >> 4) & -2) >> 1;
                                            iArr2[combineMeasuredStates] = Math.max(iArr2[combineMeasuredStates], i14);
                                            iArr[combineMeasuredStates] = Math.max(iArr[combineMeasuredStates], virtualChildCount - i14);
                                            i4 = i12;
                                            obj5 = obj2;
                                            f = f4;
                                            i3++;
                                            i6 = measuredHeight;
                                            virtualChildCount = i8;
                                            i10 = i;
                                        }
                                    }
                                    i4 = i12;
                                    obj5 = obj2;
                                    f = f4;
                                    i3++;
                                    i6 = measuredHeight;
                                    virtualChildCount = i8;
                                    i10 = i;
                                }
                            } else {
                                i4 = -1;
                            }
                            obj2 = null;
                            if (z2) {
                                i14 = virtualChildAt2.getBaseline();
                                if (i14 != i4) {
                                    if (layoutParams.gravity >= 0) {
                                    }
                                    combineMeasuredStates = ((((layoutParams.gravity >= 0 ? r7.mGravity : layoutParams.gravity) & 112) >> 4) & -2) >> 1;
                                    iArr2[combineMeasuredStates] = Math.max(iArr2[combineMeasuredStates], i14);
                                    iArr[combineMeasuredStates] = Math.max(iArr[combineMeasuredStates], virtualChildCount - i14);
                                    i4 = i12;
                                    obj5 = obj2;
                                    f = f4;
                                    i3++;
                                    i6 = measuredHeight;
                                    virtualChildCount = i8;
                                    i10 = i;
                                }
                            }
                            i4 = i12;
                            obj5 = obj2;
                            f = f4;
                            i3++;
                            i6 = measuredHeight;
                            virtualChildCount = i8;
                            i10 = i;
                        }
                    }
                    measuredHeight = i6;
                    i8 = virtualChildCount;
                    i6 = i2;
                    i3++;
                    i6 = measuredHeight;
                    virtualChildCount = i8;
                    i10 = i;
                }
                i8 = virtualChildCount;
                i6 = i2;
                r7.mTotalLength += getPaddingLeft() + getPaddingRight();
                if (iArr2[1] == -1 && iArr2[0] == -1 && iArr2[2] == -1) {
                    if (iArr2[3] != -1) {
                        i3 = baseline;
                        i7 = i3;
                        i5 = i11;
                        i3 = i4;
                        i7 = i3;
                        setMeasuredDimension(i13 | (i5 & ViewCompat.MEASURED_STATE_MASK), View.resolveSizeAndState(Math.max(i7 + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight()), i6, i5 << 16));
                        if (obj6 != null) {
                            forceUniformHeight(i8, i);
                        }
                    }
                }
                i3 = Math.max(baseline, Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))) + Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))));
                i7 = i3;
                i5 = i11;
                i3 = i4;
                i7 = i3;
                setMeasuredDimension(i13 | (i5 & ViewCompat.MEASURED_STATE_MASK), View.resolveSizeAndState(Math.max(i7 + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight()), i6, i5 << 16));
                if (obj6 != null) {
                    forceUniformHeight(i8, i);
                }
            }
        }
        i5 = baseline;
        i13 = Math.max(i13, Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))) + Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))));
        r7.mTotalLength = 0;
        i6 = 0;
        while (i6 < virtualChildCount) {
            virtualChildAt = getVirtualChildAt(i6);
            if (virtualChildAt == null) {
                r7.mTotalLength += measureNullChild(i6);
            } else if (virtualChildAt.getVisibility() != 8) {
                layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (obj3 == null) {
                    baseline = r7.mTotalLength;
                    i7 = i13;
                    r7.mTotalLength = Math.max(baseline, (((baseline + i14) + layoutParams.leftMargin) + layoutParams.rightMargin) + getNextLocationOffset(virtualChildAt));
                    i6++;
                    i13 = i7;
                } else {
                    r7.mTotalLength += ((layoutParams.leftMargin + i14) + layoutParams.rightMargin) + getNextLocationOffset(virtualChildAt);
                }
            } else {
                i6 += getChildrenSkipCount(virtualChildAt, i6);
            }
            i7 = i13;
            i6++;
            i13 = i7;
        }
        i7 = i13;
        r7.mTotalLength += getPaddingLeft() + getPaddingRight();
        i13 = View.resolveSizeAndState(Math.max(r7.mTotalLength, getSuggestedMinimumWidth()), i10, 0);
        i6 = (ViewCompat.MEASURED_SIZE_MASK & i13) - r7.mTotalLength;
        if (obj4 == null) {
            if (i6 != 0) {
            }
            i3 = Math.max(i12, i4);
            for (i12 = 0; i12 < virtualChildCount; i12++) {
                view = getVirtualChildAt(i12);
                if (view == null) {
                    if (view.getVisibility() == 8) {
                        if (((LayoutParams) view.getLayoutParams()).weight > 0.0f) {
                            view.measure(MeasureSpec.makeMeasureSpec(i14, 1073741824), MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 1073741824));
                        }
                    }
                }
            }
            i8 = virtualChildCount;
            i6 = i2;
            i7 = i3;
            setMeasuredDimension(i13 | (i5 & ViewCompat.MEASURED_STATE_MASK), View.resolveSizeAndState(Math.max(i7 + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight()), i6, i5 << 16));
            if (obj6 != null) {
                forceUniformHeight(i8, i);
            }
        }
        if (r7.mWeightSum > 0.0f) {
            f5 = r7.mWeightSum;
        }
        iArr2[3] = -1;
        iArr2[2] = -1;
        iArr2[1] = -1;
        iArr2[0] = -1;
        iArr[3] = -1;
        iArr[2] = -1;
        iArr[1] = -1;
        iArr[0] = -1;
        r7.mTotalLength = 0;
        i4 = i12;
        i11 = i5;
        baseline = -1;
        f = f5;
        i3 = 0;
        while (i3 < virtualChildCount) {
            virtualChildAt2 = getVirtualChildAt(i3);
            if (virtualChildAt2 != null) {
                if (virtualChildAt2.getVisibility() == 8) {
                    layoutParams = (LayoutParams) virtualChildAt2.getLayoutParams();
                    f2 = layoutParams.weight;
                    if (f2 <= 0.0f) {
                        measuredHeight = i6;
                        i8 = virtualChildCount;
                        i6 = i2;
                    } else {
                        i10 = (int) ((((float) i6) * f2) / f);
                        f3 = f - f2;
                        i9 = i6 - i10;
                        i8 = virtualChildCount;
                        i12 = getChildMeasureSpec(i2, ((getPaddingTop() + getPaddingBottom()) + layoutParams.topMargin) + layoutParams.bottomMargin, layoutParams.height);
                        if (layoutParams.width != 0) {
                            measuredHeight = 1073741824;
                        } else {
                            measuredHeight = 1073741824;
                            if (mode != 1073741824) {
                                if (i10 > 0) {
                                    i10 = 0;
                                }
                                virtualChildAt2.measure(MeasureSpec.makeMeasureSpec(i10, 1073741824), i12);
                                i11 = View.combineMeasuredStates(i11, virtualChildAt2.getMeasuredState() & ViewCompat.MEASURED_STATE_MASK);
                                f = f3;
                                measuredHeight = i9;
                            }
                        }
                        i10 = virtualChildAt2.getMeasuredWidth() + i10;
                        if (i10 < 0) {
                            i10 = 0;
                        }
                        virtualChildAt2.measure(MeasureSpec.makeMeasureSpec(i10, measuredHeight), i12);
                        i11 = View.combineMeasuredStates(i11, virtualChildAt2.getMeasuredState() & ViewCompat.MEASURED_STATE_MASK);
                        f = f3;
                        measuredHeight = i9;
                    }
                    if (obj3 == null) {
                        i10 = r7.mTotalLength;
                        f4 = f;
                        r7.mTotalLength = Math.max(i10, (((virtualChildAt2.getMeasuredWidth() + i10) + layoutParams.leftMargin) + layoutParams.rightMargin) + getNextLocationOffset(virtualChildAt2));
                    } else {
                        r7.mTotalLength += ((virtualChildAt2.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin) + getNextLocationOffset(virtualChildAt2);
                        f4 = f;
                    }
                    if (mode2 == 1073741824) {
                    }
                    i10 = layoutParams.topMargin + layoutParams.bottomMargin;
                    virtualChildCount = virtualChildAt2.getMeasuredHeight() + i10;
                    baseline = Math.max(baseline, virtualChildCount);
                    if (obj != null) {
                        i10 = virtualChildCount;
                    }
                    i12 = Math.max(i4, i10);
                    if (obj5 == null) {
                        i4 = -1;
                    } else {
                        i4 = -1;
                        if (layoutParams.height == -1) {
                            obj2 = 1;
                            if (z2) {
                                i14 = virtualChildAt2.getBaseline();
                                if (i14 != i4) {
                                    if (layoutParams.gravity >= 0) {
                                    }
                                    combineMeasuredStates = ((((layoutParams.gravity >= 0 ? r7.mGravity : layoutParams.gravity) & 112) >> 4) & -2) >> 1;
                                    iArr2[combineMeasuredStates] = Math.max(iArr2[combineMeasuredStates], i14);
                                    iArr[combineMeasuredStates] = Math.max(iArr[combineMeasuredStates], virtualChildCount - i14);
                                    i4 = i12;
                                    obj5 = obj2;
                                    f = f4;
                                    i3++;
                                    i6 = measuredHeight;
                                    virtualChildCount = i8;
                                    i10 = i;
                                }
                            }
                            i4 = i12;
                            obj5 = obj2;
                            f = f4;
                            i3++;
                            i6 = measuredHeight;
                            virtualChildCount = i8;
                            i10 = i;
                        }
                    }
                    obj2 = null;
                    if (z2) {
                        i14 = virtualChildAt2.getBaseline();
                        if (i14 != i4) {
                            if (layoutParams.gravity >= 0) {
                            }
                            combineMeasuredStates = ((((layoutParams.gravity >= 0 ? r7.mGravity : layoutParams.gravity) & 112) >> 4) & -2) >> 1;
                            iArr2[combineMeasuredStates] = Math.max(iArr2[combineMeasuredStates], i14);
                            iArr[combineMeasuredStates] = Math.max(iArr[combineMeasuredStates], virtualChildCount - i14);
                            i4 = i12;
                            obj5 = obj2;
                            f = f4;
                            i3++;
                            i6 = measuredHeight;
                            virtualChildCount = i8;
                            i10 = i;
                        }
                    }
                    i4 = i12;
                    obj5 = obj2;
                    f = f4;
                    i3++;
                    i6 = measuredHeight;
                    virtualChildCount = i8;
                    i10 = i;
                }
            }
            measuredHeight = i6;
            i8 = virtualChildCount;
            i6 = i2;
            i3++;
            i6 = measuredHeight;
            virtualChildCount = i8;
            i10 = i;
        }
        i8 = virtualChildCount;
        i6 = i2;
        r7.mTotalLength += getPaddingLeft() + getPaddingRight();
        if (iArr2[3] != -1) {
            i3 = baseline;
            i7 = i3;
            i5 = i11;
            i3 = i4;
            i7 = i3;
            setMeasuredDimension(i13 | (i5 & ViewCompat.MEASURED_STATE_MASK), View.resolveSizeAndState(Math.max(i7 + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight()), i6, i5 << 16));
            if (obj6 != null) {
                forceUniformHeight(i8, i);
            }
        }
        i3 = Math.max(baseline, Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))) + Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))));
        i7 = i3;
        i5 = i11;
        i3 = i4;
        i7 = i3;
        setMeasuredDimension(i13 | (i5 & ViewCompat.MEASURED_STATE_MASK), View.resolveSizeAndState(Math.max(i7 + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight()), i6, i5 << 16));
        if (obj6 != null) {
            forceUniformHeight(i8, i);
        }
    }

    private void forceUniformHeight(int i, int i2) {
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.height == -1) {
                    int i4 = layoutParams.width;
                    layoutParams.width = virtualChildAt.getMeasuredWidth();
                    measureChildWithMargins(virtualChildAt, i2, 0, makeMeasureSpec, 0);
                    layoutParams.width = i4;
                }
            }
        }
    }

    void measureChildBeforeLayout(View view, int i, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mOrientation) {
            layoutVertical(i, i2, i3, i4);
        } else {
            layoutHorizontal(i, i2, i3, i4);
        }
    }

    void layoutVertical(int i, int i2, int i3, int i4) {
        int paddingTop;
        int paddingLeft = getPaddingLeft();
        int i5 = i3 - i;
        int paddingRight = i5 - getPaddingRight();
        int paddingRight2 = (i5 - paddingLeft) - getPaddingRight();
        int virtualChildCount = getVirtualChildCount();
        i5 = this.mGravity & 112;
        int i6 = this.mGravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        if (i5 == 16) {
            paddingTop = (((i4 - i2) - r6.mTotalLength) / 2) + getPaddingTop();
        } else if (i5 != 80) {
            paddingTop = getPaddingTop();
        } else {
            paddingTop = ((getPaddingTop() + i4) - i2) - r6.mTotalLength;
        }
        int i7 = 0;
        while (i7 < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(i7);
            if (virtualChildAt == null) {
                paddingTop += measureNullChild(i7);
            } else if (virtualChildAt.getVisibility() != 8) {
                int measuredWidth = virtualChildAt.getMeasuredWidth();
                int measuredHeight = virtualChildAt.getMeasuredHeight();
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                int i8 = layoutParams.gravity;
                if (i8 < 0) {
                    i8 = i6;
                }
                i8 = GravityCompat.getAbsoluteGravity(i8, ViewCompat.getLayoutDirection(this)) & 7;
                if (i8 == 1) {
                    i8 = ((((paddingRight2 - measuredWidth) / 2) + paddingLeft) + layoutParams.leftMargin) - layoutParams.rightMargin;
                } else if (i8 != 5) {
                    i8 = layoutParams.leftMargin + paddingLeft;
                } else {
                    i8 = (paddingRight - measuredWidth) - layoutParams.rightMargin;
                }
                i5 = i8;
                if (hasDividerBeforeChildAt(i7)) {
                    paddingTop += r6.mDividerHeight;
                }
                int i9 = paddingTop + layoutParams.topMargin;
                LayoutParams layoutParams2 = layoutParams;
                setChildFrame(virtualChildAt, i5, i9 + getLocationOffset(virtualChildAt), measuredWidth, measuredHeight);
                i7 += getChildrenSkipCount(virtualChildAt, i7);
                paddingTop = i9 + ((measuredHeight + layoutParams2.bottomMargin) + getNextLocationOffset(virtualChildAt));
            }
            i7++;
        }
    }

    void layoutHorizontal(int i, int i2, int i3, int i4) {
        int paddingLeft;
        int i5;
        int i6;
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        int paddingTop = getPaddingTop();
        int i7 = i4 - i2;
        int paddingBottom = i7 - getPaddingBottom();
        int paddingBottom2 = (i7 - paddingTop) - getPaddingBottom();
        int virtualChildCount = getVirtualChildCount();
        i7 = this.mGravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        int i8 = this.mGravity & 112;
        boolean z = this.mBaselineAligned;
        int[] iArr = this.mMaxAscent;
        int[] iArr2 = this.mMaxDescent;
        i7 = GravityCompat.getAbsoluteGravity(i7, ViewCompat.getLayoutDirection(this));
        if (i7 == 1) {
            paddingLeft = (((i3 - i) - r6.mTotalLength) / 2) + getPaddingLeft();
        } else if (i7 != 5) {
            paddingLeft = getPaddingLeft();
        } else {
            paddingLeft = ((getPaddingLeft() + i3) - i) - r6.mTotalLength;
        }
        if (isLayoutRtl) {
            i5 = virtualChildCount - 1;
            i6 = -1;
        } else {
            i5 = 0;
            i6 = 1;
        }
        i7 = 0;
        while (i7 < virtualChildCount) {
            int i9;
            int i10;
            int i11;
            int i12 = i5 + (i6 * i7);
            View virtualChildAt = getVirtualChildAt(i12);
            if (virtualChildAt == null) {
                paddingLeft += measureNullChild(i12);
            } else if (virtualChildAt.getVisibility() != 8) {
                int i13;
                View view;
                LayoutParams layoutParams;
                View view2;
                int measuredWidth = virtualChildAt.getMeasuredWidth();
                int measuredHeight = virtualChildAt.getMeasuredHeight();
                LayoutParams layoutParams2 = (LayoutParams) virtualChildAt.getLayoutParams();
                if (z) {
                    i13 = i7;
                    i9 = virtualChildCount;
                    if (layoutParams2.height != -1) {
                        i7 = virtualChildAt.getBaseline();
                        virtualChildCount = layoutParams2.gravity;
                        if (virtualChildCount < 0) {
                            virtualChildCount = i8;
                        }
                        virtualChildCount &= 112;
                        i10 = i8;
                        if (virtualChildCount != 16) {
                            i7 = ((((paddingBottom2 - measuredHeight) / 2) + paddingTop) + layoutParams2.topMargin) - layoutParams2.bottomMargin;
                        } else if (virtualChildCount != 48) {
                            virtualChildCount = layoutParams2.topMargin + paddingTop;
                            if (i7 != -1) {
                                virtualChildCount += iArr[1] - i7;
                            }
                            i7 = virtualChildCount;
                        } else if (virtualChildCount == 80) {
                            i7 = paddingTop;
                        } else {
                            virtualChildCount = (paddingBottom - measuredHeight) - layoutParams2.bottomMargin;
                            if (i7 != -1) {
                                virtualChildCount -= iArr2[2] - (virtualChildAt.getMeasuredHeight() - i7);
                            }
                            i7 = virtualChildCount;
                        }
                        if (hasDividerBeforeChildAt(i12)) {
                            paddingLeft += r6.mDividerWidth;
                        }
                        virtualChildCount = layoutParams2.leftMargin + paddingLeft;
                        view = virtualChildAt;
                        i8 = i12;
                        i12 = virtualChildCount + getLocationOffset(virtualChildAt);
                        r19 = i13;
                        i11 = paddingTop;
                        layoutParams = layoutParams2;
                        setChildFrame(virtualChildAt, i12, i7, measuredWidth, measuredHeight);
                        view2 = view;
                        i7 = r19 + getChildrenSkipCount(view2, i8);
                        paddingLeft = virtualChildCount + ((measuredWidth + layoutParams.rightMargin) + getNextLocationOffset(view2));
                        i7++;
                        virtualChildCount = i9;
                        i8 = i10;
                        paddingTop = i11;
                    }
                } else {
                    i13 = i7;
                    i9 = virtualChildCount;
                }
                i7 = -1;
                virtualChildCount = layoutParams2.gravity;
                if (virtualChildCount < 0) {
                    virtualChildCount = i8;
                }
                virtualChildCount &= 112;
                i10 = i8;
                if (virtualChildCount != 16) {
                    i7 = ((((paddingBottom2 - measuredHeight) / 2) + paddingTop) + layoutParams2.topMargin) - layoutParams2.bottomMargin;
                } else if (virtualChildCount != 48) {
                    virtualChildCount = layoutParams2.topMargin + paddingTop;
                    if (i7 != -1) {
                        virtualChildCount += iArr[1] - i7;
                    }
                    i7 = virtualChildCount;
                } else if (virtualChildCount == 80) {
                    virtualChildCount = (paddingBottom - measuredHeight) - layoutParams2.bottomMargin;
                    if (i7 != -1) {
                        virtualChildCount -= iArr2[2] - (virtualChildAt.getMeasuredHeight() - i7);
                    }
                    i7 = virtualChildCount;
                } else {
                    i7 = paddingTop;
                }
                if (hasDividerBeforeChildAt(i12)) {
                    paddingLeft += r6.mDividerWidth;
                }
                virtualChildCount = layoutParams2.leftMargin + paddingLeft;
                view = virtualChildAt;
                i8 = i12;
                i12 = virtualChildCount + getLocationOffset(virtualChildAt);
                r19 = i13;
                i11 = paddingTop;
                layoutParams = layoutParams2;
                setChildFrame(virtualChildAt, i12, i7, measuredWidth, measuredHeight);
                view2 = view;
                i7 = r19 + getChildrenSkipCount(view2, i8);
                paddingLeft = virtualChildCount + ((measuredWidth + layoutParams.rightMargin) + getNextLocationOffset(view2));
                i7++;
                virtualChildCount = i9;
                i8 = i10;
                paddingTop = i11;
            } else {
                r19 = i7;
            }
            i11 = paddingTop;
            i9 = virtualChildCount;
            i10 = i8;
            i7++;
            virtualChildCount = i9;
            i8 = i10;
            paddingTop = i11;
        }
    }

    private void setChildFrame(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i3 + i, i4 + i2);
    }

    public void setOrientation(int i) {
        if (this.mOrientation != i) {
            this.mOrientation = i;
            requestLayout();
        }
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void setGravity(int i) {
        if (this.mGravity != i) {
            if ((GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK & i) == 0) {
                i |= GravityCompat.START;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.mGravity = i;
            requestLayout();
        }
    }

    public int getGravity() {
        return this.mGravity;
    }

    public void setHorizontalGravity(int i) {
        i &= GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        if ((GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK & this.mGravity) != i) {
            this.mGravity = i | (this.mGravity & -8388616);
            requestLayout();
        }
    }

    public void setVerticalGravity(int i) {
        i &= 112;
        if ((this.mGravity & 112) != i) {
            this.mGravity = i | (this.mGravity & -113);
            requestLayout();
        }
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected LayoutParams generateDefaultLayoutParams() {
        if (this.mOrientation == 0) {
            return new LayoutParams(-2, -2);
        }
        return this.mOrientation == 1 ? new LayoutParams(-1, -2) : null;
    }

    protected LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(LinearLayoutCompat.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(LinearLayoutCompat.class.getName());
    }
}
