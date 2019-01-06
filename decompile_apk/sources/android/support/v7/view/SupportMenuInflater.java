package android.support.v7.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff.Mode;
import android.support.annotation.LayoutRes;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.appcompat.C0192R;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuItemWrapperICS;
import android.support.v7.widget.DrawableUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import java.io.IOException;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({Scope.LIBRARY_GROUP})
public class SupportMenuInflater extends MenuInflater {
    static final Class<?>[] ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE = ACTION_VIEW_CONSTRUCTOR_SIGNATURE;
    static final Class<?>[] ACTION_VIEW_CONSTRUCTOR_SIGNATURE = new Class[]{Context.class};
    static final String LOG_TAG = "SupportMenuInflater";
    static final int NO_ID = 0;
    private static final String XML_GROUP = "group";
    private static final String XML_ITEM = "item";
    private static final String XML_MENU = "menu";
    final Object[] mActionProviderConstructorArguments = this.mActionViewConstructorArguments;
    final Object[] mActionViewConstructorArguments;
    Context mContext;
    private Object mRealOwner;

    private static class InflatedOnMenuItemClickListener implements OnMenuItemClickListener {
        private static final Class<?>[] PARAM_TYPES = new Class[]{MenuItem.class};
        private Method mMethod;
        private Object mRealOwner;

