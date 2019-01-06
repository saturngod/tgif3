package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.VisibleForTesting;
import android.support.v4.view.KeyEventDispatcher;
import android.support.v4.view.KeyEventDispatcher.Component;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.app.ActionBarDrawerToggle.Delegate;
import android.support.v7.appcompat.C0192R;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.view.SupportActionModeWrapper.CallbackWrapper;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.WindowCallbackWrapper;
import android.support.v7.view.menu.ListMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.ContentFrameLayout.OnAttachListener;
import android.support.v7.widget.DecorContentParent;
import android.support.v7.widget.FitWindowsViewGroup;
import android.support.v7.widget.FitWindowsViewGroup.OnFitSystemWindowsListener;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.VectorEnabledTintResources;
import android.support.v7.widget.ViewUtils;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory2;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

class AppCompatDelegateImpl extends AppCompatDelegate implements Callback, Factory2 {
    private static final boolean DEBUG = false;
    static final String EXCEPTION_HANDLER_MESSAGE_SUFFIX = ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.";
    private static final boolean IS_PRE_LOLLIPOP = (VERSION.SDK_INT < 21);
    private static final String KEY_LOCAL_NIGHT_MODE = "appcompat:local_night_mode";
    private static boolean sInstalledExceptionHandler = true;
    private static final int[] sWindowBackgroundStyleable = new int[]{16842836};
    ActionBar mActionBar;
    private ActionMenuPresenterCallback mActionMenuPresenterCallback;
    ActionMode mActionMode;
    PopupWindow mActionModePopup;
    ActionBarContextView mActionModeView;
    final AppCompatCallback mAppCompatCallback;
    private AppCompatViewInflater mAppCompatViewInflater;
    final Window.Callback mAppCompatWindowCallback;
    private boolean mApplyDayNightCalled;
    private AutoNightModeManager mAutoNightModeManager;
    private boolean mClosingActionMenu;
    final Context mContext;
    private DecorContentParent mDecorContentParent;
    private boolean mEnableDefaultActionBarUp;
    ViewPropertyAnimatorCompat mFadeAnim = null;
    private boolean mFeatureIndeterminateProgress;
    private boolean mFeatureProgress;
    private boolean mHandleNativeActionModes = true;
    boolean mHasActionBar;
    int mInvalidatePanelMenuFeatures;
    boolean mInvalidatePanelMenuPosted;
    private final Runnable mInvalidatePanelMenuRunnable = new C01872();
    boolean mIsDestroyed;
    boolean mIsFloating;
    private int mLocalNightMode = -100;
    private boolean mLongPressBackDown;
    MenuInflater mMenuInflater;
    final Window.Callback mOriginalWindowCallback;
    boolean mOverlayActionBar;
    boolean mOverlayActionMode;
    private PanelMenuPresenterCallback mPanelMenuPresenterCallback;
    private PanelFeatureState[] mPanels;
    private PanelFeatureState mPreparedPanel;
    Runnable mShowActionModePopup;
    private View mStatusGuard;
    private ViewGroup mSubDecor;
    private boolean mSubDecorInstalled;
    private Rect mTempRect1;
    private Rect mTempRect2;
    private CharSequence mTitle;
    private TextView mTitleView;
    final Window mWindow;
    boolean mWindowNoTitle;

    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$2 */
    class C01872 implements Runnable {
        C01872() {
        }

