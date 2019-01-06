package android.support.constraint;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.constraint.ConstraintLayout.LayoutParams;
import android.util.AttributeSet;
import android.view.View;

public class Group extends ConstraintHelper {
    public Group(Context context) {
        super(context);
    }

    public Group(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public Group(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void init(AttributeSet attributeSet) {
        super.init(attributeSet);
        this.mUseViewMeasure = null;
    }

    public void updatePreLayout(ConstraintLayout constraintLayout) {
        int visibility = getVisibility();
        float elevation = VERSION.SDK_INT >= 21 ? getElevation() : 0.0f;
        for (int i = 0; i < this.mCount; i++) {
            View viewById = constraintLayout.getViewById(this.mIds[i]);
            if (viewById != null) {
                viewById.setVisibility(visibility);
                if (elevation > 0.0f && VERSION.SDK_INT >= 21) {
                    viewById.setElevation(elevation);
                }
            }
        }
    }

    public void updatePostLayout(ConstraintLayout constraintLayout) {
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        layoutParams.widget.setWidth(0);
        layoutParams.widget.setHeight(0);
    }
}
