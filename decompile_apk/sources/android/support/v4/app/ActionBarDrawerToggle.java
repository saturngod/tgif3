package android.support.v4.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.InsetDrawable;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import java.lang.reflect.Method;

@Deprecated
public class ActionBarDrawerToggle implements DrawerListener {
    private static final int ID_HOME = 16908332;
    private static final String TAG = "ActionBarDrawerToggle";
    private static final int[] THEME_ATTRS = new int[]{16843531};
    private static final float TOGGLE_DRAWABLE_OFFSET = 0.33333334f;
    final Activity mActivity;
    private final Delegate mActivityImpl;
    private final int mCloseDrawerContentDescRes;
    private Drawable mDrawerImage;
    private final int mDrawerImageResource;
    private boolean mDrawerIndicatorEnabled;
    private final DrawerLayout mDrawerLayout;
    private boolean mHasCustomUpIndicator;
    private Drawable mHomeAsUpIndicator;
    private final int mOpenDrawerContentDescRes;
    private SetIndicatorInfo mSetIndicatorInfo;
    private SlideDrawable mSlider;

    @Deprecated
    public interface Delegate {
        @Nullable
        Drawable getThemeUpIndicator();

        void setActionBarDescription(@StringRes int i);

        void setActionBarUpIndicator(Drawable drawable, @StringRes int i);
    }

    @Deprecated
    public interface DelegateProvider {
        @Nullable
        Delegate getDrawerToggleDelegate();
    }

    private static class SetIndicatorInfo {
        Method mSetHomeActionContentDescription;
        Method mSetHomeAsUpIndicator;
        ImageView mUpIndicatorView;

