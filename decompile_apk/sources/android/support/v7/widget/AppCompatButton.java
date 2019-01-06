package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.TintableBackgroundView;
import android.support.v4.widget.AutoSizeableTextView;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.appcompat.C0192R;
import android.util.AttributeSet;
import android.view.ActionMode.Callback;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;

public class AppCompatButton extends Button implements TintableBackgroundView, AutoSizeableTextView {
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private final AppCompatTextHelper mTextHelper;

    public AppCompatButton(Context context) {
        this(context, null);
    }

    public AppCompatButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0192R.attr.buttonStyle);
    }

    public AppCompatButton(Context context, AttributeSet attributeSet, int i) {
        super(TintContextWrapper.wrap(context), attributeSet, i);
        this.mBackgroundTintHelper = new AppCompatBackgroundHelper(this);
        this.mBackgroundTintHelper.loadFromAttributes(attributeSet, i);
        this.mTextHelper = new AppCompatTextHelper(this);
        this.mTextHelper.loadFromAttributes(attributeSet, i);
        this.mTextHelper.applyCompoundDrawablesTints();
    }

    public void setBackgroundResource(@DrawableRes int i) {
        super.setBackgroundResource(i);
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.onSetBackgroundResource(i);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.onSetBackgroundDrawable(drawable);
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setSupportBackgroundTintList(@Nullable ColorStateList colorStateList) {
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.setSupportBackgroundTintList(colorStateList);
        }
    }

    @Nullable
    @RestrictTo({Scope.LIBRARY_GROUP})
    public ColorStateList getSupportBackgroundTintList() {
        return this.mBackgroundTintHelper != null ? this.mBackgroundTintHelper.getSupportBackgroundTintList() : null;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setSupportBackgroundTintMode(@Nullable Mode mode) {
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.setSupportBackgroundTintMode(mode);
        }
    }

    @Nullable
    @RestrictTo({Scope.LIBRARY_GROUP})
    public Mode getSupportBackgroundTintMode() {
        return this.mBackgroundTintHelper != null ? this.mBackgroundTintHelper.getSupportBackgroundTintMode() : null;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.applySupportBackgroundTint();
        }
        if (this.mTextHelper != null) {
            this.mTextHelper.applyCompoundDrawablesTints();
        }
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        if (this.mTextHelper != null) {
            this.mTextHelper.onSetTextAppearance(context, i);
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Button.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.mTextHelper != null) {
            this.mTextHelper.onLayout(z, i, i2, i3, i4);
        }
    }

    public void setTextSize(int i, float f) {
        if (PLATFORM_SUPPORTS_AUTOSIZE) {
            super.setTextSize(i, f);
        } else if (this.mTextHelper != null) {
            this.mTextHelper.setTextSize(i, f);
        }
    }

    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        if (this.mTextHelper != null && PLATFORM_SUPPORTS_AUTOSIZE == null && this.mTextHelper.isAutoSizeEnabled() != null) {
            this.mTextHelper.autoSizeText();
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (PLATFORM_SUPPORTS_AUTOSIZE) {
            super.setAutoSizeTextTypeWithDefaults(i);
        } else if (this.mTextHelper != null) {
            this.mTextHelper.setAutoSizeTextTypeWithDefaults(i);
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) throws IllegalArgumentException {
        if (PLATFORM_SUPPORTS_AUTOSIZE) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
        } else if (this.mTextHelper != null) {
            this.mTextHelper.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] iArr, int i) throws IllegalArgumentException {
        if (PLATFORM_SUPPORTS_AUTOSIZE) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
        } else if (this.mTextHelper != null) {
            this.mTextHelper.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public int getAutoSizeTextType() {
        int i = 0;
        if (PLATFORM_SUPPORTS_AUTOSIZE) {
            if (super.getAutoSizeTextType() == 1) {
                i = 1;
            }
            return i;
        } else if (this.mTextHelper != null) {
            return this.mTextHelper.getAutoSizeTextType();
        } else {
            return 0;
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public int getAutoSizeStepGranularity() {
        if (PLATFORM_SUPPORTS_AUTOSIZE) {
            return super.getAutoSizeStepGranularity();
        }
        return this.mTextHelper != null ? this.mTextHelper.getAutoSizeStepGranularity() : -1;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public int getAutoSizeMinTextSize() {
        if (PLATFORM_SUPPORTS_AUTOSIZE) {
            return super.getAutoSizeMinTextSize();
        }
        return this.mTextHelper != null ? this.mTextHelper.getAutoSizeMinTextSize() : -1;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public int getAutoSizeMaxTextSize() {
        if (PLATFORM_SUPPORTS_AUTOSIZE) {
            return super.getAutoSizeMaxTextSize();
        }
        return this.mTextHelper != null ? this.mTextHelper.getAutoSizeMaxTextSize() : -1;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public int[] getAutoSizeTextAvailableSizes() {
        if (PLATFORM_SUPPORTS_AUTOSIZE) {
            return super.getAutoSizeTextAvailableSizes();
        }
        if (this.mTextHelper != null) {
            return this.mTextHelper.getAutoSizeTextAvailableSizes();
        }
        return new int[0];
    }

    public void setSupportAllCaps(boolean z) {
        if (this.mTextHelper != null) {
            this.mTextHelper.setAllCaps(z);
        }
    }

    public void setCustomSelectionActionModeCallback(Callback callback) {
        super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, callback));
    }
}
