package android.support.v7.widget;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnHoverListener;
import android.view.View.OnLongClickListener;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;

@RestrictTo({Scope.LIBRARY_GROUP})
class TooltipCompatHandler implements OnLongClickListener, OnHoverListener, OnAttachStateChangeListener {
    private static final long HOVER_HIDE_TIMEOUT_MS = 15000;
    private static final long HOVER_HIDE_TIMEOUT_SHORT_MS = 3000;
    private static final long LONG_CLICK_HIDE_TIMEOUT_MS = 2500;
    private static final String TAG = "TooltipCompatHandler";
    private static TooltipCompatHandler sActiveHandler;
    private static TooltipCompatHandler sPendingHandler;
    private final View mAnchor;
    private int mAnchorX;
    private int mAnchorY;
    private boolean mFromTouch;
    private final Runnable mHideRunnable = new C02362();
    private final int mHoverSlop;
    private TooltipPopup mPopup;
    private final Runnable mShowRunnable = new C02351();
    private final CharSequence mTooltipText;

    /* renamed from: android.support.v7.widget.TooltipCompatHandler$1 */
    class C02351 implements Runnable {
        C02351() {
        }

        public void run() {
            TooltipCompatHandler.this.show(false);
        }
    }

    /* renamed from: android.support.v7.widget.TooltipCompatHandler$2 */
    class C02362 implements Runnable {
        C02362() {
        }

        public void run() {
            TooltipCompatHandler.this.hide();
        }
    }

    public void onViewAttachedToWindow(View view) {
    }

    public static void setTooltipText(View view, CharSequence charSequence) {
        if (sPendingHandler != null && sPendingHandler.mAnchor == view) {
            setPendingHandler(null);
        }
        if (TextUtils.isEmpty(charSequence)) {
            if (sActiveHandler != null && sActiveHandler.mAnchor == view) {
                sActiveHandler.hide();
            }
            view.setOnLongClickListener(null);
            view.setLongClickable(null);
            view.setOnHoverListener(null);
            return;
        }
        TooltipCompatHandler tooltipCompatHandler = new TooltipCompatHandler(view, charSequence);
    }

    private TooltipCompatHandler(View view, CharSequence charSequence) {
        this.mAnchor = view;
        this.mTooltipText = charSequence;
        this.mHoverSlop = ViewConfigurationCompat.getScaledHoverSlop(ViewConfiguration.get(this.mAnchor.getContext()));
        clearAnchorPos();
        this.mAnchor.setOnLongClickListener(this);
        this.mAnchor.setOnHoverListener(this);
    }

    public boolean onLongClick(View view) {
        this.mAnchorX = view.getWidth() / 2;
        this.mAnchorY = view.getHeight() / 2;
        show(true);
        return true;
    }

    public boolean onHover(View view, MotionEvent motionEvent) {
        if (this.mPopup != null && this.mFromTouch != null) {
            return false;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.mAnchor.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled() != null) {
            return false;
        }
        view = motionEvent.getAction();
        if (view != 7) {
            if (view == 10) {
                clearAnchorPos();
                hide();
            }
        } else if (!(this.mAnchor.isEnabled() == null || this.mPopup != null || updateAnchorPos(motionEvent) == null)) {
            setPendingHandler(this);
        }
        return false;
    }

    public void onViewDetachedFromWindow(View view) {
        hide();
    }

    void show(boolean z) {
        if (ViewCompat.isAttachedToWindow(this.mAnchor)) {
            long j;
            setPendingHandler(null);
            if (sActiveHandler != null) {
                sActiveHandler.hide();
            }
            sActiveHandler = this;
            this.mFromTouch = z;
            this.mPopup = new TooltipPopup(this.mAnchor.getContext());
            this.mPopup.show(this.mAnchor, this.mAnchorX, this.mAnchorY, this.mFromTouch, this.mTooltipText);
            this.mAnchor.addOnAttachStateChangeListener(this);
            if (this.mFromTouch) {
                j = LONG_CLICK_HIDE_TIMEOUT_MS;
            } else if (ViewCompat.getWindowSystemUiVisibility(this.mAnchor) & true) {
                j = HOVER_HIDE_TIMEOUT_SHORT_MS - ((long) ViewConfiguration.getLongPressTimeout());
            } else {
                j = HOVER_HIDE_TIMEOUT_MS - ((long) ViewConfiguration.getLongPressTimeout());
            }
            this.mAnchor.removeCallbacks(this.mHideRunnable);
            this.mAnchor.postDelayed(this.mHideRunnable, j);
        }
    }

    void hide() {
        if (sActiveHandler == this) {
            sActiveHandler = null;
            if (this.mPopup != null) {
                this.mPopup.hide();
                this.mPopup = null;
                clearAnchorPos();
                this.mAnchor.removeOnAttachStateChangeListener(this);
            } else {
                Log.e(TAG, "sActiveHandler.mPopup == null");
            }
        }
        if (sPendingHandler == this) {
            setPendingHandler(null);
        }
        this.mAnchor.removeCallbacks(this.mHideRunnable);
    }

    private static void setPendingHandler(TooltipCompatHandler tooltipCompatHandler) {
        if (sPendingHandler != null) {
            sPendingHandler.cancelPendingShow();
        }
        sPendingHandler = tooltipCompatHandler;
        if (sPendingHandler != null) {
            sPendingHandler.scheduleShow();
        }
    }

    private void scheduleShow() {
        this.mAnchor.postDelayed(this.mShowRunnable, (long) ViewConfiguration.getLongPressTimeout());
    }

    private void cancelPendingShow() {
        this.mAnchor.removeCallbacks(this.mShowRunnable);
    }

    private boolean updateAnchorPos(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        motionEvent = (int) motionEvent.getY();
        if (Math.abs(x - this.mAnchorX) <= this.mHoverSlop && Math.abs(motionEvent - this.mAnchorY) <= this.mHoverSlop) {
            return null;
        }
        this.mAnchorX = x;
        this.mAnchorY = motionEvent;
        return true;
    }

    private void clearAnchorPos() {
        this.mAnchorX = Integer.MAX_VALUE;
        this.mAnchorY = Integer.MAX_VALUE;
    }
}
