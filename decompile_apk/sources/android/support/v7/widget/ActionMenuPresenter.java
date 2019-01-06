package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.ActionProvider.SubUiVisibilityListener;
import android.support.v4.view.GravityCompat;
import android.support.v7.appcompat.C0192R;
import android.support.v7.view.ActionBarPolicy;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.ActionMenuItemView.PopupCallback;
import android.support.v7.view.menu.BaseMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.MenuPresenter.Callback;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.MenuView.ItemView;
import android.support.v7.view.menu.ShowableListMenu;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.widget.ActionMenuView.ActionMenuChildView;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;

class ActionMenuPresenter extends BaseMenuPresenter implements SubUiVisibilityListener {
    private static final String TAG = "ActionMenuPresenter";
    private final SparseBooleanArray mActionButtonGroups = new SparseBooleanArray();
    ActionButtonSubmenu mActionButtonPopup;
    private int mActionItemWidthLimit;
    private boolean mExpandedActionViewsExclusive;
    private int mMaxItems;
    private boolean mMaxItemsSet;
    private int mMinCellSize;
    int mOpenSubMenuId;
    OverflowMenuButton mOverflowButton;
    OverflowPopup mOverflowPopup;
    private Drawable mPendingOverflowIcon;
    private boolean mPendingOverflowIconSet;
    private ActionMenuPopupCallback mPopupCallback;
    final PopupPresenterCallback mPopupPresenterCallback = new PopupPresenterCallback();
    OpenOverflowRunnable mPostedOpenRunnable;
    private boolean mReserveOverflow;
    private boolean mReserveOverflowSet;
    private View mScrapActionButtonView;
    private boolean mStrictWidthLimit;
    private int mWidthLimit;
    private boolean mWidthLimitSet;

    private class OpenOverflowRunnable implements Runnable {
        private OverflowPopup mPopup;

        public OpenOverflowRunnable(OverflowPopup overflowPopup) {
            this.mPopup = overflowPopup;
        }

        public void run() {
            if (ActionMenuPresenter.this.mMenu != null) {
                ActionMenuPresenter.this.mMenu.changeMenuMode();
            }
            View view = (View) ActionMenuPresenter.this.mMenuView;
            if (!(view == null || view.getWindowToken() == null || !this.mPopup.tryShow())) {
                ActionMenuPresenter.this.mOverflowPopup = this.mPopup;
            }
            ActionMenuPresenter.this.mPostedOpenRunnable = null;
        }
    }

    private static class SavedState implements Parcelable {
        public static final Creator<SavedState> CREATOR = new C02071();
        public int openSubMenuId;

        /* renamed from: android.support.v7.widget.ActionMenuPresenter$SavedState$1 */
        static class C02071 implements Creator<SavedState> {
            C02071() {
            }

            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public int describeContents() {
            return 0;
        }

        SavedState() {
        }

        SavedState(Parcel parcel) {
            this.openSubMenuId = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.openSubMenuId);
        }
    }

    private class ActionMenuPopupCallback extends PopupCallback {
        ActionMenuPopupCallback() {
        }

        public ShowableListMenu getPopup() {
            return ActionMenuPresenter.this.mActionButtonPopup != null ? ActionMenuPresenter.this.mActionButtonPopup.getPopup() : null;
        }
    }

    private class PopupPresenterCallback implements Callback {
        PopupPresenterCallback() {
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            boolean z = false;
            if (menuBuilder == null) {
                return false;
            }
            ActionMenuPresenter.this.mOpenSubMenuId = ((SubMenuBuilder) menuBuilder).getItem().getItemId();
            Callback callback = ActionMenuPresenter.this.getCallback();
            if (callback != null) {
                z = callback.onOpenSubMenu(menuBuilder);
            }
            return z;
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            if (menuBuilder instanceof SubMenuBuilder) {
                menuBuilder.getRootMenu().close(false);
            }
            Callback callback = ActionMenuPresenter.this.getCallback();
            if (callback != null) {
                callback.onCloseMenu(menuBuilder, z);
            }
        }
    }

    private class ActionButtonSubmenu extends MenuPopupHelper {
        public ActionButtonSubmenu(Context context, SubMenuBuilder subMenuBuilder, View view) {
            super(context, subMenuBuilder, view, false, C0192R.attr.actionOverflowMenuStyle);
            if (((MenuItemImpl) subMenuBuilder.getItem()).isActionButton() == null) {
                setAnchorView(ActionMenuPresenter.this.mOverflowButton == null ? (View) ActionMenuPresenter.this.mMenuView : ActionMenuPresenter.this.mOverflowButton);
            }
            setPresenterCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
        }

