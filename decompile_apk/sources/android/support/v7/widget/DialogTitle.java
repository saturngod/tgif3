package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.appcompat.C0192R;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.ActionMode.Callback;
import android.widget.TextView;

@RestrictTo({Scope.LIBRARY_GROUP})
public class DialogTitle extends TextView {
    public DialogTitle(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public DialogTitle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DialogTitle(Context context) {
        super(context);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        Layout layout = getLayout();
        if (layout != null) {
            int lineCount = layout.getLineCount();
            if (lineCount > 0 && layout.getEllipsisCount(lineCount - 1) > 0) {
                setSingleLine(false);
                setMaxLines(2);
                TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(null, C0192R.styleable.TextAppearance, 16842817, 16973892);
                int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(C0192R.styleable.TextAppearance_android_textSize, 0);
                if (dimensionPixelSize != 0) {
                    setTextSize(0, (float) dimensionPixelSize);
                }
                obtainStyledAttributes.recycle();
                super.onMeasure(i, i2);
            }
        }
    }

    public void setCustomSelectionActionModeCallback(Callback callback) {
        super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, callback));
    }
}