        public void run() {
            if ((AppCompatDelegateImpl.this.mInvalidatePanelMenuFeatures & 1) != 0) {
                AppCompatDelegateImpl.this.doInvalidatePanelMenu(0);
            }
            if ((AppCompatDelegateImpl.this.mInvalidatePanelMenuFeatures & 4096) != 0) {
                AppCompatDelegateImpl.this.doInvalidatePanelMenu(108);
            }
            AppCompatDelegateImpl.this.mInvalidatePanelMenuPosted = false;
            AppCompatDelegateImpl.this.mInvalidatePanelMenuFeatures = 0;
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$6 */
    class C01886 implements Runnable {

        /* renamed from: android.support.v7.app.AppCompatDelegateImpl$6$1 */
        class C03241 extends ViewPropertyAnimatorListenerAdapter {
            C03241() {
            }

            public void onAnimationStart(View view) {
                AppCompatDelegateImpl.this.mActionModeView.setVisibility(0);
            }

            public void onAnimationEnd(View view) {
                AppCompatDelegateImpl.this.mActionModeView.setAlpha(1.0f);
                AppCompatDelegateImpl.this.mFadeAnim.setListener(null);
                AppCompatDelegateImpl.this.mFadeAnim = null;
            }
        }

        C01886() {
        }

        public void run() {
            AppCompatDelegateImpl.this.mActionModePopup.showAtLocation(AppCompatDelegateImpl.this.mActionModeView, 55, 0, 0);
            AppCompatDelegateImpl.this.endOnGoingFadeAnimation();
            if (AppCompatDelegateImpl.this.shouldAnimateActionModeView()) {
                AppCompatDelegateImpl.this.mActionModeView.setAlpha(0.0f);
                AppCompatDelegateImpl.this.mFadeAnim = ViewCompat.animate(AppCompatDelegateImpl.this.mActionModeView).alpha(1.0f);
                AppCompatDelegateImpl.this.mFadeAnim.setListener(new C03241());
                return;
            }
            AppCompatDelegateImpl.this.mActionModeView.setAlpha(1.0f);
            AppCompatDelegateImpl.this.mActionModeView.setVisibility(0);
        }
    }

    @VisibleForTesting
    final class AutoNightModeManager {
        private BroadcastReceiver mAutoTimeChangeReceiver;
        private IntentFilter mAutoTimeChangeReceiverFilter;
        private boolean mIsNight;
        private TwilightManager mTwilightManager;

        /* renamed from: android.support.v7.app.AppCompatDelegateImpl$AutoNightModeManager$1 */
        class C01891 extends BroadcastReceiver {
            C01891() {
            }

            public void onReceive(Context context, Intent intent) {
                AutoNightModeManager.this.dispatchTimeChanged();
            }
        }

        AutoNightModeManager(@NonNull TwilightManager twilightManager) {
            this.mTwilightManager = twilightManager;
            this.mIsNight = twilightManager.isNight();
        }

        int getApplyableNightMode() {
            this.mIsNight = this.mTwilightManager.isNight();
            return this.mIsNight ? 2 : 1;
        }

        void dispatchTimeChanged() {
            boolean isNight = this.mTwilightManager.isNight();
            if (isNight != this.mIsNight) {
                this.mIsNight = isNight;
                AppCompatDelegateImpl.this.applyDayNight();
            }
        }

        void setup() {
            cleanup();
            if (this.mAutoTimeChangeReceiver == null) {
                this.mAutoTimeChangeReceiver = new C01891();
            }
            if (this.mAutoTimeChangeReceiverFilter == null) {
                this.mAutoTimeChangeReceiverFilter = new IntentFilter();
                this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIME_SET");
                this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
                this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIME_TICK");
            }
            AppCompatDelegateImpl.this.mContext.registerReceiver(this.mAutoTimeChangeReceiver, this.mAutoTimeChangeReceiverFilter);
        }

        void cleanup() {
            if (this.mAutoTimeChangeReceiver != null) {
                AppCompatDelegateImpl.this.mContext.unregisterReceiver(this.mAutoTimeChangeReceiver);
                this.mAutoTimeChangeReceiver = null;
            }
        }
    }

    protected static final class PanelFeatureState {
        int background;
        View createdPanelView;
        ViewGroup decorView;
        int featureId;
        Bundle frozenActionViewState;
        Bundle frozenMenuState;
        int gravity;
        boolean isHandled;
        boolean isOpen;
        boolean isPrepared;
        ListMenuPresenter listMenuPresenter;
        Context listPresenterContext;
        MenuBuilder menu;
        public boolean qwertyMode;
        boolean refreshDecorView = false;
        boolean refreshMenuContent;
        View shownPanelView;
        boolean wasLastOpen;
        int windowAnimations;
        /* renamed from: x */
        int f7x;
        /* renamed from: y */
        int f8y;

        private static class SavedState implements Parcelable {
            public static final Creator<SavedState> CREATOR = new C01901();
            int featureId;
            boolean isOpen;
            Bundle menuState;

            /* renamed from: android.support.v7.app.AppCompatDelegateImpl$PanelFeatureState$SavedState$1 */
            static class C01901 implements ClassLoaderCreator<SavedState> {
                C01901() {
                }

                public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                    return SavedState.readFromParcel(parcel, classLoader);
                }

                public SavedState createFromParcel(Parcel parcel) {
                    return SavedState.readFromParcel(parcel, null);
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

            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.featureId);
                parcel.writeInt(this.isOpen);
                if (this.isOpen != 0) {
                    parcel.writeBundle(this.menuState);
                }
            }

            static SavedState readFromParcel(Parcel parcel, ClassLoader classLoader) {
                SavedState savedState = new SavedState();
                savedState.featureId = parcel.readInt();
                boolean z = true;
                if (parcel.readInt() != 1) {
                    z = false;
                }
                savedState.isOpen = z;
                if (savedState.isOpen) {
                    savedState.menuState = parcel.readBundle(classLoader);
                }
                return savedState;
            }
        }

        PanelFeatureState(int i) {
            this.featureId = i;
        }

        public boolean hasPanelItems() {
            boolean z = false;
            if (this.shownPanelView == null) {
                return false;
            }
            if (this.createdPanelView != null) {
                return true;
            }
            if (this.listMenuPresenter.getAdapter().getCount() > 0) {
                z = true;
            }
            return z;
        }

        public void clearMenuPresenters() {
            if (this.menu != null) {
                this.menu.removeMenuPresenter(this.listMenuPresenter);
            }
            this.listMenuPresenter = null;
        }

        void setStyle(Context context) {
            TypedValue typedValue = new TypedValue();
            Theme newTheme = context.getResources().newTheme();
            newTheme.setTo(context.getTheme());
            newTheme.resolveAttribute(C0192R.attr.actionBarPopupTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                newTheme.applyStyle(typedValue.resourceId, true);
            }
            newTheme.resolveAttribute(C0192R.attr.panelMenuListTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                newTheme.applyStyle(typedValue.resourceId, true);
            } else {
                newTheme.applyStyle(C0192R.style.Theme_AppCompat_CompactMenu, true);
            }
            Context contextThemeWrapper = new ContextThemeWrapper(context, 0);
            contextThemeWrapper.getTheme().setTo(newTheme);
            this.listPresenterContext = contextThemeWrapper;
            context = contextThemeWrapper.obtainStyledAttributes(C0192R.styleable.AppCompatTheme);
            this.background = context.getResourceId(C0192R.styleable.AppCompatTheme_panelBackground, 0);
            this.windowAnimations = context.getResourceId(C0192R.styleable.AppCompatTheme_android_windowAnimationStyle, 0);
            context.recycle();
        }

        void setMenu(MenuBuilder menuBuilder) {
            if (menuBuilder != this.menu) {
                if (this.menu != null) {
                    this.menu.removeMenuPresenter(this.listMenuPresenter);
                }
                this.menu = menuBuilder;
                if (!(menuBuilder == null || this.listMenuPresenter == null)) {
                    menuBuilder.addMenuPresenter(this.listMenuPresenter);
                }
            }
        }

        MenuView getListMenuView(MenuPresenter.Callback callback) {
            if (this.menu == null) {
                return null;
            }
            if (this.listMenuPresenter == null) {
                this.listMenuPresenter = new ListMenuPresenter(this.listPresenterContext, C0192R.layout.abc_list_menu_item_layout);
                this.listMenuPresenter.setCallback(callback);
                this.menu.addMenuPresenter(this.listMenuPresenter);
            }
            return this.listMenuPresenter.getMenuView(this.decorView);
        }

        Parcelable onSaveInstanceState() {
            Parcelable savedState = new SavedState();
            savedState.featureId = this.featureId;
            savedState.isOpen = this.isOpen;
            if (this.menu != null) {
                savedState.menuState = new Bundle();
                this.menu.savePresenterStates(savedState.menuState);
            }
            return savedState;
        }

        void onRestoreInstanceState(Parcelable parcelable) {
            SavedState savedState = (SavedState) parcelable;
            this.featureId = savedState.featureId;
            this.wasLastOpen = savedState.isOpen;
            this.frozenMenuState = savedState.menuState;
            this.shownPanelView = null;
            this.decorView = null;
        }

        void applyFrozenState() {
            if (this.menu != null && this.frozenMenuState != null) {
                this.menu.restorePresenterStates(this.frozenMenuState);
                this.frozenMenuState = null;
            }
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$3 */
    class C03003 implements OnApplyWindowInsetsListener {
        C03003() {
        }

        public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
            int systemWindowInsetTop = windowInsetsCompat.getSystemWindowInsetTop();
            int updateStatusGuard = AppCompatDelegateImpl.this.updateStatusGuard(systemWindowInsetTop);
            if (systemWindowInsetTop != updateStatusGuard) {
                windowInsetsCompat = windowInsetsCompat.replaceSystemWindowInsets(windowInsetsCompat.getSystemWindowInsetLeft(), updateStatusGuard, windowInsetsCompat.getSystemWindowInsetRight(), windowInsetsCompat.getSystemWindowInsetBottom());
            }
            return ViewCompat.onApplyWindowInsets(view, windowInsetsCompat);
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$4 */
    class C03014 implements OnFitSystemWindowsListener {
        C03014() {
        }

        public void onFitSystemWindows(Rect rect) {
            rect.top = AppCompatDelegateImpl.this.updateStatusGuard(rect.top);
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$5 */
    class C03025 implements OnAttachListener {
        public void onAttachedFromWindow() {
        }

        C03025() {
        }

        public void onDetachedFromWindow() {
            AppCompatDelegateImpl.this.dismissPopups();
        }
    }

    private class ActionBarDrawableToggleImpl implements Delegate {
        ActionBarDrawableToggleImpl() {
        }

        public Drawable getThemeUpIndicator() {
            TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(getActionBarThemedContext(), null, new int[]{C0192R.attr.homeAsUpIndicator});
            Drawable drawable = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            return drawable;
        }

        public Context getActionBarThemedContext() {
            return AppCompatDelegateImpl.this.getActionBarThemedContext();
        }

        public boolean isNavigationVisible() {
            ActionBar supportActionBar = AppCompatDelegateImpl.this.getSupportActionBar();
            return (supportActionBar == null || (supportActionBar.getDisplayOptions() & 4) == 0) ? false : true;
        }

        public void setActionBarUpIndicator(Drawable drawable, int i) {
            ActionBar supportActionBar = AppCompatDelegateImpl.this.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setHomeAsUpIndicator(drawable);
                supportActionBar.setHomeActionContentDescription(i);
            }
        }

        public void setActionBarDescription(int i) {
            ActionBar supportActionBar = AppCompatDelegateImpl.this.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setHomeActionContentDescription(i);
            }
        }
    }

    private final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        ActionMenuPresenterCallback() {
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            Window.Callback windowCallback = AppCompatDelegateImpl.this.getWindowCallback();
            if (windowCallback != null) {
                windowCallback.onMenuOpened(108, menuBuilder);
            }
            return true;
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            AppCompatDelegateImpl.this.checkCloseActionMenu(menuBuilder);
        }
    }

    class ActionModeCallbackWrapperV9 implements ActionMode.Callback {
        private ActionMode.Callback mWrapped;

        /* renamed from: android.support.v7.app.AppCompatDelegateImpl$ActionModeCallbackWrapperV9$1 */
        class C03261 extends ViewPropertyAnimatorListenerAdapter {
            C03261() {
            }

            public void onAnimationEnd(View view) {
                AppCompatDelegateImpl.this.mActionModeView.setVisibility(8);
                if (AppCompatDelegateImpl.this.mActionModePopup != null) {
                    AppCompatDelegateImpl.this.mActionModePopup.dismiss();
                } else if ((AppCompatDelegateImpl.this.mActionModeView.getParent() instanceof View) != null) {
                    ViewCompat.requestApplyInsets((View) AppCompatDelegateImpl.this.mActionModeView.getParent());
                }
                AppCompatDelegateImpl.this.mActionModeView.removeAllViews();
                AppCompatDelegateImpl.this.mFadeAnim.setListener(null);
                AppCompatDelegateImpl.this.mFadeAnim = null;
            }
        }

        public ActionModeCallbackWrapperV9(ActionMode.Callback callback) {
            this.mWrapped = callback;
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return this.mWrapped.onCreateActionMode(actionMode, menu);
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return this.mWrapped.onPrepareActionMode(actionMode, menu);
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.mWrapped.onActionItemClicked(actionMode, menuItem);
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            this.mWrapped.onDestroyActionMode(actionMode);
            if (AppCompatDelegateImpl.this.mActionModePopup != null) {
                AppCompatDelegateImpl.this.mWindow.getDecorView().removeCallbacks(AppCompatDelegateImpl.this.mShowActionModePopup);
            }
            if (AppCompatDelegateImpl.this.mActionModeView != null) {
                AppCompatDelegateImpl.this.endOnGoingFadeAnimation();
                AppCompatDelegateImpl.this.mFadeAnim = ViewCompat.animate(AppCompatDelegateImpl.this.mActionModeView).alpha(0.0f);
                AppCompatDelegateImpl.this.mFadeAnim.setListener(new C03261());
            }
            if (AppCompatDelegateImpl.this.mAppCompatCallback != null) {
                AppCompatDelegateImpl.this.mAppCompatCallback.onSupportActionModeFinished(AppCompatDelegateImpl.this.mActionMode);
            }
            AppCompatDelegateImpl.this.mActionMode = null;
        }
    }

    class AppCompatWindowCallback extends WindowCallbackWrapper {
        public void onContentChanged() {
        }

        AppCompatWindowCallback(Window.Callback callback) {
            super(callback);
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            if (!AppCompatDelegateImpl.this.dispatchKeyEvent(keyEvent)) {
                if (super.dispatchKeyEvent(keyEvent) == null) {
                    return null;
                }
            }
            return true;
        }

        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            if (!super.dispatchKeyShortcutEvent(keyEvent)) {
                if (AppCompatDelegateImpl.this.onKeyShortcut(keyEvent.getKeyCode(), keyEvent) == null) {
                    return null;
                }
            }
            return true;
        }

        public boolean onCreatePanelMenu(int i, Menu menu) {
            if (i != 0 || (menu instanceof MenuBuilder)) {
                return super.onCreatePanelMenu(i, menu);
            }
            return false;
        }

        public boolean onPreparePanel(int i, View view, Menu menu) {
            MenuBuilder menuBuilder = menu instanceof MenuBuilder ? (MenuBuilder) menu : null;
            if (i == 0 && menuBuilder == null) {
                return false;
            }
            if (menuBuilder != null) {
                menuBuilder.setOverrideVisibleItems(true);
            }
            i = super.onPreparePanel(i, view, menu);
            if (menuBuilder != null) {
                menuBuilder.setOverrideVisibleItems(false);
            }
            return i;
        }

        public boolean onMenuOpened(int i, Menu menu) {
            super.onMenuOpened(i, menu);
            AppCompatDelegateImpl.this.onMenuOpened(i);
            return true;
        }

        public void onPanelClosed(int i, Menu menu) {
            super.onPanelClosed(i, menu);
            AppCompatDelegateImpl.this.onPanelClosed(i);
        }

        public android.view.ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback callback) {
            if (VERSION.SDK_INT >= 23) {
                return null;
            }
            if (AppCompatDelegateImpl.this.isHandleNativeActionModesEnabled()) {
                return startAsSupportActionMode(callback);
            }
            return super.onWindowStartingActionMode(callback);
        }

        final android.view.ActionMode startAsSupportActionMode(android.view.ActionMode.Callback callback) {
            Object callbackWrapper = new CallbackWrapper(AppCompatDelegateImpl.this.mContext, callback);
            callback = AppCompatDelegateImpl.this.startSupportActionMode(callbackWrapper);
            return callback != null ? callbackWrapper.getActionModeWrapper(callback) : null;
        }

        @RequiresApi(23)
        public android.view.ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback callback, int i) {
            if (AppCompatDelegateImpl.this.isHandleNativeActionModesEnabled()) {
                if (i == 0) {
                    return startAsSupportActionMode(callback);
                }
            }
            return super.onWindowStartingActionMode(callback, i);
        }

