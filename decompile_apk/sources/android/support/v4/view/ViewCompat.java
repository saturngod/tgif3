package android.support.v4.view;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.UiThread;
import android.support.compat.C0014R;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.KeyEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.View.OnUnhandledKeyEventListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ViewCompat {
    public static final int ACCESSIBILITY_LIVE_REGION_ASSERTIVE = 2;
    public static final int ACCESSIBILITY_LIVE_REGION_NONE = 0;
    public static final int ACCESSIBILITY_LIVE_REGION_POLITE = 1;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_AUTO = 0;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO = 2;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS = 4;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_YES = 1;
    @Deprecated
    public static final int LAYER_TYPE_HARDWARE = 2;
    @Deprecated
    public static final int LAYER_TYPE_NONE = 0;
    @Deprecated
    public static final int LAYER_TYPE_SOFTWARE = 1;
    public static final int LAYOUT_DIRECTION_INHERIT = 2;
    public static final int LAYOUT_DIRECTION_LOCALE = 3;
    public static final int LAYOUT_DIRECTION_LTR = 0;
    public static final int LAYOUT_DIRECTION_RTL = 1;
    @Deprecated
    public static final int MEASURED_HEIGHT_STATE_SHIFT = 16;
    @Deprecated
    public static final int MEASURED_SIZE_MASK = 16777215;
    @Deprecated
    public static final int MEASURED_STATE_MASK = -16777216;
    @Deprecated
    public static final int MEASURED_STATE_TOO_SMALL = 16777216;
    @Deprecated
    public static final int OVER_SCROLL_ALWAYS = 0;
    @Deprecated
    public static final int OVER_SCROLL_IF_CONTENT_SCROLLS = 1;
    @Deprecated
    public static final int OVER_SCROLL_NEVER = 2;
    public static final int SCROLL_AXIS_HORIZONTAL = 1;
    public static final int SCROLL_AXIS_NONE = 0;
    public static final int SCROLL_AXIS_VERTICAL = 2;
    public static final int SCROLL_INDICATOR_BOTTOM = 2;
    public static final int SCROLL_INDICATOR_END = 32;
    public static final int SCROLL_INDICATOR_LEFT = 4;
    public static final int SCROLL_INDICATOR_RIGHT = 8;
    public static final int SCROLL_INDICATOR_START = 16;
    public static final int SCROLL_INDICATOR_TOP = 1;
    private static final String TAG = "ViewCompat";
    public static final int TYPE_NON_TOUCH = 1;
    public static final int TYPE_TOUCH = 0;
    private static boolean sAccessibilityDelegateCheckFailed = false;
    private static Field sAccessibilityDelegateField;
    private static Method sChildrenDrawingOrderMethod;
    private static Method sDispatchFinishTemporaryDetach;
    private static Method sDispatchStartTemporaryDetach;
    private static Field sMinHeightField;
    private static boolean sMinHeightFieldFetched;
    private static Field sMinWidthField;
    private static boolean sMinWidthFieldFetched;
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
    private static boolean sTempDetachBound;
    private static ThreadLocal<Rect> sThreadLocalRect;
    private static WeakHashMap<View, String> sTransitionNameMap;
    private static WeakHashMap<View, ViewPropertyAnimatorCompat> sViewPropertyAnimatorMap = null;

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FocusDirection {
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FocusRealDirection {
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FocusRelativeDirection {
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface NestedScrollType {
    }

    public interface OnUnhandledKeyEventListenerCompat {
        boolean onUnhandledKeyEvent(View view, KeyEvent keyEvent);
    }

    @RequiresApi(28)
    private static class OnUnhandledKeyEventListenerWrapper implements OnUnhandledKeyEventListener {
        private OnUnhandledKeyEventListenerCompat mCompatListener;

        OnUnhandledKeyEventListenerWrapper(OnUnhandledKeyEventListenerCompat onUnhandledKeyEventListenerCompat) {
            this.mCompatListener = onUnhandledKeyEventListenerCompat;
        }

        public boolean onUnhandledKeyEvent(View view, KeyEvent keyEvent) {
            return this.mCompatListener.onUnhandledKeyEvent(view, keyEvent);
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ScrollAxis {
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ScrollIndicators {
    }

    static class UnhandledKeyEventManager {
        private static final ArrayList<WeakReference<View>> sViewsWithListeners = new ArrayList();
        private SparseArray<WeakReference<View>> mCapturedKeys = null;
        private WeakReference<KeyEvent> mLastDispatchedPreViewKeyEvent = null;
        @Nullable
        private WeakHashMap<View, Boolean> mViewsContainingListeners = null;

        UnhandledKeyEventManager() {
        }

        private SparseArray<WeakReference<View>> getCapturedKeys() {
            if (this.mCapturedKeys == null) {
                this.mCapturedKeys = new SparseArray();
            }
            return this.mCapturedKeys;
        }

        static UnhandledKeyEventManager at(View view) {
            UnhandledKeyEventManager unhandledKeyEventManager = (UnhandledKeyEventManager) view.getTag(C0014R.id.tag_unhandled_key_event_manager);
            if (unhandledKeyEventManager != null) {
                return unhandledKeyEventManager;
            }
            unhandledKeyEventManager = new UnhandledKeyEventManager();
            view.setTag(C0014R.id.tag_unhandled_key_event_manager, unhandledKeyEventManager);
            return unhandledKeyEventManager;
        }

        boolean dispatch(View view, KeyEvent keyEvent) {
            if (keyEvent.getAction() == 0) {
                recalcViewsWithUnhandled();
            }
            view = dispatchInOrder(view, keyEvent);
            if (keyEvent.getAction() == 0) {
                keyEvent = keyEvent.getKeyCode();
                if (!(view == null || KeyEvent.isModifierKey(keyEvent))) {
                    getCapturedKeys().put(keyEvent, new WeakReference(view));
                }
            }
            return view != null ? true : null;
        }

        @Nullable
        private View dispatchInOrder(View view, KeyEvent keyEvent) {
            if (this.mViewsContainingListeners != null) {
                if (this.mViewsContainingListeners.containsKey(view)) {
                    if (view instanceof ViewGroup) {
                        ViewGroup viewGroup = (ViewGroup) view;
                        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                            View dispatchInOrder = dispatchInOrder(viewGroup.getChildAt(childCount), keyEvent);
                            if (dispatchInOrder != null) {
                                return dispatchInOrder;
                            }
                        }
                    }
                    if (onUnhandledKeyEvent(view, keyEvent) != null) {
                        return view;
                    }
                    return null;
                }
            }
            return null;
        }

        boolean preDispatch(KeyEvent keyEvent) {
            if (this.mLastDispatchedPreViewKeyEvent != null && this.mLastDispatchedPreViewKeyEvent.get() == keyEvent) {
                return false;
            }
            this.mLastDispatchedPreViewKeyEvent = new WeakReference(keyEvent);
            WeakReference weakReference = null;
            SparseArray capturedKeys = getCapturedKeys();
            if (keyEvent.getAction() == 1) {
                int indexOfKey = capturedKeys.indexOfKey(keyEvent.getKeyCode());
                if (indexOfKey >= 0) {
                    weakReference = (WeakReference) capturedKeys.valueAt(indexOfKey);
                    capturedKeys.removeAt(indexOfKey);
                }
            }
            if (weakReference == null) {
                weakReference = (WeakReference) capturedKeys.get(keyEvent.getKeyCode());
            }
            if (weakReference == null) {
                return false;
            }
            View view = (View) weakReference.get();
            if (view != null && ViewCompat.isAttachedToWindow(view)) {
                onUnhandledKeyEvent(view, keyEvent);
            }
            return true;
        }

        private boolean onUnhandledKeyEvent(@NonNull View view, @NonNull KeyEvent keyEvent) {
            ArrayList arrayList = (ArrayList) view.getTag(C0014R.id.tag_unhandled_key_listeners);
            if (arrayList != null) {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    if (((OnUnhandledKeyEventListenerCompat) arrayList.get(size)).onUnhandledKeyEvent(view, keyEvent)) {
                        return true;
                    }
                }
            }
            return null;
        }

        static void registerListeningView(View view) {
            synchronized (sViewsWithListeners) {
                Iterator it = sViewsWithListeners.iterator();
                while (it.hasNext()) {
                    if (((WeakReference) it.next()).get() == view) {
                        return;
                    }
                }
                sViewsWithListeners.add(new WeakReference(view));
            }
        }

        static void unregisterListeningView(View view) {
            synchronized (sViewsWithListeners) {
                for (int i = 0; i < sViewsWithListeners.size(); i++) {
                    if (((WeakReference) sViewsWithListeners.get(i)).get() == view) {
                        sViewsWithListeners.remove(i);
                        return;
                    }
                }
            }
        }

        private void recalcViewsWithUnhandled() {
            if (this.mViewsContainingListeners != null) {
                this.mViewsContainingListeners.clear();
            }
            if (!sViewsWithListeners.isEmpty()) {
                synchronized (sViewsWithListeners) {
                    if (this.mViewsContainingListeners == null) {
                        this.mViewsContainingListeners = new WeakHashMap();
                    }
                    for (int size = sViewsWithListeners.size() - 1; size >= 0; size--) {
                        View view = (View) ((WeakReference) sViewsWithListeners.get(size)).get();
                        if (view == null) {
                            sViewsWithListeners.remove(size);
                        } else {
                            this.mViewsContainingListeners.put(view, Boolean.TRUE);
                            for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                                this.mViewsContainingListeners.put((View) parent, Boolean.TRUE);
                            }
                        }
                    }
                }
            }
        }
    }

    private static Rect getEmptyTempRect() {
        if (sThreadLocalRect == null) {
            sThreadLocalRect = new ThreadLocal();
        }
        Rect rect = (Rect) sThreadLocalRect.get();
        if (rect == null) {
            rect = new Rect();
            sThreadLocalRect.set(rect);
        }
        rect.setEmpty();
        return rect;
    }

    @Deprecated
    public static boolean canScrollHorizontally(View view, int i) {
        return view.canScrollHorizontally(i);
    }

    @Deprecated
    public static boolean canScrollVertically(View view, int i) {
        return view.canScrollVertically(i);
    }

    @Deprecated
    public static int getOverScrollMode(View view) {
        return view.getOverScrollMode();
    }

    @Deprecated
    public static void setOverScrollMode(View view, int i) {
        view.setOverScrollMode(i);
    }

    @Deprecated
    public static void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        view.onPopulateAccessibilityEvent(accessibilityEvent);
    }

    @Deprecated
    public static void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        view.onInitializeAccessibilityEvent(accessibilityEvent);
    }

    public static void onInitializeAccessibilityNodeInfo(@NonNull View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        view.onInitializeAccessibilityNodeInfo(accessibilityNodeInfoCompat.unwrap());
    }

    public static void setAccessibilityDelegate(@NonNull View view, AccessibilityDelegateCompat accessibilityDelegateCompat) {
        view.setAccessibilityDelegate(accessibilityDelegateCompat == null ? null : accessibilityDelegateCompat.getBridge());
    }

    public static void setAutofillHints(@NonNull View view, @Nullable String... strArr) {
        if (VERSION.SDK_INT >= 26) {
            view.setAutofillHints(strArr);
        }
    }

    @SuppressLint({"InlinedApi"})
    public static int getImportantForAutofill(@NonNull View view) {
        return VERSION.SDK_INT >= 26 ? view.getImportantForAutofill() : null;
    }

    public static void setImportantForAutofill(@NonNull View view, int i) {
        if (VERSION.SDK_INT >= 26) {
            view.setImportantForAutofill(i);
        }
    }

    public static boolean isImportantForAutofill(@NonNull View view) {
        return VERSION.SDK_INT >= 26 ? view.isImportantForAutofill() : true;
    }

    public static boolean hasAccessibilityDelegate(@android.support.annotation.NonNull android.view.View r4) {
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
        r0 = sAccessibilityDelegateCheckFailed;
        r1 = 0;
        if (r0 == 0) goto L_0x0006;
    L_0x0005:
        return r1;
    L_0x0006:
        r0 = sAccessibilityDelegateField;
        r2 = 1;
        if (r0 != 0) goto L_0x001e;
    L_0x000b:
        r0 = android.view.View.class;	 Catch:{ Throwable -> 0x001b }
        r3 = "mAccessibilityDelegate";	 Catch:{ Throwable -> 0x001b }
        r0 = r0.getDeclaredField(r3);	 Catch:{ Throwable -> 0x001b }
        sAccessibilityDelegateField = r0;	 Catch:{ Throwable -> 0x001b }
        r0 = sAccessibilityDelegateField;	 Catch:{ Throwable -> 0x001b }
        r0.setAccessible(r2);	 Catch:{ Throwable -> 0x001b }
        goto L_0x001e;
    L_0x001b:
        sAccessibilityDelegateCheckFailed = r2;
        return r1;
    L_0x001e:
        r0 = sAccessibilityDelegateField;	 Catch:{ Throwable -> 0x0028 }
        r4 = r0.get(r4);	 Catch:{ Throwable -> 0x0028 }
        if (r4 == 0) goto L_0x0027;
    L_0x0026:
        r1 = 1;
    L_0x0027:
        return r1;
    L_0x0028:
        sAccessibilityDelegateCheckFailed = r2;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewCompat.hasAccessibilityDelegate(android.view.View):boolean");
    }

    public static boolean hasTransientState(@NonNull View view) {
        return VERSION.SDK_INT >= 16 ? view.hasTransientState() : null;
    }

    public static void setHasTransientState(@NonNull View view, boolean z) {
        if (VERSION.SDK_INT >= 16) {
            view.setHasTransientState(z);
        }
    }

    public static void postInvalidateOnAnimation(@NonNull View view) {
        if (VERSION.SDK_INT >= 16) {
            view.postInvalidateOnAnimation();
        } else {
            view.postInvalidate();
        }
    }

    public static void postInvalidateOnAnimation(@NonNull View view, int i, int i2, int i3, int i4) {
        if (VERSION.SDK_INT >= 16) {
            view.postInvalidateOnAnimation(i, i2, i3, i4);
        } else {
            view.postInvalidate(i, i2, i3, i4);
        }
    }

    public static void postOnAnimation(@NonNull View view, Runnable runnable) {
        if (VERSION.SDK_INT >= 16) {
            view.postOnAnimation(runnable);
        } else {
            view.postDelayed(runnable, ValueAnimator.getFrameDelay());
        }
    }

    public static void postOnAnimationDelayed(@NonNull View view, Runnable runnable, long j) {
        if (VERSION.SDK_INT >= 16) {
            view.postOnAnimationDelayed(runnable, j);
        } else {
            view.postDelayed(runnable, ValueAnimator.getFrameDelay() + j);
        }
    }

    public static int getImportantForAccessibility(@NonNull View view) {
        return VERSION.SDK_INT >= 16 ? view.getImportantForAccessibility() : null;
    }

    public static void setImportantForAccessibility(@NonNull View view, int i) {
        if (VERSION.SDK_INT >= 19) {
            view.setImportantForAccessibility(i);
        } else if (VERSION.SDK_INT >= 16) {
            if (i == 4) {
                i = 2;
            }
            view.setImportantForAccessibility(i);
        }
    }

    public static boolean isImportantForAccessibility(@NonNull View view) {
        return VERSION.SDK_INT >= 21 ? view.isImportantForAccessibility() : true;
    }

    public static boolean performAccessibilityAction(@NonNull View view, int i, Bundle bundle) {
        return VERSION.SDK_INT >= 16 ? view.performAccessibilityAction(i, bundle) : null;
    }

    public static AccessibilityNodeProviderCompat getAccessibilityNodeProvider(@NonNull View view) {
        if (VERSION.SDK_INT >= 16) {
            view = view.getAccessibilityNodeProvider();
            if (view != null) {
                return new AccessibilityNodeProviderCompat(view);
            }
        }
        return null;
    }

    @Deprecated
    public static float getAlpha(View view) {
        return view.getAlpha();
    }

    @Deprecated
    public static void setLayerType(View view, int i, Paint paint) {
        view.setLayerType(i, paint);
    }

    @Deprecated
    public static int getLayerType(View view) {
        return view.getLayerType();
    }

    public static int getLabelFor(@NonNull View view) {
        return VERSION.SDK_INT >= 17 ? view.getLabelFor() : null;
    }

    public static void setLabelFor(@NonNull View view, @IdRes int i) {
        if (VERSION.SDK_INT >= 17) {
            view.setLabelFor(i);
        }
    }

    public static void setLayerPaint(@NonNull View view, Paint paint) {
        if (VERSION.SDK_INT >= 17) {
            view.setLayerPaint(paint);
            return;
        }
        view.setLayerType(view.getLayerType(), paint);
        view.invalidate();
    }

    public static int getLayoutDirection(@NonNull View view) {
        return VERSION.SDK_INT >= 17 ? view.getLayoutDirection() : null;
    }

    public static void setLayoutDirection(@NonNull View view, int i) {
        if (VERSION.SDK_INT >= 17) {
            view.setLayoutDirection(i);
        }
    }

    public static ViewParent getParentForAccessibility(@NonNull View view) {
        if (VERSION.SDK_INT >= 16) {
            return view.getParentForAccessibility();
        }
        return view.getParent();
    }

    @NonNull
    public static <T extends View> T requireViewById(@NonNull View view, @IdRes int i) {
        if (VERSION.SDK_INT >= 28) {
            return view.requireViewById(i);
        }
        view = view.findViewById(i);
        if (view != null) {
            return view;
        }
        throw new IllegalArgumentException("ID does not reference a View inside this View");
    }

    @Deprecated
    public static boolean isOpaque(View view) {
        return view.isOpaque();
    }

    @Deprecated
    public static int resolveSizeAndState(int i, int i2, int i3) {
        return View.resolveSizeAndState(i, i2, i3);
    }

    @Deprecated
    public static int getMeasuredWidthAndState(View view) {
        return view.getMeasuredWidthAndState();
    }

    @Deprecated
    public static int getMeasuredHeightAndState(View view) {
        return view.getMeasuredHeightAndState();
    }

    @Deprecated
    public static int getMeasuredState(View view) {
        return view.getMeasuredState();
    }

    @Deprecated
    public static int combineMeasuredStates(int i, int i2) {
        return View.combineMeasuredStates(i, i2);
    }

    public static int getAccessibilityLiveRegion(@NonNull View view) {
        return VERSION.SDK_INT >= 19 ? view.getAccessibilityLiveRegion() : null;
    }

    public static void setAccessibilityLiveRegion(@NonNull View view, int i) {
        if (VERSION.SDK_INT >= 19) {
            view.setAccessibilityLiveRegion(i);
        }
    }

    @Px
    public static int getPaddingStart(@NonNull View view) {
        if (VERSION.SDK_INT >= 17) {
            return view.getPaddingStart();
        }
        return view.getPaddingLeft();
    }

    @Px
    public static int getPaddingEnd(@NonNull View view) {
        if (VERSION.SDK_INT >= 17) {
            return view.getPaddingEnd();
        }
        return view.getPaddingRight();
    }

    public static void setPaddingRelative(@NonNull View view, @Px int i, @Px int i2, @Px int i3, @Px int i4) {
        if (VERSION.SDK_INT >= 17) {
            view.setPaddingRelative(i, i2, i3, i4);
        } else {
            view.setPadding(i, i2, i3, i4);
        }
    }

    private static void bindTempDetach() {
        try {
            sDispatchStartTemporaryDetach = View.class.getDeclaredMethod("dispatchStartTemporaryDetach", new Class[0]);
            sDispatchFinishTemporaryDetach = View.class.getDeclaredMethod("dispatchFinishTemporaryDetach", new Class[0]);
        } catch (Throwable e) {
            Log.e(TAG, "Couldn't find method", e);
        }
        sTempDetachBound = true;
    }

    public static void dispatchStartTemporaryDetach(@NonNull View view) {
        if (VERSION.SDK_INT >= 24) {
            view.dispatchStartTemporaryDetach();
            return;
        }
        if (!sTempDetachBound) {
            bindTempDetach();
        }
        if (sDispatchStartTemporaryDetach != null) {
            try {
                sDispatchStartTemporaryDetach.invoke(view, new Object[0]);
                return;
            } catch (View view2) {
                Log.d(TAG, "Error calling dispatchStartTemporaryDetach", view2);
                return;
            }
        }
        view2.onStartTemporaryDetach();
    }

    public static void dispatchFinishTemporaryDetach(@NonNull View view) {
        if (VERSION.SDK_INT >= 24) {
            view.dispatchFinishTemporaryDetach();
            return;
        }
        if (!sTempDetachBound) {
            bindTempDetach();
        }
        if (sDispatchFinishTemporaryDetach != null) {
            try {
                sDispatchFinishTemporaryDetach.invoke(view, new Object[0]);
                return;
            } catch (View view2) {
                Log.d(TAG, "Error calling dispatchFinishTemporaryDetach", view2);
                return;
            }
        }
        view2.onFinishTemporaryDetach();
    }

    @Deprecated
    public static float getTranslationX(View view) {
        return view.getTranslationX();
    }

    @Deprecated
    public static float getTranslationY(View view) {
        return view.getTranslationY();
    }

    @Nullable
    @Deprecated
    public static Matrix getMatrix(View view) {
        return view.getMatrix();
    }

    public static int getMinimumWidth(@android.support.annotation.NonNull android.view.View r3) {
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
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 16;
        if (r0 < r1) goto L_0x000b;
    L_0x0006:
        r3 = r3.getMinimumWidth();
        return r3;
    L_0x000b:
        r0 = sMinWidthFieldFetched;
        if (r0 != 0) goto L_0x0021;
    L_0x000f:
        r0 = 1;
        r1 = android.view.View.class;	 Catch:{ NoSuchFieldException -> 0x001f }
        r2 = "mMinWidth";	 Catch:{ NoSuchFieldException -> 0x001f }
        r1 = r1.getDeclaredField(r2);	 Catch:{ NoSuchFieldException -> 0x001f }
        sMinWidthField = r1;	 Catch:{ NoSuchFieldException -> 0x001f }
        r1 = sMinWidthField;	 Catch:{ NoSuchFieldException -> 0x001f }
        r1.setAccessible(r0);	 Catch:{ NoSuchFieldException -> 0x001f }
    L_0x001f:
        sMinWidthFieldFetched = r0;
    L_0x0021:
        r0 = sMinWidthField;
        if (r0 == 0) goto L_0x0032;
    L_0x0025:
        r0 = sMinWidthField;	 Catch:{ Exception -> 0x0032 }
        r3 = r0.get(r3);	 Catch:{ Exception -> 0x0032 }
        r3 = (java.lang.Integer) r3;	 Catch:{ Exception -> 0x0032 }
        r3 = r3.intValue();	 Catch:{ Exception -> 0x0032 }
        return r3;
    L_0x0032:
        r3 = 0;
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewCompat.getMinimumWidth(android.view.View):int");
    }

    public static int getMinimumHeight(@android.support.annotation.NonNull android.view.View r3) {
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
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 16;
        if (r0 < r1) goto L_0x000b;
    L_0x0006:
        r3 = r3.getMinimumHeight();
        return r3;
    L_0x000b:
        r0 = sMinHeightFieldFetched;
        if (r0 != 0) goto L_0x0021;
    L_0x000f:
        r0 = 1;
        r1 = android.view.View.class;	 Catch:{ NoSuchFieldException -> 0x001f }
        r2 = "mMinHeight";	 Catch:{ NoSuchFieldException -> 0x001f }
        r1 = r1.getDeclaredField(r2);	 Catch:{ NoSuchFieldException -> 0x001f }
        sMinHeightField = r1;	 Catch:{ NoSuchFieldException -> 0x001f }
        r1 = sMinHeightField;	 Catch:{ NoSuchFieldException -> 0x001f }
        r1.setAccessible(r0);	 Catch:{ NoSuchFieldException -> 0x001f }
    L_0x001f:
        sMinHeightFieldFetched = r0;
    L_0x0021:
        r0 = sMinHeightField;
        if (r0 == 0) goto L_0x0032;
    L_0x0025:
        r0 = sMinHeightField;	 Catch:{ Exception -> 0x0032 }
        r3 = r0.get(r3);	 Catch:{ Exception -> 0x0032 }
        r3 = (java.lang.Integer) r3;	 Catch:{ Exception -> 0x0032 }
        r3 = r3.intValue();	 Catch:{ Exception -> 0x0032 }
        return r3;
    L_0x0032:
        r3 = 0;
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewCompat.getMinimumHeight(android.view.View):int");
    }

    @NonNull
    public static ViewPropertyAnimatorCompat animate(@NonNull View view) {
        if (sViewPropertyAnimatorMap == null) {
            sViewPropertyAnimatorMap = new WeakHashMap();
        }
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = (ViewPropertyAnimatorCompat) sViewPropertyAnimatorMap.get(view);
        if (viewPropertyAnimatorCompat != null) {
            return viewPropertyAnimatorCompat;
        }
        viewPropertyAnimatorCompat = new ViewPropertyAnimatorCompat(view);
        sViewPropertyAnimatorMap.put(view, viewPropertyAnimatorCompat);
        return viewPropertyAnimatorCompat;
    }

    @Deprecated
    public static void setTranslationX(View view, float f) {
        view.setTranslationX(f);
    }

    @Deprecated
    public static void setTranslationY(View view, float f) {
        view.setTranslationY(f);
    }

    @Deprecated
    public static void setAlpha(View view, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        view.setAlpha(f);
    }

    @Deprecated
    public static void setX(View view, float f) {
        view.setX(f);
    }

    @Deprecated
    public static void setY(View view, float f) {
        view.setY(f);
    }

    @Deprecated
    public static void setRotation(View view, float f) {
        view.setRotation(f);
    }

    @Deprecated
    public static void setRotationX(View view, float f) {
        view.setRotationX(f);
    }

    @Deprecated
    public static void setRotationY(View view, float f) {
        view.setRotationY(f);
    }

    @Deprecated
    public static void setScaleX(View view, float f) {
        view.setScaleX(f);
    }

    @Deprecated
    public static void setScaleY(View view, float f) {
        view.setScaleY(f);
    }

    @Deprecated
    public static float getPivotX(View view) {
        return view.getPivotX();
    }

    @Deprecated
    public static void setPivotX(View view, float f) {
        view.setPivotX(f);
    }

    @Deprecated
    public static float getPivotY(View view) {
        return view.getPivotY();
    }

    @Deprecated
    public static void setPivotY(View view, float f) {
        view.setPivotY(f);
    }

    @Deprecated
    public static float getRotation(View view) {
        return view.getRotation();
    }

    @Deprecated
    public static float getRotationX(View view) {
        return view.getRotationX();
    }

    @Deprecated
    public static float getRotationY(View view) {
        return view.getRotationY();
    }

    @Deprecated
    public static float getScaleX(View view) {
        return view.getScaleX();
    }

    @Deprecated
    public static float getScaleY(View view) {
        return view.getScaleY();
    }

    @Deprecated
    public static float getX(View view) {
        return view.getX();
    }

    @Deprecated
    public static float getY(View view) {
        return view.getY();
    }

    public static void setElevation(@NonNull View view, float f) {
        if (VERSION.SDK_INT >= 21) {
            view.setElevation(f);
        }
    }

    public static float getElevation(@NonNull View view) {
        return VERSION.SDK_INT >= 21 ? view.getElevation() : null;
    }

    public static void setTranslationZ(@NonNull View view, float f) {
        if (VERSION.SDK_INT >= 21) {
            view.setTranslationZ(f);
        }
    }

    public static float getTranslationZ(@NonNull View view) {
        return VERSION.SDK_INT >= 21 ? view.getTranslationZ() : null;
    }

    public static void setTransitionName(@NonNull View view, String str) {
        if (VERSION.SDK_INT >= 21) {
            view.setTransitionName(str);
            return;
        }
        if (sTransitionNameMap == null) {
            sTransitionNameMap = new WeakHashMap();
        }
        sTransitionNameMap.put(view, str);
    }

    @Nullable
    public static String getTransitionName(@NonNull View view) {
        if (VERSION.SDK_INT >= 21) {
            return view.getTransitionName();
        }
        if (sTransitionNameMap == null) {
            return null;
        }
        return (String) sTransitionNameMap.get(view);
    }

    public static int getWindowSystemUiVisibility(@NonNull View view) {
        return VERSION.SDK_INT >= 16 ? view.getWindowSystemUiVisibility() : null;
    }

    public static void requestApplyInsets(@NonNull View view) {
        if (VERSION.SDK_INT >= 20) {
            view.requestApplyInsets();
        } else if (VERSION.SDK_INT >= 16) {
            view.requestFitSystemWindows();
        }
    }

    @Deprecated
    public static void setChildrenDrawingOrderEnabled(ViewGroup viewGroup, boolean z) {
        if (sChildrenDrawingOrderMethod == null) {
            try {
                sChildrenDrawingOrderMethod = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", new Class[]{Boolean.TYPE});
            } catch (Throwable e) {
                Log.e(TAG, "Unable to find childrenDrawingOrderEnabled", e);
            }
            sChildrenDrawingOrderMethod.setAccessible(true);
        }
        try {
            sChildrenDrawingOrderMethod.invoke(viewGroup, new Object[]{Boolean.valueOf(z)});
        } catch (ViewGroup viewGroup2) {
            Log.e(TAG, "Unable to invoke childrenDrawingOrderEnabled", viewGroup2);
        } catch (ViewGroup viewGroup22) {
            Log.e(TAG, "Unable to invoke childrenDrawingOrderEnabled", viewGroup22);
        } catch (ViewGroup viewGroup222) {
            Log.e(TAG, "Unable to invoke childrenDrawingOrderEnabled", viewGroup222);
        }
    }

    public static boolean getFitsSystemWindows(@NonNull View view) {
        return VERSION.SDK_INT >= 16 ? view.getFitsSystemWindows() : null;
    }

    @Deprecated
    public static void setFitsSystemWindows(View view, boolean z) {
        view.setFitsSystemWindows(z);
    }

    @Deprecated
    public static void jumpDrawablesToCurrentState(View view) {
        view.jumpDrawablesToCurrentState();
    }

    public static void setOnApplyWindowInsetsListener(@NonNull View view, final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        if (VERSION.SDK_INT >= 21) {
            if (onApplyWindowInsetsListener == null) {
                view.setOnApplyWindowInsetsListener(null);
                return;
            }
            view.setOnApplyWindowInsetsListener(new OnApplyWindowInsetsListener() {
                public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                    return (WindowInsets) WindowInsetsCompat.unwrap(onApplyWindowInsetsListener.onApplyWindowInsets(view, WindowInsetsCompat.wrap(windowInsets)));
                }
            });
        }
    }

    public static WindowInsetsCompat onApplyWindowInsets(@NonNull View view, WindowInsetsCompat windowInsetsCompat) {
        if (VERSION.SDK_INT < 21) {
            return windowInsetsCompat;
        }
        windowInsetsCompat = (WindowInsets) WindowInsetsCompat.unwrap(windowInsetsCompat);
        view = view.onApplyWindowInsets(windowInsetsCompat);
        if (view != windowInsetsCompat) {
            windowInsetsCompat = new WindowInsets(view);
        }
        return WindowInsetsCompat.wrap(windowInsetsCompat);
    }

    public static WindowInsetsCompat dispatchApplyWindowInsets(@NonNull View view, WindowInsetsCompat windowInsetsCompat) {
        if (VERSION.SDK_INT < 21) {
            return windowInsetsCompat;
        }
        windowInsetsCompat = (WindowInsets) WindowInsetsCompat.unwrap(windowInsetsCompat);
        view = view.dispatchApplyWindowInsets(windowInsetsCompat);
        if (view != windowInsetsCompat) {
            windowInsetsCompat = new WindowInsets(view);
        }
        return WindowInsetsCompat.wrap(windowInsetsCompat);
    }

    @Deprecated
    public static void setSaveFromParentEnabled(View view, boolean z) {
        view.setSaveFromParentEnabled(z);
    }

    @Deprecated
    public static void setActivated(View view, boolean z) {
        view.setActivated(z);
    }

    public static boolean hasOverlappingRendering(@NonNull View view) {
        return VERSION.SDK_INT >= 16 ? view.hasOverlappingRendering() : true;
    }

    public static boolean isPaddingRelative(@NonNull View view) {
        return VERSION.SDK_INT >= 17 ? view.isPaddingRelative() : null;
    }

    public static void setBackground(@NonNull View view, @Nullable Drawable drawable) {
        if (VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static ColorStateList getBackgroundTintList(@NonNull View view) {
        if (VERSION.SDK_INT >= 21) {
            return view.getBackgroundTintList();
        }
        return view instanceof TintableBackgroundView ? ((TintableBackgroundView) view).getSupportBackgroundTintList() : null;
    }

    public static void setBackgroundTintList(@NonNull View view, ColorStateList colorStateList) {
        if (VERSION.SDK_INT >= 21) {
            view.setBackgroundTintList(colorStateList);
            if (VERSION.SDK_INT == 21) {
                Object obj;
                colorStateList = view.getBackground();
                if (view.getBackgroundTintList() == null) {
                    if (view.getBackgroundTintMode() == null) {
                        obj = null;
                        if (colorStateList != null && r0 != null) {
                            if (colorStateList.isStateful()) {
                                colorStateList.setState(view.getDrawableState());
                            }
                            view.setBackground(colorStateList);
                            return;
                        }
                    }
                }
                obj = 1;
                if (colorStateList != null) {
                }
            }
        } else if (view instanceof TintableBackgroundView) {
            ((TintableBackgroundView) view).setSupportBackgroundTintList(colorStateList);
        }
    }

    public static Mode getBackgroundTintMode(@NonNull View view) {
        if (VERSION.SDK_INT >= 21) {
            return view.getBackgroundTintMode();
        }
        return view instanceof TintableBackgroundView ? ((TintableBackgroundView) view).getSupportBackgroundTintMode() : null;
    }

    public static void setBackgroundTintMode(@NonNull View view, Mode mode) {
        if (VERSION.SDK_INT >= 21) {
            view.setBackgroundTintMode(mode);
            if (VERSION.SDK_INT == 21) {
                Object obj;
                mode = view.getBackground();
                if (view.getBackgroundTintList() == null) {
                    if (view.getBackgroundTintMode() == null) {
                        obj = null;
                        if (mode != null && r0 != null) {
                            if (mode.isStateful()) {
                                mode.setState(view.getDrawableState());
                            }
                            view.setBackground(mode);
                            return;
                        }
                    }
                }
                obj = 1;
                if (mode != null) {
                }
            }
        } else if (view instanceof TintableBackgroundView) {
            ((TintableBackgroundView) view).setSupportBackgroundTintMode(mode);
        }
    }

    public static void setNestedScrollingEnabled(@NonNull View view, boolean z) {
        if (VERSION.SDK_INT >= 21) {
            view.setNestedScrollingEnabled(z);
        } else if (view instanceof NestedScrollingChild) {
            ((NestedScrollingChild) view).setNestedScrollingEnabled(z);
        }
    }

    public static boolean isNestedScrollingEnabled(@NonNull View view) {
        if (VERSION.SDK_INT >= 21) {
            return view.isNestedScrollingEnabled();
        }
        return view instanceof NestedScrollingChild ? ((NestedScrollingChild) view).isNestedScrollingEnabled() : null;
    }

    public static boolean startNestedScroll(@NonNull View view, int i) {
        if (VERSION.SDK_INT >= 21) {
            return view.startNestedScroll(i);
        }
        return view instanceof NestedScrollingChild ? ((NestedScrollingChild) view).startNestedScroll(i) : null;
    }

    public static void stopNestedScroll(@NonNull View view) {
        if (VERSION.SDK_INT >= 21) {
            view.stopNestedScroll();
        } else if (view instanceof NestedScrollingChild) {
            ((NestedScrollingChild) view).stopNestedScroll();
        }
    }

    public static boolean hasNestedScrollingParent(@NonNull View view) {
        if (VERSION.SDK_INT >= 21) {
            return view.hasNestedScrollingParent();
        }
        return view instanceof NestedScrollingChild ? ((NestedScrollingChild) view).hasNestedScrollingParent() : null;
    }

    public static boolean dispatchNestedScroll(@NonNull View view, int i, int i2, int i3, int i4, @Nullable int[] iArr) {
        if (VERSION.SDK_INT >= 21) {
            return view.dispatchNestedScroll(i, i2, i3, i4, iArr);
        }
        return view instanceof NestedScrollingChild ? ((NestedScrollingChild) view).dispatchNestedScroll(i, i2, i3, i4, iArr) : null;
    }

    public static boolean dispatchNestedPreScroll(@NonNull View view, int i, int i2, @Nullable int[] iArr, @Nullable int[] iArr2) {
        if (VERSION.SDK_INT >= 21) {
            return view.dispatchNestedPreScroll(i, i2, iArr, iArr2);
        }
        return view instanceof NestedScrollingChild ? ((NestedScrollingChild) view).dispatchNestedPreScroll(i, i2, iArr, iArr2) : null;
    }

    public static boolean startNestedScroll(@NonNull View view, int i, int i2) {
        if (view instanceof NestedScrollingChild2) {
            return ((NestedScrollingChild2) view).startNestedScroll(i, i2);
        }
        return i2 == 0 ? startNestedScroll(view, i) : null;
    }

    public static void stopNestedScroll(@NonNull View view, int i) {
        if (view instanceof NestedScrollingChild2) {
            ((NestedScrollingChild2) view).stopNestedScroll(i);
        } else if (i == 0) {
            stopNestedScroll(view);
        }
    }

    public static boolean hasNestedScrollingParent(@NonNull View view, int i) {
        if (view instanceof NestedScrollingChild2) {
            ((NestedScrollingChild2) view).hasNestedScrollingParent(i);
        } else if (i == 0) {
            return hasNestedScrollingParent(view);
        }
        return null;
    }

    public static boolean dispatchNestedScroll(@NonNull View view, int i, int i2, int i3, int i4, @Nullable int[] iArr, int i5) {
        if (view instanceof NestedScrollingChild2) {
            return ((NestedScrollingChild2) view).dispatchNestedScroll(i, i2, i3, i4, iArr, i5);
        }
        return i5 == 0 ? dispatchNestedScroll(view, i, i2, i3, i4, iArr) : null;
    }

    public static boolean dispatchNestedPreScroll(@NonNull View view, int i, int i2, @Nullable int[] iArr, @Nullable int[] iArr2, int i3) {
        if (view instanceof NestedScrollingChild2) {
            return ((NestedScrollingChild2) view).dispatchNestedPreScroll(i, i2, iArr, iArr2, i3);
        }
        return i3 == 0 ? dispatchNestedPreScroll(view, i, i2, iArr, iArr2) : null;
    }

    public static boolean dispatchNestedFling(@NonNull View view, float f, float f2, boolean z) {
        if (VERSION.SDK_INT >= 21) {
            return view.dispatchNestedFling(f, f2, z);
        }
        return view instanceof NestedScrollingChild ? ((NestedScrollingChild) view).dispatchNestedFling(f, f2, z) : null;
    }

    public static boolean dispatchNestedPreFling(@NonNull View view, float f, float f2) {
        if (VERSION.SDK_INT >= 21) {
            return view.dispatchNestedPreFling(f, f2);
        }
        return view instanceof NestedScrollingChild ? ((NestedScrollingChild) view).dispatchNestedPreFling(f, f2) : null;
    }

    public static boolean isInLayout(@NonNull View view) {
        return VERSION.SDK_INT >= 18 ? view.isInLayout() : null;
    }

    public static boolean isLaidOut(@NonNull View view) {
        if (VERSION.SDK_INT >= 19) {
            return view.isLaidOut();
        }
        view = (view.getWidth() <= 0 || view.getHeight() <= null) ? null : true;
        return view;
    }

    public static boolean isLayoutDirectionResolved(@NonNull View view) {
        return VERSION.SDK_INT >= 19 ? view.isLayoutDirectionResolved() : null;
    }

    public static float getZ(@NonNull View view) {
        return VERSION.SDK_INT >= 21 ? view.getZ() : null;
    }

    public static void setZ(@NonNull View view, float f) {
        if (VERSION.SDK_INT >= 21) {
            view.setZ(f);
        }
    }

    public static void offsetTopAndBottom(@NonNull View view, int i) {
        if (VERSION.SDK_INT >= 23) {
            view.offsetTopAndBottom(i);
        } else if (VERSION.SDK_INT >= 21) {
            Rect emptyTempRect = getEmptyTempRect();
            int i2 = 0;
            ViewParent parent = view.getParent();
            if (parent instanceof View) {
                View view2 = (View) parent;
                emptyTempRect.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
                i2 = emptyTempRect.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()) ^ 1;
            }
            compatOffsetTopAndBottom(view, i);
            if (i2 != 0 && emptyTempRect.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()) != null) {
                ((View) parent).invalidate(emptyTempRect);
            }
        } else {
            compatOffsetTopAndBottom(view, i);
        }
    }

    private static void compatOffsetTopAndBottom(View view, int i) {
        view.offsetTopAndBottom(i);
        if (view.getVisibility() == 0) {
            tickleInvalidationFlag(view);
            view = view.getParent();
            if ((view instanceof View) != 0) {
                tickleInvalidationFlag(view);
            }
        }
    }

    public static void offsetLeftAndRight(@NonNull View view, int i) {
        if (VERSION.SDK_INT >= 23) {
            view.offsetLeftAndRight(i);
        } else if (VERSION.SDK_INT >= 21) {
            Rect emptyTempRect = getEmptyTempRect();
            int i2 = 0;
            ViewParent parent = view.getParent();
            if (parent instanceof View) {
                View view2 = (View) parent;
                emptyTempRect.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
                i2 = emptyTempRect.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()) ^ 1;
            }
            compatOffsetLeftAndRight(view, i);
            if (i2 != 0 && emptyTempRect.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()) != null) {
                ((View) parent).invalidate(emptyTempRect);
            }
        } else {
            compatOffsetLeftAndRight(view, i);
        }
    }

    private static void compatOffsetLeftAndRight(View view, int i) {
        view.offsetLeftAndRight(i);
        if (view.getVisibility() == 0) {
            tickleInvalidationFlag(view);
            view = view.getParent();
            if ((view instanceof View) != 0) {
                tickleInvalidationFlag(view);
            }
        }
    }

    private static void tickleInvalidationFlag(View view) {
        float translationY = view.getTranslationY();
        view.setTranslationY(1.0f + translationY);
        view.setTranslationY(translationY);
    }

    public static void setClipBounds(@NonNull View view, Rect rect) {
        if (VERSION.SDK_INT >= 18) {
            view.setClipBounds(rect);
        }
    }

    @Nullable
    public static Rect getClipBounds(@NonNull View view) {
        return VERSION.SDK_INT >= 18 ? view.getClipBounds() : null;
    }

    public static boolean isAttachedToWindow(@NonNull View view) {
        if (VERSION.SDK_INT >= 19) {
            return view.isAttachedToWindow();
        }
        return view.getWindowToken() != null ? true : null;
    }

    public static boolean hasOnClickListeners(@NonNull View view) {
        return VERSION.SDK_INT >= 15 ? view.hasOnClickListeners() : null;
    }

    public static void setScrollIndicators(@NonNull View view, int i) {
        if (VERSION.SDK_INT >= 23) {
            view.setScrollIndicators(i);
        }
    }

    public static void setScrollIndicators(@NonNull View view, int i, int i2) {
        if (VERSION.SDK_INT >= 23) {
            view.setScrollIndicators(i, i2);
        }
    }

    public static int getScrollIndicators(@NonNull View view) {
        return VERSION.SDK_INT >= 23 ? view.getScrollIndicators() : null;
    }

    public static void setPointerIcon(@NonNull View view, PointerIconCompat pointerIconCompat) {
        if (VERSION.SDK_INT >= 24) {
            view.setPointerIcon((PointerIcon) (pointerIconCompat != null ? pointerIconCompat.getPointerIcon() : null));
        }
    }

    @Nullable
    public static Display getDisplay(@NonNull View view) {
        if (VERSION.SDK_INT >= 17) {
            return view.getDisplay();
        }
        return isAttachedToWindow(view) ? ((WindowManager) view.getContext().getSystemService("window")).getDefaultDisplay() : null;
    }

    public static void setTooltipText(@NonNull View view, @Nullable CharSequence charSequence) {
        if (VERSION.SDK_INT >= 26) {
            view.setTooltipText(charSequence);
        }
    }

    public static boolean startDragAndDrop(@NonNull View view, ClipData clipData, DragShadowBuilder dragShadowBuilder, Object obj, int i) {
        if (VERSION.SDK_INT >= 24) {
            return view.startDragAndDrop(clipData, dragShadowBuilder, obj, i);
        }
        return view.startDrag(clipData, dragShadowBuilder, obj, i);
    }

    public static void cancelDragAndDrop(@NonNull View view) {
        if (VERSION.SDK_INT >= 24) {
            view.cancelDragAndDrop();
        }
    }

    public static void updateDragShadow(@NonNull View view, DragShadowBuilder dragShadowBuilder) {
        if (VERSION.SDK_INT >= 24) {
            view.updateDragShadow(dragShadowBuilder);
        }
    }

    public static int getNextClusterForwardId(@NonNull View view) {
        return VERSION.SDK_INT >= 26 ? view.getNextClusterForwardId() : -1;
    }

    public static void setNextClusterForwardId(@NonNull View view, int i) {
        if (VERSION.SDK_INT >= 26) {
            view.setNextClusterForwardId(i);
        }
    }

    public static boolean isKeyboardNavigationCluster(@NonNull View view) {
        return VERSION.SDK_INT >= 26 ? view.isKeyboardNavigationCluster() : null;
    }

    public static void setKeyboardNavigationCluster(@NonNull View view, boolean z) {
        if (VERSION.SDK_INT >= 26) {
            view.setKeyboardNavigationCluster(z);
        }
    }

    public static boolean isFocusedByDefault(@NonNull View view) {
        return VERSION.SDK_INT >= 26 ? view.isFocusedByDefault() : null;
    }

    public static void setFocusedByDefault(@NonNull View view, boolean z) {
        if (VERSION.SDK_INT >= 26) {
            view.setFocusedByDefault(z);
        }
    }

    public static View keyboardNavigationClusterSearch(@NonNull View view, View view2, int i) {
        return VERSION.SDK_INT >= 26 ? view.keyboardNavigationClusterSearch(view2, i) : null;
    }

    public static void addKeyboardNavigationClusters(@NonNull View view, @NonNull Collection<View> collection, int i) {
        if (VERSION.SDK_INT >= 26) {
            view.addKeyboardNavigationClusters(collection, i);
        }
    }

    public static boolean restoreDefaultFocus(@NonNull View view) {
        if (VERSION.SDK_INT >= 26) {
            return view.restoreDefaultFocus();
        }
        return view.requestFocus();
    }

    public static boolean hasExplicitFocusable(@NonNull View view) {
        if (VERSION.SDK_INT >= 26) {
            return view.hasExplicitFocusable();
        }
        return view.hasFocusable();
    }

    public static int generateViewId() {
        if (VERSION.SDK_INT >= 17) {
            return View.generateViewId();
        }
        int i;
        int i2;
        do {
            i = sNextGeneratedId.get();
            i2 = i + 1;
            if (i2 > MEASURED_SIZE_MASK) {
                i2 = 1;
            }
        } while (!sNextGeneratedId.compareAndSet(i, i2));
        return i;
    }

    public static void addOnUnhandledKeyEventListener(@NonNull View view, @NonNull OnUnhandledKeyEventListenerCompat onUnhandledKeyEventListenerCompat) {
        if (VERSION.SDK_INT >= 28) {
            Map map = (Map) view.getTag(C0014R.id.tag_unhandled_key_listeners);
            if (map == null) {
                map = new ArrayMap();
                view.setTag(C0014R.id.tag_unhandled_key_listeners, map);
            }
            OnUnhandledKeyEventListenerWrapper onUnhandledKeyEventListenerWrapper = new OnUnhandledKeyEventListenerWrapper(onUnhandledKeyEventListenerCompat);
            map.put(onUnhandledKeyEventListenerCompat, onUnhandledKeyEventListenerWrapper);
            view.addOnUnhandledKeyEventListener(onUnhandledKeyEventListenerWrapper);
            return;
        }
        ArrayList arrayList = (ArrayList) view.getTag(C0014R.id.tag_unhandled_key_listeners);
        if (arrayList == null) {
            arrayList = new ArrayList();
            view.setTag(C0014R.id.tag_unhandled_key_listeners, arrayList);
        }
        arrayList.add(onUnhandledKeyEventListenerCompat);
        if (arrayList.size() == 1) {
            UnhandledKeyEventManager.registerListeningView(view);
        }
    }

    public static void removeOnUnhandledKeyEventListener(@NonNull View view, @NonNull OnUnhandledKeyEventListenerCompat onUnhandledKeyEventListenerCompat) {
        if (VERSION.SDK_INT >= 28) {
            Map map = (Map) view.getTag(C0014R.id.tag_unhandled_key_listeners);
            if (map != null) {
                OnUnhandledKeyEventListener onUnhandledKeyEventListener = (OnUnhandledKeyEventListener) map.get(onUnhandledKeyEventListenerCompat);
                if (onUnhandledKeyEventListener != null) {
                    view.removeOnUnhandledKeyEventListener(onUnhandledKeyEventListener);
                }
                return;
            }
            return;
        }
        ArrayList arrayList = (ArrayList) view.getTag(C0014R.id.tag_unhandled_key_listeners);
        if (arrayList != null) {
            arrayList.remove(onUnhandledKeyEventListenerCompat);
            if (arrayList.size() == null) {
                UnhandledKeyEventManager.unregisterListeningView(view);
            }
        }
    }

    protected ViewCompat() {
    }

    @UiThread
    static boolean dispatchUnhandledKeyEventBeforeHierarchy(View view, KeyEvent keyEvent) {
        if (VERSION.SDK_INT >= 28) {
            return null;
        }
        return UnhandledKeyEventManager.at(view).preDispatch(keyEvent);
    }

    @UiThread
    static boolean dispatchUnhandledKeyEventBeforeCallback(View view, KeyEvent keyEvent) {
        if (VERSION.SDK_INT >= 28) {
            return null;
        }
        return UnhandledKeyEventManager.at(view).dispatch(view, keyEvent);
    }
}
