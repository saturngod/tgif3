package android.arch.lifecycle;

import android.arch.core.util.Function;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Transformations {
    private Transformations() {
    }

    @MainThread
    public static <X, Y> LiveData<Y> map(@NonNull LiveData<X> liveData, @NonNull final Function<X, Y> function) {
        final LiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new Observer<X>() {
            public void onChanged(@Nullable X x) {
                mediatorLiveData.setValue(function.apply(x));
            }
        });
        return mediatorLiveData;
    }

    @MainThread
    public static <X, Y> LiveData<Y> switchMap(@NonNull LiveData<X> liveData, @NonNull final Function<X, LiveData<Y>> function) {
        final LiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new Observer<X>() {
            LiveData<Y> mSource;

            /* renamed from: android.arch.lifecycle.Transformations$2$1 */
            class C02691 implements Observer<Y> {
                C02691() {
                }

                public void onChanged(@Nullable Y y) {
                    mediatorLiveData.setValue(y);
                }
            }

            public void onChanged(@Nullable X x) {
                LiveData liveData = (LiveData) function.apply(x);
                if (this.mSource != liveData) {
                    if (this.mSource != null) {
                        mediatorLiveData.removeSource(this.mSource);
                    }
                    this.mSource = liveData;
                    if (this.mSource != null) {
                        mediatorLiveData.addSource(this.mSource, new C02691());
                    }
                }
            }
        });
        return mediatorLiveData;
    }
}
