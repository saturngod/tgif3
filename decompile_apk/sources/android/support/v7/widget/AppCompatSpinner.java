package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.database.DataSetObserver;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.TintableBackgroundView;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.C0192R;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;

public class AppCompatSpinner extends Spinner implements TintableBackgroundView {
    private static final int[] ATTRS_ANDROID_SPINNERMODE = new int[]{16843505};
    private static final int MAX_ITEMS_MEASURED = 15;
    private static final int MODE_DIALOG = 0;
    private static final int MODE_DROPDOWN = 1;
    private static final int MODE_THEME = -1;
    private static final String TAG = "AppCompatSpinner";
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    int mDropDownWidth;
    private ForwardingListener mForwardingListener;
    DropdownPopup mPopup;
    private final Context mPopupContext;
    private final boolean mPopupSet;
    private SpinnerAdapter mTempAdapter;
    final Rect mTempRect;

    private static class DropDownAdapter implements ListAdapter, SpinnerAdapter {
        private SpinnerAdapter mAdapter;
        private ListAdapter mListAdapter;

        public int getItemViewType(int i) {
            return 0;
        }

        public int getViewTypeCount() {
            return 1;
        }

        public DropDownAdapter(@Nullable SpinnerAdapter spinnerAdapter, @Nullable Theme theme) {
            this.mAdapter = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.mListAdapter = (ListAdapter) spinnerAdapter;
            }
            if (theme == null) {
                return;
            }
            if (VERSION.SDK_INT >= 23 && (spinnerAdapter instanceof ThemedSpinnerAdapter)) {
                ThemedSpinnerAdapter themedSpinnerAdapter = (ThemedSpinnerAdapter) spinnerAdapter;
                if (themedSpinnerAdapter.getDropDownViewTheme() != theme) {
                    themedSpinnerAdapter.setDropDownViewTheme(theme);
                }
            } else if (spinnerAdapter instanceof ThemedSpinnerAdapter) {
                ThemedSpinnerAdapter themedSpinnerAdapter2 = (ThemedSpinnerAdapter) spinnerAdapter;
                if (themedSpinnerAdapter2.getDropDownViewTheme() == null) {
                    themedSpinnerAdapter2.setDropDownViewTheme(theme);
                }
            }
        }

        public int getCount() {
            return this.mAdapter == null ? 0 : this.mAdapter.getCount();
        }

        public Object getItem(int i) {
            return this.mAdapter == null ? 0 : this.mAdapter.getItem(i);
        }

        public long getItemId(int i) {
            return this.mAdapter == null ? -1 : this.mAdapter.getItemId(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            return getDropDownView(i, view, viewGroup);
        }

        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            if (this.mAdapter == null) {
                return 0;
            }
            return this.mAdapter.getDropDownView(i, view, viewGroup);
        }