        protected void onDismiss() {
            ActionMenuPresenter.this.mActionButtonPopup = null;
            ActionMenuPresenter.this.mOpenSubMenuId = 0;
            super.onDismiss();
        }
    }

    private class OverflowMenuButton extends AppCompatImageView implements ActionMenuChildView {
        private final float[] mTempPts = new float[2];

        public boolean needsDividerAfter() {
            return false;
        }

        public boolean needsDividerBefore() {
            return false;
        }

        public OverflowMenuButton(Context context) {
            super(context, null, C0192R.attr.actionOverflowButtonStyle);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            TooltipCompat.setTooltipText(this, getContentDescription());
            setOnTouchListener(new ForwardingListener(this, ActionMenuPresenter.this) {
                public ShowableListMenu getPopup() {
                    if (ActionMenuPresenter.this.mOverflowPopup == null) {
                        return null;
                    }
                    return ActionMenuPresenter.this.mOverflowPopup.getPopup();
                }

                public boolean onForwardingStarted() {
                    ActionMenuPresenter.this.showOverflowMenu();
                    return true;
                }

                public boolean onForwardingStopped() {
                    if (ActionMenuPresenter.this.mPostedOpenRunnable != null) {
                        return false;
                    }
                    ActionMenuPresenter.this.hideOverflowMenu();
                    return true;
                }
            });
        }

        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            ActionMenuPresenter.this.showOverflowMenu();
            return true;
        }

