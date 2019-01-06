package android.support.v4.view;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class AbsSavedState implements Parcelable {
    public static final Creator<AbsSavedState> CREATOR = new C01462();
    public static final AbsSavedState EMPTY_STATE = new C02951();
    private final Parcelable mSuperState;

    /* renamed from: android.support.v4.view.AbsSavedState$2 */
    static class C01462 implements ClassLoaderCreator<AbsSavedState> {
        C01462() {
        }

        public AbsSavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
            if (parcel.readParcelable(classLoader) == null) {
                return AbsSavedState.EMPTY_STATE;
            }
            throw new IllegalStateException("superState must be null");
        }

        public AbsSavedState createFromParcel(Parcel parcel) {
            return createFromParcel(parcel, null);
        }

        public AbsSavedState[] newArray(int i) {
            return new AbsSavedState[i];
        }
    }

    /* renamed from: android.support.v4.view.AbsSavedState$1 */
    static class C02951 extends AbsSavedState {
        C02951() {
            super();
        }
    }

    public int describeContents() {
        return 0;
    }

    private AbsSavedState() {
        this.mSuperState = null;
    }

    protected AbsSavedState(@NonNull Parcelable parcelable) {
        if (parcelable != null) {
            if (parcelable == EMPTY_STATE) {
                parcelable = null;
            }
            this.mSuperState = parcelable;
            return;
        }
        throw new IllegalArgumentException("superState must not be null");
    }

    protected AbsSavedState(@NonNull Parcel parcel) {
        this(parcel, null);
    }

    protected AbsSavedState(@NonNull Parcel parcel, @Nullable ClassLoader classLoader) {
        parcel = parcel.readParcelable(classLoader);
        if (parcel == null) {
            parcel = EMPTY_STATE;
        }
        this.mSuperState = parcel;
    }

    @Nullable
    public final Parcelable getSuperState() {
        return this.mSuperState;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mSuperState, i);
    }
}
