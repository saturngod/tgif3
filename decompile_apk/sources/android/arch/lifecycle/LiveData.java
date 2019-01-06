package android.arch.lifecycle;

import android.arch.core.executor.ArchTaskExecutor;
import android.arch.core.internal.SafeIterableMap;
import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.Lifecycle.State;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Iterator;
import java.util.Map.Entry;

public abstract class LiveData<T> {
    private static final Object NOT_SET = new Object();
    static final int START_VERSION = -1;
    private int mActiveCount = 0;
    private volatile Object mData = NOT_SET;
    private final Object mDataLock = new Object();
    private boolean mDispatchInvalidated;
    private boolean mDispatchingValue;
    private SafeIterableMap<Observer<T>, ObserverWrapper> mObservers = new SafeIterableMap();
    private volatile Object mPendingData = NOT_SET;
    private final Runnable mPostValueRunnable = new C00081();
    private int mVersion = -1;

    /* renamed from: android.arch.lifecycle.LiveData$1 */
    class C00081 implements Runnable {
        C00081() {
        }

        public void run() {
            Object access$100;
            synchronized (LiveData.this.mDataLock) {
                access$100 = LiveData.this.mPendingData;
                LiveData.this.mPendingData = LiveData.NOT_SET;
            }
            LiveData.this.setValue(access$100);
        }
    }

    private abstract class ObserverWrapper {
        boolean mActive;
        int mLastVersion = -1;
        final Observer<T> mObserver;

        void detachObserver() {
        }

        boolean isAttachedTo(LifecycleOwner lifecycleOwner) {
            return false;
        }

        abstract boolean shouldBeActive();

        ObserverWrapper(Observer<T> observer) {
            this.mObserver = observer;
        }

        void activeStateChanged(boolean z) {
            if (z != this.mActive) {
                this.mActive = z;
                int i = 1;
                z = !LiveData.this.mActiveCount;
                LiveData liveData = LiveData.this;
                int access$300 = liveData.mActiveCount;
                if (!this.mActive) {
                    i = -1;
                }
                liveData.mActiveCount = access$300 + i;
                if (z && this.mActive) {
                    LiveData.this.onActive();
                }
                if (!(LiveData.this.mActiveCount || this.mActive)) {
                    LiveData.this.onInactive();
                }
                if (this.mActive) {
                    LiveData.this.dispatchingValue(this);
                }
            }
        }
    }

    private class AlwaysActiveObserver extends ObserverWrapper {
        boolean shouldBeActive() {
            return true;
        }

        AlwaysActiveObserver(Observer<T> observer) {
            super(observer);
        }
    }

    class LifecycleBoundObserver extends ObserverWrapper implements GenericLifecycleObserver {
        @NonNull
        final LifecycleOwner mOwner;

        LifecycleBoundObserver(@NonNull LifecycleOwner lifecycleOwner, Observer<T> observer) {
            super(observer);
            this.mOwner = lifecycleOwner;
        }

        boolean shouldBeActive() {
            return this.mOwner.getLifecycle().getCurrentState().isAtLeast(State.STARTED);
        }

        public void onStateChanged(LifecycleOwner lifecycleOwner, Event event) {
            if (this.mOwner.getLifecycle().getCurrentState() == State.DESTROYED) {
                LiveData.this.removeObserver(this.mObserver);
            } else {
                activeStateChanged(shouldBeActive());
            }
        }

        boolean isAttachedTo(LifecycleOwner lifecycleOwner) {
            return this.mOwner == lifecycleOwner ? true : null;
        }

        void detachObserver() {
            this.mOwner.getLifecycle().removeObserver(this);
        }
    }

    protected void onActive() {
    }

    protected void onInactive() {
    }

    private void considerNotify(ObserverWrapper observerWrapper) {
        if (!observerWrapper.mActive) {
            return;
        }
        if (!observerWrapper.shouldBeActive()) {
            observerWrapper.activeStateChanged(false);
        } else if (observerWrapper.mLastVersion < this.mVersion) {
            observerWrapper.mLastVersion = this.mVersion;
            observerWrapper.mObserver.onChanged(this.mData);
        }
    }

