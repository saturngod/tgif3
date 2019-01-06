package android.support.v7.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.appcompat.C0192R;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import java.lang.ref.WeakReference;

@RestrictTo({Scope.LIBRARY_GROUP})
public final class ViewStubCompat extends View {
    private OnInflateListener mInflateListener;
    private int mInflatedId;
    private WeakReference<View> mInflatedViewRef;
    private LayoutInflater mInflater;
    private int mLayoutResource;

    public interface OnInflateListener {
        void onInflate(ViewStubCompat viewStubCompat, View view);
    }

    protected void dispatchDraw(Canvas canvas) {
    }

    @SuppressLint({"MissingSuperCall"})
    public void draw(Canvas canvas) {
    }

    public ViewStubCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ViewStubCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mLayoutResource = 0;
        context = context.obtainStyledAttributes(attributeSet, C0192R.styleable.ViewStubCompat, i, 0);
        this.mInflatedId = context.getResourceId(C0192R.styleable.ViewStubCompat_android_inflatedId, -1);
        this.mLayoutResource = context.getResourceId(C0192R.styleable.ViewStubCompat_android_layout, 0);
        setId(context.getResourceId(C0192R.styleable.ViewStubCompat_android_id, -1));
        context.recycle();
        setVisibility(8);
        setWillNotDraw(true);
    }

    public int getInflatedId() {
        return this.mInflatedId;
    }

    public void setInflatedId(int i) {
        this.mInflatedId = i;
    }

    public int getLayoutResource() {
        return this.mLayoutResource;
    }

    public void setLayoutResource(int i) {
        this.mLayoutResource = i;
    }

    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.mInflater = layoutInflater;
    }

    public LayoutInflater getLayoutInflater() {
        return this.mInflater;
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(0, 0);
    }

    public void setVisibility(int i) {
        if (this.mInflatedViewRef != null) {
            View view = (View) this.mInflatedViewRef.get();
            if (view != null) {
                view.setVisibility(i);
                return;
            }
            throw new IllegalStateException("setVisibility called on un-referenced view");
        }
        super.setVisibility(i);
        if (i == 0 || i == 4) {
            inflate();
        }
    }

    public View inflate() {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof ViewGroup)) {
            throw new IllegalStateException("ViewStub must have a non-null ViewGroup viewParent");
        } else if (this.mLayoutResource != 0) {
            LayoutInflater layoutInflater;
            ViewGroup viewGroup = (ViewGroup) parent;
            if (this.mInflater != null) {
                layoutInflater = this.mInflater;
            } else {
                layoutInflater = LayoutInflater.from(getContext());
            }
            View inflate = layoutInflater.inflate(this.mLayoutResource, viewGroup, false);
            if (this.mInflatedId != -1) {
                inflate.setId(this.mInflatedId);
            }
            int indexOfChild = viewGroup.indexOfChild(this);
            viewGroup.removeViewInLayout(this);
            LayoutParams layoutParams = getLayoutParams();
            if (layoutParams != null) {
                viewGroup.addView(inflate, indexOfChild, layoutParams);
            } else {
                viewGroup.addView(inflate, indexOfChild);
            }
            this.mInflatedViewRef = new WeakReference(inflate);
            if (this.mInflateListener != null) {
                this.mInflateListener.onInflate(this, inflate);
            }
            return inflate;
        } else {
            throw new IllegalArgumentException("ViewStub must have a valid layoutResource");
        }
    }

    public void setOnInflateListener(OnInflateListener onInflateListener) {
        this.mInflateListener = onInflateListener;
    }
}
