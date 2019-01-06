package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.text.PrecomputedTextCompat;
import android.support.v4.text.PrecomputedTextCompat.Params;
import android.support.v4.view.TintableBackgroundView;
import android.support.v4.widget.AutoSizeableTextView;
import android.support.v4.widget.TextViewCompat;
import android.util.AttributeSet;
import android.view.ActionMode.Callback;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import java.util.concurrent.Future;

public class AppCompatTextView extends TextView implements TintableBackgroundView, AutoSizeableTextView {
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    @Nullable
    private Future<PrecomputedTextCompat> mPrecomputedTextFuture;
    private final AppCompatTextHelper mTextHelper;

    public AppCompatTextView(Context context) {
        this(context, null);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842884);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet, int i) {
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

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        if (this.mTextHelper != null) {
            this.mTextHelper.onSetTextAppearance(context, i);
        }
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

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return AppCompatHintHelper.onCreateInputConnection(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    public void setFirstBaselineToTopHeight(@Px @IntRange(from = 0) int i) {
        if (VERSION.SDK_INT >= 28) {
            super.setFirstBaselineToTopHeight(i);
        } else {
            TextViewCompat.setFirstBaselineToTopHeight(this, i);
        }
    }

    public void setLastBaselineToBottomHeight(@Px @IntRange(from = 0) int i) {
        if (VERSION.SDK_INT >= 28) {
            super.setLastBaselineToBottomHeight(i);
        } else {
            TextViewCompat.setLastBaselineToBottomHeight(this, i);
        }
    }

    public int getFirstBaselineToTopHeight() {
        return TextViewCompat.getFirstBaselineToTopHeight(this);
    }

    public int getLastBaselineToBottomHeight() {
        return TextViewCompat.getLastBaselineToBottomHeight(this);
    }

    public void setLineHeight(@Px @IntRange(from = 0) int i) {
        TextViewCompat.setLineHeight(this, i);
    }

    public void setCustomSelectionActionModeCallback(Callback callback) {
        super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, callback));
    }

    @NonNull
    public Params getTextMetricsParamsCompat() {
        return TextViewCompat.getTextMetricsParams(this);
    }

    public void setTextMetricsParamsCompat(@NonNull Params params) {
        TextViewCompat.setTextMetricsParams(this, params);
    }

    public void setPrecomputedText(@NonNull PrecomputedTextCompat precomputedTextCompat) {
        TextViewCompat.setPrecomputedText(this, precomputedTextCompat);
    }

    private void consumeTextFutureAndSetBlocking() {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r2 = this;
        r0 = r2.mPrecomputedTextFuture;
        if (r0 == 0) goto L_0x0012;
    L_0x0004:
        r0 = r2.mPrecomputedTextFuture;	 Catch:{ InterruptedException -> 0x0012, InterruptedException -> 0x0012 }
        r1 = 0;	 Catch:{ InterruptedException -> 0x0012, InterruptedException -> 0x0012 }
        r2.mPrecomputedTextFuture = r1;	 Catch:{ InterruptedException -> 0x0012, InterruptedException -> 0x0012 }
        r0 = r0.get();	 Catch:{ InterruptedException -> 0x0012, InterruptedException -> 0x0012 }
        r0 = (android.support.v4.text.PrecomputedTextCompat) r0;	 Catch:{ InterruptedException -> 0x0012, InterruptedException -> 0x0012 }
        android.support.v4.widget.TextViewCompat.setPrecomputedText(r2, r0);	 Catch:{ InterruptedException -> 0x0012, InterruptedException -> 0x0012 }
    L_0x0012:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.AppCompatTextView.consumeTextFutureAndSetBlocking():void");
    }

    public CharSequence getText() {
        consumeTextFutureAndSetBlocking();
        return super.getText();
    }

    public void setTextFuture(@NonNull Future<PrecomputedTextCompat> future) {
        this.mPrecomputedTextFuture = future;
        requestLayout();
    }

    protected void onMeasure(int i, int i2) {
        consumeTextFutureAndSetBlocking();
        super.onMeasure(i, i2);
    }
}
