package android.support.v7.view.menu;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.view.menu.MenuView.ItemView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

@RestrictTo({Scope.LIBRARY_GROUP})
public class MenuAdapter extends BaseAdapter {
    MenuBuilder mAdapterMenu;
    private int mExpandedIndex = -1;
    private boolean mForceShowIcon;
    private final LayoutInflater mInflater;
    private final int mItemLayoutRes;
    private final boolean mOverflowOnly;

    public long getItemId(int i) {
        return (long) i;
    }

    public MenuAdapter(MenuBuilder menuBuilder, LayoutInflater layoutInflater, boolean z, int i) {
        this.mOverflowOnly = z;
        this.mInflater = layoutInflater;
        this.mAdapterMenu = menuBuilder;
        this.mItemLayoutRes = i;
        findExpandedIndex();
    }

    public boolean getForceShowIcon() {
        return this.mForceShowIcon;
    }

    public void setForceShowIcon(boolean z) {
        this.mForceShowIcon = z;
    }

    public int getCount() {
        ArrayList nonActionItems = this.mOverflowOnly ? this.mAdapterMenu.getNonActionItems() : this.mAdapterMenu.getVisibleItems();
        if (this.mExpandedIndex < 0) {
            return nonActionItems.size();
        }
        return nonActionItems.size() - 1;
    }

    public MenuBuilder getAdapterMenu() {
        return this.mAdapterMenu;
    }

    public MenuItemImpl getItem(int i) {
        ArrayList nonActionItems = this.mOverflowOnly ? this.mAdapterMenu.getNonActionItems() : this.mAdapterMenu.getVisibleItems();
        if (this.mExpandedIndex >= 0 && i >= this.mExpandedIndex) {
            i++;
        }
        return (MenuItemImpl) nonActionItems.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.mInflater.inflate(this.mItemLayoutRes, viewGroup, false);
        }
        viewGroup = getItem(i).getGroupId();
        int i2 = i - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        viewGroup = (!this.mAdapterMenu.isGroupDividerEnabled() || viewGroup == (i2 >= 0 ? getItem(i2).getGroupId() : viewGroup)) ? null : true;
        listMenuItemView.setGroupDividerEnabled(viewGroup);
        ItemView itemView = (ItemView) view;
        if (this.mForceShowIcon) {
            listMenuItemView.setForceShowIcon(true);
        }
        itemView.initialize(getItem(i), 0);
        return view;
    }

    void findExpandedIndex() {
        MenuItemImpl expandedItem = this.mAdapterMenu.getExpandedItem();
        if (expandedItem != null) {
            ArrayList nonActionItems = this.mAdapterMenu.getNonActionItems();
            int size = nonActionItems.size();
            for (int i = 0; i < size; i++) {
                if (((MenuItemImpl) nonActionItems.get(i)) == expandedItem) {
                    this.mExpandedIndex = i;
                    return;
                }
            }
        }
        this.mExpandedIndex = -1;
    }

    public void notifyDataSetChanged() {
        findExpandedIndex();
        super.notifyDataSetChanged();
    }
}