        protected boolean setFrame(int i, int i2, int i3, int i4) {
            i = super.setFrame(i, i2, i3, i4);
            i2 = getDrawable();
            i3 = getBackground();
            if (!(i2 == 0 || i3 == 0)) {
                i2 = getWidth();
                i4 = getHeight();
                int max = Math.max(i2, i4) / 2;
                i2 = (i2 + (getPaddingLeft() - getPaddingRight())) / 2;
                i4 = (i4 + (getPaddingTop() - getPaddingBottom())) / 2;
                DrawableCompat.setHotspotBounds(i3, i2 - max, i4 - max, i2 + max, i4 + max);
            }
            return i;
        }
    }

    private class OverflowPopup extends MenuPopupHelper {
        public OverflowPopup(Context context, MenuBuilder menuBuilder, View view, boolean z) {
            super(context, menuBuilder, view, z, C0192R.attr.actionOverflowMenuStyle);
            setGravity(GravityCompat.END);
            setPresenterCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
        }

        protected void onDismiss() {
            if (ActionMenuPresenter.this.mMenu != null) {
                ActionMenuPresenter.this.mMenu.close();
            }
            ActionMenuPresenter.this.mOverflowPopup = null;
            super.onDismiss();
        }
    }

    public ActionMenuPresenter(Context context) {
        super(context, C0192R.layout.abc_action_menu_layout, C0192R.layout.abc_action_menu_item_layout);
    }

    public void initForMenu(@NonNull Context context, @Nullable MenuBuilder menuBuilder) {
        super.initForMenu(context, menuBuilder);
        menuBuilder = context.getResources();
        context = ActionBarPolicy.get(context);
        if (!this.mReserveOverflowSet) {
            this.mReserveOverflow = context.showsOverflowMenuButton();
        }
        if (!this.mWidthLimitSet) {
            this.mWidthLimit = context.getEmbeddedMenuWidthLimit();
        }
        if (!this.mMaxItemsSet) {
            this.mMaxItems = context.getMaxActionButtons();
        }
        context = this.mWidthLimit;
        if (this.mReserveOverflow) {
            if (this.mOverflowButton == null) {
                this.mOverflowButton = new OverflowMenuButton(this.mSystemContext);
                if (this.mPendingOverflowIconSet) {
                    this.mOverflowButton.setImageDrawable(this.mPendingOverflowIcon);
                    this.mPendingOverflowIcon = null;
                    this.mPendingOverflowIconSet = false;
                }
                int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
                this.mOverflowButton.measure(makeMeasureSpec, makeMeasureSpec);
            }
            context -= this.mOverflowButton.getMeasuredWidth();
        } else {
            this.mOverflowButton = null;
        }
        this.mActionItemWidthLimit = context;
        this.mMinCellSize = (int) (menuBuilder.getDisplayMetrics().density * 1113587712);
        this.mScrapActionButtonView = null;
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (this.mMaxItemsSet == null) {
            this.mMaxItems = ActionBarPolicy.get(this.mContext).getMaxActionButtons();
        }
        if (this.mMenu != null) {
            this.mMenu.onItemsChanged(true);
        }
    }

    public void setWidthLimit(int i, boolean z) {
        this.mWidthLimit = i;
        this.mStrictWidthLimit = z;
        this.mWidthLimitSet = true;
    }

    public void setReserveOverflow(boolean z) {
        this.mReserveOverflow = z;
        this.mReserveOverflowSet = true;
    }

    public void setItemLimit(int i) {
        this.mMaxItems = i;
        this.mMaxItemsSet = true;
    }

    public void setExpandedActionViewsExclusive(boolean z) {
        this.mExpandedActionViewsExclusive = z;
    }

    public void setOverflowIcon(Drawable drawable) {
        if (this.mOverflowButton != null) {
            this.mOverflowButton.setImageDrawable(drawable);
            return;
        }
        this.mPendingOverflowIconSet = true;
        this.mPendingOverflowIcon = drawable;
    }

    public Drawable getOverflowIcon() {
        if (this.mOverflowButton != null) {
            return this.mOverflowButton.getDrawable();
        }
        return this.mPendingOverflowIconSet ? this.mPendingOverflowIcon : null;
    }

    public MenuView getMenuView(ViewGroup viewGroup) {
        MenuView menuView = this.mMenuView;
        MenuView menuView2 = super.getMenuView(viewGroup);
        if (menuView != menuView2) {
            ((ActionMenuView) menuView2).setPresenter(this);
        }
        return menuView2;
    }

    public View getItemView(MenuItemImpl menuItemImpl, View view, ViewGroup viewGroup) {
        View actionView = menuItemImpl.getActionView();
        if (actionView == null || menuItemImpl.hasCollapsibleActionView()) {
            actionView = super.getItemView(menuItemImpl, view, viewGroup);
        }
        actionView.setVisibility(menuItemImpl.isActionViewExpanded() != null ? 8 : null);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        LayoutParams layoutParams = actionView.getLayoutParams();
        if (actionMenuView.checkLayoutParams(layoutParams) == null) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    public void bindItemView(MenuItemImpl menuItemImpl, ItemView itemView) {
        itemView.initialize(menuItemImpl, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) itemView;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.mMenuView);
        if (this.mPopupCallback == null) {
            this.mPopupCallback = new ActionMenuPopupCallback();
        }
        actionMenuItemView.setPopupCallback(this.mPopupCallback);
    }

    public boolean shouldIncludeItem(int i, MenuItemImpl menuItemImpl) {
        return menuItemImpl.isActionButton();
    }

    public void updateMenuView(boolean z) {
        int size;
        super.updateMenuView(z);
        ((View) this.mMenuView).requestLayout();
        int i = 0;
        if (this.mMenu) {
            z = this.mMenu.getActionItems();
            size = z.size();
            for (int i2 = 0; i2 < size; i2++) {
                ActionProvider supportActionProvider = ((MenuItemImpl) z.get(i2)).getSupportActionProvider();
                if (supportActionProvider != null) {
                    supportActionProvider.setSubUiVisibilityListener(this);
                }
            }
        }
        z = this.mMenu ? this.mMenu.getNonActionItems() : false;
        if (this.mReserveOverflow && z) {
            size = z.size();
            if (size == 1) {
                i = ((MenuItemImpl) z.get(0)).isActionViewExpanded() ^ 1;
            } else if (size > 0) {
                i = 1;
            }
        }
        if (i != 0) {
            if (!this.mOverflowButton) {
                this.mOverflowButton = new OverflowMenuButton(this.mSystemContext);
            }
            ViewGroup viewGroup = (ViewGroup) this.mOverflowButton.getParent();
            if (viewGroup != this.mMenuView) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.mOverflowButton);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.mMenuView;
                actionMenuView.addView(this.mOverflowButton, actionMenuView.generateOverflowButtonLayoutParams());
            }
        } else if (this.mOverflowButton && this.mOverflowButton.getParent() == this.mMenuView) {
            ((ViewGroup) this.mMenuView).removeView(this.mOverflowButton);
        }
        ((ActionMenuView) this.mMenuView).setOverflowReserved(this.mReserveOverflow);
    }

    public boolean filterLeftoverView(ViewGroup viewGroup, int i) {
        if (viewGroup.getChildAt(i) == this.mOverflowButton) {
            return null;
        }
        return super.filterLeftoverView(viewGroup, i);
    }

    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        boolean z = false;
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        SubMenuBuilder subMenuBuilder2 = subMenuBuilder;
        while (subMenuBuilder2.getParentMenu() != this.mMenu) {
            subMenuBuilder2 = (SubMenuBuilder) subMenuBuilder2.getParentMenu();
        }
        View findViewForItem = findViewForItem(subMenuBuilder2.getItem());
        if (findViewForItem == null) {
            return false;
        }
        this.mOpenSubMenuId = subMenuBuilder.getItem().getItemId();
        int size = subMenuBuilder.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = subMenuBuilder.getItem(i);
            if (item.isVisible() && item.getIcon() != null) {
                z = true;
                break;
            }
        }
        this.mActionButtonPopup = new ActionButtonSubmenu(this.mContext, subMenuBuilder, findViewForItem);
        this.mActionButtonPopup.setForceShowIcon(z);
        this.mActionButtonPopup.show();
        super.onSubMenuSelected(subMenuBuilder);
        return true;
    }

    private View findViewForItem(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup) this.mMenuView;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if ((childAt instanceof ItemView) && ((ItemView) childAt).getItemData() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    public boolean showOverflowMenu() {
        if (!this.mReserveOverflow || isOverflowMenuShowing() || this.mMenu == null || this.mMenuView == null || this.mPostedOpenRunnable != null || this.mMenu.getNonActionItems().isEmpty()) {
            return false;
        }
        this.mPostedOpenRunnable = new OpenOverflowRunnable(new OverflowPopup(this.mContext, this.mMenu, this.mOverflowButton, true));
        ((View) this.mMenuView).post(this.mPostedOpenRunnable);
        super.onSubMenuSelected(null);
        return true;
    }

    public boolean hideOverflowMenu() {
        if (this.mPostedOpenRunnable == null || this.mMenuView == null) {
            MenuPopupHelper menuPopupHelper = this.mOverflowPopup;
            if (menuPopupHelper == null) {
                return false;
            }
            menuPopupHelper.dismiss();
            return true;
        }
        ((View) this.mMenuView).removeCallbacks(this.mPostedOpenRunnable);
        this.mPostedOpenRunnable = null;
        return true;
    }

    public boolean dismissPopupMenus() {
        return hideOverflowMenu() | hideSubMenus();
    }

    public boolean hideSubMenus() {
        if (this.mActionButtonPopup == null) {
            return false;
        }
        this.mActionButtonPopup.dismiss();
        return true;
    }

    public boolean isOverflowMenuShowing() {
        return this.mOverflowPopup != null && this.mOverflowPopup.isShowing();
    }

    public boolean isOverflowMenuShowPending() {
        if (this.mPostedOpenRunnable == null) {
            if (!isOverflowMenuShowing()) {
                return false;
            }
        }
        return true;
    }

    public boolean isOverflowReserved() {
        return this.mReserveOverflow;
    }

    public boolean flagActionItems() {
        ArrayList visibleItems;
        ActionMenuPresenter actionMenuPresenter;
        int size;
        int i;
        int i2 = 0;
        if (this.mMenu != null) {
            visibleItems = actionMenuPresenter.mMenu.getVisibleItems();
            size = visibleItems.size();
        } else {
            visibleItems = null;
            size = 0;
        }
        int i3 = actionMenuPresenter.mMaxItems;
        int i4 = actionMenuPresenter.mActionItemWidthLimit;
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) actionMenuPresenter.mMenuView;
        int i5 = i3;
        int i6 = 0;
        Object obj = null;
        int i7 = 0;
        for (i3 = 0; i3 < size; i3++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) visibleItems.get(i3);
            if (menuItemImpl.requiresActionButton()) {
                i6++;
            } else if (menuItemImpl.requestsActionButton()) {
                i7++;
            } else {
                obj = 1;
            }
            if (actionMenuPresenter.mExpandedActionViewsExclusive && menuItemImpl.isActionViewExpanded()) {
                i5 = 0;
            }
        }
        if (actionMenuPresenter.mReserveOverflow && (r9 != null || i7 + i6 > i5)) {
            i5--;
        }
        i5 -= i6;
        SparseBooleanArray sparseBooleanArray = actionMenuPresenter.mActionButtonGroups;
        sparseBooleanArray.clear();
        if (actionMenuPresenter.mStrictWidthLimit) {
            i6 = i4 / actionMenuPresenter.mMinCellSize;
            i = ((i4 % actionMenuPresenter.mMinCellSize) / i6) + actionMenuPresenter.mMinCellSize;
        } else {
            i6 = 0;
            i = 0;
        }
        i7 = i4;
        i4 = 0;
        int i8 = 0;
        while (i4 < size) {
            int i9;
            MenuItemImpl menuItemImpl2 = (MenuItemImpl) visibleItems.get(i4);
            int measuredWidth;
            boolean z;
            if (menuItemImpl2.requiresActionButton()) {
                View itemView = actionMenuPresenter.getItemView(menuItemImpl2, actionMenuPresenter.mScrapActionButtonView, viewGroup);
                if (actionMenuPresenter.mScrapActionButtonView == null) {
                    actionMenuPresenter.mScrapActionButtonView = itemView;
                }
                if (actionMenuPresenter.mStrictWidthLimit) {
                    i6 -= ActionMenuView.measureChildForCells(itemView, i, i6, makeMeasureSpec, i2);
                } else {
                    itemView.measure(makeMeasureSpec, makeMeasureSpec);
                }
                measuredWidth = itemView.getMeasuredWidth();
                i7 -= measuredWidth;
                if (i8 != 0) {
                    measuredWidth = i8;
                }
                i8 = menuItemImpl2.getGroupId();
                if (i8 != 0) {
                    z = true;
                    sparseBooleanArray.put(i8, true);
                } else {
                    z = true;
                }
                menuItemImpl2.setIsActionButton(z);
                i9 = size;
                i8 = measuredWidth;
            } else if (menuItemImpl2.requestsActionButton()) {
                measuredWidth = menuItemImpl2.getGroupId();
                z = sparseBooleanArray.get(measuredWidth);
                boolean z2 = (i5 > 0 || z) && i7 > 0 && (!actionMenuPresenter.mStrictWidthLimit || i6 > 0);
                if (z2) {
                    int i10 = z2;
                    View itemView2 = actionMenuPresenter.getItemView(menuItemImpl2, actionMenuPresenter.mScrapActionButtonView, viewGroup);
                    i9 = size;
                    if (actionMenuPresenter.mScrapActionButtonView == null) {
                        actionMenuPresenter.mScrapActionButtonView = itemView2;
                    }
                    if (actionMenuPresenter.mStrictWidthLimit) {
                        int measureChildForCells = ActionMenuView.measureChildForCells(itemView2, i, i6, makeMeasureSpec, 0);
                        i6 -= measureChildForCells;
                        if (measureChildForCells == 0) {
                            i10 = 0;
                        }
                    } else {
                        itemView2.measure(makeMeasureSpec, makeMeasureSpec);
                    }
                    i2 = itemView2.getMeasuredWidth();
                    i7 -= i2;
                    if (i8 == 0) {
                        i8 = i2;
                    }
                    if (actionMenuPresenter.mStrictWidthLimit) {
                        z2 = i10 & (i7 >= 0 ? 1 : 0);
                    } else {
                        z2 = i10 & (i7 + i8 > 0 ? 1 : 0);
                    }
                } else {
                    boolean z3 = z2;
                    i9 = size;
                }
                if (z2 && measuredWidth != 0) {
                    sparseBooleanArray.put(measuredWidth, true);
                } else if (z) {
                    sparseBooleanArray.put(measuredWidth, false);
                    size = 0;
                    while (size < i4) {
                        MenuItemImpl menuItemImpl3 = (MenuItemImpl) visibleItems.get(size);
                        if (menuItemImpl3.getGroupId() == measuredWidth) {
                            if (menuItemImpl3.isActionButton()) {
                                i5++;
                            }
                            menuItemImpl3.setIsActionButton(false);
                        }
                        size++;
                        actionMenuPresenter = this;
                    }
                }
                if (z2) {
                    i5--;
                }
                menuItemImpl2.setIsActionButton(z2);
            } else {
                i9 = size;
                menuItemImpl2.setIsActionButton(false);
                i4++;
                size = i9;
                actionMenuPresenter = this;
                i2 = 0;
            }
            i4++;
            size = i9;
            actionMenuPresenter = this;
            i2 = 0;
        }
        return true;
    }

    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        dismissPopupMenus();
        super.onCloseMenu(menuBuilder, z);
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState();
        savedState.openSubMenuId = this.mOpenSubMenuId;
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            if (savedState.openSubMenuId > 0) {
                parcelable = this.mMenu.findItem(savedState.openSubMenuId);
                if (parcelable != null) {
                    onSubMenuSelected((SubMenuBuilder) parcelable.getSubMenu());
                }
            }
        }
    }

    public void onSubUiVisibilityChanged(boolean z) {
        if (z) {
            super.onSubMenuSelected(false);
        } else if (this.mMenu) {
            this.mMenu.close(false);
        }
    }

    public void setMenuView(ActionMenuView actionMenuView) {
        this.mMenuView = actionMenuView;
        actionMenuView.initialize(this.mMenu);
    }
}