        SetIndicatorInfo(android.app.Activity r7) {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
            /*
            r6 = this;
            r6.<init>();
            r0 = 0;
            r1 = 1;
            r2 = android.app.ActionBar.class;	 Catch:{ NoSuchMethodException -> 0x0026 }
            r3 = "setHomeAsUpIndicator";	 Catch:{ NoSuchMethodException -> 0x0026 }
            r4 = new java.lang.Class[r1];	 Catch:{ NoSuchMethodException -> 0x0026 }
            r5 = android.graphics.drawable.Drawable.class;	 Catch:{ NoSuchMethodException -> 0x0026 }
            r4[r0] = r5;	 Catch:{ NoSuchMethodException -> 0x0026 }
            r2 = r2.getDeclaredMethod(r3, r4);	 Catch:{ NoSuchMethodException -> 0x0026 }
            r6.mSetHomeAsUpIndicator = r2;	 Catch:{ NoSuchMethodException -> 0x0026 }
            r2 = android.app.ActionBar.class;	 Catch:{ NoSuchMethodException -> 0x0026 }
            r3 = "setHomeActionContentDescription";	 Catch:{ NoSuchMethodException -> 0x0026 }
            r4 = new java.lang.Class[r1];	 Catch:{ NoSuchMethodException -> 0x0026 }
            r5 = java.lang.Integer.TYPE;	 Catch:{ NoSuchMethodException -> 0x0026 }
            r4[r0] = r5;	 Catch:{ NoSuchMethodException -> 0x0026 }
            r2 = r2.getDeclaredMethod(r3, r4);	 Catch:{ NoSuchMethodException -> 0x0026 }
            r6.mSetHomeActionContentDescription = r2;	 Catch:{ NoSuchMethodException -> 0x0026 }
            return;
            r2 = 16908332; // 0x102002c float:2.3877352E-38 double:8.353826E-317;
            r7 = r7.findViewById(r2);
            if (r7 != 0) goto L_0x0031;
        L_0x0030:
            return;
        L_0x0031:
            r7 = r7.getParent();
            r7 = (android.view.ViewGroup) r7;
            r3 = r7.getChildCount();
            r4 = 2;
            if (r3 == r4) goto L_0x003f;
        L_0x003e:
            return;
        L_0x003f:
            r0 = r7.getChildAt(r0);
            r7 = r7.getChildAt(r1);
            r1 = r0.getId();
            if (r1 != r2) goto L_0x004e;
        L_0x004d:
            goto L_0x004f;
        L_0x004e:
            r7 = r0;
        L_0x004f:
            r0 = r7 instanceof android.widget.ImageView;
            if (r0 == 0) goto L_0x0057;
        L_0x0053:
            r7 = (android.widget.ImageView) r7;
            r6.mUpIndicatorView = r7;
        L_0x0057:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.ActionBarDrawerToggle.SetIndicatorInfo.<init>(android.app.Activity):void");
        }
    }

    private class SlideDrawable extends InsetDrawable implements Callback {
        private final boolean mHasMirroring;
        private float mOffset;
        private float mPosition;
        private final Rect mTmpRect;

        SlideDrawable(Drawable drawable) {
            ActionBarDrawerToggle actionBarDrawerToggle = null;
            super(drawable, 0);
            if (VERSION.SDK_INT > 18) {
                actionBarDrawerToggle = true;
            }
            this.mHasMirroring = actionBarDrawerToggle;
            this.mTmpRect = new Rect();
        }

        public void setPosition(float f) {
            this.mPosition = f;
            invalidateSelf();
        }

        public float getPosition() {
            return this.mPosition;
        }

        public void setOffset(float f) {
            this.mOffset = f;
            invalidateSelf();
        }

        public void draw(@NonNull Canvas canvas) {
            copyBounds(this.mTmpRect);
            canvas.save();
            int i = 1;
            Object obj = ViewCompat.getLayoutDirection(ActionBarDrawerToggle.this.mActivity.getWindow().getDecorView()) == 1 ? 1 : null;
            if (obj != null) {
                i = -1;
            }
            float width = (float) this.mTmpRect.width();
            canvas.translate((((-this.mOffset) * width) * this.mPosition) * ((float) i), 0.0f);
            if (!(obj == null || this.mHasMirroring)) {
                canvas.translate(width, 0.0f);
                canvas.scale(-1.0f, 1.0f);
            }
            super.draw(canvas);
            canvas.restore();
        }
    }

    public void onDrawerStateChanged(int i) {
    }

    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, @DrawableRes int i, @StringRes int i2, @StringRes int i3) {
        this(activity, drawerLayout, assumeMaterial(activity) ^ 1, i, i2, i3);
    }

    private static boolean assumeMaterial(Context context) {
        return (context.getApplicationInfo().targetSdkVersion < 21 || VERSION.SDK_INT < 21) ? null : true;
    }

    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, boolean z, @DrawableRes int i, @StringRes int i2, @StringRes int i3) {
        this.mDrawerIndicatorEnabled = true;
        this.mActivity = activity;
        if (activity instanceof DelegateProvider) {
            this.mActivityImpl = ((DelegateProvider) activity).getDrawerToggleDelegate();
        } else {
            this.mActivityImpl = null;
        }
        this.mDrawerLayout = drawerLayout;
        this.mDrawerImageResource = i;
        this.mOpenDrawerContentDescRes = i2;
        this.mCloseDrawerContentDescRes = i3;
        this.mHomeAsUpIndicator = getThemeUpIndicator();
        this.mDrawerImage = ContextCompat.getDrawable(activity, i);
        this.mSlider = new SlideDrawable(this.mDrawerImage);
        this.mSlider.setOffset(z ? 1051372203 : null);
    }

    public void syncState() {
        if (this.mDrawerLayout.isDrawerOpen((int) GravityCompat.START)) {
            this.mSlider.setPosition(1.0f);
        } else {
            this.mSlider.setPosition(0.0f);
        }
        if (this.mDrawerIndicatorEnabled) {
            setActionBarUpIndicator(this.mSlider, this.mDrawerLayout.isDrawerOpen((int) GravityCompat.START) ? this.mCloseDrawerContentDescRes : this.mOpenDrawerContentDescRes);
        }
    }

    public void setHomeAsUpIndicator(Drawable drawable) {
        if (drawable == null) {
            this.mHomeAsUpIndicator = getThemeUpIndicator();
            this.mHasCustomUpIndicator = false;
        } else {
            this.mHomeAsUpIndicator = drawable;
            this.mHasCustomUpIndicator = true;
        }
        if (this.mDrawerIndicatorEnabled == null) {
            setActionBarUpIndicator(this.mHomeAsUpIndicator, 0);
        }
    }

    public void setHomeAsUpIndicator(int i) {
        setHomeAsUpIndicator(i != 0 ? ContextCompat.getDrawable(this.mActivity, i) : 0);
    }

    public void setDrawerIndicatorEnabled(boolean z) {
        if (z != this.mDrawerIndicatorEnabled) {
            if (z) {
                setActionBarUpIndicator(this.mSlider, this.mDrawerLayout.isDrawerOpen((int) GravityCompat.START) ? this.mCloseDrawerContentDescRes : this.mOpenDrawerContentDescRes);
            } else {
                setActionBarUpIndicator(this.mHomeAsUpIndicator, 0);
            }
            this.mDrawerIndicatorEnabled = z;
        }
    }

    public boolean isDrawerIndicatorEnabled() {
        return this.mDrawerIndicatorEnabled;
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (this.mHasCustomUpIndicator == null) {
            this.mHomeAsUpIndicator = getThemeUpIndicator();
        }
        this.mDrawerImage = ContextCompat.getDrawable(this.mActivity, this.mDrawerImageResource);
        syncState();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem == null || menuItem.getItemId() != ID_HOME || this.mDrawerIndicatorEnabled == null) {
            return null;
        }
        if (this.mDrawerLayout.isDrawerVisible((int) GravityCompat.START) != null) {
            this.mDrawerLayout.closeDrawer((int) GravityCompat.START);
        } else {
            this.mDrawerLayout.openDrawer((int) GravityCompat.START);
        }
        return true;
    }

    public void onDrawerSlide(View view, float f) {
        view = this.mSlider.getPosition();
        if (f > 0.5f) {
            view = Math.max(view, Math.max(0.0f, f - 0.5f) * 2.0f);
        } else {
            view = Math.min(view, f * 2.0f);
        }
        this.mSlider.setPosition(view);
    }

    public void onDrawerOpened(View view) {
        this.mSlider.setPosition(1.0f);
        if (this.mDrawerIndicatorEnabled != null) {
            setActionBarDescription(this.mCloseDrawerContentDescRes);
        }
    }

    public void onDrawerClosed(View view) {
        this.mSlider.setPosition(0.0f);
        if (this.mDrawerIndicatorEnabled != null) {
            setActionBarDescription(this.mOpenDrawerContentDescRes);
        }
    }

    private Drawable getThemeUpIndicator() {
        if (this.mActivityImpl != null) {
            return this.mActivityImpl.getThemeUpIndicator();
        }
        if (VERSION.SDK_INT >= 18) {
            Context themedContext;
            ActionBar actionBar = this.mActivity.getActionBar();
            if (actionBar != null) {
                themedContext = actionBar.getThemedContext();
            } else {
                themedContext = this.mActivity;
            }
            TypedArray obtainStyledAttributes = themedContext.obtainStyledAttributes(null, THEME_ATTRS, 16843470, 0);
            Drawable drawable = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            return drawable;
        }
        obtainStyledAttributes = this.mActivity.obtainStyledAttributes(THEME_ATTRS);
        drawable = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        return drawable;
    }

    private void setActionBarUpIndicator(Drawable drawable, int i) {
        if (this.mActivityImpl != null) {
            this.mActivityImpl.setActionBarUpIndicator(drawable, i);
            return;
        }
        ActionBar actionBar;
        if (VERSION.SDK_INT >= 18) {
            actionBar = this.mActivity.getActionBar();
            if (actionBar != null) {
                actionBar.setHomeAsUpIndicator(drawable);
                actionBar.setHomeActionContentDescription(i);
            }
        } else {
            if (this.mSetIndicatorInfo == null) {
                this.mSetIndicatorInfo = new SetIndicatorInfo(this.mActivity);
            }
            if (this.mSetIndicatorInfo.mSetHomeAsUpIndicator != null) {
                try {
                    actionBar = this.mActivity.getActionBar();
                    this.mSetIndicatorInfo.mSetHomeAsUpIndicator.invoke(actionBar, new Object[]{drawable});
                    this.mSetIndicatorInfo.mSetHomeActionContentDescription.invoke(actionBar, new Object[]{Integer.valueOf(i)});
                } catch (Drawable drawable2) {
                    Log.w(TAG, "Couldn't set home-as-up indicator via JB-MR2 API", drawable2);
                }
            } else if (this.mSetIndicatorInfo.mUpIndicatorView != 0) {
                this.mSetIndicatorInfo.mUpIndicatorView.setImageDrawable(drawable2);
            } else {
                Log.w(TAG, "Couldn't set home-as-up indicator");
            }
        }
    }

    private void setActionBarDescription(int i) {
        if (this.mActivityImpl != null) {
            this.mActivityImpl.setActionBarDescription(i);
            return;
        }
        ActionBar actionBar;
        if (VERSION.SDK_INT >= 18) {
            actionBar = this.mActivity.getActionBar();
            if (actionBar != null) {
                actionBar.setHomeActionContentDescription(i);
            }
        } else {
            if (this.mSetIndicatorInfo == null) {
                this.mSetIndicatorInfo = new SetIndicatorInfo(this.mActivity);
            }
            if (this.mSetIndicatorInfo.mSetHomeAsUpIndicator != null) {
                try {
                    actionBar = this.mActivity.getActionBar();
                    this.mSetIndicatorInfo.mSetHomeActionContentDescription.invoke(actionBar, new Object[]{Integer.valueOf(i)});
                    actionBar.setSubtitle(actionBar.getSubtitle());
                } catch (int i2) {
                    Log.w(TAG, "Couldn't set content description via JB-MR2 API", i2);
                }
            }
        }
    }
}
