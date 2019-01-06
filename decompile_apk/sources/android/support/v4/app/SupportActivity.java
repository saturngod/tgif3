package android.support.v4.app;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Lifecycle.State;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ReportFragment;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.view.KeyEventDispatcher;
import android.support.v4.view.KeyEventDispatcher.Component;
import android.view.KeyEvent;
import android.view.View;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: ComponentActivity */
public class SupportActivity extends Activity implements LifecycleOwner, Component {
    private SimpleArrayMap<Class<? extends ExtraData>, ExtraData> mExtraDataMap = new SimpleArrayMap();
    private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    @RestrictTo({Scope.LIBRARY_GROUP})
    /* compiled from: ComponentActivity */
    public static class ExtraData {
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void putExtraData(ExtraData extraData) {
        this.mExtraDataMap.put(extraData.getClass(), extraData);
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ReportFragment.injectIfNeededIn(this);
    }

    @CallSuper
    protected void onSaveInstanceState(Bundle bundle) {
        this.mLifecycleRegistry.markState(State.CREATED);
        super.onSaveInstanceState(bundle);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public <T extends ExtraData> T getExtraData(Class<T> cls) {
        return (ExtraData) this.mExtraDataMap.get(cls);
    }

    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !KeyEventDispatcher.dispatchBeforeHierarchy(decorView, keyEvent)) {
            return super.dispatchKeyShortcutEvent(keyEvent);
        }
        return true;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !KeyEventDispatcher.dispatchBeforeHierarchy(decorView, keyEvent)) {
            return KeyEventDispatcher.dispatchKeyEvent(this, decorView, this, keyEvent);
        }
        return true;
    }
}
