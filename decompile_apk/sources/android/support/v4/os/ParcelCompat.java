package android.support.v4.os;

import android.os.Parcel;

public final class ParcelCompat {
    public static boolean readBoolean(Parcel parcel) {
        return parcel.readInt() != null ? true : null;
    }

    public static void writeBoolean(Parcel parcel, boolean z) {
        parcel.writeInt(z);
    }

    private ParcelCompat() {
    }
}