        public boolean hasStableIds() {
            return this.mAdapter != null && this.mAdapter.hasStableIds();
        }

        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            if (this.mAdapter != null) {
                this.mAdapter.registerDataSetObserver(dataSetObserver);
            }
        }

        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            if (this.mAdapter != null) {
                this.mAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }

        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.mListAdapter;
            return listAdapter != null ? listAdapter.areAllItemsEnabled() : true;
        }

        public boolean isEnabled(int i) {
            ListAdapter listAdapter = this.mListAdapter;
            return listAdapter != null ? listAdapter.isEnabled(i) : true;
        }

        public boolean isEmpty() {
            return getCount() == 0;
        }
    }

    private class DropdownPopup extends ListPopupWindow {
        ListAdapter mAdapter;
        private CharSequence mHintText;
        private final Rect mVisibleRect = new Rect();

        /* renamed from: android.support.v7.widget.AppCompatSpinner$DropdownPopup$2 */
        class C02132 implements OnGlobalLayoutListener {
            C02132() {
            }

            public void onGlobalLayout() {
                if (DropdownPopup.this.isVisibleToUser(AppCompatSpinner.this)) {
                    DropdownPopup.this.computeContentWidth();
                    super.show();
                    return;
                }
                DropdownPopup.this.dismiss();
            }
        }

        public DropdownPopup(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            setAnchorView(AppCompatSpinner.this);
            setModal(true);
            setPromptPosition(null);
            setOnItemClickListener(new OnItemClickListener(AppCompatSpinner.this) {
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    AppCompatSpinner.this.setSelection(i);
                    if (AppCompatSpinner.this.getOnItemClickListener() != null) {
                        AppCompatSpinner.this.performItemClick(view, i, DropdownPopup.this.mAdapter.getItemId(i));
                    }
                    DropdownPopup.this.dismiss();
                }
            });
        }

        public void setAdapter(ListAdapter listAdapter) {
            super.setAdapter(listAdapter);
            this.mAdapter = listAdapter;
        }

        public CharSequence getHintText() {
            return this.mHintText;
        }

        public void setPromptText(CharSequence charSequence) {
            this.mHintText = charSequence;
        }

        void computeContentWidth() {
            Drawable background = getBackground();
            int i = 0;
            if (background != null) {
                background.getPadding(AppCompatSpinner.this.mTempRect);
                i = ViewUtils.isLayoutRtl(AppCompatSpinner.this) ? AppCompatSpinner.this.mTempRect.right : -AppCompatSpinner.this.mTempRect.left;
            } else {
                Rect rect = AppCompatSpinner.this.mTempRect;
                AppCompatSpinner.this.mTempRect.right = 0;
                rect.left = 0;
            }
            int paddingLeft = AppCompatSpinner.this.getPaddingLeft();
            int paddingRight = AppCompatSpinner.this.getPaddingRight();
            int width = AppCompatSpinner.this.getWidth();
            if (AppCompatSpinner.this.mDropDownWidth == -2) {
                int compatMeasureContentWidth = AppCompatSpinner.this.compatMeasureContentWidth((SpinnerAdapter) this.mAdapter, getBackground());
                int i2 = (AppCompatSpinner.this.getContext().getResources().getDisplayMetrics().widthPixels - AppCompatSpinner.this.mTempRect.left) - AppCompatSpinner.this.mTempRect.right;
                if (compatMeasureContentWidth > i2) {
                    compatMeasureContentWidth = i2;
                }
                setContentWidth(Math.max(compatMeasureContentWidth, (width - paddingLeft) - paddingRight));
            } else if (AppCompatSpinner.this.mDropDownWidth == -1) {
                setContentWidth((width - paddingLeft) - paddingRight);
            } else {
                setContentWidth(AppCompatSpinner.this.mDropDownWidth);
            }
            setHorizontalOffset(ViewUtils.isLayoutRtl(AppCompatSpinner.this) ? i + ((width - paddingRight) - getWidth()) : i + paddingLeft);
        }

        public void show() {
            boolean isShowing = isShowing();
            computeContentWidth();
            setInputMethodMode(2);
            super.show();
            getListView().setChoiceMode(1);
            setSelection(AppCompatSpinner.this.getSelectedItemPosition());
            if (!isShowing) {
                ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    final OnGlobalLayoutListener c02132 = new C02132();
                    viewTreeObserver.addOnGlobalLayoutListener(c02132);
                    setOnDismissListener(new OnDismissListener() {
                        public void onDismiss() {
                            ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
                            if (viewTreeObserver != null) {
                                viewTreeObserver.removeGlobalOnLayoutListener(c02132);
                            }
                        }
                    });
                }
            }
        }

        boolean isVisibleToUser(View view) {
            return (!ViewCompat.isAttachedToWindow(view) || view.getGlobalVisibleRect(this.mVisibleRect) == null) ? null : true;
        }
    }

    public AppCompatSpinner(Context context) {
        this(context, null);
    }

    public AppCompatSpinner(Context context, int i) {
        this(context, null, C0192R.attr.spinnerStyle, i);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0192R.attr.spinnerStyle);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, -1);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet, int i, int i2) {
        this(context, attributeSet, i, i2, null);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AppCompatSpinner(android.content.Context r8, android.util.AttributeSet r9, int r10, int r11, android.content.res.Resources.Theme r12) {
        /*
        r7 = this;
        r7.<init>(r8, r9, r10);
        r0 = new android.graphics.Rect;
        r0.<init>();
        r7.mTempRect = r0;
        r0 = android.support.v7.appcompat.C0192R.styleable.Spinner;
        r1 = 0;
        r0 = android.support.v7.widget.TintTypedArray.obtainStyledAttributes(r8, r9, r0, r10, r1);
        r2 = new android.support.v7.widget.AppCompatBackgroundHelper;
        r2.<init>(r7);
        r7.mBackgroundTintHelper = r2;
        r2 = 0;
        if (r12 == 0) goto L_0x0023;
    L_0x001b:
        r3 = new android.support.v7.view.ContextThemeWrapper;
        r3.<init>(r8, r12);
        r7.mPopupContext = r3;
        goto L_0x003e;
    L_0x0023:
        r12 = android.support.v7.appcompat.C0192R.styleable.Spinner_popupTheme;
        r12 = r0.getResourceId(r12, r1);
        if (r12 == 0) goto L_0x0033;
    L_0x002b:
        r3 = new android.support.v7.view.ContextThemeWrapper;
        r3.<init>(r8, r12);
        r7.mPopupContext = r3;
        goto L_0x003e;
    L_0x0033:
        r12 = android.os.Build.VERSION.SDK_INT;
        r3 = 23;
        if (r12 >= r3) goto L_0x003b;
    L_0x0039:
        r12 = r8;
        goto L_0x003c;
    L_0x003b:
        r12 = r2;
    L_0x003c:
        r7.mPopupContext = r12;
    L_0x003e:
        r12 = r7.mPopupContext;
        r3 = 1;
        if (r12 == 0) goto L_0x00ad;
    L_0x0043:
        r12 = -1;
        if (r11 != r12) goto L_0x0075;
    L_0x0046:
        r12 = ATTRS_ANDROID_SPINNERMODE;	 Catch:{ Exception -> 0x0062, all -> 0x005f }
        r12 = r8.obtainStyledAttributes(r9, r12, r10, r1);	 Catch:{ Exception -> 0x0062, all -> 0x005f }
        r4 = r12.hasValue(r1);	 Catch:{ Exception -> 0x005d }
        if (r4 == 0) goto L_0x0057;
    L_0x0052:
        r4 = r12.getInt(r1, r1);	 Catch:{ Exception -> 0x005d }
        r11 = r4;
    L_0x0057:
        if (r12 == 0) goto L_0x0075;
    L_0x0059:
        r12.recycle();
        goto L_0x0075;
    L_0x005d:
        r4 = move-exception;
        goto L_0x0064;
    L_0x005f:
        r8 = move-exception;
        r12 = r2;
        goto L_0x006f;
    L_0x0062:
        r4 = move-exception;
        r12 = r2;
    L_0x0064:
        r5 = "AppCompatSpinner";
        r6 = "Could not read android:spinnerMode";
        android.util.Log.i(r5, r6, r4);	 Catch:{ all -> 0x006e }
        if (r12 == 0) goto L_0x0075;
    L_0x006d:
        goto L_0x0059;
    L_0x006e:
        r8 = move-exception;
    L_0x006f:
        if (r12 == 0) goto L_0x0074;
    L_0x0071:
        r12.recycle();
    L_0x0074:
        throw r8;
    L_0x0075:
        if (r11 != r3) goto L_0x00ad;
    L_0x0077:
        r11 = new android.support.v7.widget.AppCompatSpinner$DropdownPopup;
        r12 = r7.mPopupContext;
        r11.<init>(r12, r9, r10);
        r12 = r7.mPopupContext;
        r4 = android.support.v7.appcompat.C0192R.styleable.Spinner;
        r12 = android.support.v7.widget.TintTypedArray.obtainStyledAttributes(r12, r9, r4, r10, r1);
        r1 = android.support.v7.appcompat.C0192R.styleable.Spinner_android_dropDownWidth;
        r4 = -2;
        r1 = r12.getLayoutDimension(r1, r4);
        r7.mDropDownWidth = r1;
        r1 = android.support.v7.appcompat.C0192R.styleable.Spinner_android_popupBackground;
        r1 = r12.getDrawable(r1);
        r11.setBackgroundDrawable(r1);
        r1 = android.support.v7.appcompat.C0192R.styleable.Spinner_android_prompt;
        r1 = r0.getString(r1);
        r11.setPromptText(r1);
        r12.recycle();
        r7.mPopup = r11;
        r12 = new android.support.v7.widget.AppCompatSpinner$1;
        r12.<init>(r7, r11);
        r7.mForwardingListener = r12;
    L_0x00ad:
        r11 = android.support.v7.appcompat.C0192R.styleable.Spinner_android_entries;
        r11 = r0.getTextArray(r11);
        if (r11 == 0) goto L_0x00c5;
    L_0x00b5:
        r12 = new android.widget.ArrayAdapter;
        r1 = 17367048; // 0x1090008 float:2.5162948E-38 double:8.580462E-317;
        r12.<init>(r8, r1, r11);
        r8 = android.support.v7.appcompat.C0192R.layout.support_simple_spinner_dropdown_item;
        r12.setDropDownViewResource(r8);
        r7.setAdapter(r12);
    L_0x00c5:
        r0.recycle();
        r7.mPopupSet = r3;
        r8 = r7.mTempAdapter;
        if (r8 == 0) goto L_0x00d5;
    L_0x00ce:
        r8 = r7.mTempAdapter;
        r7.setAdapter(r8);
        r7.mTempAdapter = r2;
    L_0x00d5:
        r8 = r7.mBackgroundTintHelper;
        r8.loadFromAttributes(r9, r10);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.AppCompatSpinner.<init>(android.content.Context, android.util.AttributeSet, int, int, android.content.res.Resources$Theme):void");
    }

    public Context getPopupContext() {
        if (this.mPopup != null) {
            return this.mPopupContext;
        }
        return VERSION.SDK_INT >= 23 ? super.getPopupContext() : null;
    }

    public void setPopupBackgroundDrawable(Drawable drawable) {
        if (this.mPopup != null) {
            this.mPopup.setBackgroundDrawable(drawable);
        } else if (VERSION.SDK_INT >= 16) {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    public void setPopupBackgroundResource(@DrawableRes int i) {
        setPopupBackgroundDrawable(AppCompatResources.getDrawable(getPopupContext(), i));
    }

    public Drawable getPopupBackground() {
        if (this.mPopup != null) {
            return this.mPopup.getBackground();
        }
        return VERSION.SDK_INT >= 16 ? super.getPopupBackground() : null;
    }

    public void setDropDownVerticalOffset(int i) {
        if (this.mPopup != null) {
            this.mPopup.setVerticalOffset(i);
        } else if (VERSION.SDK_INT >= 16) {
            super.setDropDownVerticalOffset(i);
        }
    }

    public int getDropDownVerticalOffset() {
        if (this.mPopup != null) {
            return this.mPopup.getVerticalOffset();
        }
        return VERSION.SDK_INT >= 16 ? super.getDropDownVerticalOffset() : 0;
    }

    public void setDropDownHorizontalOffset(int i) {
        if (this.mPopup != null) {
            this.mPopup.setHorizontalOffset(i);
        } else if (VERSION.SDK_INT >= 16) {
            super.setDropDownHorizontalOffset(i);
        }
    }

    public int getDropDownHorizontalOffset() {
        if (this.mPopup != null) {
            return this.mPopup.getHorizontalOffset();
        }
        return VERSION.SDK_INT >= 16 ? super.getDropDownHorizontalOffset() : 0;
    }

    public void setDropDownWidth(int i) {
        if (this.mPopup != null) {
            this.mDropDownWidth = i;
        } else if (VERSION.SDK_INT >= 16) {
            super.setDropDownWidth(i);
        }
    }

    public int getDropDownWidth() {
        if (this.mPopup != null) {
            return this.mDropDownWidth;
        }
        return VERSION.SDK_INT >= 16 ? super.getDropDownWidth() : 0;
    }

    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (this.mPopupSet) {
            super.setAdapter(spinnerAdapter);
            if (this.mPopup != null) {
                this.mPopup.setAdapter(new DropDownAdapter(spinnerAdapter, (this.mPopupContext == null ? getContext() : this.mPopupContext).getTheme()));
            }
            return;
        }
        this.mTempAdapter = spinnerAdapter;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mPopup != null && this.mPopup.isShowing()) {
            this.mPopup.dismiss();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mForwardingListener == null || !this.mForwardingListener.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.mPopup != 0 && MeasureSpec.getMode(i) == Integer.MIN_VALUE) {
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), compatMeasureContentWidth(getAdapter(), getBackground())), MeasureSpec.getSize(i)), getMeasuredHeight());
        }
    }

    public boolean performClick() {
        if (this.mPopup == null) {
            return super.performClick();
        }
        if (!this.mPopup.isShowing()) {
            this.mPopup.show();
        }
        return true;
    }

    public void setPrompt(CharSequence charSequence) {
        if (this.mPopup != null) {
            this.mPopup.setPromptText(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    public CharSequence getPrompt() {
        return this.mPopup != null ? this.mPopup.getHintText() : super.getPrompt();
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
    }

    int compatMeasureContentWidth(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int max = Math.max(0, getSelectedItemPosition());
        int min = Math.min(spinnerAdapter.getCount(), max + 15);
        View view = null;
        int i2 = 0;
        for (max = Math.max(0, max - (15 - (min - max))); max < min; max++) {
            int itemViewType = spinnerAdapter.getItemViewType(max);
            if (itemViewType != i) {
                view = null;
                i = itemViewType;
            }
            view = spinnerAdapter.getView(max, view, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new LayoutParams(-2, -2));
            }
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            i2 = Math.max(i2, view.getMeasuredWidth());
        }
        if (drawable != null) {
            drawable.getPadding(this.mTempRect);
            i2 += this.mTempRect.left + this.mTempRect.right;
        }
        return i2;
    }
}