        public InflatedOnMenuItemClickListener(Object obj, String str) {
            this.mRealOwner = obj;
            obj = obj.getClass();
            try {
                this.mMethod = obj.getMethod(str, PARAM_TYPES);
            } catch (Throwable e) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Couldn't resolve menu item onClick handler ");
                stringBuilder.append(str);
                stringBuilder.append(" in class ");
                stringBuilder.append(obj.getName());
                InflateException inflateException = new InflateException(stringBuilder.toString());
                inflateException.initCause(e);
                throw inflateException;
            }
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.mMethod.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.mMethod.invoke(this.mRealOwner, new Object[]{menuItem})).booleanValue();
                }
                this.mMethod.invoke(this.mRealOwner, new Object[]{menuItem});
                return true;
            } catch (MenuItem menuItem2) {
                throw new RuntimeException(menuItem2);
            }
        }
    }

    private class MenuState {
        private static final int defaultGroupId = 0;
        private static final int defaultItemCategory = 0;
        private static final int defaultItemCheckable = 0;
        private static final boolean defaultItemChecked = false;
        private static final boolean defaultItemEnabled = true;
        private static final int defaultItemId = 0;
        private static final int defaultItemOrder = 0;
        private static final boolean defaultItemVisible = true;
        private int groupCategory;
        private int groupCheckable;
        private boolean groupEnabled;
        private int groupId;
        private int groupOrder;
        private boolean groupVisible;
        ActionProvider itemActionProvider;
        private String itemActionProviderClassName;
        private String itemActionViewClassName;
        private int itemActionViewLayout;
        private boolean itemAdded;
        private int itemAlphabeticModifiers;
        private char itemAlphabeticShortcut;
        private int itemCategoryOrder;
        private int itemCheckable;
        private boolean itemChecked;
        private CharSequence itemContentDescription;
        private boolean itemEnabled;
        private int itemIconResId;
        private ColorStateList itemIconTintList = null;
        private Mode itemIconTintMode = null;
        private int itemId;
        private String itemListenerMethodName;
        private int itemNumericModifiers;
        private char itemNumericShortcut;
        private int itemShowAsAction;
        private CharSequence itemTitle;
        private CharSequence itemTitleCondensed;
        private CharSequence itemTooltipText;
        private boolean itemVisible;
        private Menu menu;

        public MenuState(Menu menu) {
            this.menu = menu;
            resetGroup();
        }

        public void resetGroup() {
            this.groupId = 0;
            this.groupCategory = 0;
            this.groupOrder = 0;
            this.groupCheckable = 0;
            this.groupVisible = true;
            this.groupEnabled = true;
        }

        public void readGroup(AttributeSet attributeSet) {
            attributeSet = SupportMenuInflater.this.mContext.obtainStyledAttributes(attributeSet, C0192R.styleable.MenuGroup);
            this.groupId = attributeSet.getResourceId(C0192R.styleable.MenuGroup_android_id, 0);
            this.groupCategory = attributeSet.getInt(C0192R.styleable.MenuGroup_android_menuCategory, 0);
            this.groupOrder = attributeSet.getInt(C0192R.styleable.MenuGroup_android_orderInCategory, 0);
            this.groupCheckable = attributeSet.getInt(C0192R.styleable.MenuGroup_android_checkableBehavior, 0);
            this.groupVisible = attributeSet.getBoolean(C0192R.styleable.MenuGroup_android_visible, true);
            this.groupEnabled = attributeSet.getBoolean(C0192R.styleable.MenuGroup_android_enabled, true);
            attributeSet.recycle();
        }

        public void readItem(AttributeSet attributeSet) {
            attributeSet = SupportMenuInflater.this.mContext.obtainStyledAttributes(attributeSet, C0192R.styleable.MenuItem);
            this.itemId = attributeSet.getResourceId(C0192R.styleable.MenuItem_android_id, 0);
            this.itemCategoryOrder = (attributeSet.getInt(C0192R.styleable.MenuItem_android_menuCategory, this.groupCategory) & SupportMenu.CATEGORY_MASK) | (attributeSet.getInt(C0192R.styleable.MenuItem_android_orderInCategory, this.groupOrder) & SupportMenu.USER_MASK);
            this.itemTitle = attributeSet.getText(C0192R.styleable.MenuItem_android_title);
            this.itemTitleCondensed = attributeSet.getText(C0192R.styleable.MenuItem_android_titleCondensed);
            this.itemIconResId = attributeSet.getResourceId(C0192R.styleable.MenuItem_android_icon, 0);
            this.itemAlphabeticShortcut = getShortcut(attributeSet.getString(C0192R.styleable.MenuItem_android_alphabeticShortcut));
            this.itemAlphabeticModifiers = attributeSet.getInt(C0192R.styleable.MenuItem_alphabeticModifiers, 4096);
            this.itemNumericShortcut = getShortcut(attributeSet.getString(C0192R.styleable.MenuItem_android_numericShortcut));
            this.itemNumericModifiers = attributeSet.getInt(C0192R.styleable.MenuItem_numericModifiers, 4096);
            if (attributeSet.hasValue(C0192R.styleable.MenuItem_android_checkable)) {
                this.itemCheckable = attributeSet.getBoolean(C0192R.styleable.MenuItem_android_checkable, false);
            } else {
                this.itemCheckable = this.groupCheckable;
            }
            this.itemChecked = attributeSet.getBoolean(C0192R.styleable.MenuItem_android_checked, false);
            this.itemVisible = attributeSet.getBoolean(C0192R.styleable.MenuItem_android_visible, this.groupVisible);
            this.itemEnabled = attributeSet.getBoolean(C0192R.styleable.MenuItem_android_enabled, this.groupEnabled);
            this.itemShowAsAction = attributeSet.getInt(C0192R.styleable.MenuItem_showAsAction, -1);
            this.itemListenerMethodName = attributeSet.getString(C0192R.styleable.MenuItem_android_onClick);
            this.itemActionViewLayout = attributeSet.getResourceId(C0192R.styleable.MenuItem_actionLayout, 0);
            this.itemActionViewClassName = attributeSet.getString(C0192R.styleable.MenuItem_actionViewClass);
            this.itemActionProviderClassName = attributeSet.getString(C0192R.styleable.MenuItem_actionProviderClass);
            Object obj = this.itemActionProviderClassName != null ? 1 : null;
            if (obj != null && this.itemActionViewLayout == 0 && this.itemActionViewClassName == null) {
                this.itemActionProvider = (ActionProvider) newInstance(this.itemActionProviderClassName, SupportMenuInflater.ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE, SupportMenuInflater.this.mActionProviderConstructorArguments);
            } else {
                if (obj != null) {
                    Log.w(SupportMenuInflater.LOG_TAG, "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.itemActionProvider = null;
            }
            this.itemContentDescription = attributeSet.getText(C0192R.styleable.MenuItem_contentDescription);
            this.itemTooltipText = attributeSet.getText(C0192R.styleable.MenuItem_tooltipText);
            if (attributeSet.hasValue(C0192R.styleable.MenuItem_iconTintMode)) {
                this.itemIconTintMode = DrawableUtils.parseTintMode(attributeSet.getInt(C0192R.styleable.MenuItem_iconTintMode, -1), this.itemIconTintMode);
            } else {
                this.itemIconTintMode = null;
            }
            if (attributeSet.hasValue(C0192R.styleable.MenuItem_iconTint)) {
                this.itemIconTintList = attributeSet.getColorStateList(C0192R.styleable.MenuItem_iconTint);
            } else {
                this.itemIconTintList = null;
            }
            attributeSet.recycle();
            this.itemAdded = false;
        }

        private char getShortcut(String str) {
            return str == null ? '\u0000' : str.charAt(0);
        }

        private void setItem(MenuItem menuItem) {
            Object obj = null;
            menuItem.setChecked(this.itemChecked).setVisible(this.itemVisible).setEnabled(this.itemEnabled).setCheckable(this.itemCheckable >= 1).setTitleCondensed(this.itemTitleCondensed).setIcon(this.itemIconResId);
            if (this.itemShowAsAction >= 0) {
                menuItem.setShowAsAction(this.itemShowAsAction);
            }
            if (this.itemListenerMethodName != null) {
                if (SupportMenuInflater.this.mContext.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                menuItem.setOnMenuItemClickListener(new InflatedOnMenuItemClickListener(SupportMenuInflater.this.getRealOwner(), this.itemListenerMethodName));
            }
            boolean z = menuItem instanceof MenuItemImpl;
            if (z) {
                MenuItemImpl menuItemImpl = (MenuItemImpl) menuItem;
            }
            if (this.itemCheckable >= 2) {
                if (z) {
                    ((MenuItemImpl) menuItem).setExclusiveCheckable(true);
                } else if (menuItem instanceof MenuItemWrapperICS) {
                    ((MenuItemWrapperICS) menuItem).setExclusiveCheckable(true);
                }
            }
            if (this.itemActionViewClassName != null) {
                menuItem.setActionView((View) newInstance(this.itemActionViewClassName, SupportMenuInflater.ACTION_VIEW_CONSTRUCTOR_SIGNATURE, SupportMenuInflater.this.mActionViewConstructorArguments));
                obj = 1;
            }
            if (this.itemActionViewLayout > 0) {
                if (obj == null) {
                    menuItem.setActionView(this.itemActionViewLayout);
                } else {
                    Log.w(SupportMenuInflater.LOG_TAG, "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
                }
            }
            if (this.itemActionProvider != null) {
                MenuItemCompat.setActionProvider(menuItem, this.itemActionProvider);
            }
            MenuItemCompat.setContentDescription(menuItem, this.itemContentDescription);
            MenuItemCompat.setTooltipText(menuItem, this.itemTooltipText);
            MenuItemCompat.setAlphabeticShortcut(menuItem, this.itemAlphabeticShortcut, this.itemAlphabeticModifiers);
            MenuItemCompat.setNumericShortcut(menuItem, this.itemNumericShortcut, this.itemNumericModifiers);
            if (this.itemIconTintMode != null) {
                MenuItemCompat.setIconTintMode(menuItem, this.itemIconTintMode);
            }
            if (this.itemIconTintList != null) {
                MenuItemCompat.setIconTintList(menuItem, this.itemIconTintList);
            }
        }

        public void addItem() {
            this.itemAdded = true;
            setItem(this.menu.add(this.groupId, this.itemId, this.itemCategoryOrder, this.itemTitle));
        }

        public SubMenu addSubMenuItem() {
            this.itemAdded = true;
            SubMenu addSubMenu = this.menu.addSubMenu(this.groupId, this.itemId, this.itemCategoryOrder, this.itemTitle);
            setItem(addSubMenu.getItem());
            return addSubMenu;
        }

        public boolean hasAddedItem() {
            return this.itemAdded;
        }

        private <T> T newInstance(String str, Class<?>[] clsArr, Object[] objArr) {
            try {
                clsArr = SupportMenuInflater.this.mContext.getClassLoader().loadClass(str).getConstructor(clsArr);
                clsArr.setAccessible(true);
                return clsArr.newInstance(objArr);
            } catch (Class<?>[] clsArr2) {
                objArr = SupportMenuInflater.LOG_TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Cannot instantiate class: ");
                stringBuilder.append(str);
                Log.w(objArr, stringBuilder.toString(), clsArr2);
                return null;
            }
        }
    }

    public SupportMenuInflater(Context context) {
        super(context);
        this.mContext = context;
        this.mActionViewConstructorArguments = new Object[]{context};
    }

    public void inflate(@LayoutRes int i, Menu menu) {
        if (menu instanceof SupportMenu) {
            XmlResourceParser xmlResourceParser = null;
            try {
                i = this.mContext.getResources().getLayout(i);
                try {
                    parseMenu(i, Xml.asAttributeSet(i), menu);
                    if (i != 0) {
                        i.close();
                    }
                    return;
                } catch (XmlPullParserException e) {
                    menu = e;
                    Object obj = i;
                    throw new InflateException("Error inflating menu XML", menu);
                } catch (IOException e2) {
                    menu = e2;
                    xmlResourceParser = i;
                    throw new InflateException("Error inflating menu XML", menu);
                } catch (Throwable th) {
                    menu = th;
                    xmlResourceParser = i;
                    if (xmlResourceParser != null) {
                        xmlResourceParser.close();
                    }
                    throw menu;
                }
            } catch (XmlPullParserException e3) {
                menu = e3;
                throw new InflateException("Error inflating menu XML", menu);
            } catch (IOException e4) {
                menu = e4;
                throw new InflateException("Error inflating menu XML", menu);
            } catch (Throwable th2) {
                menu = th2;
                if (xmlResourceParser != null) {
                    xmlResourceParser.close();
                }
                throw menu;
            }
        }
        super.inflate(i, menu);
    }

    private void parseMenu(XmlPullParser xmlPullParser, AttributeSet attributeSet, Menu menu) throws XmlPullParserException, IOException {
        MenuState menuState = new MenuState(menu);
        menu = xmlPullParser.getEventType();
        while (menu != 2) {
            menu = xmlPullParser.next();
            if (menu == 1) {
                break;
            }
        }
        menu = xmlPullParser.getName();
        if (menu.equals(XML_MENU)) {
            menu = xmlPullParser.next();
            int i = menu;
            Object obj = null;
            menu = null;
            Object obj2 = null;
            while (menu == null) {
                String name;
                switch (i) {
                    case 1:
                        throw new RuntimeException("Unexpected end of document");
                    case 2:
                        if (obj2 == null) {
                            name = xmlPullParser.getName();
                            if (!name.equals(XML_GROUP)) {
                                if (!name.equals(XML_ITEM)) {
                                    if (!name.equals(XML_MENU)) {
                                        obj = name;
                                        obj2 = 1;
                                        break;
                                    }
                                    parseMenu(xmlPullParser, attributeSet, menuState.addSubMenuItem());
                                    break;
                                }
                                menuState.readItem(attributeSet);
                                break;
                            }
                            menuState.readGroup(attributeSet);
                            break;
                        }
                        break;
                    case 3:
                        name = xmlPullParser.getName();
                        if (obj2 == null || !name.equals(r6)) {
                            if (!name.equals(XML_GROUP)) {
                                if (!name.equals(XML_ITEM)) {
                                    if (!name.equals(XML_MENU)) {
                                        break;
                                    }
                                    menu = true;
                                    break;
                                } else if (!menuState.hasAddedItem()) {
                                    if (menuState.itemActionProvider != null && menuState.itemActionProvider.hasSubMenu()) {
                                        menuState.addSubMenuItem();
                                        break;
                                    } else {
                                        menuState.addItem();
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            }
                            menuState.resetGroup();
                            break;
                        }
                        obj = null;
                        obj2 = null;
                        break;
                    default:
                        break;
                }
                i = xmlPullParser.next();
            }
            return;
        }
        attributeSet = new StringBuilder();
        attributeSet.append("Expecting menu, got ");
        attributeSet.append(menu);
        throw new RuntimeException(attributeSet.toString());
    }

    Object getRealOwner() {
        if (this.mRealOwner == null) {
            this.mRealOwner = findRealOwner(this.mContext);
        }
        return this.mRealOwner;
    }

    private Object findRealOwner(Object obj) {
        return (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) ? findRealOwner(((ContextWrapper) obj).getBaseContext()) : obj;
    }
}