        @RequiresApi(24)
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i) {
            PanelFeatureState panelState = AppCompatDelegateImpl.this.getPanelState(0, true);
            if (panelState == null || panelState.menu == null) {
                super.onProvideKeyboardShortcuts(list, menu, i);
            } else {
                super.onProvideKeyboardShortcuts(list, panelState.menu, i);
            }
        }
    }

    private class ListMenuDecorView extends ContentFrameLayout {
        public ListMenuDecorView(Context context) {
            super(context);
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            if (!AppCompatDelegateImpl.this.dispatchKeyEvent(keyEvent)) {
                if (super.dispatchKeyEvent(keyEvent) == null) {
                    return null;
                }
            }
            return true;
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0 || !isOutOfBounds((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            AppCompatDelegateImpl.this.closePanel(0);
            return true;
        }

        public void setBackgroundResource(int i) {
            setBackgroundDrawable(AppCompatResources.getDrawable(getContext(), i));
        }

        private boolean isOutOfBounds(int i, int i2) {
            if (i >= -5 && i2 >= -5 && i <= getWidth() + 5) {
                if (i2 <= getHeight() + 5) {
                    return false;
                }
            }
            return true;
        }
    }

    private final class PanelMenuPresenterCallback implements MenuPresenter.Callback {
        PanelMenuPresenterCallback() {
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            MenuBuilder rootMenu = menuBuilder.getRootMenu();
            Object obj = rootMenu != menuBuilder ? 1 : null;
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (obj != null) {
                menuBuilder = rootMenu;
            }
            menuBuilder = appCompatDelegateImpl.findMenuPanel(menuBuilder);
            if (menuBuilder == null) {
                return;
            }
            if (obj != null) {
                AppCompatDelegateImpl.this.callOnPanelClosed(menuBuilder.featureId, menuBuilder, rootMenu);
                AppCompatDelegateImpl.this.closePanel(menuBuilder, true);
                return;
            }
            AppCompatDelegateImpl.this.closePanel(menuBuilder, z);
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            if (menuBuilder == null && AppCompatDelegateImpl.this.mHasActionBar) {
                Window.Callback windowCallback = AppCompatDelegateImpl.this.getWindowCallback();
                if (!(windowCallback == null || AppCompatDelegateImpl.this.mIsDestroyed)) {
                    windowCallback.onMenuOpened(108, menuBuilder);
                }
            }
            return true;
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$7 */
    class C03257 extends ViewPropertyAnimatorListenerAdapter {
        C03257() {
        }

        public void onAnimationStart(View view) {
            AppCompatDelegateImpl.this.mActionModeView.setVisibility(0);
            AppCompatDelegateImpl.this.mActionModeView.sendAccessibilityEvent(32);
            if ((AppCompatDelegateImpl.this.mActionModeView.getParent() instanceof View) != null) {
                ViewCompat.requestApplyInsets((View) AppCompatDelegateImpl.this.mActionModeView.getParent());
            }
        }

        public void onAnimationEnd(View view) {
            AppCompatDelegateImpl.this.mActionModeView.setAlpha(1.0f);
            AppCompatDelegateImpl.this.mFadeAnim.setListener(null);
            AppCompatDelegateImpl.this.mFadeAnim = null;
        }
    }

    void onSubDecorInstalled(ViewGroup viewGroup) {
    }

    static {
        if (IS_PRE_LOLLIPOP && !sInstalledExceptionHandler) {
            final UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
                public void uncaughtException(Thread thread, Throwable th) {
                    if (shouldWrapException(th)) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(th.getMessage());
                        stringBuilder.append(AppCompatDelegateImpl.EXCEPTION_HANDLER_MESSAGE_SUFFIX);
                        Throwable notFoundException = new NotFoundException(stringBuilder.toString());
                        notFoundException.initCause(th.getCause());
                        notFoundException.setStackTrace(th.getStackTrace());
                        defaultUncaughtExceptionHandler.uncaughtException(thread, notFoundException);
                        return;
                    }
                    defaultUncaughtExceptionHandler.uncaughtException(thread, th);
                }

                private boolean shouldWrapException(Throwable th) {
                    boolean z = false;
                    if (!(th instanceof NotFoundException)) {
                        return false;
                    }
                    th = th.getMessage();
                    if (th != null && (th.contains("drawable") || th.contains("Drawable") != null)) {
                        z = true;
                    }
                    return z;
                }
            });
        }
    }

    AppCompatDelegateImpl(Context context, Window window, AppCompatCallback appCompatCallback) {
        this.mContext = context;
        this.mWindow = window;
        this.mAppCompatCallback = appCompatCallback;
        this.mOriginalWindowCallback = this.mWindow.getCallback();
        if ((this.mOriginalWindowCallback instanceof AppCompatWindowCallback) == null) {
            this.mAppCompatWindowCallback = new AppCompatWindowCallback(this.mOriginalWindowCallback);
            this.mWindow.setCallback(this.mAppCompatWindowCallback);
            context = TintTypedArray.obtainStyledAttributes(context, null, sWindowBackgroundStyleable);
            window = context.getDrawableIfKnown(null);
            if (window != null) {
                this.mWindow.setBackgroundDrawable(window);
            }
            context.recycle();
            return;
        }
        throw new IllegalStateException("AppCompat has already installed itself into the Window");
    }

    public void onCreate(android.os.Bundle r3) {
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r2 = this;
        r0 = r2.mOriginalWindowCallback;
        r0 = r0 instanceof android.app.Activity;
        if (r0 == 0) goto L_0x001f;
    L_0x0006:
        r0 = 0;
        r1 = r2.mOriginalWindowCallback;	 Catch:{ IllegalArgumentException -> 0x0010 }
        r1 = (android.app.Activity) r1;	 Catch:{ IllegalArgumentException -> 0x0010 }
        r1 = android.support.v4.app.NavUtils.getParentActivityName(r1);	 Catch:{ IllegalArgumentException -> 0x0010 }
        r0 = r1;
    L_0x0010:
        if (r0 == 0) goto L_0x001f;
    L_0x0012:
        r0 = r2.peekSupportActionBar();
        r1 = 1;
        if (r0 != 0) goto L_0x001c;
    L_0x0019:
        r2.mEnableDefaultActionBarUp = r1;
        goto L_0x001f;
    L_0x001c:
        r0.setDefaultDisplayHomeAsUpEnabled(r1);
    L_0x001f:
        if (r3 == 0) goto L_0x002f;
    L_0x0021:
        r0 = r2.mLocalNightMode;
        r1 = -100;
        if (r0 != r1) goto L_0x002f;
    L_0x0027:
        r0 = "appcompat:local_night_mode";
        r3 = r3.getInt(r0, r1);
        r2.mLocalNightMode = r3;
    L_0x002f:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatDelegateImpl.onCreate(android.os.Bundle):void");
    }

    public void onPostCreate(Bundle bundle) {
        ensureSubDecor();
    }

    public ActionBar getSupportActionBar() {
        initWindowDecorActionBar();
        return this.mActionBar;
    }

    final ActionBar peekSupportActionBar() {
        return this.mActionBar;
    }

    final Window.Callback getWindowCallback() {
        return this.mWindow.getCallback();
    }

    private void initWindowDecorActionBar() {
        ensureSubDecor();
        if (this.mHasActionBar) {
            if (this.mActionBar == null) {
                if (this.mOriginalWindowCallback instanceof Activity) {
                    this.mActionBar = new WindowDecorActionBar((Activity) this.mOriginalWindowCallback, this.mOverlayActionBar);
                } else if (this.mOriginalWindowCallback instanceof Dialog) {
                    this.mActionBar = new WindowDecorActionBar((Dialog) this.mOriginalWindowCallback);
                }
                if (this.mActionBar != null) {
                    this.mActionBar.setDefaultDisplayHomeAsUpEnabled(this.mEnableDefaultActionBarUp);
                }
            }
        }
    }

    public void setSupportActionBar(Toolbar toolbar) {
        if (this.mOriginalWindowCallback instanceof Activity) {
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar instanceof WindowDecorActionBar) {
                throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
            }
            this.mMenuInflater = null;
            if (supportActionBar != null) {
                supportActionBar.onDestroy();
            }
            if (toolbar != null) {
                supportActionBar = new ToolbarActionBar(toolbar, ((Activity) this.mOriginalWindowCallback).getTitle(), this.mAppCompatWindowCallback);
                this.mActionBar = supportActionBar;
                this.mWindow.setCallback(supportActionBar.getWrappedWindowCallback());
            } else {
                this.mActionBar = null;
                this.mWindow.setCallback(this.mAppCompatWindowCallback);
            }
            invalidateOptionsMenu();
        }
    }

    final Context getActionBarThemedContext() {
        ActionBar supportActionBar = getSupportActionBar();
        Context themedContext = supportActionBar != null ? supportActionBar.getThemedContext() : null;
        return themedContext == null ? this.mContext : themedContext;
    }

    public MenuInflater getMenuInflater() {
        if (this.mMenuInflater == null) {
            initWindowDecorActionBar();
            this.mMenuInflater = new SupportMenuInflater(this.mActionBar != null ? this.mActionBar.getThemedContext() : this.mContext);
        }
        return this.mMenuInflater;
    }

    @Nullable
    public <T extends View> T findViewById(@IdRes int i) {
        ensureSubDecor();
        return this.mWindow.findViewById(i);
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (this.mHasActionBar && this.mSubDecorInstalled) {
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.onConfigurationChanged(configuration);
            }
        }
        AppCompatDrawableManager.get().onConfigurationChanged(this.mContext);
        applyDayNight();
    }

    public void onStart() {
        applyDayNight();
    }

    public void onStop() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setShowHideAnimationEnabled(false);
        }
        if (this.mAutoNightModeManager != null) {
            this.mAutoNightModeManager.cleanup();
        }
    }

    public void onPostResume() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setShowHideAnimationEnabled(true);
        }
    }

    public void setContentView(View view) {
        ensureSubDecor();
        ViewGroup viewGroup = (ViewGroup) this.mSubDecor.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.mOriginalWindowCallback.onContentChanged();
    }

    public void setContentView(int i) {
        ensureSubDecor();
        ViewGroup viewGroup = (ViewGroup) this.mSubDecor.findViewById(16908290);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.mContext).inflate(i, viewGroup);
        this.mOriginalWindowCallback.onContentChanged();
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        ensureSubDecor();
        ViewGroup viewGroup = (ViewGroup) this.mSubDecor.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.mOriginalWindowCallback.onContentChanged();
    }

    public void addContentView(View view, LayoutParams layoutParams) {
        ensureSubDecor();
        ((ViewGroup) this.mSubDecor.findViewById(16908290)).addView(view, layoutParams);
        this.mOriginalWindowCallback.onContentChanged();
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (this.mLocalNightMode != -100) {
            bundle.putInt(KEY_LOCAL_NIGHT_MODE, this.mLocalNightMode);
        }
    }

    public void onDestroy() {
        if (this.mInvalidatePanelMenuPosted) {
            this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
        }
        this.mIsDestroyed = true;
        if (this.mActionBar != null) {
            this.mActionBar.onDestroy();
        }
        if (this.mAutoNightModeManager != null) {
            this.mAutoNightModeManager.cleanup();
        }
    }

    private void ensureSubDecor() {
        if (!this.mSubDecorInstalled) {
            this.mSubDecor = createSubDecor();
            CharSequence title = getTitle();
            if (!TextUtils.isEmpty(title)) {
                if (this.mDecorContentParent != null) {
                    this.mDecorContentParent.setWindowTitle(title);
                } else if (peekSupportActionBar() != null) {
                    peekSupportActionBar().setWindowTitle(title);
                } else if (this.mTitleView != null) {
                    this.mTitleView.setText(title);
                }
            }
            applyFixedSizeWindow();
            onSubDecorInstalled(this.mSubDecor);
            this.mSubDecorInstalled = true;
            PanelFeatureState panelState = getPanelState(0, false);
            if (!this.mIsDestroyed) {
                if (panelState == null || panelState.menu == null) {
                    invalidatePanelMenu(108);
                }
            }
        }
    }

    private ViewGroup createSubDecor() {
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(C0192R.styleable.AppCompatTheme);
        if (obtainStyledAttributes.hasValue(C0192R.styleable.AppCompatTheme_windowActionBar)) {
            View view;
            if (obtainStyledAttributes.getBoolean(C0192R.styleable.AppCompatTheme_windowNoTitle, false)) {
                requestWindowFeature(1);
            } else if (obtainStyledAttributes.getBoolean(C0192R.styleable.AppCompatTheme_windowActionBar, false)) {
                requestWindowFeature(108);
            }
            if (obtainStyledAttributes.getBoolean(C0192R.styleable.AppCompatTheme_windowActionBarOverlay, false)) {
                requestWindowFeature(109);
            }
            if (obtainStyledAttributes.getBoolean(C0192R.styleable.AppCompatTheme_windowActionModeOverlay, false)) {
                requestWindowFeature(10);
            }
            this.mIsFloating = obtainStyledAttributes.getBoolean(C0192R.styleable.AppCompatTheme_android_windowIsFloating, false);
            obtainStyledAttributes.recycle();
            this.mWindow.getDecorView();
            LayoutInflater from = LayoutInflater.from(this.mContext);
            if (this.mWindowNoTitle) {
                if (this.mOverlayActionMode) {
                    view = (ViewGroup) from.inflate(C0192R.layout.abc_screen_simple_overlay_action_mode, null);
                } else {
                    view = (ViewGroup) from.inflate(C0192R.layout.abc_screen_simple, null);
                }
                if (VERSION.SDK_INT >= 21) {
                    ViewCompat.setOnApplyWindowInsetsListener(view, new C03003());
                } else {
                    ((FitWindowsViewGroup) view).setOnFitSystemWindowsListener(new C03014());
                }
            } else if (this.mIsFloating) {
                view = (ViewGroup) from.inflate(C0192R.layout.abc_dialog_title_material, null);
                this.mOverlayActionBar = false;
                this.mHasActionBar = false;
            } else if (this.mHasActionBar) {
                Context contextThemeWrapper;
                TypedValue typedValue = new TypedValue();
                this.mContext.getTheme().resolveAttribute(C0192R.attr.actionBarTheme, typedValue, true);
                if (typedValue.resourceId != 0) {
                    contextThemeWrapper = new ContextThemeWrapper(this.mContext, typedValue.resourceId);
                } else {
                    contextThemeWrapper = this.mContext;
                }
                view = (ViewGroup) LayoutInflater.from(contextThemeWrapper).inflate(C0192R.layout.abc_screen_toolbar, null);
                this.mDecorContentParent = (DecorContentParent) view.findViewById(C0192R.id.decor_content_parent);
                this.mDecorContentParent.setWindowCallback(getWindowCallback());
                if (this.mOverlayActionBar) {
                    this.mDecorContentParent.initFeature(109);
                }
                if (this.mFeatureProgress) {
                    this.mDecorContentParent.initFeature(2);
                }
                if (this.mFeatureIndeterminateProgress) {
                    this.mDecorContentParent.initFeature(5);
                }
            } else {
                view = null;
            }
            if (view != null) {
                if (this.mDecorContentParent == null) {
                    this.mTitleView = (TextView) view.findViewById(C0192R.id.title);
                }
                ViewUtils.makeOptionalFitsSystemWindows(view);
                ContentFrameLayout contentFrameLayout = (ContentFrameLayout) view.findViewById(C0192R.id.action_bar_activity_content);
                ViewGroup viewGroup = (ViewGroup) this.mWindow.findViewById(16908290);
                if (viewGroup != null) {
                    while (viewGroup.getChildCount() > 0) {
                        View childAt = viewGroup.getChildAt(0);
                        viewGroup.removeViewAt(0);
                        contentFrameLayout.addView(childAt);
                    }
                    viewGroup.setId(-1);
                    contentFrameLayout.setId(16908290);
                    if (viewGroup instanceof FrameLayout) {
                        ((FrameLayout) viewGroup).setForeground(null);
                    }
                }
                this.mWindow.setContentView(view);
                contentFrameLayout.setAttachListener(new C03025());
                return view;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("AppCompat does not support the current theme features: { windowActionBar: ");
            stringBuilder.append(this.mHasActionBar);
            stringBuilder.append(", windowActionBarOverlay: ");
            stringBuilder.append(this.mOverlayActionBar);
            stringBuilder.append(", android:windowIsFloating: ");
            stringBuilder.append(this.mIsFloating);
            stringBuilder.append(", windowActionModeOverlay: ");
            stringBuilder.append(this.mOverlayActionMode);
            stringBuilder.append(", windowNoTitle: ");
            stringBuilder.append(this.mWindowNoTitle);
            stringBuilder.append(" }");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        obtainStyledAttributes.recycle();
        throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
    }

    private void applyFixedSizeWindow() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.mSubDecor.findViewById(16908290);
        View decorView = this.mWindow.getDecorView();
        contentFrameLayout.setDecorPadding(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(C0192R.styleable.AppCompatTheme);
        obtainStyledAttributes.getValue(C0192R.styleable.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        obtainStyledAttributes.getValue(C0192R.styleable.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        if (obtainStyledAttributes.hasValue(C0192R.styleable.AppCompatTheme_windowFixedWidthMajor)) {
            obtainStyledAttributes.getValue(C0192R.styleable.AppCompatTheme_windowFixedWidthMajor, contentFrameLayout.getFixedWidthMajor());
        }
        if (obtainStyledAttributes.hasValue(C0192R.styleable.AppCompatTheme_windowFixedWidthMinor)) {
            obtainStyledAttributes.getValue(C0192R.styleable.AppCompatTheme_windowFixedWidthMinor, contentFrameLayout.getFixedWidthMinor());
        }
        if (obtainStyledAttributes.hasValue(C0192R.styleable.AppCompatTheme_windowFixedHeightMajor)) {
            obtainStyledAttributes.getValue(C0192R.styleable.AppCompatTheme_windowFixedHeightMajor, contentFrameLayout.getFixedHeightMajor());
        }
        if (obtainStyledAttributes.hasValue(C0192R.styleable.AppCompatTheme_windowFixedHeightMinor)) {
            obtainStyledAttributes.getValue(C0192R.styleable.AppCompatTheme_windowFixedHeightMinor, contentFrameLayout.getFixedHeightMinor());
        }
        obtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    public boolean requestWindowFeature(int i) {
        i = sanitizeWindowFeatureId(i);
        if (this.mWindowNoTitle && i == 108) {
            return false;
        }
        if (this.mHasActionBar && i == 1) {
            this.mHasActionBar = false;
        }
        switch (i) {
            case 1:
                throwFeatureRequestIfSubDecorInstalled();
                this.mWindowNoTitle = true;
                return true;
            case 2:
                throwFeatureRequestIfSubDecorInstalled();
                this.mFeatureProgress = true;
                return true;
            case 5:
                throwFeatureRequestIfSubDecorInstalled();
                this.mFeatureIndeterminateProgress = true;
                return true;
            case 10:
                throwFeatureRequestIfSubDecorInstalled();
                this.mOverlayActionMode = true;
                return true;
            case 108:
                throwFeatureRequestIfSubDecorInstalled();
                this.mHasActionBar = true;
                return true;
            case 109:
                throwFeatureRequestIfSubDecorInstalled();
                this.mOverlayActionBar = true;
                return true;
            default:
                return this.mWindow.requestFeature(i);
        }
    }

    public boolean hasWindowFeature(int i) {
        boolean z;
        switch (sanitizeWindowFeatureId(i)) {
            case 1:
                z = this.mWindowNoTitle;
                break;
            case 2:
                z = this.mFeatureProgress;
                break;
            case 5:
                z = this.mFeatureIndeterminateProgress;
                break;
            case 10:
                z = this.mOverlayActionMode;
                break;
            case 108:
                z = this.mHasActionBar;
                break;
            case 109:
                z = this.mOverlayActionBar;
                break;
            default:
                z = false;
                break;
        }
        if (z || this.mWindow.hasFeature(i) != 0) {
            return true;
        }
        return false;
    }

    public final void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        if (this.mDecorContentParent != null) {
            this.mDecorContentParent.setWindowTitle(charSequence);
        } else if (peekSupportActionBar() != null) {
            peekSupportActionBar().setWindowTitle(charSequence);
        } else if (this.mTitleView != null) {
            this.mTitleView.setText(charSequence);
        }
    }

    final CharSequence getTitle() {
        if (this.mOriginalWindowCallback instanceof Activity) {
            return ((Activity) this.mOriginalWindowCallback).getTitle();
        }
        return this.mTitle;
    }

    void onPanelClosed(int i) {
        if (i == 108) {
            i = getSupportActionBar();
            if (i != 0) {
                i.dispatchMenuVisibilityChanged(false);
            }
        } else if (i == 0) {
            i = getPanelState(i, true);
            if (i.isOpen) {
                closePanel(i, false);
            }
        }
    }

    void onMenuOpened(int i) {
        if (i == 108) {
            i = getSupportActionBar();
            if (i != 0) {
                i.dispatchMenuVisibilityChanged(true);
            }
        }
    }

    public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
        Window.Callback windowCallback = getWindowCallback();
        if (!(windowCallback == null || this.mIsDestroyed)) {
            menuBuilder = findMenuPanel(menuBuilder.getRootMenu());
            if (menuBuilder != null) {
                return windowCallback.onMenuItemSelected(menuBuilder.featureId, menuItem);
            }
        }
        return null;
    }

    public void onMenuModeChange(MenuBuilder menuBuilder) {
        reopenMenu(menuBuilder, true);
    }

    public ActionMode startSupportActionMode(@NonNull ActionMode.Callback callback) {
        if (callback != null) {
            if (this.mActionMode != null) {
                this.mActionMode.finish();
            }
            ActionMode.Callback actionModeCallbackWrapperV9 = new ActionModeCallbackWrapperV9(callback);
            callback = getSupportActionBar();
            if (callback != null) {
                this.mActionMode = callback.startActionMode(actionModeCallbackWrapperV9);
                if (!(this.mActionMode == null || this.mAppCompatCallback == null)) {
                    this.mAppCompatCallback.onSupportActionModeStarted(this.mActionMode);
                }
            }
            if (this.mActionMode == null) {
                this.mActionMode = startSupportActionModeFromWindow(actionModeCallbackWrapperV9);
            }
            return this.mActionMode;
        }
        throw new IllegalArgumentException("ActionMode callback can not be null.");
    }

    public void invalidateOptionsMenu() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null || !supportActionBar.invalidateOptionsMenu()) {
            invalidatePanelMenu(0);
        }
    }

    android.support.v7.view.ActionMode startSupportActionModeFromWindow(@android.support.annotation.NonNull android.support.v7.view.ActionMode.Callback r8) {
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r7 = this;
        r7.endOnGoingFadeAnimation();
        r0 = r7.mActionMode;
        if (r0 == 0) goto L_0x000c;
    L_0x0007:
        r0 = r7.mActionMode;
        r0.finish();
    L_0x000c:
        r0 = r8 instanceof android.support.v7.app.AppCompatDelegateImpl.ActionModeCallbackWrapperV9;
        if (r0 != 0) goto L_0x0016;
    L_0x0010:
        r0 = new android.support.v7.app.AppCompatDelegateImpl$ActionModeCallbackWrapperV9;
        r0.<init>(r8);
        r8 = r0;
    L_0x0016:
        r0 = r7.mAppCompatCallback;
        r1 = 0;
        if (r0 == 0) goto L_0x0026;
    L_0x001b:
        r0 = r7.mIsDestroyed;
        if (r0 != 0) goto L_0x0026;
    L_0x001f:
        r0 = r7.mAppCompatCallback;	 Catch:{ AbstractMethodError -> 0x0026 }
        r0 = r0.onWindowStartingSupportActionMode(r8);	 Catch:{ AbstractMethodError -> 0x0026 }
        goto L_0x0027;
    L_0x0026:
        r0 = r1;
    L_0x0027:
        if (r0 == 0) goto L_0x002d;
    L_0x0029:
        r7.mActionMode = r0;
        goto L_0x016a;
    L_0x002d:
        r0 = r7.mActionModeView;
        r2 = 0;
        r3 = 1;
        if (r0 != 0) goto L_0x00da;
    L_0x0033:
        r0 = r7.mIsFloating;
        if (r0 == 0) goto L_0x00bb;
    L_0x0037:
        r0 = new android.util.TypedValue;
        r0.<init>();
        r4 = r7.mContext;
        r4 = r4.getTheme();
        r5 = android.support.v7.appcompat.C0192R.attr.actionBarTheme;
        r4.resolveAttribute(r5, r0, r3);
        r5 = r0.resourceId;
        if (r5 == 0) goto L_0x006c;
    L_0x004b:
        r5 = r7.mContext;
        r5 = r5.getResources();
        r5 = r5.newTheme();
        r5.setTo(r4);
        r4 = r0.resourceId;
        r5.applyStyle(r4, r3);
        r4 = new android.support.v7.view.ContextThemeWrapper;
        r6 = r7.mContext;
        r4.<init>(r6, r2);
        r6 = r4.getTheme();
        r6.setTo(r5);
        goto L_0x006e;
    L_0x006c:
        r4 = r7.mContext;
    L_0x006e:
        r5 = new android.support.v7.widget.ActionBarContextView;
        r5.<init>(r4);
        r7.mActionModeView = r5;
        r5 = new android.widget.PopupWindow;
        r6 = android.support.v7.appcompat.C0192R.attr.actionModePopupWindowStyle;
        r5.<init>(r4, r1, r6);
        r7.mActionModePopup = r5;
        r5 = r7.mActionModePopup;
        r6 = 2;
        android.support.v4.widget.PopupWindowCompat.setWindowLayoutType(r5, r6);
        r5 = r7.mActionModePopup;
        r6 = r7.mActionModeView;
        r5.setContentView(r6);
        r5 = r7.mActionModePopup;
        r6 = -1;
        r5.setWidth(r6);
        r5 = r4.getTheme();
        r6 = android.support.v7.appcompat.C0192R.attr.actionBarSize;
        r5.resolveAttribute(r6, r0, r3);
        r0 = r0.data;
        r4 = r4.getResources();
        r4 = r4.getDisplayMetrics();
        r0 = android.util.TypedValue.complexToDimensionPixelSize(r0, r4);
        r4 = r7.mActionModeView;
        r4.setContentHeight(r0);
        r0 = r7.mActionModePopup;
        r4 = -2;
        r0.setHeight(r4);
        r0 = new android.support.v7.app.AppCompatDelegateImpl$6;
        r0.<init>();
        r7.mShowActionModePopup = r0;
        goto L_0x00da;
    L_0x00bb:
        r0 = r7.mSubDecor;
        r4 = android.support.v7.appcompat.C0192R.id.action_mode_bar_stub;
        r0 = r0.findViewById(r4);
        r0 = (android.support.v7.widget.ViewStubCompat) r0;
        if (r0 == 0) goto L_0x00da;
    L_0x00c7:
        r4 = r7.getActionBarThemedContext();
        r4 = android.view.LayoutInflater.from(r4);
        r0.setLayoutInflater(r4);
        r0 = r0.inflate();
        r0 = (android.support.v7.widget.ActionBarContextView) r0;
        r7.mActionModeView = r0;
    L_0x00da:
        r0 = r7.mActionModeView;
        if (r0 == 0) goto L_0x016a;
    L_0x00de:
        r7.endOnGoingFadeAnimation();
        r0 = r7.mActionModeView;
        r0.killMode();
        r0 = new android.support.v7.view.StandaloneActionMode;
        r4 = r7.mActionModeView;
        r4 = r4.getContext();
        r5 = r7.mActionModeView;
        r6 = r7.mActionModePopup;
        if (r6 != 0) goto L_0x00f5;
    L_0x00f4:
        goto L_0x00f6;
    L_0x00f5:
        r3 = 0;
    L_0x00f6:
        r0.<init>(r4, r5, r8, r3);
        r3 = r0.getMenu();
        r8 = r8.onCreateActionMode(r0, r3);
        if (r8 == 0) goto L_0x0168;
    L_0x0103:
        r0.invalidate();
        r8 = r7.mActionModeView;
        r8.initForMode(r0);
        r7.mActionMode = r0;
        r8 = r7.shouldAnimateActionModeView();
        r0 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        if (r8 == 0) goto L_0x0132;
    L_0x0115:
        r8 = r7.mActionModeView;
        r1 = 0;
        r8.setAlpha(r1);
        r8 = r7.mActionModeView;
        r8 = android.support.v4.view.ViewCompat.animate(r8);
        r8 = r8.alpha(r0);
        r7.mFadeAnim = r8;
        r8 = r7.mFadeAnim;
        r0 = new android.support.v7.app.AppCompatDelegateImpl$7;
        r0.<init>();
        r8.setListener(r0);
        goto L_0x0158;
    L_0x0132:
        r8 = r7.mActionModeView;
        r8.setAlpha(r0);
        r8 = r7.mActionModeView;
        r8.setVisibility(r2);
        r8 = r7.mActionModeView;
        r0 = 32;
        r8.sendAccessibilityEvent(r0);
        r8 = r7.mActionModeView;
        r8 = r8.getParent();
        r8 = r8 instanceof android.view.View;
        if (r8 == 0) goto L_0x0158;
    L_0x014d:
        r8 = r7.mActionModeView;
        r8 = r8.getParent();
        r8 = (android.view.View) r8;
        android.support.v4.view.ViewCompat.requestApplyInsets(r8);
    L_0x0158:
        r8 = r7.mActionModePopup;
        if (r8 == 0) goto L_0x016a;
    L_0x015c:
        r8 = r7.mWindow;
        r8 = r8.getDecorView();
        r0 = r7.mShowActionModePopup;
        r8.post(r0);
        goto L_0x016a;
    L_0x0168:
        r7.mActionMode = r1;
    L_0x016a:
        r8 = r7.mActionMode;
        if (r8 == 0) goto L_0x0179;
    L_0x016e:
        r8 = r7.mAppCompatCallback;
        if (r8 == 0) goto L_0x0179;
    L_0x0172:
        r8 = r7.mAppCompatCallback;
        r0 = r7.mActionMode;
        r8.onSupportActionModeStarted(r0);
    L_0x0179:
        r8 = r7.mActionMode;
        return r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatDelegateImpl.startSupportActionModeFromWindow(android.support.v7.view.ActionMode$Callback):android.support.v7.view.ActionMode");
    }

    final boolean shouldAnimateActionModeView() {
        return this.mSubDecorInstalled && this.mSubDecor != null && ViewCompat.isLaidOut(this.mSubDecor);
    }

    public void setHandleNativeActionModesEnabled(boolean z) {
        this.mHandleNativeActionModes = z;
    }

    public boolean isHandleNativeActionModesEnabled() {
        return this.mHandleNativeActionModes;
    }

    void endOnGoingFadeAnimation() {
        if (this.mFadeAnim != null) {
            this.mFadeAnim.cancel();
        }
    }

    boolean onBackPressed() {
        if (this.mActionMode != null) {
            this.mActionMode.finish();
            return true;
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null || !supportActionBar.collapseActionView()) {
            return false;
        }
        return true;
    }

    boolean onKeyShortcut(int i, KeyEvent keyEvent) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null && supportActionBar.onKeyShortcut(i, keyEvent) != 0) {
            return true;
        }
        if (this.mPreparedPanel == 0 || performPanelShortcut(this.mPreparedPanel, keyEvent.getKeyCode(), keyEvent, 1) == 0) {
            if (this.mPreparedPanel == 0) {
                i = getPanelState(0, true);
                preparePanel(i, keyEvent);
                keyEvent = performPanelShortcut(i, keyEvent.getKeyCode(), keyEvent, 1);
                i.isPrepared = false;
                if (keyEvent != null) {
                    return true;
                }
            }
            return false;
        }
        if (this.mPreparedPanel != 0) {
            this.mPreparedPanel.isHandled = true;
        }
        return true;
    }

    boolean dispatchKeyEvent(KeyEvent keyEvent) {
        boolean z = true;
        if ((this.mOriginalWindowCallback instanceof Component) || (this.mOriginalWindowCallback instanceof AppCompatDialog)) {
            View decorView = this.mWindow.getDecorView();
            if (decorView != null && KeyEventDispatcher.dispatchBeforeHierarchy(decorView, keyEvent)) {
                return true;
            }
        }
        if (keyEvent.getKeyCode() == 82 && this.mOriginalWindowCallback.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getAction() != 0) {
            z = false;
        }
        return z ? onKeyDown(keyCode, keyEvent) : onKeyUp(keyCode, keyEvent);
    }

    boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 4) {
            i = this.mLongPressBackDown;
            this.mLongPressBackDown = false;
            keyEvent = getPanelState(0, false);
            if (keyEvent != null && keyEvent.isOpen) {
                if (i == 0) {
                    closePanel(keyEvent, true);
                }
                return true;
            } else if (onBackPressed() != 0) {
                return true;
            }
        } else if (i == 82) {
            onKeyUpPanel(0, keyEvent);
            return true;
        }
        return false;
    }

    boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = true;
        if (i == 4) {
            if ((keyEvent.getFlags() & 128) == 0) {
                z = false;
            }
            this.mLongPressBackDown = z;
        } else if (i == 82) {
            onKeyDownPanel(0, keyEvent);
            return true;
        }
        return false;
    }

    public View createView(View view, String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        boolean z;
        boolean z2 = false;
        if (this.mAppCompatViewInflater == null) {
            String string = this.mContext.obtainStyledAttributes(C0192R.styleable.AppCompatTheme).getString(C0192R.styleable.AppCompatTheme_viewInflaterClass);
            if (string != null) {
                if (!AppCompatViewInflater.class.getName().equals(string)) {
                    try {
                        this.mAppCompatViewInflater = (AppCompatViewInflater) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                    } catch (Throwable th) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Failed to instantiate custom view inflater ");
                        stringBuilder.append(string);
                        stringBuilder.append(". Falling back to default.");
                        Log.i("AppCompatDelegate", stringBuilder.toString(), th);
                        this.mAppCompatViewInflater = new AppCompatViewInflater();
                    }
                }
            }
            this.mAppCompatViewInflater = new AppCompatViewInflater();
        }
        if (IS_PRE_LOLLIPOP) {
            if (!(attributeSet instanceof XmlPullParser)) {
                z2 = shouldInheritContext((ViewParent) view);
            } else if (((XmlPullParser) attributeSet).getDepth() > 1) {
                z2 = true;
            }
            z = z2;
        } else {
            z = false;
        }
        return this.mAppCompatViewInflater.createView(view, str, context, attributeSet, z, IS_PRE_LOLLIPOP, true, VectorEnabledTintResources.shouldBeUsed());
    }

    private boolean shouldInheritContext(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        ViewParent decorView = this.mWindow.getDecorView();
        while (viewParent != null) {
            if (viewParent != decorView && (viewParent instanceof View)) {
                if (!ViewCompat.isAttachedToWindow((View) viewParent)) {
                    viewParent = viewParent.getParent();
                }
            }
            return false;
        }
        return true;
    }

    public void installViewFactory() {
        LayoutInflater from = LayoutInflater.from(this.mContext);
        if (from.getFactory() == null) {
            LayoutInflaterCompat.setFactory2(from, this);
        } else if (!(from.getFactory2() instanceof AppCompatDelegateImpl)) {
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return createView(view, str, context, attributeSet);
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    private void openPanel(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        if (!panelFeatureState.isOpen) {
            if (!this.mIsDestroyed) {
                if (panelFeatureState.featureId == 0) {
                    if (((this.mContext.getResources().getConfiguration().screenLayout & 15) == 4 ? 1 : null) != null) {
                        return;
                    }
                }
                Window.Callback windowCallback = getWindowCallback();
                if (windowCallback == null || windowCallback.onMenuOpened(panelFeatureState.featureId, panelFeatureState.menu)) {
                    WindowManager windowManager = (WindowManager) this.mContext.getSystemService("window");
                    if (windowManager != null && preparePanel(panelFeatureState, keyEvent) != null) {
                        int i;
                        KeyEvent layoutParams;
                        if (panelFeatureState.decorView != null) {
                            if (panelFeatureState.refreshDecorView == null) {
                                if (panelFeatureState.createdPanelView != null) {
                                    keyEvent = panelFeatureState.createdPanelView.getLayoutParams();
                                    if (keyEvent != null && keyEvent.width == -1) {
                                        i = -1;
                                        panelFeatureState.isHandled = false;
                                        layoutParams = new WindowManager.LayoutParams(i, -2, panelFeatureState.f7x, panelFeatureState.f8y, PointerIconCompat.TYPE_HAND, 8519680, -3);
                                        layoutParams.gravity = panelFeatureState.gravity;
                                        layoutParams.windowAnimations = panelFeatureState.windowAnimations;
                                        windowManager.addView(panelFeatureState.decorView, layoutParams);
                                        panelFeatureState.isOpen = true;
                                        return;
                                    }
                                }
                                i = -2;
                                panelFeatureState.isHandled = false;
                                layoutParams = new WindowManager.LayoutParams(i, -2, panelFeatureState.f7x, panelFeatureState.f8y, PointerIconCompat.TYPE_HAND, 8519680, -3);
                                layoutParams.gravity = panelFeatureState.gravity;
                                layoutParams.windowAnimations = panelFeatureState.windowAnimations;
                                windowManager.addView(panelFeatureState.decorView, layoutParams);
                                panelFeatureState.isOpen = true;
                                return;
                            }
                        }
                        if (panelFeatureState.decorView == null) {
                            if (initializePanelDecor(panelFeatureState) == null || panelFeatureState.decorView == null) {
                                return;
                            }
                        } else if (panelFeatureState.refreshDecorView != null && panelFeatureState.decorView.getChildCount() > null) {
                            panelFeatureState.decorView.removeAllViews();
                        }
                        if (initializePanelContent(panelFeatureState) != null) {
                            if (panelFeatureState.hasPanelItems() != null) {
                                keyEvent = panelFeatureState.shownPanelView.getLayoutParams();
                                if (keyEvent == null) {
                                    keyEvent = new LayoutParams(-2, -2);
                                }
                                panelFeatureState.decorView.setBackgroundResource(panelFeatureState.background);
                                ViewParent parent = panelFeatureState.shownPanelView.getParent();
                                if (parent != null && (parent instanceof ViewGroup)) {
                                    ((ViewGroup) parent).removeView(panelFeatureState.shownPanelView);
                                }
                                panelFeatureState.decorView.addView(panelFeatureState.shownPanelView, keyEvent);
                                if (panelFeatureState.shownPanelView.hasFocus() == null) {
                                    panelFeatureState.shownPanelView.requestFocus();
                                }
                                i = -2;
                                panelFeatureState.isHandled = false;
                                layoutParams = new WindowManager.LayoutParams(i, -2, panelFeatureState.f7x, panelFeatureState.f8y, PointerIconCompat.TYPE_HAND, 8519680, -3);
                                layoutParams.gravity = panelFeatureState.gravity;
                                layoutParams.windowAnimations = panelFeatureState.windowAnimations;
                                windowManager.addView(panelFeatureState.decorView, layoutParams);
                                panelFeatureState.isOpen = true;
                                return;
                            }
                        }
                        return;
                    }
                    return;
                }
                closePanel(panelFeatureState, true);
            }
        }
    }

    private boolean initializePanelDecor(PanelFeatureState panelFeatureState) {
        panelFeatureState.setStyle(getActionBarThemedContext());
        panelFeatureState.decorView = new ListMenuDecorView(panelFeatureState.listPresenterContext);
        panelFeatureState.gravity = 81;
        return true;
    }

    private void reopenMenu(MenuBuilder menuBuilder, boolean z) {
        if (this.mDecorContentParent == null || this.mDecorContentParent.canShowOverflowMenu() == null || (ViewConfiguration.get(this.mContext).hasPermanentMenuKey() != null && this.mDecorContentParent.isOverflowMenuShowPending() == null)) {
            menuBuilder = getPanelState(0, true);
            menuBuilder.refreshDecorView = true;
            closePanel(menuBuilder, false);
            openPanel(menuBuilder, false);
            return;
        }
        menuBuilder = getWindowCallback();
        if (this.mDecorContentParent.isOverflowMenuShowing()) {
            if (z) {
                this.mDecorContentParent.hideOverflowMenu();
                if (!this.mIsDestroyed) {
                    menuBuilder.onPanelClosed(108, getPanelState(0, true).menu);
                }
            }
        }
        if (!(menuBuilder == null || this.mIsDestroyed)) {
            if (this.mInvalidatePanelMenuPosted && (this.mInvalidatePanelMenuFeatures & true)) {
                this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
                this.mInvalidatePanelMenuRunnable.run();
            }
            z = getPanelState(0, true);
            if (!(z.menu == null || z.refreshMenuContent || !menuBuilder.onPreparePanel(0, z.createdPanelView, z.menu))) {
                menuBuilder.onMenuOpened(108, z.menu);
                this.mDecorContentParent.showOverflowMenu();
            }
        }
    }

    private boolean initializePanelMenu(PanelFeatureState panelFeatureState) {
        Context context = this.mContext;
        if ((panelFeatureState.featureId == 0 || panelFeatureState.featureId == 108) && this.mDecorContentParent != null) {
            TypedValue typedValue = new TypedValue();
            Theme theme = context.getTheme();
            theme.resolveAttribute(C0192R.attr.actionBarTheme, typedValue, true);
            Theme theme2 = null;
            if (typedValue.resourceId != 0) {
                theme2 = context.getResources().newTheme();
                theme2.setTo(theme);
                theme2.applyStyle(typedValue.resourceId, true);
                theme2.resolveAttribute(C0192R.attr.actionBarWidgetTheme, typedValue, true);
            } else {
                theme.resolveAttribute(C0192R.attr.actionBarWidgetTheme, typedValue, true);
            }
            if (typedValue.resourceId != 0) {
                if (theme2 == null) {
                    theme2 = context.getResources().newTheme();
                    theme2.setTo(theme);
                }
                theme2.applyStyle(typedValue.resourceId, true);
            }
            if (theme2 != null) {
                Context contextThemeWrapper = new ContextThemeWrapper(context, 0);
                contextThemeWrapper.getTheme().setTo(theme2);
                context = contextThemeWrapper;
            }
        }
        MenuBuilder menuBuilder = new MenuBuilder(context);
        menuBuilder.setCallback(this);
        panelFeatureState.setMenu(menuBuilder);
        return true;
    }

    private boolean initializePanelContent(PanelFeatureState panelFeatureState) {
        boolean z = true;
        if (panelFeatureState.createdPanelView != null) {
            panelFeatureState.shownPanelView = panelFeatureState.createdPanelView;
            return true;
        } else if (panelFeatureState.menu == null) {
            return false;
        } else {
            if (this.mPanelMenuPresenterCallback == null) {
                this.mPanelMenuPresenterCallback = new PanelMenuPresenterCallback();
            }
            panelFeatureState.shownPanelView = (View) panelFeatureState.getListMenuView(this.mPanelMenuPresenterCallback);
            if (panelFeatureState.shownPanelView == null) {
                z = false;
            }
            return z;
        }
    }

    private boolean preparePanel(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        if (this.mIsDestroyed) {
            return false;
        }
        if (panelFeatureState.isPrepared) {
            return true;
        }
        Object obj;
        if (!(this.mPreparedPanel == null || this.mPreparedPanel == panelFeatureState)) {
            closePanel(this.mPreparedPanel, false);
        }
        Window.Callback windowCallback = getWindowCallback();
        if (windowCallback != null) {
            panelFeatureState.createdPanelView = windowCallback.onCreatePanelView(panelFeatureState.featureId);
        }
        if (panelFeatureState.featureId != 0) {
            if (panelFeatureState.featureId != 108) {
                obj = null;
                if (!(obj == null || this.mDecorContentParent == null)) {
                    this.mDecorContentParent.setMenuPrepared();
                }
                if (panelFeatureState.createdPanelView == null && (obj == null || !(peekSupportActionBar() instanceof ToolbarActionBar))) {
                    if (panelFeatureState.menu == null || panelFeatureState.refreshMenuContent) {
                        if (panelFeatureState.menu != null && (!initializePanelMenu(panelFeatureState) || panelFeatureState.menu == null)) {
                            return false;
                        }
                        if (!(obj == null || this.mDecorContentParent == null)) {
                            if (this.mActionMenuPresenterCallback == null) {
                                this.mActionMenuPresenterCallback = new ActionMenuPresenterCallback();
                            }
                            this.mDecorContentParent.setMenu(panelFeatureState.menu, this.mActionMenuPresenterCallback);
                        }
                        panelFeatureState.menu.stopDispatchingItemsChanged();
                        if (windowCallback.onCreatePanelMenu(panelFeatureState.featureId, panelFeatureState.menu)) {
                            panelFeatureState.setMenu(null);
                            if (!(obj == null || this.mDecorContentParent == null)) {
                                this.mDecorContentParent.setMenu(null, this.mActionMenuPresenterCallback);
                            }
                            return false;
                        }
                        panelFeatureState.refreshMenuContent = false;
                    }
                    panelFeatureState.menu.stopDispatchingItemsChanged();
                    if (panelFeatureState.frozenActionViewState != null) {
                        panelFeatureState.menu.restoreActionViewStates(panelFeatureState.frozenActionViewState);
                        panelFeatureState.frozenActionViewState = null;
                    }
                    if (windowCallback.onPreparePanel(0, panelFeatureState.createdPanelView, panelFeatureState.menu)) {
                        if (!(obj == null || this.mDecorContentParent == null)) {
                            this.mDecorContentParent.setMenu(null, this.mActionMenuPresenterCallback);
                        }
                        panelFeatureState.menu.startDispatchingItemsChanged();
                        return false;
                    }
                    panelFeatureState.qwertyMode = KeyCharacterMap.load(keyEvent == null ? keyEvent.getDeviceId() : -1).getKeyboardType() == 1 ? true : null;
                    panelFeatureState.menu.setQwertyMode(panelFeatureState.qwertyMode);
                    panelFeatureState.menu.startDispatchingItemsChanged();
                }
                panelFeatureState.isPrepared = true;
                panelFeatureState.isHandled = false;
                this.mPreparedPanel = panelFeatureState;
                return true;
            }
        }
        obj = 1;
        this.mDecorContentParent.setMenuPrepared();
        if (panelFeatureState.menu != null) {
        }
        if (this.mActionMenuPresenterCallback == null) {
            this.mActionMenuPresenterCallback = new ActionMenuPresenterCallback();
        }
        this.mDecorContentParent.setMenu(panelFeatureState.menu, this.mActionMenuPresenterCallback);
        panelFeatureState.menu.stopDispatchingItemsChanged();
        if (windowCallback.onCreatePanelMenu(panelFeatureState.featureId, panelFeatureState.menu)) {
            panelFeatureState.refreshMenuContent = false;
            panelFeatureState.menu.stopDispatchingItemsChanged();
            if (panelFeatureState.frozenActionViewState != null) {
                panelFeatureState.menu.restoreActionViewStates(panelFeatureState.frozenActionViewState);
                panelFeatureState.frozenActionViewState = null;
            }
            if (windowCallback.onPreparePanel(0, panelFeatureState.createdPanelView, panelFeatureState.menu)) {
                if (keyEvent == null) {
                }
                if (KeyCharacterMap.load(keyEvent == null ? keyEvent.getDeviceId() : -1).getKeyboardType() == 1) {
                }
                panelFeatureState.qwertyMode = KeyCharacterMap.load(keyEvent == null ? keyEvent.getDeviceId() : -1).getKeyboardType() == 1 ? true : null;
                panelFeatureState.menu.setQwertyMode(panelFeatureState.qwertyMode);
                panelFeatureState.menu.startDispatchingItemsChanged();
                panelFeatureState.isPrepared = true;
                panelFeatureState.isHandled = false;
                this.mPreparedPanel = panelFeatureState;
                return true;
            }
            this.mDecorContentParent.setMenu(null, this.mActionMenuPresenterCallback);
            panelFeatureState.menu.startDispatchingItemsChanged();
            return false;
        }
        panelFeatureState.setMenu(null);
        this.mDecorContentParent.setMenu(null, this.mActionMenuPresenterCallback);
        return false;
    }

    void checkCloseActionMenu(MenuBuilder menuBuilder) {
        if (!this.mClosingActionMenu) {
            this.mClosingActionMenu = true;
            this.mDecorContentParent.dismissPopups();
            Window.Callback windowCallback = getWindowCallback();
            if (!(windowCallback == null || this.mIsDestroyed)) {
                windowCallback.onPanelClosed(108, menuBuilder);
            }
            this.mClosingActionMenu = null;
        }
    }

    void closePanel(int i) {
        closePanel(getPanelState(i, true), true);
    }

    void closePanel(PanelFeatureState panelFeatureState, boolean z) {
        if (z && panelFeatureState.featureId == 0 && this.mDecorContentParent != null && this.mDecorContentParent.isOverflowMenuShowing()) {
            checkCloseActionMenu(panelFeatureState.menu);
            return;
        }
        WindowManager windowManager = (WindowManager) this.mContext.getSystemService("window");
        if (!(windowManager == null || !panelFeatureState.isOpen || panelFeatureState.decorView == null)) {
            windowManager.removeView(panelFeatureState.decorView);
            if (z) {
                callOnPanelClosed(panelFeatureState.featureId, panelFeatureState, null);
            }
        }
        panelFeatureState.isPrepared = false;
        panelFeatureState.isHandled = false;
        panelFeatureState.isOpen = false;
        panelFeatureState.shownPanelView = null;
        panelFeatureState.refreshDecorView = true;
        if (this.mPreparedPanel == panelFeatureState) {
            this.mPreparedPanel = null;
        }
    }

    private boolean onKeyDownPanel(int i, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() == 0) {
            i = getPanelState(i, true);
            if (!i.isOpen) {
                return preparePanel(i, keyEvent);
            }
        }
        return false;
    }

    private boolean onKeyUpPanel(int i, KeyEvent keyEvent) {
        if (this.mActionMode != null) {
            return false;
        }
        PanelFeatureState panelState = getPanelState(i, true);
        AudioManager audioManager;
        if (i != 0 || this.mDecorContentParent == 0 || this.mDecorContentParent.canShowOverflowMenu() == 0 || ViewConfiguration.get(this.mContext).hasPermanentMenuKey() != 0) {
            if (panelState.isOpen == 0) {
                if (panelState.isHandled == 0) {
                    if (panelState.isPrepared != 0) {
                        if (panelState.refreshMenuContent != 0) {
                            panelState.isPrepared = false;
                            i = preparePanel(panelState, keyEvent);
                        } else {
                            i = 1;
                        }
                        if (i != 0) {
                            openPanel(panelState, keyEvent);
                            i = 1;
                            if (i != 0) {
                                audioManager = (AudioManager) this.mContext.getSystemService("audio");
                                if (audioManager == null) {
                                    audioManager.playSoundEffect(0);
                                } else {
                                    Log.w("AppCompatDelegate", "Couldn't get audio manager");
                                }
                            }
                            return i;
                        }
                    }
                }
            }
            i = panelState.isOpen;
            closePanel(panelState, true);
            if (i != 0) {
                audioManager = (AudioManager) this.mContext.getSystemService("audio");
                if (audioManager == null) {
                    Log.w("AppCompatDelegate", "Couldn't get audio manager");
                } else {
                    audioManager.playSoundEffect(0);
                }
            }
            return i;
        }
        if (this.mDecorContentParent.isOverflowMenuShowing() != 0) {
            i = this.mDecorContentParent.hideOverflowMenu();
        } else if (this.mIsDestroyed == 0 && preparePanel(panelState, keyEvent) != 0) {
            i = this.mDecorContentParent.showOverflowMenu();
        }
        if (i != 0) {
            audioManager = (AudioManager) this.mContext.getSystemService("audio");
            if (audioManager == null) {
                audioManager.playSoundEffect(0);
            } else {
                Log.w("AppCompatDelegate", "Couldn't get audio manager");
            }
        }
        return i;
        i = 0;
        if (i != 0) {
            audioManager = (AudioManager) this.mContext.getSystemService("audio");
            if (audioManager == null) {
                Log.w("AppCompatDelegate", "Couldn't get audio manager");
            } else {
                audioManager.playSoundEffect(0);
            }
        }
        return i;
    }

    void callOnPanelClosed(int i, PanelFeatureState panelFeatureState, Menu menu) {
        if (menu == null) {
            if (panelFeatureState == null && i >= 0 && i < this.mPanels.length) {
                panelFeatureState = this.mPanels[i];
            }
            if (panelFeatureState != null) {
                menu = panelFeatureState.menu;
            }
        }
        if ((panelFeatureState == null || panelFeatureState.isOpen != null) && this.mIsDestroyed == null) {
            this.mOriginalWindowCallback.onPanelClosed(i, menu);
        }
    }

    PanelFeatureState findMenuPanel(Menu menu) {
        PanelFeatureState[] panelFeatureStateArr = this.mPanels;
        int i = 0;
        int length = panelFeatureStateArr != null ? panelFeatureStateArr.length : 0;
        while (i < length) {
            PanelFeatureState panelFeatureState = panelFeatureStateArr[i];
            if (panelFeatureState != null && panelFeatureState.menu == menu) {
                return panelFeatureState;
            }
            i++;
        }
        return null;
    }

    protected PanelFeatureState getPanelState(int i, boolean z) {
        z = this.mPanels;
        if (!z || z.length <= i) {
            Object obj = new PanelFeatureState[(i + 1)];
            if (z) {
                System.arraycopy(z, 0, obj, 0, z.length);
            }
            this.mPanels = obj;
            z = obj;
        }
        PanelFeatureState panelFeatureState = z[i];
        if (panelFeatureState != null) {
            return panelFeatureState;
        }
        panelFeatureState = new PanelFeatureState(i);
        z[i] = panelFeatureState;
        return panelFeatureState;
    }

    private boolean performPanelShortcut(PanelFeatureState panelFeatureState, int i, KeyEvent keyEvent, int i2) {
        boolean z = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((panelFeatureState.isPrepared || preparePanel(panelFeatureState, keyEvent)) && panelFeatureState.menu != null) {
            z = panelFeatureState.menu.performShortcut(i, keyEvent, i2);
        }
        if (z && (i2 & 1) == null && this.mDecorContentParent == null) {
            closePanel(panelFeatureState, 1);
        }
        return z;
    }

    private void invalidatePanelMenu(int i) {
        this.mInvalidatePanelMenuFeatures = (1 << i) | this.mInvalidatePanelMenuFeatures;
        if (this.mInvalidatePanelMenuPosted == 0) {
            ViewCompat.postOnAnimation(this.mWindow.getDecorView(), this.mInvalidatePanelMenuRunnable);
            this.mInvalidatePanelMenuPosted = true;
        }
    }

    void doInvalidatePanelMenu(int i) {
        PanelFeatureState panelState = getPanelState(i, true);
        if (panelState.menu != null) {
            Bundle bundle = new Bundle();
            panelState.menu.saveActionViewStates(bundle);
            if (bundle.size() > 0) {
                panelState.frozenActionViewState = bundle;
            }
            panelState.menu.stopDispatchingItemsChanged();
            panelState.menu.clear();
        }
        panelState.refreshMenuContent = true;
        panelState.refreshDecorView = true;
        if ((i == 108 || i == 0) && this.mDecorContentParent != 0) {
            PanelFeatureState panelState2 = getPanelState(0, false);
            if (panelState2 != null) {
                panelState2.isPrepared = false;
                preparePanel(panelState2, 0);
            }
        }
    }

    int updateStatusGuard(int i) {
        Object obj;
        int i2 = 0;
        if (this.mActionModeView == null || !(this.mActionModeView.getLayoutParams() instanceof MarginLayoutParams)) {
            obj = null;
        } else {
            Object obj2;
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.mActionModeView.getLayoutParams();
            obj = 1;
            if (this.mActionModeView.isShown()) {
                if (this.mTempRect1 == null) {
                    this.mTempRect1 = new Rect();
                    this.mTempRect2 = new Rect();
                }
                Rect rect = this.mTempRect1;
                Rect rect2 = this.mTempRect2;
                rect.set(0, i, 0, 0);
                ViewUtils.computeFitSystemWindows(this.mSubDecor, rect, rect2);
                if (marginLayoutParams.topMargin != (rect2.top == 0 ? i : 0)) {
                    marginLayoutParams.topMargin = i;
                    if (this.mStatusGuard == null) {
                        this.mStatusGuard = new View(this.mContext);
                        this.mStatusGuard.setBackgroundColor(this.mContext.getResources().getColor(C0192R.color.abc_input_method_navigation_guard));
                        this.mSubDecor.addView(this.mStatusGuard, -1, new LayoutParams(-1, i));
                    } else {
                        LayoutParams layoutParams = this.mStatusGuard.getLayoutParams();
                        if (layoutParams.height != i) {
                            layoutParams.height = i;
                            this.mStatusGuard.setLayoutParams(layoutParams);
                        }
                    }
                    obj2 = 1;
                } else {
                    obj2 = null;
                }
                if (this.mStatusGuard == null) {
                    obj = null;
                }
                if (!(this.mOverlayActionMode || r3 == null)) {
                    i = 0;
                }
            } else {
                if (marginLayoutParams.topMargin != 0) {
                    marginLayoutParams.topMargin = 0;
                    obj2 = 1;
                } else {
                    obj2 = null;
                }
                obj = null;
            }
            if (obj2 != null) {
                this.mActionModeView.setLayoutParams(marginLayoutParams);
            }
        }
        if (this.mStatusGuard != null) {
            View view = this.mStatusGuard;
            if (obj == null) {
                i2 = 8;
            }
            view.setVisibility(i2);
        }
        return i;
    }

    private void throwFeatureRequestIfSubDecorInstalled() {
        if (this.mSubDecorInstalled) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    private int sanitizeWindowFeatureId(int i) {
        if (i == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            return 108;
        } else if (i != 9) {
            return i;
        } else {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            return 109;
        }
    }

    ViewGroup getSubDecor() {
        return this.mSubDecor;
    }

    void dismissPopups() {
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r2 = this;
        r0 = r2.mDecorContentParent;
        if (r0 == 0) goto L_0x0009;
    L_0x0004:
        r0 = r2.mDecorContentParent;
        r0.dismissPopups();
    L_0x0009:
        r0 = r2.mActionModePopup;
        if (r0 == 0) goto L_0x0028;
    L_0x000d:
        r0 = r2.mWindow;
        r0 = r0.getDecorView();
        r1 = r2.mShowActionModePopup;
        r0.removeCallbacks(r1);
        r0 = r2.mActionModePopup;
        r0 = r0.isShowing();
        if (r0 == 0) goto L_0x0025;
    L_0x0020:
        r0 = r2.mActionModePopup;	 Catch:{ IllegalArgumentException -> 0x0025 }
        r0.dismiss();	 Catch:{ IllegalArgumentException -> 0x0025 }
    L_0x0025:
        r0 = 0;
        r2.mActionModePopup = r0;
    L_0x0028:
        r2.endOnGoingFadeAnimation();
        r0 = 0;
        r0 = r2.getPanelState(r0, r0);
        if (r0 == 0) goto L_0x003b;
    L_0x0032:
        r1 = r0.menu;
        if (r1 == 0) goto L_0x003b;
    L_0x0036:
        r0 = r0.menu;
        r0.close();
    L_0x003b:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatDelegateImpl.dismissPopups():void");
    }

    public boolean applyDayNight() {
        int nightMode = getNightMode();
        int mapNightMode = mapNightMode(nightMode);
        boolean updateForNightMode = mapNightMode != -1 ? updateForNightMode(mapNightMode) : false;
        if (nightMode == 0) {
            ensureAutoNightModeManager();
            this.mAutoNightModeManager.setup();
        }
        this.mApplyDayNightCalled = true;
        return updateForNightMode;
    }

    public void setLocalNightMode(int i) {
        switch (i) {
            case -1:
            case 0:
            case 1:
            case 2:
                if (this.mLocalNightMode != i) {
                    this.mLocalNightMode = i;
                    if (this.mApplyDayNightCalled != 0) {
                        applyDayNight();
                        return;
                    }
                    return;
                }
                return;
            default:
                Log.i("AppCompatDelegate", "setLocalNightMode() called with an unknown mode");
                return;
        }
    }

    int mapNightMode(int i) {
        if (i == -100) {
            return -1;
        }
        if (i != 0) {
            return i;
        }
        if (VERSION.SDK_INT >= 23 && ((UiModeManager) this.mContext.getSystemService(UiModeManager.class)).getNightMode() == 0) {
            return -1;
        }
        ensureAutoNightModeManager();
        return this.mAutoNightModeManager.getApplyableNightMode();
    }

    private int getNightMode() {
        return this.mLocalNightMode != -100 ? this.mLocalNightMode : AppCompatDelegate.getDefaultNightMode();
    }

    private boolean updateForNightMode(int i) {
        Resources resources = this.mContext.getResources();
        Configuration configuration = resources.getConfiguration();
        int i2 = configuration.uiMode & 48;
        i = i == 2 ? 32 : 16;
        if (i2 == i) {
            return false;
        }
        if (shouldRecreateOnNightModeChange()) {
            ((Activity) this.mContext).recreate();
        } else {
            Configuration configuration2 = new Configuration(configuration);
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            configuration2.uiMode = i | (configuration2.uiMode & -49);
            resources.updateConfiguration(configuration2, displayMetrics);
            if (VERSION.SDK_INT < 26) {
                ResourcesFlusher.flush(resources);
            }
        }
        return true;
    }

    private void ensureAutoNightModeManager() {
        if (this.mAutoNightModeManager == null) {
            this.mAutoNightModeManager = new AutoNightModeManager(TwilightManager.getInstance(this.mContext));
        }
    }

    @VisibleForTesting
    final AutoNightModeManager getAutoNightModeManager() {
        ensureAutoNightModeManager();
        return this.mAutoNightModeManager;
    }

    private boolean shouldRecreateOnNightModeChange() {
        boolean z = false;
        if (!this.mApplyDayNightCalled || !(this.mContext instanceof Activity)) {
            return false;
        }
        try {
            if ((this.mContext.getPackageManager().getActivityInfo(new ComponentName(this.mContext, this.mContext.getClass()), 0).configChanges & 512) == 0) {
                z = true;
            }
            return z;
        } catch (Throwable e) {
            Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e);
            return true;
        }
    }

    public final Delegate getDrawerToggleDelegate() {
        return new ActionBarDrawableToggleImpl();
    }
}
