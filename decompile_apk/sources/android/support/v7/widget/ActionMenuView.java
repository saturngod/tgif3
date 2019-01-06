package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.StyleRes;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.ItemInvoker;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter.Callback;
import android.support.v7.view.menu.MenuView;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewDebug.ExportedProperty;
import android.view.accessibility.AccessibilityEvent;

public class ActionMenuView extends LinearLayoutCompat implements ItemInvoker, MenuView {
    static final int GENERATED_ITEM_PADDING = 4;
    static final int MIN_CELL_SIZE = 56;
    private static final String TAG = "ActionMenuView";
    private Callback mActionMenuPresenterCallback;
    private boolean mFormatItems;
    private int mFormatItemsWidth;
    private int mGeneratedItemPadding;
    private MenuBuilder mMenu;
    MenuBuilder.Callback mMenuBuilderCallback;
    private int mMinCellSize;
    OnMenuItemClickListener mOnMenuItemClickListener;
    private Context mPopupContext;
    private int mPopupTheme;
    private ActionMenuPresenter mPresenter;
    private boolean mReserveOverflow;

    @RestrictTo({Scope.LIBRARY_GROUP})
    public interface ActionMenuChildView {
        boolean needsDividerAfter();

        boolean needsDividerBefore();
    }

    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    private static class ActionMenuPresenterCallback implements Callback {
        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            return false;
        }

