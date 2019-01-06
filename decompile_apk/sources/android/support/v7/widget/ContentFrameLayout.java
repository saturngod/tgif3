package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;

@RestrictTo({Scope.LIBRARY})
public class ContentFrameLayout extends FrameLayout {
    private OnAttachListener mAttachListener;
    private final Rect mDecorPadding;
    private TypedValue mFixedHeightMajor;
    private TypedValue mFixedHeightMinor;
    private TypedValue mFixedWidthMajor;
    private TypedValue mFixedWidthMinor;
    private TypedValue mMinWidthMajor;
    private TypedValue mMinWidthMinor;

    public interface OnAttachListener {
        void onAttachedFromWindow();

        void onDetachedFromWindow();
    }

    public ContentFrameLayout(Context context) {
        this(context, null);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDecorPadding = new Rect();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void dispatchFitSystemWindows(Rect rect) {
        fitSystemWindows(rect);
    }

    public void setAttachListener(OnAttachListener onAttachListener) {
        this.mAttachListener = onAttachListener;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setDecorPadding(int i, int i2, int i3, int i4) {
        this.mDecorPadding.set(i, i2, i3, i4);
        if (ViewCompat.isLaidOut(this) != 0) {
            requestLayout();
        }
    }

    protected void onMeasure(int i, int i2) {
        int dimension;
        TypedValue typedValue;
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        Object obj = 1;
        Object obj2 = displayMetrics.widthPixels < displayMetrics.heightPixels ? 1 : null;
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE) {
            TypedValue typedValue2 = obj2 != null ? this.mFixedWidthMinor : this.mFixedWidthMajor;
            if (!(typedValue2 == null || typedValue2.type == 0)) {
                dimension = typedValue2.type == 5 ? (int) typedValue2.getDimension(displayMetrics) : typedValue2.type == 6 ? (int) typedValue2.getFraction((float) displayMetrics.widthPixels, (float) displayMetrics.widthPixels) : 0;
                if (dimension > 0) {
                    dimension = MeasureSpec.makeMeasureSpec(Math.min(dimension - (this.mDecorPadding.left + this.mDecorPadding.right), MeasureSpec.getSize(i)), 1073741824);
                    i = 1;
                    if (mode2 == Integer.MIN_VALUE) {
                        typedValue = obj2 == null ? this.mFixedHeightMajor : this.mFixedHeightMinor;
                        if (!(typedValue == null || typedValue.type == 0)) {
                            mode2 = typedValue.type != 5 ? (int) typedValue.getDimension(displayMetrics) : typedValue.type != 6 ? (int) typedValue.getFraction((float) displayMetrics.heightPixels, (float) displayMetrics.heightPixels) : 0;
                            if (mode2 > 0) {
                                i2 = MeasureSpec.makeMeasureSpec(Math.min(mode2 - (this.mDecorPadding.top + this.mDecorPadding.bottom), MeasureSpec.getSize(i2)), 1073741824);
                            }
                        }
                    }
                    super.onMeasure(dimension, i2);
                    mode2 = getMeasuredWidth();
                    dimension = MeasureSpec.makeMeasureSpec(mode2, 1073741824);
                    if (i == 0 && mode == Integer.MIN_VALUE) {
                        i = obj2 == null ? this.mMinWidthMinor : this.mMinWidthMajor;
                        if (!(i == 0 || i.type == 0)) {
                            i = i.type != 5 ? (int) i.getDimension(displayMetrics) : i.type != 6 ? (int) i.getFraction((float) displayMetrics.widthPixels, (float) displayMetrics.widthPixels) : 0;
                            if (i > 0) {
                                i -= this.mDecorPadding.left + this.mDecorPadding.right;
                            }
                            if (mode2 < i) {
                                dimension = MeasureSpec.makeMeasureSpec(i, 1073741824);
                                if (obj == null) {
                                    super.onMeasure(dimension, i2);
                                }
                            }
                        }
                    }
                    obj = null;
                    if (obj == null) {
                        super.onMeasure(dimension, i2);
                    }
                }
            }
        }
        dimension = i;
        i = 0;
        if (mode2 == Integer.MIN_VALUE) {
            if (obj2 == null) {
            }
            if (typedValue.type != 5) {
                if (typedValue.type != 6) {
                }
            }
            if (mode2 > 0) {
                i2 = MeasureSpec.makeMeasureSpec(Math.min(mode2 - (this.mDecorPadding.top + this.mDecorPadding.bottom), MeasureSpec.getSize(i2)), 1073741824);
            }
        }
        super.onMeasure(dimension, i2);
        mode2 = getMeasuredWidth();
        dimension = MeasureSpec.makeMeasureSpec(mode2, 1073741824);
        if (obj2 == null) {
        }
        if (i.type != 5) {
            if (i.type != 6) {
            }
        }
        if (i > 0) {
            i -= this.mDecorPadding.left + this.mDecorPadding.right;
        }
        if (mode2 < i) {
            dimension = MeasureSpec.makeMeasureSpec(i, 1073741824);
            if (obj == null) {
                super.onMeasure(dimension, i2);
            }
        }
        obj = null;
        if (obj == null) {
            super.onMeasure(dimension, i2);
        }
    }

    public TypedValue getMinWidthMajor() {
        if (this.mMinWidthMajor == null) {
            this.mMinWidthMajor = new TypedValue();
        }
        return this.mMinWidthMajor;
    }

    public TypedValue getMinWidthMinor() {
        if (this.mMinWidthMinor == null) {
            this.mMinWidthMinor = new TypedValue();
        }
        return this.mMinWidthMinor;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.mFixedWidthMajor == null) {
            this.mFixedWidthMajor = new TypedValue();
        }
        return this.mFixedWidthMajor;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.mFixedWidthMinor == null) {
            this.mFixedWidthMinor = new TypedValue();
        }
        return this.mFixedWidthMinor;
    }

    public TypedValue getFixedHeightMajor() {
        if (this.mFixedHeightMajor == null) {
            this.mFixedHeightMajor = new TypedValue();
        }
        return this.mFixedHeightMajor;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.mFixedHeightMinor == null) {
            this.mFixedHeightMinor = new TypedValue();
        }
        return this.mFixedHeightMinor;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mAttachListener != null) {
            this.mAttachListener.onAttachedFromWindow();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mAttachListener != null) {
            this.mAttachListener.onDetachedFromWindow();
        }
    }
}