    private void dispatchingValue(@Nullable ObserverWrapper observerWrapper) {
        if (this.mDispatchingValue) {
            this.mDispatchInvalidated = true;
            return;
        }
        this.mDispatchingValue = true;
        do {
            this.mDispatchInvalidated = false;
            if (observerWrapper == null) {
                Iterator iteratorWithAdditions = this.mObservers.iteratorWithAdditions();
                while (iteratorWithAdditions.hasNext()) {
                    considerNotify((ObserverWrapper) ((Entry) iteratorWithAdditions.next()).getValue());
                    if (this.mDispatchInvalidated) {
                        break;
                    }
                }
            }
            considerNotify(observerWrapper);
            observerWrapper = null;
        } while (this.mDispatchInvalidated);
        this.mDispatchingValue = false;
    }

    @MainThread
    public void observe(@NonNull LifecycleOwner lifecycleOwner, @NonNull Observer<T> observer) {
        if (lifecycleOwner.getLifecycle().getCurrentState() != State.DESTROYED) {
            LifecycleObserver lifecycleBoundObserver = new LifecycleBoundObserver(lifecycleOwner, observer);
            ObserverWrapper observerWrapper = (ObserverWrapper) this.mObservers.putIfAbsent(observer, lifecycleBoundObserver);
            if (observerWrapper != null) {
                if (!observerWrapper.isAttachedTo(lifecycleOwner)) {
                    throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
                }
            }
            if (observerWrapper == null) {
                lifecycleOwner.getLifecycle().addObserver(lifecycleBoundObserver);
            }
        }
    }

    @MainThread
    public void observeForever(@NonNull Observer<T> observer) {
        AlwaysActiveObserver alwaysActiveObserver = new AlwaysActiveObserver(observer);
        ObserverWrapper observerWrapper = (ObserverWrapper) this.mObservers.putIfAbsent(observer, alwaysActiveObserver);
        if (observerWrapper != null) {
            if (observerWrapper instanceof LifecycleBoundObserver) {
                throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
            }
        }
        if (observerWrapper == null) {
            alwaysActiveObserver.activeStateChanged(true);
        }
    }

    @MainThread
    public void removeObserver(@NonNull Observer<T> observer) {
        assertMainThread("removeObserver");
        ObserverWrapper observerWrapper = (ObserverWrapper) this.mObservers.remove(observer);
        if (observerWrapper != null) {
            observerWrapper.detachObserver();
            observerWrapper.activeStateChanged(false);
        }
    }

    @MainThread
    public void removeObservers(@NonNull LifecycleOwner lifecycleOwner) {
        assertMainThread("removeObservers");
        Iterator it = this.mObservers.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (((ObserverWrapper) entry.getValue()).isAttachedTo(lifecycleOwner)) {
                removeObserver((Observer) entry.getKey());
            }
        }
    }

    protected void postValue(T t) {
        synchronized (this.mDataLock) {
            Object obj = this.mPendingData == NOT_SET ? 1 : null;
            this.mPendingData = t;
        }
        if (obj != null) {
            ArchTaskExecutor.getInstance().postToMainThread(this.mPostValueRunnable);
        }
    }

    @MainThread
    protected void setValue(T t) {
        assertMainThread("setValue");
        this.mVersion++;
        this.mData = t;
        dispatchingValue(null);
    }

    @Nullable
    public T getValue() {
        T t = this.mData;
        return t != NOT_SET ? t : null;
    }

    int getVersion() {
        return this.mVersion;
    }

    public boolean hasObservers() {
        return this.mObservers.size() > 0;
    }

    public boolean hasActiveObservers() {
        return this.mActiveCount > 0;
    }

    private static void assertMainThread(String str) {
        if (!ArchTaskExecutor.getInstance().isMainThread()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Cannot invoke ");
            stringBuilder.append(str);
            stringBuilder.append(" on a background");
            stringBuilder.append(" thread");
            throw new IllegalStateException(stringBuilder.toString());
        }
    }
}