        ActionMenuPresenterCallback() {
        }
    }

    public static class LayoutParams extends android.support.v7.widget.LinearLayoutCompat.LayoutParams {
        @ExportedProperty
        public int cellsUsed;
        @ExportedProperty
        public boolean expandable;
        boolean expanded;
        @ExportedProperty
        public int extraPixels;
        @ExportedProperty
        public boolean isOverflowButton;
        @ExportedProperty
        public boolean preventEdgeOffset;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((android.view.ViewGroup.LayoutParams) layoutParams);
            this.isOverflowButton = layoutParams.isOverflowButton;
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.isOverflowButton = false;
        }

        LayoutParams(int i, int i2, boolean z) {
            super(i, i2);
            this.isOverflowButton = z;
        }
    }

    private class MenuBuilderCallback implements MenuBuilder.Callback {
        MenuBuilderCallback() {
        }

        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            return (ActionMenuView.this.mOnMenuItemClickListener == null || ActionMenuView.this.mOnMenuItemClickListener.onMenuItemClick(menuItem) == null) ? null : true;
        }

        public void onMenuModeChange(MenuBuilder menuBuilder) {
            if (ActionMenuView.this.mMenuBuilderCallback != null) {
                ActionMenuView.this.mMenuBuilderCallback.onMenuModeChange(menuBuilder);
            }
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public int getWindowAnimations() {
        return 0;
    }

    public ActionMenuView(Context context) {
        this(context, null);
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f = context.getResources().getDisplayMetrics().density;
        this.mMinCellSize = (int) (56.0f * f);
        this.mGeneratedItemPadding = (int) (f * 4.0f);
        this.mPopupContext = context;
        this.mPopupTheme = 0;
    }

    public void setPopupTheme(@StyleRes int i) {
        if (this.mPopupTheme != i) {
            this.mPopupTheme = i;
            if (i == 0) {
                this.mPopupContext = getContext();
            } else {
                this.mPopupContext = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public int getPopupTheme() {
        return this.mPopupTheme;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setPresenter(ActionMenuPresenter actionMenuPresenter) {
        this.mPresenter = actionMenuPresenter;
        this.mPresenter.setMenuView(this);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mPresenter != null) {
            this.mPresenter.updateMenuView(false);
            if (this.mPresenter.isOverflowMenuShowing() != null) {
                this.mPresenter.hideOverflowMenu();
                this.mPresenter.showOverflowMenu();
            }
        }
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.mOnMenuItemClickListener = onMenuItemClickListener;
    }

    protected void onMeasure(int i, int i2) {
        boolean z = this.mFormatItems;
        this.mFormatItems = MeasureSpec.getMode(i) == 1073741824;
        if (z != this.mFormatItems) {
            this.mFormatItemsWidth = 0;
        }
        int size = MeasureSpec.getSize(i);
        if (!(!this.mFormatItems || this.mMenu == null || size == this.mFormatItemsWidth)) {
            this.mFormatItemsWidth = size;
            this.mMenu.onItemsChanged(true);
        }
        size = getChildCount();
        if (!this.mFormatItems || size <= 0) {
            for (int i3 = 0; i3 < size; i3++) {
                LayoutParams layoutParams = (LayoutParams) getChildAt(i3).getLayoutParams();
                layoutParams.rightMargin = 0;
                layoutParams.leftMargin = 0;
            }
            super.onMeasure(i, i2);
            return;
        }
        onMeasureExactFormat(i, i2);
    }

    private void onMeasureExactFormat(int i, int i2) {
        int mode = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = getChildMeasureSpec(i2, paddingTop, -2);
        size -= paddingLeft;
        paddingLeft = size / this.mMinCellSize;
        int i3 = size % this.mMinCellSize;
        if (paddingLeft == 0) {
            setMeasuredDimension(size, 0);
            return;
        }
        int i4;
        int i5;
        Object obj;
        int i6;
        int i7;
        int i8;
        View childAt;
        int i9 = r0.mMinCellSize + (i3 / paddingLeft);
        i3 = getChildCount();
        int i10 = paddingLeft;
        paddingLeft = 0;
        int i11 = 0;
        Object obj2 = null;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        long j = 0;
        while (paddingLeft < i3) {
            View childAt2 = getChildAt(paddingLeft);
            i4 = size2;
            if (childAt2.getVisibility() == 8) {
                i5 = size;
            } else {
                int i15;
                boolean z;
                int i16;
                boolean z2 = childAt2 instanceof ActionMenuItemView;
                i12++;
                if (z2) {
                    i15 = i12;
                    i5 = size;
                    z = false;
                    childAt2.setPadding(r0.mGeneratedItemPadding, 0, r0.mGeneratedItemPadding, 0);
                } else {
                    i5 = size;
                    i15 = i12;
                    z = false;
                }
                LayoutParams layoutParams = (LayoutParams) childAt2.getLayoutParams();
                layoutParams.expanded = z;
                layoutParams.extraPixels = z;
                layoutParams.cellsUsed = z;
                layoutParams.expandable = z;
                layoutParams.leftMargin = z;
                layoutParams.rightMargin = z;
                z = z2 && ((ActionMenuItemView) childAt2).hasText();
                layoutParams.preventEdgeOffset = z;
                size = measureChildForCells(childAt2, i9, layoutParams.isOverflowButton ? 1 : i10, childMeasureSpec, paddingTop);
                size2 = Math.max(i13, size);
                if (layoutParams.expandable) {
                    i14++;
                }
                if (layoutParams.isOverflowButton) {
                    obj2 = 1;
                }
                i10 -= size;
                i11 = Math.max(i11, childAt2.getMeasuredHeight());
                if (size == 1) {
                    i16 = size2;
                    j |= (long) (1 << paddingLeft);
                } else {
                    i16 = size2;
                }
                i12 = i15;
                i13 = i16;
            }
            paddingLeft++;
            size2 = i4;
            size = i5;
        }
        i5 = size;
        i4 = size2;
        Object obj3 = (obj2 == null || i12 != 2) ? null : 1;
        Object obj4 = null;
        while (i14 > 0 && i10 > 0) {
            paddingTop = 0;
            int i17 = 0;
            int i18 = Integer.MAX_VALUE;
            long j2 = 0;
            while (paddingTop < i3) {
                LayoutParams layoutParams2 = (LayoutParams) getChildAt(paddingTop).getLayoutParams();
                obj = obj4;
                if (layoutParams2.expandable) {
                    if (layoutParams2.cellsUsed < i18) {
                        j2 = 1 << paddingTop;
                        i18 = layoutParams2.cellsUsed;
                        i17 = 1;
                    } else if (layoutParams2.cellsUsed == i18) {
                        j2 |= 1 << paddingTop;
                        i17++;
                    }
                }
                paddingTop++;
                obj4 = obj;
            }
            obj = obj4;
            j |= j2;
            if (i17 > i10) {
                break;
            }
            i18++;
            size = 0;
            while (size < i3) {
                View childAt3 = getChildAt(size);
                LayoutParams layoutParams3 = (LayoutParams) childAt3.getLayoutParams();
                i6 = i11;
                i7 = childMeasureSpec;
                i8 = i3;
                long j3 = (long) (1 << size);
                if ((j2 & j3) != 0) {
                    boolean z3;
                    if (obj3 == null || !layoutParams3.preventEdgeOffset) {
                        z3 = true;
                    } else {
                        z3 = true;
                        if (i10 == 1) {
                            childAt3.setPadding(r0.mGeneratedItemPadding + i9, 0, r0.mGeneratedItemPadding, 0);
                        }
                    }
                    layoutParams3.cellsUsed += z3;
                    layoutParams3.expanded = z3;
                    i10--;
                } else if (layoutParams3.cellsUsed == i18) {
                    j |= j3;
                }
                size++;
                i11 = i6;
                childMeasureSpec = i7;
                i3 = i8;
            }
            obj4 = 1;
        }
        obj = obj4;
        i7 = childMeasureSpec;
        i8 = i3;
        i6 = i11;
        LayoutParams layoutParams4;
        if (obj2 == null) {
            size = 1;
            if (i12 == 1) {
                obj3 = 1;
                if (i10 > 0 || j == 0 || (i10 >= i12 - r2 && obj3 == null && i13 <= r2)) {
                    size = i8;
                    size2 = 0;
                } else {
                    float bitCount = (float) Long.bitCount(j);
                    if (obj3 == null) {
                        if ((j & 1) != 0) {
                            size2 = 0;
                            if (!((LayoutParams) getChildAt(0).getLayoutParams()).preventEdgeOffset) {
                                bitCount -= 0.5f;
                            }
                        } else {
                            size2 = 0;
                        }
                        i3 = i8 - 1;
                        if (!((j & ((long) (1 << i3))) == 0 || ((LayoutParams) getChildAt(i3).getLayoutParams()).preventEdgeOffset)) {
                            bitCount -= 0.5f;
                        }
                    } else {
                        size2 = 0;
                    }
                    i17 = bitCount > 0.0f ? (int) (((float) (i10 * i9)) / bitCount) : 0;
                    Object obj5 = obj;
                    size = i8;
                    for (paddingLeft = 0; paddingLeft < size; paddingLeft++) {
                        if ((j & ((long) (1 << paddingLeft))) != 0) {
                            childAt = getChildAt(paddingLeft);
                            layoutParams4 = (LayoutParams) childAt.getLayoutParams();
                            if (childAt instanceof ActionMenuItemView) {
                                layoutParams4.extraPixels = i17;
                                layoutParams4.expanded = true;
                                if (paddingLeft == 0 && !layoutParams4.preventEdgeOffset) {
                                    layoutParams4.leftMargin = (-i17) / 2;
                                }
                            } else if (layoutParams4.isOverflowButton) {
                                layoutParams4.extraPixels = i17;
                                layoutParams4.expanded = true;
                                layoutParams4.rightMargin = (-i17) / 2;
                            } else {
                                if (paddingLeft != 0) {
                                    layoutParams4.leftMargin = i17 / 2;
                                }
                                if (paddingLeft != size - 1) {
                                    layoutParams4.rightMargin = i17 / 2;
                                }
                            }
                            obj5 = 1;
                        }
                    }
                    obj = obj5;
                }
                if (obj != null) {
                    while (size2 < size) {
                        childAt = getChildAt(size2);
                        layoutParams4 = (LayoutParams) childAt.getLayoutParams();
                        if (layoutParams4.expanded) {
                            i3 = i7;
                        } else {
                            i3 = i7;
                            childAt.measure(MeasureSpec.makeMeasureSpec((layoutParams4.cellsUsed * i9) + layoutParams4.extraPixels, 1073741824), i3);
                        }
                        size2++;
                        i7 = i3;
                    }
                }
                if (mode == 1073741824) {
                    size = i5;
                    mode = i6;
                } else {
                    mode = i4;
                    size = i5;
                }
                setMeasuredDimension(size, mode);
            }
        }
        size = 1;
        obj3 = null;
        if (i10 > 0) {
        }
        size = i8;
        size2 = 0;
        if (obj != null) {
            while (size2 < size) {
                childAt = getChildAt(size2);
                layoutParams4 = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams4.expanded) {
                    i3 = i7;
                    childAt.measure(MeasureSpec.makeMeasureSpec((layoutParams4.cellsUsed * i9) + layoutParams4.extraPixels, 1073741824), i3);
                } else {
                    i3 = i7;
                }
                size2++;
                i7 = i3;
            }
        }
        if (mode == 1073741824) {
            mode = i4;
            size = i5;
        } else {
            size = i5;
            mode = i6;
        }
        setMeasuredDimension(size, mode);
    }

    static int measureChildForCells(View view, int i, int i2, int i3, int i4) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        i3 = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(i3) - i4, MeasureSpec.getMode(i3));
        i4 = (view instanceof ActionMenuItemView) != 0 ? (ActionMenuItemView) view : 0;
        boolean z = true;
        i4 = (i4 == 0 || i4.hasText() == 0) ? 0 : 1;
        int i5 = 2;
        if (i2 <= 0 || (i4 != 0 && i2 < 2)) {
            i5 = 0;
        } else {
            view.measure(MeasureSpec.makeMeasureSpec(i2 * i, Integer.MIN_VALUE), i3);
            i2 = view.getMeasuredWidth();
            int i6 = i2 / i;
            if (i2 % i != 0) {
                i6++;
            }
            if (i4 == 0 || i6 >= 2) {
                i5 = i6;
            }
        }
        if (layoutParams.isOverflowButton != 0 || i4 == 0) {
            z = false;
        }
        layoutParams.expandable = z;
        layoutParams.cellsUsed = i5;
        view.measure(MeasureSpec.makeMeasureSpec(i * i5, 1073741824), i3);
        return i5;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mFormatItems) {
            int i5;
            int childCount = getChildCount();
            int i6 = (i4 - i2) / 2;
            int dividerWidth = getDividerWidth();
            int i7 = i3 - i;
            int paddingRight = (i7 - getPaddingRight()) - getPaddingLeft();
            boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
            int i8 = paddingRight;
            int i9 = 0;
            int i10 = 0;
            for (paddingRight = 0; paddingRight < childCount; paddingRight++) {
                View childAt = getChildAt(paddingRight);
                if (childAt.getVisibility() != 8) {
                    LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                    if (layoutParams.isOverflowButton) {
                        int paddingLeft;
                        int i11;
                        i9 = childAt.getMeasuredWidth();
                        if (hasSupportDividerBeforeChildAt(paddingRight)) {
                            i9 += dividerWidth;
                        }
                        int measuredHeight = childAt.getMeasuredHeight();
                        if (isLayoutRtl) {
                            paddingLeft = getPaddingLeft() + layoutParams.leftMargin;
                            i11 = paddingLeft + i9;
                        } else {
                            i11 = (getWidth() - getPaddingRight()) - layoutParams.rightMargin;
                            paddingLeft = i11 - i9;
                        }
                        i5 = i6 - (measuredHeight / 2);
                        childAt.layout(paddingLeft, i5, i11, measuredHeight + i5);
                        i8 -= i9;
                        i9 = 1;
                    } else {
                        i8 -= (childAt.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin;
                        boolean hasSupportDividerBeforeChildAt = hasSupportDividerBeforeChildAt(paddingRight);
                        i10++;
                    }
                }
            }
            if (childCount == 1 && i9 == 0) {
                View childAt2 = getChildAt(0);
                dividerWidth = childAt2.getMeasuredWidth();
                paddingRight = childAt2.getMeasuredHeight();
                i7 = (i7 / 2) - (dividerWidth / 2);
                i6 -= paddingRight / 2;
                childAt2.layout(i7, i6, dividerWidth + i7, paddingRight + i6);
                return;
            }
            i10 -= i9 ^ 1;
            if (i10 > 0) {
                i5 = i8 / i10;
                dividerWidth = 0;
            } else {
                dividerWidth = 0;
                i5 = 0;
            }
            i7 = Math.max(dividerWidth, i5);
            View childAt3;
            LayoutParams layoutParams2;
            if (isLayoutRtl) {
                paddingRight = getWidth() - getPaddingRight();
                while (dividerWidth < childCount) {
                    childAt3 = getChildAt(dividerWidth);
                    layoutParams2 = (LayoutParams) childAt3.getLayoutParams();
                    if (childAt3.getVisibility() != 8) {
                        if (!layoutParams2.isOverflowButton) {
                            paddingRight -= layoutParams2.rightMargin;
                            i9 = childAt3.getMeasuredWidth();
                            i10 = childAt3.getMeasuredHeight();
                            i8 = i6 - (i10 / 2);
                            childAt3.layout(paddingRight - i9, i8, paddingRight, i10 + i8);
                            paddingRight -= (i9 + layoutParams2.leftMargin) + i7;
                        }
                    }
                    dividerWidth++;
                }
            } else {
                paddingRight = getPaddingLeft();
                while (dividerWidth < childCount) {
                    childAt3 = getChildAt(dividerWidth);
                    layoutParams2 = (LayoutParams) childAt3.getLayoutParams();
                    if (childAt3.getVisibility() != 8) {
                        if (!layoutParams2.isOverflowButton) {
                            paddingRight += layoutParams2.leftMargin;
                            i9 = childAt3.getMeasuredWidth();
                            i10 = childAt3.getMeasuredHeight();
                            i8 = i6 - (i10 / 2);
                            childAt3.layout(paddingRight, i8, paddingRight + i9, i10 + i8);
                            paddingRight += (i9 + layoutParams2.rightMargin) + i7;
                        }
                    }
                    dividerWidth++;
                }
            }
            return;
        }
        super.onLayout(z, i, i2, i3, i4);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dismissPopupMenus();
    }

    public void setOverflowIcon(@Nullable Drawable drawable) {
        getMenu();
        this.mPresenter.setOverflowIcon(drawable);
    }

    @Nullable
    public Drawable getOverflowIcon() {
        getMenu();
        return this.mPresenter.getOverflowIcon();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public boolean isOverflowReserved() {
        return this.mReserveOverflow;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setOverflowReserved(boolean z) {
        this.mReserveOverflow = z;
    }

    protected LayoutParams generateDefaultLayoutParams() {
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 16;
        return layoutParams;
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        if (layoutParams == null) {
            return generateDefaultLayoutParams();
        }
        LayoutParams layoutParams2 = layoutParams instanceof LayoutParams ? new LayoutParams((LayoutParams) layoutParams) : new LayoutParams(layoutParams);
        if (layoutParams2.gravity <= null) {
            layoutParams2.gravity = 16;
        }
        return layoutParams2;
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return (layoutParams == null || (layoutParams instanceof LayoutParams) == null) ? null : true;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public LayoutParams generateOverflowButtonLayoutParams() {
        LayoutParams generateDefaultLayoutParams = generateDefaultLayoutParams();
        generateDefaultLayoutParams.isOverflowButton = true;
        return generateDefaultLayoutParams;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public boolean invokeItem(MenuItemImpl menuItemImpl) {
        return this.mMenu.performItemAction(menuItemImpl, 0);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void initialize(MenuBuilder menuBuilder) {
        this.mMenu = menuBuilder;
    }

    public Menu getMenu() {
        if (this.mMenu == null) {
            Context context = getContext();
            this.mMenu = new MenuBuilder(context);
            this.mMenu.setCallback(new MenuBuilderCallback());
            this.mPresenter = new ActionMenuPresenter(context);
            this.mPresenter.setReserveOverflow(true);
            this.mPresenter.setCallback(this.mActionMenuPresenterCallback != null ? this.mActionMenuPresenterCallback : new ActionMenuPresenterCallback());
            this.mMenu.addMenuPresenter(this.mPresenter, this.mPopupContext);
            this.mPresenter.setMenuView(this);
        }
        return this.mMenu;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setMenuCallbacks(Callback callback, MenuBuilder.Callback callback2) {
        this.mActionMenuPresenterCallback = callback;
        this.mMenuBuilderCallback = callback2;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public MenuBuilder peekMenu() {
        return this.mMenu;
    }

    public boolean showOverflowMenu() {
        return this.mPresenter != null && this.mPresenter.showOverflowMenu();
    }

    public boolean hideOverflowMenu() {
        return this.mPresenter != null && this.mPresenter.hideOverflowMenu();
    }

    public boolean isOverflowMenuShowing() {
        return this.mPresenter != null && this.mPresenter.isOverflowMenuShowing();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public boolean isOverflowMenuShowPending() {
        return this.mPresenter != null && this.mPresenter.isOverflowMenuShowPending();
    }

    public void dismissPopupMenus() {
        if (this.mPresenter != null) {
            this.mPresenter.dismissPopupMenus();
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    protected boolean hasSupportDividerBeforeChildAt(int i) {
        boolean z = false;
        if (i == 0) {
            return false;
        }
        View childAt = getChildAt(i - 1);
        View childAt2 = getChildAt(i);
        if (i < getChildCount() && (childAt instanceof ActionMenuChildView)) {
            z = 0 | ((ActionMenuChildView) childAt).needsDividerAfter();
        }
        if (i > 0 && (childAt2 instanceof ActionMenuChildView) != 0) {
            z |= ((ActionMenuChildView) childAt2).needsDividerBefore();
        }
        return z;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setExpandedActionViewsExclusive(boolean z) {
        this.mPresenter.setExpandedActionViewsExclusive(z);
    }
}
