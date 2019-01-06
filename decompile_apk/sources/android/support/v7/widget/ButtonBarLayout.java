package android.support.v7.widget;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.C0192R;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ButtonBarLayout extends LinearLayout {
    private static final int PEEK_BUTTON_DP = 16;
    private boolean mAllowStacking;
    private int mLastWidthSize = -1;
    private int mMinimumHeight = 0;

    public ButtonBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        context = context.obtainStyledAttributes(attributeSet, C0192R.styleable.ButtonBarLayout);
        this.mAllowStacking = context.getBoolean(C0192R.styleable.ButtonBarLayout_allowStacking, true);
        context.recycle();
    }

    public void setAllowStacking(boolean z) {
        if (this.mAllowStacking != z) {
            this.mAllowStacking = z;
            if (!this.mAllowStacking && getOrientation()) {
                setStacked(false);
            }
            requestLayout();
        }
    }

    protected void onMeasure(int i, int i2) {
        Object obj;
        int size = MeasureSpec.getSize(i);
        int i3 = 0;
        if (this.mAllowStacking) {
            if (size > this.mLastWidthSize && isStacked()) {
                setStacked(false);
            }
            this.mLastWidthSize = size;
        }
        if (isStacked() || MeasureSpec.getMode(i) != 1073741824) {
            size = i;
            obj = null;
        } else {
            size = MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
            obj = 1;
        }
        super.onMeasure(size, i2);
        if (this.mAllowStacking && !isStacked()) {
            if (((getMeasuredWidthAndState() & ViewCompat.MEASURED_STATE_MASK) == 16777216 ? 1 : null) != null) {
                setStacked(true);
                obj = 1;
            }
        }
        if (obj != null) {
            super.onMeasure(i, i2);
        }
        i = getNextVisibleChildIndex(0);
        if (i >= 0) {
            i2 = getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) i2.getLayoutParams();
            int paddingTop = (((getPaddingTop() + i2.getMeasuredHeight()) + layoutParams.topMargin) + layoutParams.bottomMargin) + 0;
            if (isStacked() != 0) {
                i = getNextVisibleChildIndex(i + 1);
                if (i >= 0) {
                    paddingTop += getChildAt(i).getPaddingTop() + ((int) (getResources().getDisplayMetrics().density * 1098907648));
                }
                i3 = paddingTop;
            } else {
                i3 = paddingTop + getPaddingBottom();
            }
        }
        if (ViewCompat.getMinimumHeight(this) != i3) {
            setMinimumHeight(i3);
        }
    }

    private int getNextVisibleChildIndex(int i) {
        int childCount = getChildCount();
        while (i < childCount) {
            if (getChildAt(i).getVisibility() == 0) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public int getMinimumHeight() {
        return Math.max(this.mMinimumHeight, super.getMinimumHeight());
    }

    private void setStacked(boolean z) {
        setOrientation(z);
        setGravity(z ? 5 : 80);
        View findViewById = findViewById(C0192R.id.spacer);
        if (findViewById != null) {
            findViewById.setVisibility(z ? true : true);
        }
        for (z = getChildCount() - 2; z < false; z--) {
            bringChildToFront(getChildAt(z));
        }
    }

    private boolean isStacked() {
        return getOrientation() == 1;
    }
}
